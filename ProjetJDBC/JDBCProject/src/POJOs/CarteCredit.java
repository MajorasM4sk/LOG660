package POJOs;

import java.util.Objects;

public class CarteCredit {
    String typeCarte;
    String noCarte;
    public String moisExpiration;
    public String anneeExpiration;
    private String cvv;
    private String nomProprio;
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

    public void setMoisExpiration(String moisExpiration) {
        this.moisExpiration = moisExpiration;
    }

    public String getAnneeExpiration() {
        return anneeExpiration;
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
