//JAN WORWA

// Klasa User zawiera wszystkie dane na temat pojedynczego u�ytkownika w systemie, w bookstore na podstawie tej klasy jest tworzona lista wszystkich u�ytkownik�w
//Korzystaj�c z tej klasy mo�na odczyta� informacje na temat u�ytkownik�w, ale te� tworzy� nowych i dodawa� ich do bazy sql, przy pomocy metody writeSQL na gotowym obiekcie
//mamy tutaj te� funkcje z kt�rej nale�y skorzysta� przylogowaniu, czyli checkPassowrd

public class User {
	int id = 0; // jest przypisywane na bazie i nast�pnie uzupe�niane
	String username;
	private String password; //pole jest ukryte tak �eby nikt nie m�g� go edytowa� ani podejrze�, do sprawdzenia has�a jest metoda checkPassword
	String email;
	String address;
	int isadmin; // 1 - admin, 0 - zwyk�y u�ytkownik 
	
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
	
	public User(int id) {				// kostruktor wykorzystywany tylko i wy��cznie to stworzenia tymczasowego obiektu do por�wnania przy pomocy equals
		this(id,"","","","",0);
	}
	
	//checkPassword u�ywana przy logowaniu 
	//Parametry: password - has�o kt�re chcemy przyr�wnac do has�a bie��cego u�ytkownika
	//Returns: true - je�li has�a si� zgadzaj� i false - je�li nie
	public boolean checkPassword(String password)
	{
		if(this.password.equals(password)) return true;
		return false;
	}
	
	
	//delete s�u�y jedynie do usuwania u�ytkownika z bazy danych
	//returns: false - je�li u�ytkownik nie ma przypisanego id poniewa� wtedy nie wiemy kt�rego u�ytkownika usun�� i prawdopodobnie nie ma go w bazie, w przeciwnym razie true
	public boolean delete(){
		if(this.id <= 0) return false;
		Mysql.delete("DELETE FROM `users` WHERE id = "+this.id);
		return true;
	}
	
	//metoda wykorzystywana po stworzeniu nowego u�ytkownika do zapisania go na bazie i przypisania nadanego mu id
	//Returns: zwraca id nadane temu u�ytkonikowi na bazie
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
	
	
	// por�wnuje obiekty jedynie na podstawie id
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
