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

    // парсер xml
    public void ImportFromXML(String path) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = (Document)documentBuilder.parse(path);

            Node root = document.getDocumentElement();
            System.out.println("Список слов:");
            System.out.println();

            // просматриваем все подэлементы
            NodeList words = root.getChildNodes();
            for (int i = 0; i < words.getLength(); i++) {
                Node word = words.item(i);
                if (word.getNodeType() != Node.TEXT_NODE) {
                    NodeList wordProps = word.getChildNodes();
                    int index = 0;
                    String w = "";
                    String d = "";
                    int l = 0;
                    for (int j = 0; j < wordProps.getLength(); j++) {
                        Node wordProp = wordProps.item(j);
                        if (wordProp.getNodeType() != Node.TEXT_NODE) {
                            if (index == 0) {
                                w = wordProp.getChildNodes().item(0).getTextContent();
                                l = w.length();
                                index++;
                            }
                            else {
                                d = wordProp.getChildNodes().item(0).getTextContent();
                            }
                        }
                    }
                    SimpleXMLWord SimpleWord = new SimpleXMLWord(w, d, l);
                    this.words.get(l < 13 ? l : 12).add(SimpleWord);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        printAllWords();
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
