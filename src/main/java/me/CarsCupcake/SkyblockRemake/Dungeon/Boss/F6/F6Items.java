package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.abilitys.GiantsSlam;
import org.bukkit.Material;

import java.util.ArrayList;

public class F6Items {
    public F6Items(){
        ItemManager manager = acientRose();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);

        manager = giantsSword();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);

    }
    private ItemManager acientRose(){
        return new ItemManager("Ancient Rose", "GOLEM_POPPY", ItemType.Non, null, null, null, null, 0, 0 ,0 ,0 , Material.POPPY, ItemRarity.RARE);
    }
    private ItemManager giantsSword(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Slam your sword into the ground");
        abilityLore.add("§7dealing §c%dmg% §7damage to");
        abilityLore.add("§7nearby enemies.");
        ItemManager manager = new ItemManager("Giant's Sword", "GIANTS_SWORD", ItemType.Sword, null, "Giant's Slam", "null",
                null, 100, 30 ,0 ,0 , Material.IRON_SWORD, ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        AbilityLore lore = new AbilityLore(abilityLore,"%dmg%", new Bundle<>(100000d,0.05));
        manager.setAbilityLore(lore);
        manager.setAbility(new GiantsSlam(), AbilityType.RightClick);
        manager.setDamage(500);
        manager.setNpcSellPrice(1000);
        return manager;
    }

    public enum Items{

        AncientRose("GOLEM_POPPY"),
        GiantsSword("GIANTS_SWORD");

    private final String id;
    Items(String id) {
        this.id = id;
    }
    public ItemManager getItem(){
        return me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.get(id);
    }
    public String getId(){
        return id;
    }
}
}
