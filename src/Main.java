import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    static AliceClient alice = new AliceClient();
    static BobServer bob = new BobServer();
    static int port = 9999;
    static DHE dhe = new DHE();
    static BigInteger g =  dhe.gen;
    static BigInteger n = BigInteger.valueOf(103);


    public static void main(String[] args) throws Exception {

        Thread objectBob = new Thread(new MultithreadingBob());
        objectBob.start();

        Thread objectAlice = new Thread(new MultithreadingAlice());
        objectAlice.start();


        if(bobKey.equals(aliceKey)) {
            bob.exchangeMessageBob(9999, "Hello, Bob", String.valueOf(bobRes));
            alice.exchangeMsgAlice(9999, "Hello, Bob", String.valueOf(aliceRes));

        }




    }
}
