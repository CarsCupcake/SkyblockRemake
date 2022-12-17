package me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks;

import me.CarsCupcake.SkyblockRemake.Commission.Commission;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks.Mithril.TitanumHandler;
import me.CarsCupcake.SkyblockRemake.MiningSystem.MiningBlock;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Titanium extends MiningBlock {
    @Override
    public int blockStrength() {
        return 2000;
    }

    @Override
    public int getInstaMineSpeed() {
        return 120000;
    }

    @Override
    public Material getType() {
        return Material.POLISHED_DIORITE;
    }

    @Override
    public int getBreakingPower() {
        return 5;
    }

    @Override
    public ArrayList<ItemStack> getDrops(SkyblockPlayer player) {
        ItemStack item = Items.Titanium();
        item.setAmount(SkyblockRemakeEvents.dropAmount((int) Main.getPlayerMiningFortune(player), 2, 4));

        return new ArrayList<>(List.of(Main.item_updater(item, player)));
    }
    @Override
    public void breakBlock(Block b, SkyblockPlayer player){
        this.block = b;
        if(!TitanumHandler.getHandlers().containsKey(b))
            return;

        Commission.updateMiningCommission(player, false);

        TitanumHandler.getHandlers().get(b).blockBreak();
        dropItems(player);
    }
}
