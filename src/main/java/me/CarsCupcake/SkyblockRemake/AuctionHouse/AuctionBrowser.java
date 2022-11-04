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
    private ArrayList<Inventory> pages = new ArrayList<>();
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
        gui.setCanceled(true);
        gui.showGUI(player);


    }
    public void refresh(){
        auctions = AuctionManager.getAllAuctions(player);
    }
    public void sortByCoins(){
        sortByCoins(null);
    }
    public void sortByCoins(Comparator<? super Double> comparator){
        TreeMap<Double, IAuction> map;
        if(comparator == null){
            map = new TreeMap<>();
        }else
            map = new TreeMap<>(comparator);
        for(IAuction auction : auctions)
            map.put(auction.getCost(), auction);
        auctions.clear();

        auctions.addAll(map.values());

    }
}
