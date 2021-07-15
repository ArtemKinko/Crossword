package Tables;

import BaseClasses.Word;

import javax.swing.*;
import java.util.List;

public class SolvingTable extends Table {
    public SolvingTable() {

    }

    // метод импорта из файла
    public boolean XMLImport(String path) {
        return true;
    }

    // список слов
    private List<Word> words;

}
