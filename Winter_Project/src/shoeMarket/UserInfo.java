package shoeMarket;

public class UserInfo {
	public String id;
	public String password;
	public boolean admin;

	public UserInfo() {

	}

	public UserInfo(String id, String password, boolean admin) {
		this.id = id;
		this.password = password;
		this.admin = admin;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
