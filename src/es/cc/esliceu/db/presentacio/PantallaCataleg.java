package es.cc.esliceu.db.presentacio;

import es.cc.esliceu.db.dao.MetaDataDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PantallaCataleg extends PantallaBase{

    List<String> cataleg;
    public PantallaCataleg(MetaDataDao dao, String titol) {
        super(dao, titol);
        cataleg = new ArrayList<>();
    }

    public boolean esOpcioValida(String opcio){
        return opcions.containsKey(opcio) || opcio.matches("^[+-]?\\d+$");
    }

    @Override
    public void render(Scanner scanner) {
        pintaDadesComunes();
        String opcio = scanner.nextLine();
        while (!opcio.equalsIgnoreCase("x")){
            if (!esOpcioValida(opcio)){
                errada("Opció incorrecte");
            } else {
                int baseSeleccionada = Integer.parseInt(opcio);
                pantallaBaseDades(scanner, cataleg.get(baseSeleccionada));
            }
            pintaDadesComunes();
            opcio = scanner.nextLine();

        }
    }

    private void pintaDadesComunes(){
        cataleg = new ArrayList<>(dao.llistaCataleg());
        pintaCapcalera();
        int i=0;
        for (String s : cataleg) {
            System.out.println("(" + Color.YELLOW_BOLD + i + Color.RESET + ") " + s);
            i++;
        }
        pintaOpcions();
    }

    public void pantallaBaseDades(Scanner scanner, String baseDades) {
        PantallaBaseDades pantallaBaseDades = new PantallaBaseDades(dao,"Base de dades " + baseDades,baseDades);
        pantallaBaseDades.aferegixOpcio("t", "Taules");
        pantallaBaseDades.aferegixOpcio("p", "Procedures");
        pantallaBaseDades.aferegixOpcio("f", "Functions");
        pantallaBaseDades.aferegixOpcio("x", "Sortir");
        pantallaBaseDades.render(scanner);
    }

}
