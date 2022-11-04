package me.CarsCupcake.SkyblockRemake.Equipment;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Configs.EquiomentFile;
import me.CarsCupcake.SkyblockRemake.Items.AbilityListener;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class EquipmentManager implements Listener {
    private final SkyblockPlayer player;

    //Ussage: Base Item - Persystent Data Container (as Strings) (Key - Value)
    public Bundle<ItemManager, HashMap<String, String>> necklace =  null;
    public Bundle<ItemManager, HashMap<String, String>> cloak =  null;
    public Bundle<ItemManager, HashMap<String, String>> belt =  null;
    public Bundle<ItemManager, HashMap<String, String>> fist =  null;

    public EquipmentManager(SkyblockPlayer player) {
        this.player = player;

    }
    public EquipmentManager(){
        this(null);
    }

    public void equip(ItemStack item){
        Bundle<ItemManager, HashMap<String, String>> bundle = getItemAsBundle(item);
        ItemType type = bundle.getFirst().type;
        if(type == ItemType.Necklace){
            necklace = bundle;
            setup(bundle);
            saveEquipment();
        }
        if(type == ItemType.Cloak){
            cloak = bundle;
            setup(bundle);
            saveEquipment();
        }
        if(type == ItemType.Belt){
            belt = bundle;
            setup(bundle);
            saveEquipment();
        }
        if(type == ItemType.Gloves || type == ItemType.Gauntlet){
            fist = bundle;
            setup(bundle);
            saveEquipment();
        }
    }

    public void loadEquipment(){
        necklace = loadByID("necklace");
        setup(necklace);
        cloak = loadByID("cloak");
        setup(cloak);
        belt = loadByID("belt");
        setup(belt);
        fist = loadByID("fist");
        setup(fist);
    }

    public void setup(Bundle<ItemManager, HashMap<String, String>> equipment){
        AbilityListener.checkArmor(player);
        if(equipment == null)
            return;

        if(equipment.getFirst().getEquipmentAbility() != null)
            equipment.getFirst().getEquipmentAbility().start(player);
    }
    public void remove(Bundle<ItemManager, HashMap<String, String>> equipment){
        AbilityListener.checkArmor(player);
        if(equipment == null)
            return;

        if(equipment.getFirst().getEquipmentAbility() != null)
            equipment.getFirst().getEquipmentAbility().stop();
    }

    public Bundle<ItemManager, HashMap<String, String>> loadByID(String str){
        EquiomentFile.reload();
        String id = EquiomentFile.get().getString(player.getUniqueId() + "."+str+".id");
        if(id != null){
            HashMap<String, String> map = new HashMap<>();
            EquiomentFile.get().getConfigurationSection(player.getUniqueId() + "." + str).getKeys(false).forEach(key ->
                    map.put(key,EquiomentFile.get().getString(player.getUniqueId() + "."+str+"." + key)));

            return new Bundle<>(Items.SkyblockItems.get(id), map);

        }else
            return null;
    }

    public static ItemStack buildItem(Bundle<ItemManager, HashMap<String, String>> bundle, SkyblockPlayer player){
        if(bundle == null){
            throw new IllegalArgumentException("bundle must not be null");


        }
        ItemStack item = bundle.getFirst().getRawItemStack();
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        bundle.getLast().forEach((t, k)->{
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

    public void saveEquipment(){
        saveEquipmentID("necklace", necklace);
        saveEquipmentID("cloak", cloak);
        saveEquipmentID("belt", belt);
        saveEquipmentID("fist", fist);

    }
    private void saveEquipmentID(String str, Bundle<ItemManager, HashMap<String, String>> bundle){
        if(bundle == null){
            EquiomentFile.get().set(player.getUniqueId() + "." + str, null);
            EquiomentFile.save();
            EquiomentFile.reload();
            return;
        }

        EquiomentFile.get().set(player.getUniqueId() + "."+ str +".id", bundle.getFirst().itemID);
        bundle.getLast().forEach((t,k) -> EquiomentFile.get().set(player.getUniqueId() + "."+ str +"." + t, k));
        EquiomentFile.save();
        EquiomentFile.reload();
    }
    public static Bundle<ItemManager, HashMap<String, String>> getItemAsBundle(ItemStack item){
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
        Bundle<ItemManager, HashMap<String, String>> bundle = new Bundle<>(Items.SkyblockItems.get(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"),PersistentDataType.STRING))
        , map);
        return bundle;
    }

    public double getTotalStat(Stats stats){
        double d = getEquipmentStat(stats, fist);
        d += getEquipmentStat(stats, belt);
        d += getEquipmentStat(stats, cloak);
        d += getEquipmentStat(stats, necklace);
      return d;
    }
    public double getEquipmentStat(Stats stats, Bundle<ItemManager, HashMap<String, String>> bundle){
        if(bundle == null)
            return 0;

        if(bundle.getLast().containsKey(stats.getDataName()))
            return Double.parseDouble(bundle.getLast().get(stats.getDataName()));
        return 0;
    }
    public void unregister(){
        remove(necklace);
        remove(belt);
        remove(cloak);
        remove(fist);
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        SkyblockPlayer.getSkyblockPlayer(event.getPlayer()).equipmentManager.unregister();
    }



}
