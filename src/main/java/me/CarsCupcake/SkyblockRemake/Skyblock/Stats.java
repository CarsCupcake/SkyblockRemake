package me.CarsCupcake.SkyblockRemake.Skyblock;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.*;

public enum Stats {
    Health("health", '❤', ChatColor.RED, "Health", false, HotPotatoBookStat.Armor, 4),
    Defense("def", '❈', ChatColor.GREEN, "Defense", false, HotPotatoBookStat.Armor, 2),
    Inteligence("mana", '✎', ChatColor.AQUA, "Inteligence", false, null, 0),
    Speed("speed", '✦', ChatColor.WHITE, "Speed", false, null, 0),
    Strength("strength", '❁', ChatColor.RED, "Strength", true, HotPotatoBookStat.Sword, 2),
    CritDamage("cd", '☠', ChatColor.BLUE, "Crit Damage", true, null, 0),
    CritChance("cc", '☣', ChatColor.BLUE, "Crit Chance", true, null, 0),
    AbilityDamage("abilitydamage", '๑', ChatColor.RED, "Ability Damage", true, null, 0),
    Ferocity("ferocity", '⫽', ChatColor.RED, "Ferocity", true, null, 0),
    MagicFind("magicfind", '✯', ChatColor.AQUA, "Magic Find", false, null, 0),
    MiningSpeed("miningspeed", '⸕', ChatColor.GOLD, "Mining Speed", false, null, 0),
    MiningFortune("miningfortune", '☘', ChatColor.GOLD, "Mining Fortune", false, null, 0),
    Pristine("pristine", '✧', ChatColor.DARK_PURPLE, "Pristine", false, null, 0),
    AttackSpeed("as", '⚔', ChatColor.YELLOW, "Attack Speed", true, null, 0),
    TrueDefense("truedefense", '❂', ChatColor.WHITE, "True Defense", false, null, 0),
    SeaCreatureChance("seacreaturechance", 'α', ChatColor.DARK_AQUA, "Sea Creature Chance", false, null, 0),
    FishingSpeed("fishingspeed", '☂', ChatColor.AQUA, "Fishing Speed", false, null, 0),
    SwingRange("swingrange", ' ', ChatColor.GOLD, "Swing Range", true, null, 0),
    BreakingPower("breakingpower", '℗', ChatColor.DARK_GREEN, "Breaking Power", false, null, 0),
    /**
     * Deprecates cause you should not use it!
     */
    @Deprecated
    WeaponDamage("dmg", ' ', ChatColor.RED, "Damage", true, HotPotatoBookStat.Sword, 2);
    public static final List<Stats> statItemDisplayOrder = List.of(WeaponDamage, Strength, CritChance, CritDamage, AttackSpeed, AbilityDamage, SwingRange, Health, Defense, Inteligence,
            MagicFind, Ferocity, MiningSpeed, Pristine, MiningFortune, SeaCreatureChance, FishingSpeed);

    private final String dataName;
    private final char symbol;
    private final ChatColor color;
    private final String name;
    @Getter
    private final boolean agressive;
    private final HotPotatoBookStat hotPotatoBookStat;
    private final int hotPotatoBookStatBoost;
    Stats(String dataName, char symbol, ChatColor color, String name, boolean isAggresive, @Nullable HotPotatoBookStat hotPotatoBookStat, int hotPotatoBookStatBoost){
        this.dataName = dataName;
        this.symbol = symbol;
        this.color = color;
        this.name = name;
        agressive = isAggresive;
        this.hotPotatoBookStat = hotPotatoBookStat;
        this.hotPotatoBookStatBoost = hotPotatoBookStatBoost;
    }
    public NamespacedKey getKey(){
        return new NamespacedKey(Main.getMain(), getDataName());
    }
    public static Stats getFromDataName(String data){
        for(Stats s : Stats.values())
            if(s.getDataName().equals(data))
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
    public static ArrayList<String> makeItemStatsLore(ItemStack item, ArrayList<String> lore, SkyblockPlayer player){
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        ItemRarity rarity = ItemRarity.valueOf(ItemHandler.getPDC("rarity", item, PersistentDataType.STRING));
        HashMap<Stats, Integer> gemstoneSlots = new HashMap<>();

        for (GemstoneSlot s : GemstoneSlot.getCurrGemstones(manager,
                item.getItemMeta().getPersistentDataContainer())) {
            if(s.currGem != null)
                gemstoneSlots.put(s.currGem.gemType.getStat(), s.currGem.getStatBoost(rarity));
        }

        for (Stats stat : statItemDisplayOrder){
            double value;
            if(stat != WeaponDamage)
                value = Main.getItemStat(player, stat, item);
            else {
                if(ItemHandler.hasPDC("dmg", item, PersistentDataType.STRING))
                    value = Main.weapondamage(item);
                else
                    if(ItemHandler.hasPDC("dmg", item, PersistentDataType.DOUBLE))
                        value = Main.getItemStat(player, stat, item);
                    else continue;
            }
            if(value == 0)
                continue;
            String row = "§7" + stat.name + " " + ((stat.agressive) ? "§c" : "§a") + ((value < 0) ? "-" : "+") + String.format("%.0f", value);

            if(stat.hotPotatoBookStat != null && ItemHandler.getOrDefaultPDC("potatobooks", item, PersistentDataType.INTEGER, 0) > 0 && stat.hotPotatoBookStat.types.contains(manager.type)){
                row += " §e(+" + (ItemHandler.getPDC("potatobooks", item, PersistentDataType.INTEGER) * stat.hotPotatoBookStatBoost) + ")";
            }

            if(gemstoneSlots.containsKey(stat)){
                int v = gemstoneSlots.get(stat);
                if (v > 0)
                    row += " §d(+" + v + ")";
            }

            lore.add(row);
        }
        return lore;
    }
    private enum HotPotatoBookStat{
        Sword(getSwordsTypes()),
        Armor(Set.of(ItemType.Helmet, ItemType.Chestplate, ItemType.Leggings, ItemType.Boots));
        private final Set<ItemType> types;
        HotPotatoBookStat(Set<ItemType> types) {
            this.types = types;
        }
        static Set<ItemType> getSwordsTypes(){
            Set<ItemType> types = new HashSet<>(List.of(ItemType.values()));
            types.remove(ItemType.Helmet);
            types.remove(ItemType.Chestplate);
            types.remove(ItemType.Leggings);
            types.remove(ItemType.Boots);
            return types;
        }
    }

}
