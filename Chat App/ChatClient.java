import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            System.out.println("Connected to server!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            String msg;
            while (true) {
                System.out.print("You: ");
                String text = console.readLine();
                out.println(text);

                if (text.equalsIgnoreCase("exit")) break;

                msg = in.readLine();
                if (msg == null || msg.equalsIgnoreCase("exit")) {
                    System.out.println("Server closed the chat.");
                    break;
                }
                System.out.println("Server: " + msg);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
