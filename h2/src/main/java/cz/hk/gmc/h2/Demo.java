package cz.hk.gmc.h2;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class Demo {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "person";

    private Connection connection;
    private Statement statement = null;

    public static void main(String[] args) {
        final Demo demo = new Demo();
        try {
            System.out.println("Creating database...");
            demo.createDatabase();
            System.out.println("Dropping table (if it exists)...");
            demo.dropTable();
            System.out.println("Creating table...");
            demo.createTable();
            System.out.println("Getting info...");
            demo.getInfo();
            System.out.println("Writing data...");
            demo.writeData();
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
        for (int i = 0; i < 5; i++) {
            final int value = i + 1;
            final int id = value;
            final String name = "name-" + value;
            final String surname = "surname-" + value;
            final int age = 30 + value;
            final String sql =
                    String.format("insert into " + TABLE_NAME + " values (%d, '%s', '%s', %d)", id, name, surname, age);
            command(sql);
        }
    }

    private void getInfo() throws SQLException {
        final String sql = "show columns from " + TABLE_NAME;
        debug(query(sql), sql);
    }

    private void dropTable() throws SQLException {
        final String sql =  "drop table if exists " + TABLE_NAME;
        command(sql);
    }

    private void createTable() throws SQLException {
        final String sql =  "create table " + TABLE_NAME +
            "(id integer not null, " +
            " first_name varchar(255), " +
            " last_name varchar(255), " +
            " age integer, " +
            " primary key ( id ))";
        command(sql);
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

    private void createDatabase() throws ClassNotFoundException, SQLException {
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
}
