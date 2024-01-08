package me.CarsCupcake.SkyblockRemake.Items.Pets.Abilitys;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.DianaItems;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GriffinListener implements Listener {
    @EventHandler
    public void onStatGet(GetTotalStatEvent event) {
        if (event.getStat() != Stats.Strength) return;
        if (event.getPlayer().getPetEquip() == null) return;
        if (event.getPlayer().getPetEquip().getPet() != DianaItems.LEGENDARY_GRIFFIN_PET.getItem()) return;
        if (event.getPlayer().currhealth >= Main.getPlayerStat(event.getPlayer(), Stats.Health) * 0.85)
            event.addMultiplier(1 + (0.01 + (event.getPlayer().getPetEquip().getLevel() * 0.0014)));
    }

    @EventHandler
    public void onDamageEvent(SkyblockDamageEvent event) {
        if (event.getType() != SkyblockDamageEvent.DamageType.EntityToPlayer && event.getType() != SkyblockDamageEvent.DamageType.PlayerSelve) return;
        if (event.getPlayer().getPetEquip() == null) return;
        if (event.getPlayer().getPetEquip().getPet() == null) return;
        if (!event.getPlayer().getPetEquip().getPet().itemID.startsWith("GRIFFIN;")) return;
        if (event.getPlayer().getPetEquip().getPet().getRarity() != ItemRarity.EPIC && event.getPlayer().getPetEquip().getPet().getRarity() != ItemRarity.LEGENDARY) return;
        double pers = event.getPlayer().getPetEquip().getLevel() * 0.002;
        double healing = event.getCalculator().damage * pers;
        Bukkit.getOnlinePlayers().stream().filter((p) -> p.getLocation().distance(event.getPlayer().getLocation()) <= 10d).map(SkyblockPlayer::getSkyblockPlayer).forEach(player -> {
            if (event.getPlayer().getPetEquip().getPet() != null && event.getPlayer().getPetEquip().getPet().itemID.startsWith("GRIFFIN;")) return;
            player.setHealth(player.getHealth() + healing, HealthChangeReason.Regenerate);
        });
    }
}
