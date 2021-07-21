package Algorithms;

import BaseClasses.Word;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.text.AbstractDocument;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public Generator(Dictionary dict) {
        this.dict = dict;
        wordList = new ArrayList<>();
    }
    private Dictionary dict;

    public GenerationWord Generate(GenerationWord word){
        for (SimpleXMLWord w: dict.getWords().get(Math.min(word.getLength(), 12))) {
            w.setUsed(true);
            word.setWord(w.getWord());
            word.setDefinition(w.getDescription());
            wordList.add(word);
            AddPair(word);
            break;
        }
        return word;
    }

    private void AddPair(GenerationWord word){
        for (int i = 0; i < word.getPairList().size(); i++) {
            GenerationWord pairWord = (GenerationWord)word.getPairList().get(i).get(0);
            char charMain = word.getWord().charAt((Integer)word.getPairList().get(i).get(1) - 1);
            int posPair = (Integer)word.getPairList().get(i).get(2) - 1;
            for (SimpleXMLWord w: dict.getWords().get(pairWord.getLength())) {
                if (w.getWord().charAt(posPair) == charMain && !w.isUsed()) {
                    w.setUsed(true);
                    pairWord.setWord(w.getWord());
                    pairWord.setDefinition(w.getDescription());
                    wordList.add(pairWord);
                    AddPair(pairWord);
                    break;
                }
            }
        }
    }

    private List<Word> wordList;
    public List<Word> getWordList() {
        return wordList;
    }

    public void GenToPath(String path, int tableSize) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            // корневой элемент
            Element rootElement = doc.createElement("Table");
            doc.appendChild(rootElement);
            Element size = doc.createElement("Size");
            size.appendChild(doc.createTextNode(String.valueOf(tableSize)));
            rootElement.appendChild(size);

            for (Word w: wordList)
                rootElement.appendChild(getWord(doc, w));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("C:/Users/Nika_Lis/Desktop/Crossword/testTable.xml"));
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getWord(Document doc, Word word) {
        Element genWord = doc.createElement("Word");
        genWord.appendChild(getWordElement(doc, genWord, "Term", word.getWord()));
        genWord.appendChild(getWordElement(doc, genWord, "Definition", word.getDefinition()));
        genWord.appendChild(getWordElement(doc, genWord, "IsHorizontal", String.valueOf(word.isHorizontal())));
        genWord.appendChild(getWordElement(doc, genWord, "PosX", String.valueOf(word.getX())));
        genWord.appendChild(getWordElement(doc, genWord, "PosX", String.valueOf(word.getX())));
        genWord.appendChild(getWordElement(doc, genWord, "PosY", String.valueOf(word.getY())));
        genWord.appendChild(getWordElement(doc, genWord, "Length", String.valueOf(word.getLength())));
        genWord.appendChild(getWordElement(doc, genWord, "Written", ""));
        return genWord;
    }

    private static Node getWordElement(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
