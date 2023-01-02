package me.CarsCupcake.SkyblockRemake.AccessoryBag.Powers;

import org.bukkit.inventory.ItemStack;

import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;

public class Bloody implements Powers {

	@Override
	public PowerLevelPackage getPackage() {
		PowerLevelPackage packages = new PowerLevelPackage( 0, 0, 3.6, 0, 10.8, 0, 10.8, 0, 0, 0, 0, 0, 0, 0,0);
		packages.addBaseStat(Stats.AttackSpeed, 10);
		return packages;
	}

	@Override
	public String getName() {
		return "Bloody";
	}

	@Override
	public ItemStack getItem() {
		ItemStack item = Tools.CustomHeadTexture("http://textures.minecraft.net/texture/a338347c8a8456602f3bd9ae78ad383a7de6b569bb60bcf7f7b4345f0ef64045");
		return item;
	}

	@Override
	public ItemManager getManager() {
		
		return new ItemManager("Beating Heart", "BEATING_HEART", ItemType.PowerStone, ItemRarity.RARE, "http://textures.minecraft.net/texture/a338347c8a8456602f3bd9ae78ad383a7de6b569bb60bcf7f7b4345f0ef64045");
	}

}
