package me.CarsCupcake.SkyblockRemake.Skyblock.Skills;

import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Configs.SkillsSave;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skill;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class Mining implements Skill {

	private SkyblockPlayer player;
	private double xp = 0;
	private int level = 0;
	private final int maxlevel = 60;
	
	private final double miningFortunePerLevel = 4;

	
	
	@Override
	public void sendLevelUpMessage() {
		
		player.sendMessage("Mining level up to level " + level + " §d§lBoop!");
		
	}

	@Override
	public void setXp(double xp) {
		
		SkillsSave.reload();
		SkillsSave.get().set(player.getUniqueId() + "." +Skills.Mining.toString().toLowerCase()+".xp", xp);
		SkillsSave.save();
		SkillsSave.reload();
		
		this.xp = xp;
		
	}

	@Override
	public void reset() {

		SkillsSave.reload();
		SkillsSave.get().set(player.getUniqueId() +"."+ Skills.Mining.toString().toLowerCase() + ".xp", 0);SkillsSave.get().set(player.getUniqueId() +"." +Skills.Mining.toString().toLowerCase()+ ".level", 0);
		SkillsSave.save();
		SkillsSave.reload();
		
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
	}

	@Override
	public void setLevel(int level) {
		
		SkillsSave.reload();
		SkillsSave.get().set(player.getUniqueId() + "."+Skills.Mining.toString().toLowerCase()+".level", (int)level);
		SkillsSave.save();
		SkillsSave.reload();
		
		this.level = level;
		
	}

	@Override
	public void levelUp() {
		
		setLevel(level + 1);
		setXp(0);
		sendLevelUpMessage();
		player.setBaseStat(Stats.MiningFortune, player.baseminingfortune + miningFortunePerLevel);
	}
	
	public void initStats() {
		player.setBaseStat(Stats.MiningFortune, player.baseminingfortune + (miningFortunePerLevel * level));

	}

	

}
