package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.MythologicalPerk;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class GaiaConstruct extends SkyblockEntity implements FinalDamageDesider {
    private int maxHealth = 1;
    private final int damage;
    int hits = 6;
    final boolean highLevel;
    private final MythologicalPerk perk;

    public GaiaConstruct(ItemRarity rarity, MythologicalPerk perk) {
        if (Objects.requireNonNull(rarity) == ItemRarity.LEGENDARY) {
            maxHealth = 1_500_000;
            damage = 3_400;
            highLevel = true;
        } else {
            maxHealth = 300_000;
            damage = 850;
            highLevel = false;
        }
        health = maxHealth;
        this.perk = perk;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    private IronGolem entity;

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, IronGolem.class, golem -> {
            golem.setCustomNameVisible(true);
        });
        SkyblockEntity.livingEntity.addEntity(entity, this);
        new EntityRunnable() {
            int i = 0;

            @Override
            public void run() {
                i++;
                if (time() > i) return;
                i = 0;
                if (!(entity.getTarget() instanceof Player player)) return;
                doIron(SkyblockPlayer.getSkyblockPlayer(player));
            }


        }.runTaskTimer(this, 1, 1);
    }

    private int time() {
        return ((getHealth() > getMaxHealth() * 0.66) ? 40 : ((getHealth() > getMaxHealth() * 0.33) ? 20 : 8));
    }

    @Override
    public String getName() {
        return "§2Gaia Cosntruct";
    }

    public void doIron(SkyblockPlayer player) {
        Block block = fitting(player.getLocation().getBlock());
        LinkedList<Block> list = new LinkedList<>();
        list.add(block);
        list.add(fitting(block.getRelative(BlockFace.NORTH)));
        list.add(fitting(block.getRelative(BlockFace.SOUTH)));
        list.add(fitting(block.getRelative(BlockFace.EAST)));
        list.add(fitting(block.getRelative(BlockFace.WEST)));
        LinkedList<Tools.FakeBlock> fake = new LinkedList<>();
        for (Block b : list) {
            Tools.FakeBlock fB = Tools.placeFakeBlock(b, Material.IRON_BLOCK);
            fake.add(fB);
        }
        new EntityRunnable() {
            @Override
            public void run() {
                Location center = Tools.getAsLocation(block).add(0, 1, 0);
                block.getWorld().strikeLightningEffect(center);
                if (player.getLocation().distance(center) <= 1.5) {
                    double damage = ((highLevel) ? 10_000d : 1_500d) + (Main.getPlayerStat(player, Stats.Health) * 0.4);
                    Calculator calculator = new Calculator();
                    calculator.entityToPlayerDamage(GaiaConstruct.this, player, new Bundle<>(0, (int) damage));
                }
                release();
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                release();
            }

            private void release() {
                fake.forEach(Tools.FakeBlock::release);
                fake.clear();
            }
        }.runTaskLater(this, 15);
    }

    private Block fitting(Block origin) {
        while (origin.isPassable() && origin.getY() != 0) origin = origin.getRelative(BlockFace.DOWN);
        while (!origin.isPassable()) origin = origin.getRelative(BlockFace.UP);
        return origin.getRelative(BlockFace.DOWN);
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        super.damage(damage, player);
        if (hits == 0) {
            if (health > maxHealth * 0.66) hits = 6;
            else if (health > maxHealth * 0.33) hits = 7;
            else hits = 8;
        } else
            entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1 + (new Random().nextFloat() - 0.25f));
        hits--;
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        return (hits == 0) ? damage : 0;
    }

    @Override
    public int getLevel() {
        return (highLevel) ? 260 : 140;
    }

    @Override
    public int getXpDrop() {
        return (highLevel) ? 250 : 70;
    }

    @Override
    public void kill() {
        super.kill();
        if (perk != null) perk.kill(this);
    }
}
