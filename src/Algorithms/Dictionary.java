package Algorithms;

import java.util.ArrayList;
import java.util.List;

// класс, реализующий словарь
public class Dictionary {
    public Dictionary() {
        words = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            List<SimpleXMLWord> list = new ArrayList<>();
            words.add(list);
        }
    }

    // список xml-слов, полученных из файла
    private final List<List<SimpleXMLWord>> words;
    public List<List<SimpleXMLWord>> getWords() {
        return words;
    }

    // метод приведедения всех меток isUsed к false
    public void ClearAllUsed() {
        for (List<SimpleXMLWord> list: words)
            for (SimpleXMLWord w: list)
                w.setUsed(false);
    }
}
