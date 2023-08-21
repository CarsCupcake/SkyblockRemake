package me.CarsCupcake.SkyblockRemake.API.PlayerEvent;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Entities.EntityHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;

@Getter
public class SkyblockDamagePlayerToEntityExecuteEvent extends PlayerEvent{
    private final Calculator calculator;
    @Getter
    private final LivingEntity entity;
    @Getter
    private final int hits;
    public SkyblockDamagePlayerToEntityExecuteEvent(SkyblockPlayer player, LivingEntity e, Calculator c) {
        super(player);
        calculator = c;
        entity = e;
        hits = EntityHandler.getOrDefault("hit", e, PersistentDataType.INTEGER, 0);
    }
}
