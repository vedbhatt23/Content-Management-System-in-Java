import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditBlogs extends JFrame implements ActionListener {
    JLabel titleLabel, contentLabel;
    JTextField titleTextField;
    JTextArea contentTextArea;
    JButton updateButton, backButton;
    String formno;

    EditBlogs(String formno) {
        this.formno = formno;
        setLayout(null);
        setTitle("Edit Blog");

        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("bg1.jpeg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(backgroundImage);
        JLabel background = new JLabel(imageIcon);
        background.setBounds(0, 0, 500, 500);
        add(background);

        // Title label
        titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 50, 100, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.BLACK);
        background.add(titleLabel);

        // Title text field
        titleTextField = new JTextField();
        titleTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        titleTextField.setBounds(150, 50, 300, 30);
        background.add(titleTextField);

        // Content label
        contentLabel = new JLabel("Content:");
        contentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contentLabel.setBounds(50, 100, 100, 30);
        background.add(contentLabel);

        // Content text area
        contentTextArea = new JTextArea();
        contentTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(contentTextArea);
        scrollPane.setBounds(50, 140, 400, 250);
        background.add(scrollPane);

        // Update button
        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBounds(100, 410, 100, 30);
        updateButton.addActionListener(this);
        background.add(updateButton);

        // Back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBounds(300, 410, 100, 30);
        backButton.addActionListener(this);
        background.add(backButton);

        setSize(500, 500);
        setVisible(true);
        setLocation(500, 200);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            // Get the title and content
            String title = titleTextField.getText();
            String content = contentTextArea.getText();

            // Update the blog post in the database
            try {
                // Establish a connection to the database
                Connection conn = new Database().c;

                // Create a PreparedStatement to update the blog post
                String sql = "UPDATE blogs SET content = ? WHERE title = ? AND id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, content);
                statement.setString(2, title);
                statement.setString(3, formno);

                // Execute the update statement
                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Blog post updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Blog post not found.");
                }

                // Close the connection and the statement
                statement.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new EditBlogs("");
    }
}
