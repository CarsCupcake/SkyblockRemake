package me.CarsCupcake.SkyblockRemake.utils.SignGUI;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutBlockChange;
import net.minecraft.network.protocol.game.PacketPlayOutOpenSignEditor;
import net.minecraft.world.level.block.entity.TileEntitySign;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.jline.internal.Preconditions;
import org.bukkit.craftbukkit.v1_17_R1.block.CraftSign;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftMagicNumbers;

import java.beans.ConstructorProperties;

public class SignGUI {
    private final SignManager              signManager;
    private final SignClickCompleteHandler completeHandler;
    private SkyblockPlayer player;
    private       String[]                 lines;

    @ConstructorProperties({"signManager", "completeHandler"})
    public SignGUI(SignManager signManager, SignClickCompleteHandler completeHandler)
    {
        this.signManager = signManager;
        this.completeHandler = completeHandler;
        this.lines = new String[4];
        this.player = null;
    }

    public SignGUI withLines(String... lines)
    {
        if (lines.length != 4)
        {
            throw new IllegalArgumentException("Must have at least 4 lines");
        }

        this.lines = lines;
        return this;
    }

    public void open(SkyblockPlayer player)
    {
        this.player = player;

        final BlockPosition blockPosition = new BlockPosition(player.getLocation().getBlockX(), 255, player.getLocation().getBlockZ());

        PacketPlayOutBlockChange packet = new PacketPlayOutBlockChange(blockPosition,CraftMagicNumbers.getBlock(Material.OAK_SIGN).getBlockData());
        sendPacket(packet);

        IChatBaseComponent[] components = CraftSign.sanitizeLines(lines);
        TileEntitySign sign = new TileEntitySign(blockPosition, CraftMagicNumbers.getBlock(Material.OAK_SIGN).getBlockData());

        System.arraycopy(components, 0, sign.d, 0, sign.d.length);
        sendPacket(sign.getUpdatePacket());

        PacketPlayOutOpenSignEditor outOpenSignEditor = new PacketPlayOutOpenSignEditor(blockPosition);
        sendPacket(outOpenSignEditor);

        this.signManager.addGui(player.getUniqueId(), this);
    }

    private void sendPacket(Packet<?> packet)
    {
        Preconditions.checkNotNull(this.player);
         this.player.getHandle().b.sendPacket(packet);
    }

    SignClickCompleteHandler getCompleteHandler()
    {
        return this.completeHandler;
    }
}
