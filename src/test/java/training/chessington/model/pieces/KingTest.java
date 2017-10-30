package training.chessington.model.pieces;

import org.junit.Before;
import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KingTest {

    private Board board;
    private King king = new King(PlayerColour.WHITE);

    @Before
    public void setup() {
        board = Board.empty();
    }

    @Test
    public void kingCanMoveToAdjacentSquares() {
        // Arrange
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, king);

        // Act
        List<Move> allowedMoves = king.getAllowedMoves(coords, board);

        // Assert
        assertThat(allowedMoves).containsExactlyInAnyOrder(
                new Move(coords, coords.plus(1, 1)),
                new Move(coords, coords.plus(1, 0)),
                new Move(coords, coords.plus(1, -1)),
                new Move(coords, coords.plus(0, 1)),
                new Move(coords, coords.plus(0, -1)),
                new Move(coords, coords.plus(-1, 1)),
                new Move(coords, coords.plus(-1, 0)),
                new Move(coords, coords.plus(-1, -1))
        );
    }

    @Test
    public void kingCanTakeOpposingPiece() {
        // Arrange
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, king);

        Piece opponent = new Pawn(PlayerColour.BLACK);
        Coordinates opponentCoords = coords.plus(1, 0);
        board.placePiece(opponentCoords, opponent);

        // Act
        List<Move> allowedMoves = king.getAllowedMoves(coords, board);

        // Assert
        assertThat(allowedMoves).contains(new Move(coords, opponentCoords));
    }

    @Test
    public void kingCannotTakeFriendlyPiece() {
        // Arrange
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, king);

        Piece friendlyPiece = new Knight(PlayerColour.WHITE);
        Coordinates friendlyCoords = coords.plus(1, 0);
        board.placePiece(friendlyCoords, friendlyPiece);

        // Act
        List<Move> allowedMoves = king.getAllowedMoves(coords, board);

        // Assert
        assertThat(allowedMoves).doesNotContain(new Move(coords, friendlyCoords));
    }

    @Test
    public void kingCannotLeaveBoard() {
        // Arrange
        Coordinates coords = new Coordinates(0, 0);
        board.placePiece(coords, king);

        // Act
        List<Move> allowedMoves = king.getAllowedMoves(coords, board);

        // Assert
        assertThat(allowedMoves).containsExactlyInAnyOrder(
                new Move(coords, new Coordinates(0, 1)),
                new Move(coords, new Coordinates(1, 1)),
                new Move(coords, new Coordinates(1, 0))
        );
    }
}
