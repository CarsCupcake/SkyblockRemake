package me.CarsCupcake.SkyblockRemake.cmd.impl.generals;

import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraBossfight;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class StartKuudra implements CommandExecutor{

	@Override
	public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
		System.out.println("sdlkfjhbsdjfkgbsdfgfkpljdfshbgpokjfdhn");
		new KuudraBossfight(Integer.parseInt(arg3[0]));
		return false;
	}

}
