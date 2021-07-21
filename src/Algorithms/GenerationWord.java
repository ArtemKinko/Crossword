package Algorithms;

import BaseClasses.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerationWord extends Word{
    public GenerationWord() {
        pairList = new ArrayList<>();
    }

    // список всех связанных с данным словом слов:
    // 1 параметр - слово, которое связано с главным
    // 2 параметр - позиция общей буквы ГЛАВНОГО слова
    // 3 параметр - позиция обзей буквы СВЯЗАННОГО слова
    private List<List<Object>> pairList;

    public List<List<Object>> getPairList() {
        return pairList;
    }

    public void addPair(GenerationWord word, int posMain, int posExtra) {
        List<Object> list = new ArrayList<>();
        list.add(word);
        list.add(posMain);
        list.add(posExtra);
        pairList.add(list);
    }

    @Override
    public String toString() {
        return "BaseClasses.Word{" +
                "length=" + getLength() +
                ", isHorizontal=" + isHorizontal() +
                ", x=" + getX() +
                ", y=" + getY() +
                ", word='" + getWord() + '\'' +
                ", definition='" + getDefinition() + '\'' +
                ", GenerationWord{" +
                ", pairList=" + pairList +
                '}';
    }
}
