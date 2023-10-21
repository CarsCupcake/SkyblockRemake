package me.CarsCupcake.SkyblockRemake.Slayer.zombie;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ZombieT5 extends ZombieSlayer implements FinalDamageDesider {
    private final Set<TntThrow> tntThrows = new HashSet<>();
    private Zombie entity;
    private int hits = 0;
    private final BukkitRunnable healing = new BukkitRunnable() {
        @Override
        public void run() {
            health += (getMaxHealth() / 1000 > getMaxHealth()) ? getMaxHealth() : getHealth() + getMaxHealth() / 1000;
        }
    };
    private BukkitRunnable tntThrower;
    private BukkitRunnable rotation;
    private boolean isInTnt = false;

    public ZombieT5(SkyblockPlayer player) {
        super(player);
    }

    @Override
    protected void equip(Zombie entity) {
        entity.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/b8469935543fc69864172c6ad95c89b2855cd6043d7028f66ebfa844b59ea2e9"));
        entity.getEquipment().setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setGlint(true).build());
        entity.getEquipment().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.WHITE).setGlint(true).build());
        entity.getEquipment().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.WHITE).setGlint(true).build());
    }

    @Override
    public int getMaxHealth() {
        return 10000000;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return 2400;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, z -> {
            equip(z);
            z.setCustomNameVisible(true);
        });
        SkyblockEntity.livingEntity.addEntity(entity, this);
        Main.updateentitystats(entity);
        healing.runTaskTimer(Main.getMain(), 20, 20);
        restartRotation();
        startTnt();
    }

    private void restartRotation() {
        rotation = new BukkitRunnable() {
            @Override
            public void run() {
                startThunder();
            }
        };
        rotation.runTaskLater(Main.getMain(), 30 * 20);
    }

    private static Block getNextFittingBlock(Block b) {
        if (b.isPassable()) {
            while (b.isPassable()) {
                b = b.getLocation().subtract(0, 1, 0).getBlock();
            }
            return b;
        } else {
            while (!b.isPassable()) {
                b = b.getLocation().add(0, 1, 0).getBlock();
            }
            return b.getLocation().subtract(0, 1, 0).getBlock();
        }
    }

    private void startThunder() {
        isInTnt = true;
        final Location l = getNextFittingBlock(entity.getLocation().getBlock()).getLocation().add(0.5, 1, 0.5);
        tntThrower.cancel();
        tntThrows.forEach(tntThrow -> tntThrow.remove(false));
        tntThrows.clear();
        Set<Bundle<Block, Material>> blocks = new HashSet<>();
        blocks.add(new Bundle<>(l.clone().subtract(0, 1, 0).getBlock(), l.clone().subtract(0, 1, 0).getBlock().getType()));
        l.clone().subtract(0, 1, 0).getBlock().setType(Material.BEDROCK);
        Set<Block> expand = new HashSet<>();
        expand.add((l.clone().subtract(0, 1, 0).getBlock()));
        tntThrower = new BukkitRunnable() {
            private int i;

            @Override
            public void run() {
                if(i > 8 * 20 + 1)
                    return;

                entity.teleport(l);
                i++;
                if (i % 20 == 0) {
                    if (i / 20 > 7) {

                        for (Block block : expand) {
                            block.getWorld().strikeLightningEffect(block.getLocation());
                            Stream<Entity> entityStream = entity.getNearbyEntities(7, 7, 7).stream().filter(e -> e instanceof Player);
                            for (Entity e : entityStream.toList()) {
                                SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) e);
                                Calculator c = new Calculator();
                                hits++;
                                c.entityToPlayerDamage(ZombieT5.this, player, new Bundle<>(4800 * hits, 0));
                                c.damagePlayer(player);
                            }
                        }
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                tntThrower.cancel();
                                isInTnt = false;
                                startTnt();
                                restartRotation();
                            }
                        }.runTaskLater(Main.getMain(), 20);
                    }else
                        for (Block b : new HashSet<>(expand)) {
                            expand.remove(b);
                            check(getNextFittingBlock(b.getLocation().add(1, 0, 0).getBlock()));
                            check(getNextFittingBlock(b.getLocation().add(-1, 0, 0).getBlock()));
                            check(getNextFittingBlock(b.getLocation().add(0, 0, 1).getBlock()));
                            check(getNextFittingBlock(b.getLocation().add(0, 0, -1).getBlock()));
                        }
                }
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                blocks.forEach(block -> block.getFirst().setType(block.getLast()));
            }

            private void check(Block block) {
                if (block.getType() != Material.BEDROCK) {
                    expand.add(block);
                    blocks.add(new Bundle<>(block, block.getType()));
                    block.setType(Material.BEDROCK);
                }
            }
        };
        tntThrower.runTaskTimer(Main.getMain(), 0, 1);
    }

    private void startTnt() {
        if (entity.isDead() || isInTnt)
            return;

        tntThrower = new BukkitRunnable() {
            @Override
            public void run() {
                tntThrows.add(new TntThrow(ZombieT5.this, getHealth() <= getHealth() / 3));
            }
        };
        tntThrower.runTaskLater(Main.getMain(), 22);
    }

    @Override
    public String getName() {
        return "§fAtoned Horror";
    }

    @Override
    public HashMap<ItemManager, Integer> getGarantuedDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(Slayer.getBaseName(this));
    }

    @Override
    public void kill() {
        super.kill();
        healing.cancel();
        try {
            tntThrower.cancel();
        } catch (Exception ignored) {
        }
        tntThrows.forEach(tntThrow -> tntThrow.remove(false));
        try {
            rotation.cancel();
        } catch (Exception ignored) {
        }
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        hits++;
        return damage;
    }

    private static class TntThrow {
        private final ArmorStand stand;
        private final HashMap<Block, Material> materials = new HashMap<>();
        private final ZombieT5 entity;
        private final Block middle;
        private final BukkitRunnable runnable;

        public TntThrow(ZombieT5 t5, boolean isEnrage) {
            entity = t5;
            markBlocks();
            Block start = entity.owner.getLocation().getBlock();
            middle = getNextFittingBlock(start);
            stand = t5.entity.getWorld().spawn(entity.getEntity().getEyeLocation(), ArmorStand.class, s -> {
                s.setInvisible(true);
                s.setGravity(false);
                s.setInvulnerable(true);
                s.getEquipment().setHelmet(new ItemStack(Material.TNT));
            });
            double d = stand.getLocation().distance(Tools.getAsLocation(middle)) / ((isEnrage) ? 10 : 20);
            Location target = Tools.getAsLocation(middle);
            Vector dir = target.clone().subtract(stand.getEyeLocation()).toVector().normalize().multiply(d);
            runnable = new BukkitRunnable() {
                int i = 0;
                Location l = stand.getLocation();

                @Override
                public void run() {
                    if (i >= 21) {
                        explode();
                        remove(true);
                        return;
                    }
                    double y = getOffset(((((isEnrage) ? 2 : 1) * i) * 0.1) * Math.PI) * 2;
                    l = l.add(dir);
                    stand.teleport(l.clone().add(0, y, 0));
                    i++;
                    if (isEnrage)
                        i++;
                }
            };
            runnable.runTaskTimer(Main.getMain(), 1, 1);
        }

        private double getOffset(double i) {
            return Math.sin(i * 0.5) + 0.5;
        }

        private void explode() {
            Location l = Tools.getAsLocation(middle).add(0, 1, 0);
            Stream<Entity> stream = middle.getWorld().getNearbyEntities(l, 3, 3, 3).stream().filter(e -> e instanceof Player);
            for (Entity e : stream.toList()) {
                SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) e);
                Calculator calculator = new Calculator();
                entity.hits++;
                calculator.entityToPlayerDamage(entity, player, new Bundle<>((int) (((l.distance(player.getLocation()) <= 2.5) ? 1 : 1.5294) * (Main
                        .getPlayerStat(player, Stats.Health) * 0.15)), 0));
                calculator.damagePlayer(player);
            }
        }

        private void markBlocks() {
            Block start = entity.owner.getLocation().getBlock();
            start = getNextFittingBlock(start);
            materials.put(start, start.getType());
            start.setType(Material.RED_TERRACOTTA);
            makeBlock(start.getLocation().add(1, 0, 0).getBlock(), Material.RED_TERRACOTTA);
            makeBlock(start.getLocation().add(-1, 0, 0).getBlock(), Material.RED_TERRACOTTA);
            makeBlock(start.getLocation().add(0, 0, 1).getBlock(), Material.RED_TERRACOTTA);
            makeBlock(start.getLocation().add(0, 0, -1).getBlock(), Material.RED_TERRACOTTA);

            makeBlock(start.getLocation().add(2, 0, 0).getBlock(), Material.WHITE_TERRACOTTA);
            makeBlock(start.getLocation().add(-2, 0, 0).getBlock(), Material.WHITE_TERRACOTTA);
            makeBlock(start.getLocation().add(0, 0, 2).getBlock(), Material.WHITE_TERRACOTTA);
            makeBlock(start.getLocation().add(0, 0, -2).getBlock(), Material.WHITE_TERRACOTTA);

            makeBlock(start.getLocation().add(1, 0, 1).getBlock(), Material.WHITE_TERRACOTTA);
            makeBlock(start.getLocation().add(-1, 0, 1).getBlock(), Material.WHITE_TERRACOTTA);
            makeBlock(start.getLocation().add(1, 0, -1).getBlock(), Material.WHITE_TERRACOTTA);
            makeBlock(start.getLocation().add(-1, 0, -1).getBlock(), Material.WHITE_TERRACOTTA);

            makeBlock(start.getLocation().add(2, 0, 1).getBlock(), Material.LIGHT_GRAY_TERRACOTTA);
            makeBlock(start.getLocation().add(-2, 0, 1).getBlock(), Material.LIGHT_GRAY_TERRACOTTA);
            makeBlock(start.getLocation().add(2, 0, -1).getBlock(), Material.LIGHT_GRAY_TERRACOTTA);
            makeBlock(start.getLocation().add(-2, 0, -1).getBlock(), Material.LIGHT_GRAY_TERRACOTTA);

            makeBlock(start.getLocation().add(1, 0, 2).getBlock(), Material.LIGHT_GRAY_TERRACOTTA);
            makeBlock(start.getLocation().add(-1, 0, -2).getBlock(), Material.LIGHT_GRAY_TERRACOTTA);
            makeBlock(start.getLocation().add(1, 0, -2).getBlock(), Material.LIGHT_GRAY_TERRACOTTA);
            makeBlock(start.getLocation().add(-1, 0, 2).getBlock(), Material.LIGHT_GRAY_TERRACOTTA);
        }

        private void makeBlock(Block b, Material m) {
            b = getNextFittingBlock(b);
            materials.put(b, b.getType());
            b.setType(m);
        }

        public void remove(boolean next) {
            for (Block b : materials.keySet())
                b.setType(materials.get(b));
            stand.remove();
            try {
                runnable.cancel();
            } catch (Exception ignored) {
            }
            if (next) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        entity.startTnt();
                    }
                }.runTaskLater(Main.getMain(), 1);
            }
        }
    }
}