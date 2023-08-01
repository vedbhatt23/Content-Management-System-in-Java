import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class AddTODO extends JFrame implements ActionListener {
    JLabel taskLabel, dueDateLabel, numberLabel;
    JTextField taskField, numberField;
    JDateChooser dateChooser;
    JButton saveButton;
    String formno;
    Database d = new Database();

    public AddTODO(String formno) {
        this.formno = formno;
        setLayout(null);

        ImageIcon bgImage = new ImageIcon("bg1.jpeg");
        Image bgImg = bgImage.getImage().getScaledInstance(350, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(bgImg);
        JLabel image1 = new JLabel(imageIcon);
        image1.setBounds(0, 0, 350, 200);
        add(image1);

        taskLabel = new JLabel("Task:");
        taskLabel.setBounds(20, 20, 80, 25);
        taskLabel.setForeground(Color.BLACK);
        image1.add(taskLabel);

        taskField = new JTextField();
        taskField.setBounds(100, 20, 200, 25);
        image1.add(taskField);

        dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setBounds(20, 50, 80, 25);
        dueDateLabel.setForeground(Color.BLACK);
        image1.add(dueDateLabel);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(100, 50, 200, 25);
        image1.add(dateChooser);

        numberLabel = new JLabel("Number:");
        numberLabel.setBounds(20, 80, 80, 25);
        numberLabel.setForeground(Color.BLACK);
        image1.add(numberLabel);

        numberField = new JTextField();
        numberField.setBounds(100, 80, 200, 25);
        image1.add(numberField);

        saveButton = new JButton("Save");
        saveButton.setBounds(100, 120, 100, 25);
        saveButton.addActionListener(this);
        image1.add(saveButton);

        setSize(350, 200);
        setVisible(true);
        setLocation(600, 300);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            if (taskField.getText().isEmpty() || dateChooser.getDate() == null || numberField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter task, number and due date.");
            } else {
                try {
                    String task = taskField.getText();
                    int number = Integer.parseInt(numberField.getText());
                    Date dueDate = dateChooser.getDate();

                    Connection conn = d.c;
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO todo (id, number, task, due_date) VALUES (?, ?, ?, ?)");
                    ps.setString(1, formno);
                    ps.setInt(2, number);
                    ps.setString(3, task);
                    ps.setDate(4, new java.sql.Date(dueDate.getTime()));
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Task added successfully.");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new AddTODO("");
    }
}
