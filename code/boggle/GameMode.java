package boggle;

import java.util.Set;

/**
 * The GameMode abstract class defines the behaviour for each game mode
 */
public abstract class GameMode {

    /**
     * Update the game variable when a string s is added
     * @param s the string added
     * @param allWords the Dictionary
     * @return the string to be displayed
     */
    public abstract String addWord(String s, Set<String> allWords);

    /**
     * Get the scores for the current session
     */
    public abstract int[] getScores();

    /**
     * Get the number of words in the current session
     * @return
     */
    public abstract int[] getNumWords();

    /**
     * Get the current gamemode
     */
    public abstract BoggleGame.GameModes getGameMode();

    /**
     * Get string to display on the GUI especially for multiplayer mode
     */
    public abstract String getDisplayString();
}
