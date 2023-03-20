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

public class CropieArmor implements FullSetBonus, Listener {
    private final static Set<SkyblockPlayer>  players = new HashSet<>();
    private SkyblockPlayer player;
    public static void init(){
        AbilityLore lore = new AbilityLore(new ArrayList<>(List.of(
                "§7Farming Pumpkin, Melon, and",
                "§7Cocoa Beans have a §a%chance% §7chance",
                "§7of fropping a Cropie. Grants",
                "§6%ff% " + Stats.FarmingFortune.getSymbol() + " " + Stats.FarmingFortune.getName() + "§7."
        )));
        lore.addPlaceholder("%chance%", (p, itemStack) -> (getChance(p) * 100) + "%");
        lore.addPlaceholder("%ff%", (p, itemStack) -> getFarmingFortune(p) + "");
        ItemManager manager = new ItemManager("Cropie Helmet", "CROPIE_HELMET", ItemType.Helmet, ItemRarity.RARE, "http://textures.minecraft.net/texture/e4bacb96734e244b9f7331d453e686fa2e32522a411aa568f960e741d74b3289");
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.Squashbuckle, "Squashbuckle", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 30));
        manager.setStat(Stats.Health, 110);
        manager.setStat(Stats.Defense, 25);
        manager.setStat(Stats.Speed, 4);
        manager.setStat(Stats.FarmingFortune, 20);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Cropie Chestplate", "CROPIE_CHESTPLATE", ItemType.Chestplate, Material.LEATHER_CHESTPLATE, Color.fromRGB(0x7A2900), ItemRarity.RARE);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.Squashbuckle, "Squashbuckle", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 30));
        manager.setStat(Stats.Health, 165);
        manager.setStat(Stats.Defense, 25);
        manager.setStat(Stats.Speed, 4);
        manager.setStat(Stats.FarmingFortune, 25);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Cropie Leggings", "CROPIE_LEGGINGS", ItemType.Leggings, Material.LEATHER_LEGGINGS, Color.fromRGB(0x94451F), ItemRarity.RARE);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.Squashbuckle, "Squashbuckle", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 30));
        manager.setStat(Stats.Health, 165);
        manager.setStat(Stats.Defense, 25);
        manager.setStat(Stats.Speed, 4);
        manager.setStat(Stats.FarmingFortune, 25);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Cropie Boots", "CROPIE_BOOTS", ItemType.Boots, Material.LEATHER_BOOTS, Color.fromRGB(0xBB6535), ItemRarity.RARE);
        manager.setUnstackeble(true);
        manager.setFullSetBonus(Bonuses.Squashbuckle, "Squashbuckle", lore, true);
        manager.setRequirement(new SkillRequirement(Skills.Farming, 30));
        manager.setStat(Stats.Health, 110);
        manager.setStat(Stats.Defense, 25);
        manager.setStat(Stats.Speed, 4);
        manager.setStat(Stats.FarmingFortune, 20);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Squash", "SQUASH", ItemType.Non, ItemRarity.EPIC, "http://textures.minecraft.net/texture/36ae076649ef22f60e8511831c68fd2b6ea63c32164dab33a8aebc18ff2a54c8");
        manager.setNpcSellPrice(750);
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
    private static int getFarmingFortune(SkyblockPlayer player){
        if(player == null) return 0;
        if(!player.bonusAmounts.containsKey(Bonuses.CropierCrops)) return 0;
        return (player.bonusAmounts.get(Bonuses.CropierCrops) * 15) - 15;
    }
    private static final Set<Material> crops = new HashSet<>(Set.of(Material.MELON, Material.PUMPKIN, Material.COCOA));
    @EventHandler
    private void farmEvent(PlayerFarmEvent event){
        if(!players.contains(event.getPlayer())) return;
        if(!crops.contains(event.getBlock().getType())) return;
        if(new Random().nextDouble() <= getChance(event.getPlayer())) {
            event.getPlayer().sendMessage("§6§lRARE DROP! " + ItemRarity.EPIC.getPrefix() + "Squash §b(Armor Set Bonus)");
            event.getPlayer().addItem(Items.SkyblockItems.get("SQUASH"));
        }
    }

    @EventHandler
    private void statEvent(GetTotalStatEvent event){
        if(!players.contains(event.getPlayer())) return;
        event.setValue(event.getValue() + getFarmingFortune(player));
    }
}
