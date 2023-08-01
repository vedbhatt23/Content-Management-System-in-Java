import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShowAllBlogs extends JFrame {
    private final JLabel contentLabel;
    private final JPanel blogsPanel;

    public ShowAllBlogs(String formno) {
        setTitle("All Blogs");
        setLayout(null);

        // Set the background image
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("bg1.jpeg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(backgroundImage);
        JLabel background = new JLabel(imageIcon);
        background.setBounds(0, 0, 500, 500);
        add(background);

        // Create the content label and text area
        contentLabel = new JLabel("Blogs");
        contentLabel.setBounds(215, 40, 100, 40);
        contentLabel.setFont(new Font("Raieway", Font.BOLD, 20));
        contentLabel.setForeground(Color.BLACK);
        background.add(contentLabel);

        // Create the blogs panel to hold all the blogs
        blogsPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(blogsPanel);
        scrollPane.setBounds(50, 100, 400, 350);
        background.add(scrollPane);

        blogsPanel.setLayout(new BoxLayout(blogsPanel, BoxLayout.Y_AXIS));

        // Retrieve all the blogs of the user from the database
        try {
            Connection conn = new Database().c;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM blogs WHERE id = ?");
            stmt.setString(1, formno);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                JLabel titleLabel = new JLabel(title);
                JTextArea contentTextArea = new JTextArea(content);
                contentTextArea.setEditable(false);
                contentTextArea.setLineWrap(true);
                contentTextArea.setWrapStyleWord(true);
                blogsPanel.add(titleLabel);
                blogsPanel.add(contentTextArea);
                blogsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setSize(500, 500);
        setVisible(true);
        setLocation(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ShowAllBlogs("");
    }
}
