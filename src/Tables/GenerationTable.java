package Tables;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GenerationTable extends Table {
    public GenerationTable(int tempSize) {
        setTableView(new JTable(tempSize, tempSize));
        setSize(tempSize);

        // выделение интервалом
        getTableView().setRowSelectionAllowed(true);
        TableColumnModel columnModel = getTableView().getColumnModel();
        columnModel.setColumnSelectionAllowed(true);
        columnModel.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // добавляем слушателя событий мыши
        getTableView().addMouseListener(new GenerationListener());
    }


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
                {
                    model.setValueAt("+", i, j);
                }
            }
        }
    }
}
