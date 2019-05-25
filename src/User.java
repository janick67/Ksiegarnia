//JAN WORWA

// Klasa User zawiera wszystkie dane na temat pojedynczego u¿ytkownika w systemie, w bookstore na podstawie tej klasy jest tworzona lista wszystkich u¿ytkowników
//Korzystaj¹c z tej klasy mo¿na odczytaæ informacje na temat u¿ytkowników, ale te¿ tworzyæ nowych i dodawaæ ich do bazy sql, przy pomocy metody writeSQL na gotowym obiekcie
//mamy tutaj te¿ funkcje z której nale¿y skorzystaæ przylogowaniu, czyli checkPassowrd

public class User {
	int id = 0; // jest przypisywane na bazie i nastêpnie uzupe³niane
	String username;
	private String password; //pole jest ukryte tak ¿eby nikt nie móg³ go edytowaæ ani podejrzeæ, do sprawdzenia has³a jest metoda checkPassword
	String email;
	String address;
	int isadmin; // 1 - admin, 0 - zwyk³y u¿ytkownik 
	
	public User(int id, String username, String password, String email, String address, int isadmin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.isadmin = isadmin;
	}
	
	public User(String username, String password, String email, String address, int isadmin) {
		this(0,username,password,email,address,isadmin);
	}
	
	public User(String username, String password, String email, String address) {
		this(0,username,password,email,address,0);
	}
	
	public User(int id) {				// kostruktor wykorzystywany tylko i wy³¹cznie to stworzenia tymczasowego obiektu do porównania przy pomocy equals
		this(id,"","","","",0);
	}
	
	//checkPassword u¿ywana przy logowaniu 
	//Parametry: password - has³o które chcemy przyrównac do has³a bie¿¹cego u¿ytkownika
	//Returns: true - jeœli has³a siê zgadzaj¹ i false - jeœli nie
	public boolean checkPassword(String password)
	{
		if(this.password.equals(password)) return true;
		return false;
	}
	
	
	//delete s³u¿y jedynie do usuwania u¿ytkownika z bazy danych
	//returns: false - jeœli u¿ytkownik nie ma przypisanego id poniewa¿ wtedy nie wiemy którego u¿ytkownika usun¹æ i prawdopodobnie nie ma go w bazie, w przeciwnym razie true
	public boolean delete(){
		if(this.id <= 0) return false;
		Mysql.delete("DELETE FROM `users` WHERE id = "+this.id);
		return true;
	}
	
	//metoda wykorzystywana po stworzeniu nowego u¿ytkownika do zapisania go na bazie i przypisania nadanego mu id
	//Returns: zwraca id nadane temu u¿ytkonikowi na bazie
	public int writeSQL()
	{
		if(this.id != 0) return -1;
		int id = Mysql.insert("INSERT INTO `users`VALUES (default,'"+username+"','"+password+"','"+email+"','"+address+"','"+isadmin+"')");
		if (id != 0) {
			this.id = id;
			return id;
		}
		return -1;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + ", isadmin="+isadmin + "]";
	}
	
	
	// porównuje obiekty jedynie na podstawie id
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
