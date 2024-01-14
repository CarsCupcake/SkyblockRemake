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

public abstract class Attribute {
    private static final HashMap<String, Attribute> registeredAttributes = new HashMap<>();
    private final ItemType activeType;
    protected final String id;

    public Attribute() {
        activeType = null;
        id = name().toLowerCase().replace(" ", "_");
    }

    public static void registerAttribute(Attribute atribute) {
        registeredAttributes.put(atribute.getId(), atribute);
    }

    public abstract String name();

    public abstract int maxLevel();

    public abstract boolean isAllowed(SkyblockPlayer player);

    public abstract List<String> lore(int level);

    public ItemType activeType() {
        return activeType;
    }

    public List<String> getAttributeLore(int level) {
        ArrayList<String> lore = new ArrayList<>(List.of("§b" + name() + " " + Tools.intToRoman(level)));
        lore.addAll(lore(level));
        return lore;
    }

    public String getId() {
        return id;
    }

    public NamespacedKey makeNamespacedKey() {
        return new NamespacedKey(Main.getMain(), getId());
    }

    public static void rool(ItemStack item, ItemManager manager) {
        Collection<Attribute> atr = registeredAttributes.values();
        ArrayList<Attribute> attrebuteList = new ArrayList<>(atr);
        Collections.shuffle(attrebuteList);
        applyAttrebute(new Attribute[]{attrebuteList.get(0), attrebuteList.get(1)}, new int[]{1, 1}, item);
    }

    public static void applyAttrebute(Attribute[] attribute, int[] levels, ItemStack item) {
        Assert.state(attribute.length == levels.length);
        Assert.notNull(attribute, "Attribute canot be null");
        Assert.isTrue(attribute.length > 0 && attribute.length < 3, "Slot is out of range");
        Assert.notNull(item, "item canot be null!");
        PersistentDataContainer container = new CraftPersistentDataContainer(new CraftPersistentDataTypeRegistry());
        int i = 0;
        for (Attribute a : attribute) {
            container.set(a.makeNamespacedKey(), INTEGER, levels[i]);
            i++;
        }
        ItemHandler.setPDC("attributes", item, TAG_CONTAINER, container);
    }

    public static List<AppliedAttribute> getAttributes(ItemStack item) {
        Assert.notNull(item, "item canot be null");
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, STRING));
        Assert.isTrue(manager.isAttributable(), "manager has to be attribuatble!");
        List<AppliedAttribute> attributes = new ArrayList<>();
        PersistentDataContainer container = ItemHandler.getOrDefaultPDC("attributes", item, TAG_CONTAINER, new CraftPersistentDataContainer(new CraftPersistentDataTypeRegistry()));
        for (NamespacedKey s : container.getKeys()) {
            String id = s.getKey();
            attributes.add(new AppliedAttribute(registeredAttributes.get(id), container.get(s, INTEGER)));
        }
        return attributes;
    }
}
