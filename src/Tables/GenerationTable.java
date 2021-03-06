package Tables;

import Algorithms.GenerationWord;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// класс таблицы для генерации
public class GenerationTable extends Table {
    public GenerationTable(int tempSize) {

        allWords = new GenerationWord();
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

            // для каждой выделенной ячейки ставим символ
            for (int i: rows) {
                for (int j: columns)
                    model.setValueAt(model.getValueAt(i, j) == "█" ? "" : "█", i, j);
            }
        }
    }

    // метод добавления одного слова из таблицы, входные данные - начало слова и направление
    private GenerationWord addNewWord(int x, int y, boolean isHorizontal) {
        numWords++;
        GenerationWord word = new GenerationWord();
        TableModel model = getTableView().getModel();
        word.setX(x);
        word.setY(y);

        // поиск длины слова
        int len = 0;
        if (isHorizontal) {
            word.setHorizontal(true);
            int k = y;
            while (k < getSize()) {
                if (model.getValueAt(x, k) == "█") {

                    // поиск общих точек для горизонтального слова
                    if (x != 0) {
                        if (model.getValueAt(x - 1, k) == "█") {
                            // если найдено слово выше горизонтали
                            int newX = x;
                            int pos = 0;
                            while (newX > 0) {
                                if (model.getValueAt(newX, k) == "█") {
                                    pos++;
                                    newX--;
                                } else break;
                            }
                            word.addPair(addNewWord(newX + 1, k, !isHorizontal), len + 1, pos);
                        }
                        else if (x != (getSize()-1)) {
                            // если ниже
                            if (model.getValueAt(x + 1, k) == "█" && model.getValueAt(x - 1, k) != "OK") {
                                if (k == y)
                                    model.setValueAt("OK", x, k);
                                word.addPair(addNewWord(x, k, !isHorizontal), len + 1, 1);
                            }

                            else model.setValueAt("OK", x, k);
                        }
                        else model.setValueAt("OK", x, k);
                    }
                    else if (x != (getSize() - 1)) {
                        if (model.getValueAt(x + 1, k) == "█" && model.getValueAt(x - 1, k) != "OK") {
                            if (k == y)
                                model.setValueAt("OK", x, k);
                            word.addPair(addNewWord(x, k, !isHorizontal), len + 1, 1);
                        }
                        else model.setValueAt("OK", x, k);
                    }
                    else model.setValueAt("OK", x, k);
                    len++;
                    k++;
                }
                else break;
            }
        }
        else {
            // для вертикальных слов
            word.setHorizontal(false);
            int k = x;
            while (k < getSize()) {
                if (model.getValueAt(k, y) == "█" || model.getValueAt(k, y) == "OK") {
                    // поиск общих точек
                    if (y != 0) {
                        if (model.getValueAt(k, y - 1) == "█") {
                            // если найдено слово левее вертикали
                            int newY = y;
                            int pos = 0;
                            while (newY > 0) {
                                if (model.getValueAt(k, newY) == "█") {
                                    pos++;
                                    newY--;
                                } else break;
                            }
                            word.addPair(addNewWord(k, newY + 1, !isHorizontal), len + 1, pos);
                        }
                        else if (y != (getSize()-1)) {
                            // если есть правее
                            if (model.getValueAt(k, y + 1) == "█" && model.getValueAt(k, y - 1) != "OK")
                                word.addPair(addNewWord(k, y, !isHorizontal), len + 1, 1);
                            else model.setValueAt("OK", k, y);
                        }
                        else model.setValueAt("OK", k, y);
                    }
                    else if (y != (getSize() - 1)) {
                        if (model.getValueAt(k, y + 1) == "█" && model.getValueAt(k, y - 1) != "OK")
                            word.addPair(addNewWord(k, y, !isHorizontal), len + 1, 1);
                        else model.setValueAt("OK", k, y);
                    }
                    else model.setValueAt("OK", k, y);
                    len++;
                    k++;
                }
                else break;
            }
        }
        word.setLength(len);
        return word;
    }

    // метод для получения главного слова таблицы для генерации кроссворда
    public void genMainWord() {
        TableModel model = getTableView().getModel();
        numWords = 0;
        search:
        for (int i = 0; i < getSize(); i++)
            for (int j = 0; j < getSize(); j++) {
                if (model.getValueAt(i, j) == "█") {
                    // если первое слово вертикально
                    if (j == (getSize() - 1)) {
                        allWords = addNewWord(i, j, false);
                    }
                    else if (j < getSize() - 1) {
                        if (model.getValueAt(i, j + 1) != "█") {
                            allWords = addNewWord(i, j, false);
                        }
                        else {
                            // если горизонтально
                            allWords = addNewWord(i, j, true);
                        }
                    }
                    else {
                        allWords = addNewWord(i, j, true);
                    }
                    break search;
                }
            }
        System.out.println(allWords.toString());
    }

    private GenerationWord allWords;
    public GenerationWord getAllWords() {
        return allWords;
    }
    public void printAll() {
        System.out.println(allWords.toString());
    }
    private int numWords;
    public int getNumWords() {
        return numWords;
    }
}
