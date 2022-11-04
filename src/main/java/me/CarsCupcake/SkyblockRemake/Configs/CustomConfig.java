package me.CarsCupcake.SkyblockRemake.Configs;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private final File file;
    private  FileConfiguration customFile;
    public CustomConfig(File file){
        this.file = file;
        init();
    }
    public CustomConfig(String name){
        file = new File(Main.getMain().getDataFolder().getPath() , name + ".yml");
        init();
    }
    private void init(){
        setup();
        save();
        reload();
    }
    public  void setup() {


        if(!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException ignored) {

            }
        }

        customFile = YamlConfiguration.loadConfiguration(file);

    }

    public  FileConfiguration get() {
        return customFile;
    }

    public  void save() {
        try {
            customFile.save(file);
        }catch(IOException e) {
            System.out.println(file.getName() + " has saving errors");
        }
    }

    public  void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
