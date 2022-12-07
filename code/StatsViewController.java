import boggle.BoggleGame;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class StatsViewController {
    static StatsView statsView;
    public StatsViewController(StatsView statsView) {
        StatsViewController.statsView = statsView;
    }
    public static void switchToHomePage(ActionEvent actionEvent) {
        try {
            HomeView homeView = new HomeView(statsView.stage);
            HomeViewController homeViewController = new HomeViewController(homeView, statsView.boggleGame);
            homeView.registerMouseListener(homeViewController);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
