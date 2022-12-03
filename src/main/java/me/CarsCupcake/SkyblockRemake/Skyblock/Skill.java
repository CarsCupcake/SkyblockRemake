package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.Skills.*;

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

		 return switch (level) {
			 case 0 -> 50;
			 case 1 -> 125;
			 case 2 -> 200;
			 case 3 -> 300;
			 case 4 -> 500;
			 case 5 -> 750;
			 case 6 -> 1000;
			 case 7 -> 1500;
			 case 8 -> 2000;
			 case 9 -> 3500;
			 case 10 -> 5000;
			 case 11 -> 7500;
			 case 12 -> 10000;
			 case 13 -> 15000;
			 case 14 -> 20000;
			 case 15 -> 30000;
			 case 16 -> 50000;
			 case 17 -> 75000;
			 case 18 -> 100000;
			 case 19 -> 200000;
			 case 20 -> 300000;
			 case 21 -> 400000;
			 case 22 -> 500000;
			 case 23 -> 600000;
			 case 24 -> 700000;
			 case 25 -> 800000;
			 case 26 -> 900000;
			 case 27 -> 1000000;
			 case 28 -> 1100000;
			 case 29 -> 1200000;
			 case 30 -> 1300000;
			 case 31 -> 1400000;
			 case 32 -> 1400000;
			 case 33 -> 1600000;
			 case 34 -> 1700000;
			 case 35 -> 1800000;
			 case 36 -> 1900000;
			 case 37 -> 2000000;
			 case 38 -> 2100000;
			 case 39 -> 2200000;
			 case 40 -> 2300000;
			 case 41 -> 2400000;
			 case 42 -> 2500000;
			 case 43 -> 2600000;
			 case 44 -> 2750000;
			 case 45 -> 2900000;
			 case 46 -> 3100000;
			 case 47 -> 3400000;
			 case 48 -> 3700000;
			 case 49 -> 4000000;
			 case 50 -> 4300000;
			 case 51 -> 4600000;
			 case 52 -> 4900000;
			 case 53 -> 5200000;
			 case 54 -> 5500000;
			 case 55 -> 5800000;
			 case 56 -> 6100000;
			 case 57 -> 6400000;
			 case 58 -> 6700000;
			 case 59 -> 7000000;
			 default -> 7000000;
		 };


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

		 if(skill == Skills.Enchanting) {
			 Skill mining = new Enchanting();
			 mining.setPlayer(player);
			 mining.setXp(xp);
			 mining.setLevel(level);
			 mining.initStats();
			 return mining;
		 }

		 if(skill == Skills.Farming) {
			 Skill mining = new Farming();
			 mining.setPlayer(player);
			 mining.setXp(xp);
			 mining.setLevel(level);
			 mining.initStats();
			 return mining;
		 }

		 if(skill == Skills.Taming) {
			 Skill mining = new Taming();
			 mining.setPlayer(player);
			 mining.setXp(xp);
			 mining.setLevel(level);
			 mining.initStats();
			 return mining;
		 }
		
		return null;
	}
	
	
	

}
