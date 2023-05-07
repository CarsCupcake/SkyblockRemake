package me.CarsCupcake.SkyblockRemake.NPC.Questing;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.Sound;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DialogBuilder {
    private final List<Action> actions = new LinkedList<>();
    private final Sound sound;
    private int speed = 20;
    private SkyblockPlayer player;

    public DialogBuilder(Sound dialogTalkSound) {
        this.sound = dialogTalkSound;
    }

    public DialogRunner build(SkyblockPlayer player) {
        Assert.state(!actions.isEmpty(), "There are no actions");
        this.player = player;
        return new DialogRunner();
    }

    public DialogBuilder setSpeed(int ticks) {
        this.speed = ticks;
        return this;
    }

    private String prefix = "";

    public DialogBuilder withTextPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public DialogBuilder addText(String s) {
        actions.add(new TextAction(prefix + s));
        return this;
    }

    @SafeVarargs
    public final DialogBuilder dialogOption(Bundle<String, DialogBuilder>... dialogOptions) {
        List<Bundle<String, Runnable>> runners = new ArrayList<>();
        for (Bundle<String, DialogBuilder> opt : dialogOptions){
            runners.add(new Bundle<>(opt.getFirst(), () -> opt.getLast().build(player)));
        }
        actions.add(new SelectionAction(runners.toArray(new Bundle[0])));
        return this;
    }

    public class DialogRunner implements Runnable {
        private BukkitTask task;
        int i = 0;

        public DialogRunner() {
            if(player.getDialog() != null) return;
            player.setDialog(this);
            resume();
        }

        @Override
        public void run() {
            sound.play(player);
            Action action = actions.get(i);
            if (action.run(player)) {
                cancel(false);
            }
            i++;
            if (actions.size() == i) cancel(true);
        }
        public synchronized void cancel(boolean end) throws IllegalStateException {
            if(end){
                player.setDialog(null);
            }
            if(task == null || task.isCancelled()) return;
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;

        }
        public void resume(){
            if(task != null && !task.isCancelled()) Bukkit.getScheduler().cancelTask(task.getTaskId());
           task = Bukkit.getScheduler().runTaskTimer(Main.getMain(), this, 0, speed);
        }
    }

    private interface Action {
        /**
         * Runs the action
         *
         * @return If it should be paused
         */
        boolean run(SkyblockPlayer player);
    }

    private record TextAction(String message) implements Action {

        @Override
        public boolean run(SkyblockPlayer player) {
            player.sendMessage(message);
            return false;
        }
    }
    private record SelectionAction(Bundle<String, Runnable>... options) implements Action{

        @Override
        public boolean run(SkyblockPlayer player) {
            List<BasicSelectOption> optionList = new ArrayList<>();
            for (Bundle<String, Runnable> option : options()){
                optionList.add(new BasicSelectOption(option.getFirst(), () -> {
                    option.getLast().run();
                    if(player.getDialog() != null) player.getDialog().resume();
                }));
            }
            new Selection(optionList.toArray(new BasicSelectOption[0]), player);
            return true;
        }
    }
}
