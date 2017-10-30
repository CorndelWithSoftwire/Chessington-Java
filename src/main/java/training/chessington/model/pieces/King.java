package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();

        for (int rowDiff = -1; rowDiff <= 1; rowDiff++) {
            for (int colDiff = -1; colDiff <= 1; colDiff++) {
                if (colDiff == 0 && rowDiff == 0) {
                    continue;
                }

                Coordinates to = from.plus(rowDiff, colDiff);

                if (!board.inRange(to)) {
                    continue;
                }

                if (containsFriendlyPiece(to, board)) {
                    continue;
                }

                moves.add(new Move(from, to));
            }
        }

        return moves;
    }
}
