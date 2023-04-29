package me.CarsCupcake.SkyblockRemake.Items.minions.implementations.combat;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Entities.BasicEntity;
import me.CarsCupcake.SkyblockRemake.Entities.MinionEntity;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Items.minions.AbstractCombatMinionData;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ZombieMinionData extends AbstractCombatMinionData {
    @Override
    protected MinionEntity getEntity() {
        return new BasicEntity(Zombie.class, 100, 5);
    }

    @Override
    public int getLevels() {
        return 11;
    }

    @Override
    public String[] getHeadStrings() {
        return new String[]{
                "http://textures.minecraft.net/texture/e8fff674587f61094c3196abd565b44d806d757405a6d2661a7b797c1144b6a4",
                "http://textures.minecraft.net/texture/95fbc329bbe5353e80a306807a93790ec564a01f341a08cbaae70a776f62c08d",
                "http://textures.minecraft.net/texture/7856b9951e24c74b2a1efcf966307658d739828bf572ed278760e577aeba87ad",
                "http://textures.minecraft.net/texture/6fe5bacd8988532bacb936791f3797137bb124c6a960bb775eb120e900386ac1",
                "http://textures.minecraft.net/texture/61202ca1ffc076542d5bd874addfe59163d9ba01457d452ad1c83cbdbc2bae66",
                "http://textures.minecraft.net/texture/13a2b16fc84e4da4b218e391244e893c7cf022bb5637753d7b2a9d87d5d7ce8d",
                "http://textures.minecraft.net/texture/8ca41ca54eaa62c83bb6f31e15120580646368d843a89ece5c5574ac4344350f",
                "http://textures.minecraft.net/texture/58e8cc9745f5b56b7a54877a7661c7f14ca85843af7817b681e551c4adcd10b3",
                "http://textures.minecraft.net/texture/247c5387db928803f314f92c8106e631352e678267a87cd2e6870185f7dd1c79",
                "http://textures.minecraft.net/texture/3903f01c033662b8b60c674ed60cca64744c63361137f4a7a6664b424be5c7bf",
                "http://textures.minecraft.net/texture/8e3436564b84d6481d89d4f253c5da73edfe650bc0569be60b758a4fd50a4dd8"
        };
    }

    @Override
    public String name() {
        return "Zombie Minion";
    }

    @Override
    public HashMap<Bundle<ItemManager, Integer>, Double> drops() {
        HashMap<Bundle<ItemManager, Integer>, Double> map = new HashMap<>();
        map.put(new Bundle<>(Items.SkyblockItems.get(Material.ROTTEN_FLESH + ""), 1), 1d);
        map.put(new Bundle<>(Items.SkyblockItems.get(Material.CARROT + ""), 1), 0.01);
        map.put(new Bundle<>(Items.SkyblockItems.get(Material.POTATO + ""), 1), 0.01);
        map.put(new Bundle<>(Items.SkyblockItems.get(Material.POISONOUS_POTATO + ""), 1), 0.02);
        return map;
    }

    @Override
    public long[] timeBetweenActions() {
        return new long[]{
                26*20,
                24*20,
                24*20,
                22*20,
                22*20,
                20*20,
                20*20,
                17*20,
                17*20,
                13*20,
                13*20
        };
    }

    @Override
    public HashMap<EquipmentSlot, ItemStack> getEquipment() {
        HashMap<EquipmentSlot, ItemStack> equipment = new HashMap<>();
        equipment.put(EquipmentSlot.CHEST, new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.fromBGR(0x136948)).build());
        equipment.put(EquipmentSlot.LEGS, new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.fromBGR(0x136948)).build());
        equipment.put(EquipmentSlot.FEET, new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.fromBGR(0x136948)).build());
        return equipment;
    }

    @Override
    public String id() {
        return "ZOMBIE";
    }
}
