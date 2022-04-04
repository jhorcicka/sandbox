package nl.hi.kuba.logging.slf4j;

import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Bug {
    private static Logger logger = LogManager.getLogManager().getLogger(String.valueOf(Bug.class));

    public static void main(String[] args) {
        System.out.println("Main says, 'Hello, world.'");
        logger.log(Level.SEVERE, args[0]);
        System.out.println("Main is exiting.");
    }
}
