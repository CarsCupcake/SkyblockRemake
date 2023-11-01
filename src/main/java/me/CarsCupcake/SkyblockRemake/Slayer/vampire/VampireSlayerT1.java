package me.CarsCupcake.SkyblockRemake.Slayer.vampire;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftCalculator;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.maps.CountMap;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class VampireSlayerT1 extends Slayer {
    private final VampireAbility[][] possibles;
    private final int phases;
    private int phase = 0;
    private Zombie entity;
    private final CountMap<Bundle<VampireAbility, Runnable>> tasks = new CountMap<>();

    public VampireSlayerT1(SkyblockPlayer player) {
        super(player);
        if (!(player instanceof RiftPlayer)) throw new IllegalStateException("Not in rift!");
        phases = 3;
        possibles = new VampireAbility[][]{new VampireAbility[]{new Mania()}};
    }

    @Override
    public int getMaxHealth() {
        return 625;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    protected void sheduleAbility(VampireAbility ability, Runnable runnable, int ticks) {
        //Is here to display this over the head of the entity
        tasks.put(new Bundle<>(ability, runnable), ticks);
    }

    @Override
    public void spawn(@NotNull Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class);
        nextPhase();
        new EntityRunnable() {
            @Override
            public void run() {

            }
        }.runTaskTimer(this, 20, 20);
        new EntityRunnable() {
            @Override
            public void run() {
                tasks.addAll(-1);
                List<Bundle<VampireAbility, Runnable>> abilitys = tasks.getByAmount(0);
                tasks.removeByAmount(0);
                for (Bundle<VampireAbility, Runnable> a : abilitys) a.getLast().run();
                if (tasks.isEmpty()) return;
                int i = Integer.MAX_VALUE;
                VampireAbility a = null;
                for (Bundle<VampireAbility, Runnable> b : tasks.keySet()) if (tasks.get(b) < i) a = b.getFirst();
                //TODO: MAke nametag that hovers over the entity which dispalyes the next scheduled ability
            }
        }.runTaskTimer(this, 1, 1);
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        super.damage(damage, player);

    }

    protected void nextPhase() {
        phase++;
        if (phase != 1) {
            for (VampireAbility ability : possibles[phase - 2])
                ability.stop();
        }
        if (phase > possibles.length) return;
        for (VampireAbility ability : possibles[phase - 1])
            ability.start();
    }

    protected int getManiaDamage() {
        return 2;
    }

    protected void damagePlayer(double damage) {
        RiftCalculator calculator = new RiftCalculator();
        calculator.damagePlayer(this, (RiftPlayer) owner);
        calculator.heartsDamage = damage;
        //TODO Iced buff
        calculator.execute();
    }

    @Override
    public String getName() {
        return "Riftstalker Bloodfiend";
    }

    @Override
    public double getRiftDamage() {
        return 0.5;
    }

    protected abstract static class VampireAbility {
        public abstract void start();

        public abstract int[] phases(int level);

        public abstract void stop();

        public abstract String name();
    }

    protected class Mania extends VampireAbility {
        private final Set<Tools.FakeBlock> first = new HashSet<>();
        private final Set<Tools.FakeBlock> second = new HashSet<>();
        private final Set<Tools.FakeBlock> third = new HashSet<>();

        private final Set<Tools.FakeBlock> borders = new HashSet<>();
        private EntityRunnable runnable;
        private final List<int[]> ints = List.of(new int[]{1, 4}, new int[]{5, 8}, new int[]{9, 11});

        @Override
        public void start() {
            Block block =entity.getLocation().getBlock();
            while (block.isPassable() && block.getY() != 0)
                block = block.getRelative(BlockFace.DOWN);
            borders.add(Tools.placeFakeBlock(block, Material.COAL_BLOCK));
            Location middle = Tools.getAsLocation(block);
            entity.setAI(false);
            Set<Block> b = new HashSet<>();
            //I have to do this bcs weard circle bug :( :cry: probably rounding error
            //Witout it its just and egg lol
            //or im just dumb
            //why is this comment so long? 🤨
            Vector[] vs = {new Vector(1, 0, 0), new Vector(1, 0, 0), new Vector(-1, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, 1), new Vector(0, 0, 1), new Vector(0, 0, -1), new Vector(0, 0, -1)};
            double increment = 1; //Test value
            for (double i = 0; i < 45; i += increment) {
                for (int j = 1; j < 13; j++) {
                    for (Vector v : vs) {
                        v.normalize().multiply(j);
                        Block selected = middle.clone().add(v).getBlock();
                        if (b.contains(selected)) continue;
                        b.add(selected);
                        Tools.FakeBlock fake = Tools.placeFakeBlock(selected, Material.COAL_BLOCK);
                        if (j == 1 || j == 12) borders.add(fake);
                        else if (j < 5) first.add(fake);
                        else if (j < 9) second.add(fake);
                        else third.add(fake);
                    }
                }
                boolean bool = false;
                for (Vector v : vs) {
                    v.rotateAroundY(Math.toRadians((bool) ? -increment : increment));
                    bool = !bool;
                }
            }

            runnable = new EntityRunnable() {
                int time = -1;
                int p = 0;
                int phase;
                int i = 0;
                private final Vector v = new Vector(1, 0, 0);

                @Override
                public void run() {
                    v.rotateAroundY(Math.toRadians(10));
                    Location[] locs = {entity.getLocation().add(v), entity.getLocation().subtract(v)};
                    for (Location l : locs) {
                        l.getWorld().spawnParticle(Particle.FLAME, l, 0, 0, 0.25, 0);
                    }
                    if (time == -1) {
                        time = 50;
                        phase = new Random().nextInt(3) + 1;
                        System.out.println(phase);
                        mark(phase);
                    }
                    if (time == 0) {
                        if (p == 0) {
                            time = 20;
                            p++;
                            switch (phase) {
                                case 1 -> {
                                    fill(second, Material.REDSTONE_BLOCK);
                                    fill(third, Material.REDSTONE_BLOCK);
                                }
                                case 2 -> {
                                    fill(first, Material.REDSTONE_BLOCK);
                                    fill(third, Material.REDSTONE_BLOCK);
                                }
                                case 3 -> {
                                    fill(first, Material.REDSTONE_BLOCK);
                                    fill(second, Material.REDSTONE_BLOCK);
                                }
                            }
                        } else if (p == 1) {
                            p = 0;
                            time = 0;
                            i++;
                            int[] ranges = ints.get(phase - 1);
                            if (!Tools.isInRange(ranges[0] - 0.5, 1.5 + ranges[1], owner.getLocation().distance(middle))) {
                                damagePlayer(getManiaDamage());
                            }
                        }
                    }
                    if (i == 6) {
                        nextPhase();
                        return;
                    }
                    time--;
                }

                @Override
                public synchronized void cancel() throws IllegalStateException {
                    super.cancel();
                    first.forEach(Tools.FakeBlock::release);
                    second.forEach(Tools.FakeBlock::release);
                    third.forEach(Tools.FakeBlock::release);
                    borders.forEach(Tools.FakeBlock::release);
                    entity.setAI(true);
                }
            };
            runnable.runTaskTimer(VampireSlayerT1.this, 0, 1);
        }

        private void fill(Set<Tools.FakeBlock> fakes, Material material) {
            for (Tools.FakeBlock fake : fakes)
                fake.change(material);
        }

        private void mark(int phase) {
            fill(first, (phase == 1) ? Material.GREEN_CONCRETE : Material.RED_CONCRETE);
            fill(second, (phase == 2) ? Material.GREEN_CONCRETE : Material.RED_CONCRETE);
            fill(third, (phase == 3) ? Material.GREEN_CONCRETE : Material.RED_CONCRETE);
        }

        @Override
        public int[] phases(int level) {
            return switch (level) {
                case 1, 2 -> new int[]{2};
                case 3, 4 -> new int[]{2, 4};
                case 5 -> new int[]{2, 4, 6};
                default -> throw new IllegalArgumentException(level + " is not allowed!");
            };
        }

        @Override
        public void stop() {
            runnable.cancel();
        }

        @Override
        public String name() {
            return "§6Mania";
        }
    }

    protected class SpectralForm extends VampireAbility {
        private EntityRunnable runnable;

        @Override
        public void start() {

        }

        @Override
        public int[] phases(int level) {
            return switch (level) {
                case 1, 2 -> new int[]{1, 3};
                case 3, 4 -> new int[]{1, 3, 5};
                case 5 -> new int[]{1, 3, 5, 7};
                default -> throw new IllegalArgumentException(level + " is not allowed!");
            };
        }

        @Override
        public void stop() {
            if (runnable != null && !runnable.isCancelled()) runnable.cancel();
        }

        @Override
        public String name() {
            return "§7Spectral Form";
        }
    }
}
