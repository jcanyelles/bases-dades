package es.cc.esliceu.db.dao.bookinfo;


import es.cc.esliceu.db.domain.bookinfo.Empleat;

import java.util.Collection;

public interface EmpleatDao {
    Empleat carrega(Integer id);


    Collection<Empleat> llistaTotsEmpleat();
    Collection<Empleat> llistaEmpleatsPerDepartament(Integer departament);

}
