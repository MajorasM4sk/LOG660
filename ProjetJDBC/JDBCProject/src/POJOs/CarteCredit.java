package POJOs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CARTE_CREDIT", schema = "EQUIPE112", catalog = "")
public class CarteCredit {
    @Basic
    @Column(name = "TYPE_CARTE", nullable = false, length = 30)
    String typeCarte;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NO_CARTE", nullable = false, length = 16)
    String noCarte;
    @Basic
    @Column(name = "MOIS_EXPIRATION", nullable = false, precision = 0)
    String moisExpiration;
    @Basic
    @Column(name = "ANNEE_EXPIRATION", nullable = false, precision = 0)
    String anneeExpiration;
    @Basic
    @Column(name = "CVV", nullable = false, length = 3)
    private String cvv;
    @Basic
    @Column(name = "NOM_PROPRIO", nullable = false, length = 50)
    private String nomProprio;
    @Basic
    @Column(name = "ID_CLIENT", nullable = false, precision = 0)
    private Integer idClient;

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }

    public String getNoCarte() {
        return noCarte;
    }

    public void setNoCarte(String noCarte) {
        this.noCarte = noCarte;
    }

    public String getMoisExpiration() {
        return moisExpiration;
    }

    public void setMoisExpiration(Byte moisExpiration) {
        this.moisExpiration = String.valueOf(moisExpiration);
    }

    public void setMoisExpiration(String moisExpiration) {
        this.moisExpiration = moisExpiration;
    }

    public String getAnneeExpiration() {
        return anneeExpiration;
    }

    public void setAnneeExpiration(Short anneeExpiration) {
        this.anneeExpiration = String.valueOf(anneeExpiration);
    }

    public void setAnneeExpiration(String anneeExpiration) {
        this.anneeExpiration = anneeExpiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNomProprio() {
        return nomProprio;
    }

    public void setNomProprio(String nomProprio) {
        this.nomProprio = nomProprio;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteCredit that = (CarteCredit) o;
        return Objects.equals(typeCarte, that.typeCarte) && Objects.equals(noCarte, that.noCarte) && Objects.equals(moisExpiration, that.moisExpiration) && Objects.equals(anneeExpiration, that.anneeExpiration) && Objects.equals(cvv, that.cvv) && Objects.equals(nomProprio, that.nomProprio) && Objects.equals(idClient, that.idClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCarte, typeCarte, anneeExpiration, moisExpiration, cvv, nomProprio, idClient);
    }
}
