package me.CarsCupcake.SkyblockRemake.abilities;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.util.BoundingBox;

public class ArcaneVision implements FullSetBonus {
    private SkyblockPlayer player;
    private BukkitRunnable runeShow;
    private BukkitRunnable stackRunner;
	public int stacks = 0;
	public int stackRuntime = 0;
	private static HashMap<SkyblockPlayer, ArcaneVision> players = new HashMap<>();
    
    
    public void addStack() {
    	
    	
    	
    	if(player.bonusAmounts.get(Bonuses.ArcaneVision) == 2)
			stackRuntime = 4;
		else if(player.bonusAmounts.get(Bonuses.ArcaneVision) == 3)
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
					if(player.bonusAmounts.get(Bonuses.ArcaneVision) == 2)
						stackRuntime = 4;
					else if(player.bonusAmounts.get(Bonuses.ArcaneVision) == 3)
						stackRuntime = 7;
					else
						stackRuntime = 10;
				}
					
				}
				
			}
		};
		stackRunner.runTaskTimer(Main.getMain(), 0, 20);
		
		if(stacks < 10) {
			stacks += 1;
		if(stacks == 10)
			player.playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
		}
		Main.updatebar(player);
		
		
    	
    	
    }
    
    
    public static boolean hasArcaneVision(SkyblockPlayer player) {
    	return players.containsKey(player);
    }
    public static ArcaneVision getArcaneVision(SkyblockPlayer player) {
    	return players.get(player);
    }
    
    
    public void makeVision(LivingEntity entity, ArcaneRune rune) {
    	Location loc = entity.getLocation();
		BoundingBox bb = entity.getBoundingBox();
    	player.spawnParticle(rune.getParticle(), loc.add(0, (bb.getMaxY()-bb.getMinY()) / 2, 0), 7 ,(bb.getMaxX() - bb.getMinX()) / 2, (bb.getMaxY() - bb.getMinY()) / 2, (bb.getMaxZ() - bb.getMinZ()) / 2, 0, null);
    }
    private ArcaneRune getRandomRune() {
    	Random rand = new Random();
    	
    	int r = rand.nextInt(3);
        return switch (r) {
            case 0 -> ArcaneRune.Defender;
            case 1 -> ArcaneRune.Mediator;
            case 2 -> ArcaneRune.Virtuoso;
            default -> null;
        };
    }
    
    
	@Override
	public void start() {
		players.put(player, this);
		runeShow = new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Entity entity : player.getNearbyEntities(40, 40, 40))
					if(entity instanceof LivingEntity && !(entity instanceof Player) && entity.getType() != EntityType.ARMOR_STAND) {
						if(entity.getScoreboardTags().isEmpty()) {
							ArcaneRune rune = getRandomRune();
							entity.addScoreboardTag("rune:" + rune.toString().toLowerCase());
						}else
							if(entity.getScoreboardTags().contains("rune:virtuoso"))
								makeVision((LivingEntity)entity, ArcaneRune.Virtuoso);
							else
						if(entity.getScoreboardTags().contains("rune:mediator"))
							makeVision((LivingEntity)entity, ArcaneRune.Mediator);
						else
						if(entity.getScoreboardTags().contains("rune:defender"))
							makeVision((LivingEntity)entity, ArcaneRune.Defender);
						else {
							ArcaneRune rune = getRandomRune();
							entity.addScoreboardTag("rune:" + rune.toString().toLowerCase());
						}
				}
				
			}
		};
		runeShow.runTaskTimer(Main.getMain(), 0, 5);

	}

	@Override
	public void stop() {
		players.remove(player);
		runeShow.cancel();

	}

	@Override
	public int getPieces() {
		return 2;
	}

	@Override
	public int getMaxPieces() {
		return 4;
	}

	@Override
	public void setPlayer(SkyblockPlayer player) {
		this.player = player;
	}

	@Override
	public Bonuses getBonus() {

		return Bonuses.ArcaneVision;
	}

}
