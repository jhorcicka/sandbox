package nl.hi.kuba.redshift;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Demo {
    private static final String JDBC_DRIVER = "com.amazon.redshift.jdbc42.Driver";
    private static final String DB_URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "";

    private Connection connection;
    private Statement statement = null;

    private static void sample() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //Dynamically load driver at runtime.
            //Redshift JDBC 4.1 driver: com.amazon.redshift.jdbc41.Driver
            //Redshift JDBC 4 driver: com.amazon.redshift.jdbc4.Driver
            Class.forName("com.amazon.redshift.jdbc.Driver");

            //Open a connection and define properties.
            System.out.println("Connecting to database...");
            Properties props = new Properties();

            //Uncomment the following line if using a keystore.
            //props.setProperty("ssl", "true");
            props.setProperty("user", USERNAME);
            props.setProperty("password", PASSWORD);
            conn = DriverManager.getConnection(DB_URL, props);

            //Try a simple query.
            System.out.println("Listing system tables...");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from information_schema.tables;";
            ResultSet rs = stmt.executeQuery(sql);

            //Get the data from the result set.
            while (rs.next()) {
                //Retrieve two columns.
                String catalog = rs.getString("table_catalog");
                String name = rs.getString("table_name");

                //Display values.
                System.out.print("Catalog: " + catalog);
                System.out.println(", Name: " + name);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            //For convenience, handle all errors here.
            ex.printStackTrace();
        } finally {
            //Finally block to close resources.
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception ex) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Finished connectivity test.");
    }

    private static void mine() {
        final Demo demo = new Demo();
        try {
            System.out.println("Connecting to the database...");
            demo.connectToDatabase();
            System.out.println("Reading data...");
            demo.readData();
        } catch (final Exception e) {
            System.err.println("Exception during demo: ");
            e.printStackTrace();
        } finally {
            try {
                demo.cleanup();
            } catch (final Exception sqle) {
                System.err.println("Problem during cleanup: ");
                sqle.printStackTrace();
            }
        }
    }

    private void readData() throws SQLException {
        final String sql = "select * from " + TABLE_NAME;
        debug(query(sql), sql);
    }

    private void writeData() throws SQLException {
        for (int i = 0; i < 3; i++) {
            final int value = i + 1;
            final int id = value;
            final String name = "name-" + value;
            final String sql = String.format("insert into " + TABLE_NAME + " values (%d, '%s')", id, name);
            command(sql);
        }
    }

    private void debug(final ResultSet resultSet) throws SQLException {
        debug(resultSet, "");
    }

    private void debug(final ResultSet resultSet, final String sql) throws SQLException {
        System.out.println("DEBUG: " + sql);

        while (resultSet.next()) {
            System.out.println("\tROW(" + resultSet.getRow() + "): ");
            int i = 1;

            try {
                while (true) {
                    final Object object = resultSet.getObject(i++);
                    System.out.println("\t\t" + object.toString());
                }
            } catch (final Exception e) {
                continue;
            }
        }
    }

    private ResultSet query(final String sql) throws SQLException {
        if (statement != null) {
            statement.close();
        }

        statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    private void command(final String sql) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(sql);

        if (statement != null) {
            statement.close();
        }
    }

    private void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    private void cleanup() throws SQLException {
        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        //sample();
        mine();
    }
}
