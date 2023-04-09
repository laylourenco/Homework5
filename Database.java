/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homework5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author layan
 */
public class Database {
    
    private int invoice;
    private int stockCode;
    private String description;
    private int quantity;
    private String invoiceDate;
    private double price;
    private int CustomerId;
    private String country;
    private String FilePath = "newData.csv";
    private final String dbUrl = "jdbc:mysql://localhost";
    private final String dbUser = "root";
    private final String dbPass = "";
    private final String dbName = "homework";
    public List<Database> functions = new ArrayList<>();
      
    public Database(){
        this.invoice = invoice;
        this.stockCode = stockCode;
        this.description = description;
        this.quantity = quantity;
        this.invoiceDate = invoiceDate;
        this.price = price;
        this.CustomerId = CustomerId;
        this.country = country;
    }

    public Database(int invoice, int stockCode, String description, int quantity, String invoiceDate, double price, int cutomerId, String country) {
        this.invoice = invoice;
        this.stockCode = stockCode;
        this.description = description;
        this.quantity = quantity;
        this.invoiceDate = invoiceDate;
        this.price = price;
        this.CustomerId = cutomerId;
        this.country = country;
    }

    public void loadCsv() throws SQLException{
        String line; 
        try (BufferedReader loadCsv = new BufferedReader(new FileReader(FilePath))) { 
            while ((line = loadCsv.readLine()) != null) {
                String[] values = line.split(",");
                this.invoice = Integer.parseInt(values[0]);
                this.stockCode = Integer.parseInt(values[1]);
                this.description = values[2];
                this.quantity = Integer.parseInt(values[3]);
                this.invoiceDate = values[4];
                this.price = Double.parseDouble(values[5]);
                this.CustomerId = Integer.parseInt(values[6]);
                this.country = values[7];
                functions.add(new Database(invoice, stockCode, description, quantity, invoiceDate, price, CustomerId, country ));
            }
        } catch (IOException e) {
            System.err.println("Error to read the csv file.");
            e.printStackTrace();
        }
   }

   public void updateDb() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try{
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            Statement stmt = conn.createStatement();
            String sqlstmt = "CREATE DATABASE IF NOT EXISTS " + dbName + ";";
            System.out.println(sqlstmt);
            stmt.execute(sqlstmt);
            System.out.println("DB Created");
            stmt.execute("USE " + dbName + ";");
            stmt.execute("CREATE TABLE IF NOT EXISTS myreader ("
                    + "movId INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "invoice INT(10),"
                    + "stockCode INT(10),"
                    + "description VARCHAR(40),"
                    + "quantity INT(10),"
                    + "invoiceDate  VARCHAR(40),"
                    + "price DOUBLE PRECISION(40,2),"
                    + "cutomerId INT(10),"
                    + "country VARCHAR(40)"
                    + ");");
            System.out.println("Table Created");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void saveToDatabase() throws SQLException {
        Connection conn = DriverManager.getConnection(dbUrl + "/" + dbName, dbUser, dbPass);
        Statement stmt = conn.createStatement();
        for (Database listData : functions) {
            stmt.execute(String.format("INSERT INTO myreader (invoice, stockCode, description, quantity, invoiceDate, price, cutomerId, country) VALUES (%d, %d, '%s', %d, '%s', '%s', %d, '%s')", invoice, stockCode, description, quantity, invoiceDate, price, CustomerId, country));
        }
    }
    
}
