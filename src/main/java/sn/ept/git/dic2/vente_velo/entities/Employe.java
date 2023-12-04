package sn.ept.git.dic2.vente_velo.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employe")
@Entity
public class Employe extends Personne{
    private Integer Actif;

    @JoinColumn(name = "Magasin_Id")
    @OneToOne
    private Magasin Magasin_Id;

    private Integer Manager_id;

    public Employe() {
    }

    public Integer getActif() {
        return Actif;
    }

    public void setActif(Integer actif) {
        Actif = actif;
    }

    public Magasin getMagasin_Id() {
        return Magasin_Id;
    }

    public void setMagasin_Id(Magasin magasin_Id) {
        Magasin_Id = magasin_Id;
    }

    public Integer getManager_id() {
        return Manager_id;
    }

    public void setManager_id(Integer manager_id) {
        Manager_id = manager_id;
    }

    public Employe(Integer actif, Magasin magasin_Id, Integer manager_id) {
        Actif = actif;
        Magasin_Id = magasin_Id;
        Manager_id = manager_id;
    }
}


