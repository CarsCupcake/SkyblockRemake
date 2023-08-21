package me.CarsCupcake.SkyblockRemake.Items.farming.emchantment;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class Replenish extends CustomEnchantment implements Listener {
    public Replenish() {
        super(new NamespacedKey(Main.getMain(), "replenish"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Replenish";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @EventHandler
    public void onFarm(BlockBreakEvent event){
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        if(player == null) return;
        ItemStack item = player.getItemInHand();
        if(!item.hasItemMeta()) return;
        if(!ItemHandler.hasEnchantment(SkyblockEnchants.REPLENISH, item)) return;
        BlockData data = event.getBlock().getBlockData();
        Material origit = event.getBlock().getType();
        Location l = event.getBlock().getLocation();
        if(data instanceof Ageable){
            new BukkitRunnable(){
                public void run(){
                    Block block = l.getBlock();
                    block.setType(origit);
                    BlockData d = block.getBlockData();
                    ((Ageable) d).setAge(0);
                }
            }.runTaskLater(Main.getMain(), 1);
        }
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[]{ItemType.Hoe};
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Upon breaking crops,nether", "§7wart, or cocoa, automatically", "§7replant from materials in your", "§7inventory.");
    }
}
