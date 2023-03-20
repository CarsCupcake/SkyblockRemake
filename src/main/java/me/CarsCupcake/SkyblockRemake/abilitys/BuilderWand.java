package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.*;

public class BuilderWand implements AbilityManager<PlayerInteractEvent> {
    public static Map<Player, List<Block>> blocksMap = new HashMap<>();
    public static Map<Player, Integer> blocksAmount = new HashMap<>();

    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        Block paramBlock = event.getClickedBlock();
        fillConnectedFaces(player, paramBlock, event.getBlockFace());
        return false;
    }

    public void fillConnectedFaces(Player player, Block origin, BlockFace face) {
        Material fillMaterial = origin.getType();
        int blockLimit = 164;
        List<Block> blocks = new ArrayList<>();
        List<Block> blocksForUndo = new ArrayList<>();
        blocks.add(origin);
        World w = player.getWorld();
        Vector check[] = null, translate = null;
        int blocksPlaced = 0;
        switch (face) {
            case NORTH:
            case SOUTH:
                check = new Vector[]{new Vector(-1, -1, 0), new Vector(-1, 0, 0), new Vector(-1, 1, 0), new Vector(0, -1, 0), new Vector(0, 1, 0), new Vector(1, -1, 0), new Vector(1, 0, 0), new Vector(1, 1, 0)};
                break;
            case EAST:
            case WEST:
                check = new Vector[]{new Vector(0, -1, -1), new Vector(0, -1, 0), new Vector(0, -1, 1), new Vector(0, 0, -1), new Vector(0, 0, 1), new Vector(0, 1, -1), new Vector(0, 1, 0), new Vector(0, 1, 1)};
                break;
            case UP:
            case DOWN:
                check = new Vector[]{new Vector(-1, 0, -1), new Vector(-1, 0, 0), new Vector(-1, 0, 1), new Vector(0, 0, -1), new Vector(0, 0, 1), new Vector(1, 0, -1), new Vector(1, 0, 0), new Vector(1, 0, 1)};
                break;
        }
        switch (face) {
            case NORTH:
                translate = new Vector(0, 0, -1);
                break;
            case SOUTH:
                translate = new Vector(0, 0, 1);
                break;
            case EAST:
                translate = new Vector(1, 0, 0);
                break;
            case WEST:
                translate = new Vector(-1, 0, 0);
                break;
            case UP:
                translate = new Vector(0, 1, 0);
                break;
            case DOWN:
                translate = new Vector(0, -1, 0);
                break;
        }
        while (blocks.size() > 0 && blockLimit > 0) {
            Location l = (blocks.get(0)).getLocation();
            for (Vector vector : check) {
                if (w.getBlockAt(l.clone().add(vector)).getType() == fillMaterial && w
                        .getBlockAt(l.clone().add(vector).clone().add(translate)).getType() == Material.AIR)
                    blocks.add(w.getBlockAt(l.clone().add(vector)));
                blocksForUndo.add(w.getBlockAt(l.clone().add(vector)));
            }
            Block fillBlock = w.getBlockAt(l.clone().add(translate));
            if (canPlaceBlock(player, fillBlock.getLocation())) {
                blocks.removeIf(blocks.get(0)::equals);
                if (fillBlock.getType() != fillMaterial) {
                    fillBlock.setType(fillMaterial);
                    blockLimit--;
                    blocksPlaced++;
                }
                if (blocksPlaced == blockLimit)
                    break;
                continue;
            }
            blocks.removeIf(blocks.get(0)::equals);
            blockLimit--;
        }
        if (blocksPlaced == 0) {
            player.sendMessage("§cYou cannot place any blocks! You do not have enough blocks to place with your Builder's wand!");
        }
        if (blocksPlaced != 0) {
            blocksMap.put(player, blocksForUndo);
            blocksAmount.put(player, blocksPlaced);


            TextComponent component = new TextComponent("§eYou built §a" + blocksPlaced + "§e blocks! ");

            player.spigot().sendMessage(component);

        }
    }

    public int countBlocks(Inventory inv, Material m, ItemStack stack) {
        int blockAmount = 0;

        for (ListIterator<ItemStack> listIterator = inv.iterator(); listIterator.hasNext(); ) {
            ItemStack item = listIterator.next();
            if (item != null &&
                    item.getType() == m)
                blockAmount += item.getAmount();
        }
        return blockAmount;
    }
    public boolean canPlaceBlock(Player player, Location l) {
        return true;
    }
}


