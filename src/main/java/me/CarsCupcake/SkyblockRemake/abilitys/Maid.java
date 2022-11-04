package me.CarsCupcake.SkyblockRemake.abilitys;

import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class Maid implements FullSetBonus {
	private SkyblockPlayer player;
	private BukkitRunnable run;

	@Override
	public void start() {
		hearts();
		
		player.setBaseStat(Stats.Ferocity, player.baseferocity + 35);
		player.setBaseStat(Stats.Health, player.basehealth + 500);

	}

	@Override
	public void stop() {
		try {
			player.setBaseStat(Stats.Ferocity, player.baseferocity - 35);
			player.setBaseStat(Stats.Health, player.basehealth - 500);
			run.cancel();
		} catch (Exception ignore) {
		
		}
	}

	@Override
	public int getPieces() {
		return 4;
	}

	@Override
	public void setPlayer(SkyblockPlayer player) {
		this.player = player;
	}

	@Override
	public Bonuses getBonus() {
		return Bonuses.Maid;
	}


	private void hearts() {
		run = new BukkitRunnable() {
			
			@Override
			public void run() {
				player.getWorld().spawnParticle(Particle.HEART, player.getLocation().add(0, 2, 0), 0, 0, 0, 1, 0, null);
			}
		};
		run.runTaskTimer(Main.getMain(), 15, 15);
	}
	@Override
	public int getMaxPieces() {
		return 4;
	}
}
