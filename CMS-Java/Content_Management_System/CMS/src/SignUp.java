import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SignUp extends JFrame implements ActionListener {

    long random;
    JTextField fnameTextField, snameTextField, emailTextField, addressTextField, cityTextField, stateTextField, pincodeTextField, usernameTextField;
    JButton next;
    JRadioButton male, female;
    JDateChooser dateChooser;
    JPasswordField passwordTextField;

    SignUp()
    {
        setLayout(null);

        Random ran = new Random();
        random  = Math.abs((ran.nextLong() % 9000L) + 1000L);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("bg1.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(850, 800, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(0, 0, 850, 800);
        add(image1);

        JLabel formno = new JLabel("SIGN UP FORM: " + random);
        formno.setFont(new Font("Simplifica", Font.BOLD, 34));
        formno.setBounds(260,20,600,40);
        formno.setForeground(Color.BLACK);
        image1.add(formno);

        JLabel personalDetails = new JLabel("Personal Details");
        personalDetails.setFont(new Font("Raieway", Font.BOLD, 25));
        personalDetails.setBounds(350,80,400,30);
        personalDetails.setForeground(Color.BLACK);
        image1.add(personalDetails);


        JLabel fname = new JLabel("First Name: ");
        fname.setFont(new Font("Raieway", Font.BOLD, 22));
        fname.setBounds(100,140,200,30);
        fname.setForeground(Color.BLACK);
        image1.add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raieway", Font.BOLD, 14));
        fnameTextField.setBounds(300, 140, 400, 30);
        image1.add(fnameTextField);



        JLabel sname = new JLabel("Surname: ");
        sname.setFont(new Font("Raieway", Font.BOLD, 22));
        sname.setBounds(100,190,200,30);
        sname.setForeground(Color.BLACK);
        image1.add(sname);

        snameTextField = new JTextField();
        snameTextField.setFont(new Font("Raieway", Font.BOLD, 14));
        snameTextField.setBounds(300, 190, 400, 30);
        image1.add(snameTextField);



        JLabel dob = new JLabel("Date of Birth: ");
        dob.setFont(new Font("Raieway", Font.BOLD, 22));
        dob.setBounds(100,240,200,30);
        dob.setForeground(Color.BLACK);
        image1.add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Raieway", Font.BOLD, 14));
        dateChooser.setBounds(300, 240, 400, 30);
        image1.add(dateChooser);



        JLabel gender = new JLabel("Gender: ");
        gender.setFont(new Font("Raieway", Font.BOLD, 22));
        gender.setBounds(100,290,200,30);
        gender.setForeground(Color.BLACK);
        image1.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Raieway", Font.BOLD, 14));
        male.setBounds(300, 290, 60, 30);
        male.setBackground(Color.WHITE);
        image1.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Raieway", Font.BOLD, 14));
        female.setBounds(450, 290, 90, 30);
        female.setBackground(Color.WHITE);
        image1.add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);



        JLabel email = new JLabel("Email Address: ");
        email.setFont(new Font("Raieway", Font.BOLD, 22));
        email.setBounds(100,340,200,30);
        email.setForeground(Color.BLACK);
        image1.add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raieway", Font.BOLD, 14));
        emailTextField.setBounds(300, 340, 400, 30);
        image1.add(emailTextField);



        JLabel address = new JLabel("Address: ");
        address.setFont(new Font("Raieway", Font.BOLD, 22));
        address.setBounds(100,390,200,30);
        address.setForeground(Color.BLACK);
        image1.add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raieway", Font.BOLD, 14));
        addressTextField.setBounds(300, 390, 400, 30);
        image1.add(addressTextField);



        JLabel city = new JLabel("City: ");
        city.setFont(new Font("Raieway", Font.BOLD, 22));
        city.setBounds(100,440,200,30);
        city.setForeground(Color.BLACK);
        image1.add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raieway", Font.BOLD, 14));
        cityTextField.setBounds(300, 440, 400, 30);
        image1.add(cityTextField);



        JLabel state = new JLabel("State: ");
        state.setFont(new Font("Raieway", Font.BOLD, 22));
        state.setBounds(100,490,200,30);
        state.setForeground(Color.BLACK);
        image1.add(state);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Raieway", Font.BOLD, 14));
        stateTextField.setBounds(300, 490, 400, 30);
        image1.add(stateTextField);



        JLabel pincode = new JLabel("Pincode: ");
        pincode.setFont(new Font("Raieway", Font.BOLD, 22));
        pincode.setBounds(100,540,200,30);
        pincode.setForeground(Color.BLACK);
        image1.add(pincode);

        pincodeTextField = new JTextField();
        pincodeTextField.setFont(new Font("Raieway", Font.BOLD, 14));
        pincodeTextField.setBounds(300, 540, 400, 30);
        image1.add(pincodeTextField);



        JLabel username = new JLabel("Username: ");
        username.setFont(new Font("Raieway", Font.BOLD, 22));
        username.setBounds(100,590,200,30);
        username.setForeground(Color.BLACK);
        image1.add(username);

        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Raieway", Font.BOLD, 14));
        usernameTextField.setBounds(300, 590, 400, 30);
        image1.add(usernameTextField);



        JLabel password = new JLabel("Password: ");
        password.setFont(new Font("Raieway", Font.BOLD, 22));
        password.setBounds(100,640,200,30);
        password.setForeground(Color.BLACK);
        image1.add(password);

        passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font("Raieway", Font.BOLD, 14));
        passwordTextField.setBounds(300, 640, 400, 30);
        image1.add(passwordTextField);



        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raieway", Font.BOLD, 14));
        next.setBounds(620, 690, 80,30);
        next.addActionListener(this);
        image1.add(next);



        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String formno ="" + random;
        String fname = fnameTextField.getText();
        String sname = snameTextField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = "";
        if(male.isSelected())
        {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }

        String email = emailTextField.getText();

        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pincode = pincodeTextField.getText();

        String username = usernameTextField.getText();

        String password = passwordTextField.getText();


        try {
            if (fname.equals("") || sname.equals("") || dob.equals("") || gender.equals("") || email.equals("") || address.equals("") || city.equals("") || pincode.equals("") || state.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Fill all fields to proceed");
            } else
            {
                Database c = new Database();
                String query1 = "INSERT INTO signup VALUES ('" + formno + "', '" + fname + "', '" + sname + "', '" + dob + "', '" + gender + "', '" + email + "', '" + address + "', '" + city + "', '" + pincode + "', '" + state + "')";
                c.s.executeUpdate(query1);

                String query2 = "INSERT INTO login VALUES('"+formno+"', '"+username+"', '"+password+"')";
                c.s.executeUpdate(query2);
            }

        } catch (Exception e)
        {
            System.out.println(e);
        }

        if(ae.getSource() == next)
        {
            setVisible(false);
            JOptionPane.showMessageDialog(null, "Welcome User. You can now LogIn");
            new Login(formno).setVisible(true);
        }
    }
    public static void main(String[] args) {
        new SignUp();
    }


}
