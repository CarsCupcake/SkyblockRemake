//All rights go to CarsCupcake  L to TakoTheMaid
//If you see this, don't remove please :D
//Never gonna give you up,
//Never gonna let you down,
//Never gonna run around and desert you,
//Never gonna make you cry,
//Never gonna say goodbye
package me.CarsCupcake.SkyblockRemake;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.UUID;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.API.ItemEvents.ManaUpdateEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.isles.AuctionHouse.AuctionHouse;
import me.CarsCupcake.SkyblockRemake.isles.Bazaar.BazaarListener;
import me.CarsCupcake.SkyblockRemake.isles.Bazaar.BazaarManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.ICollection;
import me.CarsCupcake.SkyblockRemake.Configs.*;
import me.CarsCupcake.SkyblockRemake.Items.Crafting.SkyblockRecipe;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.F7Phase1;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Equipment.EquipmentInvListener;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.Attributes.Attribute;
import me.CarsCupcake.SkyblockRemake.NPC.*;
import me.CarsCupcake.SkyblockRemake.NPC.NPC;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.Potion;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionCommand;
import me.CarsCupcake.SkyblockRemake.Settings.InfoManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.abilitys.*;
import me.CarsCupcake.SkyblockRemake.cmd.*;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Titanium;
import me.CarsCupcake.SkyblockRemake.isles.privateIsle.PrivateIsle;
import me.CarsCupcake.SkyblockRemake.utils.*;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUIListener;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import org.bukkit.*;

import java.io.*;

import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.AccessoryListener;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.Powers.Bloody;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.Powers.MaxwellListener;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.Powers.Powers;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.Powers.Slender;
import me.CarsCupcake.SkyblockRemake.isles.Areas.AreaListener;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Commission.CommissionListener;
import me.CarsCupcake.SkyblockRemake.Items.Drill.DrillMerchant;
import me.CarsCupcake.SkyblockRemake.Items.Drill.DrillPart;
import me.CarsCupcake.SkyblockRemake.isles.dwarven.DwarvenEvents.DwarvenEvent;
import me.CarsCupcake.SkyblockRemake.isles.dwarven.DwarvenEvents.EventListener;
import me.CarsCupcake.SkyblockRemake.isles.dwarven.DwarvenEvents.PlayerTurnEvent;
import me.CarsCupcake.SkyblockRemake.isles.dwarven.DwarvenMines.IceWalkerSpawning;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.FishingSystem.FishingListener;
import me.CarsCupcake.SkyblockRemake.Items.Gemstones.GemstoneGrinder;
import me.CarsCupcake.SkyblockRemake.Items.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.isles.KuudraBossFight.CanonObject;
import me.CarsCupcake.SkyblockRemake.isles.KuudraBossFight.Tentacles;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Pets.PetFollowRunner;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Pets.PetMenuListener;
import me.CarsCupcake.SkyblockRemake.Skyblock.terminals.maze;
import me.CarsCupcake.SkyblockRemake.Items.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.Items.reforges.registerReforge;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import net.minecraft.server.level.EntityPlayer;

import javax.annotation.Nullable;

public class Main extends JavaPlugin {
    public static String VERSION = "0.0.0";
    private static Main Main;
    private BukkitRunnable runnable;
    private BukkitRunnable statrunnable;
    public int time;
    public static DataManager data;
    public static boolean soulchallengeacctive;
    public FileConfiguration config = getConfig();

    public static boolean isLocalHost = true;

    public static HashMap<Player, PetFollowRunner> petstand = new HashMap<>();

    public static HashMap<Player, Integer> absorbtion = new HashMap<>();
    public static HashMap<Player, Integer> absorbtionrunntime = new HashMap<>();
    public static final ArrayList<Player> deathPersons = new ArrayList<>();
    public static HashMap<Player, Boolean> shortbow_cd = new HashMap<>();
    public static HashMap<Player, Integer> termhits = new HashMap<>();

    // entity stats
    public static HashMap<Entity, Integer> baseentityhealth = new HashMap<>();
    public static HashMap<Entity, Integer> currentityhealth = new HashMap<>();
    public static HashMap<Entity, Integer> entitydamage = new HashMap<>();
    public static HashMap<Entity, Boolean> entitydead = new HashMap<>();
    public static HashMap<Entity, ArmorStand> dinnerboneNametags = new HashMap<>();

    // Wither Stuff
    public static HashMap<Wither, Integer> WitherSmallStuff = new HashMap<>();

    // SQL
    public SQL sql;

    //Configs
    public static CustomConfig bazaarFile;

    @SuppressWarnings("deprecation")
    @Override
    public void onEnable() {
        VERSION = getDescription().getVersion();
        AutoUpdater.INSTANCE.check();
        config.addDefault("JoinSpawn", false);
        config.addDefault("LavaBounce", false);
        config.addDefault("StatSystem", true);
        config.addDefault("SkyblockDataPath", ".\\data");
        config.addDefault("ServerType", "");
        config.options().copyDefaults(true);
        saveConfig();
        Main = this;

        try {
            this.getServer().getMessenger().registerIncomingPluginChannel(this, "skyblock:main", new MessageHandler());
            this.getServer().getMessenger().registerOutgoingPluginChannel(this, "skyblock:main");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Time t = new Time();
        if (config.getBoolean("bungeeCordTime")) {
            try {
                MessageHandler.sendMessage("registerTimer");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else t.runTaskTimer(getMain(), 1, 1);
        new SkyblockServer(ServerType.getFromString(config.getString("ServerType")));
        if (SkyblockServer.getServer().getType() == null) return;
        sql = new SQL();

        for (World world : Bukkit.getWorlds())
            for (Entity entity : world.getEntities())
                if (entity instanceof LivingEntity && !(entity instanceof ArmorStand) && !(entity instanceof Player))
                    entity.remove();
        data = new DataManager(this);

        bazaarFile = new CustomConfig("bazaarData");


        try {
            SkyblockEnchants.register();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getCommand("fixenchants").setExecutor((commandSender, command, s, strings) -> {
            SkyblockEnchants.unregister();
            SkyblockEnchants.register();
            return false;
        });

        if (data.getConfig().contains("data")) loadNPC();
        PetMenus.setup();
        PetMenus.save();
        PetMenus.setup();
        PetMenus.reload();
        AccessoryBag.setup();
        AccessoryBag.save();
        AccessoryBag.reload();

        MiningSystem.setup();
        MiningSystem.save();
        MiningSystem.reload();

        EditionItems.setup();
        EditionItems.save();
        EditionItems.reload();

        EquipmentFile.setup();
        EquipmentFile.save();
        EquipmentFile.reload();

        EntityLocations.setup();
        EntityLocations.save();
        EntityLocations.reload();

        ExtraInformations.setup();
        ExtraInformations.save();
        ExtraInformations.reload();
        new InfoManager();

        // init Powers
        Powers.initPowers(new Bloody());
        Powers.initPowers(new Slender());
        Powers.initStones();

        Items.init();

        registerReforge.init();
        eventRegister();
        EntityNPC.loadNPC();
        SkyblockPlayer.init();

        ICollection.init();
        ABILITIES.init();
        itemCMD.createItemInvs();
        if (!Bukkit.getOnlinePlayers().isEmpty()) for (Player p : Bukkit.getOnlinePlayers()) {

            SkyblockPlayer player = new SkyblockPlayer((CraftServer) this.getServer(), ((CraftPlayer) p).getHandle());
            absorbtion.put(player, 0);
            absorbtionrunntime.put(player, 0);
            shortbow_cd.put(player, false);
            termhits.put(player, 0);

            if (PetMenus.get().getConfigurationSection(player.getUniqueId().toString()) == null || !PetMenus.get().getConfigurationSection(player.getUniqueId().toString()).getKeys(false).contains("equiped")) {
                PetMenus.get().set(player.getUniqueId() + ".equiped", 0);
                PetMenus.save();
                PetMenus.reload();
            }
            if (PetMenus.get().getInt(player.getUniqueId() + ".equiped") != 0) {
                new PetFollowRunner(player, Pet.pets.get(PetMenus.get().getString(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() + ".equiped") + ".id")), PetMenus.get().getInt(player.getUniqueId() + ".equiped"));
            }

            SkyblockScoreboard.updateScoreboard(player);

            player.getInventory().forEach(item -> {
                item_updater(item, SkyblockPlayer.getSkyblockPlayer(player));
                player.updateInventory();

            });
            PacketReader reader = new PacketReader(player);
            reader.inject();
            Powers.initPower(player);
            Powers.powers.get("Slender").addObitained(player);
            Powers.powers.get("Slender").setActive(player);

            new PrivateIsle(player);
        }

        SkyblockRecipe.init();

        // Commands
        getCommand("gm").setExecutor(new gmComand());
        getCommand("undozap").setExecutor(new UndoZap());
        getCommand("gm").setTabCompleter(new gmTab());
        getCommand("item").setExecutor(new itemCMD());
        getCommand("item").setTabCompleter(new itemTab());
        getCommand("npc").setExecutor(new npcCommand());
        getCommand("terminal").setExecutor(new openterminal());
        getCommand("terminal").setTabCompleter(new openterminaltab());
        getCommand("lavabounce").setExecutor(new lavabouncetoggle());
        getCommand("lavabounce").setTabCompleter(new lavabouncetoggletab());
        getCommand("statsystem").setExecutor(new togglestats());
        getCommand("statsystem").setTabCompleter(new lavabouncetoggletab());
        getCommand("stats").setExecutor(new statsCMD());
        getCommand("stats").setTabCompleter(new statsTAB());
        getCommand("e").setExecutor(new OpenMenu());
        getCommand("testobject").setExecutor(new testobjectCMD());
        getCommand("spawns").setExecutor(new spawneggsCMD());
        getCommand("part").setExecutor(new particletest());
        getCommand("slayer").setExecutor(new startslayer());
        getCommand("slayer").setTabCompleter(new startslayerTAB());
        getCommand("beacon").setExecutor(new beaconThrow());
        getCommand("laser").setExecutor(new laser());
        getCommand("ench").setExecutor(new AddCustomEnchantCMD());
        getCommand("ench").setTabCompleter(new AddCusomEnchantTAB());
        getCommand("av").setExecutor(new avCMD());
        getCommand("av").setTabCompleter(new avTAB());
        getCommand("reforge").setExecutor(new AddReforge());
        getCommand("reforge").setTabCompleter(new AddReforgeTAB());
        getCommand("coins").setExecutor(new CoinsCommand());
        getCommand("coins").setTabCompleter(new CoinsTAB());
        getCommand("canon").setExecutor(new SpawnKuudraCanon());
        getCommand("generate").setExecutor(new generateMap());
        getCommand("accessoryslots").setExecutor(new AddAccessoryBagSlots());
        getCommand("unlockpowers").setExecutor(new UnlockPowers());
        getCommand("unlockpowers").setTabCompleter(new UnlockPowersTAB());
        getCommand("event").setExecutor(new StartEventCMD());
        getCommand("addpetxp").setExecutor(new AddPetExCMD());
        getCommand("addskillxp").setExecutor(new AddSkillXpCMD());
        getCommand("addskillxp").setTabCompleter(new AddSkillXpTAB());
        getCommand("dungeon").setExecutor(new DungeonCMD());
        getCommand("giveeditionitem").setExecutor(new GiveEditionItemCMD());
        getCommand("giveeditionitem").setTabCompleter(new GiveEditionItemTAB());
        getCommand("startboss").setExecutor(new StartBossFight());
        getCommand("craft").setExecutor(new CraftCMD());
        getCommand("selectmob").setExecutor(new selectMobPlacer());
        getCommand("entitynpc").setExecutor(new CreateEntityNPCCMD());
        getCommand("deliver").setExecutor(new DeliverCMD());
        getCommand("mob").setExecutor(new SpawnEntity());
        getCommand("mob").setTabCompleter(new SpawnEntityTAB());
        getCommand("setting").setExecutor(new SettingsCMD());
        getCommand("setting").setTabCompleter(new SettingsTAB());
        getCommand("dummy").setExecutor(new DummyCMD());
        getCommand("star").setExecutor(new starItem());
        getCommand("potion").setExecutor(new PotionCommand());
        getCommand("ah").setExecutor(new AhCMD());
        getCommand("bz").setExecutor(new BzCMD());
        getCommand("max").setExecutor(new MaxItemCmd());
        getCommand("setcounter").setExecutor((commandSender, command, s, strings) -> { ItemHandler.setPDC("counter",
            SkyblockPlayer.getSkyblockPlayer((Player) commandSender).getItemInHand(), PersistentDataType.INTEGER, Integer.parseInt(strings[0]));
            return false;
        });


        getCommand("kuudra").setExecutor(new startKuudra());
        this.getServer().getPluginManager().registerEvents(new SkyblockRemakeEvents(), this);
        this.getServer().getPluginManager().registerEvents(new NPCInteraction(), this);
        this.getServer().getPluginManager().registerEvents(new OpenMenu(), this);
        this.getServer().getPluginManager().registerEvents(new SkyblockScoreboard(), this);
        this.getServer().getPluginManager().registerEvents(new PetMenuListener(), this);

        // Terminal Listeners
        this.getServer().getPluginManager().registerEvents(new maze(), this);
        this.getServer().getPluginManager().registerEvents(new SpawnEggEvents(), this);
        this.getServer().getPluginManager().registerEvents(new CustomAnvil(), this);
        this.getServer().getPluginManager().registerEvents(new GemstoneGrinder(), this);
        this.getServer().getPluginManager().registerEvents(new DrillMerchant(), this);
        this.getServer().getPluginManager().registerEvents(new AccessoryListener(), this);
        this.getServer().getPluginManager().registerEvents(new MaxwellListener(), this);
        this.getServer().getPluginManager().registerEvents(new AreaListener(), this);
        this.getServer().getPluginManager().registerEvents(new CommissionListener(), this);
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
        this.getServer().getPluginManager().registerEvents(new ItemsSearch(), this);
        this.getServer().getPluginManager().registerEvents(new WearableHelmets(), this);
        this.getServer().getPluginManager().registerEvents(new CustomCraftingTable(), this);
        this.getServer().getPluginManager().registerEvents(new EquipmentInvListener(), this);
        this.getServer().getPluginManager().registerEvents(new BazaarListener(), this);
        this.getServer().getPluginManager().registerEvents(new GUIListener(), this);
        this.getServer().getPluginManager().registerEvents(new NPCEventHandler(), this);


        // Item Abilities
        this.getServer().getPluginManager().registerEvents(new BonemerangAbility(), this);
        this.getServer().getPluginManager().registerEvents(new CannonBalls(), this);
        this.getServer().getPluginManager().registerEvents(new JerryStaffAbility(), this);
        this.getServer().getPluginManager().registerEvents(new AbilityListener(), this);
        this.getServer().getPluginManager().registerEvents(new DeployableListener(), this);
        this.getServer().getPluginManager().registerEvents(new ReleaseThePainListener(), this);
        this.getServer().getPluginManager().registerEvents(new ICBMDeployableListener(), this);
        this.getServer().getPluginManager().registerEvents(new AuctionHouse(), this);
        this.getServer().getPluginManager().registerEvents(new ExtraDamageAbility() {
            @Override
            public void extraDamageEvent(SkyblockDamageEvent event) {

            }
        }, this);
        new SignManager().init();
        Dominus.setEvent();
        BazaarManager.init();

        this.getServer().getPluginManager().registerEvents(new Laser.LaserListener(), this);


        // Fishing System
        this.getServer().getPluginManager().registerEvents(new FishingListener(), this);

/*		this.getServer().getMessenger().registerIncomingPluginChannel(this, "skyblock:main", this);
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "skyblock:main");*/

        if (getServer().getPort() == 25564) {
            this.getServer().getPluginManager().registerEvents(new IceWalkerSpawning(), this);

        }

        new DiguestMobsManager();

        ArmorStandTeleportation();


        if (getConfig().getBoolean("StatSystem")) {
            Stats();
        }
        Recipes.loadrecipe();
        WitherSize();
        Bukkit.getWorlds().forEach(world -> {
            if (!world.getEntities().isEmpty()) {
                for (Entity entity : world.getEntities()) {
                    if (!(entity instanceof Player) && !(entity.getType() == EntityType.DROPPED_ITEM) && !(entity.getType() == EntityType.ARMOR_STAND) && !(entity.getType() == EntityType.WITHER_SKULL)) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity e = (LivingEntity) entity;
                            baseentityhealth.put(e, (int) e.getMaxHealth() * 5);
                            currentityhealth.put(e, (int) e.getMaxHealth() * 5);
                            updateentitystats(e);
                            if (!entity.getScoreboardTags().contains("dinnerbone")) entity.setCustomNameVisible(true);
                            e.setHealth(e.getMaxHealth());
                        }
                    }
                }
            }
        });

    }

    public static void initAccessoryBag(Player player) {
        if (AccessoryBag.get().getConfigurationSection(player.getUniqueId().toString()) == null || !AccessoryBag.get().getConfigurationSection(player.getUniqueId().toString()).getKeys(false).contains("slots")) {
            AccessoryBag.get().set(player.getUniqueId() + ".slots", 0);
            AccessoryBag.save();
            AccessoryBag.reload();
        }
        int slots = AccessoryBag.get().getInt(player.getUniqueId() + ".slots");
        int totmagpow = 0;
        if (slots != 0) {

            for (int i = 0; i < slots; i++) {
                String baseDir = player.getUniqueId() + ".SLOT_" + i;
                if (AccessoryBag.get().getConfigurationSection(player.getUniqueId() + ".SLOT_" + i) == null) continue;

                ItemManager manager = Items.SkyblockItems.get(AccessoryBag.get().getString(baseDir + ".id"));
                ItemRarity rarity = manager.getRarity();
                if (AccessoryBag.get().getBoolean(baseDir + ".recom")) rarity = rarity.getNext();
                int magicalpower = 0;
                switch (rarity) {
                    case COMMON:
                        magicalpower = 3;
                        break;
                    case DIVINE:
                        magicalpower = 22;
                        break;
                    case EPIC:
                        magicalpower = 12;
                        break;
                    case LEGENDARY:
                        magicalpower = 16;
                        break;
                    case MYTHIC:
                        magicalpower = 22;
                        break;
                    case RARE:
                        magicalpower = 8;
                        break;
                    case SPECIAL:
                        magicalpower = 3;
                        break;
                    case SUPREME:
                        magicalpower = 5;
                        break;
                    case UNCOMMON:
                        magicalpower = 5;
                        break;
                    case UNDEFINED:
                        magicalpower = 0;
                        break;
                    case VERY_SPECIAL:
                        magicalpower = 5;
                        break;
                    default:
                        break;

                }
                totmagpow += magicalpower;

            }
        }
        SkyblockPlayer.getSkyblockPlayer(player).setMagicalpower(totmagpow);

    }

    public static void calculateMagicalPower(Player player) {
        int slots = AccessoryBag.get().getInt(player.getUniqueId() + ".slots");
        int totmagpow = 0;
        if (slots != 0) {

            for (int i = 0; i < slots; i++) {
                String baseDir = player.getUniqueId() + ".SLOT_" + i;
                if (AccessoryBag.get().getConfigurationSection(player.getUniqueId() + ".SLOT_" + i) == null) continue;

                ItemManager manager = Items.SkyblockItems.get(AccessoryBag.get().getString(baseDir + ".id"));
                ItemRarity rarity = manager.getRarity();
                if (AccessoryBag.get().getBoolean(baseDir + ".recom")) rarity = rarity.getNext();
                int magicalpower = 0;
                switch (rarity) {
                    case COMMON:
                        magicalpower = 3;
                        break;
                    case DIVINE:
                        magicalpower = 22;
                        break;
                    case EPIC:
                        magicalpower = 12;
                        break;
                    case LEGENDARY:
                        magicalpower = 16;
                        break;
                    case MYTHIC:
                        magicalpower = 22;
                        break;
                    case RARE:
                        magicalpower = 8;
                        break;
                    case SPECIAL:
                        magicalpower = 3;
                        break;
                    case SUPREME:
                        magicalpower = 5;
                        break;
                    case UNCOMMON:
                        magicalpower = 5;
                        break;
                    case UNDEFINED:
                        magicalpower = 0;
                        break;
                    case VERY_SPECIAL:
                        magicalpower = 5;
                        break;
                    default:
                        break;

                }
                totmagpow += magicalpower;

            }
        }
        SkyblockPlayer.getSkyblockPlayer(player).setMagicalpower(totmagpow);
    }

    public void eventRegister() {
        HashMap<Player, Double> Yaw = new HashMap<>();
        HashMap<Player, Double> Pitch = new HashMap<>();
        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Yaw.containsKey(p)) {

                        if (Yaw.get(p) != p.getLocation().getYaw()) {
                            Bukkit.getPluginManager().callEvent(new PlayerTurnEvent(SkyblockPlayer.getSkyblockPlayer(p)));
                        } else {
                            if (Pitch.get(p) != p.getLocation().getPitch()) {
                                Bukkit.getPluginManager().callEvent(new PlayerTurnEvent(SkyblockPlayer.getSkyblockPlayer(p)));
                            }
                        }

                        Yaw.put(p, (double) p.getLocation().getYaw());
                        Pitch.put(p, (double) p.getLocation().getPitch());

                    } else {
                        Yaw.put(p, (double) p.getLocation().getYaw());
                        Pitch.put(p, (double) p.getLocation().getPitch());
                    }

                }
            }
        }.runTaskTimer(Main, 0, 1);
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            try {
                PrivateIsle.isles.get(SkyblockPlayer.getSkyblockPlayer(player)).remove();
                PrivateIsle.isles.remove(SkyblockPlayer.getSkyblockPlayer(player));
            } catch (Exception e) {
                e.printStackTrace();
            }
            SkyblockPlayer.getSkyblockPlayer(player).saveCommissionProgress();
            saveCoins(player);
            saveBits(player);
            saveMithrilPowder(player);
            try {
                PacketReader reader = new PacketReader(player);
                reader.uninject(player);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                SkyblockPlayer.getSkyblockPlayer(player).saveInventory();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (EntityPlayer npc : NPC.getNPCs())
                NPC.removeNPC(player, npc);
        }
        SkyblockEnchants.unregister();
        EntityNPC.shutdown();

        for (World world : Bukkit.getWorlds())
            for (Entity entity : world.getEntities()) {
                if (entity instanceof LivingEntity e && !(entity instanceof ArmorStand) && !(entity instanceof Player)) {
                    entity.remove();
                    if (SkyblockEntity.livingEntity.containsKey(e)) try {
                        SkyblockEntity.livingEntity.get(e).kill();
                    } catch (Exception ignored) {
                    }
                }
                if (entity.getScoreboardTags().contains("damage_tag")) entity.remove();
                if (entity.getScoreboardTags().contains("remove")) entity.remove();


            }


        if (DiguestMobsManager.getDiguested != null && !DiguestMobsManager.getDiguested.isEmpty())
            DiguestMobsManager.getDiguested.forEach((entity, diguest) -> {

                diguest.removeDisguise();
                entity.remove();

            });
        absorbtion.clear();
        absorbtionrunntime.clear();
        baseentityhealth.clear();
        currentityhealth.clear();
        shortbow_cd.clear();
        termhits.clear();
        Items.SkyblockItems.clear();
        if (DwarvenEvent.ActiveEvent != null) DwarvenEvent.ActiveEvent.cancleEvent();
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
        try {
            if (config.getBoolean("bungeeCordTime")) {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

        }


        for (Titanium tit : SkyblockRemakeEvents.TitaniumObject.values()) {
            tit.resetTitaniumBlock();
        }


        for (Player player : Bukkit.getOnlinePlayers()) {
            SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);
            TabListManager.managers.get(p).removePlayers();
        }
        while (Bukkit.getBossBars().hasNext()) {
            BossBar bar = Bukkit.getBossBars().next();
            bar.removeAll();
        }
        Spawnable.disable();

        if (!Deployable.deployables.isEmpty()) for (Deployable flare : Deployable.deployables.values()) {
            flare.stop();
        }
        if (!petstand.isEmpty()) {
            for (PetFollowRunner runners : petstand.values()) {

                runners.remove();

            }
            petstand.clear();
        }
        if (F7Phase1.instance != null) F7Phase1.instance.removeAll();

        if (!dinnerboneNametags.isEmpty()) dinnerboneNametags.values().forEach(stand -> {
            stand.remove();
        });
        CanonObject.removeAll();
        ABILITIES.disable();
        try {

            Tentacles.removeAllTentakle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ArmorStandTeleportation() {
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!dinnerboneNametags.isEmpty()) {
                    dinnerboneNametags.forEach((entity, stand) -> {
                        stand.teleport(((LivingEntity) entity).getEyeLocation().add(0, 0.5, 0));
                    });

                }

            }
        }.runTaskTimer(this, 0, 1);
    }

    public void WitherSize() {
        new BukkitRunnable() {

            @Override
            public void run() {

                if (!WitherSmallStuff.isEmpty()) {
                    WitherSmallStuff.forEach((wither, i) -> {

                        NBTEditor.set(wither, i, "Invul");
                    });
                }

            }
        }.runTaskTimer(Main, 0, 1);

    }


    public static void EntityDeath(LivingEntity entity) {
        entitydead.put(entity, true);
        BukkitRunnable runn = new BukkitRunnable() {

            @Override
            public void run() {
                baseentityhealth.remove(entity);
                currentityhealth.remove(entity);
                if (entitydamage.containsKey(entity)) entitydamage.remove(entity);

            }

        };
        runn.runTaskLater(getMain(), 30 * 20);
    }

    public void PlayerNearbyNpc() {
        runnable = new BukkitRunnable() {

            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    List<Entity> nearby = player.getNearbyEntities(8, 8, 8);
                    NPC.getNPCs().forEach(npc -> {
                        if (nearby.contains(npc.getBukkitEntity()))
                            Bukkit.broadcastMessage("Player " + player.getName() + " is nearby an npc");
                    });

                }

            }
        };
        runnable.runTaskTimer(this, 0, 20);
    }

    public void killarmorstand(ArmorStand stand) {
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                stand.remove();
            }
        };
        runnable.runTaskLater(Main, 20);
    }

    public void juju_cooldown(Player player) {
        shortbow_cd.replace(player, true);
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                shortbow_cd.replace(player, false);
            }
        };
        runnable.runTaskLater(Main, (long) ((0.20 * 20) + ((0.25 * (1D - Main.getPlayerStat(SkyblockPlayer.getSkyblockPlayer(player), Stats.AttackSpeed) / 100)) * 20)));
    }

    public void absorbtioneffect(Player player, int times) {
        SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);
        updatebar(SkyblockPlayer.getSkyblockPlayer(player));
        absorbtionrunntime.replace(player, absorbtionrunntime.get(player) + times);

        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().contains(player)) {
                    absorbtionrunntime.replace(player, absorbtionrunntime.get(player) - 1);

                    if (absorbtionrunntime.get(player) <= 0) {
                        double health = Main.getPlayerStat(SkyblockPlayer.getSkyblockPlayer(player), Stats.Health);
                        if (absorbtion.get(player) + p.currhealth > health) p.setHealth(health);
                        else {
                            p.setHealth((absorbtion.get(player) + p.currhealth) * p.healingMulti);
                        }
                        absorbtion.replace(player, 0);

                        updatebar(SkyblockPlayer.getSkyblockPlayer(player));
                        absorbtionrunntime.replace(player, 0);
                        return;
                    } else {
                        absorbtioneffect(player, 0);
                    }
                }
            }
        };
        runnable.runTaskLater(Main, 20);

    }

    public void Stats() {
        // runnable to update the action bar with the stats
        statrunnable = new BukkitRunnable() {

            @Override
            public void run() {

                Bukkit.getOnlinePlayers().forEach(p -> {
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
                    if (ServerType.getActiveType() != ServerType.PrivateIsle)
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 25, -1, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 30, 255, false, false, false));
                    if (!deathPersons.contains(player)) {

                        player.setSaturation(100);
                        // mana regen
                        double mana = Main.getPlayerStat(player, Stats.Inteligence);
                        if (player.currmana < mana) {
                            int manaadd = (int) ((mana * 0.02) * player.getManaRegenMult());
                            int finalmana = manaadd + player.currmana;
                            player.setMana(finalmana);

                        }
                        if (player.currmana > mana) {
                            player.setMana((int) mana);
                        }

                        // health regen
                        double health = Main.getPlayerStat(player, Stats.Health);
                        if (player.currhealth < health) {
                            int healthadd = (int) (health * 0.015);
                            int finalhealth = (int) (player.currhealth + (healthadd * player.healingMulti));

                            player.setHealth(finalhealth, HealthChangeReason.Regenerate);

                        }
                        if (player.currhealth > health) {
                            player.setHealth(health);
                        }
                        float speedpersentage = (float) Main.getPlayerStat(player, Stats.Speed) / 100;
                        if (speedpersentage > 5) speedpersentage = 5;
                        player.setWalkSpeed((float) 0.2 * (float) speedpersentage);
                        SkyblockScoreboard.updateScoreboard(player);
                        updatebar(player);
                        if (TabListManager.managers.containsKey(player)) {
                            TabListManager.managers.get(player).tick();
                        }

                    }
                });
                Bukkit.getWorlds().forEach(world -> {
                    world.getEntities().forEach(entity -> {

                        if (entity.getType() == EntityType.DROPPED_ITEM)
                            if (entity.getTicksLived() >= 20 * 20) entity.remove();

                        if (entity instanceof LivingEntity) {
                            if (!(entity instanceof Player) && !(entity.getType() == EntityType.DROPPED_ITEM) && !(entity.getType() == EntityType.EXPERIENCE_ORB) && !(entity.getType() == EntityType.ARMOR_STAND) && !(entity.getType() == EntityType.WITHER_SKULL)) {
                                updateentitystats((LivingEntity) entity);
                            }
                        }
                    });
                });

            }

        };
        statrunnable.runTaskTimer(this, 0, 20);
    }

    public static void updatebar(SkyblockPlayer player) {
        if (deathPersons.contains(player)) return;
        if (player.currhealth <= 0) {

            deathPersons.add(player);
            player.getPlayer().setHealth(0);
        }
        double maxhealth = Main.getPlayerStat(player, Stats.Health);
        if (maxhealth < 125) {
            player.setMaxHealth(20);
        } else if (maxhealth < 165) {
            player.setMaxHealth(22);
        } else if (maxhealth < 230) {
            player.setMaxHealth(24);
        } else if (maxhealth < 300) {
            player.setMaxHealth(26);
        } else if (maxhealth < 400) {
            player.setMaxHealth(28);
        } else if (maxhealth < 500) {
            player.setMaxHealth(30);
        } else if (maxhealth < 650) {
            player.setMaxHealth(32);
        } else if (maxhealth < 800) {
            player.setMaxHealth(34);
        } else if (maxhealth < 1000) {
            player.setMaxHealth(36);
        } else if (maxhealth < 1250) {
            player.setMaxHealth(38);
        } else if (maxhealth >= 1250) {
            player.setMaxHealth(40);
        }


        if (absorbtion.containsKey(player) && absorbtion.get(player) != 0) {
            int abs = absorbtion.get(player);

            if (abs == 0) {
                player.setAbsorptionAmount(0);
            } else if (abs < 0) {
                player.setAbsorptionAmount(2);
            } else if (abs < 165) {
                player.setAbsorptionAmount(4);
            } else if (abs < 230) {
                player.setAbsorptionAmount(6);
            } else if (abs < 300) {
                player.setAbsorptionAmount(8);
            } else if (abs < 400) {
                player.setAbsorptionAmount(10);
            } else if (abs < 500) {
                player.setAbsorptionAmount(12);
            } else if (abs < 650) {
                player.setAbsorptionAmount(14);
            } else if (abs < 800) {
                player.setAbsorptionAmount(16);
            } else if (abs < 1000) {
                player.setAbsorptionAmount(18);
            } else if (abs < 1250) {
                player.setAbsorptionAmount(20);
            } else if (abs >= 1250) {
                player.setAbsorptionAmount(22);
            }
        } else {
            player.setAbsorptionAmount(0);
        }

        double health = player.currhealth;
        float estimated = (float) ((health / maxhealth) * player.getMaxHealth());

        String extraafterdef = "";
        if (termhits.containsKey(player) && termhits.get(player) != 0) {
            if (termhits.get(player) >= 3) extraafterdef = extraafterdef + "§a§lT3!  ";
            else extraafterdef = extraafterdef + "§6T" + termhits.get(player) + "  ";
        }

        if (estimated < 0) estimated = 0;
        if (estimated > player.getMaxHealth()) estimated = (float) player.getMaxHealth();
        player.getPlayer().setHealth(estimated);

        String stackMsg = "";
        if (player.bonusAmounts.containsKey(Bonuses.Dominus) && player.bonusAmounts.get(Bonuses.Dominus) > 1) {
            int stacks = Dominus.playerDominus.get(player).stacks;

            if (stacks != 0) {
                String large = "";
                if (stacks == 10) large = "§l";
                stackMsg += "§6" + large + stacks + "ᝐ§r";
            }


        }
        if (player.bonusAmounts.containsKey(Bonuses.HydraStrike) && player.bonusAmounts.get(Bonuses.HydraStrike) > 1) {
            int stacks = HydraStrike.get(player).stacks;

            if (stacks != 0) {
                String large = "";
                if (stacks == 10) large = "§l";
                stackMsg += "§6" + large + stacks + "⁑§r";
            }


        }
        if (player.bonusAmounts.containsKey(Bonuses.ArcaneVision) && player.bonusAmounts.get(Bonuses.ArcaneVision) > 1) {
            int stacks = ArcaneVision.getArcaneVision(player).stacks;

            if (stacks != 0) {
                String large = "";
                if (stacks == 10) large = "§l";
                stackMsg += "§6" + large + stacks + "Ѫ§r";
            }


        }
        if (player.bonusAmounts.containsKey(Bonuses.Spirit) && player.bonusAmounts.get(Bonuses.Spirit) > 1 && Spirit.getStacks(player) != 0) {
            int stacks = Spirit.getStacks(player);
            stackMsg += "§6" + ((Spirit.isMaxStacks(player, stacks)) ? "§l" : "") + stacks + "⚶§r";
        }
        String afterManaString = "";
        if (player.bonusAmounts.containsKey(Bonuses.StaticCharge) && player.bonusAmounts.get(Bonuses.StaticCharge) > 1) {
            afterManaString = "    " + StaticCharge.getPlayerDisplay(player);
        }

        health = Main.getPlayerStat(player, Stats.Health);
        double defense = Main.getPlayerStat(player, Stats.Defense);
        double mana = Main.getPlayerStat(player, Stats.Inteligence);

        String defenseString = "§a" + String.format("%.0f", Tools.round(defense, 0)) + "❈ Defense";
        if (player.showDefenceString) defenseString = player.defenseString;

        if (absorbtion.containsKey(player) && absorbtion.get(player) != 0) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6" + (player.currhealth + absorbtion.get(player)) + "/" + String.format("%.0f", Tools.round(health, 0)) + "❤ " + stackMsg + "    " + defenseString + "  " + extraafterdef + "   §b" + player.currmana + "/" + String.format("%.0f", Tools.round(mana, 0)) + "✎ Mana" + afterManaString));
        } else
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c" + player.currhealth + "/" + String.format("%.0f", Tools.round(health, 0)) + "❤ " + stackMsg + "    " + defenseString + "  " + extraafterdef + "   §b" + player.currmana + "/" + String.format("%.0f", Tools.round(mana, 0)) + "✎ Mana" + afterManaString));
        float speedpersentage = (float) Main.getPlayerStat(player, Stats.Speed) / 100;
        if (speedpersentage > 5) speedpersentage = 5;
        player.setWalkSpeed((float) 0.2 * (float) speedpersentage);

    }

    @SuppressWarnings("deprecation")
    public static void updateDiguestedEntity(LivingEntity entity) {
        if (baseentityhealth.containsKey(entity) == false) {
            baseentityhealth.put(entity, (int) entity.getMaxHealth() * 5);
            currentityhealth.put(entity, (int) entity.getMaxHealth() * 5);
        }
        float health;
        float maxhealth;

        try {
            maxhealth = baseentityhealth.get((Entity) entity);
            health = currentityhealth.get((Entity) entity);
        } catch (Exception e) {
            return;
        }
        if (health <= 0) {
            entity.setCustomNameVisible(false);

            entity.setHealth(0);
            return;
        }
        float estimated = (float) ((health / maxhealth) * entity.getMaxHealth());
        entity.setHealth(estimated);

        DiguestMobsManager.getDiguested.get(entity).setName("§7[§8Lv?§7] §c" + DiguestMobsManager.entitys.get(entity) + " §a" + (int) health + "§8/§a" + baseentityhealth.get(entity));
    }


    @SuppressWarnings("deprecation")
    public static void updateentitystats(LivingEntity entity) {

        if (SkyblockEntity.livingEntity.containsKey(entity)) {
            SkyblockEntity.updateEntity(SkyblockEntity.livingEntity.get(entity));
            if (currentityhealth.containsKey(entity)) currentityhealth.remove(entity);
            if (baseentityhealth.containsKey(entity)) baseentityhealth.remove(entity);

            return;
        }
        if (entity.getScoreboardTags().contains("npc")) return;

        if (entity.getType() == EntityType.DROPPED_ITEM) return;
        if (entity.getType() == EntityType.PLAYER) return;
        if (entitydead.containsKey(entity) && entitydead.get(entity)) return;
        if (DiguestMobsManager.entitys.containsKey(entity)) {
            updateDiguestedEntity(entity);
            return;
        }
        if (baseentityhealth.containsKey(entity) == false) {
            baseentityhealth.put(entity, (int) entity.getMaxHealth() * 5);
            currentityhealth.put(entity, (int) entity.getMaxHealth() * 5);
        }

        float health;
        float maxhealth;

        try {
            maxhealth = baseentityhealth.get((Entity) entity);
            health = currentityhealth.get((Entity) entity);
        } catch (Exception e) {
            return;
        }
        if (health <= 0) {
            entity.setCustomNameVisible(false);
            try {
                entity.setHealth(0);
            } catch (Exception e) {

            }
            return;
        }
        float estimated = (float) ((health / maxhealth) * entity.getMaxHealth());
        entity.setHealth(estimated);


        ArrayList<Boolean> hasCustomName = new ArrayList<>();
        ArrayList<Boolean> isDinnerBone = new ArrayList<>();
        ArrayList<String> StandName = new ArrayList<>();
        ArmorStand stand = null;
        if (entity.getScoreboardTags().contains("dinnerbone")) {

            if (!dinnerboneNametags.containsKey(entity)) {
                stand = entity.getWorld().spawn(entity.getEyeLocation().add(0, 0.5, 0), ArmorStand.class, s -> {
                    s.setInvisible(true);
                    s.setGravity(false);
                    s.setInvulnerable(true);
                    s.setMarker(true);
                    s.setCustomNameVisible(true);
                });
                dinnerboneNametags.put(entity, stand);

                isDinnerBone.add(true);

            } else {
                stand = dinnerboneNametags.get(entity);
                isDinnerBone.add(true);
            }
        } else {

        }

        if (entity.getScoreboardTags().contains("slayername")) {

            entity.getScoreboardTags().forEach(str -> {

                if (str.startsWith("CustomName")) {
                    String[] names = str.split(":");
                    String name = names[1];
                    {

                        if (currentityhealth.get(entity) > 999) {
                            if (currentityhealth.get(entity) > 9999) {
                                if (currentityhealth.get(entity) > 999999) {
                                    if (currentityhealth.get(entity) > 9999999) {
                                        if (!isDinnerBone.contains(true))
                                            entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + currentityhealth.get(entity) / 1000000 + "m§c§?§");
                                        else
                                            StandName.add("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + currentityhealth.get(entity) / 1000000 + "m§c§?§");
                                    } else {
                                        if (!isDinnerBone.contains(true))
                                            entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + Tools.round((float) ((float) currentityhealth.get(entity) / 1000000), 1) + "m§c§?§");
                                        else
                                            StandName.add("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + Tools.round((float) ((float) currentityhealth.get(entity) / 1000000), 1) + "m§c§?§");
                                    }
                                } else {
                                    if (!isDinnerBone.contains(true))
                                        entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + currentityhealth.get(entity) / 1000 + "k§c§?§");
                                    else
                                        StandName.add("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + currentityhealth.get(entity) / 1000 + "k§c§?§");

                                }
                            } else {
                                if (!isDinnerBone.contains(true))
                                    entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + Tools.round((float) ((float) currentityhealth.get(entity) / 1000), 1) + "k§c§?§");
                                else
                                    StandName.add("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + Tools.round((float) ((float) currentityhealth.get(entity) / 1000), 1) + "k§c§?§");
                            }
                        } else if (!isDinnerBone.contains(true))
                            entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + currentityhealth.get(entity) + "§c§?§");
                        else
                            StandName.add("§c" + Character.toChars(9760)[0] + " §b" + name + " §a" + currentityhealth.get(entity) + "§c§?§");
                        hasCustomName.add(true);
                    }
                }
            });

            if (!hasCustomName.contains(true)) {
                entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + entity.getType().getName() + " §a" + currentityhealth.get(entity) + "§c§?§");
            }

        } else {
            if (entity.getScoreboardTags().contains("slayerminibossname")) {
                if (entity.getScoreboardTags().contains("strongmini")) {
                    entity.getScoreboardTags().forEach(str -> {
                        if (str.startsWith("CustomName")) {
                            String[] names = str.split(":");
                            String name = names[1];
                            if (currentityhealth.get(entity) > 999) {
                                if (currentityhealth.get(entity) > 9999) {
                                    if (currentityhealth.get(entity) > 999999) {
                                        if (currentityhealth.get(entity) > 9999999) {
                                            entity.setCustomName("§4" + name + " §a" + currentityhealth.get(entity) / 1000000 + "m§c§?§");
                                        } else
                                            entity.setCustomName("§4" + name + " §a" + Tools.round((float) ((float) currentityhealth.get(entity) / 1000000), 1) + "m§c§?§");
                                    } else {
                                        entity.setCustomName("§4" + name + " §a" + currentityhealth.get(entity) / 1000 + "k§c§?§");
                                    }
                                } else {
                                    entity.setCustomName("§4" + name + " §a" + Tools.round((float) ((float) currentityhealth.get(entity) / 1000), 1) + "k§c§?§");
                                }
                            } else entity.setCustomName("§4" + name + " §a" + currentityhealth.get(entity) + "§c§?§");
                            hasCustomName.add(true);
                        }
                    });
                } else {
                    entity.getScoreboardTags().forEach(str -> {
                        if (str.startsWith("CustomName")) {
                            String[] names = str.split(":");
                            String name = names[1];
                            if (currentityhealth.get(entity) > 999) {
                                if (currentityhealth.get(entity) > 9999) {
                                    if (currentityhealth.get(entity) > 999999) {
                                        if (currentityhealth.get(entity) > 9999999) {
                                            entity.setCustomName("§b" + name + " §a" + currentityhealth.get(entity) / 1000000 + "m§c§?§");
                                        } else
                                            entity.setCustomName("§b" + name + " §a" + Tools.round((float) ((float) currentityhealth.get(entity) / 1000000), 1) + "m§c§?§");
                                    } else {
                                        entity.setCustomName("§b" + name + " §a" + currentityhealth.get(entity) / 1000 + "k§c§?§");
                                    }
                                } else {
                                    entity.setCustomName("§b" + name + " §a" + Tools.round((float) ((float) currentityhealth.get(entity) / 1000), 1) + "k§c§?§");
                                }
                            } else entity.setCustomName("§b" + name + " §a" + currentityhealth.get(entity) + "§c§?§");
                            hasCustomName.add(true);
                        }
                    });
                }
                if (!hasCustomName.contains(true)) {
                    entity.setCustomName("§b" + entity.getType().getName() + " §a" + currentityhealth.get(entity) + "§c§?§");
                }
            } else {

                if (!isDinnerBone.contains(true)) {

                    entity.setCustomName("§7[§8Lv?§7] §c" + entity.getType().getName() + " §a" + (int) health + "§8/§a" + baseentityhealth.get(entity));
                    entity.getScoreboardTags().forEach(tag -> {
                        if (tag.startsWith("CustomName:")) {
                            entity.setCustomName("§7[§8Lv?§7] §c" + tag.split(":")[1] + " §a" + (int) health + "§8/§a" + baseentityhealth.get(entity));
                        }
                    });
                } else
                    StandName.add("§7[§8Lv?§7] §c" + entity.getType().getName() + " §a" + (int) health + "§8/§a" + baseentityhealth.get(entity));
            }
        }
        if (entity.getScoreboardTags().contains("singlename")) {
            entity.getScoreboardTags().forEach(str -> {

                if (str.startsWith("CustomName")) {
                    String[] names = str.split(":");
                    String name = names[1];
                    if (!isDinnerBone.contains(true)) entity.setCustomName("§c" + name);
                    else StandName.set(0, "§c" + name);

                }
            });
        }

        if (stand != null) {
            stand.setCustomName(StandName.get(0));
        }

    }

    public synchronized static double getPlayerStat(SkyblockPlayer player, Stats stat) {
        double ferocity = player.getBaseStat(stat);
        ferocity = ferocity + getItemStat(SkyblockPlayer.getSkyblockPlayer(player), stat, player.getItemInHand());
        ferocity = ferocity + getItemStat(SkyblockPlayer.getSkyblockPlayer(player), stat, player.getInventory().getHelmet());
        ferocity = ferocity + getItemStat(SkyblockPlayer.getSkyblockPlayer(player), stat, player.getInventory().getChestplate());
        ferocity = ferocity + getItemStat(SkyblockPlayer.getSkyblockPlayer(player), stat, player.getInventory().getLeggings());
        ferocity = ferocity + getItemStat(SkyblockPlayer.getSkyblockPlayer(player), stat, player.getInventory().getBoots());

        if (PetMenus.get().getInt(player.getUniqueId() + ".equiped") != 0) {
            Pet pet = Pet.pets.get(PetMenus.get().getString(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() + ".equiped") + ".id"));

            ferocity += pet.getStat(stat, PetMenus.get().getInt(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() + ".equiped") + ".level"));

        }
        if (Powers.activepower.containsKey(player)) {
            Powers power = Powers.activepower.get(player);
            ferocity += power.CalculateStats(stat, player);
        }
        ferocity += SkyblockPlayer.getSkyblockPlayer(player).equipmentManager.getTotalStat(stat);
        GetTotalStatEvent event = new GetTotalStatEvent(SkyblockPlayer.getSkyblockPlayer(player), stat, ferocity);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled())
            return 0;
        ferocity = event.getValue() * event.getMultiplier();
        return ferocity;
    }


    public static double playerTrophyFishingChance(Player player) {
        double ferocity = SkyblockPlayer.getSkyblockPlayer(player).getBaseTrophyFishChance();

        ferocity *= itemTrophyFishingChance(player.getItemInHand()) + 1;
        ferocity *= itemTrophyFishingChance(player.getInventory().getHelmet()) + 1;
        ferocity *= itemTrophyFishingChance(player.getInventory().getChestplate()) + 1;
        ferocity *= itemTrophyFishingChance(player.getInventory().getLeggings()) + 1;
        ferocity *= itemTrophyFishingChance(player.getInventory().getBoots()) + 1;


        return ferocity;
    }

    public static double itemTrophyFishingChance(ItemStack item) {
        if (item == null) {
            return 0;
        } else {
            ItemMeta meta = item.getItemMeta();
            if (meta == null) {
                return 0;
            } else {
                PersistentDataContainer data = meta.getPersistentDataContainer();
                if (data == null) return 0;
                return Items.SkyblockItems.get(data.get(new NamespacedKey(getMain(), "id"), PersistentDataType.STRING)).getTrophyFishChance();
            }
        }

    }


    public synchronized static double getItemStat(SkyblockPlayer player, Stats stat, ItemStack item) {
        double value = 0;
        if (item == null) {
            return 0;
        } else {
            ItemMeta meta = item.getItemMeta();
            if (meta == null) {
                return 0;

            } else {
                PersistentDataContainer data = meta.getPersistentDataContainer();
                if (data == null) {
                    return 0;
                } else {/*
			if (stat != Stats.AbilityDamage) {*/


                    try {
                        Double rawvalue = data.get(new NamespacedKey(getMain(), stat.getDataName()), PersistentDataType.DOUBLE);
                        if (rawvalue == null) value = 0;
                        else value = rawvalue;
                    } catch (Exception ignored) {
                        Float rawvalue = data.get(new NamespacedKey(getMain(), stat.getDataName()), PersistentDataType.FLOAT);
                        if (rawvalue == null) value = 0;
                        else value = rawvalue;
                    }
                }
            }
        }
        value *= StarHandler.getStarBuff(item);
        double val = value;
        double kekw;
        GetStatFromItemEvent event = new GetStatFromItemEvent(item, stat, val, player);
        Bukkit.getPluginManager().callEvent(event);
        kekw = event.getValue() * event.getMultiplier();
        kekw = Tools.round(kekw, 1);
        return kekw;

    }


    public static double weapondamage(ItemStack item) {

        if (item == null) {
            return 0;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return 0;
        }
        PersistentDataContainer data = meta.getPersistentDataContainer();
        if (data == null) return 0;

        String speed = data.get(new NamespacedKey(getMain(), "dmg"), PersistentDataType.STRING);

        if (speed == null) {

            return 0;
        } else {
            return Tools.round(Double.parseDouble(speed) * StarHandler.getStarBuff(item), 1);
        }
    }


    public static double itembreakingpower(ItemStack item) {
        double speed = 0;
        if (item == null) {
            return 0;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return 0;
        }
        PersistentDataContainer data = meta.getPersistentDataContainer();
        if (data == null) return 0;
        try {
            speed = data.get(new NamespacedKey(getMain(), "breakingpower"), PersistentDataType.DOUBLE);
        } catch (Exception e) {
            speed = 0;
        }
        if (speed == 0) {

            return 0;
        } else {
            return speed;
        }
    }

    public static double itemcatchtime(ItemStack item) {
        Double speed;
        if (item == null) {
            return 1;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return 1;
        }
        PersistentDataContainer data = meta.getPersistentDataContainer();
        if (data == null) return 1;
        try {
            speed = data.get(new NamespacedKey(getMain(), "catchmult"), PersistentDataType.DOUBLE);
            if (speed == null) return 1;
        } catch (Exception e) {
            speed = 1D;
        }
        if (speed == 0) {

            return 0;
        } else {
            return speed;
        }
    }

    public void Timer() {
        if (time == 0) {
            reloadConfig();
            time = getConfig().getInt("TimerValue");
        }
        getConfig().set("TimerActive", true);
        saveConfig();
        runnable = new BukkitRunnable() {
            @Override
            public void run() {

                time++;
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6 §l" + shortInteger(time)));
                });
            }
        };
        runnable.runTaskTimer(this, 0, 20);

    }


    public static void saveCoins(Player player) {
        double coin = SkyblockPlayer.getSkyblockPlayer(player).coins;
        CustomConfig c = new CustomConfig(SkyblockPlayer.getSkyblockPlayer(player), "stats");
        c.get().set(player.getUniqueId().toString() + ".coins", coin);
        c.save();
        c.reload();
    }

    public static void saveBits(Player player) {
        int bit = (int) SkyblockPlayer.getSkyblockPlayer(player).bits;
        CustomConfig c = new CustomConfig(SkyblockPlayer.getSkyblockPlayer(player), "stats");
        c.get().set(player.getUniqueId().toString() + ".bits", bit);
        c.save();
        c.reload();
    }

    public static void saveMithrilPowder(Player player) {
        int bit = (int) SkyblockPlayer.getSkyblockPlayer(player).mithrilpowder;
        CustomConfig c = new CustomConfig(SkyblockPlayer.getSkyblockPlayer(player), "stats");
        c.get().set(player.getUniqueId().toString() + ".mithrilpowder", bit);
        c.save();
        c.reload();
    }

    @SuppressWarnings({"deprecation"})
    public static ItemStack item_updater(ItemStack item, @Nullable SkyblockPlayer player) {
        if (item == null) {
            return item;
        }
        if (item.getType() == Material.AIR) return item;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return item;
        }

        PersistentDataContainer data = meta.getPersistentDataContainer();

        List<String> lore = new ArrayList<>();

        if (data.get(new NamespacedKey(Main, "id"), PersistentDataType.STRING) != null) {
            ItemManager manager = Items.SkyblockItems.get(data.get(new NamespacedKey(Main, "id"), PersistentDataType.STRING));
            if (manager == null) return item;

            if (Pet.pets.containsKey(manager.itemID)) {
                item = Pet.pets.get(manager.itemID).updatePet(item);
            }

            if (manager.getFlags().contains(me.CarsCupcake.SkyblockRemake.Items.ItemFlag.SPECIAL_MATERIAL_GRABBER)) {
                if (manager.getMaterialGrabber() != null)
                    item.setType(manager.getMaterialGrabber().getMaterial(item, player));
            } else item.setType(manager.material);
            meta.setLore(new ArrayList<String>());
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            ArrayList<String> lores = new ArrayList<>();

            if (Pet.pets.containsKey(manager.itemID)) {
                lores.add("§8" + Pet.pets.get(manager.itemID).Petype + " Pet");
                lores.add(" ");

            }

            ItemRarity rarity = manager.getRarity(ItemRarity.valueOf(data.get(new NamespacedKey(Main, "rarity"), PersistentDataType.STRING)), item, player);
            if (itembreakingpower(item) != 0) {
                if (data.get(new NamespacedKey(Main, "reforge"), PersistentDataType.STRING) != null && Reforge.getReforgeValue(registerReforge.reforges.get(data.get(new NamespacedKey(Main, "reforge"), PersistentDataType.STRING)), rarity, "breakingpower") != 0) {
                    lores.add("§8Breaking Power " + String.format("%.0f", itembreakingpower(item)) + " §9(+" + Reforge.getReforgeValue(registerReforge.reforges.get(data.get(new NamespacedKey(Main, "reforge"), PersistentDataType.STRING)), rarity, "breakingpower") + ")");
                } else lores.add("§8Breaking Power " + String.format("%.0f", itembreakingpower(item)));
                lores.add(" ");
            }
            if (manager.type == ItemType.FishingRod) {
                switch (manager.getRodType()) {
                    case LavaRod -> {
                        lores.add("§8Lava Rod");
                    }
                }
            }
            lores = Stats.makeItemStatsLore(item, lores, player);


            if (manager.gemstoneSlots != null && !manager.gemstoneSlots.isEmpty()) {
                ArrayList<GemstoneSlot> gemSlots = GemstoneSlot.getCurrGemstones(manager, data);
                String gomstoneLine = "";
                for (GemstoneSlot slot : gemSlots) {
                    if (slot.currGem == null) {
                        gomstoneLine += "§8[§7" + slot.type.getSymbol() + "§8] ";
                    } else {
                        gomstoneLine += slot.currGem.getRarity().getPrefix() + "[" + slot.currGem.gemType.getPrefix() + slot.type.getSymbol() + slot.currGem.getRarity().getPrefix() + "] ";
                    }
                }
                if (!gomstoneLine.equals("") && !gomstoneLine.isEmpty()) lores.add(gomstoneLine);

            }

            if (item.getType() == Material.POTION) {
                lores.addAll(Potion.craftLore(player, item));
            }


            if (item.getEnchantments() != null && !item.getEnchantments().isEmpty()) {
                lores.add(" ");
                ArrayList<String> enchantLore = new ArrayList<>();
                HashMap<String, Integer> operator = new HashMap<>();
                operator.put("amount", 0);
                operator.put("line", 0);

                Bundle<ArrayList<UltimateEnchant>, ArrayList<Enchantment>> enchants = UltimateEnchant.splitEnchants(item.getItemMeta().getEnchants().keySet());

                for (Enchantment enchant : UltimateEnchant.orderEnchants(enchants)) {
                    int level = item.getItemMeta().getEnchants().get(enchant);
                    String prefix = (UltimateEnchant.isUltEnchant(enchant)) ? "§d§l" : "§9";


                    if (!enchant.getName().equals("non") && !enchant.getName().equals("")) {
                        String Name = makeStringFromID(enchant.getKey());
                        if (operator.get("amount") == 0) {

                            enchantLore.add(prefix + Name + " " + Tools.intToRoman(level));
                            operator.replace("amount", 1);
                        } else {
                            enchantLore.set(operator.get("line"), enchantLore.get(operator.get("line")) + "§9, " + prefix + Name + " " + Tools.intToRoman(level));

                            if (operator.get("amount") == 1) operator.replace("amount", 2);
                            else {
                                operator.replace("amount", 0);
                                operator.replace("line", operator.get("line") + 1);
                            }
                        }
                    }
                }
                if (!enchantLore.isEmpty() && !enchantLore.get(0).equals("")) {
                    for (String l : enchantLore)
                        lores.add(l);

                }

            }

            if (manager.getLore() != null && !manager.getLore().isEmpty()) {
                lores.add(" ");
                lores.addAll(manager.getLore());
            }

            if (manager.isAttributable()) {

                for (Attribute attribute : Attribute.getAttributes(item, player)) {
                    lores.add(" ");
                    lores.addAll(attribute.getAttributeLore());
                }
            }


            if (Pet.pets.containsKey(manager.itemID)) {
                meta.setDisplayName("§7[Lvl " + data.get(new NamespacedKey(Main, "level"), PersistentDataType.INTEGER) + "] " + rarity.getPrefix() + Pet.pets.get(manager.itemID).name);
            } else if (data.get(new NamespacedKey(Main, "reforge"), PersistentDataType.STRING) != null && registerReforge.reforges.containsKey(data.get(new NamespacedKey(Main, "reforge"), PersistentDataType.STRING)))
                meta.setDisplayName(rarity.getPrefix() + data.get(new NamespacedKey(Main, "reforge"), PersistentDataType.STRING) + " " + rarity.getPrefix() + manager.name + " " + StarHandler.getStarSuffix(item));
            else meta.setDisplayName(rarity.getPrefix() + manager.name + " " + StarHandler.getStarSuffix(item));

            //Tako was here

            if (manager.getEquipmentAbility() != null) {
                Ability equipmentAbility = manager.getEquipmentAbilityData();
                if (equipmentAbility.getName() != null) {
                    lores.add(" ");
                    lores.add("§6Ability: " + equipmentAbility.getName());
                }
                AbilityLore l = equipmentAbility.getLore();
                if (l != null) {


                    ArrayList<String> firstAblilityLore = l.makeLore(player, item);
                    for (String str : firstAblilityLore)
                        lores.add(str);
                }
            }
            int i = 0;
            for (Ability ability : manager.getAbilities()) {
                lores.add(" ");
                if (ability.getName() != null) lores.add(manager.getAbilityHeadline(player, i));
                if (ability.getLore() != null) for (String s : ability.getLore().makeLore(player, item))
                    lores.add(s);

                if (ability.getManacost() > 0 && !ability.isPersentage()) {
                    ManaUpdateEvent event = new ManaUpdateEvent(item, ability.getManacost());
                    Bukkit.getPluginManager().callEvent(event);
                    lores.add("§8Mana Cost §3" + event.getMana());
                } else if (ability.isPersentage()) {
                    ManaUpdateEvent event = new ManaUpdateEvent(item, ability.getPersentage());
                    Bukkit.getPluginManager().callEvent(event);
                    lores.add("§8Mana Cost §3" + String.format("%.0f",event.getMana()) + "%");
                }

                if (ability.getCooldown() > 0) lores.add("§8Ability Cooldown §a" + ability.getCooldown() + "s");
                i++;
            }

            if (Pet.pets.containsKey(manager.itemID)) {
                Pet pet = Pet.pets.get(manager.itemID);
                lores.add(" ");
                lores.addAll(pet.buildAbilityLore(player, item));
            }

            if (manager.getTrophyFishChance() != 0 && manager.type != ItemType.Pet) {

                lores.add(" ");
                String num = "";
                double catchMult = manager.getTrophyFishChance() * 100;
                if (catchMult % 1 == 0) {
                    num = ((int) catchMult) + "";
                } else num = catchMult + "";
                lores.add("§7Increases the chance to catch");
                lores.add("§6Trophy Fish§7 by §a" + num + "%");
                lores.add(" ");

            }

            if (manager.type == ItemType.Drill) {

                if (data.get(new NamespacedKey(Main, "fueltank"), PersistentDataType.STRING) != null) {
                    lores.add(" ");
                    DrillPart part = DrillPart.parts.get(data.get(new NamespacedKey(Main, "fueltank"), PersistentDataType.STRING));
                    lores.add("§a" + part.name);
                    for (String s : part.appliedLore) {
                        lores.add(s);
                    }

                } else {
                    lores.add("§7Fuel Tank: §cNot Installed");
                    lores.add("§7Increases fuel capacity with");
                    lores.add("§7part installed.");
                }
                lores.add(" ");
                if (data.get(new NamespacedKey(Main, "drillengine"), PersistentDataType.STRING) != null) {
                    DrillPart part = DrillPart.parts.get(data.get(new NamespacedKey(Main, "drillengine"), PersistentDataType.STRING));
                    lores.add("§a" + part.name);
                    for (String s : part.appliedLore) {
                        lores.add(s);
                    }
                } else {
                    lores.add("§7Drill Engine: §cNot Installed");
                    lores.add("§7Increases §6⸕Mining Speed");
                    lores.add("§7with part installed.");
                }
                lores.add(" ");
                if (data.get(new NamespacedKey(Main, "upgrademodule"), PersistentDataType.STRING) != null) {
                    DrillPart part = DrillPart.parts.get(data.get(new NamespacedKey(Main, "upgrademodule"), PersistentDataType.STRING));
                    lores.add("§a" + part.name);
                    for (String s : part.appliedLore) {
                        lores.add(s);
                    }
                } else {
                    lores.add("§7Upgrade Module: §cNot Installed");
                    lores.add("§7Aplies a passive upgrade with");
                    lores.add("§7part installed.");
                }
                lores.add(" ");

                lores.add("§7Fuel: §2" + data.get(new NamespacedKey(Main, "fuel"), PersistentDataType.INTEGER) + "§8/" + data.get(new NamespacedKey(Main, "maxfuel"), PersistentDataType.INTEGER));

            }

            if (data.get(new NamespacedKey(Main, "reforge"), PersistentDataType.STRING) != null && registerReforge.reforges.containsKey(data.get(new NamespacedKey(Main, "reforge"), PersistentDataType.STRING)) && registerReforge.reforges.get(data.get(new NamespacedKey(Main, "reforge"), PersistentDataType.STRING)).getLore() != null) {
                lores.add("");
                for (String l : registerReforge.reforges.get(data.get(new NamespacedKey(Main, "reforge"), PersistentDataType.STRING)).getLore()) {
                    lores.add(l);
                }
                lores.add("");
            }

            if (Pet.pets.containsKey(manager.itemID)) {
                lores.add(" ");
                if (data.getOrDefault(new NamespacedKey(Main, "level"), PersistentDataType.INTEGER, 1) == (Pet.pets.get(manager.itemID).MaxLevel))
                    lores.add("§b§lMax Level!");
                else if (data.getOrDefault(new NamespacedKey(Main, "level"), PersistentDataType.INTEGER, 1) > (Pet.pets.get(manager.itemID).MaxLevel))
                    lores.add("§c§l§k l §r§c§lOver Leveled! §kl");
                else {
                    double currxp = data.getOrDefault(new NamespacedKey(Main, "currxp"), PersistentDataType.DOUBLE, 0d);
                    double reqxp = Pet.pets.get(manager.itemID).getRequieredXp(data.getOrDefault(new NamespacedKey(Main, "level"), PersistentDataType.INTEGER, 1));
                    double pers = currxp / reqxp;
                    int colored = (int) (20 * pers);
                    String str = "";
                    for (int c = 0; c < 20; c++) {
                        if (colored > c) str = str + "§2";
                        else str = str + "§7";
                        str += "-";

                    }
                    str += " §e" + Tools.round(currxp, 1) + "§6/§e" + Tools.toShortNumber((int) reqxp);


                    lores.add(str);


                }
            }


            if (manager.hasEdition) {
                lores.add(" ");
                String to = "§7To: §f-";
                if (data.get(new NamespacedKey(Main, "to"), PersistentDataType.STRING) != null) {
                    to = "§7To: §f" + data.get(new NamespacedKey(Main, "to"), PersistentDataType.STRING);
                }


                String from = "§7From: §f-";
                if (data.get(new NamespacedKey(Main, "from"), PersistentDataType.STRING) != null) {
                    from = "§7From: §f" + data.get(new NamespacedKey(Main, "from"), PersistentDataType.STRING);
                }

                String edition = "§8Edition: N/A";
                if (data.get(new NamespacedKey(Main, "editionnumber"), PersistentDataType.INTEGER) != null) {
                    edition = "§8Edition: #" + data.get(new NamespacedKey(Main, "editionnumber"), PersistentDataType.INTEGER);
                }

                String date = "§8N/A";
                if (data.get(new NamespacedKey(Main, "date"), PersistentDataType.STRING) != null) {
                    date = "§8" + data.get(new NamespacedKey(Main, "date"), PersistentDataType.STRING);
                }
                lores.add(" ");
                lores.add(to);
                lores.add(from);
                lores.add(" ");
                lores.add(edition);
                lores.add(date);
                lores.add(" ");


            }
            lores.add(" ");
            String extra = "";
            if (manager.isDungeonItem) extra = "DUNGEON ";

            if (data.get(new NamespacedKey(Main, "recomed"), PersistentDataType.INTEGER) == 0)
                lores.add(((item.getType() == Material.POTION) ? me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getRarityFromLevel(me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getHighestLevel(item)).getRarityName() : rarity.getRarityName()) + " " + extra + manager.type.toString().toUpperCase());
            else {
                lores.add(((item.getType() == Material.POTION) ? me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getRarityFromLevel(me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getHighestLevel(item)).getNext().getPrefix() : rarity.getPrefix()) + "§k§lr§r " + ((item.getType() == Material.POTION) ? me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getRarityFromLevel(me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getHighestLevel(item)).getNext().getRarityName() : rarity.getRarityName()) + " " + extra + manager.type.toString().toUpperCase() + " §kr");
            }
            meta.setLore(lores);

        } else {

            if (item.getType() == Material.ENCHANTED_BOOK) {

                ItemStack newItem = Items.SkyblockItems.get("ENCHANTED_BOOK").getRawItemStack();

                if (item.getItemMeta() instanceof EnchantmentStorageMeta) {
                    EnchantmentStorageMeta enchStorage = (EnchantmentStorageMeta) item.getItemMeta();

                    ArrayList<String> enchantLore = new ArrayList<>();
                    HashMap<String, Integer> operator = new HashMap<>();
                    operator.put("amount", 0);
                    operator.put("line", 0);
                    meta = newItem.getItemMeta();
                    for (Enchantment enchant : enchStorage.getStoredEnchants().keySet()) {
                        int level = enchStorage.getStoredEnchantLevel(enchant);
                        meta.addEnchant(enchant, level, true);

                    }
                    enchStorage.getStoredEnchants().forEach((enchant, level) -> {

                        if (!enchant.getName().equals("non") && !enchant.getName().equals("")) {
                            if (operator.get("amount") == 0) {
                                String Name = enchant.getKey().getKey().substring(0, 1).toUpperCase() + enchant.getKey().getKey().substring(1);

                                Name = Name.replaceAll("_", " ");
                                enchantLore.add("§9" + Name + " " + level);
                                operator.replace("amount", 1);
                            } else {
                                String Name = enchant.getKey().getKey().substring(0, 1).toUpperCase() + enchant.getKey().getKey().substring(1);
                                Name = Name.replaceAll("_", " ");
                                enchantLore.set(operator.get("line"), enchantLore.get(operator.get("line")) + " " + "§9" + Name + " " + level);
                                operator.replace("amount", 0);
                                operator.replace("line", operator.get("line") + 1);
                            }
                        }
                    });
                    enchantLore.forEach(l -> {
                        lore.add(l);
                    });
                }

                item = newItem;

            } else {
                if ((data.get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING) == null || !data.get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING).equals("fakearrow")) && data.get(new NamespacedKey(Main, "type"), PersistentDataType.STRING) == null) {

                    int amount = item.getAmount();

                    item = Items.SkyblockItems.get(item.getType().name()).getRawItemStack();
                    item.setAmount(amount);
                    meta = item.getItemMeta();
                }

            }

            meta.setLore(lore);
        }

        item.setItemMeta(meta);

        return item;

    }

    public static Main getMain() {
        return Main;
    }

    public BukkitRunnable getRunnable() {
        return runnable;
    }

    public BukkitRunnable getStatRunnable() {
        return statrunnable;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public String shortInteger(int duration) {
        String string = "";
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (duration / 60 / 60 / 24 >= 1) {
            duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
        }
        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60;
            duration -= duration / 60 / 60 * 60 * 60;
        }
        if (duration / 60 >= 1) {
            minutes = duration / 60;
            duration -= duration / 60 * 60;
        }
        if (duration >= 1) seconds = duration;
        if (hours == 0) {
            string = String.valueOf(string) + "";
        } else {
            if (hours <= 9) {
                string = String.valueOf(string) + "0" + hours + " Stunden ";
            } else {
                string = String.valueOf(string) + hours + " Stunden ";
            }
        }
        if (minutes == 0) {
            string = String.valueOf(string) + "";
        } else {
            if (minutes <= 9) {
                string = String.valueOf(string) + "0" + minutes + " Minuten ";
            } else {
                string = String.valueOf(string) + minutes + " Minuten ";
            }
        }
        if (seconds == 0) {
            string = String.valueOf(string) + "";
        } else {
            if (seconds <= 9) {
                string = String.valueOf(string) + "0" + seconds + " Sekunden";
            } else {
                string = String.valueOf(string) + seconds + " Sekunden";
            }
        }
        return string;
    }


    public static FileConfiguration getData() {
        return data.getConfig();
    }

    public static void saveData() {
        data.saveConfig();
    }

    public ArrayList<String> getSoulStats(Player player) {
        ArrayList<String> soulstats = new ArrayList<>();
        if (getConfig().contains(player.getUniqueId().toString() + ".soulstats")) {
            soulstats = (ArrayList<String>) getConfig().getStringList(player.getUniqueId().toString() + ".soulstats");
        }

        return soulstats;
    }

    public static void loadNPC() {
        FileConfiguration file = data.getConfig();
        data.getConfig().getConfigurationSection("data").getKeys(false).forEach(npc -> {
            Location location = new Location(Bukkit.getWorld(file.getString("data." + npc + ".world")), file.getDouble("data." + npc + ".x"), file.getDouble("data." + npc + ".y"), file.getDouble("data." + npc + ".z"));
            location.setPitch((float) file.getDouble("data." + npc + ".p"));
            location.setYaw((float) file.getDouble("data." + npc + ".ya"));

            String name = file.getString("data." + npc + ".name");
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
            gameProfile.getProperties().put("rextures", new Property("textures", file.getString("data." + npc + ".text"), file.getString("data." + npc + ".signature")));

            me.CarsCupcake.SkyblockRemake.NPC.NPC.loadNPC(location, gameProfile);
        });
    }

    private static String makeStringFromID(NamespacedKey key) {
        String newString = "";
        String itemName = key.getKey();
        String[] minis = itemName.split("_");
        for (int i = 0; i != minis.length; i++) {
            minis[i].toLowerCase();
            minis[i] = minis[i].substring(0, 1).toUpperCase() + minis[i].substring(1).toLowerCase();

            if (i == minis.length - 1) {
                newString = newString + minis[i];
            } else newString = newString + minis[i] + " ";

        }
        return newString;
    }

    public InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = me.CarsCupcake.SkyblockRemake.Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }


}
