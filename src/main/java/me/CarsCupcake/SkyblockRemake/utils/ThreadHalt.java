package me.CarsCupcake.SkyblockRemake.utils;

public class ThreadHalt {
    protected final Object read;
    private int runs = 0;

    public ThreadHalt() {
        read = new Object();
    }

    public void run() {
        runs++;
        synchronized (read) {
            read.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (read) {
            if (runs != 0) runs--;
            else read.wait();
        }
    }
}
