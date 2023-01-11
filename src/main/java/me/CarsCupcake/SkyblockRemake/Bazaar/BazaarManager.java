package me.CarsCupcake.SkyblockRemake.Bazaar;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Bazaar.Offers.Oddities.Recombobulator;
import me.CarsCupcake.SkyblockRemake.Bazaar.Offers.Oddities.StockOfStonk;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class BazaarManager {
    private static HashMap<String, BazaarOffer> offers = new HashMap<>();
    public static void init(){
        offers.put(new Recombobulator().getItem().itemID, new Recombobulator());
        offers.put("STOCK_OF_STONKS", new StockOfStonk());
    }
    public void addSellOffer(ItemManager manager, int amount,double coins, SkyblockPlayer player){
         int id = 0;
        Set<String> offers = (Main.bazaarFile.get().getConfigurationSection(manager.itemID  + ".sell") != null) ?
                Main.bazaarFile.get().getConfigurationSection(manager.itemID  + ".sell").getKeys(false) :
                new TreeSet<>();

        while (offers.contains(player.getUniqueId() + "/" + amount + "/" + toFileString(coins + "") + "/" + id))
            id++;
        Main.bazaarFile.get().set(manager.itemID + ".sell."+ player.getUniqueId() + "/" + amount + "/" + toFileString(coins + "") + "/" + id, 0);
        String str = Main.bazaarFile.get().getString(player.getUniqueId() + "." + manager.itemID);
        int dataAmount = amount;
        if(str != null){
            String[] parts = str.split("/");
           if(parts[0].equals(coins +""))
               dataAmount+=Integer.parseInt(parts[1]);
        }
        //
        if(Main.bazaarFile.get().get(player.getUniqueId() + "." + manager.itemID + ".sell") == null ||Main.bazaarFile.get().get(player.getUniqueId() + "." + manager.itemID + ".sell").equals(""))
            Main.bazaarFile.get().set(player.getUniqueId() + "." + manager.itemID + ".sell", coins + "/" + dataAmount + "/" + 0);
        else
            Main.bazaarFile.get().set(player.getUniqueId() + "." + manager.itemID + ".sell",Main.bazaarFile.get().get(player.getUniqueId() + "." + manager.itemID + ".sell")  +"|" +coins + "/" + dataAmount + "/" + 0);
        int orders = Main.bazaarFile.get().getInt(player.getUniqueId() + ".orders");
        Main.bazaarFile.get().set(player.getUniqueId() + ".orders", orders +1);
        Main.bazaarFile.save();
        Main.bazaarFile.reload();
    }
    //Coins -> Items

    /**
     *
     * @param manager is the item manager for the item
     * @return returns the coins with the avaideble items
     */
    public TreeMap<Double, HashMap<UUID, Integer>> getSellOffers(ItemManager manager){
        if(Main.bazaarFile.get().getConfigurationSection(manager.itemID + ".sell") == null)
            return null;
        Set<String> offers = Main.bazaarFile.get().getConfigurationSection(manager.itemID + ".sell").getKeys(false);
        if(offers == null || offers.isEmpty())
            return null;
        Iterator<String> iterator = offers.iterator();
        TreeMap<Double, HashMap<UUID, Integer>> offer = new TreeMap<>();
        while (iterator.hasNext()){
            String id = iterator.next();
            String[] segments = id.split("/");
            UUID uuid = UUID.fromString(segments[0]);
            int amount = Integer.parseInt(segments[1]);
            double cost = Double.parseDouble(fromFileString(segments[2]));
            if(!offer.containsKey(cost)) {
                HashMap<UUID, Integer> data = new HashMap<>();
                data.put(uuid, amount);
                offer.put(cost, data);
            }else {
                HashMap<UUID, Integer> data = offer.get(cost);
                if(data.containsKey(uuid))
                    data.replace(uuid, data.get(uuid) + amount);
                else
                    data.put(uuid,amount);
                offer.replace(cost, data);
            }

        }


        return offer;
    }
    public Bundle<Double, Bundle<UUID, Integer>> getBestOffer(ItemManager manager){
        TreeMap<Double, HashMap<UUID, Integer>> offers = getSellOffers(manager);
        if(offers == null)
            return null;
        double coins = offers.firstKey();
        UUID player = offers.get(coins).keySet().stream().iterator().next();

        return new Bundle<>(coins, new Bundle<>(player, offers.get(coins).get(player)));
    }

    public TreeMap<Double, Integer> getBestSellOffers(ItemManager manager){
        TreeMap<Double, HashMap<UUID, Integer>> base = getSellOffers(manager);
        if(base == null) {
            System.out.println("the base is null");
            return null;
        }
        TreeMap<Double, Integer> amounts = new TreeMap<>();
        base.forEach((coins, has)->has.forEach((player, integer) -> {
            if (amounts.containsKey(coins))
                amounts.replace(coins, amounts.get(coins) + integer);
            else
                amounts.put(coins, integer);
        }));
        return amounts;
    }

    public int fillSellOffer(@NotNull BazaarOffer offer, @NotNull UUID player, double prize, int amount){
        double oldAmount = amount;
        StringBuilder buyOrdersString = new StringBuilder(Main.bazaarFile.get().getString(player + "." +
                offer.getItem().itemID + ".sell"));
        if(buyOrdersString == null)
            throw new BazaarOrderException("There is no order for the item with the id: " +
                    offer.getItem().itemID + " at the player: " + player);
        ArrayList<String> buyOrders = new ArrayList<>();
        if(buyOrdersString.toString().contains("|"))
            buyOrders.addAll(Arrays.asList(buyOrdersString.toString().split("\\|")));
        else
            buyOrders.add(buyOrdersString.toString());
        ArrayList<String> temp = new ArrayList(buyOrders);
        int i = 0;
        ArrayList<String> qualified = new ArrayList<>();
        for (String s : temp){
            System.out.println(s);
            Double coins = Double.parseDouble(s.split("/")[0]);
            if(coins.equals(prize)) {
                buyOrders.remove(i);
                i--;
                qualified.add(s);
            }
            i++;
        }
        if(qualified.isEmpty())
            throw new BazaarOrderException("There is no order for the item with the id: " + offer.getItem().itemID + " at the player: "
                    + player + " for the price: " + String.format("%.1f",prize));
        temp = (ArrayList<String>) qualified.clone();
        i = 0;
        for (String s : temp){
            String[] parts = s.split("/");
            int max = Integer.parseInt(parts[1]);
            int curr = Integer.parseInt(parts[2]);
            if(max > curr){
                if(curr + amount > max){
                    amount-= max-curr;
                    curr = max;
                    s = prize + "/" + max + "/" + curr;
                    qualified.set(i, s);
                }else {
                    curr += amount;
                    amount = 0;
                    s = prize + "/" + max + "/" + curr;
                    qualified.set(i, s);
                    break;
                }

            }
            i++;

        }
        buyOrdersString = new StringBuilder();
        for(String s : buyOrders)
            buyOrdersString.append(s).append("|");
        i = 0;
        for (String s : qualified){
            buyOrdersString.append(s);

            if(i  < qualified.size()-1)
                buyOrdersString.append("|");
            i++;
        }

        double amountRemoved = oldAmount - amount;
        Set<String> offers = Main.bazaarFile.get().getConfigurationSection(offer.getItem().itemID +  ".sell" ).getKeys(false);
        ArrayList<String> newOffers = new ArrayList<>();
        for(String str : offers){
            if(!str.startsWith(player + "/")) {
                newOffers.add(str);
                continue;
            }
            String oldString = str;
            str = fromFileString(str);
            String[] components = str.split("/");
            int am = Integer.parseInt(components[1]);
            Double c = Double.parseDouble(components[2]);
            if(!c.equals(prize)){
                newOffers.add(oldString);
                continue;
            }
            if(am > amountRemoved){
                am -= amountRemoved;
                amountRemoved = 0;

            }else {
                amountRemoved-= am;
                am = 0;
            }
            offers.remove(oldString);
            if(am == 0)
                continue;
            str = player + "/" + am + "/" + toFileString(c+"") + "/" + components[3];
            newOffers.add(str);
            if(amountRemoved == 0)
                break;


        }
        Main.bazaarFile.get().set(offer.getItem().itemID + ".sell", null);
        for(String str : newOffers)
            Main.bazaarFile.get().set(offer.getItem().itemID + ".sell." + str,0);
        Main.bazaarFile.get().set(player + "." + offer.getItem().itemID + ".sell", buyOrdersString.toString());
        Main.bazaarFile.save();
        Main.bazaarFile.reload();
        return amount;

    }

    public double calculateSellPrice(BazaarOffer offer, int amount){
        TreeMap<Double, Integer> offers = getBestSellOffers(offer.getItem());
        double total = 0;
        for(Double price : offers.keySet()){
            int itemAmount = offers.get(price);
            if(itemAmount > amount){
                int restAmount = amount;
                total+=(restAmount*price);
                amount = 0;
                break;
            }else {
                total += (itemAmount*price);
                amount-= itemAmount;
            }
        }
        if(amount != 0)
            throw new BazaarOrderException("There was an error while fetching sell price amounts for the item: " + offer.getItem().itemID);


        return total;
    }

    public int getSellPoolAmount(BazaarOffer offer){
        int amount = 0;
        TreeMap<Double, HashMap<UUID, Integer>> offers = getSellOffers(offer.getItem());
        if(offers == null)
            return 0;
        for(Double d : offers.keySet()){
            for(UUID player : offers.get(d).keySet())
                amount+=offers.get(d).get(player);
        }
        return amount;
    }

    public void instaBuy(BazaarOffer offer, int amount){
        TreeMap<Double, HashMap<UUID,Integer>> offers = getSellOffers(offer.getItem());
        for(Double d : offers.keySet()){
            for (UUID player : offers.get(d).keySet()) {
                amount = fillSellOffer(offer, player, d, amount);
                if(amount == 0)
                    break;
            }
            if(amount == 0)
                break;
        }

    }

    public ArrayList<BazaarOrder> getPlayerSells(SkyblockPlayer player){
        ArrayList<BazaarOrder> orders = new ArrayList<>();
        Main.bazaarFile.reload();
        ConfigurationSection section = Main.bazaarFile.get().getConfigurationSection(player.getUniqueId().toString());
        if(section != null)
        for(String str : section.getKeys(false))
        {
            if(str.equals("orders"))
                continue;
            System.out.println(str);
            String offersString = Main.bazaarFile.get().getString(player.getUniqueId() + "." + str + ".sell");
            if (offersString == null)
                continue;
            ArrayList<String> buyOrders = new ArrayList<>();
            if (offersString.contains("|"))
                buyOrders.addAll(Arrays.asList(offersString.split("\\|")));
            else
                buyOrders.add(offersString);
            int i = 0;
            for (String s : buyOrders) {
                String[] parts = s.split("/");
                if(parts.length == 3)
                 orders.add(new BazaarOrder(player, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Double.parseDouble(parts[0]), offers.get(str), true, i));
                i++;
            }
        }


        return orders;
    }





    public void addBuyOffer(ItemManager manager, int amount,double coins, SkyblockPlayer player){
        int id = 0;
        Set<String> offers =  (Main.bazaarFile.get().getConfigurationSection(manager.itemID  + ".buy") != null) ?
                Main.bazaarFile.get().getConfigurationSection(manager.itemID  + ".buy").getKeys(false) :
                new TreeSet<>();

        while (offers.contains(player.getUniqueId() + "/" + amount + "/" + coins + "/" + id))
            id++;
        Main.bazaarFile.get().set(manager.itemID + ".buy."+ player.getUniqueId() + "/" + amount + "/" + toFileString(coins + "") + "/" + id, "buy");
        String str = Main.bazaarFile.get().getString(player.getUniqueId() + "." + manager.itemID);
        int dataAmount = amount;
        if(str != null){
            String[] parts = str.split("/");
            if(parts[0].equals(coins +""))
                dataAmount+=Integer.parseInt(parts[1]);
        }
        //Syntax: <cost>(per item)/<wanted amount>/<filled state>
        System.out.println("//"+Main.bazaarFile.get().get(player.getUniqueId() + "." + manager.itemID + ".buy") + "//");

        if(Main.bazaarFile.get().get(player.getUniqueId() + "." + manager.itemID + ".buy") == null || Main.bazaarFile.get().getString(player.getUniqueId() + "." + manager.itemID + ".buy").isEmpty()
                ||Main.bazaarFile.get().getString(player.getUniqueId() + "." + manager.itemID + ".buy") == null
                ||Main.bazaarFile.get().getString(player.getUniqueId() + "." + manager.itemID + ".buy").equals(""))
            Main.bazaarFile.get().set(player.getUniqueId() + "." + manager.itemID + ".buy", coins + "/" + dataAmount + "/" + 0);
        else
            Main.bazaarFile.get().set(player.getUniqueId() + "." + manager.itemID + ".buy",Main.bazaarFile.get().get(player.getUniqueId() + "." + manager.itemID + ".buy")  +"|" +coins + "/" + dataAmount + "/" + 0);
        int orders = Main.bazaarFile.get().getInt(player.getUniqueId() + ".orders");
        Main.bazaarFile.get().set(player.getUniqueId() + ".orders", orders + 1);
        Main.bazaarFile.save();
        Main.bazaarFile.reload();
    }
    //Coins -> Items

    /**
     *
     * @param manager is the item manager for the item
     * @return returns the coins with the avaideble items
     */
    public TreeMap<Double, HashMap<UUID, Integer>> getBuyOffers(ItemManager manager){
        if(Main.bazaarFile.get().getConfigurationSection(manager.itemID + ".buy") == null)
            return null;
        Set<String> offers = Main.bazaarFile.get().getConfigurationSection(manager.itemID + ".buy").getKeys(false);
        if(offers == null || offers.isEmpty())
            return null;
        Iterator<String> iterator = offers.iterator();
        TreeMap<Double, HashMap<UUID, Integer>> offer = new TreeMap<>(Collections.reverseOrder());
        while (iterator.hasNext()){
            String id = iterator.next();
            String[] segments = id.split("/");
            UUID player = UUID.fromString(segments[0]);
            int amount = Integer.parseInt(segments[1]);
            double cost = Double.parseDouble(fromFileString(segments[2]));
            if(!offer.containsKey(cost)) {
                HashMap<UUID, Integer> data = new HashMap<>();
                data.put(player, amount);
                offer.put(cost, data);
            }else {
                HashMap<UUID, Integer> data = offer.get(cost);
                if(data.containsKey(player))
                    data.replace(player, data.get(player) + amount);
                else
                    data.put(player,amount);
                offer.replace(cost, data);
            }

        }


        return offer;
    }
    public Bundle<Double, Bundle<UUID, Integer>> getBestBuyOffer(ItemManager manager){
        TreeMap<Double, HashMap<UUID, Integer>> offers = getBuyOffers(manager);
        if(offers == null)
            return null;
        double coins = offers.firstKey();
        UUID player = offers.get(coins).keySet().stream().iterator().next();

        return new Bundle<>(coins, new Bundle<>(player, offers.get(coins).get(player)));
    }

    public TreeMap<Double, Integer> getBestBuyOffers(ItemManager manager){
        TreeMap<Double, HashMap<UUID, Integer>> base = getBuyOffers(manager);
        if(base == null) {
            System.out.println("the base is null");
            return null;
        }
        TreeMap<Double, Integer> amounts = new TreeMap<>(Collections.reverseOrder());
        base.forEach((coins, has)->has.forEach((player, integer) -> {
            if (amounts.containsKey(coins))
                amounts.replace(coins, amounts.get(coins) + integer);
            else
                amounts.put(coins, integer);
        }));
        return amounts;
    }


    public int fillBuyOffer(@NotNull BazaarOffer offer, @NotNull UUID player, double prize, int amount){
        double oldAmount = amount;
        StringBuilder buyOrdersString = new StringBuilder(Main.bazaarFile.get().getString(player + "." + offer.getItem().itemID + ".buy"));
        if(buyOrdersString == null)
            throw new BazaarOrderException("There is no order for the item with the id: " + offer.getItem().itemID +
                    " at the player: " + player);
        ArrayList<String> buyOrders = new ArrayList<>();
        if(buyOrdersString.toString().contains("|"))
            buyOrders.addAll(Arrays.asList(buyOrdersString.toString().split("\\|")));
        else
            buyOrders.add(buyOrdersString.toString());
        ArrayList<String> temp = (ArrayList<String>) buyOrders.clone();
        int i = 0;
        ArrayList<String> qualified = new ArrayList<>();
        for (String s : temp){
            System.out.println(s);
            Double coins = Double.parseDouble(s.split("/")[0]);
            if(coins.equals(prize)) {
                buyOrders.remove(i);
                i--;
                qualified.add(s);
            }
            i++;
        }
        if(qualified.isEmpty())
            throw new BazaarOrderException("There is no order for the item with the id: " + offer.getItem().itemID +
                    " at the player: " + player + " for the price: " + String.format("%.1f",prize));
        temp = (ArrayList<String>) qualified.clone();
        i = 0;
        for (String s : temp){
            String[] parts = s.split("/");
            int max = Integer.parseInt(parts[1]);
            int curr = Integer.parseInt(parts[2]);
            if(max > curr){
                if(curr + amount > max){
                    amount-= max-curr;
                    curr = max;
                    s = prize + "/" + max + "/" + curr;
                    qualified.set(i, s);
                }else {
                    curr += amount;
                    amount = 0;
                    s = prize + "/" + max + "/" + curr;
                    qualified.set(i, s);
                    break;
                }

            }
            i++;

        }
        buyOrdersString = new StringBuilder();
        for(String s : buyOrders)
            buyOrdersString.append(s).append("|");
        i = 0;
        for (String s : qualified){
            buyOrdersString.append(s);

            if(i  < qualified.size()-1)
                buyOrdersString.append("|");
            i++;
        }

        double amountRemoved = oldAmount - amount;
        Set<String> offers = Main.bazaarFile.get().getConfigurationSection(offer.getItem().itemID +  ".buy" ).getKeys(false);
        ArrayList<String> newOffers = new ArrayList<>();
        for(String str : offers){
            if(!str.startsWith(player + "/")) {
                newOffers.add(str);
                continue;
            }
            String oldString = str;
            str = fromFileString(str);
            String[] components = str.split("/");
            int am = Integer.parseInt(components[1]);
            Double c = Double.parseDouble(components[2]);
            if(!c.equals(prize)){
                newOffers.add(oldString);
                continue;
            }
            if(am > amountRemoved){
                am -= amountRemoved;
                amountRemoved = 0;

            }else {
                amountRemoved-= am;
                am = 0;
            }

            if(am == 0)
                continue;
            str = player + "/" + am + "/" + toFileString(c+"") + "/" + components[3];
            newOffers.add(str);
            if(amountRemoved == 0)
                break;


        }
        Main.bazaarFile.get().set(offer.getItem().itemID + ".buy", null);
        for(String str : newOffers)
            Main.bazaarFile.get().set(offer.getItem().itemID + ".buy." + str,0);
        Main.bazaarFile.get().set(player + "." + offer.getItem().itemID + ".buy", buyOrdersString.toString());
        Main.bazaarFile.save();
        Main.bazaarFile.reload();
        return amount;

    }

    public double calculateBuyPrice(BazaarOffer offer, int amount){
        TreeMap<Double, Integer> offers = getBestBuyOffers(offer.getItem());
        double total = 0;
        for(Double price : offers.keySet()){
            int itemAmount = offers.get(price);
            if(itemAmount > amount){
                int restAmount = amount;
                total+=(restAmount*price);
                amount = 0;
                break;
            }else {
                total += (itemAmount*price);
                amount-= itemAmount;
            }
        }
        if(amount != 0)
            throw new BazaarOrderException("There was an error while fetching sell price amounts for the item: " + offer.getItem().itemID);


        return total;
    }

    public int getBuyPoolAmount(BazaarOffer offer){
        int amount = 0;
        TreeMap<Double, HashMap<UUID, Integer>> offers = getBuyOffers(offer.getItem());
        if(offers == null)
            return 0;

        for(Double d : offers.keySet()){
            for(UUID player : offers.get(d).keySet())
                amount+=offers.get(d).get(player);
        }
        return amount;
    }

    public void instaSell(BazaarOffer offer, int amount){
        TreeMap<Double, HashMap<UUID,Integer>> offers = getBuyOffers(offer.getItem());
        for(Double d : offers.keySet()){
            for (UUID player : offers.get(d).keySet()) {
                amount = fillBuyOffer(offer, player, d, amount);
                if(amount == 0)
                    break;
            }
            if(amount == 0)
                break;
        }

    }

    public ArrayList<BazaarOrder> getPlayerBuys(SkyblockPlayer player){
        ArrayList<BazaarOrder> orders = new ArrayList<>();
        Main.bazaarFile.reload();
        ConfigurationSection section = Main.bazaarFile.get().getConfigurationSection(player.getUniqueId().toString());
        if(section != null)
            for(String str : section.getKeys(false))
            {
                if(str.equals("orders"))
                    continue;
                System.out.println(str);
                String offersString = Main.bazaarFile.get().getString(player.getUniqueId() + "." + str + ".buy");
                if (offersString == null)
                    continue;
                ArrayList<String> buyOrders = new ArrayList<>();
                if (offersString.contains("|"))
                    buyOrders.addAll(Arrays.asList(offersString.split("\\|")));
                else
                    buyOrders.add(offersString);
                int i = 0;
                for (String s : buyOrders) {
                    String[] parts = s.split("/");
                    if(parts.length == 3)
                    orders.add(new BazaarOrder(player, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Double.parseDouble(parts[0]), offers.get(str), false, i));
                    i++;
                }
            }


        return orders;
    }

    /*
    Bazaar File structure
    -Item ID
     -<PlayerUUID>/<Amount>/<Coins>/<id>

     */
    private String toFileString(String str){
        return str.replace('.','\'');
    }
    private String fromFileString(String str){
        return str.replace('\'','.');
    }
}
