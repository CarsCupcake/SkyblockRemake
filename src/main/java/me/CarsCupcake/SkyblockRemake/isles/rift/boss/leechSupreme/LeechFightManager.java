package me.CarsCupcake.SkyblockRemake.isles.rift.boss.leechSupreme;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.RegionCuboid;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.RiftEntity;
import me.CarsCupcake.SkyblockRemake.utils.Laser;
import me.CarsCupcake.SkyblockRemake.utils.SinusMovement;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.maps.CountMap;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import net.minecraft.network.protocol.game.PacketPlayOutEntity;
import net.minecraft.world.entity.item.EntityFallingBlock;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftMagicNumbers;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LeechFightManager {
    //Giant: 9 blocks down 3 back -> hand at set location
    private static final Location[] slimePots = {new Location(Bukkit.getWorld("world_the_end"), -156.5, 33, 49.5), new Location(Bukkit.getWorld("world_the_end"), -142.5, 33, 49.5), new Location(Bukkit.getWorld("world_the_end"), -156.5, 33, 63.5), new Location(Bukkit.getWorld("world_the_end"), -142.5, 33, 63.5)};
    @Getter
    private static final Location middle = new Location(Bukkit.getWorld("world_the_end"), -149.5, 33, 56.5);
    private static final Location[] bankPos = {new Location(Bukkit.getWorld("world_the_end"), -128.5, 35, 56.5), new Location(Bukkit.getWorld("world_the_end"), -170.5, 35, 56.5), new Location(Bukkit.getWorld("world_the_end"), -149.5, 35, 77.5)};
    public static final RegionCuboid bossArea = new RegionCuboid(new Vector(-178, 53, 38), new Vector(-123, 23, 80));
    private static LeechFightManager manager;
    @Getter
    private final LeechSupremeEntity entity;

    private LeechFightManager() {
        entity = new LeechSupremeEntity();
    }

    @NotNull
    public static LeechFightManager getInstance() {
        return (manager == null) ? manager = new LeechFightManager() : manager;
    }

    public void defeat() {
        manager = null;
        new BukkitRunnable() {
            @Override
            public void run() {
                getInstance().spawn();
            }
        }.runTaskLater(Main.getMain(), 600);
    }

    public void spawn() {
        entity.spawn(new Location(Bukkit.getWorld("world_the_end"), -149.5, 33, 56.5));
    }

    //16 radius for the platform
    public void slimePound() {
        entity.move(Tools.getRandom(bankPos), () -> {
            entity.getEntity().setAI(false);
            new EntityRunnable() {
                final Entity giant = middle.getWorld().spawn(middle.clone().subtract(0, 6, 3), Giant.class, (g) -> {
                    g.setInvisible(true);
                    g.getEquipment().setItemInMainHand(Tools.CustomHeadTexture("http://textures.minecraft.net/texture/e01ce68842074dde053185b218e34ee3259cb36ac471d80998f9cb01f32e51c7"));
                    g.setSilent(true);
                    g.setInvulnerable(true);
                    g.setGravity(false);
                    g.setRemoveWhenFarAway(false);
                    g.setCollidable(false);
                });
                int uses = 0;
                int cooldown = 0;
                Location target = Tools.getRandom(slimePots).clone().subtract(0, 6, 3);
                Vector dir = target.toVector().subtract(giant.getLocation().toVector()).normalize().multiply(0.5);

                @Override
                public void run() {
                    if (target == null) {
                        target = Tools.getRandom(slimePots).clone().subtract(0, 6, 3);
                        dir = target.toVector().subtract(giant.getLocation().toVector()).normalize().multiply(0.3);
                    }
                    if (cooldown != 0) {
                        if (cooldown > 30) giant.teleport(giant.getLocation().subtract(0d, 0.05, 0d));
                        else if (cooldown == 30) {
                            shockwave();
                            if (uses++ == 4) cancel();
                        } else if (cooldown > 10) giant.teleport(giant.getLocation().add(0d, 0.05, 0d));
                        cooldown--;
                        return;
                    }
                    if (giant.getLocation().distance(target) <= 0.7) {
                        giant.teleport(target);
                        target = null;
                        cooldown = 50;
                    } else giant.teleport(giant.getLocation().add(dir));
                }

                @Override
                public synchronized void cancel() throws IllegalStateException {
                    super.cancel();
                    giant.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, giant.getLocation().add(0, 9, 3), 1);
                    giant.remove();
                }

                private void shockwave() {
                    giant.getWorld().spawnParticle(Particle.SLIME, giant.getLocation().add(0, 9, 3), 4);
                    HashMap<EntityFallingBlock, Bundle<Location, Vector>> objects = new HashMap<>();
                    Material[] mats = {Material.SLIME_BLOCK, Material.EMERALD_BLOCK, Material.GREEN_WOOL, Material.SLIME_BLOCK, Material.SLIME_BLOCK};
                    Random r = new Random();
                    Location l = giant.getLocation().add(0, 6.5, 3);
                    l.getWorld().playSound(l, Sound.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 1f, 0.1f);
                    l.getWorld().playSound(l, Sound.BLOCK_ANVIL_LAND, SoundCategory.AMBIENT, 0.5f, 0.1f);
                    for (int i = 0; i < 360; i += 5) {
                        if(r.nextDouble() > 0.8) continue;
                        Vector vector = new Vector(0, 0, 1);
                        vector.rotateAroundY(i);
                        vector.normalize();
                        vector.multiply(0.7);
                        EntityFallingBlock b = new EntityFallingBlock(((CraftWorld) l.getWorld()).getHandle(), l.getX(), l.getY(), l.getZ(), CraftMagicNumbers.getBlock(Tools.getRandom(mats)).getBlockData());
                        b.b = 1;
                        b.setNoGravity(true);
                        ((CraftWorld) l.getWorld()).addEntity(b, CreatureSpawnEvent.SpawnReason.CUSTOM);
                        objects.put(b, new Bundle<>(l, vector));
                    }
                    new EntityRunnable() {
                        final CountMap<SkyblockPlayer> cooldown = new CountMap<>();
                        int i = 0;

                        @Override
                        public void run() {
                            if (i == 60) cancel();
                            cooldown.addAll(-1);
                            cooldown.removeByAmount(0);
                            for (EntityFallingBlock e : objects.keySet()) {
                                Location first = objects.get(e).getFirst();
                                Vector dir = objects.get(e).getLast();
                                Location nL = first.clone().add(dir);
                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                    ((CraftPlayer) pl).getHandle().b.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMove(e.getId(), (short) (((nL.getX() * 32) - (first.getX() * 32)) * 128), (short) (((nL.getY() * 32) - (first.getY() * 32)) * 128), (short) (((nL.getZ() * 32) - (first.getZ() * 32)) * 128), e.isOnGround()));
                                }
                                if (first.equals(nL)) System.out.println("something went wrong!");
                                objects.get(e).setFirst(nL);
                                e.getWorld().getWorld().getNearbyEntities(nL, 0.75, 0.75, 0.75).stream().filter((en) -> en instanceof Player p && !cooldown.containsKey(SkyblockPlayer.getSkyblockPlayer(p))).forEach((entity) -> {
                                    RiftPlayer player = RiftPlayer.getRiftPlayer((Player) entity);
                                    player.sendMessage("§cLeech Supreme §eremoved §a20s " + Stats.RiftTime.getSymbol() + " §efrom you using §dSlime Pound!");
                                    player.setVelocity(objects.get(e).getLast().clone().add(new Vector(0, 1, 0)).multiply(2));
                                    player.subtractRiftTime(20);
                                    cooldown.put(player, 2);
                                });
                            }
                            i++;
                        }

                        @Override
                        public synchronized void cancel() throws IllegalStateException {
                            super.cancel();
                            for (EntityFallingBlock e : objects.keySet())
                                e.die();
                            if (entity.getEntity().isDead()) return;
                            if (uses != 5) return;
                            entity.move(middle, () -> {
                                entity.getEntity().setAI(true);
                                entity.isInAbility = false;
                            });
                        }
                    }.runTaskTimer(entity, 0, 1);
                }
            }.runTaskTimer(entity, 0, 1);
        });
    }

    public void wickedBombs() {
        entity.getEntity().setAI(false);
        entity.move(Tools.getRandom(bankPos), () -> new BukkitRunnable() {
            int i = 0;
            final HashMap<BombAttacks, Double> map = Tools.mapOf(List.of(BombAttacks.Player, BombAttacks.PlayerPredict, BombAttacks.Random), List.of(0.6, 0.1, 0.3));

            @Override
            public void run() {
                i++;
                if (i > 80) cancel();
                List<Entity> e = middle.getWorld().getNearbyEntities(middle, 15, 20, 15).stream().filter(entity -> entity instanceof Player).toList();
                if (e.isEmpty()) BombAttacks.Random.run(null);
                else e.forEach((p) -> Tools.getOneItemFromLootTable(map).run(SkyblockPlayer.getSkyblockPlayer((Player) p)));

            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                entity.move(middle, () -> {
                    entity.isInAbility = false;
                    entity.getEntity().setAI(true);
                });
            }
        }.runTaskTimer(Main.getMain(), 0, 5));
    }

    public static class BombAttack {
        private final Set<Tools.FakeBlock> fakeBlocks = new HashSet<>();
        private final Block center;
        private final ArmorStand bomb;

        public BombAttack(Location location, RiftEntity entity) {
            location.setY(32);
            center = location.getBlock();
            for (var x = center.getX() - 1; x <= center.getX() + 1; x++)
                for (var z = center.getZ() - 1; z <= center.getZ() + 1; z++)
                    fakeBlocks.add(Tools.placeFakeBlock(new Location(location.getWorld(), x, 32, z).getBlock(), Material.GREEN_TERRACOTTA));
            bomb = location.getWorld().spawn(entity.getEntity().getLocation(), ArmorStand.class, armorStand -> {
                armorStand.setBasePlate(false);
                armorStand.setInvisible(true);
                armorStand.getEquipment().setHelmet(Tools.CustomHeadTexture("http://textures.minecraft.net/texture/75d096f8c68eec2a701d1d5d2d307c27f8dcbe8379d00528bfb2864c664c1"));
                armorStand.addScoreboardTag("remove");
                armorStand.setGravity(false);
                armorStand.setCollidable(false);
            });
            bomb.getWorld().playSound(entity.getEntity().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, SoundCategory.AMBIENT, 2f, 1.9f);
            Location start = entity.getEntity().getLocation();
            start.setY(33);
            new SinusMovement(1, 3, 0).move(bomb, () -> {
                bomb.remove();
                center.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, Tools.getAsLocation(center), 1);
                fakeBlocks.forEach(Tools.FakeBlock::release);
                fakeBlocks.clear();
                bomb.getWorld().playSound(Tools.getAsLocation(center), Sound.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.AMBIENT, 1.5f, 0.3f);
                center.getWorld().getNearbyEntities(Tools.getAsLocation(center), 1.5, 1.5, 1.5).stream().filter(entity1 -> entity1 instanceof Player).forEach(p -> {
                    RiftPlayer player = RiftPlayer.getRiftPlayer((Player) p);
                    player.sendMessage("§cLeech Supreme §eremoved §a20s " + Stats.RiftTime.getSymbol() + " §efrom you using §dWicked Bomb!");
                    player.subtractRiftTime(20);
                });
            }, 35, 10, start, Tools.getAsLocation(center), () -> {
                Location l = bomb.getLocation();
                l.setYaw(l.getYaw() + 40);
                bomb.teleport(l);
            });

        }
    }

    enum BombAttacks implements RunnableWithParam<SkyblockPlayer> {
        Random {
            @Override
            public void run(SkyblockPlayer player) {
                new BombAttack(new Location(middle.getWorld(), new Random().nextInt(-158, -140),
                        32, new Random().nextInt(48, 64)), getInstance().entity);
            }
        }, Player {
            @Override
            public void run(SkyblockPlayer player) {
                new BombAttack(player.getLocation(), getInstance().entity);
            }
        }, PlayerPredict {
            @Override
            public void run(SkyblockPlayer player) {
                Vector dir = player.getLocation().getDirection();
                dir.setY(0);
                dir.normalize();
                dir.multiply(2);
                new BombAttack(player.getLocation().add(dir), getInstance().entity);
            }
        }
    }
    int leechSwarm = 0;
    public void leechSwarm() {
        entity.getEntity().setAI(false);
        int amount = (int) middle.getWorld().getNearbyEntities(bossArea.toBoundingBox()).stream().filter(e -> e instanceof Player).count();
        if(amount == 0) leechSwarm = 8;
        else if(amount <= 3) leechSwarm = 8 * amount;
        else leechSwarm = 8 * 3;
        entity.move(Tools.getRandom(bankPos), () -> {
            int large;
            if(amount == 0) large = 2;
            else if(amount <= 3) large = 2 * amount;
            else large = 2 * 3;
            for (int i = 0; i < leechSwarm - large; i++)
                new LeechSwarm().spawn(getRandomSwarmLocation());
            System.out.println(large);
            for (int i = 0; i < large; i++)
                new LeechAlpha().spawn(getRandomSwarmLocation());
        });
    }
    private Location getRandomSwarmLocation(){
        Vector v = new Vector(0, 0, 17);
        v.rotateAroundY(new Random().nextInt(360));
        return middle.clone().add(v).subtract(0, 1, 0);
    }
    public void leechSwarmKill(){
        leechSwarm--;
        if(leechSwarm == 0){
            entity.move(getMiddle(), () -> {
                entity.getEntity().setAI(true);
                entity.isInAbility = false;
            });
        }
    }

    public void mortiferousLazer() {
        entity.getEntity().setAI(false);
        Vector forward = new Vector(0, 0, 0.4);
        Vector backwards = new Vector(0, 0, -0.4);
        //Z = 71
        entity.move(Tools.getRandom(bankPos), () -> {
            Laser l = new Laser(new Location(middle.getWorld(), -135, 33, 41), new Location(middle.getWorld(), -164, 33, 41));
            BoundingBox box = new BoundingBox(-135.5, 33.5, 41.5, -163.5, 32.5, 40.5);
            new EntityRunnable() {
                boolean forw = true;
                final CountMap<Player> cooldwon = new CountMap<>();
                boolean rotation = false;
                Set<FakeBlock> fakeBlocks = makeSaveSpot();
                int loop = 0;
                @Override
                public void run() {
                    for (FakeBlock b : fakeBlocks) b.b.getWorld().spawnParticle(Particle.SLIME, Tools.getAsLocation(b.b), 1);
                    cooldwon.addAll(-1);
                    cooldwon.removeByAmount(0);
                    Vector v = (rotation) ? ((forw) ? new Vector(0, -0.1, 0) : new Vector(0, 0.1, 0)) : ((forw) ? forward : backwards);
                    l.getStart().getEntity().teleport(l.getStart().getEntity().getLocation().add(v));
                    l.getEnd().getEntity().teleport(l.getEnd().getEntity().getLocation().add(v));
                    Location loc = l.getStart().getEntity().getLocation();
                    if(!rotation){
                        if (forw && Tools.isInRange(71, 72, loc.getZ())) {
                            forw = false;
                            rotation = true;
                            loop++;
                        }
                        if (!forw && Tools.isInRange(41, 42, loc.getZ())) {
                            forw = true;
                            releaseSpot(fakeBlocks);
                            fakeBlocks = makeSaveSpot();
                            rotation = true;
                            loop++;
                        }
                    }
                    if(rotation && ((loc.getY() >= 34.7 && !forw) || (loc.getY() <= 33 && forw))) rotation = false;
                    box.shift(v);
                    entity.getEntity().getWorld().getNearbyEntities(box).stream().filter(entity1 -> entity1 instanceof Player p && !cooldwon.containsKey(p)).forEach(e -> {
                        Player p = (Player) e;
                        cooldwon.put(p, 10);
                        RiftPlayer player = RiftPlayer.getRiftPlayer(p);
                        player.sendMessage("§cLeech Supreme §eremoved §a60s " + Stats.RiftTime.getSymbol() + " §efrom you using §dMortiferous Lazer!");
                        player.subtractRiftTime(60);
                        player.setVelocity(v.clone().multiply(3).setY(1));
                    });
                    if (loop >= 5) {
                        cancel();
                        entity.move(middle, () -> {
                            entity.getEntity().setAI(true);
                            entity.isInAbility = false;
                        });
                    }
                }

                @Override
                public synchronized void cancel() throws IllegalStateException {
                    super.cancel();
                    l.stop();
                    releaseSpot(fakeBlocks);
                }
            }.runTaskTimer(entity, 0, 1);
        });
    }
    private Set<FakeBlock> makeSaveSpot(){
        Set<FakeBlock> fakeBlocks = new HashSet<>();
        Location l = new Location(middle.getWorld(), new Random().nextInt(-158, -140),
                32, new Random().nextInt(48, 64));
        fakeBlocks.add(new FakeBlock(l.clone().getBlock(), Material.SPRUCE_SLAB));
        fakeBlocks.add(new FakeBlock(l.add(1, 0, 0).clone().getBlock(), Material.SPRUCE_SLAB));
        fakeBlocks.add(new FakeBlock(l.add(0, 0, 1).clone().getBlock(), Material.SPRUCE_SLAB));
        fakeBlocks.add(new FakeBlock(l.add(-1, 0, 0).clone().getBlock(), Material.SPRUCE_SLAB));
        return fakeBlocks;
    }
    private void releaseSpot(Set<FakeBlock> blocks){
        for (FakeBlock fakeBlock : blocks){
            fakeBlock.release();
            getPlayersInBox(fakeBlock.b.getBoundingBox()).forEach(player -> player.setVelocity(new Vector(0, 0.5, 0)));
        }
    }
    private Set<RiftPlayer> getPlayersInBox(BoundingBox box){
        Set<RiftPlayer> p = new HashSet<>();
        entity.getEntity().getWorld().getNearbyEntities(box)
                .stream().filter(e -> e instanceof Player)
                .forEach(e -> p.add(RiftPlayer.getRiftPlayer((Player) e)));
        return p;
    }
    public static class FakeBlock{
        private static final Set<FakeBlock> blocks = new HashSet<>();
        private final Block b;
        private final Material material;
        public FakeBlock(Block b, Material m){
            this.b = b;
            this.material = b.getType();
            if(blocks.contains(this)) return;
            b.setType(m);
            blocks.add(this);
        }
        public void release(){
            b.setType(material);
            blocks.remove(this);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof FakeBlock fb) return fb.b.equals(b);
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return Objects.hash(b);
        }

        public static void releaseAll(){
            for (FakeBlock b : blocks) b.release();
        }
    }
}
