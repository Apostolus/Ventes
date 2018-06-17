package apostolus.ventesapplication.Models.RelativeToPersons;

/**
 * cette classe décris l'adresse d'une personne.
 * elle est créée pour regrouper les attributs en rapport avec l'adresse
 */
public class Coordonnees {

	private String country;
	private String departement;
	private String city;
	private String specificAdress;
	private String email;
	private String phoneNumber;

    public Coordonnees(String country, String departement, String city, String specificAdress, String email, String phoneNumber) {
        this.country = country;
        this.departement = departement;
        this.city = city;
        this.specificAdress = specificAdress;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public Coordonnees() {
		this("country","departement","city","specificAdress","email","phoneNumber");
	}


	public String getDepartement() {
		return this.departement;
	}

	/**
	 * 
	 * @param departement
	 */
	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getCity() {
		return this.city;
	}

	/**
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	public String getSpecificAdress() {
		return this.specificAdress;
	}

	/**
	 * @param specificAdress
	 */
	public void setSpecificAdress(String specificAdress) {
		this.specificAdress = specificAdress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}