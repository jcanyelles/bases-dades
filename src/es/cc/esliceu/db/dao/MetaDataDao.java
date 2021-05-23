package es.cc.esliceu.db.dao;

import es.cc.esliceu.db.domain.Columna;
import es.cc.esliceu.db.domain.Taula;

import java.util.Collection;

public interface MetaDataDao {

    Collection<String> llistaCataleg();
    Collection<Taula> llistaTaules(String baseDades);
    Collection<String> llistaProcediments(String baseDades);
    Collection<Columna> llistaColumnes(String baseDades, String taula);
}
