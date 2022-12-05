import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import boggle.*;
import boggle.Dictionary;
import boggle.stats.BoggleStats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoggleTests {

    //BoggleGame  Test
    @Test
    void findAllWords_small() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BoggleGame game = new BoggleGame();
        Method method = game.getClass().getDeclaredMethod("findAllWords", Map.class, Dictionary.class, BoggleGrid.class);
        method.setAccessible(true);

        Dictionary boggleDict = new Dictionary("dictionaries/wordlist.txt");
        Map<String, ArrayList<Position>> allWords = new HashMap<>();
        BoggleGrid grid = new BoggleGrid(4);
        grid.initializeBoard("RHLDNHTGIPHSNMJO");
        Object r = method.invoke(game, allWords, boggleDict, grid);
        Set<String> expected = new HashSet<>(Arrays.asList("GHOST", "HOST", "THIN"));
        assertEquals(expected, allWords.keySet());
    }

    @Test
    void hints() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BoggleGame game = new BoggleGame();
        Method method = game.getClass().getDeclaredMethod("findAllWords", Map.class, Dictionary.class, BoggleGrid.class);
        method.setAccessible(true);

        Dictionary boggleDict = new Dictionary("dictionaries/wordlist.txt");
        Map<String, ArrayList<Position>> allWords = new HashMap<>();
        BoggleGrid grid = new BoggleGrid(4);
        grid.initializeBoard("RHLDNHTGIPHSNMJO");
        Object r = method.invoke(game, allWords, boggleDict, grid);

        game.playRound(4, "RHLDNHTGIPHSNMJO");

//        game.
//        game.hint_generator(allWords, boggleDict, grid);
//        Set<String> expected = new HashSet<>(Arrays.asList("GHOST", "HOST", "THIN"));

    }

    //Dictionary Test
    @Test
    void containsWord() {
        Dictionary dict = new Dictionary("dictionaries/wordlist.txt");
        assertTrue(dict.containsWord("EnZyMe"));
        assertTrue(dict.isPrefix(""));

    }

    //BoggleGrid Test
    @Test
    void setupBoard() {
        BoggleGrid grid = new BoggleGrid(10);
        String letters = "";
        for (int i = 0; i < 10; i++) {
            letters = letters + "0123456789";
        }

        grid.initializeBoard(letters);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(letters.charAt(i * 10 + j), grid.getCharAt(i, j));
            }
        }
    }

    //BoggleStats Test
    @Test
    void endRoundTest() {
        BoggleStats stats = new BoggleStats();
        stats.endRound();
        stats.endRound();
        stats.endRound();
        assertEquals(3, stats.getRound());
    }

}
