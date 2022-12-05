package themes;

import java.util.List;

public class pokemonTheme extends ThemeDecorator {
    private int dices;
    public pokemonTheme(Theme theme, int dices) {
        super(theme, dices);
        this.dices = dices;
    }

    public String getFileName() {
        return "dictionaries\\pokemon.txt";
    }

    public List<String> getDices() {
        DiceGenerator diceGenerator = new DiceGenerator(this.getFileName(), dices);
        return diceGenerator.getStrings();
    }
}

