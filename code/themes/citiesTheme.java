package themes;

import java.util.List;

public class citiesTheme extends ThemeDecorator {
    private int dices;
    public citiesTheme(Theme theme, int dices) {
        super(theme, dices);
        this.dices = dices;
    }

    public String getFileName() {
        return "dictionaries\\cities.txt";
    }

    public List<String> getDices() {
        DiceGenerator diceGenerator = new DiceGenerator(this.getFileName(), dices);
        return diceGenerator.getStrings();
    }
}

