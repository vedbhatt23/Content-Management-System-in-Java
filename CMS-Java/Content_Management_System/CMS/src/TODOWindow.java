import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TODOWindow extends JFrame implements ActionListener {
    String formno;
    JButton addImage, deleteImage, editImage, showAll, backBtn;
    JLabel label;
    JPanel panel;
    JLabel image1;

    TODOWindow(String formno) {
        this.formno = formno;
        setLayout(null);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("bg1.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        image1 = new JLabel(i6);
        image1.setBounds(0, 0, 500, 500);
        add(image1);

        label = new JLabel("TO-DO List");
        label.setFont(new Font("Osward", Font.BOLD, 25));
        label.setBounds(200, 50, 250, 50);
        image1.add(label);
//
        addImage = new JButton("Add Task");
        addImage.setFont(new Font("Osward", Font.BOLD, 14));
        addImage.setBounds(150, 150, 200, 50);
        addImage.addActionListener(this);
        image1.add(addImage);
//
        deleteImage = new JButton("Delete Task");
        deleteImage.setFont(new Font("Osward", Font.BOLD, 14));
        deleteImage.setBounds(150, 225, 200, 50);
        deleteImage.addActionListener(this);
        image1.add(deleteImage);

        editImage = new JButton("Edit Task");
        editImage.setFont(new Font("Osward", Font.BOLD, 14));
        editImage.setBounds(150, 300, 200, 50);
        editImage.addActionListener(this);
        image1.add(editImage);

        showAll = new JButton("Show Tasks");
        showAll.setFont(new Font("Osward", Font.BOLD, 14));
        showAll.setBounds(150, 375, 200, 50);
        showAll.addActionListener(this);
        image1.add(showAll);

        backBtn = new JButton("Back");
        backBtn.setBounds(10, 10, 80, 30);
        backBtn.addActionListener(this);
        image1.add(backBtn);

        setSize(500, 500);
        setVisible(true);
        setLocation(500, 200);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addImage) {
            AddTODO addTODO = new AddTODO(formno);
        } else if (e.getSource() == editImage) {
            EditTODO editImageWindow = new EditTODO(formno);
        } else if (e.getSource() == showAll) {
            ShowTODO showAllWindow = new ShowTODO(formno);
        } else if (e.getSource() == deleteImage) {
            DeleteTODO showAllWindow = new DeleteTODO(formno);
        } else if (e.getSource() == backBtn) {
            Content contentWindow = new Content(formno);
            dispose();
        }
    }
    public static void main(String[] args) {
        new TODOWindow("");
    }
}
