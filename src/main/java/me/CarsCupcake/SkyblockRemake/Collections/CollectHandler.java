package me.CarsCupcake.SkyblockRemake.Collections;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectHandler implements Listener {
    public static final ArrayList<ICollection> registeredCollections = new ArrayList<>();
    private static final HashMap<SkyblockPlayer,ArrayList<ICollection>> collections = new HashMap<>();
    @EventHandler
    public void onPickup(EntityPickupItemEvent event){
        if(!(event.getEntity() instanceof Player))
            return;
        if(event.getItem().getScoreboardTags().contains("player"))
            return;

        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) event.getEntity());
        collectItem(event.getItem().getItemStack(), player);
        }
    public static void initPlayer(SkyblockPlayer player){
        ArrayList<ICollection> c = new ArrayList<>();
        HashMap<String, ItemCollection> itemC = new HashMap<>();
        for(ICollection collection : registeredCollections) {
            ICollection col = collection.makeNew(player);
            col.load();
            c.add(col);
            if(col instanceof ItemCollection iC)
                itemC.put(iC.getCollecteItemId(), iC);

        }
        collections.put(player, c);
        ItemCollection.itemCollections.put(player, itemC);
    }
    public static void collectItem(ItemStack item, SkyblockPlayer player){
        String itemId = ItemHandler.getPDC("id",item, PersistentDataType.STRING);
        if(ItemCollection.itemCollections.get(player).containsKey(itemId)){
            ItemCollection collection = ItemCollection.itemCollections.get(player).get(itemId);
            CollectionCollectEvent e = new CollectionCollectEvent(player, item.getAmount(), collection);
            Bukkit.getPluginManager().callEvent(e);
            if(!e.isCancelled()) {
                collection.collect(e);
            }
        }
    }

    public static InventoryBuilder getBaseInventory(String name, ICollection collection, ItemStack previewItem){
        return new InventoryBuilder(6, name + " Collection")
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem(new ItemBuilder(previewItem.getType())
                        .setName("§e"+name + " " + collection.getLevel())
                        .addLoreRow("§7View all " + name)
                        .addLoreRow("§7Collection progress and rewards!")
                        .addLoreRow(" ")
                        .addLoreRow("§7Total Collected: §e" + Tools.addDigits(collection.getColectedAmount()))
                        .build(), 4)
                .setItems(new ItemBuilder(Material.BARRIER).setName("§cClose").build(),49);
    }
    public static void openAllCollections(SkyblockPlayer player){
        GUI gui = new GUI(new InventoryBuilder(6, "Collection")
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem(new ItemBuilder(Material.PAINTING)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§aCollection")
                        .addLoreRow("§7View all of the items available")
                        .addLoreRow("§7in Skyblock. Collect more of an")
                        .addLoreRow("§7item to unlock rewards on your")
                        .addLoreRow("§7way to becoming a master of")
                        .addLoreRow("§7Skyblock!")
                        .addLoreRow(" ")
                        .addLoreRow("§7Collections Maxed Out: §cComing Soon!")
                        .build(), 4)
                .setItem(new ItemBuilder(Material.GOLDEN_HOE)
                        .setName("§aFarming Collection")
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addLoreRow("§7View your Farming Collections!")
                        .addLoreRow(" ")
                        .addLoreRow("§7Collection Maxed Out: §cComing Soon")
                        .build(), 20)
                .setItem(new ItemBuilder(Material.STONE_PICKAXE)
                        .setName("§aMining Collection")
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addLoreRow("§7View your Mining Collections!")
                        .addLoreRow(" ")
                        .addLoreRow("§7Collection Maxed Out: §cComing Soon")
                        .build(), 21)
                .setItem(new ItemBuilder(Material.STONE_SWORD)
                        .setName("§aCombat Collection")
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addLoreRow("§7View your Combat Collections!")
                        .addLoreRow(" ")
                        .addLoreRow("§7Collection Maxed Out: §cComing Soon")
                        .build(), 22)
                .setItem(new ItemBuilder(Material.JUNGLE_SAPLING)
                        .setName("§aForaging Collection")
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addLoreRow("§7View your Foraging Collections!")
                        .addLoreRow(" ")
                        .addLoreRow("§7Collection Maxed Out: §cComing Soon")
                        .build(), 23)
                .setItem(new ItemBuilder(Material.FISHING_ROD)
                        .setName("§aFishing Collection")
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addLoreRow("§7View your Fishing Collections!")
                        .addLoreRow(" ")
                        .addLoreRow("§7Collection Maxed Out: §cComing Soon")
                        .build(), 24)
                .setItem(new ItemBuilder(Material.WITHER_SKELETON_SKULL)
                        .setName("§5Boss Collection")
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addLoreRow("§7View your progress and claim")
                        .addLoreRow("§7rewards you have obtained from")
                        .addLoreRow("defeating SkyBlock bosses!")
                        .addLoreRow(" ")
                        .addLoreRow("§7Collection Maxed Out: §cComing Soon")
                        .build(), 31)
                .setItem(new ItemBuilder(Material.BARRIER).setName("§cClose").build(), 49)
                .build());
        gui.setCanceled(true);
        gui.inventoryClickAction(49, type -> gui.closeInventory());
        gui.inventoryClickAction(21, type -> openMiningCollections(player));
        gui.showGUI(player);
    }
    private static void openMiningCollections(SkyblockPlayer player){
        //34
        GUI gui = new GUI(new InventoryBuilder(6, "Mining Collection")
                .fill(TemplateItems.EmptySlot.getItem(), 0, 9)
                .setItem(new ItemBuilder(Material.STONE_PICKAXE)
                        .setName("§aMining Collections")
                        .build(),4)
                .setItem(new ItemBuilder(Material.COBBLESTONE).setName("§eCobblestone " + ItemCollection.itemCollections.get(player).get(Material.COBBLESTONE.toString()).getLevel()).build(),11)
                .setItem(new ItemBuilder(Items.SkyblockItems.get("MITHRIL_ORE").material).setName("§eMithril " + Tools.intToRoman(ItemCollection.itemCollections.get(player).get("MITHRIL_ORE").getLevel())).build(),34)
                .build());
        gui.setCanceled(true);
        gui.inventoryClickAction(11,t -> ItemCollection.itemCollections.get(player).get(Material.COBBLESTONE.toString()).getInventory().showGUI(player));
        gui.inventoryClickAction(34,t -> ItemCollection.itemCollections.get(player).get("MITHRIL_ORE").getInventory().showGUI(player));
        gui.showGUI(player);
    }
    public static ItemBuilder getProgressItem(ICollection collection, int level, String name){
        double per = Tools.round(((double) collection.getColectedAmount())/((double) collection.collectAmount()[level-1]),4);
        if (per > 1) per = 1;
        per*=100;
        per = Tools.round(per, 1);
        String s = (per % 1 == 0) ? ((per == 100) ? "§a" : "§e") + (int)per : "§e" + String.format("%.1f",per);

        return new ItemBuilder(((level <= collection.getLevel()) ? Material.LIME_STAINED_GLASS_PANE : ((level == collection.getLevel() + 1) ? Material.YELLOW_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE)))
                .setName("§a" + name + " " + Tools.intToRoman(level))
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                .addLoreRow(" ")
                .addLoreRow("§7Progress: " + s + "%")
                .setAmount(level);
    }

}
