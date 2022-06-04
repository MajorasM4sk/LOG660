package POJOs;

public class Client {
    public int idClient;
    public String prenom;
    public String nom;
    public String courriel;
    public String telephone;
    public String dateNaissance;
    public String adresse;
    public String ville;
    public String province;
    public String codePostal;
    public CarteCredit carteCredit = new CarteCredit();
    public String motDePasse;
    public Forfait forfait;
}
