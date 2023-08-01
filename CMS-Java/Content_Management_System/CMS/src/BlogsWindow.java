import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlogsWindow extends JFrame implements ActionListener {
    String formno;
    JButton addBtn, editBtn, deleteBtn, showAll, backBtn;
    BlogsWindow(String formno)
    {
        this.formno=formno;

        setLayout(null);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("bg1.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(0, 0, 500, 500);
        add(image1);

        JLabel text = new JLabel("Blogs");
        text.setFont(new Font("Osward", Font.BOLD, 30));
        text.setBounds(200, 50, 250, 50);
        image1.add(text);

        // Add button
        addBtn = new JButton("Add Blogs");
        addBtn.setBounds(150, 150, 200, 50);
        addBtn.addActionListener(this);
        image1.add(addBtn);

        // Delete button
        deleteBtn = new JButton("Delete Blogs");
        deleteBtn.setBounds(150, 225, 200, 50);
        deleteBtn.addActionListener(this);
        image1.add(deleteBtn);

        // Edit button
        editBtn = new JButton("Edit Blogs");
        editBtn.setBounds(150, 300, 200, 50);
        editBtn.addActionListener(this);
        image1.add(editBtn);

        showAll = new JButton("Show All");
        showAll.setFont(new Font("Osward", Font.BOLD, 14));
        showAll.setBounds(150, 375, 200, 50);
        showAll.addActionListener(this);
        image1.add(showAll);

        // Back button
        backBtn = new JButton("Back");
        backBtn.setBounds(10, 10, 80, 30);
        backBtn.addActionListener(this);
        image1.add(backBtn);

        // Window properties
        setSize(500, 500);
        setVisible(true);
        setLocation(500, 200);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            AddBlogs addBlogsWindow = new AddBlogs(formno);
        } else if (e.getSource() == editBtn) {
            EditBlogs editBlogs = new EditBlogs(formno);
        } else if (e.getSource() == deleteBtn) {
            DeleteBlogs deleteBlogs = new DeleteBlogs(formno);
        } else if (e.getSource() == showAll) {
            ShowAllBlogs showAllBlogs = new ShowAllBlogs(formno);
        } else if (e.getSource() == backBtn) {
            Content contentWindow = new Content(formno);
            dispose();
        }
    }

    public static void main(String[] args) {
        new BlogsWindow("");
    }
}
