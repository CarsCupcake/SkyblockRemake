package me.CarsCupcake.SkyblockRemake.Entitys;

import java.util.ArrayList;
import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Corruptable;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class IceWalker extends SkyblockEntity implements Corruptable {

	private int maxHealht = 888;
	private int health = 888;
	private boolean isCorrupted = false;
	private Zombie z;
	public final String name = "Ice Walker";
	
	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return maxHealht;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	@Override
	public LivingEntity getEntity() {
		// TODO Auto-generated method stub
		return z;
	}



	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 500;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void spawn(Location loc) {
		

		z = loc.getWorld().spawn(loc, Zombie.class,zombie -> {
			DiguestMobsManager.createEntity(zombie, loc, "Ice Walker","ewogICJ0aW1lc3RhbXAiIDogMTY1MjYxNzc4NjE2MCwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzX0N1cGNha2UiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjJiMTJhODE0Y2VkOGFmMDJjZGRmMjlhMzdlN2YzMDExZTQzMGU4YTE4YjM4YjcwNmYyN2M2YmQzMTY1MGI2NSIKICAgIH0KICB9Cn0=",
					"MriUt+AvuZidZbndEAVowGALYyB25rLClpjFeQ9SpkCFP4d2u2w78NOfQ+t2Ao8nRnHPk3aNrm5LrO5axb8psGdSHanc/0ywmxGz2o4BefYyd78BBIqw1j2vku2p4b0gfqfREgZGe7OG8QG9btFPcVqmJ40enEklQchJ6cs1AIEAI+nsDC0TUifbVmgQdk0PPa1BIQ4JgyhcyxEAj7oao4xVDvN/KSjUoB6rueQ0Ad7GnF1UFp/dKOind5rVnRanUdzEp2uWeGzKpRu9jXjA29QQ4YmQzeRn229iBbPqApbbavgPJjSBbH2scM4kMVHG1y/D224p61tQxzjwgoS/p9+mB7CfrhsIc+GV5MCUg2BOZXkvSUyuL63F+jElsVffHgfoivaLoG/io+VL1oZ3c+9eTpTB/4sKaaQu9GOpNQfNSeMok5r9Bu2NmV4RcCprEW8hW2k/ULvk7csvAeTqSRyIY0bCeeOM0mKvcay2BBoVMVP9d82cUnLT2WOPbHPcvk7zL2QvmuJ4r5ZGYqmGqYULIHKrVF9qsd2gU/yI2q73jqXVcNdJkM1gNXCy9ynKewd8n7WYeKyiJQNvBblbWMHfokHds19S1+c3uHowtSX5kuD7HK/f7Gm9QqT3/wJjPg2xnBDWC9rCHPGwhkbHFgv5mdVU9tndbfjW7d6+rhM="
					);
			
			zombie.addScoreboardTag("combatxp:40");
			zombie.addScoreboardTag("id:IceWalker");
			zombie.setRemoveWhenFarAway(false);
			zombie.getEquipment().setHelmet(new ItemStack(Material.PACKED_ICE));
			ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (boolean)item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
			meta.setColor(Color.AQUA);
			item.setItemMeta(meta);
			zombie.getEquipment().setChestplate(item);
			item.setType(Material.LEATHER_LEGGINGS);
			zombie.getEquipment().setLeggings(item);
			item.setType(Material.LEATHER_BOOTS);
			zombie.getEquipment().setBoots(item);
			zombie.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD));
			zombie.getEquipment().setBootsDropChance(0);
			zombie.getEquipment().setHelmetDropChance(0);
			zombie.getEquipment().setLeggingsDropChance(0);
			zombie.getEquipment().setChestplateDropChance(0);
			zombie.getEquipment().setItemInHandDropChance(0);
			
		});

		Attributable zombieAt = z;
			AttributeInstance attributeSpeed = zombieAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
			attributeSpeed.setBaseValue(0.35);
		SkyblockEntity.livingEntity.put(z, this);
		
	}





	@Override
	public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {

		return null;
	}
	

	@Override
	public void updateNameTag() {
		if(isCorrupted)
			DiguestMobsManager.getDiguested.get(z).setName("§7[§8Lv?§7] §5§ka§5Corrupted " + getName()
					+ "§ka §a" + (int) health + "§8/§a" + getMaxHealth());
		else
			DiguestMobsManager.getDiguested.get(z).setName("§7[§8Lv?§7] §c" + getName()
		            + " §a" + (int) health + "§8/§a" + getMaxHealth());
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub 
		return name;
	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasNoKB() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void damage(double damage, SkyblockPlayer player) {
		health -= damage;
		
	}

	@Override
	public int getTrueDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void corrupt() {
		if(isCorrupted)
			return;
		isCorrupted = true;
		maxHealht *= 3;
		health *= 3;
		Main.updateentitystats(z);
	}

	@Override
	public boolean isCorrupted() {
		return isCorrupted;
	}
}
