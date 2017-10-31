package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        return getDiagonalMoves(from, board).collect(Collectors.toList());
    }

    @Override
    public Bishop duplicate() {
        return new Bishop(colour);
    }
}
