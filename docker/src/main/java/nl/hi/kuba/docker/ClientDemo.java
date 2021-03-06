package nl.hi.kuba.docker;

import java.io.IOException;

public class ClientDemo {
    private static void run() throws IOException {
        final String containerId = "10f9249c72b1";
        new ProcessBuilder("docker", "exec", containerId, "touch", "/tmp/test0.txt").start();
        System.out.println("Done");
    }

    public static void main(String[] args) {
        try {
            run();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
