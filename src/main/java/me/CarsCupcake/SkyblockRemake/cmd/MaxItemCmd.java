package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.StarHandler;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class MaxItemCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player p)) return false;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
        ItemStack item = player.getItemInHand();
        if (item == null || !item.hasItemMeta()) {
            player.sendMessage("§cInvalid item!");
            return true;
        }
        ItemManager manager = ItemHandler.getItemManager(item);
        ItemHandler.setPDC("recomed", item, PersistentDataType.INTEGER, 1);
        for (CustomEnchantment enchantment : SkyblockEnchants.registeredEnchants.values()) {
            if (enchantment.canEnchantItem(item)) ItemHandler.setEnchant(enchantment, enchantment.getMaxLevel(), item);
        }
        if (manager.isDungenoizanble()) ItemHandler.dungeonize(item, true);
        if (manager.getMaxStars() > 0) StarHandler.setStars(item, manager.getMaxStars());
        if (ItemHandler.isDungeonItem(item)) StarHandler.setMasterStars(item, 5);
        Main.itemUpdater(item, player);
        return true;
    }
}
