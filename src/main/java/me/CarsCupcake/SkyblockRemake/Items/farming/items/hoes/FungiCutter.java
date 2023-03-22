package me.CarsCupcake.SkyblockRemake.Items.farming.items.hoes;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.PlayerFarmEvent;
import me.CarsCupcake.SkyblockRemake.Items.*;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems;

public class FungiCutter implements Listener {
    public FungiCutter(){
        ItemManager manager = new ItemManager("Fungi Cutter", "FUNGI_CUTTER", ItemType.Hoe, Material.GOLDEN_HOE, ItemRarity.RARE);
        manager.customDataContainer.put("mode", "Brown");
        AbilityLore lore = new AbilityLore(new ArrayList<>(List.of(
                "§7Switches between red and brown",
                "§7mushroom cutting modes.",
                " ",
                "§eMode: §c%mode% Mushroom",
                " ",
                "§7Gain §a+30% §7mushroom drops",
                "§7when harvesting the type of",
                "§7mushrooms for your mode."
        )));
        lore.addPlaceholder("%mode&", new AbilityLore.PersistenDataPlaceHolder<>("mode", PersistentDataType.STRING));
        manager.addAbility(new Ability(), AbilityType.RightClick, "Guy Bonus", lore, 0, 0);
        SkyblockItems.put(manager.itemID, manager);
    }
    @EventHandler
    public void onFarm(PlayerFarmEvent event){
        ItemStack item = event.getPlayer().getItemInHand();
        if (!item.hasItemMeta()) return;
        if (!ItemHandler.getPDC("id", item, PersistentDataType.STRING).equals("FUNGI_CUTTER"))
            return;
        Set<Material> allowedBlocks;
        if (ItemHandler.getPDC("mode", event.getPlayer().getItemInHand(), PersistentDataType.STRING).equals("Brown"))
            allowedBlocks = Set.of(Material.BROWN_MUSHROOM, Material.BROWN_MUSHROOM_BLOCK);
        else
            allowedBlocks = Set.of(Material.RED_MUSHROOM, Material.RED_MUSHROOM_BLOCK);
        if(allowedBlocks.contains(event.getBlock().getType()))
            event.setFarmingFortune(event.getFarmingFortune() + 30);
    }
    public static class Ability implements AbilityManager<PlayerInteractEvent>{

        @Override
        public boolean triggerAbility(PlayerInteractEvent event) {
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
            if(ItemHandler.getPDC("mode", player.getItemInHand(), PersistentDataType.STRING).equals("Brown"))
                ItemHandler.setPDC("mode", player.getItemInHand(), PersistentDataType.STRING, "Red");
            else
                ItemHandler.setPDC("mode", player.getItemInHand(), PersistentDataType.STRING, "Brown");
            return false;
        }
    }
}
