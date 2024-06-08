package me.CarsCupcake.SkyblockRemake.cmd.impl.test;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.boss.loot.LootPlace;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraBossfight;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.TentacleAnimations;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraTentacle;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.Necron;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Dungeon;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.DungeonRoomsTypes;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Location2d;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.BaseRoom;
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
        if (Dungeon.INSTANCE == null) new Dungeon(7, true);
        BaseRoom room = new BaseRoom("1x1-Lots-Of-Floors-3", DungeonRoomsTypes.r1x1);
        room.place(new Location2d(0, 0), 0);
        new BukkitRunnable() {
            @Override
            public void run() {
                room.discover();
            }
        }.runTaskLater(Main.getMain(), 20);
        return false;
    }
}
