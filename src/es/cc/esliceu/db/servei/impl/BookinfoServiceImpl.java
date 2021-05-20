package es.cc.esliceu.db.servei.impl;

import es.cc.esliceu.db.dao.bookinfo.DepartamentDao;
import es.cc.esliceu.db.dao.bookinfo.EmpleatDao;
import es.cc.esliceu.db.dao.bookinfo.impl.DepartamentDaoImpl;
import es.cc.esliceu.db.dao.bookinfo.impl.EmpleatDaoImpl;
import es.cc.esliceu.db.domain.bookinfo.Departament;
import es.cc.esliceu.db.domain.bookinfo.Empleat;
import es.cc.esliceu.db.servei.BookinfoService;

import java.sql.Connection;
import java.util.Collection;

public class BookinfoServiceImpl implements BookinfoService {
    private Connection connection;
    private EmpleatDao empleatDao;
    private DepartamentDao departamentDao;
    public BookinfoServiceImpl(Connection connection) {
        this.connection = connection;
        this.empleatDao = new EmpleatDaoImpl(connection);
        this.departamentDao = new DepartamentDaoImpl(connection);
    }

    public Empleat carregaEmpleatMinim(Integer id) {
        return empleatDao.carrega(id);
    }
    @Override
    public Empleat carregaEmpleat(Integer id) {
        Empleat empleat = empleatDao.carrega(id);
        if (empleat.getDepartament()!=null){
            empleat.setDepartament(departamentDao.carrega(empleat.getDepartament().getId()));
        }
        return empleat;
    }

    @Override
    public Collection<Empleat> llistaTotsEmpleats() {
        return empleatDao.llistaTotsEmpleat();
    }

    @Override
    public Departament carregaDepartament(Integer id) {
        Departament departament = departamentDao.carrega(id);
        if (departament.getManager()!=null){
            departament.setManager(empleatDao.carrega(departament.getManager().getId()));
        }
        Collection<Empleat> empleats = empleatDao.llistaEmpleatsPerDepartament(id);
        departament.setEmpleats(empleats);
        return departament;
    }
}
