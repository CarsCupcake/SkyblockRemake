package me.CarsCupcake.SkyblockBungee.command;

import me.CarsCupcake.SkyblockBungee.Main;
import me.CarsCupcake.SkyblockBungee.features.ServerType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarpCommand extends Command implements TabExecutor {
    public WarpCommand() {
        super("warp");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer player)) {
            return;
        }
        if (strings.length != 1) return;
        ServerType type = ServerType.fromName(strings[0]);
        if (type == ServerType.Non && !strings[0].equalsIgnoreCase("Non")) {
            commandSender.sendMessage(new TextComponent("§c" + strings[0] + " is not a valid server name!"));
            return;
        }
        List<ServerInfo> servers = Main.getOrderdServer().get(type);
        System.out.println(servers + " " + type);
        if (servers.isEmpty()) {
            commandSender.sendMessage(new TextComponent("§c" + strings[0] + " is not online!"));
            return;
        }
        //TODO: Better distribution system!
        System.out.println(player.getServer() + " " + servers.get(0));
        player.connect(servers.get(0), ServerConnectEvent.Reason.COMMAND);
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender commandSender, String[] strings) {
        if (strings.length != 1) return new ArrayList<>();
        return Main.getOrderdServer().keySet().stream().map(ServerType::getName).filter(s -> s.startsWith(strings[0])).collect(Collectors.toSet());
    }
}
