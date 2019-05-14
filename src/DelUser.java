import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class DelUser extends JFrame implements ActionListener{
	private JLabel lHello,lTitle,lId;
	private JTextField tTitle,tId;
	private JButton bReturn,bConfirm;
	//private JComboBox cChoose;
	private JList<String> list;
	String title,author,tmp;
	int instock;
	DelUser()
	{
		setSize(800,600);
		setTitle("Usuwanie u¿ytkownika");
		setLayout(null);
		
		lHello = new JLabel("Panel usuwania u¿ytkownika");
		lHello.setBounds(0, 20, 800, 30);
		lHello.setHorizontalAlignment(JLabel.CENTER);
		add(lHello);
		
		Bookstore mbr = new Bookstore();
		int max=mbr.MaxId("Users");
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		list = new JList<String>(model);
		for(int i=0; i<=max; i++) { 
		String titleUser= mbr.fillUser(i);	
	    model.add(i,titleUser );
	    }
	
		list.setBounds(0, 100, 800, 200);
		add(list);
		
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
				//lTitle.setText("Nic nie wybrano");
			}
			else
			{
				int selected=list.getSelectedIndex();
				//lTitle.setText("wybrano "+selected);
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