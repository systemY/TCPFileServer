package TCPFileServerSecondEdition.copy;

import java.io.*;
import java.net.*;

class TCPServer 
{
    public static void main(String args[]) 
    {
    	String fileToSend = "C:\\test1.txt";
        while (true) 
        {
        		//Maak lokale variabels eerst aan. 
        		//Dit is nodig om deze ook te gebruiken buiten de "try catch" blok.
            ServerSocket welcomeSocket = null;
            Socket connectionSocket = null;
            BufferedOutputStream outToClient = null;
            
            try 
            {
            		//poort waar gebruiker kan op connecteren
                welcomeSocket = new ServerSocket(3248);		
                	//Tot er een gebruiker gevonden is zal .accept() blijven wachten
                	//Nadat er iemand geconnecteerd is zal er een nieuwe socket aangemaakt worden waarmee deze
                	//met elkaar communiceren
                connectionSocket = welcomeSocket.accept();
                	//buffer voor data communicatie tussen server en client
                outToClient = new BufferedOutputStream(connectionSocket.getOutputStream());
            }
            catch (IOException ex) {System.out.println("IOException1");}	//if an I/O error occurs when opening the socket.

            if (outToClient != null) //wanneer buffer correct is aangemaakt zal deze niet een null waarde hebben
            {
            		//gemaakte variabele doorgeven aan thread
                Connection c =new Connection(connectionSocket,outToClient,fileToSend);
                	//thread starten
                c.start();
            }
        }
    }
}