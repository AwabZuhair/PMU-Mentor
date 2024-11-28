package V1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteAccountTest {

    private AccountInformationPanel accountInformationPanel;
    private Frame parentFrame;
    private final String testStudentID = "202201754"; 

    @Before
    public void setUp() {
        parentFrame = new Frame();
        accountInformationPanel = new AccountInformationPanel(parentFrame);
    }

    @Test
    public void testDeleteAccount() {
        MenteeLoginPanel.StudentID = testStudentID;

        accountInformationPanel.deleteAccount();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/pmu_mentor_project", "root", "Awab_hamadto123")) {

            String query = "SELECT * FROM REGISTERED_USERS WHERE StudentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, testStudentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            assertFalse(resultSet.next());

        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }
}