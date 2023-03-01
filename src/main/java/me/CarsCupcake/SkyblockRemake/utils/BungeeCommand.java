package me.CarsCupcake.SkyblockRemake.utils;


import java.util.Arrays;
import java.util.HashMap;

public abstract class BungeeCommand {
    private static final HashMap<String, BungeeCommand> commandHashMap = new HashMap<>();
    public abstract void onCommand(String[] args);
    public static void sendCommand(String cmd){
        Assert.notNull(cmd);
        String[] values = cmd.split(":");
        Assert.isTrue(values.length >= 1);
        if(!commandHashMap.containsValue(values[0]))
            return;

        if(values.length == 1) {
            commandHashMap.get(values[0]).onCommand(new String[0]);
            return;
        }

        commandHashMap.get(values[0]).onCommand(Arrays.copyOfRange(values, 2, values.length));
    }

    public static void registerListener(String cmd, BungeeCommand command){
        commandHashMap.put(cmd, command);
    }
}
