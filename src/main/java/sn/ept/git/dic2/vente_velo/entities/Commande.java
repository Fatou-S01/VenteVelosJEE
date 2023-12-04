package sn.ept.git.dic2.vente_velo.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@XmlRootElement(name = "Commande")
@Entity
public class Commande implements Serializable {
    @Id
    private Integer numero_commande;

    private Integer Statut;

    private Date Date_Commande;

    private Date Date_Livraison_Voulue;

    private Date Date_Livraison;

    @JoinColumn(name = "client_id")
    @OneToOne
    private Client Client_Id;

    @JoinColumn(name = "magasin_id")
    @OneToOne
    private Magasin Magasin_Id;

    @JoinColumn(name = "vendeur_id")
    @OneToOne
    private Employe Vendeur_Id;

    public Commande() {
    }

    public Integer getNumero_commande() {
        return numero_commande;
    }

    public void setNumero_commande(Integer numero_commande) {
        this.numero_commande = numero_commande;
    }

    public Integer getStatut() {
        return Statut;
    }

    public void setStatut(Integer statut) {
        Statut = statut;
    }

    public Date getDate_Commande() {
        return Date_Commande;
    }

    public void setDate_Commande(Date date_Commande) {
        Date_Commande = date_Commande;
    }

    public Date getDate_Livraison_Voulue() {
        return Date_Livraison_Voulue;
    }

    public void setDate_Livraison_Voulue(Date date_Livraison_Voulue) {
        Date_Livraison_Voulue = date_Livraison_Voulue;
    }

    public Date getDate_Livraison() {
        return Date_Livraison;
    }

    public void setDate_Livraison(Date date_Livraison) {
        Date_Livraison = date_Livraison;
    }

    public Client getClient_Id() {
        return Client_Id;
    }

    public void setClient_Id(Client client_Id) {
        Client_Id = client_Id;
    }

    public Magasin getMagasin_Id() {
        return Magasin_Id;
    }

    public void setMagasin_Id(Magasin magasin_Id) {
        Magasin_Id = magasin_Id;
    }

    public Employe getVendeur_Id() {
        return Vendeur_Id;
    }

    public void setVendeur_Id(Employe vendeur_Id) {
        Vendeur_Id = vendeur_Id;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "numero_commande=" + numero_commande +
                ", Statut=" + Statut +
                ", Date_Commande=" + Date_Commande +
                ", Date_Livraison_Voulue=" + Date_Livraison_Voulue +
                ", Date_Livraison=" + Date_Livraison +
                ", Client_Id=" + Client_Id +
                ", Magasin_Id=" + Magasin_Id +
                ", Vendeur_Id=" + Vendeur_Id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return Objects.equals(numero_commande, commande.numero_commande);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero_commande);
    }
}
