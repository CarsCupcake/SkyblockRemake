package me.CarsCupcake.SkyblockRemake.Skyblock.Skills;

import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skill;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;

public class Enchanting implements Skill {

	
	private SkyblockPlayer player;
	private double xp = 0;
	private int level = 0;
	private CustomConfig skill;


	@Override
	public void sendLevelUpMessage() {
		
		player.sendMessage("Enchanting level up to level " + level + " §d§lBoop!");
		
	}

	@Override
	public void setXp(double xp) {
		
		skill.reload();
		skill.get().set(player.getUniqueId() + "." +Skills.Enchanting.toString().toLowerCase()+".xp", xp);
		skill.save();
		skill.reload();
		
		this.xp = xp;
		
	}

	@Override
	public void reset() {

		skill.reload();
		skill.get().set(player.getUniqueId() +"."+ Skills.Enchanting.toString().toLowerCase() + ".xp", 0);skill.get().set(player.getUniqueId() +"." +Skills.Enchanting.toString().toLowerCase()+ ".level", 0);
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

		return 60;
	}
	
	@Override
	public void setPlayer(SkyblockPlayer player) {
		this.player = player;
		skill = new CustomConfig(player, "Skills");
	}

	@Override
	public void setLevel(int level) {
		
		skill.reload();
		skill.get().set(player.getUniqueId() + "."+Skills.Enchanting.toString().toLowerCase()+".level", level);
		skill.save();
		skill.reload();
		
		this.level = level;
		
	}

	@Override
	public void levelUp() {
		
		setLevel(level + 1);
		setXp(0);
		sendLevelUpMessage();
		player.setBaseStat(Stats.AbilityDamage, player.getBaseStat(Stats.AbilityDamage) +  0.5);
		player.setBaseStat(Stats.Inteligence, player.getBaseStat(Stats.Inteligence) + 2);
	}
	
	public void initStats() {

		player.setBaseStat(Stats.AbilityDamage, player.getBaseStat(Stats.AbilityDamage) + (level * 0.5));
		player.setBaseStat(Stats.Inteligence, player.getBaseStat(Stats.Inteligence) + (level * 2));




	}

}
