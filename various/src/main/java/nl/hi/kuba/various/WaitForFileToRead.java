package nl.hi.kuba.various;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WaitForFileToRead {
    private static final String PATH = "/home/kuba/tmp/hot/in/file.pdf";
    private static final String COMMAND = "lsof | grep \"" + PATH + "\" | sed s/\\ \\ */\\ /g | cut -d' ' -f 4";

    private static void readStreamLinux() throws IOException {
        final File file = new File(PATH);

        if (!file.exists()) {
            System.out.println("File does not exist...");
            return;
        }

        while (isOpenedForWriting()) {
            System.out.println("waiting for the writing to be finished...");
        }

        FileInputStream fis = null;
        int iterations = 0;

        try {
            fis = new FileInputStream(file);

            while (fis.read() > 0) {
                iterations++;
            }
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e2) {
                // nothing
            }
        }

        System.out.println("iterations=" + iterations);
    }

    private static void readStreamWindows() throws IOException {
        final File file = new File(PATH);
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            int iterations = 0;
            int i;

            while ((i = fis.read()) > 0) {
                iterations += i;
                System.out.println("iterations=" + iterations);
            }
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e2) {
                // nothing
            }
        }
    }

    private static boolean isOpenedForWriting() throws IOException {
        final Process process = new ProcessBuilder("/bin/sh", "-c", COMMAND).start();
        final InputStream inputStream = process.getInputStream();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.toLowerCase().contains("w") || line.contains("u")) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        boolean done = false;
        final String systemName = System.getProperty("os.name");

        while (!done) {
            try {
                if (systemName.equals("Linux")) {
                    readStreamLinux();
                } else {
                    readStreamWindows();
                }

                done = true;
            } catch (final IOException e) {
                // copying is not finished
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}
