package me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Gemstone;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.MiningSystem.MiningBlock;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class JadeGem extends MiningBlock {
    @Override
    public int blockStrength() {
        return 3200;
    }

    @Override
    public int getInstaMineSpeed() {
        return 192000;
    }

    @Override
    public Material getType() {
        return Material.LIME_STAINED_GLASS;
    }

    @Override
    public int getBreakingPower() {
        return 7;
    }

    @Override
    public long regenTime(){
        return 60*20;
    }

    @Override
    public Material blockIfBroken(){
        return Material.AIR;
    }

    @Override
    public ArrayList<ItemStack> getDrops(SkyblockPlayer player) {
        ArrayList<ItemStack> drops = Tools.applyPristine(Items.SkyblockItems.get("ROUGH_JADE_GEM"), Items.SkyblockItems.get("FLAWED_JADE_GEM"), new Random().nextInt(3) + 3, player);
        int am = drops.get(1).getAmount();
        if(am > 0){
            ItemManager m = Items.SkyblockItems.get("FLAWED_JADE_GEM");
            player.sendMessage("§d§lPRISTINE! §rYou found " + m.rarity.getPrefix() + m.name + " §9x" + am);
        }
        return drops;
    }
}
