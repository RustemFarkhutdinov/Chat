package ru.geekbrains.chat.client.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class  SimpleSingleConsoleServer{
    private static final int PORT = 12256;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread serverConsoleThread;

    public static void main(String[] args) {
        new SimpleSingleConsoleServer().runServer();
    }

    private void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");

            waitForConnectionAndInitResources(serverSocket);
            startConsoleThread();

            while (true) {
                String msg = in.readUTF();
                if (msg.equals("end")) {
                    shutdownServer();
                    break;
                }
                System.out.println("Received: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                shutdownServer();
                System.out.println("FINISHED");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void shutdownServer() throws IOException {
        if (serverConsoleThread.isAlive()) serverConsoleThread.interrupt();
        if (socket != null) socket.close();
        System.out.println("Server STOPPED");
    }

    private void startConsoleThread() {
        serverConsoleThread = new Thread(() -> {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("You can enter message for client >>>>");
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (bufferedReader.ready()) {
                        String messageFromServer = bufferedReader.readLine();
                        out.writeUTF(messageFromServer);
                        Thread.sleep(200);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        serverConsoleThread.start();
    }

    private void waitForConnectionAndInitResources(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for connection....");
        socket = serverSocket.accept();
        System.out.println("Client has connected");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }
}
