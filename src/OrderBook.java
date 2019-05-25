//JAN WORWA

// Klasa zawiera szczeg�ly na temat pozycji zam�wienia, czyli id na bazie, ksi��k� i jej ilo��
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
	
	// wylicza cen� netto za ca�� pozycj�
	public float totalNetto()
	{
		return book.netto*amount;
	}

	
	@Override
	public String toString() {
		return "OrderBook [id=" + id + ", book=" + book + ", amount=" + amount + "]";
	}
	
	
	//por�enuje po id
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

