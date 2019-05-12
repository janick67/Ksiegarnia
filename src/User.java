public class User {
	int id = 0;
	String username;
	private String password;
	String email;
	String address;
	
	public User(String username, String password, String email, String address) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
	}
	
	public boolean checkPassword(String password)
	{
		if(this.password.equals(password)) return true;
		return false;
	}
	
	public int writeSQL()
	{
		if(this.id != 0) return -1;
		int id = Mysql.insert("INSERT INTO `users`VALUES (default,'"+username+"','"+password+"','"+email+"','"+address+"')");
		if (id != 0) {
			this.id = id;
			return id;
		}
		return -1;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + "]";
	}
	
	@Override
	public boolean equals(Object other)
    {
		if (other == this) {
		     return true;
		   }
	    if (!(other instanceof User)) {
	      return false;
	    }
	    User otherUser = (User)other;
		if (this.id != otherUser.id)
            return false;
		return true;
    }
}
