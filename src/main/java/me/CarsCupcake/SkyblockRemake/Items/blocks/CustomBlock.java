package me.CarsCupcake.SkyblockRemake.Items.blocks;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public abstract class CustomBlock {
    private static final HashMap<String, CustomBlock> blocks = new HashMap<>();
    protected final Material material;
    protected final ItemManager manager;

    public CustomBlock(@NotNull Material material, ItemManager represent) {

        this.material = material;
        manager = represent;
        blocks.put(manager.itemID, this);
    }

    public void onPlace(BlockPlaceEvent event) {

    }

    public ItemStack onBreak(BlockBreakEvent event, ItemStack dropedItem) {
        return dropedItem;
    }

    public void onInteract(PlayerInteractEvent event) {

    }

    public static class CustomBlockListener implements Listener {
        @EventHandler
        public void onBlockPlace(BlockPlaceEvent event) {
            ItemStack item = event.getItemInHand();
            if (!item.hasItemMeta()) return;
            if (!ItemHandler.hasPDC("id", item, PersistentDataType.STRING)) return;
            String id = ItemHandler.getPDC("id", item, PersistentDataType.STRING);
            if (!blocks.containsKey(id)) return;
            Block block = event.getBlockPlaced();
            CustomBlock customBlock = blocks.get(id);
            block.setType(customBlock.material);
            block.setMetadata("id", new FixedMetadataValue(Main.getMain(), id));
            customBlock.onPlace(event);
        }

        @EventHandler
        public void onBlockPlace(BlockBreakEvent event) {
            Block block = event.getBlock();
            if (!block.hasMetadata("id")) return;
            String id = block.getMetadata("id").get(0).asString();
            if (id == null) return;
            if (!blocks.containsKey(id)) return;
            CustomBlock customBlock = blocks.get(id);
            event.setDropItems(false);
            ItemStack drop = customBlock.onBreak(event, customBlock.manager.createNewItemStack());
            if (drop != null) block.getWorld().dropItemNaturally(Tools.getAsLocation(event.getBlock()), drop);
        }

        @EventHandler
        public void onInteract(PlayerInteractEvent event) {
            if (event.getClickedBlock() == null) return;
            Block block = event.getClickedBlock();
            if (!block.hasMetadata("id")) return;
            String id = block.getMetadata("id").get(0).asString();
            if (id == null) return;
            if (!blocks.containsKey(id)) return;
            CustomBlock customBlock = blocks.get(id);
            customBlock.onInteract(event);
        }
    }
}
