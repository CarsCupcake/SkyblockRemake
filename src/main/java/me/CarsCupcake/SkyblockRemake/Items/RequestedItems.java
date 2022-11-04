package me.CarsCupcake.SkyblockRemake.Items;

import me.CarsCupcake.SkyblockRemake.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Pets.PetType;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.abilitys.BloodForTheBloodGod;
import me.CarsCupcake.SkyblockRemake.abilitys.ICBMDeployerAbility;
import org.bukkit.Material;

import java.util.ArrayList;

public class RequestedItems {
    private static RequestedItems INSTANCE;
    public RequestedItems(){
        INSTANCE = this;
        getICBMDeployer();
        getAxeOfPeace();
        getShushPet();
    }
    public RequestedItems getInstance(){
        return INSTANCE;
    }
    //Rquested by LEAX_XR#9446
    public ItemManager getICBMDeployer() {
        ArrayList<String> abilityLore= new ArrayList<>();
        abilityLore.add("§7Lose half your hp but");
        abilityLore.add("§7damages the moby with 25%");
        abilityLore.add("§7of theyr hp");
        abilityLore.add("§8Caps at 1m HP");
        ItemManager manager = new ItemManager("ICBM Deployer", "ICBM_DEPLOYER", ItemType.Deployable, null, "Nuke", "", abilityLore, 500,90,0,0, ItemRarity.MYTHIC,"https://textures.minecraft.net/texture/f7beaa6bd21326ba10db9eeb72987d190444c481553467bd75cfcc382363d914");
        manager.setUnstackeble(true);
        abilityLore = new ArrayList<>();
        abilityLore.add("§7Fueling you missle is very important!");
        manager.setAbility(new ICBMDeployerAbility(), AbilityType.RightClick);
        manager.set2Ability("Fuel", new ICBMDeployerAbility(), AbilityType.LeftClick,abilityLore,0,0 );
        manager.customIntContainer.put("fueling",0 );
        Items.SkyblockItems.put(manager.itemID, manager);

        return manager;

    }
    //Requested by 1mod2#3872
    public ItemManager getAxeOfPeace() {
        ArrayList<String> abilityLore= new ArrayList<>();
        abilityLore.add("§7Gain §c150% §7more damage and §c100% §7Crit Chance for §e5 §7seconds.");
        ArrayList<String> lore= new ArrayList<>();
        lore.add("§7Pass down by the King of Pigs");
        ItemManager manager = new ItemManager("Axe of Peace", "AXE_OF_PEACE", ItemType.Axe, lore, "Blood for the blood god", "", abilityLore,
                150,15,0,0, Material.GOLDEN_AXE,ItemRarity.LEGENDARY);
        manager.setDamage(350);
        manager.setStat(Stats.Strength, 700);
        manager.setStat(Stats.CritChance, 30);
        manager.setStat(Stats.CritDamage, 210);
        manager.setStat(Stats.AttackSpeed, -10);
        manager.setAbility(new BloodForTheBloodGod(), AbilityType.RightClick);
        Items.SkyblockItems.put(manager.itemID, manager);

        return manager;

    }
    //Rquested by LEAX_XR#9446
    public ItemManager getShushPet() {

        ArrayList<String> lore1= new ArrayList<>();
        lore1.add("§7all mobs in a 10 block radius, shushes");
        ArrayList<String> lore2= new ArrayList<>();
        lore2.add("§7Shush");
        ArrayList<String> lore3= new ArrayList<>();
        lore3.add("§7Not macdonalds, every 5 mins, an armour stand will pop up infront");
        lore3.add("§7of you saying \"NOT MACDONALDS\"");
        Pet manager = new Pet("Shush", "SHUSH;MYTHIC", 0, 0, 0, 0, 100, 0, 0,
                0, 0, 500,0, 0, 0, 0, 0, 0, 0, 0, 0, 900, 0,
                0, 0, 0, 500, 0, 0, 0, 0,0,
                Material.PLAYER_HEAD, ItemRarity.MYTHIC, 100, PetType.Combat, "Shush", lore1, "Shush2", lore2, "Shush3", lore3,
                "https://textures.minecraft.net/texture/3baabe724eae59c5d13f442c7dc5d2b1c6b70c2f83364a488ce5973ae80b4c3");
        for (Stats stat : Stats.values())
            if(!(stat == Stats.Strength || stat == Stats.MagicFind))
                manager.addStat(stat, 700, 300);


        Items.SkyblockItems.put(manager.itemID, manager);

        return manager;

    }

}
