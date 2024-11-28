package V1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateAccountInformationTest {

    private AccountInformationPanel accountInformationPanel;
    private Frame parentFrame;

    @Before
    public void setUp() {
        parentFrame = new Frame();
        accountInformationPanel = new AccountInformationPanel(parentFrame);
    }

    @Test
    public void testUpdateAccountInformation() {
        accountInformationPanel.usernameTextBox.setText("newUsername");
        accountInformationPanel.emailTextBox.setText("newEmail@example.com"); 
        accountInformationPanel.passwordTextBox.setText("newPassword");

        String testStudentID = "202201754";
        MenteeLoginPanel.StudentID = testStudentID;

        printAccountInformation(testStudentID);

        accountInformationPanel.updateAccountInformation();

        printAccountInformation(testStudentID);

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

            String query = "SELECT * FROM REGISTERED_USERS WHERE StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, testStudentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                assertEquals("newUsername", resultSet.getString("username"));
                assertEquals("newEmail@example.com", resultSet.getString("PMU_Email"));
                assertEquals("newPassword", resultSet.getString("AccountPassword"));
            } else {
                fail("Account information was not updated.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }

    private void printAccountInformation(String studentID) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

            String query = "SELECT * FROM REGISTERED_USERS WHERE StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Email: " + resultSet.getString("PMU_Email"));
                System.out.println("Password: " + resultSet.getString("AccountPassword"));
            } else {
                System.out.println("No account found for StudentID: " + studentID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}