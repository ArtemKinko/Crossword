package Panels;

import javax.swing.*;
import java.awt.*;

public class PathDictionaryPanel extends JPanel{
    public PathDictionaryPanel() {
        setBorder(BorderFactory.createTitledBorder("Путь к словарю"));
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.ipadx = 70;
        constraints.ipady = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        JTextField pathField = new JTextField("Путь не выбран...");
        add(pathField, constraints);

        constraints.gridx = 1;
        constraints.ipadx = 20;
        constraints.ipady = 3;
        JButton pathButton = new JButton("...");
        add(pathButton, constraints);
    }
}
