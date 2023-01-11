package me.CarsCupcake.SkyblockRemake.AuctionHouse;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class AuctionManager {
    public static String pattern = "MM-dd-yyyy-HH-mm-ss";
    public static SimpleDateFormat df = new SimpleDateFormat(pattern);
    public static void setupBin(AuctionBin auction){
        CustomConfig file = AuctionHouse.getInstance().getFile();
        Player player = auction.getPlayer();
        int var = 1;
        if (file.get().getConfigurationSection(player.getUniqueId() + ".bin") != null &&
                file.get().contains(player.getUniqueId() + ".bin"))
            var = file.get().getConfigurationSection(player.getUniqueId() + ".bin").getKeys(false).size() + 2;
        int playerPointer = var;
        var = 1;
        if (file.get().getConfigurationSection("auction.bin") != null && file.get().contains("auction.bin"))
            var = file.get().getConfigurationSection("auction.bin").getKeys(false).size() + 2;
        int auctionPointer = var;

        file.get().set(player.getUniqueId() + ".bin." + playerPointer + ".pointer", auctionPointer);


        file.get().set("auction.bin." + auctionPointer + ".pointer", playerPointer);
        file.get().set("auction.bin." + auctionPointer + ".uuid", player.getUniqueId().toString());
        file.get().set("auction.bin." + auctionPointer + ".exp", df.format(auction.getExpiringDate()));
        file.get().set("auction.bin." + auctionPointer + ".coins", auction.getCost());
        file.get().set("auction.bin." + auctionPointer + ".status", "unfilled");
        /*for(String str : auction.getData().keySet())
            file.get().set("auction.bin." + auctionPointer + ".pdc." + str, auction.getData().get(str).getFirst() + "/" + auction.getData().get(str).getLast());*/
        saveItem(auctionPointer, getItemAsMap(auction.getItem()));
        for(Enchantment enchantment : auction.getItem().getItemMeta().getEnchants().keySet())
            file.get().set("auction.bin." + auctionPointer + ".ench." + enchantment.getKey().getKey(), auction.getItem().getItemMeta().getEnchants().get(enchantment));
        AuctionHouse.getInstance().getFile().save();
        AuctionHouse.getInstance().getFile().reload();

    }
    private static  HashMap<String, String> getItemAsMap(ItemStack item){
        PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
        HashMap<String, String> map = new HashMap<>();
        for(NamespacedKey key : data.getKeys()) {
            if(data.has(key, PersistentDataType.STRING))
                map.put(key.getKey(), data.get(key, PersistentDataType.STRING));
            if(data.has(key, PersistentDataType.DOUBLE))
                map.put(key.getKey(), data.get(key, PersistentDataType.DOUBLE).toString());
            if(data.has(key, PersistentDataType.FLOAT))
                map.put(key.getKey(), data.get(key, PersistentDataType.FLOAT).toString());
            /*if(data.has(key, PersistentDataType.INTEGER))
                map.put(key.getKey(), data.get(key, PersistentDataType.INTEGER).toString());*/

        }


        return map;
    }
    private static void saveItem(int Pointer,HashMap<String, String> bundle){

        bundle.forEach((t,k) -> AuctionHouse.getInstance().getFile().get().set("auction.bin." + Pointer + ".pdc." + t, k));
        AuctionHouse.getInstance().getFile().save();
        AuctionHouse.getInstance().getFile().reload();
    }
    public static ArrayList<IAuction> getPlayerAuctions(SkyblockPlayer player){
        ArrayList<IAuction> auctions = new ArrayList<>();
        AuctionHouse.getInstance().getFile().reload();
        ConfigurationSection section = AuctionHouse.getInstance().getFile().get().getConfigurationSection(player.getUniqueId() + ".bin");
        if(section != null){
            for(String keys : section.getKeys(false)){
                try{
                    auctions.add(buildBin("auction.bin." + AuctionHouse.getInstance().getFile().get().getInt(player.getUniqueId() + ".bin." + keys + "." + "pointer"), player));
                }catch (Exception e){
                    e.printStackTrace();
                    player.sendMessage("§cThere was an errer while loading an auction §7(" + e.getClass().getSimpleName() + ")");
                }
            }
        }
        return auctions;
    }
    private static AuctionBin buildBin(String auctionPointer, Player player) throws ParseException {
        HashMap<String, String> map = new HashMap<>();
        if(AuctionHouse.getInstance().getFile().get().getConfigurationSection(auctionPointer + ".pdc") != null)
        for (String t : AuctionHouse.getInstance().getFile().get().getConfigurationSection(auctionPointer + ".pdc").getKeys(false)){
            map.put(t, AuctionHouse.getInstance().getFile().get().getString(auctionPointer + ".pdc." + t));
        }
        ItemStack item = buildItem(map, null);
        ItemMeta meta = item.getItemMeta();
        if(AuctionHouse.getInstance().getFile().get().getConfigurationSection(auctionPointer + ".ench") != null)
        for(String t : AuctionHouse.getInstance().getFile().get().getConfigurationSection(auctionPointer + ".ench").getKeys(false)){
            NamespacedKey key;
            if(SkyblockEnchants.skyblockEnchantIds.contains(t)){
                key = new NamespacedKey(Main.getMain(), t);
            }else {
                key = NamespacedKey.minecraft(t);
            }
            if(Enchantment.getByKey(key) == null)
                continue;
            meta.addEnchant(Enchantment.getByKey(key),
                    AuctionHouse.getInstance().getFile().get().getInt(auctionPointer + ".ench." + t), false);
        }
        item.setItemMeta(meta);
        item = Main.item_updater(item, null);
        Date d;

          d = df.parse(AuctionHouse.getInstance().getFile().get().getString( auctionPointer + ".exp"));

        AuctionBin bin = new AuctionBin(player, item, d,
                AuctionHouse.getInstance().getFile().get().getDouble(auctionPointer + ".coins"));
        bin.setAuctionPointer(Integer.parseInt(auctionPointer.split("\\.")[2]));
        bin.setSold(boolFromStatus(AuctionHouse.getInstance().getFile().get().getString(auctionPointer + ".status")));
        return bin;
    }
    private static boolean boolFromStatus(String s){
        return !s.equals("unfilled");
    }
    private static ItemStack buildItem(HashMap<String, String> map, SkyblockPlayer player){
        ItemStack item = new ItemStack(Material.FEATHER);

        for(String str : map.keySet())
            if(str.equals("id"))
                item = Items.SkyblockItems.get(map.get(str)).getRawItemStack();
            ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        map.forEach((t, k)->{
            if(t.equals("dmg")){
                data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, k);
                return;
            }
            boolean isParaseble = false;
            try{
                int integer = Integer.parseInt(k);
                if(integer != 0){
                    isParaseble = true;
                    data.set(new NamespacedKey(Main.getMain(), t), PersistentDataType.INTEGER, integer);
                }
            }catch(Exception ignored){

            }
            if(!isParaseble){
                try{
                    double doubl = Double.parseDouble(k);
                    if(doubl != 0){
                        isParaseble = true;
                        data.set(new NamespacedKey(Main.getMain(), t), PersistentDataType.DOUBLE, doubl);
                    }
                }catch(Exception ignored){

                }
            }
            if(!isParaseble){
                data.set(new NamespacedKey(Main.getMain(), t), PersistentDataType.STRING, k);
            }
        } );

        item.setItemMeta(meta);

        return Main.item_updater(item, player);

    }
    public static ArrayList<IAuction> getAllAuctions(SkyblockPlayer request){
        ArrayList<IAuction> auctions = new ArrayList<>(getAllBinAuctions(request));


        ArrayList<IAuction> finalAuctions = new ArrayList<>();
        for (IAuction auction : auctions){
            if(auction.show())
                finalAuctions.add(auction);
        }
        return finalAuctions;
    }
    public static ArrayList<AuctionBin> getAllBinAuctions(SkyblockPlayer request){
        ArrayList<AuctionBin> auctionBins = new ArrayList<>();

        if(AuctionHouse.getInstance().getFile().get().getConfigurationSection("auction.bin") != null){
            for(String str : AuctionHouse.getInstance().getFile().get().getConfigurationSection("auction.bin").getKeys(false)){

                try {
                    auctionBins.add(buildBin("auction.bin." + str,
                            Bukkit.getPlayer(UUID.fromString(
                                    AuctionHouse.getInstance().getFile().get().getString("auction.bin."+str+".uuid")
                            ))));

                }catch (Exception e){
                    e.printStackTrace();
                    request.sendMessage("§cThere was an errer while loading an auction §7(" + e.getClass().getSimpleName() + ")");
                }
            }
        }

        return auctionBins;
    }
}
