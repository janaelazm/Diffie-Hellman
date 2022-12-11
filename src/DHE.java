import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;
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
    protected BigInteger calculateModPow(BigInteger g, BigInteger expo, BigInteger prime){
        return g.modPow(expo, prime);
    }



    public String decodeMessage(String message, String secretKey) throws Exception {
        return decodeWord(message, secretKey);
    }


    public static String encodeWord(String word, String key) throws  Exception {
        Security.addProvider(new BouncyCastleProvider());
        Charset charset = StandardCharsets.US_ASCII;
        MessageDigest keySHA256 = MessageDigest.getInstance("SHA-256");
        byte[] byteArrOfWord = word.getBytes(charset);
        byte[] secretWordByte = keySHA256.digest(key.getBytes(charset));
        //Solution offered by Billy Julian Lesmana
        //https://moodle.htw-berlin.de/mod/forum/discuss.php?d=205648
        if(byteArrOfWord.length % 16 != 0) {
            byte[] padded = new byte[byteArrOfWord.length + 16 - (byteArrOfWord.length % 16)];
            System.arraycopy(byteArrOfWord, 0, padded, 0, byteArrOfWord.length);
            byteArrOfWord = padded;
        }
        SecretKeySpec secretKey = new SecretKeySpec(secretWordByte, "AES");
        Cipher var4 = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        System.out.println("input text : " + Utils.toHex(byteArrOfWord));
        byte[] var5 = new byte[byteArrOfWord.length];
        var4.init(1, secretKey);
        int var6 = var4.update(byteArrOfWord, 0, byteArrOfWord.length, var5, 0);
      //  var6 += var4.doFinal(var5, var6);
        return Utils.toHex(var5);
    }



    public static byte[] hexToByteArr(String hex ) {
        byte[] data = new byte[hex.length() / 2];
        for (int i = 0; i < data.length; i++) {
            int index = i * 2;
            int val = Integer.parseInt(hex.substring(index, index+2), 16);
            data[i] = (byte) val;
        }
        return data;
    }

    public static String decodeWord(String word, String key) throws  Exception {
        Security.addProvider(new BouncyCastleProvider());
        Charset charset = StandardCharsets.US_ASCII;
        Cipher var4 = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        byte[] var5 = hexToByteArr(word);
        byte[] var7 = new byte[var5.length];
        MessageDigest keySHA256 = MessageDigest.getInstance("SHA-256");
        byte[] secretWordByte = keySHA256.digest(key.getBytes(charset));
        SecretKeySpec secretKey = new SecretKeySpec(secretWordByte, "AES");
        var4.init(2, secretKey);
        int var8 = var4.update(var5, 0, var5.length, var7, 0);
    //    var8 += var4.doFinal(var7, var8);
       // System.out.println(new String(var7, charset));
        return Utils.toHex(var7);
    }


        public void sendMessage(String message, OutputStreamWriter osw) throws IOException {
            //convert data to stream format, parameter-to where we send info, in this case we sent to output port of a socket
            //send the data
            PrintWriter out = new PrintWriter(osw);
            out.println(message);
            osw.flush();
        }

    public BigInteger receiveMessage(Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String keyToShareBob= br.readLine();
        BigInteger b = new BigInteger(keyToShareBob);
        return b;

    }

}
