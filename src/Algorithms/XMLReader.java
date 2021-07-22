package Algorithms;

import BaseClasses.Word;
import Tables.SolvingTable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class XMLReader {
    // возвращает строку определений слов
    public String TableImport(String path, List<Word> wordList, SolvingTable table) {
        String definitions = "";
        try {
            wordList.clear();
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = (Document)documentBuilder.parse(path);

            Node root = document.getDocumentElement();

            // просматриваем все подэлементы
            NodeList words = root.getChildNodes();
            int counter = 0;
            for (int i = 0; i < words.getLength(); i++) {
                Node word = words.item(i);
                if (word.getNodeType() != Node.TEXT_NODE) {
                    if (word.getNodeName() == "Size") {
                        // размер
                        table.setSize(Integer.parseInt(word.getChildNodes().item(0).getTextContent()));
                        TableModel model = table.getTableView().getModel();
                        for (int ii = 0; ii < table.getSize(); ii++)
                            for (int jj = 0; jj < table.getSize(); jj++)
                                model.setValueAt("", ii, jj);
                        continue;
                    }
                    NodeList wordProps = word.getChildNodes();
                    int index = 0;
                    counter++;

                    Word xmlWord = new Word();
                    for (int j = 0; j < wordProps.getLength(); j++) {
                        Node wordProp = wordProps.item(j);
                        if (wordProp.getNodeType() != Node.TEXT_NODE) {
                            switch (index) {
                                case 0:
                                    // слово
                                    xmlWord.setWord(wordProp.getChildNodes().item(0).getTextContent());
                                    index++;
                                    break;
                                case 1:
                                    // определение
                                    xmlWord.setDefinition(wordProp.getChildNodes().item(0).getTextContent());
                                    definitions += String.valueOf(counter) + ". " + xmlWord.getDefinition() + "\n";
                                    index++;
                                    break;
                                case 2:
                                    // направление слова
                                    String str = wordProp.getChildNodes().item(0).getTextContent();
                                    xmlWord.setHorizontal(wordProp.getChildNodes().item(0).getTextContent().equals("true"));
                                    index++;
                                    break;
                                case 3:
                                    // позиция первой буквы по X
                                    xmlWord.setX(Integer.parseInt(wordProp.getChildNodes().item(0).getTextContent()));
                                    index++;
                                    break;
                                case 4:
                                    // позиция первой буквы по Y
                                    xmlWord.setY(Integer.parseInt(wordProp.getChildNodes().item(0).getTextContent()));
                                    index++;
                                    break;
                                case 5:
                                    // длина слова
                                    xmlWord.setLength(Integer.parseInt(wordProp.getChildNodes().item(0).getTextContent()));
                                    index++;
                                    break;
                                case 6:
                                    String written = "";
                                    if (wordProp.getChildNodes().item(0) != null)
                                        written = wordProp.getChildNodes().item(0).getTextContent();
                                    int writtenIndex = 0;
                                    // уже написанное слово и загрузка всех символов
                                    TableModel model = table.getTableView().getModel();
                                    // для горизонтальных слов
                                    if (xmlWord.isHorizontal()) {
                                        // ставим сноску
                                        model.setValueAt(model.getValueAt(xmlWord.getX(), xmlWord.getY() - 1)
                                                + String.valueOf(counter) + "→", xmlWord.getX(), xmlWord.getY() - 1);
                                        // фон и сохраненное слово
                                        for (int y = xmlWord.getY(); y < xmlWord.getY() + xmlWord.getLength(); y++) {
                                            if (writtenIndex < written.length()) {
                                                model.setValueAt(written.charAt(writtenIndex), xmlWord.getX(), y);
                                                writtenIndex++;
                                            }
                                            else
                                                model.setValueAt("_", xmlWord.getX(), y);
                                        }
                                    }
                                    // для вертикальных слов
                                    else {
                                        // ставим сноску
                                        model.setValueAt(model.getValueAt(xmlWord.getX() - 1, xmlWord.getY())
                                                + String.valueOf(counter) + "↓", xmlWord.getX() - 1, xmlWord.getY());
                                        for (int x = xmlWord.getX(); x < xmlWord.getX() + xmlWord.getLength(); x++) {
                                            if (writtenIndex < written.length()) {
                                                model.setValueAt(written.charAt(writtenIndex), x, xmlWord.getY());
                                                writtenIndex++;
                                            }
                                            else
                                                model.setValueAt("_", x, xmlWord.getY());
                                        }
                                    }
                                    wordList.add(xmlWord);
                                    break;
                            }
                        }
                    }
                    System.out.println(xmlWord.toString());
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return definitions;
    }

    // парсер xml
    public String DictionaryImport(String path, Dictionary dict) {
        String list = "";
        int listIndex = 0;
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
                    dict.getWords().get(l < 13 ? l : 12).add(SimpleWord);
                    listIndex++;
                    list += String.valueOf(listIndex) + ". " + SimpleWord.getWord() + "\n";
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
