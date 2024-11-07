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
	
	
	CardLayout cardLayout;
	JPanel cardPanel;
	
	//Insert Panels Here 
	LoginPanel loginPanel;
	//RegisterPanel registerPanel;
	HomePanel homePanel;
	//SettingsPanel settingsPanel;
	//AccountInformationPanel accountInformationPanel;
	
	GraphicsDevice device;
	
	File SoundFile;
	AudioInputStream audioStream;
	public static Clip clip;
	FloatControl AudioControl;
	
	ImageIcon HeaderIcon;
	
	Frame(){
	
        
        homePanel = new HomePanel(this);
		loginPanel = new LoginPanel(this);
		//registerPanel = new RegisterPanel(this);
		//settingsPanel = new SettingsPanel(this);
		//accountInformationPanel = new AccountInformationPanel(this);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		cardPanel.add(homePanel, "home");
		cardPanel.add(loginPanel, "login");
		//cardPanel.add(registerPanel, "register");
		//cardPanel.add(settingsPanel, "settings");
		//cardPanel.add(accountInformationnPanel, "accountInfo");

		showLoginPanel();
		
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		device.setFullScreenWindow(this);
		        
		this.setLocationRelativeTo(null);
		this.add(cardPanel);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.pack();
		this.setTitle("PMU Mentor");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setVisible(true);
		
		try {
			playMenuMusic();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An Unexpected Audio Error Occured :( ", "Audio Error", JOptionPane.WARNING_MESSAGE);
	}
		
	}
	
	
	
	public void playMenuMusic() {
		
		try {	
			SoundFile = new File("peaceful-piano-background-music-218762.mp3");
			audioStream = AudioSystem.getAudioInputStream(SoundFile);
			clip = AudioSystem.getClip();
			clip.open(audioStream);	
			
			AudioControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e) {
	    	System.out.println("AN ERROR OCCURRED IN 'PLAYMENUMUSIC' AT 'FRAME'");
	    }
			
	
		}
		
		public void pauseMenuMusic() {
		    if (clip != null && clip.isRunning()) {
		        clip.stop();
		    }
		}
		
		public void resumeMenuMusic() {
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		
		public void showLoginPanel() {
	        cardLayout.show(cardPanel, "login");
		}
		
		public void showSettingsPanel() {
	        cardLayout.show(cardPanel, "settings");
		}
		
		public void showRegisterPanel() {
	        cardLayout.show(cardPanel, "register");
		}
		
		public void showHomePanel() {
	        cardLayout.show(cardPanel, "home");
		}
		
		public void showAccountInformationPanel() {
	        cardLayout.show(cardPanel, "accountInfo");
		}
		
		
		
		
}
