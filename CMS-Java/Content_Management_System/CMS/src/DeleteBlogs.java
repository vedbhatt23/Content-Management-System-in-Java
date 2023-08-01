import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteBlogs extends JFrame implements ActionListener {
    private final JLabel titleLabel;
    private final JTextField titleField;
    private final JButton delete, cancel;
    String formno;

    public DeleteBlogs(String formno) {
        this.formno = formno;
        setTitle("Delete Blog");
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
        titleLabel.setForeground(Color.BLACK);
        background.add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(150, 50, 300, 30);
        background.add(titleField);

        // Create the delete and cancel buttons
        delete = new JButton("Delete");
        delete.setBounds(100, 410, 100, 30);
        delete.addActionListener(this);
        background.add(delete);

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
        if (e.getSource() == delete) {
            String title = titleField.getText();

            // Delete the blog from the database
            try {
                Connection conn = new Database().c;

                PreparedStatement stmt = conn.prepareStatement("DELETE FROM blogs WHERE id = ? AND title = ?");
                stmt.setString(1, formno);
                stmt.setString(2, title);

                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected.");
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
        new DeleteBlogs("");
    }
}
