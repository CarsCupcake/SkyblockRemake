package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.PlayerHealthChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ReleaseThePainListener implements Listener {
    @EventHandler
    public void onHealthChange(PlayerHealthChangeEvent event) {
        if(ChadIncoming.getPlayers().contains(event.getPlayer()))
            if(event.getReason() == HealthChangeReason.Damage)
              event.setHelthChangeAmount((int) (event.getHelthChangeAmount() * 0.5));
        else
        if(ReleaseThePain.getPlayers().contains(event.getPlayer()))
            if(event.getReason() == HealthChangeReason.Damage)
              event.setHelthChangeAmount((int) (event.getHelthChangeAmount() * 0.6));
    }
}
