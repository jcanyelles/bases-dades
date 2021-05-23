package es.cc.esliceu.db;

import es.cc.esliceu.db.presentacio.PantallaCataleg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.Scanner;

public class ExempleMetaDataJdbc {
    public static void main(String[] args) throws IOException, SQLException {
        FileInputStream input = new FileInputStream("bases-dades/resources/db.properties");
        Properties props = new Properties();
        props.load(input);

        String URL = props.getProperty("url");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Usuari:"); String user = scanner.nextLine();
        System.out.println("Password:"); String password = scanner.nextLine();
        System.out.println(URL);
        Connection con = DriverManager.getConnection(URL,user, password);
        DatabaseMetaData metadata = con.getMetaData();
        PantallaCataleg pantallaCataleg = new PantallaCataleg("Catàleg");
        pantallaCataleg.setCataleg(llistaCataleg(con));
        pantallaCataleg.render();
    }
    private static Collection<String> llistaCataleg(Connection connection) throws SQLException {
        Collection<String> resultat = new ArrayList<>();
        ResultSet rs = connection.getMetaData().getCatalogs();
        while(rs.next()){
            resultat.add(rs.getString(1));
        }
        return resultat;
    }
}
