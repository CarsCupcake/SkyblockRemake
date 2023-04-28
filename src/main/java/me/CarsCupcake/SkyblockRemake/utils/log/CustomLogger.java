package me.CarsCupcake.SkyblockRemake.utils.log;

import java.util.Date;
import java.text.SimpleDateFormat;

public class CustomLogger
{
    String prefix;

    public CustomLogger(final String prefix) {
        this.prefix = "[" + prefix + "] ";
    }

    public void info(final String s) {
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + this.prefix + "[INFO] " + s);
    }

    public void println(final String s) {
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + this.prefix + "[INFO] " + s);
    }

    public void prnt(final String s) {
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + this.prefix + "[INFO] " + s);
    }

    public void warn(final String s) {
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + this.prefix + "[WARN] " + s);
    }

    public void err(final String s) {
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        System.out.println("[" + formatter.format(date) + "] " + this.prefix + "[ERROR] " + s);
    }

    public void error(final String s) {
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        System.err.println("[" + formatter.format(date) + "] " + this.prefix + "[ERROR] " + s);
    }
}
