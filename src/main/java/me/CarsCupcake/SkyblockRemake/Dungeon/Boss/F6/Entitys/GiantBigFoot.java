package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;


import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Phase2;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GiantBigFoot extends SkyblockEntity {
    private int health = 25000000;
    Giant entity;
    private BukkitRunnable run;
    private Phase2 p2;

    public GiantBigFoot(){

    }
    public GiantBigFoot(Phase2 p2){
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
        entity.getEquipment().setBoots(getColored(Color.RED, Material.LEATHER_BOOTS));
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
        start();

    }
    private ItemStack getColored(Color c, Material m){
        ItemStack i = new ItemStack(m);
        LeatherArmorMeta e = (LeatherArmorMeta) i.getItemMeta();
        e.setColor(c);
        i.setItemMeta(e);
        return i;
    }
    private void start(){
        run = new BukkitRunnable() {
            @Override
            public void run() {
                if(!entity.getNearbyEntities(18,18,18).stream().filter(entity1 -> entity1 instanceof Player ).toList().isEmpty())
                    stomp();
            }
        };
        run.runTaskTimer(Main.getMain(), 200, 200);
    }
    private void stomp(){
        entity.setVelocity(new Vector(0,10,0));
        new BukkitRunnable() {
            @Override
            public void run() {
                        if(entity.isDead())
                            cancel();
                        if(entity.isOnGround()){
                            cancel();
                            List<Entity> e = entity.getNearbyEntities(20,20,20).stream().filter(entity1 -> entity1 instanceof Player && entity1.isOnGround()).toList();
                            if(!e.isEmpty())
                                for (Entity eP : e){
                                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) eP);
                                    Calculator c = new Calculator();
                                    c.entityToPlayerDamage(GiantBigFoot.this, player, new Bundle<>(120000, 0));
                                    c.damagePlayer(player);
                                    c.showDamageTag(player);
                                }
                        }

            }
        }
        .runTaskTimer(Main.getMain(), 5, 1);
    }


    @Override
    public String getName() {
        return "Bigfoot";
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
            run.cancel();
        }catch (Exception ignored){}
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
}
