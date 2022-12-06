import boggle.BoggleGame;
import boggle.Dictionary;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeView{
    //    BoggleGame boggleGame;
    Stage stage;

    private AnchorPane root;
    ComboBox<String> board_menu;
    ComboBox<String> board_colors;
    ComboBox<String> mode_menu;
    ComboBox<String> theme_menu;
    private Slider time_slider;
    private Button start_button;

    HashMap color_mapping;

    HashMap board_size_mapping;

    HashMap mode_mapping;
    public HomeView(Stage stage) throws IOException {
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
                    "Light Mode",
                    "Dark mode",
                    "Purple",
                    "Green Blue"
            );
    ObservableList<String> modes_list =
            FXCollections.observableArrayList(
                    "Practice",
                    "Player vs Player"
            );

    private void initUI() {
        HashMap<String, ArrayList<String>> color_mapping = new HashMap<String, ArrayList<String>>();
        HashMap<String, Integer> board_size_mapping = new HashMap<String, Integer>();
        HashMap<String, String> mode_mapping = new HashMap<String, String>();
        mode_mapping.put("Practice", "PRACTICE");
        mode_mapping.put("Player vs Player", "MULTIPLAYER");
        board_size_mapping.put("4x4", 4);
        board_size_mapping.put("5x5", 5);
        board_size_mapping.put("6x6", 6);
        board_size_mapping.put("7x7", 7);
        color_mapping.put("Default", new ArrayList<>() {{
            add("#FFFFFF");
            add("#000000");
            add("#FFFFFF");
        }});
        color_mapping.put("Light Mode", new ArrayList<>() {{
            add("#FFFFFF");
            add("#000000");
            add("#0DB14B");
        }});
        color_mapping.put("Dark mode", new ArrayList<>() {{
            add("#000000");
            add("#FFFFFF");
            add("#6E005F");
        }});
        color_mapping.put("Purple", new ArrayList<>() {{
            add("#6E005F");
            add("#FFFFFF");
            add("#FF5733");
        }});
        color_mapping.put("Green Blue", new ArrayList<>() {{
            add("#18453B");
            add("#FFFFFF");
            add("#FF5733");
        }});
//        color_mapping.put("Default", new ArrayList<>(List.of("#000000", "#FFFFFF")));
//        color_mapping.put("Dark mode", new ArrayList<>(List.of("#FFFFFF", "#000000")));
//        color_mapping.put("Dark Blue", new ArrayList<>(List.of("#191970", "#FFFFFF")));
//        color_mapping.put("Light Blue", new ArrayList<>(List.of("#1E90FF", "#FFFFFF")));



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
//        Label timer = new Label();
//        timer.setLayoutX(187.0);
//        timer.setLayoutY(307.0);
//        timer.setText("Choose Time per Player Chance:");

// Adding child to parent
//        anchorPane0.getChildren().add(timer);
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
//        Slider timeSlider = new Slider();
//        timeSlider.setPrefHeight(14.0);
//        timeSlider.setMax(90.0);
//        timeSlider.setPrefWidth(405.0);
//        timeSlider.setShowTickLabels(true);
//        timeSlider.setLayoutX(119.0);
//        timeSlider.setLayoutY(358.0);
//        timeSlider.setValue(45.0);
//
//// Adding child to parent
//        anchorPane0.getChildren().add(timeSlider);
        Button startButton = new Button();
        startButton.setPrefHeight(25.0);
        startButton.setPrefWidth(102.0);
        startButton.setLayoutX(272.0);
        startButton.setText("Start Game");
        startButton.setLayoutY(315.0);
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
//        this.time_slider = timeSlider;
        this.start_button = startButton;
        this.board_colors = board_colors;
        this.color_mapping = color_mapping;
        this.board_size_mapping = board_size_mapping;
        this.mode_mapping = mode_mapping;
        stage.setTitle("Boggle Game");
        stage.setScene(new Scene(anchorPane0));
        stage.show();
    }
    public void registerMouseListener(HomeViewController homeViewController) {
        start_button.setOnAction(homeViewController::switchToBoardPage);

    }
}
