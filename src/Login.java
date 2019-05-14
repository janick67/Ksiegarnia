import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.*;


public class Login extends JFrame implements ActionListener {
	private JButton bLogIn,bReturn;
	private JLabel lLogin,lPassword,lHello,lInfo,lInfo2;
	private JTextField tLogin;
	private JPasswordField tPassword;
	private String login,password;
	
	public Login()
	{
		setSize(800,600);
		setTitle("Ksiêgarnia");
		setLayout(null);
		
		lHello=new JLabel("Witaj w panelu logowania!");
		lHello.setBounds(20, 20, 150, 20);
		add(lHello);
		
		lLogin=new JLabel("Login");
		lLogin.setBounds(20, 60, 150, 20);
		add(lLogin);
		
		lPassword=new JLabel("Has³o");
		lPassword.setBounds(20, 100, 150, 20);
		add(lPassword);
		
		tLogin=new JTextField();
		tLogin.setBounds(120, 60, 150, 20);
		add(tLogin);
		
		tPassword=new JPasswordField();
		tPassword.setBounds(120, 100, 150, 20);
		add(tPassword);
		
		bLogIn = new JButton("Zaloguj");
		bLogIn.setBounds(120, 150, 150, 20);
		add(bLogIn);
		bLogIn.addActionListener(this);
		
		bReturn = new JButton("Wróæ");
		bReturn.setBounds(650, 20, 150, 20);
		add(bReturn);
		bReturn.addActionListener(this);
		
		lInfo=new JLabel();
		lInfo.setBounds(120, 180, 150, 20);
		add(lInfo);
		
		lInfo2=new JLabel();
		lInfo2.setBounds(120, 200, 250, 20);
		add(lInfo2);
		
		}
	
	public void login() {
		Login log = new Login(); 
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
			
		}else{
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
				OrderPanel orderP = new OrderPanel();
			orderP.set();
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
		}
		
	}
	}
	


