package V1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenteeSignUpPanelTesting {

    private MenteeSignUpPanel menteeSignUpPanel;
    private Frame parentFrame;

    @Before
    public void setUp() {
        parentFrame = new Frame();
        menteeSignUpPanel = new MenteeSignUpPanel(parentFrame);
    }

    @Test
    public void testCreateMenteeAccount() {
        menteeSignUpPanel.PMUIDTextBox.setText("202201754");
        menteeSignUpPanel.UsernameTextBox.setText("AwabZuhair");
        menteeSignUpPanel.EmailTextBox.setText("a@example.com123456"); // 20 characters long
        menteeSignUpPanel.MajorTextBox.setText("Computer Science");
        menteeSignUpPanel.PasswordTextBox.setText("123FakePassword");
        menteeSignUpPanel.ReEnterPasswordTextBox.setText("123FakePassword");
        menteeSignUpPanel.FirstNameTextBox.setText("Awwab");
        menteeSignUpPanel.LastNameTextBox.setText("Hamdatu");
        menteeSignUpPanel.MiddleInitialTextBox.setText("Z");

        menteeSignUpPanel.createMenteeAccount();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

            String query = "SELECT * FROM REGISTERED_USERS WHERE StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "202201754");

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                assertEquals("AwabZuhair", resultSet.getString("username"));
                assertEquals("a@example.com123456", resultSet.getString("PMU_Email"));
                assertEquals("Computer Science", resultSet.getString("Major"));
                assertEquals("123FakePassword", resultSet.getString("AccountPassword"));
                assertEquals("Awwab", resultSet.getString("Firstname"));
                assertEquals("Hamdatu", resultSet.getString("LastName"));
                assertEquals("Z", resultSet.getString("MiddleInitial"));
            } else {
                fail("Account was not created.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown: " + e.getMessage());
        }
    }
}