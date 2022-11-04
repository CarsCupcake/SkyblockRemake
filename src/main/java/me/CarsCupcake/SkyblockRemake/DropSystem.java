package me.CarsCupcake.SkyblockRemake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.CarsCupcake.SkyblockRemake.Items.Items;



public class DropSystem {
public DropSystem(Entity entity, Player player, EntityDeathEvent e) {

	LivingEntity le = (LivingEntity) entity;
	if(SkyblockEntity.livingEntity.containsKey(le)){
		e.getDrops().clear();
		HashMap<ItemManager,Integer> drops = SkyblockEntity.livingEntity.get(le).getDrops(SkyblockPlayer.getSkyblockPlayer(player));
		if(drops != null && !drops.isEmpty()){
			for(ItemManager item : drops.keySet()) {
				ItemStack i = item.createNewItemStack();
				i.setAmount(drops.get(item));
				e.getDrops().add(Main.item_updater(i, SkyblockPlayer.getSkyblockPlayer(player)));
			}
		}
		return;
	}


	
	if(entity.getScoreboardTags().contains("revslayert4")) {
		e.getDrops().clear();
		
		Random r = new Random();
		  int low = 50;
		  int high = 64;
		  int result = r.nextInt(high-low) + low;
		  ItemStack item = Items.Revenant_Flesh();
		  item.setAmount(result);
		  e.getDrops().add(item);
		  
		 
		  //Uneffected by magic find
		  
		  double droprate = 0.1622;
		  
		  if(r.nextDouble() <=droprate) {
			 
			  low = 2;
			  high = 3;
			  result = r.nextInt(high-low) + low;
			  item = Items.Foul_Flesh();
			  item.setAmount(result);
			  e.getDrops().add(item);
			  player.sendMessage("§1§lOccasional Drop! §r(" + result + "x Foul Flesh)");
		  }
		  droprate = 0.0633;
		  if(r.nextDouble() <=droprate) {
			  
              item = Items.Pestilence_Rune();

			  e.getDrops().add(item);
			  player.sendMessage("§b§lRare Drop! §r(1x Pestilence Rune)");
		  }
		  
		  
		  
		  //affected by magic find
		  
		  droprate = 0.006*(1+(Main.playermagicfindcalc(player)/100));
		  if(r.nextDouble() <=droprate) {
			  e.getDrops().add(Items.Undead_Catalist());
			  player.sendMessage("§5§lExtraordinary Drop! §r(1x Undead Catalist) §b(+" + Main.playermagicfindcalc(player) + "✯)");
		  }
		  droprate = 0.0081*(1+(Main.playermagicfindcalc(player)/100));
		  if(r.nextDouble() <=droprate) {
			  e.getDrops().add(Items.Smite_6());
			  player.sendMessage("§5§lExtraordinary Drop! §r(1x Smite VI) §b(+" + Main.playermagicfindcalc(player) + "✯)");
		  }
		  droprate = 0.0016*(1+(Main.playermagicfindcalc(player)/100));
		  if(r.nextDouble() <=droprate) {
			  e.getDrops().add(Items.Beheaded_Horror());
			  player.sendMessage("§d§lPray RNGsus Drop! §r(1x Behaeded Horror) §b(+" + Main.playermagicfindcalc(player) + "✯)");
		  }
		  droprate = 0.0101*(1+(Main.playermagicfindcalc(player)/100));
		  if(r.nextDouble() <=droprate) {
			  e.getDrops().add(Items.Revenant_Catalist());
			  player.sendMessage("§5§lExtraordinary Drop! §r(1x Revenant Catalyst) §b(+" + Main.playermagicfindcalc(player) + "✯)");
		  }
		  droprate = 0.0016*(1+(Main.playermagicfindcalc(player)/100));
		  if(r.nextDouble() <=droprate) {
			  e.getDrops().add(Items.Snake_Rune());
			  player.sendMessage("§d§lPray RNGsus Drop! §r(1x Snake Rune) §b(+" + Main.playermagicfindcalc(player) + "✯)");
		  }
		  droprate = 0.0006*(1+(Main.playermagicfindcalc(player)/100));
		  if(r.nextDouble() <=droprate) {
			  e.getDrops().add(Items.Sythe_Blade());
			  player.sendMessage("§d§lPray RNGsus Drop! §r(1x Sythe Blade) §b(+" + Main.playermagicfindcalc(player) + "✯)");
		  }
		
		
	}
	if(entity.getScoreboardTags().contains("voidgloomt2")) {
		Random r = new Random();
		  int low = 14;
		  int high = 24;
		  int result = r.nextInt(high-low) + low;
		  ItemStack item = Items.NullSphere();
		  item.setAmount(result);
		  e.getDrops().add(item);
	}
	
}
}
