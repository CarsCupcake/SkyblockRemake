package me.CarsCupcake.SkyblockRemake;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;


import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;

import org.bukkit.NamespacedKey;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;


public class Tools {
 public static float round(double num, int digit) {
	 double d = Math.pow(10, digit);
	 
	 return (float) (Math.round(num * d) / d);
 }
 
 public static Double StringToDouble(String str){
	 int mult = 1;
	 if(str.endsWith("k")) {
		 mult = 1000;
		 str =str.replace("k", "");
	 }
	 if(str.endsWith("m")) {
		 mult = 1000000;
		 str =str.replace("m", "");
	 }
	 if(str.endsWith("b")) {
		 mult = 1000000000;
		 str = str.replace("b", "");
	 }


	 try {
		 Double.parseDouble(str);
	 } catch (Exception e) {
		 return -1d;
	 }
	 return Double.parseDouble(str)*mult ;


 }
 public static double getFallSpeedFromTimeElapsed(int tick){
	 double part1 = 392d/5d;
	 double part2 = Math.pow(98d/100d, tick) -1;
	 double mainpart1 = part1*part2;

	 return mainpart1 /20;
 }
 

 public static @NotNull ItemStack CustomHeadTexture(String url) {
			 //, (short) 3
	ItemStack skull= new ItemStack(Material.PLAYER_HEAD, 1);

	    if (url == null || url.isEmpty())
	        return skull;

	    ItemMeta skullMeta = skull.getItemMeta();
	    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
	    byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
	    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
	    Field profileField = null;

	    try {
	        profileField = skullMeta.getClass().getDeclaredField("profile");
	    } catch (NoSuchFieldException | SecurityException e) {
	        e.printStackTrace();
	    }

	    profileField.setAccessible(true);

	    try {
	        profileField.set(skullMeta, profile);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
	        e.printStackTrace();
	    }
	    skull.setItemMeta(skullMeta);
	    return skull;
 }
	public static ItemStack getCustomTexturedHeadFromSkullValue(String value) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), "");
		profile.getProperties().put("textures", new Property("textures", value));
		Field profileField = null;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		head.setItemMeta(meta);
		return head;
	}
 @SuppressWarnings("deprecation")
public static ItemStack PlayerHeadTexture(String playerName) {

	ItemStack skull= new ItemStack(Material.PLAYER_HEAD, 1);

	    

	    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

	    skullMeta.setOwner(playerName);
	    skull.setItemMeta(skullMeta);
	    return skull;
 }
 
 public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
	 Random random = new Random();
     int x = random.nextInt(clazz.getEnumConstants().length);
     return clazz.getEnumConstants()[x];
 }
 
 public static ItemStack CustomHeadTexture(String url, String customUUID) {
	ItemStack skull= new ItemStack(Material.PLAYER_HEAD, 1);
	    if (url == null || url.isEmpty())
	        return skull;

	    ItemMeta skullMeta = skull.getItemMeta();
	    GameProfile profile = new GameProfile(UUID.fromString(customUUID), null);
	    byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
	    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
	    Field profileField = null;

	    try {
	        profileField = skullMeta.getClass().getDeclaredField("profile");
	    } catch (NoSuchFieldException | SecurityException e) {
	        e.printStackTrace();
	    }

	    profileField.setAccessible(true);

	    try {
	        profileField.set(skullMeta, profile);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
	        e.printStackTrace();
	    }
	    skull.setItemMeta(skullMeta);
	    return skull;
 }
 public static double[] rotatePoint2D(double[] arr, double angle) {
     angle = Math.toRadians(angle);
     double cos = Math.cos(angle);
     double sin = Math.sin(angle);
     double x = arr[0];
     double y = arr[1];
     arr[0] = ((x * cos) + (y * sin));
     arr[1] = (-(x * sin) + (y * cos));
     return arr;
 }
	public static void removeAllItemsFromInventory(SkyblockPlayer player, ItemManager manager){

		for(ItemStack item : player.getInventory())
			if(item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(manager.itemID)) {
				item.setAmount(0);

			}
	}
	public static void removeItemsFromInventory(SkyblockPlayer player, ItemManager manager, int amount){

		for(ItemStack item : player.getInventory())
			if(item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(manager.itemID)) {
				int itemAmount = item.getAmount();
				if(amount > itemAmount){
					amount -= itemAmount;
					item.setAmount(0);
				}else{
					item.setAmount(itemAmount-amount);
					amount -= itemAmount;
				}



				if(amount <= 0)
					return;

			}
	}

	public static String addDigits(int number) {
		String str = number + "";
		StringBuilder sb = new StringBuilder(str);
		sb.reverse();
		str = sb.toString();
		String newString = "";
		int digitRunner = 0;
		for(int i = 0; i < str.toCharArray().length; i++) {
			newString = newString + str.toCharArray()[i];
			digitRunner++;
			if(digitRunner == 3 && (i+ 1) != str.toCharArray().length) {
				digitRunner = 0;
				newString = newString + ",";
			}
		}
		sb = new StringBuilder(newString);


		return sb.reverse().toString();
	}
	public static String addDigits(double number) {
		String str = ((int)(number)) + "";
		StringBuilder sb = new StringBuilder(str);
		sb.reverse();
		str = sb.toString();
		String newString = "";
		int digitRunner = 0;
		for(int i = 0; i < str.toCharArray().length; i++) {
			newString = newString + str.toCharArray()[i];
			digitRunner++;
			if(digitRunner == 3 && (i+ 1) != str.toCharArray().length) {
				digitRunner = 0;
				newString = newString + ",";
			}
		}
		sb = new StringBuilder(newString);

		String finalString = sb.reverse().toString();

		return finalString + "."  +((int)((number - ((int)number))*10));
	}
	public static int getItemInPlayerInventory(ItemManager manager, SkyblockPlayer player){
	 int amount = 0;
	 for(ItemStack item : player.getInventory())
		 if(item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(manager.itemID))
			 amount += item.getAmount();
	 return amount;
	}

 public static double getPlayerFacingPI(Player p) {

	 double deg = 0;
	 double fin = 0;
	 double x = p.getLocation().getDirection().getX();
	 double z = p.getLocation().getDirection().getZ();

	 if(x > 0.0 && z >0.0) {
	 deg = x;
	 fin = (Math.PI/2)*deg;
	 }
	 if(x> 0.0 && z<0.0) {
	 deg = z*(-1);
	 fin = ((Math.PI/2)*deg)+(Math.PI/2);
	 }
	 if(x<0.0 && z<0.0) {
	 deg = x*(-1);
	 fin = (Math.PI/2)*deg+ Math.PI;
	 }
	 if(x<0.0 && z>0.0) {
	 System.out.println("SW");
	 deg = z;
	 fin = (Math.PI/2)*deg+(Math.PI/2 *3);
	 }
	 return fin;
	 }
 
 public static boolean isInRange(double ran1, double ran2, double number) {
	 if(ran1 > number && number > ran2) {
 		return true;
 	}
 	if(ran1 < number && number < ran2) {
 		return true;
 	}
 	return false;
 }


	public static String toShortNumber(double num) {
		String str;
		if (num > 999) {
			if (num > 9999) {
				if (num > 999999) {
					if (num > 9999999) {
						if (num > 999999999d) {
							if (num > 9999999999d)
						 	 str = (int)((num /1000000000)) + "b";
							else
								str = round(num /1000000000, 1) + "b";
						}else
							str = (int)((num /1000000)) + "m";

					}else
						str = round(num /1000000, 1) + "m";
				}else
					str = (int)((num /1000)) + "k";
			}else
				str = round(num /1000, 1) + "k";}else str = num + "";

		return str;
	}
 public static boolean hasFreeSlot(@NotNull SkyblockPlayer player){

	 int slot = player.getInventory().firstEmpty();


	 return (slot < 55 && slot >= 0);
 }

 
 
}
