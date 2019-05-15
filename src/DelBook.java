import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class DelBook extends JFrame implements ActionListener{
	private JLabel lHello,lTitle,lId;
	private JTextField tTitle,tId;
	private JButton bReturn,bConfirm;
	//private JComboBox cChoose;
	private JList<String> list;
	String title,author,tmp;
	int instock;
	DelBook()
	{
		setSize(800,600);
		setTitle("Usuwanie ksi¹¿ki");
		setLayout(null);
		
		lHello = new JLabel("Panel usuwania ksi¹¿ki");
		lHello.setBounds(0, 20, 800, 30);
		lHello.setHorizontalAlignment(JLabel.CENTER);
		add(lHello);
		
//		Bookstore mbr = new Bookstore();
//		int max=mbr.MaxId();
//		
//		DefaultListModel<String> model = new DefaultListModel<String>();
//		list = new JList<String>(model);
//		for(int i=0; i<=max; i++) { 
//		String titleAuthor= mbr.fill(i);	
//	    model.add(i,titleAuthor );
//	    }
//	
		list.setBounds(0, 100, 800, 200);
		add(list);
		//list.addActionListener(this);
		
//		cChoose = new JComboBox();
//		cChoose.setBounds(0, 50, 600, 30);
//		add(cChoose);
				
		lTitle = new JLabel("Test");
		lTitle.setBounds(50, 10, 150, 30);
		lTitle.setHorizontalAlignment(JLabel.CENTER);
		add(lTitle);
//		
//		lAuthor = new JLabel("Autor");
//		lAuthor.setBounds(325, 100, 150, 30);
//		lAuthor.setHorizontalAlignment(JLabel.CENTER);
//		add(lAuthor);
//		
//		lInStock = new JLabel("Iloœæ");
//		lInStock.setBounds(600, 100, 150, 30);
//		lInStock.setHorizontalAlignment(JLabel.CENTER);
//		add(lInStock);
//		
//		tTitle = new JTextField();
//		tTitle.setBounds(50, 150, 150, 30);
//		add(tTitle);
//		
//		tAuthor = new JTextField();
//		tAuthor.setBounds(325, 150, 150, 30);
//		add(tAuthor);
//		
//		tInStock = new JTextField();
//		tInStock.setBounds(600, 150, 150, 30);
//		add(tInStock);
//		
		bConfirm = new JButton("Potwierdz");
		bConfirm.setBounds(325, 350, 150, 50);
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
			if
			(list.getSelectedIndex() == -1)
			{
				lTitle.setText("Nic nie wybrano");
			}
			else
			{
				int selected=list.getSelectedIndex();
				lTitle.setText("wybrano "+selected);
			}
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



