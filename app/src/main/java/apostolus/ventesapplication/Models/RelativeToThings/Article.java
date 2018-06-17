package apostolus.ventesapplication.Models.RelativeToThings;

import java.util.ArrayList;

import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Time;

public abstract class Article implements Cloneable{
	
	/**
	 * 
	 * l'attribut time : Time, si l'article est chez le vendeur,
	 * cet attribut désigne le temps de mis en ligne du produit,
	 * si il est chez l'acheteur il désigne le temps(date d'achat).
	 *
     *
	 */

    protected final int id;
    private static int nbArticle = 0;
    protected String type;
    protected String categorie;
    protected String description;
    protected Time time;
    protected double price;
    protected int etat;
    protected String numeroDeSerie;
    protected int quantite;
    private String nomArticle;
    
    /**
     * 
     * @param type
     * @param categorie
     * @param description
     * @param timeOfPublish
     * @param price
     * @param nomArticle TODO
     */

    protected Article(String type,String categorie, String description, Time timeOfPublish, double price, String nomArticle) {
        nbArticle++;
        this.id = nbArticle;
        this.type = type;
        this.description = description;
        this.time = timeOfPublish;
        this.categorie = categorie;
        this.price = price;
        this.numeroDeSerie = categorie.substring(0)+"-"+type.substring(0,2)+"-"+id;
        this.quantite = 1;
        this.nomArticle = nomArticle;
    }

    protected Article(Article article){
        this(article.type,article.categorie,article.description,article.time,article.price, null);
    }

    /**
     *
     * deux articles sont égaux s'ils sont de meme catégorie, type et sont dans le meme état.
     *
     * @param object
     * @return
     */
    
    @Override
    public boolean equals(Object object) {

    	Article article = (Article)object;
    	if(this.categorie.equals(article.categorie) && this.type.equals(article.type) && this.etat == article.etat) {
    		return true;
    	}
    	return false;
    }
    
    public void setArticle(Article article) {
    	this.etat = article.etat;
    	this.type = (article.type).toString();
    	this.categorie = (article.categorie).toString();
    	this.description = (article.categorie).toString();
    	this.time = new Time(article.time);
    	this.price = article.price;
    	this.nomArticle = article.nomArticle;
    }
    
    
    @Override
    public Article clone() {
    	
    	Article article = null;
    	try {
			article = (Article)(super.clone());
		} catch (CloneNotSupportedException e) {
			System.out.println("Article initialisée à la valeur null");
			return null;
		}
    	
    	article.setArticle(this);
    	
    	return article;
    }
    
    public abstract void addArticleArray(ArrayList<Article> articleArray, int quantite);
    public abstract void suppressToArticleArray(ArrayList<Article> articleArray, int quantite);
    
  
	public boolean verifierDisponibilite(ArrayList<Article> articleArray, int quantite) {
		
		if(articleArray.contains(this) && (this.quantite >= quantite)) {
			return true;
		}
		return false;
	}

    /**
     * 
     * @return
     */
    
    
    @Override
    public String toString() {
    	return "id : "+id+", type: "+", type : "+type+", categorie : "+categorie+", Date : "+description+time.getStringDateEtHeureFormat()+",price : "+price+", Serie : "+numeroDeSerie+", état : "+etat+", quantite : "+quantite;
    }
    
    public String getType() {
        return type;
    }
    
    /**
     * 
     * @param type
     */

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceAvecQuantite(){
    	return price*quantite;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
    
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public void increaseQuantite(int quantiteAjoute) {
    	this.quantite+=quantiteAjoute;
    }
    
    public void decreaseQuantite(int quantiteDecrease) {
    	if(this.quantite>=quantiteDecrease) {
    		this.quantite-=quantiteDecrease;
    	}
    	else {
    		System.out.println("la quantité à enlever est supérieure à celle existante");
    	}
    }
    
    public String getNomArticle() {
		return nomArticle;
	}

    public static int getNbArticle() {
        return nbArticle;
    }
}
