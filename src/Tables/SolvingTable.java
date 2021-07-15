package Tables;

import BaseClasses.Word;
import javax.swing.*;
import java.util.List;

public class SolvinmgTable extends Table {

    // метод импорта из файла
    public boolean XMLImport(String path) {
        return true;
    }

    // список слов
    private List<Word> words;

    // таблица отображения
    private JTable SolvingJTable;

}
