package themes;

import java.util.List;

public class animalTheme extends ThemeDecorator {
    private int dices;
    public animalTheme(int dices) {
        super(dices);
        this.dices = dices;
    }

    public String getFileName() {
        return "themes/dictionaries/animals.txt";
    }

    public List<String> getDices() {
        DiceGenerator diceGenerator = new DiceGenerator(this.getFileName(), dices);
        return diceGenerator.getStrings();
    }
}

