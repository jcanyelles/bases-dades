package es.cc.esliceu.db.servei;

import es.cc.esliceu.db.domain.bookinfo.Departament;
import es.cc.esliceu.db.domain.bookinfo.Empleat;

import java.util.Collection;

public interface BookinfoService {
    Empleat carregaEmpleatMinim(Integer id);

    Empleat carregaEmpleat(Integer id);
    Collection<Empleat> llistaTotsEmpleats();

    Departament  carregaDepartament(Integer id);


}
