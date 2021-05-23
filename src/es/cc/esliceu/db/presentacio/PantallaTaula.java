package es.cc.esliceu.db.presentacio;

import es.cc.esliceu.db.dao.MetaDataDao;
import es.cc.esliceu.db.domain.Columna;

import java.util.Scanner;

public class PantallaTaula extends PantallaBase {
    private String baseDades;
    private String taula;
    public PantallaTaula(MetaDataDao dao, String titol, String baseDades, String taula) {
        super(dao, titol);
        this.baseDades = baseDades;
        this.taula = taula;
    }

    @Override
    public void render(Scanner scanner) {
        pintaCapcalera();
        for (Columna c : dao.llistaColumnes(baseDades,taula)){
            System.out.println(c.getNom() + " " + c.getTipus() + " " + c.getSize() + " " + c.getNullable());
        }
        pintaOpcions();
        String opcio = scanner.nextLine();
        while (!opcio.equalsIgnoreCase("x")){
            opcio = scanner.nextLine();
        }
    }
    
}
