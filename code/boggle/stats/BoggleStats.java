package boggle.stats;

import java.util.HashSet;
import java.util.Set;

/**
 * The BoggleStats class for the first Assignment in CSC207, Fall 2022
 * The BoggleStats will contain statsitics related to game play Boggle 
 */
public class BoggleStats {

    /**
     * set of words the player finds in a given round 
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
     * the computer's total score across every round
     */  
    private int cScoreTotal; 
    /**
     * the average number of words, per round, found by the player
     */  
    private double pAverageWords; 
    /**
     * the average number of words, per round, found by the computer
     */  
    private double cAverageWords; 
    /**
     * the current round being played
     */  
    private int round; 

    /**
     * enumarable types of players (human or computer)
     */  
    public enum Player {
        Human("Human"),
        Computer("Computer");
        private final String player;
        Player(final String player) {
            this.player = player;
        }
    }

    /* BoggleStats constructor
     * ----------------------
     * Sets round, totals and averages to 0.
     * Initializes word lists (which are sets) for computer and human players.
     */
    public BoggleStats() {

        this.round = 0;
        this.cScoreTotal = 0;
        this.pScoreTotal = 0;
        this.playerWords = new HashSet<String>();
        this.computerWords = new HashSet<String>();
        this.wordsnotfoundyet = new HashSet<String>();
        this.pAverageWords = 0;
        this.cAverageWords = 0;
        this.cScore = 0;
        this.pScore = 0;
    }

    /* 
     * Add a word to a given player's word list for the current round.
     * You will also want to increment the player's score, as words are added.
     *
     * @param word     The word to be added to the list
     * @param player  The player to whom the word was awarded
     */
    public void addWord(String word, Player player) {
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
    public void endRound() {
        pAverageWords = (pAverageWords + ((playerWords.size()-pAverageWords)/(round + 1)));
        cAverageWords = (cAverageWords + ((computerWords.size()-cAverageWords)/(round + 1)));
        playerWords.clear();
        computerWords.clear();
        wordsnotfoundyet.clear();
        cScoreTotal = cScoreTotal + cScore;
        pScoreTotal = pScoreTotal + pScore;
        cScore = 0;
        pScore = 0;
        round = round + 1;
    }

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
    public void summarizeGame() {

        System.out.println("The total number of rounds played: " + round);
        System.out.println("The total score of the human player for the game: " + pScoreTotal);
        System.out.println("The total score of the computer for the game: " + cScoreTotal);
        System.out.println("The average number of words found by the human player per round: " + pAverageWords);
        System.out.println("The average number of words found by the computer per round: " + cAverageWords);
    }

    /* 
     * @return Set<String> The player's word list
     */
    public Set<String> getPlayerWords() {
        return this.playerWords;
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
    public int getRound() {
        return this.round;
    }

    /*
    * @return int The current player score
    */
    public int getScore() {
        return this.pScore;
    }

}
