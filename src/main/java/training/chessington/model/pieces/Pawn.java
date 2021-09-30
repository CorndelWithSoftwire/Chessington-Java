package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        // to make a list of move objects
        List<Move> allowMoves = new ArrayList<>();

        // increment counter direction
        int increment = 1;  // default is black

        // check colour of pawn and change direction
        if (this.getColour() == PlayerColour.WHITE) {
            increment = -1 ;
        }

        // make the new coordinate object
        Coordinates to = from.plus(increment, 0);

        // add move object to list of move objects
        allowMoves.add(new Move(from, to));

        return allowMoves;
    }
}
