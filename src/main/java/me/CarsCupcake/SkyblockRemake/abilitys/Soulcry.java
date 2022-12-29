package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class Soulcry implements AbilityManager<PlayerInteractEvent>, ItemManager.MaterialGrabber {
    private static final Set<SkyblockPlayer> players = new HashSet<>();
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        event.getItem().setType(Material.GOLDEN_SWORD);
        players.add(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        new BukkitRunnable() {
            @Override
            public void run() {
                event.getItem().setType(Material.DIAMOND_SWORD);
                players.remove(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
                for (ItemStack item : event.getPlayer().getInventory().getContents())
                    Main.item_updater(item, SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
            }
        }.runTaskLater(Main.getMain(), 4*20);
        return false;
    }


    @Override
    public Material getMaterial(ItemStack item, SkyblockPlayer player) {
        if(players.contains(player))
            return Material.GOLDEN_SWORD;
        else
            return Material.DIAMOND_SWORD;
    }
}
