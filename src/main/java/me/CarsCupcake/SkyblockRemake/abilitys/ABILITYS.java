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
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Jerry.JerryListener;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import me.CarsCupcake.SkyblockRemake.Skyblock.TabListManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.Teleporters;
import me.CarsCupcake.SkyblockRemake.Slayer.Enderman.EndermanT1;
import org.bukkit.event.Listener;

public class ABILITYS {
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
        if(SkyblockServer.getServer().getType() == ServerType.F7){
            registerEvent(new F7Phase3(true));
            registerEvent(new SimonSaysTerminal(null, -1));
            registerEvent(new LightsTerminal(null, -1));
            registerEvent(new ArrowShooting(null, -1));
            registerEvent(new ArrowPointing(null, -1));
            registerEvent(new FallDownArmorstand(null, null));
        }
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
}
