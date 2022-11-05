package me.CarsCupcake.SkyblockRemake.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

public class DragonTracer extends Enchantment implements Listener {
    public DragonTracer() {
        super(new NamespacedKey(Main.getMain(), "Dragon_Tracer"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Dragon Tracer";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @NotNull
    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BOW;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        return true;
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event){
        if(event.getEntity().getShooter() instanceof Player){
            if(!(event.getEntity() instanceof Arrow))
                return;

            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) event.getEntity().getShooter());

            if(player.getItemInHand() != null && player.getItemInHand().getItemMeta() != null){
                if(player.getItemInHand().getItemMeta().getEnchants().containsKey(SkyblockEnchants.DRAGON_TRACER)){
                    new TracingArrow((Arrow) event.getEntity(), player.getItemInHand().getItemMeta().getEnchants().get(SkyblockEnchants.DRAGON_TRACER) * 2);
                }

            }
        }
    }

    private static class TracingArrow{
        private final int range;
        public TracingArrow(Arrow arrow, int blocks){
            this.range = blocks;
            new BukkitRunnable() {
                private EnderDragon tracing;
                @Override
                public void run() {
                    if(arrow.isDead()) {
                        this.cancel();
                        return;
                    }
                    if(tracing != null){
                        if(tracing.isDead()){
                            tracing = null;
                            return;
                        }

                        Vector v = tracing.getLocation().toVector().subtract(arrow.getLocation().toVector());
                        v.normalize();
                        v.multiply(1.5);
                        arrow.setVelocity(v);

                    }else{
                        List<Entity> entitys = arrow.getNearbyEntities(range, range, range);
                        Stream<Entity> e =  entitys.stream().filter(entity1 -> entity1.getType() == EntityType.ENDER_DRAGON);
                        entitys = e.toList();
                        if(!entitys.isEmpty()){
                            tracing = (EnderDragon) entitys.get(0);

                        }
                    }

                }
            }.runTaskTimer(Main.getMain(), 0, 1);
        }
    }

}
