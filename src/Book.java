//JAN WORWA

// Klasa Book zawiera wszystkie dane na temat pojedynczego ksi���k w systemie, w bookstore na podstawie tej klasy jest tworzona lista wszystkich ksi��ek
// Korzystaj�c z tej klasy mo�na odczyta� informacje na temat ksi��ek, ale te� tworzy� nowe i dodawa� je do bazy sql, przy pomocy metody writeSQL na gotowym obiekcie
public class Book {
	int id = 0;
	String title;
	String author;
	int instock;
	String print;
	String language;
	int year;
	float brutto;
	float netto;
	int ean;
	int page;
	
	public Book(int id, String title, String author, int instock , String printHouse,
		String lang, int year, float brutto, float netto, int ean,
		int page) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.instock = instock;
		this.print = printHouse;
		this.language = lang;
		this.year = year;
		this.brutto = brutto;
		this.netto = netto;
		this.ean = ean;
		this.page = page;
	}

	public Book(String title, String author, int instock , String printHouse,
			String lang, int year, float brutto, float netto, int ean,
			int page) {
		this(0,title,author,instock,printHouse,lang,year,brutto,netto,ean,page);
	}
	
	public Book(String title, String author, int instock)
	{
		this(0,title,author,instock,"","PL",0,0,0,0,0);
	}
	
	public Book(int id) // jedynie do por�wnywania i wyszukiwania
	{
		this(id,"","",0,"","",0,0,0,0,0);
	}
	
	//delete s�u�y jedynie do usuwania ksi��ki z bazy danych
	//returns: false - je�li u�ytkownik nie ma przypisanego id poniewa� wtedy nie wiemy kt�r� ksi��ke usun�� i prawdopodobnie nie ma jej w bazie, w przeciwnym razie true
	public boolean delete(){
		if(this.id <= 0) return false;
		Mysql.delete("DELETE FROM `books` WHERE id = "+this.id);
		return true;
	}

	//writeSQL - do zpaisu danych na bazie lub p�zniejszej aktualizacji tych danych
	//Parametry: update - true - robi jedynie update ilo�ci na bazie
	//returns: dla Update zwraca 1, przy powodzeniu zwraca id ksi�zki a przy nie powodzeniu zwraca -1	
	public int writeSQL(boolean update)
	{
		if (update) {
			Mysql.insert("UPDATE books SET instock = "+this.instock+" WHERE id = "+this.id);
		}
		if(this.id != 0) return -1;
		int id = Mysql.insert("INSERT INTO `books`VALUES (default,'"+title+"','"+author+"','"+instock+"','"+print+"','"+language+"','"+year+"','"+brutto+"','"+netto+"','"+ean+"','"+page+"')");
		if (id != 0) {
			this.id = id;
			return id;
		}
		return -1;
	}
	
	public int writeSQL()
	{
		return this.writeSQL(false);
	}
	
	
	//addToStock - s�u�y do manipulacji ilo�ci� ksi���k na stanie, wykorzystywana przy tworzeniu i anulowaniu zam�wie�
	//Parametry: value - ilo�� ksi��ek kt�re chcemy doda� lub odj��
	//returns: 1 - dodano pomy�lnie i 0 - kiedy nie mamy wystarczaj�cej ilo�ci ksi��ek na stanie
	public int addToStock(int value)
	{
		if (this.instock + value > 0) {
			this.instock += value;
			writeSQL(true);
			return 1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", tytul=" + title + ", autor=" + author + ", liczbaSztuk=" + instock
				+"]";
	}
	
	
	//por�wnuje obiekty jedynie po ich ID
	@Override
	public boolean equals(Object other)
    {
		if (other == this) {
		     return true;
		   }
	    if (!(other instanceof Book)) {
	      return false;
	    }
	    Book otherBook = (Book)other;
		if (this.id != otherBook.id)
            return false;
		return true;
    }
}
