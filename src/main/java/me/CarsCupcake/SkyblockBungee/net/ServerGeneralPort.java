package me.CarsCupcake.SkyblockBungee.net;

import me.CarsCupcake.SkyblockBungee.Main;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import net.md_5.bungee.api.config.ServerInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerGeneralPort extends Thread {
    public static ServerGeneralPort INSTANCE;
    public final ServerSocket serverSocket;
    private boolean running;

    public ServerGeneralPort(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        start();
        INSTANCE = this;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                System.out.println("Listening for a connection");
                if (serverSocket.isClosed()) break;
                Socket socket = serverSocket.accept();
                ServerGeneralPort.RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
                System.out.println("Found Connection at " + socket.getPort());
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public void remove() {
        try {
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        running = false;
    }

    static class RequestHandler extends Thread {
        private final Socket socket;

        public RequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        @SuppressWarnings("deprecation")
        public void run() {
            try {
                String line = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
                System.out.println("Recieved Connection from port " + line);
                int port = Integer.parseInt(line);
                for (ServerInfo info : Main.getMain().getProxy().getServers().values()) {
                    if (info.getAddress().getPort() == port)
                        new ServerHandler(info);

                }
                socket.close();
            }catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
