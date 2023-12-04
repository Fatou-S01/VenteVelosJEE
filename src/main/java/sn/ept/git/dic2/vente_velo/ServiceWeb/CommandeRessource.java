package sn.ept.git.dic2.vente_velo.ServiceWeb;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.vente_velo.Facade.CommandeFacade;
import sn.ept.git.dic2.vente_velo.Facade.EmployeFacade;
import sn.ept.git.dic2.vente_velo.entities.Commande;
import sn.ept.git.dic2.vente_velo.entities.Employe;

import java.util.List;

@Path("/commandes")
public class CommandeRessource {
    @EJB
    private CommandeFacade commandeFacade;

    //Voir la liste des commandes
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Commande> getListeCommande(){
        return commandeFacade.findAll();
    }

    //une méthode pour ajouter des commandes
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Commande addCommande(Commande p){
        Commande tmp = commandeFacade.find(p.getNumero_commande()); //On cherche le commande qui a le même numéro de commande
        if(tmp != null){
            commandeFacade.remove(tmp); //on le supprime pour créer un nouveau
        }
        commandeFacade.create(p);
        return p;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Commande updateCommande(Commande p){
        commandeFacade.edit(p);
        return p;
    }

    //Trouver une commande en fonction de son id
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findCommande(@PathParam("id") Integer commande_id){
        Commande commande = commandeFacade.find(commande_id);
        if(commande == null){
            Reponse reponse = new Reponse("La commande dont l'id est:"+commande_id+" est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(reponse).build();
        }
        return Response.status(Response.Status.OK).entity(commande).build();
    }

    //Supprimer une commande
    @DELETE
    @Path("{id_commande}")
    public Response deleteCommande(@PathParam("id_commande") Integer id_commande){
        Commande commande = commandeFacade.find(id_commande);
        if(commande == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        commandeFacade.remove(commande);
        Reponse reponse = new Reponse("La commande " + commande.getNumero_commande()  + " a été supprimée");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }

}
