import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class DisBook extends JFrame implements ActionListener {
	JLabel lBook,lId,lTitle,lAuthor,lInStock,lBrutto;
	JButton bReturn;
	private JList<String> list;

	
	DisBook()
	{
		setSize(800,600);
		setTitle("Lista ksi¹¿ek");
		setLayout(null);
		
		lId = new JLabel("ID");
		lId.setBounds(20, 40, 152, 30);
		add(lId);
		
		lTitle = new JLabel("Tytu³");
		lTitle.setBounds(172, 40, 152, 30);
		add(lTitle);
		
		lAuthor = new JLabel("Autor");
		lAuthor.setBounds(324, 40, 152, 30);
		add(lAuthor);
		
		lInStock = new JLabel("Dostêpnoœæ w szt");
		lInStock.setBounds(476, 40, 152, 30);
		add(lInStock);
		
		lBrutto = new JLabel("Cena brutto");
		lBrutto.setBounds(628, 40, 152, 30);
		add(lBrutto);
		
		bReturn = new JButton("Powrót");
		bReturn.setBounds(300, 450, 150, 30);
		add(bReturn);
		bReturn.addActionListener(this);
		
		Bookstore mbr = new Bookstore();
	Interface a = new Interface();
	
		int max=a.maxBookId();//tu by³a stara funkcja	
		DefaultListModel<String> model = new DefaultListModel<String>();
		list = new JList<String>(model);
		for(int i=0; i<=max; i++) { 
		String titleAuthor= a.fillBook(i);	//tu by³a stara funkcja
	    model.add(i,titleAuthor );
	    }
	
		list.setBounds(350, 30, 300, 200);
		add(list);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		InterfaceAdmin admin = new InterfaceAdmin();
		admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin.setVisible(true);
		dispose();
		
	}

}
