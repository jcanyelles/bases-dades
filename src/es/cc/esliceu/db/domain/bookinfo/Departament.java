package es.cc.esliceu.db.domain.bookinfo;

import java.util.ArrayList;
import java.util.Collection;

public class Departament {
    private Integer id;
    private String nom;
    private Empleat manager;
    private Integer locationId;
    private Collection<Empleat> empleats = new ArrayList<>();

    public Departament() {
    }

    public Departament(Integer id) {
        this.id = id;
    }

    public Departament(Integer id, String nom, Integer managerId, Integer locationId) {
        this.id = id;
        this.nom = nom;
        this.manager = new Empleat(managerId);
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

    public Empleat getManager() {
        return manager;
    }

    public void setManager(Empleat manager) {
        this.manager = manager;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Collection<Empleat> getEmpleats() {
        return empleats;
    }

    public void setEmpleats(Collection<Empleat> empleats) {
        this.empleats = empleats;
    }

    @Override
    public String toString() {
        return "Departament{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", manager=" + manager +
                ", locationId='" + locationId + '\'' +
                '}';
    }
}
