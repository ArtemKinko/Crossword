package BaseClasses;

import Tables.Table;

import javax.swing.*;
import java.awt.*;

public class Page extends JPanel {
    public Page() {
        setLayout(new GridBagLayout());
    }

    private Table table;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
