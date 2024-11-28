package V1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemTest {

    private Frame frame;
    private MenteeSignUpPanel menteeSignUpPanel;
    private MenteeLoginPanel menteeLoginPanel;
    private AccountInformationPanel accountInformationPanel;
    private SignUpAsMentorPanel signUpAsMentorPanel;

    @Before
    public void setUp() {
        frame = new Frame();
        menteeSignUpPanel = new MenteeSignUpPanel(frame);
        menteeLoginPanel = new MenteeLoginPanel(frame);
        accountInformationPanel = new AccountInformationPanel(frame);
        signUpAsMentorPanel = new SignUpAsMentorPanel(frame);
    }

    @Test
    public void testSystemFlow() {
        menteeSignUpPanel.PMUIDTextBox.setText("202201755");
        menteeSignUpPanel.UsernameTextBox.setText("newMentee");
        menteeSignUpPanel.EmailTextBox.setText("mentee@example.com"); 
        menteeSignUpPanel.MajorTextBox.setText("Computer Science");
        menteeSignUpPanel.PasswordTextBox.setText("newPassword");
        menteeSignUpPanel.ReEnterPasswordTextBox.setText("newPassword");
        menteeSignUpPanel.FirstNameTextBox.setText("John");
        menteeSignUpPanel.LastNameTextBox.setText("Doe");
        menteeSignUpPanel.MiddleInitialTextBox.setText("A");

        menteeSignUpPanel.createMenteeAccount();

        verifyAccountCreation("202201755", "newMentee", "mentee@example.com", "Computer Science", "newPassword", "John", "Doe", "A");

        menteeLoginPanel.StudentIDTextBox.setText("202201755");
        menteeLoginPanel.PasswordIDTextBox.setText("newPassword");

        boolean accountExists = false;
        try {
            accountExists = menteeLoginPanel.checkAccount("202201755", "newPassword");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }

        assertTrue(accountExists);

        accountInformationPanel.usernameTextBox.setText("updatedMentee");
        accountInformationPanel.emailTextBox.setText("updated@example.com");
        accountInformationPanel.passwordTextBox.setText("updatedPassword");

        MenteeLoginPanel.StudentID = "202201755";
        accountInformationPanel.updateAccountInformation();

        verifyAccountUpdate("202201755", "updatedMentee", "updated@example.com", "updatedPassword");

        accountInformationPanel.deleteAccount();

        verifyAccountDeletion("202201755");

        signUpAsMentorPanel.PMUIDTextBox.setText("202201756");
        signUpAsMentorPanel.UsernameTextBox.setText("newMentor");
        signUpAsMentorPanel.EmailTextBox.setText("mentor@example.com"); 
        signUpAsMentorPanel.MajorTextBox.setText("Engineering");
        signUpAsMentorPanel.PasswordTextBox.setText("mentorPassword");
        signUpAsMentorPanel.ReEnterPasswordTextBox.setText("mentorPassword");
        signUpAsMentorPanel.FirstNameTextBox.setText("Jane");
        signUpAsMentorPanel.LastNameTextBox.setText("Smith");
        signUpAsMentorPanel.MiddleInitialTextBox.setText("B");
        signUpAsMentorPanel.GPATextBox.setText("3.8");
        signUpAsMentorPanel.LevelTextBox.setText("Senior");
        signUpAsMentorPanel.TeachTextBox.setText("Math");

        signUpAsMentorPanel.createMentorAccount();

        verifyMentorAccountCreation("202201756", "newMentor", "mentor@example.com", "Engineering", "mentorPassword", "Jane", "Smith", "B", "3.8", "Senior", "Math");
    }

    private void verifyAccountCreation(String studentID, String username, String email, String major, String password, String firstName, String lastName, String middleInitial) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

            String query = "SELECT * FROM REGISTERED_USERS WHERE StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                assertEquals(username, resultSet.getString("username"));
                assertEquals(email, resultSet.getString("PMU_Email"));
                assertEquals(major, resultSet.getString("Major"));
                assertEquals(password, resultSet.getString("AccountPassword"));
                assertEquals(firstName, resultSet.getString("Firstname"));
                assertEquals(lastName, resultSet.getString("LastName"));
                assertEquals(middleInitial, resultSet.getString("MiddleInitial"));
            } else {
                fail("Account was not created.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }

    private void verifyAccountUpdate(String studentID, String username, String email, String password) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

            String query = "SELECT * FROM REGISTERED_USERS WHERE StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                assertEquals(username, resultSet.getString("username"));
                assertEquals(email, resultSet.getString("PMU_Email"));
                assertEquals(password, resultSet.getString("AccountPassword"));
            } else {
                fail("Account information was not updated.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }

    private void verifyAccountDeletion(String studentID) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

            String query = "SELECT * FROM REGISTERED_USERS WHERE StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            assertFalse(resultSet.next());

        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }

    private void verifyMentorAccountCreation(String studentID, String username, String email, String major, String password, String firstName, String lastName, String middleInitial, String gpa, String level, String teach) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

            String query = "SELECT * FROM PENDING_MENTORS WHERE StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                assertEquals(username, resultSet.getString("username"));
                assertEquals(email, resultSet.getString("PMU_Email"));
                assertEquals(major, resultSet.getString("Major"));
                assertEquals(password, resultSet.getString("AccountPassword"));
                assertEquals(firstName, resultSet.getString("Firstname"));
                assertEquals(lastName, resultSet.getString("LastName"));
                assertEquals(middleInitial, resultSet.getString("MiddleInitial"));
                assertEquals(gpa, resultSet.getString("GPA"));
                assertEquals(level, resultSet.getString("Current_Level"));
                assertEquals(teach, resultSet.getString("Teach"));
            } else {
                fail("Mentor account was not created.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }
}