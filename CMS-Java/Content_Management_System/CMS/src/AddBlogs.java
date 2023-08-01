import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddBlogs extends JFrame implements ActionListener {
    String formno;
    private final JLabel titleLabel, contentLabel;
    private final JTextField titleField;
    private final JTextArea contentArea;
    private final JButton submit, cancel;

    public AddBlogs(String formno) {

        this.formno = formno;
        setTitle("Add Blog");
        setLayout(null);

        // Set the background image
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("bg1.jpeg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(backgroundImage);
        JLabel background = new JLabel(imageIcon);
        background.setBounds(0, 0, 500, 500);
        add(background);

        // Create the title label and text field
        titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 50, 100, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.BLACK);
        background.add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(150, 50, 300, 30);
        background.add(titleField);

        // Create the content label and text area
        contentLabel = new JLabel("Content:");
        contentLabel.setBounds(50, 100, 100, 30);
        contentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contentLabel.setForeground(Color.BLACK);
        background.add(contentLabel);

        contentArea = new JTextArea();
        contentArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setBounds(50, 140, 400, 250);
        background.add(scrollPane);

        // Create the submit and cancel buttons
        submit = new JButton("Submit");
        submit.setBounds(100, 410, 100, 30);
        submit.addActionListener(this);
        background.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(300, 410, 100, 30);
        cancel.addActionListener(this);
        background.add(cancel);

        setSize(500, 500);
        setVisible(true);
        setLocation(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String title = titleField.getText();
            String content = contentArea.getText();

            // Update the blog in the database
            try {
                Connection conn = new Database().c;
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO blogs (id, title, content) VALUES (?, ?, ?)");
                stmt.setString(1, formno);
                stmt.setString(2, title);
                stmt.setString(3, content);

                int rowsAffected = stmt.executeUpdate();
                String output = rowsAffected + " row(s) affected.";
                JOptionPane.showMessageDialog(null, output);
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // Close the window
            dispose();
        } else if (e.getSource() == cancel) {
            dispose();
        }
    }


    public static void main(String[] args) {
        new AddBlogs("");
    }
}
