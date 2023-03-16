package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class Soulcry implements AbilityManager<PlayerInteractEvent>, ItemManager.MaterialGrabber {
    private static final Set<SkyblockPlayer> players = new HashSet<>();
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        event.getItem().setType(Material.GOLDEN_SWORD);
        players.add(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));

        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());

        new BukkitRunnable() {
            int i = 1;
            @Override
            public void run() {
                switch (i){
                    case 1 -> {
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.1F, 0.5F);
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5F, 1.5F);
                    }
                    case 2 -> {
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.1F, 0.6F);
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5F, 1.6F);
                    }
                    case 3 -> {
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.1F, 0.7F);
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5F, 1.7F);
                    }
                    case 4 -> {
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.1F, 0.8F);
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5F, 1.8F);
                    }
                    case 5 -> {
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.1F, 0.9F);
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5F, 1.9F);
                    }
                    default ->cancel();

                }

                i++;
            }
        }.runTaskTimer(Main.getMain(), 0, 1);












        new BukkitRunnable() {
            @Override
            public void run() {
                event.getItem().setType(Material.DIAMOND_SWORD);
                players.remove(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
                new BukkitRunnable() {
                    int i = 0;
                    @Override
                    public void run() {
                        switch (i){
                            case 0 -> {
                                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5F, 0.9F);
                                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5F, 1.9F);
                            }
                            case 1 -> {
                                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5F, 0.8F);
                                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5F, 1.8F);
                            }
                            default -> cancel();
                        }

                        i++;
                    }
                }.runTaskTimer(Main.getMain(), 0, 1);






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
