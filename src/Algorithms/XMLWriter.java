package Algorithms;

import BaseClasses.Word;
import Tables.Table;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Locale;

// класс, реализующий запись таблицы в xml-файл
public class XMLWriter {
    // метод записи таблицы в файл
    public void TableExport(String path, List<Word> words, boolean fromGenerator, Table table) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            // корневой элемент
            Element rootElement = doc.createElement("Table");
            doc.appendChild(rootElement);
            // создаем элемент размера таблицы
            Element size = doc.createElement("Size");
            size.appendChild(doc.createTextNode(String.valueOf(table.getSize() + (fromGenerator ? 1: 0))));
            rootElement.appendChild(size);

            TableModel model = table.getTableView().getModel();
            // для каждого слова
            for (Word w: words) {
                if (fromGenerator)
                    // если источник - генератор, то поле written остается пустым
                    rootElement.appendChild(getWord(doc, w, "", fromGenerator));
                else {
                    // сканируем принадлещащие слову ячейки и получаем слово
                    StringBuilder answer = new StringBuilder();
                    // для горизонтальных слов
                    if (w.isHorizontal())
                        for (int y = w.getY(); y < w.getY() + w.getLength(); y++) {
                            if (model.getValueAt(w.getX(), y).toString().length() == 0)
                                model.setValueAt("_", w.getX(), y);
                            answer.append(model.getValueAt(w.getX(), y).toString().charAt(0));
                        }
                    else
                        // для вертикальных
                        for (int x = w.getX(); x < w.getX() + w.getLength(); x++) {
                            if (model.getValueAt(x, w.getY()).toString().length() == 0)
                                model.setValueAt("_", x, w.getY());
                            answer.append(model.getValueAt(x, w.getY()).toString().charAt(0));
                        }
                    answer = new StringBuilder(answer.toString().toLowerCase(Locale.ROOT));
                        rootElement.appendChild(getWord(doc, w, answer.toString(), fromGenerator));
                }
            }
            // записываем в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File(path));
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // метод получения ноды слова
    public static Node getWord(Document doc, Word word, String written, boolean fromGenerator) {
        Element genWord = doc.createElement("Word");
        genWord.appendChild(getWordElement(doc, "Term", word.getWord()));
        genWord.appendChild(getWordElement(doc, "Definition", word.getDefinition()));
        genWord.appendChild(getWordElement(doc, "IsHorizontal", String.valueOf(word.isHorizontal())));
        genWord.appendChild(getWordElement(doc, "PosX", String.valueOf(word.getX() + (fromGenerator? 1: 0))));
        genWord.appendChild(getWordElement(doc, "PosY", String.valueOf(word.getY() + (fromGenerator? 1: 0))));
        genWord.appendChild(getWordElement(doc, "Length", String.valueOf(word.getLength())));
        genWord.appendChild(getWordElement(doc, "Written", written));
        return genWord;
    }

    // метод получения ноды параметра слова
    private static Node getWordElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
