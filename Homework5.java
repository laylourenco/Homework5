/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package homework5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;


/**
 *
 * @author layan
 */
public class Homework5 {


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Database batabse = new Database();
        batabse.updateDb();
        batabse.loadCsv();
        batabse.saveToDatabase();
    
    }
    
}
