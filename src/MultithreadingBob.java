import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultithreadingBob implements Runnable{

    BobServer bob = new BobServer();

    public MultithreadingBob() throws IOException {
    }

    public void run()
    {
        try {

            bob.exchangeKeysBob(Main.g, Main.n, Main.port);
            BufferedReader bobRes = new BufferedReader(new InputStreamReader(System.in));

        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception Bob is caught: " + e);
        }
    }
}

