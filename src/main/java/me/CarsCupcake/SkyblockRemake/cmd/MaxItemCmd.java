package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class MaxItemCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player p)) return false;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
        ItemStack item = player.getItemInHand();
        if(item == null || !item.hasItemMeta()) {
            player.sendMessage("Invalid item!");
            return true;
        }
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        switch (manager.type){
            case Axe -> {
                ItemHandler.setEnchant(Enchantment.DIG_SPEED, 10, item);
                ItemHandler.setEnchant(SkyblockEnchants.ULTIMATE_WISE, 5, item);
            }
            case Sword -> {
                ItemHandler.setEnchant(Enchantment.DAMAGE_ALL, 7, item);
                ItemHandler.setEnchant(Enchantment.FIRE_ASPECT, 3, item);
            }
            case Hoe -> {
                ItemHandler.setEnchant(SkyblockEnchants.REPLENISH, 1, item);
                ItemHandler.setEnchant(SkyblockEnchants.HARVESTING, 6, item);
                ItemHandler.setEnchant(Enchantment.DIG_SPEED, 10, item);
                ItemHandler.setPDC("counter", item, PersistentDataType.INTEGER, 10000000);
            }
        }
        Main.item_updater(item, player);
        return true;
    }
}
