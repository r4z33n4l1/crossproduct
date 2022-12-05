package boggle;

import boggle.stats.BoggleStats;

import java.util.*;


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
     * stores game statistics
     */ 
    private BoggleStats gameStats;

    /**
     * Game mode
     */
    private GameMode gameMode;

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

    /**
     * Carina's hint stuff (don't touch)
     */
    int inner_counter = 0;
    String hint = "";
    // int hint_len = 0;
    int outer_counter = 0;

    /**
     * BoggleGame constructor
     */
    public BoggleGame(GameModes mode) {
        this.scanner = new Scanner(System.in);
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

    public void playGame() {
        int boardSize;
        while(true){
            System.out.println("Enter 1 to play on a big (5x5) grid; 2 to play on a small (4x4) one:");
            String choiceGrid = scanner.nextLine();

            //get grid size preference
            if(Objects.equals(choiceGrid, "")) break; //end game if user inputs nothing
            while(!choiceGrid.equals("1") && !choiceGrid.equals("2")){
                System.out.println("Please try again.");
                System.out.println("Enter 1 to play on a big (5x5) grid; 2 to play on a small (4x4) one:");
                choiceGrid = scanner.nextLine();
            }

            if(choiceGrid.equals("1")) boardSize = 5;
            else boardSize = 4;

            //get letter choice preference
            System.out.println("Enter 1 to randomly assign letters to the grid; 2 to provide your own.");
            String choiceLetters = scanner.nextLine();

            if(Objects.equals(choiceLetters, "")) break; //end game if user inputs nothing
            while(!choiceLetters.equals("1") && !choiceLetters.equals("2")){
                System.out.println("Please try again.");
                System.out.println("Enter 1 to randomly assign letters to the grid; 2 to provide your own.");
                choiceLetters = scanner.nextLine();
            }

            if(choiceLetters.equals("1")){
                //playRound(boardSize,randomizeLetters(boardSize));
            } else {
                System.out.println("Input a list of " + boardSize*boardSize + " letters:");
                choiceLetters = scanner.nextLine();
                while(!(choiceLetters.length() == boardSize*boardSize)){
                    System.out.println("Sorry, bad input. Please try again.");
                    System.out.println("Input a list of " + boardSize*boardSize + " letters:");
                    choiceLetters = scanner.nextLine();
                }
                //playRound(boardSize,choiceLetters.toUpperCase());
            }

            // round is over! So, store the statistics, and end the round.
            // this.gameStats.summarizeRound();
            // this.gameStats.endRound();

            //Shall we repeat?
            System.out.println("Play again? Type 'Y' or 'N'");
            String choiceRepeat = scanner.nextLine().toUpperCase();

            if(choiceRepeat.equals("")) break; //end game if user inputs nothing
            while(!choiceRepeat.equals("Y") && !choiceRepeat.equals("N")){
                System.out.println("Please try again.");
                System.out.println("Play again? Type 'Y' or 'N'");
                choiceRepeat = scanner.nextLine().toUpperCase();
            }

            if(choiceRepeat.equals("N")) break; //end game if user inputs nothing


        }
    }

    public BoggleGrid getGrid() {
        BoggleGrid grid = new BoggleGrid(4);
        grid.initializeBoard(randomizeLetters(4));
        return grid;
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

        //step 1. initialize the grid
        //BoggleGrid grid = new BoggleGrid(size);
        //grid.initializeBoard(letters);
        //step 2. initialize the dictionary of legal words
        //Dictionary boggleDict = new Dictionary("dictionaries/wordlist.txt"); //you may have to change the path to the wordlist, depending on where you place it.
        //step 3. find all legal words on the board, given the dictionary and grid arrangement.
        //Map<String, ArrayList<Position>> allWords = new HashMap<String, ArrayList<Position>>();
        //findAllWords(allWords, boggleDict, grid);
        //step 4. allow the user to try to find some words on the grid
        //humanMove(grid, allWords);
        //step 5. allow the computer to identify remaining words
        // computerMove(allWords);

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

    /*
     * Gets words from the user.  As words are input, check to see that they are valid.
     * If yes, add the word to the player's word list (in boggleStats) and increment
     * the player's score (in boggleStats).
     * End the turn once the user hits return (with no word).
     *
     * @param board - The boggle board
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     */
    private void humanMove(BoggleGrid board, Map<String,ArrayList<Position>> allWords){
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
            if(word.equals("00")){
                hint_generator();
                System.out.println(this.hint);
            }
            if(allWords.containsKey(word) && gameStats.getPlayerWords(BoggleStats.Player.Player1).contains(word)){
                System.out.println("Word already in player list");
            }
            if(allWords.containsKey(word) && !gameStats.getPlayerWords(BoggleStats.Player.Player1).contains(word)){
                System.out.println("Word added");
                gameStats.addWord(word, BoggleStats.Player.Player1);
            }
            else{
                System.out.println("Not a valid word. Please try again");
            }


            //You write code here!
            //step 1. Print the board for the user, so they can scan it for words
            //step 2. Get a input (a word) from the user via the console
            //step 3. Check to see if it is valid (note validity checks should be case-insensitive)
            //step 4. If it's valid, update the player's word list and score (stored in boggleStats)
            //step 5. Repeat step 1 - 4
            //step 6. End when the player hits return (with no word choice).

        }
    }


    /*
     * Gets words from the computer.  The computer should find words that are
     * both valid and not in the player's word list.  For each word that the computer
     * finds, update the computer's word list and increment the
     * computer's score (stored in boggleStats).
     *
     * @param allWords A mutable list of all legal words that can be found, given the boggleGrid grid letters
     */
//    private void computerMove(Map<String,ArrayList<Position>> all_words){
//        System.out.println("Now, the computer will find words!");
//        for(String word: all_words.keySet()){
//            if(!gameStats.getPlayerWords().contains(word)){
//                gameStats.addWord(word, BoggleStats.Player.Computer);
//            }
//        }
//    }



    /*
     * A function that somewhat deals with the hints' algorithm
     *  - Only human players can call for hints
     */
    public void hint_generator() {

        /// Different modes? How to differentiate which player ran out of hints. confusion
        /// Does it reset every new game -> (outer loop doesn't reset if u keep playing again rn)
        /// Atm the algorithm is global cause both players are using the same screen: need to decide on hint limit per player or global game

        inner_counter ++;

        Set<String> words_not_found = gameStats.getWordsNotFound();
        ArrayList<String> copy_words_not_found = new ArrayList<>(words_not_found);

        // No words available, return
        if (copy_words_not_found.size() == 0){
            inner_counter = 0;
            System.out.println("All words have been found!");}

        // No hints left
        else if (outer_counter >= 3 && !copy_words_not_found.contains(hint)){
            System.out.println("Not so fast! You ran out of hints :(");}

        // Words available; New word is generated; First index returned
        else if (inner_counter == 1 || (Objects.equals(hint, ""))){
            hint = hint_randomizer(copy_words_not_found);
            System.out.println("Try looking for a word starting with: " + hint.charAt(0));}

        // Words available; Counter within index (full word not revealed); Previous word not found yet; Slice returned
        else if (inner_counter < hint.length() && inner_counter > 1 && copy_words_not_found.contains(hint)) {
            System.out.println("Try looking for a word starting with: " + hint.substring(0, inner_counter));}

        // ^ Same thing, but full word revealed now
        else if (inner_counter == hint.length() && inner_counter > 1 && copy_words_not_found.contains(hint)) {
            outer_counter ++;
//            inner_counter = 0;
            int left = 3 - outer_counter;
            System.out.println("You have " + left + " reveal(s) left. Use them wisely! \n Find the word: " + hint);}

        // ^ Same thing, if still not found (looping heh)
        else if (inner_counter > hint.length() && copy_words_not_found.contains(hint)) {
//            inner_counter = 0;
            int left = 3 - outer_counter;
            System.out.println("You have " + left + " reveal(s) left. Use them wisely! \n Find the word: " + hint);}

        // Finally found, so reset to first index
        else if (inner_counter >= hint.length() && !copy_words_not_found.contains(hint)) {
//            inner_counter = 0;
            hint = hint_randomizer(copy_words_not_found);
            inner_counter = 1;
            System.out.println("Try looking for a word starting with: " + hint.charAt(0));}

        // Words available; Counter within index (full word not revealed); Previous word HAS BEEN FOUND; New word index returned
        else if (inner_counter < hint.length() && inner_counter > 1 && !copy_words_not_found.contains(hint)) {
            hint = hint_randomizer(copy_words_not_found);
            inner_counter = 1;
            System.out.println("Try looking for a word starting with: " + hint.charAt(0));}

        // temp error catcher
        else {
            System.out.println("what happened");}

//        for (String word: allWords.keySet()){
//            System.out.println(word);}

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


