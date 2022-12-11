import java.math.BigInteger;

public class MultithreadingAlice implements Runnable{
//Implement a thread with alice class and a exchange Key method
    AliceClient alice = new AliceClient();
    private volatile BigInteger value;
    public void run()
    {
        try {
           value = alice.exchangeKeysAlice(Main.g, Main.n, Main.port);
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception Alice is caught " + e);
        }
    }

    public  BigInteger getValue() {
        return value;
    }
}

