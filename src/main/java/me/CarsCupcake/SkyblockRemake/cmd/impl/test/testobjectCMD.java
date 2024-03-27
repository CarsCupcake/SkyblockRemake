package me.CarsCupcake.SkyblockRemake.cmd.impl.test;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.boss.loot.LootPlace;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraBossfight;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.TentacleAnimations;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraTentacle;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.Necron;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;


public class testobjectCMD implements CommandExecutor {
    public static KuudraTentacle tentacle;
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) sender);
        LootPlace place = new LootPlace(player.getLocation().add(0, -1, 0), Material.IRON_BLOCK, null);
        Bukkit.getScheduler().runTaskLater(Main.getMain(), place::release, 200);
        return false;
    }
}
