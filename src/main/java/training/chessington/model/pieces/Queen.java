package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends AbstractPiece {
    public Queen(PlayerColour colour) {
        super(PieceType.QUEEN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        return Stream.concat(getLateralMoves(from, board), getDiagonalMoves(from, board)).collect(Collectors.toList());
    }

    @Override
    public Queen duplicate() {
        return new Queen(colour);
    }
}
