package me.CarsCupcake.SkyblockRemake.Items;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import me.CarsCupcake.SkyblockRemake.*;
import me.CarsCupcake.SkyblockRemake.FishingSystem.RodType;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skill;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Configs.EditionItems;
import me.CarsCupcake.SkyblockRemake.Gemstones.Gemstone;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Pets.Pet;
import org.jetbrains.annotations.NotNull;

public class ItemManager {
	public static String pattern = "MMMMMMMMM yyyy";
	public static SimpleDateFormat df = new SimpleDateFormat(pattern);
public String name;
public  ArrayList<String> lore = new ArrayList<>();
public ArrayList<GemstoneSlot> gemstoneSlots = new ArrayList<>();
public double dmg;
public double health;
public double def;
public double mana;
public double speed;
public double strength;
public double cc;
public double cd;
public float abilitydamage;
public double ferocity;
public double magicfind;
public double breakingpower;
public double miningspeed;
public double miningfortune;
public double attackspeed;
	public double truedefense;
	public double seacreaturechance;
public String abilityName;
public String abilityID;
public final ArrayList<String> abilityLore;
public double abilityManaCost;
public int abilityCD;
public double abilitymultiplyer;
public String itemID;
public ItemType type;
public Material material;
public boolean isHead;
public String headTexture;
private boolean isSkullValue = false;
public UUID customUUID;
public float abilitymultiplier;
public int baseabilitydamage;
public double pristine;
public HashMap<Enchantment, Integer> enchants = new HashMap<>();
public HashMap<String, String> customDataContainer= new HashMap<>();
public HashMap<String, Integer> customIntContainer= new HashMap<>();
public HashMap<String, Double> customDoubleContainer= new HashMap<>();
public ItemRarity rarity;
public Color color;
public boolean isBaseItem = false;
public AbilityManager ability;
public AbilityType abilityType;

public String abilityName2;
public AbilityManager ability2;
public AbilityType abilityType2;
public ArrayList<String> abilityLore2;
public int manacost2;
public int cooldown2;

public Bonuses bonus;
public boolean TieredBonus = false;
public boolean hasEdition = false;
public double catchMult = 1;
public boolean isDungeonItem = false;
private boolean isUnstackeble = false;
private int npcSellPrice;
private AbilityLore newAbilityLore;
	public boolean abilityMana1AsPers = false;
	public boolean abilityMana2AsPers = false;
	private ArrayList<Pattern> bannerPattern = new ArrayList<>();
	private EquipmentAbility startStopAbility = null;
	private HashMap<Stats, Double> stats = new HashMap<>();
	private double trophyFishChance = 0;
	private RodType rodType = RodType.Normal;
	
public ItemManager(String name,String itemID,ItemType itemType,int dmg,int health, int def,int mana,int speed, int strength,int cc, int cd,float abilitydamage,int ferocity, int magicfind,int breakingpower, int miningspeed, int miningfortune,double pristine,int attackspeed, ArrayList<String> lore, String abilityName, String abilityID,ArrayList<String> abilityLore, int abilityManaCost, int abilityCD,float abilitymultiplyer,int baseabilitydamage, Material material, ItemRarity rarity) {
	this.dmg = dmg;
	this.name = name;
	this.itemID = itemID;
	this.type = itemType;
	this.health = health;
	this.def = def;
	this.mana = mana;
	this.speed = speed;
	this.strength = strength;
	this.cc = cc;
	this.cd = cd;
	this.abilitydamage = abilitydamage;
	this.ferocity = ferocity;
	this.magicfind = magicfind;
	this.lore = lore;
	this.abilityName = abilityName;
	this.abilityID = abilityID;
	this.abilityLore = abilityLore;
	this.abilityManaCost = abilityManaCost;
	this.abilityCD = abilityCD;
	this.baseabilitydamage = baseabilitydamage;
	this.abilitymultiplier = abilitymultiplyer;
	this.material = material;
	this.rarity = rarity;
	isHead = false;
	enchants = new HashMap<>();
	this.attackspeed = attackspeed;
	this.breakingpower = breakingpower;
	this.miningspeed = miningspeed;
	this.miningfortune = miningfortune;
	
	this.pristine = pristine;
	
	
}
public ItemManager(String name,String itemID,ItemType itemType, ArrayList<String> lore, String abilityName, String abilityID,ArrayList<String> abilityLore, double abilityManaCost, int abilityCD,float abilitymultiplyer,int baseabilitydamage, Material material, ItemRarity rarity) {
	this.name = name;
	this.itemID = itemID;
	this.type = itemType;
	this.lore = lore;
	this.abilityName = abilityName;
	this.abilityID = abilityID;
	this.abilityLore = abilityLore;
	this.abilityManaCost = abilityManaCost;
	this.abilityCD = abilityCD;
	this.baseabilitydamage = baseabilitydamage;
	this.abilitymultiplier = abilitymultiplyer;
	this.material = material;
	this.rarity = rarity;
	isHead = false;	
}
public ItemManager(String name,String itemID,ItemType itemType,int dmg,int health, int def,int mana,int speed, int strength,int cc, int cd,float abilitydamage,int ferocity, int magicfind,int breakingpower, int miningspeed, int miningfortune,double pristine, int attackspeed,ArrayList<String> lore, String abilityName, String abilityID,ArrayList<String> abilityLore, int abilityManaCost, int abilityCD,float abilitymultiplyer,int baseabilitydamage, Material material,Color color, ItemRarity rarity) {
	this.dmg = dmg;
	this.name = name;
	this.itemID = itemID;
	this.type = itemType;
	this.health = health;
	this.def = def;
	this.mana = mana;
	this.speed = speed;
	this.strength = strength;
	this.cc = cc;
	this.cd = cd;
	this.abilitydamage = abilitydamage;
	this.ferocity = ferocity;
	this.magicfind = magicfind;
	this.lore = lore;
	this.abilityName = abilityName;
	this.abilityID = abilityID;
	this.abilityLore = abilityLore;
	this.abilityManaCost = abilityManaCost;
	this.abilityCD = abilityCD;
	this.baseabilitydamage = baseabilitydamage;
	this.abilitymultiplier = abilitymultiplyer;
	this.material = material;
	this.rarity = rarity;
	this.color = color;
	isHead = false;
	enchants = new HashMap<>();
	this.attackspeed = attackspeed;
	this.breakingpower = breakingpower;
	this.miningspeed = miningspeed;
	this.miningfortune = miningfortune;
	
	this.pristine = pristine;
	
}
public ItemManager(String name,String itemID,ItemType itemType,ArrayList<String> lore, String abilityName, String abilityID,ArrayList<String> abilityLore, double abilityManaCost, int abilityCD,float abilitymultiplyer,int baseabilitydamage, Material material,Color color, ItemRarity rarity) {
	this.name = name;
	this.itemID = itemID;
	this.type = itemType;
	this.lore = lore;
	this.abilityName = abilityName;
	this.abilityID = abilityID;
	this.abilityLore = abilityLore;
	this.abilityManaCost = abilityManaCost;
	this.abilityCD = abilityCD;
	this.baseabilitydamage = baseabilitydamage;
	this.abilitymultiplier = abilitymultiplyer;
	this.material = material;
	this.rarity = rarity;
	this.color = color;
	isHead = false;
	enchants = new HashMap<>();
	
}
public ItemManager(String name,String itemID,ItemType itemType,int dmg,int health, int def,int mana,int speed, int strength,int cc, int cd,float abilitydamage,int ferocity, int magicfind,int breakingpower, int miningspeed, int miningfortune,double pristine,int attackspeed, ArrayList<String> lore, String abilityName, String abilityID,ArrayList<String> abilityLore, int abilityManaCost, int abilityCD,float abilitymultiplyer,int baseabilitydamage, ItemRarity rarity, String headTexture) {
	this.dmg = dmg;
	this.name = name;
	this.itemID = itemID;
	this.type = itemType;
	this.health = health;
	this.def = def;
	this.mana = mana;
	this.speed = speed;
	this.strength = strength;
	this.cc = cc;
	this.cd = cd;
	this.abilitydamage = abilitydamage;
	this.ferocity = ferocity;
	this.magicfind = magicfind;
	this.lore = lore;
	this.abilityName = abilityName;
	this.abilityID = abilityID;
	this.abilityLore = abilityLore;
	this.abilityManaCost = abilityManaCost;
	this.abilityCD = abilityCD;
	this.headTexture = headTexture;
	this.baseabilitydamage = baseabilitydamage;
	this.abilitymultiplier = abilitymultiplyer;
	this.rarity = rarity;
	isHead = true;
	enchants = new HashMap<>();
	this.attackspeed = attackspeed;
	this.breakingpower = breakingpower;
	this.miningspeed = miningspeed;
	this.miningfortune = miningfortune;
	
	this.pristine = pristine;
	
}
public ItemManager(String name,String itemID,ItemType itemType, ArrayList<String> lore, String abilityName, String abilityID,ArrayList<String> abilityLore, double abilityManaCost, int abilityCD,float abilitymultiplyer,int baseabilitydamage, ItemRarity rarity, String headTexture) {
	this.name = name;
	this.itemID = itemID;
	this.type = itemType;
	this.lore = lore;
	this.abilityName = abilityName;
	this.abilityID = abilityID;
	this.abilityLore = abilityLore;
	this.abilityManaCost = abilityManaCost;
	this.abilityCD = abilityCD;
	this.headTexture = headTexture;
	this.baseabilitydamage = baseabilitydamage;
	this.abilitymultiplier = abilitymultiplyer;
	this.rarity = rarity;
	isHead = true;
	enchants = new HashMap<>();
	
}
public ItemManager(String name,String itemID,ItemType itemType,int dmg,int health, int def,int mana,int speed, int strength,int cc, int cd,float abilitydamage,int ferocity, int magicfind,int breakingpower, int miningspeed, int miningfortune,double pristine,int attackspeed, ArrayList<String> lore, String abilityName, String abilityID,ArrayList<String> abilityLore, int abilityManaCost, int abilityCD,float abilitymultiplyer,int baseabilitydamage, ItemRarity rarity, String headTexture, UUID CustomUUID) {
	this.dmg = dmg;
	this.name = name;
	this.itemID = itemID;
	this.type = itemType;
	this.health = health;
	this.def = def;
	this.mana = mana;
	this.speed = speed;
	this.strength = strength;
	this.cc = cc;
	this.cd = cd;
	this.abilitydamage = abilitydamage;
	this.ferocity = ferocity;
	this.magicfind = magicfind;
	this.lore = lore;
	this.abilityName = abilityName;
	this.abilityID = abilityID;
	this.abilityLore = abilityLore;
	this.abilityManaCost = abilityManaCost;
	this.abilityCD = abilityCD;
	this.headTexture = headTexture;
	this.customUUID = CustomUUID;
	this.baseabilitydamage = baseabilitydamage;
	this.abilitymultiplier = abilitymultiplyer;
	this.rarity = rarity;
	isHead = true;
	enchants = new HashMap<>();
	this.attackspeed = attackspeed;
	this.breakingpower = breakingpower;
	this.miningspeed = miningspeed;
	this.miningfortune = miningfortune;
	
	this.pristine = pristine;
	
	
}
public ItemManager(String name,String itemID,ItemType itemType, ArrayList<String> lore, String abilityName, String abilityID,ArrayList<String> abilityLore, double abilityManaCost, int abilityCD,float abilitymultiplyer,int baseabilitydamage, ItemRarity rarity, String headTexture, UUID CustomUUID) {

	this.name = name;
	this.itemID = itemID;
	this.type = itemType;
	this.lore = lore;
	this.abilityName = abilityName;
	this.abilityID = abilityID;
	this.abilityLore = abilityLore;
	this.abilityManaCost = abilityManaCost;
	this.abilityCD = abilityCD;
	this.headTexture = headTexture;
	this.customUUID = CustomUUID;
	this.baseabilitydamage = baseabilitydamage;
	this.abilitymultiplier = abilitymultiplyer;
	this.rarity = rarity;
	isHead = true;
	enchants = new HashMap<>();
	
	
}

public void setEquipmentAbility(EquipmentAbility ability){
	startStopAbility = ability;
}
public EquipmentAbility getEquipmentAbility(){
	return startStopAbility;
}
public void setRodType(RodType type){
	rodType = type;
}
public RodType getRodType(){
	return rodType;
}


	public void addBannerPattern(Pattern pattern){
		bannerPattern.add(pattern);
	}
	public ArrayList<Pattern> getBannerPatterns(){
		return bannerPattern;
	}

public void setDungeonItem(boolean b){
	isDungeonItem = b;
}
public void setAbilityLore(AbilityLore lore){
	newAbilityLore = lore;
}
public AbilityLore getAbilityLore(){
	return newAbilityLore;
}

public void setEditions(boolean bol) {
	hasEdition = bol;
}
	public  String getAbilityHeadline(SkyblockPlayer player) {
		if(abilityType == null)
		return "§6Ability: " + abilityName + " §e§lRight Click";
		
		if(!TieredBonus)
		return "§6Ability: " + abilityName + " §e§l" + abilityType.toString().toUpperCase();
		else {
			if(player == null || player.bonusAmounts.get(bonus) == null)
			   return "§8Tiered Bonus: " + abilityName + " (0/"+bonus.getBonus(player).getMaxPieces()+")";
			else
				return ((player.bonusAmounts.get(bonus) < bonus.getBonus(player).getPieces()) ? "§8" : "§6") + "Tiered Bonus: " + abilityName + " ("+player.bonusAmounts.get(bonus)+"/"+bonus.getBonus(player).getMaxPieces()+")";
		}
	}
	public  String getAbility2Headline() {
		if(abilityType2 == null)
		return "§6Ability: " + abilityName2 + " §e§lRight Click";
		
		if(!TieredBonus)
		return "§6Ability: " + abilityName2 + " §e§l" + abilityType2.toString().toUpperCase();
		else
			
			return "§6Tiered Bonus: " + abilityName2 + " (max: 4)";
	}
	public void setBaseItem() {
		isBaseItem = true;
	}

	public void setTrophyFishChance(double d){
	trophyFishChance = d;
	}
	public double getTrophyFishChance(){
	return trophyFishChance;
	}
	public void setStat(Stats stat, double value) {
		switch(stat) {
		case AbilityDamage:
			abilitydamage = (float) value;
			break;
		case AttackSpeed:
			attackspeed = (int) value;
			break;
		case CritChance:
			cc = (int)value;
			break;
		case CritDamage:
			cd = (int)value;
			break;
		case Defense:
			def = (int)value;
			break;
		case Ferocity:
			ferocity = (int) value;
			break;
		case Health:
			health = (int)value;
			break;
		case Inteligence:
			mana = (int) value;
			break;
		case MagicFind:
			magicfind = (int) value;
			break;
		case MiningFortune:
			miningfortune = (int)value;
			break;
		case MiningSpeed:
			miningspeed = (int)value;
			break;
		case Pristine:
			pristine = (int) value;
			break;
		case Speed:
			speed = (int) value;
			break;
		case Strength:
			strength = (int) value;
			break;
			case TrueDefense:
				truedefense = (int) value;
				break;
			case SeaCreatureChance:
				seacreaturechance = value;
			break;
		default:
			stats.put(stat, value);
			break;
		
		}
	}

	public double getStat(@NotNull Stats stat){

		switch(stat) {
			case AbilityDamage:
				return abilitydamage;

			case AttackSpeed:
				return attackspeed;

			case CritChance:
				return cc;

			case CritDamage:
				return cd;

			case Defense:
				return def;

			case Ferocity:
				return ferocity;

			case Health:
				return health;

			case Inteligence:
				return mana;

			case MagicFind:
				return magicfind;

			case MiningFortune:
				return miningfortune;

			case MiningSpeed:
				return miningspeed;

			case Pristine:
				return pristine;

			case Speed:
				return speed;

			case Strength:
				return strength;

			case TrueDefense:
				return truedefense;

			case SeaCreatureChance:
				return seacreaturechance;




	}
	return 0;
	}
	public void setUnstackeble(boolean b){
	isUnstackeble = b;
	}
	public boolean isUnstackeble(){
	return isUnstackeble;
	}

	public void setBreakingPower(int value) {
		breakingpower = value;
	}
	public void setDamage(double value) {
		dmg = value;
	}
	public ArrayList<String> getLore(){
		if(lore == null)
		return new ArrayList<>();
		else
			return lore;
	}
	
	public void addSlot(GemstoneSlot slot) {
		if(gemstoneSlots.size() < 8) {
			gemstoneSlots.add(slot);
		}
	}
	public void setAbility(AbilityManager ability, AbilityType type) {
		this.ability = ability;
		abilityType = type;
	}
	public void setCatchMultiplier(double d){
	catchMult = d;
	}
	public void set2Ability(String name ,AbilityManager ability, AbilityType type, ArrayList<String> abilitylore, int manacost, int cooldown)  {
		abilityName2 = name;
		ability2 = ability;
		abilityType2 = type;
		abilityLore2 = abilitylore;
		manacost2 = manacost;
		cooldown2 = cooldown;
	}
	
	public void setFullSetBonus(Bonuses bonus) {
		this.bonus = bonus;
		abilityType = AbilityType.FullSetBonus;
		
	}
	public void setFullSetBonus(Bonuses bonus,boolean isTiered) {
		TieredBonus = isTiered;
		this.bonus = bonus;
		abilityType = AbilityType.FullSetBonus;
		
	}
	
	
	public void addBaseEnchantment(Enchantment enchant, int level) {
		enchants.put(enchant, level);
	}
	public void setIsSkullValue(boolean b){
	isSkullValue = b;
	}
	public ItemStack getRawItemStack() {
		if(!isHead) {
			
		ItemStack item = new ItemStack(material);
		
		if(color != null) {
			LeatherArmorMeta lmeta = (boolean)item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
			lmeta.setColor(color);
			item.setItemMeta(lmeta);
		}
		
		ItemMeta meta = item.getItemMeta();
		
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.addItemFlags(ItemFlag.HIDE_DYE);
			meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		
		PersistentDataContainer data = meta.getPersistentDataContainer();
		meta.setDisplayName(rarity.getPrefix() + name);
		if(enchants != null)
		enchants.forEach((enchant, level)->{
			meta.addEnchant(enchant, level, true);
		});
		data.set(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING, itemID);
		data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, type.toString());
		if(dmg != 0)
			data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, ""+dmg);
		if(health != 0)
			data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.DOUBLE, health);
		if(def != 0)
			data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE, def);
		if(mana != 0)
			data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE, mana);
		if(speed != 0)
			data.set(new NamespacedKey(Main.getMain(), "speed"), PersistentDataType.DOUBLE, speed);
		if(strength != 0)
			data.set(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE, strength);
		if(cc != 0)
			data.set(new NamespacedKey(Main.getMain(), "cc"), PersistentDataType.DOUBLE, cc);
		if(cd != 0)
			data.set(new NamespacedKey(Main.getMain(), "cd"), PersistentDataType.DOUBLE, cd);
		if(abilitydamage != 0f)
			data.set(new NamespacedKey(Main.getMain(), "abilitydamage"), PersistentDataType.FLOAT, abilitydamage);
		if(ferocity != 0)
			data.set(new NamespacedKey(Main.getMain(), "ferocity"), PersistentDataType.DOUBLE, ferocity);
		if(magicfind != 0)
			data.set(new NamespacedKey(Main.getMain(), "magicfind"), PersistentDataType.DOUBLE, magicfind);
		if(breakingpower != 0)
			data.set(new NamespacedKey(Main.getMain(), "breakingpower"), PersistentDataType.DOUBLE, breakingpower);
		if(miningspeed != 0)
			data.set(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE, miningspeed);
		if(miningfortune != 0)
			data.set(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE, miningfortune);
		if(attackspeed != 0)
			data.set(new NamespacedKey(Main.getMain(), "as"), PersistentDataType.DOUBLE, attackspeed);
		if(abilityID != null)
			data.set(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING, abilityID);

		if(truedefense != 0)
			data.set(new NamespacedKey(Main.getMain(), "truedefense"), PersistentDataType.DOUBLE, truedefense);
			if(seacreaturechance != 0)
				data.set(new NamespacedKey(Main.getMain(), "seacreaturechance"), PersistentDataType.DOUBLE, seacreaturechance);
			if(catchMult != 0)
				data.set(new NamespacedKey(Main.getMain(), "catchmult"), PersistentDataType.DOUBLE, catchMult);
		if(abilityCD != 0)
			data.set(new NamespacedKey(Main.getMain(), "abilitycd"), PersistentDataType.INTEGER, abilityCD);
		if(baseabilitydamage != 0)
			data.set(new NamespacedKey(Main.getMain(), "baseabilitydamage"), PersistentDataType.INTEGER, baseabilitydamage);
		if(abilitymultiplier != 0f)
			data.set(new NamespacedKey(Main.getMain(), "abilityscaling"), PersistentDataType.FLOAT, abilitymultiplier);
		if(pristine != 0)
			data.set(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE, pristine);
		data.set(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING, rarity.toString());
		data.set(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER, 0);
		if(isUnstackeble)
			data.set(new NamespacedKey(Main.getMain(), "uuid"), PersistentDataType.STRING, UUID.randomUUID().toString());
		for(Stats stat : stats.keySet())
			data.set(new NamespacedKey(Main.getMain(), stat.getDataName()), PersistentDataType.DOUBLE, stats.get(stat));
		
		
		ArrayList<String> lore= new ArrayList<>();
		if(this.lore != null)
		this.lore.forEach(l->{
			lore.add(l);
		});
		if(type == ItemType.Drill) {
			data.set(new NamespacedKey(Main.getMain(), "maxfuel"), PersistentDataType.INTEGER, 3000);
			data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, 3000);
		}
		if(customDataContainer != null) {
			customDataContainer.forEach((arg1, arg2) ->{
				data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.STRING, arg2);
			});
		}
		 
		lore.add(" ");
		if(abilityName != null)
		lore.add("§6Ability: " + abilityName + " §e§lRight Click");
		if(abilityLore != null)
		abilityLore.forEach(lore_item->{
			lore.add(lore_item);
		});
		if(abilityManaCost != 0)
		lore.add("§8Mana Cost §b" + abilityManaCost);
		if(abilityCD != 0) 
			lore.add("§8Ability Cooldown §e" + abilityCD);	
		lore.add("");
		lore.add(rarity.getRarityName() + type.toString());
		meta.setLore(lore);
		meta.setUnbreakable(true);
			if(customIntContainer != null) {
				customIntContainer.forEach((arg1, arg2) ->{
					data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.INTEGER, arg2);
				});
			}
		item.setItemMeta(meta);
			NBTEditor.set(item, itemID, "ExtraAtribute", "id");
			if(!bannerPattern.isEmpty()){
				BannerMeta bannerMeta = (BannerMeta) item.getItemMeta();
				for(Pattern patterns : bannerPattern)
					bannerMeta.addPattern(patterns);

				item.setItemMeta(bannerMeta);
			}
		return item;
		}else {ItemStack item;
			if(!isSkullValue)
			if(customUUID == null)
			 item = Tools.CustomHeadTexture(headTexture);
			else
			 item = Tools.CustomHeadTexture(headTexture, customUUID.toString());
			else
				item = Tools.getCustomTexturedHeadFromSkullValue(headTexture);

			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(rarity.getPrefix() + name);
			enchants.forEach((enchant, level)->{
				meta.addEnchant(enchant, level, true);
			});
			PersistentDataContainer data = meta.getPersistentDataContainer();
	
			data.set(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING, itemID);
			data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, type.toString());
			if(dmg != 0)
				data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, ""+dmg);
			if(health != 0)
				data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.DOUBLE, health);
			if(def != 0)
				data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE, def);
			if(mana != 0)
				data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE, mana);
			if(speed != 0)
				data.set(new NamespacedKey(Main.getMain(), "speed"), PersistentDataType.DOUBLE, speed);
			if(strength != 0)
				data.set(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE, strength);
			if(cc != 0)
				data.set(new NamespacedKey(Main.getMain(), "cc"), PersistentDataType.DOUBLE, cc);
			if(cd != 0)
				data.set(new NamespacedKey(Main.getMain(), "cd"), PersistentDataType.DOUBLE, cd);
			if(abilitydamage != 0)
				data.set(new NamespacedKey(Main.getMain(), "abilitydamage"), PersistentDataType.FLOAT, abilitydamage);
			if(ferocity != 0)
				data.set(new NamespacedKey(Main.getMain(), "ferocity"), PersistentDataType.DOUBLE, ferocity);
			if(magicfind != 0)
				data.set(new NamespacedKey(Main.getMain(), "magicfind"), PersistentDataType.DOUBLE, magicfind);
			if(breakingpower != 0)
				data.set(new NamespacedKey(Main.getMain(), "breakingpower"), PersistentDataType.DOUBLE, breakingpower);
			if(miningspeed != 0)
				data.set(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE, miningspeed);
			if(miningfortune != 0)
				data.set(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE, miningfortune);
			if(abilityID != null)
				data.set(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING, abilityID);

			if(abilityCD != 0)
				data.set(new NamespacedKey(Main.getMain(), "abilitycd"), PersistentDataType.INTEGER, abilityCD);
			if(baseabilitydamage != 0)
				data.set(new NamespacedKey(Main.getMain(), "baseabilitydamage"), PersistentDataType.INTEGER, baseabilitydamage);
			if(abilitymultiplier != 0)
				data.set(new NamespacedKey(Main.getMain(), "abilityscaling"), PersistentDataType.FLOAT, abilitymultiplier);
			if(pristine != 0)
				data.set(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE, pristine);
			if(truedefense != 0)
				data.set(new NamespacedKey(Main.getMain(), "truedefense"), PersistentDataType.DOUBLE, truedefense);
			if(seacreaturechance != 0)
				data.set(new NamespacedKey(Main.getMain(), "seacreaturechance"), PersistentDataType.DOUBLE, seacreaturechance);
			if(catchMult != 0)
				data.set(new NamespacedKey(Main.getMain(), "catchmult"), PersistentDataType.DOUBLE, catchMult);
			if(attackspeed != 0)
				data.set(new NamespacedKey(Main.getMain(), "as"), PersistentDataType.DOUBLE, attackspeed);
			data.set(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING, rarity.toString());
			data.set(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER, 0);
			ArrayList<String> lore= new ArrayList<>();
			if(this.lore != null)
			this.lore.forEach(l->{
				lore.add(l);
			});
			
			if(type == ItemType.Drill) {
				data.set(new NamespacedKey(Main.getMain(), "maxfuel"), PersistentDataType.INTEGER, 3000);
				data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, 3000);
			}
			if(isUnstackeble)
				data.set(new NamespacedKey(Main.getMain(), "uuid"), PersistentDataType.STRING, UUID.randomUUID().toString());
			for(Stats stat : stats.keySet())
				data.set(new NamespacedKey(Main.getMain(), stat.getDataName()), PersistentDataType.DOUBLE, stats.get(stat));
			
			lore.add(" ");
			if(abilityName != null)
			lore.add("§6Ability: " + abilityName + " §e§lRight Click");
			if(abilityLore != null)
			abilityLore.forEach(lore_item->{
				lore.add(lore_item);
			});
			if(abilityManaCost != 0)
			lore.add("§8Mana Cost §b" + abilityManaCost);
			if(abilityCD != 0) 
				lore.add("§8Ability Cooldown §a" + abilityCD + "s");
			lore.add("");
			lore.add(rarity.getRarityName() + type.toString());
			meta.setLore(lore);
			
			if(Gemstone.gemstones.containsKey(itemID)) {
				Gemstone gem = Gemstone.gemstones.get(itemID);
				
				data.set(new NamespacedKey(Main.getMain(), "QUALITY"), PersistentDataType.STRING, gem.gemState.toString());
				data.set(new NamespacedKey(Main.getMain(), "GEMTYPE"), PersistentDataType.STRING, gem.gemType.toString());
				
			}
			if(Pet.pets.containsKey(itemID)) {
				
				
				data.set(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER, 1);
				data.set(new NamespacedKey(Main.getMain(), "currxp"), PersistentDataType.DOUBLE, 0D);
				
			}
			if(customIntContainer != null) {
				customIntContainer.forEach((arg1, arg2) ->{
					data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.INTEGER, arg2);
				});
			}
			NBTEditor.set(item, itemID, "ExtraAtribute", "id");
			item.setItemMeta(meta);
			
			return item;
		}
		}
		
		
		public ItemStack createNewItemStack() {
			if(!isHead) {
				
			ItemStack item = new ItemStack(material);
			
			if(color != null) {
				LeatherArmorMeta lmeta = (boolean)item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
				lmeta.setColor(color);
				lmeta.addItemFlags(ItemFlag.HIDE_DYE);
				item.setItemMeta(lmeta);
			}
			
			ItemMeta meta = item.getItemMeta();
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
	        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

				meta.addItemFlags(ItemFlag.HIDE_DYE);
				meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			
			PersistentDataContainer data = meta.getPersistentDataContainer();
			meta.setDisplayName(rarity.getPrefix() + name);
			if(enchants != null)
			enchants.forEach((enchant, level)->{
				meta.addEnchant(enchant, level, true);
			});
			data.set(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING, itemID);
			data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, type.toString());
			if(dmg != 0)
				data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, ""+dmg);
			if(health != 0)
				data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.DOUBLE, health);
			if(def != 0)
				data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE, def);
			if(mana != 0)
				data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE, mana);
			if(speed != 0)
				data.set(new NamespacedKey(Main.getMain(), "speed"), PersistentDataType.DOUBLE, speed);
			if(strength != 0)
				data.set(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE, strength);
			if(cc != 0)
				data.set(new NamespacedKey(Main.getMain(), "cc"), PersistentDataType.DOUBLE, cc);
			if(cd != 0)
				data.set(new NamespacedKey(Main.getMain(), "cd"), PersistentDataType.DOUBLE, cd);
			if(abilitydamage != 0f)
				data.set(new NamespacedKey(Main.getMain(), "abilitydamage"), PersistentDataType.FLOAT, abilitydamage);
			if(ferocity != 0)
				data.set(new NamespacedKey(Main.getMain(), "ferocity"), PersistentDataType.DOUBLE, ferocity);
			if(magicfind != 0)
				data.set(new NamespacedKey(Main.getMain(), "magicfind"), PersistentDataType.DOUBLE, magicfind);
			if(breakingpower != 0)
				data.set(new NamespacedKey(Main.getMain(), "breakingpower"), PersistentDataType.DOUBLE, breakingpower);
			if(miningspeed != 0)
				data.set(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE, miningspeed);
			if(miningfortune != 0)
				data.set(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE, miningfortune);
			if(attackspeed != 0)
				data.set(new NamespacedKey(Main.getMain(), "as"), PersistentDataType.DOUBLE, attackspeed);
			if(truedefense != 0)
				data.set(new NamespacedKey(Main.getMain(), "truedefense"), PersistentDataType.DOUBLE, truedefense);
				if(seacreaturechance != 0)
					data.set(new NamespacedKey(Main.getMain(), "seacreaturechance"), PersistentDataType.DOUBLE, seacreaturechance);
				if(catchMult != 0)
					data.set(new NamespacedKey(Main.getMain(), "catchmult"), PersistentDataType.DOUBLE, catchMult);
			if(abilityID != null)
				data.set(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING, abilityID);

			if(abilityCD != 0)
				data.set(new NamespacedKey(Main.getMain(), "abilitycd"), PersistentDataType.INTEGER, abilityCD);
			if(baseabilitydamage != 0)
				data.set(new NamespacedKey(Main.getMain(), "baseabilitydamage"), PersistentDataType.INTEGER, baseabilitydamage);
			if(abilitymultiplier != 0f)
				data.set(new NamespacedKey(Main.getMain(), "abilityscaling"), PersistentDataType.FLOAT, abilitymultiplier);
			if(pristine != 0)
				data.set(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE, pristine);
			data.set(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING, rarity.toString());
			data.set(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER, 0);
			
			if(hasEdition) {
				
				data.set(new NamespacedKey(Main.getMain(), "from"), PersistentDataType.STRING, "-");
				data.set(new NamespacedKey(Main.getMain(), "to"), PersistentDataType.STRING, "-");
				EditionItems.reload();
				 int edition = EditionItems.get().getInt(itemID) + 1;
				 EditionItems.get().set(itemID, edition);
				 EditionItems.save();
				 EditionItems.reload();
				 data.set(new NamespacedKey(Main.getMain(), "date"), PersistentDataType.STRING, df.format(new Date()));
				
				data.set(new NamespacedKey(Main.getMain(), "editionnumber"), PersistentDataType.INTEGER, edition);
			}

				if(isUnstackeble)
					data.set(new NamespacedKey(Main.getMain(), "uuid"), PersistentDataType.STRING, UUID.randomUUID().toString());
				for(Stats stat : stats.keySet())
					data.set(new NamespacedKey(Main.getMain(), stat.getDataName()), PersistentDataType.DOUBLE, stats.get(stat));
			
			ArrayList<String> lore= new ArrayList<>();
			if(this.lore != null)
			this.lore.forEach(l->{
				lore.add(l);
			});
			if(type == ItemType.Drill) {
				data.set(new NamespacedKey(Main.getMain(), "maxfuel"), PersistentDataType.INTEGER, 3000);
				data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, 3000);
			}
			if(customDataContainer != null) {
				customDataContainer.forEach((arg1, arg2) ->{
					data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.STRING, arg2);
				});
			}
			if(customIntContainer != null) {
				customIntContainer.forEach((arg1, arg2) ->{
					data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.INTEGER, arg2);
				});
			}
			 
			lore.add(" ");
			if(abilityName != null)
			lore.add("§6Ability: " + abilityName + " §e§lRight Click");
			if(abilityLore != null)
			abilityLore.forEach(lore_item->{
				lore.add(lore_item);
			});
			if(abilityManaCost != 0)
			lore.add("§8Mana Cost §b" + abilityManaCost);
			if(abilityCD != 0) 
				lore.add("§8Ability Cooldown §e" + abilityCD);	
			lore.add("");
			lore.add(rarity.getRarityName() + type.toString());
			meta.setLore(lore);
			meta.setUnbreakable(true);
			item.setItemMeta(meta);
			if(!bannerPattern.isEmpty()){
				BannerMeta bannerMeta = (BannerMeta) item.getItemMeta();
				for(Pattern patterns : bannerPattern)
					bannerMeta.addPattern(patterns);
				item.setItemMeta(bannerMeta);
			}

				NBTEditor.set(item, itemID, "ExtraAtribute", "id");

			return item;
			}else {ItemStack item;
				if(!isSkullValue)
					if(customUUID == null)
						item = Tools.CustomHeadTexture(headTexture);
					else
						item = Tools.CustomHeadTexture(headTexture, customUUID.toString());
				else
					item = Tools.getCustomTexturedHeadFromSkullValue(headTexture);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(rarity.getPrefix() + name);
				enchants.forEach((enchant, level)->{
					meta.addEnchant(enchant, level, true);
				});
				PersistentDataContainer data = meta.getPersistentDataContainer();
		
				data.set(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING, itemID);
				data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, type.toString());
				if(dmg != 0)
					data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, ""+dmg);
				if(health != 0)
					data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.DOUBLE, health);
				if(def != 0)
					data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE, def);
				if(mana != 0)
					data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE, mana);
				if(speed != 0)
					data.set(new NamespacedKey(Main.getMain(), "speed"), PersistentDataType.DOUBLE, speed);
				if(strength != 0)
					data.set(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE, strength);
				if(cc != 0)
					data.set(new NamespacedKey(Main.getMain(), "cc"), PersistentDataType.DOUBLE, cc);
				if(cd != 0)
					data.set(new NamespacedKey(Main.getMain(), "cd"), PersistentDataType.DOUBLE, cd);
				if(abilitydamage != 0)
					data.set(new NamespacedKey(Main.getMain(), "abilitydamage"), PersistentDataType.FLOAT, abilitydamage);
				if(ferocity != 0)
					data.set(new NamespacedKey(Main.getMain(), "ferocity"), PersistentDataType.DOUBLE, ferocity);
				if(magicfind != 0)
					data.set(new NamespacedKey(Main.getMain(), "magicfind"), PersistentDataType.DOUBLE, magicfind);
				if(breakingpower != 0)
					data.set(new NamespacedKey(Main.getMain(), "breakingpower"), PersistentDataType.DOUBLE, breakingpower);
				if(miningspeed != 0)
					data.set(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE, miningspeed);
				if(miningfortune != 0)
					data.set(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE, miningfortune);
				if(abilityID != null)
					data.set(new NamespacedKey(Main.getMain(), "ability"), PersistentDataType.STRING, abilityID);

				if(abilityCD != 0)
					data.set(new NamespacedKey(Main.getMain(), "abilitycd"), PersistentDataType.INTEGER, abilityCD);
				if(baseabilitydamage != 0)
					data.set(new NamespacedKey(Main.getMain(), "baseabilitydamage"), PersistentDataType.INTEGER, baseabilitydamage);
				if(abilitymultiplier != 0)
					data.set(new NamespacedKey(Main.getMain(), "abilityscaling"), PersistentDataType.FLOAT, abilitymultiplier);
				if(pristine != 0)
					data.set(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE, pristine);
				if(truedefense != 0)
					data.set(new NamespacedKey(Main.getMain(), "truedefense"), PersistentDataType.DOUBLE, truedefense);
				if(seacreaturechance != 0)
					data.set(new NamespacedKey(Main.getMain(), "seacreaturechance"), PersistentDataType.DOUBLE, seacreaturechance);
				if(catchMult != 0)
					data.set(new NamespacedKey(Main.getMain(), "catchmult"), PersistentDataType.DOUBLE, catchMult);
				if(attackspeed != 0)
					data.set(new NamespacedKey(Main.getMain(), "as"), PersistentDataType.DOUBLE, attackspeed);
				data.set(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING, rarity.toString());
				data.set(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER, 0);
				ArrayList<String> lore= new ArrayList<>();
				if(this.lore != null)
				this.lore.forEach(l->{
					lore.add(l);
				});
				if(hasEdition) {
					
					data.set(new NamespacedKey(Main.getMain(), "from"), PersistentDataType.STRING, "-");
					data.set(new NamespacedKey(Main.getMain(), "to"), PersistentDataType.STRING, "-");
					EditionItems.reload();
					 int edition = EditionItems.get().getInt(itemID) + 1;
					 EditionItems.get().set(itemID, edition);
					 EditionItems.save();
					 EditionItems.reload();
					
					data.set(new NamespacedKey(Main.getMain(), "editionnumber"), PersistentDataType.INTEGER, edition);
				}
				
				if(type == ItemType.Drill) {
					data.set(new NamespacedKey(Main.getMain(), "maxfuel"), PersistentDataType.INTEGER, 3000);
					data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, 3000);
				}

				if(isUnstackeble)
					data.set(new NamespacedKey(Main.getMain(), "uuid"), PersistentDataType.STRING, UUID.randomUUID().toString());
				for(Stats stat : stats.keySet())
					data.set(new NamespacedKey(Main.getMain(), stat.getDataName()), PersistentDataType.DOUBLE, stats.get(stat));
				
				lore.add(" ");
				if(abilityName != null)
				lore.add("§6Ability: " + abilityName + " §e§lRight Click");
				if(abilityLore != null)
				abilityLore.forEach(lore_item->{
					lore.add(lore_item);
				});
				if(abilityManaCost != 0)
				lore.add("§8Mana Cost §b" + abilityManaCost);
				if(abilityCD != 0) 
					lore.add("§8Ability Cooldown §a" + abilityCD + "s");
				lore.add("");
				lore.add(rarity.getRarityName() + type.toString());
				meta.setLore(lore);
				
				if(Gemstone.gemstones.containsKey(itemID)) {
					Gemstone gem = Gemstone.gemstones.get(itemID);
					
					data.set(new NamespacedKey(Main.getMain(), "QUALITY"), PersistentDataType.STRING, gem.gemState.toString());
					data.set(new NamespacedKey(Main.getMain(), "GEMTYPE"), PersistentDataType.STRING, gem.gemType.toString());
					
				}
				if(Pet.pets.containsKey(itemID)) {
					
					
					data.set(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER, 1);
					data.set(new NamespacedKey(Main.getMain(), "currxp"), PersistentDataType.DOUBLE, 0D);
					
				}
				if(customIntContainer != null) {
					customIntContainer.forEach((arg1, arg2) ->{
						data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.INTEGER, arg2);
					});
				}
				
				item.setItemMeta(meta);
				NBTEditor.set(item, itemID, "ExtraAtribute", "id");
				return item;
			}
	}
	public void setNpcSellPrice(int i){
	npcSellPrice = i;
	}
	public int getSellPrice(){
	return npcSellPrice;
	}
	
	
}
