package me.CarsCupcake.SkyblockRemake.cmd.impl.test;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.scoreboard.ScoreboardSection;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraTentacle;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraTentacleMover;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;


public class testobjectCMD implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) sender);
        KuudraTentacle tentacle = new KuudraTentacle();
        tentacle.spawn(player.getLocation());
        new EntityRunnable() {
            @Override
            public void run() {
                KuudraTentacleMover.moveTentacle(tentacle, player.getLocation());
            }
        }.runTaskTimer(tentacle, 1, 1);
        return false;
    }
}
