package nl.hi.kuba;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Reading {
    private static final String CHARSET_NAME = "IBM500";
    private static final String INPUT_FILE = "input.ebc";
    private static final int BUFFER_SIZE = 10;

    public Reading() throws IOException {
        BufferedInputStream stream = new BufferedInputStream(this.getClass().getResourceAsStream("/" + INPUT_FILE));
        int ch;
        byte[] buffer = new byte[BUFFER_SIZE];

        for (int i = 0; i < BUFFER_SIZE; i++) {
            ch = stream.read();
            System.out.println("ch=" + ch);
            buffer[i] = (byte) ch;
        }

        String data = new String(buffer, CHARSET_NAME);
        System.out.println(data);
    }

    public static void main(String[] args) {
        try {
            new Reading();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
