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

// класс, реализующий импорт таблиц и словарей
public class XMLReader {
    // метод, импортирующий таблицу из файла path и устанавливающий ее в table
    // возвращает строку пронумерованных определений
    public String TableImport(String path, List<Word> wordList, SolvingTable table) {
        // обнуляем определения
        StringBuilder definitions = new StringBuilder();
        try {
            // очищаем список слов
            wordList.clear();
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);

            // получаем корневую ноду
            Node root = document.getDocumentElement();

            // просматриваем все подэлементы
            NodeList words = root.getChildNodes();
            // текущий номер слова
            int counter = 0;
            for (int i = 0; i < words.getLength(); i++) {
                Node word = words.item(i);
                if (word.getNodeType() != Node.TEXT_NODE) {
                    if (word.getNodeName().equals("Size")) {
                        // если нода - размер
                        table.setSize(Integer.parseInt(word.getChildNodes().item(0).getTextContent()));
                        TableModel model = table.getTableView().getModel();
                        // обновляем таблицу под размер и помещаем в каждую ячейку пустую строку
                        for (int ii = 0; ii < table.getSize(); ii++)
                            for (int jj = 0; jj < table.getSize(); jj++)
                                model.setValueAt("", ii, jj);
                        continue;
                    }
                    // получаем свойства
                    NodeList wordProps = word.getChildNodes();
                    // индекс свойства
                    int index = 0;
                    counter++;

                    Word xmlWord = new Word();
                    for (int j = 0; j < wordProps.getLength(); j++) {
                        Node wordProp = wordProps.item(j);
                        if (wordProp.getNodeType() != Node.TEXT_NODE) {
                            // 0 - слово
                            // 1 - определение
                            // 2 - направление слова
                            // 3 - позиция первой буквы по X
                            // 4 - позиция первой буквы по Y
                            // 5 - длина слова
                            // 6 - сноска, уже написанное слово и загрузка всех символов
                            switch (index) {
                                case 0 -> {
                                    xmlWord.setWord(wordProp.getChildNodes().item(0).getTextContent());
                                    index++;
                                }
                                case 1 -> {
                                    xmlWord.setDefinition(wordProp.getChildNodes().item(0).getTextContent());
                                    definitions.append(counter).append(". ").append(xmlWord.getDefinition()).append("\n");
                                    index++;
                                }
                                case 2 -> {
                                    xmlWord.setHorizontal(wordProp.getChildNodes().item(0).getTextContent().equals("true"));
                                    index++;
                                }
                                case 3 -> {
                                    xmlWord.setX(Integer.parseInt(wordProp.getChildNodes().item(0).getTextContent()));
                                    index++;
                                }
                                case 4 -> {
                                    xmlWord.setY(Integer.parseInt(wordProp.getChildNodes().item(0).getTextContent()));
                                    index++;
                                }
                                case 5 -> {
                                    xmlWord.setLength(Integer.parseInt(wordProp.getChildNodes().item(0).getTextContent()));
                                    index++;
                                }
                                case 6 -> {
                                    String written = "";
                                    if (wordProp.getChildNodes().item(0) != null)
                                        written = wordProp.getChildNodes().item(0).getTextContent();
                                    int writtenIndex = 0;
                                    TableModel model = table.getTableView().getModel();
                                    if (xmlWord.isHorizontal()) {
                                        // ставим сноску
                                        model.setValueAt(model.getValueAt(xmlWord.getX(), xmlWord.getY() - 1)
                                                + String.valueOf(counter) + "→", xmlWord.getX(), xmlWord.getY() - 1);
                                        // записываем сохраненное слово
                                        for (int y = xmlWord.getY(); y < xmlWord.getY() + xmlWord.getLength(); y++) {
                                            if (writtenIndex < written.length()) {
                                                model.setValueAt(written.charAt(writtenIndex), xmlWord.getX(), y);
                                                writtenIndex++;
                                            } else
                                                model.setValueAt("_", xmlWord.getX(), y);
                                        }
                                    }
                                    // для вертикальных слов
                                    else {
                                        // ставим сноску
                                        model.setValueAt(model.getValueAt(xmlWord.getX() - 1, xmlWord.getY())
                                                + String.valueOf(counter) + "↓", xmlWord.getX() - 1, xmlWord.getY());
                                        // записываем сохраненное слово
                                        for (int x = xmlWord.getX(); x < xmlWord.getX() + xmlWord.getLength(); x++) {
                                            if (writtenIndex < written.length()) {
                                                model.setValueAt(written.charAt(writtenIndex), x, xmlWord.getY());
                                                writtenIndex++;
                                            } else
                                                model.setValueAt("_", x, xmlWord.getY());
                                        }
                                    }
                                    wordList.add(xmlWord);
                                }
                            }
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return definitions.toString();
    }

    // метод импорта словаря из файла path в объект dict
    public String DictionaryImport(String path, Dictionary dict) {
        // строка для вывода пронумерованных слов
        StringBuilder list = new StringBuilder();
        // порядковый номер
        int listIndex = 0;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);

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
                                // слово
                                w = wordProp.getChildNodes().item(0).getTextContent();
                                l = w.length();
                                index++;
                            }
                            else {
                                // определение
                                d = wordProp.getChildNodes().item(0).getTextContent();
                            }
                        }
                    }
                    // создаем xml-слово и добавляем его в список
                    SimpleXMLWord SimpleWord = new SimpleXMLWord(w, d, l);
                    dict.getWords().get(l < 13 ? l : 12).add(SimpleWord);
                    listIndex++;
                    list.append(listIndex).append(". ").append(SimpleWord.getWord()).append("\n");
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return list.toString();
    }
}
