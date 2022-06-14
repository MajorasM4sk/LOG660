package POJOs;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

public class Personne {
    public int idPersonne;
    String nom = "";
    public String dateNaissance = "";
    String lieuNaissance = "";
    String photo = "";
    String biographie = "";

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return idPersonne == personne.idPersonne && Objects.equals(nom, personne.nom) && Objects.equals(dateNaissance, personne.dateNaissance) && Objects.equals(lieuNaissance, personne.lieuNaissance) && Objects.equals(photo, personne.photo) && Objects.equals(biographie, personne.biographie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, nom, dateNaissance, lieuNaissance, photo, biographie);
    }
}
