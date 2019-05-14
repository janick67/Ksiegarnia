import java.awt.Component;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Interface extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lQuantity,lHello,lMenu;
	private JButton bOrder, bLogin;
	private JList<String> list;

	
	public Interface()
	{
		
		setSize(800,600);
		setTitle("Ksiêgarnia");
		setLayout(null);
		
	
		lQuantity = new JLabel("");
		lQuantity.setBounds(20, 400, 150, 20);
		add(lQuantity);
		
		lHello = new JLabel("Witaj w ksiêgarni!");
		lHello.setBounds(20, 20, 150, 20);
		add(lHello);
		
		lMenu = new JLabel("Menu");
		lMenu.setBounds(30, 70, 150, 28);
		lMenu.setFont(lMenu.getFont().deriveFont(20.0f));
		add(lMenu);
		
		bOrder = new JButton("Zamów ksi¹¿kê");
		bOrder.setBounds(30, 100, 140, 30);
		add(bOrder);
		bOrder.addActionListener(this);
		
		bLogin = new JButton("Zaloguj siê");
		bLogin.setBounds(30, 130, 140, 30);
		add(bLogin);
		bLogin.addActionListener(this);
		
		Bookstore mbr = new Bookstore();
		int max=mbr.MaxId();
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		list = new JList<String>(model);
		for(int i=0; i<=max; i++) { 
		String titleAuthor= mbr.fill(i);	
	    model.add(i,titleAuthor );
	    }
	
		list.setBounds(350, 30, 300, 200);
		add(list);
		
		 ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		      
		        boolean adjust = listSelectionEvent.getValueIsAdjusting();
		        
		        if (!adjust) {
		          JList list = (JList) listSelectionEvent.getSource();
		          int selections[] = list.getSelectedIndices();
		          Object selectionValues[] = list.getSelectedValues();
		          for (int i = 0, n = selections.length; i < n; i++) {
		            if (i == 0) {
		            }
		            	lQuantity.setText("Dostêpne sztuki: ");
		    		} 
		        }
		      }
		    };
		   list.addListSelectionListener(listSelectionListener);	   
	}
		  
	
	public void window() {
		Interface window = new Interface();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);	
	}
	
	
	
	public static void main(String[] args) {
		Interface window = new Interface();
		window.window();	
	}
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if(source == bLogin)
		{
			Login log = new Login();
			log.login();
			dispose();
		} if(source == bOrder) {
				int answer = JOptionPane.showConfirmDialog(null , "Aby z³o¿yæ zamówienie musisz siê zalogowaæ!","Uwaga",JOptionPane.YES_NO_OPTION);
				if(JOptionPane.YES_OPTION == answer) {
					Login log = new Login();
					log.login();
					dispose();
				}

}
	}
}
 


