import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    String formno;
    JButton login, signup, clear;
    JTextField usernameTextField;
    JPasswordField passwordTextField;

    Login(String formno)
    {
        this.formno = formno;
        setLayout(null);

        setTitle("Content Management System"); /* Title */

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("bg1.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(800, 480, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(0, 0, 800, 480);
        add(image1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("logo.jpg")); /*get Image*/
        Image i2 = i1.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT); /*Scale image*/
        ImageIcon i3 = new ImageIcon(i2);/*Convert image*/
        JLabel label = new JLabel(i3); /*Label Image*/
        label.setBounds(70, 20, 100, 100); /*Position image*/
        image1.add(label); /*add image*/

        JLabel text = new JLabel("Welcome To Streamlined");
        text.setFont(new Font("Osward", Font.BOLD, 30));
        text.setBounds(250, 40, 600, 40);
        image1.add(text);

        JLabel text1 = new JLabel("A Content Management System");
        text1.setFont(new Font("Osward", Font.BOLD, 18));
        text1.setBounds(290, 70, 600, 40);
        image1.add(text1);

        JLabel cardno = new JLabel("Username:");
        cardno.setFont(new Font("Raieway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 150, 30);
        image1.add(cardno);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(300, 150, 230, 30);
        usernameTextField.setFont(new Font("Arial", Font.BOLD, 14));
        image1.add(usernameTextField);

        JLabel pin = new JLabel("Password:");
        pin.setFont(new Font("Raieway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        image1.add(pin);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(300, 220, 230, 30);
        passwordTextField.setFont(new Font("Arial", Font.BOLD, 14));
        image1.add(passwordTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        image1.add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        image1.add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        image1.add(signup);


        getContentPane().setBackground(Color.WHITE); /*Color of image background*/

        setSize(800, 480); /*Set Size of window*/
        setLocation(350, 200);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == clear) {
            passwordTextField.setText("");
            usernameTextField.setText("");
        } else if (ae.getSource() == login) {
            Database conn = new Database();
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            String query = "SELECT * FROM login where username = '" +username+ "' and  passwords = '"+password+"'";
            try {
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next())
                {
                    System.out.println(rs.getString(1));
                    formno = rs.getString(1);
                    new Content(formno).setVisible(true);
                    setVisible(false);
                } else
                {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                }

            } catch (Exception e)
            {
                System.out.println(e);
            }

        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignUp().setVisible(true);

        }


    }
    public static void main(String[] args) {
        new Login("");
    }
}