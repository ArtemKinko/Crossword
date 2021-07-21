package Pages;

import Algorithms.GenerationWord;
import Algorithms.Generator;
import Algorithms.XMLReader;
import Algorithms.XMLSaver;
import BaseClasses.Page;
import BaseClasses.Word;
import Panels.DefinitionPanel;
import Panels.PathDictionaryPanel;
import Panels.PathTablePanel;
import Tables.GenerationTable;
import Tables.SolvingTable;
import Tables.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

        // панель загрузки таблицы
        pathTablePanel = new PathTablePanel();
        constraints.ipadx = 20;
        constraints.ipady = 30;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(pathTablePanel, constraints);

        // при нажатии на кнопку загрузки
        pathTablePanel.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                if (pathTablePanel.getFileChooser().getSelectedFile() == null)
                    message += "Не выбрана таблица. ";
                if (!message.equals(""))
                    JOptionPane.showMessageDialog(SolvingPage.this, message);
                else {
                    XMLReader reader = new XMLReader();
                    String definitions = reader.TableImport(pathTablePanel.getFileChooser().getSelectedFile().getPath(),
                            ((SolvingTable)getTable()).getWords(), (SolvingTable)getTable());
                    definitionPanel.setDefinitions(definitions);
                    JOptionPane.showMessageDialog(SolvingPage.this, "Успешно сохранено!");
                }
            }
        });

        // при нажатии на кнопку сохранения
        pathTablePanel.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                if (pathTablePanel.getFileChooser().getSelectedFile() == null)
                    message += "Не загружена таблица. ";
                if (!message.equals(""))
                    JOptionPane.showMessageDialog(SolvingPage.this, message);
                else {
                    XMLSaver saver = new XMLSaver();
                    saver.TableExport(pathTablePanel.getFileChooser().getSelectedFile().getPath(),
                            ((SolvingTable)getTable()).getWords(), false, (SolvingTable)getTable());
                    JOptionPane.showMessageDialog(SolvingPage.this, "Успешно сохранено!");
                }
            }
        });

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

        // при нажатии на кнопку проверки решения
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                if (pathTablePanel.getFileChooser().getSelectedFile() == null)
                    message += "Не загружена таблица. ";
                if (!message.equals(""))
                    JOptionPane.showMessageDialog(SolvingPage.this, message);
                else {
                    JOptionPane.showMessageDialog(SolvingPage.this, "Верно введено слов: " +
                            String.valueOf(((SolvingTable)getTable()).getRightWords()));
                }
            }
        });
    }

    private PathTablePanel pathTablePanel;
    private DefinitionPanel definitionPanel;
}
