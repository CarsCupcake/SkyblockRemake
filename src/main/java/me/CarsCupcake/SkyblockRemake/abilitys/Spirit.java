package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;

public class Spirit implements FullSetBonus, Listener {
    private static HashMap<SkyblockPlayer, Spirit> effects = new HashMap<>();
    private BukkitRunnable stackRunner;
    private SkyblockPlayer player;
    private int stackRuntime;
    private int stacks = 0;

    @Override
    public void start() {
        effects.put(player, this);
    }

    @Override
    public void stop() {
        try{
            stackRunner.cancel();
        }catch (Exception ignored){}
        stacks = 0;
        Main.updatebar(player);
        effects.remove(player);

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
        return Bonuses.Spirit;
    }

    public void setKill(EntityDeathEvent event) {

        if(player.bonusAmounts.get(Bonuses.Spirit) == 2)
            stackRuntime = 4;
        else if(player.bonusAmounts.get(Bonuses.Spirit) == 3)
            stackRuntime = 7;
        else
            stackRuntime = 10;

        if(stackRunner != null )
            try {
                stackRunner.cancel();
            }catch (Exception e) {

            }
        stackRunner = new BukkitRunnable() {

            @Override
            public void run() {

                stackRuntime -= 1;

                if(stackRuntime == 0) {
                    stacks -= 1;
                    Main.updatebar(player);
                    if(stacks == 0)
                        stackRunner.cancel();
                    else {
                        if(player.bonusAmounts.get(getBonus()) == 2)
                            stackRuntime = 4;
                        else if(player.bonusAmounts.get(getBonus()) == 3)
                            stackRuntime = 7;
                        else
                            stackRuntime = 10;
                    }

                }

            }
        };
        stackRunner.runTaskTimer(Main.getMain(), 0, 20);


        Main.updatebar(player);
        int maxStacks = player.bonusAmounts.get(getBonus()) * 10 + 10;

        if(stacks < maxStacks)
            stacks += 1;


    }

    @EventHandler
    public void onDeath(EntityDeathEvent event){
        if(event.getEntity().getKiller() != null && effects.containsKey(SkyblockPlayer.getSkyblockPlayer(event.getEntity().getKiller()))){
            effects.get(SkyblockPlayer.getSkyblockPlayer(event.getEntity().getKiller())).setKill(event);
        }
    }
    public static int getStacks(SkyblockPlayer player){
        if(!effects.containsKey(player))
            return 0;
        else
            return effects.get(player).stacks;
    }
    public static int getMaxStacks(SkyblockPlayer player){
        if(!effects.containsKey(player))
            return 30;
        else
            return player.bonusAmounts.get(Bonuses.Spirit) * 10 + 10;
    }
    public static boolean isMaxStacks(SkyblockPlayer player, int stacks){
        return stacks >= getMaxStacks(player);
    }
    public static void addArmorSets(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Each kill by you or a recently supported");
        abilityLore.add("§7player grants §c1 §7stack of §6Spirit");
        abilityLore.add("§6⚶§7.§8(Max %stack%)");
        abilityLore.add(" ");
        abilityLore.add("§7Each §6Spirit ⚶ §7stack can be used as");
        abilityLore.add("§7charge for the §6Hollow Wand");
        abilityLore.add(" ");
        abilityLore.add("§7Lose 1 stack after §c%time%s §7of not gaining");
        abilityLore.add("§7a stack.");
        AbilityLore lore = new AbilityLore(abilityLore);
        lore.addPlaceholder(Placeholder.stacks.getHolder(), Placeholder.stacks.getFlag());
        lore.addPlaceholder(Placeholder.time.getHolder(), Placeholder.time.getFlag());
        helmets(lore);
        chestplates(lore);
        leggings(lore);
        boots(lore);
    }
    private static void helmets(AbilityLore lore){
        ItemManager manager = new ItemManager("Hollow Helmet" , "HOLLOW_HELMET", ItemType.Helmet, null, "Spirit" , "Spirit", null,0,0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/4aacd705a29b790528a70ee93a1cbd92c029601a762d52805f60460e7a7ff8f7");
        manager.setUnstackeble(true);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 160);
        manager.setStat(Stats.Defense, 50);
        manager.setStat(Stats.Strength, 15);
        manager.setStat(Stats.Inteligence, 130);
        manager.setStat(Stats.Speed, 5);
        manager.setStat(Stats.CritDamage, 10);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Hot Hollow Helmet" , "HOT_HOLLOW_HELMET", ItemType.Helmet, null, "Spirit" , "Spirit", null,0,0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/cc344d65ae6bc2230ee220d76dda1e080f90282ca0f9f0d9bc9667d22048ce9f");
        manager.setUnstackeble(true);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 202);
        manager.setStat(Stats.Defense, 63);
        manager.setStat(Stats.Strength, 19);
        manager.setStat(Stats.Inteligence, 164);
        manager.setStat(Stats.Speed, 6);
        manager.setStat(Stats.CritDamage, 13);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Burning Hollow Helmet" , "BURNING_HOLLOW_HELMET", ItemType.Helmet, null, "Spirit" , "Spirit", null,0,0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/92aa17c32d27b92816b00362c2f69d867cd99ba376d10b097796368287834ea1");
        manager.setUnstackeble(true);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 256);
        manager.setStat(Stats.Defense, 79);
        manager.setStat(Stats.Strength, 24);
        manager.setStat(Stats.Inteligence, 206);
        manager.setStat(Stats.Speed, 8);
        manager.setStat(Stats.CritDamage, 16);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Fiery Hollow Helmet" , "FIERY_HOLLOW_HELMET", ItemType.Helmet, null, "Spirit" , "Spirit", null,0,0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/28c6bdbff6f033c61bc335dd9a78956e6cdfdd613ebb5a77ec38d271a8332612");
        manager.setUnstackeble(true);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 320);
        manager.setStat(Stats.Defense, 100);
        manager.setStat(Stats.Strength, 30);
        manager.setStat(Stats.Inteligence, 260);
        manager.setStat(Stats.Speed, 10);
        manager.setStat(Stats.CritDamage, 20);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Infernal Hollow Helmet" , "INFERNAL_HOLLOW_HELMET", ItemType.Helmet, null, "Spirit" , "Spirit", null,0,0,0,0, ItemRarity.LEGENDARY,
                "https://textures.minecraft.net/texture/84eee9f23952f9777d0519c111655066851fb4c1793629d3767a00dacee6f250");
        manager.setUnstackeble(true);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 403);
        manager.setStat(Stats.Defense, 126);
        manager.setStat(Stats.Strength, 38);
        manager.setStat(Stats.Inteligence, 328);
        manager.setStat(Stats.Speed, 13);
        manager.setStat(Stats.CritDamage, 25);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);
    }
    private static void chestplates(AbilityLore lore){
        ItemManager manager = new ItemManager("Hollow Chestplate" , "HOLLOW_CHESTPLATE", ItemType.Chestplate, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFFCB0D), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 230);
        manager.setStat(Stats.Defense, 65);
        manager.setStat(Stats.Strength, 15);
        manager.setStat(Stats.Inteligence, 85);
        manager.setStat(Stats.Speed, 5);
        manager.setStat(Stats.CritDamage, 10);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Hot Hollow Chestplate" , "HOT_HOLLOW_CHESTPLATE", ItemType.Chestplate, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFFCB0D), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 290);
        manager.setStat(Stats.Defense, 82);
        manager.setStat(Stats.Strength, 19);
        manager.setStat(Stats.Inteligence, 107);
        manager.setStat(Stats.Speed, 6);
        manager.setStat(Stats.CritDamage, 13);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Burning Hollow Chestplate" , "BURNING_HOLLOW_CHESTPLATE", ItemType.Chestplate, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFFCB0D), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 365);
        manager.setStat(Stats.Defense, 103);
        manager.setStat(Stats.Strength, 24);
        manager.setStat(Stats.Inteligence, 135);
        manager.setStat(Stats.Speed, 8);
        manager.setStat(Stats.CritDamage, 16);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Fiery Hollow Chestplate" , "FIERY_HOLLOW_CHESTPLATE", ItemType.Chestplate, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFFCB0D), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 460);
        manager.setStat(Stats.Defense, 130);
        manager.setStat(Stats.Strength, 30);
        manager.setStat(Stats.Inteligence, 170);
        manager.setStat(Stats.Speed, 10);
        manager.setStat(Stats.CritDamage, 20);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Infernal Hollow Chestplate" , "INFERNAL_HOLLOW_CHESTPLATE", ItemType.Chestplate, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_CHESTPLATE, Color.fromRGB(0xFFCB0D), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 580);
        manager.setStat(Stats.Defense, 164);
        manager.setStat(Stats.Strength, 38);
        manager.setStat(Stats.Inteligence, 214);
        manager.setStat(Stats.Speed, 13);
        manager.setStat(Stats.CritDamage, 25);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);
    }
    private static void leggings(AbilityLore lore){
        ItemManager manager = new ItemManager("Hollow Leggings" , "HOLLOW_LEGGINGS", ItemType.Leggings, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_LEGGINGS, Color.fromRGB(0xFFF6A3), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 205);
        manager.setStat(Stats.Defense, 55);
        manager.setStat(Stats.Strength, 15);
        manager.setStat(Stats.Inteligence, 85);
        manager.setStat(Stats.Speed, 5);
        manager.setStat(Stats.CritDamage, 10);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Hollow Leggings" , "HOLLOW_LEGGINGS", ItemType.Leggings, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_LEGGINGS, Color.fromRGB(0xFFF6A3), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 205);
        manager.setStat(Stats.Defense, 55);
        manager.setStat(Stats.Strength, 15);
        manager.setStat(Stats.Inteligence, 85);
        manager.setStat(Stats.Speed, 5);
        manager.setStat(Stats.CritDamage, 10);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Hot Hollow Leggings" , "HOT_HOLLOW_LEGGINGS", ItemType.Leggings, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_LEGGINGS, Color.fromRGB(0xFFF6A3), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 258);
        manager.setStat(Stats.Defense, 69);
        manager.setStat(Stats.Strength, 19);
        manager.setStat(Stats.Inteligence, 107);
        manager.setStat(Stats.Speed, 6);
        manager.setStat(Stats.CritDamage, 13);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Burning Hollow Leggings" , "BURNING_HOLLOW_LEGGINGS", ItemType.Leggings, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_LEGGINGS, Color.fromRGB(0xFFF6A3), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 325);
        manager.setStat(Stats.Defense, 87);
        manager.setStat(Stats.Strength, 24);
        manager.setStat(Stats.Inteligence, 135);
        manager.setStat(Stats.Speed, 8);
        manager.setStat(Stats.CritDamage, 16);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Fiery Hollow Leggings" , "FIERY_HOLLOW_LEGGINGS", ItemType.Leggings, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_LEGGINGS, Color.fromRGB(0xFFF6A3), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 410);
        manager.setStat(Stats.Defense, 110);
        manager.setStat(Stats.Strength, 30);
        manager.setStat(Stats.Inteligence, 170);
        manager.setStat(Stats.Speed, 10);
        manager.setStat(Stats.CritDamage, 20);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Infernal Hollow Leggings" , "INFERNAL_HOLLOW_LEGGINGS", ItemType.Leggings, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_LEGGINGS, Color.fromRGB(0xFFF6A3), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 517);
        manager.setStat(Stats.Defense, 139);
        manager.setStat(Stats.Strength, 38);
        manager.setStat(Stats.Inteligence, 214);
        manager.setStat(Stats.Speed, 13);
        manager.setStat(Stats.CritDamage, 25);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);
    }

    private static void boots(AbilityLore lore){
        ItemManager manager = new ItemManager("Hollow Boots" , "HOLLOW_BOOTS", ItemType.Boots, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_BOOTS, Color.fromRGB(0xE3FFFA), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 130);
        manager.setStat(Stats.Defense, 40);
        manager.setStat(Stats.Strength, 15);
        manager.setStat(Stats.Inteligence, 85);
        manager.setStat(Stats.Speed, 5);
        manager.setStat(Stats.CritDamage, 10);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Hot Hollow Boots" , "HOT_HOLLOW_BOOTS", ItemType.Boots, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_BOOTS, Color.fromRGB(0xE3FFFA), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 164);
        manager.setStat(Stats.Defense, 50);
        manager.setStat(Stats.Strength, 19);
        manager.setStat(Stats.Inteligence, 107);
        manager.setStat(Stats.Speed, 6);
        manager.setStat(Stats.CritDamage, 13);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Burning Hollow Boots" , "BURNING_HOLLOW_BOOTS", ItemType.Boots, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_BOOTS, Color.fromRGB(0xE3FFFA), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 206);
        manager.setStat(Stats.Defense, 64);
        manager.setStat(Stats.Strength, 24);
        manager.setStat(Stats.Inteligence, 135);
        manager.setStat(Stats.Speed, 8);
        manager.setStat(Stats.CritDamage, 16);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Fiery Hollow Boots" , "FIERY_HOLLOW_BOOTS", ItemType.Boots, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_BOOTS, Color.fromRGB(0xE3FFFA), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 260);
        manager.setStat(Stats.Defense, 80);
        manager.setStat(Stats.Strength, 30);
        manager.setStat(Stats.Inteligence, 170);
        manager.setStat(Stats.Speed, 10);
        manager.setStat(Stats.CritDamage, 20);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);

        manager = new ItemManager("Infernal Hollow Boots" , "INFERNAL_HOLLOW_BOOTS", ItemType.Boots, null, "Spirit" , "Spirit", null,0,0,0,0
                , Material.LEATHER_BOOTS, Color.fromRGB(0xE3FFFA), ItemRarity.LEGENDARY);
        manager.addSlot(new GemstoneSlot(SlotType.Combat));manager.addSlot(new GemstoneSlot(SlotType.Combat));
        manager.setStat(Stats.Health, 328);
        manager.setStat(Stats.Defense, 101);
        manager.setStat(Stats.Strength, 38);
        manager.setStat(Stats.Inteligence, 214);
        manager.setStat(Stats.Speed, 13);
        manager.setStat(Stats.CritDamage, 25);
        manager.setFullSetBonus(Bonuses.Spirit, "Spirit", true);
        manager.setAbilityLore(lore);
        Items.SkyblockItems.put(manager.itemID, manager);
    }

    enum Placeholder{
        stacks("%stack%", (player, item) -> {
            return String.valueOf(getMaxStacks(player));
        }),
        time("%time%", (player, item) -> {
            if(player == null)
                return "4";
            if(player.bonusAmounts.containsKey(Bonuses.Spirit)) {
                switch (player.bonusAmounts.get(Bonuses.Spirit)){
                    case 3 ->{
                        return "7";
                    }
                    case 4 ->{
                        return "10";
                    }
                    default -> {
                        if(player.bonusAmounts.get(Bonuses.Spirit) > 4)
                            return "10";
                        else
                            return "4";
                    }
                }
            }
            else return "4";
        });
        private final String holder;
        private final UpdateFlag flag;

        Placeholder(String s, UpdateFlag flag) {
            holder = s;
            this.flag = flag;
        }
        public String getHolder(){
            return holder;
        }
        public UpdateFlag getFlag(){
            return flag;
        }
    }
}
