//Mateusz Zygmunt klasa pozwalająca adminowi zarządzać użytkownikiem

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class AdminUser extends JFrame implements ActionListener {
	private JLabel lTitle,lHello;
	private JButton bReturn,bConfirm;
	private JList<String> listA;
	private JOptionPane oALert;

	
	AdminUser()
	{
		setSize(800,600);
		setTitle("ZARZĄDZANIE UŻYTKOWNIKAMI");
		setLayout(null);
		
		InterfaceAdmin a = new InterfaceAdmin();

		int max=a.maxUserId();	
		DefaultListModel<String> model1 = new DefaultListModel<String>();
		listA = new JList<String>(model1);
		for(int i=0; i<=max; i++) { 
		String user= a.fillUser(i);	
	    model1.add(i,user );
	    }
		
		lHello = new JLabel("ZARZĄDZANIE UŻYTKOWNIKAMI");
		lHello.setBounds(0, 20, 800, 30);
		lHello.setHorizontalAlignment(JLabel.CENTER);
		add(lHello);

		listA.setBounds(300, 100, 150, 200);
		add(listA);
		
		bConfirm = new JButton("USUŃ");
		bConfirm.setBounds(300, 300, 150, 30);
		add(bConfirm);
		bConfirm.addActionListener(this);
		
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
			(listA.getSelectedIndex() == -1)
			{
				JOptionPane.showMessageDialog(oALert, "Proszę zaznaczyć użytkownika do usunięcia.");
			}
			else
			{
				int selected=listA.getSelectedIndex();
				Bookstore mbr = new Bookstore();
				mbr.load();
				mbr.deleteUser(mbr.findUserById(selected));
				
				AdminUser user = new AdminUser();
				user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				user.setVisible(true);
				dispose();
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