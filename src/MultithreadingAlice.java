public class MultithreadingAlice implements Runnable{
//Implement a thread with alice class and a exchange Key method
    AliceClient alice = new AliceClient();
    public void run()
    {
        try {
            alice.exchangeKeysAlice(Main.g, Main.n, Main.port);
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}

