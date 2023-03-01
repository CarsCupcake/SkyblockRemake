package me.CarsCupcake.SkyblockRemake.isles.privateIsle;

import com.fastasyncworldedit.core.math.MutableBlockVector2;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.OutputExtent;
import com.sk89q.worldedit.math.BlockVector2;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Polygonal2DRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockVector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrivateIslandManager {
    public static final HashMap<SkyblockPlayer, Location> baseLocations = new HashMap<>();
    //Max travel distance: 120, Every so + 300 for a new isle
    public static void addToIsle(SkyblockPlayer player){
        CustomConfig config = new CustomConfig(player, "PrivateIsle");
        ConfigurationSection section = config.get().getConfigurationSection("privateSpace");
        Location isleLocation;
        if(section == null){
            isleLocation = createNewIsle(player);
            config.get().set("privateSpace.x", isleLocation.getX());
            config.get().set("privateSpace.y", isleLocation.getY());
            config.get().set("privateSpace.z", isleLocation.getZ());
            config.save();
        }else
            isleLocation = new Location(Bukkit.getWorld("world") ,section.getDouble("x", 7.5), section.getDouble("y", 100), section.getDouble("z", 7.5));
        player.teleport(isleLocation);
        baseLocations.put(player, isleLocation);
    }

    public static Location createNewIsle(SkyblockPlayer player){

        CustomConfig config = new CustomConfig("privateIsle");
        int distance = config.get().getInt("distanceBetweenLocs", 0);
        config.get().set("distanceBetweenLocs", distance + 300);
        config.save();
        Location base = new Location(Bukkit.getWorld("world"),7.5 + distance,100,7.5);
        BossBar progressBar = Bukkit.createBossBar("Progress", BarColor.YELLOW, BarStyle.SOLID);
        progressBar.addPlayer(player);
        progressBar.setProgress(0);

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                ReplaceRunnable r = ReplaceRunnable.runs.get(player);
                double mult = r.i / r.size;
                if(mult < 1)
                    progressBar.setProgress(mult);
                else progressBar.setProgress(1);
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                progressBar.removeAll();
                super.cancel();
            }
        };
        runnable.runTaskTimer(Main.getMain(), 10, 10);

        Bukkit.getScheduler().runTaskAsynchronously(Main.getMain(), new ReplaceRunnable(runnable, player, base));


        return base;
    }
    private static class ReplaceRunnable implements Runnable{
        static HashMap<SkyblockPlayer, ReplaceRunnable> runs = new HashMap<>();
        private final SkyblockPlayer player;
        private final BukkitRunnable runnable;
        private final Location base;
        double i = 0;
        double size = 0;
        public ReplaceRunnable(BukkitRunnable runnable, SkyblockPlayer player, Location base){
            this.player = player;
            this.runnable = runnable;
            this.base = base;
            runs.put(player, this);
        }

        @Override
        public void run() {
            Region region = new Polygonal2DRegion(BukkitAdapter.adapt(base.getWorld()),
                    List.of(new MutableBlockVector2((int) (base.getX() + 120), (int) (base.getZ() + 120)), new MutableBlockVector2((int) (base.getX() + 120), (int) (base.getZ() - 120)),
                            new MutableBlockVector2((int) (base.getX() - 120), (int) (base.getZ() - 120)), new MutableBlockVector2((int) (base.getX() - 120), (int) (base.getZ() + 120))), 0, 200);
            BukkitWorld world = new BukkitWorld(base.getWorld());
            player.sendMessage("§cYour world is getting prepaired, please wait");

            try {
                size = region.getVolume();
                for (BlockVector3 vector3 : region){
                    Block b = new Location(base.getWorld(),vector3.getX(), vector3.getY(), vector3.getZ()).getBlock();
                    i++;
                    if(b.getType() == Material.AIR)
                        continue;
                    world.setBlock(vector3, new BaseBlock(0, 0));
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            Tools.loadShematic("assets/schematics/privateIsle/privateIsle.schem", base);
            player.sendMessage("§aReplacing blocks done, have fun");
            Bukkit.getScheduler().runTask(Main.getMain(), () -> player.teleport(base));
            runnable.cancel();
        }
    }
}
