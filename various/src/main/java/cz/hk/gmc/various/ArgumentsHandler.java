package cz.hk.gmc.various;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 * @author j.horcicka (GMC)
 * @since 29.4.15
 */
public class ArgumentsHandler {
    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newArgumentParser("Checksum")
                .defaultHelp(true)
                .description("Calculate checksum of given files.");
        parser.addArgument("-t", "--type")
                .choices("SHA-256", "SHA-512", "SHA1")
                .setDefault("SHA-256")
                .help("Specify hash function to use");
        parser.addArgument("file")
                .nargs("*")
                .help("File to calculate checksum");
        parser.formatHelp();
        parser.formatUsage();
        parser.formatVersion();

        Namespace ns = null;

        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        System.out.println("Type=" + ns.getString("type"));
    }
}
