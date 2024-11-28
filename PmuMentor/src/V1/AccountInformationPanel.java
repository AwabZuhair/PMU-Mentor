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

public class AccountInformationPanel extends JPanel {

    public static final int TEXTBOX_WIDTH = 580;
    public static final int TEXTBOX_HEIGHT = 57;
    public static final int BUTTON_WIDTH = 631;
    public static final int BUTTON_HEIGHT = 73;
    public static final int DELETE_BUTTON_WIDTH = 640;
    public static final int DELETE_BUTTON_HEIGHT = 81;

    public static final int USERNAME_X = 692;
    public static final int USERNAME_Y = 564;
    public static final int EMAIL_X = 692;
    public static final int EMAIL_Y = 676;
    public static final int PASSWORD_X = 692;
    public static final int PASSWORD_Y = 789;
    public static final int CONFIRM_BUTTON_X = 671;
    public static final int CONFIRM_BUTTON_Y = 875;
    public static final int DELETE_BUTTON_X = 667;
    public static final int DELETE_BUTTON_Y = 971;

    JTextField usernameTextBox;
    JTextField emailTextBox;
    JPasswordField passwordTextBox;
    JLabel confirmUpdatesButton;
    JLabel deleteAccountButton;

    Frame parentFrame;

    public Color Background_Color = new Color(0, 25, 38);
    public Image settingsPageBackground;

    public AccountInformationPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;

        settingsPageBackground = new ImageIcon("SettingsPage.png").getImage();

        usernameTextBox = new JTextField();
        emailTextBox = new JTextField();
        passwordTextBox = new JPasswordField();
        confirmUpdatesButton = new JLabel();
        deleteAccountButton = new JLabel();

        setupTextBox(usernameTextBox, USERNAME_X, USERNAME_Y);
        setupTextBox(emailTextBox, EMAIL_X, EMAIL_Y);
        setupTextBox(passwordTextBox, PASSWORD_X, PASSWORD_Y);

        confirmUpdatesButton.setBounds(CONFIRM_BUTTON_X, CONFIRM_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        confirmUpdatesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateAccountInformation();
            }
        });

        deleteAccountButton.setBounds(DELETE_BUTTON_X, DELETE_BUTTON_Y, DELETE_BUTTON_WIDTH, DELETE_BUTTON_HEIGHT);
        deleteAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                deleteAccount();
            }
        });

        this.setSize(new Dimension(parentFrame.getWidth(), parentFrame.getHeight()));
        this.add(usernameTextBox);
        this.add(emailTextBox);
        this.add(passwordTextBox);
        this.add(confirmUpdatesButton);
        this.add(deleteAccountButton);
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(settingsPageBackground, 0, 0, this);
    }

    void updateAccountInformation() {
        String newUsername = usernameTextBox.getText();
        String newEmail = emailTextBox.getText();
        String newPassword = String.valueOf(passwordTextBox.getPassword());

        if (newUsername.length() > 20) {
            JOptionPane.showMessageDialog(null, "Username cannot exceed 20 characters.", "Invalid Username", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (newPassword.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Password cannot contain spaces.", "Invalid Password", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

            StringBuilder updateQuery = new StringBuilder("UPDATE REGISTERED_USERS SET ");
            boolean firstField = true;

            if (!newUsername.isEmpty()) {
                updateQuery.append("username = ?");
                firstField = false;
            }
            if (!newEmail.isEmpty()) {
                if (!firstField) updateQuery.append(", ");
                updateQuery.append("PMU_Email = ?");
                firstField = false;
            }
            if (!newPassword.isEmpty()) {
                if (!firstField) updateQuery.append(", ");
                updateQuery.append("AccountPassword = ?");
            }
            updateQuery.append(" WHERE StudentID = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery.toString());

            int paramIndex = 1;
            if (!newUsername.isEmpty()) {
                preparedStatement.setString(paramIndex++, newUsername);
            }
            if (!newEmail.isEmpty()) {
                preparedStatement.setString(paramIndex++, newEmail);
            }
            if (!newPassword.isEmpty()) {
                preparedStatement.setString(paramIndex++, newPassword);
            }
            preparedStatement.setString(paramIndex, MenteeLoginPanel.StudentID);

            System.out.println("Executing query: " + preparedStatement.toString()); 

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Account information updated successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update account information.", "Update Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQLException: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void deleteAccount() {
        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?", "Delete Account", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

                String deleteQuery = "DELETE FROM REGISTERED_USERS WHERE StudentID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setString(1, MenteeLoginPanel.StudentID);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Account deleted successfully.", "Delete Successful", JOptionPane.INFORMATION_MESSAGE);
                    parentFrame.showHomePanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete account.", "Delete Failed", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}