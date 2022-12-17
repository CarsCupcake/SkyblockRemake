package me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Mithril;

import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.MiningSystem.MiningBlock;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MithrilBlue extends MiningBlock {
    private Material beforeMat;
    @Override
    public int blockStrength() {
        return 1500;
    }
    @Override
    public int getInstaMineSpeed() {
        return 90000;
    }
    @Override
    public long regenTime(){
        return 8*20;
    }

    @Override
    public Material getType() {
        return null;
    }

    @Override
    public int getBreakingPower() {
        return 4;
    }

    @Override
    public ArrayList<ItemStack> getDrops(SkyblockPlayer player) {
        ItemStack item = Items.Mithril();
        item.setAmount(SkyblockRemakeEvents.dropAmount((int)Main.getPlayerMiningFortune(player), 1));
        return new ArrayList<>(List.of(
                Main.item_updater(item, player)
        ));
    }
    @Override
    public void breakBlock(Block b, SkyblockPlayer player){
        this.block = b;
        beforeMat = b.getType();
        double chance = player.titaniumchance / 100;
        double i = new Random().nextDouble();
        boolean isTitanium = i <= chance;

        if(isTitanium){
            b.setType(Material.POLISHED_DIORITE);
            new TitanumHandler(b, beforeMat);
        }else super.breakBlock(b,player);
        dropItems(player);
    }
    @Override
    public Material resetType(){return beforeMat;}
    @Override
    public void reset(){
        block.setType(beforeMat);
    }
}
