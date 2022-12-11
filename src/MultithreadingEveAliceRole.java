import java.math.BigInteger;

public class MultithreadingEveAliceRole implements Runnable{
//Implement a thread with alice class and a exchange Key method
    EveClient eve = new EveClient();
    private volatile BigInteger valueAlice;

    public void run()
    {
        try {
            valueAlice = eve.exchangeKeysAlice(Main.g, Main.n, Main.port);

        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception Alice is caught " + e);
        }
    }


    public  BigInteger getValueAlice() {
        return valueAlice;
    }

}

