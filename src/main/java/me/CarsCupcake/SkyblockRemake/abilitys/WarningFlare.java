package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Firework;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class WarningFlare implements Deployable {

    private Firework firework;
    private ArmorStand stand;
    private BukkitRunnable run;
    public boolean isActive = false;
    private int timer = 0;
    private SkyblockPlayer shooter;
    private Location baseLocation;
    private double motion = 0.07;
    private boolean upmotion = true;
    private  boolean turnaround = false;
    private  int runnable = 15;
    private  ArrayList<SkyblockPlayer> boosts = new ArrayList<>();
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        event.setCancelled(true);
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        if(Deployable.deployables.containsKey(player)){
            player.sendMessage("§eYour previous flare has been removed");
            Deployable.deployables.get(player).stop();

        }
        player.setDeployable(this);
        Deployable.deployables.put(player, this);
        firework = event.getPlayer().getLocation().getWorld().spawn(event.getPlayer().getLocation(), Firework.class, f->{
           FireworkMeta meta = f.getFireworkMeta();
           meta.setPower(1);
           meta.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(0x00FF00)).withTrail().with(FireworkEffect.Type.BURST).build());
           f.setFireworkMeta(meta);
           f.addScoreboardTag("shooter:" + player.getPlayer().getName());
        });
        shooter = player;

        return false;
    }







    @Override
    public void stop(){
        if(firework != null)
            firework.remove();
        if(stand != null)
            stand.remove();
        if(run != null && !run.isCancelled())
            run.cancel();
        isActive = false;
        Deployable.deployables.remove(shooter);
        for (SkyblockPlayer player : boosts){
            player.setBaseStat(Stats.TrueDefense, player.basetruedefense - 10);
            player.addHealingMult(-0.1);
        }
        shooter.setDeployable(null);
    }
    @Override
    public void start(Location loc){
        loc = loc.add(0,-1.975,0);
        firework = null;
        animation();
        isActive = true;
        baseLocation = loc;
        stand = loc.getWorld().spawn(loc, ArmorStand.class,s ->{
            s.setInvisible(true);
            s.setInvulnerable(true);
            s.setGravity(false);
            s.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/22e2bf6c1ec330247927ba63479e5872ac66b06903c86c82b52dac9f1c971458"));
        });
    }


    @Override
    public  void addStatBoost(PlayerMoveEvent event){
        if(baseLocation == null)
            return;
        if(event.getTo().distance(baseLocation) <= 40) {
            if(!boosts.contains(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()))){
                SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
                boosts.add(player);
                player.setBaseStat(Stats.TrueDefense, player.basetruedefense + 10);
                player.addHealingMult(0.1);

            }
        }else {
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
            if(boosts.contains(player)){
                boosts.remove(player);
                player.setBaseStat(Stats.TrueDefense, player.basetruedefense - 10);
                player.addHealingMult(-0.1);

            }
        }
    }

    private void animation(){
        run = new BukkitRunnable() {
            @Override
            public void run() {
                timer++;
                if(timer >= 20*60*3){
                    cancel();
                    stop();
                    return;
                }
                if(!turnaround) {
                    Location loc = stand.getLocation();
                    loc.setYaw((loc.getYaw() + 15));
                    if(upmotion) {

                        stand.teleport(loc.add(0,0.07,0));
                    }else {
                        stand.teleport(loc.add(0,-0.07,0));
                    }
                    runnable -= 1;

                    if(runnable == 0) {

                       turnaround = true;
                    }
                }else {

                    if(upmotion) {
                        motion  -= 0.005;
                    }else {
                        motion  += 0.005;
                    }
                    Location loc = stand.getLocation();
                    loc.setYaw((loc.getYaw() + 15));
                    stand.teleport(loc.add(0,motion,0));
                    if(motion <= -0.07 && upmotion) {
                        upmotion = false;
                        turnaround = false;
                        runnable = 15;
                    }else {
                        if(motion >= 0.07 && !upmotion) {
                            upmotion = true;
                            turnaround = false;
                            runnable = 15;
                        }
                }
                }

                stand.getWorld().spawnParticle(Particle.FLAME,stand.getEyeLocation(), 5,0.2,0.2,0.2,0.2,null);
            }
        };
        run.runTaskTimer(Main.getMain(), 0,1);
    }

}
