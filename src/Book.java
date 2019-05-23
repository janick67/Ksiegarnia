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
	
	public Book(int id)
	{
		this(id,"","",0,"","",0,0,0,0,0);
	}
	
	public boolean delete(){
		if(this.id <= 0) return false;
		Mysql.delete("DELETE FROM `books` WHERE id = "+this.id);
		return true;
	}

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
	
	public int addToStock(int value)
	{
		if (this.instock - value > 0) {
			this.instock -= value;
			writeSQL(true);
			return 1;
		}
		return 0;
	}
	
	public int writeSQL()
	{
		return this.writeSQL(false);
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", tytul=" + title + ", autor=" + author + ", liczbaSztuk=" + instock
				+"]";
	}
	
	
	
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
