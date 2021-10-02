package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowableMoves = new ArrayList<>();
        CornersOfRectangles(from, board, allowableMoves);

        return allowableMoves;
    }

    private void CornersOfRectangles(Coordinates from, Board board, List<Move> initAllowableMoves) {
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        // possible row and col diff pairs for knights
        int[] rowArray = {-2, -2, 2, 2, -1, -1, 1, 1};
        int[] colArray = {-1, 1, -1, 1, -2, 2, -2, 2};

        for (int index = 0; index < 8; index++) {
            int targetRow = fromRow + rowArray[index];
            int targetCol = fromCol + colArray[index];

            if (targetRow >= 0 && targetRow <= 7 && targetCol >= 0 && targetCol <= 7) {
                Coordinates to = new Coordinates(targetRow, targetCol);
                Piece targetPiece = board.get(to);
                if (targetPiece == null || targetPiece.getColour()!= this.colour ) {
                    initAllowableMoves.add(new Move(from, to));
                }
            }
        }
    }
}
