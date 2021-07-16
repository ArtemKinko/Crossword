package Tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class Table {
    public Table() {
        size = 10;
    }
    private int size;  // размер таблицы для отображения

    public int getSize() {
        return size;
    }

    // сеттер размера таблицы, приведение ее ячеек к квадратам
    public void setSize(int size) {

        DefaultTableModel model = (DefaultTableModel) getTableView().getModel();
        int dx = this.size - size;
        if (dx < 0) {
            dx = -dx;
            // добавляем строки и столбцы
            for (int i = 0; i < dx; i++) {
                model.addRow(new Object[]{});
                model.addColumn(new Object[]{});
            }
        }
        else if (dx > 0) {
            // удаляем строки и столбцы
            TableColumnModel cmodel = getTableView().getColumnModel();
            for (int i = (this.size - 1); i > (this.size - dx - 1); i--) {
                System.out.println("Удаляем с " + Integer.toString((size - dx - 1)) + " по " + Integer.toString(size - 1));
                model.removeRow(i);
            }
            model.setColumnCount(size);
        }

        System.out.println(dx);

        TableView.setRowHeight(500 / size);
        TableView.setPreferredSize(new Dimension(500, 500));
        TableView.setFont(new Font("Serif", Font.BOLD, 500 / size / 2));
        //TableColumn column = new TableColumn(1);
        //TableView.addColumn(column);
        this.size = size;
    }

    public JTable getTableView() {
        return TableView;
    }

    public void setTableView(JTable tableView) {
        TableView = tableView;
    }

    private JTable TableView;  // отображаемая таблица
}

