package es.cc.esliceu.db.domain.bookinfo;

public class Departament {
    private Integer id;
    private String nom;
    private Integer managerId;
    private Empleat manager;
    private Integer locationId;

    public Departament() {
    }

    public Departament(Integer id) {
        this.id = id;
    }

    public Departament(Integer id, String nom, Integer managerId, Integer locationId) {
        this.id = id;
        this.nom = nom;
        this.managerId = managerId;
        this.locationId = locationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Departament{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", managerId=" + managerId +
                ", locationId='" + locationId + '\'' +
                '}';
    }
}
