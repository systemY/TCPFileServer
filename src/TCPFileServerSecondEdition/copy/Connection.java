package TCPFileServerSecondEdition.copy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Connection extends Thread { 

	Socket connectionSocket; 
	BufferedOutputStream outToClient;
	public String fileToSend = "C:\\test1.txt";
	
	public Connection (Socket connectionSocket, BufferedOutputStream outToClient) 
	{ 
	
		connectionSocket = this.connectionSocket;
       outToClient=this.outToClient;

	  }

	public void run() 
	  { 
		  File myFile = new File( fileToSend );
          byte[] mybytearray = new byte[(int) myFile.length()];

          FileInputStream fis = null;

          try {
              fis = new FileInputStream(myFile);
          } catch (FileNotFoundException ex) {
          	System.out.println("AJ2");
          }
          BufferedInputStream bis = new BufferedInputStream(fis);

          try {
              bis.read(mybytearray, 0, mybytearray.length);
              outToClient.write(mybytearray, 0, mybytearray.length);
              outToClient.flush();
              outToClient.close();
              connectionSocket.close();
              // File sent, exit the main method
              return;
          } catch (IOException ex) {
          	System.out.println("AJ3");
          }
}
}