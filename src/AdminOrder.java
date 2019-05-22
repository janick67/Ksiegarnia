import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class AdminOrder extends JFrame implements ActionListener {
	JLabel lTitle,lHello;
	JButton bReturn,bConfirm;
	private JList<String> listA,listT;

	
	AdminOrder()
	{
		setSize(800,600);
		setTitle("ZARZ¥DZANIE ZAMÓWIENIAMI");
		setLayout(null);
		
		InterfaceAdmin a = new InterfaceAdmin();

		int max=a.maxOrderId();	
		DefaultListModel<String> model1 = new DefaultListModel<String>();
		listA = new JList<String>(model1);
		for(int i=0; i<=max; i++) { 
		String order= a.fillOrder(i);	
	    model1.add(i,order );
	    }
		
		lHello = new JLabel("ZARZ¥DZANIE ZAMÓWIENIAMI");
		lHello.setBounds(0, 20, 800, 30);
		lHello.setHorizontalAlignment(JLabel.CENTER);
		add(lHello);

		listA.setBounds(0, 100, 800, 200);
		add(listA);
		
		bConfirm = new JButton("USUÑ");
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
				lTitle.setText("Nic nie wybrano");
			}
			else
			{
//				int selected=listA.getSelectedIndex();
//				lTitle.setText("wybrano "+selected);
//				Bookstore mbr = new Bookstore();
//				mbr.load();
//				mbr.deleteOrder(mbr.findOrderById(selected));
//				
//				AdminOrder order = new AdminOrder();
//				order.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				order.setVisible(true);
//				dispose();
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