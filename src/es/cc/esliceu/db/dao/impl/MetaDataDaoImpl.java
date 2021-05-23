package es.cc.esliceu.db.dao.impl;

import es.cc.esliceu.db.dao.MetaDataDao;
import es.cc.esliceu.db.domain.Columna;
import es.cc.esliceu.db.domain.Taula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

public class MetaDataDaoImpl implements MetaDataDao {
    private Connection connection;

    public MetaDataDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Collection<String> llistaCataleg()  {
        Collection<String> resultat = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = connection.getMetaData().getCatalogs();
            while(rs.next()){
                resultat.add(rs.getString(1));
            }
            return resultat;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
       return resultat;
    }

    @Override
    public Collection<Taula> llistaTaules(String baseDades) {
        Collection<Taula> resultat = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = connection.getMetaData().getTables(baseDades,null,null,null);
            while(rs.next()){
                Taula taula = new Taula();
                taula.setCataleg(rs.getString("TABLE_CAT"));
                taula.setEsquema(rs.getString("TABLE_SCHEM"));
                taula.setNom(rs.getString("TABLE_NAME"));
                taula.setTipus(rs.getString("TABLE_TYPE"));
                taula.setComentaris(rs.getString("REMARKS"));
                resultat.add(taula);
            }
            return resultat;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return resultat;
    }

    @Override
    public Collection<String> llistaProcediments(String baseDades) {
        Collection<String> resultat = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = connection.getMetaData().getProcedures(baseDades,null,null);
            while(rs.next()){
                resultat.add(rs.getString("PROCEDURE_NAME"));
            }
            return resultat;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return resultat;
    }

    @Override
    public Collection<Columna> llistaColumnes(String baseDades, String taula) {
        Collection<Columna> resultat = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = connection.getMetaData().getColumns(baseDades,null,taula, null);
            while(rs.next()){
                Columna columna = new Columna();
                columna.setNom(rs.getString("COLUMN_NAME"));
                columna.setTipus(rs.getString("TYPE_NAME"));
                columna.setNullable(rs.getString("IS_NULLABLE"));
                resultat.add(columna);
            }
            return resultat;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return resultat;
    }
}
