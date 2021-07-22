package Pages;

import Algorithms.Dictionary;
import Algorithms.GenerationWord;
import Algorithms.Generator;
import Algorithms.XMLWriter;
import Algorithms.XMLReader;
import BaseClasses.Page;
import Panels.ExtraPanel;
import Panels.PathDictionaryPanel;
import Panels.SaveGenPanel;
import Panels.SizePanel;
import Tables.GenerationTable;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

// класс страницы для генерации
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

        // при нажатии на кнопку выбора словаря
        pathDictionaryPanel.getPathButton().addActionListener(e -> {
            // получаем путь к словарю
            pathDictionaryPanel.getFileChooser().setDialogTitle("Выберите файл словаря");
            pathDictionaryPanel.getFileChooser().addChoosableFileFilter(new FileNameExtensionFilter("XML-files", "xml"));
            pathDictionaryPanel.getFileChooser().setAcceptAllFileFilterUsed(false);
            pathDictionaryPanel.getFileChooser().setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = pathDictionaryPanel.getFileChooser().showOpenDialog(GenerationPage.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                // если выбран xml-файл
                pathDictionaryPanel.getPathField().setText(String.valueOf(pathDictionaryPanel.getFileChooser().getSelectedFile()));
                XMLReader reader = new XMLReader();
                // парсим его и создаем словарь
                String str = reader.DictionaryImport(pathDictionaryPanel.getFileChooser().getSelectedFile().getPath(), dictionary);
                extraPanel.setTerms(str);
            }
        });

        // панель вывода слов словаря
        extraPanel = new ExtraPanel();
        constraints.gridy = 1;
        constraints.ipady = 0;
        add(extraPanel, constraints);

        // панель задания размера
        sizePanel = new SizePanel();
        constraints.gridy = 2;
        add(sizePanel, constraints);

        // слушатель для изменения размера от 10 до 30
        sizePanel.getNumField().addActionListener(e -> {
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

        // выполняется при нажатии на кнопку генерации
        generateButton.addActionListener(e -> {
            // если что-то не загружено
            String message = "";
            if (pathDictionaryPanel.getFileChooser().getSelectedFile() == null)
                message += "Не выбран словарь. ";
            if (saveGenPanel.getFileChooser().getSelectedFile() == null)
                message += "Не выбрана директория сохранения. ";
            if (saveGenPanel.getNameField().getText().equals(""))
                message += "Пустое имя файла.";

            if (!message.equals(""))
                // показываем сообщение об ошибке
                JOptionPane.showMessageDialog(GenerationPage.this, message);
            else {
                // генерируем таблицу
                ((GenerationTable)getTable()).genMainWord();
                Generator generator = new Generator(dictionary);
                GenerationWord genWord = ((GenerationTable) getTable()).getAllWords();
                generator.Generate(genWord, ((GenerationTable)getTable()).getNumWords());

                // сохраняем ее в файл
                String path = saveGenPanel.getFileChooser().getSelectedFile().getPath() + "\\" +
                        saveGenPanel.getNameField().getText() + ".xml";
                XMLWriter saver = new XMLWriter();
                saver.TableExport(path, generator.getWordList(), true, getTable());
                // показываем сообщение об успешном выполнении
                JOptionPane.showMessageDialog(GenerationPage.this, "Успешно сохранено!");
            }
        });
    }

    private final PathDictionaryPanel pathDictionaryPanel;
    private final SizePanel sizePanel;
    private ExtraPanel extraPanel;
    private final SaveGenPanel saveGenPanel;
    private final Dictionary dictionary;

}
