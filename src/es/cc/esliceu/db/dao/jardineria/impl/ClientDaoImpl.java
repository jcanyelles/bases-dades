package es.cc.esliceu.db.dao.jardineria.impl;

import es.cc.esliceu.db.dao.jardineria.ClientDao;
import es.cc.esliceu.db.domain.jardineria.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ClientDaoImpl implements ClientDao {
    private final Connection connection;

    public ClientDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Client carrega(Integer codi) {
        Collection<Client> clients =  llistaClients(codi,null ,null ,null);
        if (!clients.isEmpty()){
            return clients.iterator().next();
        }
        return null;
    }

    @Override
    public Collection<Client> llistaClients(Integer codi, String nom, String contacte, String telefon) {
        String sql = "select codigo_cliente,nombre_cliente,nombre_contacto,apellido_contacto," +
                     "       telefono,fax,linea_direccion1,linea_direccion2, ciudad,region,pais," +
                     "       codigo_postal,codigo_empleado_rep_ventas,limite_credito, email" +
                     " from cliente " +
                     "where 1=1 ";
        int params = 1;
        int posicioCodi = 0; int posicioNom = 0; int posicioContacte = 0; int posicioTelfon = 0;
        if (codi!=null){
            sql += " and codigo_cliente = ? ";
            posicioCodi = params;
            params++;
        }
        if (nom!=null){
            sql += " and nombre_cliente  like ( ? ) ";
            posicioNom = params;
            params++;
        }
        if (contacte!=null){
            sql += " and nombre_contacto  like ( ? ) ";
            posicioContacte = params;
            params++;
        }
        if (telefon!=null){
            sql += " and telefono  like ( ? ) ";
            posicioTelfon = params;
        }
        sql += "order by nombre_cliente ";
        Collection<Client> resultat = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(sql);
            if (posicioCodi!=0){
                statement.setInt(posicioCodi, codi);
            }
            if (posicioNom!=0){
                statement.setString(posicioNom, nom);
            }
            if (posicioContacte!=0){
                statement.setString(posicioContacte, contacte);
            }
            if (posicioTelfon!=0){
                statement.setString(posicioTelfon, telefon);
            }
            rs = statement.executeQuery();
            while (rs.next()){
                Client client = new Client();
                client.setCodi(rs.getInt("codigo_cliente"));
                client.setNom(rs.getString("nombre_cliente"));
                client.setNomContacte(rs.getString("nombre_contacto"));
                client.setTelefon(rs.getString("telefono"));
                resultat.add(client);
            }
            return resultat;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throw  new RuntimeException(throwables.getMessage());
                }
            }
            if (statement!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throw  new RuntimeException(throwables.getMessage());
                }
            }
        }
        return null;
    }
}
