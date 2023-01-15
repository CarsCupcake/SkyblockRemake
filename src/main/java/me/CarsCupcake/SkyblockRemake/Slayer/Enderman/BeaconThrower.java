package me.CarsCupcake.SkyblockRemake.Slayer.Enderman;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class BeaconThrower extends Slayer {
    private BukkitRunnable beaconRunner;
    private YangGlyph glyph;
    public BeaconThrower(SkyblockPlayer player) {
        super(player);
    }
    protected void startBeaconPhase(){
        pickUp();
    }
    public void pickUp(){
        glyph = null;
        beaconRunner = new BukkitRunnable() {
            @Override
            public void run() {
                holdBeacon();
            }
        };
        beaconRunner.runTaskLater(Main.getMain(), 60);
    }
    private void holdBeacon(){
        ((Enderman)getEntity()).setCarriedBlock(Material.BEACON.createBlockData());
        beaconRunner = new BukkitRunnable() {
            @Override
            public void run() {
                ((Enderman)getEntity()).setCarriedBlock(Material.AIR.createBlockData());
                glyph = new YangGlyph(BeaconThrower.this, owner);
            }
        };
        beaconRunner.runTaskLater(Main.getMain(), 40);
    }
    @Override
    public void kill(){
        beaconRunner.cancel();
        if(glyph != null){
            try {
                glyph.remove();
            } catch (Exception ignored) {
            }
        }
    }
    public static class YangGlyph{
        static Set<YangGlyph> glyphSet = new HashSet<>();
        private final BeaconThrower entity;
        @Getter
        private final SkyblockPlayer player;
        private BukkitRunnable runner;
        private Block block;
        private Material beforeType;
        private ArmorStand stand;
        private YangGlyph(BeaconThrower thrower, SkyblockPlayer player){
            this.entity = thrower;
            this.player = player;
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 2);
            LivingEntity e = thrower.getEntity();
            stand = e.getWorld().spawn(e.getLocation().add(0, 0.2, 0), ArmorStand.class, s -> {
                s.setInvisible(true);
                s.setInvulnerable(true);
                s.getEquipment().setHelmet(new ItemStack(Material.BEACON));
            });
            Random rand = new Random();
            stand.setVelocity(
                    stand.getVelocity().add(new Vector((rand.nextDouble() - 0.5) * 2, .7, (rand.nextDouble() - 0.5)* 2)));
            runner = new BukkitRunnable() {
                @Override
                public void run() {
                    if(stand.isOnGround()){
                        cancel();
                        block = stand.getLocation().getBlock();
                        beforeType = block.getType();
                        block.setType(Material.BEACON);
                        stand.remove();
                        tickingBomb();
                    }
                }
            };
            runner.runTaskTimer(Main.getMain(), 1, 1);
        }
        private void tickingBomb(){
            glyphSet.add(this);
            runner = new BukkitRunnable() {
                int run = 0;

                @Override
                public void run() {
                    block.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, getBeaconLocation(), 5, -0.5, -0.5, -0.5);
                    block.getWorld().playSound(block.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.5F, (float) (0.5 + (run * 0.0125)));
                    if(run >= 5*20) {
                        remove();
                        player.setHealth(0, HealthChangeReason.Force);
                        entity.kill();
                        entity.getEntity().remove();
                        cancel();
                    }
                    run++;
                }
            };
            runner.runTaskTimer(Main.getMain(), 1, 1);
        }
        public void remove(){
            if(stand != null && !stand.isDead())
                stand.remove();
            if(block != null && beforeType != null)
                block.setType(beforeType);
            try {
                runner.cancel();
            }catch (Exception ignored){}
            glyphSet.remove(this);
        }
        public void pickUp(){
            remove();
            entity.pickUp();
            block.getWorld().spawnParticle(Particle.FLASH, getBeaconLocation(), 1);
            player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1 ,1);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1 ,1);

        }
        public Location getBeaconLocation(){
            return block.getLocation().add(0.5, 0, 0.5);
        }
    }
}
