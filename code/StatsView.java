import boggle.BoggleGame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class StatsView {
    Stage stage;
    BoggleGame boggleGame;

    Button HomeButton;
    public StatsView(Stage stage, BoggleGame boggleGame) {
        this.stage = stage;
        this.boggleGame = boggleGame;
        initUI();
    }

    private void initUI() {
        String[] stats = boggleGame.getEndGameStats();
        AnchorPane anchorPane0 = new AnchorPane();
        anchorPane0.setPrefHeight(400.0);
        anchorPane0.setPrefWidth(600.0);
        Label label1 = new Label();
        label1.setPrefHeight(17.0);
        label1.setPrefWidth(349.0);
        label1.setLayoutX(61.0);
        label1.setLayoutY(75.0);
        label1.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        label1.setText(stats[1]);


// Adding child to parent
        anchorPane0.getChildren().add(label1);
        Label label2 = new Label();
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(349.0);
        label2.setLayoutX(61.0);
        label2.setLayoutY(112.0);
        label2.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        label2.setText(stats[2]);

// Adding child to parent
        anchorPane0.getChildren().add(label2);
        Label label3 = new Label();
        label3.setPrefHeight(17.0);
        label3.setPrefWidth(349.0);
        label3.setLayoutX(61.0);
        label3.setLayoutY(150.0);
        label3.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        label3.setText(stats[3]);

// Adding child to parent
        anchorPane0.getChildren().add(label3);
        Label label4 = new Label();
        label4.setPrefHeight(17.0);
        label4.setPrefWidth(349.0);
        label4.setLayoutX(61.0);
        label4.setLayoutY(186.0);
        label4.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        label4.setText(stats[4]);

// Adding child to parent
        anchorPane0.getChildren().add(label4);
        Label label5 = new Label();
        label5.setPrefHeight(17.0);
        label5.setPrefWidth(349.0);
        label5.setLayoutX(61.0);
        label5.setLayoutY(221.0);
        label5.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        label5.setText(stats[5]);

// Adding child to parent
        anchorPane0.getChildren().add(label5);
        Label label6 = new Label();
        label6.setPrefHeight(17.0);
        label6.setPrefWidth(349.0);
        label6.setLayoutX(61.0);
        label6.setLayoutY(256.0);
        label6.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        label6.setText(stats[6]);

// Adding child to parent
        anchorPane0.getChildren().add(label6);
        Label label7 = new Label();
        label7.setPrefHeight(17.0);
        label7.setPrefWidth(349.0);
        label7.setLayoutX(61.0);
        label7.setLayoutY(39.0);
        label7.setFont(Font.font("System", FontWeight.BOLD, 18.0));
        label7.setText(stats[0]);

// Adding child to parent
        anchorPane0.getChildren().add(label7);
        Button button8 = new Button();
        button8.setPrefHeight(34.0);
        button8.setPrefWidth(120.0);
        button8.setLayoutX(257.0);
        button8.setLayoutY(292.0);
        button8.setText("Home Page");
        button8.setFont(Font.font("System", FontWeight.BOLD, 14.0));
        button8.setMnemonicParsing(false);
        HomeButton = button8;
        button8.setOnAction(e -> {
            try {
                HomeView homeView = new HomeView(stage);
                HomeViewController homeViewController = new HomeViewController(homeView, boggleGame);
                homeView.registerMouseListener(homeViewController);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });


// Adding child to parent
        anchorPane0.getChildren().add(button8);
        stage.setTitle("Boggle Board");
        Scene scene = new Scene(anchorPane0);
        stage.setScene(scene);
        stage.show();
    }
//    public void registerMouseListener(HomeViewController homeViewController) {
//        HomeButton.setOnAction(StatsViewController::switchToHomePage);
//    }
}
