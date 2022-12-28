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
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class GiantJollyPink extends SkyblockEntity {
    private int health = 25000000;
    Giant entity;
    private BukkitRunnable run;
    private Phase2 p2;
    public ArrayList<YeetPlayer> yeets = new ArrayList<>();

    public GiantJollyPink(){

    }
    public GiantJollyPink(Phase2 p2){
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
        entity.getEquipment().setHelmet(getColored(Color.fromRGB(0xD21077), Material.LEATHER_HELMET));
        entity.getEquipment().setBoots(getColored(Color.fromRGB(0xD21077), Material.LEATHER_BOOTS));
        entity.getEquipment().setLeggings(getColored(Color.fromRGB(0xD21077), Material.LEATHER_LEGGINGS));
        entity.getEquipment().setChestplate(getColored(Color.fromRGB(0xD21077), Material.LEATHER_CHESTPLATE));

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
                List<Entity> e = entity.getNearbyEntities(20,20,20).stream().filter(entity1 -> entity1 instanceof Player ).toList();
                if(!e.isEmpty()) {
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) e.get(0));
                    yeet(player);
                }
            }
        };
        run.runTaskTimer(Main.getMain(), 60, 260);
    }
    private void yeet(SkyblockPlayer player){
        yeets.add(new YeetPlayer(player, this));
    }


    @Override
    public String getName() {
        return "Jolly Pink";
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
        for (YeetPlayer sP : yeets)
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

    private static class YeetPlayer extends BukkitRunnable{
        private final GiantJollyPink entity;
        private Location L;
        private final Location finalLocation;
        private final ArrayList<ArmorStand> stands = new ArrayList<>();
        private int runtime = 0;
        private final double ticksMult;
        private Vector dir;
        private double beforeY = 0;

        public YeetPlayer(SkyblockPlayer player, GiantJollyPink diamond){
            Vector dir = player.getLocation().subtract(diamond.getEntity().getLocation()).toVector();
            ticksMult = getTeleportMult(diamond.getEntity().getLocation().distance(player.getLocation()));
            finalLocation = player.getLocation().clone();
            L = diamond.entity.getLocation();
            L =  L.setDirection(dir);
            this.dir = dir;
            entity = diamond;
            summonBlocks();
            this.runTaskTimer(Main.getMain(), 1 ,1);
        }
        private double getTeleportMult(double block){
            return 0.05*block;
        }

        private void summonBlocks(){
            Location l = L.clone();
            l.setYaw(0);
            l.setPitch(0);

            stands.add(entity.entity.getWorld().spawn(l, ArmorStand.class, a ->{
                a.setVisible(false);
                a.setInvulnerable(true);
                a.setGravity(false);
                a.getEquipment().setHelmet(new ItemStack(Material.DARK_OAK_WOOD));
                a.setCustomNameVisible(false);
            }));
            for (int i = 1; i < 3; i++){
                stands.add(entity.entity.getWorld().spawn(l.clone().add(0.625*i, 0, 0), ArmorStand.class, a -> {
                    a.setVisible(false);
                    a.setInvulnerable(true);
                    a.setGravity(false);
                    a.getEquipment().setHelmet(new ItemStack(Material.SMOOTH_STONE));
                    a.setCustomNameVisible(false);
                }));
                stands.add(entity.entity.getWorld().spawn(l.clone().add(-0.625*i, 0, 0), ArmorStand.class, a -> {
                    a.setVisible(false);
                    a.setInvulnerable(true);
                    a.setGravity(false);
                    a.getEquipment().setHelmet(new ItemStack(Material.SMOOTH_STONE));
                    a.setCustomNameVisible(false);
                }));
                stands.add(entity.entity.getWorld().spawn(l.clone().add(0, 0, 0.625*i), ArmorStand.class, a -> {
                    a.setVisible(false);
                    a.setInvulnerable(true);
                    a.setGravity(false);
                    a.getEquipment().setHelmet(new ItemStack(Material.SMOOTH_STONE));
                    a.setCustomNameVisible(false);
                }));
                stands.add(entity.entity.getWorld().spawn(l.clone().add(0, 0, -0.625*i), ArmorStand.class, a -> {
                    a.setVisible(false);
                    a.setInvulnerable(true);
                    a.setGravity(false);
                    a.getEquipment().setHelmet(new ItemStack(Material.SMOOTH_STONE));
                    a.setCustomNameVisible(false);
                }));
            }
            stands.add(entity.entity.getWorld().spawn(l.clone().add(0.625,0,-0.625), ArmorStand.class, a ->{
                a.setVisible(false);
                a.setInvulnerable(true);
                a.setGravity(false);
                a.getEquipment().setHelmet(new ItemStack(Material.SMOOTH_STONE));
                a.setCustomNameVisible(false);
            }));
            stands.add(entity.entity.getWorld().spawn(l.clone().add(-0.625,0,-0.625), ArmorStand.class, a ->{
                a.setVisible(false);
                a.setInvulnerable(true);
                a.setGravity(false);
                a.getEquipment().setHelmet(new ItemStack(Material.SMOOTH_STONE));
                a.setCustomNameVisible(false);
            }));
            stands.add(entity.entity.getWorld().spawn(l.clone().add(0.625,0,0.625), ArmorStand.class, a ->{
                a.setVisible(false);
                a.setInvulnerable(true);
                a.setGravity(false);
                a.getEquipment().setHelmet(new ItemStack(Material.SMOOTH_STONE));
                a.setCustomNameVisible(false);
            }));
            stands.add(entity.entity.getWorld().spawn(l.clone().add(-0.625,0,0.625), ArmorStand.class, a ->{
                a.setVisible(false);
                a.setInvulnerable(true);
                a.setGravity(false);
                a.getEquipment().setHelmet(new ItemStack(Material.SMOOTH_STONE));
                a.setCustomNameVisible(false);
            }));

        }
        private double getYPos(double i){
            return 4*Math.sin(i*0.5) + 0.5;
        }

        @Override
        public void run() {


            runtime++;

            if(runtime > 20) {
                cancel();
                L.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, L, 2);
                List<Entity> e = L.getWorld().getNearbyEntities(L, 7,7,7).stream().filter(entity1 -> entity1 instanceof Player).toList();
                for (Entity entity : e){
                    SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer((Player) entity);
                    Calculator c = new Calculator();
                    c.entityToPlayerDamage(this.entity, p, new Bundle<>(120000,0));
                    c.damagePlayer(p);
                    c.showDamageTag(p);
                }
            }

            for(ArmorStand s : stands){
                Location l = s.getLocation();
                l=  l.add(L.getDirection().multiply(ticksMult));
                l.add(0,getYPos((runtime*0.1)*Math.PI) - beforeY, 0);
                s.teleport(l);
            }
            L=  L.add(L.getDirection().multiply(ticksMult));
            L.add(0,getYPos((runtime*0.1)*Math.PI) - beforeY, 0);
            beforeY = getYPos((runtime*0.1)*Math.PI);

        }
        private void tpstands(){
            stands.get(0).teleport(L.clone());
        }

        @Override
        public void cancel(){
            super.cancel();
            entity.yeets.remove(this);
            for (ArmorStand s : stands)
                s.remove();

        }

    }
}
