package me.CarsCupcake.SkyblockRemake.Items.attributes;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AttackSpeed extends Attribute implements Listener {

    @Override
    public String name() {
        return "Attack Speed";
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
    public List<String> lore(int level) {
        return List.of("§7Grants " + Tools.cleanDouble(level) + Stats.AttackSpeed);
    }

    @EventHandler
    public void onStatGet(GetTotalStatEvent event) {
        if (event.getStat() != Stats.AttackSpeed) return;
        for (ItemStack item : event.getPlayer().getEquipment().getArmorContents()) {
            for (AppliedAttribute attribute : getAttributes(item)) {
                if (attribute.attribute() instanceof AttackSpeed) {
                    event.addValue(attribute.level());
                }
            }
        }
    }
}
