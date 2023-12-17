package me.CarsCupcake.SkyblockRemake.Skyblock;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Items.reforges.RegisteredReforges;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.maps.CountMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;

@Getter
public enum Stats {
    Health("health", '❤', ChatColor.RED, "Health", false, HotPotatoBookStat.Armor, 4, 100),
    Defense("def", '❈', ChatColor.GREEN, "Defense", false, HotPotatoBookStat.Armor, 2),
    Inteligence("mana", '✎', ChatColor.AQUA, "Inteligence", false, 100),
    Speed("speed", '✦', ChatColor.WHITE, "Speed", false, 100, 500),
    Strength("strength", '❁', ChatColor.RED, "Strength", true, HotPotatoBookStat.Sword, 2),
    CritDamage("cd", '☠', ChatColor.BLUE, "Crit Damage", true, null, 0, 50),
    CritChance("cc", '☣', ChatColor.BLUE, "Crit Chance", true,30),
    AbilityDamage("abilitydamage", '๑', ChatColor.RED, "Ability Damage", true),
    Ferocity("ferocity", '⫽', ChatColor.RED, "Ferocity", false),
    MagicFind("magicfind", '✯', ChatColor.AQUA, "Magic Find", false),
    PetLuck("petluck", '♣', ChatColor.LIGHT_PURPLE, "Pet Luck", false),
    MiningSpeed("miningspeed", '⸕', ChatColor.GOLD, "Mining Speed", false),
    MiningFortune("miningfortune", '☘', ChatColor.GOLD, "Mining Fortune", false),
    ForagingFortune("foragingfortune", '☘', ChatColor.GOLD, "Foraging Fortune", false),
    Pristine("pristine", '✧', ChatColor.DARK_PURPLE, "Pristine", false),
    AttackSpeed("as", '⚔', ChatColor.YELLOW, "Attack Speed", true),
    TrueDefense("truedefense", '❂', ChatColor.WHITE, "True Defense", false),
    SeaCreatureChance("seacreaturechance", 'α', ChatColor.DARK_AQUA, "Sea Creature Chance", false, 20, 100),
    FishingSpeed("fishingspeed", '☂', ChatColor.AQUA, "Fishing Speed", false, null, 0),
    SwingRange("swingrange", 'Ⓢ', ChatColor.GOLD, "Swing Range", true,3, -1),
    BreakingPower("breakingpower", '℗', ChatColor.DARK_GREEN, "Breaking Power", false),
    FarmingFortune("farmingfortune", '☘', ChatColor.GOLD, "Farming Fortune", false),
    CombatWisdom("combatwisdom", '☯', ChatColor.DARK_AQUA, "Combat Wisdom", false),
    MiningWisdom("miningwisdom", '☯', ChatColor.DARK_AQUA, "Mining Wisdom", false),
    FarmingWisdom("farmingwisdom", '☯', ChatColor.DARK_AQUA, "Farming Wisdom", false),
    ForagingWisdom("foragingwisdom", '☯', ChatColor.DARK_AQUA, "Foraging Wisdom", false),
    FishingWisdom("fishingwisdom", '☯', ChatColor.DARK_AQUA, "Fishing Wisdom", false),
    EnchantingWisdom("enchantingwisdom", '☯', ChatColor.DARK_AQUA, "Enchanting Wisdom", false),
    AlchemyWisdom("alchemywisdom", '☯', ChatColor.DARK_AQUA, "Alchemy Wisdom", false),
    CarpentryWisdom("carpentrywisdom", '☯', ChatColor.DARK_AQUA, "Carpentry Wisdom", false),
    RunecraftingWisdom("runecraftingwisdom", '☯', ChatColor.DARK_AQUA, "Runecrafting Wisdom", false),
    SocialWisdom("socialwisdom", '☯', ChatColor.DARK_AQUA, "Social Wisdom", false),
    HealthRegen("healthregen", '❣', ChatColor.RED, "Health Regen", false),
    RiftTime("rifttime", 'ф', ChatColor.GREEN, "Rift Time", false, 480, -1, true),
    RiftInteligence("rmana", '✎', ChatColor.AQUA, "Inteligence", false, 100, -1, true),
    ManaRegen("manaregen", '⚡', ChatColor.AQUA, "Mana Regen", false, 0, -1, true),
    RiftSpeed("rspeed", '✦', ChatColor.WHITE, "Speed", false, 100, 500, true),
    Hearts("hearts", '❤', ChatColor.RED, "Hearts", false, 10, -1, true),
    RiftDamage("rdamage", '❁', ChatColor.DARK_PURPLE, "Rift Damage", true, 20, -1, true),
    WeaponDamage("dmg", ' ', ChatColor.RED, "Damage", true, HotPotatoBookStat.Sword, 2),
    Vitality("vitality", '♨', ChatColor.RED, "Vitality", false),
    Mending("mending", '☄', ChatColor.GREEN, "Mending", false);
    public static final List<Stats> statItemDisplayOrder = List.of(WeaponDamage, Strength, CritChance, CritDamage, AttackSpeed, AbilityDamage, SwingRange, Health, Defense, Speed, Inteligence,
            MagicFind, PetLuck, TrueDefense, Ferocity, MiningSpeed, Pristine, MiningFortune, FarmingFortune, SeaCreatureChance, FishingSpeed, FarmingWisdom, HealthRegen, Vitality, Mending);
    public static final List<Stats> riftStatItemDisplayOrder = List.of(RiftTime, RiftDamage, Hearts, RiftInteligence, RiftSpeed, ManaRegen);
    private final String dataName;
    public final char symbol;
    private final ChatColor color;
    private final String name;
    private final boolean agressive;
    private final HotPotatoBookStat hotPotatoBookStat;
    private final int hotPotatoBookStatBoost;
    private final double baseAmount;
    private final double maxAmount;
    private final boolean inRift;
    Stats(String dataName, char symbol, @NotNull ChatColor color, String name, boolean isAggresive) {
        this(dataName, symbol, color, name, isAggresive, null, 0);
    }
    Stats(String dataName, char symbol, @NotNull ChatColor color, String name, boolean isAggresive, double baseAmount) {
        this(dataName, symbol, color, name, isAggresive, null, 0, baseAmount, -1);
    }
    Stats(String dataName, char symbol, @NotNull ChatColor color, String name, boolean isAggresive, double baseAmount, double maxAmount) {
        this(dataName, symbol, color, name, isAggresive, null, 0, baseAmount, maxAmount);
    }
    Stats(String dataName, char symbol, @NotNull ChatColor color, String name, boolean isAggresive, @Nullable HotPotatoBookStat hotPotatoBookStat, int hotPotatoBookStatBoost) {
        this(dataName, symbol, color, name, isAggresive, hotPotatoBookStat, hotPotatoBookStatBoost, 0, -1);
    }

    Stats(String dataName, char symbol, @NotNull ChatColor color, String name, boolean isAggresive, @Nullable HotPotatoBookStat hotPotatoBookStat, int hotPotatoBookStatBoost, double baseAmount) {
        this(dataName, symbol, color, name, isAggresive, hotPotatoBookStat, hotPotatoBookStatBoost, baseAmount, -1);
    }

    Stats(String dataName, char symbol, @NotNull ChatColor color, String name, boolean isAggresive, @Nullable HotPotatoBookStat hotPotatoBookStat, int hotPotatoBookStatBoost, double baseAmount, double maxAmount) {
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

    public String toString() {
        return color.toString() + symbol + " " + name;
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

    public static void makeItemStatsLore(ItemStack item, ArrayList<String> lore, SkyblockPlayer player) {
        ItemManager manager = Items.SkyblockItems.get(ItemHandler.getPDC("id", item, PersistentDataType.STRING));
        ItemRarity rarity = manager.getRarity(item, player);
        CountMap<Stats> gemstoneSlots = new CountMap<>();

        for (GemstoneSlot s : GemstoneSlot.getCurrGemstones(manager,
                item.getItemMeta().getPersistentDataContainer())) {
            if (s.currGem != null)
                gemstoneSlots.add(s.currGem.gemType.getStat(), s.currGem.getStatBoost(rarity));
        }
        for (Stats stat : ((ServerType.getActiveType() == ServerType.Rift) ? riftStatItemDisplayOrder : statItemDisplayOrder)) {
            double value;
            value = Main.getItemStat(player, stat, item);
            if (value == 0)
                continue;
            String row = "§7" + stat.name + " " + ((stat.agressive) ? "§c" : "§a") + ((value == Double.MAX_VALUE) ? "∞" : (((value < 0) ? "-" : "+") + String.format("%.0f", value)));

            if (stat.hotPotatoBookStat != null && ItemHandler.getOrDefaultPDC("potatobooks", item, PersistentDataType.INTEGER, 0) > 0 && stat.hotPotatoBookStat.types.contains(manager.type)) {
                row += " §e(+" + (ItemHandler.getPDC("potatobooks", item, PersistentDataType.INTEGER) * stat.hotPotatoBookStatBoost) + ")";
            }

            if (ItemHandler.hasPDC("reforge", item, PersistentDataType.STRING)) {
                double v = RegisteredReforges.reforges.get(ItemHandler.getPDC("reforge", item, PersistentDataType.STRING)).getReforgeValue(manager.getRarity(item, player), stat);
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
    }

    public enum HotPotatoBookStat {
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
        public boolean contains(ItemType type) {
            return types.contains(type);
        }
    }

    public ItemBuilder itemPreviewBuilder() {
        return switch (this) {
            case WeaponDamage -> new ItemBuilder(Material.IRON_SWORD);
            case Speed, RiftSpeed -> new ItemBuilder(Material.SUGAR);
            case Health -> new ItemBuilder(Material.GOLDEN_APPLE);
            case Hearts -> new ItemBuilder(Material.APPLE);
            case RiftTime -> new ItemBuilder(Material.CLOCK);
            case RiftDamage -> new ItemBuilder(Material.DIAMOND_SWORD);
            case RiftInteligence, Inteligence -> new ItemBuilder(Material.ENCHANTED_BOOK);
            case MagicFind -> new ItemBuilder(Material.STICK);
            case Defense -> new ItemBuilder(Material.IRON_CHESTPLATE);
            case Strength -> new ItemBuilder(Material.BLAZE_POWDER);
            case CritChance -> new ItemBuilder(Material.PLAYER_HEAD).setHead("http://textures.minecraft.net/texture/3e4f49535a276aacc4dc84133bfe81be5f2a4799a4c04d9a4ddb72d819ec2b2b");
            case CritDamage -> new ItemBuilder(Material.PLAYER_HEAD).setHead("http://textures.minecraft.net/texture/5169c90c8874ab575b201b616a69eac7e0b5ac69bbcccbb2772e36776fe69441");
            case AttackSpeed -> new ItemBuilder(Material.GOLDEN_AXE);
            case AbilityDamage -> new ItemBuilder(Material.BEACON);
            case TrueDefense -> new ItemBuilder(Material.BONE_MEAL);
            case Ferocity -> new ItemBuilder(Material.RED_DYE);
            case HealthRegen -> new ItemBuilder(Material.POTION);
            case Vitality -> new ItemBuilder(Material.GLISTERING_MELON_SLICE);
            case Mending -> new ItemBuilder(Material.GHAST_TEAR);
            case MiningSpeed -> new ItemBuilder(Material.DIAMOND_PICKAXE);
            case MiningFortune -> new ItemBuilder(Material.PLAYER_HEAD).setHead("http://textures.minecraft.net/texture/b73579575ca88b3a8afe1ed18907b3125fe0987b02a88ef0e8a01087c3d024c4");
            case FarmingFortune -> new ItemBuilder(Material.PLAYER_HEAD).setHead("http://textures.minecraft.net/texture/220ee7741ff1b958dbb9fa7cddad9c3cce93373f470f9b834da02da67c8202a4");
            case ForagingFortune -> new ItemBuilder(Material.PLAYER_HEAD).setHead("http://textures.minecraft.net/texture/4e44e2a8dff90f5b005e76e6f5db7c12ae59cbbc56d8bc8050f3e3dbf0c3b734");
            case BreakingPower -> new ItemBuilder(Material.GOLDEN_PICKAXE);
            case Pristine -> new ItemBuilder(Material.PLAYER_HEAD).setHead("http://textures.minecraft.net/texture/d886e0f41185b18a3afd89488d2ee4caa0735009247cccf039ced6aed752ff1a");
            case SeaCreatureChance -> new ItemBuilder(Material.PRISMARINE);
            case FishingSpeed -> new ItemBuilder(Material.FISHING_ROD);
            case CombatWisdom, AlchemyWisdom, CarpentryWisdom, EnchantingWisdom, FarmingWisdom, FishingWisdom, ForagingWisdom, MiningWisdom, RunecraftingWisdom, SocialWisdom
                    -> new ItemBuilder(Material.WRITABLE_BOOK);
            case ManaRegen -> new ItemBuilder(Material.DIAMOND);
            case SwingRange -> new ItemBuilder(Material.GOLDEN_SWORD);
            case PetLuck -> new ItemBuilder(Material.LILY_PAD);
        };
    }

}
