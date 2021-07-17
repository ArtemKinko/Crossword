package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ExtraPanel extends JPanel {
    public ExtraPanel() {
        setBorder(BorderFactory.createTitledBorder("Дополнительно"));
        setLayout(new GridBagLayout());

        // прикрепляем первую кнопку
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.ipadx = 200;
        constraints.ipady = 10;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        radioDraw = new JRadioButton("Рисовать таблицу");
        radioDraw.setSelected(true);
        add(radioDraw, constraints);

        // слушатель для выбора первой кнопки
        radioDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioCount.setSelected(false);
                countField.setEnabled(false);
            }
        });

        // прикрепляем вторую кнопку
        constraints.gridwidth = 1;
        constraints.ipadx = 100;
        constraints.gridy = 1;
        radioCount = new JRadioButton("По числу слов:");
        add(radioCount, constraints);

        // слушатель для выбора второй кнопки
        radioCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioDraw.setSelected(false);
                countField.setEnabled(true);
            }
        });

        // прикрепляем поле с количеством слов
        constraints.gridx = 1;
        countField = new JTextField("5");
        countField.setEnabled(false);
        add(countField, constraints);

        // адаптер для нажатых клавиш, оставляющий только цифры
        countField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String value = countField.getText();
                int l = value.length();
                countField.setEditable(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == 8);
            }
        });

        // от 2 до 15 слов
        countField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countField.getText() == "")
                    countField.setText("2");
                else if (Integer.parseInt(countField.getText()) < 2)
                    countField.setText("2");
                else if (Integer.parseInt(countField.getText()) > 15)
                    countField.setText("15");
            }
        });
    }

    // радио-кнопка выбора генерации с рисованием таблицы
    private JRadioButton radioDraw;
    public JRadioButton getRadioDraw() {
        return radioDraw;
    }

    // радио-кнопка выбора генерации с количеством слов
    private JRadioButton radioCount;
    public JRadioButton getRadioCount() {
        return radioCount;
    }

    // поле с количеством слов
    private JTextField countField;
    public JTextField getCountField() {
        return countField;
    }
}
