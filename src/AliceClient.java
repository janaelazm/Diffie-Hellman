import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class AliceClient extends DHE{



    private final BigInteger alicePrivateKey;
    public BigInteger alicePublicKey;


     AliceClient(){
        alicePrivateKey = generateRandom(1000000, 1000);
    }

    protected BigInteger setPublicKey(BigInteger g , BigInteger prime){
        alicePublicKey = g.modPow(alicePrivateKey, prime);
        return alicePublicKey;
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
            BigInteger aliceAnswer = b.modPow(alicePrivateKey, n);
            System.out.println("Alice's answer " +  aliceAnswer);
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
        System.out.println("Alice's message " +  decodedStr);
    }


}
