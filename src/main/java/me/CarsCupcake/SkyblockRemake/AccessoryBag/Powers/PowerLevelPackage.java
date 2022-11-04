package me.CarsCupcake.SkyblockRemake.AccessoryBag.Powers;

import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.Stats;

public class PowerLevelPackage {


		public double health;
		public double def;
		public double mana;
		public double speed;
		public double strength;
		public double cc;
		public double cd;
		public float abilitydamage;
		public double ferocity;
		public double magicfind;
		public double miningspeed;
		public double miningfortune;

		public double as;
		public double pristine;
		public double truedefense;
		HashMap<Stats, Double> stats = new HashMap<>();
		HashMap<Stats, Integer> basestats = new HashMap<>();

		public PowerLevelPackage(double health, double def,double mana,double speed, double strength,double cc, double cd,float abilitydamage,double ferocity, double magicfind, double miningspeed, double miningfortune, double as , double pristine, double truedefense) {

			this.health = health; stats.put(Stats.Health, health);
			this.def = def; stats.put(Stats.Defense, def);
			this.mana = mana; stats.put(Stats.Inteligence, mana);
			this.speed = speed; stats.put(Stats.Speed, speed);
			this.strength = strength; stats.put(Stats.Strength, strength);
			this.cc = cc; stats.put(Stats.CritChance, cc);
			this.cd = cd; stats.put(Stats.CritDamage, cd);
			this.abilitydamage = abilitydamage; stats.put(Stats.AbilityDamage, (double)abilitydamage);
			this.ferocity = ferocity;stats.put(Stats.Ferocity, ferocity);
			this.magicfind = magicfind; stats.put(Stats.MagicFind, magicfind);

			this.miningspeed = miningspeed; stats.put(Stats.MiningSpeed, miningspeed);
			this.miningfortune = miningspeed; stats.put(Stats.MiningFortune, miningfortune);
			this.as = as; stats.put(Stats.AttackSpeed, as);
			this.truedefense = truedefense; stats.put(Stats.TrueDefense, truedefense);
			this.pristine = pristine;
		}
		
		public void addBaseStat(Stats stat, int value) {
			basestats.put(stat, value);
		};
	}

