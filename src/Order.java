import java.util.ArrayList;

public class Order {
	int id;
	int userId;
	int totalPrice;
	String deliveryAddress;
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Integer> orderBooksID =  new ArrayList<Integer>();
	
	
	public Order(int id, int userId, int totalPrice, String deliveryAddress) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.deliveryAddress = deliveryAddress;
		this.books = new ArrayList<Book>();
	}
	
	public Order(int userId, String deliveryAddress) {
		this(-1,userId,0,deliveryAddress);
	}
	
	public Order(int id) { // tylko do porównywania i wyszukiwania w liœcie
		this(id,0,0,"");
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
