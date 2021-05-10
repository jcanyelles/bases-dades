package es.cc.esliceu.db.dao.impl;

import es.cc.esliceu.db.dao.DepartamentDao;
import es.cc.esliceu.db.domain.Departament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class DepartamentDaoImpl implements DepartamentDao {
    private Connection connection;

    public DepartamentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Departament carrega(Integer codi) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement("select department_id, department_name, manager_id, location_id from departments where department_id = ? ");
            statement.setInt(1,codi);
            rs  = statement.executeQuery();
            if (rs.next()){
                Departament departament = new Departament();
                departament.setId(rs.getInt("department_id"));
                departament.setNom(rs.getString("department_name"));
                departament.setManagerId(rs.getInt("manager_id"));
                departament.setLocationId(rs.getString("location_id"));
                return departament;
            }
        } catch (Exception e){
            e.printStackTrace();
        }  finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throw new RuntimeException(throwables.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public void inserta(Departament departament) {

    }

    @Override
    public void modifica(Departament departament) {

    }

    @Override
    public void esborra(Departament departament) {

    }

    @Override
    public Collection<Departament> llistaTotsDepartaments() {
        return null;
    }

    @Override
    public Collection<Departament> llistaDepartamentsPerNom(String nom) {
        return null;
    }
}
