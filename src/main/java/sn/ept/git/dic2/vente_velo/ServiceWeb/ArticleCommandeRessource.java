package sn.ept.git.dic2.vente_velo.ServiceWeb;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.vente_velo.Facade.ArticleCommandeFacade;
import sn.ept.git.dic2.vente_velo.entities.ArticleCommande;

import java.util.List;

@Path("/articles_commandes")
public class ArticleCommandeRessource {
    @EJB
    private ArticleCommandeFacade articleCommandeFacade;

    //Voir la liste des articleCommandes
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<ArticleCommande> getListeArticleCommande(){
        return articleCommandeFacade.findAll();
    }

    //une méthode pour ajouter des articleCommandes
    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public ArticleCommande addArticleCommande(ArticleCommande p){
        ArticleCommande tmp = articleCommandeFacade.findTwoId(p.getNumero_commande(),p.getLigne()); //On cherche l'articleCommande qui a le même id
        if(tmp != null){
            articleCommandeFacade.remove(tmp); //on le supprime pour créer un nouveau
        }
        articleCommandeFacade.create(p);
        return p;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public ArticleCommande updateArticleCommande(ArticleCommande p){
        articleCommandeFacade.edit(p);
        return p;
    }

    //Trouver un article Commande en fonction de son id
    @GET
    @Path("{ligne}/{numeroCommande}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findArticleCommande(@PathParam("ligne") Integer ligne,@PathParam("numeroCommande") Integer numeroCommande){
        ArticleCommande articleCommande = articleCommandeFacade.findTwoId(ligne,numeroCommande);
        if(articleCommande == null){
            Reponse reponse = new Reponse("L'article Commande dont la ligne est:"+ ligne +"et le numéro de commande"+numeroCommande+" est introuvable");
            return Response.status(Response.Status.NOT_FOUND).entity(reponse).build();
        }
        return Response.status(Response.Status.OK).entity(articleCommande).build();
    }

    //Supprimer un article Commande
    @DELETE
    @Path("{ligne}/{numeroCommande}")
    public Response deleteArticleCommande(@PathParam("ligne") Integer ligne,@PathParam("numeroCommande") Integer numeroCommande){
        ArticleCommande articleCommande = articleCommandeFacade.findTwoId(ligne,numeroCommande);
        if(articleCommande == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        articleCommandeFacade.remove(articleCommande);
        Reponse reponse = new Reponse("L'article Commande dont la ligne est:"+ ligne +"et le numéro de commande"+numeroCommande+" est supprimée");
        return Response.status(Response.Status.OK).entity(reponse).build();
    }

}
