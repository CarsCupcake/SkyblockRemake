//All rights go to CarsCupcake  L to TakoTheMaid
//If you see this, don't remove please :D
//Never gonna give you up,
//Never gonna let you down,
//Never gonna run around and desert you,
//Never gonna make you cry,
//Never gonna say goodbye
package me.CarsCupcake.SkyblockRemake;

import java.net.Socket;
import java.util.*;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.API.ItemEvents.ManaUpdateEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Entities.BasicEntity;
import me.CarsCupcake.SkyblockRemake.FishingSystem.RodType;
import me.CarsCupcake.SkyblockRemake.Items.Crafting.CustomCraftingTable;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.QuestNpc;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.Selection;
import me.CarsCupcake.SkyblockRemake.Skyblock.hex.Hex;
import me.CarsCupcake.SkyblockRemake.Slayer.spider.entity.SpiderListener;
import me.CarsCupcake.SkyblockRemake.cmd.enhancedCommand.TablistBuilder;
import me.CarsCupcake.SkyblockRemake.cmd.impl.admin.*;
import me.CarsCupcake.SkyblockRemake.cmd.impl.generals.*;
import me.CarsCupcake.SkyblockRemake.cmd.impl.test.*;
import me.CarsCupcake.SkyblockRemake.isles.AuctionHouse.AuctionHouse;
import me.CarsCupcake.SkyblockRemake.isles.Bazaar.BazaarListener;
import me.CarsCupcake.SkyblockRemake.isles.Bazaar.BazaarManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.ICollection;
import me.CarsCupcake.SkyblockRemake.configs.*;
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
import me.CarsCupcake.SkyblockRemake.Settings.ServerSettings;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.abilities.*;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.helper.CompileCommand;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.helper.DungeonStickCommand;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Titanium;
import me.CarsCupcake.SkyblockRemake.isles.privateIsle.PrivateIsle;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import me.CarsCupcake.SkyblockRemake.utils.*;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUIListener;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import me.CarsCupcake.SkyblockRemake.utils.log.CustomLogger;
import me.CarsCupcake.SkyblockRemake.utils.log.DebugLogger;
import me.CarsCupcake.SkyblockRemake.utils.serverMessaging.ServerMessenger;
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
import me.CarsCupcake.SkyblockRemake.Items.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Items.Pets.PetFollowRunner;
import me.CarsCupcake.SkyblockRemake.Items.Pets.PetMenuListener;
import me.CarsCupcake.SkyblockRemake.Skyblock.terminals.maze;
import me.CarsCupcake.SkyblockRemake.Items.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.Items.reforges.RegisteredReforges;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import net.minecraft.server.level.EntityPlayer;
import org.spigotmc.SpigotConfig;

import javax.annotation.Nullable;

public class Main extends JavaPlugin {
    public static String VERSION = "0.0.0";
    @Getter
    private static Main main;
    @Getter
    private BukkitRunnable runnable;
    private BukkitRunnable statrunnable;
    @Getter
    public int time;
    public static DataManager data;
    public FileConfiguration config = getConfig();

    public static boolean isLocalHost = true;

    public static HashMap<Player, PetFollowRunner> petstand = new HashMap<>();

    public static HashMap<Player, Integer> absorbtion = new HashMap<>();
    public static HashMap<Player, Integer> absorbtionrunntime = new HashMap<>();
    public static final ArrayList<Player> deathPersons = new ArrayList<>();
    public static HashMap<Player, Boolean> shortbow_cd = new HashMap<>();
    public static HashMap<Player, Integer> termhits = new HashMap<>();
    public static HashMap<Entity, Boolean> entitydead = new HashMap<>();
    public static HashMap<Entity, ArmorStand> dinnerboneNametags = new HashMap<>();

    // Wither Stuff
    public static HashMap<Wither, Integer> WitherSmallStuff = new HashMap<>();

    // SQL
    public SQL sql;

    //Configs
    public static ConfigFile bazaarFile;
    @Getter
    public static DebugLogger debug;
    public static ServerMessenger messenger;


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
        main = this;
        new SkyblockServer(ServerType.getFromString(config.getString("ServerType")));

        if (SkyblockServer.getServer().type() == null) return;
        new ServerSettings();
        SpigotConfig.movedWronglyThreshold = 6;
        try {
            DebugLogger.debug = ServerSettings.getValue("debug", false);
            debug = new DebugLogger("");
            debug.debug("Debug Logging enabled!");
        } catch (Exception e) {
            System.out.println("An error occuret why enabeling the debug logger:");
            e.printStackTrace(System.err);
        }
        debug.debug("Set Server type", false);
        new BukkitRunnable() {
            final CustomLogger logger = new CustomLogger("Cleanup Task");

            @Override
            public void run() {
                this.logger.info("Cleaning up the JVM (This may cause a short lag spike!)");
                final long before = System.currentTimeMillis();
                System.gc();
                Runtime.getRuntime().gc();
                final long after = System.currentTimeMillis();
                this.logger.info("It took " + (after - before) + "ms to cleanup the JVM heap");
            }
        }.runTaskTimer(this, 1L, 12000L);
        Time t = new Time();
        if (checkIfBungee()) {
            try {
                messenger = new ServerMessenger(Integer.parseInt(String.valueOf(getServer().getPort()).substring(String.valueOf(getServer().getPort()).length() - 3)));
                messenger.sendBufferedMessage("add:" + ServerType.getActiveType().s);
                debug.debug("Enabeling messenger channel", false);
                messenger.registerListener(s -> {
                    if (ServerSettings.getValue("debugMessengerChannel", false)) debug.debug("Recieved Message: " + s);
                });
                messenger.registerListener(s -> {
                    if (s.startsWith("datapath:")) {
                        String[] split = s.split(":", 2);
                        config.set("SkyblockDataPath", split[1]);
                        debug.debug("Set Datapath to " + split[1]);
                        saveConfig();
                    }
                    if (s.startsWith("time:")) {
                        Time.getInstance().deserialize(s.split(":")[1]);
                    }
                    if (s.startsWith("season:")) {
                        Time.getInstance().setSeason(Integer.parseInt(s.split(":")[1]));
                    }
                });
                try {
                    Socket socket = new Socket("127.0.0.1", 20);
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println(getServer().getPort());
                    writer.flush();
                    socket.close();
                    writer.close();
                } catch (Exception ignored) {
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
                debug.debug("Enabeling messenger channel failed!");
            }
        } else t.runTaskTimer(getMain(), 1, 1);
        sql = new SQL();

        for (World world : Bukkit.getWorlds())
            for (Entity entity : world.getEntities())
                if (entity instanceof LivingEntity && !(entity instanceof ArmorStand) && !(entity instanceof Player))
                    entity.remove();
        data = new DataManager(this);

        bazaarFile = new ConfigFile("bazaarData");


        try {
            SkyblockEnchants.register();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        Objects.requireNonNull(getCommand("fixenchants")).setExecutor((commandSender, command, s, strings) -> {
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

        // init Powers
        Powers.initPowers(new Bloody());
        Powers.initPowers(new Slender());
        Powers.initStones();

        Items.init();

        RegisteredReforges.init();
        eventRegister();
        EntityNPC.loadNPC();
        SkyblockPlayer.init();

        ICollection.init();
        ABILITIES.init();

        SkyblockRecipe.init();

        debug.debug("Registering Commands", false);

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
        getCommand("setting").setTabCompleter(new TablistBuilder(SettingsCMD.class));
        getCommand("dummy").setExecutor(new DummyCMD());
        getCommand("star").setExecutor(new starItem());
        getCommand("potion").setExecutor(new PotionCommand());
        getCommand("ah").setExecutor(new AhCMD());
        getCommand("bz").setExecutor(new BzCMD());
        getCommand("max").setExecutor(new MaxItemCmd());
        getCommand("loadschematic").setExecutor(new TestLoadSchematic());
        getCommand("setcounter").setExecutor((commandSender, command, s, strings) -> {
            ItemHandler.setPDC("counter", SkyblockPlayer.getSkyblockPlayer((Player) commandSender).getItemInHand(), PersistentDataType.INTEGER, Integer.parseInt(strings[0]));
            return false;
        });
        getCommand("room").setExecutor(new RoomCMD());
        getCommand("room").setTabCompleter(new RoomCMD());
        getCommand("quest").setExecutor((commandSender, command, s, strings) -> {
            if (Selection.selections.containsKey((Player) commandSender))
                Selection.selections.get((Player) commandSender).select(UUID.fromString(strings[0]));
            return true;
        });
        getCommand("kuudra").setExecutor(new startKuudra());
        getCommand("rifttime").setExecutor(new RiftTimeCMD());
        getCommand("rifttime").setTabCompleter(new TablistBuilder(RiftTimeCMD.class));
        getCommand("wd").setExecutor(new WardrobeCMD());
        getCommand("wardrobe").setExecutor(new WardrobeCMD());
        getCommand("drhelp").setExecutor(new DungeonStickCommand());
        getCommand("compile").setExecutor(new CompileCommand());
        getCommand("gemstonegrinder").setExecutor(new GemstoneGrinderCommand());
        getCommand("gsg").setExecutor(new GemstoneGrinderCommand());
        getCommand("customitem").setExecutor(new CustomItemCreatorCommand());
        getCommand("hex").setExecutor((commandSender, command, s, strings) -> {
            if (commandSender instanceof Player p) {
                new Hex(SkyblockPlayer.getSkyblockPlayer(p)).openLandingPage();
                return true;
            }
            return false;
        });


        debug.debug("Registering Events", false);
        this.getServer().getPluginManager().registerEvents(new AbilityListener(), this);
        this.getServer().getPluginManager().registerEvents(new SkyblockRemakeEvents(), this);
        this.getServer().getPluginManager().registerEvents(new NPCInteraction(), this);
        this.getServer().getPluginManager().registerEvents(new OpenMenu(), this);
        this.getServer().getPluginManager().registerEvents(new PetMenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new maze(), this);
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
        this.getServer().getPluginManager().registerEvents(new BonemerangAbility(), this);
        this.getServer().getPluginManager().registerEvents(new CannonBalls(), this);
        this.getServer().getPluginManager().registerEvents(new JerryStaffAbility(), this);
        this.getServer().getPluginManager().registerEvents(new DeployableListener(), this);
        this.getServer().getPluginManager().registerEvents(new ReleaseThePainListener(), this);
        this.getServer().getPluginManager().registerEvents(new ICBMDeployableListener(), this);
        this.getServer().getPluginManager().registerEvents(new AuctionHouse(), this);
        this.getServer().getPluginManager().registerEvents(new SpiderListener(), this);
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
        debug.debug("Loading Loot tables");
        try {
            BasicEntity.initAllLootTables();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            debug.debug("Loading Loot tables failed");
        }
        debug.debug("Done!", false);
        Bukkit.getWorlds().forEach(world -> {
            if (!world.getEntities().isEmpty()) {
                for (Entity entity : world.getEntities()) {
                    if (!(entity instanceof Player) && !(entity.getType() == EntityType.DROPPED_ITEM) && !(entity.getType() == EntityType.ARMOR_STAND) && !(entity.getType() == EntityType.WITHER_SKULL)) {
                        if (entity instanceof LivingEntity) {
                            entity.remove();
                        }
                    }
                }
            }
        });
        SkyblockServer.getServer().init();
        if (!Bukkit.getOnlinePlayers().isEmpty()) for (Player p : Bukkit.getOnlinePlayers()) {

            SkyblockPlayer player = new SkyblockPlayer((CraftServer) this.getServer(), ((CraftPlayer) p).getHandle());
            absorbtion.put(player, 0);
            absorbtionrunntime.put(player, 0);
            shortbow_cd.put(player, false);
            termhits.put(player, 0);

            if (PetMenus.get().getConfigurationSection(player.getUniqueId().toString()) == null || !Objects.requireNonNull(PetMenus.get().getConfigurationSection(player.getUniqueId().toString())).getKeys(false).contains("equiped")) {
                PetMenus.get().set(player.getUniqueId() + ".equiped", 0);
                PetMenus.save();
                PetMenus.reload();
            }
            if (PetMenus.get().getInt(player.getUniqueId() + ".equiped") != 0) {
                new PetFollowRunner(player, Pet.pets.get(PetMenus.get().getString(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() + ".equiped") + ".id")), PetMenus.get().getInt(player.getUniqueId() + ".equiped"));
            }

            SkyblockScoreboard.updateScoreboard(player);

            player.getInventory().forEach(item -> {
                itemUpdater(item, SkyblockPlayer.getSkyblockPlayer(player));
                player.updateInventory();

            });
            PacketReader reader = new PacketReader(player);
            reader.inject();
            Powers.initPower(player);
            Powers.powers.get("Slender").addObitained(player);
            Powers.powers.get("Slender").setActive(player);
        }

    }

    public static void initAccessoryBag(Player player) {
        if (AccessoryBag.get().getConfigurationSection(player.getUniqueId().toString()) == null || !Objects.requireNonNull(AccessoryBag.get().getConfigurationSection(player.getUniqueId().toString())).getKeys(false).contains("slots")) {
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
                    case COMMON, SPECIAL:
                        magicalpower = 3;
                        break;
                    case DIVINE, MYTHIC:
                        magicalpower = 22;
                        break;
                    case EPIC:
                        magicalpower = 12;
                        break;
                    case LEGENDARY:
                        magicalpower = 16;
                        break;
                    case RARE:
                        magicalpower = 8;
                        break;
                    case SUPREME, VERY_SPECIAL, UNCOMMON:
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
        //TODO: Update!
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
                    case COMMON, SPECIAL:
                        magicalpower = 3;
                        break;
                    case DIVINE, MYTHIC:
                        magicalpower = 22;
                        break;
                    case EPIC:
                        magicalpower = 12;
                        break;
                    case LEGENDARY:
                        magicalpower = 16;
                        break;
                    case RARE:
                        magicalpower = 8;
                        break;
                    case SUPREME, VERY_SPECIAL, UNCOMMON:
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
        }.runTaskTimer(main, 0, 1);
    }

    @Override
    public void onDisable() {
        messenger.remove();
        ConfigFile.thread.finish();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (ServerType.getActiveType() == ServerType.PrivateIsle) try {
                PrivateIsle.isles.get(SkyblockPlayer.getSkyblockPlayer(player)).remove();
                PrivateIsle.isles.remove(SkyblockPlayer.getSkyblockPlayer(player));
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);
            p.saveCommissionProgress();
            saveCoins(player);
            saveBits(player);
            saveMithrilPowder(player);
            try {
                PacketReader reader = new PacketReader(player);
                reader.uninject(player);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            try {
                SkyblockPlayer.getSkyblockPlayer(player).saveInventory();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            p.unregister();
            for (EntityPlayer npc : NPC.getNPCs())
                NPC.removeNPC(player, npc);
            if (QuestNpc.shownNpc.containsKey(p)) for (QuestNpc npc : new ArrayList<>(QuestNpc.shownNpc.get(p)))
                npc.remove(p);
        }
        SkyblockEnchants.unregister();
        EntityNPC.shutdown();

        for (World world : Bukkit.getWorlds())
            for (Entity entity : world.getEntities()) {
                if (entity instanceof LivingEntity e && !(entity instanceof ArmorStand) && !(entity instanceof Player)) {
                    entity.remove();
                    if (SkyblockEntity.livingEntity.exists(e)) try {
                        Objects.requireNonNull(SkyblockEntity.livingEntity.getSbEntity(e)).kill();
                    } catch (Exception ignored) {
                    }
                }
                if (entity.getScoreboardTags().contains("damage_tag")) entity.remove();
                if (entity.getScoreboardTags().contains("remove")) entity.remove();


            }
        absorbtion.clear();
        absorbtionrunntime.clear();
        shortbow_cd.clear();
        termhits.clear();
        Items.SkyblockItems.clear();
        if (DwarvenEvent.ActiveEvent != null) DwarvenEvent.ActiveEvent.cancleEvent();
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);

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

        if (!dinnerboneNametags.isEmpty()) dinnerboneNametags.values().forEach(Entity::remove);
        CanonObject.removeAll();
        ABILITIES.disable();
        try {

            Tentacles.removeAllTentakle();
        } catch (Exception e) {
            e.printStackTrace(System.err);
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
        }.runTaskTimer(main, 0, 1);

    }


    public static void EntityDeath(LivingEntity entity) {
        entitydead.put(entity, true);
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
        stand.addScoreboardTag("remove");
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                stand.remove();
            }
        };
        runnable.runTaskLater(main, 20);
    }

    public void juju_cooldown(Player player, long cooldown) {
        shortbow_cd.replace(player, true);
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                shortbow_cd.replace(player, false);
            }
        };
        runnable.runTaskLater(main, cooldown);
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
                        double health = getPlayerStat(SkyblockPlayer.getSkyblockPlayer(player), Stats.Health);
                        if (absorbtion.get(player) + p.currhealth > health)
                            p.setHealth(health, HealthChangeReason.Ability);
                        else {
                            p.setHealth((absorbtion.get(player) + p.currhealth) * p.healingMulti, HealthChangeReason.Ability);
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
        runnable.runTaskLater(main, 20);

    }

    public void Stats() {
        // runnable to update the action bar with the stats
        statrunnable = new BukkitRunnable() {

            @Override
            public void run() {

                Bukkit.getOnlinePlayers().forEach(p -> {
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
                    if (ServerType.getActiveType() != ServerType.PrivateIsle && ServerSettings.isMiningFatuigeEnable())
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 25, -1, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 30, 255, false, false, false));
                    if (!deathPersons.contains(player)) {

                        player.setSaturation(100);
                        // mana regen
                        double mana = getPlayerStat(player, ((ServerType.getActiveType() == ServerType.Rift) ? Stats.RiftInteligence : Stats.Inteligence));
                        if (player.currmana < mana) {
                            int manaadd = (int) ((mana * 0.02) * player.getManaRegenMult());
                            int finalmana = manaadd + player.currmana;
                            player.setMana(finalmana);

                        }
                        if (player.currmana > mana) {
                            player.setMana((int) mana);
                        }

                        // health regen
                        if (ServerType.getActiveType() != ServerType.Rift) {
                            double health = getPlayerStat(player, Stats.Health);
                            if (player.currhealth < health) {
                                int healthadd = (int) (health * 0.015);
                                int finalhealth = (int) (player.currhealth + (healthadd * player.healingMulti));

                                player.setHealth(finalhealth, HealthChangeReason.Regenerate);

                            }
                            if (player.currhealth > health) {
                                player.setHealth(health, HealthChangeReason.Regenerate);
                            }
                        }
                        float speedpersentage = (float) getPlayerStat(player, ((ServerType.getActiveType() == ServerType.Rift) ? Stats.RiftSpeed : Stats.Speed)) / 100;
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
                    });
                });

            }

        };
        statrunnable.runTaskTimer(this, 0, 20);
    }

    public static void updatebar(SkyblockPlayer player) {
        if (deathPersons.contains(player)) return;
        if (ServerType.getActiveType() == ServerType.Rift) {
            RiftPlayer.getRiftPlayer(player).updateBar();
            return;
        }
        if (player.currhealth <= 0) {
            deathPersons.add(player);
            assert player.getPlayer() != null;
            player.getPlayer().setHealth(0);
            player.kill();
        }
        double maxhealth = getPlayerStat(player, Stats.Health);
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
            } else {
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
        assert player.getPlayer() != null;
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

        health = getPlayerStat(player, Stats.Health);
        double defense = getPlayerStat(player, Stats.Defense);
        double mana = getPlayerStat(player, Stats.Inteligence);

        String defenseString = "§a" + String.format("%.0f", Tools.round(defense, 0)) + "❈ Defense";
        if (player.showDefenceString) defenseString = player.defenseString;

        if (absorbtion.containsKey(player) && absorbtion.get(player) != 0) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6" + (player.currhealth + absorbtion.get(player)) + "/" + String.format("%.0f", Tools.round(health, 0)) + "❤ " + stackMsg + "    " + defenseString + "  " + extraafterdef + "   §b" + player.currmana + "/" + String.format("%.0f", Tools.round(mana, 0)) + "✎ Mana" + afterManaString));
        } else
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c" + player.currhealth + "/" + String.format("%.0f", Tools.round(health, 0)) + "❤ " + stackMsg + "    " + defenseString + "  " + extraafterdef + "   §b" + player.currmana + "/" + String.format("%.0f", Tools.round(mana, 0)) + "✎ Mana" + afterManaString));
        float speedpersentage = (float) main.getPlayerStat(player, Stats.Speed) / 100;
        if (speedpersentage > 5) speedpersentage = 5;
        player.setWalkSpeed((float) 0.2 * (float) speedpersentage);

    }

    public static void updateentitystats(LivingEntity entity) {
        if (SkyblockEntity.livingEntity.exists(entity)) {
            SkyblockEntity.updateEntity(Objects.requireNonNull(SkyblockEntity.livingEntity.getSbEntity(entity)));
            return;
        }
        new BasicEntity(entity, (int) (entity.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 5));
    }

    public synchronized static double getPlayerStat(SkyblockPlayer player, Stats stat) {
        double ferocity = player.getBaseStat(stat);
        if (player.getItemInHand() != null && player.getItemInHand().hasItemMeta() && ItemHandler.getItemManager(player.getItemInHand()) != null && ItemHandler.getItemManager(player.getItemInHand()).type.hasInHandStats())
            ferocity = ferocity + getItemStat(SkyblockPlayer.getSkyblockPlayer(player), stat, player.getItemInHand());
        ferocity += getItemStat(SkyblockPlayer.getSkyblockPlayer(player), stat, player.getInventory().getHelmet());
        ferocity += getItemStat(SkyblockPlayer.getSkyblockPlayer(player), stat, player.getInventory().getChestplate());
        ferocity += getItemStat(SkyblockPlayer.getSkyblockPlayer(player), stat, player.getInventory().getLeggings());
        ferocity += getItemStat(SkyblockPlayer.getSkyblockPlayer(player), stat, player.getInventory().getBoots());
        //TODO Rewrite Pet system!
        //File call -> Inefficient!
       /* if (PetMenus.get().getInt(player.getUniqueId() + ".equiped") != 0) {
            Pet pet = Pet.pets.get(PetMenus.get().getString(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() + ".equiped") + ".id"));
            ferocity += pet.getStat(stat, PetMenus.get().getInt(player.getUniqueId() + "." + PetMenus.get().getInt(player.getUniqueId() + ".equiped") + ".level"));
        }*/
        if (Powers.activepower.containsKey(player)) {
            Powers power = Powers.activepower.get(player);
            ferocity += power.CalculateStats(stat, player);
        }
        ferocity += SkyblockPlayer.getSkyblockPlayer(player).equipmentManager.getTotalStat(stat);
        GetTotalStatEvent event = new GetTotalStatEvent(SkyblockPlayer.getSkyblockPlayer(player), stat, ferocity);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return stat.getBaseAmount();
        ferocity = event.getValue() * event.getMultiplier();
        return ferocity;
    }


    public static double playerTrophyFishingChance(Player player) {
        double ferocity = SkyblockPlayer.getSkyblockPlayer(player).getBaseTrophyFishChance();

        ferocity *= itemTrophyFishingChance(player.getEquipment().getItemInMainHand()) + 1;
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
                return Items.SkyblockItems.get(data.get(new NamespacedKey(getMain(), "id"), PersistentDataType.STRING)).getTrophyFishChance();
            }
        }

    }


    public synchronized static double getItemStat(SkyblockPlayer player, Stats stat, ItemStack item) {
        if (item == null) {
            return 0;
        }
        ItemManager manager = ItemHandler.getItemManager(item);
        if (manager == null) return 0;
        double value = manager.getStat(stat);
        ItemRarity rarity = manager.getRarity(item, player);
        if (ItemHandler.hasPDC("reforge", item, PersistentDataType.STRING))
            value += RegisteredReforges.reforges.get(ItemHandler.getPDC("reforge", item, PersistentDataType.STRING)).getReforgeValue(rarity, stat);
        if (ItemHandler.getOrDefaultPDC("potatobooks", item, PersistentDataType.INTEGER, 0) > 0) {
            if (stat.getHotPotatoBookStat() != null && stat.getHotPotatoBookStat().contains(manager.type) && stat.getHotPotatoBookStatBoost() > 0) {
                value += stat.getHotPotatoBookStatBoost() * ItemHandler.getOrDefaultPDC("potatobooks", item, PersistentDataType.INTEGER, 0);
            }
        }
        if (manager.gemstoneSlots != null && !manager.gemstoneSlots.isEmpty()) {
            for (GemstoneSlot slot : GemstoneSlot.getCurrGemstones(manager, Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer())) {
                if (slot.currGem == null) continue;
                if (slot.currGem.getStat() != stat) continue;
                value += slot.currGem.getStatBoost(rarity);
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

    public static void saveCoins(Player player) {
        double coin = SkyblockPlayer.getSkyblockPlayer(player).coins;
        ConfigFile c = new ConfigFile(SkyblockPlayer.getSkyblockPlayer(player), "stats");
        c.get().set(player.getUniqueId() + ".coins", coin);
        c.save();
        c.reload();
    }

    public static void saveBits(Player player) {
        int bit = (int) SkyblockPlayer.getSkyblockPlayer(player).bits;
        ConfigFile c = new ConfigFile(SkyblockPlayer.getSkyblockPlayer(player), "stats");
        c.get().set(player.getUniqueId() + ".bits", bit);
        c.save();
        c.reload();
    }

    public static void saveMithrilPowder(Player player) {
        int bit = (int) SkyblockPlayer.getSkyblockPlayer(player).mithrilpowder;
        ConfigFile c = new ConfigFile(SkyblockPlayer.getSkyblockPlayer(player), "stats");
        c.get().set(player.getUniqueId() + ".mithrilpowder", bit);
        c.save();
        c.reload();
    }

    public static ItemStack itemUpdater(ItemStack item, @Nullable SkyblockPlayer player) {
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

        if (data.get(new NamespacedKey(main, "id"), PersistentDataType.STRING) != null) {
            ItemManager manager = Items.SkyblockItems.get(data.get(new NamespacedKey(main, "id"), PersistentDataType.STRING));
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
            String pdcRarity = data.get(new NamespacedKey(main, "rarity"), PersistentDataType.STRING);
            ItemRarity rarity = manager.getRarity(item, player);
            if (getItemStat(player, Stats.BreakingPower, item) != 0) {
                double br = getItemStat(player, Stats.BreakingPower, item);
                Reforge reforge = RegisteredReforges.reforges.get(ItemHandler.getOrDefaultPDC("reforge", item, PersistentDataType.STRING, ""));
                if (reforge != null && reforge.getReforgeValue(rarity, Stats.BreakingPower) > 0) {
                    lores.add("§8Breaking Power " + String.format("%.0f", br) + " §9(+" + reforge.getReforgeValue(rarity, Stats.BreakingPower) + ")");
                } else lores.add("§8Breaking Power " + String.format("%.0f", br));
                lores.add(" ");
            }
            if (manager.type == ItemType.FishingRod) {
                if (Objects.requireNonNull(manager.getRodType()) == RodType.LavaRod) {
                    lores.add("§8Lava Rod");
                }
            }
            Stats.makeItemStatsLore(item, lores, player);
            if (manager.isShortbow())
                lores.add("§7Shot Cooldown: §a" + ((double) manager.getShorbowCooldown(((player != null) ? getPlayerStat(player, Stats.AttackSpeed) : 0)) / 20d) + "s");

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
                lores.add(gomstoneLine);

            }

            if (item.getType() == Material.POTION) {
                lores.addAll(Potion.craftLore(player, item));
            }


            item.getEnchantments();
            if (!item.getEnchantments().isEmpty()) {
                ArrayList<String> enchantLore = new ArrayList<>();
                HashMap<String, Integer> operator = new HashMap<>();
                operator.put("amount", 0);
                operator.put("line", 0);
                Set<CustomEnchantment> ench = new HashSet<>();
                for (Enchantment enchantment : Objects.requireNonNull(item.getItemMeta()).getEnchants().keySet()) {
                    if (enchantment instanceof CustomEnchantment ce) {
                        if (ce == SkyblockEnchants.ENCHANT_GLINT) continue;
                        ench.add(ce);
                        continue;
                    }
                    int level = meta.getEnchantLevel(enchantment);
                    meta.removeEnchant(enchantment);
                    CustomEnchantment ce = SkyblockEnchants.registeredEnchants.get(enchantment.getKey().getKey());
                    if (ce == null) continue;
                    meta.addEnchant(ce, level, true);
                }
                if (!ench.isEmpty()) {
                    lores.add(" ");
                    if (ench.size() > 6) {
                        Bundle<ArrayList<UltimateEnchant>, ArrayList<CustomEnchantment>> enchants = UltimateEnchant.splitEnchants(ench);
                        for (CustomEnchantment enchant : UltimateEnchant.orderEnchants(enchants)) {
                            int level = item.getItemMeta().getEnchants().get(enchant);
                            String prefix = (UltimateEnchant.isUltEnchant(enchant)) ? "§d§l" : "§9";
                            if (!enchant.getName().equals("non") && !enchant.getName().isEmpty()) {
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
                        if (!enchantLore.isEmpty() && !enchantLore.get(0).isEmpty()) {
                            lores.addAll(enchantLore);
                        }
                    } else {
                        Bundle<ArrayList<UltimateEnchant>, ArrayList<CustomEnchantment>> enchants = UltimateEnchant.splitEnchants(ench);
                        for (CustomEnchantment enchantment : UltimateEnchant.orderEnchants(enchants)) {
                            lores.add(((enchantment instanceof UltimateEnchant) ? "§d§l" : "§9") + enchantment.getName() + " " + Tools.intToRoman(ItemHandler.getEnchantmentLevel(enchantment, item)));
                            lores.addAll(enchantment.getLore().makeLore(player, item));
                        }
                    }
                }

            }

            if (manager.getLore() != null && !manager.getLore().isEmpty()) {
                lores.add(" ");
                lores.addAll(manager.getLore());
            }

            if (manager.isAttributable()) {

                for (Attribute attribute : Attribute.getAttributes(item)) {
                    lores.add(" ");
                    lores.addAll(attribute.getAttributeLore());
                }
            }


            if (Pet.pets.containsKey(manager.itemID)) {
                meta.setDisplayName("§7[Lvl " + data.get(new NamespacedKey(main, "level"), PersistentDataType.INTEGER) + "] " + rarity.getPrefix() + Pet.pets.get(manager.itemID).name);
            } else if (data.get(new NamespacedKey(main, "reforge"), PersistentDataType.STRING) != null && RegisteredReforges.reforges.containsKey(data.get(new NamespacedKey(main, "reforge"), PersistentDataType.STRING)))
                meta.setDisplayName(rarity.getPrefix() + data.get(new NamespacedKey(main, "reforge"), PersistentDataType.STRING) + " " + rarity.getPrefix() + manager.name + " " + StarHandler.getStarSuffix(item));
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
                    lores.addAll(firstAblilityLore);
                }
            }
            int i = 0;
            for (Ability ability : manager.getAbilities()) {
                lores.add(" ");
                if (ability.getName() != null) {
                    String headline = manager.getAbilityHeadline(player, i);
                    if (!headline.isBlank()) lores.add(headline);
                }
                if (ability.getLore() != null) lores.addAll(ability.getLore().makeLore(player, item));

                if (ability.getManacost() > 0 && !ability.isPersentage()) {
                    ManaUpdateEvent event = new ManaUpdateEvent(item, ability.getManacost());
                    Bukkit.getPluginManager().callEvent(event);
                    lores.add("§8Mana Cost §3" + String.format("%.0f", event.getMana()));
                } else if (ability.isPersentage()) {
                    ManaUpdateEvent event = new ManaUpdateEvent(item, ability.getPersentage());
                    Bukkit.getPluginManager().callEvent(event);
                    lores.add("§8Mana Cost §3" + String.format("%.0f", event.getMana()) + "%");
                }

                if (ability.getSoulflowCost() > 0) lores.add("§8Soulflow Cost: §3" + ability.getSoulflowCost() + "⸎");

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

                if (data.get(new NamespacedKey(main, "fueltank"), PersistentDataType.STRING) != null) {
                    lores.add(" ");
                    DrillPart part = DrillPart.parts.get(data.get(new NamespacedKey(main, "fueltank"), PersistentDataType.STRING));
                    lores.add("§a" + part.name);
                    lores.addAll(part.appliedLore);

                } else {
                    lores.add("§7Fuel Tank: §cNot Installed");
                    lores.add("§7Increases fuel capacity with");
                    lores.add("§7part installed.");
                }
                lores.add(" ");
                if (data.get(new NamespacedKey(main, "drillengine"), PersistentDataType.STRING) != null) {
                    DrillPart part = DrillPart.parts.get(data.get(new NamespacedKey(main, "drillengine"), PersistentDataType.STRING));
                    lores.add("§a" + part.name);
                    lores.addAll(part.appliedLore);
                } else {
                    lores.add("§7Drill Engine: §cNot Installed");
                    lores.add("§7Increases §6⸕Mining Speed");
                    lores.add("§7with part installed.");
                }
                lores.add(" ");
                if (data.get(new NamespacedKey(main, "upgrademodule"), PersistentDataType.STRING) != null) {
                    DrillPart part = DrillPart.parts.get(data.get(new NamespacedKey(main, "upgrademodule"), PersistentDataType.STRING));
                    lores.add("§a" + part.name);
                    lores.addAll(part.appliedLore);
                } else {
                    lores.add("§7Upgrade Module: §cNot Installed");
                    lores.add("§7Aplies a passive upgrade with");
                    lores.add("§7part installed.");
                }
                lores.add(" ");

                lores.add("§7Fuel: §2" + data.get(new NamespacedKey(main, "fuel"), PersistentDataType.INTEGER) + "§8/" + data.get(new NamespacedKey(main, "maxfuel"), PersistentDataType.INTEGER));

            }

            if (data.get(new NamespacedKey(main, "reforge"), PersistentDataType.STRING) != null && RegisteredReforges.reforges.containsKey(data.get(new NamespacedKey(main, "reforge"), PersistentDataType.STRING)) && RegisteredReforges.reforges.get(data.get(new NamespacedKey(main, "reforge"), PersistentDataType.STRING)).getLore() != null) {
                lores.add("");
                lores.addAll(RegisteredReforges.reforges.get(data.get(new NamespacedKey(main, "reforge"), PersistentDataType.STRING)).getLore());
                lores.add("");
            }

            if (Pet.pets.containsKey(manager.itemID)) {
                lores.add(" ");
                if (data.getOrDefault(new NamespacedKey(main, "level"), PersistentDataType.INTEGER, 1) == (Pet.pets.get(manager.itemID).MaxLevel))
                    lores.add("§b§lMax Level!");
                else if (data.getOrDefault(new NamespacedKey(main, "level"), PersistentDataType.INTEGER, 1) > (Pet.pets.get(manager.itemID).MaxLevel))
                    lores.add("§c§l§k l §r§c§lOver Leveled! §kl");
                else {
                    double currxp = data.getOrDefault(new NamespacedKey(main, "currxp"), PersistentDataType.DOUBLE, 0d);
                    double reqxp = Pet.pets.get(manager.itemID).getRequieredXp(data.getOrDefault(new NamespacedKey(main, "level"), PersistentDataType.INTEGER, 1));
                    double pers = currxp / reqxp;
                    int colored = (int) (20 * pers);
                    StringBuilder str = new StringBuilder();
                    for (int c = 0; c < 20; c++) {
                        if (colored > c) str.append("§2");
                        else str.append("§7");
                        str.append("-");

                    }
                    str.append(" §e").append(Tools.round(currxp, 1)).append("§6/§e").append(Tools.toShortNumber((int) reqxp));


                    lores.add(str.toString());


                }
            }


            if (manager.hasEdition) {
                lores.add(" ");
                String to = "§7To: §f-";
                if (data.get(new NamespacedKey(main, "to"), PersistentDataType.STRING) != null) {
                    to = "§7To: §f" + data.get(new NamespacedKey(main, "to"), PersistentDataType.STRING);
                }


                String from = "§7From: §f-";
                if (data.get(new NamespacedKey(main, "from"), PersistentDataType.STRING) != null) {
                    from = "§7From: §f" + data.get(new NamespacedKey(main, "from"), PersistentDataType.STRING);
                }

                String edition = "§8Edition: N/A";
                if (data.get(new NamespacedKey(main, "editionnumber"), PersistentDataType.INTEGER) != null) {
                    edition = "§8Edition: #" + data.get(new NamespacedKey(main, "editionnumber"), PersistentDataType.INTEGER);
                }

                String date = "§8N/A";
                if (data.get(new NamespacedKey(main, "date"), PersistentDataType.STRING) != null) {
                    date = "§8" + data.get(new NamespacedKey(main, "date"), PersistentDataType.STRING);
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
            if (ItemHandler.isDungeonItem(item)) extra = "DUNGEON ";

            if (ItemHandler.getOrDefaultPDC("recomed", item, PersistentDataType.INTEGER, 0) == 0)
                lores.add(((item.getType() == Material.POTION) ? me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getRarityFromLevel(me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getHighestLevel(item)).getRarityName() : rarity.getRarityName()) + " " + extra + manager.type.toString().toUpperCase());
            else {
                lores.add(((item.getType() == Material.POTION) ? me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getRarityFromLevel(me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getHighestLevel(item)).getNext().getPrefix() : rarity.getPrefix()) + "§k§lr§r " + ((item.getType() == Material.POTION) ? me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getRarityFromLevel(me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionEffect.getHighestLevel(item)).getNext().getRarityName() : rarity.getRarityName()) + " " + extra + manager.type.toString().toUpperCase() + " §kr");
            }
            meta.setLore(lores);

        } else {

            if (item.getType() == Material.ENCHANTED_BOOK) {

                ItemStack newItem = Items.SkyblockItems.get("ENCHANTED_BOOK").getRawItemStack();

                if (item.getItemMeta() instanceof EnchantmentStorageMeta enchStorage) {

                    ArrayList<String> enchantLore = new ArrayList<>();
                    HashMap<String, Integer> operator = new HashMap<>();
                    operator.put("amount", 0);
                    operator.put("line", 0);
                    meta = newItem.getItemMeta();
                    for (Enchantment enchant : enchStorage.getStoredEnchants().keySet()) {
                        int level = enchStorage.getStoredEnchantLevel(enchant);
                        assert meta != null;
                        meta.addEnchant(enchant, level, true);

                    }
                    enchStorage.getStoredEnchants().forEach((enchant, level) -> {

                        if (!enchant.getName().equals("non") && !enchant.getName().isBlank()) {
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
                    lore.addAll(enchantLore);
                }

                item = newItem;

            } else {
                if ((data.get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING) == null || !data.get(NamespacedKey.minecraft("spetial"), PersistentDataType.STRING).equals("fakearrow")) && data.get(new NamespacedKey(main, "type"), PersistentDataType.STRING) == null) {

                    int amount = item.getAmount();

                    item = Items.SkyblockItems.get(item.getType().name()).getRawItemStack();
                    item.setAmount(amount);
                    meta = item.getItemMeta();
                }

            }

            assert meta != null;
            meta.setLore(lore);
        }

        item.setItemMeta(meta);

        return item;

    }

    public BukkitRunnable getStatRunnable() {
        return statrunnable;
    }

    public void setTime(int time) {
        this.time = time;
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
            string = string + "";
        } else {
            if (hours <= 9) {
                string = string + "0" + hours + " Stunden ";
            } else {
                string = string + hours + " Stunden ";
            }
        }
        if (minutes == 0) {
            string = string + "";
        } else {
            if (minutes <= 9) {
                string = string + "0" + minutes + " Minuten ";
            } else {
                string = string + minutes + " Minuten ";
            }
        }
        if (seconds == 0) {
            string = string + "";
        } else {
            if (seconds <= 9) {
                string = string + "0" + seconds + " Sekunden";
            } else {
                string = string + seconds + " Sekunden";
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

    public static void loadNPC() {
        FileConfiguration file = data.getConfig();
        Objects.requireNonNull(data.getConfig().getConfigurationSection("data")).getKeys(false).forEach(npc -> {
            Location location = new Location(Bukkit.getWorld(Objects.requireNonNull(file.getString("data." + npc + ".world"))), file.getDouble("data." + npc + ".x"), file.getDouble("data." + npc + ".y"), file.getDouble("data." + npc + ".z"));
            location.setPitch((float) file.getDouble("data." + npc + ".p"));
            location.setYaw((float) file.getDouble("data." + npc + ".ya"));

            String name = file.getString("data." + npc + ".name");
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
            gameProfile.getProperties().put("rextures", new Property("textures", file.getString("data." + npc + ".text"), file.getString("data." + npc + ".signature")));

            me.CarsCupcake.SkyblockRemake.NPC.NPC.loadNPC(location, gameProfile);
        });
    }

    private static String makeStringFromID(NamespacedKey key) {
        StringBuilder newString = new StringBuilder();
        String itemName = key.getKey();
        String[] minis = itemName.split("_");
        for (int i = 0; i != minis.length; i++) {
            minis[i] = minis[i].toLowerCase();
            minis[i] = minis[i].substring(0, 1).toUpperCase() + minis[i].substring(1).toLowerCase();

            if (i == minis.length - 1) {
                newString.append(minis[i]);
            } else newString.append(minis[i]).append(" ");

        }
        return newString.toString();
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

    public boolean checkIfBungee() {
        return Objects.requireNonNull(getServer().spigot().getConfig().getConfigurationSection("settings")).getBoolean("bungeecord");
    }

    public static Main getMain() {
        return main;
    }
}
