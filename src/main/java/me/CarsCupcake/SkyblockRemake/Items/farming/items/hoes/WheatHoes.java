package me.CarsCupcake.SkyblockRemake.Items.farming.items.hoes;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.PlayerFarmEvent;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.farming.FarmingUtils;
import me.CarsCupcake.SkyblockRemake.Items.farming.UpgradebleHoe;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Collections.ItemCollection;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class WheatHoes implements Listener {
    public WheatHoes(){
        AbilityLore lore =  new AbilityLore(new ArrayList<>(List.of("§7Counter: §e%counter% Wheat", " ", "%next%")));
        lore.addPlaceholder("%counter%", (player, itemStack) -> Tools.addDigits(ItemHandler.getOrDefaultPDC("counter", itemStack, PersistentDataType.INTEGER, 0)));
        lore.addPlaceholder("%next%", (player, itemStack) -> {
            int count = ItemHandler.getOrDefaultPDC("counter", itemStack, PersistentDataType.INTEGER, 0);
            if(count < 100000)
                return "§8Reach 100k Counter for +1 Rarity!";
            if(count < 10000000)
                return "§8Reach 10m Counter for +1 Rarity!";
            return "";
        });
        AbilityLore boosts = new AbilityLore(new ArrayList<>(List.of("§7Harvest §a+10% wheat §7and",
                "§7gain " + Stats.FarmingWisdom.getColor() + "+%wisdom% " + Stats.FarmingWisdom.getSymbol() + " Farming Wisdom",
                "§7while harvesting wheat.")));
        boosts.addPlaceholder("%wisdom%", (player, itemStack) -> getFarmingWisdomStatBoos(itemStack, player) + "");
        ItemManager manager = new ItemManager("Euclid's Wheat Hoe", "THEORETICAL_HOE_WHEAT_1", ItemType.Hoe, Material.STONE_HOE, ItemRarity.COMMON);
        manager.setRarityGrabber(new UpgradebleHoe(Material.WHEAT));
        manager.addAbility(null, null, null, boosts, 0, 0);
        manager.addAbility(null, null, null, lore, 0, 0);
        Items.SkyblockItems.put(manager.itemID, manager);

        AbilityLore logarithmicCounter = new AbilityLore(new ArrayList<>(List.of(
                "§7Harvest §a+16% §7wheat per",
                "§7digits on the Counter, minus 4!",
                "§a+%a% §6" + Stats.FarmingFortune.getSymbol() + " Farming Fortune §7for",
                "§7wheat."
        )));
        logarithmicCounter.addPlaceholder("%a%", (player, itemStack) -> FarmingUtils.getLogarithmicCounter(ItemHandler.getOrDefaultPDC("counter", itemStack, PersistentDataType.INTEGER, 0)) + "");

        boosts = new AbilityLore(new ArrayList<>(List.of("§7Harvest §a+25% wheat §7and",
                "§7gain " + Stats.FarmingWisdom.getColor() + "+%wisdom% " + Stats.FarmingWisdom.getSymbol() + " Farming Wisdom",
                "§7while harvesting wheat.")));
        boosts.addPlaceholder("%wisdom%", (player, itemStack) -> getFarmingWisdomStatBoos(itemStack, player) + "");

        manager = new ItemManager("Euclid's Wheat Hoe", "THEORETICAL_HOE_WHEAT_2", ItemType.Hoe, Material.IRON_HOE, ItemRarity.UNCOMMON);
        manager.setRarityGrabber(new UpgradebleHoe(Material.WHEAT));
        manager.addAbility(null, null, null, boosts, 0, 0);
        manager.addAbility(null, null, null, lore, 0, 0);
        manager.addAbility(null, null, "Logarithmic Counter", logarithmicCounter, 0, 0);
        Items.SkyblockItems.put(manager.itemID, manager);

        AbilityLore collectionAnalasys = new AbilityLore(new ArrayList<>(List.of(
                "§7Harvest §a+8% §7wheat per digits",
                "§7of your collection, minus 4!",
                "§a+%a% §6" + Stats.FarmingFortune.getSymbol() + " Farming Fortune §7for",
                "§7wheat."
        )));
        collectionAnalasys.addPlaceholder("%a%", (player, itemStack) -> ((player != null) ? FarmingUtils.getCollectionAnalysis(ItemCollection.itemCollections.get(player).get(Material.WHEAT.toString())) : "0") + "");

        boosts = new AbilityLore(new ArrayList<>(List.of("§7Harvest §a+50% wheat §7and",
                "§7gain " + Stats.FarmingWisdom.getColor() + "+%wisdom% " + Stats.FarmingWisdom.getSymbol() + " Farming Wisdom",
                "§7while harvesting wheat.")));
        boosts.addPlaceholder("%wisdom%", (player, itemStack) -> getFarmingWisdomStatBoos(itemStack, player) + "");

        manager = new ItemManager("Euclid's Wheat Hoe", "THEORETICAL_HOE_WHEAT_3", ItemType.Hoe, Material.DIAMOND_HOE, ItemRarity.RARE);
        manager.setRarityGrabber(new UpgradebleHoe(Material.WHEAT));
        manager.addAbility(null, null, null, boosts, 0, 0);
        manager.addAbility(null, null, null, lore, 0, 0);
        manager.addAbility(null, null, "Logarithmic Counter", logarithmicCounter, 0, 0);
        manager.addAbility(null, null, "Collection Analysis", collectionAnalasys, 0, 0);
        Items.SkyblockItems.put(manager.itemID, manager);
    }

    @EventHandler
    public void onFarmEvent(PlayerFarmEvent event){
        if(event.getBlock().getType() != Material.WHEAT) return;
        ItemStack item = event.getPlayer().getItemInHand();
        if(!item.hasItemMeta()) return;
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        if(manager == null) return;
        if(!manager.itemID.startsWith("THEORETICAL_HOE_WHEAT_")) return;
        int counter = ItemHandler.getOrDefaultPDC("counter", item, PersistentDataType.INTEGER, 0);
        switch (manager.itemID.charAt(manager.itemID.length() - 1)){
            case '1' -> event.setFarmingFortune(event.getFarmingFortune() + 10);
            case '2' -> event.setFarmingFortune(event.getFarmingFortune() + 25 + FarmingUtils.getLogarithmicCounter(counter));
            case '3' -> event.setFarmingFortune(event.getFarmingFortune() + 50 + FarmingUtils.getLogarithmicCounter(counter) + FarmingUtils.getCollectionAnalysis(ItemCollection.itemCollections.get(event.getPlayer()).get(Material.WHEAT + "")));
        }
        event.setFarmingWisdom(event.getFarmingWisdom() + getFarmingWisdomStatBoos(item, event.getPlayer()));
    }
    private int getFarmingWisdomStatBoos(ItemStack item, SkyblockPlayer player){
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        return switch (manager.getRarity(ItemRarity.getFromItem(item), item, player)){
            case COMMON -> 1;
            case UNCOMMON -> 2;
            case RARE -> 3;
            case EPIC -> 5;
            case LEGENDARY -> 8;
            case MYTHIC -> 12;
            default -> 0;
        };
    }
}
