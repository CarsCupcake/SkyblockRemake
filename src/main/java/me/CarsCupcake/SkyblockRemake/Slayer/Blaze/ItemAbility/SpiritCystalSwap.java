package me.CarsCupcake.SkyblockRemake.Slayer.Blaze.ItemAbility;

import me.CarsCupcake.SkyblockRemake.API.HellionShield;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.UpdateFlag;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class SpiritCystalSwap implements AbilityManager<PlayerInteractEvent>, UpdateFlag, ItemManager.MaterialGrabber {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        HellionShield shield;
        String s = ItemHandler.getOrDefaultPDC("attuned", event.getItem(), PersistentDataType.STRING, "null");

        switch (s){
            case "spirit" -> shield = HellionShield.Spirit;
            case "crystal" -> shield = HellionShield.Crystal;
            default -> shield = null;
        }

        if (shield == null || shield == HellionShield.Crystal){
            event.getItem().setType(Material.IRON_SWORD);
            ItemHandler.setPDC("attuned", event.getItem(), PersistentDataType.STRING, "spirit");
            event.getPlayer().sendTitle("§fSpirit", "", 0, 20, 0);
        }else if(shield == HellionShield.Spirit){
            event.getItem().setType(Material.DIAMOND_SWORD);
            ItemHandler.setPDC("attuned", event.getItem(), PersistentDataType.STRING, "crystal");
            event.getPlayer().sendTitle("§bCrystal", "", 0, 20, 0);
        }
        Main.item_updater(event.getItem(), SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        return false;
    }

    @Override
    public String getReplaceValue(SkyblockPlayer player, ItemStack itemStack) {
        String s = ItemHandler.getOrDefaultPDC("attuned", itemStack, PersistentDataType.STRING, "null");

        switch (s){
            case "crystal" -> {
                return "§bCrystal";
            }
            case "spirit" ->{
                return "§fSpirit";
            }
            default -> {
                return "§cN/A";
            }
        }
    }

    @Override
    public Material getMaterial(ItemStack item, SkyblockPlayer player) {
        String s = ItemHandler.getOrDefaultPDC("attuned", item, PersistentDataType.STRING, "null");

        if (s.equals("crystal")) {
            return Material.DIAMOND_SWORD;
        }
        return Material.IRON_SWORD;
    }
}
