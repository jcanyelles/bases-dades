package es.cc.esliceu.db;

import es.cc.esliceu.db.dao.jardineria.ClientDao;
import es.cc.esliceu.db.dao.jardineria.impl.ClientDaoImpl;
import es.cc.esliceu.db.domain.jardineria.Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.Scanner;

public class JardineriaClient {
    public static void main(String[] args) throws IOException, SQLException {
        FileInputStream input = new FileInputStream("bases-dades/resources/db.properties");
        Properties props = new Properties();
        props.load(input);

        String URL = props.getProperty("url_jardineria");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Usuari:"); String user = scanner.nextLine();
        System.out.println("Password:"); String password = scanner.nextLine();

        Connection con = DriverManager.getConnection(URL,user, password);
        ClientDao dao = new ClientDaoImpl(con);
        Collection<Client> clients = dao.llistaClients(null,"o%",null,null);
        for (Client client : clients) {
            System.out.println(client);
        }

        Client client = dao.carrega(22);
        System.out.println(client);
        con.close();
    }
}
