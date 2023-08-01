import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ShowAllWindow extends JFrame {
    private JPanel panel;
    private JScrollPane scrollPane;

    public ShowAllWindow(String formno) {
        setTitle("All Images");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set the background image
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("bg1.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(800, 480, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(0, 0, 800, 480);
        add(image1);

        // Create the panel
        panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setPreferredSize(new Dimension(800, 1000));

        // Create the scroll pane and add the panel to it
        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0, 0, 800, 480);
        image1.add(scrollPane);

        try (Connection conn = new Database().c;
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM images WHERE id = ?");
        ) {
            pstmt.setString(1, formno);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                byte[] imageData = rs.getBytes("image");
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image image = imageIcon.getImage();
                Image scaledImage = image.getScaledInstance(300, -1, Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                JLabel label = new JLabel(rs.getString("name"), scaledImageIcon, JLabel.CENTER);
                panel.add(label);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        new ShowAllWindow("");
    }
}
