package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class FallDownArmorstand implements Listener {
    private static ArrayList<ArmorStand> stands = new ArrayList<>();

    public FallDownArmorstand(Material m, Location l){
        if(m == null)
            return;
        ArmorStand stand = l.getWorld().spawn(l.clone().add(0,-1.4,0), ArmorStand.class, s -> {
            s.setInvisible(true);
            s.setInvulnerable(true);
            s.getEquipment().setHelmet(new ItemStack(m));
        });
        stands.add(stand);
        new BukkitRunnable() {
            @Override
            public void run() {
                if(stand.isDead()) {
                    cancel();
                    return;
                }
                if(stand.isOnGround()){
                    cancel();
                    stand.remove();
                    stands.remove(stand);
                }
            }
        }.runTaskTimer(Main.getMain(), 1, 1);

    }

    @EventHandler
    public void inDisable(PluginDisableEvent event){
        if(event.getPlugin() == Main.getMain())
            stands.forEach(Entity::remove);
    }

}
