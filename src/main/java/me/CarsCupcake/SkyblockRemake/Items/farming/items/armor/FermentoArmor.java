package me.CarsCupcake.SkyblockRemake.Items.farming.items.armor;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.PlayerFarmEvent;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.requirements.SkillRequirement;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.*;

public class FermentoArmor implements FullSetBonus, Listener {
    private final static Set<SkyblockPlayer> players = new HashSet<>();
    private SkyblockPlayer player;

    public static void init() {
        AbilityLore lore = new AbilityLore(new ArrayList<>(List.of("§7Combines the Tiered Bonuses of", "§7wearing §a%p& pieces §7of the", "§7Melon Armor, Cropie Armor, and", "§7Squash Armor.", "§7Grants " + "§6%ff% " + Stats.FarmingFortune.getSymbol() + " " + Stats.FarmingFortune.getName() + "§7.")));
        lore.addPlaceholder("%p%", (p, itemStack) -> {
            if (p == null) return "0";
            if (!p.bonusAmounts.containsKey(Bonuses.Feast)) return "0";
            return p.bonusAmounts.get(Bonuses.Feast) + "";
        });
        lore.addPlaceholder("%ff%", (p, itemStack) -> getFarmingFortune(p) + "");
        ItemManager manager = new ItemManager("Fermento Helmet", "FERMENTO_HELMET", ItemType.Helmet, ItemRarity.LEGENDARY, "http://textures.minecraft.net/texture/5086ddbe960f33480ca229da7402391ab417d32ebb21770430ea610de5801fe3");
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.Feast, "Feast", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 40));
        manager.setStat(Stats.Health, 130);
        manager.setStat(Stats.Defense, 40);
        manager.setStat(Stats.Speed, 5);
        manager.setStat(Stats.FarmingFortune, 30);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Fermento Chestplate", "FERMENTO_CHESTPLATE", ItemType.Chestplate, Material.LEATHER_CHESTPLATE, Color.fromRGB(0x58890C), ItemRarity.LEGENDARY);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.Feast, "Feast", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 40));
        manager.setStat(Stats.Health, 195);
        manager.setStat(Stats.Defense, 40);
        manager.setStat(Stats.Speed, 6);
        manager.setStat(Stats.FarmingFortune, 35);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Fermento Leggings", "FERMENTO_LEGGINGS", ItemType.Leggings, Material.LEATHER_LEGGINGS, Color.fromRGB(0x6A9C1B), ItemRarity.LEGENDARY);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.Feast, "Feast", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 40));
        manager.setStat(Stats.Health, 195);
        manager.setStat(Stats.Defense, 40);
        manager.setStat(Stats.Speed, 6);
        manager.setStat(Stats.FarmingFortune, 35);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Fermento Boots", "FERMENTO_BOOTS", ItemType.Boots, Material.LEATHER_BOOTS, Color.fromRGB(0x83B03B), ItemRarity.LEGENDARY);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.Feast, "Feast", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 40));
        manager.setStat(Stats.Health, 130);
        manager.setStat(Stats.Defense, 40);
        manager.setStat(Stats.Speed, 5);
        manager.setStat(Stats.FarmingFortune, 30);
        Items.SkyblockItems.put(manager.itemID, manager);
    }

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
        return 2;
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
        return Bonuses.Feast;
    }

    private static double getFermentoChance(SkyblockPlayer player) {
        if (player == null) return 0;
        if (!player.bonusAmounts.containsKey(Bonuses.Feast)) return 0;
        switch (player.bonusAmounts.get(Bonuses.Feast)) {
            case 2 -> {
                return 0.00005;
            }
            case 3 -> {
                return 0.00006;
            }
            case 4 -> {
                return 0.00007;
            }
            default -> {
                return 0;
            }
        }
    }

    private static double getSquashChance(SkyblockPlayer player) {
        if (player == null) return 0;
        if (!player.bonusAmounts.containsKey(Bonuses.Feast)) return 0;
        switch (player.bonusAmounts.get(Bonuses.Feast)) {
            case 2 -> {
                return 0.0001;
            }
            case 3 -> {
                return 0.0002;
            }
            case 4 -> {
                return 0.0003;
            }
            default -> {
                return 0;
            }
        }
    }
    private static double getCropieChance(SkyblockPlayer player){
        if(player == null) return 0;
        if(!player.bonusAmounts.containsKey(Bonuses.Feast)) return 0;
        switch (player.bonusAmounts.get(Bonuses.Feast)){
            case 2 -> {
                return 0.0003;
            }
            case 3 -> {
                return 0.0004;
            }
            case 4 -> {
                return 0.0005;
            }
            default -> {
                return 0;
            }
        }
    }

    private static int getFarmingFortune(SkyblockPlayer player) {
        if (player == null) return 0;
        if (!player.bonusAmounts.containsKey(Bonuses.Feast)) return 0;
        return (player.bonusAmounts.get(Bonuses.Feast) * 25) - 25;
    }

    private static final Set<Material> fermentoCrops = new HashSet<>(Set.of(Material.SUGAR_CANE, Material.CACTUS, Material.MUSHROOM_STEM, Material.BROWN_MUSHROOM_BLOCK, Material.RED_MUSHROOM_BLOCK, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM, Material.NETHER_WART));
    private static final Set<Material> squashCrops = new HashSet<>(Set.of(Material.MELON, Material.PUMPKIN, Material.COCOA));
    private static final Set<Material> cropieCrops = new HashSet<>(Set.of(Material.POTATOES, Material.CARROTS, Material.WHEAT));
    @EventHandler
    private void farmEvent(PlayerFarmEvent event) {
        if (!players.contains(event.getPlayer())) return;
        if (fermentoCrops.contains(event.getBlock().getType()))
            if (new Random().nextDouble() <= getFermentoChance(event.getPlayer())) {
                event.getPlayer().sendMessage("§6§lRARE DROP! " + ItemRarity.LEGENDARY.getPrefix() + "Fermento §b(Armor Set Bonus)");
                event.getPlayer().addItem(Items.SkyblockItems.get("FERMENTO"));
            }
        if (squashCrops.contains(event.getBlock().getType()))
            if (new Random().nextDouble() <= getSquashChance(event.getPlayer())) {
                event.getPlayer().sendMessage("§6§lRARE DROP! " + ItemRarity.EPIC.getPrefix() + "Squash §b(Armor Set Bonus)");
                event.getPlayer().addItem(Items.SkyblockItems.get("SQUASH"));
            }
        if (cropieCrops.contains(event.getBlock().getType()))
            if (new Random().nextDouble() <= getCropieChance(event.getPlayer())) {
                event.getPlayer().sendMessage("§6§lRARE DROP! " + ItemRarity.RARE.getPrefix() + "Cropie §b(Armor Set Bonus)");
                event.getPlayer().addItem(Items.SkyblockItems.get("CROPIE"));
            }
    }

    @EventHandler
    private void statEvent(GetTotalStatEvent event) {
        if (!players.contains(event.getPlayer())) return;
        event.setValue(event.getValue() + getFarmingFortune(player));
    }
}
