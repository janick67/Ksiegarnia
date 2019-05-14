
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton bOrder, bDisBook,bDisBaseBook,bDisOrder,bDisUser,bDelBook,bDelUser,bDelOrder,bAddNewBook,bAddBook,bEditBook,bLogOut;
	private JLabel lHello,lBook,lUser,lOrder;

	
	public InterfaceAdmin()
	{
		
		setSize(800,600);
		setTitle("Panel administracyjny");
		setLayout(null);
		
		lHello = new JLabel("Witaj w panelu administratora");
		lHello.setBounds(300, 20, 200, 30);
		add(lHello);
		
		lBook = new JLabel("Zarz¹dzanie ksi¹¿kami : ");
		lBook.setBounds(50, 60, 200, 30);
		add(lBook);
		
		lOrder = new JLabel("Zarz¹dzanie zamówieniami : ");
		lOrder.setBounds(300, 60, 200, 30);
		add(lOrder);
		
		lUser = new JLabel("Zarz¹dzanie u¿ytkownikami : ");
		lUser.setBounds(550, 60, 200, 30);
		add(lUser);
		
		bDisBook = new JButton("Wyœwietl ");
		bDisBook.setBounds(50, 100, 150, 30);
		add(bDisBook);
		bDisBook.addActionListener(this);
		
		bEditBook = new JButton("Edytuj ");
		bEditBook.setBounds(50, 140, 150, 30);
		add(bEditBook);
		bEditBook.addActionListener(this);
		
		bDelBook = new JButton("Usuñ");
		bDelBook.setBounds(50, 180, 150, 30);
		add(bDelBook);
		bDelBook.addActionListener(this);
		
		bAddNewBook = new JButton("Dodaj");
		bAddNewBook.setBounds(50, 220, 150, 30);
		add(bAddNewBook);
		bAddNewBook.addActionListener(this);
		
		bAddBook = new JButton("Zwiêksz liczbê");
		bAddBook.setBounds(50, 260, 150, 30);
		add(bAddBook);
		bAddBook.addActionListener(this);
		//////
		bDisOrder = new JButton("Wyœwietl ");
		bDisOrder.setBounds(300, 100, 150, 30);
		add(bDisOrder);
		bDisOrder.addActionListener(this);
		
		bDelOrder = new JButton("Usuñ");
		bDelOrder.setBounds(300, 140, 150, 30);
		add(bDelOrder);
		bDelOrder.addActionListener(this);
		//
		bDisUser = new JButton("Wyœwietl ");
		bDisUser.setBounds(550, 100, 150, 30);
		add(bDisUser);
		bDisUser.addActionListener(this);
		
		bDelUser = new JButton("Usuñ");
		bDelUser.setBounds(550, 140, 150, 30);
		add(bDelUser);
		bDelUser.addActionListener(this);
		
		bLogOut = new JButton("Wylogowanie");
		bLogOut.setBounds(300, 450, 150, 30);
		add(bLogOut);
		bLogOut.addActionListener(this);
		

		

		
		
	}
	

	public void window() {
		InterfaceAdmin admin = new InterfaceAdmin();
		admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin.setVisible(true);	
	}
	
	
	
	
	
//	public static void main(String[] args) {
//		InterfaceAdmin admin = new InterfaceAdmin();
//		admin.window();	
//		Bookstore test = new Bookstore();
//		int max = test.MaxId();
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
		if(source == bDisBook)
		{
			DisBook book = new DisBook();
			book.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			book.setVisible(true);
			dispose();
		}
		else if(source == bEditBook)
		{
			DisBook book = new DisBook();
			book.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			book.setVisible(true);
			dispose();
		}
		else if(source == bDelBook)
		{
			DelBook book = new DelBook();
			book.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			book.setVisible(true);
			dispose();
		}
		else if(source == bAddNewBook)
		{
			AddNewBook book = new AddNewBook();
			book.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			book.setVisible(true);
			dispose();
		}
		else if(source == bAddBook)
		{
			AddBook book = new AddBook();
			book.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			book.setVisible(true);
			dispose();
		}
		else if(source == bDisOrder)
		{
			DisBook dBook = new DisBook();
			dBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dBook.setVisible(true);
			dispose();
		}
		else if(source == bDelOrder)
		{
			DisBook dBook = new DisBook();
			dBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dBook.setVisible(true);
			dispose();
		}
		else if(source == bDisUser)
		{
			DisBook dBook = new DisBook();
			dBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dBook.setVisible(true);
			dispose();
		}
		else if(source == bDelUser)
		{
			DelUser user = new DelUser();
			user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			user.setVisible(true);
			dispose();
		}
		//else
	}
}
 
