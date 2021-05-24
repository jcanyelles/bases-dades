package es.cc.esliceu.db;

import es.cc.esliceu.db.dao.MetaDataDao;
import es.cc.esliceu.db.dao.impl.MetaDataDaoImpl;
import es.cc.esliceu.db.presentacio.PantallaCataleg;
import es.cc.esliceu.db.presentacio.PantallaPrincipal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.Scanner;

public class ExempleMetaDataJdbc {
    private MetaDataDao dao;

    public ExempleMetaDataJdbc(Connection connection) {
        this.dao = new MetaDataDaoImpl(connection);
    }


    public void pantallaPrincipal(Scanner scanner) {
        PantallaPrincipal principal = new PantallaPrincipal(dao, "Menú principal");
        principal.aferegixOpcio("b", "Bases de dades");
        principal.aferegixOpcio("x", "Sortir");
        principal.render(scanner);

    }

    public static void main(String[] args) throws IOException, SQLException {
        FileInputStream input = new FileInputStream("resources/db.properties");
        Properties props = new Properties();
        props.load(input);

        String URL = props.getProperty("url");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Usuari:"); String user = scanner.nextLine();
        System.out.println("Password:"); String password = scanner.nextLine();
        System.out.println(URL);

        Connection con = DriverManager.getConnection(URL,user, password);

        ExempleMetaDataJdbc exemple = new ExempleMetaDataJdbc(con);
        exemple.pantallaPrincipal(scanner);


    }
}
