package me.CarsCupcake.SkyblockRemake.Commission;

public enum Direction {
Up(1),Down(2),Left(3),Right(4);
	Direction(int i) {
	
}

	public String toString() {
		switch(this) {
		case Down:
			return "§a▼";
			
		case Left:
			return "§b◀";
		case Right:
			return "§5▶";
		case Up:
			return "§d▲";
		default:
			break;
		
		}
		return "";
	}
	public static Direction getFromValue(int i) {
		switch(i) {
		case 1:
			return Up;
		case 2:
			return Down;
		case 3:
			return Left;
		case 4:
			return Right;
			default:
				return null;
		}
	}
}
