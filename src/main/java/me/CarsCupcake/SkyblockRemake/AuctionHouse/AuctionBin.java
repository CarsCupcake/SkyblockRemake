package me.CarsCupcake.SkyblockRemake.AuctionHouse;

import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Date;
import java.util.List;

public class AuctionBin implements IAuction{
    private final Player player;
    private final ItemStack item;
    private Date exp;
    private double cost;
    private int pointer = -1;
    private boolean isExpired;
    private boolean isSold = false;
    public AuctionBin(Player player, ItemStack itemStack, Date expires, double cost){
        this.player = player;
        item = itemStack;
        exp = expires;
        this.cost = cost;
        Date d2 = new Date();

        long diff =  exp.getTime()-d2.getTime();
        if(diff < 0)
            isExpired = true;
        else
            isExpired = false;

    }
    public void setDate(Date date){
        exp = date;
    }
    public void setCost(double cost){
        this.cost = cost;
    }
    public Player getPlayer(){
        return player;
    }

    @Override
    public int getAuctionPointer() {
        return pointer;
    }
    public boolean isExpired(){
        return isExpired;
    }
    public void setSold(boolean b){
        isSold = b;
    }
    public boolean isSold(){
        return !AuctionHouse.getInstance().getFile().get().getString("auction.bin." + pointer + ".status").equals("unfilled");
    }

    @Override
    public ItemStack craftShowItem() {
        Date d2 = new Date();

        long diff =  exp.getTime()-d2.getTime();

        ItemStack item = this.item.clone();
        ItemMeta meta = item.getItemMeta();
        List<String> lore = item.getItemMeta().getLore();
        lore.add("§8§m----------------");
        lore.add("§7Seller: §f" + player.getName());
        lore.add("§7Buy it now: §6" + Tools.addDigits(cost) + " coins");
        lore.add(" ");
        if(isSold()){
            lore.add("§7Status: §aSold!");
        }else
        if(diff > 0) {
            lore.add("§7Ends in §e" + shortInteger((int) (diff / (1000))));
        }else
            lore.add("§7Status: §cExpired!");
        lore.add(" ");
        lore.add("§eClick to inspect!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    private String shortInteger(int duration) {
        String string = "";
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (duration / 60 / 60 / 24 >= 1) {
            days = duration / 60 / 60 / 24;
            duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
        }
        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60;
            duration -= duration / 60 / 60 * 60 * 60;
        }
        if (duration / 60 >= 1) {
            minutes = duration / 60;
            duration -= duration / 60 * 60;
        }
        if (duration >= 1)
            seconds = duration;

        if(days != 0)
        if (days <= 9) {
            string = string + days+ "d ";
        } else {
            string = string + days + "d ";}
        if(hours != 0)
        if (hours <= 9) {
            string = string  + hours + "h ";
        } else {
            string = string + hours + "h ";
        }
        if(minutes != 0)
        if (minutes <= 9) {
            string = string  + minutes + "m ";
        } else {
            string = string + minutes + "m ";
        }
        if (seconds <= 9) {
            string = string  + seconds + "s";
        } else {
            string = string + seconds + "s";
        }
        return string;
    }

    @Override
    public void openManager(SkyblockPlayer player) {
        InventoryBuilder builder = new InventoryBuilder(6, this.player.getName() + "'s Auction")
        .fill(TemplateItems.EmptySlot.getItem())
                .setItem(craftShowItem(), 13);


        if(!this.player.equals(player)){
            if (player.coins >= cost) {
                builder.setItem(new ItemBuilder(Material.GOLD_NUGGET)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§6Buy Item Right Now")
                        .addLoreRow(" ")
                        .addLoreRow("§7Price: §6" + SetupOrder.Items.getDoubleToSring(cost))
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to purchase!").build(), 31);
            } else {
                builder.setItem(new ItemBuilder(Material.POISONOUS_POTATO)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§6Buy Item Right Now")
                        .addLoreRow(" ")
                        .addLoreRow("§7Price: §6" + SetupOrder.Items.getDoubleToSring(cost))
                        .addLoreRow(" ")
                        .addLoreRow("§6You cant affort that!")
                        .build(), 31);
            }
        }else{
            if(isSold()){
                builder.setItem(new ItemBuilder(Material.GOLD_BLOCK)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§6Click to collect")
                        .addLoreRow(" ")
                        .addLoreRow("§aThe auction has sold!")
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to collect!").build(), 31);
            }else if(isExpired()){
                builder.setItem(new ItemBuilder(Material.GOLD_BLOCK)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§6Click to collect")
                        .addLoreRow(" ")
                        .addLoreRow("§cThe auction has expired!")
                        .addLoreRow(" ")
                        .addLoreRow("§eClick to collect!").build(), 31);
            }else {
                builder.setItem(new ItemBuilder(Material.POISONOUS_POTATO)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .setName("§6Buy Item Right Now")
                        .addLoreRow(" ")
                        .addLoreRow("§7Price: §6" + SetupOrder.Items.getDoubleToSring(cost))
                        .addLoreRow(" ")
                        .addLoreRow("§6You cant bid on your own auction!")
                        .build(), 31);
            }
        }


        Inventory inv = builder.build();
        GUI gui = new GUI(inv);
        gui.setCanceled(true);
        gui.inventoryClickAction(31, type -> {
            if(!this.player.equals(player)){
                if (isExpired()) {
                    player.sendMessage("§cThe auction is expired!");
                    gui.closeInventory();
                    return;
                }
                if (isSold()) {
                    player.sendMessage("§cThe auction is sold!");
                    gui.closeInventory();
                    return;
                }
                if (!(player.coins >= cost)) {
                    player.sendMessage("§cYou canot afford this auction!");
                    return;
                }
                sell(player);
                gui.closeInventory();
            }else{
                if(isSold()){
                    player.setCoins(player.coins+ cost);
                    player.sendMessage("§aYou succesfully claimed your auction!");
                    gui.closeInventory();
                    AuctionHouse.getInstance().getFile().get().set("auction.bin." + pointer, null);
                    AuctionHouse.getInstance().getFile().save();
                    AuctionHouse.getInstance().getFile().reload();
                    AuctionHouse.getInstance().getFile().get().set(
                            AuctionHouse.getInstance().getFile().get().getString("auction.bin." + pointer + ".uuid") + ".bin."
                                    + AuctionHouse.getInstance().getFile().get().getInt("auction.bin." + pointer + ".pointer")
                            , null);
                    return;
                }
                if(isExpired()){
                    player.addItem(item);
                    player.sendMessage("§aYou succesfully claimed your auction!");
                    gui.closeInventory();
                    AuctionHouse.getInstance().getFile().get().set(
                            AuctionHouse.getInstance().getFile().get().getString("auction.bin." + pointer + ".uuid") + ".bin."
                            + AuctionHouse.getInstance().getFile().get().getInt("auction.bin." + pointer + ".pointer")
                    , null);
                    AuctionHouse.getInstance().getFile().get().set("auction.bin." + pointer, null);
                    AuctionHouse.getInstance().getFile().save();
                    AuctionHouse.getInstance().getFile().reload();
                    return;
                }
                player.sendMessage("§cYou canot bit on your own auction!");

            }

        });
        gui.closeAction(type -> AuctionHouse.getInstance().getGuis().remove(gui));
        AuctionHouse.getInstance().getGuis().add(gui);
        gui.showGUI(player);
    }
    private void sell(SkyblockPlayer player){
        if(this.player.isOnline())
            this.player.sendMessage("§aThe auction for " + Items.SkyblockItems.get(
                    item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)
            ).rarity.getPrefix()
            + item.getItemMeta().getDisplayName() + " §ahas been filled!");
        isSold = true;
        AuctionHouse.getInstance().getFile().get().set("auction.bin." + pointer + ".status", "filled");
        AuctionHouse.getInstance().getFile().save();
        AuctionHouse.getInstance().getFile().reload();
        player.getInventory().addItem(item);
    }


    @Override
    public boolean show() {
        return !(isSold() || isExpired);
    }

    public void setAuctionPointer(int i){
        pointer = i;
    }

    public ItemStack getItem(){
        return item;

    }
    public double getCost(){
        return cost;
    }
    public Date getExpiringDate(){
        return exp;
    }
}
