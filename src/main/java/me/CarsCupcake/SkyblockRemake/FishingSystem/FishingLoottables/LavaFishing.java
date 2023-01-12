package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables;

import me.CarsCupcake.SkyblockRemake.CrimsonIsle.CrimsonIsleAreas;
import me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottable;
import me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures.*;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;

public class LavaFishing implements FishingLoottable {
    @Override
    public LivingEntity summonSeaCreature(SkyblockPlayer player, Location spawnLoc, Vector vector) {
        if(CrimsonIsleAreas.getPlayerLocation(player) == CrimsonIsleAreas.PlhlegblastPool){
            if(new Random().nextDouble() < 0.0001){
                Plhlegblast entity = new Plhlegblast();
                entity.spawn(spawnLoc);
                entity.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 20, false, false, false));
                entity.getEntity().setVelocity(vector);
                return entity.getEntity();
            }
        }

        if(new Random().nextDouble() < 0.0017){
            player.sendMessage("§aYou have angered a legendary creature... Lord Jawbus has arrived");
            LordJawbus entity = new LordJawbus();
            entity.spawn(spawnLoc);
            entity.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 20, false, false, false));
            entity.getEntity().setVelocity(vector);
            return entity.getEntity();
        }
        if(new Random().nextDouble() < 0.0087){
            player.sendMessage("§aYou hear a massive rumble as Thunder emerges.");
            Thunder entity = new Thunder();
            entity.spawn(spawnLoc);
            entity.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 20, false, false, false));
            entity.getEntity().setVelocity(vector);
            return entity.getEntity();
        }
        if(new Random().nextDouble() < 0.0347){
            player.sendMessage("§aTaurus and his Steed Emerge.");
            Taurus entity = new Taurus();
            entity.spawn(spawnLoc);
            entity.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 20, false, false, false));
            entity.getEntity().setVelocity(vector);
            entity.getPig().getEntity().setVelocity(vector);
            return entity.getEntity();
        }
        if(new Random().nextDouble() < 0.0608){
            player.sendMessage("§aA Fire Eel slithers out from the depths.");
            FireEel entity = new FireEel();
            entity.spawn(spawnLoc);
            entity.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 20, false, false, false));
            entity.getEntity().setVelocity(vector);
            return entity.getEntity();
        }
        if(new Random().nextDouble() < 0.0694){
            player.sendMessage("§aA Lava Flame flies out from beneath the lava.");
            LavaFlame entity = new LavaFlame();
            entity.spawn(spawnLoc);
            entity.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 20, false, false, false));
            entity.getEntity().setVelocity(vector);
            return entity.getEntity();
        }
        if(new Random().nextDouble() < 0.1302){
            player.sendMessage("§aA small but fearsome Lava Leech emerges.");
            LavaLeech entity = new LavaLeech();
            entity.spawn(spawnLoc);
            entity.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 20, false, false, false));
            entity.getEntity().setVelocity(vector);
            return entity.getEntity();
        }


        if(new Random().nextDouble() < 0.2604)
        {
            player.sendMessage("§aYou hear a faint Moo from the lava... A Moogma appears.");
            Moogma moogma = new Moogma(player);
            moogma.spawn(spawnLoc);
            moogma.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 20, false, false, false));
            moogma.getEntity().setVelocity(vector);
            return moogma.getEntity();
        }else{
            player.sendMessage("§aFrom beneath the lava appears a Magma Slug.");
            MagmaSlug entity = new MagmaSlug();
            entity.spawn(spawnLoc);
            entity.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 20, false, false, false));
            entity.getEntity().setVelocity(vector);
            return entity.getEntity();
        }
    }

    @Override
    public ItemStack getDrop(SkyblockPlayer player) {
        double trophyChance = Main.playerTrophyFishingChance(player);
        trophyChance -= 1;
        Random r = new Random();
        double rr = r.nextDouble();
        if(trophyChance > rr)
            return tophyFishPool(player).createNewItemStack();
        else{
            if(0.05 > r.nextDouble()){
                if(0.1> r.nextDouble())
                    return largeTreasurePool(player).createNewItemStack();
                else
                    return treasurePool(player).createNewItemStack();
            }else
                return regularPool().createNewItemStack();

        }
    }
    private ItemManager tophyFishPool(SkyblockPlayer player){
        player.sendMessage("Trophy Fish when?");
        return regularPool();

    }
    private ItemManager regularPool(){
        int pool = new Random().nextInt(200);
        if(pool < 100)
            return Items.SkyblockItems.get("MAGMA_FISH");
        pool-=100;
        if(pool < 60)
            return Items.SkyblockItems.get(Material.MAGMA_CREAM.toString());
        pool -= 60;
        if(pool< 20)
            return Items.SkyblockItems.get(Material.NETHERRACK.toString());
        pool -= 20;
        if(pool < 15)
            return Items.SkyblockItems.get(Material.BLAZE_POWDER.toString());
        else
            return Items.SkyblockItems.get(Material.COAL.toString());




    }
    private ItemManager treasurePool(SkyblockPlayer player){
        int pool = new Random().nextInt(48);
        if(pool < 25) {
            player.sendMessage("§e§lGOOD CATCH! §fYou cought §9Random Bait §8(Comming Soon)");
            return Items.SkyblockItems.get(Material.MAGMA_CREAM.toString());
        }
        pool -= 25;
        if(pool < 15) {
            int coins = new Random().nextInt(10000) + 10000;
            player.sendMessage("§e§lGOOD CATCH! §fYou cought §6"+ Tools.addDigits(coins)+" coins");
            player.setCoins(player.coins + coins);
            return Items.SkyblockItems.get(Material.AIR.toString());
        }
        pool -= 15;
        if(pool < 5){
            player.sendMessage("§e§lGOOD CATCH! §fYou cought §aGrand Experience Bottle! §8(Comming Soon)");
            return Items.SkyblockItems.get(Material.EXPERIENCE_BOTTLE.toString());
        }else {
            ItemManager manager = Items.SkyblockItems.get("MAGMA_FISH_SILVER");
            player.sendMessage("§e§lGOOD GATCH! §fYou cought "+manager.rarity.getPrefix() + manager.name+"§f!");
            return manager;
        }

    }
    private ItemManager largeTreasurePool(SkyblockPlayer player){
        int pool = new Random().nextInt(1230);
        if(pool < 400){
            ItemManager manager = Items.SkyblockItems.get("ENCHANTED_MAGMA_CREAM");
            player.sendMessage("§5§lGREAT CATCH! §fYou cought "+manager.rarity.getPrefix() + manager.name+"§f!");
            return manager;
        }
        pool -= 400;
        if(pool < 300){
            ItemManager manager = Items.SkyblockItems.get("ENCHANTED_BLAZE_POWDER");
            player.sendMessage("§5§lGREAT CATCH! §fYou cought "+manager.rarity.getPrefix() + manager.name+"§f!");
            return manager;
        }
        pool -= 300;
        if(pool < 170){
            ItemManager manager = Items.SkyblockItems.get("ENCHANTED_NETHER_STALK");
            player.sendMessage("§5§lGREAT CATCH! §fYou cought "+manager.rarity.getPrefix() + manager.name+"§f!");
            return manager;
        }
        pool -= 170;
        if(pool < 170){
            ItemManager manager = Items.SkyblockItems.get("ENCHANTED_COAL");
            player.sendMessage("§5§lGREAT CATCH! §fYou cought "+manager.rarity.getPrefix() + manager.name+"§f!");
            return manager;
        }
        pool -= 170;
        ItemManager manager;
        if(pool < 150){
            manager = Items.SkyblockItems.get("LAVA_SHELL");
        }else {
            manager = Items.SkyblockItems.get(Material.EXPERIENCE_BOTTLE.toString());
        }
        player.sendMessage("§5§lGREAT CATCH! §fYou cought "+manager.rarity.getPrefix() + manager.name+"§f!");
        return manager;
    }
}
