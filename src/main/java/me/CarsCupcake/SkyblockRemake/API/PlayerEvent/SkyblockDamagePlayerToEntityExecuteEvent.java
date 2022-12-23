package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.LivingEntity;

public class SkyblockDamagePlayerToEntityExecuteEvent extends PlayerEvent{
    @Getter
    private final Calculator calculator;
    @Getter
    private final LivingEntity entity;
    public SkyblockDamagePlayerToEntityExecuteEvent(SkyblockPlayer player, LivingEntity e, Calculator c) {
        super(player);
        calculator = c;
        entity = e;
    }
}
