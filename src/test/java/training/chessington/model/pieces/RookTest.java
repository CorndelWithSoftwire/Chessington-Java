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
    public void rookCanMoveHorizontallyAndVertically(){
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -1)));
    }

    @Test
    public void RookCanCaptureEnemyThatBlocksItsPath() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, rook);

        Piece pawnEnemyLeft = new Pawn(PlayerColour.BLACK);
        Coordinates pawnLeftCoords = new Coordinates(4, 0);
        board.placePiece(pawnLeftCoords, pawnEnemyLeft);

        Piece pawnEnemyTop = new Pawn(PlayerColour.BLACK);
        Coordinates pawnTopCoords = new Coordinates(2, 4);
        board.placePiece(pawnTopCoords, pawnEnemyTop);

        Piece pawnEnemyRight = new Pawn(PlayerColour.BLACK);
        Coordinates pawnRightCoords = new Coordinates(4, 6);
        board.placePiece(pawnRightCoords, pawnEnemyRight);

        Piece pawnEnemyBottom = new Pawn(PlayerColour.BLACK);
        Coordinates pawnBottomCoords = new Coordinates(5, 4);
        board.placePiece(pawnBottomCoords, pawnEnemyBottom);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, pawnLeftCoords));
        assertThat(moves).contains(new Move(coords, pawnTopCoords));
        assertThat(moves).contains(new Move(coords, pawnRightCoords));
        assertThat(moves).contains(new Move(coords, pawnBottomCoords));
    }

    @Test
    public void rookIsBlockedByAlly() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, rook);

        Piece allyPawn = new Pawn(PlayerColour.WHITE);
        Coordinates allyPawnCoords = new Coordinates(4,2);
        board.placePiece(allyPawnCoords, allyPawn);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(0, -1)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, -2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, -3)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, -4)));
    }
}
