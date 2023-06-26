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
        debug(s, true);
    }
    public void debug(final String s, boolean withTrace) {
        if (!debug) return;
        if(withTrace)
        try {
            throw new RuntimeException();
        } catch (Exception tool) {
            System.out.println(" [" + tool.getStackTrace()[2] + "] " + this.prefix + " [DEBUG] " + s);
        }
        else System.out.println(this.prefix + " [DEBUG] " + s);
    }
}
