import java.util.ArrayList;

public class Order {
	int id = 0;
	int userId;
	float totalPrice;
	String deliveryAddress;
	ArrayList<OrderBook> books;
	
	
	public Order(int id, int userId, float totalPrice, String deliveryAddress) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.deliveryAddress = deliveryAddress;
		this.books = new ArrayList<OrderBook>();
	}

	public Order(int userId, ArrayList<OrderBook> cart, String deliveryAddress) {
		this(-1,userId,0,deliveryAddress);
		float total = 0;
		for (int i = 0; i < cart.size(); i++)
		{
			total += cart.get(i).totalNetto();
		}
		this.totalPrice = total;
	}
	
	public Order(int userId, String deliveryAddress) {
		this(-1,userId,0,deliveryAddress);
	}
	
	public Order(int id) { // tylko do porównywania i wyszukiwania w liœcie
		this(id,0,0,"");
	}
	
	public void addBook(int id, Book newone, int amount)
	{
		this.totalPrice += newone.netto;
		this.books.add(new OrderBook(id, newone,amount));	
	}
	
	public void addBook(Book newone)
	{
		addBook(0,newone, 1);
	}
	
	
	public int writeSQL()
	{
		if(this.id != 0) return -1;
		int id = Mysql.insert("INSERT INTO `orders`VALUES (default,'"+this.userId+"','"+this.totalPrice+"','"+this.deliveryAddress+"')");
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
	

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", totalPrice=" + totalPrice + ", deliveryAddress="
				+ deliveryAddress + ", books=" + books + "]";
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
