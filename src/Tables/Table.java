package Tables;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class Table {
    public Table() {

    }
    private int size;  // размер таблицы для отображения

    public int getSize() {
        return size;
    }

    // сеттер размера таблицы, приведение ее ячеек к квадратам
    public void setSize(int size) {

        TableView.setRowHeight(500 / size);
        TableView.setPreferredSize(new Dimension(500, 500));
        TableView.setFont(new Font("Serif", Font.BOLD, 500 / size / 2));
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

