package me.CarsCupcake.SkyblockRemake.isles.rift.boss.leechSupreme;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.RegionCuboid;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import net.minecraft.network.protocol.game.PacketPlayOutEntity;
import net.minecraft.world.entity.item.EntityFallingBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftMagicNumbers;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class BossFightManager {
    //Giant: 9 blocks down 3 back -> hand at set location
    private static final Location[] slimePots = {new Location(Bukkit.getWorld("world_the_end"), -156.5, 33, 49.5), new Location(Bukkit.getWorld("world_the_end"), -142.5, 33, 49.5), new Location(Bukkit.getWorld("world_the_end"), -156.5, 33, 63.5), new Location(Bukkit.getWorld("world_the_end"), -142.5, 33, 63.5)};
    private static final Location middle = new Location(Bukkit.getWorld("world_the_end"), -149.5, 33, 56.5);
    private static final Location[] bankPos = {new Location(Bukkit.getWorld("world_the_end"), -128.5, 35, 56.5), new Location(Bukkit.getWorld("world_the_end"), -170.5, 35, 56.5), new Location(Bukkit.getWorld("world_the_end"), -149.5, 35, 77.5)};
    public static final RegionCuboid bossArea = new RegionCuboid(new Vector(-178, 53, 38), new Vector(-123, 23, 80));
    private static BossFightManager manager;
    private final LeechSupremeEntity entity;
    private BossFightManager(){
        entity = new LeechSupremeEntity();
    }
    @NotNull
    public static BossFightManager getInstance(){
        return (manager == null) ? manager = new BossFightManager() : manager;
    }
    public void defeat(){
        manager = null;
        new BukkitRunnable() {
            @Override
            public void run() {
                getInstance().spawn();
            }
        }.runTaskLater(Main.getMain(), 200);
    }
    public void spawn(){
        entity.spawn(new Location(Bukkit.getWorld("world_the_end"), -149.5, 33, 56.5));
    }
    public void add(SkyblockPlayer player){

    }
    public void remove(SkyblockPlayer player){

    }
    public void slimePound(){
        entity.move(Tools.getRandom(bankPos), () -> {
            entity.getEntity().setAI(false);
            new EntityRunnable() {
                final Entity giant = middle.getWorld().spawn(middle.clone().subtract(0,6,3), Giant.class, (g) -> {
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
                Location target = Tools.getRandom(slimePots).clone().subtract(0,6,3);
                Vector dir = target.toVector().subtract(giant.getLocation().toVector()).normalize().multiply(0.5);
                @Override
                public void run() {
                    if(target == null) {
                        target = Tools.getRandom(slimePots).clone().subtract(0,6,3);
                        dir = target.toVector().subtract(giant.getLocation().toVector()).normalize().multiply(0.3);
                    }
                    if(cooldown != 0){
                        if(cooldown > 30) giant.teleport(giant.getLocation().subtract(0d, 0.05, 0d));
                        else if(cooldown == 30) {
                            shockwave();
                            if(uses++ == 4) cancel();
                        }
                        else if(cooldown > 10) giant.teleport(giant.getLocation().add(0d, 0.05, 0d));
                        cooldown--;
                        return;
                    }
                    if(giant.getLocation().distance(target) <= 0.7) {
                        giant.teleport(target);
                        target = null;
                        cooldown = 50;
                    }else giant.teleport(giant.getLocation().add(dir));
                }

                @Override
                public synchronized void cancel() throws IllegalStateException {
                    super.cancel();
                    giant.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, giant.getLocation().add(0,9, 3), 1);
                    giant.remove();
                }
                private void shockwave(){
                    giant.getWorld().spawnParticle(Particle.SLIME, giant.getLocation().add(0,9, 3), 4);
                    HashMap<EntityFallingBlock, Bundle<Location, Vector>> objects = new HashMap<>();
                    Material[] mats = {Material.SLIME_BLOCK, Material.EMERALD_BLOCK, Material.GREEN_WOOL, Material.SLIME_BLOCK, Material.SLIME_BLOCK};
                    for (int i = 0; i < 360; i += 5) {
                        Vector vector = new Vector(0, 0, 1);
                        vector.rotateAroundY(i);
                        vector.normalize();
                        vector.multiply(0.5);
                        Location l = giant.getLocation().add(0, 6.5, 3);
                        EntityFallingBlock b = new EntityFallingBlock(((CraftWorld) l.getWorld()).getHandle(), l.getX(), l.getY(), l.getZ(), CraftMagicNumbers.getBlock(Tools.getRandom(mats)).getBlockData());
                        b.b = 1;
                        b.setNoGravity(true);
                        ((CraftWorld) l.getWorld()).addEntity(b, CreatureSpawnEvent.SpawnReason.CUSTOM);
                        /*ArmorStand fallingBlock = giant.getWorld().spawn(giant.getLocation().add(0, 5.5, 3), ArmorStand.class, armorStand -> {
                            armorStand.setGravity(false);
                            armorStand.setInvisible(true);
                            armorStand.setInvulnerable(true);
                            armorStand.getEquipment().setHelmet(new ItemStack(Tools.getRandom(mats)));
                            armorStand.setCollidable(false);
                            armorStand.setSmall(false);
                        });*/
                        objects.put(b, new Bundle<>(l,vector));
                    }
                    new EntityRunnable() {
                        int i = 0;
                        @Override
                        public void run() {
                            if(i == 60)
                                cancel();
                            for (EntityFallingBlock e : objects.keySet()){
                                Location first = objects.get(e).getFirst();
                                Vector dir = objects.get(e).getLast();
                                Location nL = first.clone().add(dir);
                                for (Player pl : Bukkit.getOnlinePlayers()){
                                    ((CraftPlayer) pl).getHandle().b.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMove(e.getId(),(short) (((nL.getX() * 32) - (first.getX() * 32)) * 128), (short) (((nL.getY() * 32) - (first.getY() * 32)) * 128), (short) (((nL.getZ() * 32) - (first.getZ() * 32)) * 128), e.isOnGround()));
                                }
                                if(first.equals(nL)) System.out.println("something went wrong!");
                                objects.get(e).setFirst(nL);
                                e.getWorld().getWorld().getNearbyEntities(nL,0.75, 0.75, 0.75).stream().filter((en) -> en instanceof Player).forEach((entity) -> {
                                    RiftPlayer player = RiftPlayer.getRiftPlayer((Player) entity);
                                    player.sendMessage("§cLeech Supreme §eremoved §a20s " + Stats.RiftTime.getSymbol() + " §efrom you using §dSlime Pound!");
                                    player.setVelocity(objects.get(e).getLast().clone().add(new Vector(0, 1, 0)).multiply(2));
                                });
                            }
                            i++;
                        }

                        @Override
                        public synchronized void cancel() throws IllegalStateException {
                            super.cancel();
                            for (EntityFallingBlock e : objects.keySet())
                                e.die();
                            if(entity.getEntity().isDead()) return;
                            if(uses != 5) return;
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
    public void wickedBombs(){

    }
    public void leechSwarm(){

    }
    public void mortiferousLazer(){

    }
}
