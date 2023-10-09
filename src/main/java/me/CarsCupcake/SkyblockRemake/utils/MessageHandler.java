package me.CarsCupcake.SkyblockRemake.utils;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class MessageHandler implements PluginMessageListener {

    public void onPluginMessageReceived(String channel, @NotNull Player player, byte[] bytes) {

        if (!channel.equals("skyblock:main")) {
            return;
        }

        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        DataInputStream in = new DataInputStream(stream);
        try {

            String s = in.readUTF();
            if (s.equals("close"))
                Bukkit.shutdown();

            if(s.startsWith("time:")){
                Time.getInstance().deserialize(s.split(":")[1]);
            }
            if(s.startsWith("season:")){
                Time.getInstance().setSeason(Integer.parseInt(s.split(":")[1]));
            }
            if(s.equals("askTime") && Main.getMain().getConfig().getBoolean("bungeeCordTime", false)) {
                sendMessage("registerTimer");
                sendMessage("requestTime");
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
    public static void sendMessage(String message, String... s) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(message);
            for (String ss : s) out.writeUTF(ss);
        } catch (IOException e) {

            e.printStackTrace(System.err);
        }

        Main.getMain().getServer().sendPluginMessage(Main.getMain(), "skyblock:main", stream.toByteArray());
    }
}
