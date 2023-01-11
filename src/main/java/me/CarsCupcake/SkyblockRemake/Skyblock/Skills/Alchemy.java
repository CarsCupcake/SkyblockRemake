package me.CarsCupcake.SkyblockRemake.Skyblock.Skills;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skill;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class Alchemy implements Skill {

	
	private SkyblockPlayer player;
	private double xp = 0;
	private int level = 0;
	private final int maxlevel = 50;
	private CustomConfig skill;
	


	
	
	@Override
	public void sendLevelUpMessage() {
		
		player.sendMessage("Alchemy level up to level " + level + " §d§lBoop!");
		
	}

	@Override
	public void setXp(double xp) {
		
		skill.reload();
		skill.get().set(player.getUniqueId() + "." +Skills.Alchemy.toString().toLowerCase()+".xp", xp);
		skill.save();
		skill.reload();
		
		this.xp = xp;
		
	}

	@Override
	public void reset() {

		skill.reload();
		skill.get().set(player.getUniqueId() +"."+ Skills.Alchemy.toString().toLowerCase() + ".xp", 0);skill.get().set(player.getUniqueId() +"." +Skills.Alchemy.toString().toLowerCase()+ ".level", 0);
		skill.save();
		skill.reload();
		
		level = 0;
		xp = 0;
		
	}

	@Override
	public int getLevel() {
		return level;
		
	}

	@Override
	public double getXp() {

		return xp;
	}
	@Override
	public int getMaxLevel() {

		return maxlevel;
	}
	
	@Override
	public void setPlayer(SkyblockPlayer player) {
		this.player = player;
		skill = new CustomConfig(player, "Skills");
	}

	@Override
	public void setLevel(int level) {
		
		skill.reload();
		skill.get().set(player.getUniqueId() + "."+Skills.Alchemy.toString().toLowerCase()+".level", (int)level);
		skill.save();
		skill.reload();
		
		this.level = level;
		
	}

	@Override
	public void levelUp() {
		
		setLevel(level + 1);
		setXp(0);
		sendLevelUpMessage();
		if(level < 15)
		player.setBaseStat(Stats.Inteligence, player.basemana + 1);
		else
			player.setBaseStat(Stats.Inteligence, player.basemana + 2);
	}
	
	public void initStats() {
		if(level > 14) {
			player.setBaseStat(Stats.Inteligence, player.basemana + 14);
			player.setBaseStat(Stats.Inteligence, player.basemana + ((level - 14) * 2));
		}else {
			player.setBaseStat(Stats.Inteligence, player.basemana + (1 * level));
		}

	}

}
