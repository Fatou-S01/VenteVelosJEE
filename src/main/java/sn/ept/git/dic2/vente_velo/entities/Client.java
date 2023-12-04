package sn.ept.git.dic2.vente_velo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Client")
@Entity
public class Client extends Personne{

    private String Adresse;

    private String Ville;

    private String Etat;

    private String Code_Zip;

    public Client() {
    }

    public Client(String adresse, String ville, String etat, String code_Zip) {
        Adresse = adresse;
        Ville = ville;
        Etat = etat;
        Code_Zip = code_Zip;
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


}
