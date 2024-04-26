package Code;


import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
    ArrayList<Object[]> data = new ArrayList<Object[]>();
    ResultSetMetaData rsmd;
    EtudiantDAO dao;
    String[] columnNames;

    MyTableModel(ResultSet rs, EtudiantDAO dao) throws SQLException {
        this.dao = dao;
        rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        columnNames = new String[columnCount];

        // Set column names
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = rsmd.getColumnName(i + 1);
        }
        while (rs.next()) {
            Object[] ligne = new Object[rsmd.getColumnCount()];
            for (int i = 0; i < ligne.length; i++) {
                ligne[i] = rs.getObject(i + 1);
            }
            data.add(ligne);
        }
    }
    @Override
    public int getRowCount() {
        return data.size();
    }
    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            return 0;
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}



