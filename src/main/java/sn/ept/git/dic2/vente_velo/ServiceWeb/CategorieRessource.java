package sn.ept.git.dic2.vente_velo.ServiceWeb;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Liste des catégories",
            description = "Cet endpoint est utilisé pour obtenir l'ensemble des catégories de produits disponible"
    )
    public Response getCategorieList() {
        List<Categorie> categories = categorieFacade.findAll();
        if (categories == null ) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Reponse("Un problème s'est produit lors du chargement")).build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(categories)
                .build();
    }
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
    @Operation(
            summary = "Trouver les produits",
            description = "Cet endpoint est utilisé pour obtenir l'ensemble des produits appartenant à une catégorie",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "L'ensemble des produits ont été retourné"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Catégorie correspondant à l'id indiqué est introuvable",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples =  {
                                                    @ExampleObject(name="Catégorie introuvable")
                                            }
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Une erreur s'est produite lors de la récupération des produits"
                    )
            }
    )
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
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Suppression de catégorie",
            description = "Cet endpoint est utilisé pour supprimer les catégorie de la base de données",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "La catégorie a été supprimée de la base de données"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Une erreur s'est produit lors de la suppression de la catégorie"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "La catégorie correspondant à l'id est introuvable",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples =  {
                                                    @ExampleObject(name="Categorie introuvable")
                                            }
                                    )
                            }
                    )
            }
    )
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
