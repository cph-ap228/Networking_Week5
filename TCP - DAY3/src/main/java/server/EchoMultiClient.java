package server;

import client.EchoClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maksymilian
 */
public class EchoMultiClient {
private ServerSocket serverSocket;

public void start(int PORT) throws IOException {
    serverSocket = new ServerSocket(PORT);
    while (true)    
        new EchoClientHandler(serverSocket.accept()).run();
}

public void stop() throws IOException {
serverSocket.close();
}
private static class EchoClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

public EchoClientHandler(Socket socket) {
    this.clientSocket = socket;
}

public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    String inputLine;
        try {
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("CONNECTION CLOSED");
                    break;
                    
                }
                out.println(inputLine);
            }       } catch (IOException ex) {
            Logger.getLogger(EchoMultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
out.close();
        try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
}

//TESTS
public void givenClient1_whenServerResponds_thenCorrect() {
EchoClient client1 = new EchoClient();
client1.startConnection("localhost", 7890);
String msg1 = client1.sendMessage("hello");
String msg2 = client1.sendMessage("world");
String terminate = client1.sendMessage(".");

assertEquals(msg1, "hello");
assertEquals(msg2, "world");
assertEquals(terminate, "bye");
}

public void givenClient2_whenServerResponds_thenCorrect() {
EchoClient client2 = new EchoClient();
client2.startConnection("localhost", 7890);
String msg1 = client2.sendMessage("hello");
String msg2 = client2.sendMessage("world");
String terminate = client2.sendMessage(".");

assertEquals(msg1, "hello");
assertEquals(msg2, "world");
assertEquals(terminate, "bye");
}
 
        private void assertEquals(String msg1, String hello) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    } 
} 

 
    

