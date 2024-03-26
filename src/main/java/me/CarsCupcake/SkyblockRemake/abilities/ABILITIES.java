package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.Items.Attributes.*;
import me.CarsCupcake.SkyblockRemake.Items.Gemstones.GemstoneBuffsEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.Pets.Abilitys.GriffinListener;
import me.CarsCupcake.SkyblockRemake.Items.blocks.CustomBlock;
import me.CarsCupcake.SkyblockRemake.Items.blocks.customBlocks.LogicalBlock;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.armor.CropieArmor;
import me.CarsCupcake.SkyblockRemake.Items.farming.FarmingUtils;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.armor.FermentoArmor;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.armor.MelonArmor;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.armor.SquashArmor;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.axes.CocoChopper;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.axes.MelonDicer;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.axes.PumpkinDicer;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.hoes.CactusKnife;
import me.CarsCupcake.SkyblockRemake.Items.farming.items.hoes.FungiCutter;
import me.CarsCupcake.SkyblockRemake.Items.minions.IMinionData;
import me.CarsCupcake.SkyblockRemake.Items.minions.MinionListener;
import me.CarsCupcake.SkyblockRemake.Items.minions.implementations.combat.ZombieMinionData;
import me.CarsCupcake.SkyblockRemake.Items.minions.implementations.mining.CobblestoneMinionData;
import me.CarsCupcake.SkyblockRemake.Items.trading.TradeMenuListener;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs.MinosChampion;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs.Minotaur;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.CollectHandler;
import me.CarsCupcake.SkyblockRemake.abilities.WandSpells.AvadaKedavra;
import me.CarsCupcake.SkyblockRemake.abilities.WandSpells.BasicSpell;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.Terminals.FallDownArmorstand;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.Terminals.StationaryTerminals.ArrowPointing;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.Terminals.StationaryTerminals.ArrowShooting;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.Terminals.StationaryTerminals.SimonSaysTerminal;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Secrets.BatSecret;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Secrets.ChestSecret;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Secrets.LeverSecret;
import me.CarsCupcake.SkyblockRemake.isles.End.Dragon.DragonAi.Loot;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Equipment.EquipmentManager;
import me.CarsCupcake.SkyblockRemake.Items.AbilityPreExecuteEvent;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.MiningSys;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks.Cobblestone;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks.EndStone;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks.Gemstone.JadeGem;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks.Gemstone.JadeShart;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks.Mithril.MithrilBlue;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks.Mithril.MithrilGeen;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks.Mithril.MithrilGrey;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks.Mithril.TitanumHandler;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks.Stone;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks.Titanium;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.MiningBlock;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.Effect;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionItems;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Potion.PotionListener;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.JerryListener;
import me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.BlazeSlayerListener;
import me.CarsCupcake.SkyblockRemake.Slayer.blaze.ItemAbility.TwilightDaggerHit;
import me.CarsCupcake.SkyblockRemake.Slayer.enderman.EndermanListener;
import me.CarsCupcake.SkyblockRemake.Slayer.enderman.EndermanT1;
import me.CarsCupcake.SkyblockRemake.isles.hub.HubManager;
import me.CarsCupcake.SkyblockRemake.isles.privateIsle.PrivateIsleListener;
import me.CarsCupcake.SkyblockRemake.isles.rift.items.AdminItems;
import me.CarsCupcake.SkyblockRemake.isles.rift.items.QuestItems;
import me.CarsCupcake.SkyblockRemake.isles.rift.boss.leechSupreme.LeechFightManager;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.ints.IntArrayList;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.ints.IntList;

public class ABILITIES implements Listener {
    public static void init() {
        registerEvent(new DreadlordHandler());
        registerEvent(new PreAbilityExecution() {
            @Override
            public void preEvent(AbilityPreExecuteEvent event) {

            }
        });
        registerEvent(new IceSprayWand());
        registerEvent(new JerryListener());
        registerEvent(new EquipmentManager());
        registerEvent(new StaticCharge());
        registerEvent(new Spirit());
        registerEvent(new EndermanT1(null));
        registerEvent(new Teleporters());
        registerEvent(new Loot());
        registerEvent(new ProtectiveBlood());
        registerEvent(new TabListManager());
        registerEvent(new CollectHandler());
        registerEvent(new ABILITIES());
        registerEvent(new KatanaDamagingAbilities());
        if (SkyblockServer.getServer().type() == ServerType.PrivateIsle) registerEvent(new PrivateIsleListener());
        registerEvent(new SwingRangeStat());
        registerEvent(new TwilightDaggerHit.DaggerHit());
        if (SkyblockServer.getServer().type() == ServerType.F7) {
            registerEvent(new F7Phase3(true));
            registerEvent(new SimonSaysTerminal(null, -1));
            registerEvent(new ArrowShooting(null, -1));
            registerEvent(new ArrowPointing(null, -1));
            registerEvent(new FallDownArmorstand(null, null));
        }
        Attribute.registerAttribute(new ManaPool());
        Attribute.registerAttribute(new MagicFind());
        Attribute.registerAttribute(new AttackSpeed());
        Attribute.registerAttribute(new FishingSpeed());
        Attribute.registerAttribute(new Fortitude());
        Attribute.registerAttribute(new Hunter());
        Attribute.registerAttribute(new LifeRegeneration());
        Attribute.registerAttribute(new Speed());
        Attribute.registerAttribute(new Vitality());
        Attribute.registerAttribute(new ManaRegeneration());
        Effect.init();
        new PotionItems();
        registerEvent(new PotionListener());
        registerEvent(new BlazeSlayerListener());
        registerEvent(new EndermanListener());
        registerEvent(new BlockPlaceAbility.Listener());
        registerEvent(new MinionListener());
        registerEvent(new FarmingUtils());
        registerEvent(new MelonArmor());
        registerEvent(new CropieArmor());
        registerEvent(new SquashArmor());
        registerEvent(new FermentoArmor());
        registerEvent(new MelonDicer());
        registerEvent(new PumpkinDicer());
        registerEvent(new CocoChopper());
        registerEvent(new FungiCutter());
        registerEvent(new CactusKnife());
        registerEvent(new LeverSecret.LeverInteract());
        registerEvent(new ChestSecret.ChestInteract());
        registerEvent(new BatSecret.EventListener());
        registerEvent(new MinosChampion(ItemRarity.COMMON, null));
        registerEvent(new GriffinListener());
        registerEvent(new Minotaur(null, null));
        registerEvent(new GemstoneBuffsEvent());

        //Minions
        IMinionData.registerMinion(new CobblestoneMinionData());
        IMinionData.registerMinion(new ZombieMinionData());

        if(SkyblockServer.getServer().type() == ServerType.PrivateIsle){
            registerEvent(new CustomBlock.CustomBlockListener());
        }
        LogicalBlock.init();
        registerEvent(new TradeMenuListener());
        QuestItems.init();
        AdminItems.init();

        if (ServerType.getActiveType() == ServerType.Hub)
            new HubManager();
    }

    public static void disable() {
        Totem.stopAll();
    }

    public static void registerEvent(Listener listener) {
        try {
            Main.getMain().getServer().getPluginManager().registerEvents(listener, Main.getMain());
        } catch (Exception ignored) {

        }
    }

    @EventHandler
    public void disable(PluginDisableEvent event) {
        if (event.getPlugin().equals(Main.getMain())) {
            MiningBlock.getBlocks().forEach(me.CarsCupcake.SkyblockRemake.isles.MiningSystem.MiningBlock::reset);
            TitanumHandler.getHandlers().values().forEach(TitanumHandler::reset);
        }
        LeechFightManager.FakeBlock.releaseAll();
        IntList integers = new IntArrayList(PlayerDisguise.fake.keySet());
        for (Player p : Bukkit.getOnlinePlayers()) ((CraftPlayer) p).getHandle().b.sendPacket(new PacketPlayOutEntityDestroy(integers));
    }

    @EventHandler
    public void enable(PluginEnableEvent event) {

        if (event.getPlugin().equals(Main.getMain())) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    TitanumHandler.getHandlers().values().forEach(TitanumHandler::tick);
                }
            }.runTaskTimer(Main.getMain(), 20, 20);

            MiningSys.getRegisteredBlocks().put(Material.STONE, Stone.class);
            MiningSys.getRegisteredBlocks().put(Material.COBBLESTONE, Cobblestone.class);
            if (SkyblockServer.getServer().type() == ServerType.End)
                MiningSys.getRegisteredBlocks().put(Material.END_STONE, EndStone.class);

            MiningSys.getRegisteredBlocks().put(Material.CYAN_TERRACOTTA, MithrilGrey.class);
            MiningSys.getRegisteredBlocks().put(Material.GRAY_WOOL, MithrilGrey.class);

            MiningSys.getRegisteredBlocks().put(Material.DARK_PRISMARINE, MithrilGeen.class);
            MiningSys.getRegisteredBlocks().put(Material.PRISMARINE, MithrilGeen.class);
            MiningSys.getRegisteredBlocks().put(Material.PRISMARINE_BRICKS, MithrilGeen.class);

            MiningSys.getRegisteredBlocks().put(Material.LIGHT_BLUE_WOOL, MithrilBlue.class);

            MiningSys.getRegisteredBlocks().put(Material.POLISHED_DIORITE, Titanium.class);

            MiningSys.getRegisteredBlocks().put(Material.LIME_STAINED_GLASS_PANE, JadeShart.class);
            MiningSys.getRegisteredBlocks().put(Material.LIME_STAINED_GLASS, JadeGem.class);

            //Register Spells
            Wand.Spell.registerSpells(new BasicSpell());
            Wand.Spell.registerSpells(new AvadaKedavra());

            Wand.SpellSelect.buildInventorys();
        }

    }
}
