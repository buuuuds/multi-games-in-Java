                                                                                         /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package pixelverse.app;

    import pixelverse.app.Maingames;
    import java.awt.*;
    import javax.swing.*;
    import java.awt.event.*;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;


    public class SnakeBoard extends JPanel implements ActionListener {


        private final int B_WIDTH = 635;
        private final int B_HEIGHT = 439;
        private final int DOT_SIZE = 10;
        private final int ALL_DOTS = 900;
        private final int RAND_POS = 25;
        private final int DELAY = 110;

        private final int x[] = new int[ALL_DOTS];
        private final int y[] = new int[ALL_DOTS];

        private int dots;
        private int apple_x;
        private int apple_y;
        private int score = 0;

        private boolean leftDirection = false;
        private boolean rightDirection = true;
        private boolean upDirection = false;
        private boolean downDirection = false;
        private boolean inGame = true;
        private boolean scoreStored = false; 
        private boolean paused = false;



        private Timer timer;
        private Image ball;
        private Image apple;
        private Image head;
        static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
        static final String USER = "root";
        static final String PASS = "pixelverse2024";
        private String loggedInUsername;
        
        
        public SnakeBoard(String username) {
            this.loggedInUsername = username;
            initBoard();
        }


       
         
        private void initBoard() {

            addKeyListener(new TAdapter());
            setBackground(new Color(0, 100, 0));
            setFocusable(true);

            setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
            loadImages();
            initGame();

        }


        private void loadImages() {

            ImageIcon iid = new ImageIcon("C:\\Users\\mendo\\OneDrive\\Desktop\\PixelVerse-App\\src\\pixelverse\\app\\body.png");
            ball = iid.getImage();

            ImageIcon iia = new ImageIcon("C:\\Users\\mendo\\OneDrive\\Desktop\\PixelVerse-App\\src\\pixelverse\\app\\food.png");
            apple = iia.getImage();

            ImageIcon iih = new ImageIcon("C:\\Users\\mendo\\OneDrive\\Desktop\\PixelVerse-App\\src\\pixelverse\\app\\head.png");
            head = iih.getImage();

        }

        private void initGame() {
             scoreStored = false;

            dots = 3;

            for (int z = 0; z < dots; z++) {
                x[z] = 50 - z * 10;
                y[z] = 50;
            }

            locateApple();

            timer = new Timer(DELAY, this);
            timer.start();

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            doDrawing(g);
        }
        

        private void doDrawing(Graphics g) {

            if (inGame) {

                g.drawImage(apple, apple_x, apple_y, this);

                for (int z = 0; z < dots; z++) {
                    if (z == 0) {
                        g.drawImage(head, x[z], y[z], this);
                    } else {
                        g.drawImage(ball, x[z], y[z], this);
                    }
                }

            g.setColor(Color.white);
            g.setFont(new Font("i pixel u", Font.BOLD, 18));
            g.drawString("Score: " + score, 10, 20);

                Toolkit.getDefaultToolkit().sync();

            } else {

                gameOver(g);
            } 
            if (paused) {
                
            Font pauseFont = new Font("i pixel u", Font.BOLD, 48);
            FontMetrics pauseMetrics = getFontMetrics(pauseFont);
            g.setColor(Color.white);
            g.setFont(pauseFont);
            String pauseMsg = "GAME PAUSE";
            g.drawString(pauseMsg, (B_WIDTH - pauseMetrics.stringWidth(pauseMsg)) / 2, B_HEIGHT / 2);
            }
        }
        

        private void gameOver(Graphics g) {

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\mendo\\OneDrive\\Documents\\NetBeansProjects\\FlappyBird\\TetrisJava\\src\\tetrisjava\\bg_1.jpg"); 
        Image bgImage = backgroundImage.getImage();
        
     
        g.drawImage(bgImage, 0, 0, B_WIDTH, B_HEIGHT, this);

            String msg = "Game Over";
            Font small = new Font("i pixel u", Font.BOLD, 72);
            FontMetrics metr = getFontMetrics(small);

            g.setColor(Color.red);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);

            String scoreMsg = "Score: " + score;
            Font smaller = new Font("i pixel u", Font.BOLD, 36);
            FontMetrics scoreMetr = getFontMetrics(smaller);
            g.setColor(Color.black);
            g.setFont(smaller);
            g.drawString(scoreMsg, (B_WIDTH - scoreMetr.stringWidth(scoreMsg)) / 2, (B_HEIGHT / 2) - 100);

            JButton backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back to the main menu?", "Confirmation", JOptionPane.YES_NO_OPTION);

            
            if (dialogResult == JOptionPane.YES_OPTION) {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(SnakeBoard.this);
            currentFrame.dispose();
            Maingames mg = new Maingames(loggedInUsername);
            mg.show();
            currentFrame.dispose();
            }else{
                 }
            }
        });
        backButton.setBounds((B_WIDTH - 90) / 2, (B_HEIGHT + 80) / 2, 120, 40);
        backButton.setFont(new Font("i pixel u", Font.PLAIN, 16));
        add(backButton);


        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
       JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(SnakeBoard.this);
      
                currentFrame.dispose();
        JFrame newFrame = new JFrame("Snake Game");
        SnakeBoard newGameBoard = new SnakeBoard(loggedInUsername);
        newFrame.add(newGameBoard);
        newFrame.pack();
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setLocationRelativeTo(null); 
        newFrame.setVisible(true);

               
            }
        });
        playAgainButton.setBounds((B_WIDTH - 100) / 2 + 130, (B_HEIGHT + 80) / 2, 120, 40);
        playAgainButton.setFont(new Font("i pixel u", Font.PLAIN, 16));
        add(playAgainButton);


     
        insertScore(loggedInUsername, score);
        
        scoreStored = true;
        

        }





private void insertScore(String username, int score) {
    try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
        String sql = "INSERT INTO ldbwormz (username, score) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setInt(2, score);
            statement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    




        private void checkApple() {

            if ((x[0] == apple_x) && (y[0] == apple_y)) {

                dots++;
                score++;
                locateApple();
            }
        }

        private void move() {

            for (int z = dots; z > 0; z--) {
                x[z] = x[(z - 1)];
                y[z] = y[(z - 1)];
            }

            if (leftDirection) {
                x[0] -= DOT_SIZE;
            }

            if (rightDirection) {
                x[0] += DOT_SIZE;
            }

            if (upDirection) {
                y[0] -= DOT_SIZE;
            }

            if (downDirection) {
                y[0] += DOT_SIZE;
            }
        }

        private void checkCollision() {

            for (int z = dots; z > 0; z--) {

                if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                    inGame = false;
                }
            }

            if (y[0] >= B_HEIGHT) {
                inGame = false;
            }

            if (y[0] < 0) {
                inGame = false;
            }

            if (x[0] >= B_WIDTH) {
                inGame = false;
            }

            if (x[0] < 0) {
                inGame = false;
            }

            if (!inGame) {
                timer.stop();
            }
        }

        private void locateApple() {

            int r = (int) (Math.random() * RAND_POS);
            apple_x = ((r * DOT_SIZE));

            r = (int) (Math.random() * RAND_POS);
            apple_y = ((r * DOT_SIZE));
        }

        @Override
        public void actionPerformed(ActionEvent e) {


            if (inGame) {

                checkApple();
                checkCollision();
                move();
            }

            repaint();
            }



        private class TAdapter extends KeyAdapter {

            @Override
            public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();

                if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                    leftDirection = true;
                    upDirection = false;
                    downDirection = false;
                }

                if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                    rightDirection = true;
                    upDirection = false;
                    downDirection = false;
                }

                if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                    upDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }

                if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                    downDirection = true;
                    rightDirection = false; 
                    leftDirection = false;
                }
                
                if (key == KeyEvent.VK_SPACE) { 
                if (paused) {
                    resumeGame();
                } else {
                    pauseGame();
                }
            } else {
                if (!paused) {
                    handleDirectionKey(key);
                }
            }
                
            }
        }
        private void pauseGame() {
        paused = true;
        timer.stop();
        repaint();
    }

    private void resumeGame() {
        paused = false;
        timer.start();
        repaint();
    }

    private void handleDirectionKey(int key) {
        // Handle direction key presses
    }
    }  
   
  