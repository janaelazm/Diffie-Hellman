public class MultithreadingBob implements Runnable{

    BobServer bob = new BobServer();
    public void run()
    {
        try {
            bob.exchangeKeysBob(Main.g, Main.n, Main.port);
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}

