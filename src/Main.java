import java.math.BigInteger;

public class Main {
    static int port = 9999;
    static DHE dhe = new DHE();
    static BigInteger g =  dhe.gen;
    static BigInteger n = dhe.primeNum;

    public static void main(String[] args) {
        Thread objectAlice = new Thread(new MultithreadingAlice());
        objectAlice.start();
        Thread objectBob = new Thread(new MultithreadingBob());
        objectBob.start();
    }
}
