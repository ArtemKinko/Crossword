package Pages;

import BaseClasses.Page;
import Tables.GenerationTable;
import javax.swing.*;

public class GenerationPage extends Page {
    public GenerationPage() {
        // устанавливаем генерационную таблицу
        setTable(new GenerationTable(9));

        // добавляем на экран таблицу
        add(getTable().getTableView());
    }
}
