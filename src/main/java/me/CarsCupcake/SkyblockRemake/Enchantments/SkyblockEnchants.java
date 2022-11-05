package me.CarsCupcake.SkyblockRemake.Enchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;

import java.util.Map;

import me.CarsCupcake.SkyblockRemake.Enchantments.NormalEnchants.DragonTracer;
import me.CarsCupcake.SkyblockRemake.Enchantments.NormalEnchants.Syphon;
import me.CarsCupcake.SkyblockRemake.Enchantments.UltEnchants.*;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;

public class SkyblockEnchants {
	public static ArrayList<String> ultEnchantIDs = new ArrayList<>();
	public static ArrayList<String> skyblockEnchantIds = new ArrayList<>();
	public static ArrayList<Enchantment> enchantments = new ArrayList<>();
	public static  final live_steal LIFESTEAL = new live_steal(NamespacedKey.minecraft("live_steal"));
	public static  final enchant_glient ENCHANT_GLINT = new enchant_glient(NamespacedKey.minecraft("glint"));
	public static final Telikinesis TELIKINESIS = new Telikinesis(NamespacedKey.minecraft("telikinesis"));
	public static final UltimateWise ULTIMATE_WISE = new UltimateWise();
	public static final Wisdom WISDOM = new Wisdom();
	public static final LastStand LAST_STAND = new LastStand();
	public static final Inferno INFERNO = new Inferno();
	public static final FatalTempo FATAL_TEMPO = new FatalTempo();
	public static final Chimera CHIMERA = new Chimera();
	public static final SoulEater SOUL_EATER = new SoulEater();


	public static final Syphon SYPHON = new Syphon();
	public static final DragonTracer DRAGON_TRACER = new DragonTracer();


	private static void initEvents(){
		registerEvent(new UltWiseListener());
		registerEvent(new WisdomListener());
		registerEvent(new LastStandListener());
		registerEvent(new InfernoListener());
		registerEvent(new FatalTempoListner());
		registerEvent(new ChimeraListener());
		registerEvent(new SoulEater());
		registerEvent(new NormalEnchantsListener());
		registerEvent(DRAGON_TRACER);
	}
	private static void registerEvent(Listener listener){
		Main.getMain().getServer().getPluginManager().registerEvents(listener, Main.getMain());
	}
	public static void register() {
		initEvents();
		registerEnchantment(LIFESTEAL);
		registerEnchantment(ENCHANT_GLINT);
		registerEnchantment(TELIKINESIS);
		registerEnchantment(ULTIMATE_WISE);
		registerEnchantment(WISDOM);
		registerEnchantment(LAST_STAND);
		registerEnchantment(INFERNO);
		registerEnchantment(FATAL_TEMPO);
		registerEnchantment(CHIMERA);
		registerEnchantment(SOUL_EATER);
		registerEnchantment(SYPHON);
		registerEnchantment(DRAGON_TRACER);
}
public static void unregister(){
	for (Enchantment enchantment : enchantments)
		unregisterEnchant(enchantment);
	enchantments.clear();
}
public static void registerEnchantment(Enchantment enchantment) {
	if(enchantment instanceof UltimateEnchant)
		ultEnchantIDs.add(enchantment.getKey().getKey());
	skyblockEnchantIds.add(enchantment.getKey().getKey());
	enchantments.add(enchantment);
	boolean registered = true;
	try {
		Field f = Enchantment.class.getDeclaredField("acceptingNew");
		f.setAccessible(true);
		f.set(null, true);
		Enchantment.registerEnchantment(enchantment);
	}catch(Exception e) {
		registered = false;
		e.printStackTrace();
	}
	if(!registered) {
		System.out.println("an error occured from the enchantment");
	}
}
public static void unregisterEnchant(Enchantment enchantment){
		skyblockEnchantIds.remove(enchantment);

	try {
		Field f = Enchantment.class.getDeclaredField("byKey");
		f.setAccessible(true);
		Map<NamespacedKey, Enchantment> map = (Map<NamespacedKey, Enchantment>) f.get(null);
		map.remove(enchantment.getKey());

		Field f2 = Enchantment.class.getDeclaredField("byName");
		f2.setAccessible(true);
		Map<String, Enchantment> map2 = (Map<String, Enchantment>) f2.get(null);
		map2.remove(enchantment.getName());
	}catch (Exception e){
		System.out.println("Something went wrong while unregistering the enchant: " + enchantment.getKey().getKey());
		e.printStackTrace();
		return;
	}
	System.out.println("Succesfully removed " + enchantment.getKey().getKey());
}
}
