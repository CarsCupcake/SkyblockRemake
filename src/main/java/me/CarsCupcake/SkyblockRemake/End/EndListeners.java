package me.CarsCupcake.SkyblockRemake.End;

import me.CarsCupcake.SkyblockRemake.End.Dragon.StartFight;
import me.CarsCupcake.SkyblockRemake.End.Teleporter.VoidSephultures;
import me.CarsCupcake.SkyblockRemake.End.Teleporter.VoidSephulturesBack;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Teleporters;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.persistence.PersistentDataType;

public class EndListeners implements Listener {
    private static final Block[] endPortalFrames = {new Location(Main.getMain().getServer().getWorld("world"), -669, 9, -275).getBlock(), new Location(Main.getMain().getServer().getWorld("world"), -669, 9, -277).getBlock()
    ,new Location(Main.getMain().getServer().getWorld("world"), -670, 9, -278).getBlock(), new Location(Main.getMain().getServer().getWorld("world"), -672, 9, -278).getBlock(), new Location(Main.getMain().getServer().getWorld("world"), -673, 9, -277).getBlock(),
            new Location(Main.getMain().getServer().getWorld("world"), -673, 9, -275).getBlock(), new Location(Main.getMain().getServer().getWorld("world"), -672, 9, -274).getBlock(),new Location(Main.getMain().getServer().getWorld("world"), -670, 9, -274).getBlock()};

    public static void init(){
        if(SkyblockServer.getServer().getType() == ServerType.End){
            Main.getMain().getServer().getPluginManager().registerEvents(new EndListeners(), Main.getMain());
            Teleporters.registerTeleporter(new VoidSephultures());
            Teleporters.registerTeleporter(new VoidSephulturesBack());
            Main.getMain().getServer().getPluginManager().registerEvents(new ZealotSpawning(),Main.getMain());
            Main.getMain().getServer().getPluginManager().registerEvents(new ZealotBruiserSpawning(),Main.getMain());
            for (Block block : endPortalFrames)
                new EnderAltarFrame(block.getLocation());
        }
    }
    @EventHandler
    public void teleportEvent(EntityTeleportEvent event){
        if(event.getEntity().getType() == EntityType.ENDERMAN)
            event.setCancelled(true);
    }
    @EventHandler
    public void checkPortal(PlayerMoveEvent event){
        if(event.isCancelled())
            return;
        if(event.getTo().getBlock().getType() != Material.NETHER_PORTAL)
            return;

        if(isToZealotBruiser(event.getTo())){
            event.getPlayer().teleport(new Location(Main.getMain().getServer().getWorld("world"), -617.5, 85, -204.5, -90, 0));
            }
        if(isFromZealotBruiser(event.getTo())){
            event.getPlayer().teleport(new Location(Main.getMain().getServer().getWorld("world"), -612.5, 5, -275.5,-90,0));
        }

    }
    public boolean isToZealotBruiser(Location location){
        return (Tools.isInRange(-616, -618, location.getBlock().getX()) && Tools.isInRange(5, 11, location.getY()) && Tools.isInRange(-279,-284,location.getZ())) ||
                (Tools.isInRange(-616, -618, location.getX()) && Tools.isInRange(5, 11, location.getY()) && Tools.isInRange(-268,-273,location.getZ()));
    }
    public boolean isFromZealotBruiser(Location location){
        return  Tools.isInRange(-619, -621, location.getX()) && Tools.isInRange(84, 94, location.getY()) && Tools.isInRange(-207, -199, location.getZ());
    }
    @EventHandler
    public void onEnderEyePlace(PlayerInteractEvent event){
        if(event.getClickedBlock() == null)
            return;
        if(event.getClickedBlock().getType() != Material.END_PORTAL_FRAME)
            return;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        for(Block block : endPortalFrames){
            if(!event.getClickedBlock().equals(block))
                continue;
            if(EnderAltarFrame.getFrame(block.getLocation()).getPlacer() == null){
                if(StartFight.fightActive)
                    return;

                if(player.getItemInHand().getItemMeta() != null && player.getItemInHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)){
                    if(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(EndItems.Items.SummoningEye.getItem().itemID)
                    || player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(EndItems.Items.InfinityEye.getItem().itemID)){
                        EnderAltarFrame.getFrame(block.getLocation()).place(player);
                    }
                }
            }else{
                EnderAltarFrame frame = EnderAltarFrame.getFrame(block.getLocation());
                if(frame.getPlacer() == player){
                    if(player.getItemInHand().getItemMeta() != null && player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(EndItems.Items.SleepingEye.getItem().itemID)){
                        EnderAltarFrame.getFrame(block.getLocation()).pickup();
                    }
                }
            }
        }


    }
    @EventHandler
    public void onShutDown(PluginDisableEvent event){
        if(event.getPlugin() != Main.getMain())
            return;
        for (Block block : endPortalFrames){
            if(EnderAltarFrame.getFrame(block.getLocation()).getPlacer() != null)
                EnderAltarFrame.getFrame(block.getLocation()).pickup();
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        for (Block block : endPortalFrames){
            if(EnderAltarFrame.getFrame(block.getLocation()).getPlacer() == SkyblockPlayer.getSkyblockPlayer(event.getPlayer()))
                EnderAltarFrame.getFrame(block.getLocation()).pickup();
        }
    }

}
