package es.cc.esliceu.db.presentacio;

public abstract class PantallaBase {
    private String titol;
    private String peu;


    public PantallaBase(String titol, String peu) {
        this.titol = titol;
        this.peu = peu;
    }

    protected void pintaCapcalera(){
        System.out.println(Color.YELLOW_BOLD+ "*****************" + Color.RESET);
        System.out.println(Color.YELLOW_BOLD+ "******* " + Color.BLUE_BOLD + titol+ Color.YELLOW_BOLD + " ******" + Color.RESET);
        System.out.println(Color.YELLOW_BOLD+ "*****************" + Color.RESET);
    }
    protected void pintaPeu(){
        if (peu!=null){
            System.out.println(peu);
        }
    }
    public abstract void  render();
}
