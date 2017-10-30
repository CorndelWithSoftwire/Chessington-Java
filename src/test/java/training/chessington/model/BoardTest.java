package training.chessington.model;

import org.junit.Test;
import training.chessington.model.pieces.Piece;

import static training.chessington.model.pieces.Piece.PieceType.PAWN;
import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BoardTest {
    @Test
    public void newBoardHasWhitePiecesAtBottom() {
        // Arrange
        Board board = Board.forNewGame();

        // Act
        Piece piece = board.get(new Coordinates(7, 0));

        // Assert
        assertThat(piece).isColour(PlayerColour.WHITE);
    }

    @Test
    public void newBoardHasBlackPiecesAtTop() {
        // Arrange
        Board board = Board.forNewGame();

        // Act
        Piece piece = board.get(new Coordinates(0, 0));

        // Assert
        assertThat(piece).isColour(PlayerColour.BLACK);
    }

    @Test
    public void canMovePiecesOnBoard() {
        // Arrange
        Board board = Board.forNewGame();

        Coordinates from = new Coordinates(6, 0);
        Coordinates to = new Coordinates(4, 4);

        // Act
        board.move(from, to);

        // Assert
        assertThat(board.get(from)).isNull();
        assertThat(board.get(to)).isColour(PlayerColour.WHITE).isPiece(PAWN);
    }

    @Test
    public void squaresOffBoardAreOutOfRange() {
        // Arrange

        // Act
        Board board = Board.empty();

        // Assert
        assertThat(board.inRange(new Coordinates(-1, -1))).isFalse();
        assertThat(board.inRange(new Coordinates(-1, 4))).isFalse();
        assertThat(board.inRange(new Coordinates(8, 4))).isFalse();
        assertThat(board.inRange(new Coordinates(4, -1))).isFalse();
        assertThat(board.inRange(new Coordinates(4, 8))).isFalse();
        assertThat(board.inRange(new Coordinates(8, 8))).isFalse();
    }

    @Test
    public void squaresOnBoardAreInRange() {
        // Arrange

        // Act
        Board board = Board.empty();

        // Assert
        assertThat(board.inRange(new Coordinates(0, 0))).isTrue();
        assertThat(board.inRange(new Coordinates(0, 7))).isTrue();
        assertThat(board.inRange(new Coordinates(7, 0))).isTrue();
        assertThat(board.inRange(new Coordinates(7, 7))).isTrue();
    }

    @Test
    public void returnsCorrectlyWhenQueryingForPiece() {
        // Arrange

        // Act
        Board board = Board.forNewGame();
        
        // Assert
        assertThat(board.hasPieceOfColourAt(new Coordinates(0, 0), PlayerColour.BLACK)).isTrue();
        assertThat(board.hasPieceOfColourAt(new Coordinates(0, 0), PlayerColour.WHITE)).isFalse();

        assertThat(board.hasPieceOfColourAt(new Coordinates(4, 4), PlayerColour.WHITE)).isFalse();
        assertThat(board.hasPieceOfColourAt(new Coordinates(4, 4), PlayerColour.BLACK)).isFalse();
    }
}