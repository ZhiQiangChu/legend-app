package cn.com.dplus.legend.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by hasee on 2017/3/3.
 */
public class HBaseDao {
    public static Configuration conf;
    public static Connection connection;

    static {
        Configuration HBASE_CONFIG = new Configuration();
        HBASE_CONFIG.set("hbase.zookeeper.quorum", "10.3.5.22,10.3.5.23,10.3.5.24");
        HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");
        conf = HBaseConfiguration.create(HBASE_CONFIG);
        try {
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     *
     * @param tableName
     * @param familys
     * @throws IOException
     */
    public static void createTable(String tableName, String[] familys) throws IOException {
        //HBaseAdmin admin=new HBaseAdmin(conf);
        // Connection connection= ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        if (admin.tableExists(TableName.valueOf(tableName))) {
            System.out.println("table already exists!");
        } else {
            HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
            for (int i = 0; i < familys.length; i++) {
                tableDescriptor.addFamily(new HColumnDescriptor(familys[i]));
            }
            admin.createTable(tableDescriptor);
            System.out.println("create table " + tableName + " ok.");
        }
    }

    /**
     * 删除表
     *
     * @param tableName
     * @throws IOException
     */
    public static void deleteTable(String tableName) throws Exception {
        try {
            Admin admin = connection.getAdmin();
            admin.disableTable(TableName.valueOf(tableName));
            admin.deleteTable(TableName.valueOf(tableName));
            System.out.println("delete table " + tableName + " ok.");
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据
     *
     * @param tableName
     * @param rowKey
     * @param family
     * @param qualifier
     * @param value
     * @throws Exception
     */
    public static void addRecord(String tableName, String rowKey, String family, String qualifier, String value) throws Exception {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
            table.put(put);
            System.out.println("insert record  " + rowKey + " to table " + tableName + " ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据
     *
     * @param tableName
     * @param rowKey
     * @throws Exception
     */
    public static void getOneRecord(String tableName, String rowKey) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        Result rs = table.get(get);
        for (Cell cell : rs.rawCells()) {
            System.out.println(new String(cell.getRowArray()) + " ");
            System.out.println(new String(cell.getFamilyArray()) + ":");
            System.out.println(new String(cell.getQualifierArray()) + " ");
            System.out.println(cell.getTimestamp() + " ");
            System.out.println(new String(cell.getValueArray()) + " ");
        }

    }

    /**
     * 浏览每一行
     *
     * @param tableName
     */
    public static void getAllRecord(String tableName) {

        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            ResultScanner rs = table.getScanner(scan);
            for (Result r : rs) {
                for (Cell cell : r.rawCells()) {
                    System.out.println(new String(cell.getRowArray()) + " ");
                    System.out.println(new String(cell.getFamilyArray()) + ":");
                    System.out.println(new String(cell.getQualifierArray()) + " ");
                    System.out.println(cell.getTimestamp() + " ");
                    System.out.println(new String(cell.getValueArray()) + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteRecord(String tableName, String rowKey) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        List list = new ArrayList();
        Delete delete = new Delete(rowKey.getBytes());
        list.add(delete);
        table.delete(list);
        System.out.println("delete record " + rowKey + " ok.");
    }

    public static void main(String[] args) throws Exception {
        HBaseDao hBaseDao = new HBaseDao();
//        hBaseDao.createTable("hello",new String[]{"ai","bi"});
        deleteTable("hello");
        //hBaseDao.addRecord("hello","row2","bi","521","yangqing");
        //hBaseDao.getOneRecord("hello","row2");
        //hBaseDao.getAllRecord("hello");
//        hBaseDao.deleteRecord("hello","row1");

    }
}