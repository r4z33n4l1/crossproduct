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

    public BoggleGame updatedBoggleGame() {
        String mode_chosen = (String) homeView.mode_mapping.get((homeView.mode_menu.getValue()));
        String theme_chosen = (homeView.theme_menu.getValue()).toUpperCase();
        int board_size = Integer.parseInt(homeView.board_menu.getValue().substring(0, 1));
        return new BoggleGame(BoggleGame.GameModes.valueOf(mode_chosen), BoggleGame.Themes.valueOf(theme_chosen), board_size);

    }

    public void switchToBoardPage(ActionEvent actionEvent){
        try {
            boggleGame = updatedBoggleGame();
            BoardView boardView = new BoardView(homeView, boggleGame);
            BoardViewController boardViewController = new BoardViewController(boardView);
            boggleGame.startGame();
            boardView.registerMouseListener(boardViewController);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
