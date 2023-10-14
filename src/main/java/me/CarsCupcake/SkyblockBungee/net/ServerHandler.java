package me.CarsCupcake.SkyblockBungee.net;

import me.CarsCupcake.SkyblockBungee.Main;
import me.CarsCupcake.SkyblockBungee.features.ServerType;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Listener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class ServerHandler extends Thread implements Listener {
    public Socket socket;
    public static final HashMap<ServerInfo, ServerHandler> servers = new HashMap<>();
    public boolean running = true;
    private BufferedReader in;
    private PrintWriter out;
    private final ServerInfo info;

    @SuppressWarnings("deprecation")
    public ServerHandler(ServerInfo server) {
        info = server;
        try {
            System.out.println("Connecting to: " + String.valueOf(server.getAddress().getPort()).substring(String.valueOf(server.getAddress().getPort()).length() - 3));
            socket = new Socket("127.0.0.1", Integer.parseInt(String.valueOf(server.getAddress().getPort()).substring(String.valueOf(server.getAddress().getPort()).length() - 3)));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            start();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return;
        }
        servers.put(server, this);
    }

    @Override
    public void run() {
        while (running) {
            try {
                String recieved = in.readLine();
                if (recieved == null) break;
                if (recieved.startsWith("add:")) {
                    ServerType type = ServerType.fromName(recieved.split(":")[1]);
                    Main.getServers().put(info, type);
                    Main.getOrderdServer().add(type, info);
                    System.out.println("Registered Server " + type + " from port " + socket.getPort());
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
    }

    public void sendMessage(String s) {
        out.println(s);
        out.flush();
    }
}
