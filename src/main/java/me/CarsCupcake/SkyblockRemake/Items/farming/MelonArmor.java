package me.CarsCupcake.SkyblockRemake.Items.farming;

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

public class MelonArmor implements FullSetBonus, Listener {
    private final static Set<SkyblockPlayer>  players = new HashSet<>();
    private SkyblockPlayer player;
    public static void init(){
        AbilityLore lore = new AbilityLore(new ArrayList<>(List.of(
                "§7Farming Wheat, Carrot, and",
                "§7Potatos have a §a%chance% §7chance",
                "§7of fropping a Cropie. Grants",
                "§6%ff% " + Stats.FarmingFortune.getSymbol() + " " + Stats.FarmingFortune.getName() + "§7."
        )));
        lore.addPlaceholder("%chance%", (p, itemStack) -> (getChance(p) * 100) + "%");
        lore.addPlaceholder("%ff%", (p, itemStack) -> getFarmingFortune(p) + "");
        ItemManager manager = new ItemManager("Melon Helmet", "MELON_HELMET", ItemType.Helmet, Material.MELON, ItemRarity.UNCOMMON);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.CropierCrops, "Cropier Crop", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 25));
        manager.setStat(Stats.Health, 100);
        manager.setStat(Stats.Defense, 25);
        manager.setStat(Stats.Speed, 2);
        manager.setStat(Stats.FarmingFortune, 15);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Melon Chestplate", "MELON_CHESTPLATE", ItemType.Chestplate, Material.LEATHER_CHESTPLATE, Color.fromRGB(0x899E20), ItemRarity.UNCOMMON);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.CropierCrops, "Cropier Crop", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 25));
        manager.setStat(Stats.Health, 150);
        manager.setStat(Stats.Defense, 25);
        manager.setStat(Stats.Speed, 3);
        manager.setStat(Stats.FarmingFortune, 20);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Melon Leggings", "MELON_LEGGINGS", ItemType.Leggings, Material.LEATHER_LEGGINGS, Color.fromRGB(0x899E20), ItemRarity.UNCOMMON);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.CropierCrops, "Cropier Crop", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 25));
        manager.setStat(Stats.Health, 150);
        manager.setStat(Stats.Defense, 25);
        manager.setStat(Stats.Speed, 3);
        manager.setStat(Stats.FarmingFortune, 20);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Melon Boots", "MELON_BOOTS", ItemType.Boots, Material.LEATHER_BOOTS, Color.fromRGB(0x899E20), ItemRarity.UNCOMMON);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.CropierCrops, "Cropier Crop", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 25));
        manager.setStat(Stats.Health, 100);
        manager.setStat(Stats.Defense, 25);
        manager.setStat(Stats.Speed, 2);
        manager.setStat(Stats.FarmingFortune, 15);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Cropie", "CROPIE", ItemType.Non, ItemRarity.RARE, "http://textures.minecraft.net/texture/dd01cba23ede2cd2895107f0c0258e971d2485538fe9649ef2853bd26e6232dc");
        manager.setNpcSellPrice(500);
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
        return Bonuses.CropierCrops;
    }
    private static double getChance(SkyblockPlayer player){
        if(player == null) return 0;
        if(!player.bonusAmounts.containsKey(Bonuses.CropierCrops)) return 0;
        switch (player.bonusAmounts.get(Bonuses.CropierCrops)){
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
    private static int getFarmingFortune(SkyblockPlayer player){
        if(player == null) return 0;
        if(!player.bonusAmounts.containsKey(Bonuses.CropierCrops)) return 0;
        return (player.bonusAmounts.get(Bonuses.CropierCrops) * 10) - 10;
    }
    private static final Set<Material> crops = new HashSet<>(Set.of(Material.POTATOES, Material.CARROTS, Material.WHEAT));
    @EventHandler
    private void farmEvent(PlayerFarmEvent event){
        if(!players.contains(event.getPlayer())) return;
        if(!crops.contains(event.getBlock().getType())) return;
        if(new Random().nextDouble() <= getChance(event.getPlayer())) {
            event.getPlayer().sendMessage("§6§lRARE DROP! " + ItemRarity.RARE.getPrefix() + "Cropie §b(Armor Set Bonus)");
            event.getPlayer().addItem(Items.SkyblockItems.get("CROPIE"));
        }
    }

    @EventHandler
    private void statEvent(GetTotalStatEvent event){
        if(!players.contains(event.getPlayer())) return;
        event.setValue(event.getValue() + getFarmingFortune(player));
    }
}
