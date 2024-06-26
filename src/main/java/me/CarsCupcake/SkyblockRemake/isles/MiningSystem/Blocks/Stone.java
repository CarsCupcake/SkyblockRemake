package me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks;


import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.MiningBlock;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Stone extends MiningBlock {

    @Override
    public int blockStrength() {
        return 15;
    }

    @Override
    public int getInstaMineSpeed() {
        return 450;
    }

    @Override
    public Material getType() {
        return Material.STONE;
    }

    @Override
    public int getBreakingPower() {
        return 1;
    }

    @Override
    public ArrayList<ItemStack> getDrops(SkyblockPlayer player) {
        return new ArrayList<>(List.of(Main.itemUpdater(
                Main.itemUpdater(new ItemStack(Material.COBBLESTONE), player)
                , player)));
    }
    @Override
    public Material blockIfBroken(){
        if(block.getType() == Material.COBBLESTONE)
            return Material.BEDROCK;
        else return Material.COBBLESTONE;
    }
    @Override
    public Material resetType(){
        if(block.getType() == Material.COBBLESTONE)
            return Material.STONE;
        else return Material.COBBLESTONE;
    }
    @Override
    public void breakBlock(Block b, SkyblockPlayer player){
        super.breakBlock(b,player);
    }
}
