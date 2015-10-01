import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client 
{
 static int key = 5;
 
	public static void main(String argv[]) throws Exception
    {
     String sentence;
     
     try
     {
      System.out.println("Enter line (No spaces): \n");
      BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
      Socket clientSocket = new Socket("localhost", 6637);
      DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
      sentence = console.readLine();   
      sentence = encrypt_ceasar_Cipher(sentence, key);
      toServer.writeBytes(sentence + '\n');
      }
      catch(Exception e)
      {
       System.out.println("Error");
      }
     }

	private static String encrypt_ceasar_Cipher(String sentence, int key)
	{	
	 String lowercase = sentence.toLowerCase();
	 char[] char_array = lowercase.toCharArray();
	 for(int i = 0; i < char_array.length; i++)
	 {
		 char letter = char_array[i];
		    letter = (char) (letter + key);
		    if (letter > 'z') 
		    {
			letter = (char) (letter - 26);
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
