package nl.hi.kuba.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public class LogbackDemo {
    private static final Logger logger;
    static Integer i;

    static {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "/home/jakub/logback.xml");
        logger = LoggerFactory.getLogger(LogbackDemo.class);
    }

    {
        System.out.println("hello");
    }

    private static void abc() {
        logger.info("from abc...");
    }

    public static void main(String[] args) {
        // -Dlogback.configurationFile=/home/jakub/desktop/logback.xml
        logger.info("#1: ", LogbackDemo.class.getSimpleName());

        final String newConfigFile = "/home/jakub/desktop/logback.xml";
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, newConfigFile);
        final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        try {
            final JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();
            configurator.doConfigure(newConfigFile);
        } catch (final JoranException je) {
            je.printStackTrace();
        }

        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
        logger.info("#2: ", LogbackDemo.class.getSimpleName());
        abc();
    }
}
