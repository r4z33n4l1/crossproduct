package themes;
import themes.Theme;

import java.util.List;

public abstract class ThemeDecorator implements Theme {
    private Theme theme;
    private int dices;

    public ThemeDecorator(Theme theme, int dices) {
        this.theme = theme;
        this.dices = dices;
    }

    public String getFileName() {
        return this.theme.getFileName();
    }

    public List<String> getDices() {
        return this.theme.getDices();
    }
}