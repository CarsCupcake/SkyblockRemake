package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Secrets;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.Terminals.FallDownArmorstand;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Room;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;
import java.util.Set;

public class LeverSecret extends Secret {
    public static HashSet<LeverSecret> secrets = new HashSet<>();
    private final ChestSecret secret;
    private final Set<Block> blocks;
    private final Block block;
    private boolean hasInteracted = false;

    public LeverSecret(Block lever, Set<Block> blocks, ChestSecret chestSecret, Room base) {
        super(base);
        Assert.isTrue(lever.getType() == Material.LEVER, "Not a Lever at " + lever + "!");
        Assert.allNotNull("Null!", blocks);
        secret = chestSecret;
        this.blocks = blocks;
        block = lever;
    }

    public void leverInteract() {
        hasInteracted = true;
        for (Block b : blocks)
            new FallDownArmorstand(b.getType(), Tools.getAsLocation(b));
        if (secret != null) secret.setLocked(false);
        blocks.clear();
        secrets.remove(this);
    }

    @Override
    public boolean isCompleted() {
        return (secret != null) ? secret.isCompleted() : blocks.isEmpty();
    }

    public static class LeverInteract implements Listener {
        @EventHandler
        public void playerInteract(PlayerInteractEvent event) {
            if (event.getClickedBlock() == null || secrets.isEmpty()) return;
            for (LeverSecret s : new HashSet<>(secrets))
                if (s.block.equals(event.getClickedBlock()) && !s.hasInteracted) {
                    s.leverInteract();
                    event.getPlayer().sendMessage("§cYou hear the sound of something moving...");
                } else event.getPlayer().sendMessage("§cThis lever has already been used!");
        }
    }
}
