package apostolus.ventesapplication.Models.RelativeToThings;


import java.util.ArrayList;

import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Time;
import apostolus.ventesapplication.Models.RelativeToPersons.Entite;
import apostolus.ventesapplication.Models.RelativeToPersons.Entrepot;

public class Commande{

	private static int nbCommande = 0;
    private final String numCommande;
    private Time dateDeCommande;
    private Entite destinataire;
    private Entite destinateur;
    public ArrayList<Article> articlesCommande;
    private Time timeOfCommande;

    public Commande(Entite destinataire,Entite destinateur) {
    	nbCommande++;
        this.dateDeCommande = new Time();
        this.destinataire = destinataire;
        this.destinateur = destinateur;
        this.articlesCommande = new ArrayList<>();
        this.timeOfCommande = new Time();
        this.numCommande = timeOfCommande.getStringDateFormat()+"-"+nbCommande+"-"+destinataire.getId();
    }

    /**
     *
     * cette fonction prend en paramètre l'article à ajouter à la commande ainsi que la quantité.
     * l'attribut quantité de la classe Article qui représentait le nombre d'exemplaire,
     * est utilisé dans cette classe comme la quantité commandée par le client.
     *
     * @param quantite
     * @param entite
     */
    
    public boolean addCommande(Article articleNew,int quantite,Entite entite) {
    	
    	Article article = articleNew.clone();
    	article.setQuantite(quantite);

    	if(!entite.verifierDisponibilite(article, quantite)) {
    		System.out.println("\n\n\n L'article n'est pas disponible");
    		return false;
    	}

    	if(articlesCommande.contains(article)) {
    		int index = articlesCommande.indexOf(article); // je prend l'index de l'article
        	Article articleTemp = articlesCommande.remove(index);

        	if(articleTemp instanceof Mobilier) {
        		((Mobilier)articleTemp).increaseQuantite(quantite);;
        	}
    		articlesCommande.add(articleTemp);
    		return true;

    	}
    	else {

    		if(article instanceof Mobilier) {
        		((Mobilier)article).setQuantite(quantite);;
        	}
    		articlesCommande.add(article);
    		return true;
    	}
    	
    }
    
    public boolean removeArticle(Article article) {
    	return articlesCommande.remove(article);
    	
    }

    public void LancerCommande(Entrepot entrepot){
    	entrepot.recevoirCommande(this);
    }

    public String getNumCommande() {
        return numCommande;
    }

    public Time getDateDeCommande() {
        return dateDeCommande;
    }

    public Entite getDestinataire() {
        return destinataire;
    }

    public ArrayList<Article> getArticleCommande() {
        return articlesCommande;
    }

    public Time getTimeOfCommande() {
        return timeOfCommande;
    }
    
    public double getTotalPrice() {
    	double price = 0.;
    	
    	for(Article article : articlesCommande) {
    		
    		int quantite = 1;
    		if(article instanceof Mobilier) {
    			quantite = ((Mobilier)article).getQuantite();
    		}
    		price+= (article.getPrice()*quantite);
    	}
    	return price;
    }
    
    public static int getNbCommande() {
		return nbCommande;
	}
    
    public Entite getDestinateur() {
		return destinateur;
	}
    
    public ArrayList<Article> getArticlesCommande() {
		return articlesCommande;
	}
    
    public void afficherCommande() {
    	System.out.println("\n\nL'acheteur : \n-------------------");
    	System.out.println(destinataire);
    	System.out.println("\n");
    	
    	for(Article article : articlesCommande) {
        	System.out.println(article);
    	}
    }


    
    
}