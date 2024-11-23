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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpAsMentorPanel extends JPanel {

    public static final int SCREEN_WIDTH = Frame.WIDTH;
    public static final int SCREEN_HEIGHT = Frame.HEIGHT;

    public static final int BACK_BUTTON_WIDTH = 183;
    public static final int BACK_BUTTON_HEIGHT = 176;
    public static final int BACK_BUTTON_X = 75;
    public static final int BACK_BUTTON_Y = 75;

    public static final int TEXTBOX_WIDTH = 540;
    public static final int TEXTBOX_HEIGHT = 75;
    public static final int PMUID_X = 71;
    public static final int PMUID_Y = 432;
    public static final int USERNAME_X = 706;
    public static final int USERNAME_Y = 432;
    public static final int EMAIL_X = 71;
    public static final int EMAIL_Y = 708;
    public static final int MAJOR_X = 706;
    public static final int MAJOR_Y = 708;
    public static final int PASSWORD_X = 71;
    public static final int PASSWORD_Y = 568;
    public static final int REENTER_PASSWORD_X = 706;
    public static final int REENTER_PASSWORD_Y = 568;
    public static final int FIRSTNAME_X = 339;
    public static final int FIRSTNAME_Y = 854;
    public static final int LASTNAME_X = 1111;
    public static final int LASTNAME_Y = 854;
    public static final int MIDDLE_INITIAL_X = 909;
    public static final int MIDDLE_INITIAL_Y = 854;
    public static final int MIDDLE_INITIAL_WIDTH = 155;
    public static final int GPA_X = 1325;
    public static final int GPA_Y = 432;
    public static final int LEVEL_X = 1325;
    public static final int LEVEL_Y = 568;
    public static final int TEACH_X = 1325;
    public static final int TEACH_Y = 708;

    public static final int SUBMIT_CREDENTIALS_WIDTH = 400;
    public static final int SUBMIT_CREDENTIALS_HEIGHT = 78;
    public static final int SUBMIT_CREDENTIALS_X = 792;
    public static final int SUBMIT_CREDENTIALS_Y = 963;

    JTextField PMUIDTextBox;
    JTextField UsernameTextBox;
    JTextField EmailTextBox;
    JTextField MajorTextBox;
    JPasswordField PasswordTextBox;
    JPasswordField ReEnterPasswordTextBox;
    JTextField FirstNameTextBox;
    JTextField LastNameTextBox;
    JTextField MiddleInitialTextBox;
    JTextField GPATextBox;
    JTextField LevelTextBox;
    JTextField TeachTextBox;
    JLabel SubmitCredentialsButton;
    JLabel BackButton;

    public Color Background_Color = new Color(0, 25, 38);
    public Image SignUpPanelBackground;

    Frame parentFrame;

    SignUpAsMentorPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;

        SignUpPanelBackground = new ImageIcon("Sign Up as Mentor.png").getImage();

        PMUIDTextBox = new JTextField();
        UsernameTextBox = new JTextField();
        EmailTextBox = new JTextField();
        MajorTextBox = new JTextField();
        PasswordTextBox = new JPasswordField();
        ReEnterPasswordTextBox = new JPasswordField();
        FirstNameTextBox = new JTextField();
        LastNameTextBox = new JTextField();
        MiddleInitialTextBox = new JTextField();
        GPATextBox = new JTextField();
        LevelTextBox = new JTextField();
        TeachTextBox = new JTextField();
        SubmitCredentialsButton = new JLabel();
        BackButton = new JLabel();

        setupTextBox(PMUIDTextBox, PMUID_X, PMUID_Y);
        setupTextBox(UsernameTextBox, USERNAME_X, USERNAME_Y);
        setupTextBox(EmailTextBox, EMAIL_X, EMAIL_Y);
        setupTextBox(MajorTextBox, MAJOR_X, MAJOR_Y);
        setupTextBox(PasswordTextBox, PASSWORD_X, PASSWORD_Y);
        setupTextBox(ReEnterPasswordTextBox, REENTER_PASSWORD_X, REENTER_PASSWORD_Y);
        setupTextBox(FirstNameTextBox, FIRSTNAME_X, FIRSTNAME_Y);
        setupTextBox(LastNameTextBox, LASTNAME_X, LASTNAME_Y);
        setupTextBox(MiddleInitialTextBox, MIDDLE_INITIAL_X, MIDDLE_INITIAL_Y);
        setupTextBox(GPATextBox, GPA_X, GPA_Y);
        setupTextBox(LevelTextBox, LEVEL_X, LEVEL_Y);
        setupTextBox(TeachTextBox, TEACH_X, TEACH_Y);

        SubmitCredentialsButton.setBounds(SUBMIT_CREDENTIALS_X, SUBMIT_CREDENTIALS_Y, SUBMIT_CREDENTIALS_WIDTH, SUBMIT_CREDENTIALS_HEIGHT);
        SubmitCredentialsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                createMentorAccount();
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
        this.add(PMUIDTextBox);
        this.add(UsernameTextBox);
        this.add(EmailTextBox);
        this.add(MajorTextBox);
        this.add(PasswordTextBox);
        this.add(ReEnterPasswordTextBox);
        this.add(FirstNameTextBox);
        this.add(LastNameTextBox);
        this.add(MiddleInitialTextBox);
        this.add(GPATextBox);
        this.add(LevelTextBox);
        this.add(TeachTextBox);
        this.add(SubmitCredentialsButton);
        this.add(BackButton);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void setupTextBox(JTextField textBox, int x, int y) {
        textBox.setFont(new Font("mv Boli", Font.PLAIN, 30));
        textBox.setBackground(Background_Color);
        textBox.setForeground(Color.WHITE);
        textBox.setCaretColor(Color.WHITE);
        textBox.setBorder(null);
        textBox.setBounds(x, y, TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
        if(textBox == MiddleInitialTextBox)  
        	textBox.setBounds(x, y, MIDDLE_INITIAL_WIDTH, TEXTBOX_HEIGHT); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(SignUpPanelBackground, 0, 0, this);
    }

    private void createMentorAccount() {
        String studentID = PMUIDTextBox.getText();
        String username = UsernameTextBox.getText();
        String email = EmailTextBox.getText();
        String major = MajorTextBox.getText();
        String password = String.valueOf(PasswordTextBox.getPassword());
        String reEnterPassword = String.valueOf(ReEnterPasswordTextBox.getPassword());
        String firstName = FirstNameTextBox.getText();
        String lastName = LastNameTextBox.getText();
        String middleInitial = MiddleInitialTextBox.getText();
        String gpa = GPATextBox.getText();
        String level = LevelTextBox.getText();
        String teach = TeachTextBox.getText();

        if (username.length() > 20) {
            JOptionPane.showMessageDialog(null, "Username cannot exceed 20 characters.", "Invalid Username", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (password.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Password cannot contain spaces.", "Invalid Password", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password.equals(reEnterPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Password Mismatch", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

            String insertQuery = "INSERT INTO PENDING_MENTORS (StudentID, username, Firstname, MiddleInitial, LastName, Major, PMU_Email, GPA, Current_Level, Teach, AccountPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, studentID);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, middleInitial);
            preparedStatement.setString(5, lastName);
            preparedStatement.setString(6, major);
            preparedStatement.setString(7, email);
            preparedStatement.setString(8, gpa);
            preparedStatement.setString(9, level);
            preparedStatement.setString(10, teach);
            preparedStatement.setString(11, password);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Request Sent successfully.", "Sign Up Successful", JOptionPane.INFORMATION_MESSAGE);
                this.parentFrame.showMentorPendingPanel();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to send request.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}