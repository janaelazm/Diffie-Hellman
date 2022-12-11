import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class BobServer extends DHE{

    private final BigInteger bobPrivateKey;

    BigInteger bobPublicKey;

    BobServer(){
        bobPrivateKey = generateRandom(1000000, 1000);
    }

    protected String setPublicKey(BigInteger g , BigInteger prime){
         bobPublicKey = g.modPow(bobPrivateKey, prime);
        return bobPublicKey + "";
    }

    public void exchangeMessageBob(int port, String msg, String key) throws Exception {
        ServerSocket socketServer = new ServerSocket(port);
        Socket socket = socketServer.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = br.readLine();

        String decodedA = decodeWord(str, key);
        System.out.println("Message from Alice: " + decodedA);
        String encodedMsg = encodeWord(msg, key);
        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
        PrintWriter out = new PrintWriter(osw);
        out.println(encodedMsg);
        out.flush();
    }

    public BigInteger exchangeKeysBob(BigInteger g , BigInteger n, int port) throws Exception {
        ServerSocket socketServer = new ServerSocket(port);
        Socket socket = socketServer.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = br.readLine();
        BigInteger a = new BigInteger(str);
        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
        PrintWriter out = new PrintWriter(osw);
        out.println(setPublicKey(g, n));
        out.flush();
        BigInteger res = a.modPow(bobPrivateKey, n);
        System.out.println("Bob answer  " + res);
        return res;
    }


}
