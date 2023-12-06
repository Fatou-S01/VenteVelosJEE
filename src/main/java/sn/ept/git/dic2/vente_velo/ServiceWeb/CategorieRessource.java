package sn.ept.git.dic2.vente_velo.ServiceWeb;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.vente_velo.Facade.CategorieFacade;
import sn.ept.git.dic2.vente_velo.entities.Categorie;

import java.util.List;

@Path("/categories")
public class CategorieRessource {
    @EJB
    private CategorieFacade categorieFacade;

    //Voir la liste des catégories
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Categorie> getListeCategories(){
        return categorieFacade.findAll();
    }

    //une méthode pour ajouter des catégories
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Categorie addCategorie(Categorie c){
        Categorie tmp = categorieFacade.find(c.getId()); //On cherche le catégorie qui a le même id
        if(tmp != null){
            categorieFacade.remove(tmp); //on le supprime pour créer un nouveau
        }
        categorieFacade.create(c);
        return c;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Categorie updateCategorie(Categorie c){
        categorieFacade.edit(c);
        return c;
    }

    //Trouver un élève en fonction de son numéro
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findEleve(@PathParam("id") Integer categorie_id){
        Categorie categorie = categorieFacade.find(categorie_id);
        if(categorie == null){
            Reponse reponse = new Reponse("La catégorie dont l'id est:"+categorie_id+" est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(reponse).build();
        }
        return Response.status(Response.Status.OK).entity(categorie).build();
    }

    //Supprimer une categorie
    @DELETE
    @Path("{id_categorie}")
    public Response deleteCategorie(@PathParam("id_categorie") Integer id_categorie){
        Categorie categorie = categorieFacade.find(id_categorie);
        if(categorie == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        categorieFacade.remove(categorie);
        Reponse reponse = new Reponse("La catégorie" + categorie.getNom()  + " a été supprimée");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }
}
