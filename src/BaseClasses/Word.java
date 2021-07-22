package BaseClasses;

// базовый класс слова
public class Word extends Object{

    private int length;             // длина слова
    private boolean isHorizontal;   // true - расположено горизонтально; false - вертикально
    private int x;                  // положение первой буквы в таблице по x
    private int y;                  // положение первой буквы в таблице по y
    private String word;            // само слово
    private String definition;      // определение слова

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "BaseClasses.Word{" +
                "length=" + length +
                ", isHorizontal=" + isHorizontal +
                ", x=" + x +
                ", y=" + y +
                ", word='" + word + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}
