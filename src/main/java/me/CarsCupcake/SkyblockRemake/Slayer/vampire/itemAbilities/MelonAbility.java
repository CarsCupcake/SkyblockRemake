package me.CarsCupcake.SkyblockRemake.Slayer.vampire.itemAbilities;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import org.bukkit.Sound;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;

public class MelonAbility implements AbilityManager<PlayerInteractEvent> {
    private final double healing;
    public MelonAbility(double healing) {
        this.healing = healing;
    }
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        if (ServerType.getActiveType() != ServerType.Rift) {
            event.getPlayer().sendMessage("§cAbility can only be used in the Rift!");
            return true;
        }
        RiftPlayer player = RiftPlayer.getRiftPlayer(event.getPlayer());
        player.setHealth(player.getHealth() + (2 * healing), HealthChangeReason.Ability);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1.2f);
        return true;
    }

    @Override
    public <T extends Event> AbilityManager<T> newInstance() throws InstantiationException, IllegalAccessException {
        return (AbilityManager<T>) this;
    }
}
