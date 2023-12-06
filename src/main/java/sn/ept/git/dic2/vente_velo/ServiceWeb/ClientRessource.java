package sn.ept.git.dic2.vente_velo.ServiceWeb;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.vente_velo.Facade.ClientFacade;
import sn.ept.git.dic2.vente_velo.Facade.EmployeFacade;
import sn.ept.git.dic2.vente_velo.entities.Client;
import sn.ept.git.dic2.vente_velo.entities.Employe;

import java.util.List;

@Path("/clients")
public class ClientRessource {
    @EJB
    private ClientFacade clientFacade;

    //Voir la liste des client
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Client> getListeClient(){
        return clientFacade.findAll();
    }

    //une méthode pour ajouter des clients
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Inscription ou Modification des clients",
            description = "Cet endpoint est utilisé pour enregistrer les clients dans la base de données après leur inscription ou lorsque des modifications sont apportées à un client",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Le client a été enregistré à la base de données"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Une erreur s'est produit lors de l'enregistrement du client"
                    )
            }
    )
    public Client addClient(Client p){
        Client tmp = clientFacade.find(p.getId()); //On cherche le client qui a le même id
        if(tmp != null){
            clientFacade.remove(tmp); //on le supprime pour créer un nouveau
        }
        clientFacade.create(p);
        return p;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Client updateClient(Client p){
        clientFacade.edit(p);
        return p;
    }

    //Trouver un client en fonction de son id
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findClient(@PathParam("id") Integer client_id){
        Client client = clientFacade.find(client_id);
        if(client == null){
            Reponse reponse = new Reponse("Le client dont l'id est:"+client_id+" est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(reponse).build();
        }
        return Response.status(Response.Status.OK).entity(client).build();
    }

    //Supprimer un client
    @Path("{id_client}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Suppression de client",
            description = "Cet endpoint est utilisé pour supprimer les clients de la base de données",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Le client a été supprimé de la base de données"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Une erreur s'est produit lors de la suppression du client"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Le client correspondant à l'id est introuvable",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples =  {
                                                    @ExampleObject(name="Client introuvable")
                                            }
                                    )
                            }
                    )
            }
    )

    public Response deleteClient(@PathParam("id_client") Integer id_client){
        Client client = clientFacade.find(id_client);
        if(client == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        clientFacade.remove(client);
        Reponse reponse = new Reponse("Le client " + client.getNom()  + " a été supprimée");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }

}
