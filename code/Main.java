import boggle.BoggleGame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class for the first Assignment in CSC207, Fall 2022
 */
public class Main extends Application {
    /**
    * Main method. 
    * @param args command line arguments.
    **/
//    public static void main(String[] args) {
//        BoggleGame b = new BoggleGame();
//        b.giveInstructions();
//        b.playGame();
//    }
    HomeView homeView;
    BoggleGame boggleGame;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        boggleGame = new BoggleGame();
        homeView = new HomeView(stage);
        HomeViewController homeViewController = new HomeViewController(homeView, new BoggleGame());
        homeView.registerMouseListener(homeViewController);
//        BoardViewController boardController = new BoardViewController()



    }
}
