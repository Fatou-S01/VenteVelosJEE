package sn.ept.git.dic2.vente_velo.ServiceWeb;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.vente_velo.Facade.ProduitFacade;
import sn.ept.git.dic2.vente_velo.entities.Produit;

import java.util.List;

@Path("/produits")
public class ProduitRessource {
    @EJB
    private ProduitFacade produitFacade;

    //Voir la liste des produits
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Produit> getListeProduits(){
        return produitFacade.findAll();
    }

    //une méthode pour ajouter des produits
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Produit addProduit(Produit p){
        Produit tmp = produitFacade.find(p.getId()); //On cherche leproduit qui a le même id
        if(tmp != null){
            produitFacade.remove(tmp); //on le supprime pour créer un nouveau
        }
        produitFacade.create(p);
        return p;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Produit updateProduit(Produit p){
        produitFacade.edit(p);
        return p;
    }

    //Trouver un produit en fonction de son id
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findProduit(@PathParam("id") Integer produit_id){
        Produit produit = produitFacade.find(produit_id);
        if(produit == null){
            Reponse reponse = new Reponse("Le produit dont l'id est:"+produit_id+" est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(reponse).build();
        }
        return Response.status(Response.Status.OK).entity(produit).build();
    }

    //Supprimer un produit
    @DELETE
    @Path("{id_produit}")
    public Response deleteProduit(@PathParam("id_produit") Integer id_produit){
        Produit produit = produitFacade.find(id_produit);
        if(produit == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        produitFacade.remove(produit);
        Reponse reponse = new Reponse("Le produit" + produit.getNom()  + " a été supprimée");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }
}
