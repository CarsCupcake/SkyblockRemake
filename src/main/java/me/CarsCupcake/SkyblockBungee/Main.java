package me.CarsCupcake.SkyblockBungee;

import lombok.Getter;
import me.CarsCupcake.SkyblockBungee.command.WarpCommand;
import me.CarsCupcake.SkyblockBungee.features.Time;
import me.CarsCupcake.SkyblockBungee.listeners.OnPlayerJoin;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.utils.maps.MapList;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Plugin implements Listener {
    @Getter
    private static final MapList<ServerType, ServerInfo> orderdServer = new MapList<>();
    @Getter
    private static final Map<ServerInfo, ServerType> servers = new HashMap<>();
    public static BungeeConfig config;
    @Getter
    private static Main main;
    @Override
    public void onEnable() {
        main = this;
        config = new BungeeConfig("config", false);
        this.getProxy().registerChannel("skyblock:main");
        this.getProxy().getPluginManager().registerListener(this, this);
        this.getProxy().getPluginManager().registerListener(this, new OnPlayerJoin());

        this.getProxy().getPluginManager().registerCommand(this, new WarpCommand());
    }

    @Override
    public void onDisable() {

    }
    public static void sendMessage(String message, ServerInfo server) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        server.sendData("skyblock:main", stream.toByteArray());
        // Alright quick note here guys : ProxiedPlayer.sendData() [b]WILL NOT WORK[/b]. It will send it to the client, and not to the server the client is connected. See the difference ? You need to send to Server or ServerInfo.
    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent ev) {

        if (!ev.getTag().equals("skyblock:main") ){
            return;
        }

        if (!(ev.getSender() instanceof Server)) {
            return;
        }

        ByteArrayInputStream stream = new ByteArrayInputStream(ev.getData());
        DataInputStream in = new DataInputStream(stream);
        try {
            String s = in.readUTF();
            if(s.equals("add")) {
                Time.addConnection(ev.getSender());
                ServerType type = ServerType.getFromString(in.readUTF());
                orderdServer.add(type, ((Server) ev.getSender()).getInfo());
                servers.put(((Server) ev.getSender()).getInfo(), type);
            }
            if(s.equals("removeTimer"))
                Time.removeConnection(ev.getSender());
            if(s.equals("requestTime")){
                if(ev.getSender() instanceof Server server) {
                    sendMessage("time:" + Time.timeString(), server.getInfo());
                    sendMessage("season:" + Time.getSeason().getId(), server.getInfo());
                }
            }


        } catch (IOException e) {
            e.printStackTrace(System.err);
        }



    }
}
