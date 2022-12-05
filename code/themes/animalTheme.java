package themes;

import java.util.List;

public class animalTheme extends ThemeDecorator {
    private int dices;
    public animalTheme(Theme theme, int dices) {
        super(theme, dices);
        this.dices = dices;
    }

    public String getFileName() {
        return "dictionaries\\animals.txt";
    }

    public List<String> getDices() {
        DiceGenerator diceGenerator = new DiceGenerator(this.getFileName(), dices);
        return diceGenerator.getStrings();
    }
}

