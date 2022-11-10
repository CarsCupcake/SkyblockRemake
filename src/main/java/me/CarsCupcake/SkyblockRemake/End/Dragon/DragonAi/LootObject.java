package me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi;

import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import net.minecraft.world.entity.item.EntityItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LootObject {
    private final Player p;
    private final EntityArmorStand stand;
    private final EntityItem entityItem;
    private final ItemStack item;
    private final Location location;
    public LootObject(Player player, Location location, EntityArmorStand stand, EntityItem entityItem, ItemStack item){
        p = player;
        this.entityItem = entityItem;
        this.item = item;
        this.stand = stand;
        this.location = location;
    }
    public void pickup(){
        CraftPlayer entityPlayer = (CraftPlayer) p;
        PacketPlayOutEntityDestroy itemPacket = new PacketPlayOutEntityDestroy(entityItem.getId());
        (entityPlayer.getHandle()).b.sendPacket(itemPacket);
        PacketPlayOutEntityDestroy standPacket = new PacketPlayOutEntityDestroy(stand.getId());
        (entityPlayer.getHandle()).b.sendPacket(standPacket);
        p.getInventory().addItem(item);
        p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has obtained " + item.getItemMeta().getDisplayName() + ChatColor.YELLOW + "!");
        }
    }
    public boolean isInReach(Location location){
        return this.location.distance(location) <= 2.8;
    }
}
