package V1;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class MenteeLoginPanelTesting {

    private MenteeLoginPanel menteeLoginPanel;
    private Frame parentFrame;

    @Before
    public void setUp() {
        parentFrame = new Frame();
        menteeLoginPanel = new MenteeLoginPanel(parentFrame);
    }

    @Test
    public void testSuccessfulLogin() {
        menteeLoginPanel.StudentIDTextBox.setText("202201754");
        menteeLoginPanel.PasswordIDTextBox.setText("123FakePassword");

        boolean accountExists = false;
        try {
            accountExists = menteeLoginPanel.checkAccount("202201754", "123FakePassword");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }

        assertTrue(accountExists);
        assertEquals("AwabZuhair", MenteeLoginPanel.username);
    }

    @Test
    public void testUnsuccessfulLogin() {
        menteeLoginPanel.StudentIDTextBox.setText("nonexistentID");
        menteeLoginPanel.PasswordIDTextBox.setText("wrongPassword");

        boolean accountExists = false;
        try {
            accountExists = menteeLoginPanel.checkAccount("nonexistentID", "wrongPassword");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }

        assertFalse(accountExists);
    }
}