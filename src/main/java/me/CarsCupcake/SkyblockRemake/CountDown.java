package me.CarsCupcake.SkyblockRemake;


import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDown extends BukkitRunnable {
	 
int delay;
Player player = null;
 
public CountDown(Player player, int totdelay) {
this.delay = totdelay;//change me
this.player = player;
}
 
@Override
public void run() {
if(player == null){ //if for what ever the reason the player goes offline it will cancel the runnable
this.cancel();
}
System.out.println("test");
if (this.delay > 0) {
	player.setAbsorptionAmount(16);
switch (this.delay) {


default:
break;
}
 
this.delay--;
} else {
player.setAbsorptionAmount(0);
this.cancel();
}
}

}
