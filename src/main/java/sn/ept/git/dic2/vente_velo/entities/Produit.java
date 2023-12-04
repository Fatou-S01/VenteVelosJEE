package sn.ept.git.dic2.vente_velo.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;


@XmlRootElement(name = "Produit")
@Entity
public class Produit implements Serializable {
    @Id
    private Integer Id;

    private String Nom;

    private Integer Annee_Modele;

    private Double Prix_Depart;

    @JoinColumn(name = "marque_id")
    @OneToOne
    private Marque Marque_Id;

    @JoinColumn(name = "categorie_id")
    @OneToOne
    private Categorie Categorie_Id;

    public Produit() {
    }

    public Produit(Integer id, String nom, Integer annee_Modele, Double prix_Depart, Marque marque_Id, Categorie categorie_Id) {
        Id = id;
        Nom = nom;
        Annee_Modele = annee_Modele;
        Prix_Depart = prix_Depart;
        Marque_Id = marque_Id;
        Categorie_Id = categorie_Id;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public Integer getAnnee_Modele() {
        return Annee_Modele;
    }

    public void setAnnee_Modele(Integer annee_Modele) {
        Annee_Modele = annee_Modele;
    }

    public Double getPrix_Depart() {
        return Prix_Depart;
    }

    public void setPrix_Depart(Double prix_Depart) {
        Prix_Depart = prix_Depart;
    }

    public Marque getMarque_Id() {
        return Marque_Id;
    }

    public void setMarque_Id(Marque marque_Id) {
        Marque_Id = marque_Id;
    }

    public Categorie getCategorie_Id() {
        return Categorie_Id;
    }

    public void setCategorie_Id(Categorie categorie_Id) {
        Categorie_Id = categorie_Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return Objects.equals(Id, produit.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return "Produit{" +
                "Nom='" + Nom + '\'' +
                ", Annee_Modele=" + Annee_Modele +
                ", Prix_Depart=" + Prix_Depart +
                ", Marque_Id=" + Marque_Id +
                ", Categorie_Id=" + Categorie_Id +
                '}';
    }
}
