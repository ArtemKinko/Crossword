package Pages;

import BaseClasses.Page;
import Panels.DefinitionPanel;
import Panels.PathDictionaryPanel;
import Panels.PathTablePanel;
import Tables.SolvingTable;

import javax.swing.*;
import java.awt.*;

public class SolvingPage extends Page {
    public SolvingPage() {
        // устанавливаем генерационную таблицу
        setTable(new SolvingTable(10));

        // параметры для текущего добавляемого компонента
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.ipadx = 400;        // размер по x
        constraints.gridwidth = 1;      // сколько клеток занимает по ширине
        constraints.gridheight = 5;     // сколько клеток занимает по высоте
        constraints.gridx = 0;          // x ячейки
        constraints.gridy = 0;          // y ячейки

        // добавляем на экран таблицу
        add(getTable().getTableView(), constraints);

        // панель загрузки словаря
        pathTablePanel = new PathTablePanel();
        constraints.ipadx = 20;
        constraints.ipady = 30;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(pathTablePanel, constraints);

        // панель определений
        definitionPanel = new DefinitionPanel();
        constraints.gridheight = 3;
        constraints.gridy = 1;
        add(definitionPanel, constraints);

        // кнопка проверки правильности решения
        JButton checkButton = new JButton("Проверить решение");
        constraints.gridheight = 1;
        constraints.gridy = 4;
        add(checkButton, constraints);
    }

    private PathTablePanel pathTablePanel;
    private DefinitionPanel definitionPanel;
}
