package Pages;

import Algorithms.Dictionary;
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
        dictionary = new Dictionary();
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
        pathDictionaryPanel = new PathDictionaryPanel();
        constraints.ipadx = 20;
        constraints.ipady = 30;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(pathDictionaryPanel, constraints);

        // панель задания размера
        sizePanel = new SizePanel();
        constraints.gridy = 1;
        add(sizePanel, constraints);

        // размер от 10 до 30
        sizePanel.getNumField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = sizePanel.getNumField().getText();
                if (value.equals(""))
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
            });

        // панель дополнительных настроек
        extraPanel = new ExtraPanel();
        constraints.gridy = 2;
        constraints.ipady = 100;
        add(extraPanel, constraints);

        // слушатели для радио-кнопок
        extraPanel.getRadioDraw().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sizePanel.getNumField().setEnabled(true);
                getTable().getTableView().setBackground(Color.WHITE);
                getTable().getTableView().setEnabled(true);
            }
        });

        extraPanel.getRadioCount().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sizePanel.getNumField().setEnabled(false);
                getTable().getTableView().setBackground(Color.LIGHT_GRAY);
                getTable().getTableView().clearSelection();
                getTable().getTableView().setEnabled(false);
            }
        });

        // панель сохранения
        saveGenPanel = new SaveGenPanel();
        constraints.ipady = 50;
        constraints.gridy = 3;
        add(saveGenPanel, constraints);

        // кнопка "Сгенерировать"
        JButton generateButton = new JButton("Сгенерировать!");
        constraints.gridy = 4;
        constraints.ipady = 20;
        add(generateButton, constraints);

        // выполняется при нажатии на кнопку
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
//                if (extraPanel.getRadioDraw().isSelected()) {
//                    if (pathDictionaryPanel.getFileChooser().getSelectedFile() == null)
//                        message += "Не выбран словарь. ";
//                }
//                if (saveGenPanel.getFileChooser().getSelectedFile() == null)
//                    message += "Не выбрана директория сохранения. ";
//                if (saveGenPanel.getNameField().getText().equals(""))
//                    message += "Пустое имя файла.";
//
//                if (!message.equals(""))
//                    JOptionPane.showMessageDialog(GenerationPage.this, message);
//                else {
                    ((GenerationTable)getTable()).getMainWord();
                    dictionary.ImportFromXML(pathDictionaryPanel.getFileChooser().getSelectedFile().getPath());
                    JOptionPane.showMessageDialog(GenerationPage.this, "Успешно сохранено!");
                }
//            }
        });
    }

    private PathDictionaryPanel pathDictionaryPanel;
    private SizePanel sizePanel;
    private ExtraPanel extraPanel;
    private SaveGenPanel saveGenPanel;
    private Dictionary dictionary;

}
