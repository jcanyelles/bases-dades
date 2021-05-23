package es.cc.esliceu.db.presentacio;

import java.util.Collection;

public class PantallaCataleg extends PantallaBase{
    Collection<String> cataleg;

    public PantallaCataleg(String titol) {
        super(titol, null);
    }

    public void setCataleg(Collection<String> cataleg) {
        this.cataleg = cataleg;
    }

    @Override
    public void render() {
        pintaCapcalera();
        int i=0;
        for (String s : cataleg) {
            System.out.println("(" + Color.YELLOW_BOLD + i + Color.RESET + ") " + s);
            i++;
        }
        pintaPeu();
    }

}
