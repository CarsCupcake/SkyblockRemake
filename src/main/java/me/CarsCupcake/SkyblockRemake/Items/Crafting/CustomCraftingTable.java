package me.CarsCupcake.SkyblockRemake.Items.Crafting;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Set;


public class CustomCraftingTable implements Listener {
    private static final int CRAFTING_SLOT = 23;

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (!event.getView().getTitle().equals("§8Crafting Table")) return;
        Bukkit.getScheduler().runTaskLater(Main.getMain(), () -> checkAndProcess(event.getView().getTopInventory()), 1);
    }

    @EventHandler
    public void onSwap(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§8Crafting Table")) return;

        if (event.getClickedInventory() == null) return;

        if (event.getClickedInventory().getType() != InventoryType.PLAYER) {
            int slot = event.getSlot();
            if (!(Tools.isInRange(9, 13, slot) || Tools.isInRange(18, 22, slot) || Tools.isInRange(27, 31, slot))) {
                event.setCancelled(true);
                if (event.getSlot() != CRAFTING_SLOT) return;
            }
        }
        Bukkit.getScheduler().runTaskLater(Main.getMain(), () -> {
            SkyblockRecipe recipe = getResult(event.getView().getTopInventory());
            if (event.getSlot() == CRAFTING_SLOT && recipe != null) {
                event.getView().setCursor(craft(recipe, event.getView().getTopInventory(), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())));
            }
            checkAndProcess(event.getView().getTopInventory());
        }, 1);
    }

    private void checkAndProcess(Inventory inventory) {
        SkyblockRecipe recipe = getResult(inventory);
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) inventory.getViewers().get(0));
        if (recipe == null) {
            inventory.setItem(CRAFTING_SLOT, CustomCraftingTableInvenotry.CraftDenie());
            return;
        }
        inventory.setItem(CRAFTING_SLOT, getItem(recipe, inventory, player));
    }

    private static final int[] CRAFTING_SLOTS = {10, 11, 12, 19, 20, 21, 28, 29, 30};

    private SkyblockRecipe getResult(Inventory inventory) {
        ArrayList<ItemStack> craftingGrid = new ArrayList<>();
        for (int i : CRAFTING_SLOTS)
            craftingGrid.add(inventory.getItem(i));
        Set<SkyblockRecipe> recipes = SkyblockRecipe.checkForRecipe(craftingGrid);
        if (recipes.isEmpty()) return null;
        return recipes.iterator().next();
    }

    private ItemStack craft(SkyblockRecipe recipe, Inventory inventory, SkyblockPlayer player) {
        if (recipe instanceof SkyblockShapedRecipe s) return craft(s, inventory, player);
        if (recipe instanceof SkyblockShapelessRecipe s) return craft(s, player);
        throw new IllegalArgumentException("This type of recipe is not supported!");
    }

    private ItemStack craft(SkyblockShapedRecipe recipe, Inventory inventory, SkyblockPlayer player) {
        ItemStack item = getItem(recipe, inventory, player);
        int c = 0;
        for (int i : CRAFTING_SLOTS) {
            if (recipe.getRecipe().get(c) == null) continue;
            inventory.getItem(i).setAmount(inventory.getItem(i).getAmount() - recipe.getRecipe().get(c).amount());
            c++;
        }
        return item;
    }

    private ItemStack craft(SkyblockShapelessRecipe recipe, SkyblockPlayer player) {
        return getItem(recipe, player);
    }

    private ItemStack getItem(SkyblockRecipe recipe, Inventory inventory, SkyblockPlayer player) {
        if (recipe instanceof SkyblockShapedRecipe s) return getItem(s, inventory, player);
        if (recipe instanceof SkyblockShapelessRecipe s) return getItem(s, player);
        throw new IllegalArgumentException("This type of recipe is not supported!");
    }

    private ItemStack getItem(SkyblockShapedRecipe recipe, Inventory inventory, SkyblockPlayer player) {
        ItemStack item = recipe.getResult().createNewItemStack();
        item.setAmount(recipe.getAmount());
        if (recipe.hasPrio())
            applyAttributesFromParent(inventory.getItem(CRAFTING_SLOTS[recipe.getPrior()]), item);
        return Main.itemUpdater(item, player);
    }

    private ItemStack getItem(SkyblockShapelessRecipe recipe, SkyblockPlayer player) {
        ItemStack item = recipe.getResult().createNewItemStack();
        item.setAmount(recipe.getAmount());
        return Main.itemUpdater(item, player);
    }

    private void applyAttributesFromParent(ItemStack prioItem, ItemStack newItem) {
        for (Enchantment enchantment : prioItem.getItemMeta().getEnchants().keySet())
            ItemHandler.setEnchant(enchantment, prioItem.getItemMeta().getEnchantLevel(enchantment), newItem);
        if (ItemHandler.getOrDefaultPDC("recomed", prioItem, PersistentDataType.INTEGER, 0) == 1) recom(newItem);
    }
    private void recom(ItemStack item){
        if(ItemHandler.getOrDefaultPDC("recomed", item, PersistentDataType.INTEGER, 0) != 0)
            return;
        ItemHandler.setPDC("recomed", item, PersistentDataType.INTEGER, 1);
        ItemHandler.setPDC("rarity", item, PersistentDataType.STRING, ItemRarity.valueOf(ItemHandler.getPDC("rarity", item, PersistentDataType.STRING)).getNext().toString());
    }

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
                event.setCancelled(true);
                event.getPlayer().openInventory(CustomCraftingTableInvenotry.createInventory());
            }

        }
    }

    @EventHandler
    public void returnItems(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals("§8Crafting Table")) {
            for (int i = 10; i < 13; i++) {
                if ((event.getView().getItem(i) == null)) continue;
                final int slot = i;
                boolean hasSpace = false;
                int sl = 0;
                for (ItemStack item : event.getView().getBottomInventory().getContents()) {

                    if ((item == null || item.getType() == Material.AIR) && sl < 36) {


                        hasSpace = true;
                        break;
                    }
                    sl++;
                }
                if (hasSpace) event.getPlayer().getInventory().addItem(event.getView().getItem(i));
                else
                    event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), Item.class, it -> it.setItemStack(event.getView().getTopInventory().getItem(slot)));

            }

            for (int i = 19; i < 22; i++) {
                if ((event.getView().getItem(i) == null)) continue;
                final int slot = i;
                boolean hasSpace = false;
                int sl = 0;
                for (ItemStack item : event.getView().getBottomInventory().getContents()) {

                    if ((item == null || item.getType() == Material.AIR) && sl < 36) {


                        hasSpace = true;
                        break;
                    }
                    sl++;
                }
                if (hasSpace) event.getPlayer().getInventory().addItem(event.getView().getItem(i));
                else
                    event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), Item.class, it -> it.setItemStack(event.getView().getTopInventory().getItem(slot)));

            }
            for (int i = 28; i < 31; i++) {
                if ((event.getView().getItem(i) == null)) continue;
                boolean hasSpace = false;
                int sl = 0;
                for (ItemStack item : event.getView().getBottomInventory().getContents()) {

                    if ((item == null || item.getType() == Material.AIR) && sl < 36) {


                        hasSpace = true;
                        break;
                    }
                    sl++;
                }
                if (hasSpace) event.getPlayer().getInventory().addItem(event.getView().getItem(i));
                else event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), event.getView().getItem(i));
            }

        }
    }
}