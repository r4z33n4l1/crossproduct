import boggle.BoggleGame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class for the first Assignment in CSC207, Fall 2022
 */
public class Main extends Application {

    /**
     * Main method.
     *
     * @param args command line arguments.
     **/

     HomeView homeView;
     BoggleGame boggleGame;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        homeView = new HomeView(stage);
        HomeViewController homeViewController = new HomeViewController(homeView, new
                BoggleGame(BoggleGame.GameModes.PRACTICE, BoggleGame.Themes.DEFAULT, 4));
        homeView.registerMouseListener(homeViewController);
    }

    /// Terminal Testing (Call Statistics with 11, Hints with 00)
    /// Comment out launch(args) and uncomment the following code to test the terminal version of the game.
    /// You can also change the parameters of the BoggleGame constructor to test different game modes and themes.

//    public static void main(String[] args) {
//        BoggleGame boggleGame = new BoggleGame(BoggleGame.GameModes.MULTIPLAYER, BoggleGame.Themes.DEFAULT, 4);
//        boggleGame.startGame();
//        boggleGame.hMoveTEST();
//    }

}
