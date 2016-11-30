package cz.hk.gmc.various;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingDemo {
    public static void main(String[] args) {
        try {
            final String input = "This is the input value";
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(input.getBytes());
            final byte[] output = md.digest();
            System.out.println(input + "=>" + output);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
