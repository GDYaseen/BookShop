package Models;

public class Sale {
	
	private int id_client,id_livre;
	private String date_vente;
	
	public Sale(int id_client, int id_livre, String date_vente) {
		this.id_client = id_client;
		this.id_livre = id_livre;
		this.date_vente = date_vente;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public int getId_livre() {
		return id_livre;
	}
	public void setId_livre(int id_livre) {
		this.id_livre = id_livre;
	}
	public String getDate_vente() {
		return date_vente;
	}
	public void setDate_vente(String date_vente) {
		this.date_vente = date_vente;
	}
	
	private int id,sold;
	private String titre,auteur,categories;
	private float prix, total;
	
	public Sale(int id, int id_client, int sold, String titre, String auteur, String categories, float prix,float total,String date_vente) {
		this.id = id;
		this.id_client = id_client;
		this.sold = sold;
		this.titre = titre;
		this.auteur = auteur;
		this.categories = categories;
		this.prix = prix;
		this.total = total;
		this.date_vente = date_vente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String toString() {
		return "{\"Id_client\":"+getId_client()+"#\"Id_livre\":"+getId_livre()+"#\"Date_vente\":\""+getDate_vente()+"\"#\"Id\":"+getId()+"#\"Sold\":"+getSold()+"#\"Titre\":\""+getTitre()+"\"#\"Auteur\":\""+getAuteur()+"\"#\"Categories\":\""+getCategories()+"\"#\"Prix\":"+getPrix()+"#\"Total\":"+getTotal()+"}";
	}
	
}