package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.function.Function;
import java.util.stream.Stream;

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

    private Stream<Coordinates> getValidTargetsInDirection(Coordinates origin, Function<Coordinates, Coordinates> nextCoord, Board board) {
        Coordinates thisCoordinate = nextCoord.apply(origin);

        if (!board.inRange(thisCoordinate) || containsFriendlyPiece(thisCoordinate, board)) {
            return Stream.empty();
        }

        if (containsOpposingPiece(thisCoordinate, board)) {
            return Stream.of(thisCoordinate);
        }

        Stream<Coordinates> nextMoves = getValidTargetsInDirection(thisCoordinate, nextCoord, board);

        return Stream.concat(nextMoves, Stream.of(thisCoordinate));
    }

    protected Stream<Move> getLateralMoves(Coordinates from, Board board) {
        Stream<Coordinates> leftMoves = getValidTargetsInDirection(from, c -> c.plus(0, -1), board);
        Stream<Coordinates> rightMoves = getValidTargetsInDirection(from, c -> c.plus(0, 1), board);
        Stream<Coordinates> upMoves = getValidTargetsInDirection(from, c -> c.plus(-1, 0), board);
        Stream<Coordinates> downMoves = getValidTargetsInDirection(from, c -> c.plus(1, 0), board);

        return Stream.of(leftMoves, rightMoves, upMoves, downMoves).flatMap(s -> s).map(c -> new Move(from, c));
    }

    protected Stream<Move> getDiagonalMoves(Coordinates from, Board board) {
        Stream<Coordinates> upLeftMoves = getValidTargetsInDirection(from, c -> c.plus(-1, -1), board);
        Stream<Coordinates> upRightMoves = getValidTargetsInDirection(from, c -> c.plus(-1, 1), board);
        Stream<Coordinates> downLeftMoves = getValidTargetsInDirection(from, c -> c.plus(1, -1), board);
        Stream<Coordinates> downRightMoves = getValidTargetsInDirection(from, c -> c.plus(1, 1), board);

        return Stream.of(upLeftMoves, upRightMoves, downLeftMoves, downRightMoves).flatMap(s -> s).map(c -> new Move(from, c));
    }
}
