package me.CarsCupcake.SkyblockRemake.Items.attributes;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.PlayerManaRegenEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ManaRegeneration extends Attribute implements Listener {
    @Override
    public String name() {
        return "Mana Regeneration";
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
        return List.of("§7Increases your Mana Regenration", "§7by §b" + level + "%§7.");
    }
    @Override
    public Type[] allowed() {
        return new Type[]{Type.Armor, Type.Equipment};
    }
    @EventHandler
    public void onStatGet(PlayerManaRegenEvent event) {
        for (ItemStack item : event.getPlayer().getEquipment().getArmorContents()) {
            for (AppliedAttribute attribute : getAttributes(item)) {
                if (attribute.attribute() instanceof ManaRegeneration) {
                    event.setMultiplier(event.getMultiplier() + (0.01 * attribute.level()));
                }
            }
        }
    }
}
