package me.CarsCupcake.SkyblockBungee;


import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class BungeeConfig {
    private static final ConfigurationProvider provider = ConfigurationProvider.getProvider(YamlConfiguration.class);
    private File file;
    private Configuration customFile;

    public BungeeConfig(File file) {
        this.file = file;
        init();
    }

    public BungeeConfig(String name) {
        //"C:\\Users\\09car\\Desktop\\Plugin\\Skyblock 1.17.1 Network\\files"
        String path = Main.config.get().getString("SkyblockDataPath", "./data");
        file = new File(path, name + ".yml");
        init();
    }

    public BungeeConfig(String name, boolean isInDataPath) {
        if (!isInDataPath) file = new File(Main.getMain().getDataFolder(), name + ".yml");
        else {
            String path = Main.config.get().getString("SkyblockDataPath", "./data");
            file = new File(path, name + ".yml");
        }
        init();
    }

    public BungeeConfig(ProxiedPlayer player, String name) {
        this(new File(Main.config.get().getString("SkyblockDataPath", "./data") + "\\playerData\\" + player.getUniqueId(), name + ".yml"));
    }

    public BungeeConfig(ProxiedPlayer player, String name, boolean isInDataPath) {
        if (isInDataPath) {
            file = new File(Main.config.get().getString("SkyblockDataPath", "./data") + "\\playerData\\" + player.getUniqueId(), name + ".yml");
        } else {
            file = new File(Main.getMain().getDataFolder() + "\\playerData\\" + player.getUniqueId(), name + ".yml");
        }
        init();
    }

    private void init() {
        setup();
    }

    public void setup() {
        if (!file.exists()) {
            try {
                if (file.createNewFile())
                    System.out.println("a new file at " + file.getPath() + " " + file.getName() + " has been created");
                customFile = provider.load(file);
            } catch (Exception ignored) {
            }
        } else {
            try {
                customFile = provider.load(file);
            } catch (Exception ignored) {
            }
        }
    }

    public Configuration get() {
        return customFile;
    }

    public void save() {
        try {
            provider.save(customFile, file);
        } catch (IOException e) {
            System.out.println(file.getName() + " has saving errors");
        }
    }

    public void reload() {
        try {
            customFile = provider.load(file);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void clear() {
        try {
            if (!file.delete()) System.out.println("Deletion of a file failed!");
            file = new File(file.getPath());
            if (!file.createNewFile()) System.out.println("no new file was created!");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        try {
            customFile = provider.load(file);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
