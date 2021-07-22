package Panels;

import javax.swing.*;
import java.awt.*;

// класс для реализации панели определений
public class DefinitionPanel extends JPanel {
    public DefinitionPanel() {
        setBorder(BorderFactory.createTitledBorder("Определения"));
        setLayout(new GridBagLayout());

        // прикрепляем зону для определений
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.ipadx = 300;
        constraints.ipady = 250;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        definitionArea = new JTextArea("",20, 10);
        definitionArea.setLineWrap(true);
        definitionArea.setEditable(false);
        add(new JScrollPane(definitionArea), constraints);
    }

    private final JTextArea definitionArea;
    public void setDefinitions(String definitions) {
        definitionArea.setText(definitions);
    }
}
