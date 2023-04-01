package Models;

public class Client {
	
	private int Id;
	private String nom,email;
	
	public Client(int id, String nom, String email) {
		Id = id;
		this.nom = nom;
		this.email = email;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}