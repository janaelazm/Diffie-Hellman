import java.math.BigInteger;

public class Main {

    static AliceClient alice = new AliceClient();
    static BobServer bob = new BobServer();
    static int port = 9999;
    static int port2 = 9998;
    static DHE dhe = new DHE();
    static BigInteger g =  dhe.gen;
    static BigInteger n = BigInteger.valueOf(103);
    static BigInteger resAlice;
    static BigInteger resBob;


    public static void main(String[] args) throws Exception {

        MultithreadingBob newB = new MultithreadingBob();
        Thread objectBob = new Thread(newB);
        objectBob.start();
        MultithreadingAlice newA = new MultithreadingAlice();
        Thread objectAlice = new Thread(newA);
        objectAlice.start();
        objectBob.join();
        objectAlice.join();

         resBob = newB.getValue();
        System.out.println(resBob);

         resAlice = newA.getValue();
       System.out.println(resAlice);
        objectBob.stop();
        objectAlice.stop();
        if(resAlice.equals(resBob)) {

            Thread messageBob = new Thread(new ThreadMessageBob());
            messageBob.start();
            Thread messageAlice = new Thread(new ThreadMessageAlice());
            messageAlice.start();

        }









    }
}
