package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;


import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Phase2;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GiantDiamond extends SkyblockEntity {
    private int health = 25000000;
    Giant entity;
    private BukkitRunnable stabRunner;
    private Phase2 p2;
    public ArrayList<GiantDiamond.StabPlayer> swords = new ArrayList<>();

    public GiantDiamond(){

    }
    public GiantDiamond(Phase2 p2){
        this.p2 = p2;
    }



    @Override
    public int getMaxHealth() {
        return 25000000;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }


    @Override
    public int getDamage() {
        return 20000;
    }

    @Override
    public void spawn(Location loc) {
        GiantAI ai = new GiantAI(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(ai, CreatureSpawnEvent.SpawnReason.CUSTOM);

        entity = (Giant) ai.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.setAware(true);
        entity.addScoreboardTag("combatxp:3000");
        entity.setTarget((Player)Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())]);
        entity.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        entity.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        entity.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        entity.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        entity.getEquipment().setItemInMainHand(new ItemBuilder(Material.DIAMOND_SWORD).setGlint(true).build());

        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
        start();

    }
    private void start(){
        stabRunner = new BukkitRunnable() {
            @Override
            public void run() {
                List<Entity> e = entity.getNearbyEntities(20,20,20).stream().filter(entity1 -> entity1 instanceof Player ).toList();
                if(!e.isEmpty()) {
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) e.get(0));
                    stab(player);
                }
            }
        };
        stabRunner.runTaskTimer(Main.getMain(), 100, 300);
    }
    private void stab(SkyblockPlayer player){
        swords.add(new GiantDiamond.StabPlayer(player, this));
    }


    @Override
    public String getName() {
        return "Diamond";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§c§l" + getName());
    }
    private void stop(){
        try {
            stabRunner.cancel();
        }catch (Exception ignored){}
        for (GiantDiamond.StabPlayer sP : (ArrayList<GiantDiamond.StabPlayer>)swords.clone())
            sP.cancel();
    }

    @Override
    public void kill() {
        stop();
        if(p2 != null)
            p2.killGiant();
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }

    private static class StabPlayer extends BukkitRunnable{
        private final SkyblockPlayer player;
        private final GiantDiamond entity;
        private Location L;
        private Giant sword;
        private int runntime = 0;

        public StabPlayer(SkyblockPlayer player, GiantDiamond diamond){
            this.player = player;
            entity = diamond;
            summonGiant();
            this.runTaskTimer(Main.getMain(), 1 ,1);
        }

        private void summonGiant(){
            Location pL = player.getLocation().clone();
            pL.setPitch(0);
            pL.setYaw(0);

            sword = player.getWorld().spawn(pL, Giant.class,g ->{
                g.setAI(false);
                g.setCustomName("Dinnerbone");
                g.setCustomNameVisible(false);
                g.addScoreboardTag("npc");
                g.setInvisible(true);
                g.getEquipment().setItemInMainHand(new ItemBuilder(Material.DIAMOND_SWORD).setGlint(true).build());
                g.setGravity(false);
            });
        }

        @Override
        public void run() {
            if(runntime < 100){
                if(runntime < 90) {
                    Location pL = player.getLocation().clone();
                    pL.setPitch(0);
                    pL.setYaw(0);
                    pL.subtract(2,-4,4);
                    sword.teleport(pL);
                    L = player.getLocation();
                }
            }else{
                if(runntime == 101){
                    Location pL = player.getLocation().clone();
                    pL.setPitch(0);
                    pL.setYaw(0);
                    pL.subtract(2,1,4);
                    sword.teleport(pL);
                    L.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, L, 1);
                    List<Entity> e = pL.getWorld().getNearbyEntities(L,6,6,6).stream().filter(entity1 -> entity1 instanceof Player).toList();
                    for (Entity entity : e){
                        SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer((Player) entity);
                        Calculator c = new Calculator();
                        c.entityToPlayerDamage(this.entity, p, new Bundle<>(120000,0));
                        c.damagePlayer(p);
                        c.showDamageTag(p);
                    }
                }else {
                    if(runntime > 200){
                        cancel();
                    }
                }
            }

            runntime++;
        }
        @Override
        public void cancel(){
            super.cancel();
            sword.remove();
            entity.swords.remove(this);

        }

    }
}
