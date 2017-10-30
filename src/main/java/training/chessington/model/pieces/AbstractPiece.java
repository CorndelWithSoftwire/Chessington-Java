package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.PlayerColour;

public abstract class AbstractPiece implements Piece {

    private final Piece.PieceType type;
    protected final PlayerColour colour;
    protected boolean moved = false;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
    }

    @Override
    public void setMoved() {
        moved = true;
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }

    protected boolean containsFriendlyPiece(Coordinates to, Board board) {
        return board.hasPieceOfColourAt(to, colour);
    }

    protected boolean containsOpposingPiece(Coordinates target, Board board) {
        return board.hasPieceOfColourAt(target, colour.getOpposite());
    }
}
