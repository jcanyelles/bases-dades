package es.cc.esliceu.db.dao;

import es.cc.esliceu.db.domain.Departament;

import java.sql.SQLException;
import java.util.Collection;

public interface DepartamentDao {
    Departament carrega(Integer codi);
    void inserta(Departament departament);
    void modifica(Departament departament);
    void esborra(Departament departament);

    //*** llistes
    Collection<Departament> llistaTotsDepartaments();
    Collection<Departament> llistaDepartamentsPerNom(String nom);
}
