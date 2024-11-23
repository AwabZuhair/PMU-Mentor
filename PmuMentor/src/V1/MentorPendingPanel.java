package V1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MentorPendingPanel extends JPanel {

    public static final int SCREEN_WIDTH = Frame.WIDTH;
    public static final int SCREEN_HEIGHT = Frame.HEIGHT;

    public static final int BACK_BUTTON_WIDTH = 183;
    public static final int BACK_BUTTON_HEIGHT = 176;
    public static final int BACK_BUTTON_X = 75;
    public static final int BACK_BUTTON_Y = 75;

    public Color Background_Color = new Color(0, 25, 38);
    public Image PendingPanelBackground;

    JLabel BackButton;

    Frame parentFrame;

    MentorPendingPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;

        PendingPanelBackground = new ImageIcon("Mentor Application Sent.png").getImage();

        BackButton = new JLabel();
        BackButton.setBounds(BACK_BUTTON_X, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        BackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                parentFrame.showHomePanel();
            }
        });

        this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
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
        g.drawImage(PendingPanelBackground, 0, 0, this);
    }
}