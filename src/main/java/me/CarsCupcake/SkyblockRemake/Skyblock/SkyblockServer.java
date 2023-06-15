package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.CrimsonIsle;
import me.CarsCupcake.SkyblockRemake.isles.End.EndListeners;
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
            Main.getMain().getLogger().log(Level.SEVERE, "\n*********************************************************\n\n\nNo Server type provided! Skyblock Plugin is shutting down!\n\n\n*********************************************************");
            Main.getMain().getServer().getPluginManager().disablePlugin(Main.getMain());

        }
        if(type == ServerType.Non){
            Main.getMain().getLogger().log(Level.SEVERE, "\n*********************************************************\n\n\nNo good Skyblock Server type provided! Please change it!\n\n\n*********************************************************");
        }
        Main.getMain().getLogger().fine("Server is running on server type " + type);
        server = this;
        this.type = type;

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
    public void init(){
        if(type == ServerType.End)
            EndListeners.init();

        if(type == ServerType.CrimsonIsle)
            new CrimsonIsle();
    }


}
