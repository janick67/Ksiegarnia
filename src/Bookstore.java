import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bookstore {
	
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Order> orders = new ArrayList<Order>();

	public static void main(String[] args) {
		Bookstore mbs = new Bookstore();
		mbs.loadBooks();
		mbs.listofbooks();
		mbs.loadUsers();
		//mbs.listofusers();
		mbs.loadOrders();
		mbs.listofOrders();
//		Book potop = new Book("Potop","Sienkiewicz",5);
//		potop.writeSQL();
//		mbs.books.add(potop);
//		User admin = new User("admin","admin","email@admin.net","");
//		admin.writeSQL();
//		mbs.users.add(admin);
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
				Book temp = new Book(title,author,instock);
				temp.id = id;
				if (!books.contains(temp)) books.add(temp);
			}
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		}
	}
	
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
					if (!order.orderBooksID.contains(id)) {
						order.orderBooksID.add(id);
						for (int i = 0; i < amount; i++) {
							int indexBooks = books.indexOf(new Book(bookId));
							if (indexBooks >= 0 && indexBooks < books.size()) {
								order.books.add(books.get(indexBooks));
							}
							else {
								System.out.println("nie znaleziono ksi¹¿ki index = "+indexBooks+", bookId = "+ bookId);
							}
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
