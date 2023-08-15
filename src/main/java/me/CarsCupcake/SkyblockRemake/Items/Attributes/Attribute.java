package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.ClassUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_17_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_17_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;

import java.lang.reflect.Constructor;
import java.util.*;

import static org.bukkit.persistence.PersistentDataType.*;

public abstract class Attribute implements Listener {
    private static final HashMap<String, Class<? extends Attribute>> registeredAttributes = new HashMap<>();
    @Getter
    protected int level;
    private final ItemType activeType;
    protected final String id;

    public Attribute() {
        activeType = null;
        id = name().toLowerCase().replace(" ", "_");
    }

    public static void registerAttribute(Attribute atribute) {
        registeredAttributes.put(atribute.getId(), atribute.getClass());
    }

    public Attribute(ItemType activeType, Integer level, SkyblockPlayer player) {
        id = name().toLowerCase().replace(" ", "_");
        this.activeType = activeType;
        this.level = level;
    }

    public abstract String name();

    public abstract int maxLevel();

    public abstract boolean isAllowed();

    public abstract List<String> lore();

    public ItemType activeType() {
        return activeType;
    }

    public List<String> getAttributeLore() {
        ArrayList<String> lore = new ArrayList<>(List.of("§b" + name() + " " + Tools.intToRoman(level)));
        lore.addAll(lore());
        return lore;
    }

    public String getId() {
        return id;
    }

    public NamespacedKey makeNamespacedKey() {
        return new NamespacedKey(Main.getMain(), getId());
    }

    public static void rool(ItemStack item, ItemManager manager) {
        Collection<Class<? extends Attribute>> atr = registeredAttributes.values();
        ArrayList<Class<? extends Attribute>> attrebuteList = new ArrayList<>(atr);
        Collections.shuffle(attrebuteList);
        Constructor<? extends Attribute> constructor = Tools.getConstructorIfAvailable(attrebuteList.get(0), ItemType.class, Integer.class);
        constructor = Tools.getConstructorIfAvailable(attrebuteList.get(1), ItemType.class, Integer.class);
        applyAttrebute(new Attribute[]{ClassUtils.instantiateClass(constructor, manager.type, 1), ClassUtils.instantiateClass(constructor, manager.type, 1)}, item);
    }

    public static void applyAttrebute(Attribute[] attribute, ItemStack item) {
        Assert.notNull(attribute, "Attribute canot be null");
        Assert.isTrue(attribute.length > 0 && attribute.length < 3, "Slot is out of range");
        Assert.notNull(item, "item canot be null!");
        PersistentDataContainer container = new CraftPersistentDataContainer(new CraftPersistentDataTypeRegistry());
        for (Attribute a : attribute)
            container.set(a.makeNamespacedKey(), INTEGER, a.level);
        ItemHandler.setPDC("attributes", item, TAG_CONTAINER, container);
    }

    public static List<Attribute> getAttributes(ItemStack item) {
        Assert.notNull(item, "item canot be null");
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, STRING));
        Assert.isTrue(manager.isAttributable(), "manager has to be attribuatble!");
        List<Attribute> attributes = new ArrayList<>();
        PersistentDataContainer container = ItemHandler.getOrDefaultPDC("attributes", item, TAG_CONTAINER, new CraftPersistentDataContainer(new CraftPersistentDataTypeRegistry()));
        for (NamespacedKey s : container.getKeys()) {
            String id = s.getKey();
            Constructor<? extends Attribute> constructor = Tools.getConstructorIfAvailable(registeredAttributes.get(id), ItemType.class, Integer.class);
            Attribute attribute = ClassUtils.instantiateClass(constructor, manager.type, container.get(s, INTEGER));
        }
        return attributes;
    }
}
