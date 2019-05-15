import java.awt.Component;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.acl.LastOwnerException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//import com.sun.glass.ui.Window;

public class Interface extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lQuantity,lHello,lMenu;
	private JButton bOrder, bLogin;
	private JList<String> list;

	
	public Interface()
	{
		
		setSize(800,600);
		setTitle("Ksiêgarnia");
		setLayout(null);
		
	
		lQuantity = new JLabel("");
		lQuantity.setBounds(560, 25, 150, 20);
		add(lQuantity);
		
		lHello = new JLabel("Witaj w ksiêgarni!");
		lHello.setBounds(20, 20, 150, 20);
		add(lHello);
		
		lMenu = new JLabel("Menu");
		lMenu.setBounds(30, 70, 150, 28);
		lMenu.setFont(lMenu.getFont().deriveFont(20.0f));
		add(lMenu);
		
		bOrder = new JButton("Zamów ksi¹¿kê");
		bOrder.setBounds(30, 100, 140, 30);
		add(bOrder);
		bOrder.addActionListener(this);
		
		bLogin = new JButton("Zaloguj siê");
		bLogin.setBounds(30, 130, 140, 30);
		add(bLogin);
		bLogin.addActionListener(this);
		
		Bookstore mbr = new Bookstore();
		int max=maxBookId();		
		DefaultListModel<String> model = new DefaultListModel<String>();
		list = new JList<String>(model);
		
		for(int i=0; i<=max; i++) { 
		String titleAuthor = fillBook(i);
		model.add(i,titleAuthor);
	    }
	
		list.setBounds(260, 30, 300, 150);
		add(list);
		
		 ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		      
		        boolean adjust = listSelectionEvent.getValueIsAdjusting();
		        
		        if (!adjust) {
		          JList list = (JList) listSelectionEvent.getSource();
		          int selections[] = list.getSelectedIndices();
		          Object selectionValues[] = list.getSelectedValues();
		          for (int i = 0, n = selections.length; i < n; i++) {
		        	 
		        	  Bookstore mbr = new Bookstore();
		      		mbr.load();	
		      	
		      		Book my = mbr.findBookById(selections[i]);
		      		 int q=my.instock; 
		            	lQuantity.setText("Dostêpne sztuki: "+q);
		    		} 
		    		
		        }
		      }
		    };
		   list.addListSelectionListener(listSelectionListener);	   
	}
	
		  
	public String fillBook(int u)
	{
		Bookstore mbr = new Bookstore();
		mbr.load();	
		Book my = mbr.findBookById(u);
		
		if(my != null) return(my.author+"  -  "+my.title+my.id); 
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
	
	
	public void window() {
		Interface window = new Interface();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		Bookstore mbr = new Bookstore();	
		mbr.load();											// test ¿e dzia³a wyszukiwanie u¿ytkownika, tak samo jest z wyszukiwaniem ksiazki
		//System.out.print(mbr.findUserById(6));
//		System.out.println();
//		System.out.print(mbr.findBookById(2));
//		System.out.println();						// test ksiazki
//		System.out.print(mbr.fill(2));
//		System.out.println();
		//mbr.addBook("Harry Potter", "J.K.R.", 5); // test dodawnia ksiazki
		//System.out.print(mbr.books);	
		Interface window = new Interface();
		window.window();
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if(source == bLogin)
		{
			Login log = new Login();
			log.login();
			dispose();
		} if(source == bOrder) {
				int answer = JOptionPane.showConfirmDialog(null , "Aby z³o¿yæ zamówienie musisz siê zalogowaæ!","Uwaga",JOptionPane.YES_NO_OPTION);
				if(JOptionPane.YES_OPTION == answer) {
					Login log = new Login();
					log.login();
					dispose();
				}

}
	}
}
 


