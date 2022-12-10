import java.math.BigInteger;

public class Bob extends DHE{
    private BigInteger bobPrivateKey;
    public BigInteger bobPublicKey;
    public BigInteger alicePublicKey;
    private BigInteger message;

    Bob(){
        bobPrivateKey = generateRandom(1000000, 1000);
    }

    void setAlice(BigInteger key, BigInteger prime){
        alicePublicKey = key;
        message = calculateSharedSecret(alicePublicKey, bobPrivateKey, prime);
    }

    protected void setPublicKey(BigInteger g , BigInteger prime){
        bobPublicKey = calculatePublicKey(g, bobPrivateKey, prime);
    }

    protected BigInteger getMessage(){
        return message;
    }
}
