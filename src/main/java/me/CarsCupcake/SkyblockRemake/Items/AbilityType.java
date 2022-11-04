package me.CarsCupcake.SkyblockRemake.Items;

public enum AbilityType {
RightClick,
LeftClick,
SneakRightClick,
SneakLeftClick,
Sneak,
EntityHit,
FullSetBonus,
	LeftOrRightClick,
	SneakLeftOrRightClick;
	public String toString() {
		return switch (this) {
			case EntityHit -> "Hit";
			case LeftClick -> "Left Click";
			case RightClick -> "Right Click";
			case Sneak -> "Sneak";
			case SneakLeftClick -> "Sneak Left Click";
			case SneakRightClick -> "Sneak Right Click";
			case FullSetBonus -> "Full Set Bonus";
			case LeftOrRightClick -> "Left/Right Click";
			case SneakLeftOrRightClick -> "Sneak Left/Right Click";
		};
	}
	public static boolean isNoSneak(AbilityType type){
		if(type == SneakLeftClick || type == SneakLeftOrRightClick || type == Sneak || type == SneakRightClick)
			return false;
		else
			return true;
	}
}
