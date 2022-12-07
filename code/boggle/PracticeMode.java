package boggle;

import boggle.stats.BoggleStats;

import java.util.Set;

public class PracticeMode extends GameMode {
    private final BoggleStats gameStats;

    public PracticeMode(BoggleStats stats) {
        this.gameStats = stats;

    }

    /**
     * Update the game variable when a string s is added
     *
     * @param s the string added
     * @return the string to be displayed
     */
    @Override
    public String addWord(String s, Set<String> allWords) {
        if (!allWords.contains(s)) {
            return "Invalid Word";
        }
        if (gameStats.getPlayerWords(BoggleStats.Player.Player1).contains(s)) {
            return "Word Already Found";
        }
        if (s.length() < 4) {
            return "Word Too Short";
        }
        gameStats.addWord(s, BoggleStats.Player.Player1);
        return "☑ Word Added";
    }

    /**
     * Get the scores for the current session
     */
    @Override
    public int[] getScores() {
        int[] scores = new int[1];
        scores[0] = gameStats.getScore(BoggleStats.Player.Player1);
        return scores;
    }

    /**
     * Get the number of words found for the current session
     */
    @Override
    public int[] getNumWords() {
        int[] scores = new int[1];
        scores[0] = gameStats.getPlayerWords(BoggleStats.Player.Player1).size();
        return scores;
    }

    /**
     * Get the current gamemode
     */
    @Override
    public BoggleGame.GameModes getGameMode() {
        return BoggleGame.GameModes.PRACTICE;
    }

    /**
     * Get string to display on the GUI especially for multiplayer mode
     */
    @Override
    public String getDisplayString() {
        return "Player 1 ";
    }
}
