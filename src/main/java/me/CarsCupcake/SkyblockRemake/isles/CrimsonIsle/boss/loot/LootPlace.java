package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.boss.loot;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.StandUtils;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.loot.ItemLoot;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LootPlace {
    private final Set<Tools.FakeBlock> fakeBlocks = new HashSet<>();

    public LootPlace(Location center, Material material, HashMap<SkyblockPlayer, List<ItemLoot>> loot) {
        Block block = center.getBlock();
        List<Location> locs = StandUtils.generateSphere(center, 4, false);
        locs.add(center.clone().add(4, 0, 0));
        locs.add(center.clone().add(-4, 0, 0));
        locs.add(center.clone().add(0, 0, -4));
        locs.add(center.clone().add(0, 0, 4));
        for (Location location : locs) {
            if (location.getBlock().equals(block)) continue;
            fakeBlocks.add(Tools.placeFakeBlock(location.getBlock(), material));
        }
        fakeBlocks.add(Tools.placeFakeBlock(block, Material.BEACON));
        Location[] diamond = new Location[]{center.clone().subtract(0, 1, 0), center.clone().subtract(1, 1, 0), center.clone().subtract(0, 1, 1),
                center.clone().subtract(-1, 1, 0), center.clone().subtract(0, 1, -1), center.clone().subtract(1, 1, 1), center.clone().subtract(1, 1, -1),
                center.clone().subtract(-1, 1, 1), center.clone().subtract(-1, 1, -1)};
        for (Location l : diamond)
            fakeBlocks.add(Tools.placeFakeBlock(l.getBlock(), Material.DIAMOND_BLOCK));
        Block b = block;
        if (b.getY() < 255) while (b.getY() != 255) {
            b = b.getRelative(BlockFace.UP);
            if (b.getType() != Material.AIR) fakeBlocks.add(Tools.placeFakeBlock(b, Material.GLASS));
        }
    }

    public void release() {
        fakeBlocks.forEach(Tools.FakeBlock::release);
        fakeBlocks.clear();
    }
}
