import java.math.BigInteger;
import java.util.Random;

public class DHE{

    // randomly generated base and modulus
    protected BigInteger primeNum;
    protected BigInteger gen;

    //generate random prime and gen variable. Prime is our modulo, gen is our base
    DHE(){
        gen = generateRandom(1000, 2);
        this.primeNum = generateRandom(100, 2);

        if(!primeNum.isProbablePrime(1)){
            this.primeNum = primeNum.nextProbablePrime();
        }
    }


//generate random Integer
    protected BigInteger generateRandom(int upperLimit, int lowerLimit){
        BigInteger bigInteger = BigInteger.valueOf(upperLimit);// upper limit
        BigInteger min = BigInteger.valueOf(lowerLimit);// lower limit
        BigInteger bigInteger1 = bigInteger.subtract(min);
        Random rnd = new Random();
        int maxNumBitLength = bigInteger.bitLength();

        BigInteger aRandomBigInt;
        aRandomBigInt = new BigInteger(maxNumBitLength, rnd);


        if (aRandomBigInt.compareTo(min) < 0) {
            aRandomBigInt = aRandomBigInt.add(min);
        }
        if (aRandomBigInt.compareTo(bigInteger) >= 0) {
            aRandomBigInt = aRandomBigInt.mod(bigInteger1).add(min);
        }

        return aRandomBigInt;
    }

    //for subclasses to calculate their public keys
    protected BigInteger calculatePublicKey(BigInteger g, BigInteger expo, BigInteger prime){
        return g.modPow(expo, prime);
    }
}
