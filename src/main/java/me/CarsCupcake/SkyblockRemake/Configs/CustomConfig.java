package me.CarsCupcake.SkyblockRemake.Configs;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private File file;
    private FileConfiguration customFile;

    public CustomConfig(File file) {
        this.file = file;
        init();
    }

    public CustomConfig(String name) {
        //"C:\\Users\\09car\\Desktop\\Plugin\\Skyblock 1.17.1 Network\\files"
        String path = Main.getMain().config.getString("SkyblockDataPath");
        file = new File(path, name + ".yml");
        init();
    }

    public CustomConfig(String name, boolean isInDataPath) {
        if (!isInDataPath) file = new File(Main.getMain().getDataFolder(), name + ".yml");
        else {
            String path = Main.getMain().config.getString("SkyblockDataPath");
            file = new File(path, name + ".yml");
        }
        init();
    }

    public CustomConfig(SkyblockPlayer player, String name) {
        this(new File(Main.getMain().config.getString("SkyblockDataPath") + "\\playerData\\" + player.getUniqueId(), name + ".yml"));
    }

    public CustomConfig(SkyblockPlayer player, String name, boolean isInDataPath) {
        if (isInDataPath) {
            file = new File(Main.getMain().config.getString("SkyblockDataPath") + "\\playerData\\" + player.getUniqueId(), name + ".yml");
        } else {
            file = new File(Main.getMain().getDataFolder().getPath() + "\\playerData\\" + player.getUniqueId(), name + ".yml");
        }
        init();
    }

    private void init() {
        setup();
        save();
        reload();
    }

    public void setup() {
        if (!file.exists()) {
            try {

                if (file.createNewFile())
                    System.out.println("a new file at " + file.getPath() + " " + file.getName() + " has been created");
            } catch (IOException ignored) {

            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);

    }

    public FileConfiguration get() {
        return customFile;
    }

    public void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println(file.getName() + " has saving errors");
        }
    }

    public void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public void clear() {
        try {
            if(!file.delete()) System.out.println("Deletion of a file failed!");
            file = new File(file.getPath());
            if(!file.createNewFile()) System.out.println("no new file was created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
