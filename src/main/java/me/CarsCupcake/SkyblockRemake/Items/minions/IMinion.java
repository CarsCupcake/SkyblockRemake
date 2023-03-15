package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import me.CarsCupcake.SkyblockRemake.abilitys.BlockPlaceAbility;
import me.CarsCupcake.SkyblockRemake.isles.privateIsle.PrivateIsle;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public interface IMinion {
    int getLevels();
    String[] getHeadStrings();
    String name();

    /**
     * Get the drops for 1 generate
     * @return Hasmap with a bundle Item - Count and a double for the chance
     */
    HashMap<Bundle<ItemManager, Integer>, Double> drops();

    /**
     * Is for the time between the working actions
     * @return Time in ticks
     */
    long[] timeBetweenActions();
    HashMap<EquipmentSlot, ItemStack> getEquipment();
    ItemStack getItemInHand();
    String id();

    HashMap<String, IMinion> minions = new HashMap<>();

    static void registerMinion(IMinion minion){
        minions.put(minion.id(), minion);

        for (int i = 1; i <= minion.getLevels(); i++){
            ItemManager manager = new ItemManager(minion.name() + " " + Tools.intToRoman(i), minion.id() + "_GENERATOR_" + i, ItemType.Minion, ItemRarity.RARE, minion.getHeadStrings()[i-1]);
            manager.setUnstackeble(true);
            BlockPlaceAbility.addEvent(manager, new MinionPlace(minion, i));
            Items.SkyblockItems.put(manager.itemID, manager);
        }
    }
    class MinionPlace implements BlockPlaceAbility {
        private final IMinion minion;
        private final int level;
        public MinionPlace(IMinion minion, int level){
            this.minion = minion;
            this.level = level;
        }

        @Override
        public boolean place(BlockPlaceEvent event) {
            if(SkyblockServer.getServer().getType() != ServerType.PrivateIsle)
                return false;

            event.getItemInHand().setAmount(event.getItemInHand().getAmount() - 1);
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
            if(!PrivateIsle.isles.get(player).addMinion(minion, level, Tools.getAsLocation(event.getBlock())))
                player.sendMessage("§cYou already placed the maximum amount of minions!");
            return true;
        }
    }
}
