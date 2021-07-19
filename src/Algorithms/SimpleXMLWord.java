package Algorithms;

public class SimpleXMLWord {
    SimpleXMLWord(String word, String description, int length) {
        this.word = word;
        this.description = description;
        this.length = length;
    }

    private String word;
    private String description;
    private int length;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
