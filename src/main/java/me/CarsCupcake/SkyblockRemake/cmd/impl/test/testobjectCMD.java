package me.CarsCupcake.SkyblockRemake.cmd.impl.test;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraBossfight;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.TentacleAnimations;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraTentacle;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.Necron;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;


public class testobjectCMD implements CommandExecutor {
    public static KuudraTentacle tentacle;
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        new Necron(7, false).spawn(((Player) sender).getLocation());
        return false;
    }
}
