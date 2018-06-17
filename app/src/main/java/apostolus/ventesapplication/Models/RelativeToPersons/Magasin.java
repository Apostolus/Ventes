package apostolus.ventesapplication.Models.RelativeToPersons;

import java.util.ArrayList;
import apostolus.ventesapplication.Models.RelativeToThings.Article;


public class Magasin extends Professionnel {

    @SuppressWarnings("unused")
	private ArrayList<Article> panier;

    public Magasin(String country, String departement, String city, String specificAdress, String email, String phoneNumber,boolean decouvert, int montantDecouvertAutorise, int montantDecouvert, int solde,String nom){
        super(country,departement,city,specificAdress,email,phoneNumber,decouvert,montantDecouvertAutorise,montantDecouvert,solde,nom);
        panier = new ArrayList<>();
    }
    public Magasin() {
    	this(null,null,null,null,null,null,true,0,0,0,"magasin");
    }

}
