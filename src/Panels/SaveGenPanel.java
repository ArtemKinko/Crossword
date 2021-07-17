package Panels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveGenPanel extends JPanel {
    public SaveGenPanel() {
        setBorder(BorderFactory.createTitledBorder("Сохранение"));
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // добавляем надпись
        constraints.ipadx = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel labelPath = new JLabel("Путь:");
        add(labelPath, constraints);

        // добавляем поле директории
        constraints.gridx = 1;
        constraints.ipadx = 20;
        constraints.gridwidth = 2;
        constraints.ipady = 10;
        JTextField pathField = new JTextField("Путь не выбран...");
        pathField.setEditable(false);
        add(pathField, constraints);

        // добавляем кнопку выбора директории
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.ipadx = 20;
        constraints.ipady = 3;
        JButton buttonPath = new JButton("...");
        add(buttonPath, constraints);

        // слушатель нажатия кнопки для выбора директории
        fileChooser = new JFileChooser();
        buttonPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выберите директорию для сохранения кроссворда");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(SaveGenPanel.this);
                if (result == JFileChooser.APPROVE_OPTION)
                    pathField.setText(String.valueOf(fileChooser.getSelectedFile()));
            }
        });

        // добавляем надпись
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        JLabel labelName = new JLabel("Введите название файла:");
        add(labelName, constraints);

        // добавляем поле названия файла
        constraints.gridx = 2;
        constraints.ipadx = 50;
        constraints.gridwidth = 2;
        nameField = new JTextField();
        add(nameField, constraints);
    }

    private JFileChooser fileChooser;
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    private JTextField nameField;
    public JTextField getNameField() {
        return nameField;
    }
}
