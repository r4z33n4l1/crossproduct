import boggle.BoggleGame;
import boggle.BoggleGrid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;



public class BoardView {
    BoggleGame boggleGame;
    Stage stage;
    public BoardView(BoggleGame boggleGame, Stage stage){
        this.boggleGame = boggleGame;
        this.stage = stage;
        initUI();
    }
    private void initUI() {
        SplitPane splitPane0 = new SplitPane();
        splitPane0.setPrefHeight(439.0);
        splitPane0.setPrefWidth(625.0);
        splitPane0.setDividerPositions(0.6);
        AnchorPane anchorPane0 = new AnchorPane();
        anchorPane0.setPrefHeight(200.0);
        anchorPane0.setPrefWidth(200.0);
        StackPane stackPane0 = new StackPane();
        stackPane0.setPrefHeight(429.0);
        stackPane0.setPrefWidth(361.0);
        stackPane0.setLayoutX(3.0);
        stackPane0.setLayoutY(4.0);
        stackPane0.setMouseTransparent(true);
        Label boggleboardDisplay = new Label();
        boggleboardDisplay.setFont(Font.font("System", FontWeight.BOLD, 35.0));
        stackPane0.getChildren().add(boggleboardDisplay);
        anchorPane0.getChildren().add(stackPane0);
        splitPane0.getItems().add(anchorPane0);
        AnchorPane anchorPane1 = new AnchorPane();
        anchorPane1.setPrefHeight(437.0);
        anchorPane1.setPrefWidth(275.0);
        Label wordLabel = new Label();
        wordLabel.setLayoutX(18.0);
        wordLabel.setLayoutY(67.0);
        wordLabel.setText("Enter word found below:");
        wordLabel.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        anchorPane1.getChildren().add(wordLabel);
        TextField wordEntered = new TextField();
        wordEntered.setLayoutX(17.0);
        wordEntered.setLayoutY(105.0);
        wordEntered.setPrefHeight(27.0);
        wordEntered.setPrefWidth(211.0);
        anchorPane1.getChildren().add(wordEntered);
        Button submitButton = new Button();
        submitButton.setLayoutX(63.0);
        submitButton.setLayoutY(146.0);
        submitButton.setMnemonicParsing(false);
        submitButton.setPrefHeight(27.0);
        submitButton.setPrefWidth(120.0);
        submitButton.setText("Submit Word");
        anchorPane1.getChildren().add(submitButton);
        Button endButton = new Button();
        endButton.setLayoutX(63.0);
        endButton.setLayoutY(198.0);
        endButton.setMnemonicParsing(false);
        endButton.setPrefHeight(27.0);
        endButton.setPrefWidth(120.0);
        endButton.setText("End Game");
        anchorPane1.getChildren().add(endButton);
        Button backButton = new Button();
        backButton.setLayoutX(63.0);
        backButton.setLayoutY(303.0);
        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(27.0);
        backButton.setPrefWidth(120.0);
        backButton.setText("Back to home");
        anchorPane1.getChildren().add(backButton);
        BoggleGrid boggleGrid = boggleGame.getGrid();
        String boggleBoard = boggleGrid.toString();
        boggleboardDisplay.setText(boggleBoard);
        splitPane0.getItems().add(anchorPane1);
        stage.setTitle("Boggle Board");
        Scene scene = new Scene(splitPane0);
        stage.setScene(scene);
        stage.show();
    }
}
