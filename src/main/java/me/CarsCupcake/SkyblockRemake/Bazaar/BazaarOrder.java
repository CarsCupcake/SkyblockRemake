package me.CarsCupcake.SkyblockRemake.Bazaar;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class BazaarOrder {
    private final SkyblockPlayer player;
    private final BazaarOffer offer;
    private int amount;
    private int fillAmount;
    private final boolean isSell;
    private final double coins;
    private int stringPlace;

    public BazaarOrder(SkyblockPlayer player, int amount, int fillAmount, double coins, BazaarOffer item, boolean isSell, int stringPlace){
        this.player = player;
        this.offer = item;
        this.amount = amount;
        this.fillAmount = fillAmount;
        this.isSell = isSell;
        this.coins = coins;
        this.stringPlace = stringPlace;
    }
    public ItemStack makeItem(){
        ItemBuilder builder = (offer.getItem().isHead) ?new ItemBuilder(Material.PLAYER_HEAD)
                .setHead(offer.getItem().headTexture) :
                new ItemBuilder(offer.getItem().material);
        Bundle<Double,Bundle<UUID, Integer>> bestBundle = (isSell) ? new BazaarManager().getBestOffer(offer.getItem()) : new BazaarManager().getBestBuyOffer(offer.getItem());
        return builder
                .setName(((isSell) ? "§6§lSELL §r" : "§a§lBUY §r")  + offer.getItem().rarity.getPrefix() + offer.getItem().name)
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                .addLoreRow("§8Worth " +((bestBundle == null) ?"N/A" :Tools.addDigits(bestBundle.getFirst() )) + " coins")
                .addLoreRow(" ")
                .addLoreRow("§7Offer amount §a" + amount + "§7x")
                .addLoreRow((fillAmount > 0) ?  "§7Filled: §6" +fillAmount +"§7/" + amount + " " + ((fillAmount == amount) ? "§a§l100%" : "§8(" + ((int) ((amount/fillAmount) * 100)) + "%)") : "")
                .addLoreRow(" ")
                .addLoreRow("§7Price per unit: §6" + Tools.addDigits(coins) + " coins")
                .addLoreRow(" ")
                .addLoreRow((fillAmount != 0) ? ((isSell) ? "§eYou have §6" + Tools.addDigits((fillAmount*coins)*(1-0.0125)) + " coins §eto claim!":"§eYou have §a" + amount + " items to claim§e") : "")
                .addLoreRow((fillAmount != 0) ? " " : "")
                .addLoreRow(((fillAmount > 0) ? "§aClick to collect" : "§eClick to view options"))
                .build();
    }
    public void claim(){
        if(isSell)
            fillSellOffer();
        else
            fillBuyOrder();

        amount-= fillAmount;
        fillAmount=0;

    }
    private void fillSellOffer(){
        Main.bazaarFile.reload();
        String offersString = Main.bazaarFile.get().getString(player.getUniqueId() + "." + offer.getItem().itemID + ".sell");
        if(offersString == null)
            throw new BazaarOrderException("There is no order for the item with the id: " + offer.getItem().itemID + " at the player: " + player.getName());
        ArrayList<String> buyOrders = new ArrayList<>();
        if(offersString.contains("|"))
            buyOrders.addAll(Arrays.asList(offersString.split("\\|")));
        else
            buyOrders.add(offersString);
        String newOfferString;

         newOfferString = coins + "/" + (amount - fillAmount ) + "/0";

        if(amount-fillAmount != 0)
         buyOrders.set(stringPlace, newOfferString);
        else {
            buyOrders.remove(stringPlace);
            stringPlace = -1;
            Main.bazaarFile.get().set(player.getUniqueId() + ".orders", Main.bazaarFile.get().getInt(player.getUniqueId() + ".orders")-1);
        }
        StringBuilder buyOrdersString = new StringBuilder();
        int i = 0;
        for (String s : buyOrders){
            buyOrdersString.append(s);

            if(i  < buyOrders.size()-1)
                buyOrdersString.append("|");
            i++;
        }
        player.setCoins(player.coins + (Tools.round((fillAmount * coins) * (1-0.0125),1)));
        player.sendMessage("§aClaimed you offer for §6" + (Tools.round((fillAmount * coins) * (1-0.0125),1)) + " coins");
        Main.bazaarFile.get().set(player.getUniqueId() + "." + offer.getItem().itemID + ".sell", buyOrdersString.toString());
        Main.bazaarFile.save();
        Main.bazaarFile.reload();
    }

    private void fillBuyOrder(){
        Main.bazaarFile.reload();
        String offersString = Main.bazaarFile.get().getString(player.getUniqueId() + "." + offer.getItem().itemID + ".buy");
        if(offersString == null)
            throw new BazaarOrderException("There is no order for the item with the id: " + offer.getItem().itemID + " at the player: " + player.getName());
        ArrayList<String> buyOrders = new ArrayList<>();
        if(offersString.contains("|"))
            buyOrders.addAll(Arrays.asList(offersString.split("\\|")));
        else
            buyOrders.add(offersString);


        String newOfferString = coins + "/" + (amount - fillAmount ) + "/0";

        if(amount-fillAmount != 0)
            buyOrders.set(stringPlace, newOfferString);
        else {
            buyOrders.remove(stringPlace);
            stringPlace = -1;
            Main.bazaarFile.get().set(player.getUniqueId() + ".orders", Main.bazaarFile.get().getInt(player.getUniqueId() + ".orders")-1);

        }
        StringBuilder buyOrdersString = new StringBuilder();
        int i = 0;
        for (String s : buyOrders){
            buyOrdersString.append(s);

            if(i  < buyOrders.size()-1)
                buyOrdersString.append("|");
            i++;
        }
        ItemStack item = offer.getItem().createNewItemStack();
        item.setAmount(fillAmount);
        player.getInventory().addItem(item);
        Main.bazaarFile.get().set(player.getUniqueId() + "." + offer.getItem().itemID + ".buy", buyOrdersString.toString());
        Main.bazaarFile.save();
        Main.bazaarFile.reload();
    }
    public int getFillAmount(){
        return fillAmount;
    }
}
