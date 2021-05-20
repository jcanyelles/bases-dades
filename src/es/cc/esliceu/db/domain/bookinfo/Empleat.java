package es.cc.esliceu.db.domain.bookinfo;

import java.util.Date;

public class Empleat {
    private Integer id;
    private String nom;
    private String llinatge;
    private String email;
    private Date naixement;
    private Departament departament;
    private String jobId;


    public Empleat() {}

    public Empleat(Integer id) {
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

    public String getLlinatge() {
        return llinatge;
    }

    public void setLlinatge(String llinatge) {
        this.llinatge = llinatge;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNaixement() {
        return naixement;
    }

    public void setNaixement(Date naixement) {
        this.naixement = naixement;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "Empleat{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", llinatge='" + llinatge + '\'' +
                ", email='" + email + '\'' +
                ", naixement=" + naixement +
                ", departament=" + departament +
                ", jobId='" + jobId + '\'' +
                '}';
    }
}
