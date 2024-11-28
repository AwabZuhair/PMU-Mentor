package V1;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class Frame extends JFrame{
	
	
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	//Insert Panels Here 
	private MenteeLoginPanel MenteeLoginPanel;
	private HomePanel homePanel;
	private MentorLoginPanel MentorLoginPanel;
	private MenteeSignUpPanel MenteeSignUpPanel;
	private SignUpAsMentorPanel SignUpAsMentorPanel;
	private MentorPendingPanel MentorPendingPanel;
	private AccountInformationPanel accountInformationPanel;
	
	private GraphicsDevice device;

	
	Frame(){
	
        
        homePanel = new HomePanel(this);
		MenteeLoginPanel = new MenteeLoginPanel(this);
		accountInformationPanel = new AccountInformationPanel(this);
		MentorLoginPanel = new MentorLoginPanel(this);
		MenteeSignUpPanel = new MenteeSignUpPanel(this);
		SignUpAsMentorPanel = new SignUpAsMentorPanel(this);
		MentorPendingPanel = new MentorPendingPanel(this);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		cardPanel.add(homePanel, "home");
		cardPanel.add(MenteeLoginPanel, "MenteeLogin");
		cardPanel.add(accountInformationPanel, "accountInformation");
		cardPanel.add(MentorLoginPanel, "MentorLogin");
		cardPanel.add(MenteeSignUpPanel, "MenteeRegister");
		cardPanel.add(SignUpAsMentorPanel, "MentorRegister");
		cardPanel.add(MentorPendingPanel, "MentorPending");

		showHomePanel();
		
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		device.setFullScreenWindow(this);
		        
		this.setLocationRelativeTo(null);
		this.add(cardPanel);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		//this.pack();
		this.setTitle("PMU Mentor");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLayout(null);
		this.setVisible(true);	
	}
	
		public void showMenteeLoginPanel() {
	        cardLayout.show(cardPanel, "MenteeLogin");
		}
		
		public void showMentorLoginPanel() {
	        cardLayout.show(cardPanel, "MentorLogin");
		}
		
		public void showMenteeRegisterPanel() {
	        cardLayout.show(cardPanel, "MenteeRegister");
		}
		
		public void showMentorRegisterPanel() {
	        cardLayout.show(cardPanel, "MentorRegister");
		}
		
		public void showHomePanel() {
	        cardLayout.show(cardPanel, "home");
		}
		
		public void showAccountInformationPanel() {
	        cardLayout.show(cardPanel, "accountInformation");
		}

		public void showMentorPendingPanel() {
	        cardLayout.show(cardPanel, "MentorPending");
			
		}
		
		
		
		
}
