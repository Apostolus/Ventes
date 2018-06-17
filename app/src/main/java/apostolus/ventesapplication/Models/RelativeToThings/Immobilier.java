package apostolus.ventesapplication.Models.RelativeToThings;

import java.util.ArrayList;

import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Time;

public class Immobilier extends Article {

    private double Totalesurface;



    public Immobilier(String type, String description, Time time, double price, double surface) {

        super(type, "Immbolier", description, time,price, null);
        this.Totalesurface = surface;
    }
    
    public Immobilier(Immobilier mobilier) {
    	this(mobilier.type,mobilier.description,mobilier.time,mobilier.price,10.0);
    }
    
    @Override
	public Immobilier clone() {
    	// TODO Auto-generated method stub
    	return new Immobilier(this);
    }
    
    public Immobilier() {
    	this("type","description",new Time(),100.0,0);
    }
    
    public double getTotalesurface() {
		return Totalesurface;
	}
    
    public void setTotalesurface(double totalesurface) {
		Totalesurface = totalesurface;
	}

	@Override
	public void addArticleArray(ArrayList<Article> articleArray, int quantite) {
		System.out.println("On est ici");
		Immobilier immobilier = this.clone();
		immobilier.setQuantite(1);
		articleArray.add(immobilier);
	}

	@Override
	public void suppressToArticleArray(ArrayList<Article> articleArray, int quantite) {
		if(articleArray.contains(this)) {
			articleArray.remove(this);
		}
		else {
			System.out.println("Le produit n'existe pas dans L'array");
		}
		
	}

}
