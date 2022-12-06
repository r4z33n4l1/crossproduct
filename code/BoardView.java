import boggle.BoggleGame;
import boggle.BoggleGrid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class BoardView {
    BoggleGame boggleGame;
    Stage stage;

    HomeView homeView;

    private Button endButton;

    private Button submitButton;

    private Button backButton;

    Label isValidWord;

    TextField wordEntered;
    public BoardView(HomeView view, BoggleGame boggleGame){
        this.boggleGame = boggleGame;
        this.homeView = view;
        this.stage = view.stage;

        initUI();
    }
    private void initUI() {
//        ArrayList<String> a = (homeView.color_mapping.get((homeView.board_colors.getValue())));
        // convert a to array of strings

        Object a = homeView.color_mapping.get((homeView.board_colors.getValue()));
        List<String> b = Arrays.asList(a.toString().split(","));
        // remove the brackets
        b.set(0, b.get(0).substring(1));
        b.set(b.size()-1, b.get(b.size()-1).substring(0, b.get(b.size()-1).length()-1));

//        b.set(b.size()-2, b.get(b.size()-2).substring(0, b.get(b.size()-2).length()-1));
        String background_color = b.get(0);
        String text_color = b.get(1);
        String button_color = b.get(2);
//        String button_color = b.get(2);
//        System.out.println(button_color);

        SplitPane splitPane0 = new SplitPane();
        splitPane0.setPrefHeight(439.0);
        splitPane0.setPrefWidth(625.0);
        splitPane0.setDividerPositions(0.6);
        //make the divider transparent
        AnchorPane anchorPane0 = new AnchorPane();
        anchorPane0.setPrefHeight(200.0);
        anchorPane0.setPrefWidth(200.0);
        anchorPane0.setStyle("-fx-background-color: " + background_color + ";");
        StackPane stackPane0 = new StackPane();
        stackPane0.setPrefHeight(429.0);
        stackPane0.setPrefWidth(361.0);
        stackPane0.setLayoutX(3.0);
        stackPane0.setLayoutY(4.0);
        stackPane0.setMouseTransparent(true);
        Label boggleboardDisplay = new Label();
        boggleboardDisplay.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        boggleboardDisplay.setFont(Font.font("System", FontWeight.BOLD, 35.0)); // carina fonts
        stackPane0.getChildren().add(boggleboardDisplay);
        anchorPane0.getChildren().add(stackPane0);
        splitPane0.getItems().add(anchorPane0);
        AnchorPane anchorPane1 = new AnchorPane();
        anchorPane1.setPrefHeight(437.0);
        anchorPane1.setPrefWidth(275.0);
        anchorPane1.setStyle("-fx-background-color: " + background_color + ";");
        Label wordLabel = new Label();
        wordLabel.setLayoutX(17.0);
        wordLabel.setLayoutY(14.0);
        wordLabel.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        wordLabel.setText("Enter word found below:");
        wordLabel.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        anchorPane1.getChildren().add(wordLabel);
        TextField wordEntered = new TextField();
        wordEntered.setLayoutX(17.0);
        wordEntered.setLayoutY(59.0);
        wordEntered.setPrefHeight(27.0);
        wordEntered.setPrefWidth(211.0);
        anchorPane1.getChildren().add(wordEntered);
        Button submitButton = new Button();
        submitButton.setLayoutX(63.0);
        submitButton.setLayoutY(101.0);
        submitButton.setMnemonicParsing(false);
        submitButton.setPrefHeight(27.0);
        submitButton.setPrefWidth(120.0);
        submitButton.setText("Submit Word");
        submitButton.setStyle("-fx-background-color: " + button_color + "; -fx-text-fill: " + text_color + "; " +
                "-fx-border-color: " + text_color +";");
        anchorPane1.getChildren().add(submitButton);
        Button endButton = new Button();
        endButton.setLayoutX(63.0);
        endButton.setLayoutY(271.0);
        endButton.setMnemonicParsing(false);
        endButton.setPrefHeight(27.0);
        endButton.setPrefWidth(120.0);
        endButton.setStyle("-fx-background-color: " + button_color + "; -fx-text-fill: " + text_color + "; " +
                "-fx-border-color: " + text_color +";");
        endButton.setText("End Game");

        anchorPane1.getChildren().add(endButton);
        Button backButton = new Button();
        backButton.setLayoutX(63.0);
        backButton.setLayoutY(363.0);
        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(27.0);
        backButton.setPrefWidth(120.0);
        backButton.setText("Back to home");
        backButton.setStyle("-fx-background-color: " + button_color + "; -fx-text-fill: " + text_color + "; " +
                "-fx-border-color: " + text_color +";");
        anchorPane1.getChildren().add(backButton);
        StackPane stackforisvalid = new StackPane();
        stackforisvalid.setLayoutX(45.0);
        stackforisvalid.setLayoutY(142.0);
        stackforisvalid.setPrefHeight(35.0);
        stackforisvalid.setPrefWidth(155.0);
        Label isValidWord = new Label();
//        isValidWord.setLayoutX(108.0);
//        isValidWord.setLayoutY(156.0);
        isValidWord.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        stackforisvalid.getChildren().add(isValidWord);
        anchorPane1.getChildren().add(stackforisvalid);
//        anchorPane1.getChildren().add(isValidWord);
        Button hintButton = new Button();
        hintButton.setLayoutX(63.0);
        hintButton.setLayoutY(189.0);
        hintButton.setMnemonicParsing(false);
        hintButton.setPrefHeight(27.0);
        hintButton.setPrefWidth(120.0);
        hintButton.setText("Get Hint");
        hintButton.setStyle("-fx-background-color: " + button_color + "; -fx-text-fill: " + text_color + "; " +
                "-fx-border-color: " + text_color +";");
        anchorPane1.getChildren().add(hintButton);
        StackPane stackforhint = new StackPane();
        stackforhint.setLayoutX(44.0);
        stackforhint.setLayoutY(226.0);
        stackforhint.setPrefHeight(17.0);
        stackforhint.setPrefWidth(30.0);
        Label hintMessage = new Label();
//        hintMessage.setLayoutX(109.0);
//        hintMessage.setLayoutY(237.0);
        hintMessage.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        stackforhint.getChildren().add(hintMessage);
        anchorPane1.getChildren().add(stackforhint);
        BoggleGrid boggleGrid = boggleGame.getGrid();
        String boggleBoard = boggleGrid.toString();
        boggleboardDisplay.setText(boggleBoard);
        splitPane0.getItems().add(anchorPane1);
        stage.setTitle("Boggle Board");
        Scene scene = new Scene(splitPane0);
        stage.setScene(scene);
        stage.show();

        this.backButton = backButton;
        this.submitButton = submitButton;
        // map submitbutton to key F and backbutton to key B
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.F1) {
                    submitButton.fire();
                }
                if (event.getCode() == KeyCode.F2) {
                    backButton.fire();
                }
            }
        });
        this.endButton = endButton;
        this.wordEntered = wordEntered;
        this.isValidWord = isValidWord;
    }
    public void registerMouseListener(BoardViewController boardController) {
//        endButton.setOnAction(BoardViewController::showStatsPage);
        submitButton.setOnAction(BoardViewController::checkWord);
        backButton.setOnAction(BoardViewController::switchToHomePage);

    }
    public void registerKeyListener(BoardViewController boardController) {
        submitButton.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                submitButton.fire();
            }
        });
    }
}
