package apostolus.ventesapplication.Models.RelativeToPersons;

import java.util.ArrayList;

import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Acheter;
import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Vendre;
import apostolus.ventesapplication.Models.RelativeToThings.Article;
import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Commande;


public abstract class Entite implements Acheter, Vendre {

	private static int entiteNumber = 0;
	private final int id;
	private String type;
    protected Coordonnees coordonnees;
    protected ArrayList<Article> articleArray;
    protected ArrayList<Commande> Otherscommandes;
    protected Commande panier;
    protected boolean hasAcount;


    protected Entite(String country, String departement, String city, String specificAdress, String email, String phoneNumber,String type) {
        entiteNumber++;
    	this.coordonnees = new Coordonnees(country,departement,city,specificAdress,email,phoneNumber);
        this.articleArray = new ArrayList<>();
        this.type = type;
        this.id = entiteNumber;
        this.Otherscommandes = new ArrayList<>();
        this.hasAcount = false;
        this.panier = null;
    }
    
    
    public void createPanier(Entite vendeur) {
    	this.panier = new Commande(this,vendeur);
    }
    
    public boolean addPanier(Article article,int quantite){
        return panier.addCommande(article, panier.getDestinateur());
	}
    
    /**
     * 
     * TODO --On pourra rappeler à la suite les méthodes qui mettent en communication les deux paticuliers, 
     * pour finaliser la vente.
     * pour le moment, achèter à un particulier, reviens à payer le montant d'un article précis dont l'existence
     * est assurée.
     * 
     * cette méthode revoie un booléen, si c'est vraie, le particulier à qui on achète peut procèder à la livraison.
     * le temps de livraison est déterminé par les deux (entente entre les deux personnes).
     * @return
     */
    
	@Override
    public boolean achete() {
		
		if(!hasAcount) {
			System.out.println("\n\n Le mec il n'a pas de compte\n\n");
			return false;
		}
    	
		Entite vendeur = panier.getDestinateur();
    	double price = panier.getTotalPrice();
    	if(this.verifierPaiement(price)) {
    		
    			this.payeMontant(price);
    			vendeur.addToAccount(price);
    			vendeur.recevoirCommande(panier);
    			
    			System.out.println("\n\n le tout est ok\n\n");
    			return true;
    	}
    	
    	System.out.println("\n\n le mec il ne peut pas payer\n\n");
    	return false;
    }

    @Override
    public void recevoirCommande(Commande commande) {
    	Otherscommandes.add(commande);
    }
    
    @Override
    public boolean verifierDisponibilite(Article article, int quantite) {
  		//TODO
		return false;
    }

    
    public void addArticleArray(Article article, int quantite) {
    	//TODO
    }
    
    protected void suppressToArticleArray(Article article, int quantite) {
    	//TODO
    }
    

	public void payeCommande(Commande commande) {
 		//TODO
 	}

    public boolean payeMontant(double price) {
        //TODO
		return false;
    }
    
    public boolean verifierPaiement(double price) {
		//TODO
    	return false;
    }
    
    public void addToAccount(double montant) {
		//TODO
    }
    
    public Coordonnees getCoordonnees() { return coordonnees; }

	public String getType() {
		return type;
	}

	public int getId() {
		return id;
	}


    public static int getEntiteNumber() {
        return entiteNumber;
    }
}
