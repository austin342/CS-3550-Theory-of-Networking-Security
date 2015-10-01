import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

//This RSA assumes the message is a number

public class RSAClient 
{
	public static void main(String argv[]) throws Exception
    {
        String sentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket client = new Socket("localhost", 2018);
        DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
        sentence = inFromUser.readLine();
        sentence = "" + encrypt(Long.parseLong(sentence));
        outToServer.writeBytes(sentence + '\n');
        System.out.println("Encrypted Value: " + sentence);
    }

    public static long encrypt (long message) //Formula: M^e mod N
    {
        long[] publicKey = {33, 3};
        //private key not needed here yet
        long c = ((long)Math.pow(message, publicKey[1]))% publicKey[0];
        return c;
    }
}
