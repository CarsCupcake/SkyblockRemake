package me.CarsCupcake.SkyblockRemake.configs;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.ThreadHalt;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;

public class ConfigFile {
    public static final FileThread thread;

    static {
        thread = new FileThread();
    }

    private File file;
    private FileConfiguration customFile;

    public ConfigFile(File file) {
        this.file = file;
        init();
    }

    public ConfigFile(String name) {
        //"C:\\Users\\09car\\Desktop\\Plugin\\Skyblock 1.17.1 Network\\files"
        String path = Main.getMain().config.getString("SkyblockDataPath");
        file = new File(path, name + ".yml");
        init();
    }

    public ConfigFile(String name, boolean isInDataPath) {
        if (!isInDataPath) file = new File(Main.getMain().getDataFolder(), name + ".yml");
        else {
            String path = Main.getMain().config.getString("SkyblockDataPath");
            file = new File(path, name + ".yml");
        }
        init();
    }

    public ConfigFile(SkyblockPlayer player, String name) {
        this(new File(Main.getMain().config.getString("SkyblockDataPath") + "\\playerData\\" + player.getUniqueId(), name + ".yml"));
    }

    public ConfigFile(SkyblockPlayer player, String name, boolean isInDataPath) {
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
        thread.append(this);
    }

    public void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public void clear() {
        try {
            if (!file.delete()) System.out.println("Deletion of a file failed!");
            file = new File(file.getPath());
            if (!file.createNewFile()) System.out.println("no new file was created!");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static class FileThread extends Thread {
        public static boolean online = true;
        private final ThreadHalt asyncHalt = new ThreadHalt();
        private ArrayDeque<ConfigFile> files = new ArrayDeque<>();

        public FileThread() {
            start();
            setName("I/O Thread");
        }

        public void append(ConfigFile file) {
            if (!thread.isAlive()) {
                try {
                    file.customFile.save(file.file);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
                return;
            }
            synchronized (files) {
                files.add(file);
                asyncHalt.run();
            }
        }

        public void finish() {
            online = false;
            asyncHalt.run();
        }

        @Override
        public void run() {
            files = new ArrayDeque<>();
            while (online) {
                try {
                    asyncHalt.await();
                    if (!online) break;
                    synchronized (files) {
                        if (files.isEmpty()) continue;
                        ConfigFile configFile = files.pop();
                        try {
                            configFile.customFile.save(configFile.file);
                        } catch (Exception e) {
                            e.printStackTrace(System.err);
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
