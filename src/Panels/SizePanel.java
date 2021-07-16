package Panels;

import javax.swing.*;
import java.awt.*;

public class SizePanel extends JPanel {
    public SizePanel() {
        setBorder(BorderFactory.createTitledBorder("Размер поля"));
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.ipadx = 20;
        constraints.ipady = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel label = new JLabel("Введите размер таблицы:");
        add(label, constraints);

        constraints.gridx = 1;
        constraints.ipadx = 50;
        JTextField numField = new JTextField();
        add(numField, constraints);
    }
}
