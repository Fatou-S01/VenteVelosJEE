package sn.ept.git.dic2.vente_velo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "Personne")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne implements Serializable {
    @Id
    private Integer Id;

    private String Prenom;

    private String Nom;

    private String Telephone;

    private String Email;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
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

    @Override
    public String toString() {
        return "Personne{" +
                "Prenom='" + Prenom + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(Id, personne.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
