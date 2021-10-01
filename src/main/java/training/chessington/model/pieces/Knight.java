package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowableMoves = new ArrayList<>();

        // laying rectangle
        getCornersOfRectangle(from, board, allowableMoves, 1, 2);
        // standing rectangle
        getCornersOfRectangle(from, board, allowableMoves, 2, 1);

        return allowableMoves;
    }

    private void getCornersOfRectangle(Coordinates from, Board board,List<Move> initAllowableMoves, int rowIncrement, int colIncrement) {
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        int[] rowArray = {fromRow - rowIncrement, fromRow + rowIncrement};
        int[] colArray = {fromCol - colIncrement, fromCol + colIncrement};

        for(int r: rowArray) {
            // quick exit to next item
            if (r < 0 | r > 7) { continue; }
            for (int c: colArray) {
                // quick exit to next item
                if (c < 0 | c > 7) { continue; }
                Coordinates to = new Coordinates(r, c);
                // if target loc does not contain ally then add to list of allowable moves
                if (board.get(to) != null && board.get(to).getColour() == this.colour) { continue; }
                initAllowableMoves.add(new Move(from, to));
            }
        }
    }
}
