package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.API.SkyblockPlayerEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
public class PlayerManaRegenEvent extends SkyblockPlayerEvent {
    private double totalMana;
    private double currentMana;
    private double regenrateAmount;
    private double multiplier;
    public PlayerManaRegenEvent(@NotNull SkyblockPlayer player) {
        super(player);
    }
}
