package me.CarsCupcake.SkyblockBungee.command;

import me.CarsCupcake.SkyblockBungee.net.ServerHandler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class MessangerChannelTest extends Command {
    public MessangerChannelTest() {
        super("channeltest");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        for (ServerHandler handler : ServerHandler.servers.values()) handler.sendMessage(strings[0]);
    }
}
