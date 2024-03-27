package me.CarsCupcake.SkyblockRemake.Items.attributes;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MagicFind extends Attribute implements Listener {

    @Override
    public String name() {
        return "Magic Find";
    }

    @Override
    public int maxLevel() {
        return 10;
    }

    @Override
    public boolean isAllowed(SkyblockPlayer player) {
        return true;
    }

    @Override
    public List<String> lore(int l) {
        return List.of("§7Grants §b+" + ((getBuff(l) % 1 == 0) ? String.format("%.0f", getBuff(l)) : getBuff(l)) + "✯ Magic Find");
    }

    private double getBuff(int level) {
        return level * 0.5;
    }

    @EventHandler
    public void onStatGet(GetTotalStatEvent event) {
        if (event.getStat() != Stats.MagicFind) return;
        for (ItemStack item : event.getPlayer().getEquipment().getArmorContents()) {
            for (AppliedAttribute attribute : getAttributes(item)) {
                if (attribute.attribute() instanceof MagicFind magicFind) {
                    event.addValue(magicFind.getBuff(attribute.level()));
                }
            }
        }
    }
}
