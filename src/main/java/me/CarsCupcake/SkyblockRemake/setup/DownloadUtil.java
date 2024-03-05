package me.CarsCupcake.SkyblockRemake.setup;

import me.CarsCupcake.SkyblockRemake.utils.Assert;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="https://stackoverflow.com/questions/29314235/download-a-file-from-mediafire-with-java">User on StackOverlow</a>
 */
public class DownloadUtil {

    public static File navigate(String url, String sufix, File target) {
        Assert.notNull(url, target);
        Assert.state(target.isDirectory());
        try {
            System.out.println(url);
            return saveUrl(url, sufix, target);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    private static File saveUrl(final String urlString, String sufix, File parent) throws Exception {
        System.out.println("Downloading...");
        String filename = urlString.substring(urlString.lastIndexOf("/") + 1, urlString.lastIndexOf(".")) + ((sufix == null) ? "" : ("_" + sufix)) + urlString.substring(urlString.lastIndexOf("."));
        File fileOut = new File(parent, filename);
        try (BufferedInputStream in = new BufferedInputStream(new URL(urlString).openStream()); FileOutputStream fout = new FileOutputStream(fileOut)) {

            final byte[] data = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        }
        System.out.println("Success!");
        return fileOut;
    }

    private static String getUrlSource(String url) throws IOException {
        System.out.println("Connecting...");
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        StringBuilder total = new StringBuilder();
        while ((inputLine = in.readLine()) != null) total.append(inputLine);
        in.close();
        return total.toString();
    }

    private static String fetchDownloadLink(String str) {
        System.out.println("Fetching download link");
        try {
            int pointer = str.indexOf("Download File");
            System.out.println(pointer);
            int firstIndex = str.indexOf("\"", pointer + 12);
            int secondIndex = str.indexOf("\"", firstIndex + 1);
            return str.substring(firstIndex + 1, secondIndex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
