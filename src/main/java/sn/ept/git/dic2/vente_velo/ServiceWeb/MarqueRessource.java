package sn.ept.git.dic2.vente_velo.ServiceWeb;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.vente_velo.Facade.MarqueFacade;
import sn.ept.git.dic2.vente_velo.entities.Marque;


import java.util.List;

@Path("/marques")
public class MarqueRessource {
    @EJB
    private MarqueFacade marqueFacade;

    //Voir la liste des marques
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Marque> getListeMarques(){
        return marqueFacade.findAll();
    }

    //une méthode pour ajouter des marques
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Marque addMarque(Marque p){
        Marque tmp = marqueFacade.find(p.getId()); //On cherche la marque qui a le même id
        if(tmp != null){
            marqueFacade.remove(tmp); //on le supprime pour créer un nouveau
        }
        marqueFacade.create(p);
        return p;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Marque updateProduit(Marque p){
        marqueFacade.edit(p);
        return p;
    }

    //Trouver une marque en fonction de son id
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findMarque(@PathParam("id") Integer marque_id){
        Marque marque = marqueFacade.find(marque_id);
        if(marque == null){
            Reponse reponse = new Reponse("La marque dont l'id est:"+marque_id+" est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(reponse).build();
        }
        return Response.status(Response.Status.OK).entity(marque).build();
    }

    //Supprimer une marque
    @DELETE
    @Path("{id_marque}")
    public Response deleteMarque(@PathParam("id_marque") Integer id_marque){
        Marque marque = marqueFacade.find(id_marque);
        if(marque == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        marqueFacade.remove(marque);
        Reponse reponse = new Reponse("La marque " + marque.getNom()  + " a été supprimée");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }

}
