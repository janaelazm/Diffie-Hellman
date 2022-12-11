public class Utils {
    private static final String digits = "0123456789abcdef";

    public Utils() {
    }

    public static String toHex(byte[] var0, int var1) {
        StringBuilder var2 = new StringBuilder();

        for(int var3 = 0; var3 != var1; ++var3) {
            int var4 = var0[var3] & 255;
            var2.append(digits.charAt(var4 >> 4));
            var2.append(digits.charAt(var4 & 15));
        }

        return var2.toString();
    }

    public static String toHex(byte[] var0) {
        return toHex(var0, var0.length);
    }

}
