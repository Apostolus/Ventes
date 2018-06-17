package apostolus.ventesapplication.Models.RelativeToThings;

public class Immobilier extends Article {

    private double Totalesurface;



    public Immobilier(String type, String description, double price, double surface) {

        super(type, "Immbolier", description, price);
        this.Totalesurface = surface;
    }
    
    public double getTotalesurface() {
		return Totalesurface;
	}
    
    public void setTotalesurface(double totalesurface) {
		Totalesurface = totalesurface;
	}

}
