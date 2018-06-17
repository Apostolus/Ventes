package apostolus.ventesapplication.Models.ActionTest;

import apostolus.ventesapplication.Models.RelativeToThings.Article;

public class CompteBanque{
	
	private final int PAIEMENT_NON_AUTORISE = -1;
	private final int PAIEMENT_AUTORISE = 0;
	private final int PAIEMENT_AUTORISE_AVEC_DECOUVERT = 1;

	private static int nbCompte =0;
    private int id;
    private final String numero; // je vais à la fin personnaliser le numéro du compte bancaire en fonction de l'entité
    private boolean decouvert;
    private double montantDecouvertAutorise;  // la limite maximal autorise
    private double montantDecouvert;     //le montant courant de decouvert
    private double solde;
    
    /**
     * 
     * @param decouvert
     * @param autorisationDecouvert
     * @param e
     * @param f
     */

    public CompteBanque(boolean decouvert, double autorisationDecouvert, double e, double f) {
    	
        nbCompte++;
        this.numero = ""+id;
        this.decouvert = decouvert;
        this.montantDecouvert = e;
        this.montantDecouvertAutorise = autorisationDecouvert;
        this.solde = f;
    }
    
    public CompteBanque() {
		this(false,0.0,0.0,0.0);
	}

	/**
     * 
     * @param article
     * @return
     */

    //verifier si il faut payer

    // diminue le solde par le prix du Article a
    public boolean paye(Article article, int quantite) {   // le cas ou il a pas l'argent alors on renvoie un false tq dans la fonction acheter(Article) on appel la fonction paye
    	
    	return paye(article.getPrice()*quantite);
    	
    }
    
    public boolean paye(double price) {
    	
    	switch (verifierPaiement(price)) {
		case PAIEMENT_AUTORISE:
			solde-=price;
			return true;
			
		case PAIEMENT_AUTORISE_AVEC_DECOUVERT:
			montantDecouvert -= (price - solde);
			solde-=price;
			return true;

		case PAIEMENT_NON_AUTORISE:
			return false;

			default:
			    return false;
		}
    }
    
    /**
     * 
     * 
     * 
     * @param montant
     */

    //fonction pour remplir le compte, lorsqu'un acheteur achete a un vendeur par exemple
    public void reflouer(double montant) {
        if (montantDecouvert < montantDecouvertAutorise) {
            if (montantDecouvert + montant < montantDecouvertAutorise)
                montantDecouvert += montant;
            else {
                montantDecouvert += (montantDecouvertAutorise - montantDecouvert);
                montant -= (montantDecouvertAutorise - montantDecouvert);
                solde += montant;
            }
        } else {
            solde += montant;
        }
    }
    
    public int verifierPaiement(double price) {
    	
    	if(solde>price) {
    		return PAIEMENT_AUTORISE;
    	}
    	else if(decouvert && montantDecouvert>=price) {
    		return PAIEMENT_AUTORISE_AVEC_DECOUVERT;
    	}
    	
    	return PAIEMENT_NON_AUTORISE;
    }

    //GETTERS
    public boolean isDecouvert() {
        return decouvert;
    }

    public double getMontantDecouvert() {
        return montantDecouvert;
    }

    public double getSolde() {
        return solde;
    }

    public double getMontantDecouvertAutorise() {
        return montantDecouvertAutorise;
    }

    public int getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public static int nbCompte(){ return nbCompte; }
}