package me.CarsCupcake.SkyblockRemake.Items;

import java.text.SimpleDateFormat;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.*;
import me.CarsCupcake.SkyblockRemake.FishingSystem.RodType;
import me.CarsCupcake.SkyblockRemake.Items.Attributes.Attribute;
import me.CarsCupcake.SkyblockRemake.Items.requirements.Requirement;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.ArtifactAbility;
import me.CarsCupcake.SkyblockRemake.utils.NBTEditor;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.banner.Pattern;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Configs.EditionItems;
import me.CarsCupcake.SkyblockRemake.Items.Gemstones.Gemstone;
import me.CarsCupcake.SkyblockRemake.Items.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.Pets.Pet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemManager implements ItemGenerator {
    public static String pattern = "MMMMMMMMM yyyy";
    public static SimpleDateFormat df = new SimpleDateFormat(pattern);
    public String name;
    @Setter
    public ArrayList<String> lore = new ArrayList<>();
    public ArrayList<GemstoneSlot> gemstoneSlots = new ArrayList<>();
    public double dmg;
    @Setter
    public double breakingpower;
    public String itemID;
    public ItemType type;
    public Material material;
    public boolean isHead;
    public String headTexture;
    private boolean isSkullValue = false;
    public UUID customUUID;
    public int baseabilitydamage;
    public HashMap<Enchantment, Integer> enchants = new HashMap<>();
    public HashMap<String, String> customDataContainer = new HashMap<>();
    public HashMap<String, Integer> customIntContainer = new HashMap<>();
    public HashMap<String, Double> customDoubleContainer = new HashMap<>();
    private ItemRarity rarity;
    public Color color;
    public boolean isBaseItem = false;

    public Bonuses bonus;
    public boolean TieredBonus = false;
    public boolean hasEdition = false;
    public double catchMult = 1;
    public boolean isDungeonItem = false;
    private boolean isUnstackeble = false;
    private int npcSellPrice;
    private ArrayList<Pattern> bannerPattern = new ArrayList<>();
    private EquipmentAbility startStopAbility = null;
    private HashMap<Stats, Double> stats = new HashMap<>();
    private double trophyFishChance = 0;
    private RodType rodType = RodType.Normal;
    @Getter
    private final Set<me.CarsCupcake.SkyblockRemake.Items.ItemFlag> flags = new HashSet<>();
    @Getter
    @Setter
    private MaterialGrabber materialGrabber;
    @Getter
    @Setter
    private boolean attributable = false;
    @Getter
    @Setter
    private int maxStars = 0;
    @Getter
    private ArrayList<Ability> abilities = new ArrayList<>();
    @Getter
    private int fullSetBonusPointer = -1;
    @Getter
    private Ability equipmentAbilityData;
    @Getter
    @Setter
    private SpecialRarityGrabber rarityGrabber = new SpecialRarityGrabber() {
        @Override
        public ItemRarity getRarity(@NotNull ItemRarity rarity, @NotNull ItemStack item, @Nullable SkyblockPlayer player) {
            return rarity;
        }
    };
    @Getter
    @Setter
    private Requirement requirement = (player, item) -> true;
    @Getter
    @Setter
    private ArtifactAbility artifactAbility;

    public ItemManager(String name, String itemID, ItemType itemType, ArrayList<String> lore, String abilityName, String abilityID, ArrayList<String> abilityLore, double abilityManaCost, int abilityCD, float abilitymultiplyer, int baseabilitydamage, Material material, ItemRarity rarity) {
        this.name = name;
        this.itemID = itemID;
        this.type = itemType;
        this.lore = lore;
        this.baseabilitydamage = baseabilitydamage;
        this.material = material;
        this.rarity = rarity;
        isHead = false;
    }

    public ItemManager(String name, String itemID, ItemType itemType, Material material, ItemRarity rarity) {
        this.name = name;
        this.itemID = itemID;
        this.type = itemType;
        this.material = material;
        this.rarity = rarity;
        Items.SkyblockItems.put(itemID, this);
    }

    public ItemManager(String name, String itemID, ItemType itemType, ArrayList<String> lore, String abilityName, String abilityID, ArrayList<String> abilityLore, double abilityManaCost, int abilityCD, float abilitymultiplyer, int baseabilitydamage, Material material, Color color, ItemRarity rarity) {
        this.name = name;
        this.itemID = itemID;
        this.type = itemType;
        this.lore = lore;
        this.baseabilitydamage = baseabilitydamage;
        this.material = material;
        this.rarity = rarity;
        this.color = color;
        isHead = false;
        enchants = new HashMap<>();
    }

    public ItemManager(String name, String itemID, ItemType itemType, Material material, Color color, ItemRarity rarity) {
        this.name = name;
        this.itemID = itemID;
        this.type = itemType;
        this.material = material;
        this.rarity = rarity;
        this.color = color;
        Items.SkyblockItems.put(itemID, this);
    }

    public ItemManager(String name, String itemID, ItemType itemType, ArrayList<String> lore, String abilityName, String abilityID, ArrayList<String> abilityLore, double abilityManaCost, int abilityCD, float abilitymultiplyer, int baseabilitydamage, ItemRarity rarity, String headTexture) {
        this.name = name;
        this.itemID = itemID;
        this.type = itemType;
        this.lore = lore;
        this.headTexture = headTexture;
        this.baseabilitydamage = baseabilitydamage;
        this.rarity = rarity;
        isHead = true;
        material = Material.PLAYER_HEAD;
        enchants = new HashMap<>();

    }

    public ItemManager(String name, String itemID, ItemType itemType, ItemRarity rarity, String headTexture) {
        this.name = name;
        this.itemID = itemID;
        this.type = itemType;
        material = Material.PLAYER_HEAD;
        this.rarity = rarity;
        this.headTexture = headTexture;
        if (itemType == ItemType.Helmet) setUnstackeble(true);
        isHead = true;
        Items.SkyblockItems.put(itemID, this);
    }

    public ItemManager(String name, String itemID, ItemType itemType, ArrayList<String> lore, String abilityName, String abilityID, ArrayList<String> abilityLore, double abilityManaCost, int abilityCD, float abilitymultiplyer, int baseabilitydamage, ItemRarity rarity, String headTexture, UUID CustomUUID) {

        this.name = name;
        this.itemID = itemID;
        this.type = itemType;
        this.lore = lore;
        this.headTexture = headTexture;
        this.customUUID = CustomUUID;
        this.baseabilitydamage = baseabilitydamage;
        this.rarity = rarity;
        isHead = true;
        material = Material.PLAYER_HEAD;
        enchants = new HashMap<>();


    }

    public ItemManager(String name, String itemID, ItemType itemType, ItemRarity rarity, String headTexture, UUID customId) {
        this.name = name;
        this.itemID = itemID;
        this.type = itemType;
        material = Material.PLAYER_HEAD;
        this.rarity = rarity;
        this.headTexture = headTexture;
        this.customUUID = customId;
        isHead = true;
        Items.SkyblockItems.put(itemID, this);
    }
    public ItemRarity getRarity(){
        return rarity;
    }
    public ItemRarity getRarity(@NotNull ItemStack item, @Nullable SkyblockPlayer player){
        return getRarity(rarity, item, player);
    }
    public ItemRarity getRarity(ItemRarity rarity ,@NotNull ItemStack item){
        return getRarity(rarity, item, null);
    }
    public ItemRarity getRarity(@NotNull ItemRarity rarity, @NotNull ItemStack item, @Nullable SkyblockPlayer player){
        return rarityGrabber.getRarity(rarity, item, player);
    }


    public void setEquipmentAbility(EquipmentAbility ability, String name) {
        setEquipmentAbility(ability, name, new AbilityLore(new ArrayList<>()));
    }

    public void setEquipmentAbility(EquipmentAbility ability, String name, AbilityLore lore) {
        startStopAbility = ability;
        equipmentAbilityData = new Ability(null, name, null, lore, 0, 0);
    }

    public EquipmentAbility getEquipmentAbility() {
        return startStopAbility;
    }

    public void setRodType(RodType type) {
        rodType = type;
    }

    public RodType getRodType() {
        return rodType;
    }


    public void addBannerPattern(Pattern pattern) {
        bannerPattern.add(pattern);
    }

    public ArrayList<Pattern> getBannerPatterns() {
        return bannerPattern;
    }

    public void setDungeonItem(boolean b) {
        isDungeonItem = b;
    }

    public void setEditions(boolean bol) {
        hasEdition = bol;
    }

    public String getAbilityHeadline(SkyblockPlayer player, int i) {
        Ability ability = abilities.get(i);

        if (ability.getType() == null) return "§6" + ability.getName();

        if (!TieredBonus)
            return "§6Ability: " + ability.getName() + " §e§l" + ability.getType().toString().toUpperCase();
        else {
            if (player == null || player.bonusAmounts.get(bonus) == null)
                return "§8Tiered Bonus: " + ability.getName() + " (0/" + bonus.getBonus(player).getMaxPieces() + ")";
            else
                return ((player.bonusAmounts.get(bonus) < bonus.getBonus(player).getPieces()) ? "§8" : "§6") + "Tiered Bonus: " + ability.getName() + " (" + player.bonusAmounts.get(bonus) + "/" + bonus.getBonus(player).getMaxPieces() + ")";
        }
    }

    public void setBaseItem() {
        isBaseItem = true;
    }

    public void setTrophyFishChance(double d) {
        trophyFishChance = d;
    }

    public double getTrophyFishChance() {
        return trophyFishChance;
    }

    public void setStat(Stats stat, double value) {
        stats.put(stat, value);
    }

    public double getStat(@NotNull Stats stat) {
        return stats.getOrDefault(stat, 0d);
    }

    public void setUnstackeble(boolean b) {
        isUnstackeble = b;
    }

    public boolean isUnstackeble() {
        return isUnstackeble;
    }

    public void setBreakingPower(int value) {
        breakingpower = value;
    }

    public void setDamage(double value) {
        dmg = value;
    }

    public ArrayList<String> getLore() {
        if (lore == null) return new ArrayList<>();
        else return lore;
    }

    public void addSlot(GemstoneSlot slot) {
        if (gemstoneSlots.size() < 8) {
            gemstoneSlots.add(slot);
        }
    }

    public void addAbility(AbilityManager<? extends Event> ability, AbilityType type, String name, int manacost, int cooldown) {
        abilities.add(new Ability(ability, name, type, new AbilityLore(new ArrayList<>()), manacost, cooldown));
    }

    public void addAbility(AbilityManager<? extends Event> ability, AbilityType type, String name, AbilityLore lore, int manacost, int cooldown) {
        abilities.add(new Ability(ability, name, type, (lore == null) ? new AbilityLore(new ArrayList<>()) : lore, manacost, cooldown));
    }

    public void addAbility(AbilityManager<? extends Event> ability, AbilityType type, String name, AbilityLore lore, double pers, boolean isPers, int cooldown) {
        Ability a = new Ability(ability, name, type, (lore == null) ? new AbilityLore(new ArrayList<>()) : lore, (int) pers, cooldown);
        if (isPers) a.setPersentage(pers);
        abilities.add(a);
    }

    public void setCatchMultiplier(double d) {
        catchMult = d;
    }

    public void set2Ability(String name, AbilityManager ability, AbilityType type, ArrayList<String> abilitylore, int manacost, int cooldown) {
        addAbility(ability, type, name, new AbilityLore(abilitylore), manacost, cooldown);
    }

    public void setFullSetBonus(Bonuses bonus, String name) {
        this.bonus = bonus;
        abilities.add(new Ability(null, name, AbilityType.FullSetBonus, new AbilityLore(new ArrayList<>()), 0, 0));
        fullSetBonusPointer = abilities.size() - 1;
    }

    public void setFullSetBonus(Bonuses bonus, String name, boolean isTiered) {
        TieredBonus = isTiered;
        setFullSetBonus(bonus, name, new AbilityLore(new ArrayList<>()));
    }

    public void setFullSetBonus(Bonuses bonus, String name, AbilityLore lore) {
        this.bonus = bonus;
        abilities.add(new Ability(null, name, AbilityType.FullSetBonus, lore, 0, 0));
        fullSetBonusPointer = abilities.size() - 1;
    }

    public void setFullSetBonus(Bonuses bonus, String name, AbilityLore lore, boolean isTiered) {
        TieredBonus = isTiered;
        setFullSetBonus(bonus, name, lore);
    }

    public Ability getFullSetBonus() {
        if (fullSetBonusPointer == -1) return null;
        return abilities.get(fullSetBonusPointer);
    }

    public void addBaseEnchantment(Enchantment enchant, int level) {
        enchants.put(enchant, level);
    }

    public void setIsSkullValue(boolean b) {
        isSkullValue = b;
    }

    public ItemStack getRawItemStack() {
        if (!isHead) {

            ItemStack item = new ItemStack(material);

            if (color != null) {
                LeatherArmorMeta lmeta = (boolean) item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
                lmeta.setColor(color);
                item.setItemMeta(lmeta);
            }

            ItemMeta meta = item.getItemMeta();

            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.addItemFlags(ItemFlag.HIDE_DYE);
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

            PersistentDataContainer data = meta.getPersistentDataContainer();
            meta.setDisplayName(rarity.getPrefix() + name);
            if (enchants != null) enchants.forEach((enchant, level) -> {
                meta.addEnchant(enchant, level, true);
            });
            data.set(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING, itemID);
            data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, type.toString());
            if (dmg != 0) data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, "" + dmg);
            if (breakingpower != 0)
                data.set(new NamespacedKey(Main.getMain(), "breakingpower"), PersistentDataType.DOUBLE, breakingpower);

            for (Stats s : stats.keySet())
                data.set(new NamespacedKey(Main.getMain(), s.getDataName()), PersistentDataType.DOUBLE, stats.get(s));
            if (catchMult != 0)
                data.set(new NamespacedKey(Main.getMain(), "catchmult"), PersistentDataType.DOUBLE, catchMult);
            if (baseabilitydamage != 0)
                data.set(new NamespacedKey(Main.getMain(), "baseabilitydamage"), PersistentDataType.INTEGER, baseabilitydamage);
            data.set(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING, rarity.toString());
            data.set(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER, 0);
            if (isUnstackeble)
                data.set(new NamespacedKey(Main.getMain(), "uuid"), PersistentDataType.STRING, UUID.randomUUID().toString());
            for (Stats stat : stats.keySet())
                data.set(new NamespacedKey(Main.getMain(), stat.getDataName()), PersistentDataType.DOUBLE, stats.get(stat));


            ArrayList<String> lore = new ArrayList<>();
            if (this.lore != null) this.lore.forEach(l -> {
                lore.add(l);
            });
            if (type == ItemType.Drill) {
                data.set(new NamespacedKey(Main.getMain(), "maxfuel"), PersistentDataType.INTEGER, 3000);
                data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, 3000);
            }
            if (customDataContainer != null) {
                customDataContainer.forEach((arg1, arg2) -> {
                    data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.STRING, arg2);
                });
            }

            lore.add("");
            lore.add(rarity.getRarityName() + type.toString());
            meta.setLore(lore);
            meta.setUnbreakable(true);
            if (customIntContainer != null) {
                customIntContainer.forEach((arg1, arg2) -> {
                    data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.INTEGER, arg2);
                });
            }
            item.setItemMeta(meta);
            NBTEditor.set(item, itemID, "ExtraAtribute", "id");
            if (!bannerPattern.isEmpty()) {
                BannerMeta bannerMeta = (BannerMeta) item.getItemMeta();
                for (Pattern patterns : bannerPattern)
                    bannerMeta.addPattern(patterns);

                item.setItemMeta(bannerMeta);
            }
            return item;
        } else {
            ItemStack item;
            if (!isSkullValue) if (customUUID == null) item = Tools.CustomHeadTexture(headTexture);
            else item = Tools.CustomHeadTexture(headTexture, customUUID.toString());
            else item = Tools.getCustomTexturedHeadFromSkullValue(headTexture);

            ItemMeta meta;
            if (!isSkullValue) if (customUUID == null) meta = Tools.CustomHeadTextureMeta(headTexture);
            else meta = Tools.CustomHeadTextureMeta(headTexture, customUUID.toString());
            else meta = Tools.getCustomTexturedHeadFromSkullValueMeta(headTexture);
            meta.setDisplayName(rarity.getPrefix() + name);
            enchants.forEach((enchant, level) -> {
                meta.addEnchant(enchant, level, true);
            });
            PersistentDataContainer data = meta.getPersistentDataContainer();

            data.set(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING, itemID);
            data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, type.toString());
            if (dmg != 0) data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, "" + dmg);

            if (breakingpower != 0)
                data.set(new NamespacedKey(Main.getMain(), "breakingpower"), PersistentDataType.DOUBLE, breakingpower);
            if (baseabilitydamage != 0)
                data.set(new NamespacedKey(Main.getMain(), "baseabilitydamage"), PersistentDataType.INTEGER, baseabilitydamage);
            ;
            for (Stats s : stats.keySet())
                data.set(new NamespacedKey(Main.getMain(), s.getDataName()), PersistentDataType.DOUBLE, stats.get(s));
            if (catchMult != 0)
                data.set(new NamespacedKey(Main.getMain(), "catchmult"), PersistentDataType.DOUBLE, catchMult);
            data.set(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING, rarity.toString());
            data.set(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER, 0);
            ArrayList<String> lore = new ArrayList<>();
            if (this.lore != null) this.lore.forEach(l -> {
                lore.add(l);
            });

            if (type == ItemType.Drill) {
                data.set(new NamespacedKey(Main.getMain(), "maxfuel"), PersistentDataType.INTEGER, 3000);
                data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, 3000);
            }
            if (isUnstackeble)
                data.set(new NamespacedKey(Main.getMain(), "uuid"), PersistentDataType.STRING, UUID.randomUUID().toString());
            for (Stats stat : stats.keySet())
                data.set(new NamespacedKey(Main.getMain(), stat.getDataName()), PersistentDataType.DOUBLE, stats.get(stat));

            lore.add(" ");

            lore.add(rarity.getRarityName() + type.toString());
            meta.setLore(lore);

            if (Gemstone.gemstones.containsKey(itemID)) {
                Gemstone gem = Gemstone.gemstones.get(itemID);

                data.set(new NamespacedKey(Main.getMain(), "QUALITY"), PersistentDataType.STRING, gem.gemState.toString());
                data.set(new NamespacedKey(Main.getMain(), "GEMTYPE"), PersistentDataType.STRING, gem.gemType.toString());

            }
            if (Pet.pets.containsKey(itemID)) {


                data.set(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER, 1);
                data.set(new NamespacedKey(Main.getMain(), "currxp"), PersistentDataType.DOUBLE, 0D);

            }
            if (customIntContainer != null) {
                customIntContainer.forEach((arg1, arg2) -> {
                    data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.INTEGER, arg2);
                });
            }
            item.setItemMeta(meta);
            net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
            NBTTagCompound d = tag.getCompound("ExtraAttributes") != null ? tag.getCompound("ExtraAttributes") : new NBTTagCompound();
            d.setString("id", itemID);
            tag.set("ExtraAttributes", d);
            nmsItem.setTag(tag);
            item = CraftItemStack.asBukkitCopy(nmsItem);


            return item;
        }
    }


    public ItemStack createNewItemStack() {
        if (!isHead) {

            ItemStack item = new ItemStack(material);

            if (color != null) {
                LeatherArmorMeta lmeta = (boolean) item.hasItemMeta() ? (LeatherArmorMeta) item.getItemMeta() : (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
                lmeta.setColor(color);
                lmeta.addItemFlags(ItemFlag.HIDE_DYE);
                item.setItemMeta(lmeta);
            }

            ItemMeta meta = item.getItemMeta();

            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

            meta.addItemFlags(ItemFlag.HIDE_DYE);
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

            PersistentDataContainer data = meta.getPersistentDataContainer();
            meta.setDisplayName(rarity.getPrefix() + name);
            if (enchants != null) enchants.forEach((enchant, level) -> {
                meta.addEnchant(enchant, level, true);
            });
            data.set(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING, itemID);
            data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, type.toString());
            if (dmg != 0) data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, "" + dmg);

            if (breakingpower != 0)
                data.set(new NamespacedKey(Main.getMain(), "breakingpower"), PersistentDataType.DOUBLE, breakingpower);
            for (Stats s : stats.keySet())
                data.set(new NamespacedKey(Main.getMain(), s.getDataName()), PersistentDataType.DOUBLE, stats.get(s));
            if (catchMult != 0)
                data.set(new NamespacedKey(Main.getMain(), "catchmult"), PersistentDataType.DOUBLE, catchMult);

            if (baseabilitydamage != 0)
                data.set(new NamespacedKey(Main.getMain(), "baseabilitydamage"), PersistentDataType.INTEGER, baseabilitydamage);

            data.set(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING, rarity.toString());
            data.set(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER, 0);

            if (hasEdition) {

                data.set(new NamespacedKey(Main.getMain(), "from"), PersistentDataType.STRING, "-");
                data.set(new NamespacedKey(Main.getMain(), "to"), PersistentDataType.STRING, "-");
                EditionItems.reload();
                int edition = EditionItems.get().getInt(itemID) + 1;
                EditionItems.get().set(itemID, edition);
                EditionItems.save();
                EditionItems.reload();
                data.set(new NamespacedKey(Main.getMain(), "date"), PersistentDataType.STRING, df.format(new Date()));

                data.set(new NamespacedKey(Main.getMain(), "editionnumber"), PersistentDataType.INTEGER, edition);
            }

            if (isUnstackeble)
                data.set(new NamespacedKey(Main.getMain(), "uuid"), PersistentDataType.STRING, UUID.randomUUID().toString());
            for (Stats stat : stats.keySet())
                data.set(new NamespacedKey(Main.getMain(), stat.getDataName()), PersistentDataType.DOUBLE, stats.get(stat));

            ArrayList<String> lore = new ArrayList<>();
            if (this.lore != null) this.lore.forEach(l -> {
                lore.add(l);
            });
            if (type == ItemType.Drill) {
                data.set(new NamespacedKey(Main.getMain(), "maxfuel"), PersistentDataType.INTEGER, 3000);
                data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, 3000);
            }
            if (customDataContainer != null) {
                customDataContainer.forEach((arg1, arg2) -> {
                    data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.STRING, arg2);
                });
            }
            if (customIntContainer != null) {
                customIntContainer.forEach((arg1, arg2) -> {
                    data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.INTEGER, arg2);
                });
            }

            lore.add(" ");

            lore.add(rarity.getRarityName() + type.toString());
            meta.setLore(lore);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
            if (!bannerPattern.isEmpty()) {
                BannerMeta bannerMeta = (BannerMeta) item.getItemMeta();
                for (Pattern patterns : bannerPattern)
                    bannerMeta.addPattern(patterns);
                item.setItemMeta(bannerMeta);
            }

            NBTEditor.set(item, itemID, "ExtraAttributes", "id");
            if (isAttributable()) Attribute.rool(item, this);
            return item;
        } else {
            ItemStack item;
            if (!isSkullValue) if (customUUID == null) item = Tools.CustomHeadTexture(headTexture);
            else item = Tools.CustomHeadTexture(headTexture, customUUID.toString());
            else item = Tools.getCustomTexturedHeadFromSkullValue(headTexture);
            ItemMeta meta;
            if (!isSkullValue) if (customUUID == null) meta = Tools.CustomHeadTextureMeta(headTexture);
            else meta = Tools.CustomHeadTextureMeta(headTexture, customUUID.toString());
            else meta = Tools.getCustomTexturedHeadFromSkullValueMeta(headTexture);
            meta.setDisplayName(rarity.getPrefix() + name);
            enchants.forEach((enchant, level) -> {
                meta.addEnchant(enchant, level, true);
            });
            PersistentDataContainer data = meta.getPersistentDataContainer();

            data.set(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING, itemID);
            data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, type.toString());
            if (dmg != 0) data.set(new NamespacedKey(Main.getMain(), "dmg"), PersistentDataType.STRING, "" + dmg);
            for (Stats s : stats.keySet())
                data.set(new NamespacedKey(Main.getMain(), s.getDataName()), PersistentDataType.DOUBLE, stats.get(s));
            if (breakingpower != 0)
                data.set(new NamespacedKey(Main.getMain(), "breakingpower"), PersistentDataType.DOUBLE, breakingpower);
            if (baseabilitydamage != 0)
                data.set(new NamespacedKey(Main.getMain(), "baseabilitydamage"), PersistentDataType.INTEGER, baseabilitydamage);

            if (catchMult != 0)
                data.set(new NamespacedKey(Main.getMain(), "catchmult"), PersistentDataType.DOUBLE, catchMult);

            data.set(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING, rarity.toString());
            data.set(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER, 0);
            ArrayList<String> lore = new ArrayList<>();
            if (this.lore != null) this.lore.forEach(l -> {
                lore.add(l);
            });
            if (hasEdition) {

                data.set(new NamespacedKey(Main.getMain(), "from"), PersistentDataType.STRING, "-");
                data.set(new NamespacedKey(Main.getMain(), "to"), PersistentDataType.STRING, "-");
                EditionItems.reload();
                int edition = EditionItems.get().getInt(itemID) + 1;
                EditionItems.get().set(itemID, edition);
                EditionItems.save();
                EditionItems.reload();

                data.set(new NamespacedKey(Main.getMain(), "editionnumber"), PersistentDataType.INTEGER, edition);
            }

            if (type == ItemType.Drill) {
                data.set(new NamespacedKey(Main.getMain(), "maxfuel"), PersistentDataType.INTEGER, 3000);
                data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, 3000);
            }

            if (isUnstackeble)
                data.set(new NamespacedKey(Main.getMain(), "uuid"), PersistentDataType.STRING, UUID.randomUUID().toString());
            for (Stats stat : stats.keySet())
                data.set(new NamespacedKey(Main.getMain(), stat.getDataName()), PersistentDataType.DOUBLE, stats.get(stat));

            lore.add(" ");

            lore.add(rarity.getRarityName() + type.toString());
            meta.setLore(lore);

            if (Gemstone.gemstones.containsKey(itemID)) {
                Gemstone gem = Gemstone.gemstones.get(itemID);

                data.set(new NamespacedKey(Main.getMain(), "QUALITY"), PersistentDataType.STRING, gem.gemState.toString());
                data.set(new NamespacedKey(Main.getMain(), "GEMTYPE"), PersistentDataType.STRING, gem.gemType.toString());

            }
            if (Pet.pets.containsKey(itemID)) {


                data.set(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER, 1);
                data.set(new NamespacedKey(Main.getMain(), "currxp"), PersistentDataType.DOUBLE, 0D);

            }
            if (customIntContainer != null) {
                customIntContainer.forEach((arg1, arg2) -> {
                    data.set(new NamespacedKey(Main.getMain(), arg1), PersistentDataType.INTEGER, arg2);
                });
            }

            item.setItemMeta(meta);
            net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
            NBTTagCompound d = tag.getCompound("ExtraAttributes") != null ? tag.getCompound("ExtraAttributes") : new NBTTagCompound();
            d.setString("id", itemID);
            tag.set("ExtraAttributes", d);
            nmsItem.setTag(tag);
            item = CraftItemStack.asBukkitCopy(nmsItem);
            if (isAttributable()) Attribute.rool(item, this);
            return item;
        }
    }

    public void setNpcSellPrice(int i) {
        npcSellPrice = i;
    }

    public int getSellPrice() {
        return npcSellPrice;
    }

    public static interface MaterialGrabber {
        Material getMaterial(ItemStack item, SkyblockPlayer player);
    }
    public static interface SpecialRarityGrabber {
        ItemRarity getRarity(@NotNull ItemRarity rarity,@NotNull ItemStack item,@Nullable SkyblockPlayer player);
    }
}
