// GameOverFrame.java
package pixelverse.app;

    import java.awt.*;
    import javax.swing.*;
    import java.awt.event.*;

public class GameOverFrame extends JFrame {
    private String loggedInUsername;
    private JButton playAgainButton;
    private JButton backButton;
    private FlappyBird flappyBird;

    public GameOverFrame(FlappyBird flappyBird) {
        this.flappyBird = flappyBird;
        initComponents();
    }

    private void initComponents() {
        setTitle("Game Over");
        setSize(635, 439);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("i pixel u", Font.PLAIN, 72));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gameOverLabel, BorderLayout.CENTER);

        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flappyBird.restartGame();
                dispose(); 
            }
        });
        playAgainButton.setPreferredSize(new Dimension(120, 40));
        playAgainButton.setFont(new Font("i pixel u", Font.PLAIN, 16));// Set preferred size
        add(playAgainButton, BorderLayout.SOUTH);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Maingames mg = new Maingames(loggedInUsername);
                    mg.show();
                dispose(); 
            }
        });
        backButton.setPreferredSize(new Dimension(120, 40)); // Set preferred size
        backButton.setFont(new Font("i pixel u", Font.PLAIN, 16));
        add(backButton, BorderLayout.NORTH);

      
        playAgainButton.setVisible(true);
        backButton.setVisible(true);
    }
}
