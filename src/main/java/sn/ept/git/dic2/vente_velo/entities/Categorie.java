package sn.ept.git.dic2.vente_velo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "categorie")
@Entity
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String Nom;

    public Categorie() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return Objects.equals(Id, categorie.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "Nom='" + Nom + '\'' +
                '}';
    }
}
