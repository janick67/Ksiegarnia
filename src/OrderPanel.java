import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;


public class OrderPanel extends JFrame implements ListSelectionListener,ActionListener {
	
	private JButton bConfirm,bLogout;
	private JLabel lLogin,lPassword,lHello,lInfo,lInfo2;
	private JTextField text;
	private JPasswordField tPassword;
	private String login,password;
	private JScrollPane jp;
	private JList<String> list;
	
	public OrderPanel()
	{
		setSize(800,600);
		setTitle("Ksiêgarnia");
		setLayout(null);
		
		bConfirm = new JButton("Potwierdz");
		bConfirm.setBounds(230, 50, 150, 30);
		add(bConfirm);
		bConfirm.addActionListener(this);
		
		//lHello=new JLabel("Teraz mo¿esz wybraæ ksi¹¿ki");
		//lHello.setBounds(20, 20, 150, 20);
		//add(lHello);
		
		text = new JTextField("");
		text.setBounds(20, 50, 150, 20);
		add(text);
		
		
		bLogout = new JButton("Wyloguj");
		bLogout.setBounds(600, 700, 150, 20);
		add(bLogout);
		bLogout.addActionListener(this);
		
		Bookstore mbr = new Bookstore();
		int max=mbr.MaxId();
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		list = new JList<String>(model);
		for(int i=0; i<=max; i++) { 
		String titleAuthor= mbr.fill(i);	
	    model.add(i,titleAuthor );
	    }
		JScrollPane jp = new JScrollPane(list);
	    add(jp);
	    model.addElement("item1");
	    list.setVisibleRowCount(4);
		list.setBounds(400, 30, 300, 200);
		add(list);
		list.addListSelectionListener(this);
		
		 ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		      
		      text.setText(list.getSelectedValue());
		      }};
		      //  boolean adjust = listSelectionEvent.getValueIsAdjusting();
		        
		       // if (!adjust) {
		       //   JList list = (JList) listSelectionEvent.getSource();
		        //  int selections[] = list.getSelectedIndices();
		        //  Object selectionValues[] = list.getSelectedValues();
		        //  for (int i = 0, n = selections.length; i < n; i++) {
		        //    if (i == 0) {
		        //    }
		            	
		    	//	} 
		      //  }
		    //  }
		  //  };
		 //  list.addListSelectionListener(listSelectionListener);	   
	
		 }
		
	
	public void set(){
		OrderPanel order = new OrderPanel(); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object source = e.getSource();
		if(source == bLogout)
		{
			Bookstore log = new Bookstore();
			Interface logout = new Interface();
			logout.window();
			log.logout();
			dispose();
			
		}else{
		
				
				
		}
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	
	
	
		 

