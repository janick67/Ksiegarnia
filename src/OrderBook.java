//JAN WORWA

// Klasa zawiera szczególy na temat pozycji zamówienia, czyli id na bazie, ksi¹¿kê i jej iloœæ
public class OrderBook {
	int id = 0;
	Book book;
	int amount;
	
	public OrderBook(int id, Book book, int amount)
	{
		this.id = id;
		this.book = book;
		this.amount = amount;
	}
	
	public OrderBook(Book book, int amount)
	{
		this(0,book,amount);
	}

	public OrderBook(int id)
	{
		this(id,null,0);
	}
	
	// wylicza cenê netto za ca³¹ pozycjê
	public float totalNetto()
	{
		return book.netto*amount;
	}

	
	@Override
	public String toString() {
		return "OrderBook [id=" + id + ", book=" + book + ", amount=" + amount + "]";
	}
	
	
	//poróenuje po id
	@Override
	public boolean equals(Object other)
    {
		if (other == this) {
		     return true;
		   }
	    if (!(other instanceof OrderBook)) {
	      return false;
	    }
	    OrderBook otherOrderBook = (OrderBook)other;
		if (this.id != otherOrderBook.id)
            return false;
		return true;
    }
}

