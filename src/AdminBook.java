//Mateusz Zygmunt klasa pozwalająca adminowi zarządzać książkami
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class AdminBook extends JFrame implements ActionListener {
	private JLabel lTitle,lHello,lId,lTitleB,lAuthor,lInstock;
	private JButton bReturn,bConfirm,bAdd;
	private JOptionPane oALert;
	private JList<String> listA,listB,listC,listD,listT;

	
	AdminBook()
	{
		setSize(800,600);
		setTitle("ZARZĄDZANIE KSIĄŻKAMI");
		setLayout(null);
		
		InterfaceAdmin a = new InterfaceAdmin();
		int max=a.maxBookId();
		
		DefaultListModel<String> model1 = new DefaultListModel<String>();
		listA = new JList<String>(model1);
		for(int i=0; i<=max; i++) { 
		String book= a.fillBookId(i);	
	    model1.add(i,book );
	    }
		
		DefaultListModel<String> model2 = new DefaultListModel<String>();
		listB = new JList<String>(model2);
		for(int i=0; i<=max; i++) { 
		String book= a.fillBookTitle(i);	
	    model2.add(i,book );
	    }
		
		DefaultListModel<String> model3 = new DefaultListModel<String>();
		listC = new JList<String>(model3);
		for(int i=0; i<=max; i++) { 
		String book= a.fillAuthor(i);	
	    model3.add(i,book );
	    }
		
		DefaultListModel<String> model4 = new DefaultListModel<String>();
		listD = new JList<String>(model4);
		for(int i=0; i<=max; i++) { 
		String book= a.fillBookInstock(i);	
	    model4.add(i,book );
	    }
		
//	//	int max=a.maxBookId();	
//	//	DefaultListModel<String> model1 = new DefaultListModel<String>();
//		listA = new JList<String>(model1);
//		for(int i=0; i<=max; i++) { 
//		String book= a.fillBook(i);	
//	    model1.add(i,book );
//	    }
		
		lHello = new JLabel("ZARZĄDZANIE KSIĄŻKAMI");
		lHello.setBounds(0, 20, 800, 30);
		lHello.setHorizontalAlignment(JLabel.CENTER);
		add(lHello);

		lId = new JLabel("ID");
		lId.setBounds(0, 50, 200, 30);
		add(lId);
		
		lTitleB = new JLabel("Tytuł");
		lTitleB.setBounds(200, 50, 200, 30);
		add(lTitleB);
		
		lAuthor = new JLabel("Autor");
		lAuthor.setBounds(400, 50, 200, 30);
		add(lAuthor);
		
		lInstock = new JLabel("Dostępność w szt");
		lInstock.setBounds(600, 50, 200, 30);
		add(lInstock);
		
		listA.setBounds(0, 100, 200, 200);
		add(listA);
		listB.setBounds(200, 100, 200, 200);
		add(listB);
		listC.setBounds(400, 100, 200, 200);
		add(listC);
		listD.setBounds(600, 100, 200, 200);
		add(listD);
		
		bConfirm = new JButton("USUŃ");
		bConfirm.setBounds(300, 300, 150, 30);
		add(bConfirm);
		bConfirm.addActionListener(this);
		
		bAdd = new JButton("DODAJ KSIĄŻKĘ");
		bAdd.setBounds(300, 330, 150, 30);
		add(bAdd);
		bAdd.addActionListener(this);
		
		lTitle = new JLabel();
		lTitle.setBounds(300, 350, 150, 30);
		lTitle.setHorizontalAlignment(JLabel.CENTER);
		add(lTitle);
		
		bReturn = new JButton("Powrót");
		bReturn.setBounds(300, 450, 150, 30);
		add(bReturn);
		bReturn.addActionListener(this);
		
		

	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source == bConfirm) 
		{
			if
			(listA.getSelectedIndex() == -1 && listB.getSelectedIndex() == -1 )
			{
				//lTitle.setText("Nic nie wybrano");
				//Component oALert = null;
				JOptionPane.showMessageDialog(oALert, "Proszę zaznaczyć książkę do usunięcia.");
			}
			else
			{
				int selected;
				if(listA.getSelectedIndex() != -1)
				{
					 selected=listA.getSelectedIndex();
				}
				else
				{
				 selected=listB.getSelectedIndex();
				}
				//lTitle.setText("wybrano "+selected);
				Bookstore mbr = new Bookstore();
				mbr.load();
				mbr.deleteBook(mbr.findBookById(selected));
				
				AdminBook book = new AdminBook();
				book.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				book.setVisible(true);
				dispose();
			}
		}
		else if(source == bAdd) 
		{
			AddNewBook book = new AddNewBook();
			book.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			book.setVisible(true);
			dispose();
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