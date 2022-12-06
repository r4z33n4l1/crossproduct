package boggle.stats;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
     * Set of words the computer finds in a given round
     */
    private Set<String> missing_words = new HashSet<String>();

    /**
     * Set of words that the first player finds in a given round
     */
    private Set<String> player1Words;

    /**
     * Set of words that the second player finds in a given round
     */
    private Set<String> player2Words;

    /**
     * Player 1's score for the current round
     */
    private int player1Score;

    /**
     * Player 2's score for the current round
     */
    private int player2Score;

    /**
     * Number of hints used by the player
     */
    private int hintsCount;

    /**
     * Enumerable types of players (human or computer)
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
        this.hintsCount = 0;
        this.player1Words = new TreeSet<String>();
        this.player2Words = new TreeSet<String>();
        this.missing_words = new HashSet<String>();
    }

    /**
     * Add a word to a given player's word list for the current round.
     * You will also want to increment the player's score, as words are added.
     *
     * @param word The word to be added to the list
     * @param player The player to whom the word was awarded
     */
    public void addWord(String word, Player player) {
        String insensitive_word = word.toLowerCase();
        if (player == Player.Player1) {
            player1Words.add(insensitive_word);
            player1Score  = player1Score + (insensitive_word.length() - 4) + 1;
            missing_words.remove(insensitive_word);
        } else {
            player2Words.add(insensitive_word);
            missing_words.remove(insensitive_word);
            player2Score = player2Score + (insensitive_word.length() - 4) + 1;
        }
    }

    /*
     * Summary of the game
     * ONLY USED FOR TERMINAL TESTING
     */
    public void summarizeRound() {
        System.out.println("The words the human player 1 found this round was " + player1Words);
        System.out.println("The words the human player 2 found this round was " + player2Words);
        System.out.println("The words not found this round " + missing_words);
        System.out.println("The number of words the human player 1 (practice, pvp) found this round was " + player1Words.size());
        System.out.println("The number of words the human player 2 (pvp) found this round was " + player2Words.size());
        System.out.println("The number of words not found this round " + missing_words.size());
        System.out.println("The number of hints called this round " + hintsCount);
    }

    /**
     * Return End Game Stats
     */
    public String[] endGameStats(boolean isMultiplayer) {
        String[] endStats = {"NA", "NA", "NA", "NA", "NA"};
        endStats[0] = String.valueOf(player1Score);
        endStats[2] = player1Words.size() + " words found";
        endStats[4] = missing_words.size() + " words not found";
        if (isMultiplayer) {
            endStats[1] = String.valueOf(player2Score);
            endStats[3] = player2Words.size() + " words found";
        }
        return endStats;
    }

    /**
     * @return Set<String> The player's word list
     */
    public Set<String> getPlayerWords(Player player) {
        if (player == Player.Player1) {
            return player1Words;
        } else {
            return player2Words;
        }
    }

    /**
     * Getter method for missing words
     * @return Set<String> Words that have not been found yet (for both players or a single player)
     */
    public Set<String> getWordsNotFound(){
        return this.missing_words;
    }

    /**
     * Setter method for missing words
     * Used in helper method for findAllWords in BoggleGame class
     */
    public void setWordsNotFound(String word){
        missing_words.add(word);
    }

    /**
     * Hint counter
     */
    public void HintCounter(){
        hintsCount++;
    }

    /**
    * @return int The current player's score
    */
    public int getScore(Player player) {
        if (player == Player.Player1) {
            return player1Score;
        } else {
            return player2Score;
        }
    }
}
