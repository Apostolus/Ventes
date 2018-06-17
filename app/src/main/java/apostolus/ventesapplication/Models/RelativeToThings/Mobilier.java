package apostolus.ventesapplication.Models.RelativeToThings;
import java.util.Date;
import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Garantissable;
import apostolus.ventesapplication.Models.ActionsBtwPersonsThings.Time;

public class Mobilier extends Article implements Garantissable {
	private String marque;
	private Time dureeGarantie = null;
	private double priceGarantiePerMonth;
	private boolean isGarantissable = false;
	private String nomMobilier;

    public Mobilier(String type, String description, double price, String nomMobilier, String marque, double priceGarantiePerMonth) {
        super(type, "Mobilier", description, price);
        this.marque = marque;
        this.nomMobilier = nomMobilier;
        this.priceGarantiePerMonth = priceGarantiePerMonth;
    }
    
    /**
     * sur cette méthode, on suppose qu'on a déjà testé que la garantie est encore valide.
     * elle retourne la date de fin de garantie.
     * 
     * @return : Date
     */
    
    @Override
	public Date garantieTimeRemaining() {

    	return (this.timeOfPublish).addToCurrent(dureeGarantie.getDate());
    }
	
	/**
	 * cette méthode teste si la garantie est encore valide, elle retourne true si ele l'est,
	 * false sinon.
	 * 
	 * elle pourra servir dans les cas on a besoin de savoir quand est ce que la garantie de l'article en question va se terminer.
	 * 
	 * @return : boolean
	 */
    
    @Override
    public boolean garantieIsAvailable() {
    	
    	Date timeNow = new Date();
    	Date tillBuy = (this.getTimeOfPublish()).addToCurrent(dureeGarantie.getDate());

		return (timeNow.compareTo(tillBuy) < 0);
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
		(this.dureeGarantie).setDate(dureeGarantie);
		this.isGarantissable = true;
	}

	public String getMarque() {
		return marque;
	}
	public String getNomMobilier() {
		return nomMobilier;
	}
}
