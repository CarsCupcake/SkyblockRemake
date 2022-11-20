package me.CarsCupcake.SkyblockRemake.DwarvenEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class PlayerTurnEvent extends Event{

	private final SkyblockPlayer player;
	private static final HandlerList HANDLERS = new HandlerList();
	
	
	
	public PlayerTurnEvent(SkyblockPlayer p) {
		player = p;
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
	public Player getPlayer() {
		return player.getPlayer();
	}
	
	
	
	
	

}
