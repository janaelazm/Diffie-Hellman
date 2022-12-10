import java.math.BigInteger;

public class Alice extends DHE{
    private BigInteger alicePrivateKey;
    public BigInteger alicePublicKey;
    public BigInteger bobPublicKey;
    private BigInteger message;


    Alice(){
        alicePrivateKey = generateRandom(1000000, 1000);
    }

    void setBob(BigInteger key, BigInteger prime){
        bobPublicKey = key;
        message = calculateSharedSecret(bobPublicKey, alicePrivateKey, prime);
    }

    protected void setPublicKey(BigInteger g , BigInteger prime){
        alicePublicKey = calculatePublicKey(g, alicePrivateKey, prime);
    }

    protected BigInteger getMessage(){
        return message;
    }
}
