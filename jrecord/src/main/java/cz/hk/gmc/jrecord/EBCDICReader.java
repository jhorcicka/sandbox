package cz.hk.gmc.jrecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class EBCDICReader {
    private static final String CHARSET = "ibm500";

    public static void main(String[] args) {
        try {
            EBCDICReader reader = new EBCDICReader();
            reader.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        int numericCharacter;
        InputStream inputStream = getClass().getResourceAsStream("/ebcdic.dat");
        Charset charset = Charset.forName(CHARSET);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);

        while ((numericCharacter = inputStreamReader.read()) != -1) {
            System.out.println((char)numericCharacter);
        }
    }
}
