package me.CarsCupcake.SkyblockRemake.isles.MiningSystem;

import java.util.Date;


import lombok.Setter;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;



public class CurrentMiningBlock {
	
    public Block block;
    public Player player;
    public ItemStack item;
    @Setter
    public Date lastChanged;
    @Setter
    int ticks;
    public CurrentMiningBlock(Block block, Player player, ItemStack item, int ticks, Date lastChanged){
        this.block = block;
        this.player = player;
        this.item = item;
        this.ticks = ticks;
        this.lastChanged = lastChanged;
    }


}
