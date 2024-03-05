package me.CarsCupcake.SkyblockRemake.cmd.impl.test;

import com.sk89q.worldedit.internal.SchematicsEventListener;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.FileUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RegenF7Boss implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        //-7.5 255 147
        Bukkit.getOnlinePlayers().forEach((player -> player.kickPlayer("Regenerating")));
        World world;
        WorldCreator creator = new WorldCreator("bossroom");
        creator.environment(World.Environment.NORMAL);
        creator.generator(new EmptyWorldGenerator());
        world = creator.createWorld();
        SkyblockServer.getServer().gamerules();
        Tools.loadShematic("assets/schematics/dungeon/bossfight/7/bossroom.schem", new Location(world, -7.5, 255, 147));
        System.out.println("DONE!");
        return false;
    }
    public static class EmptyWorldGenerator extends ChunkGenerator {
        @Override
        public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {

        }

        @Override
        public boolean canSpawn(@NotNull World world, int x, int z) {
            return false;
        }
    }
}
