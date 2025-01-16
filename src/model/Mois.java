package model;

public class Mois {

    public Mois(int id, String nom){
        this.setId(id);
        this.setNom(nom);
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String nom;
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
