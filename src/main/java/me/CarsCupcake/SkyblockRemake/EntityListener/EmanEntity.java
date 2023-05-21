package me.CarsCupcake.SkyblockRemake.Entities.EntityListener;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class EmanEntity extends JavaPlugin implements Listener {
    private static File file;
    private static FileConfiguration emantpfile;
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("SkyblockRemake").getDataFolder(), "emantp.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
            }
        }
        emantpfile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return emantpfile;
    }

    public static void save(){
        try{
            emantpfile.save(file);
        }catch (IOException e){
            System.out.println("Couldn't save file");
        }
    }

    public static void reload(){
        emantpfile = YamlConfiguration.loadConfiguration(file);
    }

    @EventHandler
    public void onMiddle(final EntityTeleportEvent event) {
        if (event.getEntityType().equals(((Object) EntityType.ENDERMAN))) {
            event.setCancelled(true);
        }
    }
}
