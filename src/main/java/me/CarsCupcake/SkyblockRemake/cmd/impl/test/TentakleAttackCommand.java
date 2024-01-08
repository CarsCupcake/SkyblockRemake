package me.CarsCupcake.SkyblockRemake.cmd.impl.test;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.TentacleAnimations;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.entitys.KuudraFollower;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TentakleAttackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player p)) return false;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
        testobjectCMD.tentacle.getSpawnableEntities().add(new KuudraFollower(5));
        testobjectCMD.tentacle.setAnimations(TentacleAnimations.SpawnMobs);
        return false;
    }
}
