import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class AddBook extends JFrame implements ActionListener{
	private JLabel lTitle,lNumber;
	private JTextField tValue;
	private JButton bReturn,bConfirm;
	private JList<String> list;

	AddBook()
	{
		setSize(800,600);
		setTitle("Zmiana liczby ksi¹¿ek");
		setLayout(null);
		
//		Bookstore mbr = new Bookstore();
//		//int max=mbr.MaxId();
//		
//		DefaultListModel<String> model = new DefaultListModel<String>();
//		list = new JList<String>(model);
//		for(int i=0; i<=max; i++) { 
//		String titleAuthor= mbr.fill(i);	
//	    model.add(i,titleAuthor );
//	    }
	
		list.setBounds(0, 70, 800, 200);
		add(list);
				
		lTitle = new JLabel("Wybierz ksi¹¿kê której chcesz zmieniæ liczbê");
		lTitle.setBounds(0, 40, 800, 30);
		lTitle.setHorizontalAlignment(JLabel.CENTER);
		add(lTitle);
		
		lNumber = new JLabel("Wpisz liczbê szt");
		lNumber.setBounds(0, 275, 800, 30);
		lNumber.setHorizontalAlignment(JLabel.CENTER);
		add(lNumber);
	
		tValue = new JTextField();
		tValue.setBounds(350, 300, 100, 30);
		add(tValue);
		
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



