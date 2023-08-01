import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteTODO extends JFrame {
    private JTextField taskNumField;
    private String formno;

    public DeleteTODO(String formno) {
        this.formno = formno;

        JLabel taskNumLabel = new JLabel("Task number:");
        taskNumField = new JTextField(10);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String taskNum = taskNumField.getText();

                if (taskNum.equals("")) {
                    JOptionPane.showMessageDialog(DeleteTODO.this, "Please enter a task number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Connection conn = new Database().c;
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM todo WHERE id = ? AND number = ?");
                    ps.setString(1, formno);
                    ps.setString(2, taskNum);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 0) {
                        JOptionPane.showMessageDialog(DeleteTODO.this, "Task not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(DeleteTODO.this, "Task deleted successfully.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(DeleteTODO.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(taskNumLabel);
        panel.add(taskNumField);
        panel.add(deleteButton);
        add(panel);

        setTitle("Delete Task");
        setSize(300, 100);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new DeleteTODO("");
    }
}
