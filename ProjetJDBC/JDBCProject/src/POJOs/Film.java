package POJOs;

import java.util.LinkedList;
import java.util.List;

public class Film {
    public int codeFilm;
    public String titre;
    public int annee;
    public int duree;
    public String langue;
    public String resume;
    public String lienAffiche;
    // public List<String> bandeAnnonce = new LinkedList<String>();
    public List<Role> roles = new LinkedList<Role>();
    public List<Personne> realisateurs = new LinkedList<Personne>();
    public List<Genre> genres = new LinkedList<Genre>();
    public List<Pays> pays = new LinkedList<Pays>();
}
