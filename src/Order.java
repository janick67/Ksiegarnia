import java.util.ArrayList;

public class Order {
	int id = 0;
	int userId;
	float totalPrice;
	String deliveryAddress;
	int status; //1 - przyjête do realizacji, 2 - zrealizowane, 3 - anulowane
	ArrayList<OrderBook> books;
	
	
	public Order(int id, int userId, float totalPrice, String deliveryAddress, int status) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.deliveryAddress = deliveryAddress;
		this.status = status;
		this.books = new ArrayList<OrderBook>();
	}

	public Order(int userId, ArrayList<OrderBook> cart, String deliveryAddress) {
		this(0,userId,0,deliveryAddress, 1);
		float total = 0;
		for (int i = 0; i < cart.size(); i++)
		{
			cart.get(i).book.addToStock(cart.get(i).amount);
			total += cart.get(i).totalNetto();
		}
		books = cart;
		this.totalPrice = total;
	}
	
	public Order(int userId, String deliveryAddress) {
		this(-1,userId,0,deliveryAddress, 1);
	}
	
	public Order(int id) { // tylko do porównywania i wyszukiwania w liœcie
		this(id,0,0,"",0);
	}
	
	public int addBook(int id, Book newone, int amount)
	{
		if (newone.addToStock(amount) != 1) return -1;
		this.totalPrice += newone.brutto;
		this.books.add(new OrderBook(id, newone,amount));	
		return 1;
	}
	
	public int addBook(Book newone)
	{
		return addBook(0,newone, 1);
	}
	
	public int writeSQL(boolean update)
	{
		if (update) {
			Mysql.insert("UPDATE orders SET status = "+this.status+" WHERE id = "+this.id);
		}
		if(this.id != 0) return -1;
		int id = Mysql.insert("INSERT INTO `orders`VALUES (default,'"+this.userId+"','"+this.totalPrice+"','"+this.deliveryAddress+"','"+this.status+"',default)");
		if (id != 0) {
			this.id = id;			
			for (int i = 0; i < books.size(); i++)
			{
				OrderBook book = books.get(i);
				int bookId = Mysql.insert("INSERT INTO `orderbooks`VALUES (default,'"+this.id+"','"+book.book.id+"','"+book.amount+"')");
				if (bookId != 0)
				{
					book.id = bookId;
				}
			}
			return id;
		}
		return -1;
	}
	
	public void writeSQL()
	{
		this.writeSQL(false);
	}
	
	public void cancel() {
		if (this.status == 1) 
		{
			this.status = 3;
			for (int i = 0; i < books.size(); i++)
			{
				books.get(i).book.addToStock(books.get(i).amount);
			}
			this.writeSQL(true);
		}
		else System.out.println("Nie mo¿na anulowaæ zamówienia ze statusem innym ni¿ \"przyjête do realizacji\"");
	}
	
	public void realize() {
		if (this.status == 1) 
		{
			this.status = 2;
			this.writeSQL(true);
		}
		else System.out.println("Nie mo¿na zrealizowaæ zamówienia ze statusem innym ni¿ \"przyjête do realizacji\"");
	}
	

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", totalPrice=" + totalPrice + ", deliveryAddress="
				+ deliveryAddress + ", books=" + books + ",status="+status+"]";
	}
	
	@Override
	public boolean equals(Object other)
    {
		if (other == this) {
		     return true;
		   }
	    if (!(other instanceof Order)) {
	      return false;
	    }
	    Order otherOrder = (Order)other;
		if (this.id != otherOrder.id)
            return false;
		return true;
    }
	
	
}
