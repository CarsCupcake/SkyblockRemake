package me.CarsCupcake.SkyblockRemake.utils;

public class ThreadHalt {
    protected final Object read;
    private int runs = 0;

    public ThreadHalt() {
        read = new Object();
    }

    public void run() {
        runs++;
    }

    public void await() throws InterruptedException {
        synchronized (read) {
            while (runs == 0) {
                runs--;
                read.wait();
            }
        }
    }
}
