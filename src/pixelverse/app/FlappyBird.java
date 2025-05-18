 package pixelverse.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.Timer;
import pixelverse.app.FlappyBirdBoard;

public class FlappyBird implements ActionListener, MouseListener, KeyListener {

    public static FlappyBird flappyBird;
    public final int WIDTH = 633, HEIGHT = 436;
    public FlappyBirdBoard renderer;
    public Rectangle bird;
    public ArrayList<Rectangle> columns;
    public int ticks, yMotion, score;
    public boolean gameOver, started, paused;
    public Random rand;

    static final String JDBC_URL = "jdbc:mysql://localhost:3306/mysql";
    static final String USERNAME = "root";
    static final String PASSWORD = "pixelverse2024";

    private Timer timer;
    private boolean scoreStored = false;
    String loggedInUsername;
    
    private JButton playAgainButton;
    private JButton backButton;
    private JFrame frame;
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String loggedInUsername = getUsernameFromDatabase();
            FlappyBird flappyBird = new FlappyBird(loggedInUsername);
            flappyBird.initGame();
        });
    }

    private static String getUsernameFromDatabase() {
        return JOptionPane.showInputDialog("Enter your username");
    }

    public FlappyBird(String username) {
        this.loggedInUsername = username;
    }


    void initGame() {
        JFrame jframe = new JFrame();
        timer = new Timer(20, this);
        renderer = new FlappyBirdBoard(this);
        rand = new Random();

        jframe.add(renderer);
        jframe.setTitle("Flappy Bird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setLocationRelativeTo(null);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setUndecorated(true);
        jframe.setVisible(true);

       

        
        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 8, 20, 20);
        columns = new ArrayList<>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }

    public void addColumn(boolean start) {
        int space = 250;
        int width = 80;
        int height = 50 + rand.nextInt(100);

        if (start) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        } else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
        }
    }

    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.black);
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void jump() {
        if (gameOver) {
            bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 8, 20, 20);
            columns.clear();
            yMotion = 0;
            score = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;
        }

        if (!started) {
            started = true;
        } else if (!gameOver && !paused) {
            if (yMotion > 0) {
                yMotion = 0;
            }

            yMotion -= 10;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int speed = 8;

        ticks++;

        if (started && !gameOver) { // Check if the game is started and not over
        for (int i = 0; i < columns.size(); i++) {
            Rectangle column = columns.get(i);
            column.x -= speed;
            
        }

        if (ticks % 2 == 0 && yMotion < 15) {
            yMotion += 2;
        }

        for (int i = 0; i < columns.size(); i++) {
            Rectangle column = columns.get(i);

            if (column.x + column.width < 0) {
                columns.remove(column);

                if (column.y == 0) {
                    addColumn(false);
                }
            }
        }

        bird.y += yMotion;

        for (Rectangle column : columns) {
            if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10) {
                // Increment score by 1 only when every second column is passed
                if (ticks % 2 == 0) {
                    score++;
                }
            }

            if (column.intersects(bird)) {
                gameOver = true;

                if (bird.x <= column.x) {
                    bird.x = column.x - bird.width;
                } else {
                    if (column.y != 0) {
                        bird.y = column.y - bird.height;
                    } else if (bird.y < column.height) {
                        bird.y = column.height;
                    }
                }
            }
        }

        if (bird.y > HEIGHT - 120 || bird.y < 0) {
            gameOver = true;
        }

        if (bird.y + yMotion >= HEIGHT - 120) {
            bird.y = HEIGHT - 120 - bird.height;
            gameOver = true;
        }
    }

    renderer.repaint();
    if (gameOver && !scoreStored) {
        // Insert score into the database
        insertScore(loggedInUsername, score);
        scoreStored = true;
        
        GameOverFrame gameOverFrame = new GameOverFrame(this);
        gameOverFrame.setVisible(true);
    }
    }

    private void insertScore(String username, int score) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO ldbtwintower (username, score) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setInt(2, score);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void repaint(Graphics g) {
        g.setColor(new Color(7, 15, 43));  // background
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(new Color(51, 51, 51));  //underland
        g.fillRect(0, HEIGHT - 120, WIDTH, 120);

        g.setColor(new Color(102, 102, 102));  //land
        g.fillRect(0, HEIGHT - 120, WIDTH, 20);
        
        

        if (!gameOver) {
            g.setColor(Color.red); 
            } else {
                g.setColor(Color.yellow); 
            }
            g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle column : columns) {
            paintColumn(g, column);
        }

        g.setColor(Color.white);
        g.setFont(new Font("i pixel u", 1, 20));
        

        if (!started) {
            g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
        }

        if (gameOver) {
            g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
        }

        if (!gameOver && started) {
            g.drawString("Score: " + String.valueOf(score), 10, 30);
        }
        
        if (paused) {
        g.setColor(Color.white);
        g.setFont(new Font("i pixel u", Font.BOLD, 40));
        String pauseText = "PAUSE";
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(pauseText);
        int x = (WIDTH - textWidth) / 2;
        int y = HEIGHT / 2;
        g.drawString(pauseText, x, y);
    }
        
    }
    
    


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (timer != null && timer.isRunning()) {
                // Pause the timer
                timer.stop();
                paused = true; // Set the game as paused
            } else if (timer != null) {
                // Resume the timer
                timer.start();
                paused = false; // Set the game as resumed
            }
        }else if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Check if the Enter key is pressed
            if (timer != null && timer.isRunning()) {
                // Pause the timer
                timer.stop();
                paused = true; // Set the game as paused
            } else if (timer != null) {
                // Resume the timer
                timer.start();
                paused = false; // Set the game as resumed
            }
        }
    }

    void restartGame() {
        // Reset game state to start a new game
        gameOver = false;
        started = false;
        paused = false;
        scoreStored = false;
        score = 0;
        columns.clear();
        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);
        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 8, 20, 20);
        yMotion = 0;
     
        timer.restart(); // Restart the timer
    }

    void show() {
        flappyBird = new FlappyBird(loggedInUsername);
    }

      public void disposeFrame() {
        frame.dispose();
    }
}
