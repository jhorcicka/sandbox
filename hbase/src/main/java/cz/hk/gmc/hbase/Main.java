package cz.hk.gmc.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class Main {
    private static final String TABLE_NAME = "data";
    private static final String FAMILY_PERSONAL = "personal";
    private static final String FAMILY_PROFESSIONAL = "professional";
    private final Configuration _conf;
    private final HBaseAdmin _admin;

    public static void main(String[] args) {
        try {
            final Main main = new Main();
            main.dropTable();
            main.listTables();
            main.createTable();
            main.listTables();
            main.putData();
            main.readData();
            main.updateData();
            main.readData();
            main.deleteData();
            main.readData();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteData() throws IOException {
        final HTable table = new HTable(_conf, TABLE_NAME);
        final Delete delete = new Delete(Bytes.toBytes("row1"));
        delete.deleteColumn(Bytes.toBytes("personal"), Bytes.toBytes("name"));
        delete.deleteFamily(Bytes.toBytes("professional"));
        table.delete(delete);
        table.close();
        System.out.println("Data deleted.");
    }

    public void readData() throws IOException {
        final HTable table = new HTable(_conf, TABLE_NAME);
        final Get get = new Get(Bytes.toBytes("row1"));
        final Result result = table.get(get);
        byte [] value = result.getValue(Bytes.toBytes("personal"),Bytes.toBytes("name"));
        byte [] value1 = result.getValue(Bytes.toBytes("personal"),Bytes.toBytes("city"));
        final String name = Bytes.toString(value);
        final String city = Bytes.toString(value1);
        System.out.println("Read data: name=" + name + "; city=" + city);
    }

    public void updateData() throws IOException {
        final HTable table = new HTable(_conf, TABLE_NAME);
        final Put put = new Put(Bytes.toBytes("row1"));
        put.add(Bytes.toBytes(FAMILY_PERSONAL), Bytes.toBytes("city"),Bytes.toBytes("Moscow"));
        table.put(put);
        table.close();
        System.out.println("Data updated.");
    }

    public void putData() throws IOException {
        final Put put = new Put(Bytes.toBytes("row1"));
        put.add(Bytes.toBytes(FAMILY_PERSONAL), Bytes.toBytes("name"),Bytes.toBytes("John"));
        put.add(Bytes.toBytes(FAMILY_PERSONAL), Bytes.toBytes("city"),Bytes.toBytes("Rome"));
        put.add(Bytes.toBytes(FAMILY_PROFESSIONAL),Bytes.toBytes("designation"), Bytes.toBytes("manager"));
        put.add(Bytes.toBytes(FAMILY_PROFESSIONAL), Bytes.toBytes("salary"), Bytes.toBytes("50000"));
        final HTable table = new HTable(_conf, TABLE_NAME);
        table.put(put);
        table.close();
        System.out.println("Data inserted.");
    }

    public void listTables() throws IOException {
        System.out.println("Table listing: ");

        for (final HTableDescriptor descriptor : _admin.listTables()) {
            System.out.println("* " + descriptor.getNameAsString());
        }
    }

    public void createTable() throws IOException {
        final HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
        tableDescriptor.addFamily(new HColumnDescriptor(FAMILY_PERSONAL));
        tableDescriptor.addFamily(new HColumnDescriptor(FAMILY_PROFESSIONAL));
        _admin.createTable(tableDescriptor);
        System.out.println("Table created.");
    }

    public void dropTable() throws IOException {
        if (_admin.tableExists(TABLE_NAME)) {
            _admin.disableTable(TABLE_NAME);
            _admin.deleteTable(TABLE_NAME);
            System.out.println("Table deleted.");
        }
    }

    public Main() throws IOException {
        _conf = HBaseConfiguration.create();
        _admin = new HBaseAdmin(_conf);
        System.out.println("Configuration created.");
    }
}
