package sn.ept.git.dic2.vente_velo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "Marque")
@Entity
public class Marque implements Serializable {
    @Id
    private Integer Id;

    private String Nom;

    public Marque() {
    }

    public Marque(Integer id, String nom) {
        Id = id;
        Nom = nom;
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
        Marque marque = (Marque) o;
        return Objects.equals(Id, marque.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
