import boggle.BoggleGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;

public class HomeView{
    BoggleGame boggleGame;
    Stage stage;

    private AnchorPane root;
    private ComboBox<String> board_menu;
    private ComboBox<String> board_colors;
    private ComboBox<String> mode_menu;
    private ComboBox<String> theme_menu;
    private Slider time_slider;
    private Button start_button;
    public HomeView(Stage stage) throws IOException {
//        this.boggleGame = boggleGame;
        this.stage = stage;
        initUI();
    }
    ObservableList<String> theme_options =
        FXCollections.observableArrayList(
            "Default",
            "Pokemon",
            "Animals",
                "Cities"
        );
    ObservableList<String> board_options =
        FXCollections.observableArrayList(
            "4x4",
            "5x5",
            "6x6",
            "7x7"
        );
    ObservableList<String> colors_list =
        FXCollections.observableArrayList(
            "Default",
            "Dark mode",
            "Dark Blue",
            "Light Blue"
        );
    ObservableList<String> modes_list =
        FXCollections.observableArrayList(
            "Practice",
            "Player vs Player",
            "Player vs Computer"
        );

    private void initUI() {

        AnchorPane anchorPane0 = new AnchorPane();
        anchorPane0.setPrefHeight(508.0);
        anchorPane0.setPrefWidth(645.0);
        Label themeChoice = new Label();
        themeChoice.setLayoutX(107.0);
        themeChoice.setFont(Font.font("Verdana", FontWeight.BOLD, 18.0));
        themeChoice.setLayoutY(73.0);
        themeChoice.setText("Choose your Theme:");

// Adding child to parent
        anchorPane0.getChildren().add(themeChoice);
        Label boardChoice = new Label();
        boardChoice.setLayoutX(106.0);
        boardChoice.setLayoutY(129.0);
        boardChoice.setText("Choose your Board Size:");

// Adding child to parent
        anchorPane0.getChildren().add(boardChoice);
        Label modeChoice = new Label();
        modeChoice.setLayoutX(107.0);
        modeChoice.setLayoutY(240.0);
        modeChoice.setText("Choose your Mode to Play:");

// Adding child to parent
        anchorPane0.getChildren().add(modeChoice);
        Label timer = new Label();
        timer.setLayoutX(187.0);
        timer.setLayoutY(307.0);
        timer.setText("Choose Time per Player Chance:");

// Adding child to parent
        anchorPane0.getChildren().add(timer);
        ComboBox <String> theme_menu = new ComboBox<>();
        theme_menu.setPrefWidth(150.0);
        theme_menu.setLayoutX(405.0);
        theme_menu.setLayoutY(74.0);

// Adding child to parent
        anchorPane0.getChildren().add(theme_menu);
        ComboBox <String> board_menu = new ComboBox<>();
        board_menu.setPrefWidth(150.0);
        board_menu.setLayoutX(405.0);
        board_menu.setLayoutY(130.0);

// Adding child to parent
        anchorPane0.getChildren().add(board_menu);
        ComboBox <String> mode_menu = new ComboBox<>();
        mode_menu.setPrefWidth(150.0);
        mode_menu.setLayoutX(405.0);
        mode_menu.setLayoutY(241.0);

// Adding child to parent
        anchorPane0.getChildren().add(mode_menu);
        Slider timeSlider = new Slider();
        timeSlider.setPrefHeight(14.0);
        timeSlider.setMax(90.0);
        timeSlider.setPrefWidth(405.0);
        timeSlider.setShowTickLabels(true);
        timeSlider.setLayoutX(119.0);
        timeSlider.setLayoutY(358.0);
        timeSlider.setValue(45.0);

// Adding child to parent
        anchorPane0.getChildren().add(timeSlider);
        Button startButton = new Button();
        startButton.setPrefHeight(25.0);
//        button9.setOnAction(#switchToBoardPage);
        startButton.setPrefWidth(102.0);
        startButton.setLayoutX(271.0);
        startButton.setText("Start Game");
        startButton.setLayoutY(403.0);
        startButton.setMnemonicParsing(false);

// Adding child to parent
        anchorPane0.getChildren().add(startButton);
        Label boardColor = new Label();
        boardColor.setLayoutX(106.0);
        boardColor.setLayoutY(185.0);
        boardColor.setText("Choose your Board Color:");

// Adding child to parent
        anchorPane0.getChildren().add(boardColor);
        ComboBox<String> board_colors = new ComboBox<>();
        board_colors.setPrefWidth(150.0);
        board_colors.setLayoutX(405.0);
        board_colors.setLayoutY(187.0);
        board_menu.setItems(board_options);
        board_menu.setValue("4x4");
        board_colors.setItems(colors_list);
        board_colors.setValue("Default");
        mode_menu.setItems(modes_list);
        mode_menu.setValue("Practice");
        theme_menu.setItems(theme_options);
        theme_menu.setValue("Default");
        anchorPane0.getChildren().add(board_colors);
        for (int i = 0; i < anchorPane0.getChildren().size(); i++) {
            if(anchorPane0.getChildren().get(i) instanceof Label label) {
                label.setFont(Font.font("Regular System", FontWeight.BOLD, 18.0));
            }
        }
        for(int i = 0; i < anchorPane0.getChildren().size(); i++) {
            if(anchorPane0.getChildren().get(i) instanceof Button button) {
                button.setStyle("-fx-font-size: 14");
            }
        }
        this.root = anchorPane0;
        this.theme_menu = theme_menu;
        this.board_menu = board_menu;
        this.mode_menu = mode_menu;
        this.time_slider = timeSlider;
        this.start_button = startButton;
        this.board_colors = board_colors;
        stage.setTitle("Boggle Game");
        stage.setScene(new Scene(anchorPane0));
        stage.show();
    }
    public void registerMouseListener(HomeViewController homeViewController) {
        start_button.setOnAction(homeViewController::switchToBoardPage);

    }
}
