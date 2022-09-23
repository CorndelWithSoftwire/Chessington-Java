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
        switch (this.colour) {
            case BLACK: 
                moves = getPawnMovesAsColor(this.colour, from, moves);
            case WHITE: {
                moves = getPawnMovesAsColor(this.colour, from, moves);
            }
        }
        return moves; 
    }

    public List<Move> getPawnMovesAsColor(PlayerColour colour, Coordinates from, List<Move> moves) {
        int startingRow = this.colour == PlayerColour.BLACK ? 1 : 6;
        Coordinates moveOneRow = this.colour == PlayerColour.BLACK ? new Coordinates(from.getRow()+1, from.getCol()) : new Coordinates(from.getRow()-1, from.getCol());
        Coordinates moveTwoRows = this.colour == PlayerColour.BLACK ? new Coordinates(from.getRow()+2, from.getCol()) : new Coordinates(from.getRow()-2, from.getCol());

        if (from.getRow() == startingRow) {
            Coordinates blackMove1 = moveOneRow;
            Coordinates blackMove2 = moveTwoRows;
            moves.add(new Move(from, blackMove1));
            moves.add(new Move(from, blackMove2));
        } else {
            Coordinates blackMove1 = new Coordinates(from.getRow()+1, from.getCol());
            moves.add(new Move(from, blackMove1));
        }
        return moves; 
    }
}
