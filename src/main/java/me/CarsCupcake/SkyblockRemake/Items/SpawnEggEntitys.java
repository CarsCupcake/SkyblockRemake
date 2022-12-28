package me.CarsCupcake.SkyblockRemake.Items;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Zombie;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;

public class SpawnEggEntitys {
	
	@SuppressWarnings("deprecation")
	public static void SummonT1Rev(Location loc, String playerName) {
		Zombie zombie = loc.getWorld().spawn(loc, Zombie.class);
		
		zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
		zombie.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		zombie.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/d8bee23b5c726ae8e3d021e8b4f7525619ab102a4e04be983b61414349aaac67"));
		zombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_HOE));
		Main.entitydamage.put(zombie, 15);
		Main.baseentityhealth.put(zombie, 500);
		Main.currentityhealth.put(zombie, 500);
		zombie.addScoreboardTag("abilityimun");
		zombie.addScoreboardTag("slayername");
		zombie.addScoreboardTag("CustomName:Revenant Horror");
		zombie.addScoreboardTag("revslayert1");
		zombie.addScoreboardTag("owner:" + playerName);
		zombie.setAdult();
		zombie.getEquipment().setBootsDropChance(0);
	    zombie.getEquipment().setLeggingsDropChance(0);
		zombie.getEquipment().setChestplateDropChance(0);
		zombie.getEquipment().setHelmetDropChance(0);
		zombie.getEquipment().setItemInHandDropChance(0);
		Main.updateentitystats(zombie);
		
	}
	
	@SuppressWarnings("deprecation")
	public static void SummonT2Rev(Location loc, String playerName) {
		Zombie zombie = loc.getWorld().spawn(loc, Zombie.class);
		
		zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
		zombie.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		zombie.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/d8bee23b5c726ae8e3d021e8b4f7525619ab102a4e04be983b61414349aaac67"));
		zombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_HOE));
		zombie.setAdult();
		Main.entitydamage.put(zombie, 25);
		Main.baseentityhealth.put(zombie, 20000);
		Main.currentityhealth.put(zombie, 20000);
		zombie.addScoreboardTag("abilityimun");
		zombie.addScoreboardTag("slayername");
		zombie.addScoreboardTag("CustomName:Revenant Horror");
		zombie.addScoreboardTag("revslayert2");
		zombie.addScoreboardTag("owner:" + playerName);
		zombie.getEquipment().setBootsDropChance(0);
	    zombie.getEquipment().setLeggingsDropChance(0);
		zombie.getEquipment().setChestplateDropChance(0);
		zombie.getEquipment().setHelmetDropChance(0);
		zombie.getEquipment().setItemInHandDropChance(0);
		Main.updateentitystats(zombie);
	}
	@SuppressWarnings("deprecation")
	public static void SummonT3Rev(Location loc, String playerName) {
		Zombie zombie = loc.getWorld().spawn(loc, Zombie.class);
		
		zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
		zombie.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		zombie.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/d8bee23b5c726ae8e3d021e8b4f7525619ab102a4e04be983b61414349aaac67"));
		zombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_HOE));
		zombie.setAdult();
		Main.entitydamage.put(zombie, 120);
		Main.baseentityhealth.put(zombie, 400000);
		Main.currentityhealth.put(zombie, 400000);
		zombie.addScoreboardTag("abilityimun");
		zombie.addScoreboardTag("slayername");
		zombie.addScoreboardTag("CustomName:Revenant Horror");
		zombie.addScoreboardTag("revslayert3");
		zombie.addScoreboardTag("owner:" + playerName);
		zombie.getEquipment().setBootsDropChance(0);
	    zombie.getEquipment().setLeggingsDropChance(0);
		zombie.getEquipment().setChestplateDropChance(0);
		zombie.getEquipment().setHelmetDropChance(0);
		zombie.getEquipment().setItemInHandDropChance(0);
		Main.updateentitystats(zombie);
	}
	@SuppressWarnings("deprecation")
	public static void SummonT4Rev(Location loc, String playerName) {
		Zombie zombie = loc.getWorld().spawn(loc, Zombie.class);
		
		zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
		zombie.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		zombie.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/d8bee23b5c726ae8e3d021e8b4f7525619ab102a4e04be983b61414349aaac67"));
		zombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_HOE));
		zombie.setAdult();
		Main.entitydamage.put(zombie, 400);
		Main.baseentityhealth.put(zombie, 1500000);
		Main.currentityhealth.put(zombie, 1500000);
		zombie.addScoreboardTag("abilityimun");
		zombie.addScoreboardTag("slayername");
		zombie.addScoreboardTag("CustomName:Revenant Horror");
		zombie.addScoreboardTag("revslayert4");
		zombie.addScoreboardTag("owner:" + playerName);
		zombie.getEquipment().setBootsDropChance(0);
	    zombie.getEquipment().setLeggingsDropChance(0);
		zombie.getEquipment().setChestplateDropChance(0);
		zombie.getEquipment().setHelmetDropChance(0);
		zombie.getEquipment().setItemInHandDropChance(0);
		Main.updateentitystats(zombie);
		zombie.setLootTable(null);
	}
	@SuppressWarnings("deprecation")
	public static void SummonRevT3MiniBoss1(Location loc) {
		Zombie zo = loc.getWorld().spawn(loc, Zombie.class, zombie->{
		
		zombie.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
		
		zombie.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		
		zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
	    zombie.getEquipment().setBootsDropChance(0);
	    zombie.getEquipment().setLeggingsDropChance(0);
		zombie.getEquipment().setChestplateDropChance(0);
		zombie.getEquipment().setHelmetDropChance(0);
		zombie.getEquipment().setItemInHandDropChance(0);
		zombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
		zombie.setAdult();
		Main.entitydamage.put(zombie, 320);
		Main.baseentityhealth.put(zombie, 24000);
		Main.currentityhealth.put(zombie, 24000);
		
		
		zombie.addScoreboardTag("CustomName:Revenant Sycophant");
		zombie.addScoreboardTag("combatxp:360");
		zombie.addScoreboardTag("slayerminibossname");
		});
		Main.updateentitystats(zo);
		
	}
	@SuppressWarnings("deprecation")
	public static void SummonRevT4MiniBoss1(Location loc) {
		Zombie zo = loc.getWorld().spawn(loc, Zombie.class, zombie->{
		
		zombie.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
		zombie.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
	
		zombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
		zombie.setAdult();
		Main.entitydamage.put(zombie, 800);
		Main.baseentityhealth.put(zombie, 90000);
		Main.currentityhealth.put(zombie, 90000);
		
		
		zombie.addScoreboardTag("CustomName:Revenant Champion");
		zombie.addScoreboardTag("combatxp:400");
		zombie.addScoreboardTag("slayerminibossname");
		zombie.getEquipment().setBootsDropChance(0);
	    zombie.getEquipment().setLeggingsDropChance(0);
		zombie.getEquipment().setChestplateDropChance(0);
		zombie.getEquipment().setHelmetDropChance(0);
		zombie.getEquipment().setItemInHandDropChance(0);
		});
		Main.updateentitystats(zo);
	}
	@SuppressWarnings("deprecation")
	public static void SummonRevT4MiniBoss2(Location loc) {
		Zombie zo = loc.getWorld().spawn(loc, Zombie.class, zombie->{
		ItemStack item1 = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta meta1 = (boolean)item1.hasItemMeta() ? (LeatherArmorMeta) item1.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item1.getType());
		meta1.setColor(Color.RED);
		item1.setItemMeta(meta1);
		
		zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
		zombie.getEquipment().setLeggings(new ItemStack(item1));
		zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		item1.setType(Material.LEATHER_HELMET);
		zombie.getEquipment().setHelmet(new ItemStack(item1));
		zombie.getEquipment().setItemInHand(new ItemStack(Material.LEGACY_GOLD_SWORD));
		zombie.setAdult();
		Main.entitydamage.put(zombie, 1600);
		Main.baseentityhealth.put(zombie, 360000);
		Main.currentityhealth.put(zombie, 360000);
		
		
		zombie.addScoreboardTag("CustomName:Deformed Revenant");
		zombie.addScoreboardTag("combatxp:1200");
		zombie.addScoreboardTag("slayerminibossname");
		zombie.addScoreboardTag("strongmini");
		zombie.getEquipment().setBootsDropChance(0);
	    zombie.getEquipment().setLeggingsDropChance(0);
		zombie.getEquipment().setChestplateDropChance(0);
		zombie.getEquipment().setHelmetDropChance(0);
		zombie.getEquipment().setItemInHandDropChance(0);
	});
	Main.updateentitystats(zo);
		
		
	}
	public static void SummonVoidT2(Location loc, String playerName) {
		@SuppressWarnings("deprecation")
		Enderman zo = loc.getWorld().spawn(loc, Enderman.class, zombie->{
		ItemStack item1 = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta meta1 = (boolean)item1.hasItemMeta() ? (LeatherArmorMeta) item1.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item1.getType());
		meta1.setColor(Color.RED);
		item1.setItemMeta(meta1);
		
		
		Main.entitydamage.put(zombie, 5000);
		Main.baseentityhealth.put(zombie, 15000000);
		Main.currentityhealth.put(zombie, 15000000);
		
		
		zombie.addScoreboardTag("CustomName:Voidgloom Seraph");
		zombie.addScoreboardTag("owner:" + playerName);
		zombie.addScoreboardTag("slayername");
		zombie.addScoreboardTag("voidgloomt2");
		zombie.addScoreboardTag("abilityimun");

		zombie.setTarget(Bukkit.getPlayer(playerName));
		
		zombie.getEquipment().setBootsDropChance(0);
	    zombie.getEquipment().setLeggingsDropChance(0);
		zombie.getEquipment().setChestplateDropChance(0);
		zombie.getEquipment().setHelmetDropChance(0);
		zombie.getEquipment().setItemInHandDropChance(0);
		zombie.attack(Bukkit.getPlayer(playerName));
	});
	Main.updateentitystats(zo);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void revSwitchEnragetOutfitOn(Zombie entity) {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta meta = (boolean)item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
		meta.setColor(Color.RED);
		item.setItemMeta(meta);
		entity.getEquipment().setChestplate(item);
		
	}
	public static void revSwitchEnragetOutfitOff(Zombie entity) {
		;
		entity.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		
	}
	
	
	
	
	

}
