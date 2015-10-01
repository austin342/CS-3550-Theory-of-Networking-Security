import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
 static int key = 5;
	
	public static void main(String argv[]) throws Exception
    {
     String clientSentence;
    
     ServerSocket acknowledgement = new ServerSocket(6637);
     while(true) 
     {
      try
      {	 
       Socket connectionSocket = acknowledgement.accept();
       BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
       clientSentence = inFromClient.readLine();
       System.out.println("The Server received this encrypted message: " + clientSentence);
       String new_sentence = decrypt_ceasar_Cipher(clientSentence, key);
       System.out.println("The Server has decoded the message to: " + new_sentence);
       break;
      }
      catch(Exception e)
      {
       System.out.println("Error");
       break;
      }
     }
    }
      
	private static String decrypt_ceasar_Cipher(String sentence, int key)
	{	
	 String lowercase = sentence.toLowerCase();
	 char[] char_array = lowercase.toCharArray();
	 for(int i = 0; i < char_array.length; i++)
	 {		
		 char letter = char_array[i];
		    letter = (char) (letter - key); 
		    if(letter > 'z')		     
		    {
			letter = (char) (letter + 26);
		    } 
		    else if (letter < 'a')
		    {
			letter = (char) (letter + 26);
		    }
		    char_array[i] = letter; 		    
	 }
		return new String(char_array);	
	}
}
