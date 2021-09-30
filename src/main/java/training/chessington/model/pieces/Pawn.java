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
        List<Move> allowableMoves = new ArrayList<>();
        int direction = 1;
        if(this.getColour() == PlayerColour.WHITE) {
            direction = -1;
        }
        boolean itMoved = hasItMoved(from.getRow(), direction);
        // make the new coordinate object
        Coordinates to = from.plus(direction, 0);
        // add move object to list of move objects
        allowableMoves.add(new Move(from, to));

        if (!itMoved) {
            to = from.plus(direction * 2, 0);
            allowableMoves.add(new Move(from, to));
        }

        return allowableMoves;
    }

    public boolean hasItMoved(int fromRow, int direction) {
        int initRowBlack = 1;
        int initRowWhite = 6;

        // quick exit
        // black and row isnt 1  OR white and row isnt 6 then it moved
        if ( (direction == 1 && fromRow != initRowBlack) | (direction == -1 && fromRow != initRowWhite) ) {
            return true;
        }
        return false;

    }

}
