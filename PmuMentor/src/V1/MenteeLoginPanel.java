package V1;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class LoginPanel extends JPanel{

	static final int SCREEN_WIDTH = Frame.WIDTH;
	static final int SCREEN_HEIGHT = Frame.HEIGHT;
	static final int PASS_WIDTH = 300;
	static final int USER_WIDTH = 300;
	
	public static String StudentID;
	public static String username;
	public String password;
	
	public static Image LoginPanelBackground;
	
	Frame parentFrame;
	
	TextField EnterID;
	JPasswordField EnterPassword;
	JLabel Login;
	JLabel SignUp;
	
	LoginPanel(Frame parentFrame){
		this.parentFrame = parentFrame;
		
		LoginPanelBackground = new ImageIcon("Login.png").getImage();
		
		EnterID = new TextField();
		EnterPassword = new JPasswordField();
		Login = new JLabel();
		SignUp = new JLabel();
		
		EnterPassword.setFont(new Font("mv Boli", Font.PLAIN, 30));
		EnterPassword.setBackground(Color.ORANGE);
		EnterPassword.setForeground(Color.BLUE);
		EnterPassword.setText("Password");
		EnterPassword.setBounds(100, 650, PASS_WIDTH, 50);
		
		EnterID.setFont(new Font("mv Boli", Font.PLAIN, 30));
		EnterID.setBackground(Color.ORANGE);
		EnterID.setForeground(Color.BLUE);
		EnterID.setText("ID");
		EnterID.setBounds(100, 500, USER_WIDTH, 50);
		
		Login.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent event) {
				checkInformation();
			}
			
		});
		Login.setIcon(new ImageIcon("button_login.png"));
		//Login.setFont(new Font("Consolas",Font.BOLD,15));
		//Login.setForeground(Color.BLUE);
		Login.setBounds(100, 725, 200, 50);
		Login.setFocusable(false);
		
		SignUp.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent event) {
				
			}
			
		});
        SignUp.setIcon(new ImageIcon("button_sign-up.png"));
        //SignUp.setFont(new Font("Consolas",Font.BOLD,15));
        //SignUp.setForeground(Color.RED);
        SignUp.setBounds(100, 800, 200, 50);
        SignUp.setFocusable(false);
        
		this.setSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.add(Login);
		this.add(SignUp);
		this.add(EnterPassword);
		this.add(EnterID);
		this.setLayout(null);
		this.setVisible(true);;
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g) {
    	g.drawImage(LoginPanelBackground, 0, 0, this);
    }
    
	
	public void checkInformation() {
			StudentID = EnterID.getText();
			password = String.valueOf(EnterPassword.getPassword());
			
			try {
				boolean exists = checkAccount(StudentID, password);
				
				if(exists) {
					parentFrame.showHomePanel();
					//System.out.print("The account exists");
					//this.dispose();
					//JOptionPane.showMessageDialog(null, "Account Found Successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					//Thread.sleep(1000);

				}
				else {
					System.out.print("The account doesnt exist");
					//JOptionPane.showMessageDialog(null, "Input Account Was Not Recognized. Try Again", "Uknown Account", JOptionPane.OK_OPTION);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	public boolean checkAccount(String StudentID, String password) throws SQLException {

		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/pmu_mentor_project",
				"root",
				"Awab_hamadto123");
		
		java.sql.Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM REGISTERED_USERS");
		
		while(resultSet.next()) 
			if(StudentID.equals(resultSet.getString("StudentID")) && password.equals(resultSet.getString("AccountPassword"))) {
				username = resultSet.getString("username");
				return true;
			}
			return false;
				
	}
}
