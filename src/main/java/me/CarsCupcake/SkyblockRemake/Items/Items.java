package me.CarsCupcake.SkyblockRemake.Items;


import java.lang.reflect.Field;
import java.util.*;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Crafting.ShapeEncoder;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockRecipe;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockShapedRecipe;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.F6Items;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.TextTerminal;
import me.CarsCupcake.SkyblockRemake.End.EndItems;
import me.CarsCupcake.SkyblockRemake.FishingSystem.RodType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Slayer.Enderman.EndermanSlayerItems;
import me.CarsCupcake.SkyblockRemake.WinterIsle.WinterItems;
import me.CarsCupcake.SkyblockRemake.abilitys.*;
import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Drill.DrillPart;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemState;
import me.CarsCupcake.SkyblockRemake.Gemstones.Gemstone;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneType;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Pets.PetType;
import org.bukkit.scheduler.BukkitRunnable;


public class Items {
public static HashMap<String, ItemManager> SkyblockItems = new HashMap<>();
	public static void init() {
		initAllItems();
		initBaseItems();
		storm_boots();
		storm_leggings();
		storm_chestplate();
	Hyperion();
	TestItem();
	live_steal_book();
	protection_book();
	Bonemerang();
	terminator();
	wither_googles();
	HotPotatoBook();
	Recombobulator3000();
	Divans_Drill();
	Mithril();
	Titanium();
	Gemstone_Gauntlet();
	SwordOfTheUniverse();
	CannonTestItem();
	RoughRuby();
	FlawedRuby();
	FineRuby();
	FlawlessRuby();
	PerfectRuby();
	
	RoughAmber();
	FlawedAmber();
	FineAmber();
	FlawlessAmber();
	PerfectAmber();
	
	RoughJade();
	FlawedJade();
	FineJade();
	FlawlessJade();
	PerfectJade();
	
	
	RoughSapphire();
	FlawedSapphire();
	FineSapphire();
	FlawlessSapphire();
	PerfectSapphire();
	
	RoughAmethyst();
	FlawedAmethyst();
	FineAmethyst();
	FlawlessAmethyst();
	PerfectAmethyst();
	
	RoughJasper();
	FlawedJasper();
	FineJasper();
	FlawlessJasper();
	PerfectJasper();
	
	RoughTopaz();
	FlawedTopaz();
	FineTopaz();
	FlawlessTopaz();
	PerfectTopaz();
	
	SorrowBoots();
	SorrowLeggings();
	SorrowChestplate();
	SorrowHelmet();
	
	DivanHelmet();
	DivanChestplate();
	DivanLeggings();
	DivanBoots();
	
	ManaFluxPowerOrb();
	Volta();
	
	MithrilInfusedFuelTank();
	TitaniumInfusedFuelTank();
	GemstoneFuelTank();
	PerfectlyCutFuelTank();
	
	MithrilPlatedDrillEngine();
	TitaniumPlatedDrillEngine();
	RubyPolishedDrillEngine();
	SapphirePolishedDrillEngine();
	AmberPolishedDrillEngine();
	TakoPowerdDrillEngine();
	
	JerryChineGun();
	
	EnderDragonLegendary();
	CarsCupcakeSpetial();
	
	GemstoneGolemCommon();
	GemstoneGolemUncommon();
	GemstoneGolemRare();
	GemstoneGolemEpic();
	GemstoneGolemLegendary();
	TestAccessory();
	RoyalPigeon();
	
	Zoom();
	raygun();
	
	Chopstick();
	
	TestHelmet();
	TestChestplate();
	TestLeggings();
	TestBoots();
	
	ReaperMask();
	
	CrimsonHelmet();
	CrimsonChestplate();
	CrimsonLeggings();
	CrimsonBoots();
	
	TerrorHelmet();
	TerrorChestplate();
	TerrorLeggings();
	TerrorBoots();
	
	AuroraStaff();
	
	AuroraHelmet();
	AuroraChestplate();
	AuroraLeggings();
	AuroraBoots();
	
	FervorHelmet();
	
	dctrSpaceHelmet();
	kloonboat();
	
	AxeOfTheShredded();
	stormsHelmet();
	
	
	DumbshitSword();
	
	maidHelmet();
	maidChestplate();
	maidLeggings();
	maidBoots();
	intellijItem();

	sulphur();
	enchantedSulphur();
	moltenPowder();
	warningFlare();
	infernoVertex();
	alertFlare();
	infernoApex();
	wilsonEngineeringPlans();
	sosFlare();
		susFlare();
		rodOfTheSea();
		craysFish();
		witherCatalyst();
		new WitherSets();
		necrons_handle();
		necrons_blade();
		laser_eye();
		valkyrie();
		diamantesHandle();
		jollyPinkRock();
		astrea();
		bigfootsLasso();
		scylla();
		wardenHelmet();
		bonzoStaff();
		placeLocationSetter();
		PainReleaser();
		ChadStick();
		reaperPepper();
		chiliPepper();
		stuffedChiliPepper();
		enchantedBlazeRod();
		new RequestedItems();
		uraniumFuel();
		unstackableBlazeRod();
		lividDagger();
		dreadlordSword();
		spititScepter();
		flowerOfTruth();
		aspectOfTheEnd();
		aspectOfTheVoid();
		aspectOfTheVoid_upgraded();
		stockOfStonks();
		iceSprayWand();
		totemOfCorruption();
		redBelt();
		thunderShards();
		ThunderboltNecklace();
		thunderHelmet();
		thunderChestplate();
		thunderLeggings();
		thunderBoots();

		magmaLordFragment();
		magmaLordHelmet();
		magmaLordChestplate();
		magmaLordLeggings();
		magmaLordBoots();
		magmaLordGauntlet();

		magmaFish();
		silverMagmaFish();
		goldMagmaFish();
		diamondMagmaFish();
		lavaShell();

		hellfireRod();


		enchantedMagmaCream();
		enchantedBlazePowder();
		enchantedNetherWart();
		enchantedCoal();

		lumpOfMagma();

		Spirit.addArmorSets();
		HollowSpirit.addItem();
		new EndItems();

		new EndermanSlayerItems();

		new F6Items();
		CoinsItem.init();
		SkillXpItem.init();
		new WinterItems();

	}

	public static void raygun() {
		ItemManager manager = new ItemManager("Raygun", "RAYGUN", ItemType.Bow, 99999999, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, 0, 0, 0, Material.BOW, ItemRarity.SPECIAL);
		SkyblockItems.put(manager.itemID, manager);
	}
	private static void lividDagger(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Throw your dagger at your");
		abilityLore.add("§7enemies!");
		ItemManager manager = new ItemManager("Livid Dagger", "LIVID_DAGGER" , ItemType.Sword, null,"Throw","",abilityLore,150,5,0,0,Material.IRON_SWORD,ItemRarity.LEGENDARY);
		manager.setDamage(210);
		manager.setStat(Stats.Strength, 60);
		manager.setStat(Stats.CritChance, 100);
		manager.setStat(Stats.CritDamage, 50);
		manager.setStat(Stats.AttackSpeed, 50);
		manager.setAbility(new LividDaggerability(), AbilityType.RightClick);
		abilityLore = new ArrayList<>();
		abilityLore.add("§7Your Critical Hits deal §9100%");
		abilityLore.add("§7more damage if you are behind");
		abilityLore.add("§7your target.");
		manager.set2Ability("", new LividDaggerability(), AbilityType.EntityHit, abilityLore, 0,0);
		manager.addSlot(new GemstoneSlot(SlotType.Jasper));
		manager.setNpcSellPrice(500000);
		manager.setDungeonItem(true);


		SkyblockItems.put(manager.itemID, manager);

	}
	private static void redBelt(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7This is also called 'The Belt of Love'");
		ItemManager manager = new ItemManager("Red Belt", "DEBUG_BELT" , ItemType.Belt, null,"Love","",abilityLore,0,0,0,0,ItemRarity.SPECIAL,"https://textures.minecraft.net/texture/72b181a304da499b586497fcd1e5fb82ab6c2707643693c68c5a49a572b441a5");


		manager.setStat(Stats.Inteligence, 10);
		manager.setEquipmentAbility(new EquipmentAbility() {
			private BukkitRunnable run;
			@Override
			public void start(SkyblockPlayer player) {
				run = new BukkitRunnable() {
					@Override
					public void run() {
						player.getEyeLocation().getWorld().spawnParticle(Particle.HEART, player.getEyeLocation().add(0,0.25,0), 1);
					}
				};
				run.runTaskTimer(Main.getMain(), 0, 2);
			}

			@Override
			public void stop() {
				run.cancel();
			}
		});
		manager.setNpcSellPrice(69);


		SkyblockItems.put(manager.itemID, manager);

	}
	private static void thunderShards(){
		ArrayList<String> lore = new ArrayList<>();
		
		ItemManager manager = new ItemManager("Thunder Shards", "THUNDER_SHARDS" , ItemType.Non, lore,null,null,null,0,0,
				0,0,ItemRarity.EPIC, "https://textures.minecraft.net/texture/7b3328d3e9d710420322555b17239307f12270adf81bf63afc50faa04b5c06e1");


		
		manager.setNpcSellPrice(50000);
		manager.setUnstackeble(true);

		SkyblockItems.put(manager.itemID, manager);

	}
	private static void ThunderboltNecklace(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Gains §c1 §7static charge every");
		abilityLore.add("§e%time% §7seconds.");
		abilityLore.add("§7Hitting a target will cause a");
		abilityLore.add("§7discharge, adding §a%damage% §7damage");
		abilityLore.add("§7to that hit for each charge");
		abilityLore.add("§7collected");
		abilityLore.add(" ");
		abilityLore.add("§7Maximum Charge Capacity: §c%charge%");
		ItemManager manager = new ItemManager("Thunderbolt Necklace", "THUNDERBOLT_NECKLACE" , ItemType.Necklace, null,"Static Charge","",abilityLore,0,0,
				0,0,ItemRarity.EPIC, "https://textures.minecraft.net/texture/6ceed3b08fa34982bc3faaa9936941b6cd00abd37173ee67b3ce3b9e18bfca6c");


		manager.setUnstackeble(true);
		manager.setStat(Stats.Health, 40);
		manager.setStat(Stats.Defense, 30);
		manager.setStat(Stats.SeaCreatureChance, 1.5);
		manager.setFullSetBonus(Bonuses.StaticCharge, true);
		manager.setNpcSellPrice(75000);
		AbilityLore lore = new AbilityLore(abilityLore);
		lore.addPlaceholder("%time%", StaticCharge.Placeholders.time.getFlag());
		lore.addPlaceholder("%damage%", StaticCharge.Placeholders.damage.getFlag());
		lore.addPlaceholder("%charge%", StaticCharge.Placeholders.charge.getFlag());
		manager.setAbilityLore(lore);

		SkyblockItems.put(manager.itemID, manager);
		ShapeEncoder encoder = new ShapeEncoder("s s", "s s", " s ");
		encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("THUNDER_SHARDS"), 1));
		SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("THUNDERBOLT_NECKLACE", manager, 1);
		shapedRecipe.setRecipe(encoder.encode());
		SkyblockRecipe.recipes.put(shapedRecipe.getId(),shapedRecipe);

	}
	private static void thunderHelmet(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Gains §c1 §7static charge every");
		abilityLore.add("§e%time% §7seconds.");
		abilityLore.add("§7Hitting a target will cause a");
		abilityLore.add("§7discharge, adding §a%damage% §7damage");
		abilityLore.add("§7to that hit for each charge");
		abilityLore.add("§7collected");
		abilityLore.add(" ");
		abilityLore.add("§7Maximum Charge Capacity: §c%charge%");
		ItemManager manager = new ItemManager("Thunder Helmet", "THUNDER_HELMET" , ItemType.Helmet, null,"Static Charge","",abilityLore,0,0,
				0,0,ItemRarity.EPIC, "https://textures.minecraft.net/texture/4a93d2bf999a7fe052bf6795e0a0bc7555cc1e014c667fa86dc421cb186d5b67");


		manager.setUnstackeble(true);
		manager.setStat(Stats.Health, 200);
		manager.setStat(Stats.Defense, 170);
		manager.setStat(Stats.Strength, 25);
		manager.setStat(Stats.SeaCreatureChance, 4);
		manager.setFullSetBonus(Bonuses.StaticCharge, true);
		manager.setNpcSellPrice(125000);
		AbilityLore lore = new AbilityLore(abilityLore);
		lore.addPlaceholder("%time%", StaticCharge.Placeholders.time.getFlag());
		lore.addPlaceholder("%damage%", StaticCharge.Placeholders.damage.getFlag());
		lore.addPlaceholder("%charge%", StaticCharge.Placeholders.charge.getFlag());
		manager.setAbilityLore(lore);
		manager.addSlot(new GemstoneSlot(SlotType.Combat));

		SkyblockItems.put(manager.itemID, manager);
		ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "   ");
		encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("THUNDER_SHARDS"), 1));
		SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("THUNDERBOLT_HELMET", manager, 1);
		shapedRecipe.setRecipe(encoder.encode());
		SkyblockRecipe.recipes.put(shapedRecipe.getId(),shapedRecipe);

	}
	private static void thunderChestplate(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Gains §c1 §7static charge every");
		abilityLore.add("§e%time% §7seconds.");
		abilityLore.add("§7Hitting a target will cause a");
		abilityLore.add("§7discharge, adding §a%damage% §7damage");
		abilityLore.add("§7to that hit for each charge");
		abilityLore.add("§7collected");
		abilityLore.add(" ");
		abilityLore.add("§7Maximum Charge Capacity: §c%charge%");
		ItemManager manager = new ItemManager("Thunder Chestplate", "THUNDER_CHESTPLATE" , ItemType.Chestplate, null,"Static Charge","",abilityLore,0,0,
				0,0,Material.LEATHER_CHESTPLATE,Color.fromRGB(0x24DDE5),ItemRarity.EPIC );


		manager.setStat(Stats.Health, 230);
		manager.setStat(Stats.Defense, 230);
		manager.setStat(Stats.Strength, 25);
		manager.setStat(Stats.SeaCreatureChance, 4);
		manager.setFullSetBonus(Bonuses.StaticCharge, true);
		manager.setNpcSellPrice(200000);
		AbilityLore lore = new AbilityLore(abilityLore);
		lore.addPlaceholder("%time%", StaticCharge.Placeholders.time.getFlag());
		lore.addPlaceholder("%damage%", StaticCharge.Placeholders.damage.getFlag());
		lore.addPlaceholder("%charge%", StaticCharge.Placeholders.charge.getFlag());
		manager.setAbilityLore(lore);
		manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);
		ShapeEncoder encoder = new ShapeEncoder("s s", "sss", "sss");
		encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("THUNDER_SHARDS"), 1));
		SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("THUNDERBOLT_CHESTPLATE", manager, 1);
		shapedRecipe.setRecipe(encoder.encode());
		SkyblockRecipe.recipes.put(shapedRecipe.getId(),shapedRecipe);

	}
	private static void thunderLeggings(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Gains §c1 §7static charge every");
		abilityLore.add("§e%time% §7seconds.");
		abilityLore.add("§7Hitting a target will cause a");
		abilityLore.add("§7discharge, adding §a%damage% §7damage");
		abilityLore.add("§7to that hit for each charge");
		abilityLore.add("§7collected");
		abilityLore.add(" ");
		abilityLore.add("§7Maximum Charge Capacity: §c%charge%");
		ItemManager manager = new ItemManager("Thunder Leggings", "THUNDER_LEGGINGS" , ItemType.Leggings, null,"Static Charge","",abilityLore,0,0,
				0,0,Material.LEATHER_LEGGINGS,Color.fromRGB(0x24DDE5),ItemRarity.EPIC );


		manager.setStat(Stats.Health, 220);
		manager.setStat(Stats.Defense, 200);
		manager.setStat(Stats.Strength, 25);
		manager.setStat(Stats.SeaCreatureChance, 4);
		manager.setFullSetBonus(Bonuses.StaticCharge, true);
		manager.setNpcSellPrice(175000);
		AbilityLore lore = new AbilityLore(abilityLore);
		lore.addPlaceholder("%time%", StaticCharge.Placeholders.time.getFlag());
		lore.addPlaceholder("%damage%", StaticCharge.Placeholders.damage.getFlag());
		lore.addPlaceholder("%charge%", StaticCharge.Placeholders.charge.getFlag());
		manager.setAbilityLore(lore);
		manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);
		ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "s s");
		encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("THUNDER_SHARDS"), 1));
		SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("THUNDERBOLT_LEGGINGS", manager, 1);
		shapedRecipe.setRecipe(encoder.encode());
		SkyblockRecipe.recipes.put(shapedRecipe.getId(),shapedRecipe);
	}
	private static void thunderBoots(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Gains §c1 §7static charge every");
		abilityLore.add("§e%time% §7seconds.");
		abilityLore.add("§7Hitting a target will cause a");
		abilityLore.add("§7discharge, adding §a%damage% §7damage");
		abilityLore.add("§7to that hit for each charge");
		abilityLore.add("§7collected");
		abilityLore.add(" ");
		abilityLore.add("§7Maximum Charge Capacity: §c%charge%");
		ItemManager manager = new ItemManager("Thunder Boots", "THUNDER_BOOTS" , ItemType.Boots, null,"Static Charge","",abilityLore,0,0,
				0,0,Material.LEATHER_BOOTS,Color.fromRGB(0x24DDE5),ItemRarity.EPIC );


		manager.setStat(Stats.Health, 170);
		manager.setStat(Stats.Defense, 150);
		manager.setStat(Stats.Strength, 25);
		manager.setStat(Stats.SeaCreatureChance, 4);
		manager.setFullSetBonus(Bonuses.StaticCharge, true);
		manager.setNpcSellPrice(100000);
		AbilityLore lore = new AbilityLore(abilityLore);
		lore.addPlaceholder("%time%", StaticCharge.Placeholders.time.getFlag());
		lore.addPlaceholder("%damage%", StaticCharge.Placeholders.damage.getFlag());
		lore.addPlaceholder("%charge%", StaticCharge.Placeholders.charge.getFlag());
		manager.setAbilityLore(lore);
		manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);
		ShapeEncoder encoder = new ShapeEncoder("s s", "s s", "   ");
		encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("THUNDER_SHARDS"), 1));
		SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("THUNDERBOLT_BOOTS", manager, 1);
		shapedRecipe.setRecipe(encoder.encode());
		SkyblockRecipe.recipes.put(shapedRecipe.getId(),shapedRecipe);

	}
	private static void lavaShell(){
		ArrayList<String> lore = new ArrayList<>();

		ItemManager manager = new ItemManager("Lava Shell", "LAVA_SHELL" , ItemType.Non, lore,null,null,null,0,0,
				0,0,ItemRarity.EPIC, "https://textures.minecraft.net/texture/1b5b097fab550b8cead470417d253c50707c430a009a90f62e1dfbab43007b0f");
		manager.setNpcSellPrice(3000);
		SkyblockItems.put(manager.itemID, manager);

	}

	private static void magmaFish(){
		ArrayList<String> lore = new ArrayList<>();

		ItemManager manager = new ItemManager("Magmafish", "MAGMA_FISH" , ItemType.Non, lore,null,null,null,0,0,
				0,0,ItemRarity.RARE, "https://textures.minecraft.net/texture/f56b5955b295522c9689481960c01a992ca1c7754cf4ee313c8dd0c356d335f",	 UUID.fromString("bb822952-35b8-44c8-93ca-c0a5f4fff372"));

		manager.setNpcSellPrice(20);
		SkyblockItems.put(manager.itemID, manager);

	}
	private static void silverMagmaFish(){
		ArrayList<String> lore = new ArrayList<>();

		ItemManager manager = new ItemManager("Silver Magmafish", "MAGMA_FISH_SILVER" , ItemType.Non, lore,null,null,null,0,0,
				0,0,ItemRarity.EPIC, "https://textures.minecraft.net/texture/353e10c0712ff28d877b9cef990d613b21d843732663f4aac385dc7db97ac54a",
				UUID.fromString("bb822952-35b8-44c8-93ca-c0a5f4fff372"));

		manager.setNpcSellPrice(1600);
		SkyblockItems.put(manager.itemID, manager);

	}
	private static void goldMagmaFish(){
		ArrayList<String> lore = new ArrayList<>();

		ItemManager manager = new ItemManager("Gold Magmafish", "MAGMA_FISH_GOLD" , ItemType.Non, lore,null,null,null,0,0,
				0,0,ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/5324eda6af85e50f35180607341728ff90faea1465be57a6242c6dea63aa3f28",
				UUID.fromString("bb822952-35b8-44c8-93ca-c0a5f4fff372"));

		manager.setNpcSellPrice(128000);
		SkyblockItems.put(manager.itemID, manager);

	}
	private static void diamondMagmaFish(){
		ArrayList<String> lore = new ArrayList<>();

		ItemManager manager = new ItemManager("Diamond Magmafish", "MAGMA_FISH_DIAMOND" , ItemType.Non, lore,null,null,null,0,0,
				0,0,ItemRarity.MYTHIC, "https://textures.minecraft.net/texture/19b393eb6a5bd65d735aaa3b3cfa993b50f5e536d7a13b535514bd0740d63350"
				, UUID.fromString("bb822952-35b8-44c8-93ca-c0a5f4fff372"));

		SkyblockItems.put(manager.itemID, manager);

	}
	private static void magmaLordFragment(){
		ArrayList<String> lore = new ArrayList<>();

		ItemManager manager = new ItemManager("Magma Lord Fragment", "MAGMA_LORD_FRAGMENT" , ItemType.Non, lore,null,null,null,0,0,
				0,0,ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/82ccf1a1a6f4b18054bf4ec66bcd379f1215b06f37598ba79e0de2092823fb5");

		manager.setUnstackeble(true);
		SkyblockItems.put(manager.itemID, manager);

	}
	
	
	private static void magmaLordHelmet(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Provides immunity to §6nether");
		abilityLore.add("§6magma§7.");
		abilityLore.add(" ");
		abilityLore.add("%prefix%Tiered Bonus: Lord's Blessing(%amount%/5)");
		abilityLore.add("§7Grants §3%scc%α Sea Creature Chance");
		abilityLore.add("§3Chance§7.");
		abilityLore.add("§7Grants §b+%mf%✯ Magic Find§7 on");
		abilityLore.add("§7Lava Sea Creatures.");
		ItemManager manager = new ItemManager("Magma Lord Helmet", "MAGMA_LORD_HELMET" , ItemType.Helmet, null,"Fireproof","",abilityLore,0,0,
				0,0,ItemRarity.LEGENDARY, "ewogICJ0aW1lc3RhbXAiIDogMTY0MzYwNTU1MjE0NywKICAicHJvZmlsZUlkIiA6ICJjNTZlMjI0MmNiZWY0MWE2ODdlMzI2MGRjMGNmOTM2MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJMSlI3MzEwMCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83N2Q5MWJhYzQ3NTVkYmM5NjhhZGQ3NWU2ZWMzOTc5YTc0ZGEzMGJjYjRkMjU1NmUzNWE0NjM1NzE4YWU3MzkwIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=");


		manager.setIsSkullValue(true);
		manager.setUnstackeble(true);
		manager.setStat(Stats.Health, 220);
		manager.setStat(Stats.Defense, 190);
		manager.setStat(Stats.Strength, 10);
		manager.setStat(Stats.CritDamage, 35);
		manager.setStat(Stats.SeaCreatureChance, 4.5);
		manager.setFullSetBonus(Bonuses.MagmaLordArmor, true);
		manager.setNpcSellPrice(250000);
		AbilityLore lore = new AbilityLore(abilityLore);
		lore.addPlaceholder("%prefix%", MagmaLordArmor.Placeholders.prefix.getFlag());
		lore.addPlaceholder("%amount%", MagmaLordArmor.Placeholders.amount.getFlag());
		lore.addPlaceholder("%scc%", MagmaLordArmor.Placeholders.scc.getFlag());
		lore.addPlaceholder("%mf%", MagmaLordArmor.Placeholders.mf.getFlag());
		manager.setAbilityLore(lore);
		manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);
		ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "   ");
		encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("MAGMA_LORD_FRAGMENT"), 1));
		SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("MAGMA_LORD_HELMET", manager, 1);
		shapedRecipe.setRecipe(encoder.encode());
		SkyblockRecipe.recipes.put(shapedRecipe.getId(),shapedRecipe);

	}
	private static void magmaLordChestplate(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Provides immunity to §6nether");
		abilityLore.add("§6magma§7.");
		abilityLore.add(" ");
		abilityLore.add("%prefix%Tiered Bonus: Lord's Blessing(%amount%/5)");
		abilityLore.add("§7Grants §3%scc%α Sea Creature Chance");
		abilityLore.add("§3Chance§7.");
		abilityLore.add("§7Grants §b+%mf%✯ Magic Find§7 on");
		abilityLore.add("§7Lava Sea Creatures.");
		ItemManager manager = new ItemManager("Magma Lord Chestplate", "MAGMA_LORD_CHESTPLATE" , ItemType.Chestplate, null,"Fireproof","",abilityLore,0,0,
				0,0,Material.LEATHER_CHESTPLATE, Color.fromRGB(0x6F0F08),ItemRarity.LEGENDARY);


		manager.setIsSkullValue(true);
		manager.setStat(Stats.Health, 250);
		manager.setStat(Stats.Defense, 240);
		manager.setStat(Stats.Strength, 10);
		manager.setStat(Stats.CritDamage, 35);
		manager.setStat(Stats.SeaCreatureChance, 4.5);
		manager.setFullSetBonus(Bonuses.MagmaLordArmor, true);
		manager.setNpcSellPrice(350000);
		AbilityLore lore = new AbilityLore(abilityLore);
		lore.addPlaceholder("%prefix%", MagmaLordArmor.Placeholders.prefix.getFlag());
		lore.addPlaceholder("%amount%", MagmaLordArmor.Placeholders.amount.getFlag());
		lore.addPlaceholder("%scc%", MagmaLordArmor.Placeholders.scc.getFlag());
		lore.addPlaceholder("%mf%", MagmaLordArmor.Placeholders.mf.getFlag());
		manager.setAbilityLore(lore);
		manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);
		ShapeEncoder encoder = new ShapeEncoder("s s", "sss", "sss");
		encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("MAGMA_LORD_FRAGMENT"), 1));
		SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("MAGMA_LORD_CHESTPLATE", manager, 1);
		shapedRecipe.setRecipe(encoder.encode());
		SkyblockRecipe.recipes.put(shapedRecipe.getId(),shapedRecipe);

	}
	private static void magmaLordLeggings(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Provides immunity to §6nether");
		abilityLore.add("§6magma§7.");
		abilityLore.add(" ");
		abilityLore.add("%prefix%Tiered Bonus: Lord's Blessing(%amount%/5)");
		abilityLore.add("§7Grants §3%scc%α Sea Creature Chance");
		abilityLore.add("§3Chance§7.");
		abilityLore.add("§7Grants §b+%mf%✯ Magic Find§7 on");
		abilityLore.add("§7Lava Sea Creatures.");
		ItemManager manager = new ItemManager("Magma Lord Leggings", "MAGMA_LORD_LEGGINGS" , ItemType.Leggings, null,"Fireproof","",abilityLore,0,0,
				0,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0x6F0F08),ItemRarity.LEGENDARY);

		manager.setIsSkullValue(true);
		manager.setStat(Stats.Health, 235);
		manager.setStat(Stats.Defense, 210);
		manager.setStat(Stats.Strength, 10);
		manager.setStat(Stats.CritDamage, 35);
		manager.setStat(Stats.SeaCreatureChance, 4.5);
		manager.setFullSetBonus(Bonuses.MagmaLordArmor, true);
		manager.setNpcSellPrice(350000);
		AbilityLore lore = new AbilityLore(abilityLore);
		lore.addPlaceholder("%prefix%", MagmaLordArmor.Placeholders.prefix.getFlag());
		lore.addPlaceholder("%amount%", MagmaLordArmor.Placeholders.amount.getFlag());
		lore.addPlaceholder("%scc%", MagmaLordArmor.Placeholders.scc.getFlag());
		lore.addPlaceholder("%mf%", MagmaLordArmor.Placeholders.mf.getFlag());
		manager.setAbilityLore(lore);
		manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);
		ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "s s");
		encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("MAGMA_LORD_FRAGMENT"), 1));
		SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("MAGMA_LORD_LEGGINGS", manager, 1);
		shapedRecipe.setRecipe(encoder.encode());
		SkyblockRecipe.recipes.put(shapedRecipe.getId(),shapedRecipe);

	}
	private static void magmaLordBoots(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Provides immunity to §6nether");
		abilityLore.add("§6magma§7.");
		abilityLore.add(" ");
		abilityLore.add("%prefix%Tiered Bonus: Lord's Blessing (%amount%/5)");
		abilityLore.add("§7Grants §3%scc%α Sea Creature Chance");
		abilityLore.add("§3Chance§7.");
		abilityLore.add("§7Grants §b+%mf%✯ Magic Find§7 on");
		abilityLore.add("§7Lava Sea Creatures.");
		ItemManager manager = new ItemManager("Magma Lord Boots", "MAGMA_LORD_BOOTS" , ItemType.Boots, null,"Fireproof","",abilityLore,0,0,
				0,0,Material.LEATHER_BOOTS, Color.fromRGB(0x6F0F08),ItemRarity.LEGENDARY);
		manager.setIsSkullValue(true);
		manager.setStat(Stats.Health, 190);
		manager.setStat(Stats.Defense, 165);
		manager.setStat(Stats.Strength, 10);
		manager.setStat(Stats.CritDamage, 35);
		manager.setStat(Stats.SeaCreatureChance, 4.5);
		manager.setFullSetBonus(Bonuses.MagmaLordArmor, true);
		manager.setNpcSellPrice(250000);
		AbilityLore lore = new AbilityLore(abilityLore);
		lore.addPlaceholder("%prefix%", MagmaLordArmor.Placeholders.prefix.getFlag());
		lore.addPlaceholder("%amount%", MagmaLordArmor.Placeholders.amount.getFlag());
		lore.addPlaceholder("%scc%", MagmaLordArmor.Placeholders.scc.getFlag());
		lore.addPlaceholder("%mf%", MagmaLordArmor.Placeholders.mf.getFlag());
		manager.setAbilityLore(lore);
		manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);
		ShapeEncoder encoder = new ShapeEncoder("s s", "s s", "   ");
		encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("MAGMA_LORD_FRAGMENT"), 1));
		SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("MAGMA_LORD_BOOTS", manager, 1);
		shapedRecipe.setRecipe(encoder.encode());
		SkyblockRecipe.recipes.put(shapedRecipe.getId(),shapedRecipe);

	}
	private static void magmaLordGauntlet(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Provides immunity to §6nether");
		abilityLore.add("§6magma§7.");
		abilityLore.add(" ");
		abilityLore.add("%prefix%Tiered Bonus: Lord's Blessing (%amount%/5)");
		abilityLore.add("§7Grants §3%scc%α Sea Creature Chance");
		abilityLore.add("§3Chance§7.");
		abilityLore.add("§7Grants §b+%mf%✯ Magic Find§7 on");
		abilityLore.add("§7Lava Sea Creatures.");
		ItemManager manager = new ItemManager("Magma Lord Gauntlet", "MAGMA_LORD_GAUNTLET" , ItemType.Gauntlet, null,"Fireproof","",abilityLore,0,0,
				0,0,ItemRarity.EPIC, "https://textures.minecraft.net/texture/168a2bfe6e56ddea103de8cec000efa83ebb9a959c68d82402543c01d4137d26");
		manager.setUnstackeble(true);
		manager.setStat(Stats.Health, 35);
		manager.setStat(Stats.Strength, 10);
		manager.setStat(Stats.CritDamage, 30);
		manager.setStat(Stats.SeaCreatureChance, 1.5);
		manager.setStat(Stats.Ferocity, 2);
		manager.setFullSetBonus(Bonuses.MagmaLordArmor, true);
		manager.setNpcSellPrice(93750);
		AbilityLore lore = new AbilityLore(abilityLore);
		lore.addPlaceholder("%prefix%", MagmaLordArmor.Placeholders.prefix.getFlag());
		lore.addPlaceholder("%amount%", MagmaLordArmor.Placeholders.amount.getFlag());
		lore.addPlaceholder("%scc%", MagmaLordArmor.Placeholders.scc.getFlag());
		lore.addPlaceholder("%mf%", MagmaLordArmor.Placeholders.mf.getFlag());
		manager.setAbilityLore(lore);
		manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);
		ShapeEncoder encoder = new ShapeEncoder("sss", "sss", "   ");
		encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("MAGMA_LORD_FRAGMENT"), 1));
		SkyblockShapedRecipe shapedRecipe = new SkyblockShapedRecipe("MAGMA_LORD_BOOTS", manager, 1);
		shapedRecipe.setRecipe(encoder.encode());
		SkyblockRecipe.recipes.put(shapedRecipe.getId(),shapedRecipe);

	}


	private static void dreadlordSword(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Shoots a skull deals §a%damage%");
		abilityLore.add("§7damage");
		ItemManager manager = new ItemManager("Dreadlord Sword", "CRYPT_DREADLORD_SWORD" , ItemType.Sword, null,"Dreadlord","",null,40,0,0,0,Material.IRON_SWORD,ItemRarity.RARE);
		manager.setDamage(52);
		manager.setStat(Stats.Strength, 23);
		manager.setStat(Stats.Inteligence, 85);
		manager.setDungeonItem(true);
		manager.setAbility(new DreadlordAbility(), AbilityType.RightClick);
		manager.setNpcSellPrice(200);
		manager.setAbilityLore(new AbilityLore(abilityLore, "%damage%", new Bundle<>(500d, 0.3)));


		SkyblockItems.put(manager.itemID, manager);

	}

	private static void totemOfCorruption(){
		ArrayList<String> abilityLore = new ArrayList<>();

		ItemManager manager = new ItemManager("Totem Of Corruption", "TOTEM_OF_CORRUPTION" , ItemType.Sword, null,"Deploy","",abilityLore,200,0,0,0,Material.WHITE_BANNER,ItemRarity.UNCOMMON);
		manager.setAbility(new CorruptedTotem(), AbilityType.RightClick);
		manager.setNpcSellPrice(96000);
		manager.addBannerPattern(new Pattern(DyeColor.BLACK, PatternType.BASE));
		manager.addBannerPattern(new Pattern(DyeColor.MAGENTA, PatternType.CROSS));
		manager.addBannerPattern(new Pattern(DyeColor.PURPLE, PatternType.CURLY_BORDER));
		manager.addBannerPattern(new Pattern(DyeColor.PURPLE, PatternType.CIRCLE_MIDDLE));
		manager.addBannerPattern(new Pattern(DyeColor.BLACK, PatternType.FLOWER));
		manager.addBannerPattern(new Pattern(DyeColor.BLACK, PatternType.TRIANGLES_BOTTOM));
		manager.addBannerPattern(new Pattern(DyeColor.BLACK, PatternType.TRIANGLES_TOP));

		SkyblockItems.put(manager.itemID, manager);

	}

	private static void iceSprayWand(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Produces a cone of ice in front");
		abilityLore.add("§7of the caster that deals");
		abilityLore.add("§c%damage% §7damage to mobs and");
		abilityLore.add("§7freezes them in place for §e5");
		abilityLore.add("§7seconds! Frozen mobs take");
		abilityLore.add("§c10% §7increased damage!");
		ItemManager manager = new ItemManager("Ice Spray Wand", "ICE_SPRAY_WAND" , ItemType.Sword, null,"Ice Spray","",null,50,5,0,0,Material.STICK,ItemRarity.RARE);
		manager.setDamage(93);
		manager.setStat(Stats.Inteligence, 230);
		manager.setDungeonItem(true);
		manager.setAbility(new IceSprayWand(), AbilityType.RightClick);
		manager.setNpcSellPrice(10000);
		manager.setUnstackeble(true);
		manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
		manager.setAbilityLore(new AbilityLore(abilityLore, "%damage%", new Bundle<>(17000d, 0.1)));
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);


		SkyblockItems.put(manager.itemID, manager);

	}

	private static void spititScepter(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Shoots a guided spirit bat,");
		abilityLore.add("§7following your aim and exploding");
		abilityLore.add("§7for §c%damage% §7damage.");
		ItemManager manager = new ItemManager("Spirit Sceptre", "BAT_WAND" , ItemType.Sword, null,"Guided Bat","",null,200,0,0,0,Material.ALLIUM, ItemRarity.LEGENDARY);
		manager.setDamage(180);
		manager.setStat(Stats.Inteligence, 300);
		manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
		manager.setDungeonItem(true);
		manager.setAbility(new SpiritScepter(), AbilityType.RightClick);
		manager.setAbilityLore(new AbilityLore(abilityLore, "%damage%", new Bundle<>(2000d, 0.2)));


		SkyblockItems.put(manager.itemID, manager);

	}
	private static void flowerOfTruth(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Shoots a rose that ricochets");
		abilityLore.add("§7between enemies, damaging up to");
		abilityLore.add("§a3 §7of your foes! Damage");
		abilityLore.add("§7multiplies as more enemies are");
		abilityLore.add("§7hit.");
		ItemManager manager = new ItemManager("Flower of Truth", "FLOWER_OF_TRUTH" , ItemType.Sword, null,"Heat-Seeking Rose","",abilityLore,0.1,0,0,0,Material.POPPY, ItemRarity.LEGENDARY);
		manager.abilityMana1AsPers = true;
		manager.setDamage(160);
		manager.setStat(Stats.Strength, 300);
		manager.addSlot(new GemstoneSlot(SlotType.Jasper));
		manager.setDungeonItem(true);
		manager.setAbility(new FlowerOfTruth(), AbilityType.RightClick);
		manager.setNpcSellPrice(100000);


		SkyblockItems.put(manager.itemID, manager);

	}
	private static void aspectOfTheEnd(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Teleport §a8 blocks §7ahead of");
		abilityLore.add("§7you and gain §a+50 §f✦ Speed");
		abilityLore.add("§7for §a3 seconds§f.");
		ItemManager manager = new ItemManager("Aspect Of The End", "ASPECT_OF_THE_END" , ItemType.Sword, null,"Transmission","",
				abilityLore,50,0,0,0,Material.DIAMOND_SWORD, ItemRarity.RARE);
		manager.setDamage(100);
		manager.setStat(Stats.Strength, 100);
		manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
		manager.setDungeonItem(true);
		manager.setAbility(new AspectOfTheEnd(), AbilityType.RightClick);
		manager.setNpcSellPrice(56000);


		SkyblockItems.put(manager.itemID, manager);

	}
	private static void aspectOfTheVoid(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Teleport §a8 blocks §7ahead of");
		abilityLore.add("§7you and gain §a+50 §f✦ Speed");
		abilityLore.add("§7for §a3 seconds§f.");
		ItemManager manager = new ItemManager("Aspect Of The Void", "ASPECT_OF_THE_VOID" , ItemType.Sword, null,"Transmission","",
				abilityLore,45,0,0,0,Material.DIAMOND_SHOVEL, ItemRarity.EPIC);
		manager.setDamage(120);
		manager.setStat(Stats.Strength, 100);
		manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
		manager.setDungeonItem(true);
		manager.setAbility(new AspectOfTheEnd(), AbilityType.RightClick);
		manager.setNpcSellPrice(56000);


		SkyblockItems.put(manager.itemID, manager);

	}
	private static void aspectOfTheVoid_upgraded(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Teleport §a8 blocks §7ahead of");
		abilityLore.add("§7you and gain §a+50 §f✦ Speed");
		abilityLore.add("§7for §a3 seconds§f.");
		ItemManager manager = new ItemManager("Aspect Of The Void", "ASPECT_OF_THE_VOID_1" , ItemType.Sword, null,"Transmission","",
				abilityLore,50,0,0,0,Material.DIAMOND_SHOVEL, ItemRarity.EPIC);
		manager.setDamage(120);
		manager.setStat(Stats.Strength, 100);
		manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
		manager.setDungeonItem(true);
		manager.setAbility(new AspectOfTheEnd(), AbilityType.RightClick);
		abilityLore = new ArrayList<>();
		abilityLore.add("§7Teleport to your targetted block");
		abilityLore.add("§7up to §a57 blocks §7away.");
		manager.set2Ability("Ether Transmission", new AspectOfTheVoid(), AbilityType.SneakRightClick, abilityLore, 180, 0);
		manager.setNpcSellPrice(56000);


		SkyblockItems.put(manager.itemID, manager);

	}
	private static void stockOfStonks(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7This is a test item for the Bazzar.");
		lore.add("§7Available from Warren for a limited time.");
		lore.add(" ");
		lore.add("§7Doesen't do anything for now.");
		lore.add(" ");
		lore.add("§c§lWARNING: RISKY INVESTMENT");
		lore.add("§cThis is a TEST item, may get deleted!");
			ItemManager manager = new ItemManager("Stock of Stonks", "STOCK_OF_STONKS" , ItemType.Non, lore,null,null,
				null,0,0,0,0,Material.PAPER, ItemRarity.EPIC);



		SkyblockItems.put(manager.itemID, manager);

	}

	public static ItemManager uraniumFuel() {
		//https://textures.minecraft.net/texture/a84ddca766725b8b97413f259c3f7668070f6ae55483a90c8e5525394f9c099
		ItemManager manager = new ItemManager("Uranium Fuel", "URANIUM_FUEL" , ItemType.Non, null,null,null,null,0,0,0,0,ItemRarity.RARE, "https://textures.minecraft.net/texture/a84ddca766725b8b97413f259c3f7668070f6ae55483a90c8e5525394f9c099");

		manager.setUnstackeble(true);


		SkyblockItems.put(manager.itemID, manager);
		return manager;

	}
	public static ItemManager unstackableBlazeRod() {

		ItemManager manager = new ItemManager("YOU WILL NOT STACK!", "UNSTACKABLE_DEBUG" , ItemType.Non, null,null,null,null,0,0,0,0,Material.BLAZE_ROD,ItemRarity.SPECIAL);

		manager.setUnstackeble(true);


		SkyblockItems.put(manager.itemID, manager);
		return manager;

	}

	public static ItemManager diamantesHandle() {

		ItemManager manager = new ItemManager("Diamante's Handle", "GIANT_FRAGMENT_DIAMOND" , ItemType.Non, null,null,null,null,0,0,0,0,Material.DIAMOND_HORSE_ARMOR,ItemRarity.EPIC);
		manager.setDungeonItem(true);


		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
	public static ItemManager jollyPinkRock() {

		ItemManager manager = new ItemManager("Jolly Pink Rock", "GIANT_FRAGMENT_BOULDER" , ItemType.Non, null,null,null,null,0,0,0,0,Material.FIREWORK_STAR,ItemRarity.EPIC);
		manager.setDungeonItem(true);


		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
	public static ItemManager bigfootsLasso() {

		ItemManager manager = new ItemManager("Bigfoot's Lasso", "GIANT_FRAGMENT_BIGFOOT" , ItemType.Non, null,null,null,null,0,0,0,0,Material.LEAD,ItemRarity.EPIC);
		manager.setDungeonItem(true);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);

		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
	public static ItemManager laser_eye() {

		ItemManager manager = new ItemManager("L.A.S.E.R.'s Eye", "GIANT_FRAGMENT_LASER" , ItemType.Non, null,null,null,null,0,0,0,0,Material.ENDER_EYE,ItemRarity.EPIC);
		manager.setDungeonItem(true);


		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
public static void necrons_handle() {

	ItemManager manager = new ItemManager("Necron's Handle", "NECRON_HANDLE" , ItemType.Non, null,null,null,null,0,0,0,0,Material.STICK,ItemRarity.EPIC);
	manager.setDungeonItem(true);
	manager.customDataContainer.put("uuid", UUID.randomUUID().toString());
	manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
	SkyblockItems.put(manager.itemID, manager);

	}
	public static void necrons_blade() {
		ArrayList<String> lore = new ArrayList<>();

		lore.add("§7Teleports §a10 blocks §7ahead of");
		lore.add("§7you. Then implode dealing");
		lore.add("§7§c10000 §7damage to nearby");
		lore.add("§7enemies. Also applies the wither");
		lore.add("§7shield scroll ability reducing");
		lore.add("§7damage taken and granting an");
		lore.add("§7absorption shield for §e5 §7seconds.");
		ItemManager manager = new ItemManager("Necron's Blade (Unrefined)", "NECRON_BLADE" , ItemType.Sword,null, "Wither Impact", "lool",lore,300,0,0.3f, 10000,Material.IRON_SWORD,ItemRarity.LEGENDARY );
		manager.setDungeonItem(true);
		manager.setDamage(260);
		manager.setStat(Stats.Strength,110);
		manager.setStat(Stats.Inteligence,50);
		manager.setStat(Stats.Ferocity,30);
		manager.setAbility(new Hyperion_WitherImpact(),AbilityType.RightClick);
		SkyblockItems.put(manager.itemID, manager);


	}
	public static void valkyrie() {
		ArrayList<String> lore = new ArrayList<>();

		lore.add("§7Teleports §a10 blocks §7ahead of");
		lore.add("§7you. Then implode dealing");
		lore.add("§7§c10000 §7damage to nearby");
		lore.add("§7enemies. Also applies the wither");
		lore.add("§7shield scroll ability reducing");
		lore.add("§7damage taken and granting an");
		lore.add("§7absorption shield for §e5 §7seconds.");
		ItemManager manager = new ItemManager("Valkyrie", "VALKYRIE" , ItemType.Sword,null, "Wither Impact", "lool",lore,300,0,0.3f, 10000,Material.IRON_SWORD,ItemRarity.LEGENDARY );
		manager.setDungeonItem(true);
		manager.setDamage(270);
		manager.setStat(Stats.Strength,145);
		manager.setStat(Stats.Inteligence,60);
		manager.setStat(Stats.Ferocity,60);
		manager.setAbility(new Hyperion_WitherImpact(),AbilityType.RightClick);
		manager.addSlot(new GemstoneSlot(SlotType.Jasper));
		manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);


	}
	public static void astrea() {
		ArrayList<String> lore = new ArrayList<>();

		lore.add("§7Teleports §a10 blocks §7ahead of");
		lore.add("§7you. Then implode dealing");
		lore.add("§7§c10000 §7damage to nearby");
		lore.add("§7enemies. Also applies the wither");
		lore.add("§7shield scroll ability reducing");
		lore.add("§7damage taken and granting an");
		lore.add("§7absorption shield for §e5 §7seconds.");
		ItemManager manager = new ItemManager("Astraea", "ASTRAEA" , ItemType.Sword,null, "Wither Impact", "lool",lore,300,0,0.3f, 10000,Material.IRON_SWORD,ItemRarity.LEGENDARY );
		manager.setDungeonItem(true);
		manager.setDamage(270);
		manager.setStat(Stats.Strength,150);
		manager.setStat(Stats.Inteligence,50);
		manager.setStat(Stats.Ferocity,30);
		manager.setStat(Stats.Defense,250);
		manager.setStat(Stats.TrueDefense,20);
		manager.setAbility(new Hyperion_WitherImpact(),AbilityType.RightClick);
		manager.addSlot(new GemstoneSlot(SlotType.Defensive));
		manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);


	}
	public static void scylla() {
		ArrayList<String> lore = new ArrayList<>();

		lore.add("§7Teleports §a10 blocks §7ahead of");
		lore.add("§7you. Then implode dealing");
		lore.add("§7§c10000 §7damage to nearby");
		lore.add("§7enemies. Also applies the wither");
		lore.add("§7shield scroll ability reducing");
		lore.add("§7damage taken and granting an");
		lore.add("§7absorption shield for §e5 §7seconds.");
		ItemManager manager = new ItemManager("Scylla", "SCYLLA" , ItemType.Sword,null, "Wither Impact", "lool",lore,300,0,0.3f, 10000,Material.IRON_SWORD,ItemRarity.LEGENDARY );
		manager.setDungeonItem(true);
		manager.setDamage(270);
		manager.setStat(Stats.Strength,150);
		manager.setStat(Stats.Inteligence,50);
		manager.setStat(Stats.Ferocity,30);
		manager.setStat(Stats.CritChance,12);
		manager.setStat(Stats.CritDamage,35);
		manager.setAbility(new Hyperion_WitherImpact(),AbilityType.RightClick);
		manager.addSlot(new GemstoneSlot(SlotType.Combat));
		manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);


	}
	public static void wardenHelmet() {
		ArrayList<String> lore = new ArrayList<>();

		lore.add("§7Halves your speed but grants");
		lore.add("§c+20% §7base weapon damage for");
		lore.add("§7every §a25 §7speed");
		ItemManager manager = new ItemManager("Warden Helmet", "WARDEN_HELMET" , ItemType.Helmet,null, "Brute Force", null,lore,300,0,0.3f, 10000,ItemRarity.LEGENDARY,"https://textures.minecraft.net/texture/aeb56444326b5f461449039752f26227a24b0679ac0778ac690a56f96a0c21ca" );
		manager.setStat(Stats.Health,300);
		manager.setStat(Stats.Defense,100);
		manager.setFullSetBonus(Bonuses.BruteForce);
		SkyblockItems.put(manager.itemID, manager);


	}
	public static void witherCatalyst() {
		ItemManager manager = new ItemManager("Wither Catalyst","WITHER_CATALYST",ItemType.Non, null,null,null,null,0,0,0,0,Material.WITHER_SKELETON_SKULL, ItemRarity.RARE);
		manager.setDungeonItem(true);
		SkyblockItems.put(manager.itemID, manager);
	}
@SuppressWarnings("deprecation")
public static ItemStack wither_googles() {
	String url = "http://textures.minecraft.net/texture/37ceb8f0758e2d8ac49de6f977603c7bfc23fd82a8574810a45f5e97c6436d79";
	ItemStack skull= new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);

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
    skullMeta.setDisplayName("§5Wither Googles");
    PersistentDataContainer data = skullMeta.getPersistentDataContainer();
    data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.INTEGER, 300);
    data.set(new NamespacedKey(Main.getMain(), "abilitydamage"), PersistentDataType.FLOAT, 45f);
    
    
    
    skull.setItemMeta(skullMeta);
    
    ItemManager manager = new ItemManager("Wither Googles", "wither_googles", ItemType.Helmet, 0, 0, 0, 300, 0, 0, 0, 0, 45f, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, ItemRarity.EPIC, "http://textures.minecraft.net/texture/37ceb8f0758e2d8ac49de6f977603c7bfc23fd82a8574810a45f5e97c6436d79");
    SkyblockItems.put(manager.itemID, manager);
    return manager.getRawItemStack();
    
    
    
}

private static void stormsHelmet(){
		ItemManager manager = new ItemManager("Storm's Helmet","WISE_WITHER_HELMET",ItemType.Helmet, null, "Witherborn" , "wither" , null, 0,0,0,0,ItemRarity.LEGENDARY,"https://textures.minecraft.net/texture/7c33b1e96b8ba078a91f0002d4919c615543cd093c2d709d9aee9f6268134c2c");
		manager.setDungeonItem(true);
		manager.setStat(Stats.Health, 180);
		manager.setStat(Stats.Defense,80);
		manager.setStat(Stats.Inteligence, 400);
	manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
		SkyblockItems.put(manager.itemID, manager);
}

public static ItemStack storm_chestplate() {
	ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
	LeatherArmorMeta meta = (boolean)item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
   PersistentDataContainer data = meta.getPersistentDataContainer();
   
   meta.setDisplayName("§6Storm's Chestplate");
   
   meta.setColor(Color.fromRGB(0x1793C4));
   data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.INTEGER, 250);
   data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.INTEGER, 260);
   data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.INTEGER, 120);
   ItemManager manager = new ItemManager("Storm's Chestplate", "STORM_CHESTPLATE", ItemType.Chestplate, 0, 260, 120, 250, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.LEATHER_CHESTPLATE, Color.fromRGB(0x1793C4), ItemRarity.LEGENDARY);
	
   item.setItemMeta(meta);

	manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setDungeonItem(true);
   SkyblockItems.put(manager.itemID, manager);
   
	return manager.getRawItemStack();
}
public static ItemStack storm_leggings() {
	ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
	LeatherArmorMeta meta = (boolean)item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
   PersistentDataContainer data = meta.getPersistentDataContainer();
   
   meta.setDisplayName("§6Storm's Leggings");
   
   meta.setColor(Color.fromRGB(0x17A8C4));
   data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.INTEGER, 250);
   data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.INTEGER, 230);
   data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.INTEGER, 105);
	
   ItemManager manager = new ItemManager("Storm's Leggings", "STORM_LEGGINGS", ItemType.Leggings, 0, 230, 105, 250, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.LEATHER_LEGGINGS, Color.fromRGB(0x17A8C4), ItemRarity.LEGENDARY);
	manager.setDungeonItem(true);
	manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
   SkyblockItems.put(manager.itemID, manager);
	
   item.setItemMeta(meta);
	
	return manager.getRawItemStack();
}

public static ItemStack storm_boots() {
	ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
	LeatherArmorMeta meta = (boolean)item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
   PersistentDataContainer data = meta.getPersistentDataContainer();
   
   meta.setDisplayName("§6Storm's Chestplate");
   
   meta.setColor(Color.fromRGB(0x1793C4));
   data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.INTEGER, 250);
   data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.INTEGER, 260);
   data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.INTEGER, 120);
   ItemManager manager = new ItemManager("Storm's Boots", "STORM_BOOTS", ItemType.Boots, 0, 145, 65, 250, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.LEATHER_BOOTS, Color.fromRGB(0x1CD4E4), ItemRarity.LEGENDARY);
	manager.setDungeonItem(true);
   item.setItemMeta(meta);
	manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
  
   SkyblockItems.put(manager.itemID, manager);
   
	return manager.getRawItemStack();
}

public static ItemStack live_steal_book() {
	
	ItemManager manager = new ItemManager("Enchanted Book", "LIFE_STEAL", ItemType.EnchantBook, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.ENCHANTED_BOOK, ItemRarity.COMMON);
	manager.addBaseEnchantment(SkyblockEnchants.LIFESTEAL, 1);
	

	SkyblockItems.put("LIFE_STEAL", manager);
	return manager.getRawItemStack();
}
public static ItemStack protection_book() {
	
	ItemManager manager = new ItemManager("Enchanted Book", "PROTECTION", ItemType.EnchantBook, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.ENCHANTED_BOOK, ItemRarity.COMMON);
	manager.addBaseEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	

	SkyblockItems.put("PROTECTION", manager);
	return manager.getRawItemStack();
}
public static ItemStack Revenant_Flesh() {
	ItemStack item = new ItemStack(Material.ROTTEN_FLESH);
	ItemMeta meta =  item.getItemMeta();
	meta.addEnchant(Enchantment.QUICK_CHARGE, 1, false);
	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	meta.setDisplayName("Revenant Flesh");
	
	item.setItemMeta(meta);

	return item;
}
public static ItemStack Foul_Flesh() {
	ItemStack item = new ItemStack(Material.CHARCOAL);
	ItemMeta meta =  item.getItemMeta();
	meta.setDisplayName("Foul Flesh");
	ItemManager manager = new ItemManager("Foul Flesh", "FOUL_FLESH", ItemType.Non, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.CHARCOAL, ItemRarity.RARE);

	SkyblockItems.put(manager.itemID, manager);
	
	item.setItemMeta(meta);

	return manager.getRawItemStack();
}
public static ItemStack Pestilence_Rune() {
	ItemStack item = Tools.CustomHeadTexture("https://textures.minecraft.net/texture/a8c4811395fbf7f620f05cc3175cef1515aaf775ba04a01045027f0693a90147", "a5f18638-c7df-11ec-9d64-0242ac120004");
	ItemManager manager = new ItemManager("Pestilence Rune I", "PESTILENCE_RUNE", ItemType.Rune, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0,  ItemRarity.RARE,"https://textures.minecraft.net/texture/a8c4811395fbf7f620f05cc3175cef1515aaf775ba04a01045027f0693a90147", UUID.fromString("a5f18638-c7df-11ec-9d64-0242ac120004"));

	ItemMeta meta =  item.getItemMeta();
	meta.setDisplayName("Pestilence Rune I");
	
SkyblockItems.put(manager.itemID, manager);
	
	item.setItemMeta(meta);

	return manager.getRawItemStack();
}
public static ItemStack Undead_Catalist() {
	ItemStack item = Tools.CustomHeadTexture("https://textures.minecraft.net/texture/80625369b0a7b052632db6b926a87670219539922836ac5940be26d34bf14e10");
	ItemMeta meta =  item.getItemMeta();
	meta.setDisplayName("Undead Catalist");
	

	
	item.setItemMeta(meta);

	return item;
}
public static ItemStack Smite_6() {
	ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
	ItemMeta meta =  item.getItemMeta();
	meta.setDisplayName("Smite VI - §cComing Soon");
	
	

	
	item.setItemMeta(meta);

	return item;
}
public static ItemStack Beheaded_Horror() {
	ItemStack item = Tools.CustomHeadTexture("https://textures.minecraft.net/texture/dbad99ed3c820b7978190ad08a934a68dfa90d9986825da1c97f6f21f49ad626");
	ItemMeta meta =  item.getItemMeta();
	meta.setDisplayName("Beheaded Horror");
	

	ItemManager manager = new ItemManager("Beheaded Horror", "BEHEADED_HORROR", ItemType.Non, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0,  ItemRarity.RARE,"https://textures.minecraft.net/texture/dbad99ed3c820b7978190ad08a934a68dfa90d9986825da1c97f6f21f49ad626");

	SkyblockItems.put(manager.itemID, manager);
	
	item.setItemMeta(meta);

	return manager.getRawItemStack();
}

public static ItemStack Revenant_Catalist() {
	ItemStack item = Tools.CustomHeadTexture("https://textures.minecraft.net/texture/b88cfafa5f03f8aef042a143799e964342df76b7c1eb461f618e398f84a99a63");
	ItemMeta meta =  item.getItemMeta();
	meta.setDisplayName("Revenant Catalist");
	

	
	item.setItemMeta(meta);

	return item;
}

public static ItemStack Snake_Rune() {
	ItemStack item = Tools.CustomHeadTexture("https://textures.minecraft.net/texture/2c4a65c689b2d36409100a60c2ab8d3d0a67ce94eea3c1f7ac974fd893568b5d" , "a5f18638-c7df-11ec-9d64-0242ac120003");
	ItemMeta meta =  item.getItemMeta();
	meta.setDisplayName("Snake Rune I");
	

	
	item.setItemMeta(meta);

	return item;
}
public static ItemStack Sythe_Blade() {
	ItemStack item = new ItemStack(Material.DIAMOND);
	ItemMeta meta =  item.getItemMeta();
	meta.addEnchant(Enchantment.QUICK_CHARGE, 1, false);
	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	meta.setDisplayName("Sythe Blade");
	

	
	item.setItemMeta(meta);

	return item;
}
public static ItemStack juju_shortbow() {
	ItemStack item = new ItemStack(Material.BOW);
	ItemMeta meta =  item.getItemMeta();
    PersistentDataContainer data = meta.getPersistentDataContainer();
    
	meta.setDisplayName("§6Juju Shortbow");
	

	data.set(NamespacedKey.minecraft("ability"), PersistentDataType.STRING, "shortbow");
	data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, "310");
	data.set(new NamespacedKey(Main.getMain(), "strenght"), PersistentDataType.INTEGER, 50);
	data.set(new NamespacedKey(Main.getMain(), "cc"), PersistentDataType.INTEGER, 10);
	data.set(new NamespacedKey(Main.getMain(), "cd"), PersistentDataType.INTEGER, 110);
	item.setItemMeta(meta);

	ItemManager manager = new ItemManager("Juju Shortbow", "JUJU_SHORTBOW", ItemType.Bow, 310, 0, 0, 0, 0, 50, 10, 110, 0, 0, 0,0,0,0,0,0, null, "Shortbow", "shortbow", null, 0, 0, 0, 0, Material.BOW, ItemRarity.EPIC);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack FakeArrow(String name) {
	ItemStack item = Tools.CustomHeadTexture("https://textures.minecraft.net/texture/75479510422b1c5dcc77f75fdc3346ed4d9dbbcc1e885b4a2992a273733646a9", "a5f18638-c7df-11ec-9d64-0242ac120002");
	ItemMeta meta =  item.getItemMeta();
	meta.setDisplayName(name);
	meta.getPersistentDataContainer().set(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING, "fakearrow");

	
	item.setItemMeta(meta);

	return item;
}
public static ItemStack SkyblockMenu() {
	ItemStack item = new ItemStack(Material.NETHER_STAR);
	ItemMeta meta =  item.getItemMeta();
	meta.setDisplayName("§eSkyblock Menu");
	meta.getPersistentDataContainer().set(NamespacedKey.minecraft("ability"), PersistentDataType.STRING, "skyblockmenu");item.setItemMeta(meta);
	ItemManager manager = new ItemManager("Skyblock Menu", "SKYBLOCK_MENU", ItemType.Non, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,null, "Open Menu", "skyblockmenu", null, 0, 0, 0, 0, Material.NETHER_STAR, ItemRarity.SPECIAL);
	SkyblockItems.put(manager.itemID, manager);

	
	

	return manager.getRawItemStack();
}
public static ItemStack MagicalMap() {
	ItemManager manager = new ItemManager("Magical Map", "MAGICAL_MAP", ItemType.Non, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, null, "Open Menu", "skyblockmenu", null, 0, 0, 0, 0, Material.FILLED_MAP, ItemRarity.DIVINE);
	SkyblockItems.put(manager.itemID, manager);

	
	

	return manager.getRawItemStack();
}
public static ItemStack SummoningEye() {
	ItemStack item = Tools.CustomHeadTexture("https://textures.minecraft.net/texture/daa8fc8de6417b48d48c80b443cf5326e3d9da4dbe9b25fcd49549d96168fc0");
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName("Summoning Eye");
	item.setItemMeta(meta);
	return item;
}
public static ItemStack NullSphere() {
	ItemStack item = new ItemStack(Material.FIREWORK_STAR);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName("§rNull Sphere");
	meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	item.setItemMeta(meta);
	return item;
}
public static ItemStack TwilightArrowPoison() {
	ItemStack item = new ItemStack(Material.MAGENTA_DYE);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName("§rTwilight Arrow Poison");
	item.setItemMeta(meta);
	return item;
}

public static ItemStack terminator() {
	ItemStack item = new ItemStack(Material.BOW);
	ItemMeta meta =  item.getItemMeta();
    PersistentDataContainer data = meta.getPersistentDataContainer();
    
	meta.setDisplayName("§6Terminator");
	
	ItemManager manager = new ItemManager("Terminator", "terminator", ItemType.Bow, 310, 0, 0, 0, 0, 50, 0, 250, 0, 0, 0,0,0,0,0,0, null, "Shortbow", "shortbow", null, 0, 0, 0, 0, Material.BOW, ItemRarity.LEGENDARY);

	data.set(NamespacedKey.minecraft("ability"), PersistentDataType.STRING, "shortbow");
	data.set(NamespacedKey.minecraft("shoottype"), PersistentDataType.STRING, "term");
	data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, "310");
	data.set(new NamespacedKey(Main.getMain(), "strenght"), PersistentDataType.INTEGER, 50);
	data.set(new NamespacedKey(Main.getMain(), "cd"), PersistentDataType.INTEGER, 250);
	data.set(new NamespacedKey(Main.getMain(), "atspeed"), PersistentDataType.INTEGER, 250);
	item.setItemMeta(meta);
	manager.customDataContainer.put("shoottype", "term");

	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
	public static void PainReleaser(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Heals §a20% §7of the Player's health");
		lore.add("§7and reduce §d40% §7damage taken by");
		lore.add("§7anything for §e5 §7seconds");
		lore.add("§8(Canot be stacked with \"CHAD INCOMING\")");
		ItemManager manager = new ItemManager("Pain Releaser", "PAIN_RELEASER", ItemType.Non, null, "RELEASE THE PAIN!", "", lore, 300, 30,0,0, Material.STICK, ItemRarity.EPIC);
		manager.setAbility(new ReleaseThePain(), AbilityType.RightClick);
		manager.setUnstackeble(true);
		SkyblockItems.put(manager.itemID, manager);


	}
	public static void ChadStick(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Heals §a30% §7of the Player's health");
		lore.add("§7and reduce §d50% §7damage taken by");
		lore.add("§7anything for §e10 §7seconds");
		lore.add("§8(Canot be stacked with \"RELEASE THE PAIN!\")");
		ItemManager manager = new ItemManager("Chad Stick", "CHAD_STICK", ItemType.Non, null, "CHAD INCOMING", "", lore, 500, 30,0,0, Material.STICK, ItemRarity.LEGENDARY);
		manager.setAbility(new ChadIncoming(), AbilityType.RightClick);
		manager.setUnstackeble(true);
		SkyblockItems.put(manager.itemID, manager);


	}
	public static ItemManager reaperPepper(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7You may eat up to 5 peppers");
		lore.add("§7before it's considered truly");
		lore.add("§7dangerous.");
		ArrayList<String> abiliytLore = new ArrayList<>();
		abiliytLore.add("§7Consume this §odangerous");
		abiliytLore.add("§7pepper to permanently gain");
		abiliytLore.add("§7§c+1 Health§f.");
		ItemManager manager = new ItemManager("Reaper Pepper", "REAPER_PEPPER", ItemType.Non, lore, "Consume", "", abiliytLore, 0, 0,0,0, ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/65f7810414a2cee2bc1de12ecef7a4c89fc9b38e9d0414a90991241a5863705f");
		manager.setAbility(new ReaperPepper(), AbilityType.RightClick);
		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
	public static ItemManager chiliPepper(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7It's red hot, but green!");
		ItemManager manager = new ItemManager("Chili Pepper", "CHILI_PEPPER", ItemType.Non, lore, null, null, null, 0, 0,0,0, ItemRarity.UNCOMMON, "https://textures.minecraft.net/texture/f859c8df1109c08a756275f1d2887c2748049fe33877769a7b415d56eda469d8");
		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
	public static ItemManager stuffedChiliPepper(){

		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7It's red hot, but green and");
		lore.add("§7stuffed!");
		ItemManager manager = new ItemManager("Stuffed Chili Pepper", "STUFFED_CHILI_PEPPER", ItemType.Non, null, null, null, null, 0, 0,0,0, ItemRarity.RARE, "https://textures.minecraft.net/texture/b6c98b410123b0944422303798fc2db8cea0feeb09d0da40f5361b59498f3e8b");
		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
	public static ItemManager enchantedBlazeRod(){

		
		ItemManager manager = new ItemManager("Enchanted Blaze Rod", "ENCHANTED_BLAZE_ROD", ItemType.Non, null, null, null, null, 0, 0,0,0, Material.BLAZE_ROD ,ItemRarity.UNCOMMON);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
	public static ItemManager enchantedMagmaCream(){



		ItemManager manager = new ItemManager("Enchanted Magma Cream", "ENCHANTED_MAGMA_CREAM", ItemType.Non, null, null, null, null, 0, 0,0,0, Material.MAGMA_CREAM,ItemRarity.UNCOMMON);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
	public static ItemManager enchantedBlazePowder(){



		ItemManager manager = new ItemManager("Enchanted Blaze Powder", "ENCHANTED_BLAZE_POWDER", ItemType.Non, null, null, null, null,
				0, 0,0,0, Material.BLAZE_POWDER,ItemRarity.UNCOMMON);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
	public static ItemManager enchantedNetherWart(){



		ItemManager manager = new ItemManager("Enchanted Nether Wart", "ENCHANTED_NETHER_STALK", ItemType.Non, null, null, null, null, 0, 0,0,0, Material.NETHER_WART,ItemRarity.UNCOMMON);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
	public static ItemManager enchantedCoal(){



		ItemManager manager = new ItemManager("Enchanted Coal", "ENCHANTED_COAL", ItemType.Non, null, null, null, null, 0,
				0,0,0, Material.COAL,ItemRarity.UNCOMMON);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}

	public static ItemManager lumpOfMagma(){



		ItemManager manager = new ItemManager("Lump Of Magma", "LUMP_OF_MAGMA", ItemType.Non, null, null, null, null, 0,
				0,0,0, Material.COAL,ItemRarity.UNCOMMON);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		manager.setNpcSellPrice(50);
		SkyblockItems.put(manager.itemID, manager);
		return manager;
	}
public static ItemStack Hyperion() {
	
	ArrayList<String> lore = new ArrayList<>();
	
	lore.add("§7Teleports §a10 blocks §7ahead of");
	lore.add("§7you. Then implode dealing");
	lore.add("§7§c%dmg% §7damage to nearby");
	lore.add("§7enemies. Also applies the wither");
	lore.add("§7shield scroll ability reducing");
	lore.add("§7damage taken and granting an");
	lore.add("§7absorption shield for §e5 §7seconds.");
	ItemManager manager = new ItemManager("Hyperion","hyperion", ItemType.Sword,260, 0, 0, 250, 0, 150, 0, 0, 0, 30, 0,0,0,0,0,0, null,
			"Wither Impact", "hyperion", lore, 300, 0, 0.3f, 10000, Material.IRON_SWORD,ItemRarity.LEGENDARY);
	manager.setAbilityLore(new AbilityLore(lore, "%dmg%", new Bundle<>(10000d, 0.3)));
	manager.getAbilityLore().addFlag(AbilityLore.LoreFlags.AsShortInteger);
	manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbility(new Hyperion_WitherImpact(), AbilityType.RightClick);
	SkyblockItems.put("hyperion", manager);
	return manager.getRawItemStack();
}

public static ItemStack TestItem() {
	
	ArrayList<String> lore = new ArrayList<>();
	lore.add("test");
	lore.add("Get Bugged Sticked lol");
	ArrayList<String> abilitylore = new ArrayList<>();
	abilitylore.add("Its the ultimate test");
	
	ItemManager manager = new ItemManager("Test Item","debug", ItemType.Sword,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1,1,1,1,1, lore, "Test", "lolxD", abilitylore, 69, 420, 0f, 0, Material.STICK,ItemRarity.SUPREME);
	manager.addSlot(new GemstoneSlot(SlotType.Universal));manager.addSlot(new GemstoneSlot(SlotType.Universal));manager.addSlot(new GemstoneSlot(SlotType.Universal));manager.addSlot(new GemstoneSlot(SlotType.Universal));manager.addSlot(new GemstoneSlot(SlotType.Universal));manager.addSlot(new GemstoneSlot(SlotType.Universal));manager.addSlot(new GemstoneSlot(SlotType.Universal));
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack Bonemerang() {
	
	
	ArrayList<String> abilitylore = new ArrayList<>();
	abilitylore.add("§7Throw bone a short distance,");
	abilitylore.add("§7dealing the damage an arrow");
	abilitylore.add("§7would.");
	abilitylore.add("");
	abilitylore.add("§7Deals §cdouble damage §7when");
	abilitylore.add("§7coming back. Pierces up to §e10");
	abilitylore.add("§7foes");
	
	ItemManager manager = new ItemManager("Bonemerang","bonemerang", ItemType.Bow,270, 0, 0, 0, 0, 130, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Swing", "swing", abilitylore, 0, 0, 0f, 0, Material.BONE,ItemRarity.LEGENDARY);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack HotPotatoBook() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§7Combine this Book in an Anvil");
	lore.add("§7with a weapon or armor piece to");
	lore.add("§7gain a small but permanent stat");
	lore.add("§7boost!");
	ItemManager manager = new ItemManager("§5Hot Potato Book","HOT_POTATO_BOOK", ItemType.PotatoBook,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, lore, null, null, null, 0, 0, 0f, 0, Material.BOOK, ItemRarity.EPIC);
	manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
	manager.customDataContainer.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack Recombobulator3000() {
	ItemManager manager = new ItemManager("Recombobulator 3000", "rocombobulator3000", ItemType.Recom, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/57ccd36dc8f72adcb1f8c8e61ee82cd96ead140cf2a16a1366be9b5a8e3cc3fc");
SkyblockItems.put(manager.itemID, manager);
return manager.getRawItemStack();
}

public static ItemStack Divans_Drill() {
	ItemManager manager = new ItemManager("Divan's Drill", "DIVAN_DRILL", ItemType.Drill, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10,1800,150,0,0, null, null, null, null, 0, 0, 0, 0,Material.PRISMARINE_SHARD, ItemRarity.MYTHIC);
	manager.customDataContainer.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
	manager.addSlot(new GemstoneSlot(SlotType.Mining));
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack Mithril() {
	ItemManager manager = new ItemManager("Mithril", "MITHRIL_ORE", ItemType.Non, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0,Material.PRISMARINE_CRYSTALS, ItemRarity.COMMON);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack Titanium() {
	ItemManager manager = new ItemManager("Titanium", "TITANIUM", ItemType.Non, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, ItemRarity.RARE, "https://textures.minecraft.net/texture/a14c6e41a762d37863a9fff6888c738905b92cc6c3898892a38dfdfe2ac4bf", UUID.fromString("da5bd3bc-d2d2-11ec-9d64-0242ac120002"));
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack Gemstone_Gauntlet() {
	
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§7Harness the true power of the");
	lore.add("§dGemstones§7!Each gemstone placed in");
	lore.add("§7here will boost its associated");
	lore.add("§astat§7.");
	lore.add("");
	lore.add("§eCan be used as both a §c sword §eand");
	lore.add("§ea §9pickaxe");
	
	ItemManager manager = new ItemManager("Gemstone Gauntlet", "GEMSTONE_GAUNTLET", ItemType.Gauntlet, 200, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,9,1600,0,0,0, lore, null, null, null, 0, 0, 0, 0, ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/7bf01c198f6e16965e230235cd22a5a9f4a40e40941234478948ff9a56e51775");
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Topaz));
	
	
	manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
	manager.addSlot(new GemstoneSlot(SlotType.Amethyst));
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack SwordOfTheUniverse() {
	ItemManager manager = new ItemManager("Sword of the Universe", "NOVA_SWORD", ItemType.Sword,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.GOLDEN_SWORD, ItemRarity.SPECIAL);
	manager.addSlot(new GemstoneSlot(SlotType.Ruby));
	manager.setDamage(Double.POSITIVE_INFINITY);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack CannonTestItem() {
	ArrayList<String> abilityLore = new ArrayList<>();
	abilityLore.add("§7Shoots a Cannonball");
	ItemManager manager = new ItemManager("Totaly a Cannon", "CANNON", ItemType.Bow,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Cannon Ball", "cannon", abilityLore, 0, 0, 0, 0, Material.STICK, ItemRarity.SPECIAL);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack SorrowHelmet() {
	ItemManager manager = new ItemManager("Sorrow Helmet", "SORROW_HELMET", ItemType.Helmet, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 50, 20, 0, 0,null, null, null, null, 0, 0, 0, 0, Material.CHAINMAIL_HELMET, ItemRarity.LEGENDARY);
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Universal));
	manager.setStat(Stats.TrueDefense, 100);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack SorrowChestplate() {
	ItemManager manager = new ItemManager("Sorrow Chestplate", "SORROW_CHESTPLATE", ItemType.Chestplate, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 50, 20, 0, 0,null, null, null, null, 0, 0, 0, 0, Material.CHAINMAIL_CHESTPLATE, ItemRarity.LEGENDARY);
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Universal));
	manager.setStat(Stats.TrueDefense, 200);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack SorrowLeggings() {
	ItemManager manager = new ItemManager("Sorrow Leggings", "SORROW_LEGGINGS", ItemType.Leggings, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 50, 20, 0,0, null, null, null, null, 0, 0, 0, 0, Material.CHAINMAIL_LEGGINGS, ItemRarity.LEGENDARY);
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Universal));
	manager.setStat(Stats.TrueDefense, 150);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack SorrowBoots() {
	ItemManager manager = new ItemManager("Sorrow Boots", "SORROW_Boots", ItemType.Boots, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 50, 20, 0, 0,null, null, null, null, 0, 0, 0, 0, Material.CHAINMAIL_BOOTS, ItemRarity.LEGENDARY);
	manager.setStat(Stats.TrueDefense, 75);
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Universal));
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}



public static ItemStack DivanHelmet() {
	ItemManager manager = new ItemManager("Helmet of Divan", "DIVAN_HELMET", ItemType.Helmet, 0, 100, 130, 0, 0, 0, 0, 0, 0, 0, 0, 0, 80, 30, 0, 0,null, null, null, null, 0, 0, 0, 0, ItemRarity.LEGENDARY, "http://textures.minecraft.net/texture/83f69ef27f3120a51050e6be66a0e3f8e89af888d4806716bf86f590da638317");
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Topaz));
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack DivanChestplate() {
	ItemManager manager = new ItemManager("Chestplate of Divan", "DIVAN_CHESTPLATE", ItemType.Chestplate, 0, 200, 130, 0, 0, 0, 0, 0, 0, 0, 5, 0, 80, 30, 0, 0,null, null, null, null, 0, 0, 0, 0, Material.GOLDEN_CHESTPLATE, ItemRarity.LEGENDARY);
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Topaz));
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack DivanLeggings() {
	ItemManager manager = new ItemManager("Leggings of Divan", "DIVAN_LEGGINGS", ItemType.Leggings, 0, 130, 170, 0, 0, 0, 0, 0, 0, 0, 5, 0, 80, 30, 0,0, null, null, null, null, 0, 0, 0, 0, Material.GOLDEN_LEGGINGS, ItemRarity.LEGENDARY);
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Topaz));
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack DivanBoots() {
	ItemManager manager = new ItemManager("Boots of Divan", "DIVAN_Boots", ItemType.Boots, 0, 80, 110, 0, 0, 0, 0, 0, 0, 0, 5, 0, 80, 30, 0, 0,null, null, null, null, 0, 0, 0, 0, Material.GOLDEN_BOOTS, ItemRarity.LEGENDARY);
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Amber));
	manager.addSlot(new GemstoneSlot(SlotType.Jade));
	manager.addSlot(new GemstoneSlot(SlotType.Topaz));
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}



public static ItemStack ManaFluxPowerOrb() {
	ItemManager manager = new ItemManager("Mana Flux Power Orb", "MANAFLUX", ItemType.Non, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, null, "Orb", "orb", null, 0, 0, 0, 0, ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/1d5a09884cb83ef5c908dddd385f246fefdee221712c010177f54376da238fdd");

	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack JerryChineGun() {
	ArrayList<String> abilityLore = new ArrayList<>();
	abilityLore.add("§7Shoots a jerry bullet, dealing");
	abilityLore.add("§c500 §7damage on impact and");
	abilityLore.add("§7knocking you back.");
	ItemManager manager = new ItemManager("Jerry-chine Gun", "JERRY_STAFF", ItemType.Sword, 80, 0, 0, 200, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, null, "Rapid-Fire", "jerrystaff", abilityLore, 30, 0, 0, 500, Material.GOLDEN_HORSE_ARMOR ,ItemRarity.EPIC);
	manager.customDataContainer.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack Volta() {
	ItemManager manager = new ItemManager("Volta", "VOLTA", ItemType.DrillFuel, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, null, null, null, null, 0, 0, 0, 0, ItemRarity.RARE, "https://textures.minecraft.net/texture/7b3328d3e9d710420322555b17239307f12270adf81bf63afc50faa04b5c06e1", UUID.fromString("d0f23c32-d9dd-11ec-9d64-0242ac120002"));
manager.customDataContainer.put("refuel", "10000");
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack MithrilInfusedFuelTank() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§8Drill Part");
	lore.add(" ");
	lore.add("§7Part Type: §6Fuel Tank");
	lore.add(" ");
	lore.add("§7Increases the fuel capacity to");
	lore.add("§210,000 §7.");
	lore.add(" ");
	lore.add("§7Can be applied to Drills by");
	lore.add("§7talking to a §2Drill Mechanic §7!");
	
	ArrayList<String> aplyLore = new ArrayList<>();
	aplyLore.add("§7Increases the fuel capacity to");
	aplyLore.add("§210,000");
	
	DrillPart manager = new DrillPart("Mithril-Infused Fuel Tank", "MITHRIL_FUEL_TANK", ItemType.FuelTank, lore, aplyLore, ItemRarity.RARE, "https://textures.minecraft.net/texture/ecb316f7a227a8c59d58ae0dd6768fe4fa546d55b9cfdd56cfe40b6586d81c24");
manager.setFuelCapacity(10000);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack TitaniumInfusedFuelTank() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§8Drill Part");
	lore.add(" ");
	lore.add("§7Part Type: §6Fuel Tank");
	lore.add(" ");
	lore.add("§7Increases the fuel capacity to");
	lore.add("§225,000 §7.");
	lore.add(" ");
	lore.add("§7Can be applied to Drills by");
	lore.add("§7talking to a §2Drill Mechanic §7!");
	
	ArrayList<String> aplyLore = new ArrayList<>();
	aplyLore.add("§7Increases the fuel capacity to");
	aplyLore.add("§225,000");
	
	DrillPart manager = new DrillPart("Titanium-Infused Fuel Tank", "TITANIUM_FUEL_TANK", ItemType.FuelTank, lore, aplyLore, ItemRarity.RARE, "https://textures.minecraft.net/texture/aef5acf134fa952f13fdf0b3899060cc18ebc0a971347e45a31ecf95a4b0b31c");
manager.setFuelCapacity(25000);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack GemstoneFuelTank() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§8Drill Part");
	lore.add(" ");
	lore.add("§7Part Type: §6Fuel Tank");
	lore.add(" ");
	lore.add("§7Increases the fuel capacity to");
	lore.add("§250,000 §7.");
	lore.add(" ");
	lore.add("§7Can be applied to Drills by");
	lore.add("§7talking to a §2Drill Mechanic §7!");
	
	ArrayList<String> aplyLore = new ArrayList<>();
	aplyLore.add("§7Increases the fuel capacity to");
	aplyLore.add("§250,000");
	
	DrillPart manager = new DrillPart("Gemstone Fuel Tank", "GEMSTONE_FUEL_TANK", ItemType.FuelTank, lore, aplyLore, ItemRarity.RARE, "https://textures.minecraft.net/texture/9fef722da05ae8bd8132905a11b5b6dfbf8e7ae0af599c8266e160fb05e6159b");
manager.setFuelCapacity(50000);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack PerfectlyCutFuelTank() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§8Drill Part");
	lore.add(" ");
	lore.add("§7Part Type: §6Fuel Tank");
	lore.add(" ");
	lore.add("§7Increases the fuel capacity to");
	lore.add("§2100,000 §7.");
	lore.add(" ");
	lore.add("§7Can be applied to Drills by");
	lore.add("§7talking to a §2Drill Mechanic §7!");
	
	ArrayList<String> aplyLore = new ArrayList<>();
	aplyLore.add("§7Increases the fuel capacity to");
	aplyLore.add("§2100,000");
	
	DrillPart manager = new DrillPart("Perfectly-Cut Fuel Tank", "PERFECTLY_CUT_FUEL_TANK", ItemType.FuelTank, lore, aplyLore, ItemRarity.RARE, "https://textures.minecraft.net/texture/f7e541dfb4ba1f7dc28b548e347abbdc987ebe0e61c49fa87111ef1b2dcb2218");
manager.setFuelCapacity(100000);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack MithrilPlatedDrillEngine() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§8Drill Part");
	lore.add(" ");
	lore.add("§7Part Type: §6Drill Engine");
	lore.add(" ");
	lore.add("§7Grants §a+50 §6⸕Mining Speed §7.");
	lore.add(" ");
	lore.add("§7Can be applied to Drills by");
	lore.add("§7talking to a §2Drill Mechanic §7!");
	
	ArrayList<String> aplyLore = new ArrayList<>();
	aplyLore.add("§7Grants §a+50 §6⸕Mining Speed §7.");
	
	DrillPart manager = new DrillPart("Mithril-Plated Drill Engine", "MITHRIL_DRILL_ENGINE", ItemType.DrillEngine, lore, aplyLore, ItemRarity.RARE, "https://textures.minecraft.net/texture/4a7d54ca45a398c364cebbffb5390ce5e0345e0c7bc5e863acabf57d1342c4bd");
manager.SetApplyMiningSpeed(50);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack TitaniumPlatedDrillEngine() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§8Drill Part");
	lore.add(" ");
	lore.add("§7Part Type: §6Drill Engine");
	lore.add(" ");
	lore.add("§7Grants §a+100 §6⸕Mining Speed §7.");
	lore.add(" ");
	lore.add("§7Can be applied to Drills by");
	lore.add("§7talking to a §2Drill Mechanic §7!");
	
	ArrayList<String> aplyLore = new ArrayList<>();
	aplyLore.add("§7Grants §a+100 §6⸕Mining Speed §7.");
	
	DrillPart manager = new DrillPart("Titanium-Plated Drill Engine", "TITANIUM_DRILL_ENGINE", ItemType.DrillEngine, lore, aplyLore, ItemRarity.RARE, "https://textures.minecraft.net/texture/fb9051485e15700418022e38a5db28a049a2e627c5ffa56fd313bcdead406a8e");
manager.SetApplyMiningSpeed(100);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}
public static ItemStack RubyPolishedDrillEngine() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§8Drill Part");
	lore.add(" ");
	lore.add("§7Part Type: §6Drill Engine");
	lore.add(" ");
	lore.add("§7Grants §a+150 §6⸕Mining Speed §7.");
	lore.add(" ");
	lore.add("§7Can be applied to Drills by");
	lore.add("§7talking to a §2Drill Mechanic §7!");
	
	ArrayList<String> aplyLore = new ArrayList<>();
	aplyLore.add("§7Grants §a+150 §6⸕Mining Speed §7.");
	
	DrillPart manager = new DrillPart("Ruby-polished Drill Engine", "RUBY_POLISHED_DRILL_ENGINE", ItemType.DrillEngine, lore, aplyLore, ItemRarity.RARE, "https://textures.minecraft.net/texture/7f0b9c45bbae11b00e4323af0119c27883d3690e8c48b49cf29b246981179e0");
manager.SetApplyMiningSpeed(150);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

public static ItemStack SapphirePolishedDrillEngine() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§8Drill Part");
	lore.add(" ");
	lore.add("§7Part Type: §6Drill Engine");
	lore.add(" ");
	lore.add("§7Grants §a+250 §6⸕Mining Speed §7.");
	lore.add(" ");
	lore.add("§7Can be applied to Drills by");
	lore.add("§7talking to a §2Drill Mechanic §7!");
	
	ArrayList<String> aplyLore = new ArrayList<>();
	aplyLore.add("§7Grants §a+250 §6⸕Mining Speed §7.");
	
	DrillPart manager = new DrillPart("Sapphire-polished Drill Engine", "SAPPHIRE_POLISHED_DRILL_ENGINE", ItemType.DrillEngine, lore, aplyLore, ItemRarity.RARE, "https://textures.minecraft.net/texture/863efa6c13a180eaa71cf0c64a62350c42b24093e9957492b2449578268256ab");
manager.SetApplyMiningSpeed(250);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}

	public static ItemStack AmberPolishedDrillEngine() {
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§8Drill Part");
		lore.add(" ");
		lore.add("§7Part Type: §6Drill Engine");
		lore.add(" ");
		lore.add("§7Grants §a+400 §6⸕Mining Speed §7.");
		lore.add(" ");
		lore.add("§7Can be applied to Drills by");
		lore.add("§7talking to a §2Drill Mechanic §7!");

		ArrayList<String> aplyLore = new ArrayList<>();
		aplyLore.add("§7Grants §a+400 §6⸕Mining Speed §7.");

		DrillPart manager = new DrillPart("Amber-polished Drill Engine", "AMBER_POLISHED_DRILL_ENGINE", ItemType.DrillEngine, lore, aplyLore, ItemRarity.RARE, "https://textures.minecraft.net/texture/9fef722da05ae8bd8132905a11b5b6dfbf8e7ae0af599c8266e160fb05e6159b");
		manager.SetApplyMiningSpeed(400);
		SkyblockItems.put(manager.itemID, manager);
		return manager.getRawItemStack();
	}

	public static ItemStack TakoPowerdDrillEngine() {
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7TakoTheMaid powered Drill Engine");
		lore.add("§7Dont ask how the power get supplied");
		lore.add(" ");
		lore.add("§8Drill Part");
		lore.add(" ");
		lore.add("§7Part Type: §6Drill Engine");
		lore.add(" ");
		lore.add("§7Grants §a+5000 §6⸕Mining Speed §7.");
		lore.add(" ");
		lore.add("§7Can be applied to Drills by");
		lore.add("§7talking to a §2Drill Mechanic §7!");

		ArrayList<String> aplyLore = new ArrayList<>();
		aplyLore.add("§7Grants §a+5000 §6⸕Mining Speed §7.");

		DrillPart manager = new DrillPart("Maid Powered Drill Engine", "TAKOTHEMAID_DRILL_ENGINE", ItemType.DrillEngine, lore, aplyLore, ItemRarity.SPECIAL, "https://textures.minecraft.net/texture/e8d11ac394a12ec9229aaab1d114953971f1a00b806467d6a157e72c3769d5f7");
		manager.SetApplyMiningSpeed(5000);
		SkyblockItems.put(manager.itemID, manager);
		return manager.getRawItemStack();
	}

public static ItemStack EnderDragonLegendary() {
	ArrayList<String> lore1 = new ArrayList<>();
	lore1.add("§7Deal §a%ability1% §7more damage to");
	lore1.add("§7end mobs");
	ArrayList<String> lore2 = new ArrayList<>();
	lore2.add("§7Buffs the Aspect of the");
	lore2.add("§7Dragons sword by §a%ability2-1% §c Damage");
	lore2.add("§7and §a%ability2-2% §c Strenght.");
	ArrayList<String> lore3 = new ArrayList<>();	
	lore3.add("§7Increases most stats by §a%ability3%");
	Pet pet = new Pet("Ender Dragon", "ENDER_DRAGON;LEGENDARY", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 50, 50, 50, 0, 0, 0, 0, 0, 0, 0, 0,Material.PLAYER_HEAD, ItemRarity.LEGENDARY, 100, PetType.Combat, "End Strike", lore1, "One with the Dragons", lore2, "Superior", lore3, "http://textures.minecraft.net/texture/c279dc91373b427769043fae889ce2add3ae32166496534a4d6a8a8aaa2d");
	return pet.getRawItemStack();
}
public static ItemStack CarsCupcakeSpetial() {
	ArrayList<String> lore1 = new ArrayList<>();
	lore1.add("§7Its just op");
;
	ArrayList<String> lore2 = new ArrayList<>();
	lore2.add("§7Gives you admin power");

	ArrayList<String> lore3 = new ArrayList<>();	
	lore3.add("§7Increases all stats by §a100% §7cuz idk ¯\\_(ツ)_/¯");
	Pet pet = new Pet("CarsCupcake", "ADMIN_PET;SPETIAL", 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,100, Material.PLAYER_HEAD, ItemRarity.SPECIAL, 100, PetType.Mining, "Op", lore1, "Admin", lore2, "More Stats!", lore3, "http://textures.minecraft.net/texture/d3b83f3763982eada0538a90122cab68a20c0750f956ffc129f9bfff71b537c");
	return pet.getRawItemStack();
}


//Pet Idea:
//Crystal Golem
//Head texture: http://textures.minecraft.net/texture/197f286dfe87c92cea292881571e82612586438cab5c0f847ffe0c91ab9dbe1
//gives pristine 0-10 on leg, mining speed 0-100 and minig fortune 0-100.
//Abilitys 
//1. Spetial one: has universal gemstone slots +1 for each rarity
//2. Ability More Minig Fortune while breaking gemstones
//3. More Gemstone Powder while breaking gemstone

public static ItemStack GemstoneGolemCommon() {
	ArrayList<String> lore1 = new ArrayList<>();
	lore1.add("§7This Pet is able to use 1 gemstone");

	ArrayList<String> lore2 = new ArrayList<>();
	lore2.add("§7Gain §6%ability2% ☘ Mining Fortune");
	lore2.add("§7while Mining §eGemstones");

	ArrayList<String> lore3 = new ArrayList<>();	
	lore3.add("§7Gain a §5%ability3%% §7chance to");
	lore3.add("§7gain extra §dGemstone Powder");
	Pet pet = new Pet("Gemstone Golem", "GEMSTONE_GOLEM;COMMON", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100, 10,0, Material.PLAYER_HEAD, ItemRarity.COMMON, 100, PetType.Mining, "Gemstone Slots", lore1, null, null, null, null, "http://textures.minecraft.net/texture/197f286dfe87c92cea292881571e82612586438cab5c0f847ffe0c91ab9dbe1");
	pet.addSlot(new GemstoneSlot(SlotType.Universal));
	return pet.getRawItemStack();
}
public static ItemStack GemstoneGolemUncommon() {
	ArrayList<String> lore1 = new ArrayList<>();
	lore1.add("§7This Pet is able to use 1 gemstone");

	ArrayList<String> lore2 = new ArrayList<>();
	lore2.add("§7Gain §6%ability2% ☘ Mining Fortune");
	lore2.add("§7while Mining §eGemstones");

	ArrayList<String> lore3 = new ArrayList<>();	
	lore3.add("§7Gain a §5%ability3%% §7chance to");
	lore3.add("§7gain extra §dGemstone Powder");
	Pet pet = new Pet("Gemstone Golem", "GEMSTONE_GOLEM;UNCOMMON", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100, 10,0, Material.PLAYER_HEAD, ItemRarity.UNCOMMON, 100, PetType.Mining, "Gemstone Slots", lore1, null, null, null, null, "http://textures.minecraft.net/texture/197f286dfe87c92cea292881571e82612586438cab5c0f847ffe0c91ab9dbe1");
	pet.addSlot(new GemstoneSlot(SlotType.Universal));
	return pet.getRawItemStack();
}
public static ItemStack GemstoneGolemRare() {
	ArrayList<String> lore1 = new ArrayList<>();
	lore1.add("§7This Pet is able to use 2 gemstone");

	ArrayList<String> lore2 = new ArrayList<>();
	lore2.add("§7Gain §6%ability2% ☘ Mining Fortune");
	lore2.add("§7while Mining §eGemstones");

	ArrayList<String> lore3 = new ArrayList<>();	
	lore3.add("§7Gain a §5%ability3%% §7chance to");
	lore3.add("§7gain extra §dGemstone Powder");
	Pet pet = new Pet("Gemstone Golem", "GEMSTONE_GOLEM;RARE", 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100, 10,0, Material.PLAYER_HEAD, ItemRarity.RARE, 100, PetType.Mining, "Gemstone Slots", lore1, "My Gemstones >:)", lore2, null, null, "http://textures.minecraft.net/texture/197f286dfe87c92cea292881571e82612586438cab5c0f847ffe0c91ab9dbe1");
	pet.addSlot(new GemstoneSlot(SlotType.Universal));pet.addSlot(new GemstoneSlot(SlotType.Universal));
	return pet.getRawItemStack();
}
public static ItemStack GemstoneGolemEpic() {
	ArrayList<String> lore1 = new ArrayList<>();
	lore1.add("§7This Pet is able to use 3 gemstone");

	ArrayList<String> lore2 = new ArrayList<>();
	lore2.add("§7Gain §6%ability2% ☘ Mining Fortune");
	lore2.add("§7while Mining §eGemstones");

	ArrayList<String> lore3 = new ArrayList<>();	
	lore3.add("§7Gain a §5%ability3%% §7chance to");
	lore3.add("§7gain extra §dGemstone Powder");
	Pet pet = new Pet("Gemstone Golem", "GEMSTONE_GOLEM;EPIC", 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100, 10,0, Material.PLAYER_HEAD, ItemRarity.EPIC, 100, PetType.Mining, "Gemstone Slots", lore1, "My Gemstones >:)", lore2, null, null, "http://textures.minecraft.net/texture/197f286dfe87c92cea292881571e82612586438cab5c0f847ffe0c91ab9dbe1");
	pet.addSlot(new GemstoneSlot(SlotType.Universal));pet.addSlot(new GemstoneSlot(SlotType.Universal));pet.addSlot(new GemstoneSlot(SlotType.Universal));
	return pet.getRawItemStack();
}
public static ItemStack GemstoneGolemLegendary() {
	ArrayList<String> lore1 = new ArrayList<>();
	lore1.add("§7This Pet is able to use 3 gemstone");

	ArrayList<String> lore2 = new ArrayList<>();
	lore2.add("§7Gain §6%ability2% ☘ Mining Fortune");
	lore2.add("§7while Mining §eGemstones");

	ArrayList<String> lore3 = new ArrayList<>();	
	lore3.add("§7Gain a §5%ability3%% §7chance to");
	lore3.add("§7gain extra §dGemstone Powder");
	Pet pet = new Pet("Gemstone Golem", "GEMSTONE_GOLEM;LEGENDARY", 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 100, 100, 10,0,
			Material.PLAYER_HEAD, ItemRarity.LEGENDARY, 100, PetType.Mining, "Gemstone Slots", lore1, "My Gemstones >:)", lore2, "Powder Gain", lore3,
			"http://textures.minecraft.net/texture/197f286dfe87c92cea292881571e82612586438cab5c0f847ffe0c91ab9dbe1");
	pet.addSlot(new GemstoneSlot(SlotType.Universal));pet.addSlot(new GemstoneSlot(SlotType.Universal));pet.addSlot(new GemstoneSlot(SlotType.Universal));
	return pet.getRawItemStack();
}



//Ruby gemsone

public static ItemStack RoughRuby() {
	Gemstone gem = new Gemstone("§?§ Rough Ruby", "ROUGH_RUBY_GEM", null, GemstoneType.Ruby, GemState.Rough, "https://textures.minecraft.net/texture/d159b03243be18a14f3eae763c4565c78f1f339a8742d26fde541be59b7de07");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawedRuby() {
	Gemstone gem = new Gemstone("§?§ Flawed Ruby", "FLAWED_RUBY_GEM", null, GemstoneType.Ruby, GemState.Flawed, "https://textures.minecraft.net/texture/46d81068cbdf4a364231a26453d6cd660a0095f9cd8795307c5be667427712e");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FineRuby() {
	Gemstone gem = new Gemstone("§?§ Fine Ruby", "FINE_RUBY_GEM", null, GemstoneType.Ruby, GemState.Fine, "https://textures.minecraft.net/texture/e672959028f274b379d430f068f0f15a4f793eac12afb94ae0b4e50cf895df0f");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawlessRuby() {
	Gemstone gem = new Gemstone("§?§ Flawless Ruby", "FLAWLESS_RUBY_GEM", null, GemstoneType.Ruby, GemState.Flawless, "https://textures.minecraft.net/texture/926a248fbbc06cf06e2c920eca1cac8a2c96164d3260494bed142d553026cc6");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack PerfectRuby() {
	Gemstone gem = new Gemstone("§?§ Perfect Ruby", "PERFECT_RUBY_GEM", null, GemstoneType.Ruby, GemState.Perfect, "https://textures.minecraft.net/texture/39b6e047d3b2bca85e8cc49e5480f9774d8a0eafe6dfa9559530590283715142");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}

//Amber Gemstone
public static ItemStack RoughAmber() {
	Gemstone gem = new Gemstone("⸕ Rough Amber", "ROUGH_AMBER_GEM", null, GemstoneType.Amber, GemState.Rough, "https://textures.minecraft.net/texture/da19436f6151a7b66d65ed7624add4325cfbbc2eee815fcf76f4c29ddf08f75b");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawedAmber() {
	Gemstone gem = new Gemstone("⸕ Flawed Amber", "FLAWED_AMBER_GEM", null, GemstoneType.Amber, GemState.Flawed, "https://textures.minecraft.net/texture/173bcfc39eb85df1848535985214060a1bd1b3bb47defe4201476edc31671744");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FineAmber() {
	Gemstone gem = new Gemstone("⸕ Fine Amber", "FINE_AMBER_GEM", null, GemstoneType.Amber, GemState.Fine, "https://textures.minecraft.net/texture/4b1cce22de19ed6727abc5e6c2d57864c871a44c956bbe2eb3960269b686b8b3");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawlessAmber() {
	Gemstone gem = new Gemstone("⸕ Flawless Amber", "FLAWLESS_AMBER_GEM", null, GemstoneType.Amber, GemState.Flawless, "https://textures.minecraft.net/texture/9dce62f70ac046b881113c6cf862987727774e265885501c9a245b180db08c0d");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack PerfectAmber() {
	Gemstone gem = new Gemstone("⸕ Perfect Amber", "PERFECT_AMBER_GEM", null, GemstoneType.Amber, GemState.Perfect, "https://textures.minecraft.net/texture/37ae236cdec3f2a6f51eae15e2c8f6228b34f137da1569fec9e803f9cd81759d");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}


//Jade Gemstone
public static ItemStack RoughJade() {
	Gemstone gem = new Gemstone("☘ Rough Jade", "ROUGH_JADE_GEM", null, GemstoneType.Jade, GemState.Rough, "https://textures.minecraft.net/texture/3b4c2afd544d0a6139e6ae8ef8f0bfc09a9fd837d0cad4f5cd0fe7f607b7d1a0");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawedJade() {
	Gemstone gem = new Gemstone("☘ Flawed Jade", "FLAWED_JADE_GEM", null, GemstoneType.Jade, GemState.Flawed, "https://textures.minecraft.net/texture/82282c6bb8343e0f0d61ee0747dada75344f332e9ff0acaa3adcdf09321d3dd");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FineJade() {
	Gemstone gem = new Gemstone("☘ Fine Jade", "FINE_JADE_GEM", null, GemstoneType.Jade, GemState.Fine, "https://textures.minecraft.net/texture/b28f1c0c5092e12d33770df45c5845a9610886039b34abe93a16c5e942dfc8e4");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawlessJade() {
	Gemstone gem = new Gemstone("☘ Flawless Jade", "FLAWLESS_JADE_GEM", null, GemstoneType.Jade, GemState.Flawless, "https://textures.minecraft.net/texture/f89f75e0b00378a583dbba728dcdc6e9346f31dd601d448f3d60615c7465cc3e");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack PerfectJade() {
	Gemstone gem = new Gemstone("☘ Perfect Jade", "PERFECT_JADE_GEM", null, GemstoneType.Jade, GemState.Perfect, "https://textures.minecraft.net/texture/3fced7977382bf71d4ee17ff5b919e0eb7972083c4cccfa175c8753ae40ba006");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}


//Sapphire Gemstone
public static ItemStack RoughSapphire() {
	Gemstone gem = new Gemstone("✎ Rough Sapphire", "ROUGH_SAPPHIRE_GEM", null, GemstoneType.Sapphire, GemState.Rough, "https://textures.minecraft.net/texture/cfcebe54dbc345ea7e22206f703e6b33befbe95b6a918bd1754b76188bc65bb5");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawedSapphire() {
	Gemstone gem = new Gemstone("✎ Flawed Sapphire", "FLAWED_SAPPHIRE_GEM", null, GemstoneType.Sapphire, GemState.Flawed, "https://textures.minecraft.net/texture/8a0af99e8d8703194a847a55268cf5ef4ac4eb3b24c0ed86551339d10b646529");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FineSapphire() {
	Gemstone gem = new Gemstone("✎ Fine Sapphire", "FINE_SAPPHIRE_GEM", null, GemstoneType.Sapphire, GemState.Fine, "https://textures.minecraft.net/texture/36161daa3589ec9c8187459ac36fd4dd2646c040678d3bfacb72a2210c6c801c");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawlessSapphire() {
	Gemstone gem = new Gemstone("✎ Flawless Sapphire", "FLAWLESS_SAPPHIRE_GEM", null, GemstoneType.Sapphire, GemState.Flawless, "https://textures.minecraft.net/texture/957cfa9c75ba584645ee2af6d9867d767ddea4667cdfc72dc1061dd1975ca7d0");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack PerfectSapphire() {
	Gemstone gem = new Gemstone("✎ Perfect Sapphire", "PERFECT_SAPPHIRE_GEM", null, GemstoneType.Sapphire, GemState.Perfect, "https://textures.minecraft.net/texture/8e93ebacb60b71793355fde0d4bba43a9c5ec09c3f38897c48c1f857523a0a29");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}


//Amethyst Gemstone
public static ItemStack RoughAmethyst() {
	Gemstone gem = new Gemstone("§?§ Rough Amethyst", "ROUGH_AMETHYST_GEM", null, GemstoneType.Amethyst, GemState.Rough, "https://textures.minecraft.net/texture/e493c6f540c7001fed97b07f6b4c89128e3a7c37563aa223f0acca314f175515");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawedAmethyst() {
	Gemstone gem = new Gemstone("§?§ Flawed Amethyst", "FLAWED_AMETHYST_GEM", null, GemstoneType.Amethyst, GemState.Flawed, "https://textures.minecraft.net/texture/71db59260895578d37e59505880602de940b088e5fff8da3e65201d739c86e84");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FineAmethyst() {
	Gemstone gem = new Gemstone("§?§ Fine Amethyst", "FINE_AMETHYST_GEM", null, GemstoneType.Amethyst, GemState.Fine, "https://textures.minecraft.net/texture/7a1ee5ffce04eb7da592d42414ff35e1bf38194d6b82e310dbc6261b47fb9c91");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawlessAmethyst() {
	Gemstone gem = new Gemstone("§?§ Flawless Amethyst", "FLAWLESS_AMETHYST_GEM", null, GemstoneType.Amethyst, GemState.Flawless, "https://textures.minecraft.net/texture/d3623521c8111ad29e9dcf7acc56085a9ab07da732d1518976aee61d0b3e3bd6");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack PerfectAmethyst() {
	Gemstone gem = new Gemstone("§?§ Perfect Amethyst", "PERFECT_AMETHYST_GEM", null, GemstoneType.Amethyst, GemState.Perfect, "https://textures.minecraft.net/texture/d886e0f41185b18a3afd89488d2ee4caa0735009247cccf039ced6aed752ff1a");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}


//Jasper Gemstone
public static ItemStack RoughJasper() {
	Gemstone gem = new Gemstone("§?? Rough Jasper", "ROUGH_JASPER_GEM", null, GemstoneType.Jasper, GemState.Rough, "https://textures.minecraft.net/texture/23d064ec150172d05844c11a18619c1421bbfb2ddd1dbb87cdc10e22252b773b");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawedJasper() {
	Gemstone gem = new Gemstone("§?? Flawed Jasper", "FLAWED_JASPER_GEM", null, GemstoneType.Jasper, GemState.Flawed, "https://textures.minecraft.net/texture/a73511e504c316b139edb35febe73ef591c0f455e8caf9ee353bc12b6c14a922");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FineJasper() {
	Gemstone gem = new Gemstone("§?? Fine Jasper", "FINE_JASPER_GEM", null, GemstoneType.Jasper, GemState.Fine, "https://textures.minecraft.net/texture/aac15f6fcf2ce963ef4ca71f1a8685adb97eb769e1d11194cbbd2e964a88978c");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawlessJasper() {
	Gemstone gem = new Gemstone("§?? Flawless Jasper", "FLAWLESS_JASPER_GEM", null, GemstoneType.Jasper, GemState.Flawless, "https://textures.minecraft.net/texture/ff993d3a43d40597b474485976160d0cf52ac64d157307d3b1c941db224d0ac6");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack PerfectJasper() {
	Gemstone gem = new Gemstone("§?? Perfect Jasper", "PERFECT_JASPER_GEM", null, GemstoneType.Jasper, GemState.Perfect, "https://textures.minecraft.net/texture/263f991b8e038e46b8ed7632f44ca2e30c15f42977070a8c8d8728e3fc04fc7c");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}

//Topaz Gemstone
public static ItemStack RoughTopaz() {
	Gemstone gem = new Gemstone("✧ Rough Topaz", "ROUGH_TOPAZ_GEM", null, GemstoneType.Topaz, GemState.Rough, "https://textures.minecraft.net/texture/3fd960722ec29c66716ae5ca97b9b6b2628984e1d6f9d2592cd089914206a1b");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawedTopaz() {
	Gemstone gem = new Gemstone("✧ Flawed Topaz", "FLAWED_TOPAZ_GEM", null, GemstoneType.Topaz, GemState.Flawed, "https://textures.minecraft.net/texture/b6392773d114be30aeb3c09c90cbe691ffeaceb399b530fe6fb53ddc0ced3714");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FineTopaz() {
	Gemstone gem = new Gemstone("✧ Fine Topaz", "FINE_TOPAZ_GEM", null, GemstoneType.Topaz, GemState.Fine, "https://textures.minecraft.net/texture/92cb6e51c461e7359526bea5e06209cddde7c6469a819f3405cf0a038c159502");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack FlawlessTopaz() {
	Gemstone gem = new Gemstone("✧ Flawless Topaz", "FLAWLESS_TOPAZ_GEM", null, GemstoneType.Topaz, GemState.Flawless, "https://textures.minecraft.net/texture/b6392773d114be30aeb3c09c90cbe691ffeaceb399b530fe6fb53ddc0ced3714");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}
public static ItemStack PerfectTopaz() {
	Gemstone gem = new Gemstone("✧ Perfect Topaz", "PERFECT_TOPAZ_GEM", null, GemstoneType.Topaz, GemState.Perfect, "https://textures.minecraft.net/texture/3da6ecdcbc3fe355ca0611192a3fbd35dd5635d5fcdf3fbc79ed2bc1f4a017fe");
	SkyblockItems.put(gem.itemID, gem);
	return gem.getRawItemStack();
}

public static ItemStack Zoom() {
	ItemManager manager = new ItemManager("Zoom", "ZOOM", ItemType.Pickaxe, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 3000, 0, 0, 0, null, null, null, null, 0, 0, 0, 0, Material.WOODEN_PICKAXE, ItemRarity.SPECIAL);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
}


public static ItemStack TestAccessory() {
	
	ItemManager manager = new ItemManager("Test Accesory","ADMIN_ACCESORY", ItemType.Accessory,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1,1,1,1,1, null, null, null, null, 0, 0, 0f, 0, Material.NETHER_STAR,ItemRarity.SPECIAL);
	manager.addSlot(new GemstoneSlot(SlotType.Universal));manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Defensive));manager.addSlot(new GemstoneSlot(SlotType.Mining));manager.addSlot(new GemstoneSlot(SlotType.Offensive));manager.addSlot(new GemstoneSlot(SlotType.Universal));manager.addSlot(new GemstoneSlot(SlotType.Universal));
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
	

}
public static ItemStack RoyalPigeon() {
	ArrayList<String> abilitylore = new ArrayList<>();
	abilitylore.add("§7Reach out to the King and open");
	abilitylore.add("§7the Commissions menu");
	ItemManager manager = new ItemManager("Royal Pigeon","ROYAL_PIGEON", ItemType.Non,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Seek The King", "OPEN_COMMISSIONS", abilitylore, 0, 5, 0f, 0, ItemRarity.LEGENDARY,"http://textures.minecraft.net/texture/b7ea4c017e3456cf09a5c263f34d3cc5f41577b74d60f6f8196c60e07f8c5a96" );
	manager.setAbility(new SeekTheKing(), AbilityType.RightClick);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
	

}
public static ItemStack InsaneDisc() {
	ArrayList<String> abilitylore = new ArrayList<>();
	abilitylore.add("§7Just Yibe~");

	ItemManager manager = new ItemManager("Insane Disc","INSANE_DISC", ItemType.Non,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Play Music", "PLAY_MUSIC", abilitylore, 0, 5, 0f, 0, Material.MUSIC_DISC_BLOCKS ,ItemRarity.SPECIAL);
	manager.setAbility(new SeekTheKing(), AbilityType.RightClick);
	SkyblockItems.put(manager.itemID, manager);
	return manager.getRawItemStack();
	

}

public static void Chopstick() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§7Requested by 1mod2#3872");
	ArrayList<String> abilityLore = new ArrayList<>();
	abilityLore.add("§7Shot §a2 §7Arrow after 5 hits,");
	abilityLore.add("§7in the direction of the §a2 nearest");
	abilityLore.add("§7Enimies");
	ItemManager manager = new ItemManager("Chopstick", "CHOPSTICK", ItemType.Sword, 300, 0, 0, 0, 0, 70, -50, 75, 0, 0, 0, 0, 0, 0, 0, 0, lore, "United Chopstick", "CHOPSTICK", abilityLore, 0, 0, 0, 0, Material.STICK, ItemRarity.EPIC);
	manager.setAbility(new UntiedChopstick(), AbilityType.EntityHit);
	SkyblockItems.put(manager.itemID, manager);
}


public static void TestHelmet() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Is just for debuging...");
	ItemManager manager = new ItemManager("Test Helmet","TEST_HELMET", ItemType.Helmet,20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Debuging", "debuglol", abiliyLore, 0, 0, 0, 0, Material.LEATHER_HELMET, ItemRarity.SPECIAL);
	manager.setFullSetBonus(Bonuses.TestBonus);
	SkyblockItems.put(manager.itemID, manager);
}
public static void TestChestplate() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Is just for debuging...");
	ItemManager manager = new ItemManager("Test Chestplate","TEST_CHESTPLATE", ItemType.Chestplate,20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Debuging", "debuglol", abiliyLore, 0, 0, 0, 0, Material.LEATHER_CHESTPLATE, ItemRarity.SPECIAL);
	manager.setFullSetBonus(Bonuses.TestBonus);
	SkyblockItems.put(manager.itemID, manager);
}
public static void TestLeggings() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Is just for debuging...");
	ItemManager manager = new ItemManager("Test Leggings","TEST_LEGGINGS", ItemType.Leggings,20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Debuging", "debuglol", abiliyLore, 0, 0, 0, 0, Material.LEATHER_LEGGINGS, ItemRarity.SPECIAL);
	manager.setFullSetBonus(Bonuses.TestBonus);
	SkyblockItems.put(manager.itemID, manager);
}
public static void TestBoots() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Is just for debuging...");
	ItemManager manager = new ItemManager("Test Boots","TEST_BOOTS", ItemType.Boots,20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Debuging", "debuglol", abiliyLore, 0, 0, 0, 0, Material.LEATHER_BOOTS, ItemRarity.SPECIAL);
	manager.setFullSetBonus(Bonuses.TestBonus);
	SkyblockItems.put(manager.itemID, manager);
}

public static void ReaperMask() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7While wearing:");
	abiliyLore.add("§7- 2x healing from all sources");
	abiliyLore.add("§7- 2x Zombies from Reaper Scythe");
	abiliyLore.add("§7- Allows you to store and");
	abiliyLore.add("§7summon an additional §e2 §7souls");
	abiliyLore.add("§7in their necromancer weapon.");
	abiliyLore.add("§7- Upgrades Zombie Armor");
	abiliyLore.add("§7effect to trigger on all");
	abiliyLore.add("§7hits.");
	ItemManager manager = new ItemManager("Reaper Mask","REAPER_MASK", ItemType.Helmet,0, 150, 100, 100, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Disgusting Healing", "Disgusting Healing", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/d8bee23b5c726ae8e3d021e8b4f7525619ab102a4e04be983b61414349aaac67");
	manager.setFullSetBonus(Bonuses.DisgustingHealing);
	manager.addSlot(new GemstoneSlot(SlotType.Ruby));
	manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
	SkyblockItems.put(manager.itemID, manager);
}


public static void CrimsonHelmet() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7For every melee kill gain §c1 §7stack");
	abiliyLore.add("§7of §6Dominus§7.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Each §6Dominus §7stack grants §e0.1");
	abiliyLore.add("§7melee range.");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks also §dswipe §7in a");
	abiliyLore.add("§7random direction hitting every enemy");
	abiliyLore.add("§7in the path of swipe.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c%time%s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Crimson Helmet","CRIMSON_HELMET", ItemType.Helmet,0, 160, 50, 15, 0, 30, 0, 20, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/5051c83d9ebf69013f1ec8c9efc979ec2d925a921cc877ff64abe09aadd2f6cc");
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	AbilityLore lore = new AbilityLore(abiliyLore);
	lore.addPlaceholder("%time%", new UpdateFlag() {
		@Override
		public String getReplaceValue(SkyblockPlayer player, ItemStack item) {
			if(player == null)
				return "4";
			if(player.bonusAmounts == null)
				return "4";
			if(player.bonusAmounts.get(Bonuses.Dominus) == null)
				return "4";
			int pieces = player.bonusAmounts.get(Bonuses.Dominus);

			if(pieces == 3)
				return "7";
			if(pieces >= 4)
				return "10";

			return "4";
		}
	});
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Hot Crimson Helmet","HOT_CRIMSON_HELMET", ItemType.Helmet,0, 202, 63, 19, 0, 38, 0, 25, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/bbe6d66770a61bf56e6d4b476922b1c3b3dc9f78a26e56b36cd965b7ab20b417");
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Burning Crimson Helmet","BURNING_CRIMSON_HELMET", ItemType.Helmet,0, 254, 79, 24, 0, 48, 0, 32, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/eb034a5d97c24fe0ec902bd02fec52a09d917366be060c51f9a1a276b284a9d7");
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Fiery Crimson Helmet","FIERY_CRIMSON_HELMET", ItemType.Helmet,0, 320, 100, 30, 0, 60, 0, 40, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/91f0c7afff1782465d8cdb5eba261b65423a7a0712ee3a4c572c33f94c68c55");
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Infernal Crimson Helmet","INFERNAL_CRIMSON_HELMET", ItemType.Helmet,0, 403, 126, 38, 0, 76, 0, 50, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/507d6bf7611190ed9c580d8c87c296059216230c9501eec6359e0d60ec84758e");
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
}

public static void CrimsonChestplate() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7For every melee kill gain §c1 §7stack");
	abiliyLore.add("§7of §6Dominus§7.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Each §6Dominus §7stack grants §e0.1");
	abiliyLore.add("§7melee range.");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks also §dswipe §7in a");
	abiliyLore.add("§7random direction hitting every enemy");
	abiliyLore.add("§7in the path of swipe.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c%time%s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Crimson Chestplate","CRIMSON_CHESTPLATE", ItemType.Chestplate,0, 230, 65, 5, 0, 30, 0, 20, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFF6F0C),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	AbilityLore lore = new AbilityLore(abiliyLore);
	lore.addPlaceholder("%time%", new UpdateFlag() {
		@Override
		public String getReplaceValue(SkyblockPlayer player, ItemStack item) {
			if(player == null)
				return "4";
			if(player.bonusAmounts == null)
				 return "4";
			if(player.bonusAmounts.get(Bonuses.Dominus) == null)
				return "4";
			int pieces = player.bonusAmounts.get(Bonuses.Dominus);

			if(pieces == 3)
				return "7";
			if(pieces >= 4)
				return "10";

			return "4";
		}
	});
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Hot Crimson Chestplate","HOT_CRIMSON_CHESTPLATE", ItemType.Chestplate,0, 290, 82, 6, 0, 38, 0, 25, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFF6F0C),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Burning Crimson Chestplate","BURNING_CRIMSON_CHESTPLATE", ItemType.Chestplate,0, 365, 103, 8, 0, 48, 0, 32, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFF6F0C),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Fiery Crimson Chestplate","FIERY_CRIMSON_CHESTPLATE", ItemType.Chestplate,0, 460, 130, 10, 0, 60, 0, 40, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFF6F0C),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Infernal Crimson Chestplate","INFERNAL_CRIMSON_CHESTPLATE", ItemType.Chestplate,0,
			580, 164, 13, 0, 76, 0, 50, 0, 0, 0,0,0,0,0,0, null,
            "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFF6F0C),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
}

public static void CrimsonLeggings() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7For every melee kill gain §c1 §7stack");
	abiliyLore.add("§7of §6Dominus§7.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Each §6Dominus §7stack grants §e0.1");
	abiliyLore.add("§7melee range.");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks also §dswipe §7in a");
	abiliyLore.add("§7random direction hitting every enemy");
	abiliyLore.add("§7in the path of swipe.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c%time%s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Crimson Leggings","CRIMSON_LEGGINGS", ItemType.Leggings,0, 205, 55, 5, 0, 30, 0, 20, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_LEGGINGS, Color.fromRGB(0xE66105),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	AbilityLore lore = new AbilityLore(abiliyLore);
	lore.addPlaceholder("%time%", new UpdateFlag() {
		@Override
		public String getReplaceValue(SkyblockPlayer player, ItemStack item) {
			if(player == null)
				return "4";
			if(player.bonusAmounts == null)
				return "4";
			if(player.bonusAmounts.get(Bonuses.Dominus) == null)
				return "4";
			int pieces = player.bonusAmounts.get(Bonuses.Dominus);

			if(pieces == 3)
				return "7";
			if(pieces >= 4)
				return "10";

			return "4";
		}
	});
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	
	manager = new ItemManager("Hot Crimson Leggings","HOT_CRIMSON_LEGGINGS", ItemType.Leggings,0, 258, 69, 6, 0, 38, 0, 25, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_LEGGINGS, Color.fromRGB(0xE66105),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Burning Crimson Leggings","BURNING_CRIMSON_LEGGINGS", ItemType.Leggings,0, 325, 87, 8, 0, 48, 0, 32, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_LEGGINGS, Color.fromRGB(0xE66105),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Fiery Crimson Leggings","FIERY_CRIMSON_LEGGINGS", ItemType.Leggings,0, 410, 110, 10, 0, 60, 0, 40, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_LEGGINGS, Color.fromRGB(0xE66105),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Infernal Crimson Leggings","INFERNAL_CRIMSON_LEGGINGS", ItemType.Leggings,0, 517, 139, 13, 0, 76, 0, 50, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_LEGGINGS, Color.fromRGB(0xE66105),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
}

public static void CrimsonBoots() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7For every melee kill gain §c1 §7stack");
	abiliyLore.add("§7of §6Dominus§7.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Each §6Dominus §7stack grants §e0.1");
	abiliyLore.add("§7melee range.");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks also §dswipe §7in a");
	abiliyLore.add("§7random direction hitting every enemy");
	abiliyLore.add("§7in the path of swipe.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c%time%s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Crimson Boots","CRIMSON_BOOTS", ItemType.Boots,0, 130, 40, 5, 0, 30, 0, 20, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_BOOTS, Color.fromRGB(0xE65300),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	AbilityLore lore = new AbilityLore(abiliyLore);
	lore.addPlaceholder("%time%", new UpdateFlag() {
		@Override
		public String getReplaceValue(SkyblockPlayer player, ItemStack item) {
			if(player == null)
				return "4";
			if(player.bonusAmounts == null)
				return "4";
			if(player.bonusAmounts.get(Bonuses.Dominus) == null)
				return "4";
			int pieces = player.bonusAmounts.get(Bonuses.Dominus);

			if(pieces == 3)
				return "7";
			if(pieces >= 4)
				return "10";

			return "4";
		}
	});
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Hot Crimson Boots","HOT_CRIMSON_BOOTS", ItemType.Boots,0, 164, 50, 6, 0, 38, 0, 25, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_BOOTS, Color.fromRGB(0xE65300),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Burning Crimson Boots","BURNING_CRIMSON_BOOTS", ItemType.Boots,0, 206, 64, 8, 0, 48, 0, 32, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_BOOTS, Color.fromRGB(0xE65300),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Fiery Crimson Boots","FIERY_CRIMSON_BOOTS", ItemType.Boots,0, 260, 80, 10, 0, 60, 0, 40, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_BOOTS, Color.fromRGB(0xE65300),ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
	
	manager = new ItemManager("Infernal Crimson Boots","INFERNAL_CRIMSON_BOOTS", ItemType.Boots,0, 328, 101, 13, 0, 76, 0, 50, 0, 0, 0,0,0,0,0,0, null, "Dominus", "Dominus", abiliyLore, 0, 0, 0, 0,   Material.LEATHER_BOOTS, Color.fromRGB(0xE65300),ItemRarity.LEGENDARY);

	manager.setFullSetBonus(Bonuses.Dominus, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setAbilityLore(lore);
	SkyblockItems.put(manager.itemID, manager);
}

public static void TerrorHelmet() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Hitting enimies grants §c1 §7stack of");
	abiliyLore.add("§6Hydra Strike §?§§7.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Each §6Hydra Strike §?§ §7stack makes your");
	abiliyLore.add("§7arrows fly §c40% §7faster, and ");
	abiliyLore.add("§7deal §c+20% §7more damage.");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks your bow fires §d2 ");
	abiliyLore.add("§7extra arrows");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c4s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Terror Helmet","TERROR_HELMET", ItemType.Helmet,0, 160, 50, 15, 12, 0, 0, 50, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/52af8833697c81b46e83c8f1895266e606efbb3a59f1c3b4ca2816da2bcfa9d6");
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Hot Terror Helmet","HOT_TERROR_HELMET", ItemType.Helmet,0, 202, 63, 19, 15, 0, 0, 63, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/bdea30e305483713ac9a5295ae698d4109766c9ae2bc744ac58f6bb4cf93a9f1");
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Burning Terror Helmet","BURNING_TERROR_HELMET", ItemType.Helmet,0, 254, 79, 24, 19, 0, 0, 79, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/4482ec351f353445775b439ebc909d5702732f8bae9d32b0b08860b3d6439061");
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Fiery Terror Helmet","FIERY_TERROR_HELMET", ItemType.Helmet,0, 320, 100, 30, 24, 0, 0, 100, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/b46902e1756a242f401d0c2567ec6481c65084af9b1aaabb9732f56cade542f3");
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Infernal Terror Helmet","INFERNAL_TERROR_HELMET", ItemType.Helmet,0, 403, 126, 38, 30, 0, 0, 126, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/4eadf2954f1629d793d7f467181c449e18d89aa4941bec3ea5211e5909bb567");
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
}

public static void TerrorChestplate() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Hitting enimies grants §c1 §7stack of");
	abiliyLore.add("§6Hydra Strike §?§§7.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Each §6Hydra Strike §?§ §7stack makes your");
	abiliyLore.add("§7arrows fly §c40% §7faster, and ");
	abiliyLore.add("§7deal §c+20% §7more damage.");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks your bow fires §d2 ");
	abiliyLore.add("§7extra arrows");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c4s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Terror Chestplate","TERROR_CHESTPLATE", ItemType.Chestplate,0, 230, 65, 5, 12, 0, 0, 50, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_CHESTPLATE,Color.fromRGB(0x3E05AF) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Hot Terror Chestplate","HOT_TERROR_CHESTPLATE", ItemType.Chestplate,0, 290, 82, 6, 15, 0, 0, 63, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_CHESTPLATE,Color.fromRGB(0x3E05AF) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Burning Terror Chestplate","BURNING_TERROR_CHESTPLATE", ItemType.Chestplate,0, 365, 103, 8, 19, 0, 0, 79, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_CHESTPLATE,Color.fromRGB(0x3E05AF) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Fiery Terror Chestplate","FIERY_TERROR_CHESTPLATE", ItemType.Chestplate,0, 460, 130, 10, 24, 0, 0, 100, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_CHESTPLATE,Color.fromRGB(0x3E05AF) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Infernal Terror Chestplate","INFERNAL_TERROR_CHESTPLATE", ItemType.Chestplate,0, 580, 164, 13, 30, 0, 0, 126, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_CHESTPLATE,Color.fromRGB(0x3E05AF) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	
}
public static void TerrorLeggings() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Hitting enimies grants §c1 §7stack of");
	abiliyLore.add("§6Hydra Strike §?§§7.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Each §6Hydra Strike §?§ §7stack makes your");
	abiliyLore.add("§7arrows fly §c40% §7faster, and ");
	abiliyLore.add("§7deal §c+20% §7more damage.");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks your bow fires §d2 ");
	abiliyLore.add("§7extra arrows");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c4s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Terror Leggings","TERROR_LEGGINGS", ItemType.Leggings,0, 205, 55, 5, 12, 0, 0, 50, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_LEGGINGS,Color.fromRGB(0x5D23D1) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Hot Terror Leggings","HOT_TERROR_LEGGINGS", ItemType.Leggings,0, 258, 69, 6, 15, 0, 0, 63, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_LEGGINGS,Color.fromRGB(0x5D23D1) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Burning Terror Leggings","BURNING_TERROR_LEGGINGS", ItemType.Leggings,0, 325, 87, 8, 19, 0, 0, 79, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_LEGGINGS,Color.fromRGB(0x5D23D1) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Fiery Terror Leggings","FIERY_TERROR_LEGGINGS", ItemType.Leggings,0, 410, 110, 10, 24, 0, 0, 100, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_LEGGINGS,Color.fromRGB(0x5D23D1) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Infernal Terror Leggings","INFERNAL_TERROR_LEGGINGS", ItemType.Leggings,0, 517, 139, 13, 30, 0, 0, 126, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_LEGGINGS,Color.fromRGB(0x5D23D1) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
}

public static void TerrorBoots() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Hitting enimies grants §c1 §7stack of");
	abiliyLore.add("§6Hydra Strike §?§§7.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Each §6Hydra Strike §?§ §7stack makes your");
	abiliyLore.add("§7arrows fly §c40% §7faster, and ");
	abiliyLore.add("§7deal §c+20% §7more damage.");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks your bow fires §d2 ");
	abiliyLore.add("§7extra arrows");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c4s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Terror Boots","TERROR_BOOTS", ItemType.Boots,0, 130, 40, 5, 12, 0, 0, 50, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_BOOTS,Color.fromRGB(0x7C44EC) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Hot Terror Boots","HOT_TERROR_BOOTS", ItemType.Boots,0, 164, 50, 6, 15, 0, 0, 63, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_BOOTS,Color.fromRGB(0x7C44EC) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Burning Terror Boots","BURNING_TERROR_BOOTS", ItemType.Boots,0, 206, 64, 8, 19, 0, 0, 79, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_BOOTS,Color.fromRGB(0x7C44EC) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Fiery Terror Boots","FIERY_TERROR_BOOTS", ItemType.Boots,0, 260, 80, 10, 24, 0, 0, 100, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_BOOTS,Color.fromRGB(0x7C44EC) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Infernal Terror Boots","INFERNAL_TERROR_BOOTS", ItemType.Boots,0, 328, 101, 13, 30, 0, 0, 126, 0, 0, 0,0,0,0,0,0, null, "Hydra Strike", "HydraStrike", abiliyLore, 0, 0, 0, 0, Material.LEATHER_BOOTS,Color.fromRGB(0x7C44EC) ,  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.HydraStrike, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
}

public static void AuroraStaff() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§7Every being is sensible to");
	lore.add("§7specific Runes, in particular to");
	lore.add("§73 categories of Runes: Mediator,");
	lore.add("§7Defender and Virtuoso. Using the");
	lore.add("§7proper Runic Spell on enemies");
	lore.add("§7can have great benefits.");
	ItemManager manager = new ItemManager("Aurora Staff","RUNIC_STAFF", ItemType.Sword,20, 0, 0, 250, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, lore, "Rune Swap", "runeswap", new ArrayList<>(), 0, 0, 0, 0, Material.BLAZE_ROD ,  ItemRarity.LEGENDARY);
	manager.customDataContainer.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
	manager.setAbility(new AuroraStaffLeftClick(), AbilityType.LeftClick);
	ArrayList<String> abilityLore = new ArrayList<>();
	abilityLore.add("§7Fires a beam of runic energy and");
	abilityLore.add("§7hits the first enemy in its");
	abilityLore.add("§7path. The more distance");
	abilityLore.add("§7travelled the less damage it");
	abilityLore.add("§7deals.");
	manager.set2Ability("Arcane Zap", new ArcaneZap(), AbilityType.RightClick, abilityLore, 10, 1);
	manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
	manager.addSlot(new GemstoneSlot(SlotType.Sapphire));
	SkyblockItems.put(manager.itemID, manager);
}

public static void AuroraHelmet() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Gives you the ability to see the runic");
	abiliyLore.add("§7affinity of enimies.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Using the proper §bRunic Spells §fof");
	abiliyLore.add("§7the §6Aurora Staff §7grants 1 stack of");
	abiliyLore.add("§6Arcane Vision Ѫ§f.");
	abiliyLore.add(" ");
	abiliyLore.add("§7each §6Arcane Vision Ѫ §7stack grants");
	abiliyLore.add("§7you §c+2% §7damage on your next Runic");
	abiliyLore.add("§7Spell");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks the §6Aurora Staff");
	abiliyLore.add("§7spells explode on hit.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c4s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Aurora Helmet","AURORA_HELMET", ItemType.Helmet,0, 160, 50, 190, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/763d2fe93881b4f26cbe1dd3b09da7cc48dbcdc568d19852ad635d5d16859611");
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Hot Aurora Helmet","HOT_AURORA_HELMET", ItemType.Helmet,0, 202, 63, 239, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/30b6e2d43ee24c9a246a83515f9b7414846f315ad954400c38ca65cdf08e919e");
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Burning Aurora Helmet","BURNRNG_AURORA_HELMET", ItemType.Helmet,0, 254, 79, 302, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/f886b484b750c367107892586930a5e50cd57e9be843bd3db7eb217cb782f0a1");
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Fiery Aurora Helmet","FIERY_AURORA_HELMET", ItemType.Helmet,0, 320, 100, 380, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/1855aabafa461d35b864291d494ca36a2f46307ceadad3a28774054f86788e8");
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Infernal Aurora Helmet","INFERNAL_AURORA_HELMET", ItemType.Helmet,0, 403, 129, 479, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/2cc3ea5345c288c24f1aa7dd8fc63295597d3fb374e314f605640b8dea06fe3f");
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
}
public static void AuroraChestplate() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Gives you the ability to see the runic");
	abiliyLore.add("§7affinity of enimies.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Using the proper §bRunic Spells §fof");
	abiliyLore.add("§7the §6Aurora Staff §7grants 1 stack of");
	abiliyLore.add("§6Arcane Vision Ѫ§f.");
	abiliyLore.add(" ");
	abiliyLore.add("§7each §6Arcane Vision Ѫ §7stack grants");
	abiliyLore.add("§7you §c+2% §7damage on your next Runic");
	abiliyLore.add("§7Spell");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks the §6Aurora Staff");
	abiliyLore.add("§7spells explode on hit.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c4s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Aurora Chestplate","AURORA_CHESTPLATE", ItemType.Chestplate,0, 230, 65, 125, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_CHESTPLATE, Color.fromRGB(0x2841F1),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Hot Aurora Chestplate","HOT_AURORA_CHESTPLATE", ItemType.Chestplate,0, 290, 82, 158, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_CHESTPLATE, Color.fromRGB(0x2841F1),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Burning Aurora Chestplate","BURNING_AURORA_CHESTPLATE", ItemType.Chestplate,0, 365, 103, 198, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_CHESTPLATE, Color.fromRGB(0x2841F1),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Fiery Aurora Chestplate","FIERY_AURORA_CHESTPLATE", ItemType.Chestplate,0, 460, 130, 250, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_CHESTPLATE, Color.fromRGB(0x2841F1),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Infernal Aurora Chestplate","INFERNAL_AURORA_CHESTPLATE", ItemType.Chestplate,0, 580, 164, 315, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_CHESTPLATE, Color.fromRGB(0x2841F1),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
}
public static void AuroraLeggings() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Gives you the ability to see the runic");
	abiliyLore.add("§7affinity of enimies.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Using the proper §bRunic Spells §fof");
	abiliyLore.add("§7the §6Aurora Staff §7grants 1 stack of");
	abiliyLore.add("§6Arcane Vision Ѫ§f.");
	abiliyLore.add(" ");
	abiliyLore.add("§7each §6Arcane Vision Ѫ §7stack grants");
	abiliyLore.add("§7you §c+2% §7damage on your next Runic");
	abiliyLore.add("§7Spell");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks the §6Aurora Staff");
	abiliyLore.add("§7spells explode on hit.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c4s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Aurora Leggings","AURORA_LEGGINGS", ItemType.Leggings,0, 205, 55, 125, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_LEGGINGS, Color.fromRGB(0x3F56FB),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Hot Aurora Leggings","HOT_AURORA_LEGGINGS", ItemType.Leggings,0, 258, 69, 158, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_LEGGINGS, Color.fromRGB(0x3F56FB),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Burning Aurora Leggings","BURNING_AURORA_LEGGINGS", ItemType.Leggings,0, 325, 87, 198, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_LEGGINGS, Color.fromRGB(0x3F56FB),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Fiery Aurora Leggings","FIERY_AURORA_LEGGINGS", ItemType.Leggings,0, 410, 110, 250, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_LEGGINGS, Color.fromRGB(0x3F56FB),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Infernal Aurora Leggings","INFERNAL_AURORA_LEGGINGS", ItemType.Leggings,0, 517, 139, 315, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_LEGGINGS, Color.fromRGB(0x3F56FB),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
}
public static void AuroraBoots() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Gives you the ability to see the runic");
	abiliyLore.add("§7affinity of enimies.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Using the proper §bRunic Spells §fof");
	abiliyLore.add("§7the §6Aurora Staff §7grants 1 stack of");
	abiliyLore.add("§6Arcane Vision Ѫ§f.");
	abiliyLore.add(" ");
	abiliyLore.add("§7each §6Arcane Vision Ѫ §7stack grants");
	abiliyLore.add("§7you §c+2% §7damage on your next Runic");
	abiliyLore.add("§7Spell");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks the §6Aurora Staff");
	abiliyLore.add("§7spells explode on hit.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c4s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Aurora Boots","AURORA_BOOTS", ItemType.Boots,0, 130, 40, 125, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_BOOTS, Color.fromRGB(0x6184FC),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Hot Aurora Boots","HOT_AURORA_BOOTS", ItemType.Boots,0, 164, 50, 158, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_BOOTS, Color.fromRGB(0x6184FC),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Burning Aurora Boots","BURNING_AURORA_BOOTS", ItemType.Boots,0, 206, 64, 198, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_BOOTS, Color.fromRGB(0x6184FC),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Fiery Aurora Boots","FIERY_AURORA_BOOTS", ItemType.Boots,0, 260, 80, 250, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_BOOTS, Color.fromRGB(0x6184FC),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Infernal Aurora Boots","INFERNAL_AURORA_BOOTS", ItemType.Boots,0, 328, 101, 315, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, "Arcane Vision", "ArcaneVision", abiliyLore, 0, 0, 0, 0,Material.LEATHER_BOOTS, Color.fromRGB(0x6184FC),  ItemRarity.LEGENDARY);
	manager.setFullSetBonus(Bonuses.ArcaneVision, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	SkyblockItems.put(manager.itemID, manager);
}

public static void FervorHelmet() {
	ArrayList<String> abiliyLore = new ArrayList<>();
	abiliyLore.add("§7Gives you the ability to see the runic");
	abiliyLore.add("§7affinity of enimies.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Using the proper §bRunic Spells §fof");
	abiliyLore.add("§7the §6Aurora Staff §7grants 1 stack of");
	abiliyLore.add("§6Arcane Vision Ѫ§f.");
	abiliyLore.add(" ");
	abiliyLore.add("§7each §6Arcane Vision Ѫ §7stack grants");
	abiliyLore.add("§7you §c+2% §7damage on your next Runic");
	abiliyLore.add("§7Spell");
	abiliyLore.add(" ");
	abiliyLore.add("§7At §c10 §7stacks the §6Aurora Staff");
	abiliyLore.add("§7spells explode on hit.");
	abiliyLore.add(" ");
	abiliyLore.add("§7Lose 1 stack after §c4s §7of not");
	abiliyLore.add("§7gaining a stack.");
	ItemManager manager = new ItemManager("Fervor Helmet", "FERVOR_HELMET", ItemType.Helmet, null, "Fervor", "fervor", abiliyLore, 0, 0, 0, 0, ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/bc6e137e2a6a501dfc854ca900b5b681227dea4d5a9616849a3a68f09c6dc327");
	manager.setFullSetBonus(Bonuses.Fervor, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setStat(Stats.Health, 180);
	manager.setStat(Stats.Defense, 100);
	manager.setStat(Stats.Inteligence, 15);
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Hot Fervor Helmet", "HOT_FERVOR_HELMET", ItemType.Helmet, null, "Fervor", "fervor", abiliyLore, 0, 0, 0, 0, ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/3030c3217a2ac070e404cf8c21eafba3a0f6b579dbf4ec9fdc1633ad2180cf28");
	manager.setFullSetBonus(Bonuses.Fervor, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setStat(Stats.Health, 227);
	manager.setStat(Stats.Defense, 126);
	manager.setStat(Stats.Inteligence, 19);
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Burning Fervor Helmet", "BURNING_FERVOR_HELMET", ItemType.Helmet, null, "Fervor", "fervor", abiliyLore, 0, 0, 0, 0, ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/97e2d177faac867c9e49542aa4ec831b458d8537f20672014c9149e4ddba43d6");
	manager.setFullSetBonus(Bonuses.Fervor, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setStat(Stats.Health, 286);
	manager.setStat(Stats.Defense, 159);
	manager.setStat(Stats.Inteligence, 24);
	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Fiery Fervor Helmet", "FIERY_FERVOR_HELMET", ItemType.Helmet, null, "Fervor", "fervor", abiliyLore, 0, 0, 0, 0, ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/73f65fffe8fb4e70ee1c1edb8d8f5ef7bb66d0fd1807fb2568b09f1ab976d64f");
	manager.setFullSetBonus(Bonuses.Fervor, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setStat(Stats.Health, 360);
	manager.setStat(Stats.Defense, 200);
	manager.setStat(Stats.Inteligence, 30);
	SkyblockItems.put(manager.itemID, manager);
	
	
	
	manager = new ItemManager("Infernal Fervor Helmet", "INFERNAL_FERVOR_HELMET", ItemType.Helmet, null, "Fervor", "fervor", abiliyLore, 0, 0, 0, 0, ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/1d47cf6e12d12a5eb161759ba95689a9237ec111473e92a140ecab0158dfd258");
	manager.setFullSetBonus(Bonuses.Fervor, true);
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.addSlot(new GemstoneSlot(SlotType.Combat));
	manager.setStat(Stats.Health, 454);
	manager.setStat(Stats.Defense, 252);
	manager.setStat(Stats.Inteligence, 38);
	SkyblockItems.put(manager.itemID, manager);



}

public static void dctrSpaceHelmet(){
	ArrayList<String> lore = new ArrayList<String>();
	lore.add("§7§oA rare space helmet forged");
	lore.add("§7§ofrom shards of moon glass");
	ItemManager manager = new ItemManager("Dctr's Space Helmet", "DCTR_SPACE_HELM", ItemType.Helmet, lore, null, null, null, 0, 0, 0, 0, Material.RED_STAINED_GLASS, ItemRarity.SPECIAL);
	manager.setFullSetBonus(Bonuses.DctrSpaceHelmet);
	manager.customDataContainer.put("uuid", UUID.randomUUID().toString());
	manager.setEditions(true);
	SkyblockItems.put(manager.itemID, manager);
}

public static void kloonboat(){
	ArrayList<String> lore = new ArrayList<String>();
	lore.add("§7Drop this item to decrease your FPS.");
	lore.add("§8Immune to droping.");
	ItemManager manager = new ItemManager("Kloonboat", "KLOONBOAT", ItemType.Non, lore, null, null, null, 0, 0, 0, 0, Material.OAK_BOAT, ItemRarity.SPECIAL);

	manager.customDataContainer.put("uuid", UUID.randomUUID().toString());
	manager.setEditions(true);
	SkyblockItems.put(manager.itemID, manager);
}
public static void maidHelmet(){
	ArrayList<String> lore = new ArrayList<String>();
	lore.add("§7This Item does nothing.");
	lore.add("§7by §6TakoTheMaid §c§?§");
	
	ArrayList<String> abilityLore = new ArrayList<String>();

	abilityLore.add("§dFill the World with §c§lLove§r§d! Also gives");
	abilityLore.add("§dsome Ferocity I guess.");

	ItemManager manager = new ItemManager("Maid Helmet", "MAID_HELMET", ItemType.Helmet, lore, "Maid", "", abilityLore, 0, 0, 0, 0,  ItemRarity.SPECIAL,"https://textures.minecraft.net/texture/1ad7c0a04f1485c7a3ef261a48ee83b2f1aa701ab11f3fc911e0366a9b97e");
	
	for(Stats stat: Stats.values()) {
		if(stat != Stats.MagicFind){
			manager.setStat(stat, 10);
		}else
			manager.setStat(stat, 5);
	}

	manager.customDataContainer.put("uuid", UUID.randomUUID().toString());

	manager.setFullSetBonus(Bonuses.Maid);
	SkyblockItems.put(manager.itemID, manager);
}

public static void DumbshitSword() {
	ArrayList<String> abilityLore = new ArrayList<>();
	abilityLore.add("§7Kill the player that holding");
	abilityLore.add("§7it after killing a mob with");
	abilityLore.add("§7the sword");
	ItemManager manager =new ItemManager("Dumbshit Sword", "NOT_BY_ME", ItemType.Sword, null, "What the F*ck", "notbyme", abilityLore, 0, 0, 0, 0, Material.WOODEN_HOE, ItemRarity.COMMON);
	manager.setDamage(1500);
	manager.setStat(Stats.Strength, 500);
	manager.setStat(Stats.CritChance, 100);
	manager.setStat(Stats.CritDamage, 200);
	manager.setAbility(new WhatThaFuck(), AbilityType.EntityHit);
	SkyblockItems.put(manager.itemID, manager);
}

public static void AxeOfTheShredded() {
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§7Heal §c50 §7per hit.");
	lore.add("§7Deal §a+250% §7damage to Zombies.");
	lore.add("§7Receive §a25% §7less damage");
	lore.add("§7from Zombies when held.");
	ArrayList<String> abilityLore = new ArrayList<>();
	abilityLore.add("§7Throw your axe damaging all");
	abilityLore.add("§7enemies in its path dealing");
	abilityLore.add("§c10% §7melee damage.");
	abilityLore.add("§7Consecutive stack §c2x");
	abilityLore.add("§7damage but cost §92y §7mana up");
	abilityLore.add("§7to 16x");
	ItemManager manager =new ItemManager("Axe Of The Shredded", "AXE_OF_THE_SHREDDED", ItemType.Sword,  lore,"Throw", null, abilityLore, 20, 0, 0, 0, Material.DIAMOND_AXE, ItemRarity.LEGENDARY);
	manager.setDamage(140);
	manager.setStat(Stats.Strength, 115);
    manager.addSlot(new GemstoneSlot(SlotType.Jasper));
    manager.addSlot(new GemstoneSlot(SlotType.Combat));
    
    manager.setAbility(new AxeOfTheShredded(), AbilityType.RightClick);
	manager.set2Ability(null, new AxeOfTheShredded(), AbilityType.EntityHit, null, 0,0);
	SkyblockItems.put(manager.itemID, manager);
}

public static void maidChestplate(){
	ArrayList<String> lore = new ArrayList<String>();
	lore.add("§7The Power of a Maid");
	lore.add("§7is engraved in this.");
	lore.add("§7by §6TakoTheMaid §c§?§");
	ArrayList<String> abilityLore = new ArrayList<String>();

	abilityLore.add("§dFill the World with §c§lLove§r§d! Also gives");
	abilityLore.add("§dsome Ferocity I guess.");

	ItemManager manager = new ItemManager("Maid Chestplate", "MAID_CHESTPLATE", ItemType.Chestplate, lore,"Maid", "", abilityLore, 0, 0, 0, 0, Material.LEATHER_CHESTPLATE,Color.fromRGB(255, 255, 255) ,ItemRarity.SPECIAL);

	for(Stats stat: Stats.values()) {
		if(stat != Stats.MagicFind){
			manager.setStat(stat, 10);
		}else
			manager.setStat(stat, 5);
	}
	
	manager.setFullSetBonus(Bonuses.Maid);
	
	SkyblockItems.put(manager.itemID, manager);
}

public static void maidLeggings(){
	ArrayList<String> lore = new ArrayList<String>();
	lore.add("§7The Power of a Maid");
	lore.add("§7is engraved in this.");
	lore.add("§7by §6TakoTheMaid §c§?§");
	ArrayList<String> abilityLore = new ArrayList<String>();

	abilityLore.add("§dFill the World with §c§lLove§r§d! Also gives");
	abilityLore.add("§dsome Ferocity I guess.");

	ItemManager manager = new ItemManager("Maid Skirt", "MAID_LEGGINGS", ItemType.Leggings, lore, "Maid", "", abilityLore, 0, 0, 0, 0, Material.LEATHER_LEGGINGS,Color.fromRGB(0, 0, 0) ,ItemRarity.SPECIAL);

	for(Stats stat: Stats.values()) {
		if(stat != Stats.MagicFind){
			manager.setStat(stat, 10);
		}else
			manager.setStat(stat, 5);
	}
	
	manager.setFullSetBonus(Bonuses.Maid);
	
	SkyblockItems.put(manager.itemID, manager);
}

public static void maidBoots(){
	ArrayList<String> lore = new ArrayList<String>();
	lore.add("§7The Power of a Maid");
	lore.add("§7is engraved in this.");
	lore.add("§7by §6TakoTheMaid §c§?§");
	ArrayList<String> abilityLore = new ArrayList<String>();

	abilityLore.add("§dFill the World with §c§lLove§r§d! Also gives");
	abilityLore.add("§dsome Ferocity I guess.");

	ItemManager manager = new ItemManager("Maid Boots", "MAID_BOOTS", ItemType.Boots, lore, "Maid", "", abilityLore, 0, 0, 0, 0, Material.LEATHER_BOOTS,Color.fromRGB(255, 255, 255) ,ItemRarity.SPECIAL);

	for(Stats stat: Stats.values()) {
		if(stat != Stats.MagicFind){
			manager.setStat(stat, 10);
		}else
			manager.setStat(stat, 5);
	}
	
	manager.setFullSetBonus(Bonuses.Maid);
	
	SkyblockItems.put(manager.itemID, manager);
}

	public static void intellijItem(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7I'll remember the day where i ");
		lore.add("§7nearly killed my plugin");
		lore.add("§7- CarsCupcake");
		ItemManager manager = new ItemManager("Intellij User", "INTELLIJ", ItemType.Non ,lore,  null,  null,  null,0,0,0f, 0,Material.DEAD_BUSH , ItemRarity.SPECIAL);
		manager.setEditions(true);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
	}
	public static void sulphur(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7§o\"Give me some Sulphure and");
		lore.add("§7§oI'll make wand. Give me all");
		lore.add("§7§othe Sulphur and I'll conquer the");
		lore.add("§7§oworld.\" - Unnamed Mage.");
		ItemManager manager = new ItemManager("Sulphur", "SULPHUR", ItemType.Non ,lore,  null,  null,  null,0,0,0f, 0,Material.GLOWSTONE_DUST,  ItemRarity.UNCOMMON);


		SkyblockItems.put(manager.itemID, manager);
	}
	public static void enchantedSulphur(){

		ItemManager manager = new ItemManager("Enchanted Sulphur", "ENCHANTED_SULPHUR", ItemType.Non ,null,null,null,null,0,0,0f, 0,Material.GLOWSTONE_DUST,  ItemRarity.RARE);

		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
	}
	public static void moltenPowder(){

		ItemManager manager = new ItemManager("Molten Powder", "MOLTEN_POWDER", ItemType.Non ,null,null,null,null,0,0,0f, 0,Material.BLAZE_POWDER,  ItemRarity.RARE);

		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
	}
	public static void warningFlare(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Shoot the flare up in the sky");
		abilityLore.add("§7for §a3m §7buffing up to §b8");
		abilityLore.add("§7players within §a40 §7blocks");
		abilityLore.add(" ");
		abilityLore.add("§aFlare Buff: Warning Flare");
		abilityLore.add("§7Grands §a+10% §7incoming healing.");
		abilityLore.add("§7Gain §f+10❂ True Defense§7 .");
		ItemManager manager = new ItemManager("Warning Flare", "WARNING_FLARE", ItemType.Deployable ,null,"Deploy","Flare",abilityLore,300,0,0f, 0,Material.FIREWORK_ROCKET, ItemRarity.UNCOMMON);
		manager.setAbility(new WarningFlare(), AbilityType.RightClick);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
	}
	public static void infernoVertex(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Created once in a while by");
		lore.add("§7Inferno minions using");
		lore.add("§7Hypergolic-grade fuel.");

		ItemManager manager = new ItemManager("Inferno Vertex", "INFERNO_VERTEX", ItemType.Non ,lore,  null,  null,  null,0,0,0f, 0,  ItemRarity.EPIC, "https://textures.minecraft.net/texture/ae221bb733172207315acedcc5c99836a639862a3727da6d5df36b851fc191c4");


		SkyblockItems.put(manager.itemID, manager);
	}
	public static void alertFlare(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Shoot the flare up in the sky");
		abilityLore.add("§7for §a3m §7buffing up to §b8");
		abilityLore.add("§7players within §a40 §7blocks");
		abilityLore.add(" ");
		abilityLore.add("§9Flare Buff: Warning Flare");
		abilityLore.add("§7Grands §b+50% §7incoming mana regen .");
		abilityLore.add("§7Grands §a+20% §7incoming healing.");
		abilityLore.add("§7Gain §f+20❂ True Defense§7.");
		abilityLore.add("§7Grands §c+10 ⫽Ferocity.");
		ItemManager manager = new ItemManager("Alert Flare", "ALERT_FLARE", ItemType.Deployable ,null,"Deploy","Flare",abilityLore,300,0,0f, 0,Material.FIREWORK_ROCKET, ItemRarity.RARE);
		manager.setAbility(new AlertFlare(), AbilityType.RightClick);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
	}
	public static void infernoApex(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Created once in a while by");
		lore.add("§7Inferno minions using");
		lore.add("§7Hypergolic-grade fuel.");

		ItemManager manager = new ItemManager("Inferno Apex", "INFERNO_APEX", ItemType.Non ,lore,  null,  null,  null,0,0,0f, 0,  ItemRarity.LEGENDARY, "https://textures.minecraft.net/texture/ed78cc391affb80b2b35eb7364ff762d38424c07e724b99396dee921fbbc9cf");


		SkyblockItems.put(manager.itemID, manager);
	}
	public static void sosFlare(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Shoot the flare up in the sky");
		abilityLore.add("§7for §a3m §7buffing up to §b8");
		abilityLore.add("§7players within §a40 §7blocks");
		abilityLore.add(" ");
		abilityLore.add("§5Flare Buff: Sos Flare");
		abilityLore.add("§7Grands §b+125% §7incoming mana regen .");
		abilityLore.add("§7Grands §a+30% §7incoming healing.");
		abilityLore.add("§7Gain §f+25❂ True Defense§7.");
		abilityLore.add("§7Grands §c+10 ⫽Ferocity.");
		abilityLore.add("§7Grands §e+5 ⚔Bonus Attack Speed.");
		ItemManager manager = new ItemManager("Sos Flare", "SOS_FLARE", ItemType.Deployable ,null,"Deploy","Flare",abilityLore,250,0,0f, 0,Material.FIREWORK_ROCKET, ItemRarity.EPIC);
		manager.setAbility(new SosFlare(), AbilityType.RightClick);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
	}
	public static void wilsonEngineeringPlans(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Drops very rarely from the");
		lore.add("§7Inferno Demonlord");


		ItemManager manager = new ItemManager("Wilson's Engineering Plans", "WILSON_ENGINEERING_PLANS", ItemType.Non ,lore,  null,  null,  null,0,0,0f, 0,Material.PAPER , ItemRarity.LEGENDARY);
		manager.customDataContainer.put("uuid",UUID.randomUUID().toString());
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
	}
	public static void susFlare(){
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Its basicly an sos flare");
		lore.add("§7but §cSUS");
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Shoot the flare up in the sky");
		abilityLore.add("§7for §a3m §7buffing up to §b8");
		abilityLore.add("§7players within §a40 §7blocks");
		abilityLore.add(" ");
		abilityLore.add("§cFlare Buff: Sus Flare");
		abilityLore.add("§7Grands §b+125% §7incoming mana regen .");
		abilityLore.add("§7Grands §a+30% §7incoming healing.");
		abilityLore.add("§7Gain §f+25❂ True Defense§7.");
		abilityLore.add("§7Grands §c+10 ⫽Ferocity.");
		abilityLore.add("§7Grands §e+5 ⚔Bonus Attack Speed.");
		ItemManager manager = new ItemManager("Sus Flare", "SUS_FLARE", ItemType.Deployable ,lore,"Deploy","Flare",abilityLore,250,0,0f, 0,Material.FIREWORK_ROCKET, ItemRarity.SPECIAL);
		manager.setAbility(new SusFlare(), AbilityType.RightClick);
		manager.setEditions(true);
		manager.addBaseEnchantment(SkyblockEnchants.ENCHANT_GLINT, 1);
		SkyblockItems.put(manager.itemID, manager);
	}
    private static void rodOfTheSea(){
        ItemManager manager = new ItemManager("Rod Of The Sea", "ROD_OF_THE_SEA", ItemType.FishingRod,null,null,null,null,0,0,0,0,Material.FISHING_ROD,ItemRarity.LEGENDARY);
        manager.setDamage(150);
        manager.setStat(Stats.Strength, 150);
        manager.setStat(Stats.SeaCreatureChance, 7);
        manager.setStat(Stats.FishingSpeed, 110);
        SkyblockItems.put(manager.itemID, manager);
    }
	private static void hellfireRod(){
		ItemManager manager = new ItemManager("Hellfire Rod", "HELLFIRE_ROD", ItemType.FishingRod,null,null,null,null,0,0,0,0,Material.FISHING_ROD,ItemRarity.LEGENDARY);
		manager.setDamage(180);
		manager.setStat(Stats.Strength, 180);
		manager.setStat(Stats.SeaCreatureChance, 14);
		manager.setStat(Stats.FishingSpeed, 75);
		manager.setTrophyFishChance(0.2);
		manager.setNpcSellPrice(5634000);
		manager.setRodType(RodType.LavaRod);
		SkyblockItems.put(manager.itemID, manager);
	}
	private static void craysFish(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("Werfe Cray's Fish!");
		ItemManager manager = new ItemManager("Cray's Fish", "CRAYS_FISH",ItemType.Sword, null,"Throw","",abilityLore,15,0,0,0,Material.TROPICAL_FISH,ItemRarity.DIVINE);
		manager.setDamage(100);
		manager.setStat(Stats.Strength, 100);
		manager.setAbility(new CraysFish(), AbilityType.RightClick);
		SkyblockItems.put(manager.itemID,manager);

	}
	private static void bonzoStaff(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§7Bonzo Staff");
		ItemManager manager = new ItemManager("Bonzo Staff", "BONZO_STAFF",ItemType.Sword, null, "Showtime", null, abilityLore, 100,0,0,0,Material.BLAZE_ROD, ItemRarity.RARE);
		manager.setDamage(160);
		manager.setDungeonItem(true);
		manager.setStat(Stats.Inteligence, 300);
		manager.setStat(Stats.Strength, 25);
		manager.setStat(Stats.CritDamage,50);
		manager.setAbility(new Showtime(), AbilityType.RightClick);
		SkyblockItems.put(manager.itemID,manager);
	}
	private static void placeLocationSetter(){
		ArrayList<String> abilityLore = new ArrayList<>();
		abilityLore.add("§c§lPlease do not use!!");
		ItemManager manager = new ItemManager("Locations", "SETTER_LOCATIONS",ItemType.Non, null, "Mark location", null, abilityLore, 0,0,0,0,Material.STICK, ItemRarity.SPECIAL);


		manager.setAbility(new EntityLocationSetter(), AbilityType.RightClick);
		manager.set2Ability("Select Mob", new EntityLocationSelecter(),AbilityType.LeftClick,abilityLore,0,0);
		SkyblockItems.put(manager.itemID,manager);
	}

@SuppressWarnings("deprecation")
public static void initBaseItems() {
	//Swords
	ItemManager manager = new ItemManager("Wooden Sword","WOODEN_SWORD", ItemType.Sword,20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.WOODEN_SWORD, ItemRarity.COMMON);
	SkyblockItems.put("WOODEN_SWORD", manager);
	manager.setBaseItem();
	manager = new ItemManager("Golden Sword","GOLDEN_SWORD", ItemType.Sword,20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.GOLDEN_SWORD,ItemRarity.COMMON);
	manager.setBaseItem();
	SkyblockItems.put("GOLDEN_SWORD", manager);
	manager = new ItemManager("Stone Sword","STONE_SWORD", ItemType.Sword,25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.STONE_SWORD,ItemRarity.COMMON);
manager.setBaseItem();
	SkyblockItems.put("STONE_SWORD", manager);
	manager = new ItemManager("Iron Sword","IRON_SWORD", ItemType.Sword,30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0, 0,null, null, null, null, 0, 0, 0, 0, Material.IRON_SWORD,ItemRarity.COMMON);
manager.setBaseItem();

	SkyblockItems.put("IRON_SWORD", manager);
	manager = new ItemManager("Diamond Sword","DIAMOND_SWORD", ItemType.Sword,35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0, 0,null, null, null, null, 0, 0, 0, 0, Material.DIAMOND_SWORD,ItemRarity.UNCOMMON);
manager.setBaseItem();

	SkyblockItems.put("DIAMOND_SWORD", manager);
	manager = new ItemManager("Netherite Sword","NETHERITE_SWORD", ItemType.Sword,40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.NETHERITE_SWORD, ItemRarity.UNCOMMON);
manager.setBaseItem();

	SkyblockItems.put("NETHERITE_SWORD", manager);
	manager = new ItemManager("Enchanted Book","ENCHANTED_BOOK", ItemType.EnchantBook,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.ENCHANTED_BOOK,ItemRarity.COMMON);
manager.setBaseItem();

	SkyblockItems.put("ENCHANTED_BOOK", manager);
	//Pickaxes
	manager = new ItemManager("Wooden Pickaxe","WOODEN_PICKAXE", ItemType.Pickaxe,15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1,70,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.WOODEN_PICKAXE, ItemRarity.COMMON);
manager.setBaseItem();

	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Golden Pickaxe","GOLDEN_PICKAXE", ItemType.Pickaxe,15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1,250,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.GOLDEN_PICKAXE, ItemRarity.COMMON);
manager.setBaseItem();

	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Stone Pickaxe","STONE_PICKAXE", ItemType.Pickaxe,20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,2,110,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.STONE_PICKAXE, ItemRarity.COMMON);
manager.setBaseItem();

	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Iron Pickaxe","IRON_PICKAXE", ItemType.Pickaxe,25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,3,160,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.IRON_PICKAXE, ItemRarity.COMMON);
manager.setBaseItem();

	SkyblockItems.put(manager.itemID, manager);
	manager = new ItemManager("Diamond Pickaxe","DIAMOND_PICKAXE", ItemType.Pickaxe,30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,4,230,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.DIAMOND_PICKAXE, ItemRarity.UNCOMMON);
	manager.setBaseItem();

		SkyblockItems.put(manager.itemID, manager);
		manager = new ItemManager("Skull Item","LEGACY_SKULL_ITEM", ItemType.Non,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,4,230,0,0,0, null, null, null, null, 0, 0, 0, 0, Material.LEGACY_SKULL_ITEM, ItemRarity.COMMON);
		manager.setBaseItem();

			SkyblockItems.put(manager.itemID, manager);

}
public static void initAllItems() {

	for(Material mat : Material.values()) {

		String itemName = mat.toString();
		String newString = "";
        String[] minis = itemName.split("_");
        for(int i =  0; i != minis.length; i++){
            minis[i].toLowerCase();
           minis[i] = minis[i].substring(0,1).toUpperCase() + minis[i].substring(1).toLowerCase();

           if(i == minis.length -1 ){
            newString = newString +  minis[i];
           }else
           newString = newString + minis[i] + " ";

        }

        ItemManager manager = new ItemManager(newString,mat.name(), ItemType.Non,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,0, null, null, null, null, 0, 0, 0, 0, mat,ItemRarity.COMMON);
        manager.setBaseItem();
    	SkyblockItems.put(manager.itemID, manager);
		ArrayList<String> m = new ArrayList<>();
		for(String s : mat.toString().split("_"))
			m.add(s);
		if(m.contains("WOOL") || (m.contains("GLASS") && !m.contains("PANE")) || m.contains("DYE") || m.contains("TERRACOTTA")) {
			if(!m.get(0).equals("TINTED") && !m.get(0).equals("TERRACOTTA") && !m.get(0).equals("LIGHT"))
				TextTerminal.colorItems.put(mat, m.get(0));
		}



	}
}
}