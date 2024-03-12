package me.CarsCupcake.SkyblockRemake.setup;

import me.CarsCupcake.SkyblockBungee.features.ServerType;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import org.kohsuke.github.GHAsset;
import org.kohsuke.github.GitHub;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public record DataFetcher(me.CarsCupcake.SkyblockBungee.features.ServerType type) {
    public void install() {
        File dir = new File("./" + type.getName());
        dir.mkdirs();
        File pluginsFolder = new File(dir, "plugins");
        pluginsFolder.mkdirs();
        File tempFolder = new File(dir, "work");
        tempFolder.mkdirs();
        AtomicReference<File> file = new AtomicReference<>();
        AtomicReference<File> spigotExe = new AtomicReference<>();
        Thread t1 = new Thread(() -> {
            try {
                file.set(DownloadUtil.navigate(type.getUrl(), null, tempFolder));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
               spigotExe.set(DownloadUtil.navigate(Main.spigotFileDownload, null, dir));
               boolean result = spigotExe.get().renameTo(new File(dir, "server.jar"));
               if (!result) {
                   throw new RuntimeException("Error while renaming!");
               }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("All downloads finished!");
        System.out.println("Extracting World");
        File out = new File(dir, "world");
        try {
            if (file.get().getName().endsWith(".rar")) {
                for (InputStream stream : extract(file.get().getAbsolutePath(), "").keySet()) {
                    BufferedInputStream in = new BufferedInputStream(stream);
                    FileOutputStream fout = new FileOutputStream(out);
                    final byte[] data = new byte[1024];
                    int count;
                    while ((count = in.read(data, 0, 1024)) != -1) {
                        fout.write(data, 0, count);
                    }
                }
            } else {
                getZipFiles(file.get().getAbsolutePath(), dir.getAbsolutePath());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done");
        File batchFile = new File(dir, "start.bat");
        try {
            if (!batchFile.createNewFile()) {
                throw new RuntimeException("Error while creating start.bat!");
            }
            FileWriter writer = new FileWriter(batchFile);
            writer.append("@echo off\n");
            writer.append("java -Xms2G -Xmx2G -XX:+UseG1GC -jar server.jar nogui");
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Created start.bat");
        File settingsFolder = new File(pluginsFolder, "SkyblockRemake");
        settingsFolder.mkdirs();
        File serverSettings = new File(settingsFolder, "config.yml");
        try {
            serverSettings.createNewFile();
            FileWriter writer = new FileWriter(serverSettings);
            writer.append("SkyblockDataPath: ./data\nServerType: " + type.s + "\nJoinSpawn: true\nStatSystem: true\nLavaBounce: " + (type == ServerType.F7));
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        File eulaAccepting = new File(dir, "eula.txt");
        try {
            eulaAccepting.createNewFile();
            FileWriter writer = new FileWriter(eulaAccepting);
            writer.append("#By changing the setting below to TRUE you are indicating your agreement to our EULA (https://account.mojang.com/documents/minecraft_eula).\neula=true");
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("Fetching latest plugin");
            GitHub gitHub =  GitHub.connectAnonymously();
            for (GHAsset asset : gitHub.getUser("CarsCupcake").getRepository("SkyblockRemake").getLatestRelease().listAssets().toList()) {
                if (asset.getName().endsWith(".jar")) {
                    DownloadUtil.navigate(asset.getBrowserDownloadUrl(), null, pluginsFolder);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done!");
        try {
            System.out.println("Fetching latest FAWE version");
            GitHub gitHub =  GitHub.connectAnonymously();
            for (GHAsset asset : gitHub.getUser("IntellectualSites").getRepository("FastAsyncWorldEdit").getLatestRelease().listAssets().toList()) {
                if (asset.getName().endsWith(".jar")) {
                    DownloadUtil.navigate(asset.getBrowserDownloadUrl(), null, pluginsFolder);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done!");
        System.out.println("Cleaning up...");
        try {
            Arrays.stream(tempFolder.listFiles()).forEach(f -> {
                try {
                    f.delete();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            });
            tempFolder.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done!");
    }

    public void getZipFiles(String zipFile, String destFolder) throws IOException {
        BufferedOutputStream dest = null;
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            System.out.println("Extracting: " + entry.getName());
            int count;
            byte[] data = new byte[1024];

            if (entry.isDirectory()) {
                new File(destFolder + "/" + entry.getName()).mkdirs();
                continue;
            } else {
                int di = entry.getName().lastIndexOf('/');
                if (di != -1) {
                    new File(destFolder + "/" + entry.getName().substring(0, di)).mkdirs();
                }
            }
            FileOutputStream fos = new FileOutputStream(destFolder + "/" + entry.getName());
            dest = new BufferedOutputStream(fos);
            while ((count = zis.read(data)) != -1) dest.write(data, 0, count);
            dest.flush();
            dest.close();
        }
    }

    /**
     * Extracts files from archive. Archive can be encrypted with password
     *
     * @param filePath path to .rar file
     * @param password string password for archive
     * @return map of extracted file with file name
     */
    public Map<InputStream, String> extract(String filePath, String password) throws IOException {
        Map<InputStream, String> extractedMap = new HashMap<>();

        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
        RandomAccessFileInStream randomAccessFileStream = new RandomAccessFileInStream(randomAccessFile);
        IInArchive inArchive = SevenZip.openInArchive(null, randomAccessFileStream);

        for (ISimpleInArchiveItem item : inArchive.getSimpleInterface().getArchiveItems()) {
            if (!item.isFolder()) {
                ExtractOperationResult result = item.extractSlow(data -> {
                    extractedMap.put(new BufferedInputStream(new ByteArrayInputStream(data)), item.getPath());

                    return data.length;
                }, password);

                if (result != ExtractOperationResult.OK) {
                    throw new RuntimeException(String.format("Error extracting archive. Extracting error: %s", result));
                }
            }
        }

        return extractedMap;
    }
}

