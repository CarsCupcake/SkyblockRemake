package me.CarsCupcake.SkyblockRemake.AuctionHouse;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.RightClickNPC;
import me.CarsCupcake.SkyblockRemake.Settings.InfoManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import net.minecraft.world.item.ItemBanner;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.N;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AuctionHouse implements Listener {
    private static AuctionHouse main = null;

    private final CustomConfig file;
    private final NamespacedKey pointerKey;
    @Getter
    private Set<GUI> guis = new HashSet<>();

    public AuctionHouse(){
        if(main != null){
            throw new AuctionHouseException("Can not start Auction Hause!");
        }
        main = this;
        file = new CustomConfig("ah");
        pointerKey = new NamespacedKey(Main.getMain(), "pointer");

    }
    public static AuctionHouse getInstance(){
        return main;
    }
    public void shutdown(){
        for (GUI gui : guis)
            gui.closeInventory();
    }
    public CustomConfig getFile(){
        return file;
    }

    @EventHandler
    public void npcClick(RightClickNPC event){
        if(event.getNPC().getName().equals("Auction House")){
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
            openInv(player);
        }
    }
    public void openInv(SkyblockPlayer player){
        if(!InfoManager.getValue("ah", true)){
            player.sendMessage("§cAuction Hause is currently disabled");
            return;
        }


        GUI gui = new GUI(new AuctionHouseTemplate().getCompiledItems());
        gui.setCanceled(true);
        gui.inventoryClickAction(11, type -> new AuctionBrowser(player).openInventory());
        gui.inventoryClickAction(25, type -> gui.closeInventory());
        gui.inventoryClickAction(15, type -> {
            ArrayList<IAuction> playerAuction = AuctionManager.getPlayerAuctions(player);
            if(playerAuction != null && !playerAuction.isEmpty()){
                auctionList(playerAuction, player);
            }else
                new SetupOrder(player);
        });
        gui.closeAction(type -> guis.remove(gui));
        guis.add(gui);
        gui.showGUI(player);
    }
    private void auctionList(ArrayList<IAuction> auctions, SkyblockPlayer player){
        int rows = ((int)(auctions.size()/7))+3;
        InventoryBuilder builder = new InventoryBuilder(rows, "Your Auctions")
                .fill(TemplateItems.EmptySlot.getItem());
        for(int i = 1; i < rows; i++){
            builder.fill(new ItemStack(Material.AIR), i*9+1, i*9+7);
        }
        int i = 10;
        int row = 1;
        int arrayListPointer = 0;
        for(IAuction auction : auctions){
            if(row == 7){
                row = 0;
                i += 2;
            }else {
                ItemStack item = auction.craftShowItem();
                ItemMeta meta = item.getItemMeta();
                meta.getPersistentDataContainer().set(pointerKey, PersistentDataType.INTEGER, arrayListPointer);
                item.setItemMeta(meta);
                builder.setItem(item, i);
            }

            row++;
            i++;
            arrayListPointer++;
        }

        int r = rows - 1;
        builder.fill(TemplateItems.EmptySlot.getItem(),r * 9, r * 9 + 8)
                .setItem(new ItemBuilder(Material.GOLDEN_HORSE_ARMOR)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§6Create an auction")
                        .build(),r * 9 + 5);

        GUI gui = new GUI(builder
                .build());
        gui.setCanceled(true);
        gui.setGeneralAction((slot, actionType, type) -> {
            if(actionType != GUI.GUIActions.Click)
                return;

            ItemStack item = gui.getInventory().getItem(slot);
            if(item == null)
                return;
            ItemMeta meta = item.getItemMeta();
            if(meta == null)
                return;
            PersistentDataContainer data = meta.getPersistentDataContainer();
            if (data == null)
                return;

            if(!data.has(pointerKey, PersistentDataType.INTEGER))
                return;
            auctions.get(data.get(pointerKey, PersistentDataType.INTEGER)).openManager(player);
        });
        gui.inventoryClickAction(r * 9 + 5, type -> new SetupOrder(player));
        gui.closeAction(type -> guis.remove(gui));
        guis.add(gui);
        gui.showGUI(player);
    }



}
