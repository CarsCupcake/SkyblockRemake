package me.CarsCupcake.SkyblockRemake.MiningSystem;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class MiningBlock {
    @Getter
    private static final ArrayList<MiningBlock> blocks = new ArrayList<>();
    protected Block block = null;

    public abstract int blockStrength();
    public abstract int getInstaMineSpeed();
    public abstract Material getType();
    public abstract int getBreakingPower();
    public abstract ArrayList<ItemStack> getDrops(SkyblockPlayer player);
    //Ticks
    public long regenTime(){
        return 5*20;
    }
    public Material blockIfBroken(){return Material.BEDROCK;}
    public Material resetType(){return getType();}

    public double getSoftCap() {
        return Tools.round((6d + 2/3)*blockStrength(), 0);
    }
    public int getMiningTicks(SkyblockPlayer player){
        double mining_speed = Main.getPlayerMiningSpeed(player);
        double SoftCap= getSoftCap();
        if(SoftCap <= mining_speed)
            mining_speed = SoftCap;

        double MiningTime = (blockStrength() * 30)/mining_speed;
        return (int) MiningTime;
    }


    public void breakBlock(Block b, SkyblockPlayer player){

        this.block = b;
        b.setType(blockIfBroken());
        new BukkitRunnable() {
            @Override
            public void run() {
                b.setType(resetType());
                b.getState().update();
            }
        }.runTaskLater(Main.getMain(), regenTime());
        dropItems(player);
    }
    protected void dropItems(SkyblockPlayer player){
        for(ItemStack item : getDrops(player))
            if(item.getType() != Material.AIR && item.getAmount() > 0)
                if(player.getItemInHand().getEnchantments().containsKey(SkyblockEnchants.TELIKINESIS))
                    player.addItem(item);
                else
                    block.getWorld().dropItemNaturally(block.getLocation(),item);
    }
    public void reset(){
        block.setType(getType());
    }

}
