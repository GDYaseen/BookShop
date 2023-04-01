package Models;

public class Category{
	
	private int Id;
	private String Catg;
	
	public Category(int id, String catg) {
		super();
		Id = id;
		Catg = catg;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCatg() {
		return Catg;
	}
	public void setCatg(String catg) {
		Catg = catg;
	}
	
}