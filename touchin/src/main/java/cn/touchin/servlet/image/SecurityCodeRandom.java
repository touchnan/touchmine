/*
 * cn.touchin.servlet.image.SecurityCodeRandom.java
 * Mar 3, 2012 
 */
package cn.touchin.servlet.image;

import java.security.SecureRandom;

/**
 * Mar 3, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class SecurityCodeRandom {
    static final String NUMBERS = "1234567890";
    static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String SMALL_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Returns a printable String corresponding to a byte array.
     * 
     * @param b
     * @return rand String
     */
    public static synchronized String printable(byte[] b) {
        final char[] alphabet = (SMALL_LETTERS + CAPITAL_LETTERS + NUMBERS).toCharArray();
        char[] out = new char[b.length];
        for (int i = 0; i < b.length; i++) {
            int index = b[i] % alphabet.length;
            if (index < 0) {
                index += alphabet.length;
            }
            out[i] = alphabet[index];
        }
        return new String(out);
    }

    /**
     * Returns a printable String corresponding to a byte array.
     * 
     * @param b
     * @return number string
     */
    public static synchronized String printableNumber(byte[] b) {
        final char[] alphabet = NUMBERS.toCharArray();
        char[] out = new char[b.length];
        for (int i = 0; i < b.length; i++) {
            int index = b[i] % alphabet.length;
            if (index < 0) {
                index += alphabet.length;
            }
            out[i] = alphabet[index];
        }
        return new String(out);
    }

    /**
     * get rand id
     * 
     * @param length
     * @return random id
     */
    public static String getRandomID(int length) {
        // produce the random transaction ID
        byte[] b = new byte[length];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(b);
        return printable(b);
    }

    /**
     * get numberic rand id
     * 
     * @param length
     * @return random id
     */
    public static String getNumericRandomID(int length) {
        // produce the random transaction ID
        byte[] b = new byte[length];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(b);
        return printableNumber(b);
    }

}
