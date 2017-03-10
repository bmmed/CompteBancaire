import java.util.ArrayList;

public class Client {
	private  int idClient ;
	private String nomClient;
	private String prenomClient;
	private ArrayList<Compte> cptClient;
	private ArrayList<Categorie> catClient;


	public Client(int idClient, String nomClient, String prenomClient) {
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
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

	public String getCvsClient()
	{
		String res="\n";
		res+="identifiant du client: "+this.getIdClient()+"  nom: "+this.getNomClient()+"   prenom: "+this.getPrenomClient()+"\n";
		for(Compte i:this.getCptClient())
		{
			res+=i.getCvsCpt();
		}
		return res;
	}
}
