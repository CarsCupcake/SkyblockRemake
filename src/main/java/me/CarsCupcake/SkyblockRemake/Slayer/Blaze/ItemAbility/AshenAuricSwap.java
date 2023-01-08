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

public class AshenAuricSwap implements AbilityManager<PlayerInteractEvent>, UpdateFlag, ItemManager.MaterialGrabber {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        HellionShield shield;
        String s = ItemHandler.getOrDefaultPDC("attuned", event.getItem(), PersistentDataType.STRING, "null");

        switch (s){
            case "auric" -> shield = HellionShield.Auric;
            case "ashen" -> shield = HellionShield.Ashen;
            default -> shield = null;
        }

        if (shield == null || shield == HellionShield.Auric){
            event.getItem().setType(Material.STONE_SWORD);
            ItemHandler.setPDC("attuned", event.getItem(), PersistentDataType.STRING, "ashen");
            event.getPlayer().sendTitle("§8Ashen", "", 0, 20, 0);
        }else if(shield == HellionShield.Ashen){
            event.getItem().setType(Material.GOLDEN_SWORD);
            ItemHandler.setPDC("attuned", event.getItem(), PersistentDataType.STRING, "auric");
            event.getPlayer().sendTitle("§eAuric", "", 0, 20, 0);
        }
        Main.item_updater(event.getItem(), SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        return false;
    }

    @Override
    public String getReplaceValue(SkyblockPlayer player, ItemStack itemStack) {
        String s = ItemHandler.getOrDefaultPDC("attuned", itemStack, PersistentDataType.STRING, "null");

        switch (s){
            case "auric" -> {
                return "§eAuric";
            }
            case "ashen" ->{
                return "§8Ashen";
            }
            default -> {
                return "§cN/A";
            }
        }
    }

    @Override
    public Material getMaterial(ItemStack item, SkyblockPlayer player) {
        String s = ItemHandler.getOrDefaultPDC("attuned", item, PersistentDataType.STRING, "null");

        if (s.equals("auric")) {
            return Material.GOLDEN_SWORD;
        }
        return Material.STONE_SWORD;
    }
}
