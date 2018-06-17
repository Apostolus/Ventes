package apostolus.ventesapplication.Models.ActionsBtwPersonsThings;

import apostolus.ventesapplication.Models.RelativeToThings.Article;
import apostolus.ventesapplication.Models.RelativeToThings.Commande;

public interface Vendre {
    
	boolean verifierDisponibilite(Article article, int quantite);
	void recevoirCommande(Commande commande);
	void livrerCommande(Commande commande);
}
