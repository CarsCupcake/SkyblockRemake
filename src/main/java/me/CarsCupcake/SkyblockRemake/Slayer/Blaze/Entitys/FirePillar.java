package me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.*;

public class FirePillar {
    public static HashMap<Block, FirePillar> pillars = new HashMap<>();
    private static final String headURL = "https://textures.minecraft.net/texture/368743497820c08627d2cc5e891c0f9fc3793f75b6e41e140ac9b007d3b505a5";
    private final Set<ArmorStand> stands = new HashSet<>();
    private final ArmorStand base;
    private final SkyblockEntity entity;
    private int hits = 8;
    @Getter
    private final SkyblockPlayer player;
    private boolean isRemoved = false;
    private ArmorStand timer;
    private List<Bundle<Block, Material>> blocks;
    private int seconds = 7;

    public FirePillar(SkyblockEntity deployer, SkyblockPlayer target){
        this.entity = deployer;
        this.player = target;
        base = deployer.getEntity().getWorld().spawn(deployer.getEntity().getEyeLocation(), ArmorStand.class, armorStand -> {
            armorStand.setInvisible(true);
            armorStand.setInvulnerable(true);
            armorStand.addScoreboardTag("firePillar");
        });
        for(int i = 0; i < 7; i++){
            Location l = deployer.getEntity().getEyeLocation().add(0,2,0);
            l.setYaw(new Random().nextInt(360));
            stands.add(deployer.getEntity().getWorld().spawn(l, ArmorStand.class, armorStand -> {
                armorStand.setInvisible(true);
                armorStand.setInvulnerable(true);
                armorStand.getEquipment().setItemInMainHand(Tools.CustomHeadTexture(headURL));
                armorStand.setRightArmPose(new EulerAngle(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
                armorStand.addScoreboardTag("firePillar");
            }));
        }
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if(deployer.getEntity() == null || deployer.getEntity().isDead()){
                    cancel();
                    base.remove();
                    stands.forEach(Entity::remove);
                    return;
                }

                i++;
                if(i > 70){
                    base.teleport(deployer.getEntity().getEyeLocation().add(0,2,0));
                    cancel();
                    throwFire();
                    return;
                }
                base.teleport(deployer.getEntity().getEyeLocation().add(0,2,0));
                base.getWorld().spawnParticle(Particle.FLAME, base.getLocation().add(0,0.9, 0),5, 0.5, 0.5, 0.5, 0);
                for (ArmorStand s : stands){
                    s.setRightArmPose(s.getRightArmPose().add(0.2,0.2,0.2));
                    Location l = deployer.getEntity().getEyeLocation();
                    l = l.add(0,2,0);
                    l.setPitch(0);
                    l.setYaw(s.getLocation().getYaw() + 1);
                    s.teleport(l);
                }
            }
        }.runTaskTimer(Main.getMain(), 0, 1);
    }

    public void throwFire(){
        base.setVelocity(Vector.getRandom());
        new BukkitRunnable() {
            @Override
            public void run() {
                if(entity.getEntity() == null || entity.getEntity().isDead()){
                    cancel();
                    base.remove();
                    stands.forEach(Entity::remove);
                    return;
                }

                if(base.isOnGround()){
                    cancel();
                    placePillar(base.getLocation());
                    return;
                }

                base.getWorld().spawnParticle(Particle.FLAME, base.getLocation().add(0,0.9, 0),5, 0.5, 0.5, 0.5, 0);
                for (ArmorStand s : stands){
                    s.setRightArmPose(s.getRightArmPose().add(0.2,0.2,0.2));
                    Location l = base.getLocation();
                    l.setPitch(0);
                    l.setYaw(s.getLocation().getYaw() + 1);
                    s.teleport(l);
                }

            }
        }.runTaskTimer(Main.getMain(), 1, 1);
    }
    public void placePillar(Location l){
        base.remove();
        stands.forEach(Entity::remove);
        blocks = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            Block b = l.clone().add(0,i,0).getBlock();
            blocks.add(new Bundle<>(b, b.getType()));
            switch (i){
                case 0 -> b.setType(Material.BROWN_CONCRETE);
                case 1 -> b.setType(Material.RED_CONCRETE);
                case 2 -> b.setType(Material.ORANGE_CONCRETE);
                case 3 -> b.setType(Material.YELLOW_CONCRETE);
            }

            pillars.put(b, this);
        }
        timer = l.getWorld().spawn(l.clone().add(0.5,4,0.5), ArmorStand.class, stand -> {
            stand.setMarker(true);
            stand.setInvisible(true);
            stand.setCustomNameVisible(true);
        });
        timer.setCustomName("§e§l7s §c§l" + hits + " Hits");
        new BukkitRunnable() {
            @Override
            public void run() {
                if(isRemoved){
                    cancel();
                    return;
                }

                seconds--;
                if(seconds == 0){
                    cancel();
                    player.setHealth(0, HealthChangeReason.Ability);
                    setRemoved();
                    if(entity instanceof PillarThrower thrower)
                        thrower.setPillarExploded();
                    return;
                }
                timer.setCustomName("§e§l"+seconds+"s §c§l" + hits + " Hits");


            }
        }.runTaskTimer(Main.getMain(), 20, 20);
    }

    public void setRemoved(){
        isRemoved = true;
        timer.remove();
        for (Bundle<Block, Material> blockData : blocks){
            pillars.remove(blockData.getFirst());
            blockData.getFirst().setType(blockData.getLast());
        }
    }
    public void hit(){
        hits--;
        if(hits == 0) {
            setRemoved();
            if(entity instanceof PillarThrower thrower)
                thrower.setPillarDestroied();
        }
        else
            timer.setCustomName("§e§l"+seconds+"s §c§l" + hits + " Hits");
    }

    public static interface PillarThrower{
        void setPillarDestroied();
        void setPillarExploded();
    }

}
