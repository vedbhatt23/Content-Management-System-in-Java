import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditTODO extends JFrame implements ActionListener {
    String formno;
    JLabel numberLabel, titleLabel, dateLabel;
    JTextField numberField, titleField;
    JButton updateButton;
    JDateChooser dateChooser;
    Database d = new Database();

    public EditTODO(String formno) {
        this.formno = formno;
        setLayout(null);

        numberLabel = new JLabel("Number:");
        numberLabel.setBounds(20, 20, 80, 25);
        numberLabel.setForeground(Color.BLACK);
        add(numberLabel);

        numberField = new JTextField();
        numberField.setBounds(100, 20, 200, 25);
        add(numberField);

        titleLabel = new JLabel("Task:");
        titleLabel.setBounds(20, 50, 80, 25);
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(100, 50, 200, 25);
        add(titleField);

        dateLabel = new JLabel("Due Date:");
        dateLabel.setBounds(20, 80, 80, 25);
        dateLabel.setForeground(Color.BLACK);
        add(dateLabel);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(100, 80, 200, 25);
        add(dateChooser);

        updateButton = new JButton("Update");
        updateButton.setBounds(100, 120, 100, 25);
        updateButton.addActionListener(this);
        add(updateButton);

        try {
            Connection conn = d.c;
            PreparedStatement ps = conn.prepareStatement("SELECT number, task, due_date FROM todo WHERE number = ? AND id = ?");
            ps.setString(1, numberField.getText());
            ps.setString(2, formno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                numberField.setText(rs.getString("number"));
                titleField.setText(rs.getString("task"));
                dateChooser.setDate(rs.getDate("due_date"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        setSize(350, 200);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            if (numberField.getText().isEmpty() || titleField.getText().isEmpty() || dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Please enter number, task, and due date.");
            } else {
                try {
                    int number = Integer.parseInt(numberField.getText());
                    String task = titleField.getText();
                    java.sql.Date date = new java.sql.Date(dateChooser.getDate().getTime());

                    Connection conn = d.c;
                    PreparedStatement ps = conn.prepareStatement("UPDATE todo SET task = ?, due_date = ? WHERE number = ? and id = ?");
                    ps.setString(1, task);
                    ps.setDate(2, date);
                    ps.setInt(3, number);
                    ps.setString(4, formno);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "TODO updated successfully.");
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
        new EditTODO("");
    }
}
