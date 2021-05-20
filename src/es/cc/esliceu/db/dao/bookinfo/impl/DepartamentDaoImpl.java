package es.cc.esliceu.db.dao.bookinfo.impl;

import es.cc.esliceu.db.dao.bookinfo.DepartamentDao;
import es.cc.esliceu.db.dao.bookinfo.EmpleatDao;
import es.cc.esliceu.db.domain.bookinfo.Departament;
import es.cc.esliceu.db.domain.bookinfo.Empleat;

import java.sql.*;
import java.util.ArrayList;
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
                departament.setManager(new Empleat(rs.getInt("manager_id")));
                departament.setLocationId(null);
                Object location = rs.getObject("location_id");
                if (location!=null){
                    departament.setLocationId(rs.getInt("location_id"));
                }
                return departament;
            }
        } catch (Exception e){
            e.printStackTrace();
        }  finally {
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
        return null;
    }

    @Override
    public void inserta(Departament departament) {
        String sql = "insert into departments (department_id, department_name, manager_id, location_id ) values (? , ? , ? ,?) ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, departament.getId());
            statement.setString(2, departament.getNom());
            if (departament.getManager()!=null){
                statement.setInt(3, departament.getManager().getId());
            } else {
                statement.setNull(3, Types.INTEGER);
            }
            if (departament.getLocationId()!=null){
                statement.setInt(4, departament.getLocationId());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throw new RuntimeException(throwables.getMessage());
                }
            }
        }

    }

    @Override
    public void modifica(Departament departament) {
        String sql = "update departments set department_name =? , manager_id = ?, location_id = ? where department_id = ?   ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(4, departament.getId());
            statement.setString(1, departament.getNom());
            statement.setInt(2, departament.getManager().getId());
            if (departament.getLocationId()!=null){
                statement.setInt(3, departament.getLocationId());
            } else {
                statement.setNull(3, Types.INTEGER);
            }
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throw new RuntimeException(throwables.getMessage());
                }
            }
        }

    }

    @Override
    public void esborra(Departament departament) {
        String sql = "delete from departments where department_id = ? ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, departament.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throw new RuntimeException(throwables.getMessage());
                }
            }
        }
    }

    @Override
    public Collection<Departament> llistaTotsDepartaments() {
        Collection<Departament> resultat = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement("select department_id, department_name, manager_id, location_id from departments order by department_id ");
            rs = statement.executeQuery();
            while (rs.next()){
                Departament departament = new Departament();
                departament.setId(rs.getInt("department_id"));
                departament.setNom(rs.getString("department_name"));
                departament.setManager(new Empleat(rs.getInt("manager_id")));
                departament.setLocationId(null);
                Object location = rs.getObject("location_id");
                if (location!=null){
                    departament.setLocationId(rs.getInt("location_id"));
                }
                resultat.add(departament);
            }
            return resultat;

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
        return null;
    }

    @Override
    public Collection<Departament> llistaDepartamentsPerNom(String nom) {
        return null;
    }
}
