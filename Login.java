package InstituteProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener, KeyListener
{
    JLabel heading, lblun, lblpsw;
    JTextField txtuser;
    JPasswordField txtpsw;
    JButton btnlog, btnclr;
    static int attempt = 0;
    Connection con;
    Statement stmt;
    ResultSet rs;
    Color c1 = new Color(203, 255, 250);
    Color c2 =new Color(199, 0, 57 );
    Font font1 = new Font("Courier", Font.BOLD, 22);

    Login()
    {
        heading = new JLabel("Login");
        heading.setBounds(160, 30, 200, 50);
        heading.setFont(font1);
        heading.setForeground(c2);
        add(heading);

        lblun = new JLabel("Username");
        lblun.setBounds(50, 100, 100, 30);
        add(lblun);

        txtuser = new JTextField();
        txtuser.setBounds(200, 100, 150, 30);
        add(txtuser);

        lblpsw = new JLabel("Password");
        lblpsw.setBounds(50, 150, 150, 30);
        add(lblpsw);

        txtpsw = new JPasswordField();
        txtpsw.setBounds(200, 150, 150, 30);
        add(txtpsw);

        btnlog = new JButton("Login");
        btnlog.setBounds(50, 220, 70, 30);
        add(btnlog);
        btnlog.setBackground(c1);
        btnlog.addActionListener(this);
        btnlog.addKeyListener(this);

        btnclr = new JButton("Clear");
        btnclr.setBounds(250, 220, 70, 30);
        add(btnclr);
        btnclr.setBackground(c1);
        btnclr.addActionListener(this);

        ImageIcon img=new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\myProject\\src\\InstituteProject\\img5.jpg");
        JLabel background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,600,680);

        setLayout(null);
        setSize(400, 400);
        setLocation(400, 150);
        setTitle("Login Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(background);

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //JOptionPane.showMessageDialog(null,"Driver Connected");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituteprj", "root", "Rohit@1410");
            //JOptionPane.showMessageDialog(null,"Connected");
        }
        catch(Exception e1)
        {
            JOptionPane.showMessageDialog(null, e1);
        }
    }

    public boolean validateFiels()
    {
        String username = txtuser.getText();
        String password = txtpsw.getText();
        if(txtuser.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Username");
            return false;
        }
        if(!username.matches("^[a-zA-Z ]+"))
        {
            JOptionPane.showMessageDialog(null,"Enter Correct Username","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(txtpsw.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Password");
            return false;
        }
        if(!password.matches("^[0-9]{4}"))
        {
            JOptionPane.showMessageDialog(null,"Enter Correct Password","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(validateFiels()) {
            if (btnlog == e.getSource()) {
                try {

                    String user = txtuser.getText();
                    String pass = txtpsw.getText();

                    stmt = con.createStatement();
                    String sql = "select * from login where username='" + user + "' and password='" + pass + "'";
                    rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login Successfully");
                        setVisible(false);

                        new Course();

                    } else if (user == txtuser.getText() && pass == txtpsw.getText()) {
                        JOptionPane.showMessageDialog(null, "Incorrect username and password");
                        txtuser.setText("");
                        txtpsw.setText("");
                    } else {
                        attempt++;
                        JOptionPane.showMessageDialog(null, "Login Failed", "Login", JOptionPane.ERROR_MESSAGE);
                        txtuser.setText("");
                        txtpsw.setText("");
                        txtuser.requestFocus();
                        if (attempt == 3) {
                            JOptionPane.showMessageDialog(null, "3 Attempts Over", "Login", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }
                    }
                } catch (Exception e2) {

                    JOptionPane.showMessageDialog(null, e2);
                }
            }
        }

        if (btnclr == e.getSource())
        {
            txtuser.setText("");
            txtpsw.setText("");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        char[] pass = txtpsw.getPassword();
//        if (btnlog == e.getSource()) {
//            if (txtuser.getText().length() == 0) {
//                JOptionPane.showMessageDialog(null, "Please Enter Username");
//            } else if (!txtuser.getText().matches("[a-zA-Z]+")) {
//                JOptionPane.showMessageDialog(null, "Invalid Username");
//            } else if (pass.length == 0) {
//                JOptionPane.showMessageDialog(null, "Please Enter Username");
//            }
//
//        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
