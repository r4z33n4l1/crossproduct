package boggle;

import boggle.stats.BoggleStats;

public class SingleplayerMode extends GameMode {
    private final BoggleStats gameStats;

    public SingleplayerMode(BoggleStats stats) {
        this.gameStats = stats;
    }

    /**
     * Update the game variable when a string s is added
     *
     * @param s the string added
     * @return the string to be displayed
     */
    @Override
    public String addWord(String s) {
        if (gameStats.getPlayerWords(BoggleStats.Player.Player1).contains(s)) {
            return "Word Already Found";
        }
        if (s.length() < 4) {
            return "Word Too Short";
        }
        gameStats.addWord(s, BoggleStats.Player.Player1);
        return "Word Added";
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
}