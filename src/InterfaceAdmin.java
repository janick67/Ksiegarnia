
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InterfaceAdmin extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private JButton bBook, bOrder,bUser,bLogOut;
	private JLabel lHello,lBook,lUser,lOrder;

	
	public InterfaceAdmin()
	{
		
		setSize(800,600);
		setTitle("PANEL ADMININSTRACYJNY");
		setLayout(null);
		
		lHello = new JLabel("PANEL ADMININSTRACYJNY");
		lHello.setBounds(300, 20, 200, 30);
		add(lHello);
		
		bBook = new JButton("KSI¥¯KI ");
		bBook.setBounds(40, 100, 200, 200);
		add(bBook);
		bBook.addActionListener(this);

		bOrder = new JButton("ZAMÓWIENIA ");
		bOrder.setBounds(290, 100, 200, 200);
		add(bOrder);
		bOrder.addActionListener(this);

		bUser = new JButton("U¯YTKOWNICY ");
		bUser.setBounds(540, 100, 200, 200);
		add(bUser);
		bUser.addActionListener(this);

		bLogOut = new JButton("Wylogowanie");
		bLogOut.setBounds(300, 450, 150, 30);
		add(bLogOut);
		bLogOut.addActionListener(this);
		

		

		
		
	}
	
	

	public void window() {
		InterfaceAdmin admin = new InterfaceAdmin();
		admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin.setVisible(true);	
//		Bookstore mbr = new Bookstore();	
//		mbr.load();
		//mbr.deleteBook(mbr.findBookById(1));
	}
	
	public String fillBook(int u)
	{
		Bookstore mbr = new Bookstore();
		mbr.load();	
		Book my = mbr.findBookById(u);
		
		if(my != null) return(my.id+"  -  "+my.title+"  -  "+my.author+"  -  "+my.instock); 
		return "";
	}
	public String fillBookId(int u)
	{
		Bookstore mbr = new Bookstore();
		mbr.load();	
		Book my = mbr.findBookById(u);
		
		if(my != null) return(""+my.id); 
		return "";
	}
	public String fillBookTitle(int u)
	{
		Bookstore mbr = new Bookstore();
		mbr.load();	
		Book my = mbr.findBookById(u);
		
		if(my != null) return(my.title); 
		return "";
	}
	public String fillAuthor(int u)
	{
		Bookstore mbr = new Bookstore();
		mbr.load();	
		Book my = mbr.findBookById(u);
		
		if(my != null) return(my.author); 
		return "";
	}
	public String fillBookInstock(int u)
	{
		Bookstore mbr = new Bookstore();
		mbr.load();	
		Book my = mbr.findBookById(u);
		
		if(my != null) return(""+my.instock); 
		return "";
	}
	
	public int  maxBookId(){
		Bookstore mbr = new Bookstore();
		mbr.load();
		Iterator<Book> iterator = mbr.books.iterator();
		int max=0;
		while(iterator.hasNext()) {
			Book wartosc = iterator.next();
			max= wartosc.id;
			}
		return max;
	}
	
	public String fillUser(int u)
	{
		Bookstore mbr = new Bookstore();
		mbr.load();	
		User my = mbr.findUserById(u);
		
		if(my != null) return(my.id+"  -  "+my.username); 
		return "";
	}
	
	
	public int  maxUserId(){
		Bookstore mbr = new Bookstore();
		mbr.load();
		Iterator<User> iterator = mbr.users.iterator();
		int max=0;
		while(iterator.hasNext()) {
			User wartosc = iterator.next();
			max= wartosc.id;
			}
		return max;
	}
	
	public String fillOrder(int u)
	{
		Bookstore mbr = new Bookstore();
		mbr.load();	
		Order my = mbr.findOrderById(u);
		//String dane = mbr.findOrderById(id);
		if(my != null) return(my.id+"  -  "+my.userId+"  -  "+my.totalPrice+"  -  "+my.deliveryAddress); 
		return "";
	}
	
	
	public int  maxOrderId(){
		Bookstore mbr = new Bookstore();
		mbr.load();
		Iterator<User> iterator = mbr.users.iterator();
		int max=0;
		while(iterator.hasNext()) {
			User wartosc = iterator.next();
			max= wartosc.id;
			}
		return max;
	}
	
	
	
	
	
//	public static void main(String[] args) {
//		InterfaceAdmin admin = new InterfaceAdmin();
//		admin.window();	
//		Bookstore test = new Bookstore();
//		test.load();
////		for(int i=0;i<max;i++)
////		{
////		System.out.println(test.books.get(i));
////		}
//		
//	}
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == bBook)
		{
			AdminBook book = new AdminBook();
			book.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			book.setVisible(true);
			dispose();
		}

		else if(source == bOrder)
		{
			AdminOrder order = new AdminOrder();
			order.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			order.setVisible(true);
			dispose();
		}

		else if(source == bUser)
		{
			AdminUser user = new AdminUser();
			user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			user.setVisible(true);
			dispose();
		}

		else
		{
			Bookstore log = new Bookstore();
			Interface logout = new Interface();
			logout.window();
			log.logout();
			dispose();
		}
	}
}

 
