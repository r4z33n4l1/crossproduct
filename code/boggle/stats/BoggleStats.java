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
     *  set of words the player finds in a given round 
     */  
    private Set<String> playerWords = new HashSet<String>();  
    /**
     * set of words the computer finds in a given round 
     */  
    private Set<String> computerWords = new HashSet<String>();

    /**
     * set of words the computer finds in a given round
     */
    private Set<String> wordsnotfoundyet = new HashSet<String>();

    /**
     * the player's score for the current round
     */  
    private int pScore; 
    /**
     * the computer's score for the current round
     */  
    private int cScore; 
    /**
     * the player's total score across every round
     */  
    private int pScoreTotal; 

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


//        this.round = 0;
//        this.cScoreTotal = 0;
//        this.pScoreTotal = 0;
//        this.playerWords = new HashSet<String>();
//        this.computerWords = new HashSet<String>();
//        this.wordsnotfoundyet = new HashSet<String>();
//        this.pAverageWords = 0;
//        this.cAverageWords = 0;
//        this.cScore = 0;
//        this.pScore = 0;

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

        if (player == Player.Human) {
            playerWords.add(word);
            wordsnotfoundyet.remove(word);
            pScore  = pScore + (word.length() - 4) + 1;
        } else {
            wordsnotfoundyet.add(word);
            computerWords.add(word);
            cScore = cScore + (word.length() - 4) + 1;
        }
    }

    /* 
     * End a given round.
     * This will clear out the human and computer word lists, so we can begin again.
     * The function will also update each player's total scores, average scores, and
     * reset the current scores for each player to zero.
     * Finally, increment the current round number by 1.
     */
//    public void endRound() {
//        pAverageWords = (pAverageWords + ((playerWords.size()-pAverageWords)/(round + 1)));
//        cAverageWords = (cAverageWords + ((computerWords.size()-cAverageWords)/(round + 1)));
//        playerWords.clear();
//        computerWords.clear();
//        wordsnotfoundyet.clear();
//        cScoreTotal = cScoreTotal + cScore;
//        pScoreTotal = pScoreTotal + pScore;
//        cScore = 0;
//        pScore = 0;
//        round = round + 1;
//    }

    /* 
     * Summarize one round of boggle.  Print out:
     * The words each player found this round.
     * Each number of words each player found this round.
     * Each player's score this round.
     */
    public void summarizeRound() {

        System.out.println("The words the human player found this round was " + playerWords);
        System.out.println("The words the computer found this round was " + computerWords);
        System.out.println("The number of words the human player found this round was " + playerWords.size());
        System.out.println("The number of words the computer found this round was " + computerWords.size());
        System.out.println("The human player's score this round was " + pScore);
        System.out.println("The computer's score this round was " + cScore);

    }

    /* 
     * Summarize the entire boggle game.  Print out:
     * The total number of rounds played.
     * The total score for either player.
     * The average number of words found by each player per round.
     */
//    public void summarizeGame() {
//
//        System.out.println("The total number of rounds played: " + round);
//        System.out.println("The total score of the human player for the game: " + pScoreTotal);
//        System.out.println("The total score of the computer for the game: " + cScoreTotal);
//        System.out.println("The average number of words found by the human player per round: " + pAverageWords);
//        System.out.println("The average number of words found by the computer per round: " + cAverageWords);
//    }

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

     * @return Set<String> The computer's word list
     */
    public Set<String> getComputerWords() {
        return this.computerWords;
    }

    /*
     * @return Set<String> Words that haven't been found yet (old version of computer's word list)
     * At the moment same functionality as computerWords that will change with the addition of modes!
     */
    public Set<String> getWordsNotFound(){
        return this.wordsnotfoundyet;
    }

    public void setWordsNotFound(String word){
        wordsnotfoundyet.add(word);
    }

    /*
     * @return int The number of rounds played
     */
    // public int getRound() {
        return this.round;
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
