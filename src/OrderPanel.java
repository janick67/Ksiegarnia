import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;


public class OrderPanel extends JFrame implements ListSelectionListener,ActionListener {
	
	
	private static final long serialVersionUID = 1L;
	private JButton bLogout,bAddToBasket,bSendBasket,bDelete ;
	private JLabel lItem,lSum,lBasket;
	private JTextField tAmount;
	DefaultListModel<String> model = new DefaultListModel<String>();
	DefaultListModel<String> model2 = new DefaultListModel<String>();
	private JList<String> list;
	private JList<String> list2;
	int userId;
	String userAdress;
	int index=0;
	ArrayList<Float>price= new ArrayList<Float>();
	ArrayList<Integer> quantity= new ArrayList<Integer>();
	ArrayList<Integer> select= new ArrayList<Integer>();
	
	public OrderPanel(int sesion,String adr)
	{
		setSize(800,600);
		setTitle("Ksiêgarnia");
		setLayout(null);
		
		this.userId= sesion;
		this.userAdress = adr;
		
		tAmount=new JTextField();
		tAmount.setBounds(20, 290, 60, 30);
		tAmount.setToolTipText("Podaj iloœæ");
		add(tAmount);
		
		lItem = new JLabel("Wybierz ksia¿ki");
		lItem.setBounds(85, 60, 150, 20);
		add(lItem);
		
		lBasket = new JLabel("Wybrane pozycje");
		lBasket.setBounds(525, 60, 150, 20);
		add(lBasket);
		
		lSum = new JLabel("");
		lSum.setBounds(400, 400, 300, 200);
		add(lSum);

		bLogout = new JButton("Wyloguj");
		bLogout.setBounds(650, 20, 100, 20);
		add(bLogout);
		bLogout.addActionListener(this);
		
		bAddToBasket = new JButton("Dodaj do koszyka");
		bAddToBasket.setBounds(180, 290, 140, 30);
		add(bAddToBasket);
		bAddToBasket.addActionListener(this);
		
		bSendBasket = new JButton("Wyœlij zamówienie");
		bSendBasket.setBounds(20, 360, 140, 30);
		add(bSendBasket);
		bSendBasket.addActionListener(this);
		
		bDelete = new JButton("Usuñ");
		bDelete .setBounds(400, 300, 140, 30);
		add(bDelete );
		bDelete .addActionListener(this);
		
		
		
		list = new JList<String>(model);
		
		mm();
			
		
		list.setBounds(20, 80, 300, 200);
		add(list);
		list.addListSelectionListener(this);
		list2 = new JList<String>(model2);
		list2.setBounds(400, 80, 300, 200);
  		add(list2);
  		list2.addListSelectionListener(this);

		
	ListSelectionListener listSelectionListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
		  
		      	}
		    };
		   list.addListSelectionListener(listSelectionListener);	   
	
		 //list2
	ListSelectionListener listSelectionListener1 = new ListSelectionListener() {
		 public void valueChanged(ListSelectionEvent listSelectionEvent) {
			      }
			    };
			   list2.addListSelectionListener(listSelectionListener1);	   
		}
      public void mm() {
    	  Interface inter = new Interface();
  		int max=inter.maxBookId();	
    	  for(int i=0; i<=max; i++) 
			{ 
    		  
				String titleAuthorPriceAmount = inter.fillBookExtended(i);
				model.add(i,titleAuthorPriceAmount);
			}
      }
	      	    
public void set(int id,String adr)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}

	
	

@Override
	
public void actionPerformed(ActionEvent e) 
	{
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
		
		else if (source == bAddToBasket)
		{
			Interface i = new Interface();
			int amount= Integer.parseInt(tAmount.getText());		
			quantity.add(amount);
			int selected=list.getSelectedIndex()-1;
			select.add(selected);
			Book kk = mbr.findBookById(selected+1);
			price.add(kk.netto*amount);
			String show=index+"  --  "+i.fillBook(selected+1)+" szt: "+amount+"x"+kk.netto;
			index++;
			
				model2.addElement(show);
				float sum=0;
				for(Float f:price) {
					sum=sum+f ;
				}
				lSum.setText("Do zap³aty:  "+sum);
				tAmount.setText("");
			
		}else if(source == bSendBasket) {
			for(int i=0;i<quantity.size();i++) {
				mbr.addToCart(mbr.books.get(select.get(i)),quantity.get(i));
				
				}
			mbr.cartOrder(mbr.users.get(userId-1),userAdress);	
			model2.clear();
			int answer =  JOptionPane.showConfirmDialog(rootPane, "Zamówienie zosta³o wys³ane. Wylogowaæ Ciê ?","Result",JOptionPane.YES_NO_OPTION);
			if(answer == JOptionPane.YES_OPTION)
			{
				Bookstore log = new Bookstore();
				Interface logout = new Interface();
				logout.window();
				log.logout();
				dispose();
			}else {
				
				mbr.load();
				model.clear();
				mm();
				
			}
		}
		else if (source == bDelete){
			int selected=list2.getSelectedIndex();
			System.out.println(selected);
		
			quantity.remove(selected);
			select.remove(selected);
			price.remove(selected);
			model2.remove(selected);
			
			float sum=0;
			for(Float f:price) {
				sum=sum+f ;
			}
			
			lSum.setText("Do zap³aty:  "+sum);
		
		}
		}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		
	}
}
	
	
	
		 

