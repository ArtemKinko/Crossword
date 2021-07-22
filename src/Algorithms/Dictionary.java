package Algorithms;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    public Dictionary() {
        words = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            List<SimpleXMLWord> list = new ArrayList<>();
            words.add(list);
        }
    }
    private List<List<SimpleXMLWord>> words;
    public List<List<SimpleXMLWord>> getWords() {
        return words;
    }

    public void ClearAllUsed() {
        for (List<SimpleXMLWord> list: words)
            for (SimpleXMLWord w: list)
                w.setUsed(false);
    }

    public void printAllWords() {
        for (int i = 0; i < 13; i++) {
            System.out.println("------\nСлова длины " + String.valueOf(i) + ":\n");
            for (SimpleXMLWord x: this.words.get(i))
                System.out.println(x.getWord());
        }
    }
}
