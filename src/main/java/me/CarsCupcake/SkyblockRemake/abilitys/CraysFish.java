package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Ferocity;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.AdditionalManaCosts;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class CraysFish implements AbilityManager {
    private static final String itemID = "AXE_OF_THE_SHREDDED";
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {

        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());

        thrower(player);
        return false;
    }

    private void thrower(final SkyblockPlayer p) {
        Location throwLoc = p.getLocation().add(0, 1.2, 0);
        Vector throwVec = p.getLocation().add(p.getLocation().getDirection().multiply(10)).toVector().subtract(p.getLocation().toVector()).normalize().multiply(1.2D);

        ArmorStand armorStand =  p.getWorld().spawn(throwLoc, ArmorStand.class, stand ->{

            stand.getEquipment().setItemInMainHand(new ItemStack(Material.TROPICAL_FISH));
            stand.setInvulnerable(true);
            stand.setInvisible(true);
        });
        final Vector[] previousVector = {throwVec};
        Collection<Entity> damaged = new ArrayList<>();
        new BukkitRunnable() {
            private int run = -1;
            private SkyblockPlayer player = p;

            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                run++;
                if(run > 100) {
                    cancel();
                    return;
                }
                if(!armorStand.getLocation().getBlock().getType().isTransparent() || armorStand.isOnGround()) {
                    armorStand.remove();
                    cancel();
                    return;
                }

                double xPos = armorStand.getRightArmPose().getX();
                armorStand.setRightArmPose(new EulerAngle(xPos + 0.6D, 0.0D, 0.0D));

                Vector newVector = new Vector(throwVec.getX(), previousVector[0].getY() - 0.03D, throwVec.getZ());
                previousVector[0] = newVector;
                armorStand.setVelocity(newVector);

                for(Entity e : armorStand.getNearbyEntities(1, 1, 1)) {
                    if(e instanceof LivingEntity && e.getType() != EntityType.ARMOR_STAND && !(e instanceof Player)) {
                        if(damaged.contains(e)) continue;
                        damaged.add(e);
                        LivingEntity entity = (LivingEntity)e;
                        int stre = (int)Main.playerstrengthcalc(player);
                        double cd = Main.playercdcalc(player);
                        int weapondmg = (int)Main.weapondamage(player.getItemInHand());

                        float preMultiplier = (float) SkyblockRemakeEvents.additiveMultiplier(player);
                        double damage = 0;
                        int cc =(int) Main.playercccalc(player);
                        int cccalc = (int )(Math.random() * 100 + 1);



                        if(cccalc <= cc) {
                            damage = (5 + (float)weapondmg) * (1+((float)stre/100)) * (1+((float)cd/100)) * (1+(preMultiplier)) * (1+(SkyblockPlayer.getSkyblockPlayer(player).getAdititveMultiplier()));

                        }else {
                            damage = (5 + (float)weapondmg) * (1+((float)stre/100))* (1+(preMultiplier))* (1+(SkyblockPlayer.getSkyblockPlayer(player).getAdititveMultiplier()));
                        }



                        if(SkyblockEntity.livingEntity.containsKey(entity)) {
                            SkyblockEntity se = SkyblockEntity.livingEntity.get(entity);
                            se.damage((int)damage,SkyblockPlayer.getSkyblockPlayer(player));



                        }else {
                            int live = Main.currentityhealth.get(entity) - (int) damage;
                            Main.currentityhealth.replace(entity, live);}
                        if((SkyblockEntity.livingEntity.containsKey(entity) && SkyblockEntity.livingEntity.get(e).getHealth() <= 0) || (!SkyblockEntity.livingEntity.containsKey(entity)&&Main.currentityhealth.get(entity) <= 0) ) {

                            Main.EntityDeath(entity);
                            entity.damage(9999999,player);

                            if(SkyblockEntity.livingEntity.containsKey(e))
                                SkyblockEntity.livingEntity.remove(e);

                            entity.addScoreboardTag("killer:" + player.getName());
                        }
                        Main.updateentitystats(entity);
                        final double FINAL_DAMAGE = damage;
                        Location loc = new Location(entity.getWorld(), entity.getLocation().getX() ,entity.getLocation().getY() + 0.5 , entity.getLocation().getZ());

                        ArmorStand stand =  entity.getWorld().spawn(loc, ArmorStand.class, armorstand ->{
                            armorstand.setVisible(false);

                            armorstand.setGravity(false);
                            armorstand.setMarker(true);


                            armorstand.setCustomNameVisible(true);

                            armorstand.setInvulnerable(true);
                            if(cccalc <= cc) {
                                String name = "§f✧";
                                String num = "" + Tools.round(FINAL_DAMAGE, 0);
                                num.substring(1, num.length() -2);
                                int col =1;
                                int coltype = 1;
                                String colstr = "§f";

                                for (char x : num.toCharArray()) {
                                    name = name + colstr + x;
                                    ++col;
                                    if(col ==2) {
                                        col = 0;
                                        ++coltype;
                                        switch(coltype) {
                                            case 1:
                                                colstr = "§f";
                                                break;
                                            case 2:
                                                colstr = "§e";
                                                break;
                                            case 3:
                                                colstr = "§6";
                                                coltype = 0;
                                                break;

                                        }

                                    }
                                }
                                String x = "✧";
                                name = name + colstr + x;
                                armorstand.setCustomName(name);
                            }else
                                armorstand.setCustomName("§7" + FINAL_DAMAGE);

                            armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
                            armorstand.addScoreboardTag("damage_tag");
                            armorstand.setArms(false);

                            armorstand.setBasePlate(false);});

                        Main.getMain().killarmorstand(stand);
                        e.setCustomNameVisible(true);

                        if(Main.playerferocitycalc(player) != 0) {
                            int ferocity = (int)Main.playerferocitycalc(player);


                            if(ferocity < 100) {
                                Random r = new Random();
                                int low = 1;//includes 1
                                int high = 100;// includes 100
                                int result = r.nextInt(high-low) + low;
                                if(ferocity >= result) {

                                    Ferocity.hit(entity,(int) damage, cccalc <= cc, player);
                                    Main.updateentitystats((LivingEntity)e);
                                }
                            }else {
                                double hits =(double) ferocity / 100;

                                if(hits % 1 == 0) {

                                    SkyblockRemakeEvents.ferocity_call(entity, damage, cccalc, cc, player, (int)hits);


                                }else {
                                    int minus = (int) ((int)hits * 100);
                                    double hitchance = (double)ferocity - (double)minus;

                                    Random r = new Random();
                                    int low = 1;//includes 1
                                    int high = 100;// includes 100
                                    int result = r.nextInt(high-low) + low;

                                    if(hitchance >= result) {
                                        hits = hits +1;
                                    }
                                    SkyblockRemakeEvents.ferocity_call(entity, damage, cccalc, cc, player, (int)hits);
                                }
                            }
                        }



                    }






                }
            }



        }.runTaskTimer(Main.getMain(),0, 2);
    }





    @Override
    public boolean executeAbility(EntityDamageByEntityEvent event) {

        return false;
    }

}
