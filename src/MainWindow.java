import Pages.GenerationPage;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    MainWindow() {
        super("Crossword generator by Kinko Artem");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);

        // панель вкладок
        JTabbedPane tabbedPane = new JTabbedPane();

        // ТУТ БУДЕТ ДОБАВЛЕНИЕ СТРАНИЦ

        tabbedPane.addTab("Generate", new GenerationPage());

        // КОНЕЦ ДОБАВЛЕНИЯ СТРАНИЦ
        getContentPane().add(tabbedPane);



        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
