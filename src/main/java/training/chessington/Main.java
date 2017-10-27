package training.chessington;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import training.chessington.model.Board;
import training.chessington.model.Game;
import training.chessington.view.ChessApp;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Board board = Board.forNewGame();
        Game game = new Game(board);
        Parent chessBoard = new ChessApp(game);
        primaryStage.setTitle("Chessington");
        Scene scene = new Scene(new Group());
        primaryStage.setScene(scene);
        scene.setRoot(chessBoard);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
