package Models;

import java.sql.Blob;

public class Book {

	private int id,stock;
	private float Prix;
	private String Titre,Auteur,Categories;
	private byte[] Cover;
	
	public Book(int id, String titre, String auteur, String categories,float Prix, byte[] cover,int stock) {
		this.id = id;
		Titre = titre;
		Auteur = auteur;
		Categories = categories;
		Cover = cover;
		this.Prix=Prix;
		this.stock=stock;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public String getAuteur() {
		return Auteur;
	}
	public void setAuteur(String auteur) {
		Auteur = auteur;
	}
	public String getCategories() {
		return Categories;
	}
	public void setCategories(String categories) {
		Categories = categories;
	}
	public float getPrix() {
		return Prix;
	}
	public void setPrix(float prix) {
		Prix = prix;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public byte[] getCover() {
		return Cover;
	}
	public void setCover(byte[] cover) {
		Cover = cover;
	}
	public String toString() {
		return "{\"Id\":\""+getId()+"\"#\"Titre\":\""+getTitre()+"\"#\"Auteur\":\""+getAuteur()+"\"#\"Categories\":\""+getCategories()+"\"#\"Prix\":\""+getPrix()+"\"#\"Stock\":\""+getStock()+"\"#\"Cover\":\""+(new String(getCover())).replaceAll(",", "#")+"\"}";
	}

}
