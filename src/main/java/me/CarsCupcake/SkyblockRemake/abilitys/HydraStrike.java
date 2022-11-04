package me.CarsCupcake.SkyblockRemake.abilitys;

import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class HydraStrike implements FullSetBonus {

	private SkyblockPlayer player;
	private final static HashMap<SkyblockPlayer, HydraStrike> hydraStrikes = new HashMap<>();
	private BukkitRunnable stackRunner;
	public int stacks = 0;
	public int stackRuntime = 0;
	
	public static void getEntityKill(ProjectileHitEvent event) {
		
		if(event.getEntity().getShooter() instanceof Player && event.getHitEntity() != null) {
			SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer((Player) event.getEntity().getShooter());
			if(HydraStrike.hasHydraStrike(p))
				HydraStrike.get(p).setHit();
		}
		
	}
	
	
	public double getFlySpeed() {
		return switch (stacks) {
			case 1 -> 1.04;
			case 2 -> 1.08;
			case 3 -> 1.12;
			case 4 -> 1.16;
			case 5 -> 1.2;
			case 6 -> 1.24;
			case 7 -> 1.28;
			case 8 -> 1.32;
			case 9 -> 1.36;
			case 10 -> 1.4;
			default -> 1;
		};
	}
	
	public void setHit() {
		
		
		
		if(player.bonusAmounts.get(Bonuses.HydraStrike) == 2)
			stackRuntime = 4;
		else if(player.bonusAmounts.get(Bonuses.HydraStrike) == 3)
			stackRuntime = 7;
		else
			stackRuntime = 10;
		
		if(stackRunner != null )
			try {
		stackRunner.cancel();
		}catch (Exception ignored) {
		
		}
		stackRunner = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				stackRuntime -= 1;
				
				if(stackRuntime == 0) {
				stacks -= 1;
				Main.updatebar(player);
				if(stacks == 0)
					stackRunner.cancel();
				else {
					if(player.bonusAmounts.get(Bonuses.HydraStrike) == 2)
						stackRuntime = 4;
					else if(player.bonusAmounts.get(Bonuses.HydraStrike) == 3)
						stackRuntime = 7;
					else
						stackRuntime = 10;
				}
					
				}
				
			}
		};
		stackRunner.runTaskTimer(Main.getMain(), 0, 20);
		
		
		Main.updatebar(player);
		
		if(stacks < 10) {
			stacks += 1;
		if(stacks == 10)
			player.playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
		}
		
		
		
	}
	
	
	
	
	public static HydraStrike get(SkyblockPlayer player) {
		return hydraStrikes.get(player);
	}
	public static boolean hasHydraStrike(SkyblockPlayer player) {
		return hydraStrikes.containsKey(player);
	}
	
	@Override
	public void start() {

		hydraStrikes.put(player, this);

	}

	@Override
	public void stop() {
		hydraStrikes.remove(player);
	}

	@Override
	public int getPieces() {
		
		return 2;
	}

	@Override
	public void setPlayer(SkyblockPlayer player) {
		this.player = player;
		
	}

	@Override
	public Bonuses getBonus() {

		return Bonuses.HydraStrike;
	}
	@Override
	public int getMaxPieces() {
		return 4;
	}

}
