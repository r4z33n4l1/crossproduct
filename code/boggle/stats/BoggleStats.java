package boggle.stats;

import java.util.HashSet;
import java.util.Set;

/**
 * The BoggleStats class for the first Assignment in CSC207, Fall 2022
 * The BoggleStats will contain statistics related to game play Boggle
 */
public class BoggleStats {

    /**
     * It's a singleton class so, yeah!
     */
    private static BoggleStats instance = null;
    /**
     * set of words that the first player finds in a given round
     */
    private Set<String> player1Words;
    /**
     * set of words that the second player finds in a given round
     */
    private Set<String> player2Words;
    /**
     * player 1's score for the current round
     */
    private int player1Score;
    /**
     * player 2's score for the current round
     */
    private int player2Score;

    /**
     * enumarable types of players (human or computer)
     */
    public enum Player {
        Player1("Player1"),
        Player2("Player2");
        private final String player;
        Player(final String player) {
            this.player = player;
        }
    }

    /* BoggleStats constructor
     * ----------------------
     * Sets scores to 0.
     * Initializes word lists (which are sets) for computer and human players.
     */
    private BoggleStats() {

        this.resetStats();

    }

    /**
     * Get instance of boggleStats
     * @return current instance
     */
    public static synchronized BoggleStats getInstance() {
        if (instance == null) {
            instance = new BoggleStats();
        }
        return instance;
    }

    /**
     * Sets all the stats to 0 for a new game
     */
    public void resetStats() {
        this.player1Score = 0;
        this.player2Score = 0;
        this.player1Words = new HashSet<String>();
        this.player2Words = new HashSet<String>();
    }

    /*
     * Add a word to a given player's word list for the current round.
     * You will also want to increment the player's score, as words are added.
     *
     * @param word     The word to be added to the list
     * @param player  The player to whom the word was awarded
     */
    public void addWord(String word, Player player) {
        if (player == Player.Player1) {
            player1Words.add(word);
            player1Score  = player1Score + (word.length() - 4) + 1;
        } else {
            player2Words.add(word);
            player2Score = player2Score + (word.length() - 4) + 1;
        }
    }

    /*
     * @return Set<String> The player's word list
     */
    public Set<String> getPlayerWords(Player player) {
        if (player == Player.Player1) {
            return player1Words;
        } else {
            return player2Words;
        }
    }

    /*
    * @return int The current player score
    */
    public int getScore(Player player) {
        if (player == Player.Player1) {
            return player1Score;
        } else {
            return player2Score;
        }
    }

}
