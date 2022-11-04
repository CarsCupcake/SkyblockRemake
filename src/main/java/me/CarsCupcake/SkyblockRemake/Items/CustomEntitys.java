package me.CarsCupcake.SkyblockRemake.Items;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;



public class CustomEntitys {

	public static Entity IceWalker(Location loc) {
		@SuppressWarnings("deprecation")
		Entity entity = loc.getWorld().spawn(loc, Zombie.class, z->{
			z.getEquipment().setHelmet(new ItemStack(Material.PACKED_ICE));
			ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta meta = (boolean)item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
			meta.setColor(Color.AQUA);
			item.setItemMeta(meta);
			z.getEquipment().setChestplate(item);
			item.setType(Material.LEATHER_LEGGINGS);
			z.getEquipment().setLeggings(item);
			item.setType(Material.LEATHER_BOOTS);
			z.getEquipment().setBoots(item);
			z.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD));
			
			Main.entitydamage.put(z, 500);
			Main.baseentityhealth.put(z, 888);
			Main.currentityhealth.put(z, 888);
			z.addScoreboardTag("combatxp:40");
			z.addScoreboardTag("id:IceWalker");
			z.setRemoveWhenFarAway(false);
			
		});
		DiguestMobsManager.createEntity(entity, loc, "Ice Walker","ewogICJ0aW1lc3RhbXAiIDogMTY1MjYxNzc4NjE2MCwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzX0N1cGNha2UiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjJiMTJhODE0Y2VkOGFmMDJjZGRmMjlhMzdlN2YzMDExZTQzMGU4YTE4YjM4YjcwNmYyN2M2YmQzMTY1MGI2NSIKICAgIH0KICB9Cn0=",
		"MriUt+AvuZidZbndEAVowGALYyB25rLClpjFeQ9SpkCFP4d2u2w78NOfQ+t2Ao8nRnHPk3aNrm5LrO5axb8psGdSHanc/0ywmxGz2o4BefYyd78BBIqw1j2vku2p4b0gfqfREgZGe7OG8QG9btFPcVqmJ40enEklQchJ6cs1AIEAI+nsDC0TUifbVmgQdk0PPa1BIQ4JgyhcyxEAj7oao4xVDvN/KSjUoB6rueQ0Ad7GnF1UFp/dKOind5rVnRanUdzEp2uWeGzKpRu9jXjA29QQ4YmQzeRn229iBbPqApbbavgPJjSBbH2scM4kMVHG1y/D224p61tQxzjwgoS/p9+mB7CfrhsIc+GV5MCUg2BOZXkvSUyuL63F+jElsVffHgfoivaLoG/io+VL1oZ3c+9eTpTB/4sKaaQu9GOpNQfNSeMok5r9Bu2NmV4RcCprEW8hW2k/ULvk7csvAeTqSRyIY0bCeeOM0mKvcay2BBoVMVP9d82cUnLT2WOPbHPcvk7zL2QvmuJ4r5ZGYqmGqYULIHKrVF9qsd2gU/yI2q73jqXVcNdJkM1gNXCy9ynKewd8n7WYeKyiJQNvBblbWMHfokHds19S1+c3uHowtSX5kuD7HK/f7Gm9QqT3/wJjPg2xnBDWC9rCHPGwhkbHFgv5mdVU9tndbfjW7d6+rhM="
		);
		
		Attributable zombieAt = (Attributable) entity;
		AttributeInstance attributeSpeed = zombieAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
		attributeSpeed.setBaseValue(0.35);
		
		
		return entity;
		
	}
	public static Entity OldNecron(Location loc) {

		Entity entity = loc.getWorld().spawn(loc, Wither.class, z->{
			
			
			
			Main.entitydamage.put(z, 100000);
			Main.baseentityhealth.put(z, 1000000000);
			Main.currentityhealth.put(z, 1000000000);
			z.addScoreboardTag("CustomName:Necron");
			z.addScoreboardTag("singlename");
			
			
			
			
		});
		
		
		Attributable zombieAt = (Attributable) entity;
		AttributeInstance attributeSpeed = zombieAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
		attributeSpeed.setBaseValue(0.35);
		
		
		return entity;
	
	}
	public static Entity KuudraHotTier(Location loc) {

		Entity entity = loc.getWorld().spawn(loc, MagmaCube.class, z->{
			
			
			
			Main.entitydamage.put(z, 1000);
			Main.baseentityhealth.put(z, 700000);
			Main.currentityhealth.put(z, 700000);
			z.addScoreboardTag("CustomName:Kuudra");
			z.addScoreboardTag("singlename");
			z.setAI(false);
			z.setSize(30);
			
		});
		
		
		
		
		
		return entity;
	
	}
	public static Entity KuudraTentacle(Location loc) {

		Entity entity = loc.getWorld().spawn(loc,Wither.class, z->{
			
			
			
			Main.entitydamage.put(z, 0);

			z.addScoreboardTag("CustomName:Tentacle");
			z.addScoreboardTag("singlename");
			z.addScoreboardTag("dinnerbone");
			z.addScoreboardTag("kuudratentakle");
			z.addScoreboardTag("invinc");
			z.setCustomName(null);
			z.setAI(false);
			z.setGravity(false);
			z.getBossBar().removeAll();
			Main.WitherSmallStuff.put(z, 10000);
			z.setCustomNameVisible(false);
		
			
			
		});
		
		
		
		
		
		return entity;
	
	}
}
