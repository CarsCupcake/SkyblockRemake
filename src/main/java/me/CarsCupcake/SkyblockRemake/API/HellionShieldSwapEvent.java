package me.CarsCupcake.SkyblockRemake.API;

import org.bukkit.entity.LivingEntity;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class HellionShieldSwapEvent extends Event{

	private final SkyblockPlayer player;
	private static final HandlerList HANDLERS = new HandlerList();
	private final HellionShield oldShield;
	private final HellionShield newShield;
	private final LivingEntity entity;
	
	
	public HellionShieldSwapEvent(SkyblockPlayer p, LivingEntity e, HellionShield oldShield, HellionShield newShield) {
		player = p;
		this.oldShield = oldShield;
		this.newShield = newShield;
		this.entity = e;
	}
	
	public final HellionShield getOldShield() {
		return oldShield;
	}
	public final HellionShield getNewShield() {
		return newShield;
	}
	public LivingEntity getEntity() {
		return entity;
	}
	
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	public static HandlerList getHandlerList() {
        return HANDLERS;
    }
	public SkyblockPlayer getSkyblockPlayer() {
		return player;
	}
	public SkyblockPlayer getPlayer() {
		return player;
	}
	
	
	
	
	

}
