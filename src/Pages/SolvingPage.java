package Pages;

import BaseClasses.Page;
import Tables.SolvingTable;

public class SolvingPage extends Page {
    public SolvingPage() {
        // устанавливаем генерационную таблицу
        setTable(new SolvingTable(9));

        // добавляем на экран таблицу
        add(getTable().getTableView());
    }
}
