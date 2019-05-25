//Mateusz Zygmunt Klasa odpowiedzialna za dodawanie ksi¹¿ek przez adminna
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

	public class AddNewBook extends JFrame implements ActionListener{
	private JLabel lHello,lTitle,lAuthor,lInStock,lTest;
	private JTextField tTitle,tTitleB,tAuthor,tInStock;
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
		
		lTest = new JLabel();
		lTest.setBounds(0, 500, 800, 30);
		lTest.setHorizontalAlignment(JLabel.CENTER);
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
		
		tTitleB = new JTextField();
		tTitleB.setBounds(50, 150, 150, 30);
		add(tTitleB);
		
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
			if(tTitleB.getText().equals("") ||tAuthor.getText().equals("")||tInStock.getText().equals(""))
			{
				lTest.setText("Popraw dane !");
			}
			//lTest.setText("Popraw dane !");
			else
			{
			title=tTitleB.getText();
			author=tAuthor.getText();
			instock=Integer.parseInt(tInStock.getText());
			
			Bookstore add = new Bookstore();
			add.load();
			add.addBook(title,author,instock);
			
			AdminBook admin = new AdminBook();
			admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			admin.setVisible(true);
			dispose();
			}
//			if(tTitle.equals(""))
//			{
//				lTest.setText("Popraw dane !");
//				//add.addBook(title,author,instock);
//			}
//			 
			

		}
		else 
			{
			AdminBook admin = new AdminBook();
			admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			admin.setVisible(true);
			dispose();
			}
			
	}

}
