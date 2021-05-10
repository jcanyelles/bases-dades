package es.cc.esliceu.db.domain;

public class Departament {
    private Integer id;
    private String nom;
    private Integer managerId;
    private String locationId;

    public Departament() {
    }

    public Departament(Integer id) {
        this.id = id;
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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
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
