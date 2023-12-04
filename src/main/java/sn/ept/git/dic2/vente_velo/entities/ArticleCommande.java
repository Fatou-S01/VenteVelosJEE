package sn.ept.git.dic2.vente_velo.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;


@XmlRootElement(name = "articleCommande")
@Entity
public class ArticleCommande implements Serializable {
    @Id
    private Integer ligne;

    @JoinColumn(name = "numero_commande")
    @Id
    @OneToOne
    private Commande numero_commande;

    @JoinColumn(name = "produit_id")
    @OneToOne
    private Produit produit_id;

    private Integer quantite;

    private Double prix_depart;

    private Double Remise;

    public ArticleCommande() {
    }

    public ArticleCommande(Integer ligne, Commande numero_commande, Integer quantite, Double prix_depart, Double remise) {
        this.ligne = ligne;
        this.numero_commande = numero_commande;
        this.quantite = quantite;
        this.prix_depart = prix_depart;
        Remise = remise;
    }

    public Integer getLigne() {
        return ligne;
    }

    public void setLigne(Integer ligne) {
        this.ligne = ligne;
    }

    public Commande getNumero_commande() {
        return numero_commande;
    }

    public void setNumero_commande(Commande numero_commande) {
        this.numero_commande = numero_commande;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getPrix_depart() {
        return prix_depart;
    }

    public void setPrix_depart(Double prix_depart) {
        this.prix_depart = prix_depart;
    }

    public Double getRemise() {
        return Remise;
    }

    public void setRemise(Double remise) {
        Remise = remise;
    }

    public Produit getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(Produit produit_id) {
        this.produit_id = produit_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleCommande that = (ArticleCommande) o;
        return Objects.equals(ligne, that.ligne) && Objects.equals(numero_commande, that.numero_commande);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ligne, numero_commande);
    }

    @Override
    public String toString() {
        return "ArticleCommmande{" +
                "ligne=" + ligne +
                ", numero_commande=" + numero_commande +
                ", quantite=" + quantite +
                ", prix_depart=" + prix_depart +
                ", Remise=" + Remise +
                '}';
    }
}





