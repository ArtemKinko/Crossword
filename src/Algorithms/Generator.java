package Algorithms;

import BaseClasses.Word;

import java.util.ArrayList;
import java.util.List;

public class Generator {
    public Generator(Dictionary dict) {
        this.dict = dict;
        wordList = new ArrayList<>();
    }
    private Dictionary dict;

    public GenerationWord Generate(GenerationWord word, int numWords){
        for (SimpleXMLWord w: dict.getWords().get(Math.min(word.getLength(), 12))) {
            curWords = 0;
            dict.ClearAllUsed();
            wordList.clear();
            w.setUsed(true);
            word.setWord(w.getWord());
            word.setDefinition(w.getDescription());
            wordList.add(word);
            curWords++;
            AddPair(word);
            if (curWords >= numWords)
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
                    curWords++;
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
    private int curWords;
}
