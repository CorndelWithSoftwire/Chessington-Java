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

    private void getCornersOfRectangle(Coordinates from, Board board,List<Move> initAllowableMoves, int rowInc, int colInc) {
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        int[] rowArray = {fromRow - rowInc, fromRow + rowInc};
        int[] colArray = {fromCol - colInc, fromCol + colInc};

        for(int r: rowArray) {

            // quick exit to next item
            if (r < 0 | r > 7) {
                continue;
            }

            for (int c: colArray) {
                // quick exit to next item
                if (c < 0 | c > 7) {
                    continue;
                }

                Coordinates to = new Coordinates(r, c);
                initAllowableMoves.add(new Move(from, to));

            }
        }
    }
}
