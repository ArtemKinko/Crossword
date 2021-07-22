package Panels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

// класс, реализующий панель выбора файла таблицы, сохранения и загрузки
public class PathTablePanel extends JPanel {
    public PathTablePanel() {
        setBorder(BorderFactory.createTitledBorder("Путь к таблице"));
        setLayout(new GridBagLayout());

        // прикрепляем поле с директорией к файлу
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.ipadx = 200;
        constraints.ipady = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        pathField = new JTextField("Путь не выбран...");
        pathField.setEditable(false);
        add(pathField, constraints);

        // прикрепляем кнопку
        constraints.gridx = 1;
        constraints.ipadx = 50;
        constraints.ipady = 3;
        JButton pathButton = new JButton("...");
        add(pathButton, constraints);

        // подключение кнопки для выбора файла
        fileChooser = new JFileChooser();
        pathButton.addActionListener(e -> {
            fileChooser.setDialogTitle("Выберите файл словаря");
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML-files", "xml"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(PathTablePanel.this);
            if (result == JFileChooser.APPROVE_OPTION)
                pathField.setText(String.valueOf(fileChooser.getSelectedFile()));
        });

        // прикрепляем кнопку сохранения
        constraints.ipadx = 150;
        constraints.gridx = 0;
        constraints.gridy = 1;
        saveButton = new JButton("Сохранить");
        add(saveButton, constraints);

        // прикрепляем кнопку загрузки
        constraints.ipadx = 0;
        constraints.gridx = 1;
        loadButton = new JButton("Загрузить");
        add(loadButton, constraints);
    }

    // выбор файла
    private final JFileChooser fileChooser;
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    // поле с директорией
    private final JTextField pathField;

    // кнопка сохранения
    private final JButton saveButton;
    public JButton getSaveButton() {
        return saveButton;
    }

    // кнопка загрузки
    private final JButton loadButton;
    public JButton getLoadButton() {
        return loadButton;
    }
}
