package me.CarsCupcake.SkyblockRemake.utils.runnable;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SequencedRunnable {
    private final List<RunnableDispacher> runnables = new ArrayList<>();
    private final List<Runnable> cancelAction = new ArrayList<>();
    @Getter
    private boolean cancelled = false;
    private int current = 0;
    public void addCustom(RunnableDispacher dispacher) {
        runnables.add(dispacher);
    }
    public void addRepeatingRunnable(Runnable runnable, int delay, int repeat) {
        addCustom(new BukkitRunnableDispatcher(new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }, repeat, delay));
    }
    public void addRepeatingRunnable(Runnable runnable, int repeat) {
        addRepeatingRunnable(runnable, 0, repeat);
    }
    public void addDelayedRunnable(Runnable runnable, int delay) {
        addRepeatingRunnable(runnable, delay, -1);
    }
    public void start() {
        runnables.get(0).start();
    }
    public void setCancelled() {
        if (cancelled) return;
        cancelled = true;
        if (runnables.get(current).isNotDone()) runnables.get(current).stop();
        for (Runnable runnable : cancelAction) runnable.run();
    }
    public void addCancelAction(Runnable runnable) {
        cancelAction.add(runnable);
    }

    /**
     * Next step in the runnables
     * @return if the sequence has finished
     */
    public boolean next() {
        if (runnables.get(current).isNotDone()) runnables.get(current).stop();
        current++;
        if (current == runnables.size()) {
            cancelled = true;
            setCancelled();
            return true;
        }
        runnables.get(current).start();
        return false;
    }
    public static class BukkitRunnableDispatcher implements RunnableDispacher {
        private final long repeat;
        private final long delay;
        private final BukkitRunnable runnable;
        public BukkitRunnableDispatcher(BukkitRunnable runnable, long repeat, long delay) {
            this.runnable = runnable;
            this.repeat = repeat;
            this.delay = delay;
        }
        @Override
        public void start() {
            if (repeat >= 0) runnable.runTaskTimer(Main.getMain(), delay, repeat);
            else  runnable.runTaskLater(Main.getMain(), delay);
        }

        @Override
        public void stop() {
            runnable.cancel();
        }

        @Override
        public boolean isNotDone() {
            return !runnable.isCancelled();
        }
    }
}
