package es.cc.esliceu.db.dao.bookinfo.impl;

import es.cc.esliceu.db.dao.bookinfo.DepartamentDao;
import es.cc.esliceu.db.dao.bookinfo.EmpleatDao;
import es.cc.esliceu.db.domain.bookinfo.Departament;
import es.cc.esliceu.db.domain.bookinfo.Empleat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmpleatDaoImpl implements EmpleatDao {
    private Connection connection;

    public EmpleatDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Empleat carrega(Integer id) {
        Collection<Empleat> resultat = llistaEmpleats(id, null, null, null);
        if (!resultat.isEmpty()){
            return resultat.iterator().next();
        }
        return null;
    }

    @Override
    public Collection<Empleat> llistaTotsEmpleat() {
        return llistaEmpleats(null,null,null,null);
    }

    @Override
    public Collection<Empleat> llistaEmpleatsPerDepartament(Integer departament) {
        return llistaEmpleats(null,departament,null,null);
    }


    private Collection<Empleat> llistaEmpleats(Integer codiEmpleat, Integer codiDepartament, String nom, String job) {
        Collection<Empleat> resultat = new ArrayList<>();
        int params = 1;
        Map<String,Integer> mapaParametres = new HashMap<>();
        String sql = "select employee_id, first_name, last_name, email, hire_date, job_id, department_id  " +
                      "from employees " +
                      "where 1=1 ";
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
                Empleat empleat = new Empleat();
                empleat.setId(rs.getInt("employee_id"));
                empleat.setNom(rs.getString("first_name"));
                empleat.setLlinatge(rs.getString("last_name"));
                empleat.setJobId(rs.getString("job_id"));
                empleat.setNaixement(rs.getDate("hire_date"));
                Object departament = rs.getObject("department_id");
                if (departament!=null){
                    empleat.setDepartament(new Departament(rs.getInt("department_id")));
                }
                resultat.add(empleat);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throw new RuntimeException(throwables.getMessage());
                }
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throw new RuntimeException(throwables.getMessage());
                }
            }
        }
        return resultat;
    }
}
