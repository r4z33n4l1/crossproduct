package themes;

import java.util.List;

public class baseTheme extends ThemeDecorator {
    private int dices;
    public baseTheme(Theme theme, int dices) {
        super(theme, dices);
        this.dices = dices;
    }

    public String getFileName() {
        return "dictionaries\\wordlist.txt";
    }

    public List<String> getDices() {
        DiceGenerator diceGenerator = new DiceGenerator(this.getFileName(), dices);
        return diceGenerator.getStrings();
    }
}