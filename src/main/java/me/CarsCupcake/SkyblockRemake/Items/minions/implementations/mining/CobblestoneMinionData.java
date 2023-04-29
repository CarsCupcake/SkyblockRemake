package me.CarsCupcake.SkyblockRemake.Items.minions.implementations.mining;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Items.minions.AbstractMiningMinionData;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CobblestoneMinionData extends AbstractMiningMinionData {
    @Override
    public Material representiveBlock() {
        return Material.COBBLESTONE;
    }

    @Override
    public int getLevels() {
        return 12;
    }

    @Override
    public String[] getHeadStrings() {
        return new String[]{
                "http://textures.minecraft.net/texture/f9c38fe4fc98a248079cd04c65b6bff9b45017f16460dab3c34c17bfc3ee1d2f",
                "http://textures.minecraft.net/texture/dfc263bb3def3ea8eaf1c20a7eb84b5428c31456b433f0a219eac5b42e2aba11",
                "http://textures.minecraft.net/texture/2978fdd9a825e757477e93d5fc5dc22e627ec26c10e58d99d8d14f90451f826b",
                "http://textures.minecraft.net/texture/87453f284a5ba9f0de9da1b1753ac45a6395d1f91e38ccb9db87d68fe78ce77f",
                "http://textures.minecraft.net/texture/d2f9160653099869c08c53774cef61a4be3b8bf845f03c52b5e3ee351cee4eba",
                "http://textures.minecraft.net/texture/b6527b9c80378bccb030c876b19f6547aa76bf465833f471a35114b7d81e788f",
                "http://textures.minecraft.net/texture/691492796195fec3d56be0bce3ec40f631bf9b54054178f4582bb7952cf17752",
                "http://textures.minecraft.net/texture/f1eb86e1d5cdb3bb7dd8effd9e5064160be9f8340d480b494983490eeb54c8aa",
                "http://textures.minecraft.net/texture/74c5a4633283743b81ee0c1dae0babd3f8aab4b848e034b3c7fdd072e3e61d08",
                "http://textures.minecraft.net/texture/fcb946d415e610fc541c1e77beb137776c70d29809c942599f35d62953a8bcd6",
                "http://textures.minecraft.net/texture/14163ccd97254e51cdddc866d2d5cae8db3408cd39a0d60c1ac3cc4da90ec2aa",
                "http://textures.minecraft.net/texture/1927e4b8c375868795c3b50d711a8bec4583e5f27d2bd18d6f1c0cc46a067ee"
        };
    }

    @Override
    public String name() {
        return "Cobblestone Minion";
    }

    @Override
    public HashMap<Bundle<ItemManager, Integer>, Double> drops() {
        HashMap<Bundle<ItemManager, Integer>, Double> chance = new HashMap<>();
        chance.put(new Bundle<>(Items.SkyblockItems.get(Material.COBBLESTONE + ""), 1), 1d);
        return chance;
    }

    @Override
    public long[] timeBetweenActions() {
        return new long[]{
                14*20,
                14*20,
                12*20,
                12*20,
                10*20,
                10*20,
                9*20,
                9*20,
                8*20,
                8*20,
                7*20,
                6*20
        };
    }

    @Override
    public HashMap<EquipmentSlot, ItemStack> getEquipment() {
        HashMap<EquipmentSlot, ItemStack> equipment = new HashMap<>();
        equipment.put(EquipmentSlot.CHEST, new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.fromRGB(8026468)).build());
        equipment.put(EquipmentSlot.LEGS, new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.fromRGB(8026468)).build());
        equipment.put(EquipmentSlot.FEET, new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.fromRGB(8026468)).build());
        return equipment;
    }

    @Override
    public String id() {
        return "COBBLESTONE";
    }
}
