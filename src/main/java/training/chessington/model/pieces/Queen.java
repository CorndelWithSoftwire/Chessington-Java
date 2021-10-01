package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractPiece {
    public Queen(PlayerColour colour) {
        super(PieceType.QUEEN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        // get list of allowable moves from rook's get allow move method
        List<Move> allowableMoves = new Rook(this.colour).getAllowedMoves(from, board);
        // get list of allowable moves from bishop's get allow move method
        allowableMoves.addAll(new Bishop(this.colour).getAllowedMoves(from, board));

        return allowableMoves;
    }
}
