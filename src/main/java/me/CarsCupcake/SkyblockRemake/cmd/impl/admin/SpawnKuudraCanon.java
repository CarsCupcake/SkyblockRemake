package me.CarsCupcake.SkyblockRemake.cmd.impl.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.CanonObject;


public class SpawnKuudraCanon implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg2.equalsIgnoreCase("canon")) {
			new CanonObject(( ((Player)arg0).getLocation() ));
			arg0.sendMessage("Spawned a new Canon obkect");
			return true;
		}
		return false;
	}

}
