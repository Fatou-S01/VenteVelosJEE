package sn.ept.git.dic2.vente_velo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "Magasin")
@Entity
public class Magasin implements Serializable {
    @Id
    private Integer Id;

    private String Nom;

    private String Telephone;

    private String Email;

    private String Adresse;

    private String Ville;

    private String Etat;

    private String Code_Zip;

    public Magasin() {
    }

    public Magasin(Integer id, String nom, String telephone, String email, String adresse, String ville, String etat, String code_Zip) {
        Id = id;
        Nom = nom;
        Telephone = telephone;
        Email = email;
        Adresse = adresse;
        Ville = ville;
        Etat = etat;
        Code_Zip = code_Zip;
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

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }

    public String getCode_Zip() {
        return Code_Zip;
    }

    public void setCode_Zip(String code_Zip) {
        Code_Zip = code_Zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magasin magasin = (Magasin) o;
        return Objects.equals(Id, magasin.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
