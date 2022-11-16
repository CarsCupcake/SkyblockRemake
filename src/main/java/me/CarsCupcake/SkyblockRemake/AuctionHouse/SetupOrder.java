package me.CarsCupcake.SkyblockRemake.AuctionHouse;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignGUI;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Calendar;
import java.util.Date;

public class SetupOrder {
    private final SkyblockPlayer player;
    private AuctionType type = AuctionType.Bin;
    private double coins = 500;
    private int minutes = 60*6;
    private ItemStack putInItem;
    private GUI gui;

    public SetupOrder(SkyblockPlayer player){
        this.player = player;
        openInv();
    }
    private void openInv(){
        GUI gui = new GUI(new InventoryBuilder(6, "Create BIN Auction")
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem(Items.EmptyItem.getItem(), 13)
                .setItem(Items.DenyBin.getItem(), 29)
                .setItem(Items.setSellPrice(coins), 31)
                .setItem(Items.getDuration(minutes), 33)
                .build());
        gui.setGeneralAction((slot, actionType, type1) -> {
            if(actionType == GUI.GUIActions.PlayerClick){
                if(putInItem != null){
                    player.sendMessage("§cThere is already something on the auction!");
                    return;
                }
                putInItem = player.getInventory().getItem(slot);
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                updateInventory();
            }
        });
        gui.inventoryClickAction(13, type1 -> {
            if(putInItem != null){
                player.getInventory().addItem(putInItem);
                putInItem = null;
                updateInventory();
            }
        });
        gui.inventoryClickAction(29, type1 -> {
            if(putInItem == null)
                return;

            if(type == AuctionType.Bin){
                int cost = (minutes/6);
                if(cost < 20)
                    cost = 20;

                double fee = cost + Tools.round(coins*0.01,1);

                if(fee > player.coins){
                    player.sendMessage("§cYou canot afort that!");
                    return;
                }
                player.setCoins(player.coins - fee);

                gui.closeInventory();
                try{
                    Date d = new Date();
                    Calendar c = Calendar.getInstance();
                    c.setTime(d);
                    c.add(Calendar.MINUTE, minutes);
                    AuctionBin bin = new AuctionBin(player, putInItem, c.getTime(), coins);
                    AuctionManager.setupBin(bin);
                }catch (Exception e){
                    e.printStackTrace();
                    player.sendMessage("§cThere was an error while setting up the auction! §7(" + e.getClass().getSimpleName() + ")");
                    player.sendMessage("§7Checking escrow for recent transaction...\n§eEscrow refunded §6" + Items.getDoubleToSring(fee) + " coins§e!");
                    player.setCoins(player.coins + fee);

                }


            }
        });
        gui.inventoryClickAction(31, type1 -> {
            SignManager manager = new SignManager();
            new SignGUI(manager, event -> {
                Bukkit.getScheduler().runTask(Main.getMain(), () -> {
                    double newCost = Tools.StringToDouble(event.lines()[0]);
                    if (newCost < 0) {
                        player.sendMessage("§cNot a valid number!");
                        return;
                    }
                    coins = newCost;
                    updateInventory();
                    this.gui.showGUI(player);
                });


            }
            ).withLines("", "^^^^^^^^^^^^^^^", "Your auction", "starting bid").open(player);


        });
        gui.inventoryClickAction(33, type1 -> openTimeManager());
        gui.setCanceled(true);
        this.gui = gui;
        gui.showGUI(player);
    }
    private void openTimeManager(){
        GUI g = new GUI(
                new InventoryBuilder(4, "Auction Duration")
                        .fill(TemplateItems.EmptySlot.getItem())
                        .setItem(new ItemBuilder(Material.RED_TERRACOTTA)
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .setName("§a60 Minutes")
                                .addLoreRow("§7Extra Fee: §620 coins")
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to pick!")
                                .build(), 10)
                        .setItem(new ItemBuilder(Material.PINK_TERRACOTTA)
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .setName("§a360 Minutes")
                                .addLoreRow("§7Extra Fee: §660 coins")
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to pick!")
                                .build(), 11)
                        .setItem(new ItemBuilder(Material.ORANGE_TERRACOTTA)
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .setName("§a720 Minutes")
                                .addLoreRow("§7Extra Fee: §6120 coins")
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to pick!")
                                .build(), 12)
                        .setItem(new ItemBuilder(Material.YELLOW_TERRACOTTA)
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .setName("§a1440 Minutes")
                                .addLoreRow("§7Extra Fee: §6240 coins")
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to pick!")
                                .build(), 13)
                        .setItem(new ItemBuilder(Material.WHITE_TERRACOTTA)
                                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                                .setName("§a2880 Minutes")
                                .addLoreRow("§7Extra Fee: §6480 coins")
                                .addLoreRow(" ")
                                .addLoreRow("§eClick to pick!")
                                .build(), 14)
                        .setItem(TemplateItems.BackArrow.getItem(), 31)
                        .build());
        g.setCanceled(true);
        g.inventoryClickAction(10, type1 -> {
            this.minutes = 60;
            updateInventory();
            gui.showGUI(player);
        });
        g.inventoryClickAction(11, type1 -> {
            this.minutes = 360;
            updateInventory();
            gui.showGUI(player);
        });
        g.inventoryClickAction(12, type1 -> {
            this.minutes = 720;
            updateInventory();
            gui.showGUI(player);
        });
        g.inventoryClickAction(13, type1 -> {
            this.minutes = 1440;
            updateInventory();
            gui.showGUI(player);
        });
        g.inventoryClickAction(14, type1 -> {
            this.minutes = 2880;
            updateInventory();
            gui.showGUI(player);
        });
        g.inventoryClickAction(31, type1 -> {
            updateInventory();
            gui.showGUI(player);
        });
        g.showGUI(player);
    }
    private void updateInventory(){
        if(putInItem == null) {
            gui.getInventory().setItem(13, Items.EmptyItem.getItem());
            gui.getInventory().setItem(29, Items.DenyBin.getItem());
        }else{int cost = (minutes/6);
            if(cost < 20)
                cost = 20;

            double fee = cost + Tools.round(coins*0.01,1);

            gui.getInventory().setItem(13, Items.makeShowItem(putInItem));
            gui.getInventory().setItem(29, new ItemBuilder(Material.GREEN_TERRACOTTA)
                            .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                    .setName("§aCreate BIN Auction")
                            .addLoreRow("§7This item will be added the")
                            .addLoreRow("§7auction house for other players")
                            .addLoreRow("§7to purchase.")
                            .addLoreRow(" ")
                            .addLoreRow("§7Item: " + putInItem.getItemMeta().getDisplayName())
                            .addLoreRow("§7Auction duration: §e" + minutes + " Minutes")
                            .addLoreRow("§7Item price: §6" + Items.getDoubleToSring(coins) + " coins")
                            .addLoreRow(" ")
                            .addLoreRow("§7Creation fee: §6" + Items.getDoubleToSring(fee) + " coins")
                            .addLoreRow(" ")
                            .addLoreRow("§eClick to submit!")
                    .build());
        }
        gui.getInventory().setItem(31,Items.setSellPrice(coins));
        gui.getInventory().setItem(33,Items.getDuration(minutes));
        player.getHandle().bV.updateInventory();
    }


    public enum Items{
        EmptyItem(new ItemBuilder(Material.STONE_BUTTON)
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                .setName("§eClick an item in your inventory!")
                .addLoreRow("§7Selects it for auction")
                .build()),
        DenyBin(new ItemBuilder(Material.RED_TERRACOTTA)
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                .setName("§cCreate BIN Auction")
                .addLoreRow("§7No item selected!")
                .addLoreRow(" ")
                .addLoreRow("§7Click an item in your inventory")
                .addLoreRow("§7to select it for this auction.")
                .build());

        private final ItemStack item;
        Items(ItemStack build) {
            item = build;
        }
        public ItemStack getItem(){
            return item;
        }
        public static ItemStack setSellPrice(double coins){

            return new ItemBuilder(Material.GOLD_INGOT)
                    .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                    .setName("§fItem price: §6" + getDoubleToSring(coins) + " coins")
                    .addLoreRow("§7The price at which want to sell this item.")
                    .addLoreRow("§7sell this item.")
                    .addLoreRow(" ")
                    .addLoreRow("§7Extra fee: §6+"+ getDoubleToSring(Tools.round(coins*0.01,1)) + " coins §e(1%)")
                    .addLoreRow(" ")
                    .addLoreRow("§eClick to edit!")
                    .build();

        }
        public static ItemStack getDuration(int minutes){
            int cost = (minutes/6);
            if(cost < 20)
                cost = 20;



            return new ItemBuilder(Material.CLOCK)
                    .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                    .setName("§fDuration: §e" + minutes + " Minutes")
                    .addLoreRow("§7How long the item will be up for")
                    .addLoreRow("§7sale.")
                    .addLoreRow(" ")
                    .addLoreRow("§7Extra fee: §6+"+ cost + " coins")
                    .addLoreRow(" ")
                    .addLoreRow("§eClick to edit!")
                    .build();

        }
        public static ItemStack makeShowItem(ItemStack stack){
            ItemBuilder builder = new ItemBuilder(stack.getType())
                    .setName("§a§l§nAUCTION FOR ITEM:")
                    .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                    .addLoreRow(" ")
                    .addLoreRow(stack.getItemMeta().getDisplayName())
                    .addAllLore(stack.getItemMeta().getLore())
                    .addLoreRow(" ")
                    .addLoreRow("§eClick to pickup!");
            ItemManager manager = me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.get(
                    stack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)
            );
            if(stack.getType() == Material.PLAYER_HEAD)
                builder.setHead(manager.headTexture);

            if(manager.color != null){
                builder.setLeatherColor(manager.color);
            }
            if(!manager.getBannerPatterns().isEmpty()){
                builder.setBannerPatterns(manager.getBannerPatterns());
            }
            if(stack.getItemMeta().getEnchants() !=null && !stack.getItemMeta().getEnchants().isEmpty()){
                builder.setGlint(true);
            }
            builder.setAmount(stack.getAmount());

            return builder.build();
        }
        public static String getDoubleToSring(double d){
            if(d % 1 == 0){
                return String.format("%.0f", d);
            }else
                return String.format("%.1f", d);
        }
    }
}
