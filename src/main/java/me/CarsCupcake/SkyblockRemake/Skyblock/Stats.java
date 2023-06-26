package me.CarsCupcake.SkyblockRemake.Skyblock;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Items.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.Items.reforges.RegisteredReforges;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.*;

public enum Stats {
    Health("health", '❤', ChatColor.RED, "Health", false, HotPotatoBookStat.Armor, 4, 100),
    Defense("def", '❈', ChatColor.GREEN, "Defense", false, HotPotatoBookStat.Armor, 2),
    Inteligence("mana", '✎', ChatColor.AQUA, "Inteligence", false, null, 0, 100),
    Speed("speed", '✦', ChatColor.WHITE, "Speed", false, null, 0, 100, 500),
    Strength("strength", '❁', ChatColor.RED, "Strength", true, HotPotatoBookStat.Sword, 2),
    CritDamage("cd", '☠', ChatColor.BLUE, "Crit Damage", true, null, 0, 50),
    CritChance("cc", '☣', ChatColor.BLUE, "Crit Chance", true, null, 0, 30),
    AbilityDamage("abilitydamage", '๑', ChatColor.RED, "Ability Damage", true, null, 0),
    Ferocity("ferocity", '⫽', ChatColor.RED, "Ferocity", false, null, 0),
    MagicFind("magicfind", '✯', ChatColor.AQUA, "Magic Find", false, null, 0),
    MiningSpeed("miningspeed", '⸕', ChatColor.GOLD, "Mining Speed", false, null, 0),
    MiningFortune("miningfortune", '☘', ChatColor.GOLD, "Mining Fortune", false, null, 0),
    Pristine("pristine", '✧', ChatColor.DARK_PURPLE, "Pristine", false, null, 0),
    AttackSpeed("as", '⚔', ChatColor.YELLOW, "Attack Speed", true, null, 0),
    TrueDefense("truedefense", '❂', ChatColor.WHITE, "True Defense", false, null, 0),
    SeaCreatureChance("seacreaturechance", 'α', ChatColor.DARK_AQUA, "Sea Creature Chance", false, null, 0, 20, 100),
    FishingSpeed("fishingspeed", '☂', ChatColor.AQUA, "Fishing Speed", false, null, 0),
    SwingRange("swingrange", ' ', ChatColor.GOLD, "Swing Range", true, null, 0),
    BreakingPower("breakingpower", '℗', ChatColor.DARK_GREEN, "Breaking Power", false, null, 0),
    FarmingFortune("farmingfortune", '☘', ChatColor.GOLD, "Farming Fortune", false, null, 0),
    FarmingWisdom("farmingwisdom", '☯', ChatColor.DARK_AQUA, "Farming Wisdom", false, null, 0),
    HealthRegen("healthregen", '❣', ChatColor.RED, "Health Regen", false, null, 0),
    RiftTime("rifttime", 'ф', ChatColor.GREEN, "Rift Time", false, 480, -1, true),
    RiftIntelligence("rmana", '✎', ChatColor.AQUA, "Intelligence", false, 100, -1, true),
    ManaRegen("manaregen", '⚡', ChatColor.AQUA, "Mana Regen", false, 0, -1, true),
    RiftSpeed("rspeed", '✦', ChatColor.WHITE, "Speed", false, 100, 500, true),
    Hearts("hearts", '❤', ChatColor.RED, "Hearts", false, 10, -1, true),
    RiftDamage("rdamage", '❁', ChatColor.DARK_PURPLE, "Rift Damage", true, 20, -1, true),
    WeaponDamage("dmg", ' ', ChatColor.RED, "Damage", true, HotPotatoBookStat.Sword, 2);
    public static final List<Stats> statItemDisplayOrder = List.of(WeaponDamage, Strength, CritChance, CritDamage, AttackSpeed, AbilityDamage, SwingRange, Health, Defense, Inteligence,
            MagicFind, Ferocity, MiningSpeed, Pristine, MiningFortune, FarmingFortune, SeaCreatureChance, FishingSpeed, FarmingWisdom);
    public static final List<Stats> riftStatItemDisplayOrder = List.of(RiftTime, RiftDamage, Hearts, Inteligence, Speed, RiftIntelligence, ManaRegen);
    private final String dataName;
    private final char symbol;
    private final ChatColor color;
    private final String name;
    @Getter
    private final boolean agressive;
    private final HotPotatoBookStat hotPotatoBookStat;
    private final int hotPotatoBookStatBoost;
    @Getter
    private final double baseAmount;
    @Getter
    private final double maxAmount;
    @Getter
    private final boolean inRift;

    Stats(String dataName, char symbol, ChatColor color, String name, boolean isAggresive, @Nullable HotPotatoBookStat hotPotatoBookStat, int hotPotatoBookStatBoost) {
        this(dataName, symbol, color, name, isAggresive, hotPotatoBookStat, hotPotatoBookStatBoost, 0, -1);
    }

    Stats(String dataName, char symbol, ChatColor color, String name, boolean isAggresive, @Nullable HotPotatoBookStat hotPotatoBookStat, int hotPotatoBookStatBoost, double baseAmount) {
        this(dataName, symbol, color, name, isAggresive, hotPotatoBookStat, hotPotatoBookStatBoost, baseAmount, -1);
    }

    Stats(String dataName, char symbol, ChatColor color, String name, boolean isAggresive, @Nullable HotPotatoBookStat hotPotatoBookStat, int hotPotatoBookStatBoost, double baseAmount, double maxAmount) {
        this.dataName = dataName;
        this.symbol = symbol;
        this.color = color;
        this.name = name;
        agressive = isAggresive;
        this.hotPotatoBookStat = hotPotatoBookStat;
        this.hotPotatoBookStatBoost = hotPotatoBookStatBoost;
        this.baseAmount = baseAmount;
        this.maxAmount = maxAmount;
        inRift = false;
    }

    Stats(String dataName, char symbol, ChatColor color, String name, boolean isAggresive, int baseAmount, int maxAmount, boolean isInRift) {
        this.dataName = dataName;
        this.symbol = symbol;
        this.color = color;
        this.name = name;
        hotPotatoBookStat = null;
        this.baseAmount = baseAmount;
        hotPotatoBookStatBoost = 0;
        this.maxAmount = maxAmount;
        this.inRift = isInRift;
        this.agressive = isAggresive;
    }

    public NamespacedKey getKey() {
        return new NamespacedKey(Main.getMain(), getDataName());
    }

    public static Stats getFromDataName(String data) {
        for (Stats s : Stats.values())
            if (s.getDataName().equals(data))
                return s;
        throw new IndexOutOfBoundsException("There is no stat with the id: " + data);
    }

    public String getDataName() {
        return this.dataName;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public static ArrayList<String> makeItemStatsLore(ItemStack item, ArrayList<String> lore, SkyblockPlayer player) {
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        ItemRarity rarity = ItemRarity.valueOf(ItemHandler.getPDC("rarity", item, PersistentDataType.STRING));
        HashMap<Stats, Integer> gemstoneSlots = new HashMap<>();

        for (GemstoneSlot s : GemstoneSlot.getCurrGemstones(manager,
                item.getItemMeta().getPersistentDataContainer())) {
            if (s.currGem != null)
                gemstoneSlots.put(s.currGem.gemType.getStat(), s.currGem.getStatBoost(rarity));
        }
        for (Stats stat : ((ServerType.getActiveType() == ServerType.Rift) ? riftStatItemDisplayOrder : statItemDisplayOrder)) {
            double value;
            if (stat != WeaponDamage)
                value = Main.getItemStat(player, stat, item);
            else {
                if (ItemHandler.hasPDC("dmg", item, PersistentDataType.STRING))
                    value = Main.weapondamage(item);
                else if (ItemHandler.hasPDC("dmg", item, PersistentDataType.DOUBLE))
                    value = Main.getItemStat(player, stat, item);

                else continue;
            }
            if (value == 0)
                continue;
            String row = "§7" + stat.name + " " + ((stat.agressive) ? "§c" : "§a") + ((value == Double.MAX_VALUE) ? "∞" : (((value < 0) ? "-" : "+") + String.format("%.0f", value)));

            if (stat.hotPotatoBookStat != null && ItemHandler.getOrDefaultPDC("potatobooks", item, PersistentDataType.INTEGER, 0) > 0 && stat.hotPotatoBookStat.types.contains(manager.type)) {
                row += " §e(+" + (ItemHandler.getPDC("potatobooks", item, PersistentDataType.INTEGER) * stat.hotPotatoBookStatBoost) + ")";
            }

            if (ItemHandler.hasPDC("reforge", item, PersistentDataType.STRING)) {
                double v = Reforge.getReforgeValue(RegisteredReforges.reforges.get(ItemHandler.getPDC("reforge", item, PersistentDataType.STRING)), manager.getRarity(rarity, item, player), stat.getDataName());
                if (v != 0)
                    row += " §9(+" + ((v % 1 == 0) ? String.format("%.0f", v) : v) + ")";
            }

            if (gemstoneSlots.containsKey(stat)) {
                int v = gemstoneSlots.get(stat);
                if (v > 0)
                    row += " §d(+" + v + ")";
            }

            lore.add(row);
        }
        return lore;
    }

    private enum HotPotatoBookStat {
        Sword(getSwordsTypes()),
        Armor(Set.of(ItemType.Helmet, ItemType.Chestplate, ItemType.Leggings, ItemType.Boots));
        private final Set<ItemType> types;

        HotPotatoBookStat(Set<ItemType> types) {
            this.types = types;
        }

        static Set<ItemType> getSwordsTypes() {
            Set<ItemType> types = new HashSet<>(List.of(ItemType.values()));
            types.remove(ItemType.Helmet);
            types.remove(ItemType.Chestplate);
            types.remove(ItemType.Leggings);
            types.remove(ItemType.Boots);
            return types;
        }
    }

}
