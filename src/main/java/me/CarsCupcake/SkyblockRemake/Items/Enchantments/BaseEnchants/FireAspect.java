package me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FireAspect extends CustomEnchantment {
    public FireAspect() {
        super(new NamespacedKey(Main.getMain(), FIRE_ASPECT.getKey().getKey()));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Ignites your enemies for §a%time%s §7,", "§7dealing §a%pers% §7of your damage", "§7per second.")
                .addPlaceholder("%time%", (player, itemStack) -> "" + getSeconds(ItemHandler.getEnchantmentLevel(this, itemStack)))
                .addPlaceholder("%pers%", (player, itemStack) -> getDamagePersentage(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Fire Aspect";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
    public static int getSeconds(int level) {
        if(level == 1) return 3;
        return 4;
    }
    public static int getDamagePersentage(int level) {
        return level * 3;
    }
    public static class Fire extends BukkitRunnable {
        private static final Map<SkyblockEntity, Fire> fireInstance = new HashMap<>();
        private final SkyblockEntity entity;
        private int time;
        private double damage;
        private SkyblockPlayer player;
        private Fire(SkyblockEntity entity, int level) {
            this.entity = entity;
            time = getSeconds(level) * 20;
            fireInstance.put(entity, this);
        }
        @Override
        public void run() {
            if (entity.getEntity() == null ||  entity.getEntity().isDead()) {
                cancel();
                return;
            }
            time--;
            if(time < 0) {
                cancel();
                return;
            }
            entity.getEntity().getWorld().spawnParticle(Particle.FLAME, entity.getEntity().getLocation().add(0, 0.2, 0), 5 ,0.25, 0.7, 0.25, 0, null);
            if(time % 20 == 0) {
                Calculator c = new Calculator();
                c.damage = damage;
                c.setApplyFerocity(false);
                c.setIgnoreHit(true);
                c.damageEntity(entity.getEntity(), player);
                c.showFireDamageTag(entity.getEntity());
            }
        }

        @Override
        public synchronized void cancel() throws IllegalStateException {
            super.cancel();
            fireInstance.remove(entity);
        }
        public static void fire(SkyblockEntity entity, int level, double damage, SkyblockPlayer player) {
            Fire fire = fireInstance.get(entity);
            if (fire == null) {
                fire = new Fire(entity, level);
                fire.damage = damage * ((double) getDamagePersentage(level) / 100);
                fire.player = player;
                fire.runTaskTimer(Main.getMain(), 0, 1);
            } else {
                fire.damage = damage * ((double) getDamagePersentage(level) / 100);
                fire.time = getSeconds(level) * 20;
                fire.player = player;
            }
        }
    }
}
