/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pixelverse.app;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author mendo
 */
public class pixelCreate extends javax.swing.JFrame {

    /**
     * Creates new form pixelCreate
     */
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    static final String USER = "root";
    static final String PASS = "pixelverse2024";

    // SQL INSERT query to insert user data into the "users" table
    static final String INSERT_QUERY = "INSERT INTO users (username, password) VALUES (?, ?)";

    public pixelCreate() {
        initComponents();
    
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(645, 339));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setFont(new java.awt.Font("Daydream", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Back");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 360, 110, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\mendo\\OneDrive\\Documents\\NetBeansProjects\\FlappyBird\\TetrisJava\\src\\tetrisjava\\resizedbg.png")); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 153, 255));
        jPanel2.setForeground(new java.awt.Color(204, 153, 255));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Daydream", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("username     ");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(20, 60, 270, 45);

        jLabel2.setFont(new java.awt.Font("Daydream", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("password     ");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(20, 120, 184, 27);

        jLabel3.setFont(new java.awt.Font("Daydream", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("re-enter password");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 160, 260, 40);

        jTextField1.setBackground(new java.awt.Color(0, 102, 102));
        jTextField1.setFont(new java.awt.Font("Minecraft", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1);
        jTextField1.setBounds(280, 60, 180, 40);

        jTextField2.setBackground(new java.awt.Color(0, 102, 102));
        jTextField2.setFont(new java.awt.Font("Minecraft", 0, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField2);
        jTextField2.setBounds(280, 110, 180, 40);

        jTextField3.setBackground(new java.awt.Color(0, 102, 102));
        jTextField3.setFont(new java.awt.Font("Minecraft", 0, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField3);
        jTextField3.setBounds(280, 160, 180, 40);

        jButton1.setBackground(new java.awt.Color(51, 153, 0));
        jButton1.setFont(new java.awt.Font("Minecraft", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Register");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(290, 220, 150, 30);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 490, 270));

        jLabel7.setFont(new java.awt.Font("Daydream", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 153));
        jLabel7.setText("Create account");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pixelverse/app/nightbg.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 430));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 420));

        setSize(new java.awt.Dimension(626, 421));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String username = jTextField1.getText();
        String password = jTextField2.getText();
        String reEnterPassword = jTextField3.getText();
           jLabel4.setText("");
            jLabel5.setText("");
        // Check if any field is empty
    if (username.isEmpty() || password.isEmpty() || reEnterPassword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Information", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Check if the password and re-entered password match
    if (!password.equals(reEnterPassword)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match. Please re-enter the password.", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Check if the username already exists in the database
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?")) {

        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        // If a row is returned, the username already exists
        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.", "Username Exists", JOptionPane.ERROR_MESSAGE);
            return;
        }


    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // If all checks pass, insert the new user into the database
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?")) {

        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        // If a row is returned, the username already exists
        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.", "Username Exists", JOptionPane.ERROR_MESSAGE);
            return;
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Password validation
    if (password.length() < 8) {
        jLabel4.setText("Password must be at least 8 characters long.");
        return;
    }

    if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[^a-zA-Z0-9].*")) {
        jLabel5.setText("Password must contain both uppercase and lowercase letters, as well as special characters.");
        return;
    }

    // Insert the new user into the database
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement(INSERT_QUERY)) {

        pstmt.setString(1, username);
        pstmt.setString(2, password);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Registered Successfully", "Registered", JOptionPane.INFORMATION_MESSAGE);
            Mainmenu mm = new Mainmenu();
            mm.show();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
    }

 
       
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
        jButton2.setForeground(new Color(51,0,0));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        // TODO add your handling code here:
        jButton2.setForeground(new Color(255,102,102));
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Mainmenu mm = new Mainmenu();
        mm.show();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pixelCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pixelCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pixelCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pixelCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pixelCreate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
