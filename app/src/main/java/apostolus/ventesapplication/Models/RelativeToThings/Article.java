package apostolus.ventesapplication.Models.RelativeToThings;
import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Time;

public abstract class Article implements Cloneable{

    protected final int id;
    private static int nbArticle = 0;
    protected String type;
    protected String categorie;
    protected String description;
    protected Time timeOfPublish;
    protected Time timeOfBuy;
    protected double price;
    protected int etat;
    protected String numeroDeSerie;

    /**
     * @param type : type de l"article
     * @param categorie : catégorie de l'article
     * @param description : déscription de l'article
     * @param price : le prix de l"article
     */

    protected Article(String type, String categorie, String description, double price) {
        nbArticle++;
        this.id = nbArticle;
        this.type = type;
        this.description = description;
        this.timeOfPublish = new Time();
        this.timeOfBuy = new Time();
        this.categorie = categorie;
        this.price = price;
        this.numeroDeSerie = categorie.substring(0)+"-"+type.substring(0,2)+"-"+id;
    }

    /**
     * deux articles sont égaux s'ils sont de meme catégorie, de meme type et sont dans le meme état.
     *
     * @param object : un article
     * @return : retourne vraie si les deux articles sont de meme catégorie, de meme type, et sont
     * dans le meme état.
     */
    
    @Override
    public boolean equals(Object object) {

    	Article article = (Article)object;
        return this.categorie.equals(article.categorie) && this.type.equals(article.type) && (this.etat == article.etat);
    }

    public String getType() { return type;}

    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Time getTimeOfPublish() { return timeOfPublish; }

    public Time getTimeOfBuy() { return timeOfBuy; }

    public void setTimeOfBuy(Time timeOfBuy) { this.timeOfBuy = timeOfBuy; }

    public void setTimeOfPublish(Time timeOfPublish) { this.timeOfPublish = timeOfPublish; }

    public String getCategorie() { return categorie; }

    public void setCategorie(String categorie) { this.categorie = categorie; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public int getEtat() { return etat; }

    public void setEtat(int etat) { this.etat = etat; }
    
    public String getNumeroDeSerie() { return numeroDeSerie; }


    public static int getNbArticle() { return nbArticle; }
}
