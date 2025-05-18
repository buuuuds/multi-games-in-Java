/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pixelverse.app;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class loading extends JFrame {

    private JProgressBar progressBar;
    private Timer timer;
    private int progress;

    public loading() {
        initComponents();
        setSize(190, 30);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Set as undecorated
      

        // Initialize progress bar and timer
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE); 
        progressBar.setForeground(new Color(204, 0, 204)); 
        progressBar.setBounds(20, 50, 260, 30);
        progressBar.setFont(new Font("Minecraaft", Font.PLAIN, 14));
        add(progressBar);

        timer = new Timer(20, e -> {
            if (progress < 100) {
                progress++;
                progressBar.setValue(progress);
            } else {
                timer.stop();
                dispose();
                // Show the main menu after loading
                Mainmenu mainMenu = new Mainmenu();
                mainMenu.setVisible(true);
            }
        });
        timer.start(); // Start the timer to update progress
    }

    private void initComponents() {
        JLabel loadingLabel = new JLabel("Loading Main Menu...", SwingConstants.CENTER);
        loadingLabel.setForeground(Color.YELLOW); // Set text color to yellow
        loadingLabel.setFont(new Font("Minecraaft", Font.PLAIN, 14)); // Set font to Minecraaft, plain style, size 14
        getContentPane().add(loadingLabel);
    }

    public static void main(String[] args) {
        // Display the loading screen
        loading loadingScreen = new loading();
        loadingScreen.setVisible(true);
    }
}