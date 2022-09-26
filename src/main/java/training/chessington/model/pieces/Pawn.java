package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Black;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        moves = getPawnMovesAsColor(this.colour, from, moves, board);
        return moves; 
    }

    public List<Move> getPawnMovesAsColor(PlayerColour colour, Coordinates from, List<Move> moves, Board board) {
        int startingRow = this.colour == PlayerColour.BLACK ? 1 : 6;
        Coordinates moveOneRow = this.colour == PlayerColour.BLACK ? new Coordinates(from.getRow()+1, from.getCol()) : new Coordinates(from.getRow()-1, from.getCol());
        Coordinates moveTwoRows = this.colour == PlayerColour.BLACK ? new Coordinates(from.getRow()+2, from.getCol()) : new Coordinates(from.getRow()-2, from.getCol());
        
        if (isEdge(from, colour)) {
            return moves;
        }

        if (from.getRow() == startingRow) {
            if (checkPawnCanMove(board, moveOneRow)) {
                moves.add(new Move(from, moveOneRow));
            }
            if (checkPawnCanMove(board, moveTwoRows)) {
                moves.add(new Move(from, moveTwoRows));
            }
        } else {
            if (checkPawnCanMove(board, moveOneRow)) {
                moves.add(new Move(from, moveOneRow));
            }
        }
        return moves; 
    }

    public boolean checkPawnCanMove(Board board, Coordinates to) {
        return board.get(to) == null ? true : false;
    }

    public boolean isEdge(Coordinates from, PlayerColour colour) {
        if (this.colour == PlayerColour.WHITE) {
            return from.getRow() == 0 ? true : false;
        } else {
            return from.getRow() == 7 ? true : false;
        }
    }
}
