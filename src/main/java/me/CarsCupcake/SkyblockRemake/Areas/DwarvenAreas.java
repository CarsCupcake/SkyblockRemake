package me.CarsCupcake.SkyblockRemake.Areas;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public enum DwarvenAreas  {
AristocratPassage,
BarracksOfHeroes,
CCMinecartsCo,
CliffsideVeins,
DivansGateway,
DwarvenTavern,
DwarvenVillage,
FarReserve,
ForgeBasin,
GatesToTheMines,
GoblinBurrows,
GrandLibrary,
GreatIceWall,
HangingCourt,
LavaSprings,
MinersGuild,
PalaceBridge,
RampartsQuarry,
RoyalMines,
RoyalPalace,
RoyalQuarters,
TheForge,
TheLift,
TheMist,
UpperMines;
	 static DwarvenAreas movement(PlayerMoveEvent event) {
		 
		 Location coord1 = new Location(event.getPlayer().getWorld(), -73, 200, -135);
		 Location coord2 = new Location(event.getPlayer().getWorld(), -100, 216, -107);
		 Player player = event.getPlayer();
		 if(isInRect(player,coord1, coord2) || isInRect(player, new Location(player.getWorld(), -74, 194, -115), new Location(player.getWorld(), -44, 215, -130))){
			
		            	return TheLift;
		            	
		            }
		 if( isInRect(player, new Location(player.getWorld(), -41, 199, -161), new Location(player.getWorld(), 50, 240, -76))) {
			 return DwarvenVillage;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 21, 180, -73), new Location(player.getWorld(), 42, 213, -35))) {
			 return GatesToTheMines;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 95, 185, -20), new Location(player.getWorld(), 159, 257, 100))) {
			 return PalaceBridge;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 100, 183, 107), new Location(player.getWorld(), 158, 253, 212))) {
			 return RoyalPalace;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 161, 192, 213), new Location(player.getWorld(), 205, 250, 147))) {
			 return GrandLibrary;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 97, 192, 165), new Location(player.getWorld(), 54, 236, 207))) {
			 return BarracksOfHeroes;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 85, 192, 210), new Location(player.getWorld(), 175, 229, 289))) {
			 return RoyalQuarters;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 87, 193, 156), new Location(player.getWorld(), 126, 147, 164)) ||  isInRect(player, new Location(player.getWorld(), 124, 148, 173), new Location(player.getWorld(), 135, 165, 100))
				 ||  isInRect(player, new Location(player.getWorld(), 141, 148, 129), new Location(player.getWorld(), 85, 175, 147))) {
			 return AristocratPassage;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 98, 182, 113), new Location(player.getWorld(), 72, 236, 163))) {
			 return HangingCourt;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 94, 141, 107), new Location(player.getWorld(), 192, 178, 7))) {
			 return RoyalMines;
		 }
		 if( isInRect(player, new Location(player.getWorld(), -28, 116, 33), new Location(player.getWorld(), 99, 252, 54)) || isInRect(player, new Location(player.getWorld(),  96, 116, 33), new Location(player.getWorld(), 18, 180, 17))
				 || isInRect(player, new Location(player.getWorld(),  76, 129, 17), new Location(player.getWorld(), 27, 166, -4))) {
			 return CliffsideVeins;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 38, 144, -33), new Location(player.getWorld(), -32, 172, -132))) {
			 return TheForge;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 80, 190, -31), new Location(player.getWorld(), 29, 245, 21))) {
			 return LavaSprings;
		 }
		
		 if( isInRect(player, new Location(player.getWorld(), -24, 120, 16), new Location(player.getWorld(), 30, 255, -34))) {
			 return ForgeBasin;
		 }
		 if( isInRect(player, new Location(player.getWorld(), 81, 98, 176), new Location(player.getWorld(), -94, 50, 1)) || isInRect(player, new Location(player.getWorld(), 80, 90, 102), new Location(player.getWorld(), 131, 50, 36))
				 || isInRect(player, new Location(player.getWorld(), 141, 98, 102), new Location(player.getWorld(), 187, 50, 18))) {
			 return TheMist;
		 }
		 
		 //divans gateway: icewal seite z:139  royal mines x: 69  cliffside: z:59
		 
		 if( isInRect(player, new Location(player.getWorld(), 69, 97, 139), new Location(player.getWorld(), -39, 255, 59))) {
			 return DivansGateway;
		 }
		 
		 if( isInRect(player, new Location(player.getWorld(), -150, 168, -103), new Location(player.getWorld(), -125, 255, -3)) || isInRect(player, new Location(player.getWorld(), -100, 157, -78), new Location(player.getWorld(), -126, 255, -60))) {
			 return UpperMines;
		 }
		 
		 
		 if( isInRect(player, new Location(player.getWorld(), -28, 97, 50), new Location(player.getWorld(), -120, 255, -80)) ) {
			 return RampartsQuarry;
		 }
		 if( isInRect(player, new Location(player.getWorld(), -115, 114, -29), new Location(player.getWorld(), -178, 161, -9)) || isInRect(player, new Location(player.getWorld(), -200, 162, -51), new Location(player.getWorld(), -167, 139, 4))) {
			 return CCMinecartsCo;
		 }
		 if( isInRect(player, new Location(player.getWorld(), -124, 255, -34), new Location(player.getWorld(), -182, 97, 129)) || isInRect(player, new Location(player.getWorld(), -86, 255, 40), new Location(player.getWorld(), -182, 97, 129)) ) {
			 return FarReserve;
		 }
		
		 if( isInRect(player, new Location(player.getWorld(), 26, 125, 143), new Location(player.getWorld(), -36, 255, 204))) {
			 return GreatIceWall;
		 }
		 
		      

		 
		return null;
	}
	 public static boolean isInRect(Player player, Location loc1, Location loc2)
	 {
	     double[] dim = new double[2];
	  
	     dim[0] = loc1.getX();
	     dim[1] = loc2.getX();
	     Arrays.sort(dim);
	     if(player.getLocation().getX() > dim[1] || player.getLocation().getX() < dim[0])
	         return false;
	  
	     dim[0] = loc1.getZ();
	     dim[1] = loc2.getZ();
	     Arrays.sort(dim);
	     if(player.getLocation().getZ() > dim[1] || player.getLocation().getZ() < dim[0])
	         return false;
	     
	     dim[0] = loc1.getY();
	     dim[1] = loc2.getY();
	     Arrays.sort(dim);
	     if(player.getLocation().getY() > dim[1] || player.getLocation().getY() < dim[0])
	         return false;
	  
	    
	  
	     return true;
	 }
	 
	 
	 public String getString() {
		 switch(this) {
		case AristocratPassage:
			return "§bAristocrat Passage";
			
		case BarracksOfHeroes:
			return "§bBarracks Of Heroes";
		case CCMinecartsCo:
			return"§bC&C Minecarts Co.";
		case CliffsideVeins:
			return "§bCliffside Veins";
		case DivansGateway:
			return "§bDivan's Gateway";
		case DwarvenTavern:
			return "§bDwarven Tavern";
		case DwarvenVillage:
			return "§bDwarven Village";
		case FarReserve:
			return "§bFar Reserve";
		case ForgeBasin:
			return "§bForge Basin";
		case GatesToTheMines:
			return "§bGates To The Mines";
		case GoblinBurrows:
			return "§bGoblin Burrows";
		case GrandLibrary:
			return "§bGrand Library";
		case GreatIceWall:
			return "§bGreat Ice Wall";
		case HangingCourt:
			return "§bHanging Court";
		case LavaSprings:
			return "§bLava Springs";
		case MinersGuild:
			return"$bMiner's Guild";
		case PalaceBridge:
			return "§bPalace Bridge";
		case RampartsQuarry:
			return "§bRampart's Quarry";
		case RoyalMines:
			return "§bRoyal Mines";
		case RoyalPalace:
			return "§bRoyal Palace";
		case RoyalQuarters:
			return "§bRoyal Quarters";
		case TheForge:
			return "§bThe Forge";
		case TheLift:
			return "§bThe Lift";
		case TheMist:
			return "§7The Mist";
		case UpperMines:
			return "§bUpper Mines";
		default:
			break;
		 
		 }
		 return "§3Dwarven Mines";
	 }

	
}

