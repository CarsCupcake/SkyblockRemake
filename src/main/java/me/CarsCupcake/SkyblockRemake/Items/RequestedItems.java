package me.CarsCupcake.SkyblockRemake.Items;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Items.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Items.Pets.PetAbility;
import me.CarsCupcake.SkyblockRemake.Items.Pets.PetType;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.abilities.BloodForTheBloodGod;
import me.CarsCupcake.SkyblockRemake.abilities.ICBMDeployerAbility;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class RequestedItems {
    private static RequestedItems INSTANCE;

    public RequestedItems() {
        INSTANCE = this;
        getICBMDeployer();
        getAxeOfPeace();
        getShushPet();
        croissant();
        getAutomatonGolem();
    }

    public RequestedItems getInstance() {
        return INSTANCE;
    }

    //Rquested by LEAX_XR#9446
    public void getICBMDeployer() {
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Lose half your hp but");
        abilityLore.add("§7damages the moby with 25%");
        abilityLore.add("§7of theyr hp");
        abilityLore.add("§8Caps at 1m HP");
        ItemManager manager = new ItemManager("ICBM Deployer", "ICBM_DEPLOYER", ItemType.Deployable, null, "Nuke", "", abilityLore, 500, 90, 0, 0, ItemRarity.MYTHIC, "https://textures.minecraft.net/texture/f7beaa6bd21326ba10db9eeb72987d190444c481553467bd75cfcc382363d914");
        manager.setUnstackeble(true);
        abilityLore = new ArrayList<>();
        abilityLore.add("§7Fueling you missle is very important!");
        manager.addAbility(new ICBMDeployerAbility(), AbilityType.RightClick, "Nuke", new AbilityLore(abilityLore), 500, 90);
        manager.set2Ability("Fuel", new ICBMDeployerAbility(), AbilityType.LeftClick, abilityLore, 0, 0);
        manager.customIntContainer.put("fueling", 0);
        Items.SkyblockItems.put(manager.itemID, manager);

    }

    //Requested by 1mod2#3872
    public void getAxeOfPeace() {
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Gain §c150% §7more damage and §c100% §7Crit Chance for §e5 §7seconds.");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Pass down by the King of Pigs");
        ItemManager manager = new ItemManager("Axe of Peace", "AXE_OF_PEACE", ItemType.Axe, lore, "Blood for the blood god", "", abilityLore, 150, 15, 0, 0, Material.GOLDEN_AXE, ItemRarity.LEGENDARY);
        manager.setStat(Stats.WeaponDamage, 350);
        manager.setStat(Stats.Strength, 700);
        manager.setStat(Stats.CritChance, 30);
        manager.setStat(Stats.CritDamage, 210);
        manager.setStat(Stats.AttackSpeed, -10);
        manager.addAbility(new BloodForTheBloodGod(), AbilityType.RightClick, "Blood for the blood god", new AbilityLore(abilityLore), 150, 15);
        Items.SkyblockItems.put(manager.itemID, manager);

    }

    //Rquested by LEAX_XR#9446
    public void getShushPet() {
        Pet manager = new Pet("Shush", "SHUSH;MYTHIC", "https://textures.minecraft.net/texture/3baabe724eae59c5d13f442c7dc5d2b1c6b70c2f83364a488ce5973ae80b4c3", ItemRarity.MYTHIC, 100, PetType.Combat);
        for (Stats stat : Stats.values())
            if (!(stat == Stats.Strength || stat == Stats.MagicFind)) manager.addStat(stat, 700, 300);
        Items.SkyblockItems.put(manager.itemID, manager);
    }

    //Requested by pingu#5390
    public void croissant() {
        ItemManager manager = new ItemManager("Croissant", "CROISSANT", ItemType.Non, ItemRarity.UNCOMMON, "http://textures.minecraft.net/texture/2faaae94779138d0e144f4fbfa6295e9e43d5054fa6eb0a64421b546c88c5b48");

    }

    public void getAutomatonGolem() {
        Pet manager = new Pet("Automaton", "AUTOMATON_PET;LEGENDARY", "http://textures.minecraft.net/texture/ca14326aeac97f96189bb9969e7b955ec650d37b7c9790356ff09f32010f1ae0", ItemRarity.LEGENDARY, 100, PetType.Mining);
        manager.addStat(Stats.MiningFortune, 0, 50);
        manager.addStat(Stats.TrueDefense, 0, 10);
        manager.addStat(Stats.MiningSpeed, 0, 100);
        AbilityLore lore = new AbilityLore(List.of("§rAdds §6%mf% Mining Fortune", "§rAnd §6%ms% Mining Speed §rin the", "§9Precursor Rmeineds"));
        lore.addPlaceholder("%mf%", (player, itemStack) -> (ItemHandler.getPDC("level", itemStack, PersistentDataType.INTEGER) * 0.5) + " ");
        lore.addPlaceholder("%ms%", (player, itemStack) -> (ItemHandler.getPDC("level", itemStack, PersistentDataType.INTEGER) * 2) + " ");
        manager.addPetAbility("Monkey", lore, new AutomatonOne());
        lore = new AbilityLore(List.of("§r50% that you gain 25%", "§rmore drops from §5Pritine"));
        manager.addPetAbility("Tinder Gold", lore, new AutomatonTwo());
    }

    private static class AutomatonOne implements PetAbility {

        @Override
        public void start(SkyblockPlayer player) {

        }

        @Override
        public void stop(SkyblockPlayer player) {

        }
    }

    private static class AutomatonTwo implements PetAbility {

        @Override
        public void start(SkyblockPlayer player) {

        }

        @Override
        public void stop(SkyblockPlayer player) {

        }
    }
}
