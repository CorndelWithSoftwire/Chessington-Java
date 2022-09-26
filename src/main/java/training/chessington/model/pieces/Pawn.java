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
        Coordinates captureLeft = this.colour == PlayerColour.BLACK ? new Coordinates(from.getRow()+1, from.getCol()-1) : new Coordinates(from.getRow()-1, from.getCol()-1);
        Coordinates captureRight = this.colour == PlayerColour.BLACK ? new Coordinates(from.getRow()+1, from.getCol()+1) : new Coordinates(from.getRow()-1, from.getCol()+1);

        if (isEdgeRow(from, colour)) {
            return moves;
        }

        if (canCaptureLeft(board, from, colour)) {
            if(checkForEnemyPiece(board, captureLeft, colour)) {
                moves.add(new Move(from, captureLeft));
            }
        }
        
        if (canCaptureRight(board, from, colour)) {
            if(checkForEnemyPiece(board, captureRight, colour)) {
                moves.add(new Move(from, captureRight));
            }
        }

        if (from.getRow() == startingRow) {
            if (checkPawnCanMove(board, moveOneRow)) {
                moves.add(new Move(from, moveOneRow));

                if(checkPawnCanMove(board, moveTwoRows)) {
                    moves.add(new Move(from, moveTwoRows));
                }
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

    public boolean isEdgeRow(Coordinates from, PlayerColour colour) {
        switch(this.colour) {
            case WHITE:
                return from.getRow() == 0;
            case BLACK:
                return from.getRow() == 7;
            default:
                return false;
        }
    }

    public boolean canCaptureLeft(Board board, Coordinates from, PlayerColour colour) {
        Coordinates captureLeft = this.colour == PlayerColour.BLACK ? new Coordinates(from.getRow()+1, from.getCol()-1) : new Coordinates(from.getRow()-1, from.getCol()-1);
        
        if (captureLeft.getCol() < 0) {
            return false;
        }
        return true;
    }

    public boolean canCaptureRight(Board board, Coordinates from, PlayerColour colour) {
        Coordinates captureRight = this.colour == PlayerColour.BLACK ? new Coordinates(from.getRow()+1, from.getCol()+1) : new Coordinates(from.getRow()-1, from.getCol()+1);
        
        if (captureRight.getCol() > 7) {
            return false;
        }
        return true;
    }

    public boolean checkForEnemyPiece (Board board, Coordinates to, PlayerColour colour) {
        if(board.get(to) != null) {
            return !board.get(to).getColour().equals(colour);
        }
        return false;
    }
}
