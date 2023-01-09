package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Collections.CollectHandler;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.FallDownArmorstand;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals.ArrowPointing;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals.ArrowShooting;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals.LightsTerminal;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.StationaryTerminals.SimonSaysTerminal;
import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.Loot;
import me.CarsCupcake.SkyblockRemake.Equipment.EquipmentManager;
import me.CarsCupcake.SkyblockRemake.Items.AbilityPreExecuteEvent;
import me.CarsCupcake.SkyblockRemake.Items.Attributes.Attribute;
import me.CarsCupcake.SkyblockRemake.Items.Attributes.MagicFind;
import me.CarsCupcake.SkyblockRemake.Items.Attributes.ManaPool;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.MiningSys;
import me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Cobblestone;
import me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Gemstone.JadeGem;
import me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Gemstone.JadeShart;
import me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Mithril.MithrilBlue;
import me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Mithril.MithrilGeen;
import me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Mithril.MithrilGrey;
import me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Mithril.TitanumHandler;
import me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Stone;
import me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Titanium;
import me.CarsCupcake.SkyblockRemake.MiningSystem.MiningBlock;
import me.CarsCupcake.SkyblockRemake.Pets.PetFollowRunner;
import me.CarsCupcake.SkyblockRemake.Potion.Effect;
import me.CarsCupcake.SkyblockRemake.Potion.PotionEffect;
import me.CarsCupcake.SkyblockRemake.Potion.PotionItems;
import me.CarsCupcake.SkyblockRemake.Potion.PotionListener;
import me.CarsCupcake.SkyblockRemake.Skyblock.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.JerryListener;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.BlazeSlayerListener;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.ItemAbility.TwilightDaggerHit;
import me.CarsCupcake.SkyblockRemake.Slayer.Enderman.EndermanT1;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ABILITYS implements Listener{
    public static void init(){
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
        registerEvent(new ABILITYS());
        registerEvent(new KatanaDamagingAbilitys());
        registerEvent(new Attribute() {
            @Override
            public String name() {
                return null;
            }

            @Override
            public int maxLevel() {
                return 0;
            }

            @Override
            public boolean isAllowed() {
                return false;
            }

            @Override
            public List<String> lore() {
                return null;
            }

            @Override
            public void start() {

            }

            @Override
            public void stop() {

            }
        });
        registerEvent(new SwingRangeStat());
        registerEvent(new TwilightDaggerHit.DaggerHit());
        if(SkyblockServer.getServer().getType() == ServerType.F7){
            registerEvent(new F7Phase3(true));
            registerEvent(new SimonSaysTerminal(null, -1));
            registerEvent(new LightsTerminal(null, -1));
            registerEvent(new ArrowShooting(null, -1));
            registerEvent(new ArrowPointing(null, -1));
            registerEvent(new FallDownArmorstand(null, null));
        }
        Attribute.registerAttribute(new ManaPool(null ,0, null));
        Attribute.registerAttribute(new MagicFind(null ,0, null));
        Effect.init();
        new PotionItems();
        registerEvent(new PotionListener());
        registerEvent(new BlazeSlayerListener());
    }
    public static void disable(){
        Totem.stopAll();
    }
    private static void registerEvent(Listener listener){
        try {
            Main.getMain().getServer().getPluginManager().registerEvents(listener, Main.getMain());
        }catch (Exception ignored){

        }
    }
    @EventHandler
    public void disable(PluginDisableEvent event){
        if(event.getPlugin().equals(Main.getMain())) {
            MiningBlock.getBlocks().forEach(me.CarsCupcake.SkyblockRemake.MiningSystem.MiningBlock::reset);
            TitanumHandler.getHandlers().values().forEach(TitanumHandler::reset);
        }
        Main.petstand.values().forEach(PetFollowRunner::remove);
    }
    @EventHandler
    public void enable(PluginEnableEvent event){
        new BukkitRunnable() {
            @Override
            public void run() {
                TitanumHandler.getHandlers().values().forEach(TitanumHandler::tick);
            }
        }.runTaskTimer(Main.getMain(), 20, 20);
        if(event.getPlugin().equals(Main.getMain())) {
            MiningSys.getRegisteredBlocks().put(Material.STONE, Stone.class);
            MiningSys.getRegisteredBlocks().put(Material.COBBLESTONE, Cobblestone.class);

            MiningSys.getRegisteredBlocks().put(Material.CYAN_TERRACOTTA, MithrilGrey.class);
            MiningSys.getRegisteredBlocks().put(Material.GRAY_WOOL, MithrilGrey.class);

            MiningSys.getRegisteredBlocks().put(Material.DARK_PRISMARINE, MithrilGeen.class);
            MiningSys.getRegisteredBlocks().put(Material.PRISMARINE, MithrilGeen.class);
            MiningSys.getRegisteredBlocks().put(Material.PRISMARINE_BRICKS, MithrilGeen.class);

            MiningSys.getRegisteredBlocks().put(Material.LIGHT_BLUE_WOOL, MithrilBlue.class);

            MiningSys.getRegisteredBlocks().put(Material.POLISHED_DIORITE, Titanium.class);

            MiningSys.getRegisteredBlocks().put(Material.LIME_STAINED_GLASS_PANE, JadeShart.class);
            MiningSys.getRegisteredBlocks().put(Material.LIME_STAINED_GLASS, JadeGem.class);
        }

    }
}
