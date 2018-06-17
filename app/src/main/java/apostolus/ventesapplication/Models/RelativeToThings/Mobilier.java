package apostolus.ventesapplication.Models.RelativeToThings;

import java.util.ArrayList;
import java.util.Date;

import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Garantissable;
import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Time;

public class Mobilier extends Article implements Garantissable {
	private String marque;
	private Time dureeGarantie = null;
	private double priceGarantiePerMonth;
	private boolean isGarantissable = false;

    public Mobilier(String type, String description, Time timeOfPublish, double price, int quantite, String nomMobilier, String marque, double priceGarantiePerMonth) {
        super(type, "Mobilier", description, timeOfPublish, price, nomMobilier);
        super.quantite = quantite;
        this.marque = marque;
        this.priceGarantiePerMonth = priceGarantiePerMonth;
    }
    
    /**
     * constructeur de test avec les valeur par défaut.
     * 
     */
    
    public Mobilier() {
    	this("type","description",new Time(),100.0,1, null, "marque", 0.0);
    }
    
     /**
      * Constructeur par copie.
      * 
      * @param mobilier
      */
    public Mobilier(Mobilier mobilier) {
    	this(mobilier.type,mobilier.description,mobilier.time,mobilier.price,mobilier.quantite, mobilier.getNomArticle(), mobilier.marque, 0.0);
    }
    
    @Override
	public Mobilier clone() {
    	Mobilier mobilier = new Mobilier(this);
    	mobilier.makeArticleGarantissable(dureeGarantie.getDate());
    	return mobilier;
    }
   
    @Override
	public void addArticleArray(ArrayList<Article> articleArray, int quantite) {
    	
    	Mobilier mobilier = this.clone();
    	
    	if(this.isGarantissable) {
    		mobilier.setTime(new Time());
    		mobilier.setQuantite(quantite);
    		articleArray.add(mobilier);
    	}
    	
    	else if(articleArray.contains(mobilier)) {
    		int index_art = articleArray.indexOf(mobilier);
			mobilier =(Mobilier)articleArray.remove(index_art);
    	
    		if(this instanceof Mobilier) {
    			mobilier.decreaseQuantite(quantite);
    		}
			
    		mobilier.setTime(new Time());
    		articleArray.add(mobilier);
    	}
    	else {
    		this.setQuantite(quantite);
    		this.setTime(new Time());
    		articleArray.add(this);
    	}
    
    }
    
    /**
     * sur cette méthode, on suppose qu'on a déjà testé que la garantie est encore valide.
     * elle retourne la date de fin de garantie.
     * 
     * @return
     */
    
    @Override
	public Date garantieTimeRemaining() {
    	
    	return (this.time).addToCurrent(dureeGarantie.getDate());
    }
	
	
	
	/**
	 * cette méthode teste si la garantie est encore valide, elle retourne true si ele l'est,
	 * false sinon.
	 * 
	 * elle pourra servir dans les cas on a besoin de savoir quand est ce que la garantie de l'article en question va se terminer.
	 * 
	 * @return
	 */
    
    @Override
    public boolean garantieIsAvailable() {
    	
    	Date timeNow = new Date();
    	Date tillBuy = (this.getTime()).addToCurrent(dureeGarantie.getDate());
    	
    	if(timeNow.compareTo(tillBuy) == -1) {
    		return true;
    	}
    	return false;
    }

	@Override
	public void setPriceGarantiePerMonth(double priceGarantiePerMonth) {
		this.priceGarantiePerMonth = priceGarantiePerMonth;
		
	}

	@Override
	public double getPriceGarantiePerMonth() {
		return priceGarantiePerMonth;
	}

	@Override
	public void setDureeGarantie(Date date) {
		dureeGarantie.setDate(date);
	}

	@Override
	public Time getDureeGarantie() {
		return dureeGarantie;
	}

	@Override
	public boolean isGarantissable() {
		return isGarantissable;
	}

	@Override
	public void makeArticleGarantissable(Date dureeGarantie) {
		this.dureeGarantie = new Time(1,0,0);
		this.isGarantissable = true;
	}

	@Override
	public void suppressToArticleArray(ArrayList<Article> articleArray, int quantite) {
		
		if(articleArray.contains(this)) {
			int index = articleArray.indexOf(this);
			Mobilier mobilier = (Mobilier) articleArray.remove(index);
			mobilier.decreaseQuantite(quantite);
			articleArray.add(mobilier);
		}
		else {
			System.out.println("Le produit n'existe pas dans L'array");
		}
	}
    
}
