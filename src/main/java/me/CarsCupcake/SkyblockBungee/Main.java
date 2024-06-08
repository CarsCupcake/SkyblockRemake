package me.CarsCupcake.SkyblockBungee;

import lombok.Getter;
import me.CarsCupcake.SkyblockBungee.command.MessangerChannelTest;
import me.CarsCupcake.SkyblockBungee.command.WarpCommand;
import me.CarsCupcake.SkyblockBungee.features.Time;
import me.CarsCupcake.SkyblockBungee.listeners.OnPlayerJoin;
import me.CarsCupcake.SkyblockBungee.net.ServerGeneralPort;
import me.CarsCupcake.SkyblockBungee.net.ServerHandler;
import me.CarsCupcake.SkyblockBungee.features.ServerType;
import me.CarsCupcake.SkyblockRemake.utils.maps.MapList;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends Plugin {
    @Getter
    private static final MapList<ServerType, ServerInfo> orderdServer = new MapList<>();
    @Getter
    private static final Map<Integer, ServerType> servers = new HashMap<>();
    public static BungeeConfig config;
    @Getter
    private static Main main;

    @Override
    public void onEnable() {
        main = this;
        config = new BungeeConfig("config", false);
        config.get().set("SkyblockDataPath", config.get().get("SkyblockDataPath", "./data"));
        config.save();
        this.getProxy().getPluginManager().registerListener(this, new OnPlayerJoin());
        this.getProxy().getPluginManager().registerCommand(this, new WarpCommand());
        this.getProxy().getPluginManager().registerCommand(this, new MessangerChannelTest());
        Time.start();
        System.out.println("Initing Listener at port 20");
        try {
            new ServerGeneralPort(20);
        }catch (Exception e) {
            e.printStackTrace(System.err);
        }
        checkServers();
    }

    public void checkServers() {
        for (ServerInfo servers : main.getProxy().getServers().values()) {
            new Thread(() -> servers.ping((serverPing, throwable) -> {
                boolean connected = ServerHandler.servers.containsKey(servers);
                if (throwable != null) {
                    if (connected) {
                        ServerHandler handler = ServerHandler.servers.get(servers);
                        ServerHandler.servers.remove(servers);
                        try {
                            handler.socket.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                } else {
                    if (!connected) {
                        new ServerHandler(servers);
                    }
                }
            })).start();
        }
    }

    @Override
    public void onDisable() {
        for (ServerHandler handler : ServerHandler.servers.values()) {
            try {
                handler.sendMessage("close");
                handler.socket.close();
                handler.running = false;
            }catch (Exception exception) {
                exception.printStackTrace(System.err);
            }
        }
        ServerGeneralPort.INSTANCE.remove();
    }
    @SuppressWarnings("deprecation")
    public static void sendMessage(String s, ServerInfo serverInfo) {
        ServerHandler.address.get(serverInfo.getAddress().getPort()).sendMessage(s);
    }
}
