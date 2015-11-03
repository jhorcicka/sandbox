import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Files {
    private static Logger LOGGER = Logger.getLogger(Files.class.getName());

    public static void main(String[] args) throws IOException, SQLException {
        bufferedReader();
    }

    private static void bufferedReader() throws FileNotFoundException {

        try {
            String path = "/home/kuba/tmp/Dutch%20Movers%20Index/file.txt";
            //String path = "/home/kuba/tmp/Dutch Movers Index/file.txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                LOGGER.info(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
