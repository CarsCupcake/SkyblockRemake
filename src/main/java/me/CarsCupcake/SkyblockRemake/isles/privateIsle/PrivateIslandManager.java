package me.CarsCupcake.SkyblockRemake.isles.privateIsle;

import com.fastasyncworldedit.core.math.MutableBlockVector2;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.regions.Polygonal2DRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BlockTypes;
import me.CarsCupcake.SkyblockRemake.Configs.ConfigFile;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;

public class PrivateIslandManager {
    public static final HashMap<SkyblockPlayer, Location> baseLocations = new HashMap<>();
    //Max travel distance: 120, Every so + 300 for a new isle
    public static void addToIsle(SkyblockPlayer player){
        ConfigFile config = new ConfigFile(player, "PrivateIsle");
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
        isleLocation.getChunk().load();
        player.teleport(isleLocation);
        baseLocations.put(player, isleLocation);
        new PrivateIsle(player);
    }

    public static Location createNewIsle(SkyblockPlayer player){

        ConfigFile config = new ConfigFile("privateIsle");
        int distance = config.get().getInt("distanceBetweenLocs", 0);
        config.get().set("distanceBetweenLocs", distance + 300);
        config.save();
        Location base = new Location(Bukkit.getWorld("world"),7.5 + distance,100,7.5);

        Bukkit.getScheduler().runTaskAsynchronously(Main.getMain(), new ReplaceRunnable( player, base));


        return base;
    }
    private static class ReplaceRunnable implements Runnable{
        static HashMap<SkyblockPlayer, ReplaceRunnable> runs = new HashMap<>();
        private final SkyblockPlayer player;
        private final Location base;
        double i = 0;
        double size = 0;
        public ReplaceRunnable( SkyblockPlayer player, Location base){
            this.player = player;
            this.base = base;
            runs.put(player, this);
        }

        @Override
        public void run() {
            long before = System.currentTimeMillis();
            Region region = new Polygonal2DRegion(BukkitAdapter.adapt(base.getWorld()),
                    List.of(new MutableBlockVector2((int) (base.getX() + 120), (int) (base.getZ() + 80)), new MutableBlockVector2((int) (base.getX() + 80), (int) (base.getZ() - 80)),
                            new MutableBlockVector2((int) (base.getX() - 80), (int) (base.getZ() - 80)), new MutableBlockVector2((int) (base.getX() - 80), (int) (base.getZ() + 80))), 0, 200);
            BukkitWorld world = new BukkitWorld(base.getWorld());
            player.sendMessage("§cYour world is getting prepaired, please wait");

            try {
                EditSession session = WorldEdit.getInstance().newEditSession(region.getWorld());
                session.setBlocks((Region) region, BlockTypes.AIR);
                session.close();
                player.sendMessage("§aThis operation took " + (System.currentTimeMillis()- before) + "ms");
            }catch (Exception e){
                e.printStackTrace();
            }
            Tools.loadShematic("assets/schematics/privateIsle/privateIsle.schem", base);
            player.sendMessage("§aReplacing blocks done, have fun");
            Bukkit.getScheduler().runTask(Main.getMain(), () -> player.teleport(base));
        }
    }
}
