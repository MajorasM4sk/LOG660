package POJOs;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pays {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODE_PAYS", nullable = false, length = 3)
    String codePays;
    @Basic
    @Column(name = "NOM", nullable = false, length = 60)
    String nom;

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pays pays = (Pays) o;
        return Objects.equals(codePays, pays.codePays) && Objects.equals(nom, pays.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codePays, nom);
    }
}
