import boggle.BoggleGame;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HomeViewController {
    HomeView homeView;
    BoggleGame boggleGame;

    public HomeViewController(HomeView homeView, BoggleGame boggleGame) {
        this.homeView = homeView;
        this.boggleGame = boggleGame;
    }

    public void switchToBoardPage(ActionEvent actionEvent){
        try {
            BoardView boardView = new BoardView(boggleGame, homeView.stage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
