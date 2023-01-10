package me.CarsCupcake.SkyblockRemake.Slayer.Blaze;

import me.CarsCupcake.SkyblockRemake.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Crafting.ShapeEncoder;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockRecipe;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockShapedRecipe;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.ItemAbility.AshenAuricSwap;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.ItemAbility.FiredustDaggerHit;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.ItemAbility.SpiritCystalSwap;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.ItemAbility.TwilightDaggerHit;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class BlazeSlayerItems {
    public BlazeSlayerItems(){
        ItemManager manager = new ItemManager("Firedust Dagger", "FIREDUST_DAGGER", ItemType.Sword, Material.STONE_SWORD, ItemRarity.RARE);
        manager.setDamage(90);
        manager.setStat(Stats.Strength, 45);
        manager.setStat(Stats.CritDamage, 15);
        manager.customDataContainer.put("attuned", "ashen");
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        List<String> abilityLore = List.of("§7Swap between §fSpirit §7and", "§bCrystal §7mode in order to", "§7attack through Hellion Shields.", " ", "§7Attuned: %at%"," ", "§7Deal §c1.2x §7damage to Blazes",
                "§7Deal §c1.1x §7damage to Pigmen.");
        AbilityLore lore = new AbilityLore(abilityLore);
        lore.addPlaceholder("%at%", new AshenAuricSwap());
        manager.setAbility(new AshenAuricSwap(), AbilityType.RightClick, "Attunement", 0, 0);
        manager.setAbilityLore(lore);
        manager.set2Ability("Burning Vengeance ﬗ", new FiredustDaggerHit(), AbilityType.SkyblockPreHit, new ArrayList<>(List.of("§7On hit, §crepeat §7dealt", "§7damage after §c6s §7if the",
                "§7Hellion Shield has switched", "§7attunement.")), 0, 0);
        manager.getFlags().add(ItemFlag.SPECIAL_MATERIAL_GRABBER);
        manager.setMaterialGrabber(new AshenAuricSwap());

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("firedust dagger", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder(" f ", " m ", " s ");
        encoder.setKey('f', new CraftingObject(Items.SkyblockItems.get("MAGMA_FISH_SILVER"), 30));
        encoder.setKey('m', new CraftingObject(Items.SkyblockItems.get("MOLTEN_POWDER"), 1));
        encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get(Material.STICK.toString()), 1));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        manager = new  ItemManager("Twilight Dagger", "MAWDUST_DAGGER", ItemType.Sword, Material.IRON_SWORD, ItemRarity.RARE);
        manager.setDamage(90);
        manager.setStat(Stats.Strength, 45);
        manager.setStat(Stats.CritDamage, 15);
        manager.setAbility(new SpiritCystalSwap(), AbilityType.RightClick, "Attunement", 0, 0);
        abilityLore = List.of("§7Swap between §8Ashen §7and", "§eAuric §7mode in order to", "§7attack through Hellion Shields.", " ", "§7Attuned: %at%"," ", "§7Deal §c1.2x §7damage to Blazes",
                "§7Deal §c1.1x §7damage to Pigmen.");
        lore = new AbilityLore(abilityLore);
        lore.addPlaceholder("%at%", new SpiritCystalSwap());
        manager.setAbilityLore(lore);
        manager.set2Ability("Wraith Drain", new TwilightDaggerHit(), AbilityType.AfterHit, new ArrayList<>(List.of("§7On hit, heal for §c200 §7+", "§c4%" +
                Stats.Health.getSymbol() + " §7after §a6s §7if the", "§7Hellion Shield has switched", "§7attunement")), 0, 0);
        manager.getFlags().add(ItemFlag.SPECIAL_MATERIAL_GRABBER);
        manager.setMaterialGrabber(new SpiritCystalSwap());
    }
}
