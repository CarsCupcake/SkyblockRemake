package me.CarsCupcake.SkyblockBungee.net;

import me.CarsCupcake.SkyblockBungee.Main;
import me.CarsCupcake.SkyblockBungee.features.ServerType;
import me.CarsCupcake.SkyblockBungee.features.Time;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

@SuppressWarnings("deprecation")
public class ServerHandler extends Thread implements Listener {
    public Socket socket;
    public static final HashMap<ServerInfo, ServerHandler> servers = new HashMap<>();
    public static final HashMap<Integer, ServerHandler> address = new HashMap<>();
    public boolean running = true;
    private BufferedReader in;
    private PrintWriter out;
    private final ServerInfo info;

    public ServerHandler(ServerInfo server) {
        info = server;
        try {
            System.out.println("Connecting to: " + String.valueOf(server.getAddress().getPort()).substring(String.valueOf(server.getAddress().getPort()).length() - 3));
            socket = new Socket("127.0.0.1", Integer.parseInt(String.valueOf(server.getAddress().getPort()).substring(String.valueOf(server.getAddress().getPort()).length() - 3)));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            servers.put(server, this);
            address.put(server.getAddress().getPort(), this);
            sendMessage("datapath:" + new File(Main.config.get().getString("SkyblockDataPath")).getPath());
            start();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                if (socket.isClosed() || !socket.isConnected() || socket.isInputShutdown() || socket.isOutputShutdown()) break;
                String recieved = in.readLine();
                if (recieved == null) break;
                if (recieved.startsWith("add:")) {
                    ServerType type = ServerType.fromName(recieved.split(":")[1]);
                    synchronized (Main.getMain()){
                        Main.getServers().put(info.getAddress().getPort(), type);
                        Main.getOrderdServer().add(type, info);
                        System.out.println("Registered Server " + type + " from port " + socket.getPort());
                        Time.addConnection(info);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        System.out.println("Closing Connection to " + socket.getPort());
        try {
            in.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        try {
            out.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        ServerType type = Main.getServers().get(info.getAddress().getPort());
        Main.getServers().remove(info.getAddress().getPort());
        Main.getOrderdServer().removeFromList(type, info);
    }

    public void sendMessage(String s) {
        out.println(s);
        out.flush();
    }
}
