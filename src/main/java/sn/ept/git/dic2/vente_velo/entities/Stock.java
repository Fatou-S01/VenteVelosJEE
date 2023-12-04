package sn.ept.git.dic2.vente_velo.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "Stock")
@Entity
@NamedQuery(name = "Stock.findByProduitId", query = "SELECT s FROM Stock s WHERE s.Produit_Id =:produit_id")
public class Stock implements Serializable {
    @JoinColumn(name = "magasin_id")
    @Id
    @OneToOne
    private Magasin Magasin_Id;

    @JoinColumn(name = "produit_id")
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    private Produit Produit_Id;

    private Integer Quantite;

    public Stock() {
    }

    public Stock(Magasin magasin_Id, Produit produit_Id, Integer quantite) {
        Magasin_Id = magasin_Id;
        Produit_Id = produit_Id;
        Quantite = quantite;
    }

    public Magasin getMagasin_Id() {
        return Magasin_Id;
    }

    public void setMagasin_Id(Magasin magasin_Id) {
        Magasin_Id = magasin_Id;
    }

    public Produit getProduit_Id() {
        return Produit_Id;
    }

    public void setProduit_Id(Produit produit_Id) {
        Produit_Id = produit_Id;
    }

    public Integer getQuantite() {
        return Quantite;
    }

    public void setQuantite(Integer quantite) {
        Quantite = quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(Magasin_Id, stock.Magasin_Id) && Objects.equals(Produit_Id, stock.Produit_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Magasin_Id, Produit_Id);
    }
}
