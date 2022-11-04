package me.CarsCupcake.SkyblockRemake.End;

import me.CarsCupcake.SkyblockRemake.End.Dragon.StartFight;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.craftbukkit.v1_17_R1.block.data.type.CraftEndPortalFrame;
import org.bukkit.craftbukkit.v1_17_R1.block.impl.CraftEnderPortalFrame;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class EnderAltarFrame {
    private static HashMap<Location, EnderAltarFrame> frames = new HashMap<>();
    private final Location location;
    private SkyblockPlayer placer;
    private ItemStack placed;
    private static int alrPlaced = 0;

    public EnderAltarFrame(Location location){
        this.location = location;
        frames.put(location, this);
    }
    public static EnderAltarFrame getFrame(Location location){
        return frames.get(location);
    }
    public void place(SkyblockPlayer player){
        placer = player;
        EndPortalFrame frame = (EndPortalFrame) location.getBlock().getBlockData();
        frame.setEye(true);
        location.getBlock().setBlockData(frame);

        if(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"),PersistentDataType.STRING).equals(EndItems.Items.SummoningEye.getItem().itemID)) {
            placed = player.getItemInHand();
            ItemStack item = EndItems.Items.SleepingEye.getItem().getRawItemStack();
            placed.setItemMeta(item.getItemMeta());
            Main.item_updater(placed, placer);
        }
        alrPlaced++;
        Bukkit.broadcastMessage("§5☬ §d"+player.getName()+" placed a Summoning Eye! " + ((alrPlaced == 8) ? "Brace yourselve! §7(§a8§7/§a8§7)" : "§7(§e" + alrPlaced + "§7/§a8§7)"));
        if(alrPlaced == 8)
            startBoss();
    }
    public void pickup(){
        if(placer == null)
            throw new IllegalArgumentException("There is no eye placed!");
        EndPortalFrame frame = (EndPortalFrame) location.getBlock().getBlockData();
        frame.setEye(false);
        location.getBlock().setBlockData(frame);

        if(placed != null){
            ItemStack item = EndItems.Items.SummoningEye.getItem().getRawItemStack();
            placed.setItemMeta(item.getItemMeta());
            Main.item_updater(placed, placer);
        }

        if(placer.getItemInHand().getItemMeta() != null && placer.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING).equals(EndItems.Items.SleepingEye.getItem().itemID))
            placer.setItemInHand(placed);
        placed = null;
        placer = null;
        alrPlaced--;
    }
    public void transform(){
        StartFight.placedEyes.put(location, placer);

        EndPortalFrame frame = (EndPortalFrame) location.getBlock().getBlockData();
        frame.setEye(false);
        location.getBlock().setBlockData(frame);
        if(placed != null){
            ItemStack item = EndItems.Items.RemnantOfTheEye.getItem().getRawItemStack();
            placed.setItemMeta(item.getItemMeta());
            Main.item_updater(placed, placer);
        }
        placer = null;
        placed = null;

    }
    public SkyblockPlayer getPlacer(){
        return placer;
    }
    public static void startBoss(){
        for (EnderAltarFrame frame : frames.values())
            frame.transform();
        StartFight.startDragonFight();
        alrPlaced = 0;
    }
}
