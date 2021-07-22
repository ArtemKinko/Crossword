package Algorithms;

import BaseClasses.Word;

import java.util.ArrayList;
import java.util.List;

// класс, реализующий генерацию слов
public class Generator {
    public Generator(Dictionary dict) {
        this.dict = dict;
        wordList = new ArrayList<>();
    }
    private final Dictionary dict;

    // главный метод, начинающий генерацию с первого слова, найденного в таблице, word,
    // и принимающий количество слов в таблице numWords
    public GenerationWord Generate(GenerationWord word, int numWords){
        // создаем копию слов, в которой будем запоминать максимально длинный кроссворд
        GenerationWord tempWord = word;
        // максимальное количество успешно сгенерированных слов
        int maxWords = 0;
        // для каждого первого слова
        for (SimpleXMLWord w: dict.getWords().get(Math.min(word.getLength(), 12))) {
            // обнуляем количество сгенерированных слов
            curWords = 0;
            // очищаем все метки
            dict.ClearAllUsed();
            // очищаем список слов
            wordList.clear();
            // ставим метку, что использовали первое слово
            w.setUsed(true);
            // устанавливаем слово и определение
            word.setWord(w.getWord());
            word.setDefinition(w.getDescription());
            // добавляем в список
            wordList.add(word);
            // увеличиваем счетчик сгенерированных слов
            curWords++;
            // начинаем смотреть связи
            AddPair(word);
            // если сгенерированы все слова
            if (curWords >= numWords)
                // выходим из цикла по первым словам
                break;
            // если больше, чем максимум
            else if (curWords > maxWords) {
                // запоминаем текущую генерацию и изменяем максимум
                maxWords = curWords;
                tempWord = word;
            }
        }
        // возвращаем максимум генерации
        if (curWords >= numWords)
            return word;
        else
            return tempWord;
    }

    // метод для генерации слова по связям
    private void AddPair(GenerationWord word){
        // для всех связанных слов
        for (int i = 0; i < word.getPairList().size(); i++) {
            // получаем связанное слово
            GenerationWord pairWord = (GenerationWord)word.getPairList().get(i).get(0);
            // получаем букву, которая должна быть одинаковой
            char charMain = word.getWord().charAt((Integer)word.getPairList().get(i).get(1) - 1);
            // получаем позицию в связанном слове
            int posPair = (Integer)word.getPairList().get(i).get(2) - 1;
            // перебираем все слова подходящей длины
            for (SimpleXMLWord w: dict.getWords().get(pairWord.getLength())) {
                // если буква совпадает
                if (w.getWord().charAt(posPair) == charMain && !w.isUsed()) {
                    // ставим метку использования
                    w.setUsed(true);
                    // генерируем это слово и продолжаем генерировать связанные с ним слова
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

    // список сгенерированных слов
    private final List<Word> wordList;
    public List<Word> getWordList() {
        return wordList;
    }

    // текущее количество сгенерированных слов
    private int curWords;
}
