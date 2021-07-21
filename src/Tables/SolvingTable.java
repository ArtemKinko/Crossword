package Tables;

import BaseClasses.Word;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.List;

public class SolvingTable extends Table {
    public SolvingTable(int tempSize) {
        setTableView(new JTable(tempSize, tempSize));
        setSize(tempSize);

        // выделение интервалом
        getTableView().setRowSelectionAllowed(true);
        TableColumnModel columnModel = getTableView().getColumnModel();
        columnModel.setColumnSelectionAllowed(true);
        columnModel.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    }

    // метод импорта из файла
    public void XMLImport(String path) {

    }

    // список слов
    private List<Word> words;
    private boolean isImported;
    public boolean isImported() {
        return isImported;
    }
}
