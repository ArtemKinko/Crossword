package Panels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PathDictionaryPanel extends JPanel{
    public PathDictionaryPanel() {
        setBorder(BorderFactory.createTitledBorder("Путь к словарю"));
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
        constraints.ipadx = 20;
        constraints.ipady = 3;
        pathButton = new JButton("...");
        add(pathButton, constraints);

        // подключение кнопки для выбора файла
        fileChooser = new JFileChooser();
    }

    // выбор файла
    private JFileChooser fileChooser;
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    // поле с директорией
    private JTextField pathField;
    public JTextField getPathField() {
        return pathField;
    }

    // кнопка открытия меню с выбором файла словаря
    private JButton pathButton;
    public JButton getPathButton() {
        return pathButton;
    }
}
