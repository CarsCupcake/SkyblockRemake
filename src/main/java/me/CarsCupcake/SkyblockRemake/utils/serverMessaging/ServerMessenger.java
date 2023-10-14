package me.CarsCupcake.SkyblockRemake.utils.serverMessaging;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ServerMessenger extends Thread {
    private final ServerSocket serverSocket;
    private Socket bungeeSocket;
    private boolean running;
    private PrintWriter out;
    private final List<RunnableWithParam<String>> listeners = new ArrayList<>();
    private final List<String> messageBuff = new ArrayList<>();

    public ServerMessenger(int port) throws IOException {
        Main.getDebug().debug("Connecting to port " + port);
        serverSocket = new ServerSocket(port);
        start();

    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                System.out.println("Listening for a connection");
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
                System.out.println("Found Connection at " + socket.getPort());
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public void sendBufferedMessage(String s) {
        if (out == null) {
            messageBuff.add(s);
            return;
        }
        new Thread(() -> {
            out.println(s);
            out.flush();
        }).start();
    }

    private void init() {
        for (String s : messageBuff)
            sendBufferedMessage(s);
    }

    public void registerListener(RunnableWithParam<String> listener) {
        listeners.add(listener);
    }

    public void remove() {
        try {
            serverSocket.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        running = false;
    }

    class RequestHandler extends Thread {
        private final Socket socket;

        public RequestHandler(Socket socket) {
            this.socket = socket;
            try {
                out = new PrintWriter(socket.getOutputStream());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bungeeSocket = socket;
                init();
                while (running) {
                    try {
                        if (socket.isClosed() || !socket.isConnected() || socket.isInputShutdown()) {
                            System.out.println("Disconecting Bungee!");
                            System.out.println("Shuting Down!");
                            Main.getMain().getPluginLoader().disablePlugin(Main.getMain());
                            break;
                        }

                        String line = in.readLine();
                        if (line == null) break;
                        if (line.startsWith("close")) {
                            System.out.println("Disconecting Bungee!");
                            System.out.println("Shuting Down!");
                            Main.getMain().getPluginLoader().disablePlugin(Main.getMain());
                            break;
                        }
                        synchronized (Main.getMain()) {
                            for (RunnableWithParam<String> listener : listeners)
                                try {
                                    listener.run(line);
                                } catch (Exception e) {
                                    e.printStackTrace(System.err);
                                }
                        }
                    } catch (Exception e) {
                        e.printStackTrace(System.err);
                        System.out.println("Error! Closing Connection!");
                        break;

                    }
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            try {
                out.close();
            } catch (Exception e1) {
                e1.printStackTrace(System.err);
            }
            try {
                bungeeSocket.close();
            } catch (Exception e1) {
                e1.printStackTrace(System.err);
            }
            bungeeSocket = null;
        }
    }
}
