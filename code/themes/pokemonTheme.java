package themes;

import java.util.List;

public class pokemonTheme extends ThemeDecorator {
    private int dices;
    public pokemonTheme(int dices) {
        super(dices);
        this.dices = dices;
    }

    public String getFileName() {
        return "themes\\dictionaries\\pokemon.txt";
    }

    public List<String> getDices() {
        DiceGenerator diceGenerator = new DiceGenerator(this.getFileName(), dices);
        return diceGenerator.getStrings();
    }
}

