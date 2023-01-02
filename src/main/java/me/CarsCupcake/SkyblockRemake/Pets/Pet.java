package me.CarsCupcake.SkyblockRemake.Pets;

import java.util.ArrayList;
import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.Items.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Configs.PetMenus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;


public class Pet extends ItemManager {
	public static HashMap<String, Pet> pets = new HashMap<>();
	public final int MaxLevel;
//max means level 100 so half of the stats if pet goes to level 200
public double maxhealth;
public double maxdef;
public double maxmana;
public double maxspeed;
public double maxstrength;
public double maxcc;
public double maxcd;
public float maxabilitydamage;
public double maxferocity;
public double maxmagicfind;
public int maxbreakingpower;
public double maxminingspeed;
public double maxminingfortune;
public double maxattackspeed;
public double maxpristine;
public double maxtruedefense;
public double maxseacreaturechance;
private ArrayList<AbilityLore> abilityLores = new ArrayList<>();
private ArrayList<PetAbility> abilities = new ArrayList<>();
private ArrayList<String> abilityName = new ArrayList<>();
	public final PetType Petype;
	public Pet(String name, String itemID, int health, int def,int mana,int speed, int strength,int cc, int cd,float abilitydamage,int ferocity, int magicfind,int breakingpower, int miningspeed, int miningfortune,double pristine,int attackspeed,
			int maxhealth, int maxdef,int maxmana,int maxspeed, int maxstrength,int maxcc, int maxcd,float maxabilitydamage,int maxferocity, int maxmagicfind,int maxbreakingpower, int maxminingspeed, int maxminingfortune,double maxpristine,int maxattackspeed
			, Material material, ItemRarity rarity, int MaxLevel,PetType pettype,
			String ability1, ArrayList<String> lore1,String ability2, ArrayList<String> lore2,String ability3, ArrayList<String> lore3, String headTexture) {
		
		
		
		
		super(name, itemID, ItemType.Pet,rarity, headTexture);
		this.MaxLevel = MaxLevel;
		super.setUnstackeble(true);
		this.maxhealth = maxhealth;
		this.maxdef = maxdef;
		this.maxmana = maxmana;
		this.maxspeed = maxspeed;
		this.maxstrength = maxstrength;
		this.maxcc = maxcc;
		this.maxcd = maxcd;
		this.maxabilitydamage = maxabilitydamage;
		this.maxferocity = maxferocity;
		this.maxmagicfind = maxmagicfind;
		this.maxminingspeed = maxminingspeed;
		this.maxminingfortune = maxminingfortune;
		this.maxattackspeed = maxattackspeed;
		this.maxpristine = maxpristine;
		this.maxtruedefense = 0;
		Petype = pettype;
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§6" + ability1);
		for(String l : lore1)
			lore.add(l);
		if(lore2 != null) {
		lore.add(" " );
		
		lore.add("§6" + ability2);
		for(String l : lore2)
			lore.add(l);
		}
		if(lore3 != null) {
		lore.add(" " );
		
		lore.add("§6" + ability3);
		for(String l : lore3)
			lore.add(l);
		}
		
		
		
		super.lore = lore;
		pets.put(itemID, this);
		Items.SkyblockItems.put(itemID, this);
	}
	public Pet(String name, String itemID, String headTexture, ItemRarity rarity, int MaxLevel, PetType petType){
		super(name, itemID, ItemType.Pet, null, null, null,null, 0,0,0,0,rarity, headTexture);
		Petype = petType;
		this.MaxLevel = MaxLevel;
		super.setUnstackeble(true);
		pets.put(itemID, this);
		Items.SkyblockItems.put(itemID, this);
	}

	public Pet(String name, String itemID, int health, int def,int mana,int speed, int strength,int cc, int cd,float abilitydamage,int ferocity, int magicfind,int breakingpower, int miningspeed, int miningfortune,double pristine,int attackspeed,int truedefense,
			int maxhealth, int maxdef,int maxmana,int maxspeed, int maxstrength,int maxcc, int maxcd,float maxabilitydamage,int maxferocity, int maxmagicfind,int maxbreakingpower, int maxminingspeed, int maxminingfortune,double maxpristine,int maxattackspeed
			,int maxtruedefense, Material material, ItemRarity rarity, int MaxLevel,PetType pettype,
			String ability1, ArrayList<String> lore1,String ability2, ArrayList<String> lore2,String ability3, ArrayList<String> lore3, String headTexture) {
		
		
		
		
		super(name, itemID, ItemType.Pet ,rarity, headTexture);
		this.MaxLevel = MaxLevel;
		super.setUnstackeble(true);
		this.maxhealth = maxhealth;
		this.maxdef = maxdef;
		this.maxmana = maxmana;
		this.maxspeed = maxspeed;
		this.maxstrength = maxstrength;
		this.maxcc = maxcc;
		this.maxcd = maxcd;
		this.maxabilitydamage = maxabilitydamage;
		this.maxferocity = maxferocity;
		this.maxmagicfind = maxmagicfind;
		this.maxminingspeed = maxminingspeed;
		this.maxminingfortune = maxminingfortune;
		this.maxattackspeed = maxattackspeed;
		this.maxpristine = maxpristine;
		this.maxtruedefense = maxtruedefense;
		
		Petype = pettype;
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§6" + ability1);
		for(String l : lore1)
			lore.add(l);
		if(lore2 != null) {
		lore.add(" " );
		
		lore.add("§6" + ability2);
		for(String l : lore2)
			lore.add(l);
		}
		if(lore3 != null) {
		lore.add(" " );
		
		lore.add("§6" + ability3);
		for(String l : lore3)
			lore.add(l);
		}
		
		
		
		super.lore = lore;
		pets.put(itemID, this);
		Items.SkyblockItems.put(itemID, this);
	}

	public void addAbility(String abilityName, AbilityLore lore, PetAbility ability){
		abilities.add(ability);
		abilityLores.add(lore);
		this.abilityName.add(abilityName);
	}
	public ArrayList<String> buildAbilityLore(SkyblockPlayer player, ItemStack item){
		ArrayList<String> lore = new ArrayList<>();
		if(!abilities.isEmpty())
		for(int i = 0; i < abilities.size(); i++){
			lore.add("§6" + abilityName.get(i));
			lore.addAll(abilityLores.get(i).makeLore(player, item));
			lore.add(" ");
		}
		return lore;
	}


	public  void addStat(Stats stat, double baseValue, double maxValue){

		switch (stat){

			case Health -> {
				this.setStat(stat,baseValue);
				maxhealth = (int)maxValue;

			}
			case Defense -> {this.setStat(stat,baseValue);
				maxdef = (int)maxValue;

			}
			case Inteligence -> {
				this.setStat(stat,baseValue);
				maxmana = (int)maxValue;
			}
			case Speed -> {
				this.setStat(stat,baseValue);
				maxspeed = (int)maxValue;
			}
			case Strength -> {
				this.setStat(stat,baseValue);
				maxstrength = (int)maxValue;
			}
			case CritDamage -> {
				this.setStat(stat,baseValue);
				maxcd = (int)maxValue;
			}
			case CritChance -> {
				this.setStat(stat,baseValue);
				maxcc = (int)maxValue;
			}
			case AbilityDamage -> {
				this.setStat(stat,baseValue);
				maxabilitydamage = (float)maxValue;
			}
			case Ferocity -> {
				this.setStat(stat,baseValue);
				maxferocity = (int)maxValue;
			}
			case MagicFind -> {
				this.setStat(stat,baseValue);
				maxmagicfind = (int)maxValue;
			}
			case MiningSpeed -> {
				this.setStat(stat,baseValue);
				maxminingspeed = (int)maxValue;
			}
			case MiningFortune -> {
				this.setStat(stat,baseValue);
				maxminingfortune = (int)maxValue;
			}
			case Pristine -> {
				this.setStat(stat,baseValue);
				maxpristine = (int)maxValue;
			}
			case AttackSpeed -> {this.setStat(stat,baseValue);
				maxattackspeed = (int)maxValue;

			}
			case TrueDefense -> {
				this.setStat(stat,baseValue);
				maxtruedefense = (int)maxValue;
			}
			case SeaCreatureChance -> {
				this.setStat(stat,baseValue);
				maxseacreaturechance = (int)maxValue;
			}
		}
	}
	
	
	public static void addPetXP(SkyblockPlayer player , double xp) {

		
		if(PetMenus.get().getInt(player.getUniqueId() +  ".equiped") != 0) {
			Pet pet = Pet.pets.get(PetMenus.get().getString(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() +  ".equiped") + ".id"));
			double currxp = PetMenus.get().getDouble(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() +  ".equiped") + ".currxp");
			int level = PetMenus.get().getInt(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() +  ".equiped") + ".level");
			
			if(level + 1 > pet.MaxLevel) {
				PetMenus.get().set(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() +  ".equiped") + ".currxp",xp + currxp);

				PetMenus.save();
				PetMenus.reload();

				return;

				}
			
			if(pet.getRequieredXp(level) <= currxp + xp) {
				double upxp = currxp + xp;
				while(upxp > 0) {
					
					if(upxp - pet.getRequieredXp(level) < 0) {
						PetMenus.get().set(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() +  ".equiped") + ".currxp",upxp);
						PetMenus.get().set(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() +  ".equiped") + ".level",level);
						PetMenus.save();
						PetMenus.reload();
						Main.updatebar(player);
						Main.petstand.get(player).updatePet();
						player.sendMessage("§aYour " + pet.rarity.getPrefix() + pet.name + " §alevelled up to level §9" + level);
						Bukkit.getPluginManager().callEvent(new PetLevelUpEvent(player, pet, level));
						return;
					}
					if(level + 1 >= pet.MaxLevel) {
						PetMenus.get().set(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() +  ".equiped") + ".currxp",upxp -= pet.getRequieredXp(level));
						PetMenus.get().set(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() +  ".equiped") + ".level",pet.MaxLevel);
						PetMenus.save();
						PetMenus.reload();
						Main.updatebar(player);
						Main.petstand.get(player).updatePet();
						player.sendMessage("§aYour " + pet.rarity.getPrefix() + pet.name + " §alevelled up to level §9" + pet.MaxLevel);
						Bukkit.getPluginManager().callEvent(new PetLevelUpEvent(player, pet, level));
						return;

						}
					upxp -= pet.getRequieredXp(level);
					level += 1;
					Bukkit.getPluginManager().callEvent(new PetLevelUpEvent(player, pet, level));
					if(level == 100)
						level += 1;
				}
			}else {
				PetMenus.get().set(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() +  ".equiped") + ".currxp", xp + currxp);
				PetMenus.save();
				PetMenus.reload();
			}
			
		}
		
		
		
	}
	public static void addPetXP(SkyblockPlayer player , double xp, Skills skill) {
		if(PetMenus.get().getInt(player.getUniqueId() +  ".equiped") == 0)
			return;
		Pet pet = Pet.pets.get(PetMenus.get().getString(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() +  ".equiped") + ".id"));
		double result = xp;
		if(skill == Skills.Fishing || skill == Skills.Mining)
			result *= 1.50;
		
		if(!pet.Petype.equals(skill)) {
			if(skill == Skills.Alchemy || skill == Skills.Enchanting)
				result /= 12;
			else
				result /= 3;
			
			
		}
		result = Tools.round(result, 1);

		switch (skill){
			case Farming, Mining, Combat, Foraging, Fishing, Enchanting -> player.addSkillXp(xp*0.25, Skills.Taming);
			case Alchemy -> player.addSkillXp(xp*0.025, Skills.Taming);
		}
		
		addPetXP(player, result);
	}
	public ItemStack updatePet(ItemStack item) {
		
		final int level = item.getItemMeta().getPersistentDataContainer().getOrDefault(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER, 1);
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer data = meta.getPersistentDataContainer();
		
		/*data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxhealth+health));
		data.set(new NamespacedKey(Main.getMain(), "abilitydamage"), PersistentDataType.FLOAT,(float)( ((double)level*0.01)*maxabilitydamage+abilitydamage));
		data.set(new NamespacedKey(Main.getMain(), "cc"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxcc+cc));
		data.set(new NamespacedKey(Main.getMain(), "cd"), PersistentDataType.DOUBLE,(double)((int)( ((double)level*0.01)*maxcd+cd)));
		data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxdef+def));
		data.set(new NamespacedKey(Main.getMain(), "ferocity"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxferocity+ferocity));
		data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxmana+mana));
		data.set(new NamespacedKey(Main.getMain(), "magicfind"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxmagicfind+magicfind));
		data.set(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxminingfortune+miningfortune));
		data.set(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxminingspeed+miningspeed));
		data.set(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxpristine+pristine));
		data.set(new NamespacedKey(Main.getMain(), "speed"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxspeed+speed));
		data.set(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxstrength+strength));
		data.set(new NamespacedKey(Main.getMain(), "as"), PersistentDataType.DOUBLE,( ((double)level*0.01)*maxattackspeed+attackspeed));*/
		for(Stats stat : Stats.values())
			data.set(new NamespacedKey(Main.getMain(),stat.getDataName()), PersistentDataType.DOUBLE,getStat(stat, level));
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public double getStat(Stats stat, int level){

		
		switch (stat) {
		case AbilityDamage:
			
			return ((double)level*0.01)*maxabilitydamage+super.getStat(stat);
			
			
		case CritChance:
			return ((double)((level-1)/(MaxLevel-1)))*maxcc+super.getStat(stat);
		case CritDamage:
			return ((double)((level-1)/(MaxLevel-1)))*maxcd+super.getStat(stat);
		case Defense:
			return ((double)((level-1)/(MaxLevel-1)))*maxdef+super.getStat(stat);
		case Ferocity:
			return ((double)((level-1)/(MaxLevel-1)))*maxferocity+super.getStat(stat);
		case Health:
			return ((double)((level-1)/(MaxLevel-1)))*maxhealth+super.getStat(stat);
		case Inteligence:
			return ((double)((level-1)/(MaxLevel-1)))*maxmana+super.getStat(stat);
		case MagicFind:
			return ((double)((level-1)/(MaxLevel-1)))*maxmagicfind+super.getStat(stat);
		case MiningFortune:
			return ((double)((level-1)/(MaxLevel-1)))*maxminingfortune+super.getStat(stat);
		case MiningSpeed:
			return ((double)((level-1)/(MaxLevel-1)))*maxminingspeed+super.getStat(stat);
		case Pristine:
			return ((double)((level-1)/(MaxLevel-1)))*maxpristine+super.getStat(stat);
		case Speed:
			return ((double)((level-1)/(MaxLevel-1)))*maxspeed+super.getStat(stat);
		case Strength:

			return (((double)((level-1)/(MaxLevel-1)))*maxstrength)+super.getStat(stat);
			case SeaCreatureChance:
				return (((double)((level-1)/(MaxLevel-1)))*maxseacreaturechance) + super.getStat(stat);
		default:
			break;
		
		}
		
		
		
		
		return 0;
		
	}
	//level = currlevel -> getNextLevel exp
	public double getRequieredXp(int level) {
		switch(rarity) {
		case COMMON:
			switch(level) {
			case 1:
				return 100;
			case 2:
				return 110;
			case 3:
				return 120;
			case 4:
			    return 130;
			case 5:
				return 145;
			case 6:
				return 160;
			case 7:
				return 175;
			case 8:
				return 190;
			case 9:
				return 210;
			case 10:
				return 230;
			case 11:
				return 250;
			case 12:
				return 275;
			case 13:
				return 300;
			case 14:
				return 330;
			case 15:
				return 360;
			case 16:
			    return 400;
			case 17:
				return 440;
			case 18:
				return 490;
			case 19:
				return 540;
			case 20:
				return 600;
			case 21:
				return 660;
			case 22:
				return 730;
			case 23:
				return 800;
			case 24:
				return 880;
			case 25:
				return 960;
			case 26:
				return 1050;
			case 27:
				return 1150;
			case 28:
				return 1260;
			case 29:
				return 1380;
			case 30:
				return 1510;
			case 31:
				return 1650;
			case 32:
				return 1800;
			case 33:
				return 1960;
			case 34:
				return 2130;
			case 35:
				return 2310;
			case 36:
				return 2500;
			case 37:
				return 2700;
			case 38:
				return 2920;
			case 39:
				return 3160;
			case 40:
				return 3420;
			case 41:
				return 3700;
			case 42:
				return 4000;
			case 43:
				return 4350;
			case 44:
				return 4750;
			case 45:
				return 5200;
			case 46:
				return 5700;
			case 47:
				return 6300;
			case 48:
				return 7000;	
			case 49:
				return 7800;
			case 50:
				return 8700;
			case 51:
				return 9700;
			case 52:
				return 10800;
			case 53:
				return 12000;
			case 54:
				return 13300;
			case 55:
				return 14700;
			case 56:
				return 16200;
			case 57:
				return 17800;
			case 58:
				return 19500;
			case 59:
				return 21300;
			case 60:
				return 23200;
			case 61:
				return 25200;
			case 62:
				return 27400;
			case 63:
				return 29800;
			case 64:
				return 32400;
			case 65:
				return 35200;
			case 66:
				return 38200;
			case 67:
				return 41400;
			case 68:
				return 44800;
			case 69:
				return 48400;
			case 70:
				return 52200;
			case 71:
				return 56200;
			case 72:
				return 60400;
			case 73:
				return 64800;
			case 74:
				return 69420;
			case 75:
				return 74200;
			case 76:
				return 79200;
			case 77:
				return 84700;
			case 78:
				return 90700;
			case 79:
				return 97200;
			case 80:
				return 104200;
			case 81:
				return 111700;
			case 82:
				return 119700;
			case 83:
				return 128200;
			case 84:
				return 137200;
			case 85:
				return 146700;
			case 86:
				return 156700;
			case 87:
				return 167700;
			case 88:
				return 179700;
			case 89:
				return 192700;
			case 90:
				return 206700;
			case 91:
				return 221700;
			case 92:
				return 237700;
			case 93:
				return 254700;
			case 94:
				return 272700;
			case 95:
				return 291700;
			case 96:
				return 311700;
			case 97:
				return 333700;
			case 98:
				return 357700;
			case 99:
				return 383700;	
			}
			return 383700;
			
		case DIVINE:
			switch(level) {
			case 1:
				return 660;
			case 2:
				return 730;
			case 3:
				return 800;
			case 4:
			    return 880;
			case 5:
				return 960;
			case 6:
				return 1050;
			case 7:
				return 1150;
			case 8:
				return 1260;
			case 9:
				return 1380;
			case 10:
				return 1510;
			case 11:
				return 1650;
			case 12:
				return 1800;
			case 13:
				return 1960;
			case 14:
				return 2130;
			case 15:
				return 2310;
			case 16:
			    return 2500;
			case 17:
				return 2700;
			case 18:
				return 2920;
			case 19:
				return 3160;
			case 20:
				return 3420;
			case 21:
				return 3700;
			case 22:
				return 4000;
			case 23:
				return 4350;
			case 24:
				return 4750;
			case 25:
				return 5200;
			case 26:
				return 5700;
			case 27:
				return 6300;
			case 28:
				return 7000;
			case 29:
				return 7800;
			case 30:
				return 8700;
			case 31:
				return 9700;
			case 32:
				return 10800;
			case 33:
				return 12000;
			case 34:
				return 13300;
			case 35:
				return 14700;
			case 36:
				return 16200;
			case 37:
				return 17800;
			case 38:
				return 19500;
			case 39:
				return 21300;
			case 40:
				return 23200;
			case 41:
				return 25200;
			case 42:
				return 27400;
			case 43:
				return 29800;
			case 44:
				return 32400;
			case 45:
				return 35200;
			case 46:
				return 38200;
			case 47:
				return 41400;
			case 48:
				return 44800;	
			case 49:
				return 48400;
			case 50:
				return 52200;
			case 51:
				return 56200;
			case 52:
				return 60400;
			case 53:
				return 64800;
			case 54:
				return 69400;
			case 55:
				return 74200;
			case 56:
				return 79200;
			case 57:
				return 84700;
			case 58:
				return 90700;
			case 59:
				return 97200;
			case 60:
				return 104200;
			case 61:
				return 111700;
			case 62:
				return 119700;
			case 63:
				return 128200;
			case 64:
				return 137200;
			case 65:
				return 146700;
			case 66:
				return 156700;
			case 67:
				return 167700;
			case 68:
				return 179700;
			case 69:
				return 192700;
			case 70:
				return 206700;
			case 71:
				return 221700;
			case 72:
				return 237700;
			case 73:
				return 254700;
			case 74:
				return 272700;
			case 75:
				return 291700;
			case 76:
				return 311700;
			case 77:
				return 333700;
			case 78:
				return 357700;
			case 79:
				return 383700;
			case 80:
				return 411700;
			case 81:
				return 441700;
			case 82:
				return 476700;
			case 83:
				return 516700;
			case 84:
				return 516700;
			case 85:
				return 611700;
			case 86:
				return 666700;
			case 87:
				return 726700;
			case 88:
				return 791700;
			case 89:
				return 861700;
			case 90:
				return 936700;
			case 91:
				return 1016700;
			case 92:
				return 1101700;
			case 93:
				return 1191700;
			case 94:
				return 1286700;
			case 95:
				return 1386700;
			case 96:
				return 1496700;
			case 97:
				return 1616700;
			case 98:
				return 1746700;
			case 99:
				return 1886700;	
			}
			return 1886700;
		case EPIC:
			switch(level) {
			case 1:
				return 440;
			case 2:
				return 490;
			case 3:
				return 540;
			case 4:
			    return 600;
			case 5:
				return 660;
			case 6:
				return 730;
			case 7:
				return 800;
			case 8:
				return 880;
			case 9:
				return 960;
			case 10:
				return 1050;
			case 11:
				return 1150;
			case 12:
				return 1260;
			case 13:
				return 1380;
			case 14:
				return 1510;
			case 15:
				return 1650;
			case 16:
			    return 1800;
			case 17:
				return 1960;
			case 18:
				return 2130;
			case 19:
				return 2310;
			case 20:
				return 2500;
			case 21:
				return 2700;
			case 22:
				return 2920;
			case 23:
				return 3160;
			case 24:
				return 3420;
			case 25:
				return 3700;
			case 26:
				return 4000;
			case 27:
				return 4350;
			case 28:
				return 4750;
			case 29:
				return 5200;
			case 30:
				return 5700;
			case 31:
				return 6300;
			case 32:
				return 7000;
			case 33:
				return 7800;
			case 34:
				return 8700;
			case 35:
				return 8700;
			case 36:
				return 10800;
			case 37:
				return 12000;
			case 38:
				return 13300;
			case 39:
				return 14700;
			case 40:
				return 16200;
			case 41:
				return 17800;
			case 42:
				return 19500;
			case 43:
				return 21300;
			case 44:
				return 23200;
			case 45:
				return 25200;
			case 46:
				return 27400;
			case 47:
				return 29800;
			case 48:
				return 32400;	
			case 49:
				return 35200;
			case 50:
				return 38200;
			case 51:
				return 41400;
			case 52:
				return 44800;
			case 53:
				return 48400;
			case 54:
				return 48400;
			case 55:
				return 52200;
			case 56:
				return 56200;
			case 57:
				return 60400;
			case 58:
				return 64800;
			case 59:
				return 69400;
			case 60:
				return 74200;
			case 61:
				return 79200;
			case 62:
				return 84700;
			case 63:
				return 90700;
			case 64:
				return 97200;
			case 65:
				return 104200;
			case 66:
				return 111700;
			case 67:
				return 119700;
			case 68:
				return 128200;
			case 69:
				return 137200;
			case 70:
				return 146700;
			case 71:
				return 156700;
			case 72:
				return 167700;
			case 73:
				return 179700;
			case 74:
				return 192700;
			case 75:
				return 206700;
			case 76:
				return 221700;
			case 77:
				return 237700;
			case 78:
				return 254700;
			case 79:
				return 272700;
			case 80:
				return 291700;
			case 81:
				return 311700;
			case 82:
				return 333700;
			case 83:
				return 357700;
			case 84:
				return 383700;
			case 85:
				return 411700;
			case 86:
				return 441700;
			case 87:
				return 476700;
			case 88:
				return 516700;
			case 89:
				return 611700;
			case 90:
				return 666700;
			case 91:
				return 726700;
			case 92:
				return 791700;
			case 93:
				return 861700;
			case 94:
				return 936700;
			case 95:
				return 1016700;
			case 96:
				return 1101700;
			case 97:
				return 1191700;
			case 98:
				return 1286700;
			case 99:
				return 1386700;
			}
			return 1386700;
		case LEGENDARY:
			switch(level) {
			case 1:
				return 660;
			case 2:
				return 730;
			case 3:
				return 800;
			case 4:
			    return 880;
			case 5:
				return 960;
			case 6:
				return 1050;
			case 7:
				return 1150;
			case 8:
				return 1260;
			case 9:
				return 1380;
			case 10:
				return 1510;
			case 11:
				return 1650;
			case 12:
				return 1800;
			case 13:
				return 1960;
			case 14:
				return 2130;
			case 15:
				return 2310;
			case 16:
			    return 2500;
			case 17:
				return 2700;
			case 18:
				return 2920;
			case 19:
				return 3160;
			case 20:
				return 3420;
			case 21:
				return 3700;
			case 22:
				return 4000;
			case 23:
				return 4350;
			case 24:
				return 4750;
			case 25:
				return 5200;
			case 26:
				return 5700;
			case 27:
				return 6300;
			case 28:
				return 7000;
			case 29:
				return 7800;
			case 30:
				return 8700;
			case 31:
				return 9700;
			case 32:
				return 10800;
			case 33:
				return 12000;
			case 34:
				return 13300;
			case 35:
				return 14700;
			case 36:
				return 16200;
			case 37:
				return 17800;
			case 38:
				return 19500;
			case 39:
				return 21300;
			case 40:
				return 23200;
			case 41:
				return 25200;
			case 42:
				return 27400;
			case 43:
				return 29800;
			case 44:
				return 32400;
			case 45:
				return 35200;
			case 46:
				return 38200;
			case 47:
				return 41400;
			case 48:
				return 44800;	
			case 49:
				return 48400;
			case 50:
				return 52200;
			case 51:
				return 56200;
			case 52:
				return 60400;
			case 53:
				return 64800;
			case 54:
				return 69400;
			case 55:
				return 74200;
			case 56:
				return 79200;
			case 57:
				return 84700;
			case 58:
				return 90700;
			case 59:
				return 97200;
			case 60:
				return 104200;
			case 61:
				return 111700;
			case 62:
				return 119700;
			case 63:
				return 128200;
			case 64:
				return 137200;
			case 65:
				return 146700;
			case 66:
				return 156700;
			case 67:
				return 167700;
			case 68:
				return 179700;
			case 69:
				return 192700;
			case 70:
				return 206700;
			case 71:
				return 221700;
			case 72:
				return 237700;
			case 73:
				return 254700;
			case 74:
				return 272700;
			case 75:
				return 291700;
			case 76:
				return 311700;
			case 77:
				return 333700;
			case 78:
				return 357700;
			case 79:
				return 383700;
			case 80:
				return 411700;
			case 81:
				return 441700;
			case 82:
				return 476700;
			case 83:
				return 516700;
			case 84:
				return 516700;
			case 85:
				return 611700;
			case 86:
				return 666700;
			case 87:
				return 726700;
			case 88:
				return 791700;
			case 89:
				return 861700;
			case 90:
				return 936700;
			case 91:
				return 1016700;
			case 92:
				return 1101700;
			case 93:
				return 1191700;
			case 94:
				return 1286700;
			case 95:
				return 1386700;
			case 96:
				return 1496700;
			case 97:
				return 1616700;
			case 98:
				return 1746700;
			case 99:
				return 1886700;	
			}
			return 1886700;
		case MYTHIC:
			switch(level) {
			case 1:
				return 660;
			case 2:
				return 730;
			case 3:
				return 800;
			case 4:
			    return 880;
			case 5:
				return 960;
			case 6:
				return 1050;
			case 7:
				return 1150;
			case 8:
				return 1260;
			case 9:
				return 1380;
			case 10:
				return 1510;
			case 11:
				return 1650;
			case 12:
				return 1800;
			case 13:
				return 1960;
			case 14:
				return 2130;
			case 15:
				return 2310;
			case 16:
			    return 2500;
			case 17:
				return 2700;
			case 18:
				return 2920;
			case 19:
				return 3160;
			case 20:
				return 3420;
			case 21:
				return 3700;
			case 22:
				return 4000;
			case 23:
				return 4350;
			case 24:
				return 4750;
			case 25:
				return 5200;
			case 26:
				return 5700;
			case 27:
				return 6300;
			case 28:
				return 7000;
			case 29:
				return 7800;
			case 30:
				return 8700;
			case 31:
				return 9700;
			case 32:
				return 10800;
			case 33:
				return 12000;
			case 34:
				return 13300;
			case 35:
				return 14700;
			case 36:
				return 16200;
			case 37:
				return 17800;
			case 38:
				return 19500;
			case 39:
				return 21300;
			case 40:
				return 23200;
			case 41:
				return 25200;
			case 42:
				return 27400;
			case 43:
				return 29800;
			case 44:
				return 32400;
			case 45:
				return 35200;
			case 46:
				return 38200;
			case 47:
				return 41400;
			case 48:
				return 44800;	
			case 49:
				return 48400;
			case 50:
				return 52200;
			case 51:
				return 56200;
			case 52:
				return 60400;
			case 53:
				return 64800;
			case 54:
				return 69400;
			case 55:
				return 74200;
			case 56:
				return 79200;
			case 57:
				return 84700;
			case 58:
				return 90700;
			case 59:
				return 97200;
			case 60:
				return 104200;
			case 61:
				return 111700;
			case 62:
				return 119700;
			case 63:
				return 128200;
			case 64:
				return 137200;
			case 65:
				return 146700;
			case 66:
				return 156700;
			case 67:
				return 167700;
			case 68:
				return 179700;
			case 69:
				return 192700;
			case 70:
				return 206700;
			case 71:
				return 221700;
			case 72:
				return 237700;
			case 73:
				return 254700;
			case 74:
				return 272700;
			case 75:
				return 291700;
			case 76:
				return 311700;
			case 77:
				return 333700;
			case 78:
				return 357700;
			case 79:
				return 383700;
			case 80:
				return 411700;
			case 81:
				return 441700;
			case 82:
				return 476700;
			case 83:
				return 516700;
			case 84:
				return 516700;
			case 85:
				return 611700;
			case 86:
				return 666700;
			case 87:
				return 726700;
			case 88:
				return 791700;
			case 89:
				return 861700;
			case 90:
				return 936700;
			case 91:
				return 1016700;
			case 92:
				return 1101700;
			case 93:
				return 1191700;
			case 94:
				return 1286700;
			case 95:
				return 1386700;
			case 96:
				return 1496700;
			case 97:
				return 1616700;
			case 98:
				return 1746700;
			case 99:
				return 1886700;	
			}
			return 1886700;
		case RARE:
			switch(level) {
			case 1:
				return 275;
			case 2:
				return 300;
			case 3:
				return 330;
			case 4:
			    return 360;
			case 5:
				return 400;
			case 6:
				return 440;
			case 7:
				return 490;
			case 8:
				return 540;
			case 9:
				return 600;
			case 10:
				return 660;
			case 11:
				return 730;
			case 12:
				return 800;
			case 13:
				return 880;
			case 14:
				return 960;
			case 15:
				return 1050;
			case 16:
			    return 1150;
			case 17:
				return 1260;
			case 18:
				return 1380;
			case 19:
				return 1510;
			case 20:
				return 1650;
			case 21:
				return 1800;
			case 22:
				return 1960;
			case 23:
				return 2130;
			case 24:
				return 2310;
			case 25:
				return 2500;
			case 26:
				return 2700;
			case 27:
				return 2920;
			case 28:
				return 3160;
			case 29:
				return 3420;
			case 30:
				return 3700;
			case 31:
				return 4000;
			case 32:
				return 4350;
			case 33:
				return 4750;
			case 34:
				return 5200;
			case 35:
				return 5700;
			case 36:
				return 6300;
			case 37:
				return 7000;
			case 38:
				return 7800;
			case 39:
				return 8700;
			case 40:
				return 9700;
			case 41:
				return 10800;
			case 42:
				return 12000;
			case 43:
				return 13300;
			case 44:
				return 14700;
			case 45:
				return 16200;
			case 46:
				return 17800;
			case 47:
				return 19500;
			case 48:
				return 21300;	
			case 49:
				return 23200;
			case 50:
				return 25200;
			case 51:
				return 27400;
			case 52:
				return 29800;
			case 53:
				return 32400;
			case 54:
				return 35200;
			case 55:
				return 38200;
			case 56:
				return 41400;
			case 57:
				return 44800;
			case 58:
				return 48400;
			case 59:
				return 52200;
			case 60:
				return 56200;
			case 61:
				return 60400;
			case 62:
				return 64800;
			case 63:
				return 69400;
			case 64:
				return 74200;
			case 65:
				return 79200;
			case 66:
				return 84700;
			case 67:
				return 90700;
			case 68:
				return 97200;
			case 69:
				return 104200;
			case 70:
				return 111700;
			case 71:
				return 119700;
			case 72:
				return 128200;
			case 73:
				return 137200;
			case 74:
				return 146700;
			case 75:
				return 156700;
			case 76:
				return 167700;
			case 77:
				return 179700;
			case 78:
				return 192700;
			case 79:
				return 206700;
			case 80:
				return 221700;
			case 81:
				return 237700;
			case 82:
				return 254700;
			case 83:
				return 272700;
			case 84:
				return 291700;
			case 85:
				return 311700;
			case 86:
				return 333700;
			case 87:
				return 357700;
			case 88:
				return 383700;
			case 89:
				return 411700;
			case 90:
				return 441700;
			case 91:
				return 476700;
			case 92:
				return 516700;
			case 93:
				return 561700;
			case 94:
				return 611700;
			case 95:
				return 666700;
			case 96:
				return 726700;
			case 97:
				return 791700;
			case 98:
				return 861700;
			case 99:
				return 936700;
				
				
				
				
				
				
			}
		case SPECIAL:
			switch(level) {
			case 1:
				return 660;
			case 2:
				return 730;
			case 3:
				return 800;
			case 4:
			    return 880;
			case 5:
				return 960;
			case 6:
				return 1050;
			case 7:
				return 1150;
			case 8:
				return 1260;
			case 9:
				return 1380;
			case 10:
				return 1510;
			case 11:
				return 1650;
			case 12:
				return 1800;
			case 13:
				return 1960;
			case 14:
				return 2130;
			case 15:
				return 2310;
			case 16:
			    return 2500;
			case 17:
				return 2700;
			case 18:
				return 2920;
			case 19:
				return 3160;
			case 20:
				return 3420;
			case 21:
				return 3700;
			case 22:
				return 4000;
			case 23:
				return 4350;
			case 24:
				return 4750;
			case 25:
				return 5200;
			case 26:
				return 5700;
			case 27:
				return 6300;
			case 28:
				return 7000;
			case 29:
				return 7800;
			case 30:
				return 8700;
			case 31:
				return 9700;
			case 32:
				return 10800;
			case 33:
				return 12000;
			case 34:
				return 13300;
			case 35:
				return 14700;
			case 36:
				return 16200;
			case 37:
				return 17800;
			case 38:
				return 19500;
			case 39:
				return 21300;
			case 40:
				return 23200;
			case 41:
				return 25200;
			case 42:
				return 27400;
			case 43:
				return 29800;
			case 44:
				return 32400;
			case 45:
				return 35200;
			case 46:
				return 38200;
			case 47:
				return 41400;
			case 48:
				return 44800;	
			case 49:
				return 48400;
			case 50:
				return 52200;
			case 51:
				return 56200;
			case 52:
				return 60400;
			case 53:
				return 64800;
			case 54:
				return 69400;
			case 55:
				return 74200;
			case 56:
				return 79200;
			case 57:
				return 84700;
			case 58:
				return 90700;
			case 59:
				return 97200;
			case 60:
				return 104200;
			case 61:
				return 111700;
			case 62:
				return 119700;
			case 63:
				return 128200;
			case 64:
				return 137200;
			case 65:
				return 146700;
			case 66:
				return 156700;
			case 67:
				return 167700;
			case 68:
				return 179700;
			case 69:
				return 192700;
			case 70:
				return 206700;
			case 71:
				return 221700;
			case 72:
				return 237700;
			case 73:
				return 254700;
			case 74:
				return 272700;
			case 75:
				return 291700;
			case 76:
				return 311700;
			case 77:
				return 333700;
			case 78:
				return 357700;
			case 79:
				return 383700;
			case 80:
				return 411700;
			case 81:
				return 441700;
			case 82:
				return 476700;
			case 83:
				return 516700;
			case 84:
				return 516700;
			case 85:
				return 611700;
			case 86:
				return 666700;
			case 87:
				return 726700;
			case 88:
				return 791700;
			case 89:
				return 861700;
			case 90:
				return 936700;
			case 91:
				return 1016700;
			case 92:
				return 1101700;
			case 93:
				return 1191700;
			case 94:
				return 1286700;
			case 95:
				return 1386700;
			case 96:
				return 1496700;
			case 97:
				return 1616700;
			case 98:
				return 1746700;
			case 99:
				return 1886700;	
			}
			return 1886700;
		case SUPREME:
			switch(level) {
			case 1:
				return 660;
			case 2:
				return 730;
			case 3:
				return 800;
			case 4:
			    return 880;
			case 5:
				return 960;
			case 6:
				return 1050;
			case 7:
				return 1150;
			case 8:
				return 1260;
			case 9:
				return 1380;
			case 10:
				return 1510;
			case 11:
				return 1650;
			case 12:
				return 1800;
			case 13:
				return 1960;
			case 14:
				return 2130;
			case 15:
				return 2310;
			case 16:
			    return 2500;
			case 17:
				return 2700;
			case 18:
				return 2920;
			case 19:
				return 3160;
			case 20:
				return 3420;
			case 21:
				return 3700;
			case 22:
				return 4000;
			case 23:
				return 4350;
			case 24:
				return 4750;
			case 25:
				return 5200;
			case 26:
				return 5700;
			case 27:
				return 6300;
			case 28:
				return 7000;
			case 29:
				return 7800;
			case 30:
				return 8700;
			case 31:
				return 9700;
			case 32:
				return 10800;
			case 33:
				return 12000;
			case 34:
				return 13300;
			case 35:
				return 14700;
			case 36:
				return 16200;
			case 37:
				return 17800;
			case 38:
				return 19500;
			case 39:
				return 21300;
			case 40:
				return 23200;
			case 41:
				return 25200;
			case 42:
				return 27400;
			case 43:
				return 29800;
			case 44:
				return 32400;
			case 45:
				return 35200;
			case 46:
				return 38200;
			case 47:
				return 41400;
			case 48:
				return 44800;	
			case 49:
				return 48400;
			case 50:
				return 52200;
			case 51:
				return 56200;
			case 52:
				return 60400;
			case 53:
				return 64800;
			case 54:
				return 69400;
			case 55:
				return 74200;
			case 56:
				return 79200;
			case 57:
				return 84700;
			case 58:
				return 90700;
			case 59:
				return 97200;
			case 60:
				return 104200;
			case 61:
				return 111700;
			case 62:
				return 119700;
			case 63:
				return 128200;
			case 64:
				return 137200;
			case 65:
				return 146700;
			case 66:
				return 156700;
			case 67:
				return 167700;
			case 68:
				return 179700;
			case 69:
				return 192700;
			case 70:
				return 206700;
			case 71:
				return 221700;
			case 72:
				return 237700;
			case 73:
				return 254700;
			case 74:
				return 272700;
			case 75:
				return 291700;
			case 76:
				return 311700;
			case 77:
				return 333700;
			case 78:
				return 357700;
			case 79:
				return 383700;
			case 80:
				return 411700;
			case 81:
				return 441700;
			case 82:
				return 476700;
			case 83:
				return 516700;
			case 84:
				return 516700;
			case 85:
				return 611700;
			case 86:
				return 666700;
			case 87:
				return 726700;
			case 88:
				return 791700;
			case 89:
				return 861700;
			case 90:
				return 936700;
			case 91:
				return 1016700;
			case 92:
				return 1101700;
			case 93:
				return 1191700;
			case 94:
				return 1286700;
			case 95:
				return 1386700;
			case 96:
				return 1496700;
			case 97:
				return 1616700;
			case 98:
				return 1746700;
			case 99:
				return 1886700;	
			}
			return 1886700;
		case UNCOMMON:
			switch(level) {
			case 1:
				return 175;
			case 2:
				return 190;
			case 3:
				return 210;
			case 4:
			    return 230;
			case 5:
				return 250;
			case 6:
				return 275;
			case 7:
				return 300;
			case 8:
				return 330;
			case 9:
				return 360;
			case 10:
				return 400;
			case 11:
				return 440;
			case 12:
				return 490;
			case 13:
				return 540;
			case 14:
				return 600;
			case 15:
				return 660;
			case 16:
			    return 730;
			case 17:
				return 800;
			case 18:
				return 880;
			case 19:
				return 960;
			case 20:
				return 1050;
			case 21:
				return 1150;
			case 22:
				return 1260;
			case 23:
				return 1380;
			case 24:
				return 1510;
			case 25:
				return 1650;
			case 26:
				return 1800;
			case 27:
				return 1960;
			case 28:
				return 2130;
			case 29:
				return 2310;
			case 30:
				return 2500;
			case 31:
				return 2700;
			case 32:
				return 2920;
			case 33:
				return 3160;
			case 34:
				return 3420;
			case 35:
				return 3700;
			case 36:
				return 4000;
			case 37:
				return 4350;
			case 38:
				return 4750;
			case 39:
				return 5200;
			case 40:
				return 5700;
			case 41:
				return 6300;
			case 42:
				return 7000;
			case 43:
				return 7800;
			case 44:
				return 8700;
			case 45:
				return 9700;
			case 46:
				return 10800;
			case 47:
				return 12000;
			case 48:
				return 13300;	
			case 49:
				return 14700;
			case 50:
				return 16200;
			case 51:
				return 17800;
			case 52:
				return 19500;
			case 53:
				return 21300;
			case 54:
				return 23200;
			case 55:
				return 25200;
			case 56:
				return 27400;
			case 57:
				return 29800;
			case 58:
				return 32400;
			case 59:
				return 35200;
			case 60:
				return 38200;
			case 61:
				return 41400;
			case 62:
				return 44800;
			case 63:
				return 48400;
			case 64:
				return 52200;
			case 65:
				return 56200;
			case 66:
				return 60400;
			case 67:
				return 64800;
			case 68:
				return 69400;
			case 69:
				return 74200;
			case 70:
				return 79200;
			case 71:
				return 84700;
			case 72:
				return 90700;
			case 73:
				return 97200;
			case 74:
				return 104200;
			case 75:
				return 111700;
			case 76:
				return 119700;
			case 77:
				return 128200;
			case 78:
				return 137200;
			case 79:
				return 146700;
			case 80:
				return 156700;
			case 81:
				return 167700;
			case 82:
				return 179700;
			case 83:
				return 192700;
			case 84:
				return 206700;
			case 85:
				return 221700;
			case 86:
				return 237700;
			case 87:
				return 254700;
			case 88:
				return 272700;
			case 89:
				return 291700;
			case 90:
				return 311700;
			case 91:
				return 333700;
			case 92:
				return 357700;
			case 93:
				return 383700;
			case 94:
				return 411700;
			case 95:
				return 441700;
			case 96:
				return 476700;
			case 97:
				return 516700;
			case 98:
				return 561700;
			case 99:
				return 611700;
				
				
				
				
				
				
			}
			return 611700;
		case UNDEFINED:
			return 1;
		case VERY_SPECIAL:
			switch(level) {
			case 1:
				return 660;
			case 2:
				return 730;
			case 3:
				return 800;
			case 4:
			    return 880;
			case 5:
				return 960;
			case 6:
				return 1050;
			case 7:
				return 1150;
			case 8:
				return 1260;
			case 9:
				return 1380;
			case 10:
				return 1510;
			case 11:
				return 1650;
			case 12:
				return 1800;
			case 13:
				return 1960;
			case 14:
				return 2130;
			case 15:
				return 2310;
			case 16:
			    return 2500;
			case 17:
				return 2700;
			case 18:
				return 2920;
			case 19:
				return 3160;
			case 20:
				return 3420;
			case 21:
				return 3700;
			case 22:
				return 4000;
			case 23:
				return 4350;
			case 24:
				return 4750;
			case 25:
				return 5200;
			case 26:
				return 5700;
			case 27:
				return 6300;
			case 28:
				return 7000;
			case 29:
				return 7800;
			case 30:
				return 8700;
			case 31:
				return 9700;
			case 32:
				return 10800;
			case 33:
				return 12000;
			case 34:
				return 13300;
			case 35:
				return 14700;
			case 36:
				return 16200;
			case 37:
				return 17800;
			case 38:
				return 19500;
			case 39:
				return 21300;
			case 40:
				return 23200;
			case 41:
				return 25200;
			case 42:
				return 27400;
			case 43:
				return 29800;
			case 44:
				return 32400;
			case 45:
				return 35200;
			case 46:
				return 38200;
			case 47:
				return 41400;
			case 48:
				return 44800;	
			case 49:
				return 48400;
			case 50:
				return 52200;
			case 51:
				return 56200;
			case 52:
				return 60400;
			case 53:
				return 64800;
			case 54:
				return 69400;
			case 55:
				return 74200;
			case 56:
				return 79200;
			case 57:
				return 84700;
			case 58:
				return 90700;
			case 59:
				return 97200;
			case 60:
				return 104200;
			case 61:
				return 111700;
			case 62:
				return 119700;
			case 63:
				return 128200;
			case 64:
				return 137200;
			case 65:
				return 146700;
			case 66:
				return 156700;
			case 67:
				return 167700;
			case 68:
				return 179700;
			case 69:
				return 192700;
			case 70:
				return 206700;
			case 71:
				return 221700;
			case 72:
				return 237700;
			case 73:
				return 254700;
			case 74:
				return 272700;
			case 75:
				return 291700;
			case 76:
				return 311700;
			case 77:
				return 333700;
			case 78:
				return 357700;
			case 79:
				return 383700;
			case 80:
				return 411700;
			case 81:
				return 441700;
			case 82:
				return 476700;
			case 83:
				return 516700;
			case 84:
				return 516700;
			case 85:
				return 611700;
			case 86:
				return 666700;
			case 87:
				return 726700;
			case 88:
				return 791700;
			case 89:
				return 861700;
			case 90:
				return 936700;
			case 91:
				return 1016700;
			case 92:
				return 1101700;
			case 93:
				return 1191700;
			case 94:
				return 1286700;
			case 95:
				return 1386700;
			case 96:
				return 1496700;
			case 97:
				return 1616700;
			case 98:
				return 1746700;
			case 99:
				return 1886700;	
			}
			return 1886700;
		default:
			break;
		
		}
		return 0;
	}
	

}
