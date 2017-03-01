
public class Client {
	private  int idClient ;
	private String nomClient;
	private String prenomClient;


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


}
