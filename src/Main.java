import java.math.BigInteger;

public class Main {

    static int port = 9999;
    static int port2 = 9998;
    static DHE dhe = new DHE();
    static BigInteger g =  dhe.gen;
    static BigInteger n = BigInteger.valueOf(103);
    static BigInteger resAlice;
    static BigInteger resBob;
    static BigInteger resEve;


    public static void main(String[] args) throws Exception {

        MultithreadingBob newB = new MultithreadingBob();
        Thread objectBob = new Thread(newB);
        objectBob.start();

        MultithreadingEveAliceRole newE = new MultithreadingEveAliceRole();
        Thread objectEve = new Thread(newE);
        objectEve.start();


        objectBob.join();
        objectEve.join();


         resBob = newB.getValue();
            System.out.println(resBob);

         resEve = newE.getValueAlice();
       System.out.println(resEve);
        objectBob.stop();
        objectEve.stop();
        if(resEve.equals(resBob)) {

            Thread messageBob = new Thread(new ThreadMessageBob());
            messageBob.start();
            Thread messageEve = new Thread(new ThreadMessageEveAliceRole());
            messageEve.start();

        }









    }
}
