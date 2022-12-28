package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;


import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Phase3;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Sadan extends SkyblockEntity {
    private int health = 40000000;
    Giant entity;
    private BukkitRunnable stabRunner;
    private BukkitRunnable laserRunner;
    private BukkitRunnable stompRunner;
    private BukkitRunnable yeetRunner;
    private Phase3 p3;
    public ArrayList<Sadan.StabPlayer> swords = new ArrayList<>();
    public ArrayList<Sadan.YeetPlayer> yeets = new ArrayList<>();
    private BossBar bar;


    public Sadan(){

    }
    public Sadan(Phase3 p3){
        this.p3 = p3;
    }



    @Override
    public int getMaxHealth() {
        return 40000000;
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
        return 34340;
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
        entity.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        entity.getEquipment().setChestplate(getColored(Color.fromRGB(0xD21077), Material.LEATHER_CHESTPLATE));
        entity.getEquipment().setItemInMainHand(new ItemBuilder(Material.DIAMOND_SWORD).setGlint(true).build());
        bar = Bukkit.createBossBar("§cSadan", BarColor.PURPLE, BarStyle.SOLID);
        for(Player p : Bukkit.getOnlinePlayers())
            bar.addPlayer(p);
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
        laserRunner = new BukkitRunnable() {
            @Override
            public void run() {
                List<Entity> e = entity.getNearbyEntities(16,16,16).stream().filter(entity1 -> entity1 instanceof Player).toList();
                if(!e.isEmpty()){
                    if(e.contains(entity.getTarget())){
                        laser(SkyblockPlayer.getSkyblockPlayer((Player) entity.getTarget()));
                    }else {
                        SkyblockPlayer newTarget = SkyblockPlayer.getSkyblockPlayer(((Player)e.get(new Random().nextInt(e.size()))));
                        entity.setTarget(newTarget);
                        laser(newTarget);
                    }
                }
            }
        };
        laserRunner.runTaskTimer(Main.getMain(), 10, 10);
        stompRunner = new BukkitRunnable() {
            @Override
            public void run() {
                if(!entity.getNearbyEntities(18,18,18).stream().filter(entity1 -> entity1 instanceof Player ).toList().isEmpty())
                    stomp();
            }
        };
        stompRunner.runTaskTimer(Main.getMain(), 200, 200);
        yeetRunner = new BukkitRunnable() {
            @Override
            public void run() {
                List<Entity> e = entity.getNearbyEntities(20,20,20).stream().filter(entity1 -> entity1 instanceof Player ).toList();
                if(!e.isEmpty()) {
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) e.get(0));
                    yeet(player);
                }
            }
        };
        yeetRunner.runTaskTimer(Main.getMain(), 60, 260);
    }
    private void yeet(SkyblockPlayer player){
        yeets.add(new Sadan.YeetPlayer(player, this));
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
                            c.entityToPlayerDamage(Sadan.this, player, new Bundle<>(120000, 0));
                            c.damagePlayer(player);
                            c.showDamageTag(player);
                        }
                }

            }
        }
                .runTaskTimer(Main.getMain(), 5, 1);
    }
    private void laser(SkyblockPlayer target){
        final Particle.DustOptions dust = new Particle.DustOptions(
                Color.fromRGB( 255, 0,  0), 2);
        Calculator c = new Calculator();
        c.entityToPlayerDamage(this, target, new Bundle<>(60000,0));
        c.damagePlayer(target);
        c.showDamageTag(target);
        Vector dir = target.getLocation().toVector().subtract(entity.getEyeLocation().toVector());
        dir.normalize();
        Location ll = entity.getLocation();
        ll.setDirection(dir.clone());
        entity.teleport(ll);
        dir = dir.multiply(0.2);
        Location l = entity.getEyeLocation().clone();
        int i = 0;
        while (target.getLocation().distance(l) > 0.5){
            l = l.add(dir);
            entity.getLocation().getWorld().spawnParticle(Particle.REDSTONE, l.getX(), l.getY(), l.getZ(),1, dust);
            i++;
            if(i > 1000000)
                break;
        }
    }
    private void stab(SkyblockPlayer player){
        swords.add(new Sadan.StabPlayer(player, this));
    }


    @Override
    public String getName() {
        return "Sadan";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§c§l" + getName());
        bar.setProgress(((double) getHealth())/(double) getMaxHealth());
    }
    private void stop(){
        try {
            stabRunner.cancel();
        }catch (Exception ignored){}
        try {
            laserRunner.cancel();
        }catch (Exception ignored){}
        try {
            stompRunner.cancel();
        }catch (Exception ignored){}
        try {
            yeetRunner.cancel();
        }catch (Exception ignored){}
        for (StabPlayer sP : (ArrayList<StabPlayer>)swords.clone())
            sP.cancel();
    }


    @Override
    public void kill() {
        stop();
        if(p3 != null)
            p3.end();
        bar.removeAll();
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
        private final Sadan entity;
        private Location L;
        private Giant sword;
        private int runntime = 0;

        public StabPlayer(SkyblockPlayer player, Sadan diamond){
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
    private static class YeetPlayer extends BukkitRunnable{
        private final Sadan entity;
        private Location L;
        private final Location finalLocation;
        private final ArrayList<ArmorStand> stands = new ArrayList<>();
        private int runtime = 0;
        private final double ticksMult;
        private Vector dir;
        private double beforeY = 0;

        public YeetPlayer(SkyblockPlayer player, Sadan diamond){
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
