package sn.ept.git.dic2.vente_velo.ServiceWeb;


import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.vente_velo.Facade.MagasinFacade;
import sn.ept.git.dic2.vente_velo.Facade.MarqueFacade;
import sn.ept.git.dic2.vente_velo.entities.Magasin;
import sn.ept.git.dic2.vente_velo.entities.Marque;

import java.util.List;

@Path("/magasins")
public class MagasinRessource {
    @EJB
    private MagasinFacade magasinFacade;

    //Voir la liste des magasins
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Magasin> getListeMagasins(){
        return magasinFacade.findAll();
    }

    //une méthode pour ajouter des magasins
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Magasin addMagasin(Magasin p){
        Magasin tmp = magasinFacade.find(p.getId()); //On cherche le magasin qui a le même id
        if(tmp != null){
            magasinFacade.remove(tmp); //on le supprime pour créer un nouveau
        }
       magasinFacade.create(p);
        return p;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Magasin updateProduit(Magasin p){
        magasinFacade.edit(p);
        return p;
    }

    //Trouver un magasin en fonction de son id
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findMagasin(@PathParam("id") Integer magasin_id){
        Magasin magasin = magasinFacade.find(magasin_id);
        if(magasin == null){
            Reponse reponse = new Reponse("Le magasin dont l'id est:"+magasin_id+" est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(reponse).build();
        }
        return Response.status(Response.Status.OK).entity(magasin).build();
    }

    //Supprimer un magasin
    @DELETE
    @Path("{id_magasin}")
    public Response deleteMarque(@PathParam("id_magasin") Integer id_magasin){
        Magasin magasin = magasinFacade.find(id_magasin);
        if(magasin == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        magasinFacade.remove(magasin);
        Reponse reponse = new Reponse("La marque " + magasin.getNom()  + " a été supprimée");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }

}
