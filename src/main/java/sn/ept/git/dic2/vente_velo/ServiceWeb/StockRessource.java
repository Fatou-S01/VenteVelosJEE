package sn.ept.git.dic2.vente_velo.ServiceWeb;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.vente_velo.Facade.StockFacade;
import sn.ept.git.dic2.vente_velo.entities.Stock;

import java.util.List;

@Path("/stocks")
public class StockRessource {
    @EJB
    private StockFacade stockFacade;

    //Voir la liste des stocks
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Stock> getListeStock(){
        return stockFacade.findAll();
    }

    //une méthode pour ajouter des stocks
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Stock addStock(Stock p){
        Stock tmp = stockFacade.findTwoId(p.getProduit_Id(),p.getMagasin_Id()); //On cherche l'articleCommande qui a le même id
        if(tmp != null){
            stockFacade.remove(tmp); //on le supprime pour créer un nouveau
        }
        stockFacade.create(p);
        return p;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Stock updateStock(Stock p){
        stockFacade.edit(p);
        return p;
    }

    //Trouver un article Commande en fonction de son id magasin et produit
    @GET
    @Path("{magasin_id}/{produit_id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findStock(@PathParam("magasin_id") Integer magasin_id, @PathParam("produit_id") Integer produit_id){
        Stock stock = stockFacade.findTwoId(magasin_id,produit_id);
        if(stock == null){
            Reponse reponse = new Reponse("Le stock dont le magasin_id est:"+ magasin_id +"et le produit_id"+produit_id+" est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(reponse).build();
        }
        return Response.status(Response.Status.OK).entity(stock).build();
    }

    //Supprimer un stock
    @DELETE
    @Path("{magasin_id}/{produit_id}")
    public Response deleteStock(@PathParam("magasin_id") Integer magasin_id,@PathParam("produit_id") Integer produit_id){
        Stock stock = stockFacade.findTwoId(magasin_id,produit_id);
        if(stock == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        stockFacade.remove(stock);
        Reponse reponse = new Reponse("Le stock dont le magasin_id est:"+ magasin_id  +"et le produit_id"+produit_id+" est supprimée");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }
}
