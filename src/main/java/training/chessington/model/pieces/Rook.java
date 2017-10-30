package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        Stream<Coordinates> leftMoves = getMovesInDirection(from, c -> c.plus(0, -1), board);
        Stream<Coordinates> rightMoves = getMovesInDirection(from, c -> c.plus(0, 1), board);
        Stream<Coordinates> upMoves = getMovesInDirection(from, c -> c.plus(-1, 0), board);
        Stream<Coordinates> downMoves = getMovesInDirection(from, c -> c.plus(1, 0), board);

        return Stream.of(leftMoves, rightMoves, upMoves, downMoves).flatMap(s -> s).map(c -> new Move(from, c)).collect(Collectors.toList());
    }

    private Stream<Coordinates> getMovesInDirection(Coordinates origin, Function<Coordinates, Coordinates> nextCoord, Board board) {
        Coordinates thisCoordinate = nextCoord.apply(origin);

        if (!board.inRange(thisCoordinate) || containsFriendlyPiece(thisCoordinate, board)) {
            return Stream.empty();
        }

        if (containsOpposingPiece(thisCoordinate, board)) {
            return Stream.of(thisCoordinate);
        }

        Stream<Coordinates> nextMoves = getMovesInDirection(thisCoordinate, nextCoord, board);

        return Stream.concat(nextMoves, Stream.of(thisCoordinate));
    }
}
