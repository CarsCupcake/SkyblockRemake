package me.CarsCupcake.SkyblockRemake.Slayer.vampire;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.EntityNPC;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftCalculator;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import me.CarsCupcake.SkyblockRemake.utils.SinusMovement;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;
import me.CarsCupcake.SkyblockRemake.utils.maps.CountMap;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class VampireSlayerT1 extends Slayer implements FinalDamageDesider {
    public static Set<Class<? extends SkyblockEntity>> vampires = Set.of(VampireSlayerT1.class);
    public static final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTY2MTQwMDI0MTc2NiwKICAicHJvZmlsZUlkIiA6ICI4YTg3NGJhNmFiZDM0ZTc5OTljOWM1ODMwYWYyY2NmNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXphMTExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVhYTI5ZWE5NjE3NTdkYzNjOTBiZmFiZjMwMmM1YWJlOWQzMDhmYjRhN2QzODY0ZTU3ODhhZDJjYzkxNjBhYTIiCiAgICB9CiAgfQp9";
    public static final String signature = "u7oD4NYj9J7UzMV/GZ3oScp3E6ci7+YI3DsDlTzVfsHKB5yWNEyZPttL09dMDWyJY1kdC8hsK8i5xhF5BaC/2pj/f3SNndzkrflEYrwUr8/1GVXpejIEVpb+SNqImpjsxWY3bLVQHaQ47WjMzvfrQ/gaEMKp3vDmjqST4gWKPxyk6hEHAudA1evE95QSjKX+ayMc822WQPOlPsqcFIZ/f/HYivYl9FQ4HbSyRfK2iI3Ibb0Mwg7BDcJuvkxdnIpkwBz1Hu3SH77dcpXZtLvIBc7dy41zJOMhUzyqkFFVrid5GvgTgb2o+iJ9mSNfVxN9khpG2q15lofdfIseijpq3QP2rAhdl3uX7DqT/CzOzfXP/9FGQaGuYySkNRlbt1WLfWJN9sHWK/jyz1nhV+JwJvwg/uV4Cor9q1jr01cv/FsWIUwSHLnXndIOEileCKnqlo6G/FtTU4Rgd1C5CryBUhY1WMc+HPk38wmWo6HzNOlhT1HltiPjb4kpSUP+vz5LTtplOqwomw/XBp/wuXuS2ijzCVo6lovtUzra5lsGa9EijHPreXt2dEHy68bTZBt2Os4BeWCMTz58d4wvSvC/hHNXdd/asx1CcW288HFxWRxoNLLawanDILCZLdRln4MwlGP1IruOuK0wJOkP3kxqHJdCL51psBPWDpPTzW0VC9c=";
    private VampireAbility[][] possibles;
    @Getter
    private int phases;
    @Getter
    private int phase = 0;
    private Zombie entity;
    private ArmorStand stand;
    protected boolean innvincible = false;
    private final CountMap<Bundle<VampireAbility, Runnable>> tasks = new CountMap<>();
    private int phaseMilestone;
    private boolean block = false;

    public VampireSlayerT1(SkyblockPlayer player) {
        super(player);
        if (!(player instanceof RiftPlayer)) throw new IllegalStateException("Not in rift!");
        phases(3, 1, new SpectralForm(), new Mania(), new Stonewrath());
    }

    protected void phases(int phases, int level, VampireAbility... abilities) {
        this.phases = phases;
        possibles = new VampireAbility[phases][];
        for (int p = 1; p <= phases; p++) {
            List<VampireAbility> a = new ArrayList<>();
            for (VampireAbility ability : abilities)
                for (int i : ability.phases(level))
                    if (i == p) {
                        a.add(ability);
                        break;
                    }
            VampireAbility[] vamp = new VampireAbility[a.size()];
            for (int i = 0; i < a.size(); i++) vamp[i] = a.get(i);
            possibles[p - 1] = vamp;
        }
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
    public LootTable getLootTable() {
        LootTable lootTable = new LootTable();
        lootTable.addLoot(new ItemLoot(VampireItems.CovenSeal.getManager(), 1, 4));
        return lootTable;
    }

    @Override
    public void spawn(@NotNull Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, zombie -> {
            zombie.setTarget(owner);
        });
        new PlayerDisguise(entity, texture, signature);
        stand = entity.getWorld().spawn(loc.clone().add(0, -0.1, 0), ArmorStand.class, s -> {
            s.setGravity(false);
            s.setInvisible(true);
            s.setInvulnerable(true);
            s.setCustomNameVisible(true);
            s.setCustomName("§7 ");
            s.addScoreboardTag("remove");
        });
        nextPhase();
        new EntityRunnable() {
            @Override
            public void run() {
                damagePlayer(getRiftDamage());
            }
        }.runTaskTimer(this, 20, 20);
        new EntityRunnable() {
            VampireAbility last = null;

            @Override
            public void run() {
                stand.teleport(entity.getLocation().add(0, 0.15, 0));
                if (block) return;
                tasks.addAll(-1);
                List<Bundle<VampireAbility, Runnable>> abilitys = tasks.getByAmount(0);
                tasks.removeByAmount(0);
                for (Bundle<VampireAbility, Runnable> a : abilitys) {
                    a.getLast().run();
                }
                if (tasks.isEmpty()) {
                    if (last != null) {
                        last = null;
                        stand.setCustomNameVisible(false);
                    }
                    return;
                }
                int i = Integer.MAX_VALUE;
                VampireAbility a = null;
                for (Bundle<VampireAbility, Runnable> b : tasks.keySet()) if (tasks.get(b) < i) {
                    a = b.getFirst();
                    i = tasks.get(b);
                }
                if (last != a) {
                    if (!stand.isCustomNameVisible()) stand.setCustomNameVisible(true);
                    stand.setCustomName(a.name() + " §a" + Tools.round((double) i / 20d, 1));
                }
            }
        }.runTaskTimer(this, 1, 1);
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        super.damage(damage, player);
        if (getHealth() <= phaseMilestone) nextPhase();
    }

    protected void nextPhase() {
        phase++;
        if (phase != 1) {
            for (VampireAbility ability : possibles[phase - 2])
                ability.stop();
        }
        if (phase > possibles.length) return;
        for (VampireAbility ability : possibles[phase - 1]) {
            ability.start();
        }
        for (Bundle<VampireAbility, Runnable> abilitys : tasks.keySet()) {
            boolean b = false;
            for (VampireAbility ability : possibles[phase - 1])
                if (ability.name().equals(abilitys.getFirst().name())) {
                    b = true;
                    break;
                }
            if (!b) tasks.remove(abilitys);
        }
        phaseMilestone = getMaxHealth() - ((getMaxHealth() / phases) * phase);
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
        if (damage == 0.5) {
            System.out.println("1 damage!");
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                System.out.println(e.getStackTrace()[1]);
            }
        }
    }

    @Override
    public void kill() {
        super.kill();
        stand.remove();
        tasks.clear();
        for (Cadaver cadaver : cadavers) cadaver.destory();
        for (Clotgoyle clotgoyle : new ArrayList<>(clotgoyles)) SkyblockEntity.killEntity(clotgoyle, owner);
    }

    @Override
    public String getName() {
        return "Riftstalker Bloodfiend";
    }

    @Override
    public double getRiftDamage() {
        return 0.5;
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        return (innvincible) ? 0 : damage;
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
            innvincible = true;
            Block block = entity.getLocation().getBlock();
            while (block.isPassable() && block.getY() != 0) block = block.getRelative(BlockFace.DOWN);
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
                        innvincible = false;
                        return;
                    }
                    time--;
                }

                @Override
                public synchronized void cancel() throws IllegalStateException {
                    super.cancel();
                    first.forEach(Tools.FakeBlock::release);
                    first.clear();
                    second.forEach(Tools.FakeBlock::release);
                    second.clear();
                    third.forEach(Tools.FakeBlock::release);
                    third.clear();
                    borders.forEach(Tools.FakeBlock::release);
                    borders.clear();
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
        private boolean inited = false;

        protected SpectralForm() {
        }

        @Override
        public void start() {
            if (!inited) {
                sheduleAbility(this, this::start, 200);
                inited = true;
                return;
            }
            innvincible = true;
            Random r = new Random();
            Block b = null;
            for (int i = 0; i < 64; i++) {
                Vector dir = new Vector(r.nextDouble() * 16 - 5, 0, r.nextDouble() * 16 - 5);
                Block block = fitting(owner.getLocation().add(dir).getBlock());
                double dist = Tools.getAsLocation(block).distance(owner.getLocation());
                if (dist < 8 && owner.rayTraceBlocks(dist) == null) {
                    b = block;
                    break;
                }
            }
            if (b == null) {
                sheduleAbility(this, this::start, 200);
                return;
            }
            SinusMovement movement = new SinusMovement(1, 3, 0);
            entity.setInvisible(true);
            entity.setAI(false);
            EntityNPC tempNpc = EntityNPC.makeNPC(null, Bat.class, entity.getEyeLocation(), true);
            Bat bat = (Bat) tempNpc.getEntity();
            bat.setAwake(true);
            movement.move(tempNpc.getEntity(), () -> {
                tempNpc.remove();
                entity.setInvisible(false);
                entity.setAI(true);
                sheduleAbility(this, this::start, 200);
                innvincible = false;
            }, 15, 0, entity.getEyeLocation(), Tools.getAsLocation(b), () -> entity.teleport(tempNpc));
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
        }

        @Override
        public String name() {
            return "§7Spectral Form";
        }
    }

    private Block fitting(Block origin) {
        while (origin.isPassable() && origin.getY() != 0) origin = origin.getRelative(BlockFace.DOWN);
        while (!origin.isPassable()) origin = origin.getRelative(BlockFace.UP);
        return origin;
    }

    private final Set<Clotgoyle> clotgoyles = new HashSet<>();
    private final Set<Cadaver> cadavers = new HashSet<>();

    protected class Stonewrath extends VampireAbility {

        @Override
        public void start() {
            for (int i = clotgoyles.size() + cadavers.size(); i < 3; i++) {
                Clotgoyle clotgoyle = new Clotgoyle(VampireSlayerT1.this);
                clotgoyle.spawn(entity.getEyeLocation());
                clotgoyles.add(clotgoyle);
            }
            sheduleAbility(this, this::komet, 300);
        }

        private void komet() {
            block = true;
            innvincible = true;
            entity.setAI(false);
            entity.setGravity(false);
            new EntityRunnable() {
                int i = 0;
                Location l = owner.getLocation();
                Vector v;

                @Override
                public void run() {
                    if (i < 60) {
                        if (i < 20) {
                            entity.teleport(entity.getLocation().add(0, 0.5, 0));
                            i++;
                            return;
                        } else
                            l = owner.getLocation();
                    }
                    if (i % 2 == 0) {
                        animation(fitting(l.getBlock().getRelative(-3, 0, -3)));
                    }
                    if (i >= 90) {
                        if (i == 90) {
                            v = owner.getLocation().toVector().subtract(entity.getLocation().toVector());
                            v = v.normalize();
                            if (entity.getWorld() != owner.getWorld()) {
                                killEntity(VampireSlayerT1.this, null);
                                owner.sendMessage("There was an error with the vampire slayer!");
                                cancel();
                                return;
                            }
                            v = v.multiply(owner.getLocation().distance(entity.getLocation()) / 10);
                        }
                        entity.teleport(entity.getLocation().add(v));
                    }
                    if (i == 100) {
                        cancel();
                        l.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 1);
                        entity.teleport(l);
                        entity.setAI(true);
                        entity.setGravity(true);
                        if (Tools.isInRange(l.getX() + 7, l.getX() - 7, owner.getLocation().getX()) && Tools.isInRange(l.getZ() + 7, l.getZ() - 7, owner.getLocation().getZ()))
                            damagePlayer(Main.getPlayerStat(owner, Stats.Hearts) * 0.75);
                        for (Cadaver cadaver : new ArrayList<>(cadavers))
                            if (Tools.isInRange(l.getX() + 7, l.getX() - 7, cadaver.stand.getLocation().getX()) && Tools.isInRange(l.getZ() + 7, l.getZ() - 7, cadaver.stand.getLocation().getZ())) {
                                cadaver.destory();
                                cadavers.remove(cadaver);
                            }
                        sheduleAbility(new VampireAbility() {
                            @Override
                            public void start() {}

                            @Override
                            public int[] phases(int level) {return new int[0];}

                            @Override
                            public void stop() {}

                            @Override
                            public String name() {
                                return "§fResurrect";
                            }
                        }, () -> {
                            for (Cadaver cadaver : cadavers) {
                                cadaver.revive();
                            }
                            cadavers.clear();
                            sheduleAbility(Stonewrath.this, Stonewrath.this::komet, 400);
                        }, 100);
                        entity.teleport(l);
                    }
                    i++;
                }

                @Override
                public synchronized void cancel() throws IllegalStateException {
                    super.cancel();
                    block = false;
                    innvincible = false;
                    p1.forEach(Entity::remove);
                    p2.forEach(Entity::remove);
                    p1.clear();
                    p2.clear();
                }
            }.runTaskTimer(VampireSlayerT1.this, 0, 1);
        }

        Set<FallingBlock> p1 = new HashSet<>();
        Set<FallingBlock> p2 = new HashSet<>();
        private boolean b = true;

        private void animation(Block first) {
            placeBlocks(first, (b) ? p1 : p2, b);
            b = !b;
        }
        Material[] materials = new Material[]{Material.REDSTONE_BLOCK, Material.GRAY_CONCRETE, Material.RED_CONCRETE};

        private void placeBlocks(Block first, Set<FallingBlock> set, boolean b) {
            set.clear();
            Random r = new Random();
            for (int i = 0; i < 7; i++)
                for (int j = 0; j < 7; j++) {
                    if (r.nextDouble() < 0.3) continue;
                    b = !b;
                    if (b) continue;
                    Location l = Tools.getAsLocation(first).subtract(0, 0.1, 0).add(i + (0.5 - r.nextDouble()), 0 , j + (0.5 - r.nextDouble()));
                    FallingBlock fB = l.getWorld().spawnFallingBlock(l, materials[r.nextInt(materials.length)].createBlockData());
                    fB.setDropItem(false);
                    fB.setHurtEntities(false);
                    set.add(fB);
                }
        }

        @Override
        public int[] phases(int level) {
            return switch (level) {
                case 1, 2 -> new int[]{3};
                case 3, 4 -> new int[]{3, 5};
                case 5 -> new int[]{3, 5, 7};
                default -> throw new IllegalArgumentException(level + " is not allowed!");
            };
        }

        @Override
        public void stop() {
            if (phase == phases + 1) cadavers.forEach(Cadaver::destory);
        }

        @Override
        public String name() {
            return "§bStonewrath";
        }
    }

    protected class Cadaver {
        private final ArmorStand stand;

        public Cadaver(Location l) {
            stand = l.getWorld().spawn(l.subtract(0, 1.5, 0), ArmorStand.class, armorStand -> {
                armorStand.setInvisible(true);
                armorStand.setMarker(true);
                armorStand.addScoreboardTag("remove");
                armorStand.getEquipment().setHelmet(Tools.getCustomTexturedHeadFromSkullValue("ewogICJ0aW1lc3RhbXAiIDogMTY3NzUwNDQ5NTIxOSwKICAicHJvZmlsZUlkIiA6ICJlMWFmMzI1NzM4MjU0MDE1YTYyZDZmZmFhY2U1YTIyNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJfcHZwU21hc2hfIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVlZTkzNWIxNWExODdjMjMzZTRkMTczZWU2MjFjMTYyYTI4OTFjNTc1ODI1MGIxNzFjZDM2Y2I3MmNhYjYzMiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9"));
            });
            cadavers.add(this);
        }

        public void revive() {
            destory();
            Clotgoyle clotgoyle = new Clotgoyle(VampireSlayerT1.this);
            clotgoyle.spawn(stand.getEyeLocation());
            clotgoyles.add(clotgoyle);
        }

        public void destory() {
            stand.remove();
        }
    }
    public void killClotgoyle(Clotgoyle clotgoyle) {
        clotgoyles.remove(clotgoyle);
        Cadaver cadaver = new Cadaver(clotgoyle.getEntity().getLocation());
        cadavers.add(cadaver);
    }
}
