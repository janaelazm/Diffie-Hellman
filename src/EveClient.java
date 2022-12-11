import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class EveClient extends DHE{



    private final BigInteger evePrivateKey;
    public BigInteger evePublicKey;


     EveClient(){
        evePrivateKey = generateRandom(1000000, 1000);
    }

    protected BigInteger setPublicKey(BigInteger g , BigInteger prime){
        evePublicKey = g.modPow(evePrivateKey, prime);
        return evePublicKey;
    }

    public  BigInteger exchangeKeysAlice(BigInteger g, BigInteger n, int port) throws IOException {
            String ip = "localhost";
            Socket socket = new Socket(ip, port);
            String sentKey = String.valueOf(setPublicKey(g, n));

        //convert data to stream format, parameter-to where we send info, in this case we sent to output port of a socket
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter out = new PrintWriter(osw);
            out.println(sentKey);
            out.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = br.readLine();
            BigInteger b = new BigInteger(str);
            BigInteger aliceAnswer = b.modPow(evePrivateKey, n);
            System.out.println("Eve's answer for Alice " +  aliceAnswer);
            return aliceAnswer;
        }

    public void exchangeMsgAlice( int port, String msg, String key) throws Exception {
        String ip = "localhost";
        String encodedMsg = encodeWord(msg, key);
        Socket socket = new Socket(ip, port);
        //convert data to stream format, parameter-to where we send info, in this case we sent to output port of a socket
        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
        //sendMessage(setPublicKey(g, n), osw);
        PrintWriter out = new PrintWriter(osw);
        out.println(encodedMsg);
        out.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = br.readLine();
        String decodedStr = decodeWord(str, key);
        System.out.println("Bob's message caught " +  decodedStr);
    }



}
