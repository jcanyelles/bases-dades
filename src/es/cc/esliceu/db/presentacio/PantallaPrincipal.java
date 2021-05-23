package es.cc.esliceu.db.presentacio;

import es.cc.esliceu.db.dao.MetaDataDao;

import java.util.Scanner;

public class PantallaPrincipal extends PantallaBase {

    public PantallaPrincipal(MetaDataDao dao, String titol) {
        super(dao, titol);
    }

    @Override
    public void render(Scanner scanner) {
        pinta();
        String opcio = scanner.nextLine();
        while (!opcio.equalsIgnoreCase("x")){
            if (!esOpcioValida(opcio)){
                errada("Opció incorrecte");
                pintaOpcions();
            } else if (opcio.equalsIgnoreCase("b")){
                pantallaCataleg(scanner);
            }
            pinta();
            opcio = scanner.nextLine();
        }
    }
    private void pinta(){
        pintaCapcalera();
        pintaOpcions();
    }
    public void pantallaCataleg(Scanner scanner) {
        PantallaCataleg pantallaCataleg = new PantallaCataleg(dao,"Catàleg");
        pantallaCataleg.aferegixOpcio("x", "Sortir");
        pantallaCataleg.render(scanner);

    }
}
