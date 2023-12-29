package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Items.Pets.PetType;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.itemAbilitys.SpadeLeftClick;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.itemAbilitys.SpadeRightClick;
import me.CarsCupcake.SkyblockRemake.utils.Factory;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public enum DianaItems implements Factory<String, ItemManager> {
    AncientClaw("ANCIENT_CLAW") {
        @Override
        public ItemManager factor(String obj) {
            return new ItemManager("Ancient Claw", obj, ItemType.Non, Material.FLINT, ItemRarity.RARE);
        }
    },
    EnchantedAncientClaw("ENCHANTED_ANCIENT_CLAW") {
        @Override
        public ItemManager factor(String obj) {
            ItemManager manager = new ItemManager("Enchanted Ancient Claw", obj, ItemType.Non, Material.FLINT, ItemRarity.EPIC);
            manager.setGlint();
            return manager;
        }
    },
    AncestralSpade("ANCESTRAL_SPADE") {
        @Override
        public ItemManager factor(String s) {
            ItemManager manager = new ItemManager("Ancestral Spade", s, ItemType.Non, Material.WOODEN_SHOVEL, ItemRarity.RARE);
            manager.addAbility(new SpadeLeftClick(), AbilityType.LeftClick, null, new AbilityLore("§7Hold in your hand to reveal and", "§7dig out §eGriffin Burrows §7in", "§7the hub, which hold both", "§6treasures §7and §cdangers§7."));
            manager.addAbility(new SpadeRightClick(), AbilityType.RightClick, "Echo", new AbilityLore("§7Show the way to the next or", "§7nearby Griffin burrow"), 100, 3);
            return manager;
        }
    },
    COMMON_GRIFFIN_PET("GRIFFIN;COMMON") {
        @Override
        public ItemManager factor(String obj) {
            return getGriffinPet(obj, ItemRarity.COMMON);
        }
    },
    UNCOMMON_GRIFFIN_PET("GRIFFIN;UNCOMMON") {
        @Override
        public ItemManager factor(String obj) {
            Pet pet = getGriffinPet(obj, ItemRarity.UNCOMMON);
            pet.addPetAbility("Legendary Constitution", new AbilityLore("§7Permanent §cRegeneration V", "§7and §4Strength VII", "§8---WIP---"), null);
            return pet;
        }
    },
    RARE_GRIFFIN_PET("GRIFFIN;RARE") {
        @Override
        public ItemManager factor(String obj) {
            Pet pet = getGriffinPet(obj, ItemRarity.RARE);
            pet.addPetAbility("Legendary Constitution", new AbilityLore("§7Permanent §cRegeneration VI", "§7and §4Strength VII", "§8---WIP---"), null);
            return pet;
        }
    },
    EPIC_GRIFFIN_PET("GRIFFIN;EPIC") {
        @Override
        public ItemManager factor(String obj) {
            Pet pet = getGriffinPet(obj, ItemRarity.EPIC);
            pet.addPetAbility("Legendary Constitution", new AbilityLore("§7Permanent §cRegeneration VI", "§7and §4Strength VII", "§8---WIP---"), null);
            AbilityLore lore = new AbilityLore("§7Heal nearby players for", "§a%pes%% of the final damage you recieve.", "§8Excludes other griffins.");
            lore.addPlaceholder("%pes%", (player, itemStack) -> Double.toString(0.16 * ItemHandler.getOrDefaultPDC("level", itemStack, PersistentDataType.INTEGER, 1)));
            pet.addPetAbility("Perpetual Empathy", lore, null);
            return pet;
        }
    },
    LEGENDARY_GRIFFIN_PET("GRIFFIN;LEGENDARY") {
        @Override
        public ItemManager factor(String obj) {
            Pet pet = getGriffinPet(obj, ItemRarity.LEGENDARY);
            pet.addPetAbility("Legendary Constitution", new AbilityLore("§7Permanent §cRegeneration VI", "§7and §4Strength VII", "§8---WIP---"), null);
            AbilityLore lore = new AbilityLore("§7Heal nearby players for", "§a%pes%% of the final damage you recieve.", "§8Excludes other griffins.");
            lore.addPlaceholder("%pes%", (player, itemStack) -> Double.toString(0.16 * ItemHandler.getOrDefaultPDC("level", itemStack, PersistentDataType.INTEGER, 1)));
            pet.addPetAbility("Perpetual Empathy", lore, null);
            AbilityLore kokAbility = new AbilityLore("§7Gain §c%str%% " + Stats.Strength, "§7when above §c85%§7health");
            kokAbility.addPlaceholder("%str%", (player, itemStack) -> Double.toString(1 + (0.14 * ItemHandler.getOrDefaultPDC("level", itemStack, PersistentDataType.INTEGER, 1))));
            pet.addPetAbility("King of Kings", kokAbility, null);
            return pet;
        }
    },
    GRIFFIN_FEATHER("GRIFFIN_FEATHER"){
        @Override
        public ItemManager factor(String obj) {
            return new ItemManager("Griffin Feather", obj, ItemType.Non, Material.FEATHER, ItemRarity.RARE);
        }
    },
    CROWN_OF_GREED("CROWN_OF_GREED"){
        @Override
        public ItemManager factor(String obj) {
            ItemManager manager = new ItemManager("Crown of Greed", obj, ItemType.Helmet, Material.GOLDEN_HELMET, ItemRarity.LEGENDARY);
            manager.setStat(Stats.Health, 130);
            manager.setStat(Stats.Defense, 90);
            manager.setStat(Stats.MagicFind, 4);
            manager.setNpcSellPrice(1_000_000);
            manager.setLore(List.of("§7Hits have §c+25% §7base damage,", "§7but cost §6100x §7the weapon's", "§7base damage in §6coins §7from", "§7your purse"));
            return manager;
        }
    };

    @NotNull
    private static Pet getGriffinPet(String obj, ItemRarity rarity) {
        Pet pet = new Pet("Griffin", obj, "ewogICJ0aW1lc3RhbXAiIDogMTU5ODQ0Njc0MjE0MSwKICAicHJvZmlsZUlkIiA6ICI0MWQzYWJjMmQ3NDk0MDBjOTA5MGQ1NDM0ZDAzODMxYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNZWdha2xvb24iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGMyN2UzY2I1MmE2NDk2OGU2MGM4NjFlZjFhYjg0ZTBhMGNiNWYwN2JlMTAzYWM3OGRhNjc3NjE3MzFmMDBjOCIKICAgIH0KICB9Cn0=",
                rarity, 100, PetType.Combat);
        pet.setIsSkullValue(true);
        pet.addStat(Stats.CritChance, 0, 10);
        pet.addStat(Stats.CritDamage, 0, 50);
        pet.addStat(Stats.Strength, 0, 25);
        pet.addStat(Stats.Inteligence, 0, 10);
        pet.addStat(Stats.MagicFind, 0, 10);
        pet.addPetAbility("Odyssey", new AbilityLore("§2Mythological creatures §7you", "§7find and burrows you dig scale", "§7in §cdiffivulty §7and §6rewards", "§7based on your eqiipped", "§7Griffin's rarity"), null);
        return pet;
    }

    private final ItemManager item;

    DianaItems(String id) {
        item = this.factor(id);
    }

    public static void init() {
        for (DianaItems items : values()) {
            Items.SkyblockItems.put(items.item.itemID, items.item);
        }
    }
}
