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
        List<Move> moves = new ArrayList<>();
        switch (this.colour) {
            case BLACK: 
                if (from.getRow() == 1) {
                    Coordinates blackMove1 = new Coordinates(from.getRow()+1, from.getCol());
                    Coordinates blackMove2 = new Coordinates(from.getRow()+2, from.getCol());
                    moves.add(new Move(from, blackMove1));
                    moves.add(new Move(from, blackMove2));
                } else {
                    Coordinates blackMove1 = new Coordinates(from.getRow()+1, from.getCol());
                    moves.add(new Move(from, blackMove1));
                }
            case WHITE: {
                if (from.getRow() == 6) {
                    Coordinates whiteMove1 = new Coordinates(from.getRow()-1, from.getCol());
                    Coordinates whiteMove2 = new Coordinates(from.getRow()-2, from.getCol());
                    moves.add(new Move(from, whiteMove1));
                    moves.add(new Move(from, whiteMove2));
                } else {
                    Coordinates whiteMove1 = new Coordinates(from.getRow()-1, from.getCol());
                    moves.add(new Move(from, whiteMove1));
                }
            }
        }
        return moves; 
    }
}
