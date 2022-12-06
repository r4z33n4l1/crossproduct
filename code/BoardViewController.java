import javafx.event.ActionEvent;

public class BoardViewController {
    static BoardView boardView;
    public BoardViewController(BoardView boardView) {
        BoardViewController.boardView = boardView;
    }
    public static void switchToHomePage(ActionEvent actionEvent) {
        try {
            HomeView homeView = new HomeView(boardView.stage);
            HomeViewController homeViewController = new HomeViewController(homeView, boardView.boggleGame);
            homeView.registerMouseListener(homeViewController);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
