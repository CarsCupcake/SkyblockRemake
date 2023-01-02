package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Crafting.CraftingObject;
import me.CarsCupcake.SkyblockRemake.Crafting.ShapeEncoder;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockRecipe;
import me.CarsCupcake.SkyblockRemake.Crafting.SkyblockShapedRecipe;
import me.CarsCupcake.SkyblockRemake.End.EndItems;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.UUID;

public class ProtectiveBlood implements FullSetBonus, Listener {
    private SkyblockPlayer player;
    private static final ArrayList<SkyblockPlayer> actives = new ArrayList<>();
    @Override
    public void start() {
        actives.add(player);
    }

    @Override
    public void stop() {
        actives.remove(player);
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
        return Bonuses.ProtectiveBlood;
    }
    @EventHandler
    public void onDefensItem(GetStatFromItemEvent event){
        if (event.getStat() != Stats.Defense)
            return;
        if(event.getPlayer() == null)
            return;
        if (!actives.contains(event.getPlayer()))
            return;
        if(event.getItem() == null || event.getItem().getItemMeta() == null)
            return;
        String id = event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING);
        if(id.equals(EndItems.Items.ProtectorHelmet.getId()) || id.equals(EndItems.Items.ProtectorChestplate.getId())
                || id.equals(EndItems.Items.ProtectorLeggings.getId()) || id.equals(EndItems.Items.ProtectorBoots.getId())){
            double hpPers = player.currhealth / Main.playerhealthcalc(event.getPlayer());
            hpPers -= 1;
            hpPers *= -1;
            hpPers = Tools.round(hpPers, 2);
            event.setValue(event.getValue() + (event.getValue() * hpPers));
        }
    }

    public static void addItems(){
        ItemManager manager = protectorDragonFragment();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorLeggings();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorHelmet();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorChestplate();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
        manager = protectorBoots();
        me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.put(manager.itemID, manager);
    }

    private static ItemManager protectorDragonFragment(){
        ItemManager manager = new ItemManager("Protector Dragon Fragment", "PROTECTOR_FRAGMENT", ItemType.Non, null, null, null, null, 0, 0,0,0, ItemRarity.EPIC
                ,"https://textures.minecraft.net/texture/48de339af63a229c9238d027e47f53eeb56141a419f51b35c31ea1494b435dd3", UUID.fromString("86541f16-3bdf-4d08-871a-dcdf8ba373f5"));
        return manager;
    }
    private static ItemManager protectorHelmet(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Increases the defense of each");
        abilityLore.add("§7armor piece by §a+1% ❈");
        abilityLore.add("§aDefense §7for each missing");
        abilityLore.add("§7percent of HP.");
        ItemManager manager = new ItemManager("Protector Dragon Helmet", "PROTECTOR_DRAGON_HELMET", ItemType.Helmet, null,
                "Protective Blood", null, null, 0, 0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/f37a596cdc4b11a9948ffa38c2aa3c6942ef449eb0a3982281d3a5b5a14ef6ae");
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 70);
        manager.setStat(Stats.Defense, 135);
        manager.addSlot(new GemstoneSlot(SlotType.Amethyst));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.ProtectiveBlood, "Protective Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("PROTECTOR_DRAGON_HELMET", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "   ");
        encoder.setKey('s', new CraftingObject(EndItems.Items.ProtectorDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorChestplate(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Increases the defense of each");
        abilityLore.add("§7armor piece by §a+1% ❈");
        abilityLore.add("§aDefense §7for each missing");
        abilityLore.add("§7percent of HP.");
        ItemManager manager = new ItemManager("Protector Dragon Chestplate", "PROTECTOR_DRAGON_CHESTPLATE", ItemType.Chestplate, null,
                "Protective Blood", null, null, 0, 0,0,0, Material.LEATHER_CHESTPLATE, Color.fromRGB(0x99978B),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 120);
        manager.setStat(Stats.Defense, 185);
        manager.addSlot(new GemstoneSlot(SlotType.Amethyst));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.ProtectiveBlood, "Protective Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("PROTECTOR_DRAGON_CHESTPLATE", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("s s", "sss", "sss");
        encoder.setKey('s', new CraftingObject(EndItems.Items.ProtectorDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorLeggings(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Increases the defense of each");
        abilityLore.add("§7armor piece by §a+1% ❈");
        abilityLore.add("§aDefense §7for each missing");
        abilityLore.add("§7percent of HP.");
        ItemManager manager = new ItemManager("Protector Dragon Leggings", "PROTECTOR_DRAGON_LEGGINGS", ItemType.Leggings, null,
                "Protective Blood", null, null, 0, 0,0,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0x99978B),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 100);
        manager.setStat(Stats.Defense, 165);
        manager.addSlot(new GemstoneSlot(SlotType.Amethyst));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.ProtectiveBlood,"Protective Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("PROTECTOR_DRAGON_LEGGINGS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("sss", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.ProtectorDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }
    private static ItemManager protectorBoots(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Increases the defense of each");
        abilityLore.add("§7armor piece by §a+1% ❈");
        abilityLore.add("§aDefense §7for each missing");
        abilityLore.add("§7percent of HP.");
        ItemManager manager = new ItemManager("Protector Dragon Boots", "PROTECTOR_DRAGON_BOOTS", ItemType.Boots, null,
                "Protective Blood", null, null, 0, 0,0,0,Material.LEATHER_BOOTS, Color.fromRGB(0x99978B),ItemRarity.LEGENDARY);
        manager.setNpcSellPrice(100000);
        manager.setStat(Stats.Health, 100);
        manager.setStat(Stats.Defense, 165);
        manager.addSlot(new GemstoneSlot(SlotType.Amethyst));
        AbilityLore lore = new AbilityLore(abilityLore);
        manager.setFullSetBonus(Bonuses.ProtectiveBlood, "Protective Blood");
        manager.setAbilityLore(lore);
        manager.setUnstackeble(true);

        SkyblockShapedRecipe recipe = new SkyblockShapedRecipe("PROTECTOR_DRAGON_BOOTS", manager, 1);
        ShapeEncoder encoder = new ShapeEncoder("   ", "s s", "s s");
        encoder.setKey('s', new CraftingObject(EndItems.Items.ProtectorDragonFragment.getItem(), 10));
        recipe.setRecipe(encoder.encode());
        SkyblockRecipe.recipes.add(recipe);

        return manager;
    }

}
