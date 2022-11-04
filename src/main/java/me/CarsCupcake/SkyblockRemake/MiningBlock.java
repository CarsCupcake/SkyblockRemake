package me.CarsCupcake.SkyblockRemake;

import java.util.Date;


import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;



public class MiningBlock {
	
    public Block block;
    public Player player;
    public ItemStack item;
    public Date lastChanged;
    int ticks;
    public MiningBlock(Block block, Player player, ItemStack item, int ticks, Date lastChanged){
        this.block = block;
        this.player = player;
        this.item = item;
        this.ticks = ticks;
        this.lastChanged = lastChanged;
    }
   

    public void setTicks(int ticks){
        this.ticks = ticks;
    }
    public void setLastChanged(Date newVal){
        this.lastChanged = newVal;
    }
}
