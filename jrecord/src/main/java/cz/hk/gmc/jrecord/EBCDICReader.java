package cz.hk.gmc.jrecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class EBCDICReader {
    private static final String CHARSET = "ibm500";
    private static final int HEADER_SIZE = 854;
    private static final int EOF = -1;
    private static final int[] WIDTHS =
            new int[] { 2, 1, 8, 3, 105, 70, 35, 4, 92, 3, 35, 35, 35, 35, 105, 70, 35, 105, 76 };
    private int _numericCharacter;
    private int _column = 0;
    private StringBuilder _columnValue = new StringBuilder();
    private InputStreamReader _inputStreamReader = null;

    public static void main(String[] args) {
        try {
            EBCDICReader reader = new EBCDICReader();
            reader.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/ebcdic.dat");
        Charset charset = Charset.forName(CHARSET);
        _inputStreamReader = new InputStreamReader(inputStream, charset);

        skipHeader();
        processContent();
    }

    private void processContent() throws IOException {
        int counter = 0;
        int limit = 3;

        while (nextChar() != EOF) {
            readRecord();
            counter++;

            if (counter >= limit) {
                break;
            }
        }
    }

    private void readRecord() throws IOException {
        for (int columnSize : WIDTHS) {
            readColumn(columnSize);
            nextColumn();
        }
    }

    private void readColumn(int columnSize) throws IOException {
        for (int i = 0; i < columnSize; i++) {
            _columnValue.append(nextChar());
        }
    }

    private void skipHeader() throws IOException {
        for (int i = 0; i < HEADER_SIZE; i++) { // skipping header: HDREBUILD DATE20140503 and then spaces
            nextChar();
        }
    }

    private char nextChar() throws IOException {
        _numericCharacter = _inputStreamReader.read();

        return (char)_numericCharacter;
    }

    private void nextColumn() {
        System.out.println("Column=" + _column + ": " + _columnValue.toString() + " [" + WIDTHS[_column] + "]");
        _column = (_column + 1) % WIDTHS.length;
        _columnValue = new StringBuilder();
    }
}
