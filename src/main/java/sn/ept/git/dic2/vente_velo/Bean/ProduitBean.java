package sn.ept.git.dic2.vente_velo.Bean;


import jakarta.ejb.EJB;
import jakarta.inject.Named;
import sn.ept.git.dic2.vente_velo.Facade.*;
import sn.ept.git.dic2.vente_velo.entities.*;

import java.util.List;

@Named("produitBean")
public class ProduitBean {
    @EJB
    private ProduitFacade produitFacade;

    private List<Produit> produits;

    private Produit selectedProduit;

    private List<Produit> selectedProduits;

    private Integer selectedMarqueId;
    private Integer selectedCategorieId;


    @EJB
    private CategorieFacade categorieFacade;

    @EJB
    private MarqueFacade marqueFacade;

    private Produit produit = new Produit();

    public List<Marque> getAllMarques() {
        return marqueFacade.findAll();
    }

    public List<Categorie> getAllCategories() {
        return categorieFacade.findAll();
    }



    public List<Produit> getProduits() {
        if(produits == null){
            this.produits = produitFacade.findAll();
        }
        return produits;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Produit getSelectedProduit() {
        return selectedProduit;
    }

    public void setSelectedProduit(Produit selectedProduit) {
        this.selectedProduit = selectedProduit;
    }

    public Integer getSelectedMarqueId() {
        return selectedMarqueId;
    }

    public void setSelectedMarqueId(Integer selectedMarqueId) {
        this.selectedMarqueId = selectedMarqueId;
    }

    public Integer getSelectedCategorieId() {
        return selectedCategorieId;
    }

    public void setSelectedCategorieId(Integer selectedCategorieId) {
        this.selectedCategorieId = selectedCategorieId;
    }

    public void save() {
        if(selectedMarqueId != null){
            produit.setMarque_Id(marqueFacade.find(selectedMarqueId));
        }
        if(selectedCategorieId != null){
            produit.setCategorie_Id(categorieFacade.find(selectedCategorieId));
        }

        System.out.println("Saving the product : " + produit);
        this.produitFacade.create(produit);

        this.produit = new Produit();
    }

    public void delete(Produit produit) {
        this.produitFacade.remove(produit);
        this.refreshDisplay();
    }
    private void refreshDisplay() {
        // update the displayed rows
        produits = produitFacade.findAll();
        this.selectedProduit = null;
    }
    public void undo() {
        this.refreshDisplay();
    }
    public void edit() {
        if(selectedMarqueId != null){
            this.selectedProduit.setMarque_Id(marqueFacade.find(selectedMarqueId));
        }
        if(selectedCategorieId != null){
            this.selectedProduit.setCategorie_Id(categorieFacade.find(selectedCategorieId));
        }

        System.out.println("Editing the product : " + this.selectedProduit);
        this.produitFacade.edit(this.selectedProduit);
        this.refreshDisplay();
    }

}
