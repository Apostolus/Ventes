package apostolus.ventesapplication.Models.RelativeToPersons;
import apostolus.ventesapplication.Models.RelativeToThings.Article;
import apostolus.ventesapplication.Models.RelativeToThings.Commande;

public abstract class Professionnel extends Entite {
	
     public Professionnel(String country, String departement, String city, String specificAdress, String email, String phoneNumber,boolean decouvert, int montantDecouvertAutorise, int montantDecouvert, int solde, String nom){
        super(country,departement,city,specificAdress,email,phoneNumber,nom);
     }
     

 	public void livrerCommande(Commande commande) {
    	 System.out.println("\n\n\nLivraison");
    	 Entite client = commande.getDestinataire();
    	 if((commande.articlesCommande).isEmpty()) {
    		 System.out.println("L'article des commandes est vide");
    		 return;
    	 }
    	 for (Article article : commande.articlesCommande) {
    		 client.addArticleArray(article, article.getQuantite());
    		 this.suppressToArticleArray(article, article.getQuantite());
    	 }
 	}
     
     public void livreAll() {
    	 for(Commande commande : Otherscommandes) {
    		 this.livrerCommande(commande);
    	 }
     }
}