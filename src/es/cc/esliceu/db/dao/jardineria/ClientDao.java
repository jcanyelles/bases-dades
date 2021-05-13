package es.cc.esliceu.db.dao.jardineria;

import es.cc.esliceu.db.domain.jardineria.Client;

import java.util.Collection;

public interface ClientDao {
    Client carrega(Integer codi);
    Collection<Client> llistaClients(Integer codi,String nom, String contacte, String telefon);
}
