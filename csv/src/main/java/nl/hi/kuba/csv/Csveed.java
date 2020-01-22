package nl.hi.kuba.csv;

import java.io.FileReader;
import java.io.Reader;

import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;
import org.csveed.api.Header;
import org.csveed.api.Row;

public class Csveed {
    private static final String PATH = "bad_input_3.csv";

    private static void readAllTest() throws Exception {
        final Reader reader = new FileReader(ClassLoader.getSystemResource(PATH).getFile());
        final CsvClient client = new CsvClientImpl(reader);
        client.setSeparator('\t');
        final Header header = client.readHeader();
        System.err.println("MYTODO: header size=" + header.size());
        int line = 0;
        while (!client.isFinished()) {
            line++;
            try {
                final Row row = client.readRow();
                System.err.println("MYTODO: row size=" + row.size());
                for (final String column : row) {
                    System.err.println("MYTODO: column=" + column);
                }
            } catch (Exception ignored) {
                client.setStartRow(line + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        readAllTest();
    }
}
