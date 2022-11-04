package me.CarsCupcake.SkyblockRemake.Items;

import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.Color;
import org.bukkit.Material;

public class WitherSets {
    public WitherSets(){
        ItemManager manager = new ItemManager("Wither Helmet","WITHER_HELMET",ItemType.Helmet, null,"Witherborn","witherborn",null,0,0,0,0,ItemRarity.LEGENDARY,"https://textures.minecraft.net/texture/5c5faf218c78e0a847ac4ec1a688fe79e1a7ad13ae3c3b68b7fc4436320cdac4");
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,200);
        manager.setStat(Stats.Defense,100);
        manager.setStat(Stats.Inteligence,30);
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = new ItemManager("Wither Chestplate","WITHER_CHESTPLATE",ItemType.Chestplate, null,"Witherborn","witherborn",null,0,0,0,0, Material.LEATHER_CHESTPLATE, Color.BLACK,ItemRarity.LEGENDARY);
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,300);
        manager.setStat(Stats.Defense,250);
        manager.setStat(Stats.Inteligence,10);
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = new ItemManager("Wither Leggings","WITHER_LEGGIGNS",ItemType.Leggings, null,"Witherborn","witherborn",null,0,0,0,0,Material.LEATHER_LEGGINGS, Color.BLACK,ItemRarity.LEGENDARY);
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,250);
        manager.setStat(Stats.Defense,220);
        manager.setStat(Stats.Inteligence,10);
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = new ItemManager("Wither Boots","WITHER_BOOTS",ItemType.Boots, null,"Witherborn","witherborn",null,0,0,0,0,Material.LEATHER_BOOTS, Color.BLACK,ItemRarity.LEGENDARY);
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,180);
        manager.setStat(Stats.Defense,150);
        manager.setStat(Stats.Inteligence,10);
        Items.SkyblockItems.put(manager.itemID, manager);


        manager = new ItemManager("Necron's Helmet","POWER_WITHER_HELMET",ItemType.Helmet, null,"Witherborn","witherborn",null,0,0,0,0,ItemRarity.LEGENDARY,"http://textures.minecraft.net/texture/2bbb2fa7a6ca087280ea0cb564b41ef1ae404a19f7a8128d3d28c519a85e063f");
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,180);
        manager.setStat(Stats.Defense,100);
        manager.setStat(Stats.Inteligence,30);
        manager.setStat(Stats.Strength, 40);
        manager.setStat(Stats.CritDamage,30);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = new ItemManager("Necron's Chestplate","POWER_WITHER_CHESTPLATE",ItemType.Chestplate, null,"Witherborn","witherborn",null,0,0,0,0, Material.LEATHER_CHESTPLATE, Color.fromRGB(0xE7413C),ItemRarity.LEGENDARY);
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,260);
        manager.setStat(Stats.Defense,140);
        manager.setStat(Stats.Inteligence,10);
        manager.setStat(Stats.Strength, 40);
        manager.setStat(Stats.CritDamage,30);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = new ItemManager("Necron's Leggings","POWER_WITHER_LEGGINGS",ItemType.Leggings, null,"Witherborn","witherborn",null,0,0,0,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0xE75C3C),ItemRarity.LEGENDARY);
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,230);
        manager.setStat(Stats.Defense,125);
        manager.setStat(Stats.Inteligence,10);
        manager.setStat(Stats.Strength, 40);
        manager.setStat(Stats.CritDamage,30);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = new ItemManager("Necron's Boots","POWER_WITHER_BOOTS",ItemType.Boots, null,"Witherborn","witherborn",null,0,0,0,0,Material.LEATHER_BOOTS, Color.fromRGB(0xE76E3C),ItemRarity.LEGENDARY);
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,145);
        manager.setStat(Stats.Defense,80);
        manager.setStat(Stats.Inteligence,10);
        manager.setStat(Stats.Strength, 40);
        manager.setStat(Stats.CritDamage,30);
        manager.addSlot(new GemstoneSlot(SlotType.Jasper));
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        Items.SkyblockItems.put(manager.itemID, manager);



        manager = new ItemManager("Maxor's Helmet","SPEED_WITHER_HELMET",ItemType.Helmet, null,"Witherborn","witherborn",null,0,0,0,0,ItemRarity.LEGENDARY,"https://textures.minecraft.net/texture/4d50a8fd7a1dd471e8f9292f1283e99c5e52a5186302bff3a93f807fa9b442a4");
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,180);
        manager.setStat(Stats.Defense,80);
        manager.setStat(Stats.Inteligence,30);
        manager.setStat(Stats.Speed, 15);
        manager.setStat(Stats.CritDamage,45);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = new ItemManager("Maxor's Chestplate","SPEED_WITHER_CHESTPLATE",ItemType.Chestplate, null,"Witherborn","witherborn",null,0,0,0,0, Material.LEATHER_CHESTPLATE, Color.fromRGB(0x4A14B7),ItemRarity.LEGENDARY);
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,260);
        manager.setStat(Stats.Defense,110);
        manager.setStat(Stats.Inteligence,10);
        manager.setStat(Stats.Speed, 30);
        manager.setStat(Stats.CritDamage,45);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        Items.SkyblockItems.put(manager.itemID, manager);
        manager = new ItemManager("Maxor's Leggings","SPEED_WITHER_LEGGINGS",ItemType.Leggings, null,"Witherborn","witherborn",null,0,0,0,0,Material.LEATHER_LEGGINGS, Color.fromRGB(0x5D2FB9),ItemRarity.LEGENDARY);
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,230);
        manager.setStat(Stats.Defense,105);
        manager.setStat(Stats.Inteligence,10);
        manager.setStat(Stats.Speed, 30);
        manager.setStat(Stats.CritDamage,45);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        Items.SkyblockItems.put(manager.itemID, manager);


        manager = new ItemManager("Maxor's Boots","SPEED_WITHER_BOOTS",ItemType.Boots, null,"Witherborn","witherborn",null,0,0,0,0,Material.LEATHER_BOOTS, Color.fromRGB(0x8969C8),ItemRarity.LEGENDARY);
        manager.setDungeonItem(true);
        manager.setStat(Stats.Health,145);
        manager.setStat(Stats.Defense,65);
        manager.setStat(Stats.Inteligence,10);
        manager.setStat(Stats.Speed, 30);
        manager.setStat(Stats.CritDamage,45);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.addSlot(new GemstoneSlot(SlotType.Combat));
        Items.SkyblockItems.put(manager.itemID, manager);


    }

}
