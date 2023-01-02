package me.CarsCupcake.SkyblockRemake.AccessoryBag.Powers;

import org.bukkit.inventory.ItemStack;

import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;

public class Slender implements Powers {

	@Override
	public PowerLevelPackage getPackage() {
		PowerLevelPackage pack = new PowerLevelPackage(6.7, 4.8, 7.2, 0.6, 4.8, 0, 4.8, 0, 0, 0, 0, 0, 0.7, 0,0);
		pack.addBaseStat(Stats.Defense, 100);
		pack.addBaseStat(Stats.Strength, 50);
		return pack;
	}

	@Override
	public String getName() {
		return "Slender";
	}

	@Override
	public ItemStack getItem() {
		return Tools.CustomHeadTexture("http://textures.minecraft.net/texture/71e72890a79e500cf13a97d1374c5ac8a4f15a9e0d6885997fc6f2e3c11254c");
	}
	
	@Override
	public ItemManager getManager() {
		
		return new ItemManager("Biohazard Enderman", "BIOHAZARD_ENDERMAN", ItemType.PowerStone, ItemRarity.RARE, "http://textures.minecraft.net/texture/71e72890a79e500cf13a97d1374c5ac8a4f15a9e0d6885997fc6f2e3c11254c");
	}

}
