package V1;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class MentorLoginPanelTesting {

    private MentorLoginPanel mentorLoginPanel;
    private Frame parentFrame;

    @Before
    public void setUp() {
        parentFrame = new Frame();
        mentorLoginPanel = new MentorLoginPanel(parentFrame);
    }

    @Test
    public void testSuccessfulLogin() {
        mentorLoginPanel.MentorIDTextBox.setText("202201753");
        mentorLoginPanel.PasswordIDTextBox.setText("123FakePassword");

        boolean accountExists = false;
        try {
            accountExists = mentorLoginPanel.checkAccount("202201753", "123FakePassword");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }

        assertTrue(accountExists);
        assertEquals("Mohammed", MentorLoginPanel.username);
    }

    @Test
    public void testUnsuccessfulLogin() {
        mentorLoginPanel.MentorIDTextBox.setText("nonexistentID");
        mentorLoginPanel.PasswordIDTextBox.setText("wrongPassword");

        boolean accountExists = false;
        try {
            accountExists = mentorLoginPanel.checkAccount("nonexistentID", "wrongPassword");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }

        assertFalse(accountExists);
    }
}