package Tables;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GenerationTable extends Table {
    public GenerationTable(int tempSize) {

        // создаем таблицу с запрещенным редактированием
        setTableView(new JTable(tempSize, tempSize) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        });
        setSize(tempSize);

        // выделение интервалом
        getTableView().setRowSelectionAllowed(true);
        TableColumnModel columnModel = getTableView().getColumnModel();
        columnModel.setColumnSelectionAllowed(true);
        columnModel.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // добавляем слушателя событий мыши
        getTableView().addMouseListener(new GenerationListener());
    }


    // адаптер для выделения мыши (выполняется при отпускании клавиши)
    private class GenerationListener extends MouseAdapter {
        @Override
        // при отпускании клавиши
        public void mouseReleased(MouseEvent e) {
            System.out.println("КЛИК");
            int[] rows = getTableView().getSelectedRows();
            int[] columns = getTableView().getSelectedColumns();

            TableModel model = getTableView().getModel();

            for (int i: rows) {
                for (int j: columns)
                    model.setValueAt(model.getValueAt(i, j) == "█" ? "" : "█", i, j);
            }
        }
    }
}
