package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.CrimsonIsle;
import me.CarsCupcake.SkyblockRemake.isles.End.EndListeners;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftIsle;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public record SkyblockServer(ServerType type) {
    private static SkyblockServer server;

    public SkyblockServer {
        if (type == null) {
            for (Player p : Bukkit.getOnlinePlayers())
                p.sendTitle("§cThere is no Server type!", "§ePlease set a server type in the files!", 20, 400, 20);
            Main.getMain().getLogger().log(Level.SEVERE, "\n*********************************************************\n\n\nNo Server type provided! Skyblock Plugin is shutting down!\n\n\n*********************************************************");
            Main.getMain().getServer().getPluginManager().disablePlugin(Main.getMain());

        }
        if (type == ServerType.Non) {
            Main.getMain().getLogger().log(Level.SEVERE, "\n*********************************************************\n\n\nNo good Skyblock Server type provided! Please change it!\n\n\n*********************************************************");
        }
        Main.getMain().getLogger().fine("Server is running on server type " + type);
        server = this;
    }

    public static SkyblockServer getServer() {
        return server;
    }

    public static SkyblockServer makeServerFromPort(int port) {
        if (!Main.isLocalHost) return new SkyblockServer(ServerType.DwarvenMines);
        else return new SkyblockServer(ServerType.getServerByPort(port));
    }

    public void init() {
        gamerules();

        if (type == ServerType.End) EndListeners.init();

        if (type == ServerType.CrimsonIsle) new CrimsonIsle();

        if (type == ServerType.Rift) new RiftIsle();
    }

    public void gamerules() {
        Main.getDebug().debug("Setting up gamerules", false);
        for (World w : Bukkit.getWorlds()) {
            w.setGameRule(GameRule.DO_FIRE_TICK, false);
            w.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
            w.setGameRule(GameRule.NATURAL_REGENERATION, false);
            w.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        }
    }

}
