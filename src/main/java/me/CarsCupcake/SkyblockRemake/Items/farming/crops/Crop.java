package me.CarsCupcake.SkyblockRemake.Items.farming.crops;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.PlayerFarmEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Items.farming.FarmingUtils;
import me.CarsCupcake.SkyblockRemake.Items.farming.UpgradebleHoe;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public abstract class Crop {
    public abstract Material getBlockType();

    public abstract String getItemId();

    /**
     * get the drops
     *
     * @return first is lowest drop amount 2. is highest
     */
    protected abstract Bundle<Integer, Integer> getDropAmounts();

    public ItemStack makeDrop(SkyblockPlayer player, Block b) {
        Bundle<Integer, Integer> d = getDropAmounts();
        PlayerFarmEvent event = new PlayerFarmEvent(player, b, Main.getPlayerStat(player, Stats.FarmingFortune), Main.getPlayerStat(player, Stats.FarmingWisdom), new Random().nextInt(d.getLast() - d.getFirst()) + d.getFirst());
        Bukkit.getPluginManager().callEvent(event);
        addSkillXp(event);
        ItemStack item = Items.SkyblockItems.get(getItemId()).createNewItemStack();
        int mult = (1 + ((int) (event.getFarmingFortune() / 100)));
        double rest = (event.getFarmingFortune() / 100)  - ((int)(event.getFarmingFortune() / 100));
        if(rest != 0){
            if(new Random().nextDouble() <= rest)
                mult += 1;
        }
        item.setAmount(event.getDrops() * mult);
        ItemStack itemInHand = player.getItemInHand();
        if(itemInHand.hasItemMeta()){
            ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", itemInHand, PersistentDataType.STRING));
            if(manager != null){
                if(manager.getRarityGrabber() instanceof UpgradebleHoe upgradebleHoe)
                    if(upgradebleHoe.crop == b.getType()) {
                        ItemHandler.setPDC("counter", itemInHand, PersistentDataType.INTEGER, ItemHandler.getOrDefaultPDC("counter", itemInHand, PersistentDataType.INTEGER, 0) + 1);
                        Main.item_updater(itemInHand, player);
                    }
            }
        }
        return Main.item_updater(item, player);
    }
    public void addSkillXp(PlayerFarmEvent event){
        event.getPlayer().addSkillXp((FarmingUtils.farmingXp.get(event.getBlock().getType()) * (1 + (event.getFarmingWisdom() / 100))), Skills.Farming);
    }
}
