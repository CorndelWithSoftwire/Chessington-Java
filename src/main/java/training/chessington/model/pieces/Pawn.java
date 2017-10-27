package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        int rowStep = colour == PlayerColour.BLACK ? 1 : -1;

        Coordinates oneAhead = from.plus(rowStep, 0);

        if (!board.inRange(oneAhead)) {
            return moves;
        }

        List<Coordinates> diagonals = Arrays.asList(
                oneAhead.plus(0, 1),
                oneAhead.plus(0, -1)
        );

        for (Coordinates diagonal : diagonals) {
            if (board.inRange(diagonal) && containsEnemy(diagonal, board)) {
                moves.add(new Move(from, diagonal));
            }
        }

        if (squareBlocked(oneAhead, board)) {
            return moves;
        }
        moves.add(new Move(from, oneAhead));

        if (moved) {
            return moves;
        }

        Coordinates twoAhead = from.plus(rowStep * 2, 0);
        if (squareBlocked(twoAhead, board)) {
            return moves;
        }

        moves.add(new Move(from, twoAhead));
        return moves;
    }

    private boolean squareBlocked(Coordinates target, Board board) {
        return board.get(target) != null;
    }

    private boolean containsEnemy(Coordinates target, Board board) {
        Piece targetPiece = board.get(target);
        return targetPiece != null && targetPiece.getColour() == colour.getOpposite();
    }
}
