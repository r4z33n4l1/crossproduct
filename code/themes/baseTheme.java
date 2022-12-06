package themes;

import java.util.List;

public class baseTheme extends ThemeDecorator {
    private int dices;
    public baseTheme(int dices) {
        super(dices);
        this.dices = dices;
    }

    public String getFileName() {
        return "code\\themes\\dictionaries\\wordlist.txt";
    }

    public List<String> getDices() {
        DiceGenerator diceGenerator = new DiceGenerator(this.getFileName(), dices);
        return diceGenerator.getStrings();
    }
}