package sn.ept.git.dic2.vente_velo.Bean;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.vente_velo.Facade.ArticleCommandeFacade;
import sn.ept.git.dic2.vente_velo.Facade.CommandeFacade;
import sn.ept.git.dic2.vente_velo.Facade.ProduitFacade;
import sn.ept.git.dic2.vente_velo.entities.ArticleCommande;
import sn.ept.git.dic2.vente_velo.entities.Commande;
import sn.ept.git.dic2.vente_velo.entities.Produit;

import java.io.Serializable;
import java.util.List;

@Named("articleCommandeBean")
@ViewScoped
public class ArticleCommandeBean implements Serializable {
    @EJB
    private ArticleCommandeFacade articleCommandeFacade;

    private List<ArticleCommande> articleCommandes;

    private ArticleCommande selectedArticleCommande;

    private List<ArticleCommande> selectedArticleCommandes;

    private Integer selectedCommandeId;
    private Integer selectedProduitId;


    @EJB
    private ProduitFacade produitFacade;

    @EJB
    private CommandeFacade commandeFacade;

    private ArticleCommande articleCommande = new ArticleCommande();

    public List<Produit> getAllProduits() {
        return produitFacade.findAll();
    }

    public List<Commande> getAllCommandes() {
        return commandeFacade.findAll();
    }


    public List<ArticleCommande> getArticleCommandes() {
        if(articleCommandes == null){
            articleCommandes = articleCommandeFacade.findAll();
        }
        return articleCommandes;
    }

    public ArticleCommande getArticleCommande() {
        return articleCommande;
    }

    public void setArticleCommande(ArticleCommande articleCommande) {
        this.articleCommande = articleCommande;
    }

    public void setArticleCommandes(List<ArticleCommande> articleCommandes) {
        this.articleCommandes = articleCommandes;
    }

    public ArticleCommande getSelectedArticleCommande() {
        return selectedArticleCommande;
    }

    public void setSelectedArticleCommande(ArticleCommande selectedArticleCommande) {
        this.selectedArticleCommande = selectedArticleCommande;
    }

    public Integer getSelectedCommandeId() {
        return selectedCommandeId;
    }

    public void setSelectedCommandeId(Integer selectedCommandeId) {
        this.selectedCommandeId = selectedCommandeId;
    }

    public Integer getSelectedProduitId() {
        return selectedProduitId;
    }

    public void setSelectedProduitId(Integer selectedProduitId) {
        this.selectedProduitId = selectedProduitId;
    }

    public void save() {
        if(selectedCommandeId != null){
            articleCommande.setNumero_commande(commandeFacade.find(selectedCommandeId));
        }
        if(selectedProduitId != null){
            articleCommande.setProduit_id(produitFacade.find(selectedProduitId));
        }

        System.out.println("Saving the articleCommande : " + articleCommande);
        this.articleCommandeFacade.create(articleCommande);

        this.articleCommande = new ArticleCommande();
    }

    public void delete(ArticleCommande articleCommande) {
        this.articleCommandeFacade.remove(articleCommande);
        this.refreshDisplay();
    }
    private void refreshDisplay() {
        // update the displayed rows
        articleCommandes = articleCommandeFacade.findAll();
        this.selectedArticleCommande = null;
    }
    public void undo() {
        this.refreshDisplay();
    }
    public void edit() {
        if(selectedCommandeId != null){
            this.selectedArticleCommande.setNumero_commande(commandeFacade.find(selectedCommandeId));
        }
        if(selectedProduitId != null){
            this.selectedArticleCommande.setProduit_id(produitFacade.find(selectedProduitId));
        }

        System.out.println("Editing the articleCommande : " + this.selectedArticleCommande);
        this.articleCommandeFacade.edit(this.selectedArticleCommande);
        this.refreshDisplay();
    }
}
