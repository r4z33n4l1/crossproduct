package boggle;

import boggle.stats.BoggleStats;
import themes.*;

import java.util.*;
import java.util.stream.*;


/**
 * The BoggleGame class for the first Assignment in CSC207, Fall 2022
 */
public class BoggleGame {

    /**
     * Scanner
     */
    public Scanner scanner;

    /**
     * Timer
     */
    public Timer timer;

    /**
     * Dices
     */
    public List<String> dices;

    /**
     * Grid
     */
    public BoggleGrid grid;

    /**
     * All words
     */
    public HashMap<String,ArrayList<Position>> allWords;

    /**
     * Word entered by the player
     */
//    public String word;

    /**
     * stores game statistics
     */
    private BoggleStats gameStats;

    /**
     * Game mode
     */
    private GameMode gameMode;

    /**
     * Theme
     */
    private final ThemeDecorator gameTheme;

    public enum GameModes {
        PRACTICE,
        SINGLEPLAYER,
        MULTIPLAYER
    }

    public enum Themes {
        ANIMALS,
        CITIES,
        POKEMON,
        DEFAULT
    }

    /**
     * Carina's hint stuff (don't touch)
     */
    int index_tracker = 0;
    String hint = "";
    String displayed = "";
//    int proper_hint_count = 0;

    int size;

    /**
     * BoggleGame constructor
     * NOTE: make sure to run the start method before beginning the game!!!! Disobey to your disadvantage
     */
    public BoggleGame(GameModes mode, Themes theme, int size) {
        this.scanner = new Scanner(System.in);
        this.gameStats = BoggleStats.getInstance();
        this.gameStats.resetStats();
        this.timer = new Timer();
        this.size = size;
        // GameModes
        if (mode == GameModes.PRACTICE) {
            this.gameMode = new PracticeMode(gameStats);
        } else if (mode == GameModes.SINGLEPLAYER) {
            this.gameMode = new SingleplayerMode(gameStats);
        } else if (mode == GameModes.MULTIPLAYER) {
            this.gameMode = new MultiplayerMode(gameStats);
        }
        // GameThemes
        if (theme == Themes.ANIMALS) {
            this.gameTheme = new animalTheme(size);
        } else if (theme == Themes.POKEMON) {
            this.gameTheme = new pokemonTheme(size);
        } else if (theme == Themes.CITIES) {
            this.gameTheme = new citiesTheme(size);
        } else {
            this.gameTheme = new baseTheme(size);
        }

    }

    public BoggleGrid getGrid() {
        return this.grid;
    }

    /**
     * Begin a new game
     */
    public void startGame() {
        // get the dictionaries and find all words here
        Dictionary boggleDict = new Dictionary(this.gameTheme.getFileName());
        this.allWords = new HashMap<>();
        while (allWords.size() < 5){
            this.allWords = new HashMap<>();
            this.dices = this.gameTheme.getDices();
            this.grid = new BoggleGrid(this.size);
            this.grid.initializeBoard(randomizeLetters(this.size));
            findAllWords(this.allWords, boggleDict, this.grid);
            //System.out.println("All wordsize: " + this.allWords.size());
        }
//            findAllWords(this.allWords, boggleDict, this.grid);
    }

    /**
     * This method runs when a player enters a word
     */
    public String addWord(String s) {
        return this.gameMode.addWord(s, allWords.keySet());
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
        String[] dice = this.dices.toArray(new String[size]);
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
        //System.out.println("All wordsize: " + allWords);
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
                gameStats.setWordsNotFound(word);
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

    public String getHint() {
        // TODO: Carina, over to you :)
        // probably useful stuff
        // hint_generator();
        // System.out.println(this.hint);
        return "hintStuff";
    }

    /*
     * Gets words from the user.  As words are input, check to see that they are valid.
     * If yes, add the word to the player's word list (in boggleStats) and increment
     * the player's score (in boggleStats).
     * End the turn once the user hits return (with no word).
     *
     * @param board - The boggle board
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     */
    private String humanMove(BoggleGrid board, Map<String,ArrayList<Position>> allWords, String word) {

        return this.addWord(word);
    }

    public String hMove(String word) {
        return humanMove(this.grid, this.allWords, word);
    }


    /*
     * Gets words from the user.  As words are input, check to see that they are valid.
     * If yes, add the word to the player's word list (in boggleStats) and increment
     * the player's score (in boggleStats).
     * End the turn once the user hits return (with no word).
     *
     * @param board - The boggle board
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     */

    private void humanMoveTEST(BoggleGrid board, Map<String,ArrayList<Position>> allWords){
        System.out.println("It's your turn to find some words!");
        while(true) {
            System.out.println(board);
            System.out.println(gameStats.getWordsNotFound());
            System.out.println("Enter a word: ");
            String word = scanner.nextLine();
            word = word.toUpperCase();
            if(word.equals("")){
                break;
            }
            if(word.equals("11")){
                gameStats.summarizeRound();
            }
            if(word.equals("00")){
                hint_generator();
            }
            System.out.println(this.addWord(word));
        }
    }

    /*
     * Human Move
     * USED IN TERMINAL TESTING
     */
    public void hMoveTEST() {
        humanMoveTEST(this.grid, this.allWords);
    }

    /**
     * Return end game statistics
     * @return Array -
     * 0: Player1 score
     * 1: Player2 score
     * 2: Number of words player 1 found
     * 3: Number of words player 2 found
     * 4: Number of words not found by either
     * // Not yet implemented:
     * 5: Number of hints used by player 1
     * 6: Number of hints used by player 2
     */
    public String[] getEndGameStats() {
        if (this.gameMode.getGameMode() == GameModes.MULTIPLAYER) {
            return this.gameStats.endGameStats(true);
        }
        return this.gameStats.endGameStats(false);
    }

    /**
     * Get display string
     */
    public String getDisplayString() {
        return this.gameMode.getDisplayString();
    }

    /*
     * A function that somewhat deals with the hints' algorithm
     *  - Only human players can call for hints
     */
    public String hint_generator() {
        System.out.println(gameStats.getWordsNotFound().toString());

        index_tracker ++;

        int hint_limit = 12;


        Set<String> words_not_found = gameStats.getWordsNotFound();
        ArrayList<String> copy_words_not_found = new ArrayList<>(words_not_found);


        // No words available
        if (copy_words_not_found.size() == 0){
            index_tracker = 0;

            int hint_count_display = hint_limit - gameStats.GetHintCount();

            String displayed_counter = "HINTS LEFT: " + (hint_count_display);
            String hint_return = "All words have been found!";



            return hint_return + "\n" + displayed_counter;
        }

        // No hints left
        else if (gameStats.GetHintCount() >= hint_limit && !copy_words_not_found.contains(hint)){

            int hint_count_display = hint_limit - gameStats.GetHintCount();

            String displayed_counter = "HINTS LEFT: " + (hint_count_display);
            String hint_return = "Not so fast! You have used up all your hints!";



            return hint_return + "\n" + displayed_counter;
        }

        // No hints left but still not guessed
        else if (gameStats.GetHintCount() >= hint_limit && copy_words_not_found.contains(hint)){

            int hint_count_display = hint_limit - gameStats.GetHintCount();
            String displayed_counter = "HINTS LEFT: " + (hint_count_display);


            return displayed + "\n" + displayed_counter;
        }

        // Words available; New word is generated; First index returned + underscores
        else if (index_tracker == 1 || (Objects.equals(hint, ""))){
            hint = hint_randomizer(copy_words_not_found);
            gameStats.setHintCount(gameStats.GetHintCount() + 1);

            int hint_count_display = hint_limit - gameStats.GetHintCount();
            String displayed_counter = "HINTS LEFT: " + (hint_count_display);

            String underscores = IntStream.range(0, hint.length() - 1).mapToObj(i ->"_").collect(Collectors.joining(""));
            displayed = "Try looking for a word starting with: " + hint.charAt(0) + underscores;

            return displayed + "\n" + displayed_counter;
        }

        // Words available; Counter within index; Previous word not found yet; Slice returned or first index
        else if (index_tracker <= hint.length() && index_tracker >= 1 && copy_words_not_found.contains(hint)) {
            gameStats.setHintCount(gameStats.GetHintCount() + 1);

            int hint_count_display = hint_limit - gameStats.GetHintCount();
            String displayed_counter = "HINTS LEFT: " + (hint_count_display);

            String underscores = IntStream.range(0, hint.length() - index_tracker).mapToObj(i -> "_").collect(Collectors.joining(""));
            displayed = "Try looking for a word starting with: " + hint.substring(0, index_tracker) + underscores;


            return displayed + "\n" + displayed_counter;
        }

        // They can't guess word after fully displayed
        else if (index_tracker > hint.length() && copy_words_not_found.contains(hint)) {
             displayed = "Find the Word: " + hint;
            int hint_count_display = hint_limit - gameStats.GetHintCount();
            String displayed_counter = "HINTS LEFT: " + (hint_count_display);

            return displayed + "\n" + displayed_counter;
        }

        // Finally found, so reset to first index
        // Words available; Counter within index (full word not revealed); Previous word HAS BEEN FOUND; New word index returned
        else if (index_tracker > 1 && !copy_words_not_found.contains(hint)) {
            hint = hint_randomizer(copy_words_not_found);
            index_tracker = 1;
            gameStats.setHintCount(gameStats.GetHintCount() + 1);
            int hint_count_display = hint_limit - gameStats.GetHintCount();
            String displayed_counter = "HINTS LEFT: " + (hint_count_display);
            String underscores = IntStream.range(0, hint.length() - 1).mapToObj(i ->"_").collect(Collectors.joining(""));
             displayed = "Try looking for a word starting with: " + hint.charAt(0) + underscores;

            return displayed + "\n" + displayed_counter;}

        // temp error catcher
        else {

            return "what happened";}
    }


    /*
     * Helper for hints
     */
    private String hint_randomizer(ArrayList<String> copy_words_not_found) {
        // Set<String> words_not_found = gameStats.getWordsNotFound();
        // ArrayList<String> copy_words_not_found = new ArrayList<String>(words_not_found);
        Random rand = new Random();
        return copy_words_not_found.get(rand.nextInt(copy_words_not_found.size()));}

}