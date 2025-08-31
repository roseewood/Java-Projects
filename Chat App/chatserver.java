import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            String msg;
            while (true) {
                msg = in.readLine();
                if (msg == null || msg.equalsIgnoreCase("exit")) {
                    System.out.println("Client left the chat.");
                    break;
                }
                System.out.println("Client: " + msg);

                System.out.print("You: ");
                String reply = console.readLine();
                out.println(reply);

                if (reply.equalsIgnoreCase("exit")) break;
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
