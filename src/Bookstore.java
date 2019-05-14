import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// ¿eby dodaæ zamówówienie u¿yj opcji prepareOrder, pózniej dodaj do niego ksiazki uzywajac order.addBook a jak ju¿ jest gotowe to u¿yj order.writeSQL

public class Bookstore {
	
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<OrderBook> cart = new ArrayList<OrderBook>();
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Order> orders = new ArrayList<Order>();
	User activeUser = null;

//	public static void main(String[] args) {
//		Bookstore mbs = new Bookstore();
//		mbs.load();
//		
//		
//		System.out.println(mbs.activeUser);
//		if (mbs.activeUser != null) System.out.println(mbs.activeUser.username);
//		System.out.println(mbs.login("admin", "admin"));
//		System.out.println(mbs.activeUser);
//		if (mbs.activeUser != null) System.out.println(mbs.activeUser.username);
////		Book potop = new Book("Potop","Sienkiewicz",5);
////		potop.writeSQL();
////		mbs.books.add(potop);
////		User admin = new User("admin","admin","email@admin.net","");
////		admin.writeSQL();
////		mbs.users.add(admin);
//	}
	
	public void load()
	{
		loadBooks();
		loadUsers();
		loadOrders();
		
	}
	
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
				Book temp = new Book(title,author,instock,print,language,year,brutto,netto,ean,page);
				temp.id = id;
				if (!books.contains(temp)) books.add(temp);
			}
		    
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		}
	}
	
	//Jacek - max ID
	public int MaxId()
	{
		int id = 0;
		Mysql mysqlConnect = new Mysql();
		try {
		    Statement stmt = mysqlConnect.connect().createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT MAX(ID) as max from books");
		    if(rs.next()) {
		    	  id = rs.getInt("max");	
		    }
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		}
		return id;
	}
	
	public String fill(int u)
	{
		String title="";
		String author="";
		String result="";
		Mysql mysqlConnect = new Mysql();
		
		try {
			String sql=("SELECT title, author from books where ID="+u);
		    Statement stmt = mysqlConnect.connect().createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()) {
		    	  title = rs.getString("title");
		    	  author = rs.getString("author");
		    	  result=(title+" "+ author);
			}
		  
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		}
		return result ;
	}
	
	//Jacek- koniec
	
	//Mateusz
		public int MaxId(String name)
		{
			int id = 0;
			Mysql mysqlConnect = new Mysql();
			try {
			    Statement stmt = mysqlConnect.connect().createStatement();
			    ResultSet rs = stmt.executeQuery("SELECT MAX(ID) as max from "+name);
			    if(rs.next()) {
			    	  id = rs.getInt("max");	
			    }
			    
			} catch (SQLException e) {
			    e.printStackTrace();
			} finally {
			    mysqlConnect.disconnect();
			}
			return id;
		}
		
		public String fillUser(int u)
		{
			String username="";
			//String email="";
			//String result="";
			Mysql mysqlConnect = new Mysql();
			
			try {
				String sql=("SELECT username from users where ID="+u);
			    Statement stmt = mysqlConnect.connect().createStatement();
			    ResultSet rs = stmt.executeQuery(sql);
			    
			    while(rs.next()) {
			    	username = rs.getString("username");
			    	//email = rs.getString("email");
			    	 // result=(username+" "+ email+" "+u);
				}
			  
			} catch (SQLException e) {
			    e.printStackTrace();
			} finally {
			    mysqlConnect.disconnect();
			}
			return username ;
		}

		//Mateusz-koniec
	
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
				User temp = new User(username,password,email,address);
				temp.id = id;
				if (!users.contains(temp)) users.add(temp);
			}
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		}
	}
	
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
				Order temp = new Order(id, userId, total, deliveryAddress);
				if (!orders.contains(temp)) orders.add(temp);
			}
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		    loadOrderBooks();
		}
	}
	
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
	
	public boolean login(String username, String password)
	{
		for(int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.username.equals(username))
			{
				if (user.checkPassword(password))
				{
					this.activeUser = user;
					return true;	
				}
				return false;
			}
		}
		return false;
	}
	
	public void logout()
	{
		activeUser = null;
		cart = new ArrayList<OrderBook>();
	}
	
	
	public void addToCart(Book newone, int amount)
	{
		cart.add(new OrderBook(newone, amount));
	}
	
	public void cartOrder(User user, String address)
	{
		Order newone = new Order(user.id, cart, address);
		orders.add(newone);
		newone.writeSQL();
	}
	
	
	public void addBook(String title, String author, int instock)
	{
		Book newone = new Book(title, author, instock);
		newone.id = newone.writeSQL();
		books.add(newone);
	}

	public void addUser(String username, String password, String email, String address)
	{
		User newone = new User(username, password, email, address);
		newone.id = newone.writeSQL();
		users.add(newone);
	}
	
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
