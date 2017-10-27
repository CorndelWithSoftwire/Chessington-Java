package training.chessington.view;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.chessington.model.Game;
import training.chessington.model.InvalidMoveException;
import training.chessington.model.Move;

import java.util.HashSet;
import java.util.Set;

public class ChessApp extends Parent {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Game game;
    private GridPane grid;
    private Square selectedSquare;
    private Set<Square> validMoveSquares = new HashSet<>();

    public ChessApp(Game game) {
        this.game = game;
        buildDisplayBoard();
        redrawPieces();
        getChildren().add(grid);
    }

    private Square[][] squares = new Square[Game.SIZE][Game.SIZE];

    private void buildDisplayBoard() {
        grid = new GridPane();

        for (int row = 0; row < Game.SIZE; row++) {
            for (int col = 0; col < Game.SIZE; col++) {
                Square square = new Square(row, col);
                square.setOnMouseClicked(e -> onSquareClicked(square));
                grid.add(square, col, row);
                squares[row][col] = square;
            }
        }
    }

    private void onSquareClicked(Square square) {
        if (validMoveSquares.contains(square)) {
            onMoveMade(square);
        } else {
            onNewSquareSelected(square);
        }
    }

    private void onMoveMade(Square moveTo) {
        try {
            game.makeMove(new Move(selectedSquare.getCoordinates(), moveTo.getCoordinates()));
        } catch (InvalidMoveException e) {
            LOGGER.error("Invalid move attempted", e);
        }
        redrawPieces();
        resetHighlighting();
        validMoveSquares.clear();
        selectedSquare = null;

        if (game.isEnded()) {
            showResult(game.getResult());
        }
    }

    private void showResult(String result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game over!");
        alert.setHeaderText(result);
        alert.show();
    }

    private void onNewSquareSelected(Square square) {
        validMoveSquares.clear();
        resetHighlighting();
        selectedSquare = square;
        square.showAsSelected();
        for (Move move : game.getAllowedMoves(square.getCoordinates())) {
            Square targetSquare = squares[move.getTo().getRow()][move.getTo().getCol()];
            validMoveSquares.add(targetSquare);
            targetSquare.showAsMoveOption();
        }
    }

    private void redrawPieces() {
        for (int row = 0; row < Game.SIZE; row++) {
            for (int col = 0; col < Game.SIZE; col++) {
                squares[row][col].setPiece(game.pieceAt(row, col));
            }
        }
    }

    private void resetHighlighting() {
        for (int row = 0; row < Game.SIZE; row++) {
            for (int col = 0; col < Game.SIZE; col++) {
                squares[row][col].resetHighlighting();
            }
        }
    }
}
