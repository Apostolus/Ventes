package apostolus.ventesapplication.Models.RelativeToPersons;

import java.util.ArrayList;

import apostolus.ventesapplication.Models.ActionTest.CompteBanque;
import apostolus.ventesapplication.Models.RelativeToThings.Article;
import apostolus.ventesapplication.Models.RelativeToThings.Commande;

public class Particulier extends Entite {

    private double note;
    private ArrayList<Article> acquis;

    public Particulier(String nom, Coordonnees cordonnees, CompteBanque compteBanque) {
        super(cordonnees.getCountry(),cordonnees.getDepartement(),cordonnees.getCity(),cordonnees.getSpecificAdress(),cordonnees.getEmail(),cordonnees.getPhoneNumber(),nom);
        this.acquis = new ArrayList<Article>();
        this.note=0;
    }

    public Particulier() {
    	this("nomParticulier", new Coordonnees(),new CompteBanque());
	}
    
    /**
     * 
     * @param article
     * @return
     */

    public Article retirerArticleVendu(Article article){
        int index = articleArray.indexOf(article);
        return articleArray.remove(index);
    }
    
    /**
     * 
     * @param articleAVendre
     */

    public void mettreEnVente(Article articleAVendre) {
        int index = acquis.indexOf(articleAVendre);
        articleArray.add(acquis.remove(index));
    }
    
    /**
     * 
     * 
     */

    public void noterVendeur(){ //on parcours les objets a vendre et on definit la note enfonction de l'etat de chaque objct
        double sommeEtat=0 ;
        int nbArticle=0;
        for(Article articleEnVente: articleArray){
            sommeEtat+= articleEnVente.getEtat();
            nbArticle++;
        }
        if(nbArticle != 0) this.note = (sommeEtat/nbArticle);
    }

    @Override
    public boolean verifierDisponibilite(Article article, int quantite) {
        return false;
    }

    @Override
    public void recevoirCommande(Commande commande) {

    }

    /**
	 * dans le cas où le particulier vend un article.
	 * la livraison se fait par entente.
	 * soit par courier postale, dans la mésure où l'on aura besoin de savoir les cordonnées de l'acheteur.
	 * soit l'acheteur pourra venir recuperer l'Article chez le vendeur.
	 * ou bien ils se fixent un point de rendez-vous.
	 * 
	 * Ceci est traité par le système de message qu'ils échangent.
	 * c'est à dire que nous on aura pas à gerer ces échanger.
	 * 
	 * cette méthode se contentera de notifier à l'acheteur la disponibilité de la commande.
	 * Ainsi pouront proceder comme ils l'entendent à l'echange.
	 * 
	 * Seulement, une fois la transaction effectué, 
	 * On voudra savoir si l'echange s'est bien passé.
	 * 
	 * Vu la complexité de la méthode, elle sera implémenté ultérieurement.
	 */
	
	@Override
	public void livrerCommande(Commande commande) {
		// TODO Auto-generated method stub
		
	}
	
	public double getNote() {
		return note;
	}

    @Override
    public void payeCommande(Commande commande) {

    }
}




