package com.internshala.connectfour;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
    private Controller controller;

    @Override
    // Mandatory to override Start method
    // Init & Stop are optional to override

    public void start(Stage primaryStage) throws Exception // Stage is outermost container of the app
    {
        System.out.println("Application Started");

        // Loader connects MyMain with FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane rootGridPane = loader.load(); // Loads rootNode as GridPane

        controller = loader.getController();
        controller.createPlayground();

        MenuBar menuBar = createMenu();

        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        // Matches the menuBar width with primaryStage width

        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().add(menuBar);

        Scene scene = new Scene(rootGridPane);

        // Setting the scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect 4");
        primaryStage.setResizable(false); // If you want to prevent the user from resizing the Stage
        primaryStage.show();
    }

    private MenuBar createMenu()
    {
        //File Menu
        Menu fileMenu = new Menu("File");

        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(event -> controller.resetGame()); // Replaced with Lambda

        MenuItem resetGame = new MenuItem("Reset Game");
        resetGame.setOnAction(event -> controller.resetGame()); // Replaced with Lambda

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        // allows for a horizontal Separator to be embedded within it

        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setOnAction(event -> exitGame());

        fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);

        //Help Menu
        Menu helpMenu = new Menu("Help");

        MenuItem aboutGame = new MenuItem("About Connect 4");
        aboutGame.setOnAction(event -> aboutConnect4());
        SeparatorMenuItem separatorItem = new SeparatorMenuItem();
        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(event -> aboutMe());

        helpMenu.getItems().addAll(aboutGame, separatorItem, aboutMe);

        // Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu); // Adding Menus to Menu Bar

        return menuBar;
    }
    private void aboutMe() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Developer");
        alert.setHeaderText("Rahul Shinde");
        alert.setContentText("I love to play around with code and create games."+
                "Connect4 is one of them, In free time"+
                "I like to spend time with nears and dears.");
        alert.show();
    }

    private void aboutConnect4() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Connect Four");
        alert.setHeaderText("How to Play");
        alert.setContentText("Connect Four is a two-player connection game" +
                " in which the players first choose a color and then take turns dropping colored discs from " +
                "the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down," +
                " occupying the next available space within the column. The objective of the game is to be the first to" +
                " form a horizontal, vertical, or diagonal line of four of one's own discs." +
                " Connect Four is a solved game. The first player can always win by playing the right moves.");
        alert.show();
    }


    private void exitGame() {
        Platform.exit();
        System.exit(0);
    }

    private void resetGame() {

    }


    public static void main(String[] args) {
        launch(args);
    }
}
