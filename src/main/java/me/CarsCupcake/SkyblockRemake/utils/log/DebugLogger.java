package me.CarsCupcake.SkyblockRemake.utils.log;
public class DebugLogger {
    public static boolean debug = false;
    String prefix;

    public DebugLogger(final String prefix) {
        this.prefix =  prefix;

    }

    public static void setDebug(boolean debug) {
        DebugLogger.debug = debug;
    }

    public void debug(final String s) {
        if (!debug) return;
        try {
            throw new RuntimeException();
        } catch (Exception tool) {
            System.out.println(" \n[" + tool.getStackTrace()[1] + "] " + this.prefix + " [DEBUG] " + s);
        }
    }
}
