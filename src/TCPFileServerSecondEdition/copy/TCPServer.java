package TCPFileServerSecondEdition.copy;

import java.io.*;
import java.net.*;

class TCPServer {

   // private final static String fileToSend = "C:\\test1.txt";

    public static void main(String args[]) {
    	//public String fileToSend = "C:\\test.rar";
        while (true) {
            ServerSocket welcomeSocket = null;
            Socket connectionSocket = null;
            BufferedOutputStream outToClient = null;

            try {
                welcomeSocket = new ServerSocket(3248);
                connectionSocket = welcomeSocket.accept();
                outToClient = new BufferedOutputStream(connectionSocket.getOutputStream());
            } catch (IOException ex) {
                // Do exception handling
            	System.out.println("AJ1");
            }

            if (outToClient != null) 
            {
                Connection c =new Connection(connectionSocket,outToClient);
                c.start();
            }
        }
    }
}