package Tables;

import BaseClasses.Word;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// класс таблицы для решения кроссвордов
public class SolvingTable extends Table {
    public SolvingTable(int tempSize) {
        words = new ArrayList<>();
        setTableView(new JTable(tempSize, tempSize));
        setSize(tempSize);

        // выделение интервалом
        getTableView().setRowSelectionAllowed(true);
        TableColumnModel columnModel = getTableView().getColumnModel();
        columnModel.setColumnSelectionAllowed(true);
        columnModel.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    }

    // список слов
    private final List<Word> words;
    public List<Word> getWords() {
        return words;
    }

    private boolean isImported;
    public boolean isImported() {
        return isImported;
    }

    // определения слов
    private String definitions;
    public String getDefinitions() {
        return definitions;
    }

    // возвращает число верно написанных слов
    public int getRightWords() {
        int counter = 0;
        TableModel model = getTableView().getModel();

        for (Word word: words) {
            String answer = "";

            if (word.isHorizontal())
                for (int y = word.getY(); y < word.getY() + word.getLength(); y++) {
                    if (model.getValueAt(word.getX(), y).toString().length() == 0)
                        model.setValueAt("_", word.getX(), y);
                    answer += model.getValueAt(word.getX(), y).toString().charAt(0);
                }
            else
                for (int x = word.getX(); x < word.getX() + word.getLength(); x++) {
                    if (model.getValueAt(x, word.getY()).toString().length() == 0)
                        model.setValueAt("_", x, word.getY());
                    answer += model.getValueAt(x, word.getY()).toString().charAt(0);
                }
            answer = answer.toLowerCase(Locale.ROOT);
            System.out.println(answer);

            if (answer.equals(word.getWord()))
                counter++;
        }
        return counter;
    }
}
