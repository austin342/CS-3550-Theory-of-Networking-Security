import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class DTClient
{
	public static void main(String argv[]) throws Exception
    {
        String sentence;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Socket client = new Socket("localhost", 6206);
        DataOutputStream toServer = new DataOutputStream(client.getOutputStream());
        sentence = input.readLine();
        sentence = encryptMatrix(sentence);
        toServer.writeBytes(sentence + '\n');  
    }

    public static String encryptMatrix (String text) 
    { 
      int[] r_key = {3,5,1,4,2};
      int[] c_key = {1,3,2};
      int columns = 3, rows = 5, index = 0;

      char[][] encrypted_matrix = new char[rows][columns]; //Setting up matricies to store the values
      char[][] double_transposed_Matrix = new char[rows][columns];
        
        for (int i = 0; i < encrypted_matrix.length; i++)  //Fill the matrix in a loop
        {
            for(int j = 0; j < encrypted_matrix[i].length; j++) 
            {
             if (index == text.length())
             {
             encrypted_matrix[i][j] = 'x'; //Note: Put in at suggestion
             break;
             }
             encrypted_matrix[i][j] = text.charAt(index);
             index++;
            }
        }

        for (int i = 0; i < encrypted_matrix.length; i++)    //Double transpose the matrix
        for (int j = 0; j < r_key.length ; j++)   
         if (r_key[j] - 1 == i) 
        for (int k = 0; k < double_transposed_Matrix[j].length; k++)
        for (int f = 0; f < c_key.length ; f++) 
        if (c_key[f] - 1 == k)
         {
          double_transposed_Matrix[j][f] = encrypted_matrix[i][k];
         }
        String returnText = "";
        for (int i = 0 ; i < double_transposed_Matrix.length; i++)
        for(int j = 0; j < double_transposed_Matrix[i].length; j++) 
          returnText += double_transposed_Matrix[i][j];
             return returnText;
    }
}
