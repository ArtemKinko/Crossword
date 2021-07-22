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

public class XMLWriter {
    public void TableExport(String path, List<Word> words, boolean fromGenerator, Table table) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            // корневой элемент
            Element rootElement = doc.createElement("Table");
            doc.appendChild(rootElement);
            Element size = doc.createElement("Size");
            size.appendChild(doc.createTextNode(String.valueOf(table.getSize() + (fromGenerator ? 1: 0))));
            rootElement.appendChild(size);

            TableModel model = table.getTableView().getModel();
            for (Word w: words) {
                if (fromGenerator)
                    rootElement.appendChild(getWord(doc, w, "", fromGenerator));
                else {
                    String answer = "";
                    if (w.isHorizontal())
                        for (int y = w.getY(); y < w.getY() + w.getLength(); y++) {
                            if (model.getValueAt(w.getX(), y).toString().length() == 0)
                                model.setValueAt("_", w.getX(), y);
                            answer += model.getValueAt(w.getX(), y).toString().charAt(0);
                        }
                    else
                        for (int x = w.getX(); x < w.getX() + w.getLength(); x++) {
                            if (model.getValueAt(x, w.getY()).toString().length() == 0)
                                model.setValueAt("_", x, w.getY());
                            answer += model.getValueAt(x, w.getY()).toString().charAt(0);
                        }
                    answer = answer.toLowerCase(Locale.ROOT);
                        rootElement.appendChild(getWord(doc, w, answer, fromGenerator));
                }
            }
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

    public static Node getWord(Document doc, Word word, String written, boolean fromGenerator) {
        Element genWord = doc.createElement("Word");
        genWord.appendChild(getWordElement(doc, genWord, "Term", word.getWord()));
        genWord.appendChild(getWordElement(doc, genWord, "Definition", word.getDefinition()));
        genWord.appendChild(getWordElement(doc, genWord, "IsHorizontal", String.valueOf(word.isHorizontal())));
        genWord.appendChild(getWordElement(doc, genWord, "PosX", String.valueOf(word.getX() + (fromGenerator? 1: 0))));
        genWord.appendChild(getWordElement(doc, genWord, "PosY", String.valueOf(word.getY() + (fromGenerator? 1: 0))));
        genWord.appendChild(getWordElement(doc, genWord, "Length", String.valueOf(word.getLength())));
        genWord.appendChild(getWordElement(doc, genWord, "Written", written));
        return genWord;
    }

    private static Node getWordElement(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
