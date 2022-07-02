package POJOs;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Personne {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_PERSONNE", nullable = false, precision = 0)
    BigInteger idPersonne;
    @Basic
    @Column(name = "NOM", nullable = false, length = 50)
    String nom = "";
    @Basic
    @Column(name = "DATE_NAISSANCE", nullable = true)
    Date dateNaissance = new Date(0,0,0);
    @Basic
    @Column(name = "LIEU_NAISSANCE", nullable = true, length = 200)
    String lieuNaissance = "";
    @Basic
    @Column(name = "PHOTO", nullable = true, length = 200)
    String photo = "";
    @Basic
    @Column(name = "BIOGRAPHIE", nullable = true)
    String biographie = "";

    public BigInteger getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(BigInteger idPersonne) {
        this.idPersonne = idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = BigInteger.valueOf(idPersonne);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
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
