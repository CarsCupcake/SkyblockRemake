package me.CarsCupcake.SkyblockRemake.AuctionHouse;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class AuctionBrowser {
    private final SkyblockPlayer player;
    private ArrayList<IAuction> auctions = new ArrayList<>();
    private int site = 0;
    private final ArrayList<Inventory> pages = new ArrayList<>();
    public AuctionBrowser(SkyblockPlayer player){
        this.player = player;
        refresh();
        sortByCoins();
        generateNextPage();

    }
    public void generateNextPage(){
        int pointer = site * 45;
        if(site * 45 >= auctions.size())
            return;
        InventoryBuilder builder = new InventoryBuilder(6, "Auction Browser Page " + (site + 1));
        builder.fill(TemplateItems.EmptySlot.getItem(),45, 53);
        for (int i = 0; i < 45; i++){
            if(auctions.size() <= pointer)
                break;
            builder.setItem(auctions.get(pointer).craftShowItem(), i);
            pointer++;
        }
        pages.add(builder.build());


    }
    public void openInventory(){
        if(pages.isEmpty()){
            player.sendMessage("§cThe market is empty!");
            return;
        }

        openInventory(0);
    }
    public void openInventory(int page){
        if(page == pages.size() -1)
            generateNextPage();

        site = page;

        GUI gui = new GUI(pages.get(page));
        if(page != pages.size() - 1){
            gui.inventoryClickAction(53, type -> openInventory(page + 1));
        }
        gui.setGeneralAction((slot, actionType, type) -> {
            if(actionType != GUI.GUIActions.Click)
                return;
            int pointer = site * 45 + slot ;
            if(auctions.size() <= pointer)
                return;

            IAuction auction = auctions.get(pointer);
            if(auction == null)
                return;
            auction.openManager(player);
        });
        gui.setCanceled(true);
        gui.closeAction(type -> AuctionHouse.getInstance().getGuis().remove(gui));
        AuctionHouse.getInstance().getGuis().add(gui);
        gui.showGUI(player);


    }
    public void refresh(){
        auctions = new ArrayList<>(AuctionManager.getAllAuctions(player));
    }
    public void sortByCoins(){
        sortAuction((o1, o2) ->
                (int) (o1.getCost() - o2.getCost()));
    }
    public void sortAuction(Comparator<? super IAuction> comparator){
        auctions.sort(comparator);
    }
}
