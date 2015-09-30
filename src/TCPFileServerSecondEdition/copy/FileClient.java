package TCPFileServerSecondEdition.copy;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.net.*;

class FileClient {

    public static void main(String args[]) 
    {
        byte[] aByte = new byte[1];
        int bytesRead;
        String serverIP = "192.168.1.5";
        int serverPort = 3248;
        String fileOutput = "C:\\testout.txt";
        Socket clientSocket = null;
        InputStream is = null;

        try 
        {
        	//nieuwe socket om te communiceren met server
            clientSocket = new Socket( serverIP , serverPort );	
            is = clientSocket.getInputStream();
        } 
        catch (IOException ex) {System.out.println("I/O error");}

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if (is != null) 
        {
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            
            try 
            {
                fos = new FileOutputStream(fileOutput);
                bos = new BufferedOutputStream(fos);
                bytesRead = is.read(aByte, 0, aByte.length);

                do 
                {
                        baos.write(aByte);
                        bytesRead = is.read(aByte);
                } while (bytesRead != -1);

                bos.write(baos.toByteArray());
                bos.flush();
                bos.close();
                clientSocket.close();
            } 
            catch (IOException ex) {System.out.println("I/O error");}
        }
    }
}