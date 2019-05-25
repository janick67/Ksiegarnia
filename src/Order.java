import java.util.ArrayList;
//JAN WORWA

// Klasa zawiera informacje o nag��wku zam�wienia i po�rednio o pozycjach na tym zam�wieniu poprzez list� OrderBook
// Zam�wienia stworzone przy pomocy tej klasy mo�na potwierdzi� lub anulowa�
// Dodanie zam�wienia odejmuje ksi��k� ze stanu a anulowanie zam�winia przywraca j� na stan

public class Order {
	int id = 0;
	int userId;
	float totalPrice;	//wyliczane przy dodawaniu ksi��ek do listy
	String deliveryAddress;
	int status; //1 - przyj�te do realizacji, 2 - zrealizowane, 3 - anulowane
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

	public Order(int userId, ArrayList<OrderBook> cart, String deliveryAddress) {	// tworzenie zam�wienia na podstawie koszyka
		this(0,userId,0,deliveryAddress, 1);
		float total = 0;
		for (int i = 0; i < cart.size(); i++)
		{
			cart.get(i).book.addToStock(-cart.get(i).amount);	//dodanie ksi��ki do zam�wienia odejmuje j� ze stanu
			total += cart.get(i).totalNetto();
		}
		books = cart;
		this.totalPrice = total;
	}
	
	public Order(int userId, String deliveryAddress) {
		this(-1,userId,0,deliveryAddress, 1);
	}
	
	public Order(int id) { // tylko do por�wnywania i wyszukiwania w li�cie
		this(id,0,0,"",0);
	}
	
	
	//addBook - s�u�y do dodania pojedynczej pozycji do zam�wienia, wykorzystywane po wczytaniu zam�wie� z bazy
	//Parametry: id - id pozycji dla zam�wienia na bazie w tabeli orderBooks
	//returns: -1 - nie dodano ksi��ki, 1 - dodano
	public int addBook(int id, Book newone, int amount)
	{
		if (newone.addToStock(-amount) != 1) return -1;
		this.totalPrice += newone.brutto;
		this.books.add(new OrderBook(id, newone,amount));	
		return 1;
	}
	
	public int addBook(Book newone)
	{
		return addBook(0,newone, 1);
	}
	
	//writeSQL - do zpaisu danych na bazie lub p�zniejszej aktualizacji tych danych, zapisuje najpierw do orders a nast�pnie ka�d� pozycje do orderBooks
	//Parametry: update - true - robi jedynie update statusu na bazie
	//returns: dla Update zwraca 1, przy powodzeniu zwraca id zam�wienia a przy nie powodzeniu zwraca -1
	public int writeSQL(boolean update)
	{
		if (update) {
			Mysql.insert("UPDATE orders SET status = "+this.status+" WHERE id = "+this.id);
			return 1;
		}
		if(this.id != 0) return -1;
		int id = Mysql.insert("INSERT INTO `orders`VALUES (default,'"+this.userId+"','"+this.totalPrice+"','"+this.deliveryAddress+"',default,'"+this.status+"')");
		if (id != 0) {
			this.id = id;			
			for (int i = 0; i < books.size(); i++)
			{
				OrderBook book = books.get(i);
				int bookId = Mysql.insert("INSERT INTO `orderbooks` VALUES (default,'"+this.id+"','"+book.book.id+"','"+book.amount+"')");
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
	
	
	//cancel - s�u�y do anulowania zam�wienia, mo�na anulowa� jedynie zam�wienia przyj�te do realizacji, przywraca zam�wione ksi��ki na stan magazynowy
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
		else System.out.println("Nie mo�na anulowa� zam�wienia ze statusem innym ni� \"przyj�te do realizacji\"");
	}
	
	//realize - s�u�y do oznaczenia zam�wienia jako zrealizowane, zrealizowa� mo�na jedynie zam�wienie ze statusem przyj�te do realizacji
	public void realize() {
		if (this.status == 1) 
		{
			this.status = 2;
			this.writeSQL(true);
		}
		else System.out.println("Nie mo�na zrealizowa� zam�wienia ze statusem innym ni� \"przyj�te do realizacji\"");
	}
	

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", totalPrice=" + totalPrice + ", deliveryAddress="
				+ deliveryAddress + ", books=" + books + ",status="+status+"]";
	}
	
	
	// por�wnuje obiekty po ID
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
