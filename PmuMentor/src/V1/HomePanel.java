package V1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel {

	private static final int SIGN_UP_BUTTON_WIDTH = 400;
    private static final int SIGN_UP_BUTTON_HEIGHT = 81;
	private static final int LOG_IN_BUTTON_WIDTH = 361;
	private static final int LOG_IN_BUTTON_HEIGHT = 85;
	private final int SIGN_UP_AS_MENTEE_X = 144;
	private final int SIGN_UP_AS_MENTEE_Y = 619;
	private final int SIGN_UP_AS_MENTOR_X = 148;
	private final int SIGN_UP_AS_MENTOR_Y = 721;
	private final int LOGIN_X = 167;
	private final int LOGIN_AS_MENTEE_Y = 823;
	private final int LOGIN_AS_MENTOR_Y = 929;
	
	private Image HomePanelBackground;
	
	private JLabel SignUpAsMenteeButton;
	private JLabel SignUpAsMentorButton;
	private JLabel LoginAsMenteeButton;
	private JLabel LoginAsMentorButton;
	
	private Frame parentFrame;
	
	HomePanel(Frame parentFrame){
		this.parentFrame = parentFrame;
		
		HomePanelBackground = new ImageIcon("InitialPage.png").getImage();
		
		SignUpAsMenteeButton = new JLabel();
		SignUpAsMentorButton = new JLabel();
		LoginAsMenteeButton = new JLabel();
		LoginAsMentorButton = new JLabel();
		
		SignUpAsMenteeButton.setBounds(SIGN_UP_AS_MENTEE_X, SIGN_UP_AS_MENTEE_Y, SIGN_UP_BUTTON_WIDTH, SIGN_UP_BUTTON_HEIGHT);
		SignUpAsMentorButton.setBounds(SIGN_UP_AS_MENTOR_X, SIGN_UP_AS_MENTOR_Y, SIGN_UP_BUTTON_WIDTH, SIGN_UP_BUTTON_HEIGHT);
		LoginAsMenteeButton.setBounds(LOGIN_X, LOGIN_AS_MENTEE_Y, LOG_IN_BUTTON_WIDTH, LOG_IN_BUTTON_HEIGHT);
		LoginAsMentorButton.setBounds(LOGIN_X, LOGIN_AS_MENTOR_Y, LOG_IN_BUTTON_WIDTH, LOG_IN_BUTTON_HEIGHT);
		
		SignUpAsMenteeButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				parentFrame.showMenteeRegisterPanel();
			}
			
		});
		
		SignUpAsMentorButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				parentFrame.showMentorRegisterPanel();
			}
			
		});
		
		LoginAsMenteeButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				parentFrame.showMenteeLoginPanel();
			}
			
		});
		
		LoginAsMentorButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				parentFrame.showMentorLoginPanel();
			}
		});
		
        
		this.setSize(new Dimension(parentFrame.getWidth(),parentFrame.getHeight()));
		this.add(SignUpAsMenteeButton);
		this.add(SignUpAsMentorButton);
		this.add(LoginAsMenteeButton);
		this.add(LoginAsMentorButton);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g) {
    	g.drawImage(HomePanelBackground, 0, 0, this);
    }
}
