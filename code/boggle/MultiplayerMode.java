package boggle;

import boggle.stats.BoggleStats;

import java.util.Set;

public class MultiplayerMode extends GameMode {
    private final BoggleStats gameStats;
    private boolean isPlayerOne;

    public MultiplayerMode(BoggleStats stats) {
        this.gameStats = stats;
        this.isPlayerOne = true;
    }

    /**
     * Update the game variable when a string s is added
     *
     * @param s the string added
     * @return the string to be displayed
     */
    @Override
    public String addWord(String s, Set<String> allWords) {
        this.isPlayerOne = !this.isPlayerOne;
        if (!allWords.contains(s)) {
            return "Invalid Word";
        }
        if (gameStats.getPlayerWords(BoggleStats.Player.Player1).contains(s) ||
                gameStats.getPlayerWords(BoggleStats.Player.Player2).contains(s)) {
            return "Word Already Found";
        }
        if (s.length() < 4) {
            return "Word Too Short";
        }
        if (isPlayerOne) {
            gameStats.addWord(s, BoggleStats.Player.Player2);
        } else {
            gameStats.addWord(s, BoggleStats.Player.Player1);
        }

        return "Word Added";
    }

    /**
     * Get the scores for the current session
     */
    @Override
    public int[] getScores() {
        int[] scores = new int[2];
        scores[0] = gameStats.getScore(BoggleStats.Player.Player1);
        scores[1] = gameStats.getScore(BoggleStats.Player.Player2);
        return scores;
    }

    /**
     * Get the number of words found for the current session
     */
    @Override
    public int[] getNumWords() {
        int[] scores = new int[2];
        scores[0] = gameStats.getPlayerWords(BoggleStats.Player.Player1).size();
        scores[1] = gameStats.getPlayerWords(BoggleStats.Player.Player2).size();
        return scores;
    }

    /**
     * Get the current gamemode
     */
    @Override
    public BoggleGame.GameModes getGameMode() {
        return BoggleGame.GameModes.MULTIPLAYER;
    }

    /**
     * Get string to display on the GUI especially for multiplayer mode
     */
    @Override
    public String getDisplayString() {
        if (isPlayerOne) {
            return "Player 1's turn:";
        } else {
            return "Player 2's turn:";
        }
    }
}