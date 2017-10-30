package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        Stream<Coordinates> upLeftMoves = getMovesInDirection(from, c -> c.plus(-1, -1), board);
        Stream<Coordinates> upRightMoves = getMovesInDirection(from, c -> c.plus(-1, 1), board);
        Stream<Coordinates> downLeftMoves = getMovesInDirection(from, c -> c.plus(1, -1), board);
        Stream<Coordinates> downRightMoves = getMovesInDirection(from, c -> c.plus(1, 1), board);

        return Stream.of(upLeftMoves, upRightMoves, downLeftMoves, downRightMoves).flatMap(s -> s).map(c -> new Move(from, c)).collect(Collectors.toList());
    }
}
