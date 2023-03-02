package me.CarsCupcake.SkyblockRemake.isles.privateIsle;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Items.minions.IMinion;
import me.CarsCupcake.SkyblockRemake.Items.minions.Minion;
import me.CarsCupcake.SkyblockRemake.Items.minions.MinionRemoveReason;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.UUID;

public class PrivateIsle {
    public static final HashMap<SkyblockPlayer, PrivateIsle> isles = new HashMap<>();
    public final HashMap<UUID, Minion> minions = new HashMap<>();
    private int maxMinions = 5;
    private final SkyblockPlayer player;

    public PrivateIsle(SkyblockPlayer player) {
        this.player = player;
        isles.put(player, this);
        CustomConfig config = new CustomConfig(player, "minions", false);
        ConfigurationSection section = config.get().getConfigurationSection("");
        if (section != null) {
            for (String id : section.getKeys(false)) {
                String[] args = config.get().getString(id + ".id").split("-");
                addMinion(IMinion.minions.get(args[0]), Integer.parseInt(args[1]),
                        new Location(
                                Bukkit.getWorld("world"),
                                Double.parseDouble(config.get().getString(id + ".location.x")),
                                Double.parseDouble(config.get().getString(id + ".location.y")),
                                Double.parseDouble(config.get().getString(id + ".location.z"))
                        )
                        , UUID.fromString(id));
            }
        }
    }

    public boolean addMinion(IMinion minion, int level, Location location) {
        UUID uuid = UUID.randomUUID();
        while (minions.containsKey(uuid)) uuid = UUID.randomUUID();
        return addMinion(minion, level, location, uuid);
    }

    public boolean addMinion(IMinion minion, int level, Location location, UUID uuid) {
        if (minions.size() + 1 >= maxMinions) return false;

        minions.put(uuid, Minion.getMinion(minion, level, location, uuid.toString(), player));

        return true;
    }

    public void remove() {
        isles.remove(player);
        for (Minion minion : minions.values()) {
            minion.remove(MinionRemoveReason.QUIT);
        }
    }
}
