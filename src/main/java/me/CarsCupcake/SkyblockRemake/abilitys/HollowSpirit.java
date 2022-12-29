package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Gemstones.SlotType;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class HollowSpirit implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)
            HollowSpiritSpell.getSpell(player).addSack(HollowSpiritSpell.Stack.Hollowed);
        else  if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR)
            HollowSpiritSpell.getSpell(player).addSack(HollowSpiritSpell.Stack.Spirit);

        return false;
    }


    public static void addItem(){
        ArrayList<String> abilityLore = new ArrayList<>();
        abilityLore.add("§7Left Click to cast a stack of §8[§c✤§8]§7 and");
        abilityLore.add("§7Right Click to cast a stack of §8[§b✦§8]§7.");
        abilityLore.add("§7Combine stacks to cast different spells!");
        abilityLore.add(" ");
        abilityLore.add("§8⊳ §8[§c✤§7§l﹍§7§l﹍§7§l﹍§8]§7 §dSpirit Spark §8(§65 ⚶§8)");
        abilityLore.add("§8⊳ §8[§b✦§7§l﹍§7§l﹍§7§l﹍§8]§7 §dHollowed Shield §8(§65 ⚶§8)");
        abilityLore.add("§8⊳ §8[§c✤§c✤§b✦§7§l﹍§8]§7 §dSpirit Barrage §8(§620 ⚶§8)");
        abilityLore.add("§8⊳ §8[§b✦§b✦§c✤§7§l﹍§8]§7 §dHollowed Barrage §8(§620 ⚶§8)");
        abilityLore.add("§8⊳ §8[§c✤§c✤§c✤§c✤§8]§7 §dDivine Spark §8(§640 ⚶§8)");
        abilityLore.add("§8§oType '/wandinfo' for detailed ability info.");

        ItemManager manager = new ItemManager("Hollow Wand" , "HOLLOW_WAND", ItemType.Wand, null, "Hollow Wand" , "Spirit", abilityLore,0,0,0,0
                , Material.FEATHER, ItemRarity.LEGENDARY);
        manager.setUnstackeble(true);
        manager.setDamage(150);
        manager.setStat(Stats.Strength, 20);
        manager.setStat(Stats.Inteligence, 120);
        manager.setNpcSellPrice(10000);
        manager.setAbility(new HollowSpirit(), AbilityType.LeftOrRightClick);
        Items.SkyblockItems.put(manager.itemID, manager);

    }
}
