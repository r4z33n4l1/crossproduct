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

    public static void checkWord(ActionEvent actionEvent) {
        try {

            String isValid = boardView.boggleGame.hMove(boardView.wordEntered.getText().toLowerCase());

            if (isValid.equals("Invalid Word")) {
                boardView.isValidWord.setText("You added an invalid word." +"\n" + "    " +
                        "   Please try again.");
            }
            else if(isValid.equals("Word Already Found")){
                boardView.isValidWord.setText("You already found this word." +"\n" + "    " +
                        "   Please try again.");
            }
            else{
                boardView.isValidWord.setText(isValid);
            }
            boardView.wordLabel.setText(boardView.boggleGame.getDisplayString() + "enter word below:");
            boardView.wordEntered.setText("");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showStatsPage(ActionEvent actionEvent) {
        try {
            StatsView statsView = new StatsView(boardView.stage, boardView.boggleGame);
//            StatsViewController statsViewController = new StatsViewController(statsView);
//            statsView.registerMouseListener(statsViewController);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getHint(ActionEvent actionEvent) {
        try {
            boardView.hintMessage.setText(boardView.boggleGame.hint_generator());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
