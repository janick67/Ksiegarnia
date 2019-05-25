import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class NewUser extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JButton bReturn,bAddAccount,bNewAccount ;
	private JLabel lLogin,lPassword,lHello,lWarning,lAdress,lEmail;
	private JTextField tLogin,tAdress,tEmail;
	private JPasswordField tPassword;

	
	
	public NewUser()
	{
		setSize(800,600);
		setTitle("Ksiêgarnia");
		setLayout(null);
		
		lWarning=new JLabel("");
		lWarning.setBounds(20, 280,300, 20);
		add(lWarning);
		
		lLogin=new JLabel("Login");
		lLogin.setBounds(20, 60, 150, 20);
		add(lLogin);
		
		lPassword=new JLabel("Has³o");
		lPassword.setBounds(20, 100, 150, 20);
		add(lPassword);
		
		lAdress=new JLabel("Adres");
		lAdress.setBounds(20, 140, 150, 20);
		add(lAdress);
		
		lEmail=new JLabel("E-mail");
		lEmail.setBounds(20, 180, 150, 20);
		add(lEmail);
		
		tAdress=new JTextField();
		tAdress.setBounds(120, 140, 150, 20);
		add(tAdress);
		
		tEmail=new JTextField();
		tEmail.setBounds(120,180, 150, 20);
		add(tEmail);
		
		tLogin=new JTextField();
		tLogin.setBounds(120, 60, 150, 20);
		add(tLogin);
		
		tPassword=new JPasswordField();
		tPassword.setBounds(120, 100, 150, 20);
		add(tPassword);
		
		bNewAccount = new JButton("Za³ó¿ konto");
		bNewAccount.setBounds(120, 220, 150, 20);
		bNewAccount.addActionListener(this);
		add(bNewAccount);
		
		
		bReturn = new JButton("Wróæ");
		bReturn.setBounds(650, 20, 100, 20);
		add(bReturn);
		bReturn.addActionListener(this);
		
		
		
		}
	
public void addUser() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if(source == bReturn)
		{
				Interface ret = new Interface();
				ret.window();
				dispose();
			}
			else if(source == bAddAccount) 
				 {
					 lHello.setText("Podaj dane do za³o¿enia konta!");
					 lHello.setBounds(20, 20, 300, 20);
					 
					 
					
			
				 }
				 else if(source == bNewAccount) 
				 {
					 Bookstore newUser =new Bookstore();
					 String log = tLogin.getText();
					 String pass = tPassword.getText();
					 String adr = tAdress.getText();
					 String Email = tEmail.getText();
		 
					 if(log.isEmpty() || pass.isEmpty() ||adr.isEmpty() || Email.isEmpty()) 
					 {
						 lWarning.setText("Podaj dane!");
					 }else{
						 		
						 		newUser.addUser(log, pass,adr,Email);
						 		Login load = new Login();
						 		load.login();
						 		dispose();
					 	  }
				 }
	}
	}
	


