package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// класс, реализующий панель для
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
        numField = new JTextField("10");

        // адаптер для нажатых клавиш, оставляющий только цифры
        numField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                numField.setEditable(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == 8);
            }
        });
        add(numField, constraints);
    }

    public JTextField getNumField() {
        return numField;
    }

    private final JTextField numField;
}
