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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;


public class OrderPanel extends JFrame implements ListSelectionListener,ActionListener {
	
	private JButton bLogout,bSelect ;
	private JLabel lLogin,lPassword,lItem,lBasket,lInfo2;
	private JTextField text;
	private JPasswordField tPassword;
	private String login,password;
	private JScrollPane jp;
	private JList<String> list;
	private JList<String> list2;
	
	ArrayList<String> author= new ArrayList<String>();
	ArrayList<String> title= new ArrayList<String>();
	ArrayList<Integer> id= new ArrayList<Integer>();
	ArrayList<Float> price= new ArrayList<Float>();
	
	public OrderPanel()
	{
		setSize(800,600);
		setTitle("Ksiêgarnia");
		setLayout(null);
		
		
		
		lItem = new JLabel("Wybierz ksia¿ki");
		lItem.setBounds(85, 30, 150, 20);
		add(lItem);
		
		lBasket = new JLabel("Wybrane pozycje");
		lBasket.setBounds(525, 30, 150, 20);
		add(lBasket);

		bLogout = new JButton("Wyloguj");
		bLogout.setBounds(650, 20, 100, 20);
		add(bLogout);
		bLogout.addActionListener(this);
		
		bSelect = new JButton("Wybierz");
		bSelect .setBounds(30, 260, 140, 30);
		add(bSelect );
		bSelect .addActionListener(this);
		
		Bookstore mbr = new Bookstore();
		Interface inter = new Interface();
		int max=inter.maxBookId();	
		
		
		
		
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		DefaultListModel<String> model2 = new DefaultListModel<String>();
		
		
		list = new JList<String>(model);
		for(int i=0; i<=max; i++) { 
			String titleAuthor = inter.fillBook(i);
			
			model.add(i,titleAuthor);
			
	    }
		JScrollPane jp = new JScrollPane(list);
	    add(jp);
	   
	  
		list.setBounds(20, 50, 300, 200);
		add(list);
		list.addListSelectionListener(this);
		 list2 = new JList<String>(model2);
		list2.setBounds(400, 50, 300, 200);
  		add(list2);
  		list2.addListSelectionListener(this);

		
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			
		      private int[][] tablicas;
			private String zms[][];

			public void valueChanged(ListSelectionEvent listSelectionEvent) {
		      
		        boolean adjust = listSelectionEvent.getValueIsAdjusting();
		        
		        if (!adjust) {
		          JList list = (JList) listSelectionEvent.getSource();
		          int selections[] = list.getSelectedIndices();
		          Object selectionValues[] = list.getSelectedValues();
		         
		          for (int i = 0, n = selections.length; i < n; i++) {
		        	 String  t = (String) selectionValues[i];
		        	   t = (String) selectionValues[i];
		        	
		        		 model2.addElement(t);
		        	
		        		 int index = selections[i];
			       		
			       		  Bookstore m = new Bookstore();
			       			m.load();	
			       			Book kk = m.findBookById(index );
			       			if(kk != null)  
			       			
			       			author.add(kk.author);
			       			title.add(kk.title);
			       			id.add(kk.id);
			       			price.add(kk.brutto);
		        		
			       			System.out.print(author);
			       			System.out.println();
			       			System.out.print(title);
			       			System.out.println();
			       			System.out.print(id);
			       			System.out.println();
			       			System.out.print(price);
			       			System.out.println();
			       			
			       			float sum=0;
			    			for(Float f:price) {
			    				sum=sum+f;
			    			}
			    			System.out.println(sum);
		      	    }
		      		JScrollPane jp = new JScrollPane(list2);
		      	    add(jp);
		      	  
		      	    
		      	  add(list2);
		      	  list.clearSelection();
		    		}
		      }
		    };
		   list.addListSelectionListener(listSelectionListener);	   
	
	
	//list2
	ListSelectionListener listSelectionListener1 = new ListSelectionListener() {
		
	      public void valueChanged(ListSelectionEvent listSelectionEvent) {
	      
	        boolean adjust = listSelectionEvent.getValueIsAdjusting();
	        
	        if (!adjust) {
	          JList list2 = (JList) listSelectionEvent.getSource();
	          int selections[] = list2.getSelectedIndices();
	          
	          Object selectionValues[] = list2.getSelectedValues();
	          for (int i = 0, n = selections.length; i < n; i++) {
	        	 String  t = (String) selectionValues[i];
	        	 int j = selections[i];
	        	 int index = selections[i];
	       		 Bookstore m = new Bookstore();
	       			m.load();	
	       			Book kk = m.findBookById(index);
	       			
	       			if(kk != null) {  		
	       		author.remove(kk.id);
	       		title.remove(kk.id);
	       		id.remove(kk.id);
	       		price.remove(kk.id);
	       		
	       		model2.remove(j);
	       			System.out.print(author);
	       			System.out.println();
	     			System.out.print(title);
	     			System.out.println();
	     			System.out.print(id);
	     			System.out.println();
	     			System.out.print(price);
	     			System.out.println();
	     			float sum=0;
	    			for(Float f:price) {
	    				sum=sum+f;
	    			}
	    			System.out.println(sum);
	       			}else
	       			{
	       				model2.remove(j);
	       				author.remove(j);
	       			   title.remove(j);
	       			   id.remove(j);
	       			   price.remove(j);
	       			System.out.print(author);
		        	System.out.println();
	       			System.out.print(title);
	     			System.out.println();
	     			System.out.print(id);
	     			System.out.println();
	     			System.out.print(price);
	     			System.out.println();
	     			float sum=0;
	    			for(Float f:price) {
	    				sum=sum+f;
	    			}
	    			System.out.println(sum);
	       			}
	       				
	       		
	       			}
       			
	      	    }
	         
	      		JScrollPane jp = new JScrollPane(list2);
	      	    add(jp);
	      	 add(list2);  
	    		}      
	      
	    };
	   list2.addListSelectionListener(listSelectionListener1);	   
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
			
		}else if (source == bSelect){
			
			
	        	 
	          
		}}
	


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	
	
	
		 

