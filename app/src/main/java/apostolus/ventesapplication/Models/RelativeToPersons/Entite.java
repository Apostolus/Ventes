package apostolus.ventesapplication.Models.RelativeToPersons;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;

import apostolus.ventesapplication.Models.ActionTest.Banque;
import apostolus.ventesapplication.Models.ActionTest.CompteBanque;
import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Acheter;
import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Vendre;
import apostolus.ventesapplication.Models.RelativeToThings.Article;
import apostolus.ventesapplication.Models.RelativeToThings.Commande;
import apostolus.ventesapplication.Models.RelativeToThings.Mobilier;


public abstract class Entite implements Acheter, Vendre {

	private static int entiteNumber = 0;
	private final int id;
	private String nom;
    protected Coordonnees coordonnees;
    private CompteBanque compteBanque = null;
    protected ArrayList<Article> articleArray;
    protected ArrayList<Commande> Otherscommandes;
    protected Commande panier;
    protected boolean hasAcount;


    protected Entite(String country, String departement, String city, String specificAdress, String email, String phoneNumber,String nom) {
        entiteNumber++;
    	this.coordonnees = new Coordonnees(country,departement,city,specificAdress,email,phoneNumber);
        this.articleArray = new ArrayList<>();
        this.nom = nom;
        this.id = entiteNumber;
        this.Otherscommandes = new ArrayList<>();
        this.hasAcount = false;
        this.panier = null;
    }
    
    
    public void createPanier(Entite vendeur) {
    	this.panier = new Commande(this,vendeur);
    }
    
    public boolean addPanier(Article article,int quantite){
        return panier.addCommande(article, quantite, panier.getDestinateur());
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
    
    public void createAcount(Banque banque) {
    	if(hasAcount) {
    		return;
    	}
    	else {
    		compteBanque = banque.createACount(this);
    		hasAcount = true;
    	}
    }
    
    @Override
    public boolean verifierDisponibilite(Article article, int quantite) {
    	return article.verifierDisponibilite(articleArray, quantite);
    }

    
    public void addArticleArray(Article article, int quantite) {
    	article.addArticleArray(articleArray, quantite);
    }
    
    protected void suppressToArticleArray(Article article, int quantite) {
    	article.suppressToArticleArray(articleArray, quantite);
    }
    

	public void payeCommande(Commande commande) {
 		compteBanque.paye(commande.getTotalPrice());
 	}

    public boolean payeMontant(double price) {
        return compteBanque.paye(price);
    }

    public boolean payeArticle(Article article, int quantite) {
        return compteBanque.paye(article, quantite);
    }
    
    public boolean verifierPaiement(double price) {
    	
    	int verifier = compteBanque.verifierPaiement(price);
    	if(verifier == 0 || verifier == 1) {
    		return true;
    	}
    	return false;
    }
    
    public void addToAccount(double montant) {
    	compteBanque.reflouer(montant);
    }
    
    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

	public String getNom() {
		return nom;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		StringWriter stringWriter = new StringWriter();
		stringWriter.write(", id : "+id);
		stringWriter.write(", nom : "+nom);
		stringWriter.write(", Argent sur compte : "+compteBanque.getSolde());
	
		return stringWriter.toString();
	}
	
	public void afficherPanier() {
		for(Article article : panier.getArticleCommande()) {
			System.out.println(article);
		}
	}
	
	public void afficherAllcommande() {
		for(Commande commande : Otherscommandes) {
			commande.afficherCommande();
		}
	}
	
	public void afficheAllArticleArrays() {
		if(articleArray.isEmpty()) {
			System.out.println("Le mec n'a pas des articles");
		}
		for(Article article : articleArray) {
			System.out.println(article);
			if(article instanceof Mobilier && this instanceof Particulier) {
				if(((Mobilier)article).isGarantissable()){
					Date garantie = ((Mobilier)article).garantieTimeRemaining();
					System.out.print("Le temps de garantie remaining : "+garantie.toString());
				}
			}
			//System.out.println(article);
		}
	}

    public static int getEntiteNumber() {
        return entiteNumber;
    }
}
