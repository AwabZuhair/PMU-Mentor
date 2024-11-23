package V1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MenteeLoginPanel extends JPanel{

	public static final int SCREEN_WIDTH = Frame.WIDTH;
	public static final int SCREEN_HEIGHT = Frame.HEIGHT;
	
	public static final int BACK_BUTTON_WIDTH = 183;
	public static final int BACK_BUTTON_HEIGHT = 176;
	public static final int BACK_BUTTON_X = 75;
	public static final int BACK_BUTTON_Y = 75;
	
	public static final int TEXTBOX_WIDTH = 542;
	public static final int TEXTBOX_HEIGHT = 83;
	public static final int TEXTBOX_X = 715;
	public final int STUDENT_ID_BOX_Y = 604;
	public final int PASSWORD_BOX_Y = 784;
	
	public static final int SUBMIT_CREDENTIALS_WIDTH = 400;
	public static final int SUBMIT_CREDENTIALS_HEIGHT = 90;
	public static final int SUBMIT_CREDENTIALS_X = 795;
	public static final int SUBMIT_CREDENTIALS_Y = 917;
	
	JTextField StudentIDTextBox;
	JPasswordField PasswordIDTextBox;
	JLabel SubmitCredentialsButton;
	JLabel BackButton;
	
	public static String StudentID;
	public static String username;
	public String password;
	
	public Color Background_Color = new Color(0, 25, 38);
	
	public Image LoginPanelBackground;
	
	Frame parentFrame;
	
	
	MenteeLoginPanel(Frame parentFrame){
		this.parentFrame = parentFrame;
		
		LoginPanelBackground = new ImageIcon("Mentee New.png").getImage();
		
		StudentIDTextBox = new JTextField();
		PasswordIDTextBox = new JPasswordField();
		SubmitCredentialsButton = new JLabel();
		BackButton = new JLabel();
		
		PasswordIDTextBox.setFont(new Font("mv Boli", Font.PLAIN, 30));
		PasswordIDTextBox.setBackground(Background_Color);
		PasswordIDTextBox.setForeground(Color.WHITE);
		PasswordIDTextBox.setCaretColor(Color.WHITE);
		PasswordIDTextBox.setBorder(null);
		PasswordIDTextBox.setBounds(TEXTBOX_X, PASSWORD_BOX_Y, TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		
		StudentIDTextBox.setFont(new Font("mv Boli", Font.PLAIN, 30));
		StudentIDTextBox.setBackground(Background_Color);
		StudentIDTextBox.setForeground(Color.WHITE);
		StudentIDTextBox.setCaretColor(Color.WHITE);
		StudentIDTextBox.setBorder(null);
		StudentIDTextBox.setBounds(TEXTBOX_X, STUDENT_ID_BOX_Y, TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
		
		SubmitCredentialsButton.setBounds(SUBMIT_CREDENTIALS_X, SUBMIT_CREDENTIALS_Y, SUBMIT_CREDENTIALS_WIDTH, SUBMIT_CREDENTIALS_HEIGHT);
		SubmitCredentialsButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent event) {
				checkMenteeInformation();
			}
			
		});
		
		BackButton.setBounds(BACK_BUTTON_X, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
		BackButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent event) {
				parentFrame.showHomePanel();
			}
			
		});

        
		this.setSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.add(StudentIDTextBox);
		this.add(PasswordIDTextBox);
		this.add(SubmitCredentialsButton);
		this.add(BackButton);
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
    
	
	public void checkMenteeInformation() {
			StudentID = StudentIDTextBox.getText();
			password = String.valueOf(PasswordIDTextBox.getPassword());
			
			try {
				boolean exists = checkAccount(StudentID, password);
				
				if(exists) {
					parentFrame.showAccountInformationPanel();
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Input Account Was Not Recognized. Please Try Again", "Uknown Account", JOptionPane.WARNING_MESSAGE);
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
