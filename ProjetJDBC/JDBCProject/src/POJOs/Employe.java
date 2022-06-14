package POJOs;

import java.sql.Date;
import java.util.Objects;

public class Employe {
    private Integer matricule;
    private String courriel;
    private String motDePasse;
    private Integer telephone;
    private String nom;
    private String prenom;
    private String adresseCivique;
    private Date dateNaissance;

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresseCivique() {
        return adresseCivique;
    }

    public void setAdresseCivique(String adresseCivique) {
        this.adresseCivique = adresseCivique;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return Objects.equals(matricule, employe.matricule) && Objects.equals(courriel, employe.courriel) && Objects.equals(motDePasse, employe.motDePasse) && Objects.equals(telephone, employe.telephone) && Objects.equals(nom, employe.nom) && Objects.equals(prenom, employe.prenom) && Objects.equals(adresseCivique, employe.adresseCivique) && Objects.equals(dateNaissance, employe.dateNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule, courriel, motDePasse, telephone, nom, prenom, adresseCivique, dateNaissance);
    }
}
