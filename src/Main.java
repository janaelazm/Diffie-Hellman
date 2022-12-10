import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //System.out.print("g = " + Alice.gen + " and p = " + example.primeNum);

        DHE dhe = new DHE();
        System.out.println(dhe.primeNum + " , " + dhe.gen);
        Alice alice1 = new Alice();
        Bob bob1 = new Bob();

        System.out.println("Shhhh bob and alice just agreed upon a p and g");
        promptEnterKey();
        System.out.println("shhh alice and bob are about to exchange public keys...");
        alice1.setPublicKey(dhe.gen ,dhe.primeNum);
        bob1.setPublicKey(dhe.gen , dhe.primeNum);
        promptEnterKey();
        System.out.println("Public key from alice is....." + alice1.alicePublicKey);
        bob1.setAlice(alice1.alicePublicKey, dhe.primeNum);
        System.out.println("Public key from bob is....." + bob1.bobPublicKey);
        alice1.setBob(bob1.bobPublicKey, dhe.primeNum);
        promptEnterKey();
        System.out.println("Bobs Message....."+bob1.getMessage());
        System.out.println("alices Message....."+alice1.getMessage());


    }

    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
