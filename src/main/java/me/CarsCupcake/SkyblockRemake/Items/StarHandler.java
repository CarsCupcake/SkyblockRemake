package me.CarsCupcake.SkyblockRemake.Items;

import joptsimple.internal.Strings;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class StarHandler {
    public static String getStarSuffix(ItemStack item){
        try{
            Assert.allNotNull("", item, item.getItemMeta(), item.getItemMeta().getPersistentDataContainer());
        }catch (Exception ignored){
            return "";
        }
        String s = "";
        int stars = ItemHandler.getOrDefaultPDC("stars", item, PersistentDataType.INTEGER, 0);
        if(!(stars <= 0)) {
            if (stars <= 5) {
                s = "§6" + Strings.repeat('✪', stars);
            } else if (stars <= 10) {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < 5; i++)
                    str.append((i + 6 > stars) ? "§6" : "§d").append("✪");
                s = str.toString();
            } else if (stars <= 15) {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < 5; i++)
                    str.append((i + 11 > stars) ? "§d" : "§b").append("✪");
                s = str.toString();
            }
        }

        if(!ItemHandler.hasPDC("master_stars",item, PersistentDataType.INTEGER))
            return s;
        int mstars = ItemHandler.getPDC("master_stars", item, PersistentDataType.INTEGER);
        if(mstars <= 0)
            return s;
        String sing;
        switch (mstars){
            case 1 -> sing = "❶";
            case 2 -> sing = "❷";
            case 3 -> sing = "❸";
            case 4 -> sing = "❹";
            case 5 -> sing = "❺";
            default -> throw new IndexOutOfBoundsException("Master Star count is to high");
        }
        s += "§c" + sing;
        return s;

    }
    public static void addStar(ItemStack item){
        Assert.allNotNull("", item, item.getItemMeta(), item.getItemMeta().getPersistentDataContainer());
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        int stars = ItemHandler.getOrDefaultPDC("stars", item, PersistentDataType.INTEGER, 0);
        stars++;
        if(stars > manager.getMaxStars())
            return;
        ItemHandler.setPDC("stars", item, PersistentDataType.INTEGER, stars);
    }
    public static void setStars(ItemStack item, int i) throws NullPointerException{
        Assert.allNotNull("", item, item.getItemMeta(), item.getItemMeta().getPersistentDataContainer());
        Assert.isTrue(i >= 0, "stars have to be larger then 0");
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        if(i > manager.getMaxStars()){
            ItemHandler.setPDC("stars", item, PersistentDataType.INTEGER, manager.getMaxStars());
            return;
        }
        ItemHandler.setPDC("stars", item, PersistentDataType.INTEGER, i);
    }
    public static void addMasterStars(ItemStack item){
        Assert.allNotNull("", item, item.getItemMeta(), item.getItemMeta().getPersistentDataContainer());
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        Assert.isTrue(manager.isDungeonItem, "The item is not a dungeon item");
        int stars = ItemHandler.getOrDefaultPDC("master_stars", item, PersistentDataType.INTEGER, 0);
        stars++;
        if(stars > 5)
            return;
        ItemHandler.setPDC("master_stars", item, PersistentDataType.INTEGER, stars);
    }
    public static void setMasterStars(ItemStack item, int stars){
        Assert.allNotNull("", item, item.getItemMeta(), item.getItemMeta().getPersistentDataContainer());
        Assert.isTrue(stars >= 0, "stars have to be larger then 0");
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        Assert.isTrue(manager.isDungeonItem, "The item is not a dungeon item");
        Assert.isTrue(stars <= 5, "The stars canot be larger then 5");
        ItemHandler.setPDC("master_stars", item, PersistentDataType.INTEGER, stars);
    }
    public static double getStarBuff(ItemStack item){
        double i = 1;
        Assert.allNotNull("", item, item.getItemMeta(), item.getItemMeta().getPersistentDataContainer());
        i += ItemHandler.getOrDefaultPDC("stars", item, PersistentDataType.INTEGER, 0) * 0.02;
        return i;
    }


}
