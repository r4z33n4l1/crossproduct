package boggle;

/**
 * The GameMode abstract class defines the behaviour for each game mode
 */
public abstract class GameMode {

    /**
     * Update the game variable when a string s is added
     * @param s the string added
     * @return the string to be displayed
     */
    public abstract String addWord(String s);

    /**
     * Get the scores for the current session
     */
    public abstract int[] getScores();

    /**
     * Get the number of words in the current session
     * @return
     */
    public abstract int[] getNumWords();
}
