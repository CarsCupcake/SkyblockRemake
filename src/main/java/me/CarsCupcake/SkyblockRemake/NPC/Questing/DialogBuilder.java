package me.CarsCupcake.SkyblockRemake.NPC.Questing;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.Container;
import me.CarsCupcake.SkyblockRemake.utils.Sound;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DialogBuilder implements Cloneable{
    private List<Action> actions = new LinkedList<>();
    private final Sound sound;
    private int speed = 20;
    @Getter
    private final Container<SkyblockPlayer> playerContainer = new Container<>();
    private Runnable endOfDialog;

    public DialogBuilder(Sound dialogTalkSound) {
        this.sound = dialogTalkSound;
    }

    public DialogRunner build(SkyblockPlayer player) {
        Assert.state(!actions.isEmpty(), "There are no actions");
        this.playerContainer.setContent(player);
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
    public final DialogBuilder addDialogOption(Bundle<String, DialogBuilder>... dialogOptions) {
        List<Bundle<String, Runnable>> runners = new ArrayList<>();
        for (Bundle<String, DialogBuilder> opt : dialogOptions){
            runners.add(new Bundle<>(opt.getFirst(), () -> opt.getLast().build(playerContainer.getContent())));
        }
        actions.add(new SelectionAction(runners.toArray(new Bundle[0])));
        return this;
    }

    /**
     * Sets up a survey
     * @param headline the headline
     * @param answers are the possible responses
     * @param right are the right answers (remember 0 index!)
     * @param onRight is what should happen if it right
     * @param onWrong is what should happen if its wrong
     * @return the instance
     */
    public DialogBuilder addTestingSelection(String headline, List<String> answers, List<Integer> right, Runnable onRight, Runnable onWrong){
        int i = 0;
        List<QuestOption> options = new ArrayList<>();
        for (String s : answers){
            options.add(new BasicSelectOption(s, ((right.contains(i)) ? onRight : onWrong)));
            i++;
        }
        actions.add(new TestingSelectionOption(options, headline));
        return this;
    }
    public DialogBuilder addTestingSelection(String headline, HashMap<String, Runnable> responses){
        List<QuestOption> options = new ArrayList<>();
        responses.forEach((s, runnable) -> options.add(new BasicSelectOption(s, runnable)));
        actions.add(new TestingSelectionOption(options, headline));
        return this;
    }
    public DialogBuilder addResponse(String... responses){
        List<Bundle<String, Runnable>> runners = new ArrayList<>();
        for (String str : responses){
            runners.add(new Bundle<>(str, () -> {
                if(playerContainer.getContent().getDialog() != null)
                    playerContainer.getContent().getDialog().resume();
            }));
        }
        actions.add(new SelectionAction(runners.toArray(new Bundle[0])));
        return this;
    }
    @SafeVarargs
    public final DialogBuilder addSelection(Bundle<String, Runnable>... options){
        actions.add(new SelectionAction(options));
        return this;
    }
    public DialogBuilder onDialogEnd(Runnable runnable){
        endOfDialog = runnable;
        return this;
    }
    public DialogBuilder giveItems(ItemManager manager, int count){
        return addSelection(new Bundle<>("Give " + count + "x " + manager.name, () -> {
            if(Tools.itemsInInv(playerContainer.getContent(), manager) >= count){
                Tools.removeItemsFromInventory(playerContainer.getContent(), manager, count);
                if(playerContainer.getContent().getDialog() != null) playerContainer.getContent().getDialog().resume();
            }else playerContainer.getContent().sendMessage("§cYou do not have enouth items!");

        }));
    }

    @Override
    public DialogBuilder clone() {
        DialogBuilder builder;
        try {
            builder = (DialogBuilder) super.clone();
        } catch (CloneNotSupportedException cantHappen) {
            throw new InternalError("Bad clone from Location2d");
        }
        builder.actions = this.actions;
        return builder;
    }

    public class DialogRunner implements Runnable {
        private BukkitTask task;
        int i = 0;

        public DialogRunner() {
            if(playerContainer.getContent().getDialog() != null) return;
            playerContainer.getContent().setDialog(this);
            resume();
        }

        @Override
        public void run() {
            sound.play(playerContainer.getContent());
            Action action = actions.get(i);
            if (action.run(playerContainer.getContent())) {
                cancel(false);
            }
            i++;
            if (actions.size() == i) {
                cancel(true);
            }
        }
        public synchronized void cancel(boolean end) throws IllegalStateException {
            if(end){
                if(endOfDialog != null) endOfDialog.run();
                playerContainer.getContent().setDialog(null);
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
    private record SelectionAction(Bundle<String, Runnable>[] options) implements Action{

        @Override
        public boolean run(SkyblockPlayer player) {
            List<BasicSelectOption> optionList = new ArrayList<>();
            for (Bundle<String, Runnable> option : options()){
                optionList.add(new BasicSelectOption(option.getFirst(),
                    option.getLast()
                ));
            }
            new Selection(optionList.toArray(new BasicSelectOption[0]), player);
            return true;
        }
    }
    private record TestingSelectionOption(List<QuestOption> options, String desc) implements Action {

        @Override
        public boolean run(SkyblockPlayer player) {
            new TestingSelection(options, desc, player);
            return true;
        }
    }
}
