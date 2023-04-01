package Models;

public class User{
	
	private int id;
	private String username,password;
	private boolean admin;
	
	public User(int id, String username, String password, boolean isAdmin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.admin = isAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}

}