import boggle.BoggleGame;
import boggle.BoggleGrid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    private Button hintButton;

    Label hintMessage;


    Label wordLabel;

    Label isValidWord;

    Font custom_font2 = Font.loadFont(new FileInputStream("fonts/Boxed In Regular.ttf"), 30);

    TextField wordEntered;
    public BoardView(HomeView view, BoggleGame boggleGame) throws FileNotFoundException {
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
        splitPane0.setPrefHeight(519.0);
        splitPane0.setPrefWidth(745.0);
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
        //boggleboardDisplay.setFont(Font.font("System", FontWeight.BOLD, 35.0));
        boggleboardDisplay.setFont(custom_font2);

        stackPane0.getChildren().add(boggleboardDisplay);
        anchorPane0.getChildren().add(stackPane0);
        splitPane0.getItems().add(anchorPane0);
        AnchorPane anchorPane1 = new AnchorPane();
        anchorPane1.setPrefHeight(467.0);
        anchorPane1.setPrefWidth(271.0);
        anchorPane1.setStyle("-fx-background-color: " + background_color + ";");
        Label wordLabel = new Label();
        wordLabel.setLayoutX(26.0);
        wordLabel.setLayoutY(14.0);
        wordLabel.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        wordLabel.setText(boggleGame.getDisplayString()+ "-- enter word below ↓");
        wordLabel.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        anchorPane1.getChildren().add(wordLabel);
        TextField wordEntered = new TextField();
        //Allow only letters to be entered
        wordEntered.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!event.getCharacter().matches("[a-zA-Z]")) {
                    event.consume();
                }
            }
        });
        wordEntered.setLayoutX(25.0);
        wordEntered.setLayoutY(50.0);
        wordEntered.setPrefHeight(27.0);
        wordEntered.setPrefWidth(211.0);
        anchorPane1.getChildren().add(wordEntered);
        Button submitButton = new Button();
        submitButton.setLayoutX(63.0);
        submitButton.setLayoutY(98.0);
        submitButton.setMnemonicParsing(false);
        submitButton.setPrefHeight(27.0);
        submitButton.setPrefWidth(120.0);
        submitButton.setText("Submit Word");
        submitButton.setStyle("-fx-background-color: " + button_color + "; -fx-text-fill: " + text_color + "; " +
                "-fx-border-color: " + text_color +";");
        anchorPane1.getChildren().add(submitButton);
        Button endButton = new Button();
        endButton.setLayoutX(22.0);
        endButton.setLayoutY(298.0);
        endButton.setMnemonicParsing(false);
        endButton.setPrefHeight(27.0);
        endButton.setPrefWidth(91.0);
        endButton.setStyle("-fx-background-color: " + button_color + "; -fx-text-fill: " + text_color + "; " +
                "-fx-border-color: " + text_color +";");
        endButton.setText("End Game");

        anchorPane1.getChildren().add(endButton);
        Button backButton = new Button();
        backButton.setLayoutX(143.0);
        backButton.setLayoutY(298.0);
        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(27.0);
        backButton.setPrefWidth(91.0);
        backButton.setText("Home");
        backButton.setStyle("-fx-background-color: " + button_color + "; -fx-text-fill: " + text_color + "; " +
                "-fx-border-color: " + text_color +";");
        anchorPane1.getChildren().add(backButton);
        StackPane stackforisvalid = new StackPane();
        stackforisvalid.setLayoutX(45.0);
        stackforisvalid.setLayoutY(142.0);
        stackforisvalid.setPrefHeight(35.0);
        stackforisvalid.setPrefWidth(155.0);
        Label isValidWord = new Label();
        isValidWord.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        stackforisvalid.getChildren().add(isValidWord);
        anchorPane1.getChildren().add(stackforisvalid);
        Button hintButton = new Button();
        hintButton.setLayoutX(63.0);
        hintButton.setLayoutY(189.0);
        hintButton.setMnemonicParsing(false);
        hintButton.setPrefHeight(27.0);
        hintButton.setPrefWidth(120.0);
        hintButton.setText("Hint");
        hintButton.setStyle("-fx-background-color: " + button_color + "; -fx-text-fill: " + text_color + "; " +
                "-fx-border-color: " + text_color +";");
        anchorPane1.getChildren().add(hintButton);
//        StackPane stackforhint = new StackPane();
//        stackforhint.setLayoutX(3.0);
//        stackforhint.setLayoutY(223.0);
//        stackforhint.setPrefHeight(67.0);
//        stackforhint.setPrefWidth(253.0);
        Label hintMessage = new Label();
        hintMessage.setPrefWidth(240.0);
        hintMessage.setPrefHeight(52.0);
        hintMessage.setLayoutX(11.0);
        hintMessage.setLayoutY(226.0);
//        hintMessage.setLayoutX(109.0);
//        hintMessage.setLayoutY(237.0);
        hintMessage.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
//        stackforhint.getChildren().add(hintMessage);
        anchorPane1.getChildren().add(hintMessage);
        Label Keyboard_mapping = new Label();
        Keyboard_mapping.setLayoutX(68.0);
        Keyboard_mapping.setLayoutY(346.0);
        Keyboard_mapping.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        Keyboard_mapping.setText("Keyboard Mapping");
        Keyboard_mapping.setFont(Font.font("System", FontWeight.BOLD, 14.0));
        Keyboard_mapping.setPrefHeight(20.0);
        Keyboard_mapping.setPrefWidth(135.0);
        anchorPane1.getChildren().add(Keyboard_mapping);
        Label submit_mapping = new Label();
        submit_mapping.setLayoutX(69.0);
        submit_mapping.setLayoutY(379.0);
        submit_mapping.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        submit_mapping.setText("Ctrl+1: Submit Word");
        submit_mapping.setPrefHeight(17.0);
        submit_mapping.setPrefWidth(120.0);
        anchorPane1.getChildren().add(submit_mapping);
        Label hint_mapping = new Label();
        hint_mapping.setLayoutX(68.0);
        hint_mapping.setLayoutY(404.0);
        hint_mapping.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        hint_mapping.setText("Ctrl+2: Get Hint");
        hint_mapping.setPrefHeight(17.0);
        hint_mapping.setPrefWidth(135.0);
        anchorPane1.getChildren().add(hint_mapping);
        Label end_mapping = new Label();
        end_mapping.setLayoutX(69.0);
        end_mapping.setLayoutY(431.0);
        end_mapping.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        end_mapping.setText("Ctrl+3: End Game");
        end_mapping.setPrefHeight(17.0);
        end_mapping.setPrefWidth(120.0);
        anchorPane1.getChildren().add(end_mapping);
        Label back_mapping = new Label();
        back_mapping.setLayoutX(70.0);
        back_mapping.setLayoutY(458.0);
        back_mapping.setStyle("-fx-background-color: " + background_color + "; -fx-text-fill: " + text_color + ";");
        back_mapping.setText("Ctrl+4: Back to Home");
        back_mapping.setPrefHeight(17.0);
        back_mapping.setPrefWidth(120.0);
        anchorPane1.getChildren().add(back_mapping);
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
        submitButton.setMnemonicParsing(true);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.CONTROL_DOWN), submitButton::fire);
        backButton.setMnemonicParsing(true);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.DIGIT4, KeyCombination.CONTROL_DOWN), backButton::fire);
        endButton.setMnemonicParsing(true);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.DIGIT3, KeyCombination.CONTROL_DOWN), endButton::fire);
        this.endButton = endButton;
        this.wordEntered = wordEntered;
        this.isValidWord = isValidWord;
        this.wordLabel = wordLabel;
        this.hintButton = hintButton;
        this.hintMessage = hintMessage;
    }
    public void registerMouseListener(BoardViewController boardController) {
        endButton.setOnAction(BoardViewController::showStatsPage);
        submitButton.setOnAction(BoardViewController::checkWord);
        backButton.setOnAction(BoardViewController::switchToHomePage);
        hintButton.setOnAction(BoardViewController::getHint);

    }
}
