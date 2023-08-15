package me.CarsCupcake.SkyblockRemake.utils.SignGUI;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayInUpdateSign;

import java.beans.ConstructorProperties;
import java.util.*;

import org.bukkit.Bukkit;


public class SignManager {
    private static  Map<UUID, SignGUI> guiMap;
    private static final ArrayList<SignManager> managers = new ArrayList<>();



    @ConstructorProperties({"plugin"})
    public SignManager()
    {
        guiMap = new HashMap<>();
    }

    public void init()
    {

        managers.add(this);
    }
    public static void recivePacket(UUID uuid, PacketPlayInUpdateSign packet){
        managers.forEach((t)->t.onPlayerJoin(packet, SkyblockPlayer.getSkyblockPlayer(Bukkit.getPlayer(uuid))));
    }

    public void onPlayerJoin(PacketPlayInUpdateSign inUpdateSign, SkyblockPlayer player) {
        try {
            Bukkit.getScheduler().runTask(Main.getMain(), () -> {
                if (guiMap.containsKey(player.getUniqueId())) {
                    SignGUI signGUI = guiMap.get(player.getUniqueId());
                    BlockPosition blockPosition = SignReflection.getValue(inUpdateSign, "b");
                    String[] lines = SignReflection.getValue(inUpdateSign, "c");
                    String[] lines2 = new String[4];
                    System.arraycopy(lines, 0, lines2, 0, lines.length);
                    signGUI.getCompleteHandler().onAnvilClick(new SignCompleteEvent(player, blockPosition, lines2));
                    guiMap.remove(player.getUniqueId());
                }
            });
        }catch (Exception e) {
            System.out.println("Error while reding sign packet!");
            e.printStackTrace(System.err);
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }


    /**
     * Add New gui
     * @param uuid - UUID of the player
     * @param signGUI - {@link SignGUI} instance
     */
    void addGui(UUID uuid, SignGUI signGUI)
    {
        guiMap.put(uuid, signGUI);
    }

}
