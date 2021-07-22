package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ExtraPanel extends JPanel {
    public ExtraPanel() {
        setBorder(BorderFactory.createTitledBorder("Список доступных слов"));
        setLayout(new GridBagLayout());

        // прикрепляем первую кнопку
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.ipadx = 300;
        constraints.ipady = 150;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        termsArea = new JTextArea("",20, 10);
        termsArea.setLineWrap(true);
        termsArea.setEditable(false);

        add(new JScrollPane(termsArea), constraints);
    }

    private JTextArea termsArea;
    public void setTerms(String terms) {
        termsArea.setText(terms);
    }
}
