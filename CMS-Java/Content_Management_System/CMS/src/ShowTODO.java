import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowTODO extends JFrame {
    private String formno;

    public ShowTODO(String formno) {
        this.formno = formno;
        setLayout(new BorderLayout());

        // Create table header
        String[] columnNames = {"ID", "Task", "Due Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        // Populate table with tasks
        try {
            Connection conn = new Database().c;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM todo WHERE id = ?");
            ps.setString(1, formno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] rowData = new String[3];
                rowData[0] = rs.getString("number");
                rowData[1] = rs.getString("task");
                rowData[2] = rs.getString("due_date");
                model.addRow(rowData);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new ShowTODO("");
    }
}
