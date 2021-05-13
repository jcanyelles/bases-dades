package es.cc.esliceu.db;

import es.cc.esliceu.db.dao.bookinfo.DepartamentDao;
import es.cc.esliceu.db.dao.bookinfo.impl.DepartamentDaoImpl;
import es.cc.esliceu.db.domain.bookinfo.Departament;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class ClientBookinfoJdbc {

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
        Departament departament2 = dao.carrega(20);
        System.out.println("Departament " + departament2.getNom() + " " + departament2.getId());

        Departament departament3 = dao.carrega(1000);
        if (departament3!=null){
          System.out.println(departament3.getNom());
        }

        Departament nou = new Departament(500,"Departament 500", 100,null);
        dao.inserta(nou);

        Departament existeix = dao.carrega(500);
        System.out.println("Existeix? " + existeix);

        if (existeix!=null){
            dao.esborra(existeix);
        }
        System.out.println("***** iserta **** ");
        Departament nouDepartament = new Departament(512,"Departament 510", 101,null);
        dao.inserta(nouDepartament);

        System.out.println("***** modifica **** ");
        nouDepartament.setNom("Departament Elon Musk");
        dao.modifica(nouDepartament);

        for (Departament d : dao.llistaTotsDepartaments()){
            System.out.println("\t" + d);
        }
        con.close();
    }
}
