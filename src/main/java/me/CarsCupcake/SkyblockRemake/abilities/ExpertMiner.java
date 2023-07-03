package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Items.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ExpertMiner implements FullSetBonus, Listener {
    public static void init() {
        ItemManager manager = new ItemManager("Glacite Jewel", "GLACITE_JEWEL", ItemType.Non, ItemRarity.RARE, "");
        manager.setNpcSellPrice(2000);
        Items.SkyblockItems.put(manager.itemID, manager);

        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Grants " + Stats.MiningSpeed.getColor() + "+" + Stats.MiningSpeed.getSymbol() + " " + Stats.MiningSpeed.getName() + " §7for");
        abilityLore.add("§7each of your mining levels.");
        abilityLore.add(" ");
        abilityLore.add("§7The Defense of this item is");
        abilityLore.add("§7doubled while on a mining island");
        manager = new ItemManager("Glacite Helmet", "GLACITE_HELMET", ItemType.Helmet, Material.PACKED_ICE, ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.addSlot(new GemstoneSlot(SlotType.Amber));
        manager.addSlot(new GemstoneSlot(SlotType.Jade));
        manager.setFullSetBonus(Bonuses.ExpertMiner, "Expert Miner", new AbilityLore(abilityLore));
        manager.setStat(Stats.Defense, 70);
        manager.setStat(Stats.Speed, 10);
        manager.setStat(Stats.TrueDefense, 5);
        manager.setStat(Stats.MiningSpeed,  10);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Glacite Chestplate", "GLACITE_CHESTPLATE", ItemType.Chestplate, Material.LEATHER_CHESTPLATE, Color.fromRGB(0x03FCF8), ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.addSlot(new GemstoneSlot(SlotType.Amber));
        manager.addSlot(new GemstoneSlot(SlotType.Jade));
        manager.setFullSetBonus(Bonuses.ExpertMiner, "Expert Miner", new AbilityLore(abilityLore));
        manager.setStat(Stats.Defense, 150);
        manager.setStat(Stats.Speed, 15);
        manager.setStat(Stats.TrueDefense, 5);
        manager.setStat(Stats.MiningSpeed,  10);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Glacite Leggings", "GLACITE_LEGGINGS", ItemType.Leggings, Material.LEATHER_LEGGINGS, Color.fromRGB(0x03FCF8), ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.addSlot(new GemstoneSlot(SlotType.Amber));
        manager.addSlot(new GemstoneSlot(SlotType.Jade));
        manager.setFullSetBonus(Bonuses.ExpertMiner, "Expert Miner", new AbilityLore(abilityLore));
        manager.setStat(Stats.Defense, 125);
        manager.setStat(Stats.Speed, 15);
        manager.setStat(Stats.TrueDefense, 5);
        manager.setStat(Stats.MiningSpeed,  10);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Glacite Boots", "GLACITE_BOOTS", ItemType.Boots, Material.LEATHER_BOOTS, Color.fromRGB(0x03FCF8), ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.addSlot(new GemstoneSlot(SlotType.Amber));
        manager.addSlot(new GemstoneSlot(SlotType.Jade));
        manager.setFullSetBonus(Bonuses.ExpertMiner, "Expert Miner", new AbilityLore(abilityLore));
        manager.setStat(Stats.Defense, 70);
        manager.setStat(Stats.Speed, 10);
        manager.setStat(Stats.TrueDefense, 5);
        manager.setStat(Stats.MiningSpeed,  10);
        Items.SkyblockItems.put(manager.itemID, manager);
    }
    static final Set<SkyblockPlayer> players = new HashSet<>();
    private SkyblockPlayer player;
    @Override
    public void start() {
        players.add(player);
    }

    @Override
    public void stop() {
        players.remove(player);
    }

    @Override
    public int getPieces() {
        return 4;
    }

    @Override
    public int getMaxPieces() {
        return 4;
    }

    @Override
    public void setPlayer(SkyblockPlayer player) {
        this.player = player;
    }

    @Override
    public Bonuses getBonus() {
        return Bonuses.ExpertMiner;
    }
    @EventHandler
    public void miningSpeedBoost(GetTotalStatEvent event){
        if(players.contains(event.getPlayer())){
            event.setValue(event.getValue() + (2 * event.getPlayer().getSkill(Skills.Mining).getLevel()));
        }
    }
}
