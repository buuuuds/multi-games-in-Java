/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pixelverse.app;

/**
 *
 * @author mendo
 */
import pixelverse.app.SnakeBoard;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class Snake extends JFrame {

    public Snake(String loggedInUsername) {
        
        initUI(loggedInUsername);
    }
    
    private void initUI(String loggedInUsername) {
        
        add(new SnakeBoard(loggedInUsername));
               
        setResizable(false);
        pack();
        
        setTitle("Wormz");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private boolean isValidUSername(String username){
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","s","c");
            String query = "Select * FROM users WHERE username = ?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1,username);
        
        rs = psmt.executeQuery();
        
        isValid = rs.next();
        
        }catch(SQLException e){
    e.printStackTrace();
        }finally{
            
        try{
        if (rs!= null) rs.close();
        if(psmt != null) psmt.close();
        if (conn != null) conn.close();
    }catch(SQLException e) {
        e.printStackTrace();
    }
}
        return isValid;
}
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
           String loggedInUsername = getUsernameFromDatabase();
           
            JFrame ex = new Snake(loggedInUsername);
            ex.setVisible(true);
        });
    }
        private static String getUsernameFromDatabase(){
        
             return JOptionPane.showInputDialog("Enter your username");
    }
        private static void showLeaderboard() {
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "s", "c");
         PreparedStatement pstmt = conn.prepareStatement("SELECT ldbwormz.username, ldbwormz.score " +
                                                           "FROM ldbwormz " +
                                                           "JOIN users ON users.username = ldbwormz.username " +
                                                           "ORDER BY ldbsnake.score DESC " +
                                                           "LIMIT 10")) {
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String username = rs.getString("username");
            int score = rs.getInt("score");
            System.out.println(username + ": " + score);
            // Add code to display username and score
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.err.println("Error fetching leaderboard: " + ex.getMessage());
    }
}
}


