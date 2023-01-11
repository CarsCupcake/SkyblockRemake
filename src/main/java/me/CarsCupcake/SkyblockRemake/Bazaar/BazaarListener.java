package me.CarsCupcake.SkyblockRemake.Bazaar;


import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Bazaar.Offers.Oddities.Recombobulator;
import me.CarsCupcake.SkyblockRemake.Bazaar.Offers.Oddities.StockOfStonk;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.RightClickNPC;
import me.CarsCupcake.SkyblockRemake.Settings.InfoManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Templates.BazaarInventoryTemplate;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignGUI;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;


public class BazaarListener implements Listener {
    private static final ArrayList<GUI> bazzarGUIs = new ArrayList<>();
    @EventHandler
    public void onNpcInteract(RightClickNPC event){
        final SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        if(event.getNPC().getName().equalsIgnoreCase("Bazaar")){
            if(!InfoManager.isBazaarEnabled()){
                player.sendMessage("§cBazzar is temporarely disabled!");
                return;
            }

            /*event.getPlayer().openInventory(new InventoryBuilder(new BazaarInventoryTemplate("Test Bazaar", Material.LIME_STAINED_GLASS_PANE, 0)).build());*/
            GUI gui = new GUI(new InventoryBuilder(new BazaarInventoryTemplate("Test Bazaar", Material.LIME_STAINED_GLASS_PANE, 0, player))
                    .setItem(new Recombobulator().getItem(), 11)
                    .setItem(new StockOfStonk().getItem(), 12)
                    .build());

             gui.inventoryClickAction(11,(type) -> {

                /*GUI offer = new GUI(new InventoryBuilder(new OfferTemplate(new Recombobulator()))

                        .build());
                offer.setCanceled(true);
                if(Tools.getItemInPlayerInventory(new Recombobulator().getItem(), player) > 0)
                 offer.inventoryClickAction(16, () -> {
                    GUI sellOffer = new GUI(OfferTemplate.getSellOfferScreen(new Recombobulator(), Tools.getItemInPlayerInventory(new Recombobulator().getItem(), player)));
                    sellOffer.setCanceled(true);
                    sellOffer.inventoryClickAction(10, new GUIAction() {
                        @Override
                        public void run() {
                            Bundle<Double, Bundle<SkyblockPlayer,Integer>> topOffer = new BazaarManager().getBestOffer(new Recombobulator().getItem());
                            new BazaarManager().addSellOffer(new Recombobulator().getItem(),Tools.getItemInPlayerInventory(new Recombobulator().getItem(), player), (topOffer == null) ? 500000000 : topOffer.getFirst() ,player);
                            Tools.removeAllItemsFromInventory(player,new Recombobulator().getItem());
                            player.sendMessage("§aSell offer was setuped for §6" + topOffer.getFirst() + " coins");
                        }
                    });
                    sellOffer.showGUI(player);
                });
                offer.showGUI(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));*/
                 manageOffer(new Recombobulator(), player);
            });
             gui.inventoryClickAction(12,(type -> manageOffer(new StockOfStonk(), player)));
            gui.setGeneralAction((slot, actionType, type) -> {
                if(actionType == GUI.GUIActions.Click)
                    if(slot == 50)
                        bazaarOrders(player);
                if(actionType == GUI.GUIActions.Close)
                    bazzarGUIs.remove(gui);

            });
            gui.setCanceled(true);
            bazzarGUIs.add(gui);


            gui.showGUI(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        }
    }
    public static void openBazzar(SkyblockPlayer player){

        if(!InfoManager.isBazaarEnabled()){
            player.sendMessage("§cBazzar is temporarely disabled!");
            return;
        }

        /*event.getPlayer().openInventory(new InventoryBuilder(new BazaarInventoryTemplate("Test Bazaar", Material.LIME_STAINED_GLASS_PANE, 0)).build());*/
        GUI gui = new GUI(new InventoryBuilder(new BazaarInventoryTemplate("Test Bazaar", Material.LIME_STAINED_GLASS_PANE, 0, player))
                .setItem(new Recombobulator().getItem(), 11)
                .setItem(new StockOfStonk().getItem(), 12)
                .build());

        gui.inventoryClickAction(11,(type) -> {

                /*GUI offer = new GUI(new InventoryBuilder(new OfferTemplate(new Recombobulator()))

                        .build());
                offer.setCanceled(true);
                if(Tools.getItemInPlayerInventory(new Recombobulator().getItem(), player) > 0)
                 offer.inventoryClickAction(16, () -> {
                    GUI sellOffer = new GUI(OfferTemplate.getSellOfferScreen(new Recombobulator(), Tools.getItemInPlayerInventory(new Recombobulator().getItem(), player)));
                    sellOffer.setCanceled(true);
                    sellOffer.inventoryClickAction(10, new GUIAction() {
                        @Override
                        public void run() {
                            Bundle<Double, Bundle<SkyblockPlayer,Integer>> topOffer = new BazaarManager().getBestOffer(new Recombobulator().getItem());
                            new BazaarManager().addSellOffer(new Recombobulator().getItem(),Tools.getItemInPlayerInventory(new Recombobulator().getItem(), player), (topOffer == null) ? 500000000 : topOffer.getFirst() ,player);
                            Tools.removeAllItemsFromInventory(player,new Recombobulator().getItem());
                            player.sendMessage("§aSell offer was setuped for §6" + topOffer.getFirst() + " coins");
                        }
                    });
                    sellOffer.showGUI(player);
                });
                offer.showGUI(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));*/
            manageOffer(new Recombobulator(), player);
        });
        gui.inventoryClickAction(12,(type -> manageOffer(new StockOfStonk(), player)));
        gui.setGeneralAction((slot, actionType, type) -> {
            if(actionType == GUI.GUIActions.Click)
                if(slot == 50)
                    bazaarOrders(player);
            if(actionType == GUI.GUIActions.Close)
                bazzarGUIs.remove(gui);

        });
        gui.setCanceled(true);
        bazzarGUIs.add(gui);


        gui.showGUI(SkyblockPlayer.getSkyblockPlayer(player));
    }
    private static void manageOffer(BazaarOffer offer, SkyblockPlayer player){



        /*event.getPlayer().openInventory(new InventoryBuilder(new BazaarInventoryTemplate("Test Bazaar", Material.LIME_STAINED_GLASS_PANE, 0)).build());*/


        GUI offerGUI = new GUI(new InventoryBuilder(new OfferTemplate(offer, player))

                .build());
        bazzarGUIs.add(offerGUI);
        offerGUI.setGeneralAction((slot, actionType, type) -> {
            if (actionType == GUI.GUIActions.Close)
                bazzarGUIs.remove(offerGUI);
        });
        offerGUI.setCanceled(true);
        if(Tools.getItemInPlayerInventory(offer.getItem(), player) > 0)
            offerGUI.inventoryClickAction(16, (type) -> {
                if(type == ClickType.RIGHT || type == ClickType.SHIFT_RIGHT){
                    new SignGUI(new SignManager(), event ->{
                        Bukkit.getScheduler().runTask(Main.getMain(), ()->{

                            int amount = 0;
                            try{
                                amount = Integer.parseInt(event.lines()[0]);
                            }catch (Exception e){

                                player.sendMessage("§cError! Invalid amount!");

                            }
                            if(amount < 1){
                                player.sendMessage("§cError! Invalid amount!");
                                offerGUI.closeInventory();
                                return;
                            }
                            try {
                                sellWithCustomAmount(amount, player, offer);
                            }catch (Exception e){
                                player.closeInventory();
                                player.sendMessage("§7(" + e.getClass().getSimpleName() + ")§c " + e.getMessage());

                            }


                        });
                    })
                            .withLines("", "^^^^^^^^^^^^^^^", "Enter amount", "to sell")
                            .open(player);
                }
                else {
                    GUI sellOffer = new GUI(OfferTemplate.getSellOfferScreen(offer, Tools.getItemInPlayerInventory(offer.getItem(), player)));
                    sellOffer.setCanceled(true);
                    sellOffer.inventoryClickAction(10, (type2) -> {
                        Bundle<Double, Bundle<UUID, Integer>> topOffer = new BazaarManager().getBestOffer(offer.getItem());
                        new BazaarManager().addSellOffer(offer.getItem(), Tools.getItemInPlayerInventory(offer.getItem(), player), (topOffer == null) ? 500000000 : topOffer.getFirst(), player);
                        Tools.removeAllItemsFromInventory(player, offer.getItem());
                        player.sendMessage("§aSell offer was setuped for §6" + String.format("%.1f", topOffer.getFirst()) + " coins §aper");
                        sellOffer.closeInventory();
                    });
                    sellOffer.inventoryClickAction(12, (type3) -> {
                        Bundle<Double, Bundle<UUID, Integer>> topOffer = new BazaarManager().getBestOffer(offer.getItem());
                        new BazaarManager().addSellOffer(offer.getItem(), Tools.getItemInPlayerInventory(offer.getItem(), player), (topOffer == null) ? 500000000 - 0.1 : topOffer.getFirst() - 0.1, player);
                        Tools.removeAllItemsFromInventory(player, offer.getItem());
                        player.sendMessage("§aSell offer was setuped for §6" + String.format("%.1f", (topOffer == null) ? 500000000 - 0.1 : topOffer.getFirst() - 0.1) + " coins §aper");
                        sellOffer.closeInventory();
                    });
                    sellOffer.setGeneralAction((slot, actionType, t) -> {
                        if (actionType == GUI.GUIActions.Close)
                            bazzarGUIs.remove(sellOffer);
                    });
                    sellOffer.showGUI(player);
                }
            });
        offerGUI.inventoryClickAction(15, (type1) -> {
            GUI preBuyScreen = new GUI(OfferTemplate.preBuy(offer));
            preBuyScreen.setCanceled(true);
            preBuyScreen.inventoryClickAction(10, (type) -> buyScreen(offer,player,offer.getItemSizes()[0]));
            preBuyScreen.inventoryClickAction(12, (type) -> buyScreen(offer,player,offer.getItemSizes()[1]));
            preBuyScreen.inventoryClickAction(14, (type) -> buyScreen(offer,player,offer.getItemSizes()[2]));
            preBuyScreen.inventoryClickAction(16, (type -> new SignGUI(new SignManager(), event -> Bukkit.getScheduler().runTask(Main.getMain(), () ->{
                try {
                    Integer i = Integer.parseInt(event.lines()[0]);

                    if(i < 1){
                        player.sendMessage("§cError! Invalid amount!");
                        offerGUI.closeInventory();
                        return;
                    }

                    buyScreen(offer,player,i);
                } catch (Exception ignored) {
                    preBuyScreen.closeInventory();
                    player.sendMessage("§cThis is not a number");
                }
            })).withLines("", "^^^^^^^^^^^^^^^", "Your Bazaar buy", "amount")
                    .open(SkyblockPlayer.getSkyblockPlayer(player))));
            preBuyScreen.setGeneralAction((slot, actionType, type) -> {
                if (actionType == GUI.GUIActions.Close)
                    bazzarGUIs.remove(preBuyScreen);
            });
            preBuyScreen.showGUI(player);
        });
        offerGUI.inventoryClickAction(10, (type) -> {
            GUI intaBuyPreScreen = new GUI(OfferTemplate.preBuy(offer));
            intaBuyPreScreen.setCanceled(true);
            intaBuyPreScreen.inventoryClickAction(10, (type2) -> intaBuyScreen(offer,player,offer.getItemSizes()[0]));
            intaBuyPreScreen.inventoryClickAction(12, (type2) -> intaBuyScreen(offer,player,offer.getItemSizes()[1]));
            intaBuyPreScreen.inventoryClickAction(14, (type2) -> intaBuyScreen(offer,player,offer.getItemSizes()[2]));
            intaBuyPreScreen.inventoryClickAction(16, type1 -> {
                new SignGUI(new SignManager(), event -> Bukkit.getScheduler().runTask(Main.getMain(), () ->{
                    try {
                        Integer i = Integer.parseInt(event.lines()[0]);

                        if(i < 1){
                            player.sendMessage("§cError! Invalid amount!");
                            offerGUI.closeInventory();
                            return;
                        }

                        intaBuyScreen(offer,player,i);
                    } catch (Exception ignored) {
                        intaBuyPreScreen.closeInventory();
                        player.sendMessage("§cThis is not a number");
                    }
                })).withLines("", "^^^^^^^^^^^^^^^", "Your Bazaar buy", "amount")
                        .open(SkyblockPlayer.getSkyblockPlayer(player));
            });
            intaBuyPreScreen.setGeneralAction((slot, actionType, t) -> {
                if (actionType == GUI.GUIActions.Close)
                    bazzarGUIs.remove(intaBuyPreScreen);
            });
            intaBuyPreScreen.showGUI(player);
        });
        offerGUI.inventoryClickAction(11, (type) -> {
            if(type.isRightClick())
                player.sendMessage("§cNot done yet");
            else
                instaSellInventory(player,offer,offerGUI);
        });
        offerGUI.showGUI(SkyblockPlayer.getSkyblockPlayer(player));


    }
    private static void buyScreen(BazaarOffer offer, SkyblockPlayer player, int amount){


        GUI buyOrder = new GUI(OfferTemplate.getBuyOrderScreen(offer,amount));
        buyOrder.setCanceled(true);
        buyOrder.inventoryClickAction(10,(type)->{


            Bundle<Double, Bundle<UUID,Integer>> topOffer = new BazaarManager().getBestBuyOffer(offer.getItem());
            double coins = (topOffer == null) ? 500000000 : topOffer.getFirst();


            if(player.coins < coins){
                buyOrder.closeInventory();
                player.sendMessage("§cYou canot afford that!");
                return;
            }
            new BazaarManager().addBuyOffer(offer.getItem(),amount, (topOffer == null) ? 500000000 : topOffer.getFirst() ,player);
            player.setCoins(player.coins-coins);

            player.sendMessage("§aBuy order was setuped for §6" +String.format("%.1f", coins)  + " coins §aper");
            buyOrder.closeInventory();


        });
        buyOrder.inventoryClickAction(12,(type)->{


            Bundle<Double, Bundle<UUID,Integer>> topOffer = new BazaarManager().getBestBuyOffer(offer.getItem());
            double coins = (topOffer == null) ? 500000000 + 0.1 : topOffer.getFirst() + 0.1;


            if(player.coins < coins){
                buyOrder.closeInventory();
                player.sendMessage("§cYou canot afford that!");
                return;
            }
            new BazaarManager().addBuyOffer(offer.getItem(),amount, coins ,player);
            player.setCoins(player.coins-coins);

            player.sendMessage("§aBuy order was setuped for §6" + String.format("%.1f", coins) + " coins §aper");
            buyOrder.closeInventory();


        });
        buyOrder.inventoryClickAction(16,(type)-> new SignGUI(new SignManager(), event -> Bukkit.getScheduler().runTask(Main.getMain(), () -> Bukkit.getScheduler().runTask(Main.getMain(),()->{ try {
            double coins;
            coins = Tools.StringToDouble(event.lines()[0]);
            if (coins <=0) {

                    player.sendMessage("§cError! Invalid amount!");
                    buyOrder.closeInventory();
                    return;

            }
            if (player.coins < coins) {
                buyOrder.closeInventory();
                player.sendMessage("§cYou canot afford that!");
                return;
            }
            new BazaarManager().addBuyOffer(offer.getItem(), amount, coins, player);
            player.setCoins(player.coins - coins);

            player.sendMessage("§aBuy order was setuped for §6" + String.format("%.1f", coins) + " coins §aper");
            buyOrder.closeInventory();
        } catch (Exception ignored) {
            buyOrder.closeInventory();
            player.sendMessage("§cThis is not a number");
        }
        }))).withLines("", "^^^^^^^^^^^^^^^", "Your Bazaar coins", "")
                .open(SkyblockPlayer.getSkyblockPlayer(player)));
        buyOrder.setGeneralAction((slot, actionType, type) -> {
            if (actionType == GUI.GUIActions.Close)
                bazzarGUIs.remove(buyOrder);
        });
        buyOrder.showGUI(player);
    }
    private static void sellWithCustomAmount(int amount, SkyblockPlayer player, BazaarOffer offer){
        if(amount > Tools.getItemInPlayerInventory(offer.getItem(), player))
            throw new BazaarOrderException("Amount is larger than items in inventory");

        GUI sellOffer = new GUI(OfferTemplate.getSellOfferScreen(offer, amount));
        sellOffer.setCanceled(true);
        sellOffer.inventoryClickAction(10, (type2) -> {
            Bundle<Double, Bundle<UUID, Integer>> topOffer = new BazaarManager().getBestOffer(offer.getItem());
            new BazaarManager().addSellOffer(offer.getItem(),amount, (topOffer == null) ? 500000000 : topOffer.getFirst(), player);
            Tools.removeItemsFromInventory(player, offer.getItem(),amount);
            player.sendMessage("§aSell offer was setuped for §6" + String.format("%.1f", topOffer.getFirst()) + " coins §aper");
            sellOffer.closeInventory();
        });
        sellOffer.inventoryClickAction(12, (type3) -> {
            Bundle<Double, Bundle<UUID, Integer>> topOffer = new BazaarManager().getBestOffer(offer.getItem());
            new BazaarManager().addSellOffer(offer.getItem(),amount, (topOffer == null) ? 500000000 - 0.1 : topOffer.getFirst() - 0.1, player);
            Tools.removeItemsFromInventory(player, offer.getItem(),amount);
            player.sendMessage("§aSell offer was setuped for §6" + String.format("%.1f", (topOffer.getFirst() - 0.1)) + " coins §aper");
            sellOffer.closeInventory();
        });
        sellOffer.setGeneralAction((slot, actionType, type) -> {
            if (actionType == GUI.GUIActions.Close)
                bazzarGUIs.remove(sellOffer);
        });
        sellOffer.showGUI(player);

    }
    private static void intaBuyScreen(BazaarOffer offer, SkyblockPlayer player, int amount){


            if(new BazaarManager().getSellPoolAmount(offer) < amount)
            {
                player.sendMessage("§cNot enouth items on the market!");
                player.closeInventory();
                return;
            }
            double rawPrice = new BazaarManager().calculateSellPrice(offer, amount);
        System.out.println(rawPrice);
            double coins = Tools.round(rawPrice * 1.0125,1);


            if(player.coins < coins){
                player.closeInventory();
                player.sendMessage("§cYou canot afford that!");
                return;
            }
            new BazaarManager().instaBuy(offer,amount);
            player.setCoins(player.coins-coins);
        System.out.println(amount + " OOO");
            if(amount < 64 && !offer.getItem().isUnstackeble()){
                ItemStack item = offer.getItem().createNewItemStack();
                item.setAmount(amount);
                player.addItem(item, false);
            }else{
                for(int i = 0;i < amount; i++){
                    ItemStack item = offer.getItem().createNewItemStack();
                    player.addItem(item, false);
                }
            }

            player.sendMessage("§6[Bazaar] §7Bought §a" + amount +"§7x §a" + offer.getItem().name + " §7for §6" + String.format("%.1f", coins) + " coins§7!");

            player.closeInventory();



    }
    private static void instaSellInventory(SkyblockPlayer player, BazaarOffer offer, GUI gui){
        BazaarManager manager = new BazaarManager();
        int maxAmount = manager.getBuyPoolAmount(offer);
        if(maxAmount == 0){
            player.sendMessage("§cThere are 0 buy orders on the bazaar for this item!");
            return;
        }
        int amount = Tools.getItemInPlayerInventory(offer.getItem(), player);
        if(maxAmount < amount)
            amount = maxAmount;
        double rawPrice = new BazaarManager().calculateBuyPrice(offer, amount);
        System.out.println(rawPrice);
        double coins = Tools.round(rawPrice * 0.9875,1);



        new BazaarManager().instaSell(offer,amount);
        player.setCoins(player.coins+coins);
        Tools.removeItemsFromInventory(player,offer.getItem(),amount);

        player.sendMessage("§6[Bazaar] §7Sold §a" + amount +"§7x §a" + offer.getItem().name + " §7for §6" + String.format("%.1f", coins) + " coins§7!");
        gui.setGeneralAction((slot, actionType, type) -> {
            if (actionType == GUI.GUIActions.Close)
                bazzarGUIs.remove(gui);
        });
        gui.closeInventory();


    }
    private static void bazaarOrders(SkyblockPlayer player){
        final Bundle<Inventory, Bundle<ArrayList<BazaarOrder>,ArrayList<BazaarOrder>>> infos = OfferTemplate.makeOrderInv(player);
        final ArrayList<BazaarOrder> sOrder = infos.getLast().getFirst();
        final ArrayList<BazaarOrder> bOrder = infos.getLast().getLast();
        GUI bazGUI = new GUI(infos.getFirst());
        bazGUI.setCanceled(true);
        bazGUI.setGeneralAction((slot, actionType,type) -> {
            if(actionType == GUI.GUIActions.Click){
                if(Tools.isInRange(9,17,slot) && sOrder.size() > slot - 10 && sOrder.get(slot-10).getFillAmount() > 0) {
                    sOrder.get(slot-10).claim();
                    bazGUI.closeInventory();
                }
                if(Tools.isInRange(18,26,slot) && bOrder.size() > slot - 19 && bOrder.get(slot-19).getFillAmount() > 0) {
                    bOrder.get(slot-19).claim();
                    bazGUI.closeInventory();
                }

            }
            if(actionType == GUI.GUIActions.Close)
                bazzarGUIs.remove(bazGUI);
        });
        bazzarGUIs.add(bazGUI);
        bazGUI.showGUI(player);
    }
    public static void shutdownBazzar(){
        for (GUI gui : bazzarGUIs){
            gui.getPlayer().sendMessage("§cBazaar got shutdowned!");
            gui.closeInventory();

        }
    }

}
