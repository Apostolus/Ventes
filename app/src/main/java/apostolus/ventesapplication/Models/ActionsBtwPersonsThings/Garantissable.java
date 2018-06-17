package apostolus.ventesapplication.Models.ActionsBtwPersonsThings;

import java.util.Date;

public interface Garantissable {
	
	public boolean garantieIsAvailable();
	public Date garantieTimeRemaining();
	public void setPriceGarantiePerMonth(double priceGarantiePerMonth);
	public double getPriceGarantiePerMonth();
	public void setDureeGarantie(Date date);
	public Time getDureeGarantie();
	public boolean isGarantissable();
	public void makeArticleGarantissable(Date dureeGarantie);
}
