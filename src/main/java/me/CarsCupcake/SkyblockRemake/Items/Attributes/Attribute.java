package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.ClassUtils;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Constructor;
import java.util.*;

import static org.bukkit.persistence.PersistentDataType.*;

public abstract class Attribute implements Listener {
    private static final HashMap<String, Class<? extends Attribute>> registeredAttributes = new HashMap<>();
    @Getter
    private static final HashMap<SkyblockPlayer, Set<Attribute>> activeAttributes = new HashMap<>();
    @Getter
    protected int level;
    private final ItemType activeType;
    protected final SkyblockPlayer player;

    public Attribute() {
        activeType = null;
        player = null;
    }

    public static void registerAttribute(Attribute atribute){
        registeredAttributes.put(atribute.name(), atribute.getClass());
    }
    public Attribute(ItemType activeType, Integer level, SkyblockPlayer player){
        this.activeType = activeType;
        this.level = level;
        this.player = player;
    }
    public abstract String name();
    public abstract int maxLevel();
    public abstract boolean isAllowed();
    public abstract List<String> lore();
    public abstract void start();
    public abstract void stop();
    public ItemType activeType(){
        return activeType;
    }
    public List<String> getAttributeLore(){
        ArrayList<String> lore = new ArrayList<>(List.of("§b" + name() + " " + Tools.intToRoman(level)));
        lore.addAll(lore());
        return lore;
    }
    @EventHandler
    public void swappingHands(PlayerItemHeldEvent event){
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());

        if(ItemHandler.hasPDC("id",player.getInventory().getItem(event.getPreviousSlot()), STRING)) {

            ItemManager prevItem = Items.SkyblockItems.get(ItemHandler.getPDC("id", player.getInventory().getItem(event.getPreviousSlot()), STRING));

            if (prevItem.isAttributable() && !activeAttributes.get(player).isEmpty()) {
                for (Attribute attribute : new HashSet<>(activeAttributes.get(player))) {
                    if (attribute.activeType == prevItem.type) {
                        attribute.stop();
                        activeAttributes.get(player).remove(attribute);
                    }
                }
            }
        }

        if(!ItemHandler.hasPDC("id",player.getInventory().getItem(event.getNewSlot()), STRING))
            return;
        ItemStack item = player.getInventory().getItem(event.getNewSlot());
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id",item, STRING));

        if(!manager.isAttributable())
            return;

        Constructor<? extends Attribute> constructor = Tools.getConstructorIfAvailable(registeredAttributes.get(ItemHandler.getPDC("attribute_1", item, STRING)), ItemType.class, Integer.class, SkyblockPlayer.class);
        Attribute attribute = ClassUtils.instantiateClass(constructor, manager.type, ItemHandler.getPDC("attribute_1_level", item, INTEGER), player);

        attribute.start();
        if(activeAttributes.containsKey(player))
            activeAttributes.get(player).add(attribute);
        else {
            activeAttributes.put(player, new HashSet<>());
            activeAttributes.get(player).add(attribute);
        }

        constructor = Tools.getConstructorIfAvailable(registeredAttributes.get(ItemHandler.getPDC("attribute_2", item, STRING)), ItemType.class, Integer.class, SkyblockPlayer.class);
        attribute = ClassUtils.instantiateClass(constructor, manager.type, ItemHandler.getPDC("attribute_2_level", item, INTEGER), player);

        attribute.start();
        activeAttributes.get(player).add(attribute);
    }

    public static void swapAttrbutes(ItemStack item1, ItemStack item2, SkyblockPlayer player){
        if(ItemHandler.hasPDC("id",item1, STRING)) {
            ItemManager prevItem = Items.SkyblockItems.get(ItemHandler.getPDC("id", item1, STRING));

            if (prevItem.isAttributable()) {
                for (Attribute attribute : new HashSet<>(activeAttributes.get(player))) {
                    if (attribute.activeType == prevItem.type) {
                        attribute.stop();
                        activeAttributes.get(player).remove(attribute);
                    }
                }
            }
        }

        if(item2 == null)
            return;

        if(!ItemHandler.hasPDC("id",item2, STRING))
            return;
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id",item2, STRING));

        if(!manager.isAttributable())
            return;

        Constructor<? extends Attribute> constructor = Tools.getConstructorIfAvailable(registeredAttributes.get(ItemHandler.getPDC("attribute_1", item2, STRING)), ItemType.class, Integer.class, SkyblockPlayer.class);
        Attribute attribute = ClassUtils.instantiateClass(constructor, manager.type, ItemHandler.getPDC("attribute_1_level", item2, INTEGER), player);

        attribute.start();
        activeAttributes.get(player).add(attribute);

        constructor = Tools.getConstructorIfAvailable(registeredAttributes.get(ItemHandler.getPDC("attribute_2", item2, STRING)), ItemType.class, Integer.class, SkyblockPlayer.class);
        attribute = ClassUtils.instantiateClass(constructor, manager.type, ItemHandler.getPDC("attribute_2_level", item2, INTEGER), player);

        attribute.start();
        activeAttributes.get(player).add(attribute);
    }

    public static void rool(ItemStack item, ItemManager manager){
        System.out.println("gmable :>");

        Collection<Class<? extends Attribute>> atr = registeredAttributes.values();
        ArrayList<Class<? extends Attribute>> attrebuteList = new ArrayList<>(atr);
        Collections.shuffle(attrebuteList);

        Constructor<? extends Attribute> constructor = Tools.getConstructorIfAvailable(attrebuteList.get(0), ItemType.class, Integer.class, SkyblockPlayer.class);
        Attribute attribute = ClassUtils.instantiateClass(constructor, manager.type, 1, null);
        applyAttrebute(attribute, 1, item);

        constructor = Tools.getConstructorIfAvailable(attrebuteList.get(1), ItemType.class, Integer.class, SkyblockPlayer.class);
        attribute = ClassUtils.instantiateClass(constructor, manager.type, 1, null);
        applyAttrebute(attribute, 2, item);

    }

    public static void applyAttrebute(Attribute attribute, int slot, ItemStack item){
        Assert.notNull(attribute, "Attribute canot be null");
        Assert.isTrue(slot > 0 && slot < 3, "Slot is out of range");
        Assert.notNull(item, "item canot be null!");

        ItemHandler.setPDC("attribute_" + slot, item, STRING, attribute.name());
        ItemHandler.setPDC("attribute_" + slot + "_level", item, INTEGER, attribute.getLevel());

    }

    public static ArrayList<Attribute> getAttributes(ItemStack item, SkyblockPlayer player){
        Assert.notNull(item, "item canot be null");
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, STRING));
        Assert.isTrue(manager.isAttributable(), "manager has to be attribuatble!");
        ArrayList<Attribute> attributes = new ArrayList<>();
        if(ItemHandler.hasPDC("attribute_1", item, STRING)) {
            Constructor<? extends Attribute> constructor = Tools.getConstructorIfAvailable(registeredAttributes.get(ItemHandler.getPDC("attribute_1", item, STRING)), ItemType.class, Integer.class, SkyblockPlayer.class);
            Attribute attribute = ClassUtils.instantiateClass(constructor, manager.type,
                    ItemHandler.getOrDefaultPDC("attribute_1_level", item, INTEGER, 1), player);
            attributes.add(attribute);
        }
        if(ItemHandler.hasPDC("attribute_2", item, STRING)) {
            Constructor<? extends Attribute> constructor = Tools.getConstructorIfAvailable(registeredAttributes.get(ItemHandler.getPDC("attribute_2", item, STRING)), ItemType.class, Integer.class, SkyblockPlayer.class);
            Attribute attribute = ClassUtils.instantiateClass(constructor, manager.type, ItemHandler.getOrDefaultPDC("attribute_2_level", item, INTEGER, 1), player);
            attributes.add(attribute);
        }
        return attributes;
    }

    private static final Set<Player> checkDrop = new HashSet<>();
    @EventHandler
    public void onDrop(InventoryClickEvent event){
        if(!(event.getClickedInventory() instanceof PlayerInventory))
            return;

        if(event.getClick() == ClickType.DROP || event.getClick() == ClickType.CONTROL_DROP)
            checkDrop.add((Player) (event.getWhoClicked()));
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if(checkDrop.contains(event.getPlayer())) {
            checkDrop.remove(player);
            return;
        }
        swapAttrbutes(event.getItemDrop().getItemStack(), null, SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event){
        if(event.getEntity() instanceof Player p){
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);

        }
    }
}
