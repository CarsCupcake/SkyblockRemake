package me.CarsCupcake.SkyblockRemake.Items.farming.items.armor;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.PlayerFarmEvent;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Items.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Items.Crafting.ShapeEncoder;
import me.CarsCupcake.SkyblockRemake.Items.Crafting.SkyblockShapedRecipe;
import me.CarsCupcake.SkyblockRemake.Items.requirements.SkillRequirement;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.*;

public class SquashArmor implements FullSetBonus, Listener {
    private final static Set<SkyblockPlayer>  players = new HashSet<>();
    private SkyblockPlayer player;
    public static void init(){
        AbilityLore lore = new AbilityLore(new ArrayList<>(List.of(
                "§7Farming Sugar Cane, Cactus,",
                "§7Mushroom, and Nether Warts have",
                "§a%chance% §7chance of dropping",
                "§7a Fermento. Grants " +
                "§6%ff% " + Stats.FarmingFortune.getSymbol() + " " + Stats.FarmingFortune.getName() + "§7."
        )));
        lore.addPlaceholder("%chance%", (p, itemStack) -> (getChance(p) * 100) + "%");
        lore.addPlaceholder("%ff%", (p, itemStack) -> getFarmingFortune(p) + "");
        ItemManager manager = new ItemManager("Squash Helmet", "SQUASH_HELMET", ItemType.Helmet, ItemRarity.EPIC, "http://textures.minecraft.net/texture/de1087c0c519a9a1dcee4325410b19a1be4855eac5a662ae1b523329f90faecd");
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.MentoFermento, "Mento Fermento", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 35));
        manager.setStat(Stats.Health, 120);
        manager.setStat(Stats.Defense, 35);
        manager.setStat(Stats.Speed, 4);
        manager.setStat(Stats.FarmingFortune, 25);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Squash Chestplate", "SQUASH_CHESTPLATE", ItemType.Chestplate, Material.LEATHER_CHESTPLATE, Color.fromRGB(0x03430E), ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.MentoFermento, "Mento Fermento", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 35));
        manager.setStat(Stats.Health, 180);
        manager.setStat(Stats.Defense, 35);
        manager.setStat(Stats.Speed, 5);
        manager.setStat(Stats.FarmingFortune, 30);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Squash Leggings", "SQUASH_LEGGINGS", ItemType.Leggings, Material.LEATHER_LEGGINGS, Color.fromRGB(0x0C4A16), ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.MentoFermento, "Mento Fermento", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 35));
        manager.setStat(Stats.Health, 180);
        manager.setStat(Stats.Defense, 35);
        manager.setStat(Stats.Speed, 5);
        manager.setStat(Stats.FarmingFortune, 30);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Squash Boots", "SQUASH_BOOTS", ItemType.Boots, Material.LEATHER_BOOTS, Color.fromRGB(0x13561E), ItemRarity.EPIC);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.MentoFermento, "Mento Fermento", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 35));
        manager.setStat(Stats.Health, 120);
        manager.setStat(Stats.Defense, 35);
        manager.setStat(Stats.Speed, 4);
        manager.setStat(Stats.FarmingFortune, 25);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Fermento", "FERMENTO", ItemType.Non, ItemRarity.LEGENDARY, "http://textures.minecraft.net/texture/cb41daeb57d2ae62c66e58eb6debb2a7d446e34541a771350728c9db15beafba");
        manager.setNpcSellPrice(1000);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Condensed Fermento", "CONDENSED_FERMENTO", ItemType.Non, ItemRarity.LEGENDARY, "http://textures.minecraft.net/texture/8233d3a70d82eee1caaa58c16b83ee6224e8bfc7cda636793853e041a1fbaa6b");
        manager.setNpcSellPrice(9000);
        Items.SkyblockItems.put(manager.itemID, manager);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("CONDENSED_FERMENTO", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "sss", "sss");
        encoder.setKey('s', new CraftingObject(Items.SkyblockItems.get("FERMENTO"), 1));
        recipe.setRecipe(encoder.encode());
        recipe.register();
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
        return Bonuses.MentoFermento;
    }
    private static double getChance(SkyblockPlayer player){
        if(player == null) return 0;
        if(!player.bonusAmounts.containsKey(Bonuses.MentoFermento)) return 0;
        switch (player.bonusAmounts.get(Bonuses.MentoFermento)){
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
    private static int getFarmingFortune(SkyblockPlayer player){
        if(player == null) return 0;
        if(!player.bonusAmounts.containsKey(Bonuses.MentoFermento)) return 0;
        return (player.bonusAmounts.get(Bonuses.MentoFermento) * 20) - 20;
    }
    private static final Set<Material> crops = new HashSet<>(Set.of(Material.SUGAR_CANE, Material.CACTUS, Material.MUSHROOM_STEM, Material.BROWN_MUSHROOM_BLOCK, Material.RED_MUSHROOM_BLOCK, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM, Material.NETHER_WART));
    @EventHandler
    private void farmEvent(PlayerFarmEvent event){
        if(!players.contains(event.getPlayer())) return;
        if(!crops.contains(event.getBlock().getType())) return;
        if(new Random().nextDouble() <= getChance(event.getPlayer())) {
            event.getPlayer().sendMessage("§6§lRARE DROP! " + ItemRarity.LEGENDARY.getPrefix() + "Fermento §b(Armor Set Bonus)");
            event.getPlayer().addItem(Items.SkyblockItems.get("FERMENTO"));
        }
    }

    @EventHandler
    private void statEvent(GetTotalStatEvent event){
        if(!players.contains(event.getPlayer())) return;
        event.setValue(event.getValue() + getFarmingFortune(player));
    }
}
