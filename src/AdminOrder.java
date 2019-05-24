import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class AdminOrder extends JFrame implements ActionListener {
	private JLabel lTitle,lHello,lId,lIdUser,lTotalPrice,lDeliveryAddress,lStatus,lTest;
	private JButton bReturn,bConfirm,bCancel;
	private JList<String> listId,listIdUser,listTotalPrice,listDeliveryAddress,listStatus,listUser;
	private JOptionPane oALert;

	
	AdminOrder()
	{
		setSize(800,600);
		setTitle("ZARZ¥DZANIE ZAMÓWIENIAMI");
		setLayout(null);
		
		InterfaceAdmin a = new InterfaceAdmin();

		int max=a.maxOrderId();	
	//	int maxUser=a.maxUserId();
		
//		DefaultListModel<String> users = new DefaultListModel<String>();
//		listUser = new JList<String>(users);
//		listUser.setSelectionMode(0);;;
//		for(int i=0; i<=maxUser; i++) { 
//		String user= a.fillUser(i);	
//	    users.add(i,user );
//	    }
		
		DefaultListModel<String> id = new DefaultListModel<String>();
		listId = new JList<String>(id);
		for(int i=0; i<=max; i++) { 
		String order= a.fillOrderId(i);	
	    id.add(i,order );
	    }
		
		DefaultListModel<String> idUser = new DefaultListModel<String>();
		listIdUser = new JList<String>(idUser);
		for(int i=0; i<=max; i++) { 
		String order= a.fillOrderIdUser(i);	
		idUser.add(i,order );
	    }
		
		DefaultListModel<String> totalPrice = new DefaultListModel<String>();
		listTotalPrice = new JList<String>(totalPrice);
		for(int i=0; i<=max; i++) { 
		String order= a.fillOrderTotalPrice(i);	
		totalPrice.add(i,order );
	    }
		
		DefaultListModel<String> deliveryAddress = new DefaultListModel<String>();
		listDeliveryAddress = new JList<String>(deliveryAddress);
		for(int i=0; i<=max; i++) { 
		String order= a.fillOrderDeliveryAddress(i);	
		deliveryAddress.add(i,order );
	    }
		
		DefaultListModel<String> status = new DefaultListModel<String>();
		listStatus = new JList<String>(status);
		for(int i=0; i<=max; i++) { 
		String order= a.fillOrderStatus(i);
		status.add(i,order );
		
	    }
		

		
		
		
		lHello = new JLabel("ZARZ¥DZANIE ZAMÓWIENIAMI");
		lHello.setBounds(0, 20, 800, 30);
		lHello.setHorizontalAlignment(JLabel.CENTER);
		add(lHello);
		
		lId=new JLabel("ID");
		lId.setBounds(0, 80, 160, 20);
		add(lId);
		
		lIdUser=new JLabel("ID urzytkownika");
		lIdUser.setBounds(160, 80, 160, 20);
		add(lIdUser);
		
		lTotalPrice=new JLabel("Wartoœæ ");
		lTotalPrice.setBounds(320, 80, 160, 20);
		add(lTotalPrice);
		
		lDeliveryAddress=new JLabel("Adres wysy³ki");
		lDeliveryAddress.setBounds(480, 80, 160, 20);
		add(lDeliveryAddress);
		
		lStatus=new JLabel("Status ");
		lStatus.setBounds(640, 80, 160, 20);
		add(lStatus);

		listId.setBounds(0, 100, 160, 200);
		add(listId);
		listIdUser.setBounds(160, 100, 160, 200);
		add(listIdUser);
		listTotalPrice.setBounds(320, 100, 160, 200);
		add(listTotalPrice);
		listDeliveryAddress.setBounds(480, 100, 160, 200);
		add(listDeliveryAddress);
		listStatus.setBounds(640, 100, 160, 200);
		add(listStatus);
		
		bConfirm = new JButton("ZATWIERDZ");
		bConfirm.setBounds(200, 300, 200, 30);
		add(bConfirm);
		bConfirm.addActionListener(this);
		
		bCancel = new JButton("ANULUJ");
		bCancel.setBounds(400, 300, 200, 30);
		add(bCancel);
		bCancel.addActionListener(this);
		
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
			(listId.getSelectedIndex() == -1)
			{
				JOptionPane.showMessageDialog(oALert, "Proszê zaznaczyæ zamówienie do potwierdzenia.");
			}
			else
			{
				InterfaceAdmin a = new InterfaceAdmin();
				//int max=a.maxOrderId();
				int selected=listId.getSelectedIndex();
				
				int id=Integer.valueOf(a.fillOrderId(selected));
				int idUser=Integer.valueOf(a.fillOrderIdUser(selected));
				float totalPrice=Float.parseFloat(a.fillOrderTotalPrice(selected));
				String adress=a.fillOrderDeliveryAddress(selected);
				int status =Integer.valueOf(a.fillOrderStatus(selected));
				Order order = new Order(id,idUser,totalPrice,adress,status);
				if(status==1)
				{
					order.realize();
				}
				else JOptionPane.showMessageDialog(oALert, "Nie mo¿na zrealizowaæ zamówienia ze statusem innym ni¿ \"przyjête do realizacji\"");
				
				
				lTitle.setText("wybrano "+a.fillOrderId(selected)+""+a.fillOrderDeliveryAddress(selected));
			
				AdminOrder order2 = new AdminOrder();
				order2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				order2.setVisible(true);
				dispose();
			}
		}
		
		else if(source == bCancel) 
		{
			if
			(listId.getSelectedIndex() == -1)
			{
				JOptionPane.showMessageDialog(oALert, "Proszê zaznaczyæ zamówienie do potwierdzenia.");
			}
			else
			{
				InterfaceAdmin a = new InterfaceAdmin();
				int max=a.maxOrderId();
				int selected=listId.getSelectedIndex();
				
				int id=Integer.valueOf(a.fillOrderId(selected));
				int idUser=Integer.valueOf(a.fillOrderIdUser(selected));
				float totalPrice=Float.parseFloat(a.fillOrderTotalPrice(selected));
				String adress=a.fillOrderDeliveryAddress(selected);
				int status =Integer.valueOf(a.fillOrderStatus(selected));
				Order order = new Order(id,idUser,totalPrice,adress,status);
				if(status==1)
				{
					order.cancel();
				}
				else JOptionPane.showMessageDialog(oALert, "Nie mo¿na zrealizowaæ zamówienia ze statusem innym ni¿ \"przyjête do realizacji\"");
				
				
				lTitle.setText("wybrano "+a.fillOrderId(selected)+""+a.fillOrderDeliveryAddress(selected));
			
				AdminOrder order2 = new AdminOrder();
				order2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				order2.setVisible(true);
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