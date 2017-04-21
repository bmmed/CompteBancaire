package Controller;

import java.util.ArrayList;

public class Client {
	private  int idClient ;
	private String nomClient;
	private String prenomClient;
	private ArrayList<Compte> cptClient;
	private ArrayList<Categorie> catClient;


	public Client(int id, String nom, String prenom) {
		this.idClient = id;
		this.nomClient = nom;
		this.prenomClient = prenom;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public ArrayList<Compte> getCptClient() {
		return cptClient;
	}

	public void setCptClient(ArrayList<Compte> cptClient) {
		this.cptClient = cptClient;
	}

	public ArrayList<Categorie> getCatClient() {
		return catClient;
	}

	public void setCatClient(ArrayList<Categorie> catClient) {
		this.catClient = catClient;
	}

	public Categorie creatCat(String des){
		Categorie res =new Categorie(0,des,this.getIdClient());
		return res;
	}

	public String getCvsClient() {

		String res="\n";
		res+="\n Identifiant client: "+this.getIdClient()+"\n Nom: "+this.getNomClient()+"\n Prenom: "+this.getPrenomClient()+"\n Date: "+MaDate.getSysDate()+"\n \n";

		res+=" I-Les Categories D'operations favoris du client: \n";
		for(Categorie i:this.getCatClient())
		{
			res+="    "+i.getIdCat()+" , "+i.getDesCat()+"\n";
		}

		res+="\n\nII-Les Comptes client:\n";
		for(Compte i:this.getCptClient())
		{
			res+=i.getCvsCpt();
		}
		return res;
	}
}
