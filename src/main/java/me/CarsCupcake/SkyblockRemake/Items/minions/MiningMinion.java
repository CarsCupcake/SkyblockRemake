package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.HashSet;
import java.util.Set;

public class MiningMinion extends AbstractMinion{
    private final AbstractMiningMinion minion;
    public MiningMinion(int level, AbstractMiningMinion base, Location location,String minionid ,SkyblockPlayer player) {
        super(level, base, location, minionid, player);
        minion = base;
    }

    @Override
    public boolean isInventoryFull() {
        return false;
    }

    @Override
    void startGetAnimation() {

    }

    @Override
    boolean startGenerateAnimation() {
        Set<Block> missing = getMissingBlocks();
        if(missing.isEmpty())
            return true;

        Block target = getMissingBlocks().iterator().next();
        missing.remove(target);

        Location lo = stand.getLocation().setDirection(Tools.getAsLocation(target).clone().subtract(stand.getLocation()).toVector());
        EulerAngle angle = new EulerAngle(Math.toRadians(lo.getPitch()), Math.toRadians(lo.getYaw()), 0);
        stand.teleport(lo);
        stand.setHeadPose(angle);

        new BukkitRunnable() {
            double rotation = 0;
            int through;
            int i = 0;
            @Override
            public void run() {
                if (stand == null || stand.isDead()) {
                    cancel();
                    return;
                }

                if(rotation >= 180){
                    rotation = 0;
                    through += 1;
                    if(through == 4){
                        cancel();
                        target.setType(Material.AIR);
                        generateLoot();
                        return;
                    }
                }

                rotation += 180d/4d;

                stand.setRightArmPose(new EulerAngle(0,0,rotation));
                PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(1, new BlockPosition(target.getX(), target.getY(), target.getZ()), getBlockBreakStage(i));
                for (Player p : Bukkit.getOnlinePlayers()){
                    ((CraftPlayer) p).getHandle().b.sendPacket(animation);
                }

                i++;
            }
        }.runTaskTimer(Main.getMain(), 10, 5);

        return missing.isEmpty();
    }

    private int getBlockBreakStage(int currentTick) {
        double result = ((double) currentTick / (double) 16) * 10;
        return (int) result;
    }

    @Override
    boolean isMaxGenerated() {
        return getMissingBlocks().isEmpty();
    }

    private Set<Block> getMissingBlocks(){
        Set<Block> blocks = new HashSet<>();
        for (Block block : Tools.getBlocksBetween(location.clone().add(2, -1, 2).getBlock(), location.clone().subtract(2, 1, 2).getBlock())) {
            if(block.getX() == location.getBlockX() && block.getY() == location.getBlockY() - 1 && block.getZ() == location.getBlockZ())
                continue;
            if(block.getType() != minion.representiveBlock())
                blocks.add(block);
        }
        return blocks;
    }
}
