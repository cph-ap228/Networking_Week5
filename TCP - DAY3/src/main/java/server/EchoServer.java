package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Maksymilian
 */
public class EchoServer {
    private static int PORT = 7890;
    private static String IP = "localhost";
    private static ServerSocket serverSocket; 
    
    public static void handleClient(Socket s) throws IOException{
        Scanner scan = new Scanner(s.getInputStream());
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true); //DON'T FORGET autoflush
        //IMPORTANT BLOCKING 
        String messageFromClient = scan.nextLine();
        
        System.out.println("Received: "+messageFromClient);
        
        while(!messageFromClient.equals("STOP")){
            pw.println(messageFromClient.toUpperCase());
            messageFromClient = scan.nextLine();   
        }
        System.out.println("Stopped");
    }
    
    public static void main(String[] args) throws IOException {
        if(args.length == 2) {
            PORT = Integer.parseInt(args[0]);
            IP = args[1];
        }
        serverSocket = new ServerSocket(PORT); //Remember to bind it.
        System.out.println("Waiting for a Client on PORT "+PORT);
        while (true){
        Socket socket = serverSocket.accept(); //IMPORTANT BLOCKING CALL.
        System.out.println("Found a Client");
        handleClient(socket);
    }
    }
}
    

