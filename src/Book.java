public class Book {
	int id;
	String title;
	String author;
	int instock;
	String wydawnictwo;
	String dataUkazaniaWKsiegarni;
	String jezyk;
	int rokWydania;
	float cenaBrutto;
	float cenaNetto;
	int numerKatalogowy;
	String ISBN;
	int EAN;
	int liczbaStron;
	
	public Book(int id, String title, String author, int instock , String wydawnictwo, String dataUkazaniaWKsiegarni,
		String jezyk, int rokWydania, float cenaBrutto, float cenaNetto, int numerKatalogowy, String iSBN, int eAN,
		int liczbaStron) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.instock = instock;
		this.wydawnictwo = wydawnictwo;
		this.dataUkazaniaWKsiegarni = dataUkazaniaWKsiegarni;
		this.jezyk = jezyk;
		this.rokWydania = rokWydania;
		this.cenaBrutto = cenaBrutto;
		this.cenaNetto = cenaNetto;
		this.numerKatalogowy = numerKatalogowy;
		this.ISBN = iSBN;
		this.EAN = eAN;
		this.liczbaStron = liczbaStron;
	}
	
	public Book(String title, String author, int instock)
	{
		this(0,title,author,instock,"","","PL",0,0,0,0,"",0,0);
	}
	
	public Book(int id)
	{
		this(id,"","",0,"","","",0,0,0,0,"",0,0);
	}

	public int writeSQL()
	{
		int id = Mysql.insert("INSERT INTO `books`VALUES (default,'"+title+"','"+author+"','"+instock+"')");
		if (id != 0) {
			this.id = id;
			return id;
		}
		return -1;
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
