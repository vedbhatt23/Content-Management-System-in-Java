import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteImage extends JFrame implements ActionListener {
    String formno;
    JLabel nameLabel, imageLabel, image1;
    JTextField nameField;
    JButton deleteButton;

    Database d = new Database();

    public DeleteImage(String formno) {
        this.formno = formno;
        setLayout(null);

        ImageIcon bgImage = new ImageIcon("bg1.jpeg");
        Image bgImg = bgImage.getImage().getScaledInstance(350, 150, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(bgImg);
        image1 = new JLabel(imageIcon);
        image1.setBounds(0, 0, 350, 150);
        add(image1);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        nameLabel.setForeground(Color.BLACK);
        image1.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 200, 25);
        image1.add(nameField);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(100, 50, 100, 25);
        deleteButton.addActionListener(this);
        image1.add(deleteButton);

        setSize(350, 150);
        setVisible(true);
        setLocation(600, 300);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            if (nameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter name.");
            } else {
                try {
                    String name = nameField.getText();

                    Connection conn = d.c;
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM images WHERE id = ? AND name = ?");
                    ps.setString(1, formno);
                    ps.setString(2, name);
                    int result = ps.executeUpdate();

                    if (result == 0) {
                        JOptionPane.showMessageDialog(null, "Image not found.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Image deleted successfully.");
                    }
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new DeleteImage("");
    }
}
