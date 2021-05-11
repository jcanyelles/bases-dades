package es.cc.esliceu.db;

import es.cc.esliceu.db.dao.DepartamentDao;
import es.cc.esliceu.db.dao.impl.DepartamentDaoImpl;
import es.cc.esliceu.db.domain.Departament;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class ClientJdbc {

    public static void main(String[] args) throws IOException, SQLException {
        FileInputStream input = new FileInputStream("bases-dades/resources/db.properties");
        Properties props = new Properties();
        props.load(input);

        String URL = props.getProperty("url");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Usuari:"); String user = scanner.nextLine();
        System.out.println("Password:"); String password = scanner.nextLine();

        Connection con = DriverManager.getConnection(URL,user, password);
        DepartamentDao dao = new DepartamentDaoImpl(con);
        Departament departament = dao.carrega(30);
        System.out.println("Departament " + departament.getNom());
        con.close();
    }
}
