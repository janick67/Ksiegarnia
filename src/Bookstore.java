import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//JAN WORWA

// Moja g³ówna klasa w której ³¹czê pozosta³e: book, order, user
// W tej klasie przy pomocy load mo¿na pobraæ wszystkie dane z bazy i uzupe³niæ nimi klasy
// Zawiera informacje o wszystkich obiektach, obiekyty s¹ przechowywane w tablicowych listach i zawieraj¹ dane na temat wszytkich u¿ytkowników, ksi¹¿ek i zamówieñ w systemie

// Aby dodaæ nowe zamówienie z koszyka u¿yj metody addToCart podaj¹c wybrane ksi¹¿ki a nastêpnie, potwierdz zamówienie korzystaj¹c z orderCart
// ¯eby dodaæ zamówówienie bez koszyka u¿yj opcji prepareOrder, pózniej dodaj do niego ksiazki uzywajac order.addBook a jak ju¿ jest gotowe to u¿yj order.writeSQL
public class Bookstore {
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<OrderBook> cart = new ArrayList<OrderBook>();
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Order> orders = new ArrayList<Order>();
	User activeUser = null;

	//metoda powinna byæ odpalona zaraz po stworzeniu nowej instancji klasy, celowo nie jest odpalana w konstruktorze
	public void load()
	{
		loadBooks();
		loadUsers();
		loadOrders();
	}
	
	//pobiera dane o ksi¹¿kach i wpisuje je do listy books
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
	
	// findBookById - wyszukuje i zwraca ksi¹¿kê w lisicie books na podstawie podanego id
	// Parametry: id ksi¹¿ki któr¹ chcemy znalezæ
	// Returns: zwraca ksi¹¿kê o podanym id lub null jeœli nie znalaz³ ¿adnej
	public Book findBookById(int id ) {
		int index = books.indexOf(new Book(id));
		if (index >= 0) return books.get(index);
		return null;
	}
	
	// findBookByname - wyszukuje i zwraca ksi¹¿kê w lisicie books na podstawie podanej pe³nej nazwy
	// Parametry: nazwa ksi¹¿ki któr¹ chcemy znalezæ
	// Returns: zwraca ksi¹¿kê w formie stringu 
	public String findBookByname(String name ) {
		String ks = null;
		for(int i=0;i<books.size();i++) {
			if(name.equals(books.get(i))){
				  ks = books.toString();
			}
		}return ks;
	}
	
	// findUserById - wyszukuje i zwraca u¿ytkownika z listy users na podstawie podanego id
	// Parametry: id u¿ytkownika którego chcemy znalezæ
	// Returns: zwraca u¿ytkownika o podanym id lub null jeœli nie znalaz³ ¿adnego
	public User findUserById(int id) {
		int index = users.indexOf(new User(id));
		if (index >= 0) return users.get(index);
		return null;
	}
	
	
	// findOrderById - wyszukuje i zwraca zamówienie z listy orders na podstawie podanego id
	// Parametry: id zamówienia którego chcemy znalezæ
	// Returns: zwraca zamówienie o podanym id lub null jeœli nie znalaz³ ¿adnego
//MZ	
	public Order findOrderById(int id) {
		int index = orders.indexOf(new Order(id));
		if (index >= 0) return orders.get(index);
		return null;
	}
//MZ end	
	
	// deleteUser usuwa podanego u¿ytkownika z listy i z bazy sql
	// Parametry: user - obiekt typu User zawieraj¹cy u¿ytkownika którego chcemy usun¹æ
	public boolean deleteUser(User user) {
		user.delete();
		users.remove(user);
		return true;
	}
	
	
	// deleteBook usuwa podan¹ ksi¹¿kê z listy i z bazy sql
	// Parametry: user - obiekt typu User zawieraj¹cy u¿ytkownika którego chcemy usun¹æ
	public boolean deleteBook(Book book) {
		book.delete();
		books.remove(book);
		return true;
	}
	
	
	// loadUsers - pobiera z bazy sql wszystkie dane na temat u¿ytkoników z tabeli users i zapisuje ich w liœcie users jako obiekty typu User
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
	

	// loadUsers - pobiera z bazy sql wszystkie dane na temat zamówieñ z tabeli orders i zapisuje je w liœcie orders jako obiekty typu Order
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
	
	
	// loadUsers - pobiera z bazy sql wszystkie dane na temat ksi¹¿ek z tabeli books i zapisuje je w liœcie books jako obiekty typu Book
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
								System.out.println("nie znaleziono ksi¹¿ki index = "+indexBooks+", bookId = "+ bookId);
							}
					}
				}
				else {
					System.out.println("nie znaleziono zamówienia index = "+indexOrders+", orderId = "+ orderId);
				}
			}
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		}
	}
	
	
	// login - funkcja pozwala zalogowaæ siê do ksiêgarni, sprawdza czy podany u¿ytkownik istanieje i czy has³o siê zgadza, po udanym logowaniu zapisuje u¿ytkownika w activeUser
	// Parametry: nazwa u¿ytkonika i has³o na którego chcemy siê zalogowaæ
	// Returns: zwraca id zalogowanego u¿ytkonika lub -1 jeœli logowanie siê nie powiod³o
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
	
	// logout - wylogowywuje u¿ytkonika, przy okazji czyszcz¹c jego koszyk
	public void logout()
	{
		activeUser = null;
		cart = new ArrayList<OrderBook>();
	}
	
	// addToCart - dodaje ksi¹¿ki do koszyka
	// Parametry: newone - ksi¹¿ka któr¹ chcemy dodaæ, amount - liczba sztuk ksi¹¿ki któr¹ chcemy dodaæ
	public void addToCart(Book newone, int amount)
	{
		if (newone.instock - amount >= 0) {
			cart.add(new OrderBook(newone, amount));
		}
	}
	
	// cartOrder - tworzy zamówienie z ksi¹¿ek znajduj¹cych siê w koszyku 
	// Parametry: user - u¿ytkownik który sk³ada zamówienie, address - adres na który ma zostaæ wys³ane zamówienie
	public void cartOrder(User user, String address)
	{
		Order newone = new Order(user.id, cart, address);
		orders.add(newone);
		System.out.println(newone);
		newone.writeSQL();
	}
	
	// addBook - dodaje ksi¹¿kê uzupe³niaj¹c jedynie podstawowe dane, o pozosta³e dane mo¿na uzupe³niæ pózniej
	// Parametry: tytu³, autot i iloœæ sztuk która ma zostaæ dodana na magazynie
	public void addBook(String title, String author, int instock)
	{
		Book newone = new Book(title, author, instock);
		newone.id = newone.writeSQL();
		books.add(newone);
	}
		
	// addUser - dodaje u¿ytkownika do listy users i na bazie sql
	// Parametry: nazwa u¿ytkownika, has³o, email, adres
	public void addUser(String username, String password, String email, String address, int isadmin)
	{
		User newone = new User(username, password, email, address, isadmin);
		newone.id = newone.writeSQL();
		users.add(newone);
	}
	
	//Przeci¹¿enie powy¿szej metody ustawiaj¹c domyœlnie u¿ytkownika jako nie admina
	public void addUser(String username, String password, String email, String address)
	{
		addUser(username, password, email, address, 0);
	}
	
	// preapreOrder tworzy nowe zamówienie ale nie zapisuje go na bazie sql
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
