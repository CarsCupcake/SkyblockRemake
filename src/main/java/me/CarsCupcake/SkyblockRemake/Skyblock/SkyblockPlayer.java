package me.CarsCupcake.SkyblockRemake.Skyblock;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.PlayerHealthChangeEvent;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Items.Pets.PetEquip;
import me.CarsCupcake.SkyblockRemake.Items.Pets.PetFollowRunner;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.DialogBuilder;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.Elections;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.MythologicalPerk;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.ArtifactAbility;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.CollectHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.ICollection;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Commission.Commission;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Commission.DwarvenCommissions;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Equipment.EquipmentManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.Effect;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.levels.SkyblockLevelsHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.Region;
import me.CarsCupcake.SkyblockRemake.Skyblock.scoreboard.ScoreboardSection;
import me.CarsCupcake.SkyblockRemake.abilities.Deployable;
import me.CarsCupcake.SkyblockRemake.abilities.SuperCompactor;
import me.CarsCupcake.SkyblockRemake.configs.ConfigFile;
import me.CarsCupcake.SkyblockRemake.configs.ExtraInformations;
import me.CarsCupcake.SkyblockRemake.configs.MiningSystem;
import me.CarsCupcake.SkyblockRemake.isles.Areas.DwarvenAreas;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.CrimsonIsle;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.CrimsonIsleAreas;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.MiningSys;
import me.CarsCupcake.SkyblockRemake.isles.privateIsle.PrivateIsle;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class SkyblockPlayer extends CraftPlayer {

    private static HashMap<Player, SkyblockPlayer> players = new HashMap<>();
    private final Player player;
    @Getter
    private final List<ScoreboardSection> scoreboardSections = new ArrayList<>();
    public double healingMulti = 1;
    public int currhealth;
    public double currmana;
    public double titaniumchance;
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
    private Skill enchanting;
    private Skill farming;
    private Skill taming;


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
    private final ConfigFile inventory;
    @Getter
    private int magicalpower = 0;
    @Getter
    private final SortedSet<Effect> activeEffects = new TreeSet<>((o1, o2) -> o1.name().compareTo(o2.name()));
    private final ConfigFile statsConfig = new ConfigFile(this, "stats");
    @Getter
    @Setter
    private boolean autoPickup = false;

    @Getter
    @Setter
    private int skyblockLevel = 0;

    @Getter
    @Setter
    private int skyblockLevelXp = 1;
    @Getter
    @Setter
    private DialogBuilder.DialogRunner dialog;
    @Getter
    private final List<String> timeCharms;
    @Getter
    @Setter
    private Region region;
    @Getter
    @Setter
    private PetEquip petEquip;
    @Getter
    @Setter
    private PetFollowRunner petFollowRunner;
    @Getter
    @Setter
    private ConfigFile generalConfig;
    private ConfigFile accessoryBagFile;

    public SkyblockPlayer(CraftServer server, EntityPlayer entity) {
        this(server, entity, false);
    }

    public SkyblockPlayer(CraftServer server, EntityPlayer entity, boolean isChecked) {
        super(server, entity);
        inventory = new ConfigFile(this, ((this instanceof RiftPlayer) ? "rInv" : "inventory"));
        player = entity.getBukkitEntity().getPlayer();
        ConfigFile riftMainStoryFile = new ConfigFile(this, "\\rift\\main", true);
        List<String> sl = null;
        try {
            sl = riftMainStoryFile.get().getStringList("timecharm");
        } catch (Exception ignored) {
        }
        if (sl == null) timeCharms = new ArrayList<>();
        else timeCharms = sl;
        if (ServerType.getActiveType() == ServerType.Rift && !isChecked) {
            new RiftPlayer(server, entity);
            return;
        }
        //Check for 1.17.1 (https://wiki.vg/Protocol_version_numbers)
        if (Bukkit.getPluginManager().isPluginEnabled("ViaVersion")) {
            this.sendTitle("§a1.17.1 is recomendet!", "I suggest using 1.17.1 for less buggs");
            this.sendMessage("§cThe plugin is made for Version 1.17.1, other versions could bug out and break some system's!");
        }
        autoPickup = entity.displayName.equals("CarsCupcake");

        players.put(player, this);
        generalConfig = new ConfigFile(this, "general");
        AbilityManager.additionalMana.put(this, new HashMap<>());
        for (Stats stat : Stats.values()) {
            if (stat == Stats.WeaponDamage) continue;
            double value = statsConfig.get().getDouble(stat.getDataName(), stat.getBaseAmount());
            if (stat.getMaxAmount() > 0 && value > stat.getMaxAmount()) value = stat.getMaxAmount();
            setBaseStat(stat, value);
        }
        titaniumchanceinjection(player);
        coinsinjection(player);
        bitsinjection(player);
        mithrilpowderinjection(player);
        initTrophyFishChance();
        initCommission();
        initSkills();
        initHotM();

        equipmentManager.loadEquipment();
        CollectHandler.initPlayer(this);
        loadInventory();
        new MiningSys(this);
        AbilityListener.checkArmor(this);
        initAccessoryBag();
        //Last things to load!
        SkyblockScoreboard.createScoreboard(this);
        SkyblockLevelsHandler.initGetters(this);
        SkyblockLevelsHandler.claculate(this);
        SkyblockLevelsHandler.addStats(this);
        currmana = (int) Main.getPlayerStat(this, Stats.Inteligence);
        currhealth = (int) Main.getPlayerStat(this, Stats.Health);
        new BukkitRunnable() {

            @Override
            public void run() {
                new TabListManager(SkyblockPlayer.this);

            }
        }.runTaskLater(Main.getMain(), 10);
        Effect.load(this);
        initQuest();
        if (Elections.major.hasPerk(3) && ServerType.getActiveType() == ServerType.Hub) {
            synchronized (Main.getMain()) {
                new MythologicalPerk(this);
            }
        }
        ConfigFile file = new ConfigFile(this, "pet", true);
        int petId = file.get().getInt("equiped", 0);
        if (petId != 0) {
            Pet pet = (Pet) Items.SkyblockItems.get(file.get().getString(petId + ".id"));
            new PetFollowRunner(this, pet, petId);
            new PetEquip(this);
        }
        player.setPlayerListHeaderFooter("§bYou are Playing on §e§l" + Main.getMain().getServer().getIp() + " \n ", " \n§a§lActive Effects§r \nNo Active Effects. Drink Potions or Splash\nthem on the ground to buff yourselfe!\n \n§d§lCookie Buff§r\nNot Active! Obtain booster cookies from the\ncommunity shop in the hub.\n \n§r§aRanks, Boosters & MORE! §c§lSTORE.HYPIXEL.NET");
    }

    public static void init() {

    }

    public void unregister() {
        CollectHandler.collections.get(this).forEach(ICollection::save);
        try {
            if (ServerType.getActiveType() == ServerType.Hub) MythologicalPerk.getPlayer(this).getRunnable().cancel();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        if (getPetFollowRunner() != null) getPetFollowRunner().remove();
        if (getPetEquip() != null) getPetEquip().despawn(true);
        for (Stats stats : Stats.values()) {
            if (stats == Stats.WeaponDamage) continue;
            statsConfig.get().set(stats.getDataName(), getBaseStat(stats));
        }
        statsConfig.save(Main.getMain().isEnabled());
        saveAccessoryBag();
    }

    public void kill() {
        Slayer possibleSlayer = Slayer.getSlayer(this);
        if (possibleSlayer != null) SkyblockEntity.killEntity(possibleSlayer, null);
    }

    public void initQuest() {
        Main.getDebug().debug("Registering " + player.getName() + " to Quests", false);
        if (ServerType.getActiveType() == ServerType.CrimsonIsle) {
            CrimsonIsle.init(this);
        }
    }

    public PrivateIsle getPrivateIsle() {
        Assert.isTrue(SkyblockServer.getServer().type() == ServerType.PrivateIsle);
        return PrivateIsle.isles.get(this);
    }

    private void loadInventory() {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getMain(), () -> {
            getInventory().clear();
            inventory.reload();
            try {
                if (inventory.get().getConfigurationSection("") != null) {
                    for (String k : inventory.get().getKeys(false)) {
                        int i = Integer.parseInt(k);
                        ItemStack item = inventory.get().getItemStack(k);
                        new BukkitRunnable() {
                            public void run() {
                                Main.itemUpdater(item, SkyblockPlayer.this);
                                player.getInventory().setItem(i, item);
                            }
                        }.runTask(Main.getMain());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            player.getInventory().setItem(8, Items.SkyblockMenu());
            player.updateInventory();
        });

    }

    public void saveInventory() {
        if (Main.getMain().isEnabled()) Bukkit.getScheduler().runTaskAsynchronously(Main.getMain(), () -> {
            saveInventory0();
        });
        else saveInventory0();
    }

    private void saveInventory0() {
        inventory.clear();
        inventory.save();
        inventory.reload();
        for (int i = 0; i < 40; i++) {
            ItemStack item = this.getInventory().getItem(i);
            if (item != null && item.getType() != Material.AIR)
                inventory.get().set(i + "", this.getInventory().getItem(i));
        }
        inventory.save();
        inventory.reload();
    }

    private HashMap<String, String> getItemAsMap(ItemStack item) {
        PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
        HashMap<String, String> map = new HashMap<>();
        for (NamespacedKey key : data.getKeys()) {
            if (data.has(key, PersistentDataType.STRING))
                map.put(key.getKey(), data.get(key, PersistentDataType.STRING));
            else if (data.has(key, PersistentDataType.DOUBLE))
                map.put(key.getKey(), data.get(key, PersistentDataType.DOUBLE).toString());
            else if (data.has(key, PersistentDataType.FLOAT))
                map.put(key.getKey(), data.get(key, PersistentDataType.FLOAT).toString());
            else if (data.has(key, PersistentDataType.INTEGER))
                map.put(key.getKey(), data.get(key, PersistentDataType.INTEGER).toString());

        }


        return map;
    }

    private void saveItem(int Pointer, HashMap<String, String> bundle) {
        bundle.forEach((t, k) -> inventory.get().set(getUniqueId() + ".SLOT_" + Pointer + ".pdc." + t, k));
    }

    public void setSpeedCap(int i) {
        speedCap = i;
    }

    public double getBaseTrophyFishChance() {
        return baseTrophyFishChance;
    }

    public void setBaseTrophyFishChance(double d) {
        baseTrophyFishChance = d;
    }

    public double getAdititveMultiplier() {
        return additiveMultiplier;

    }

    public void addCooldown(Bundle<Class<? extends AbilityManager>, AbilityType> b) {
        cooldowns.add(b);

    }

    public void removeCooldown(Bundle<Class<? extends AbilityManager>, AbilityType> b) {

        cooldowns.remove(b);

    }

    public ArrayList<Bundle<Class<? extends AbilityManager>, AbilityType>> getCooldowns() {
        return cooldowns;
    }

    public void setRawDamageMult(double d) {
        rawDamageMult = d;
    }

    public double getRawDamageMult() {
        return rawDamageMult;
    }

    public void setManaRegenMult(double d) {
        manaRegenMult = d;
    }

    public double getManaRegenMult() {
        return manaRegenMult;
    }

    public void addManaRegenMult(double d) {
        manaRegenMult += d;
    }

    public void setAdititveMultiplier(double value) {
        additiveMultiplier = value;

    }

    public boolean hasDeplayableEffect() {
        return hasDeployableEffect;
    }

    public void deployableEffect(boolean b) {
        hasDeployableEffect = b;
    }

    public void setDeployable(Deployable deployable) {
        this.deployable = deployable;
    }

    public Deployable getDeployable() {
        return deployable;
    }

    public void addAdititveMultiplier(double value) {
        additiveMultiplier += value;
    }

    private final ConfigFile SkillsSave = new ConfigFile(this, "Skills");

    public void initSkills() {
        SkillsSave.reload();
        for (FullSetBonus bonus : activeBonuses)
            bonus.stop();

        for (Skills skill : Skills.values()) {

            int level;
            level = SkillsSave.get().getInt(player.getUniqueId() + "." + skill.toString().toLowerCase() + ".level");


            if (level == 0) {
                SkillsSave.get().set(player.getUniqueId() + "." + skill.toString().toLowerCase() + ".level", 0);
                SkillsSave.save();


            }

            SkillsSave.reload();


            double xp;
            xp = SkillsSave.get().getDouble(player.getUniqueId() + "." + skill.toString().toLowerCase() + ".xp");
            if (xp == 0) {
                SkillsSave.get().set(player.getUniqueId() + "." + skill.toString().toLowerCase() + ".xp", 0D);
                SkillsSave.save();
                SkillsSave.reload();
                xp = 0;


            }
        }
        SkillsSave.reload();

        mining = Skill.getInstance(Skills.Mining, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Mining.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." + Skills.Mining.toString().toLowerCase() + ".xp"));
        combat = Skill.getInstance(Skills.Combat, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Combat.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." + Skills.Combat.toString().toLowerCase() + ".xp"));
        alchemy = Skill.getInstance(Skills.Alchemy, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Alchemy.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." + Skills.Alchemy.toString().toLowerCase() + ".xp"));
        foraging = Skill.getInstance(Skills.Foraging, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Foraging.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." + Skills.Foraging.toString().toLowerCase() + ".xp"));
        fishing = Skill.getInstance(Skills.Fishing, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Fishing.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." + Skills.Fishing.toString().toLowerCase() + ".xp"));
        enchanting = Skill.getInstance(Skills.Enchanting, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Enchanting.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." + Skills.Enchanting.toString().toLowerCase() + ".xp"));
        farming = Skill.getInstance(Skills.Farming, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Farming.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." + Skills.Farming.toString().toLowerCase() + ".xp"));
        taming = Skill.getInstance(Skills.Taming, this, SkillsSave.get().getInt(player.getUniqueId().toString() + "." + Skills.Taming.toString().toLowerCase() + ".level"), SkillsSave.get().getDouble(player.getUniqueId().toString() + "." + Skills.Taming.toString().toLowerCase() + ".xp"));
        int reaperPepperAmount = ExtraInformations.get().getInt(player.getUniqueId() + ".reaperpepper");
        setBaseStat(Stats.Health, reaperPepperAmount + getBaseStat(Stats.Health));
        for (FullSetBonus bonus : activeBonuses) {
            bonus.start();
        }

    }

    public void addSkillXp(double amount, Skills skill) {
        Skill skil = getSkill(skill);
        if (amount == 0) return;
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);


        if (skil == null) return;
        double currxp = skil.getXp();
        int level = skil.getLevel();
        boolean skip = level + 1 > skil.getMaxLevel();

        if (Skill.getNextLevelXp(level) <= currxp + amount && !skip) {
            double upxp = currxp + amount;
            while (upxp > 0) {

                if (upxp - Skill.getNextLevelXp(level) < 0) {
                    skil.setXp(upxp);
                    Main.updatebar(this);
                    upxp = 0;
                    continue;
                }
                if (level + 1 >= skil.getMaxLevel()) {
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
        } else {
            skil.setXp(amount + currxp);
        }


        double calc = skil.getXp() / (double) Skill.getNextLevelXp(level);
        if (skil.getMaxLevel() != skil.getLevel())
            defenseString = "§3+" + amount + " " + skill + " (" + Tools.round(calc * 100, 2) + "%)";
        else defenseString = "§3+" + amount + " " + skill;

        showDefenceString = true;
        hideDefenceString();
        Main.updatebar(this);
        if (skill == Skills.Mining || skill == Skills.Fishing || skill == Skills.Combat || skill == Skills.Farming || skill == Skills.Foraging || skill == Skills.Enchanting || skill == Skills.Alchemy)
            getPetEquip().addPetXP(amount, skill);

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
        defenceStringRunnable.runTaskLater(Main.getMain(), 20 * 3);

    }

    public void setTempDefenceString(String str) {
        defenseString = str;
        showDefenceString = true;
        Main.updatebar(this);
        hideDefenceString();
    }

    public Skill getSkill(Skills skill) {
        switch (skill) {
            case Farming -> {
                return farming;
            }
            case Fishing -> {
                return fishing;
            }
            case Enchanting -> {
                return enchanting;
            }
            case Taming -> {
                return taming;
            }
            case Alchemy -> {
                return alchemy;
            }
            case Carpentry -> {
                return null;
            }
            case Combat -> {
                return combat;
            }
            case Dungeoneering -> {
                return null;
            }
            case Foraging -> {
                return foraging;
            }
            case Mining -> {
                return mining;
            }
            case Runecrafting -> {
                return null;
            }
            case Social -> {
                return null;
            }
        }
        return null;
    }

    public void addItem(ItemManager manager) {
        addItem(manager, 1);
    }

    public void addItem(ItemManager manager, int amount) {
        ItemStack item = manager.createNewItemStack();
        item = Main.itemUpdater(item, this);
        item.setAmount(amount);
        addItem(item);
    }

    public void addItem(ItemStack item) {
        addItem(item, true);
    }

    public void addItem(ItemStack item, boolean countAsCollection) {
        if (countAsCollection) CollectHandler.collectItem(item, this);
        getInventory().addItem(item);
        String id = ItemHandler.getPDC("id", item, PersistentDataType.STRING);
        if (SuperCompactor.active.containsKey(this)) {
            for (SuperCompactor.SuperCompactorRecipe recipe : SuperCompactor.active.get(this))
                if (recipe.base().equals(id)) recipe.check(this);
        }
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
        try {
            MiningSystem.reload();
            if (!(MiningSystem.get().contains(super.getUniqueId().toString()))) {
                MiningSystem.get().set(getUniqueId() + ".commissions.slots", 2);
                MiningSystem.get().set(getUniqueId() + ".commissions.COM_0.type", Commission.generateNewConsistentCommision().getComm().toString());
                MiningSystem.get().set(getUniqueId() + ".commissions.COM_0.progress", 0);

                MiningSystem.get().set(getUniqueId() + ".commissions.COM_1.type", Commission.generateNewSituationCommision().getComm().toString());
                MiningSystem.get().set(getUniqueId() + ".commissions.COM_1.progress", 0);
                MiningSystem.save();
                MiningSystem.reload();
            }

            if (!(MiningSystem.get().contains(super.getUniqueId().toString() + ".comissions.completet"))) {
                MiningSystem.get().set(getUniqueId() + ".commissions.completet", 0);
                MiningSystem.save();
                MiningSystem.reload();
            }

            int slots = MiningSystem.get().getInt(getUniqueId() + ".commissions.slots");
            commissionsSlots = slots;
            for (int i = 0; i < slots; i++) {
                Commission com = new Commission(DwarvenCommissions.valueOf(MiningSystem.get().getString(getUniqueId() + ".commissions.COM_" + i + ".type")));
                com.setScore(MiningSystem.get().getInt(getUniqueId() + ".commissions.COM_" + i + ".progress"));
                Comms.add(com);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initHotM() {
        if (!(MiningSystem.get().contains(getUniqueId() + ".hotm"))) {
            MiningSystem.get().set(getUniqueId() + ".hotm.exp", 0);
            MiningSystem.get().set(getUniqueId() + ".hotm.tokens", 0);
            MiningSystem.get().set(getUniqueId() + ".hotm.level", 0);
            MiningSystem.save();
            MiningSystem.reload();
        }
    }

    public void saveCommissionProgress() {
        int i = 0;
        for (Commission com : Comms) {
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

        switch (type) {
            case Helmet:
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
        if (mithrilrun == null || mithrilrun.isCancelled()) mithrilpowderRunner();
        SkyblockScoreboard.updateScoreboard(SkyblockPlayer.getSkyblockPlayer(player));


    }

    public void addMithrilPowder(int amount) {
        if (amount != 0) setMithrilpowder(amount += mithrilpowder);
    }

    private void mithrilpowderRunner() {
        mithrilrun = new BukkitRunnable() {

            @Override
            public void run() {
                mithrilpowderrunntime -= 1;

                if (mithrilpowderrunntime == 0) {
                    showMithrilPowder = false;
                    SkyblockScoreboard.updateScoreboard(SkyblockPlayer.getSkyblockPlayer(player));
                    cancel();
                } else mithrilpowderRunner();

            }
        };
        mithrilrun.runTaskLater(Main.getMain(), 20L);
    }


    public void saveMithrilPowder() {
        statsConfig.reload();
        statsConfig.get().set(getUniqueId() + ".mithrilpowder", mithrilpowder);
        statsConfig.save();
        statsConfig.reload();
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
        setHealth((double) value, reason);

    }

    public void setHealth(double value, HealthChangeReason reason) {
        if (reason != HealthChangeReason.Force) {
            PlayerHealthChangeEvent event = new PlayerHealthChangeEvent(this, (int) (currhealth - value), reason);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;
            currhealth = currhealth - event.getHelthChangeAmount();
        } else currhealth = (int) value;
        if (currhealth < 0) currhealth = 0;
        double maxHealth = Main.getPlayerStat(this, Stats.Health);
        if (currhealth > maxHealth) currhealth = (int) maxHealth;
        Main.updatebar(this);
    }

    public void setHealth(float value, HealthChangeReason reason) {
        setHealth((double) value, reason);
    }

    public void setMana(double value) {
        currmana = value;
        double max = Main.getPlayerStat(this, (ServerType.getActiveType() == ServerType.Rift) ? Stats.RiftInteligence : Stats.Inteligence);
        if (currmana > max) currmana = max;
    }

    public void addBaseStat(Stats stat, double v) {
        setBaseStat(stat, v + getBaseStat(stat));
    }

    public void setBaseStat(Stats stat, double value) {
        baseStat.put(stat, value);
    }

    public double getBaseStat(Stats stat) {
        return baseStat.getOrDefault(stat, 0d);
    }

    public void setTitaniumChance(double value) {
        titaniumchance = value;
    }

    public void titaniumchanceinjection(Player player) {
        double filebaseabilitydaamge;
        try {
            filebaseabilitydaamge = statsConfig.get().getDouble(player.getUniqueId().toString() + ".titaniumchance");
            if (filebaseabilitydaamge == 0) {
                statsConfig.get().set(player.getUniqueId().toString() + ".titaniumchance", 2D);
                statsConfig.save();
                filebaseabilitydaamge = 2;
            }
        } catch (Exception e) {
            statsConfig.get().set(player.getUniqueId().toString() + ".titaniumchance", 2D);
            statsConfig.save();
            filebaseabilitydaamge = 2;
        }


        statsConfig.reload();
        titaniumchance = filebaseabilitydaamge;


    }

    public void coinsinjection(Player player) {
        double filebaseabilitydaamge;
        try {
            filebaseabilitydaamge = statsConfig.get().getDouble(player.getUniqueId().toString() + ".coins");
            if (filebaseabilitydaamge == 0) {
                statsConfig.get().set(player.getUniqueId().toString() + ".coins", 0d);
                statsConfig.save();
                filebaseabilitydaamge = 0;
            }
        } catch (Exception e) {
            statsConfig.get().set(player.getUniqueId().toString() + ".coins", 0d);
            statsConfig.save();
            filebaseabilitydaamge = 0;
        }


        statsConfig.reload();
        coins = filebaseabilitydaamge;


    }

    public void bitsinjection(Player player) {
        int filebaseabilitydaamge;
        try {
            filebaseabilitydaamge = statsConfig.get().getInt(player.getUniqueId().toString() + ".bits");
            if (filebaseabilitydaamge == 0) {
                statsConfig.get().set(player.getUniqueId().toString() + ".bits", 0);
                statsConfig.save();
                filebaseabilitydaamge = 0;
            }
        } catch (Exception e) {
            statsConfig.get().set(player.getUniqueId().toString() + ".bits", 0);
            statsConfig.save();
            filebaseabilitydaamge = 0;
        }


        statsConfig.reload();
        bits = filebaseabilitydaamge;


    }

    public void mithrilpowderinjection(Player player) {
        int filebaseabilitydaamge;
        try {
            filebaseabilitydaamge = statsConfig.get().getInt(player.getUniqueId().toString() + ".mithrilpowder");
            if (filebaseabilitydaamge == 0) {
                statsConfig.get().set(player.getUniqueId().toString() + ".mithrilpowder", 0);
                statsConfig.save();
                filebaseabilitydaamge = 0;
            }
        } catch (Exception e) {
            statsConfig.get().set(player.getUniqueId().toString() + ".mithrilpowder", 0);
            statsConfig.save();
            filebaseabilitydaamge = 0;
        }


        statsConfig.reload();
        mithrilpowder = filebaseabilitydaamge;


    }

    public void initTrophyFishChance() {
        double filebaseabilitydaamge;
        try {
            filebaseabilitydaamge = statsConfig.get().getDouble(player.getUniqueId().toString() + ".trophyfishchance");
            if (filebaseabilitydaamge == 0) {
                statsConfig.get().set(player.getUniqueId().toString() + ".trophyfishchance", 1D);
                statsConfig.save();
                filebaseabilitydaamge = 1;
            }
        } catch (Exception e) {
            statsConfig.get().set(player.getUniqueId().toString() + ".trophyfishchance", 1D);
            statsConfig.save();
            filebaseabilitydaamge = 10;
        }


        statsConfig.reload();
        baseTrophyFishChance = filebaseabilitydaamge;


    }
    @Getter
    private List<ItemStack> accessoryBag = new ArrayList<>();
    @Getter
    @Setter
    private int maxAccessoryBagSlots = 0;
    public void initAccessoryBag() {
        maxAccessoryBagSlots = generalConfig.get().getInt("accessoryBagSlots", 0);
        accessoryBagFile = new ConfigFile(this, "accessorybag");

        if (accessoryBagFile.get().getKeys(false).isEmpty()) {
            return;
        }
        for (Object o : accessoryBagFile.get().getList("", new ArrayList<>())) {
            if (o instanceof ItemStack item) accessoryBag.add(item);
            else sendMessage("An Error Occured while loading you Accessory Bag!\n" + o.toString());
        }
        updateAccessoryBag();
    }
    Set<ArtifactAbility> accessoryAbilities = new HashSet<>();

    private void updateAccessoryBag() {
        int totmagpow = 0;
        if (!accessoryBag.isEmpty()) {
            Set<String> alreadyCalculatedIds = new HashSet<>();
            Map<String, ItemRarity> accessoryIds = new HashMap<>();
            Map<ArtifactAbility, ItemStack> artifactBaseAbility = new HashMap<>();
            Map<String, Bundle<ArtifactAbility, ItemStack>> artifactIdAbilities = new HashMap<>();
            for (ItemStack item : accessoryBag) {
                ItemManager base = ItemHandler.getItemManager(item);
                ItemRarity rarity = base.getRarity(item, this);
                if (alreadyCalculatedIds.contains(base.itemID)) continue;
                alreadyCalculatedIds.add(base.itemID);
                int magicalpower = 0;
                if (base.getAccessoryId() != null) {
                    ItemRarity otherRarity = accessoryIds.get(base.getAccessoryId());
                    if (otherRarity != null) {
                        if (getMagicalPower(otherRarity) < getMagicalPower(rarity)) {
                            magicalpower -= getMagicalPower(otherRarity);
                            accessoryIds.put(base.getAccessoryId(), rarity);
                            artifactIdAbilities.remove(base.getAccessoryId());
                            if (base.getArtifactAbility() != null) artifactIdAbilities.put(base.getAccessoryId(), new Bundle<>(base.getArtifactAbility(), item));
                        }
                    } else {
                        accessoryIds.put(base.getAccessoryId(), rarity);
                        if (base.getArtifactAbility() != null) artifactIdAbilities.put(base.getAccessoryId(), new Bundle<>(base.getArtifactAbility(), item));
                    }
                } else {
                    if (base.getArtifactAbility() != null) artifactBaseAbility.put(base.getArtifactAbility(), item);
                }
                totmagpow += magicalpower + getMagicalPower(rarity);
            }
            for (Map.Entry<ArtifactAbility, ItemStack> entry : artifactBaseAbility.entrySet()) entry.getKey().start(this, entry.getValue());
            for (Bundle<ArtifactAbility, ItemStack> entry : artifactIdAbilities.values()) entry.getFirst().start(this, entry.getLast());
        }
        magicalpower = totmagpow;
    }

    public void swapAccessoryBag(List<ItemStack> items) {
        for(ArtifactAbility ability : accessoryAbilities) ability.stop(this);
        accessoryBag = items;
        updateAccessoryBag();
    }

    private int getMagicalPower(ItemRarity rarity) {
        return switch (rarity) {
            case COMMON, SPECIAL -> 3;
            case DIVINE, MYTHIC -> 22;
            case EPIC -> 12;
            case LEGENDARY -> 16;
            case UNCOMMON, SUPREME, VERY_SPECIAL -> 5;
            case RARE -> 8;
            default -> 0;
        };
    }

    public void saveAccessoryBag() {
        accessoryBagFile.get().set("", accessoryBag);
        accessoryBagFile.save();
    }

    public void sendPacket(Packet<?> packet) {
        getHandle().b.sendPacket(packet);
    }

    public String coinsChange = "";

    public void addCoins(double v) {
        coins += v;
        coinsChange = " §e(+" + Tools.cleanDouble(v, 1) + ")";
    }

	/*@Override
	public boolean equals(Object o){
		Assert.notNull(o);
		return o.equals(player);
	}*/

}
