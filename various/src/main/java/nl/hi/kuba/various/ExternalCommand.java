package nl.hi.kuba.various;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExternalCommand {
    private static void outputWithArguments() throws Exception {
        final String command = "pwd";
        final String argument = "--help";
        Process process = new ProcessBuilder(command, argument).start();

        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        System.out.println("Output: ");

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }

    private static void outputWithoutArguments() throws Exception {
        Process proc = Runtime.getRuntime().exec("pwd");
        InputStream ips = proc.getInputStream();
        int c;
        String output = "";

        while ((c = ips.read()) != -1) {
            output += (char) c;
        }

        System.out.println(output);
    }

    private static void noOutput() throws Exception {
        final String command = "touch";
        final String argument = "/tmp/simple-file.txt";
        Process process = new ProcessBuilder(command, argument).start();
    }

    public static void main(String[] args) {
        try {
            System.out.println(System.getProperty("os.name"));
            outputWithArguments();
            outputWithoutArguments();
            noOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
