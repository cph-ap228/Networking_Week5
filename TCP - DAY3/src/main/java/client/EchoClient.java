package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Maksymilian
 */
public class EchoClient {
    Socket socket;
    Scanner scan;
    PrintWriter pw;
    
    public void connect(String ip, int port) throws IOException{
        socket = new Socket(ip,port);
        scan = new Scanner(socket.getInputStream());
        pw = new PrintWriter(socket.getOutputStream(),true);
    }
    
    public void send(String msg){
        pw.println(msg);
    }
    // Will not work from a GUI, since it blocks.
    public String receive(){
        String msg = scan.nextLine();
        return msg; 
    };
    public static void main(String[] args) throws IOException {
        EchoClient client = new EchoClient();
        client.connect("localhost",7890);
        client.send("Hello World");
        System.out.println("GOT: "+client.receive());
        client.send("STOP");
        System.out.println("STOPPED");
    }

    public void startConnection(String string, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String sendMessage(String hello) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

//Couldn't generate the client handler somehow, 
// and the second client doesn't receive the output message. Any idea why? 
