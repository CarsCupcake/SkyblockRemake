package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Secrets;

import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Dungeon;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Room;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;

public class ChestSecret extends Secret {
    private static final HashSet<ChestSecret> secrets = new HashSet<>();
    private final Block block;
    @Setter
    private boolean isLocked;
    private boolean isDone = false;

    public ChestSecret(Block block, Room base) {
        this(block, false, base);
    }

    public ChestSecret(Block block, boolean isLocked, Room base) {
        super(base);
        Assert.isTrue(block.getType() == Material.CHEST);
        this.block = block;
        secrets.add(this);
        this.isLocked = isLocked;
    }
    public void open(SkyblockPlayer player){
        if(isLocked) return;
        isDone = true;
        secrets.remove(this);
        Chest chest = (Chest) block.getState();
        chest.open();
        Dungeon.INSTANCE.doSecret(this);
    }

    @Override
    public boolean isCompleted() {
        return isDone;
    }

    public static class ChestInteract implements Listener {
        @EventHandler
        public void playerInteract(PlayerInteractEvent event) {
            if (event.getClickedBlock() == null || secrets.isEmpty()) return;
            for (ChestSecret s : new HashSet<>(secrets))
                if (s.block.equals(event.getClickedBlock()) && !s.isLocked) {
                    event.setCancelled(true);
                    s.open(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
                } else {
                    if (s.isLocked) event.getPlayer().sendMessage("§cThis chest is locked!");
                    else event.getPlayer().sendMessage("§cThis lever has already been used!");
                }
        }
    }
}
