package me.CarsCupcake.SkyblockRemake.utils.SignGUI;

import me.CarsCupcake.SkyblockRemake.API.PacketRecieveEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayInUpdateSign;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import io.netty.channel.Channel;
import org.checkerframework.checker.units.qual.A;


public class SignManager {
    private final Plugin             plugin;
    private static  Map<UUID, SignGUI> guiMap;
    private       PluginManager      pluginManager;
    private static ArrayList<SignManager> managers = new ArrayList<>();



    @ConstructorProperties({"plugin"})
    public SignManager(Plugin plugin)
    {
        this.plugin = plugin;
        this.guiMap = new HashMap<>();
        this.pluginManager = Bukkit.getPluginManager();
    }

    public void init()
    {

        managers.add(this);
    }
    public static void recivePacket(UUID uuid, PacketPlayInUpdateSign packet){
        managers.forEach((t)->t.onPlayerJoin(packet, SkyblockPlayer.getSkyblockPlayer(Bukkit.getPlayer(uuid))));
    }

    public void onPlayerJoin(PacketPlayInUpdateSign inUpdateSign, SkyblockPlayer player)
    {





        if (guiMap.containsKey(player.getUniqueId())) {
            SignGUI signGUI = guiMap.get(player.getUniqueId());

            BlockPosition blockPosition = SignReflection.getValue(inUpdateSign, "b");
            String[] lines =  SignReflection.getValue(inUpdateSign, "c");
            String[] lines2 = new String[4];
            System.arraycopy(lines, 0, lines2, 0, lines.length);

            signGUI.getCompleteHandler().onAnvilClick(new SignCompleteEvent(player, blockPosition, lines2));
            guiMap.remove(player.getUniqueId());
        }


    }


    /**
     * Add New gui
     * @param uuid - UUID of the player
     * @param signGUI - {@link SignGUI} instance
     */
    void addGui(UUID uuid, SignGUI signGUI)
    {
        this.guiMap.put(uuid, signGUI);
    }

    protected Map<UUID, SignGUI> getGUIMap()
    {
        return guiMap;
    }
}
