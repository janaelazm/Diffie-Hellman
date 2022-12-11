import java.io.IOException;
import java.math.BigInteger;

public class MultithreadingBob implements Runnable{

    BobServer bob = new BobServer();
    private volatile BigInteger value;
    public MultithreadingBob() throws IOException {
    }
@Override
    public void run()
    {
        try {
            value = bob.exchangeKeysBob(Main.g, Main.n, Main.port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public BigInteger getValue()  {
        return value;
    }
}

