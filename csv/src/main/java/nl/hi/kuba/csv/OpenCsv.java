package nl.hi.kuba.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class OpenCsv {
    private static final String CORRECT = "input.csv";
    private static final String INCORRECT = "bad_input_3.csv";
    private static final String INPUT = INCORRECT;
    private static final Integer READ_AHEAD_LIMIT = 1000;
    //private static final Integer READ_AHEAD_LIMIT = 32768;

    private static List<String[]> readAll(Reader reader) throws Exception {
        final CSVReader csvReader = new CSVReader(reader);
        final List<String[]> list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }

    private static void readOneByOneTest() throws Exception {
        final Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(INPUT).toURI()));
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    private static void readAllTest() throws Exception {
        final Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(INPUT).toURI()));
        for (final String[] row : OpenCsv.readAll(reader)) {
            for (final String column : row) {
                System.err.println("MYTODO: " + column);
            }
        }
    }

    private static void readOneByOne() throws Exception {
        final FileWriter writer = new FileWriter(new File("/tmp/test-output.csv"));
        Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(INPUT).toURI()));
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
            } catch (IOException e) {
                System.err.println("MYTODO: wrong line=" + (linesRead + 1));
                reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(INPUT).toURI()));
                for (int i = 0; i <= linesRead; i++) {
                    ((BufferedReader) reader).readLine(); // skip previously read line
                }
                csvReader.close();
                csvReader = new CSVReader(reader);
            }
        } while (reader.ready());

        csvReader.close();
        reader.close();
        writer.close();
    }

    private static void validateOneByOneWithReader() throws IOException, URISyntaxException {
        try (InputStream inputStream = new FileInputStream(
                Paths.get(ClassLoader.getSystemResource(INPUT).toURI()).toFile());
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader.mark(READ_AHEAD_LIMIT);
            reader.reset();
            long lineCounter = 0;
            final CSVParser parser = new CSVParserBuilder()
                    //.withStrictQuotes(true)
                    //.withIgnoreQuotations(true)
                    .withSeparator(';').withQuoteChar('"')
                    //.withEscapeChar('\u0000')
                    .withEscapeChar('\\').build();
            CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build();

            do {
                lineCounter++;
                String[] line = null;

                try {
                    line = csvReader.readNext();
                    //System.err.println("MYTODO: " + lineCounter + ": " + String.join(";", line));
                } catch (Exception e) {
                    if (line != null) {
                        System.err.println(
                                "MYTODO: " + "Invalid CSV line (number " + lineCounter + ") was encountered ('" + line
                                        + "').");
                    } else {
                        System.err.println(
                                "MYTODO: " + "CSV line (number " + lineCounter + ") could not be read correctly. ");
                    }
                }
            } while (reader.ready());

            System.err.println("MYTODO: " + lineCounter);
        }
    }

    private static void validateOneByOneWithParser() throws IOException, URISyntaxException {
        try (InputStream inputStream = new FileInputStream(
                Paths.get(ClassLoader.getSystemResource(INPUT).toURI()).toFile());
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader.mark(READ_AHEAD_LIMIT);
            reader.reset();
            //final RFC4180Parser parser = new RFC4180ParserBuilder().build();
            //final CSVParser parser = new CSVParser();
            final CSVParser parser = new CSVParserBuilder()
                    //.withStrictQuotes(true)
                    //.withIgnoreQuotations(true)
                    .withSeparator(';').withQuoteChar('"').withEscapeChar('\\').build();
            long lineCounter = 0;

            do {
                lineCounter++;
                String line = null;

                try {
                    line = reader.readLine();
                    parser.parseLine(line);
                    //System.err.println("MYTODO: correctLine=" + line + ", pending=" + parser.isPending());
                } catch (IOException e) {
                    if (line != null) {
                        System.err.println(
                                "MYTODO: " + "Invalid CSV line (number " + lineCounter + ") was encountered ('" + line
                                        + "').");
                    } else {
                        System.err.println(
                                "MYTODO: " + "CSV line (number " + lineCounter + ") could not be read correctly. ");
                    }
                }
            } while (reader.ready());
        }
    }

    public static void main(String[] args) throws Exception {
        //readAllTest();
        //readOneByOneTest();
        //readOneByOne();
        //validateOneByOneWithReader();
        //validateOneByOneWithParser();
    }
}
