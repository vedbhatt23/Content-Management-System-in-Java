import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowBlogs extends JFrame {
    String formno;

    ShowBlogs(String formno) {
        this.formno = formno;
        setTitle("Show All Blogs");
        setLayout(new BorderLayout());

        // Retrieve all user's blogs (dummy data)
        ArrayList<String> blogs = getAllBlogs(formno);

        // Create a JTextArea to display the blogs
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        for (String blog : blogs) {
            textArea.append(blog + "\n");
        }

        // Add the JTextArea to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Window properties
        setSize(400, 500);
        setVisible(true);
        setLocation(570, 200);
    }

    // Dummy method to retrieve all user's blogs
    private ArrayList<String> getAllBlogs(String formno) {
        // Replace with your actual implementation to retrieve user's blogs
        // Here's an example of dummy data
        ArrayList<String> blogs = new ArrayList<>();
        blogs.add("Blog 1");
        blogs.add("Blog 2");
        blogs.add("Blog 3");
        blogs.add("Blog 4");
        return blogs;
    }

    public static void main(String[] args) {
        new ShowBlogs("");
    }
}
