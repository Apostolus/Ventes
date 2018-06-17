package apostolus.ventesapplication.Models.RelativeToPersons;
import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Commande;

public abstract class Professionnel extends Entite {
	
     public Professionnel(String country, String departement, String city, String specificAdress, String email, String phoneNumber, String nom){
        super(country,departement,city,specificAdress,email,phoneNumber,nom);
     }

 	public void livrerCommande(Commande commande) {
    	//TODO
 	}
     
     public void livreAll() {
    	 for(Commande commande : Otherscommandes) {
    		 this.livrerCommande(commande);
    	 }
     }
}