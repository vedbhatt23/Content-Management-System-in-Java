import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditImage extends JFrame implements ActionListener {
    public String formno;
    private JLabel lblOldName, lblNewName, imageLabel;
    private JTextField txtOldName, txtNewName;
    private JButton btnUpdate;

    private Database d = new Database();

    public EditImage(String formno) {
        this.formno = formno;
        setLayout(null);

        // Add background image
        ImageIcon bgImageIcon = new ImageIcon(ClassLoader.getSystemResource("bg1.jpeg"));
        Image bgImage = bgImageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon scaledBgImageIcon = new ImageIcon(bgImage);
        JLabel background = new JLabel(scaledBgImageIcon);
        background.setBounds(0, 0, 500, 500);
        add(background);

        // Initialize UI components
        lblOldName = new JLabel("Old Name:");
        lblOldName.setForeground(Color.BLACK);
        lblOldName.setBounds(50, 100, 100, 20);
        txtOldName = new JTextField(20);
        txtOldName.setBounds(150, 100, 200, 20);
        lblNewName = new JLabel("New Name:");
        lblNewName.setForeground(Color.BLACK);
        lblNewName.setBounds(50, 150, 100, 20);
        txtNewName = new JTextField(20);
        txtNewName.setBounds(150, 150, 200, 20);
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(200, 200, 100, 30);

        // Register action listener for the button
        btnUpdate.addActionListener(this);

        // Add components to the frame
        background.add(lblOldName);
        background.add(txtOldName);
        background.add(lblNewName);
        background.add(txtNewName);
        background.add(btnUpdate);

        setSize(500, 500);
        setVisible(true);
        setLocation(550, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update image name in the database
        try {
            Connection conn = d.c;
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE images SET name = ? WHERE name = ? AND id = ?");
            statement.setString(1, txtNewName.getText());
            statement.setString(2, txtOldName.getText());
            statement.setString(3, formno);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 1) {
                JOptionPane.showMessageDialog(this, "Image name updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Image not found with the old name!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to update image name: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new EditImage("");
    }
}
