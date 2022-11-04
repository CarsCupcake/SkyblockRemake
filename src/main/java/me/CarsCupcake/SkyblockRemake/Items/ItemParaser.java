package me.CarsCupcake.SkyblockRemake.Items;






import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.reforges.registerReforge;
import net.kyori.adventure.key.Namespaced;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class ItemParaser {
    private ItemStack item;
    //Type - Value
    private HashMap<ParaseType, String> types = new HashMap<>();
    public void ItemParaser(ItemStack item){
        this.item = item;
    }
    public  ItemParaser(HashMap<ParaseType,String> types){
        this.types = types;
    }

    public ItemStack getItem(){return this.item;}
    public HashMap<ParaseType,String> getTypes(){return this.types;}

    public ItemStack paraseToItem(){
        ItemManager manager = Items.SkyblockItems.get(this.types.get(ParaseType.Id));
        if(manager == null){
            throw new IllegalArgumentException("Id is null or incorrect!");
        }
        ItemStack item = manager.getRawItemStack();
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        for (ParaseType type : types.keySet()){

        }


        return item;
    }

    public void setItemSTack(ItemStack item){
        this.item = item;
    }

    public static double calculateStat(Stats stat, ItemStack item){
        double result = 0;
        if(item != null && item.hasItemMeta() && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer() != null && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), stat.getDataName()), PersistentDataType.DOUBLE) != null){
            ItemManager manager = Items.SkyblockItems.get(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
    ItemMeta meta = item.getItemMeta();
    PersistentDataContainer data = meta.getPersistentDataContainer();
result += manager.getStat(stat);
    int potatobooks = 0;
    if(item.getItemMeta().getPersistentDataContainer()
                    .get(new NamespacedKey(Main.getMain(), "potatobooks"), PersistentDataType.INTEGER) != null){
    potatobooks = item.getItemMeta().getPersistentDataContainer()
                    .get(new NamespacedKey(Main.getMain(), "potatobooks"), PersistentDataType.INTEGER);
    }
            if ((manager.type == ItemType.Helmet || manager.type == ItemType.Chestplate
                    || manager.type == ItemType.Leggings || manager.type == ItemType.Boots) && stat == Stats.Defense)
                result += potatobooks*2;
            else
            if ((manager.type == ItemType.Helmet || manager.type == ItemType.Chestplate
                    || manager.type == ItemType.Leggings || manager.type == ItemType.Boots) && stat == Stats.Health)
                result += potatobooks*4;
            else
                if(stat == Stats.Strength)
                    result += potatobooks*2;




                if (data.get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING) != null
                        && Reforge.getReforgeValue(
                        registerReforge.reforges.get(
                                data.get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING)),
                        manager.rarity, "health") != 0)
                    result += Reforge.getReforgeValue(registerReforge.reforges
                                    .get(data.get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING)),
                            manager.rarity, stat.getDataName());








        }
        return result;
    }



    enum ParaseType {
        Recomed,
        HotpotatoBooks,
        Slot_0,
        Slot_1,
        Slot_2,
        Slot_3,
        Slot_4,
        Slot_5,
        Slot_6,
        Id,

        //Otionals
        Petxp,
        Petlevel,

        DrillEngine,
        DrillFuelTank,
        DrillFuel,

        GivenFrom,
        GivenTo,
        Edition,
        Date;
        public String getDataTag(){
            switch (this){

                case Recomed -> {
                    return "recomed";
                }

                case HotpotatoBooks -> {
                    return "potatobooks";
                }
                case Slot_0 -> {
                    return "SLOT_0";
                }
                case Slot_1 -> {
                    return "SLOT_1";
                }
                case Slot_2 -> {
                    return "SLOT_2";
                }
                case Slot_3 -> {
                    return "SLOT_3";
                }
                case Slot_4 -> {
                    return "SLOT_4";
                }
                case Slot_5 -> {
                    return "SLOT_5";
                }
                case Slot_6 -> {
                    return "SLOT_6";
                }
                case Id -> {
                    return "id";
                }
                case Petxp -> {
                    return "currxp";
                }
                case Petlevel -> {
                    return "level";
                }
                case DrillEngine -> {
                    return "drillengine";
                }
                case DrillFuel -> {
                    return "fuel";
                }
                case DrillFuelTank -> {
                    return "fueltank";
                }
                case GivenFrom -> {
                    return "from";
                }
                case GivenTo -> {
                    return "to";
                }
                case Edition -> {
                    return "edition";
                }
                case Date -> {
                    return "date";
                }
            }

            return null;


     }
  }
}
