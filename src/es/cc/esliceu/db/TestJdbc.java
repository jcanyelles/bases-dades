package es.cc.esliceu.db;

import es.cc.esliceu.db.dao.DepartamentDao;
import es.cc.esliceu.db.dao.impl.DepartamentDaoImpl;
import es.cc.esliceu.db.domain.Departament;
import es.cc.esliceu.db.domain.Empleat;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class TestJdbc {
    private final Connection connection;
    public TestJdbc(Connection connection) {
        this.connection = connection;
    }


    public Collection<Empleat> llistaEmpleats(Integer codiEmpleat, String nom, Integer codiDepartament, String job) throws SQLException {
        Collection<Empleat> resultat = new ArrayList<>();
        int params = 1;
        Map<String,Integer> mapaParametres = new HashMap<>();
        String sql = "select employee_id, first_name, last_name, email, hire_date, job_id, department_id from employees where 1=1 ";
        if (codiEmpleat!=null){
            sql += " and employee_id = ? ";
            mapaParametres.put("codiEmpleat", params);
            params++;
        }
        if (codiDepartament!=null){
            sql += " and department_id = ? ";
            mapaParametres.put("codiDepartament", params);
            params++;
        }
        if (job!=null){
            sql += " and job_id = ? ";
            mapaParametres.put("job", params);
            params++;
        }
        if (nom!=null){
            sql += " and first_name  like ( ? ) ";
            mapaParametres.put("nom", params);
            params++;
        }
        System.out.println(sql);

        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(sql);
            if (codiEmpleat!=null){
                Integer posicio = mapaParametres.get("codiEmpleat");
                statement.setInt(posicio, codiEmpleat);
            }
            if (codiDepartament!=null){
                Integer posicio = mapaParametres.get("codiDepartament");
                statement.setInt(posicio, codiDepartament);
            }
            if (job!=null){
                Integer posicio = mapaParametres.get("job");
                statement.setString(posicio, job);
            }
            if (nom!=null){
                Integer posicio = mapaParametres.get("nom");
                statement.setString(posicio, nom);
            }
            rs = statement.executeQuery();
            while (rs.next()){
                Empleat empleat = new Empleat(rs.getInt("employee_id"));
                empleat.setNom(rs.getString("first_name"));
                empleat.setLlinatge(rs.getString("last_name"));
                empleat.setEmail(rs.getString("email"));
                empleat.setCodiDepartament(rs.getLong("department_id"));
                empleat.setJobId(rs.getString("job_id"));
                empleat.setNaixement(rs.getDate("hire_date"));
                resultat.add(empleat);
            }
            return resultat;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs!=null){
                rs.close();
            }
            if (statement!=null){
                statement.close();
            }
        }
        return resultat;

    }
    public void transaccions(String id, Float parcentatge) throws SQLException {
        PreparedStatement stJobs = null;
        PreparedStatement stEmpleats = null;
        try {
            stJobs = connection.prepareStatement("UPDATE jobs set max_salary = max_salary + (max_salary * ? ) where job_id = ?");
            stJobs.setFloat(1, parcentatge);
            stJobs.setString(2, id);
            stJobs.executeUpdate();

            stEmpleats = connection.prepareStatement("update employees1 set salary = salary + (salary * ?) where job_id = ?");
            stEmpleats.setFloat(1, parcentatge);
            stEmpleats.setString(2, id);
            stEmpleats.executeUpdate();

            connection.commit();


        } catch (Exception e){
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (stJobs!=null){
                stJobs.close();
            }
            if (stEmpleats!=null){
                stEmpleats.close();
            }
        }

    }

    public Integer procediment(int codiDepartament) throws SQLException {
        Integer resultat= null;
        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("{ ?= call recompteEmpleatsPerDepartament (?) }");
            statement.setInt(2,codiDepartament);
            statement.registerOutParameter(1, Types.INTEGER);
            rs = statement.executeQuery();
            if (rs.next()){
                resultat = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs!=null){
                rs.close();
            }
            if (statement !=null){
                statement.close();
            }
        }
        return resultat;
    }
    public void actualitzaDepartament (int codiDepartament, String nom) throws SQLException {
        PreparedStatement stUpdate = null;
        try {
            stUpdate =connection.prepareStatement("UPDATE departments set department_name = ? where department_id = ?");
            stUpdate.setString(1, nom);
            stUpdate.setInt(2, codiDepartament);
            stUpdate.executeUpdate();
        } catch (Exception e ) {
            e.printStackTrace();
        }finally {
            if (stUpdate!=null){
                stUpdate.close();
            }
        }
    }
    public static void main(String[] args) throws IOException, SQLException {
        FileInputStream input = new FileInputStream("resources/db.properties");
        Properties props = new Properties();
        props.load(input);

        String URL = props.getProperty("url");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Usuari:"); String user = scanner.nextLine();
        System.out.println("Password:"); String password = scanner.nextLine();

        Connection con = DriverManager.getConnection(URL,user, password);
        //con.setAutoCommit(false);
        DepartamentDao dao = new DepartamentDaoImpl(con);
        Departament departament = dao.carrega(30);
        System.out.println("Departament " + departament.getNom());

        /*
        System.out.println("Seleccionam per codi empleat");
        test.llistaEmpleats(101,null ,null,null);

        System.out.println("Seleccionam per departament");
        test.llistaEmpleats(null,null ,30,null);
         */
        /*
        TestJdbc test = new TestJdbc(con);
        System.out.println("Seleccionam per nom");
        Collection<Empleat> resultat = test.llistaEmpleats(null,"E%" ,null,null);
        for (Empleat empleat : resultat) {
            System.out.println(empleat);
        }
        */


        //test.actualitzaDepartament(10,"Administració");
        //test.actualitzaDepartament(20,"Marqueting");


        //Integer empleats = test.procediment(30);
        //System.out.println("Empleats: " + empleats);

        //test.transaccions("AC_ACCOUNT", 0.15f);
        con.close();
    }
}
