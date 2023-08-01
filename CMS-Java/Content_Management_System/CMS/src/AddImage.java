import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddImage extends JFrame implements ActionListener {
    String formno;
    JLabel nameLabel, imageLabel, image1;
    JTextField nameField;
    JButton chooseButton, saveButton;

    Database d = new Database();

    public AddImage(String formno) {
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

        imageLabel = new JLabel("Image:");
        imageLabel.setBounds(20, 50, 80, 25);
        imageLabel.setForeground(Color.BLACK);
        image1.add(imageLabel);

        chooseButton = new JButton("Choose");
        chooseButton.setBounds(100, 50, 100, 25);
        chooseButton.addActionListener(this);
        image1.add(chooseButton);

        saveButton = new JButton("Save");
        saveButton.setBounds(100, 80, 100, 25);
        saveButton.addActionListener(this);
        image1.add(saveButton);

        setSize(350, 150);
        setVisible(true);
        setLocation(600, 300);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chooseButton) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imageLabel.setText(selectedFile.getAbsolutePath());
            }
        } else if (e.getSource() == saveButton) {
            if (nameField.getText().isEmpty() || imageLabel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter name and image.");
            } else {
                try {
                    String name = nameField.getText();
                    File file = new File(imageLabel.getText());
                    FileInputStream fis = new FileInputStream(file);

                    Connection conn = d.c;
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO images (id, name, image) VALUES (?, ?, ?)");
                    ps.setString(1, formno);
                    ps.setString(2, name);
                    ps.setBinaryStream(3, fis, (int) file.length());
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Image added successfully.");
                    dispose();
                } catch (SQLException | IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new AddImage("");
    }
}
