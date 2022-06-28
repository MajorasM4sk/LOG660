package POJOs;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "T_CLIENT", schema = "EQUIPE112", catalog = "")
public class TClient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_CLIENT", nullable = false, precision = 0)
    private Integer idClient;
    @Basic
    @Column(name = "COURRIEL", nullable = false, length = 50)
    private String courriel;
    @Basic
    @Column(name = "MOT_DE_PASSE", nullable = false, length = 20)
    private String motDePasse;
    @Basic
    @Column(name = "TELEPHONE", nullable = false, precision = 0)
    private Integer telephone;
    @Basic
    @Column(name = "NOM", nullable = false, length = 25)
    private String nom;
    @Basic
    @Column(name = "PRENOM", nullable = false, length = 25)
    private String prenom;
    @Basic
    @Column(name = "DATE_NAISSANCE", nullable = false)
    private Date dateNaissance;
    @Basic
    @Column(name = "CODE_FORFAIT", nullable = true, length = 1)
    private String codeForfait;

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCodeForfait() {
        return codeForfait;
    }

    public void setCodeForfait(String codeForfait) {
        this.codeForfait = codeForfait;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TClient tClient = (TClient) o;
        return Objects.equals(idClient, tClient.idClient) && Objects.equals(courriel, tClient.courriel) && Objects.equals(motDePasse, tClient.motDePasse) && Objects.equals(telephone, tClient.telephone) && Objects.equals(nom, tClient.nom) && Objects.equals(prenom, tClient.prenom) && Objects.equals(dateNaissance, tClient.dateNaissance) && Objects.equals(codeForfait, tClient.codeForfait);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, courriel, motDePasse, telephone, nom, prenom, dateNaissance, codeForfait);
    }
}
