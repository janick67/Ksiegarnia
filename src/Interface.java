
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


//////
public class Interface extends JFrame implements ActionListener{

	
	private static final long serialVersionUID = 1L;
	private JLabel lPrice,lQuantity,lHello,lMenu,lList;
	private JButton bOrder, bLogin,bSearch;
	private JList<String> list;

	
	public Interface()
	{
		
		setSize(800,600);
		setTitle("Ksiêgarnia");
		setLayout(null);
		
		lQuantity = new JLabel("");
		lQuantity.setBounds(600, 75, 150, 20);
		add(lQuantity);
		
		lHello = new JLabel("Witaj w ksiêgarni!");
		lHello.setFont(lHello.getFont().deriveFont(15.0f));
		lHello.setBounds(20, 20, 150, 20);
		add(lHello);
		
		lPrice = new JLabel("");
		lPrice.setBounds(600, 105, 150, 20);
		add(lPrice);
		
		lMenu = new JLabel("Menu");
		lMenu.setBounds(30, 100, 150, 28);
		lMenu.setFont(lMenu.getFont().deriveFont(20.0f));
		add(lMenu);
		
		lList = new JLabel("Dostêpne pozycje");
		lList.setFont(lList.getFont().deriveFont(13.0f));
		lList.setBounds(380, 50, 150, 28);
		add(lList);
	
		bOrder = new JButton("Zamów ksi¹¿kê");
		bOrder.setBounds(30, 130, 140, 30);
		add(bOrder);
		bOrder.addActionListener(this);
		
		bLogin = new JButton("Zaloguj siê");
		bLogin.setBounds(30, 170, 140, 30);
		add(bLogin);
		bLogin.addActionListener(this);
		
		bSearch = new JButton("Wyszukaj");
		bSearch.setBounds(30, 210, 140, 30);
		add(bSearch);
		bSearch.addActionListener(this);
		
		
		int max=maxBookId();		
		DefaultListModel<String> model = new DefaultListModel<String>();
		list = new JList<String>(model);
		
		for(int i=0; i<=max; i++) { 
		String titleAuthor = fillBook(i);
		model.add(i,titleAuthor);
	    }
	
		list.setBounds(300, 80, 300, 150);
		add(list);
		
		 ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		      
		        boolean adjust = listSelectionEvent.getValueIsAdjusting();
		        
		        if (!adjust) {
		          JList list = (JList) listSelectionEvent.getSource();
		          int selections[] = list.getSelectedIndices();
		       
		          for (int i = 0, n = selections.length; i < n; i++)
		          	{
		        	  Bookstore mbr = new Bookstore();
		      		  mbr.load();	
		      	
		      		  Book mybook = mbr.findBookById(selections[i]);
		      		  int q=mybook.instock; 
		      		  float price = mybook.netto;
		      		  		lQuantity.setText("Dostêpna iloœæ: "+q);
		      		  		lPrice.setText("Cena: "+price);
		    		} 
		        }
		      }
		    };
		   list.addListSelectionListener(listSelectionListener);	   
	}
	
	
public String findBookByName(String name) {
		
		Bookstore mbr = new Bookstore();
		mbr.load();
		String info = null;
		
			for(int i=1; i<mbr.books.size(); i++) 
				{
					Book book = mbr.findBookById(i);
					if(name.equals(book.title)){
					info= "Mamy tak¹ ksi¹¿kê.";
				}else 
					{
						info = "Nie mamy takiej ksi¹¿ki.";
					}
				}return info;
			}
		
	
		  
public String fillBook(int u)
	{
		Bookstore mbr = new Bookstore();
		mbr.load();	
		Book mybook = mbr.findBookById(u);
		
		if(mybook != null) return(mybook.author+"  --  "+mybook.title); 
		return "";
	}
	
public String fillBookExtended(int u)
	{
		Bookstore mbr = new Bookstore();
		mbr.load();	
		Book my = mbr.findBookById(u);
		
		if(my != null) return(my.author+"  --  "+my.title+"  Cena: "+my.netto+"  Szt. "+my.instock); 
		return "";
	}
	
	
public int  maxBookId(){
		Bookstore mbr = new Bookstore();
		mbr.load();
		Iterator<Book> iterator = mbr.books.iterator();
		int max=0;
		while(iterator.hasNext()) {
			Book value = iterator.next();
			max= value.id;
			}
		return max;
	}
	
	
public void window() {
		Interface window = new Interface();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	
public static void main(String[] args) {
		Bookstore mbr = new Bookstore();	
		//mbr.addToCart(mbr.books.get(i),1);		//dodawanie ksi¹¿ek do koszyka
		//	mbr.cartOrder(mbr.users.get(1),"Sów");	//wysy³anie zamówienia
		//mbr.deleteBook(book)// test ¿e dzia³a wyszukiwanie u¿ytkownika, tak samo jest z wyszukiwaniem ksiazki
		//System.out.print(mbr.findUserById(6));
//		System.out.println();
//		System.out.print(mbr.findBookById(2));
//		System.out.println();						// test ksiazki
//		System.out.print(mbr.fill(2));
//		System.out.println();
		//mbr.addUser("oooo", "oooso", "we","email",false); // test dodawnia ksiazki
		//mbr.addUser(username, password, email, address, isadmin);
		//System.out.print(mbr.books);	
		
		Interface window = new Interface();
		window.window();
		
}
	


@Override
public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
			if(source == bLogin)
				{
					Login log = new Login();
					log.login();
					dispose();
				} 
			if(source == bOrder) 
				{
				int answer = JOptionPane.showConfirmDialog(rootPane , "Aby z³o¿yæ zamówienie musisz siê zalogowaæ!","Uwaga",JOptionPane.YES_NO_OPTION);
					if(JOptionPane.YES_OPTION == answer) 
						{
						Login log = new Login();
						log.login();
						dispose();
						}
				}
		if(source == bSearch ) 
			{
			Interface window = new Interface();
			String name= JOptionPane.showInputDialog(rootPane,"Podaj tytu³ ksi¹¿ki.");
			String information =window.findBookByName(name);
			JOptionPane.showMessageDialog(rootPane, information);
			}
	}
}
 


