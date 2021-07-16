package Pages;

import BaseClasses.Page;
import Panels.ExtraPanel;
import Panels.PathDictionaryPanel;
import Panels.SaveGenPanel;
import Panels.SizePanel;
import Tables.GenerationTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerationPage extends Page {
    public GenerationPage() {
        // устанавливаем генерационную таблицу
        setTable(new GenerationTable(10));

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
        JPanel panel = new PathDictionaryPanel();
        constraints.ipadx = 20;
        constraints.ipady = 30;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(panel, constraints);

        // панель задания размера
        sizePanel = new SizePanel();
        constraints.gridy = 1;
        add(sizePanel, constraints);
        sizePanel.getNumField().addActionListener(new SizeChangedAdapter());

        // панель дополнительных настроек
        panel = new ExtraPanel();
        constraints.gridy = 2;
        constraints.ipady = 100;
        add(panel, constraints);

        // панель сохранения
        panel = new SaveGenPanel();
        constraints.gridy = 3;
        add(panel, constraints);

        // кнопка "Сгенерировать"
        JButton generateButton = new JButton("Сгенерировать!");
        constraints.gridy = 4;
        constraints.ipady = 20;
        add(generateButton, constraints);
    }

    private SizePanel sizePanel;

    private class SizeChangedAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String value = sizePanel.getNumField().getText();
            if (value == "")
                sizePanel.getNumField().setText("10");
            else {
                int num = Integer.parseInt(value);
                if (num < 10) {
                    sizePanel.getNumField().setText("10");
                    getTable().setSize(10);
                } else if (num > 30) {
                    sizePanel.getNumField().setText("30");
                    getTable().setSize(30);
                } else
                    getTable().setSize(num);
            }
            System.out.println(Integer.parseInt(sizePanel.getNumField().getText()));
        }
    }
}
