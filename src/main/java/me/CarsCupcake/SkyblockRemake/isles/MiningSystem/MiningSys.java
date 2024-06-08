package me.CarsCupcake.SkyblockRemake.isles.MiningSystem;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class MiningSys {
    private static final HashMap<SkyblockPlayer, MiningSys> mines = new HashMap<>();
    @Getter
    private static final HashMap<Material, Class<? extends MiningBlock>> registeredBlocks = new HashMap<>();
    @Getter
    private final SkyblockPlayer player;
    private BukkitRunnable runn;
    @Getter
    private me.CarsCupcake.SkyblockRemake.isles.MiningSystem.MiningBlock block;
    public int ticks;
    public Block b;
    private boolean hasStoped = false;

    public MiningSys(SkyblockPlayer player) {
        this.player = player;
        mines.put(player, this);
    }

    public void stopMinig() {
        try {
            hasStoped = true;
            runn.cancel();
            PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(1, new BlockPosition(b.getX(), b.getY(), b.getZ()), 10);
            player.getHandle().b.sendPacket(animation);
        } catch (Exception ignored) {
        }
    }

    public static MiningSys getMiningSystem(SkyblockPlayer player) {
        return mines.get(player);
    }

    public static MiningSys getMiningSystem(Player player) {
        return mines.get(SkyblockPlayer.getSkyblockPlayer(player));
    }


    public void startMining(Block block) {
        if (!checkValid(block)) return;
        if (player.getEquipment().getItemInMainHand().getItemMeta() != null) {
            ItemManager manager = Items.SkyblockItems.get(player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
            if (manager != null && manager.type == ItemType.Drill) {
                if (player.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER) <= 0)
                    return;
            }
        }
        try {
            this.block = registeredBlocks.get(block.getType()).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return;
        }
        if (this.block == null) return;
        if (this.block.getBreakingPower() > Main.getPlayerStat(player, Stats.BreakingPower)) {
            return;
        }
        SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);
        final int ticks;
        double speed = Main.getPlayerStat(player, Stats.MiningSpeed);
        if (speed >= this.block.getSoftCap()) {
            if (speed >= this.block.getInstaMineSpeed()) ticks = 1;
            else ticks = 4;
        } else ticks = this.block.getMiningTicks(player);
        this.ticks = ticks;
        b = block;
        runn = new BukkitRunnable() {
            public int ti = ticks;

            @Override
            public void run() {
                if (!b.getLocation().equals(block.getLocation()) || runn.isCancelled()) return;
                int t = ti;
                t -= 1;
                PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(1, new BlockPosition(block.getX(), block.getY(), block.getZ()), getBlockBreakStage(ticks, ticks - t));

                MiningSys.this.ticks = t;
                ti = t;
                if (t <= 0) {
                    this.cancel();
                    p.breakBlock(block);
                    animation = new PacketPlayOutBlockBreakAnimation(1, new BlockPosition(block.getX(), block.getY(), block.getZ()), 10);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (checkValid(b.getLocation().getBlock()) && !hasStoped && MiningSys.this.block.getBreakingPower() <= Main.getPlayerStat(player, Stats.BreakingPower)) {
                                startMining(block.getLocation().getBlock());
                            }
                        }
                    }.runTaskLater(Main.getMain(), 1);
                }
                for (Player pp : Bukkit.getOnlinePlayers())
                    ((CraftPlayer) pp).getHandle().b.sendPacket(animation);
            }
        };
        runn.runTaskTimer(Main.getMain(), 0, 1);
    }

    public int getBlockBreakStage(int totalTicks, int currentTick) {
        double result = ((double) currentTick / (double) totalTicks) * 10;
        return (int) result;
    }

    public boolean checkValid(Block block) {
        return registeredBlocks.containsKey(block.getType());
    }
}
