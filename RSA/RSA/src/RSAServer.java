import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class RSAServer 
{
	public static void main(String argv[]) throws Exception
    {
        String client;
        ServerSocket welcomeSocket = new ServerSocket(2018);

        while(true) 
        {
         try
         {
	      Socket connectionSocket = welcomeSocket.accept();
          BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
          DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
          client = inFromClient.readLine();
          System.out.println("Received encrypted Message: " + client);
          decrypt(Long.parseLong(client));
         }
         catch(Exception e)
         {
          System.out.println("Error");
         }
        }
    }
            
    public static void decrypt (long message)//Formula = c^d mod N
    {
        long[] publicKey = {3233, 17};
        long privateKey = 65;

        message = ((long)Math.pow(message, privateKey))% publicKey[0];
        System.out.println("Decrypted Value: " + message);
    }
}
