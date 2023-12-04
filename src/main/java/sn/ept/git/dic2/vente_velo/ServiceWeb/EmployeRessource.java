package sn.ept.git.dic2.vente_velo.ServiceWeb;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.vente_velo.Facade.EmployeFacade;
import sn.ept.git.dic2.vente_velo.entities.Employe;


import java.util.List;

@Path("/employee")
public class EmployeRessource {
    @EJB
    private EmployeFacade employeFacade;

    //Voir la liste des employés
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Employe> getListeEmployes(){
        return employeFacade.findAll();
    }

    //une méthode pour ajouter des employés
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Employe addEmploye(Employe p){
        Employe tmp = employeFacade.find(p.getId()); //On cherche l'employé qui a le même id
        if(tmp != null){
            employeFacade.remove(tmp); //on le supprime pour créer un nouveau
        }
        employeFacade.create(p);
        return p;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Employe updateEmploye(Employe p){
        employeFacade.edit(p);
        return p;
    }

    //Trouver un employe en fonction de son id
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findEmploye(@PathParam("id") Integer employe_id){
        Employe employe = employeFacade.find(employe_id);
        if(employe == null){
            Reponse reponse = new Reponse("L'employé dont l'id est:"+employe_id+" est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(reponse).build();
        }
        return Response.status(Response.Status.OK).entity(employe).build();
    }

    //Supprimer un employé
    @DELETE
    @Path("{id_employe}")
    public Response deleteEmploye(@PathParam("id_employe") Integer id_employe){
        Employe employe = employeFacade.find(id_employe);
        if(employe == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        employeFacade.remove(employe);
        Reponse reponse = new Reponse("L'employe " + employe.getNom()  + " a été supprimée");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }

}
