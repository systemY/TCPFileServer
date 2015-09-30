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

class Connection extends Thread 
{ 

	Socket connectionSocket; 
	BufferedOutputStream outToClient;
	public String fileToSend;
	
	public Connection (Socket connectionSocket, BufferedOutputStream outToClient, String fileToSend) 
	{ 
		connectionSocket = this.connectionSocket;	//communcatie socket
		outToClient=this.outToClient;				//BufferedOutputStream
		fileToSend=this.fileToSend;					//file locatie
	 }

	public void run() 
	  { 
			//plaats het te verzenden bestand in een byte array 
		  File myFile = new File(fileToSend );
          byte[] mybytearray = new byte[(int) myFile.length()];	//byte array aanmaken
          FileInputStream fis = null;
          try 
          {
              fis = new FileInputStream(myFile);						//input stream
              BufferedInputStream bis = new BufferedInputStream(fis);	//buffered input stream
              bis.read(mybytearray, 0, mybytearray.length);				//byte array vullen via buffered input stream
              bis.close();
              fis.close();
          } 
          catch (FileNotFoundException ex) {System.out.println("Bestand bestaad niet");}
          catch (SecurityException sx) {System.out.println("Geen toegang tot bestand");} 
          catch (IOException e) {System.out.println("I/O error");}

          try 
          {
              outToClient.write(mybytearray, 0, mybytearray.length);
              outToClient.flush();
              outToClient.close();
              connectionSocket.close();
              return;
          } 
          catch (IOException ex) {System.out.println("IOException");}
	  }
}