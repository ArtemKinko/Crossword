package Pages;

import Tables.GenerationTable;
import javax.swing.*;

public class MainPage extends JFrame{
    MainPage() {
        super("Страница генерации кроссвордов");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private GenerationTable table;
}
