package Panels;

import javax.swing.*;
import java.awt.*;

public class DefinitionPanel extends JPanel {
    public DefinitionPanel() {
        setBorder(BorderFactory.createTitledBorder("Определения"));
        setLayout(new GridBagLayout());

        // прикрепляем первую кнопку
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.ipadx = 300;
        constraints.ipady = 250;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        definitionArea = new JTextArea(20, 10);
        //definitionArea.setSize(new Dimension(100, 200));
        definitionArea.setLineWrap(true);

        JButton button = new JButton("TEXT");
        add(new JScrollPane(definitionArea), constraints);
    }

    private JTextArea definitionArea;
}
