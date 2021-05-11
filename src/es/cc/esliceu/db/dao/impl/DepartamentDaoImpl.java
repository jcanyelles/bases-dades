package es.cc.esliceu.db.dao.impl;

import es.cc.esliceu.db.dao.DepartamentDao;
import es.cc.esliceu.db.domain.Departament;

import java.sql.*;
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
            if (departament.getManagerId()!=null){
                statement.setInt(3, departament.getManagerId());
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
        return null;
    }

    @Override
    public Collection<Departament> llistaDepartamentsPerNom(String nom) {
        return null;
    }
}
