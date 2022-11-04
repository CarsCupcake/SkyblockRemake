package me.CarsCupcake.SkyblockRemake.Equipment;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryGUIAction;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class EquipmentInv {
    private Inventory inventory;
    private final SkyblockPlayer player;

    public EquipmentInv(SkyblockPlayer player){

        this.player = player;
        createInventory();


    }
    public void createInventory(){
        InventoryBuilder builder = new InventoryBuilder(6, "Your Equipment and Stats")
                .fill(TemplateItems.EmptySlot.getItem())
                .setItems(player.getItemInHand(), 2);
        if(player.equipmentManager.necklace == null)
        {
            ItemStack item = emptySlotIten();
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§8>Necklace");
            meta.setLore(lore);
            item.setItemMeta(meta);
            builder.setItem( item, 10);
        }else
            builder.setItem( EquipmentManager.buildItem(player.equipmentManager.necklace, player), 10);

        builder.setItem(player.getEquipment().getHelmet(), 11);

        if(player.equipmentManager.cloak == null)
        {
            ItemStack item = emptySlotIten();
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§8>Cloak");
            meta.setLore(lore);
            item.setItemMeta(meta);
            builder.setItem( item, 19);
        }else
            builder.setItem( EquipmentManager.buildItem(player.equipmentManager.cloak, player), 19);

        builder.setItem(player.getEquipment().getChestplate(), 20);

        if(player.equipmentManager.belt == null)
        {
            ItemStack item = emptySlotIten();
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§8>Belt");
            meta.setLore(lore);
            item.setItemMeta(meta);
            builder.setItem( item, 28);
        }else
            builder.setItem( EquipmentManager.buildItem(player.equipmentManager.belt, player), 28);

        builder.setItem(player.getEquipment().getLeggings(), 29);

        if(player.equipmentManager.fist == null)
        {
            ItemStack item = emptySlotIten();
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§8>Gloves");
            lore.add("§8>Braceles");
            meta.setLore(lore);
            item.setItemMeta(meta);
            builder.setItem( item, 37);
        }else
            builder.setItem( EquipmentManager.buildItem(player.equipmentManager.fist, player), 37);

        builder.setItem(player.getEquipment().getBoots(), 38);


        inventory = builder.build();
    }
    private ItemStack emptySlotIten(){
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7Empty Equipment Slot");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);

        return item;
    }
    public void openInventory() {
        GUI gui = new GUI(inventory);
        gui.setCanceled(true);
        gui.setGeneralAction((slot, actionType, type) -> {
            if(actionType == GUI.GUIActions.PlayerClick){
                ItemManager manager = getItem(player, slot);
                if(manager != null){
                    if(manager.type == ItemType.Necklace) {
                        if (player.equipmentManager.necklace != null)
                            swapEquipment(player.getInventory().getItem(slot), player.equipmentManager.necklace, slot);
                        else {
                            player.equipmentManager.equip(player.getInventory().getItem(slot));
                            player.getInventory().getItem(slot).setAmount(0);

                        }
                        new EquipmentInv(player).openInventory();
                    }
                    if(manager.type == ItemType.Belt) {
                        if (player.equipmentManager.belt != null)
                            swapEquipment(player.getInventory().getItem(slot), player.equipmentManager.belt, slot);
                        else {
                            player.equipmentManager.equip(player.getInventory().getItem(slot));
                            player.getInventory().getItem(slot).setAmount(0);
                        }
                        new EquipmentInv(player).openInventory();
                    }
                    if(manager.type == ItemType.Cloak) {
                        if (player.equipmentManager.cloak != null)
                            swapEquipment(player.getInventory().getItem(slot), player.equipmentManager.cloak, slot);
                        else {
                            player.equipmentManager.equip(player.getInventory().getItem(slot));
                            player.getInventory().getItem(slot).setAmount(0);
                        }
                        new EquipmentInv(player).openInventory();
                    }
                    if(manager.type == ItemType.Gauntlet || manager.type == ItemType.Gloves) {
                        if (player.equipmentManager.fist != null)
                            swapEquipment(player.getInventory().getItem(slot), player.equipmentManager.fist, slot);
                        else {
                            player.equipmentManager.equip(player.getInventory().getItem(slot));
                            player.getInventory().getItem(slot).setAmount(0);
                        }
                        new EquipmentInv(player).openInventory();
                    }

                }
            }
        });
        gui.inventoryClickAction(10, type -> {
            if(player.equipmentManager.necklace != null) {
                player.getInventory().addItem(EquipmentManager.buildItem(player.equipmentManager.necklace, player));
                player.equipmentManager.remove(player.equipmentManager.necklace);
                player.equipmentManager.necklace = null;
                player.equipmentManager.saveEquipment();
                new EquipmentInv(player).openInventory();
            }
        });
        gui.inventoryClickAction(19, type -> {
            if(player.equipmentManager.cloak != null) {
                player.getInventory().addItem(EquipmentManager.buildItem(player.equipmentManager.cloak, player));
                player.equipmentManager.remove(player.equipmentManager.cloak);
                player.equipmentManager.cloak = null;
                player.equipmentManager.saveEquipment();
                new EquipmentInv(player).openInventory();
            }
        });
        gui.inventoryClickAction(28, type -> {
            if(player.equipmentManager.belt != null) {
                player.getInventory().addItem(EquipmentManager.buildItem(player.equipmentManager.belt, player));
                player.equipmentManager.remove(player.equipmentManager.belt);
                player.equipmentManager.belt= null;
                player.equipmentManager.saveEquipment();
                new EquipmentInv(player).openInventory();
            }
        });
        gui.inventoryClickAction(37, type -> {
            if(player.equipmentManager.fist != null) {
                player.getInventory().addItem(EquipmentManager.buildItem(player.equipmentManager.fist, player));
                player.equipmentManager.remove(player.equipmentManager.fist);
                player.equipmentManager.fist = null;
                player.equipmentManager.saveEquipment();
                new EquipmentInv(player).openInventory();
            }
        });
        gui.showGUI(player);
    }
    private ItemManager getItem(SkyblockPlayer player, int slot){
        ItemStack item = player.getInventory().getItem(slot);
        if(item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer() != null){
            return Items.SkyblockItems.get(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
        }

        return null;
    }
    private void swapEquipment(ItemStack item, Bundle<ItemManager, HashMap<String, String>> old, int slot){
        ItemStack newItem = EquipmentManager.buildItem(old, player);
        player.equipmentManager.equip(item);
        player.getInventory().setItem(slot, newItem);
    }

}

