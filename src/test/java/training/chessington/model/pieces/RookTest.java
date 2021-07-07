package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class RookTest {
    @Test
    public void whiteRookCanMoveInEmptyBoard() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-3, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(3, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(4, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -3)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -4)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 3)));
    }

    @Test
    public void blackRookCanMoveInEmptyBoard() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(5, 4);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-5, 0)));

        assertThat(moves).contains(new Move(coords, coords.plus(0, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 3)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -4)));
    }


    @Test
    public void whiteRookBlockedwithWhitePieces() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates initial = new Coordinates(6, 4);
        board.placePiece(initial, rook);

        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates coor = new Coordinates(4,4);
        board.placePiece(coor, pawn);

        // Act
        List<Move> moves = pawn.getAllowedMoves(initial, board);

        // Assert
        assertThat(moves).contains(new Move(initial, initial.plus(-1, 0)));
        assertThat(moves).doesNotContain(new Move(initial, initial.plus(-2, 0)));
    }
    @Test
    public void blackRookBlockedwithWhitePieces() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates initial = new Coordinates(6, 4);
        board.placePiece(initial, rook);

        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates coor = new Coordinates(4,4);
        board.placePiece(coor, pawn);

        // Act
        List<Move> moves = rook.getAllowedMoves(initial, board);

        // Assert
        assertThat(moves).contains(new Move(initial, initial.plus(-2, 0)));
        assertThat(moves).doesNotContain(new Move(initial, initial.plus(-3, 0)));
    }


}
