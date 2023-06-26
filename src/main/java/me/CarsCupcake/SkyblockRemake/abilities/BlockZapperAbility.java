package me.CarsCupcake.SkyblockRemake.abilities;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BlockZapperAbility implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        event.setCancelled(true);
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        Zapper zapper = new Zapper(event.getClickedBlock().getType(), event.getClickedBlock(), 30, player);
        for(int i = 0; i < zapper.done; ++i)
            player.getInventory().addItem(new ItemStack(event.getClickedBlock().getType()));
        return false;
    }
    public static class Zapper{
        public static final HashMap<UUID, Zapper> zapps = new HashMap<>();
        private final SkyblockPlayer player;
        private final UUID uuid;
        private final Set<Block> blocks = new HashSet<>();
        private final Material material;
        @Getter
        private int done = 0;

        public Zapper(Material material, Block origin, int amount, SkyblockPlayer player){
            this.material = material;
            UUID id;
            do {
                id = UUID.randomUUID();
            }while (zapps.containsKey(id));
            zapps.put(id, this);
            uuid = id;
            this.player = player;
            List<Block> blockList = new ArrayList<>();
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
                        if (nearby.getType().equals(material)) {
                            nearby.setType(Material.AIR);
                            blockList.add(nearby);
                            blocks.add(nearby);
                            done++;
                        }
                    }
                }
            }
            TextComponent Zappedcomp = new TextComponent("§eZapped §a" + done + "§e blocks! ");
            TextComponent unzapcomp = Tools.makeClickableText("§a§lUNDO", "§eClick to undo the latest zap!", ClickEvent.Action.RUN_COMMAND, "/undozap " + id);
            player.spigot().sendMessage(Zappedcomp , unzapcomp);
        }
        public void undoZap(SkyblockPlayer player) {
            if(zapps.get(uuid).player != player)
                return;
            try {
                for (Block block : blocks) {
                    block.setType(material);
                }
            } catch (Exception e) {
                player.sendMessage("§cFailded to undoZap Blocks");
            }
        }
    }
}

