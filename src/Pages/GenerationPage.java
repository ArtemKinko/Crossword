package Pages;

import BaseClasses.Page;
import Panels.ExtraPanel;
import Panels.PathDictionaryPanel;
import Panels.SaveGenPanel;
import Panels.SizePanel;
import Tables.GenerationTable;
import javax.swing.*;
import java.awt.*;

public class GenerationPage extends Page {
    public GenerationPage() {
        // устанавливаем генерационную таблицу
        setTable(new GenerationTable(9));


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 400;
        constraints.gridwidth = 1;
        constraints.gridheight = 5;
        constraints.gridx = 0;
        constraints.gridy = 0;



        // добавляем на экран таблицу
        add(getTable().getTableView(), constraints);

        JPanel panel = new PathDictionaryPanel();

        constraints.ipadx = 20;
        constraints.ipady = 30;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;

        add(panel, constraints);

        panel = new SizePanel();
        //panel.setBorder(BorderFactory.createTitledBorder("Размер поля"));
        constraints.gridy = 1;
        add(panel, constraints);

        panel = new ExtraPanel();
        constraints.gridy = 2;
        constraints.ipady = 100;
        add(panel, constraints);

        panel = new SaveGenPanel();
        constraints.gridy = 3;
        add(panel, constraints);

        JButton generateButton = new JButton("Сгенерировать!");
        constraints.gridy = 4;
        constraints.ipady = 20;
        add(generateButton, constraints);
    }
}
