import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//JAN WORWA

// Moja g��wna klasa w kt�rej ��cz� pozosta�e: book, order, user
// W tej klasie przy pomocy load mo�na pobra� wszystkie dane z bazy i uzupe�ni� nimi klasy
// Zawiera informacje o wszystkich obiektach, obiekyty s� przechowywane w tablicowych listach i zawieraj� dane na temat wszytkich u�ytkownik�w, ksi��ek i zam�wie� w systemie

// Aby doda� nowe zam�wienie z koszyka u�yj metody addToCart podaj�c wybrane ksi��ki a nast�pnie, potwierdz zam�wienie korzystaj�c z orderCart
// �eby doda� zam�w�wienie bez koszyka u�yj opcji prepareOrder, p�zniej dodaj do niego ksiazki uzywajac order.addBook a jak ju� jest gotowe to u�yj order.writeSQL
public class Bookstore {
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<OrderBook> cart = new ArrayList<OrderBook>();
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Order> orders = new ArrayList<Order>();
	User activeUser = null;

	//metoda powinna by� odpalona zaraz po stworzeniu nowej instancji klasy, celowo nie jest odpalana w konstruktorze
	public void load()
	{
		loadBooks();
		loadUsers();
		loadOrders();
	}
	
	//pobiera dane o ksi��kach i wpisuje je do listy books
	public void loadBooks()
	{
		Mysql mysqlConnect = new Mysql();
		try {
		    Statement stmt = mysqlConnect.connect().createStatement();
		    ResultSet rs = stmt.executeQuery("select * from books");
		    
		    while (rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int instock = rs.getInt("instock");
				String print = rs.getString("print");
				String language = rs.getString("language");
				int year = rs.getInt("year");
				float brutto = rs.getFloat("brutto");
				float netto = rs.getFloat("netto");
				int ean = rs.getInt("ean");
				int page = rs.getInt("page");
				Book temp = new Book(id,title,author,instock,print,language,year,brutto,netto,ean,page);
				if (!books.contains(temp)) books.add(temp);
			}
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		}
	}
	
	// findBookById - wyszukuje i zwraca ksi��k� w lisicie books na podstawie podanego id
	// Parametry: id ksi��ki kt�r� chcemy znalez�
	// Returns: zwraca ksi��k� o podanym id lub null je�li nie znalaz� �adnej
	public Book findBookById(int id ) {
		int index = books.indexOf(new Book(id));
		if (index >= 0) return books.get(index);
		return null;
	}
	
	// findBookByname - wyszukuje i zwraca ksi��k� w lisicie books na podstawie podanej pe�nej nazwy
	// Parametry: nazwa ksi��ki kt�r� chcemy znalez�
	// Returns: zwraca ksi��k� w formie stringu 
	public String findBookByname(String name ) {
		String ks = null;
		for(int i=0;i<books.size();i++) {
			if(name.equals(books.get(i))){
				  ks = books.toString();
			}
		}return ks;
	}
	
	// findUserById - wyszukuje i zwraca u�ytkownika z listy users na podstawie podanego id
	// Parametry: id u�ytkownika kt�rego chcemy znalez�
	// Returns: zwraca u�ytkownika o podanym id lub null je�li nie znalaz� �adnego
	public User findUserById(int id) {
		int index = users.indexOf(new User(id));
		if (index >= 0) return users.get(index);
		return null;
	}
	
	
	// findOrderById - wyszukuje i zwraca zam�wienie z listy orders na podstawie podanego id
	// Parametry: id zam�wienia kt�rego chcemy znalez�
	// Returns: zwraca zam�wienie o podanym id lub null je�li nie znalaz� �adnego
//MZ	
	public Order findOrderById(int id) {
		int index = orders.indexOf(new Order(id));
		if (index >= 0) return orders.get(index);
		return null;
	}
//MZ end	
	
	// deleteUser usuwa podanego u�ytkownika z listy i z bazy sql
	// Parametry: user - obiekt typu User zawieraj�cy u�ytkownika kt�rego chcemy usun��
	public boolean deleteUser(User user) {
		user.delete();
		users.remove(user);
		return true;
	}
	
	
	// deleteBook usuwa podan� ksi��k� z listy i z bazy sql
	// Parametry: user - obiekt typu User zawieraj�cy u�ytkownika kt�rego chcemy usun��
	public boolean deleteBook(Book book) {
		book.delete();
		books.remove(book);
		return true;
	}
	
	
	// loadUsers - pobiera z bazy sql wszystkie dane na temat u�ytkonik�w z tabeli users i zapisuje ich w li�cie users jako obiekty typu User
	public void loadUsers()
	{
		Mysql mysqlConnect = new Mysql();
		try {
		    Statement stmt = mysqlConnect.connect().createStatement();
		    ResultSet rs = stmt.executeQuery("select * from users");
		    
		    while (rs.next()) {
				int id = rs.getInt("ID");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String address = rs.getString("address");
				int isadmin = rs.getInt("isadmin");
				User temp = new User(username,password,email,address,isadmin);
				temp.id = id;
				if (!users.contains(temp)) users.add(temp);
			}
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		}
	}
	

	// loadUsers - pobiera z bazy sql wszystkie dane na temat zam�wie� z tabeli orders i zapisuje je w li�cie orders jako obiekty typu Order
	public void loadOrders()
	{
		Mysql mysqlConnect = new Mysql();
		try {
		    Statement stmt = mysqlConnect.connect().createStatement();
		    ResultSet rs = stmt.executeQuery("select * from orders");
		    
		    while (rs.next()) {
				int id = rs.getInt("ID");
				int userId = rs.getInt("userID");
				int total = rs.getInt("totalPrice");
				String deliveryAddress = rs.getString("deliveryaddress");
				int status = rs.getInt("status");
				Order temp = new Order(id, userId, total, deliveryAddress, status);
				if (!orders.contains(temp)) orders.add(temp);
			}
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		    loadOrderBooks();
		}
	}
	
	
	// loadUsers - pobiera z bazy sql wszystkie dane na temat ksi��ek z tabeli books i zapisuje je w li�cie books jako obiekty typu Book
	public void loadOrderBooks()
	{
		Mysql mysqlConnect = new Mysql();
		try {
		    Statement stmt = mysqlConnect.connect().createStatement();
		    ResultSet rs = stmt.executeQuery("select * from orderbooks order by ordersID");
		    
		    while (rs.next()) {
				int id = rs.getInt("ID");
				int orderId = rs.getInt("ordersID");
				int bookId = rs.getInt("booksID");
				int amount = rs.getInt("amount");
				int indexOrders = orders.indexOf(new Order(orderId));
				if (indexOrders >=  0 && indexOrders < books.size()) {
					Order order = orders.get(indexOrders);
					if (!order.books.contains(new OrderBook(id))) {
							int indexBooks = books.indexOf(new Book(bookId));
							if (indexBooks >= 0 && indexBooks < books.size()) {
								order.addBook(id, books.get(indexBooks),amount);
								
							}
							else {
								System.out.println("nie znaleziono ksi��ki index = "+indexBooks+", bookId = "+ bookId);
							}
					}
				}
				else {
					System.out.println("nie znaleziono zam�wienia index = "+indexOrders+", orderId = "+ orderId);
				}
			}
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		}
	}
	
	
	// login - funkcja pozwala zalogowa� si� do ksi�garni, sprawdza czy podany u�ytkownik istanieje i czy has�o si� zgadza, po udanym logowaniu zapisuje u�ytkownika w activeUser
	// Parametry: nazwa u�ytkonika i has�o na kt�rego chcemy si� zalogowa�
	// Returns: zwraca id zalogowanego u�ytkonika lub -1 je�li logowanie si� nie powiod�o
	public int login(String username, String password)
	{
		for(int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.username.equals(username))
			{
				if (user.checkPassword(password))
				{
					this.activeUser = user;
					
					return activeUser.id;	
				}
				return -1;
			}
		}
		return -1;
	}
	
	// logout - wylogowywuje u�ytkonika, przy okazji czyszcz�c jego koszyk
	public void logout()
	{
		activeUser = null;
		cart = new ArrayList<OrderBook>();
	}
	
	// addToCart - dodaje ksi��ki do koszyka
	// Parametry: newone - ksi��ka kt�r� chcemy doda�, amount - liczba sztuk ksi��ki kt�r� chcemy doda�
	public void addToCart(Book newone, int amount)
	{
		if (newone.instock - amount >= 0) {
			cart.add(new OrderBook(newone, amount));
		}
	}
	
	// cartOrder - tworzy zam�wienie z ksi��ek znajduj�cych si� w koszyku 
	// Parametry: user - u�ytkownik kt�ry sk�ada zam�wienie, address - adres na kt�ry ma zosta� wys�ane zam�wienie
	public void cartOrder(User user, String address)
	{
		Order newone = new Order(user.id, cart, address);
		orders.add(newone);
		System.out.println(newone);
		newone.writeSQL();
	}
	
	// addBook - dodaje ksi��k� uzupe�niaj�c jedynie podstawowe dane, o pozosta�e dane mo�na uzupe�ni� p�zniej
	// Parametry: tytu�, autot i ilo�� sztuk kt�ra ma zosta� dodana na magazynie
	public void addBook(String title, String author, int instock)
	{
		Book newone = new Book(title, author, instock);
		newone.id = newone.writeSQL();
		books.add(newone);
	}
		
	// addUser - dodaje u�ytkownika do listy users i na bazie sql
	// Parametry: nazwa u�ytkownika, has�o, email, adres
	public void addUser(String username, String password, String email, String address, int isadmin)
	{
		User newone = new User(username, password, email, address, isadmin);
		newone.id = newone.writeSQL();
		users.add(newone);
	}
	
	//Przeci��enie powy�szej metody ustawiaj�c domy�lnie u�ytkownika jako nie admina
	public void addUser(String username, String password, String email, String address)
	{
		addUser(username, password, email, address, 0);
	}
	
	// preapreOrder tworzy nowe zam�wienie ale nie zapisuje go na bazie sql
	public Order prepareOrder(int userId)
	{
		Order newone = new Order(userId);
		orders.add(newone);
		return newone;
	} 
	
	public void listofbooks()
	{
		System.out.println(books.toString());
	}
	
	public void listofusers()
	{
		System.out.println(users.toString());
	}
	
	public void listofOrders()
	{
		System.out.println(orders.toString());
	}
	
}
