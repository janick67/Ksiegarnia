import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Login extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bLogin,bReturn,bAdd,bNew ;
	private JLabel lLogin,lPassword,lHello,lInfo,lInfo2,lWarning,lAdress,lEmail;
	private JTextField tLogin,tAdress,tEmail;
	private JPasswordField tPassword;
	private String login,password;
	
	
	public Login()
	{
		setSize(800,600);
		setTitle("Ksiêgarnia");
		setLayout(null);
		
		lWarning=new JLabel("");
		lWarning.setBounds(20, 280,300, 20);
		add(lWarning);
			
		lHello=new JLabel("Witaj w panelu logowania!");
		lHello.setBounds(20, 20, 150, 20);
		add(lHello);
		
		lInfo=new JLabel("Nie masz jeszcze u nas konta?");
		lInfo.setBounds(20, 200,300, 20);
		add(lInfo);
		
		lLogin=new JLabel("Login");
		lLogin.setBounds(20, 60, 150, 20);
		add(lLogin);
		
		lPassword=new JLabel("Has³o");
		lPassword.setBounds(20, 100, 150, 20);
		add(lPassword);
		
		lAdress=new JLabel("Adres");
		lAdress.setBounds(20, 140, 150, 20);
		
		
		lEmail=new JLabel("E-mail");
		lEmail.setBounds(20, 180, 150, 20);
		
		tAdress=new JTextField();
		tAdress.setBounds(120, 140, 150, 20);
		
		
		tEmail=new JTextField();
		tEmail.setBounds(120,180, 150, 20);
		
		
		
		tLogin=new JTextField();
		tLogin.setBounds(120, 60, 150, 20);
		add(tLogin);
		
		tPassword=new JPasswordField();
		tPassword.setBounds(120, 100, 150, 20);
		add(tPassword);
		
	
		
		bLogin = new JButton("Zaloguj");
		bLogin.setBounds(120, 150, 150, 20);
		add(bLogin);
		bLogin.addActionListener(this);
		
		bNew = new JButton("Za³ó¿ konto");
		bNew .setBounds(120, 220, 150, 20);
		//add(bNew );
		bNew .addActionListener(this);
		
		bAdd = new JButton("Za³ó¿ ju¿ teraz!");
		bAdd .setBounds(30, 230, 150, 20);
		add(bAdd );
		bAdd .addActionListener(this);
		
		bReturn = new JButton("Wróæ");
		bReturn.setBounds(650, 20, 100, 20);
		add(bReturn);
		bReturn.addActionListener(this);
		
		
		
		lInfo2=new JLabel();
		lInfo2.setBounds(120, 200, 250, 20);
		add(lInfo2);
		
		}
	
	public void login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		Object source = e.getSource();
		if(source == bReturn)
		{
			Interface ret = new Interface();
			ret.window();
			dispose();
			
		}else if(source == bLogin){
		Bookstore log = new Bookstore();
		log.load();
		login = tLogin.getText();
		password = tPassword.getText();
		log.login(login, password);
		if(log.activeUser != null)
		{
			lInfo.setText("Twoj login to : "+log.activeUser.username);
			if(log.activeUser.id!=6)//normal user
			{
				
				
				OrderPanel orderP = new OrderPanel(log.activeUser.id,log.activeUser.address);
			orderP.set(log.activeUser.id,log.activeUser.address);
			dispose();
			}
			else//Admin
			{
				lInfo2.setText("Masz prawa admina");
				InterfaceAdmin admin = new InterfaceAdmin();
				admin.window();
				dispose();
			}
		}
		else lInfo.setText("Bledne dane");
		}else if(source == bAdd) {
			lHello.setText("Podaj dane do za³o¿enia konta!");
			lHello.setBounds(20, 20, 300, 20);
			bAdd.setVisible(false);;
			 lInfo.setVisible(false);
			bLogin.setVisible(false);
			add(bNew);
			add(tEmail);
			add(tAdress);
			add(lAdress);
			add(lEmail);
			
		}else if(source == bNew) {
		 Bookstore newUser =new Bookstore();
		 String log = tLogin.getText();
		 String pass = tPassword.getText();
		 String adr = tAdress.getText();
		 String Email = tEmail.getText();
		 
		 
		 if(log.isEmpty() || pass.isEmpty()) {
			 lWarning.setText("Podaj dane!");
			
		 }else {
			 newUser.addUser(log, pass,adr, Email);
			 Login load = new Login();
			 load.login();
			 dispose();
		 }
		 
		}
		
	}
	}
	


