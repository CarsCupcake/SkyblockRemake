package me.CarsCupcake.SkyblockRemake.Items.attributes;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.abilities.ABILITIES;
import me.CarsCupcake.SkyblockRemake.utils.Factory;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_17_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_17_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

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
        if (atribute instanceof Listener listener) {
            ABILITIES.registerEvent(listener);
        }
    }

    public abstract String name();

    public abstract int maxLevel();

    public abstract boolean isAllowed(SkyblockPlayer player);

    public abstract List<String> lore(int level);

    public abstract Type[] allowed();

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
        List<Attribute> attrebuteList = atr.stream().filter(attribute -> {
            for (Type type : attribute.allowed()) if (type.factor(manager)) return true;
            return false;
        }).toList();
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
        if (item == null) return new ArrayList<>();
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, STRING));
        if (manager == null) return new ArrayList<>();
        if (!manager.isAttributable()) return new ArrayList<>();
        List<AppliedAttribute> attributes = new ArrayList<>();
        PersistentDataContainer container = ItemHandler.getOrDefaultPDC("attributes", item, TAG_CONTAINER, new CraftPersistentDataContainer(new CraftPersistentDataTypeRegistry()));
        for (NamespacedKey s : container.getKeys()) {
            String id = s.getKey();
            attributes.add(new AppliedAttribute(registeredAttributes.get(id), container.get(s, INTEGER)));
        }
        return attributes;
    }
    public static Attribute getAttribute(String s) {
        return registeredAttributes.get(s);
    }
    public static Set<String> getAttributeIds() {
        return registeredAttributes.keySet();
    }
    public enum Type implements Factory<ItemManager, Boolean> {
        Weapons {
            @Override
            public Boolean factor(ItemManager obj) {
                return obj.type == ItemType.Sword || obj.type == ItemType.Longsword || obj.type == ItemType.Bow;
            }
        },
        Swords {
            @Override
            public Boolean factor(ItemManager obj) {
                return obj.type == ItemType.Sword || obj.type == ItemType.Longsword;
            }
        },
        Bows {
            @Override
            public Boolean factor(ItemManager obj) {
                return obj.type == ItemType.Bow;
            }
        },
        Armor {
            @Override
            public Boolean factor(ItemManager obj) {
                return obj.type == ItemType.Helmet || obj.type == ItemType.Chestplate || obj.type == ItemType.Leggings || obj.type == ItemType.Boots;
            }
        },
        Equipment {
            @Override
            public Boolean factor(ItemManager obj) {
                return obj.type == ItemType.Belt || obj.type == ItemType.Necklace || obj.type == ItemType.Bracelet || obj.type == ItemType.Cloak || obj.type == ItemType.Gloves;
            }
        },
        FishingArmor {
            static String[] allowed = {"TAURUS_HELMET", "FLAMING_CHESTPLATE", "MOOGMA_LEGGINGS", "SLUG_BOOTS",
                    "THUNDER_HELMET", "THUNDER_CHESTPLATE", "THUNDER_LEGGINGS", "THUNDER_BOOTS",
            "MAGMA_LORD_HELMET", "MAGMA_LORD_CHESTPLATE", "MAGMA_LORD_LEGGINGS", "MAGMA_LORD_BOOTS"};
            @Override
            public Boolean factor(ItemManager obj) {
                for (String s : allowed) if (obj.itemID.equals(s)) return true;
                return false;
            }
        },
        FishingEquipment {
            static String[] allowed = {"THUNDERBOLT_NECKLACE", "MAGMA_LORD_GAUNTLET"};
            @Override
            public Boolean factor(ItemManager obj) {
                for (String s : allowed) if (obj.itemID.equals(s)) return true;
                return false;
            }
        },
        FishingRods {
            @Override
            public Boolean factor(ItemManager obj) {
                return obj.type == ItemType.FishingRod;
            }
        },
        All {
            @Override
            public Boolean factor(ItemManager obj) {
                return true;
            }
        }
    }
}
