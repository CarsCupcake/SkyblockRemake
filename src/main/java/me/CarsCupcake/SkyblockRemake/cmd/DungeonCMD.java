package me.CarsCupcake.SkyblockRemake.cmd;


import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BlockTypes;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Generator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class DungeonCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
        Player player = (Player) arg0;
        if (arg3.length == 1 && arg3[0].equals("wipe")) {
            new BukkitRunnable() {
                public void run() {
                    long before = System.currentTimeMillis();
                    CuboidRegion region = new CuboidRegion(BlockVector3.at(-103, 0, -103), BlockVector3.at(259, 150, 259));
                    region.setWorld(BukkitAdapter.adapt(Bukkit.getWorld("world")));
                    EditSession session = WorldEdit.getInstance().newEditSession(region.getWorld());
                    session.setBlocks((Region) region, BlockTypes.AIR);
                    session.close();
                    player.sendMessage("§aThis operation took " + (System.currentTimeMillis()- before) + "ms");
                }
            }.runTaskAsynchronously(Main.getMain());
            /*Runnable r = () -> Tools.makeAir(new Location(Bukkit.getWorld("world"), -103, 0, -103), new Location(Bukkit.getWorld("world"), 259, 150, 259));
            Main.getMain().getServer().getScheduler().runTaskAsynchronously(Main.getMain(), r);*/
            return true;
        }
        Generator.place();
        player.sendMessage("§aGenerated test dungeon at 0 0 0");
        return false;
    }

}
