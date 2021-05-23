package es.cc.esliceu.db.presentacio;

import es.cc.esliceu.db.dao.MetaDataDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class PantallaBase {
    protected MetaDataDao dao;
    private String titol;
    protected Map<String, String> opcions;


    public PantallaBase(MetaDataDao dao, String titol) {
        this.dao = dao;
        this.titol = titol;
        this.opcions = new HashMap<>();
    }


    protected void pintaCapcalera(){
        System.out.println(Color.YELLOW_BOLD+ "**********************************" + Color.RESET);
        System.out.println(Color.YELLOW_BOLD+ "******* " + Color.BLUE_BOLD + titol+ Color.YELLOW_BOLD + " ******" + Color.RESET);
        System.out.println(Color.YELLOW_BOLD+ "**********************************" + Color.RESET);
    }

    public void aferegixOpcio(String tecla, String literal){
        this.opcions.put(tecla, literal);
    }

    public boolean esOpcioValida(String opcio){
        return opcions.containsKey(opcio);
    }

    public void  pintaOpcions(){
        for (Map.Entry<String, String> entrada : opcions.entrySet()) {
            System.out.println("(" + Color.YELLOW_BOLD + entrada.getKey() + Color.RESET + ") " + entrada.getValue());
        }
        System.out.println("Esculli una opció:");
    }

    public void errada(String errada){
        System.out.println(Color.RED_BOLD + errada + Color.RESET);
    }

    public abstract void  render(Scanner scanner);
}
