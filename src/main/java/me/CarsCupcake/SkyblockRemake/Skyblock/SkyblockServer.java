package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.End.EndListeners;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SkyblockServer {
    private static SkyblockServer server;
    private final ServerType type;

    public SkyblockServer(ServerType type){
        if(type == null){
            for (Player p : Bukkit.getOnlinePlayers())
                p.sendTitle("§cThere is no Server type!", "§ePlease set a server type in the files!",20,400,20);
            Main.getMain().getLogger().log(Level.SEVERE, "No Server type provided! Skyblock Plugin is shutting down!");
            Main.getMain().getServer().getPluginManager().disablePlugin(Main.getMain());
        }
        server = this;
        this.type = type;
        if(type == ServerType.End)
            EndListeners.init();
    }
    public ServerType  getType(){
        return type;
    }
    public static SkyblockServer getServer(){
        return server;
    }
    public static SkyblockServer makeServerFromPort(int port){
        if(!Main.isLocalHost)
            return new SkyblockServer(ServerType.DwarvenMines);
        else
            return new SkyblockServer(ServerType.getServerByPort(port));
    }


}
