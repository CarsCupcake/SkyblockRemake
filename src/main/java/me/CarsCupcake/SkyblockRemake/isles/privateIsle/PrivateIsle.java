package me.CarsCupcake.SkyblockRemake.isles.privateIsle;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Configs.ConfigFile;
import me.CarsCupcake.SkyblockRemake.Items.minions.IMinionData;
import me.CarsCupcake.SkyblockRemake.Items.minions.Minion;
import me.CarsCupcake.SkyblockRemake.Items.minions.MinionRemoveReason;
import me.CarsCupcake.SkyblockRemake.Settings.InfoManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.UUID;

public class PrivateIsle {
    public static final HashMap<SkyblockPlayer, PrivateIsle> isles = new HashMap<>();
    public final HashMap<UUID, Minion> minions = new HashMap<>();
    @Getter
    @Setter
    private int maxMinions = 5;
    private final SkyblockPlayer player;

    public PrivateIsle(SkyblockPlayer player) {
        System.out.println("Player isle addition of " + player.getName());
        this.player = player;
        isles.put(player, this);
        ConfigFile config = new ConfigFile(player, "minions", false);
        ConfigurationSection section = config.get().getConfigurationSection("");
        if (section != null) {
            for (String id : section.getKeys(false)) {
                try {
                    String[] args = config.get().getString(id + ".id").split("-");
                    addMinion(IMinionData.minions.get(args[0]), Integer.parseInt(args[1]), new Location(Bukkit.getWorld("world"), Double.parseDouble(config.get().getString(id + ".location.x")), Double.parseDouble(config.get().getString(id + ".location.y")), Double.parseDouble(config.get().getString(id + ".location.z"))), UUID.fromString(id));
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public boolean addMinion(IMinionData minion, int level, Location location) {
        UUID uuid = UUID.randomUUID();
        while (minions.containsKey(uuid)) uuid = UUID.randomUUID();
        return addMinion(minion, level, location, uuid);
    }

    public boolean addMinion(IMinionData minion, int level, Location location, UUID uuid) {
        if (!InfoManager.isUnlimitedMinions()) if (minions.size() + 1 >= maxMinions) return false;
        minions.put(uuid, Minion.getMinion(minion, level, location, uuid.toString(), player));
        return true;
    }

    public void pickupMinion(Minion minion) {
        Assert.isTrue(minions.containsValue(minion));
        minion.remove(MinionRemoveReason.PICKUP_MINION);
        minions.remove(minion.getId());
    }

    public void remove() {
        for (Minion minion : minions.values()) {
            minion.remove(MinionRemoveReason.QUIT);
        }
    }

}
