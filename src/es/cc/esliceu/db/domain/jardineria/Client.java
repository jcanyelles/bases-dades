package es.cc.esliceu.db.domain.jardineria;

public class Client {
    private Integer codi;
    private String nom;
    private String nomContacte;
    private String llinatgeContacte;
    private String telefon;
    private String fax;
    private String adreca1;
    private String adreca2;
    private String ciutat;
    private String regio;
    private String pais;
    private String cp;
    private Integer codiEmpleat;
    private Float credit;
    private String email;

    public Client() {
    }

    public Client(Integer codi) {
        this.codi = codi;
    }

    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomContacte() {
        return nomContacte;
    }

    public void setNomContacte(String nomContacte) {
        this.nomContacte = nomContacte;
    }

    public String getLlinatgeContacte() {
        return llinatgeContacte;
    }

    public void setLlinatgeContacte(String llinatgeContacte) {
        this.llinatgeContacte = llinatgeContacte;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAdreca1() {
        return adreca1;
    }

    public void setAdreca1(String adreca1) {
        this.adreca1 = adreca1;
    }

    public String getAdreca2() {
        return adreca2;
    }

    public void setAdreca2(String adreca2) {
        this.adreca2 = adreca2;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public String getRegio() {
        return regio;
    }

    public void setRegio(String regio) {
        this.regio = regio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Integer getCodiEmpleat() {
        return codiEmpleat;
    }

    public void setCodiEmpleat(Integer codiEmpleat) {
        this.codiEmpleat = codiEmpleat;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "codi=" + codi +
                ", nom='" + nom + '\'' +
                ", nomContacte='" + nomContacte + '\'' +
                ", llinatgeContacte='" + llinatgeContacte + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
