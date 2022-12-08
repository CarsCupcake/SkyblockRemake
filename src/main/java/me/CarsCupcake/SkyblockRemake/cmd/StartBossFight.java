package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Phase1;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.*;
import me.CarsCupcake.SkyblockRemake.End.Dragon.StartFight;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F1.BonzoPhase1;

public class StartBossFight implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player && arg3.length == 1) {
			if(arg3[0].equals("1")) {
				BonzoPhase1 b = new BonzoPhase1();
				b.spawn(((Player)arg0).getLocation());
			}
			if(arg3[0].equals("7")) {
				Maxor m = new Maxor();
				m.spawn(new Location(Bukkit.getWorld("world"),72.5,227,56.5));
				Main.getMain().getServer().getPluginManager().registerEvents(new F7Phase1(m), Main.getMain());
			}
			if(arg3[0].equals("dragon")) {
				StartFight.startDragonFight();
			}
			if(arg3[0].equals("6")) {
				new Phase1();
			}
		}else{
			if(arg3[0].equals("7")) {
				if(arg3[1].equalsIgnoreCase("maxor")){
					new Maxor().spawn(((Player)arg0).getLocation());
				}if(arg3[1].equalsIgnoreCase("storm")){
					Storm m = new Storm();
					m.spawn(((Player)arg0).getLocation());

					Main.getMain().getServer().getPluginManager().registerEvents(new F7Phase2(m), Main.getMain());
				}
				if(arg3[1].equalsIgnoreCase("goldor")){
					new F7Phase3();
				}

			}
		}
		return false;
	}

}
