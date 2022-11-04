package me.CarsCupcake.SkyblockRemake.abilitys;

import java.util.HashMap;

import org.bukkit.Particle;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public enum ArcaneRune {

Mediator,
Defender,
Virtuoso;
private static HashMap<SkyblockPlayer, ArcaneRune> playerrunes = new HashMap<>();
public static ArcaneRune getPlayerRune(SkyblockPlayer player) {
	if(!playerrunes.containsKey(player)) {
		playerrunes.put(player, Mediator);
		return Mediator;
		
	}else
		return playerrunes.get(player);
	
	
}
public ArcaneRune nextRune(SkyblockPlayer player) {
	switch(this) {
	case Defender:
		playerrunes.put(player, Virtuoso);
		return Virtuoso;
	case Mediator:
		playerrunes.put(player, Defender);
		return Defender;
	case Virtuoso:
		playerrunes.put(player, Mediator);
		return Mediator;
	default:
		return null;
	
	}

}
public Particle getParticle() {
	switch(this) {
	case Defender:
		return Particle.CRIT_MAGIC;
	case Mediator:
		return Particle.FLAME;
	case Virtuoso:
		return Particle.SPELL_WITCH;
	default:
		return null;
	
	}
}
}
