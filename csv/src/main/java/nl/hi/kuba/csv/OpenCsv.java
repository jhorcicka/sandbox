package nl.hi.kuba.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvMalformedLineException;
import com.opencsv.exceptions.CsvValidationException;

public class OpenCsv {
    private static final String PATH = "input.csv";

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

    private static String[] readLine(final CSVReader reader) {
        String[] line = null;
        try {
            line = reader.readNext();
        } catch (CsvMalformedLineException e) {
            long lineNumber = e.getLineNumber();
            e.printStackTrace();
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    private static void readAllTest() throws Exception {
        final Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(PATH).toURI()));
        for (final String[] row : OpenCsv.readAll(reader)) {
            for (final String column : row) {
                System.err.println("MYTODO: " + column);
            }
        }
    }

    private static void validateOneByOne() throws Exception {
        final FileWriter writer = new FileWriter(new File("/tmp/test-output.csv"));
        Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(PATH).toURI()));
        CSVReader csvReader = new CSVReader(reader);
        long linesRead = 0;

        String[] columns;
        do {
            try {
                columns = csvReader.readNext();
                final String correctLine = String.join(",", columns) + "\n";
                writer.write(correctLine);
                linesRead++;

                System.err.println("MYTODO: correct line=" + correctLine);
            } catch (CsvMalformedLineException e) {
                System.err.println("MYTODO: wrong line=" + e.getLineNumber());
                reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(PATH).toURI()));
                for (int i = 0; i <= linesRead; i++) {
                    ((BufferedReader) reader).readLine(); // skip previously read line
                }
                csvReader.close();
                csvReader = new CSVReader(reader);
            } catch (CsvValidationException | IOException e) {
                e.printStackTrace();
            }
        } while (reader.ready());

        csvReader.close();
        reader.close();
        writer.close();
    }

    public static void main(String[] args) throws Exception {
        //readAllTest();
        //readOneByOneTest();
        validateOneByOne();
    }
}
