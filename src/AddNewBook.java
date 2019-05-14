import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddNewBook extends JFrame implements ActionListener{
	private JLabel lHello,lTitle,lAuthor,lInStock,lTest;
	private JTextField tTitle,tAuthor,tInStock;
	private JButton bReturn,bConfirm;
	String title,author,tmp;
	int instock;
	AddNewBook()
	{
		setSize(800,600);
		setTitle("Dodawanie ksi¹¿ki");
		setLayout(null);
		
		lHello = new JLabel("Panel dodawania nowej ksi¹¿ki");
		lHello.setBounds(300, 20, 200, 30);
		add(lHello);
		
		lTest = new JLabel("Panel dodawania nowej ksi¹¿ki");
		lTest.setBounds(300, 500, 200, 30);
		add(lTest);
		
		lTitle = new JLabel("Tytu³");
		lTitle.setBounds(50, 100, 150, 30);
		lTitle.setHorizontalAlignment(JLabel.CENTER);
		add(lTitle);
		
		lAuthor = new JLabel("Autor");
		lAuthor.setBounds(325, 100, 150, 30);
		lAuthor.setHorizontalAlignment(JLabel.CENTER);
		add(lAuthor);
		
		lInStock = new JLabel("Iloœæ");
		lInStock.setBounds(600, 100, 150, 30);
		lInStock.setHorizontalAlignment(JLabel.CENTER);
		add(lInStock);
		
		tTitle = new JTextField();
		tTitle.setBounds(50, 150, 150, 30);
		add(tTitle);
		
		tAuthor = new JTextField();
		tAuthor.setBounds(325, 150, 150, 30);
		add(tAuthor);
		
		tInStock = new JTextField();
		tInStock.setBounds(600, 150, 150, 30);
		add(tInStock);
		
		bConfirm = new JButton("Potwierdz");
		bConfirm.setBounds(325, 300, 150, 50);
		add(bConfirm);
		bConfirm.addActionListener(this);
		
		bReturn = new JButton("Powrót");
		bReturn.setBounds(325, 450, 150, 30);
		add(bReturn);
		bReturn.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == bConfirm) 
		{
			title=tTitle.getText();
			author=tAuthor.getText();
			instock=Integer.parseInt(tInStock.getText());
			Bookstore add = new Bookstore();
			add.load();
			add.addBook(title,author,instock);
		}
		else 
			{
			InterfaceAdmin admin = new InterfaceAdmin();
			admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			admin.setVisible(true);
			dispose();
			}
			
	}

}
