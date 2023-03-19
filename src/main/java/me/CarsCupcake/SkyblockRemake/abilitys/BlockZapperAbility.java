package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BlockZapperAbility implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        doBlockEater(event.getClickedBlock().getType(), event.getClickedBlock(), 30, player);
        TextComponent Zappedcomp = new TextComponent("§eZapped §a" + blockAmount.get(player) + "§e blocks! ");
        TextComponent unzapcomp = Tools.makeClickableText("§a§lUNDO", "§eClick to undo the latest zap!", ClickEvent.Action.RUN_COMMAND, "/undozap");
        player.spigot().sendMessage(Zappedcomp , unzapcomp);
        for(int i = 0; i < i1; ++i) {
            player.getInventory().addItem(new ItemStack(event.getClickedBlock().getType()));
        }
        return false;
    }
    public static int i1 = 0;
    public static List<Block> zapped = new ArrayList<>();
    public static Map<Player, List<Block>> blocks = new HashMap<>();
    public static Map<Player, Integer> blockAmount = new HashMap<>();
    public static Map<Player, Material> material = new HashMap<>();


    public void doBlockEater(Material type, Block origin, int amount, Player player) {
        zapped.clear();
        blocks.remove(player);
        i1 = 0;
        List<Block> blockList = new ArrayList<>();
        material.put(player, type);
        blockList.add(origin);
        for (int i = 0; i <= amount; ++i) {
            List<Block> preClonedBlocks = new ArrayList<>(blockList);
            for (Block b : preClonedBlocks) {
                blockList.remove(b);
                Block up = b.getRelative(BlockFace.UP);
                Block down = b.getRelative(BlockFace.DOWN);
                Block north = b.getRelative(BlockFace.NORTH);
                Block east = b.getRelative(BlockFace.EAST);
                Block west = b.getRelative(BlockFace.WEST);
                Block south = b.getRelative(BlockFace.SOUTH);
                for (Block nearby : Arrays.asList(up, down, north, east, south, west)) {
                    if (nearby.getType().equals(type)) {
                        nearby.setType(Material.AIR);
                        blockList.add(nearby);
                        zapped.add(nearby);
                        blocks.put(player, zapped);
                        ++i1;
                        blockAmount.put(player, i1);
                    }
                }
            }
        }
    }
    public static void undoZap(Player player) {
        try {
            for (Block block : blocks.get(player)) {
                block.setType(material.get(player));
            }
        } catch (Exception e) {
            player.sendMessage("§cFailded to undoZap Blocks");
        }
    }
}

