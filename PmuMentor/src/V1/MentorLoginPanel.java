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

public class MentorLoginPanel extends JPanel {

    public static final int SCREEN_WIDTH = Frame.WIDTH;
    public static final int SCREEN_HEIGHT = Frame.HEIGHT;

    public static final int BACK_BUTTON_WIDTH = 183;
    public static final int BACK_BUTTON_HEIGHT = 176;
    public static final int BACK_BUTTON_X = 75;
    public static final int BACK_BUTTON_Y = 75;

    public static final int TEXTBOX_WIDTH = 542;
    public static final int TEXTBOX_HEIGHT = 83;
    public static final int TEXTBOX_X = 714;
    public final int MENTOR_ID_BOX_Y = 606;
    public final int PASSWORD_BOX_Y = 787;

    public static final int SUBMIT_CREDENTIALS_WIDTH = 400;
    public static final int SUBMIT_CREDENTIALS_HEIGHT = 90;
    public static final int SUBMIT_CREDENTIALS_X = 795;
    public static final int SUBMIT_CREDENTIALS_Y = 917;

    JTextField MentorIDTextBox;
    JPasswordField PasswordIDTextBox;
    JLabel SubmitCredentialsButton;
    JLabel BackButton;

    public static String MentorID;
    public static String username;
    public String password;

    public Color Background_Color = new Color(0, 25, 38);

    public Image LoginPanelBackground;

    Frame parentFrame;

    MentorLoginPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;

        LoginPanelBackground = new ImageIcon("Mentor New.png").getImage();

        MentorIDTextBox = new JTextField();
        PasswordIDTextBox = new JPasswordField();
        SubmitCredentialsButton = new JLabel();
        BackButton = new JLabel();

        PasswordIDTextBox.setFont(new Font("mv Boli", Font.PLAIN, 30));
        PasswordIDTextBox.setBackground(Background_Color);
        PasswordIDTextBox.setForeground(Color.WHITE);
        PasswordIDTextBox.setCaretColor(Color.WHITE);
        PasswordIDTextBox.setBorder(null);
        PasswordIDTextBox.setBounds(TEXTBOX_X, PASSWORD_BOX_Y, TEXTBOX_WIDTH, TEXTBOX_HEIGHT);

        MentorIDTextBox.setFont(new Font("mv Boli", Font.PLAIN, 30));
        MentorIDTextBox.setBackground(Background_Color);
        MentorIDTextBox.setForeground(Color.WHITE);
        MentorIDTextBox.setCaretColor(Color.WHITE);
        MentorIDTextBox.setBorder(null);
        MentorIDTextBox.setBounds(TEXTBOX_X, MENTOR_ID_BOX_Y, TEXTBOX_WIDTH, TEXTBOX_HEIGHT);

        SubmitCredentialsButton.setBounds(SUBMIT_CREDENTIALS_X, SUBMIT_CREDENTIALS_Y, SUBMIT_CREDENTIALS_WIDTH, SUBMIT_CREDENTIALS_HEIGHT);
        SubmitCredentialsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                checkMentorInformation();
            }
        });

        BackButton.setBounds(BACK_BUTTON_X, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        BackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                parentFrame.showHomePanel();
            }
        });

        this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.add(MentorIDTextBox);
        this.add(PasswordIDTextBox);
        this.add(SubmitCredentialsButton);
        this.add(BackButton);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(LoginPanelBackground, 0, 0, this);
    }

    public void checkMentorInformation() {
        MentorID = MentorIDTextBox.getText();
        password = String.valueOf(PasswordIDTextBox.getPassword());

        try {
            boolean exists = checkAccount(MentorID, password);

            if (exists) {
                JOptionPane.showMessageDialog(null, "Account found successfully.", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Input Account Was Not Recognized. Please Try Again", "Unknown Account", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkAccount(String MentorID, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project",
                "root",
                "Awab_hamadto123");

        java.sql.Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM REGISTERED_MENTORS");

        while (resultSet.next()) {
            if (MentorID.equals(resultSet.getString("MentorID")) && password.equals(resultSet.getString("AccountPassword"))) {
                username = resultSet.getString("username");
                return true;
            }
        }
        return false;
    }
}