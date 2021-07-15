package BaseClasses;

public class Word {

    private int lenght;             // длина слова
    private boolean isHorisontal;   // true - расположено горизонтально; false - вертикально
    private int x;                  // положение первой буквы в таблице по x
    private int y;                  // положение первой буквы в таблице по y
    private String word;            // само слово
    private String definition;      // определение слова

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public boolean isHorisontal() {
        return isHorisontal;
    }

    public void setHorisontal(boolean horisontal) {
        isHorisontal = horisontal;
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
                "lenght=" + lenght +
                ", isHorisontal=" + isHorisontal +
                ", x=" + x +
                ", y=" + y +
                ", word='" + word + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}
