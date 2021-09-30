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

        // check for obstruction
        boolean canMove = obstruction(from, board, direction);
        if (!canMove) {
            return allowableMoves;
        }

        // make the new coordinate object
        Coordinates to = from.plus(direction, 0);
        // add move object to list of move objects
        allowableMoves.add(new Move(from, to));

        // check if it can move two spaces
        boolean itMoved = hasItMovedAtAll(from.getRow(), direction);
        if (!itMoved) {
            to = from.plus(direction * 2, 0);
            // check if 2 squares away has a piece, only add to list of moves if loc is null
            if (board.get(to) == null) {
                allowableMoves.add(new Move(from, to));
            }
        }

        return allowableMoves;
    }

    public boolean hasItMovedAtAll(int fromRow, int direction) {
        int initRowBlack = 1;
        int initRowWhite = 6;

        // quick exit
        // black and row isnt 1  OR white and row isnt 6 then it moved
        if ( (direction == 1 && fromRow != initRowBlack) | (direction == -1 && fromRow != initRowWhite) ) {
            return true;
        }
        return false;
    }

    private boolean obstruction(Coordinates from, Board board, int direction) {
        // if black and one space below is occupied then cannot move
        // if white and one space above is occupied then cannot move
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        Coordinates coordsPieceInFront = new Coordinates(fromRow + direction, fromCol);

        Piece pieceInFront = board.get(coordsPieceInFront);

        if (pieceInFront == null) {
            return true;
        }
        return false;
    }

}
