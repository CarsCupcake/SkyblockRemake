package me.CarsCupcake.SkyblockRemake.Bazaar;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryTemplate;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.ToDoubleBiFunction;

public class OfferTemplate implements InventoryTemplate {
    private Inventory inv;
    private String name = "N/A";
    private BazaarType type;

    public OfferTemplate(BazaarOffer offer, SkyblockPlayer player){
        type = offer.getType();
        name = offer.getItem().name;
        TreeMap<Double, Integer> sellOffers = new BazaarManager().getBestSellOffers(offer.getItem());
        Bundle<Double, Bundle<UUID, Integer>> bestSell = new BazaarManager().getBestOffer(offer.getItem());
        Bundle<Double, Bundle<UUID, Integer>> bestBuy = new BazaarManager().getBestBuyOffer(offer.getItem());
        System.out.println(new BazaarManager().getSellPoolAmount(offer));
        if(sellOffers == null)
            System.out.println("null :(");
        inv = new InventoryBuilder(getRows(), offer.getType() + " ➜ " + offer.getItem().name)
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem(new ItemBuilder(Material.GOLDEN_HORSE_ARMOR)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§aBuy Instantly")
                        .addLoreRow("§8" + offer.getItem().name)
                        .addLoreRow(" ")
                        .addLoreRow("§7Price per unit: §6"+ ((bestSell == null) ? "N/A" : Tools.addDigits(bestSell.getFirst())) + " coins")
                        .addLoreRow("§7Stack price: §6" + ((new BazaarManager().getSellPoolAmount(offer) < 64) ? "N/A" : new BazaarManager().calculateSellPrice(offer,64)) +" coins")
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to pick amount!")
                .build(), 10)
                .setItem(new ItemBuilder(Material.HOPPER)
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§6Sell Instantly")
                        .addLoreRow("§8" + offer.getItem().name)
                        .addLoreRow(" ")
                        .addLoreRow("§7Inventory: "+ ((Tools.getItemInPlayerInventory(offer.getItem(), player) == 0) ? "§cNone!" : "§a"+Tools.getItemInPlayerInventory(offer.getItem(), player) + " item(s)") )
                        .addLoreRow(" ")
                        .addLoreRow("§7Amount: §aN/A§7x")
                        .addLoreRow("§8Current tax: 1.1%")
                        .addLoreRow(" ")
                        .addLoreRow("§bRight-Click to pick amount!")
                        .addLoreRow("§eClick to sell inventory!")
                                .build()
                        ,11)
                .setItem((offer.getItem().isHead) ?new ItemBuilder(Material.PLAYER_HEAD)
                                .setHead(offer.getItem().headTexture)
                                .setName("§f"+offer.getItem().name)
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .addLoreRow("§7The item you have chosen.")
                                .build()
                        : new ItemBuilder(offer.getItem().material)
                        .setName("§f"+offer.getItem().name)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addLoreRow("§7The item you have chosen.")
                        .build()
                        , 13)
                .setItem(new ItemBuilder(Material.FILLED_MAP)
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .setName("§aCreate Buy Order")
                                .addLoreRow("§8" + offer.getItem().name)
                                .addLoreRow(" ")
                                .addLoreRow("§aTop Orders:")
                                .addLoreRow("§7N/A")
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to setup buy order!")
                                .build()
                        ,15)
                .setItem(new ItemBuilder(Material.MAP)
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .setName("§6Create Sell Order")
                                .addLoreRow("§8" + offer.getItem().name)
                                .addLoreRow(" ")
                                .addLoreRow("§aTop Offers:")
                                .addLoreRow("§7" +( (sellOffers == null) ? "N/A": "§8- §6" + String.format("%.1f", sellOffers.firstKey()) + " coins §7each | §a" + sellOffers.get(sellOffers.firstKey()) + "§7x"))
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to setup sell order!")
                                .build()
                        ,16)
                .build();
    }
    @Override
    public @Range(from = 1, to = 6) int getRows() {
        return 4;
    }

    @Override
    public @NotNull Inventory getCompiledItems() {
        return inv;
    }

    @Override
    public @NotNull String getName() {
        return type + " ➜ " + name;
    }
    public static Inventory getSellOfferScreen(BazaarOffer offer, int amount){
        Bundle<Double, Bundle<UUID, Integer>> bestOffer = new BazaarManager().getBestOffer(offer.getItem());
        return new InventoryBuilder(4, "At what price are you selling?")
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem((offer.getItem().isHead) ?new ItemBuilder(Material.PLAYER_HEAD)
                                .setHead(offer.getItem().headTexture)
                                .setName("§6Same as Best Offer")
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .addLoreRow("§8Sell Offer Setup")
                                .addLoreRow(" ")
                                .addLoreRow("§7Use the same prive as the lowest")
                                .addLoreRow("§7sell offer for this item.")
                                .addLoreRow(" ")
                                .addLoreRow("§7Selling: §a" + amount + "§7x")
                                .addLoreRow("§7Unit price: §6" + ((bestOffer == null) ? "500,000,000" : Tools.addDigits(bestOffer.getFirst())) + " coins")
                                .addLoreRow(" ")
                                .addLoreRow("§7Total: §6" + ((bestOffer == null) ? Tools.addDigits(Tools.round(500000000*amount*(1-0.0125), 1)) : Tools.addDigits(Tools.round(bestOffer.getFirst()*amount*(1-0.0125), 1))) + " coins")
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to proceed!")

                                .build()
                                : new ItemBuilder(offer.getItem().material)
                                .setName("§6Same as Best Offer")
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .addLoreRow("§8Sell Offer Setup")
                                .addLoreRow(" ")
                                .addLoreRow("§7Use the same prive as the lowest")
                                .addLoreRow("§7sell offer for this item.")
                                .addLoreRow(" ")
                                .addLoreRow("§7Selling: §a" + amount + "§7x")
                                .addLoreRow("§7Unit price: §6" + ((bestOffer == null) ? "500,000,000" : Tools.addDigits(bestOffer.getFirst())) + " coins")
                                .addLoreRow(" ")
                                .addLoreRow("§7Total: §6" + ((bestOffer == null) ? Tools.addDigits(Tools.round(500000000*amount*(1-0.0125), 1)) : Tools.addDigits(Tools.round(bestOffer.getFirst()*amount*(1-0.0125), 1))) + " coins")
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to proceed!")
                                .build()
                        , 10)
                .setItem(new ItemBuilder(Material.GOLD_NUGGET)
                        .setName("§6Best Offer -0.1")
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addLoreRow("§8Sell Offer Setup")
                        .addLoreRow(" ")
                        .addLoreRow("§7Use the same prive as the lowest")
                        .addLoreRow("§7sell offer for this item.")
                        .addLoreRow(" ")
                        .addLoreRow("§7Selling: §a" + amount + "§7x")
                        .addLoreRow("§7Unit price: §6" + ((bestOffer == null) ? (500000000-0.1) : Tools.addDigits(bestOffer.getFirst() - 0.1)) + " coins")
                        .addLoreRow(" ")
                        .addLoreRow("§7Total: §6" + ((bestOffer == null) ? Tools.addDigits(Tools.round((500000000-0.1)*amount*(1-0.0125), 1)) : Tools.addDigits(Tools.round((bestOffer.getFirst()-0.1)*amount*(1-0.0125), 1))) + " coins")
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to proceed!")
                        .build(), 12)
                .build();
    }
    public static Inventory preBuy(BazaarOffer offer){
        ItemBuilder preItemBuild = (offer.getItem().isHead) ?new ItemBuilder(Material.PLAYER_HEAD)
                .setHead(offer.getItem().headTexture) :
                new ItemBuilder(offer.getItem().material);
        String firstName = (offer.getItemSizes()[0] == 64) ? "§aBuy a stack" : "§aBuy only §e" + offer.getItemSizes()[0] + "§a!";
        String secondName = (offer.getItemSizes()[1] == 160) ? "§aBuy a big stack" : "§aBuy §e" + offer.getItemSizes()[1]+ "§a!";
        String thirdName = (offer.getItemSizes()[2] == 1024) ? "§aBuy a thousend" : "§aBuy §e" + offer.getItemSizes()[2]+ "§a!";
        return new InventoryBuilder(4,"How many do you want?")
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem(preItemBuild
                        .setAmount(64)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName(firstName)
                        .addLoreRow("§8Buy Order Setup")
                        .addLoreRow(" ")

                        .addLoreRow("§7Amount: §a" +offer.getItemSizes()[0] + "§7x")


                        .addLoreRow(" ")
                        .addLoreRow("§eClick to proceed!")
                        .build(),10)
                .setItem(new ItemBuilder(Material.CHEST)
                        .setAmount(2)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName(secondName)
                        .addLoreRow("§8Buy Order Setup")
                        .addLoreRow(" ")
                        .addLoreRow("§7Amount: §a" +offer.getItemSizes()[1] + "§7x")
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to proceed!")
                        .build(),12)
                .setItem(new ItemBuilder(Material.CHEST)
                        .setAmount(16)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName(thirdName)
                        .addLoreRow("§8Buy Order Setup")
                        .addLoreRow(" ")
                        .addLoreRow("§7Amount: §a" +offer.getItemSizes()[2] + "§7x")
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to proceed!")
                        .build(),14)
                .setItem(new ItemBuilder(Material.OAK_SIGN)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§cNot Avaideble at the moment!")
                        .build(),16)
                .build();
    }

    public static Inventory getBuyOrderScreen(BazaarOffer offer, int amount){
        Bundle<Double, Bundle<UUID, Integer>> bestOffer = new BazaarManager().getBestBuyOffer(offer.getItem());
        return new InventoryBuilder(4, "How much do you want to pay?")
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem((offer.getItem().isHead) ?new ItemBuilder(Material.PLAYER_HEAD)
                                .setHead(offer.getItem().headTexture)
                                .setName("§6Same as Top Order")
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .addLoreRow("§8Buy Order Setup")
                                .addLoreRow(" ")
                                .addLoreRow("§7Use the same price as the highest")
                                .addLoreRow("§7buy order for this item.")
                                .addLoreRow(" ")
                                .addLoreRow("§7Selling: §a" + amount + "§7x")
                                .addLoreRow("§7Unit price: §6" + ((bestOffer == null) ? "500,000,000" : Tools.addDigits(bestOffer.getFirst())) + " coins")
                                .addLoreRow(" ")
                                .addLoreRow("§7Total: §6" + ((bestOffer == null) ? Tools.addDigits(Tools.round(500000000*amount*(1+0.0125), 1)) : Tools.addDigits(Tools.round(bestOffer.getFirst()*amount*(1+0.0125), 1))) + " coins")
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to proceed!")

                                .build()
                                : new ItemBuilder(offer.getItem().material)
                                .setName("§6Same as Best Offer")
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .addLoreRow("§8Sell Offer Setup")
                                .addLoreRow(" ")
                                .addLoreRow("§7Use the same price as the lowest")
                                .addLoreRow("§7buy order for this item.")
                                .addLoreRow(" ")
                                .addLoreRow("§7Selling: §a" + amount + "§7x")
                                .addLoreRow("§7Unit price: §6" + ((bestOffer == null) ? "500,000,000" : Tools.addDigits(bestOffer.getFirst())) + " coins")
                                .addLoreRow(" ")
                                .addLoreRow("§7Total: §6" + ((bestOffer == null) ? Tools.addDigits(Tools.round(500000000*amount*(1+0.0125), 1)) : Tools.addDigits(Tools.round(bestOffer.getFirst()*amount*(1+0.0125), 1))) + " coins")
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to proceed!")
                                .build()
                        , 10)
                .setItem(new ItemBuilder(Material.GOLD_NUGGET)
                        .setName("§6Best Offer +0.1")
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addLoreRow("§8Sell Offer Setup")
                        .addLoreRow(" ")
                        .addLoreRow("§7Beat the price of the top order")
                        .addLoreRow("§7so yours is filled first.")
                        .addLoreRow(" ")
                        .addLoreRow("§7Selling: §a" + amount + "§7x")
                        .addLoreRow("§7Unit price: §6" + ((bestOffer == null) ? (500000000+0.1) : Tools.addDigits(bestOffer.getFirst() + 0.1)) + " coins")
                        .addLoreRow(" ")
                        .addLoreRow("§7Total: §6" + ((bestOffer == null) ? Tools.addDigits(Tools.round((500000000+0.1)*amount*(1+0.0125), 1)) : Tools.addDigits(Tools.round((bestOffer.getFirst()-0.1)*amount*(1+0.0125), 1))) + " coins")
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to proceed!")
                        .build(), 12)
                .setItem(new ItemBuilder(Material.OAK_SIGN)
                        .setName("§6Coins amount")
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .build(), 16)
                .build();

    }
    public static Bundle<Inventory, Bundle<ArrayList<BazaarOrder>,ArrayList<BazaarOrder>>> makeOrderInv( SkyblockPlayer player){
        Inventory inv = new InventoryBuilder(4, "Your Bazaar Orders")
                .fill(TemplateItems.EmptySlot.getItem())
                .fill(new ItemStack(Material.AIR), 10, 25)
                .setItem(TemplateItems.EmptySlot.getItem(), 18)
                .build();
        ArrayList<BazaarOrder> orders = new BazaarManager().getPlayerSells( player);
        for (int i = 10; i < 17; i++){
            int place = i-10;
            if(place >= orders.size())
                break;
            inv.setItem(i, orders.get(place).makeItem());
        }
        ArrayList<BazaarOrder> borders = new BazaarManager().getPlayerBuys(player);
        for (int i = 19; i < 26; i++){
            int place = i-19;
            if(place >= borders.size())
                break;
            inv.setItem(i, borders.get(place).makeItem());
        }

        return new Bundle<>(inv,new Bundle<>(orders,borders));
    }

}
