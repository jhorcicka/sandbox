package nl.hi.kuba.csv;

import java.util.Arrays;

import com.csvreader.CsvReader;

public class JavaCsv {
    private static final String PATH = "bad_input_3.csv";

    private static void readAllTest() throws Exception {
        final CsvReader reader = new CsvReader(ClassLoader.getSystemResource(PATH).getPath());
        reader.setDelimiter('\t'); // manually specified!
        reader.readHeaders();

        while (reader.readRecord()) {
            System.err.println("line: " + reader.getRawRecord());
            if (reader.getHeaderCount() <= reader.getColumnCount()) {
                System.err.println("OK: " + String
                        .join(", ", Arrays.asList(reader.getValues()).subList(0, reader.getHeaderCount())));
            } else {
                System.err.println("ERR: " + String.join(", ", reader.getValues()));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        readAllTest();
    }
}
