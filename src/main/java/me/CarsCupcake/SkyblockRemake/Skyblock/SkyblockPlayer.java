package me.CarsCupcake.SkyblockRemake.Skyblock;

import java.util.ArrayList;
import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.PlayerHealthChangeEvent;
import me.CarsCupcake.SkyblockRemake.AuctionHouse.AuctionHouse;
import me.CarsCupcake.SkyblockRemake.Collections.CollectHandler;
import me.CarsCupcake.SkyblockRemake.Configs.*;
import me.CarsCupcake.SkyblockRemake.CrimsonIsle.CrimsonIsleAreas;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Equipment.EquipmentManager;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.abilitys.Deployable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.MiningSys;
import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Areas.DwarvenAreas;
import me.CarsCupcake.SkyblockRemake.Commission.Commission;
import me.CarsCupcake.SkyblockRemake.Commission.DwarvenCommissions;
import me.CarsCupcake.SkyblockRemake.Pets.Pet;
import net.minecraft.server.level.EntityPlayer;

public class SkyblockPlayer extends CraftPlayer{
	
	private static HashMap<Player, SkyblockPlayer> players = new HashMap<>();
	private final Player player;
	public double healingMulti = 1; 
	public int currhealth;
	public int currmana;
	
	public int basehealth;
	public int basemana;
	public int basedef;
	public int basespeed;
	public int basestrength;
	public int basecc;
	public double basecd;
	public float baseabilitydamage;
	public int baseferocity;
	public int basemagicfind;
	public int baseminingspeed;
	public int baseminingfortune;
	public double titaniumchance;
	public double basepristine;
	public int baseattackspeed;
	public int basetruedefense;
	public double baseseacreaturechance;
	public DwarvenAreas dwarvenArea;
	public CrimsonIsleAreas crimsonArea;
	public double coins;
	public double bits;
	public int mithrilpowder = 0;
	private SearchTopic searchTopic;
	private double additiveMultiplier = 1;
	private HashMap<Stats, Double> baseStat = new HashMap<>();
	
	public ArrayList<Commission> Comms = new ArrayList<>();
	public int commissionsSlots;
	
	public int absorbtion;
	@SuppressWarnings("unused")
	private int absorbtionrunntime;
	public boolean shortbow_cd;
	public int termhits;
	
	public boolean showMithrilPowder = false;
	private int mithrilpowderrunntime;
	private BukkitRunnable mithrilrun;

	//Full Set Bonus system
	public ItemManager helmet;
	public ItemManager chestplate;
	public ItemManager leggings;
	public ItemManager boots;
	public HashMap<Bonuses, Integer> bonusAmounts = new HashMap<>();
	public ArrayList<FullSetBonus> activeBonuses = new ArrayList<>();
	
	
	//Skills
	private Skill mining;
	private Skill combat;
	private Skill alchemy;
	private Skill foraging;
	private Skill fishing;
	
	
	public String defenseString = "";
	public boolean showDefenceString = false;
	private BukkitRunnable defenceStringRunnable;

	private double manaRegenMult = 1;
	
	private boolean hasDeployableEffect = false;

	private Deployable deployable;
	public int speedCap = 500;
	private double rawDamageMult = 1;
	private ArrayList<Bundle<Class<? extends AbilityManager>, AbilityType>> cooldowns = new ArrayList<>();

	public EquipmentManager equipmentManager = new EquipmentManager(this);
	private double baseTrophyFishChance;
	private final CustomConfig inventory;

	
	
	public SkyblockPlayer(CraftServer server, EntityPlayer entity) {
		super(server, entity);
		player = entity.getBukkitEntity().getPlayer();
		players.put(player, this);
		AbilityManager.additionalMana.put(this, new HashMap<>());
		manainjection(player);
		healthinjection(player);
		definjection(player);
		speedinjection(player);
		strengthinjection(player);
		cdinjection(player);
		ccinjection(player);
		abilitydamageinjection(player);
		ferocityinjection(player);
		magicfindinjection(player);
		miningspeedinjection(player);
		miningfortuneinjection(player);
		titaniumchanceinjection(player);
		coinsinjection(player);
		bitsinjection(player);
		mithrilpowderinjection(player);
		mpristineinjection(player);
		attackspeedinjection(player);
		truedefenseinjection(player);
		seacreaturechanceinjection(player);
		fishingspeedinjection(this);
		initTrophyFishChance();
		initCommission();
		initSkills();
		initHotM();
		equipmentManager.loadEquipment();
		CollectHandler.initPlayer(this);
		inventory = new CustomConfig("inv");
		loadInventory();
		new MiningSys(this);
		AbilityListener.checkArmor(this);
		SkyblockScoreboard.createScoreboard(this);
		new BukkitRunnable() {
			
			@Override
			public void run() {
				new TabListManager(SkyblockPlayer.this);
				
			}
		}.runTaskLater(Main.getMain(), 10);

		
		
		
		player.setPlayerListHeaderFooter("§bYou are Playing on §e§llocalhost:25565 \n ", " \n§a§lActive Effects§r \nNo Active Effects. Drink Potions or Splash\nthem on the ground to buff yourselfe!\n \n§d§lCookie Buff§r\nNot Active! Obtain booster cookies from the\ncommunity shop in the hub.\n \n§r§aRanks, Boosters & MORE! §c§lSTORE.HYPIXEL.NET");
	
	}
	private void loadInventory(){

		try{
			if (inventory.get().getConfigurationSection(getUniqueId().toString()) != null) {
				for (String k : inventory.get().getConfigurationSection(getUniqueId().toString()).getKeys(false)) {
					if (inventory.get().get(getUniqueId() + "." + k + ".pdc.id") == null) {
						getInventory().setItem(Integer.parseInt(k.split("_")[1]), null);
						continue;
					}
					String auctionPointer = getUniqueId() + "." + k;
					HashMap<String, String> map = new HashMap<>();
					if (inventory.get().getConfigurationSection(auctionPointer + ".pdc") != null)
						for (String t : inventory.get().getConfigurationSection(auctionPointer + ".pdc").getKeys(false)) {
							map.put(t, inventory.get().getString(auctionPointer + ".pdc." + t));
						}
					ItemStack item = buildItem(map, null);
					ItemMeta meta = item.getItemMeta();
					if (inventory.get().getConfigurationSection(auctionPointer + ".ench") != null)
						for (String t : inventory.get().getConfigurationSection(auctionPointer + ".ench").getKeys(false)) {
							NamespacedKey key;
							if (SkyblockEnchants.skyblockEnchantIds.contains(t)) {
								key = new NamespacedKey(Main.getMain(), t);
							} else {
								key = NamespacedKey.minecraft(t);
							}
							meta.addEnchant(Enchantment.getByKey(key), inventory.get().getInt(auctionPointer + ".ench." + t), false);
						}
					item.setItemMeta(meta);
					item = Main.item_updater(item, null);
					getInventory().setItem(Integer.parseInt(k.split("_")[1]), item);

				}
			} else
				getInventory().clear();
		}catch (Exception e){e.printStackTrace();}
		player.getInventory().setItem(8, Items.SkyblockMenu());player.updateInventory();
	}
	private ItemStack buildItem(HashMap<String, String> map, SkyblockPlayer player){
		ItemStack item = new ItemStack(Material.FEATHER);

		for(String str : map.keySet())
			if(str.equals("id"))
				item = Items.SkyblockItems.get(map.get(str)).getRawItemStack();
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer data = meta.getPersistentDataContainer();
		map.forEach((t, k)->{
			if(t.equals("dmg")){
				data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, k);
				return;
			}
			boolean isParaseble = false;
			try{
				int integer = Integer.parseInt(k);
				if(integer != 0){
					isParaseble = true;
					data.set(new NamespacedKey(Main.getMain(), t), PersistentDataType.INTEGER, integer);
				}
			}catch(Exception ignored){

			}
			if(!isParaseble){
				try{
					double doubl = Double.parseDouble(k);
					if(doubl != 0){
						isParaseble = true;
						data.set(new NamespacedKey(Main.getMain(), t), PersistentDataType.DOUBLE, doubl);
					}
				}catch(Exception ignored){

				}
			}
			if(!isParaseble){
				data.set(new NamespacedKey(Main.getMain(), t), PersistentDataType.STRING, k);
			}
		} );

		item.setItemMeta(meta);

		return Main.item_updater(item, player);

	}
	public void saveInventory(){
		int i = 0;
		for (ItemStack item : getInventory().getContents()){
			if(item == null || item.getType() == Material.AIR) {
				inventory.get().set(getUniqueId() + ".SLOT_" + i, "AIR");
				continue;
			}
			for(Enchantment enchantment : item.getItemMeta().getEnchants().keySet())
				inventory.get().set(getUniqueId() + ".SLOT_" + i + ".ench." + enchantment.getKey().getKey(), item.getItemMeta().getEnchants().get(enchantment));
			saveItem(i, getItemAsMap(item));
			i++;
		}
		inventory.save();
		inventory.reload();
	}
	private  HashMap<String, String> getItemAsMap(ItemStack item){
		PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
		HashMap<String, String> map = new HashMap<>();
		for(NamespacedKey key : data.getKeys()) {
			if(data.has(key, PersistentDataType.STRING))
				map.put(key.getKey(), data.get(key, PersistentDataType.STRING));
			if(data.has(key, PersistentDataType.DOUBLE))
				map.put(key.getKey(), data.get(key, PersistentDataType.DOUBLE).toString());
			if(data.has(key, PersistentDataType.FLOAT))
				map.put(key.getKey(), data.get(key, PersistentDataType.FLOAT).toString());
            /*if(data.has(key, PersistentDataType.INTEGER))
                map.put(key.getKey(), data.get(key, PersistentDataType.INTEGER).toString());*/

		}


		return map;
	}
	private void saveItem(int Pointer,HashMap<String, String> bundle){
		bundle.forEach((t,k) -> inventory.get().set(getUniqueId() + ".SLOT_" + Pointer + ".pdc." + t, k));
	}

	public void setSpeedCap(int i){
		speedCap = i;
	}
	public double getBaseTrophyFishChance(){
		return baseTrophyFishChance;
	}
	public void setBaseTrophyFishChance(double d){
		baseTrophyFishChance = d;
	}

	public double getAdititveMultiplier() {
		return additiveMultiplier;
		
	}
	public void addCooldown(Bundle<Class<? extends AbilityManager>, AbilityType> b){
		cooldowns.add(b);

	}
	public void removeCooldown(Bundle<Class<? extends AbilityManager>, AbilityType> b){

		cooldowns.remove(b);

	}
	public ArrayList<Bundle<Class<? extends AbilityManager>, AbilityType>> getCooldowns(){
		return cooldowns;
	}

	public void setRawDamageMult(double d){
		rawDamageMult = d;
	}
	public double getRawDamageMult(){
		return rawDamageMult;
	}

	public void setManaRegenMult(double d){
		manaRegenMult = d;
	}
	public double getManaRegenMult(){
		return manaRegenMult;
	}
	public void addManaRegenMult(double d){
		manaRegenMult += d;
	}

	public void setAdititveMultiplier(double value) {
		additiveMultiplier = value;
		
	}
	public boolean hasDeplayableEffect(){
		return hasDeployableEffect;
	}
	public void deployableEffect(boolean b){
		hasDeployableEffect = b;
	}

	public void setDeployable(Deployable deployable){
		this.deployable = deployable;
	}
	public Deployable getDeployable(){
		return deployable;
	}

	public void addAdititveMultiplier(double value) {
		additiveMultiplier += value;
	}
	public void initSkills() {
		SkillsSave.reload();
		for (FullSetBonus bonus : activeBonuses)
			bonus.stop();

		for(Skills skill : Skills.values()) {
			
				int level;
				level = SkillsSave.get().getInt(player.getUniqueId() + "." + skill.toString().toLowerCase() + ".level");

				
				 if(level == 0) {
					 SkillsSave.get().set(player.getUniqueId() + "." + skill.toString().toLowerCase() + ".level", 0);
					 SkillsSave.save();
					 
					
				 }
			
			SkillsSave.reload();
			
			
				double xp;
				xp = SkillsSave.get().getDouble(player.getUniqueId()+ "." + skill.toString().toLowerCase() + ".xp");
				 if(xp == 0) {
					 SkillsSave.get().set(player.getUniqueId() + "." + skill.toString().toLowerCase() + ".xp", 0D);
					 SkillsSave.save();
					 SkillsSave.reload();
						xp = 0;
				 
			
		}
				 }
		SkillsSave.reload();
		
		mining = Skill.getInstance(Skills.Mining, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Mining.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." +Skills.Mining.toString().toLowerCase() + ".xp"));
		combat = Skill.getInstance(Skills.Combat, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Combat.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." +Skills.Combat.toString().toLowerCase() + ".xp"));
		alchemy = Skill.getInstance(Skills.Alchemy, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Alchemy.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." +Skills.Alchemy.toString().toLowerCase() + ".xp"));
		foraging = Skill.getInstance(Skills.Foraging, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Foraging.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." +Skills.Foraging.toString().toLowerCase() + ".xp"));
		fishing = Skill.getInstance(Skills.Fishing, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Fishing.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." +Skills.Fishing.toString().toLowerCase() + ".xp"));
		int reaperPepperAmount = ExtraInformations.get().getInt(player.getUniqueId() + ".reaperpepper");
		setBaseStat(Stats.Health, reaperPepperAmount + basehealth);
		for (FullSetBonus bonus : activeBonuses) {
			bonus.start();
		}

	}
	
	public void addSkillXp(double amount, Skills skill) {
		Skill skil = getSkill(skill);
//		player.sendMessage("You got " + amount + " " + skill + " XP");
		
		
		if(amount == 0)
			return;
		player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,2);
		
		
		
		
		
		double currxp = skil.getXp();
		int level = skil.getLevel();
		boolean skip = level + 1 > skil.getMaxLevel();

		if(Skill.getNextLevelXp(level) <= currxp + amount && !skip) {
			double upxp = currxp + amount;
			while(upxp > 0) {
				
				if(upxp - Skill.getNextLevelXp(level) < 0) {
					skil.setXp(upxp);
					Main.updatebar(this);
					upxp = 0;
					continue;
				}
				if(level + 1 >= skil.getMaxLevel()) {
					skil.levelUp();
					skil.setLevel(skil.getMaxLevel());
					skil.setXp(upxp);
					
					Main.updatebar(this);

					
					upxp = 0;
					continue;

					}
				upxp -= Skill.getNextLevelXp(level);
				level += 1;
				skil.levelUp();
				
			}
		}else {
			skil.setXp(amount + currxp);
		}
		
		
		
		double calc = skil.getXp()/(double)Skill.getNextLevelXp(level);
				if(skil.getMaxLevel() != skil.getLevel())
				defenseString = "§3+" + amount + " " + skill + " (" + Tools.round(calc*100, 2) +"%)";
				else
					defenseString = "§3+" + amount + " " + skill;

				showDefenceString = true;
				hideDefenceString();
				Main.updatebar(this);
		Pet.addPetXP(this, amount, skill);
		
	}
	
	private void hideDefenceString() {
		
		try {
			defenceStringRunnable.cancel();
		} catch (Exception ignored) {
			
		}
		
		defenceStringRunnable = new BukkitRunnable() {
			
			@Override
			public void run() {
				showDefenceString = false;
				Main.updatebar(SkyblockPlayer.this);
				
			}
		};
		defenceStringRunnable.runTaskLater(Main.getMain(), 20*3);
		
	}
	public void setTempDefenceString(String str){
		defenseString = str;
		showDefenceString = true;
		Main.updatebar(this);
		hideDefenceString();
	}
	
	public Skill getSkill(Skills skill) {
		
		if(Skills.Mining == skill)
			return mining;
		if(Skills.Combat == skill)
			return combat;
		if(Skills.Foraging== skill)
			return foraging;
		if(Skills.Alchemy == skill)
			return alchemy;
		if(Skills.Fishing == skill)
			return fishing;
		
		return null;
	}
	public void addItem(ItemManager manager){
		addItem(manager, 1);
	}
	public void addItem(ItemManager manager, int amount){
		ItemStack item = manager.createNewItemStack();
		item = Main.item_updater(item, this);
		item.setAmount(amount);
		addItem(item);
	}
	public void addItem(ItemStack item){
		CollectHandler.collectItem(item, this);
		getInventory().addItem(item);
	}


	public static SkyblockPlayer getSkyblockPlayer(Player player) {
		
			return players.get(player);
		
	}
	public Player getPlayer() {
		return player;
	}
	
	public void setSearching(SearchTopic topic) {
	searchTopic = topic;
	}
	public boolean isSearching() {
		return searchTopic != null;
	}
	public SearchTopic getSearching() {
		return searchTopic;
	}
	
	
	private void initCommission() {
		MiningSystem.reload();
		if(!(MiningSystem.get().contains(super.getUniqueId().toString()))) {
			MiningSystem.get().set(getUniqueId() + ".commissions.slots", 2);
			MiningSystem.get().set(getUniqueId() + ".commissions.COM_0.type", Commission.generateNewConsistentCommision().getComm().toString());
			MiningSystem.get().set(getUniqueId() + ".commissions.COM_0.progress", 0);
			
			MiningSystem.get().set(getUniqueId() + ".commissions.COM_1.type", Commission.generateNewSituationCommision().getComm().toString());
			MiningSystem.get().set(getUniqueId() + ".commissions.COM_1.progress", 0);
			MiningSystem.save();
			MiningSystem.reload();
		}
		
		if(!(MiningSystem.get().contains(super.getUniqueId().toString() + ".comissions.completet"))) {
			MiningSystem.get().set(getUniqueId() + ".commissions.completet", 0);
			MiningSystem.save();
			MiningSystem.reload();
		}
		
		int slots = MiningSystem.get().getInt(getUniqueId() + ".commissions.slots");
		commissionsSlots = slots;
		for(int i = 0; i < slots; i++) {
			Commission com = new Commission(DwarvenCommissions.valueOf(MiningSystem.get().getString(getUniqueId() + ".commissions.COM_" + i + ".type")));
			com.setScore(MiningSystem.get().getInt(getUniqueId() + ".commissions.COM_" + i + ".progress"));
			Comms.add(com);
			
		}
		
	}
	private void initHotM(){
		if(!(MiningSystem.get().contains(getUniqueId() + ".hotm"))) {
			MiningSystem.get().set(getUniqueId() + ".hotm.exp", 0);
			MiningSystem.get().set(getUniqueId() + ".hotm.tokens", 0);
			MiningSystem.get().set(getUniqueId() + ".hotm.level", 0);
			MiningSystem.save();
			MiningSystem.reload();
		}
	}
	
	public void saveCommissionProgress() {
		int i = 0;
		for(Commission com : Comms) {
			MiningSystem.get().set(getUniqueId() + ".commissions.COM_" + i + ".type", com.getComm().toString());
			MiningSystem.get().set(getUniqueId() + ".commissions.COM_" + i + ".progress", com.getScore());
			MiningSystem.save();
			MiningSystem.reload();
			i++;
		}
	}
	
	
	public void setDwarvenArea(DwarvenAreas dwarvenArea) {
		this.dwarvenArea = dwarvenArea;
	}
	public void setCoins(double value) {

		coins = value;
		SkyblockScoreboard.updateScoreboard(this);
	}
	public void setBits(double value) {
		bits = value;
	}
	
	public void setArmorPiece(ItemType type, ItemManager manager) {
		
		switch(type) {
		case  Helmet: 
			helmet = manager;
			break;
		case Chestplate:
			chestplate = manager;
			break;
		case Leggings:
			leggings = manager;
			break;
		case Boots:
			boots = manager;
			break;
		default:
			break;
		}
		
	}
	
	
	public void setMithrilpowder(int value) {
		
		
		mithrilpowder = value;
		saveMithrilPowder();
		mithrilpowderrunntime = 2;
		showMithrilPowder = true;
		if(mithrilrun == null || mithrilrun.isCancelled())
			mithrilpowderRunner();
		SkyblockScoreboard.updateScoreboard(player);
		
		
		
	}
	
	public void addMithrilPowder(int amount) {
		if(amount != 0)
			setMithrilpowder(amount += mithrilpowder);
	}
	
	private void mithrilpowderRunner() {
		mithrilrun = new BukkitRunnable() {
			
			@Override
			public void run() {
				mithrilpowderrunntime -= 1;

				if(mithrilpowderrunntime == 0) {
					showMithrilPowder = false;
					SkyblockScoreboard.updateScoreboard(player);
					cancel();
				}else
					mithrilpowderRunner();
				
			}
		};
		mithrilrun.runTaskLater(Main.getMain(), 20L);
	}
	
	
	
	public void saveMithrilPowder() {
		ConfigFile.reload();
		ConfigFile.get().set(getUniqueId() +  ".mithrilpowder", mithrilpowder);
		ConfigFile.save();
		ConfigFile.reload();
	}
	
	public void setHealingMult(double d) {
		healingMulti = d;
		
	}
	public void addHealingMult(double d) {
		setHealingMult(d += healingMulti);
	}

	@Deprecated
	public void setHealth(int value) {
		setHealth(value, HealthChangeReason.Creative);
	}
	@Deprecated
	public void setHealth(double value) {
		setHealth(value, HealthChangeReason.Creative);
	}
	@Deprecated
	public void setHealth(float value) {
		setHealth(value, HealthChangeReason.Creative);
	}

	public void setHealth(int value, HealthChangeReason reason) {
		PlayerHealthChangeEvent event = new PlayerHealthChangeEvent(this, currhealth - value, reason);
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) return;

		currhealth = currhealth - event.getHelthChangeAmount();
		if(currhealth < 0)
			currhealth = 0;
		if(currhealth > Main.playerhealthcalc(this))
			currhealth = (int) Main.playerhealthcalc(this);

	}
	public void setHealth(double value, HealthChangeReason reason) {
		PlayerHealthChangeEvent event = new PlayerHealthChangeEvent(this, (int) (currhealth - value), reason);
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) return;

		currhealth = currhealth - event.getHelthChangeAmount();
		if(currhealth < 0)
			currhealth = 0;
		if(currhealth > Main.playerhealthcalc(this))
			currhealth = (int) Main.playerhealthcalc(this);
	}
	public void setHealth(float value, HealthChangeReason reason) {
		PlayerHealthChangeEvent event = new PlayerHealthChangeEvent(this, (int) (currhealth - value), reason);
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) return;

		currhealth =  currhealth - event.getHelthChangeAmount();
		if(currhealth < 0)
			currhealth = 0;
		if(currhealth > Main.playerhealthcalc(this))
			currhealth = (int) Main.playerhealthcalc(this);
	}
	public void setMana(int value) {
		currmana = value;
	}
	public void setBaseStat(Stats stat, double value) {
		switch (stat) {
		case AbilityDamage:
			baseabilitydamage = (float)value;
			break;
		case AttackSpeed:
			baseattackspeed = (int)value;
			break;
		case CritChance:
			basecc = (int)value;
			break;
		case CritDamage:
			basecd =value;
			break;
		case Defense:
			basedef = (int) value;
			break;
		case Ferocity:
			baseferocity = (int)value;
			break;
		case Health:
			basehealth = (int)value;
			break;
		case Inteligence:
			basemana = (int)value;
			break;
		case MagicFind:
			basemagicfind = (int)value;
			break;
		case MiningFortune:
			baseminingfortune = (int)value;
			break;
		case MiningSpeed:
			baseminingspeed = (int)value;
			break;
		case Pristine:
			basepristine = value;
			break;
		case Speed:
			basespeed = (int)value;
			break;
		case Strength:
			basestrength = (int)value;

			break;
			case TrueDefense:
				basetruedefense = (int)value;
				break;
			case SeaCreatureChance:
				baseseacreaturechance = value;
				break;
		default:
			baseStat.put(stat, value);
			break;
		
		}
	}
	public double getBaseStat(Stats stat){
		switch (stat){
			case AbilityDamage:
				return baseabilitydamage;
				
			case AttackSpeed:
				return baseattackspeed;
				
			case CritChance:
				return basecc ;
				
			case CritDamage:
				return basecd;
				
			case Defense:
				return basedef;
				
			case Ferocity:
				return baseferocity ;
				
			case Health:
				return basehealth ;
				
			case Inteligence:
				return basemana ;
				
			case MagicFind:
				return basemagicfind ;
				
			case MiningFortune:
				return baseminingfortune ;
				
			case MiningSpeed:
				return baseminingspeed ;
				
			case Pristine:
				return basepristine;
				
			case Speed:
				return basespeed ;
				
			case Strength:
				return basestrength ;

				
			case TrueDefense:
				return basetruedefense ;
				
			case SeaCreatureChance:
				return baseseacreaturechance;
				
			default:
				if(baseStat.get(stat) != null)
				return baseStat.get(stat);
				else
					return 0;
		}
	}
	
	public void setTitaniumChance(double value) {
		titaniumchance = value;
	}
	
	public void manainjection(Player player) {
		int filebasemana;
		try {
			 filebasemana = ConfigFile.get().getInt(player.getUniqueId().toString() + ".basemana");
			 if(filebasemana == 0) {
				 ConfigFile.get().set(player.getUniqueId().toString() + ".basemana", 100);
					ConfigFile.save();
					filebasemana = 100;
			 }
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".basemana", 100);
			ConfigFile.save();
			filebasemana = 100;
		}
			
			
			
			ConfigFile.reload();
		basemana=filebasemana;

		currmana = (int) Main.playermanacalc(player);
		
		
	}
	
	public  void healthinjection(Player player) {
		int filebasehealth;
		try {
			filebasehealth = ConfigFile.get().getInt(player.getUniqueId().toString() + ".basehealth");
			if(filebasehealth == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".basehealth", 100);
				ConfigFile.save();
				filebasehealth = 100;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".basehealth", 100);
			ConfigFile.save();
			filebasehealth = 100;
		}
			
			
			
			ConfigFile.reload();
		basehealth = filebasehealth;
		
		currhealth= (int) Main.playerhealthcalc(player);
		
	}
	public  void definjection(Player player) {
		int filebasedef;
		try {
			filebasedef = ConfigFile.get().getInt(player.getUniqueId().toString() + ".basedef");
			if(filebasedef == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".basedef", 0);
				ConfigFile.save();
				filebasedef = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".basedef", 0);
			ConfigFile.save();
			filebasedef = 0;
		}
			
			
		
			
			ConfigFile.reload();
		basedef=filebasedef;
		
		
	}
	public  void strengthinjection(Player player) {
		int filebasestrength;
		try {
			filebasestrength = ConfigFile.get().getInt(player.getUniqueId().toString() + ".basestrength");
			if(filebasestrength == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".basestrength", 0);
				ConfigFile.save();
				filebasestrength = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".basestrength", 0);
			ConfigFile.save();
			filebasestrength = 0;
		}
			
			
		
			
			ConfigFile.reload();
		basestrength=filebasestrength;
		
		
	}
	public  void ccinjection(Player player) {
		int filebasecc;
		try {
			filebasecc = ConfigFile.get().getInt(player.getUniqueId().toString() + ".basecc");
			if(filebasecc == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".basecc", 30);
				ConfigFile.save();
				filebasecc = 30;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".basecc", 30);
			ConfigFile.save();
			filebasecc = 30;
		}
			
			
		
			
			ConfigFile.reload();
		basecc= filebasecc;
		
		
	}
	public  void cdinjection(Player player) {
		int filebasecd;
		try {
			filebasecd = ConfigFile.get().getInt(player.getUniqueId().toString() + ".basecd");
			if(filebasecd == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".basecd", 50);
				ConfigFile.save();
				filebasecd = 50;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".basecd", 50);
			ConfigFile.save();
			filebasecd = 50;
		}
			
			
		
			
			ConfigFile.reload();
		basecd=filebasecd;
		
		
	}
	public  void speedinjection(Player player) {
		int filebasespeed;
		try {
			filebasespeed = ConfigFile.get().getInt(player.getUniqueId().toString() + ".basespeed");
			if(filebasespeed == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".basespeed", 100);
				ConfigFile.save();
				filebasespeed = 100;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".basespeed", 100);
			ConfigFile.save();
			filebasespeed = 100;
		}
			
			
			ConfigFile.reload();
		basespeed=filebasespeed;
		float speedpersentage = (float)Main.playerspeedcalc(player)/100;
		if (speedpersentage > 5)
			speedpersentage = 5;
			player.setWalkSpeed((float)0.2*(float)speedpersentage);
		
	}
	public  void abilitydamageinjection(Player player) {
		float filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getInt(player.getUniqueId().toString() + ".baseabilitydamage");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".baseabilitydamage", 0f);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".baseabilitydamage", 0f);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}
			
			
			ConfigFile.reload();
		baseabilitydamage=filebaseabilitydaamge;

		
		
		
	}
	public  void ferocityinjection(Player player) {
		int filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getInt(player.getUniqueId().toString() + ".baseferocity");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".baseferocity", 0);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".baseferocity", 0);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}
			
			
			ConfigFile.reload();
		baseferocity=filebaseabilitydaamge;

		
		
		
	}
	public  void magicfindinjection(Player player) {
		int filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getInt(player.getUniqueId().toString() + ".basemagicfind");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".basemagicfind", 0);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".basemagicfind", 0);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}
			
			
			ConfigFile.reload();
		basemagicfind=filebaseabilitydaamge;

		
		
		
	}
	public  void miningspeedinjection(Player player) {
		int filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getInt(player.getUniqueId().toString() + ".baseminingspeed");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".baseminingspeed", 0);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".baseminingspeed", 0);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}
			
			
			ConfigFile.reload();
			baseminingspeed=filebaseabilitydaamge;

		
		
		
	}
	public  void miningfortuneinjection(Player player) {
		int filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getInt(player.getUniqueId().toString() + ".baseminingfortune");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".baseminingfortune", 0);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".baseminingfortune", 0);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}
			
			
			ConfigFile.reload();
			baseminingfortune=filebaseabilitydaamge;

		
		
		
	}
	public  void titaniumchanceinjection(Player player) {
		double filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getDouble(player.getUniqueId().toString() + ".titaniumchance");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".titaniumchance", 2D);
				ConfigFile.save();
				filebaseabilitydaamge = 2;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".titaniumchance", 2D);
			ConfigFile.save();
			filebaseabilitydaamge = 2;
		}
			
			
			ConfigFile.reload();
			titaniumchance=filebaseabilitydaamge;

		
		
		
	}
	public  void coinsinjection(Player player) {
		double filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getDouble(player.getUniqueId().toString() + ".coins");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".coins", 0d);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".coins", 0d);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}
			
			
			ConfigFile.reload();
			coins=filebaseabilitydaamge;

		
		
		
	}
	public  void bitsinjection(Player player) {
		int filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getInt(player.getUniqueId().toString() + ".bits");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".bits", 0);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".bits", 0);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}
			
			
			ConfigFile.reload();
			bits=filebaseabilitydaamge;

		
		
		
	}
	public  void mithrilpowderinjection(Player player) {
		int filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getInt(player.getUniqueId().toString() + ".mithrilpowder");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".mithrilpowder", 0);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".mithrilpowder", 0);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}
			
			
			ConfigFile.reload();
			mithrilpowder =filebaseabilitydaamge;

		
		
		
	}
	public  void mpristineinjection(Player player) {
		double filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getDouble(player.getUniqueId().toString() + ".pristine");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".pristine", 0);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".pristine", 0);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}
			
			
			ConfigFile.reload();
			basepristine =filebaseabilitydaamge;

		
		
		
	}
	public  void attackspeedinjection(Player player) {
		int filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getInt(player.getUniqueId().toString() + ".as");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".as", 0);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".as", 0);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}
			
			
			ConfigFile.reload();
			baseattackspeed=filebaseabilitydaamge;

		
		
		
	}
	public  void truedefenseinjection(Player player) {
		int filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getInt(player.getUniqueId().toString() + ".truedefense");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".truedefense", 0);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".truedefense", 0);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}


		ConfigFile.reload();
		basetruedefense=filebaseabilitydaamge;




	}
	public  void seacreaturechanceinjection(Player player) {
		double filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getDouble(player.getUniqueId().toString() + ".seacreaturechance");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".seacreaturechance", 20D);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".seacreaturechance",20D);
			ConfigFile.save();
			filebaseabilitydaamge = 20;
		}


		ConfigFile.reload();
		baseseacreaturechance=filebaseabilitydaamge;




	}
	public  void initTrophyFishChance() {
		double filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getDouble(player.getUniqueId().toString() + ".trophyfishchance");
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + ".trophyfishchance", 1D);
				ConfigFile.save();
				filebaseabilitydaamge = 1;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + ".trophyfishchance",1D);
			ConfigFile.save();
			filebaseabilitydaamge = 10;
		}


		ConfigFile.reload();
		baseTrophyFishChance=filebaseabilitydaamge;




	}
	private  void fishingspeedinjection(Player player) {
		double filebaseabilitydaamge;
		try {
			filebaseabilitydaamge = ConfigFile.get().getDouble(player.getUniqueId().toString() + "." + Stats.FishingSpeed.getDataName());
			if(filebaseabilitydaamge == 0) {
				ConfigFile.get().set(player.getUniqueId().toString() + "." + Stats.FishingSpeed.getDataName(), 0D);
				ConfigFile.save();
				filebaseabilitydaamge = 0;
			}
		}catch(Exception e) {
			ConfigFile.get().set(player.getUniqueId().toString() + "." + Stats.FishingSpeed.getDataName(),0);
			ConfigFile.save();
			filebaseabilitydaamge = 0;
		}


		ConfigFile.reload();
		setBaseStat(Stats.FishingSpeed, filebaseabilitydaamge);




	}
	
}
