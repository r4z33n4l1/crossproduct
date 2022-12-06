import boggle.BoggleGame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class for the first Assignment in CSC207, Fall 2022
 */
public class Main {
    /**
     * Main method.
     *
     * @param args command line arguments.
     **/
//    public static void main(String[] args) {
//        BoggleGame b = new BoggleGame();
//        b.giveInstructions();
//        b.playGame();
//    }
    // HomeView homeView;
    // BoggleGame boggleGame;
    public static void main(String[] args) {
        BoggleGame boggleGame = new BoggleGame(BoggleGame.GameModes.PRACTICE, BoggleGame.Themes.POKEMON, 5);
        boggleGame.startGame();
        boggleGame.hMove();
    }
}

//    @Override
//    public void start(Stage stage) throws Exception {
//        boggleGame = new BoggleGame();
//        homeView = new HomeView(stage);
//        HomeViewController homeViewController = new HomeViewController(homeView, boggleGame);
//        homeView.registerMouseListener(homeViewController);}
//
//}