package boggle;

import boggle.stats.BoggleStats;

import java.util.*;

/**
 * The BoggleGame class for the first Assignment in CSC207, Fall 2022
 */
public class BoggleGame {

    /**
     * Timer
     */ 
    public Timer timer;
    /**
     * stores game statistics
     */ 
    private final BoggleStats gameStats;

    /**
     * Game mode
     */
    private final GameMode gameMode;

    /**
     * dice used to randomize letter assignments for a small grid
     */ 
    private final String[] dice_small_grid= //dice specifications, for small and large grids
            {"AAEEGN", "ABBJOO", "ACHOPS", "AFFKPS", "AOOTTW", "CIMOTU", "DEILRX", "DELRVY",
                    "DISTTY", "EEGHNW", "EEINSU", "EHRTVW", "EIOSST", "ELRTTY", "HIMNQU", "HLNNRZ"};
    /**
     * dice used to randomize letter assignments for a big grid
     */ 
    private final String[] dice_big_grid =
            {"AAAFRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM", "AEEGMU", "AEGMNN", "AFIRSY",
                    "BJKQXZ", "CCNSTW", "CEIILT", "CEILPT", "CEIPST", "DDLNOR", "DDHNOT", "DHHLOR",
                    "DHLNOR", "EIIITT", "EMOTTT", "ENSSSU", "FIPRSY", "GORRVW", "HIPRRY", "NOOTUW", "OOOTTU"};

    public enum GameModes {
        PRACTICE,
        SINGLEPLAYER,
        MULTIPLAYER
    }

    /* 
     * BoggleGame constructor
     */
    public BoggleGame(GameModes mode) {
        this.gameStats = BoggleStats.getInstance();
        this.timer = new Timer();
        if (mode == GameModes.PRACTICE) {
            this.gameMode = new PracticeMode(gameStats);
        } else if (mode == GameModes.SINGLEPLAYER) {
            this.gameMode = new SingleplayerMode(gameStats);
        } else {
            this.gameMode = new MultiplayerMode(gameStats);
        }
    }

    /**
     * Begin a new game
     */
    public void startGame(int seconds) {
        this.timer.setSeconds(seconds);
        this.timer.start();
        // TODO: get the dictionaries and find all words here
    }

    /**
     * This method runs when a player enters a word
     */
    public String addWord(String s) {
        // TODO: Check if this word is already in the dictionary
        return this.gameMode.addWord(s);
    }

    /*
     * This method should return a String of letters (length 16 or 25 depending on the size of the grid).
     * There will be one letter per grid position, and they will be organized left to right,
     * top to bottom. A strategy to make this string of letters is as follows:
     * -- Assign a one of the dice to each grid position (i.e. dice_big_grid or dice_small_grid)
     * -- "Shuffle" the positions of the dice to randomize the grid positions they are assigned to
     * -- Randomly select one of the letters on the given die at each grid position to determine
     *    the letter at the given position
     *
     * @return String a String of random letters (length 16 or 25 depending on the size of the grid)
     */
    private String randomizeLetters(int size) {
        StringBuilder letters = new StringBuilder();
        String[] dice;
        if (size == 5) {
            dice = dice_big_grid;
        } else {
            dice = dice_small_grid;
        }
        Random random = new Random();
        Collections.shuffle(Arrays.asList(dice));
        for(String letter: dice){
            letters.append(letter.charAt(random.nextInt(0, letter.length())));
        }
        return letters.toString();
    }


    /* 
     * This should be a recursive function that finds all valid words on the boggle board.
     * Every word should be valid (i.e. in the boggleDict) and of length 4 or more.
     * Words that are found should be entered into the allWords HashMap.  This HashMap
     * will be consulted as we play the game.
     *
     * Note that this function will be a recursive function.  You may want to write
     * a wrapper for your recursion. Note that every legal word on the Boggle grid will correspond to
     * a list of grid positions on the board, and that the Position class can be used to represent these
     * positions. The strategy you will likely want to use when you write your recursion is as follows:
     * -- At every Position on the grid:
     * ---- add the Position of that point to a list of stored positions
     * ---- if your list of stored positions is >= 4, add the corresponding word to the allWords Map
     * ---- recursively search for valid, adjacent grid Positions to add to your list of stored positions.
     * ---- Note that a valid Position to add to your list will be one that is either horizontal, diagonal, or
     *      vertically touching the current Position
     * ---- Note also that a valid Position to add to your list will be one that, in conjunction with those
     *      Positions that precede it, form a legal PREFIX to a word in the Dictionary (this is important!)
     * ---- Use the "isPrefix" method in the Dictionary class to help you out here!!
     * ---- Positions that already exist in your list of stored positions will also be invalid.
     * ---- You'll be finished when you have checked EVERY possible list of Positions on the board, to see
     *      if they can be used to form a valid word in the dictionary.
     * ---- Food for thought: If there are N Positions on the grid, how many possible lists of positions
     *      might we need to evaluate?
     *
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     * @param boggleDict A dictionary of legal words
     * @param boggleGrid A boggle grid, with a letter at each position on the grid
     */
    private void findAllWords(Map<String,ArrayList<Position>> allWords, Dictionary boggleDict, BoggleGrid boggleGrid) {
        boolean[][] visited = new boolean[boggleGrid.numRows()][boggleGrid.numCols()];
        for (int i = 0; i < boggleGrid.numRows(); i++) {
            for (int j = 0; j < boggleGrid.numCols(); j++) {
                ArrayList<Position> position_list = new ArrayList<Position>();
                position_list.add(new Position(i, j));
                String current_word = "";
                WordsHelper(allWords, boggleDict, boggleGrid, position_list, i, j, current_word, visited);
            }
        }
    }
    /*
     * A recursive helper method that given indexes i,j finds the neighbouring positions
     * to [i, j] and checks recursively to see if the word being formed is valid and not repeated
     * @param list_of_words A mutable list of all legal words that can be found, given the boggleGrid grid letters
     * @param boggleDict A dictionary of legal words
     * @param boggleGrid A boggle grid, with a letter at each position on the grid
     * @param position_list Mutable list that keeps track of positions of every letter for the word being formed
     *  from the boggle grid.
     * @param i Integer that keeps track of the current row position
     * @param j Integer that keeps track of the current col position
     * @param current_word A string that keeps track of the current word formed
     * @param visited A 2-dimensional boolean array with same size as the boggle grid that keeps track of whether
     * a position has already been visited
     */
    private void WordsHelper(Map<String, ArrayList<Position>> list_of_words,
                                    Dictionary boggleDict, BoggleGrid boggleGrid, ArrayList<Position> position_list,
                             int i, int j, String current_word, boolean[][] visited) {
        // Make current position as visited so as not to use the letter at that position again when forming a word
        visited[i][j] = true;
        String word = current_word + boggleGrid.getCharAt(i, j);
        // if word formed is valid and in the dictionary, add it to list of legal words found
        if (position_list.size() >= 4) {
            if (boggleDict.containsWord(word)) {
                list_of_words.put(word, position_list);
            }
        }
        // if word is a prefix, check neighbouring values and find valid words recursively
        if (boggleDict.isPrefix(word)) {
            for (int row = i - 1; row <= i + 1; row++) {
                for (int col = j - 1; col <= j + 1; col++) {
                    if (row >= 0 && row < boggleGrid.numRows()
                            && col >= 0 && col < boggleGrid.numCols() && !visited[row][col]) {
                        ArrayList<Position> new_position_list = new ArrayList<Position>(position_list);
                        new_position_list.add(new Position(row, col));
                        WordsHelper(list_of_words, boggleDict, boggleGrid, new_position_list, row, col, word, visited);
                    }
                }
            }
        }
        // change the current position back to not visited so that the
        // letter at that position can be used for other words
        visited[i][j] = false;
    }
}
