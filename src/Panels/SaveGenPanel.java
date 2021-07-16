package Panels;

import javax.swing.*;
import java.awt.*;

public class SaveGenPanel extends JPanel {
    public SaveGenPanel() {
        setBorder(BorderFactory.createTitledBorder("Сохранение"));
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.ipadx = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel labelPath = new JLabel("Путь:");
        add(labelPath, constraints);

        constraints.gridx = 1;
        constraints.ipadx = 20;
        constraints.gridwidth = 2;
        constraints.ipady = 10;
        JTextField pathField = new JTextField("Путь не выбран...");
        add(pathField, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.ipadx = 20;
        constraints.ipady = 3;
        JButton buttonPath = new JButton("...");
        add(buttonPath, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        JLabel labelName = new JLabel("Введите название файла:");
        add(labelName, constraints);

        constraints.gridx = 2;
        constraints.ipadx = 50;
        constraints.gridwidth = 2;
        JTextField nameField = new JTextField();
        add(nameField, constraints);
    }
}
