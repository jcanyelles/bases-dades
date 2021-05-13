package es.cc.esliceu.db.dao.bookinfo;

import es.cc.esliceu.db.domain.bookinfo.Departament;

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
