import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class DTServer 
{
  public static void main(String argv[]) throws Exception
  {
	String clientSentence;
	String capitalizedSentence;
	
	ServerSocket welcomeSocket = new ServerSocket(6206);
    while(true) 
    {
     try
     {		
	   Socket connectionSocket = welcomeSocket.accept();
	   BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
       DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
       clientSentence = inFromClient.readLine();
       System.out.println("Received encrypted Message: " + clientSentence);
       decrypted_Matrix(clientSentence);
     }
     catch(Exception e)
     {
      System.out.println("Error");
	 }
	}
  }

  public static void decrypted_Matrix (String input) 
  {
	int stringIndex = 0;
	int rows = 5;
	int cols = 3;
	int[] keyRow = {3,5,1,4,2};
	int[] keyCol = {1,3,2};

	char[][] matrix = new char[rows][cols];
	char[][] transposedMatrix = new char[rows][cols];
	        
	//populate matrix
	for (int i = 0; i < matrix.length; i++)  //Fill the matrix
	for(int j = 0; j < matrix[i].length; j++)
	 {
	  if (stringIndex == input.length()){
	    matrix[i][j] = 'x'; //Note: Same as before
	    break;
	  }
	  matrix[i][j] = input.charAt(stringIndex);
	  stringIndex++;	  
	 }
								                      //Double transpose back to original
	 for (int i = 0; i < matrix.length; i++) 
	 for (int j = 0; j < keyRow.length ; j++) 
	  if (keyRow[j] - 1 == i) 
	 for (int k = 0; k < transposedMatrix[j].length; k++)
	 for (int g = 0; g < keyCol.length ; g++) 
	  if (keyCol[g] - 1 == k)
	     transposedMatrix[i][k] = matrix[j][g];
	     String message = "";
	       for (int i = 0 ; i < transposedMatrix.length; i++)	       
	         for(int j = 0; j < transposedMatrix[i].length; j++) 
	            {
	             message += transposedMatrix[i][j];
	            }	       
	        System.out.println("Decrypting: " + message);
	    }

	}

