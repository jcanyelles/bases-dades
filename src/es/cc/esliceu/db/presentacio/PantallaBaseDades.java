package es.cc.esliceu.db.presentacio;

import es.cc.esliceu.db.dao.MetaDataDao;
import es.cc.esliceu.db.domain.Taula;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PantallaBaseDades extends PantallaBase {
    List<Taula> taules;
    List<String> procedures;
    String baseDades;
    public PantallaBaseDades(MetaDataDao dao, String titol, String baseDades) {
        super(dao, titol);
        this.taules = new ArrayList<>();
        this.procedures = new ArrayList<>();
        this.baseDades = baseDades;
    }

    @Override
    public void render(Scanner scanner) {

        pintaDadesComunes();
        String opcio = scanner.nextLine();
        while (!opcio.equalsIgnoreCase("x")){
            if (!esOpcioValida(opcio)){
                errada("Opció incorrecte");
                pintaOpcions();
            } else  if (opcio.equalsIgnoreCase("t")) {
                System.out.println(Color.BLUE_BOLD + "Llista taules " + baseDades + Color.RESET);
                taules = new ArrayList<>(dao.llistaTaules(baseDades));
                int i=0;
                for (Taula s : taules) {
                    System.out.println("(" + Color.YELLOW_BOLD + i + Color.RESET + ") " + s.getNom() + "\t" + s.getCataleg() + "\t" + s.getTipus() + "\t" + s.getComentaris());
                    i++;
                }
                opcio = scanner.nextLine();
                int taulaSeleccionada = Integer.parseInt(opcio);
                pantallaTaula(scanner, taules.get(taulaSeleccionada).getNom());

            } else if (opcio.equalsIgnoreCase("p")){
                procedures = new ArrayList<>(dao.llistaProcediments(baseDades));
                for (String p : procedures) {
                    System.out.println(p);

                }
            }
            pintaDadesComunes();
            opcio = scanner.nextLine();
        }
    }

    private void pintaDadesComunes(){
        pintaCapcalera();
        pintaOpcions();
    }

    public void pantallaTaula(Scanner scanner,  String taula) {
        PantallaTaula patanllaTaula = new PantallaTaula(dao,"Taula " + baseDades + "." + taula,baseDades, taula);
        patanllaTaula.aferegixOpcio("x", "Sortir");
        patanllaTaula.render(scanner);
    }
}
