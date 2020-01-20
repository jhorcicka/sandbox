package nl.hi.kuba.csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvMalformedLineException;
import com.opencsv.exceptions.CsvValidationException;

public class Main {
    private static final String PATH = "bad_input.csv";
    //private static final String PATH = "input.csv";

    private static List<String[]> readAll(Reader reader) throws Exception {
        final CSVReader csvReader = new CSVReader(reader);
        final List<String[]> list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }

    private static void readOneByOneTest() throws Exception {
        final Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(PATH).toURI()));
        final CSVReader csvReader = new CSVReader(reader);

        String[] line;
        do {
            line = readLine(csvReader);
            for (final String column : line) {
                System.err.println("MYTODO: " + column);
            }
        } while (line != null);

        reader.close();
        csvReader.close();
    }

    private static String[] readLine(final CSVReader reader) throws IOException {
        String[] line = null;
        try {
            line = reader.readNext();
        } catch (CsvMalformedLineException e) {
            System.err.println("MYTODO: " + e.getLineNumber());
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    private static void readAllTest() throws Exception {
        final Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(PATH).toURI()));
        for (final String[] row : Main.readAll(reader)) {
            for (final String column : row) {
                System.err.println("MYTODO: " + column);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //readAllTest();
        readOneByOneTest();
    }
}
