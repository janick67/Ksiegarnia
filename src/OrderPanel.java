import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;


public class OrderPanel extends JFrame implements ListSelectionListener,ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bLogout,bSelect,bSelect1,bDelete ;
	private JLabel lItem,lBasket1,lBasket;
	private JTextField tAmount;
	DefaultListModel<String> model = new DefaultListModel<String>();
	DefaultListModel<String> model2 = new DefaultListModel<String>();
	
	private JList<String> list;
	private JList<String> list2;
	int userId;
	String userAdress;
	int licznik=0;
	
	
	ArrayList<Float>price= new ArrayList<Float>();
	ArrayList<Integer> il= new ArrayList<Integer>();
	ArrayList<Integer> select= new ArrayList<Integer>();
	
	public OrderPanel(int sesion,String adr)
	{
		setSize(800,600);
		setTitle("Ksiêgarnia");
		setLayout(null);
		
		this.userId= sesion;
		this.userAdress = adr;
		
		tAmount=new JTextField();
		tAmount.setBounds(200, 260, 140, 30);
		add(tAmount);
		
		lItem = new JLabel("Wybierz ksia¿ki");
		lItem.setBounds(85, 30, 150, 20);
		add(lItem);
		
		lBasket = new JLabel("Wybrane pozycje");
		lBasket.setBounds(525, 30, 150, 20);
		add(lBasket);
		
		lBasket1 = new JLabel("");
		lBasket1.setBounds(400, 400, 300, 200);
		add(lBasket1);

		bLogout = new JButton("Wyloguj");
		bLogout.setBounds(650, 20, 100, 20);
		add(bLogout);
		bLogout.addActionListener(this);
		
		bSelect = new JButton("Wybierz");
		bSelect .setBounds(30, 260, 140, 30);
		add(bSelect );
		bSelect .addActionListener(this);
		
		bSelect1 = new JButton("Wybierz1");
		bSelect1 .setBounds(30, 300, 140, 30);
		add(bSelect1 );
		bSelect1 .addActionListener(this);
		
		bDelete = new JButton("Usuñ");
		bDelete .setBounds(30, 450, 140, 30);
		add(bDelete );
		bDelete .addActionListener(this);
		
		
		Interface inter = new Interface();
		int max=inter.maxBookId();	
		
		
		
		
		list = new JList<String>(model);
		
		for(int i=0; i<=max; i++) { 
			String titleAuthorPriceAmount = inter.fillBook2(i);
			
			model.add(i,titleAuthorPriceAmount);
			
	    }
		
	   
	  
		list.setBounds(20, 50, 300, 200);
		add(list);
	list.addListSelectionListener(this);
	
		 list2 = new JList<String>(model2);
		list2.setBounds(400, 50, 300, 200);
  		add(list2);
  		list2.addListSelectionListener(this);

		
	ListSelectionListener listSelectionListener = new ListSelectionListener() {
			
		     

			public void valueChanged(ListSelectionEvent listSelectionEvent) {
		      
	        boolean adjust = listSelectionEvent.getValueIsAdjusting();
		        
		        if (!adjust) {
		          JList list = (JList) listSelectionEvent.getSource();	         
		          int selections[] = list.getSelectedIndices();
		          Object selectionValues[] = list.getSelectedValues();
		         
          for (int i = 0, n = selections.length; i < n; i++) {
		        	 String  t = (String) selectionValues[i];
		        	   t = (String) selectionValues[i];
		        	 
		        		
		        	
	      	    }
         
		      		
		      //	  list.clearSelection();
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
			        	
			        	 
			        	//model2.remove(j);
			      	    }
			      		
			      //	 add(list2);  
			    		}      
			      }
			    };
			   list2.addListSelectionListener(listSelectionListener1);	   
		}
       		
	      	    
	public void set(int id,String adr){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
      
	}

	
	

	@Override
	
	public void actionPerformed(ActionEvent e) {
	
		Object source = e.getSource();
		Bookstore mbr = new Bookstore();
		mbr.load();
	
		
		if(source == bLogout)
		{
			Bookstore log = new Bookstore();
			Interface logout = new Interface();
			logout.window();
			log.logout();
			dispose();
			
		}
		
		else if (source == bSelect){
			Interface i = new Interface();
			
			
			int ilosc= Integer.parseInt(tAmount.getText());
			il.add(ilosc);
			int selected=list.getSelectedIndex()-1;
			select.add(selected);
			Book kk = mbr.findBookById(selected+1);
			price.add(kk.brutto*ilosc);
			
			String g=licznik+"  --  "+i.fillBook(selected+1)+" szt: "+ilosc+"x"+kk.brutto;
			licznik++;
			
				model2.addElement(g);
				float suma=0;
				for(Float f:price) {
					suma=suma+f ;
				}
				lBasket1.setText("Do zap³aty:  "+suma);
				tAmount.setText("");
		
			
		}else if(source == bSelect1) {
			for(int i=0;i<il.size();i++) {
				mbr.addToCart(mbr.books.get(select.get(i)),il.get(i));
				
				}
			mbr.cartOrder(mbr.users.get(userId-1),userAdress);	
		}
		else if (source == bDelete){
			int selected=list2.getSelectedIndex();
			System.out.println(selected);
		
			il.remove(selected);
			select.remove(selected);
			price.remove(selected);
			model2.remove(selected);
			
			
			System.out.print(il);
   			System.out.println();
 			System.out.print(select);
 			System.out.println();
 			System.out.print(price);
 			System.out.println();
 			
			
		}
		}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		
	}
}
	
	
	
		 

