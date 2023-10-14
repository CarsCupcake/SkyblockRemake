package me.CarsCupcake.SkyblockBungee.listeners;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import me.CarsCupcake.SkyblockBungee.BungeeConfig;
import me.CarsCupcake.SkyblockBungee.Main;
import me.CarsCupcake.SkyblockBungee.features.ServerType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

public class OnPlayerJoin implements Listener {
    @EventHandler
    public void  onJoin(ServerConnectEvent event) {
        if(Main.getServers().isEmpty()) {
            event.getPlayer().disconnect(new TextComponent("§cNo Server found!"));
            return;
        }
        BungeeConfig config = new BungeeConfig(event.getPlayer(), "server");
        ServerType type =  ServerType.getFromString(config.get().getString("id", ServerType.Hub.s));
        List<ServerInfo> servers = Main.getOrderdServer().get(type);
        if (!servers.isEmpty()) {
            event.setTarget(servers.get(0));
            return;
        }
        for (ServerType t : ServerType.values()) {
            List<ServerInfo> s = Main.getOrderdServer().get(t);
            if (!s.isEmpty()) {
                event.setTarget(s.get(0));
                return;
            }
        }
    }
    @EventHandler
    public void onDisconnect(ServerDisconnectEvent event) {
        new ThreadFactoryBuilder().build().newThread(() -> {
            BungeeConfig config = new BungeeConfig(event.getPlayer(), "server");
            config.get().set("id", Main.getServers().get(event.getTarget()).s);
            config.save();
        }).start();
    }
}
