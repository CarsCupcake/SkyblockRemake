package me.CarsCupcake.SkyblockRemake.Commission;

public enum DwarvenSituationalCommissions {
GoblinRaidSlayer,
GoblinRaid,
Raffle,
GoldenGoblinSlayer,
StarSentryPuncher,
LuckyRaffle;
	public int maxScore() {
		switch(this) {
		case GoblinRaid:
			return 1;
		case GoblinRaidSlayer:
			return 20;
		case GoldenGoblinSlayer:
			return 1;
		case LuckyRaffle:
			return 20;
		case Raffle:
			return 1;
		case StarSentryPuncher:
			return 10;
		default:
			break;
		
		}
		return 0;
	}
}
