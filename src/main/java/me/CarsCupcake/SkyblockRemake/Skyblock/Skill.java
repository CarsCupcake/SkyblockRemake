package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Alchemy;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Combat;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Foraging;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.Mining;

public interface Skill {
	
	 void sendLevelUpMessage();

	 void reset();
	 int getLevel();
	 double getXp();
	 int getMaxLevel();
	 void setPlayer(SkyblockPlayer player);
	 void setLevel(int level);
	 void setXp(double i);
	 void levelUp();
	 void initStats();

	
	
	
	 static int getNextLevelXp(int level) {
		
		switch (level) {
		case 0:
			return 50;
		case 1:
			return 125;
		case 2:
			return 200;
		case 3:
			return 300;
		case 4:
		    return 500;
		case 5:
			return 750;
		case 6:
			return 1000;
		case 7:
			return 1500;
		case 8:
			return 2000;
		case 9:
			return 3500;
		case 10:
			return 5000;
		case 11:
			return 7500;
		case 12:
			return 10000;
		case 13:
			return 15000;
		case 14:
			return 20000;
		case 15:
			return 30000;
		case 16:
		    return 50000;
		case 17:
			return 75000;
		case 18:
			return 100000;
		case 19:
			return 200000;
		case 20:
			return 300000;
		case 21:
			return 400000;
		case 22:
			return 500000;
		case 23:
			return 600000;
		case 24:
			return 700000;
		case 25:
			return 800000;
		case 26:
			return 900000;
		case 27:
			return 1000000;
		case 28:
			return 1100000;
		case 29:
			return 1200000;
		case 30:
			return 1300000;
		case 31:
			return 1400000;
		case 32:
			return 1400000;
		case 33:
			return 1600000;
		case 34:
			return 1700000;
		case 35:
			return 1800000;
		case 36:
			return 1900000;
		case 37:
			return 2000000;
		case 38:
			return 2100000;
		case 39:
			return 2200000;
		case 40:
			return 2300000;
		case 41:
			return 2400000;
		case 42:
			return 2500000;
		case 43:
			return 2600000;
		case 44:
			return 2750000;
		case 45:
			return 2900000;
		case 46:
			return 3100000;
		case 47:
			return 3400000;
		case 48:
			return 3700000;	
		case 49:
			return 4000000;
		case 50:
			return 4300000;
		case 51:
			return 4600000;
		case 52:
			return 4900000;
		case 53:
			return 5200000;
		case 54:
			return 5500000;
		case 55:
			return 5800000;
		case 56:
			return 6100000;
		case 57:
			return 6400000;
		case 58:
			return 6700000;
		case 59:
			return 7000000;
		}
		
		
		return 7000000;	
	}
	
	
	 static Skill getInstance(Skills skill,SkyblockPlayer player, int level, double xp) {
		
		if(skill == Skills.Mining) {
			Skill mining = new Mining();
			mining.setPlayer(player);
			mining.setXp(xp);
			mining.setLevel(level);
			mining.initStats();
			return mining;
		}
		if(skill == Skills.Combat) {
			Skill mining = new Combat();
			mining.setPlayer(player);
			mining.setXp(xp);
			mining.setLevel(level);
			mining.initStats();
			return mining;
		}
		
		if(skill == Skills.Foraging) {
			Skill mining = new Foraging();
			mining.setPlayer(player);
			mining.setXp(xp);
			mining.setLevel(level);
			mining.initStats();
			return mining;
		}
		
		if(skill == Skills.Alchemy) {
			Skill mining = new Alchemy();
			mining.setPlayer(player);
			mining.setXp(xp);
			mining.setLevel(level);
			mining.initStats();
			return mining;
		}
		
		return null;
	}
	
	
	

}
