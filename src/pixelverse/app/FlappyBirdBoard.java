package pixelverse.app;

import javax.swing.*;
import java.awt.*;

public class FlappyBirdBoard extends JPanel {

    private static final long serialVersionUID = 1L;
    private FlappyBird flappyBirdInstance;

    public FlappyBirdBoard(FlappyBird flappyBirdInstance) {
        this.flappyBirdInstance = flappyBirdInstance;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (flappyBirdInstance != null) {
            flappyBirdInstance.repaint(g);
        }
    }
}
