package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class ICBMDeployerAbility implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
            launchRocket(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        }
        if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR){
            addFuel(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        }
        return false;
    }

    private static void addFuel(SkyblockPlayer player){
        Inventory inv = Bukkit.createInventory(null, 27, "Your Missle:");
        ItemStack emptyItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = emptyItem.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName(" ");
        emptyItem.setItemMeta(meta);
        for(int i = 0; i < 27; i++)
            inv.setItem(i, emptyItem);
        int fuel = player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fueling"), PersistentDataType.INTEGER);
        ItemStack emptyFuelSlot = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        meta = emptyFuelSlot.getItemMeta();
        meta.setDisplayName("§cEmpty Uranium Fuel Slot");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Click on a Uranium Fuel in your");
        lore.add("§7Inventory to add it to the missle");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        emptyFuelSlot.setItemMeta(meta);
        for (int i = 11; i < 16; i++)
            inv.setItem(i, emptyFuelSlot);
        ItemStack fuelTank = Main.item_updater(Items.uraniumFuel().getRawItemStack(), player);
        if(fuel > 0)
            for(int i = 11; i < 11 + fuel; i++)
                inv.setItem(i, fuelTank);
        player.openInventory(inv);


    }
    public static void updatePlayersFuelInventory(SkyblockPlayer player){

        addFuel(player);
    }
    private void launchRocket(SkyblockPlayer player){
        if(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fueling"), PersistentDataType.INTEGER) <= 0) {
        player.sendMessage("§cNot Enouth fuel!");
        return;
    }
        if(player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
            player.setHealth(player.currhealth - Main.playerhealthcalc(player) / 2, HealthChangeReason.Ability);
            ItemStack item = player.getItemInHand();
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(new NamespacedKey(Main.getMain(), "fueling"), PersistentDataType.INTEGER, data.get(new NamespacedKey(Main.getMain(), "fueling"), PersistentDataType.INTEGER) -1 );
            item.setItemMeta(meta);
            player.setItemInHand(item);
        }
        player.damage(0.00001);

        final ArmorStand stand = player.getWorld().spawn(player.getLocation().add(0,0.5,0), ArmorStand.class, (s) ->{
            s.setInvulnerable(true);
            s.setInvisible(true);
            s.setRemoveWhenFarAway(false);
            s.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/f7beaa6bd21326ba10db9eeb72987d190444c481553467bd75cfcc382363d914"));
        });
        Location loc = player.getLocation().clone();
        loc.setPitch(-45);

        stand.setVelocity(loc.add(0,1,0).getDirection().multiply(1));
        final Particle.DustOptions dust = new Particle.DustOptions(
                Color.GRAY, 3);
        new BukkitRunnable() {

            @Override
            public void run() {

                if(stand.isOnGround()){
                    cancel();
                    stand.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, stand.getLocation().add(0, 0.5, 0), 6, 0, 0, 0, 6, null, true);
                    Location loc = stand.getLocation().clone();
                    stand.remove();
                    stand.getWorld().playSound(stand.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                    ArrayList<Entity> entities = new ArrayList<>();
                    for(Entity entity : loc.getWorld().getNearbyEntities(loc, 10,10,10)){

                        if(entity instanceof LivingEntity && !(entity instanceof Player) && !entities.contains(entity)){
                            Integer maxHealth;
                            if(SkyblockEntity.livingEntity.containsKey(entity))
                                maxHealth = SkyblockEntity.livingEntity.get(entity).getMaxHealth();
                            else
                                maxHealth = Main.baseentityhealth.get(entity);
                            if(maxHealth == null) {
                                entities.add(entity);
                                continue;
                            }
                            Calculator calculator = new Calculator();
                            calculator.damage = Tools.round(maxHealth/4,0) ;
                            if(calculator.damage > 1000000)
                                calculator.damage = 1000000;
                            calculator.damageEntity((LivingEntity) entity, player);
                            calculator.showDamageTag(entity);
                            ((LivingEntity)entity).damage(0.0001);


                        }
                        entities.add(entity);
                    }
                }else{
                    Vector dir = stand.getVelocity();

                    double pitch = -Math.atan2(dir.getY(), Math.hypot(dir.getX(), dir.getZ()));
                    stand.setHeadPose(new EulerAngle(pitch ,0, 0));
                    stand.getWorld().spawnParticle(Particle.FLAME, stand.getEyeLocation().clone().add(stand.getEyeLocation().getDirection().multiply(-0.625)), 1,0.1,0.1,0.1,0.1);

                    stand.getWorld().spawnParticle(Particle.REDSTONE, stand.getEyeLocation().clone().add(stand.getEyeLocation().getDirection().multiply(-0.625)), 1,dust);
                }
            }
        }.runTaskTimer(Main.getMain(),0,1);
    }
}
